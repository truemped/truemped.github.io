---
title: "Monolith to Microservice"
date: 2020-11-16T11:00:00+02:00
draft: false
toc: false
images:
tags: 
  - microservice
  - monolith
aliases:
  - "/posts/engineering/monolith-to-microservice/"
---

Why is moving from a monolithic architecture to a microservices based one so
hard? And is it worth it? Often those projects happen in organizations that
were successful in the past and now enter a phase of scaling. This can be
scaling by adding lots and lots of developers, or by allowing individual scaling
of sub-components, because the system is screaming under load and is getting
more and more expensive to run.

In both microservice transitions I've participated in I would argue that the
project did not achieve the returns everyone hoped for. Instead, they added a
lot of complexity that kept everyone moving forward slow and painstaking. I see
three main reasons for both: 1/ focus on tech not people, 2/ messing with
physics, and 3/ Conway's Law.

# It's the people, not the tech

The aspect I don't think is getting enough attention is the organization's
culture. There is a reason why the monolith became unmanageable: the
accumulation of missed code refactoring, no cleaning up of failed experiments,
no boundaries between subsystems, and finally the layers of *hacks* on top of
one another.

Culture is the sum of all actions and in this case the lack of action. Moving to
a microservice architecture will not change this behavior. On the contrary,
while building the new services, the same lack of action will bring the same
problems to the new systems. People are creatures of habit and changing habits
is hard. Ignoring this will eventually lead to the same mess as in the monolith.
Only this time a lot worse as it now is a distributed monolith.

Before starting the microservice transition the leadership team must take
deliberate steps to change those habits and behaviors. The Shopify engineering
team wrote a great [article][1] on how they address this in their organization:

> That’s why making a fundamental architecture change to a system that’s being
> actively worked on is in large part a people problem.

As described in the first of the [Six Simple Rules][2] it is important to
*Understand what people do*. Only then one can reason about behavior and provide
ways to change them. All of this can even be done before the migration is
started.  There is no reason to wait changing this behavior.

# Messing with physics

Another aspect is the lack of understanding of the hard truths about a
microservice architecture, especially in management.

A key part of any software system is the data it stores and serves. In the
monolith there was one system running code and one (sometimes two, when adding
search capabilities) serving data. The new architecture needs multiple systems
to store, manage, and serve data.

The interesting question is to organize the *flow* of data across services and
the organization? What parts of the organization are responsible to create and
manage what data? What other parts of the organization need that data and how to
get it to them? What are the responsibilities of the data producing team and
what are those of the consumers?

In my experience the best system to create this data foundation is using [Apache
Kafka][3] and the ideas expressed in the talk [Turning the Database inside
out][4]. At it's core, Kafka is what Databases have used for decades to
replicate data in order to scale read traffic: a log. Every event creates a new
entry in the log. The combination of ideas from [Change Data Capture (CDC)][5]
and what Kafka calls [log compaction][6], forms the basis of a system that
provides solutions to all the questions raised before. Every event in the system
is idempotent and since the order is preserved, the latest event is always the
correct one. Log compaction keeps the data at rest manageable as only the latest
version(s) of each datum is stored. And stateful stream processing enables teams
to build on top of other team's output.

When not starting with breaking up the data layer, there will be at least two
steps in the migration. First, moving the serving or reading layer out of the
monolith. While this is happening, the data providing teams are busy building
the new data layer for the new architecture. The reading systems still call the
underlying existing databases.

The second migration will then be moving all the new services to the new data
layer. For many teams this means another re-write of their service. The data
acquisition is not a call to a data base anymore and teams need to create their
own *materialized view* off of Kafka.

Finally I believe it is critically to state that one needs to use Kafka as a
Database and not an event broker. The difference is subtle, but the consequences
are dramatic. I once worked on a system where locally, in the teams, people were
using stateful stream joining. Between the teams though, data was just
forwarded. Essentially it was a chain of updates and not multiple different
tables that consuming teams could re-arrange to create a representation they
required. Teams always had to consume everything, even if they were only
interested in parts of the data.

# Team Topologies

The final aspect I want to mention is *Conway's law*. The organization
leadership creates resembles the architecture it will come up with. This
requires clear communication of the team's objectives and what they are
responsible for.  But not only for the team alone, also for the interactions
between teams and other parts of the organization. As Russell Ackoff states:

> Performance of a system depends on how the parts interact, not on how they act
> taken separately.

What does that mean for the Microservice transition? First, start building the
organization based on the target architecture that was agreed upon. This usually
requires a re-organization for which requires buy-in from the people in the
organization. Not having this buy-in ruins the project from the start as
management will not have everyone working for, but against them.

# Microservices don't solve the problems

The transition to microservices does not necessarily solve problems, but create
new ones. Understanding the people/company/culture side of the organization
before pushing through with such a (company-)life changing project is critical
to success.

> Spreading an understanding of what we’re aiming for, and why, is critical.
>
> Philipp Müller in [Under Deconstruction: The State of Shopify’s Monolith][1]

First, start changing the habits and behaviors of the people, then the tech
stack. Don't fall prey to *trying to solve people issues with tech*. That never
works.

[1]: https://shopify.engineering/shopify-monolith
[2]: https://www.goodreads.com/book/show/18249372-six-simple-rules
[3]: https://kafka.apache.org/
[4]: https://www.youtube.com/watch?v=fU9hR3kiOK0
[5]: https://en.wikipedia.org/wiki/Change_data_capture
[6]: https://www.confluent.io/blog/log-compaction-highlights-in-the-apache-kafka-and-stream-processing-community-june-2017/
