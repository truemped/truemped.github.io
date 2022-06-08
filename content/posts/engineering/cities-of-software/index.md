---
title: "Cities of Software"
date: 2022-03-30T17:00:00+02:00
draft: false
toc: false
images:
  - /posts/engineering/cities-of-software/adrian-schwarz-XS7q-baZrmE-unsplash.jpg
tags: 
  - change
  - lean
  - event-streaming
  - microservice
---

{{< figure src="/posts/engineering/cities-of-software/adrian-schwarz-XS7q-baZrmE-unsplash.jpg"
    caption="Photo by [Adrian Schwarz](https://unsplash.com/@aeschwarz) on [Unsplash](https://unsplash.com/photos/XS7q-baZrmE)">}}


Cities are an interesting way to think about Software. Cities have foundations,
like infrastructure for cars, electricity. Housing for people to live and work.
Stores to sell and buy goods. Also, Cities are often referred to as an
_organism_. Just like a large organization, a department, or a team are an
_organism_. But these organisms are building software.

I first came across this idea in Mikio Braun's post on [Everyone Is Still
Terrible At Creating Software][0]:

> Cities can be thought as platforms for human activities. They provide basic
> infrastructure like roads, electricity, buildings, shop space you can rent,
> and now Internet. Some of these pieces of infrastructure change very slowly
> (like roads), while others are much more flexible, like the way apartments or
> shops are used.

He compares how organizations are not capable of building software that looks
and works like a city. Instead, if cities would look like the software companies
build, they would be pretty useless and dangerous to live in.

When we use a city as an analogy for an organization, the different areas or
blocks represent the parts of the organization. In e-commerce, there is one
block with a large bank for the checkout, a mall for the catalog and search, and
so on. The streets between the blocks depict the data that flows between the
different parts. Some streets are big, others small. There might even be
streets within a block, but by design small ones.

# The design of the streets dictate the effectiveness of the city

A too small street between two important blocks limits the interaction between
the blocks and has crippling effects on other blocks. If the trucks and cars
cannot use the street between two blocks, they might have to make detours to get
to their destination. If the street does not even exist, it needs to be built,
possibly replacing other infrastructure or even blocks.

Some trucks might have been designed in another country and are not even allowed
to use the streets built in your city. Are you making sure the 40t truck can
cross that bridge?

# How streets look like in Software

Just as the streets of a city define it's shape for decades, the design of the
data flow in an organization defines it's shape for a considerable amount of
time. This part of the architecture must have a careful design and not manifest
itself by accident and happenstance. The result of this design will outlast a
number of organizational changes in the future. And even enable them, or make
them hard.

In software (-architecture for that matter) there are two different types of
streets that people build: APIs and Event Streams. APIs are a simple way for
system A to provide a service for other systems to consume or leverage. For
example, a system that exposes if an article is in stock, or the current price of
that article.

In an Event Streaming environment, system A would publish an event describing
the fact that the article is in stock. Another system would publish an event
containing the price. A third system responsible for showing a catalog or
offering search capabilities, e.g., would then need to merge these streams in
order to have the final data in shape for the use-case.

## API-First

I believe the core idea of API-first was originally invented by Jeff Bezos' API
mandate:

> 1. All teams will henceforth expose their data and functionality through
>    service interfaces.
> 2. Teams must communicate with each other through these interfaces.
> 3. There will be no other form of interprocess communication allowed: no
>    direct linking, no direct reads of another team’s data store, no
>    shared-memory model, no back-doors whatsoever. The only communication
>    allowed is via service interface calls over the network.
> 4. It doesn’t matter what technology they use. HTTP, Corba, Pubsub, custom
>    protocols — doesn’t matter.
> 5. All service interfaces, without exception, must be designed from the ground
>    up to be externalizable. That is to say, the team must plan and design to
>    be able to expose the interface to developers in the outside world. No
>    exceptions.
> 6. Anyone who doesn’t do this will be fired.
> 7. Thank you; have a nice day![^1]

The interesting bit in this is that _API First_ is an interpretation of the
memo. He actually mentions _pubsub_ as a means to communicate. Yet, the majority
of people first think about APIs.

What happens over time is as we move on, build new features, change strategies
and course, we keep adding more APIs. We add more calls to the system in order
to get the data we need for the new feature to work.

