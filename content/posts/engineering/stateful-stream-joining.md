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

One of the most influential concepts I learned when working with services (micro
or not) is *stateful stream joining*. When moving to a service oriented
architecture a new problem emerges right away: how do the new services get their
data? Instead of one single main database, data governance is distributed
across the organizational landscape. Multiple teams are responsible for
different parts of the data. In an e-commerce environment there might be one
team responsible for the raw product data and another one for the prices. In
another organization there could be a team responsible for managing product
visibility for customers.

There are as many solutions to this issue as there are choices for queuing
technology today. I have actually seen someone publish a *change* event to a
queue and then reading the value from a database read replica. This is obviously
a bad idea! Two asynchronous systems involved in reacting to change. The event
in the queue contains an ID for example which is then read from a read replica.
At this point it is impossible to know that the data contained in the read
replica contains the changes from the event in the queue.

