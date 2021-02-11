---
title: "Campaigns in Elasticsearch"
date: 2020-04-13T17:00:00+02:00
draft: false
toc: false
images:
tags:
  - elasticsearch
  - howto
---

In e-commerce search enabling dynamic prices or campaigns requires some
attributes to be more volatile than others. A discount on Black Friday that is
should only be valid between 7 PM and midnight. The flags that promote the
discount. The teasers on product listings showing what products will have a
discount, before the discount is in effect. Products that are only discounted
for a certain customer group.

Traditionally this is solved by updating the documents in the search index.
Around the time new information has to go live, all affected documents are
updated. There are at least two huge problems with this approach. The first one
is that updating a large portion of the index takes time. During large sales
events this easily means updating the whole index. Sometimes more than once if
your intake pipelines contain loops, e.g. This creates a huge load onto the
system which at this point can already have a lot of users looking for the deals
to become active. User facing errors are the last thing you would tolerate at
that time.

The second problem is the missing of an exact timing for the new information to
go live. The only possibility to have cross-document transactions in Lucene is
by not committing the new changes. Only when all changes have been processed you
run a `commit` once to make the new information visible to users. In an event
sourced system, the exact time at which a certain update is finished, may be
impossible to define. Additionally this operation is potentially heavy as you
might be reloading the whole index. Queries to this index then experience a time
of high latency as all caches need to be filled again.

# Modeling dynamic information as nested documents

Another solution to this is to model the data in a way that information becomes
`true` based on the time of the users request. The idea is quite simple: instead
of updating all documents, the times at which information is valid is encoded in
the documents themselves. Using Elasticsearch's nested documents the relevant
pieces are then extracted at query time. The timelines of when what part of the
information is visible can then be ingested long before and during times of very
low traffic.

I will describe the idea using a toy example. Two *products* are indexed and
they both have a list of `prices` as objects. These *price objects* contain the
price as well as the start and end date. Filtering and aggregating (aka
faceting) are then achieved using nested filters and aggregations.

## Index Setup

The index mapping describes the list of `prices` indexed as nested documents:

``` json {linenos=table}
{
  "mappings": {
  "properties": {
      "prices": {
        "type": "nested",
        "properties": {
          "price": {"type": "integer"},
          "from":  {"type": "date"},
          "to":    {"type": "date"}
        }
      }
    }
  }
}
```

Two documents will now be indexed. The first product is discounted between
*2020-03-17T18:00:00Z* and *2020-03-18T23:59:59Z*. It's normal price is 15 and
the discounted price is 12.

``` json {linenos=table}
{
  "prices": [
    {
      "price": 15,
      "from":  "2020-03-17T00:00:00Z",
      "to":    "2020-03-17T17:59:59Z"
    },
    {
      "price": 12,
      "from":  "2020-03-17T18:00:00Z",
      "to":    "2020-03-18T23:59:59Z"
    },
    {
      "price": 15,
      "from":  "2020-03-19T00:00:00Z",
      "to":    "2020-03-31T23:59:59Z"
    }
  ]
}
```

The second product is discounted between *2020-03-17T19:00:00Z* and
*2020-03-18T09:59:59Z*. It's normal price is 20 and the discounted price is 10.

``` json {linenos=table}
{
  "prices": [
    {
      "price": 20,
      "from":  "2020-03-17T00:00:00Z",
      "to":    "2020-03-17T17:59:59Z"
    },
    {
      "price": 10,
      "from":  "2020-03-17T19:00:00Z",
      "to":    "2020-03-18T09:59:59Z"
    },
    {
      "price": 20,
      "from":  "2020-03-19T00:00:00Z",
      "to":    "2020-03-31T23:59:59Z"
    }
  ]
}
```

## Constructing the price facet

All examples have a simulated time. In reality one would simply change the time
to something like *now/m*. This would allow for price changes to take effect
every minute while enabling internal caches in Elasticsearch. In the following
examples, *now* is explicitly set to a certain point in time.

The Elasticsearch query to retrieve the correct price contains a boolean filter.
This filter will select the correct *price object* based on a certain point in
time. The aggregation will then be performed based on those objects. In this
example, *now* is equal to *2020-03-17T13:00:00Z*, so before any discount was
given.

``` json {linenos=table}
{
  "size": 0,
  "query": {"match_all": {}},
  "aggs": {
    "nested_prices": {
      "nested": {"path": "prices"},
      "aggs": {
        "inner": {
          "filter": {
            "bool": {
              "must": [
                {
                  "range": {"prices.from": {"lte": "2020-03-17T13:00:00Z"}}
                },
                {
                  "range": {"prices.to": {"gte": "2020-03-17T13:00:00Z"}}
                }
              ]
            }
          },
          "aggs": {
            "prices": {
              "terms": {"field": "prices.price"}
            }
          }
        }
      }
    }
  }
}
```