Eventually we end up with a call graph so complex that hardly any single
engineer can still understand how all of this actually works. Suddenly, this one
request the user issued, is calling 10, 20, 30 different APIs. How do you debug
latency issues? Evolving the different SLOs over time is a challenge -- they all
depend on one another. And how do you remove this one API in the middle?

Debugging latency is simple: we add distributed tracing. Another vendor,
additional technology that provides us a filtered view on what is going on,
because the amount of tracing data would explode any cloud storage. Those
systems are not really cheap as well. And you need all teams to implement it.

Teams negotiate their SLOs and their rate limits. Maybe we reach a stable state
and everything works. But then the next change is around the corner. SLOs and
rate limits don't match anymore. Your _local_ project now depends on other teams
with potential crippling effects.

And with changes we have to adapt the APIs and their schema. Over time,
different clients start using the API with different needs. Evolving the schema
becomes non-trivial. For one use case a field might be optional, but for another
it is required. The complexity for the API provider increases with every new
feature. Maintenance and improvements are getting more difficult. And for use
case A the latency profile might be a match, but use case B requires the
response in a tenth of the latency. The technology choices might not match
anymore, only a different storage system for the two use cases can match the
competing requirements.

## Events first

An alternative to this is to design the streets as a stream of events. These
events describe the data when it was created, updated, or deleted. And they
follow a centrally available schema model. Clients that require the data need
the schema which they can simply pull from the "schema registry".

Clever schema implementations can even allow for decentralized schema changes as
long as they are compatible.  Depending on how careful you design and evolve
your schemata, you can even change data producers and consumers independently
of one another. Only if the data consumer actually needs the new data, they
need to update their code base with the new schema design.

The underlying mechanism that enables all of this are log-based event streaming
systems. They are based on a simple insight: we need to distribute data the same
way as databases do this when they replicate. We do not want to share the
Database between multiple services and only one system can write to the
database. But we can scale the reads by adding "read replicas". Systems can
join the different streams to map the data into an optimized version for their
use case.

Log-based event streaming systems have two different modes of operation. They
can act as a simple message queue, a never ending stream of events. But they
also act as a _table_ of data. Kafka for example can compact a stream and remove
duplicate messages for the same key, only retaining the last value that was
published.

With this you can add, remove, or adapt data consumers at any point in time,
without even talking to the data producer. There is only one point where the
consumer maps producer's schema to the schema used internally. The consumer is
responsible for their schema and not the producer.

The city block responsible for the customer _domain model_ publishes details
about the customers. The city block responsible for order fulfillment is only
interested in a subset of the data and can map the data before it enters their
city block.

# What now?

Over time, we have created these literate _big balls of mud_, an illusion of a
microservice architecture, that promised autonomy. Instead, we got endless
alignment. We need to untangle this if we want to enable change. The more we
bake our assumptions into code and relationships between APIs, the harder change
becomes.

The gist of this is: focus on designing the streets. The way data flows through
an organization and its systems is the bottleneck in regard to change. The more
complex this flow is, the more difficult change becomes. And change is
inevitable.

This is also a great point to add a Russel Ackoff (of course!) quote:

> Performance of a system depends on how the parts interact, not on how they act
> taken separately.

# Additional Resources

There is a nice interview with Scott Havens on the Idealcast: [Simplifying The
Inventory Management Systems at the World’s Largest Retailer Using Functional
Programming Principles][1]. In this episode Havens describes their journey from
migrating a convoluted and nested set of APIs to a system built on event
streaming foundations. This is a great resource to start understanding this
better. And Gene Kim is a great proponent of adaptability.

Martin Kleppmann pioneered this in 2014(!) in his talk ["Turning the database
inside out with Apache Samza"][2]. One of my all-time [favorites][3]!

[0]: https://mikiobraun.wordpress.com/2021/04/05/creating-software-at-scale/

[^1]: I could not find a real source for that, only other's citing it with a
    note, that the memo might have become a myth by now, albeit a good one...

[1]: https://itrevolution.com/the-idealcast-episode-23

[2]: https://www.youtube.com/watch?v=fU9hR3kiOK0

[3]: {{< relref "/posts/favorite-talks#turning-the-database-inside-out-with-apache-samza" >}}
