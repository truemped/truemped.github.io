---
title: "Varnish All the Things"
date: 2016-02-01T12:15:09+01:00
draft: false
toc: false
images:
tags: 
  - varnish
  - caching
  - infrastructure
aliases:
  - "/posts/2016-02-01-varnish-all-the-things.html"
  - "/posts/infra/varnish-all-the-things/"
---

My usual web application stack for the past years was based on  a nginx as
reverse proxy in front of a number of Python processes. Static resources were
served by nginx. Each Python process was stateless, state was stored in some
kind of database. If the processes needed some shared ephemeral state like
counters a local redis instance solved that. A battle tested common ground for
Python based web applications.

What I have added to this lately was [Varnish](https://www.varnish-cache.org/),
a powerful proxying cache. The first that comes in mind could be "there are two
problems in computer science: naming things and cache invalidation".  Agreed,
invalidation is tricky. But there are several tricks at hand that make this not
so bad at all.

Note: all examples should work with Varnish 4.0 and greater. If not, drop me a
line!

### Caching for a very short period

The first trick is to cache for only a very short amount of time, say 10
seconds or maybe even only 1:

```bash
Cache-Control: public, max-age=10
```

Say you have a resource that is being hammered a lot and it should be "near
real-time". Varnish will cache this response for 10 seconds. Now after the 10
seconds a new version must be computed. At this point Varnish will queue the
incoming requests and only forward one of them to the backend. All "parked"
requests will then get the response from the backend. Only one request to the
backend every 10 seconds, but there could be billions to Varnish that don't
bother you.

### Grace time

Say you chose to cache for only one second. Everything will be fine then if the
backend is able to compute a new version within one second. If it occasionally
takes longer then users will have to wait for the backend response. Having
users to wait is bad as you might be blocking the browser to render (which
you should be avoiding by other means!) or the user sees the loading symbol
even though all other elements are already loaded.

Here the grace time helps. It basically means that Varnish will return a stale
cache object until the backend is done computing the new version. So let's say
the backend sets this header:

```bash
Cache-Control: public, max-age=1
```

In the Varnish VCL you then add:

```bash
sub vcl_backend_response {
    set beresp.grace = 1m;
}
```

This will allow your backend to take up to one minute to compute the new
version. During this time Varnish will deliver the old version to all incoming
requests. As soon as the backend is finished the new version will be delivered.


### Grace in times of unhealthy backends

Grace time can also be extended in order to serve content while the backend is
down. For this you need to enable health checking for the backend like so:

```bash
backend server1 {
    .host = "server1.example.com";
    .probe {
        .url = "/_health";
        .timeout = 1s;
        .interval = 5s;
        .window = 5;
        .threshold = 3;
    }
}
```

This tells varnish to check the **/_health** endpoint every five seconds. The
backend needs to answer within one second. If three of the last five checks
were successful Varnish considers this backend to be healthy.

Now Varnish needs to detect failure and deliver stale content in order to have
some time fixing backends. For this we need to modify the *vcl_hit* method to
increase the grace time to a very large value during times no healthy backend
is available.

```bash
sub vcl_hit {
    # a cache hit
    if (obj.ttl >= 0s) {
        return (deliver);
    }
    # object is expired
    if (std.healthy(req.backend_hint)) {
        # but we have a healthy backend
        if (obj.ttl + obj.grace > 0s) {
            # object is within the grace period
            return (deliver);
        } else {
            # object is outside the grace persiod, fetch a new version
            return(fetch);
        }
    } else {
        # no healthy backend available, deliver old version for two hours
        if (obj.ttl + 2h > 0s) {
            return (deliver);
        } else {
            # after two hours we still have no healthy backend, now signal
            # the failure to the user
            return (fetch);
        }
    }
}
```

If you now manage to repair the backend within two hours the user will not
*see* the outage except maybe for old content.


### Purging the cache

In order to remove a cache object a special HTTP verb can be used.  By simply
accessing the resource that should be removed with a *PURGE* verb, e.g.,
Varnish can dismiss the object. Again this is controlled in the VCL itself:

```bash
sub vcl_hit {
    if (req.request == "PURGE") {
        return(purge);
        return(synth(200, "Purged"));
    }
}
sub vcl_miss {
    if (req.request == "PURGE") {
        return(purge);
        return(synth(200, "Purged"));
    }
}
```

Now executing something like

```bash
$ curl -XPURGE "http://www.example.com/recent"
```

would purge the cached object. The next **GET** request issues a new fetch from
the backend. Obviously not everyone should be allowed to purge the cache so a
simple ACL with white listed IPs blocks unwanted access:

```bash
acl purge {
    "localhost";
    # add a list of allowed IPs here
}
```

Then use it to block access from any other machine:

```bash
sub vcl_hit {
    if (req.request == "PURGE") {
        if (!client.ip ~ purge) {
            return(synth(405, "Not allowed."));
        }
        return(purge);
        return(synth(200, "Purged"));
    }
}
```

### Cache invalidation based on content

Sometimes invalidation based on URLs is not sufficient. Let's say one of the
articles from the big publishing house had to be removed for legal reasons and
you absolutely don't want to show it anymore. Invalidating the whole cache
would place quite some load on the backend. Maybe even too much load. In this
case banning objects based on its content enables one to only invalidate the
*affected* objects.

For this we need to introduce a custom HTTP response header from the backend.
This could be something like a list of article ids:

```bash
Content-Articles: 1,2,3,4,5
```

Varnish will store this header along with the object. In order to invalidate
all cached objects containing article 3 an artificial endpoint in the VCL is
introduced:

```bash
sub vcl_recv {
    if (req.url ~ "/_ban" && req.method == "BAN") {
        ban("obj.http.Content-Articles ~ " + req.http.Ban-Article);
        return(synth(204, "NO CONTENT"));
    }
}
```

The request to ban article 3 would now be:

```bash
$ curl -XBAN -H 'Ban-Article: 3' 'http://www.example.com/_ban'
```

Again securing via ACL is trivial and left out for clarity.

Banning works slightly different from purging. With purging the object is
removed instantly. For each call to ban, Varnish keeps a list of banning
statements that live for a longer period of time. When a cached object is
requested all of the banning statements are executed against the cached object.
If one matches, the object is fetched from the backend and not served. Once all
objects have been visited, the banned statement is removed.

In the case of rarely accessed objects, this might never happen. For this the
**ban lurker** thread will iterate over the otherwise missed objects and remove
them when necessary.

### Conclusion

I hope interest was raised if it has not been present so far. Varnish has
helped me a lot over the years and saved a lot of money by reducing the amount
of servers necessary for the individual project.

#### Resources

* [Varnish 4 documentation][docs]

* [Grace time in Varnish 4][grace]

* [Health checks][health]

* [Ban lurker][banlurker]


[docs]: https://www.varnish-cache.org/docs/4.0/

[grace]: http://info.varnish-software.com/blog/grace-varnish-4-stale-while-revalidate-semantics-varnish

[health]: https://www.varnish-cache.org/docs/4.0/users-guide/vcl-backends.html#health-checks

[banlurker]: http://info.varnish-software.com/blog/ban-lurker
