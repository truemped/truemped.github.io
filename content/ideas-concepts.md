---
title: "Ideas and Concepts"
date: 2022-03-03T08:30:00+01:00
draft: false
comments: false
toc: true
images:
---

I have come across a number of ideas and core concepts that have influenced my
thinking. They cover areas of building Software, designing teams and
organizations, decision-making, and mental models. On this page I'm keeping a
reference to them and will link to background material. My idea is to keep
references to the in my blog posts.

# The Three Ways

The three ways are a concept or idea introduced in [The Phoenix Project][1], by
Gene Kim, Kevin Behr, and George Spafford. In this novel, they describe a
hypothetical company, a car parts shop that is in the process of digitalization
and establishing itself as an e-commerce store.  Through the story the main
character learns and describes different aspects of managing teams and projects.
The influences are obvious: the Toyota Production System, Lean Processes, the
Theory of Constraints. And the novel itself was heavily influenced by Eliyahu
Goldratt's [The Goal][2] and the [Theory of Constraints]({{<
relref "#theory-of-constraints" >}}).

## Fast Flow

> “The First Way helps us understand how to create fast flow of work as it moves
> from Development into IT Operations, because that’s what’s between the
> business and the customer.
>
> [The Phoenix Project][1]

The idea is simple and yet powerful: whatever gets in the way of successful
completion of a project is a problem. This can mean a lot of different things
in different contexts, but the goal is to ensure a fast delivery of features
and changes.

Think of it like the flow of water through rivers. It can be about the flow of
information through the organization, especially about project updates. The flow
of a team working on code together. The flow of data through the technical
organization.

## Feedback Loops

> The Second Way shows us how to shorten and amplify feedback loops, so we can
> fix quality at the source and avoid rework.
>
> [The Phoenix Project][1]

Essential to any type of learning or improvement are feedback loops. The way a
feedback loop works, is a crucial integration part of how the system can improve
over time. This can be a social system like a team, but also a technical system.

## Learning Culture

> And the Third Way shows us how to create a culture that simultaneously fosters
> experimentation, learning from failure, and understanding that repetition and
> practice are the prerequisites to mastery.”
>
> [The Phoenix Project][1]

A fundamental attribute of a successful organization is adaptability, which is
not possible without the ability to learn. But learning requires a lot of effort
and attention to detail. Especially on how the organization is equipped to deal
with failure.

# The Five Ideals

In the [The Unicorn Project][0], a follow-up book to the [The Phoenix
Project][1], Gene Kim introduces the five ideals. The scene is the same as the
predecessor, but with a focus on the engineering part instead of management.
They are a set of principles that help build successful teams and organizations.

## Locality and Simplicity

> We need to design things so that we have locality in our systems and the
> organizations that build them.
> 
> [The Unicorn Project][0]

This sounds so simple, but in practice I think it is difficult to implement and
adapt this over time.  Organizations grow, strategies adapt, people come and go.
This all requires constant change and evaluation: "can we still take decisions
locally?" Over time I've seen organizations adapt to the changing business
demands, but the technical systems do not evolve along with it. Inevitably this
leads to a situation where the organization does not reflect the technical
landscape anymore. Shortcuts will be taken, systems will be implemented multiple
times.

## Focus, Flow, and Joy

> “The Second Ideal is Focus, Flow, and Joy. It’s all about how our daily work
> feels.
> 
> [The Unicorn Project][0]

People do not like multitasking.  And if they do, they will eventually end up
burning out.  _Focus_ is about this: focus on one thing at a time.  When in
_flow_, the tasks and especially their fulfillment can bring _joy_, a sense of
achievement.  People are engaged.

Engineers are familiar with *the flow*, when you *get in the zone* and this is
the psychological aspect of flow:

> In essence, flow is characterized by the complete absorption in what one does,
> and a resulting transformation in one's sense of time.
>
> [Wikipedia][2]

How to get into flow? Remove all distractions, turn off notifications, only
check mails in the evening.  Being in the flow is the most extreme form of
focus.  And this is what it is really about: focus.

## Improvement of Daily Work

> The Third Ideal is Improvement of Daily Work. Reflect upon what the Toyota
> Andon cord teaches us about how we must elevate improvement of daily work over
> daily work itself.
> 
> [The Unicorn Project][0]

The Toyota Production System is one of the early examples of a social system
optimizing for improvement. The Andon cord resembles this idea: anyone working
in the production line can pull the cord when they notice something is not
working. This does trigger curiosity and everyone sees this as a welcomed
opportunity to learn about something and improve the overall system.

This has obvious connections to:

## Psychological Safety