Beginning with line 10 the filter for the currently active price object is
defined. In line 21 a nested aggregation, based on the filtered price objects,
creates the actual computation of the prices.

The result looks like this:

``` json {linenos=table}
{
  [...]
  "aggregations" : {
    "nested_prices" : {
      "meta" : { },
      "doc_count" : 6,
      "inner" : {
        "meta" : { },
        "doc_count" : 2,
        "prices" : {
          "doc_count_error_upper_bound" : 0,
          "sum_other_doc_count" : 0,
          "buckets" : [
            {
              "key" : 15,
              "doc_count" : 1
            },
            {
              "key" : 20,
              "doc_count" : 1
            }
          ]
        }
      }
    }
  }
}
```

In line 6 a `doc_count` of 6 is defined. This is the number of nested documents
that are in the index. For each of the two **products**, three nested documents
contain the price information at a given point in time. In line 9 the
`doc_count` is only two, as only two nested documents match the filter criteria.
Beginning on line 13 we see the two prices: 15 and 20.

Changing *now* to *2020-03-18T09:00:00Z* we expect to get the discounted prices
for both documents. Using the query from above but only changing the date
returns the following:

``` json {linenos=table}
{
  [...]
  "aggregations" : {
    "nested_prices" : {
      "doc_count" : 6,
      "inner" : {
        "doc_count" : 2,
        "prices" : {
          "doc_count_error_upper_bound" : 0,
          "sum_other_doc_count" : 0,
          "buckets" : [
            {
              "key" : 10,
              "doc_count" : 1
            },
            {
              "key" : 12,
              "doc_count" : 1
            }
          ]
        }
      }
    }
  }
}
```

Beginning in line 11 there are now the correct prices returned: 10 and 12.

## Filtering for price ranges

Based on the information the user has seen, they now want to filter for a
certain price range. For this to work the main `query` part of the query above
needs to filter for all documents, that contain a price within the range at a
given point in time. Again this is achieved using nested filters. The query from
above needs to change the query part in order to filter for prices between 5 and
11:

``` json {linenos=table}
{
  "size": 0,
  "query": {
    "bool": {
      "must": [
        {
          "nested": {
            "path": "prices",
            "query": {
              "range": {
                "prices.price": {
                  "gte": 5,
                  "lte": 11
                }
              }
            }
          }
        },
        {
          "nested": {
            "path": "prices",
            "query": {
              "range": {"prices.from": {"lte": "2020-03-18T09:00:00Z"}}
            }
          }
        },
        {
          "nested": {
            "path": "prices",
            "query": {
              "range": {"prices.to": {"gte": "2020-03-18T09:00:00Z"}}
            }
          }
        }
      ]
    }
  },
  "aggs": {
    "nested_prices": {
      "nested": {"path": "prices"},
      "aggs": {
        "inner": {
          "filter": {
            "bool": {
              "must": [
                {
                  "range": {"prices.from": {"lte": "2020-03-18T09:00:00Z"}}
                },
                {
                  "range": {"prices.to": {"gte": "2020-03-18T09:00:00Z"}}
                }
              ]
            }
          },
          "aggs": {
              "prices": {
                "terms": {"field": "prices.price"}
              }
            }
          }
        }
      }
    }
}
```

This query will return the following result:

``` json {linenos=table}
{
  "hits" : {
    "total" : {
      "value" : 1,
      "relation" : "eq"
    },
    "max_score" : null,
    "hits" : [ ]
  },
  "aggregations" : {
    "nested_prices" : {
      "doc_count" : 3,
      "inner" : {
        "doc_count" : 1,
        "prices" : {
          "doc_count_error_upper_bound" : 0,
          "sum_other_doc_count" : 0,
          "buckets" : [
            {
              "key" : 10,
              "doc_count" : 1
            }
          ]
        }
      }
    }
  }
}
```

In contrast to before only one document matched (line 4). This is also reflected
in the number of nested documents used for the aggregation (line 12). For the
final nested aggregation only one price object is used (line 14). Both, the
filtering for documents with a specific price and the aggregation at a certain
time work.

## Conclusion

This method allows the modeling of dynamic information that changes based on the
time a user issued a query. In the example above only prices were used but other
information like flags or availability can me modeled accordingly.

I see two drawbacks of this method. In case of quick adjustments to campaign
plans from commercial teams a full re-index is still required. There is just no
way around this. For this to get more efficient, Elasticsearch needs to support
updating doc values alone, without updating the whole document.

The second critical point is the data modeling and enforcement of constraints.
This method relies on the fact that only one nested document is within the time
range at any given point in time. Two documents, that overlap in their start or
end-date return invalid results, because the same product document is counted
twice. This constraint needs to be enforced at index time. In case were the
price information belongs to another team, this needs to be part of the contract
between the two teams. But this is a whole other story.

[0]: https://www.elastic.co/guide/en/elasticsearch/reference/current/nested.html
