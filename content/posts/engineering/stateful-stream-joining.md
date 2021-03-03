---
title: "Stateful Stream Joining"
date: 2021-02-17T19:00:00+02:00
draft: false
toc: false
images:
tags: 
  - microservice
  - stream processing
---

One of the main concepts I learned when working with services (micro or not) is
*stateful stream joining*. When moving from a monolithic architecture a new
problem emerges right away: how do the new services get their data?

A naive solution would be to set up read replicas. But in contrast of the
monolithic architecture where one central piece of code is *writing* data, now a
lot of services write data into their local databases. Different read replicas
would need to be setup and custom code would perform the actual data merging.
This has a number of critical challenges though. The first is the complexity
inherent in the solution. And complexity begets failure.

The second is how data consumers notice changes and react to it. I have actually
seen someone publish a *change* event to a queue and then reading the value from
a read replica. This is obviously a bad idea! Two asynchronous systems involved
in reacting to change. The event in the queue contains an ID for example which
is then read from a read replica. At this point it is impossible to know that
the data contained in the read replica contains the changes from the event in
the queue.