> The Fourth Ideal is Psychological Safety, where we make it safe to talk about
> problems, because solving problems requires prevention, which requires
> honesty, and honesty requires the absence of fear. In manufacturing,
> psychological safety is just as important as physical safety.
> 
> [The Unicorn Project][0]

I wrote [On Psychological Safety]({{< relref
"/posts/mgmt/psychological-safety.md" >}}) before where I try to highlight how
easy it is to destroy and how difficult it is to keep. If people do not feel
safe to speak up, they will not pull the Andon cord and not tell you about the
great improvement they would like to make. There is another great quote by
Russell Ackoff (of course):

> If you want your subordinates to be stupid, they'll be stupid, because they
> don't want to disappoint you.

## Customer Focus

> And finally, the Fifth Ideal is Customer Focus, where we ruthlessly question
> whether something actually matters to our customers, as in, are they willing
> to pay us for it or is it only of value to our functional silo?”
> 
> [The Unicorn Project][0]

Amazon is famous for being "customer obsessed". But I think this goes even
broader. Every team has a customer, whether they are aware of it or not. For
example the platform team in your company has a lot of other teams as their
customer. Often times they are not aware of this and instead implement cool
technical solutions -- that their customer might not even need right now.

Another great transition to:

# Team Topologies

The book [Team Topologies][3] by Matthew Skeleton and Manuel Pairs quickly became
a must-read in my management and principal engineering circle. It introduces a
number of great mental models for teams and their expected behavior.

## Four Fundamental Team Topologies

They mention four types of teams: *stream-aligned* (think end-to-end) teams that
can produce features without dependencies; *enabling* teams that try to
understand the needs of a stream aligned team and help them succeed;
*complicated subsystems* solve technically hard problems for the stream aligned
teams (Search for example); *platform* teams that 10x the stream aligned teams
by treating the platform as a product.

## Cognitive Load

This almost touches the aspects of building organizations. The insight is that
good team boundaries reduce cognitive load. In the example of a stream aligned
team and a platform team, the goal of the platform team is to reduce their
cognitive load.

Skelton and Pais identify three types of cognitive load: *intrinsic* relates to
the fundamental aspects of the problem space, for example being able to code in
Java. *Extraneous* relates to the environment in which work is done. For example
the complexity of CI/CD pipelines and the ability for a team to deploy often.
And finally, *germane* relates to the actual value that is created.

Organizations must work towards minimizing *intrinsic* load, for example through
training and pair programming, and *extraneous* load for example through
automation. This means people on the team have enough cognitive capacity to work
on the actual tasks that generate value.

# Theory of Constraints

Eliyahu Goldratt introduces the theory of constraints in [The Goal][2]. In this
novel the protagonist has to turn around the output of a factory within a short
amount of time.

The basic idea is that work is performed at certain work places. Some of those
are more resource constraint than others. The theory dictates, that any
improvement not done at the bottleneck is worthless. It does not improve the
output of the whole system and instead creates work that does not have the
highest possible impact.

# Wardley Maps

Simon Wardley developed the idea of [Wardley Maps][4] idea at Fotango in 2005.
As CEO of the company he was responsible for the overall strategy of a photo
printing service. The main issue he faced was not knowing where to invest in.

The idea is to create a map that helps identify business strategy. It
contains two main aspects: the value to the customer and the maturity of the
components needed to solve the customer problem. In the first step the different
components required to fulfill the customer problem are identified and mapped on
the Y-axis. The X-axis describes the maturity of the different components. The
maturity model contains four stages: *genesis*, a brand-new idea or product;
*custom-built*, something build only for this purpose; *product/hire*, a
component you could buy from a vendor; and finally *commodity/product*, a
component that is universally available, like electricity.

The goal of this exercise is to identify where to invest in. Obviously the
business should invest in the components that bring the most value for the
customer. Trivial examples for this could be the decision to invest into a cloud
solution, or build your own data center. I would argue there are only select
companies in the world that should actually build their own data centers.

## Pioneers, Settlers, Town Planners

Similarly to the notion of component maturity, Wardley introduces the different
types of approaches one needs in order to be successful in building and
maintaining them. The idea is that for the different levels of maturity, a
different mind set is required. The *pioneers* are not afraid of new technology,
and are not afraid to walk in unknown territory. *Settlers* build the bridge
from *custom-built* to *commodity* by productizing the custom solutions. And
finally the *town planners* are working with commodity products and scale
products across the whole organization or maybe even industry.

[0]: https://www.goodreads.com/book/show/44333183-the-unicorn-project

[1]: https://www.goodreads.com/book/show/17255186-the-phoenix-project

[2]: https://www.goodreads.com/book/show/113934.The_Goal

[3]: https://www.goodreads.com/book/show/44135420-team-topologies

[4]: https://medium.com/wardleymaps
