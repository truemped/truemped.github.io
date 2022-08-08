---
title: "Buy versus Build"
date: 2022-07-26T15:28:45+02:00
lastmod: 2022-08-06T21:05:45+02:00
draft: false
toc: false
images:
  - /posts/mgmt/buy-vs-build/ivan-henao-04rZ7R1fKhY-unsplash.jpg
tags: 
  - strategy
  - business
description:
  A post in which I describe my mental model for what to buy and what to build in software and technology business.
  Analysis using Wardley Maps helps us understand how we can be most effective in business.
  I claim we underestimate the total cost of ownership.
  And over-index on risks of buying so we end up in a not-invented-here syndrome.
keywords:
  - buy
  - build
  - buy vs build
  - business
  - strategy
  - technology
  - analysis
---

{{< figure src="/posts/mgmt/buy-vs-build/ivan-henao-04rZ7R1fKhY-unsplash.jpg"
    caption="Photo by [Ivan Henao](https://unsplash.com/@ivanchenao) on [Unsplash](https://unsplash.com/photos/04rZ7R1fKhY)">}}

This is maybe the most complex question that is never asked in the interview process in tech.
And often not even on a managerial level.
But: it is such an interesting one to discuss!
There might be no right or wrong.
But it does reveal a lot about people's thought processes and mental models they have collected.

This one here is mine.
Today.
One that might change in the years to come.

I want to start with describing the factors that drive this decision.
Some of them are of technical nature, but they are not important.
A red herring to distract us from the real issue.
The main driver of this decision has to be the business and the value we bring to customers.
This is what will get us paid.
And not that we have built everything on our own.
Literally everything.

# The business

Before we even go into the decision of buying or building, we first have to establish **what** to do.
A great tool to discuss this is by using [Wardley Maps][1].
It is an elegant way to discuss the value stream of how we fulfill customer needs and the capabilities we need for that.
In a large organization this means we need to understand who our customer is!
This can be the real customer, the one giving us money for the service.
But it can also be all the teams building software.

With the value stream at hand we can now depict the evolution of those capabilities: _genesis_, _custom build_, _product_, and _commodity_.
Are the required capabilities commonly available like electricity?
Or something never seen before?
Genesis, or commodity?

How does this all translate into a buy versus build decision?
Capabilities that exist and are easy to get access to are nothing we would want to build ourselves.
For example _Cloud Computing_ is giving access to data centers for everyone.
We are likely much better off not building a data center before writing code.
The same is true for project planning _products_ that are easy to use and buy.

We have to concentrate our building efforts on the left side of the map.
Focus on the **genesis**, or **custom build** capabilities.
Capabilities that exist and are freely available to buy, will likely not differentiate us from the rest.

{{< figure src="/posts/mgmt/buy-vs-build/wardley.png" caption="Buy vs. Build in a Wardley Map" >}}

# Evolution

What is sometimes overlooked is that a Wardley map is always a visualization of a specific point in time.
Evolution is visible on the horizontal axis.
But evolution, well, it evolves.
What is a custom-built solution today, can become a product in a year.

If we now have built the system too integrated, replacing it later becomes complex.
And I don't mean microservices.
Basic abstractions.
Those we also have in a large single repo code base.

We need to make sure we design the system with the right fracture planes in place.
Between the different capabilities.
This will make it easier to adapt later on.
Because the reason we think we need to build it from scratch today are likely to be irrelevant in the future.
Reversing this decision might become really expensive when we missed those fracture planes.

Obviously this touches [Domain Driven Design][2].
An area we often miss when we build and run our organizations.

# Wait, that's it?

There are of course a lot of other factors.
In addition to the value aspect discussed above, there are at least costs and risks associated.
Will Larson writes about this in his [Build versus Buy][0] article.
But I would argue they are secondary aspects.
At least in the vast majority of cases.

## Costs

*Costs* can become a problem in both cases, when we build **and** when we buy.
In the build case I am sure we constantly underestimate the **total cost of ownership**.
The amount of work required to keep the capability from deteriorating.
The technical debt incurred by not working on the code.
Not Updating libraries.
Not Fixing security problems.
Until we have to, and then it becomes a tricky problem.
Likely the engineers that originally built this moved on to other teams or companies.
We need to decide to either pay back an enormous amount of tech debt, rebuild it again, or buy it this time.

But when we buy, *costs* can become as well.
The first contract runs out, and we have to negotiate a new one.
I mean: if we are the vendor, we would likely increase our price after the first contract runs out.
We as the vendor are fully aware of the costs of change for the client.

## Risks

By using a vendor we also incur risks that would otherwise not be present.
Risks of a vendor going bankrupt.
Or being acquired.
The management might change, and the new contract has a drastically increased price structure.

But I think there will always be risks.
In both cases actually: buying **and** building.
But if we focus too much on the risks of buying, we underestimate the risks of building.
And then suffer from the **not invented here syndrome**

# Takeaways

The most important part of this decision is to look at the value creation.
Where and how is value (aka money) created in our business?
Those in _genesis_ we definitely build ourselves.
And capabilities somewhere in or between _custom-built_ and _product_ are worth exploring further.

With this at hand we should also be able to put a price tag on a certain capability.
This price tag should allow us to compare offerings on the market.
And assess our capability and willingness to invest into building it ourselves.

But when we build, we have to use all the clever tech available to us.
Of course, it is important to use good technologies, great algorithms and data structures.
But I think they mainly control our cost structure.
The bottom line.
Use them where they bring the most value.

[0]: https://lethain.com/build-vs-buy/
[1]: {{< relref "/ideas-concepts#wardley-maps" >}}
[2]: https://en.wikipedia.org/wiki/Domain-driven_design
