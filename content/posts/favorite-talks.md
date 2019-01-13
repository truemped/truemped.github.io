---
title: "Favorite Talks"
date: 2019-01-13T10:00:00+01:00
toc: false
images:
tags:
---

Earlier this month Monica Lent published a list of
[Tech talks that'll change how you think][0]. Every one on this list is awesome
and some have influenced me pretty heavily. Especially [Simple made Easy][3] by
Rich Hickey. To these I'd like to add a few from my list of favorite talks.

## [Full Stack Awareness][1]

In this, Arthur Bergman rants about how everything is broken. We, as engineers,
are so used to just connect a few systems and the new feature is working. We
take so many things for granted that we forget how underlying systems down to
the operating system work.

{{< youtube oebqlzblfyo >}}

## [Stop Rate Limiting! Capacity Management Done Right][2]

A very nice introduction into [Little's Law][5] and how often times rate
limiting your backend is actually a bad idea. When protecting your backend
system usually you limit the RPS per customer. Initially you negotiate the rate
limits during onboarding and then forget about the exact numbers. In normal
operations you then discover that client A needs more RPS while client B needs
less. So you limit requests for client A even though you have spare capacity.

There is also a nice implementation of the algorithm in Elasticsearch called
[Adaptive Replica Selection][6] that we are using in production for a while now.

{{< youtube m64SWl9bfvk >}}

## [How NOT to Measure Latency][4]

Measuring and reporting latency is commonly based on percentiles. The problem
here is that we are happy with reporting this one data point for our system.
In today's Microservices architectures this is problematic though. A real user's
request usually triggers multiple calls to our backend systems. But that means
the chances of a user experiencing the p99 latency or even the maximum latency
is very high.

{{< youtube lJ8ydIuPFeU >}}

[0]: https://monicalent.com/blog/2019/01/01/favorite-programming-talks/
[1]: https://youtu.be/oebqlzblfyo
[2]: https://youtu.be/m64SWl9bfvk
[3]: https://www.infoq.com/presentations/Simple-Made-Easy
[4]: https://youtu.be/lJ8ydIuPFeU
[5]: https://en.wikipedia.org/wiki/Little%27s_law
[6]: https://www.elastic.co/blog/improving-response-latency-in-elasticsearch-with-adaptive-replica-selection
