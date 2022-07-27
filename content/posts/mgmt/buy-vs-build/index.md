---
title: "Buy versus Build"
date: 2022-07-26T15:28:45+02:00
draft: false
toc: false
images:
  - /posts/mgmt/buy-vs-build/ivan-henao-04rZ7R1fKhY-unsplash.jpg
tags: 
  - strategy
  - business
---

{{< figure src="/posts/mgmt/buy-vs-build/ivan-henao-04rZ7R1fKhY-unsplash.jpg"
    caption="Photo by [Ivan Henao](https://unsplash.com/@ivanchenao) on [Unsplash](https://unsplash.com/photos/04rZ7R1fKhY)">}}

This is maybe the most complex question that is never asked in the interview process in tech.
And to my understanding not even on a managerial level.
But it is such an interesting one to discuss!
There might be no right or wrong.
But it does reveal a lot about people's thought processes and mental models they have collected.

This one here is mine.
Today.
One that might change in the years to come.

I want to start with describing the factors that drive this decision.
Some of them are of technical nature.
But they are not important.
A red herring to distract you from the real issue.
The main driver of this decision has to be the business model.
In the end the value you bring to customers is what will get you paid.
Not the clever tech.
Yet.

# The business

Before we even go into the decision of buying or building, we first have to establish **what** to do.
A great tool to discuss this is by using [Wardley Maps][1].
It is an elegant way to discuss the value stream of how you fulfill customer needs and the capabilities you need for that.
In a large organization this means you need to understand who your customer is!
With the value stream at hand we can now depict the evolution of those capabilities: _genesis_, _custom build_, _product_, and _commodity_.
Are the required capabilities commonly available like electricity?
Or something never seen before?
Genesis, or commodity?

A good introduction to Wardley mapping is this thread by the man himself:

{{< tweet swardley 1550514919530934273 >}}

How does this all translate into a buy versus build decision?
Capabilities that exist and are easy to get access to are nothing you would want to build yourself.
_Cloud Computing_ is giving access to data centers for everyone and you are likely much better off not building a data center before writing code.
The same is true for project planning _products_ that are easy to use and buy.

You should concentrate your building efforts on the left side of the map.
Focus on the **genesis**, or **custom build** capabilities.
Capabilities that exist and are freely available to buy, will likely not differentiate you from the rest.

{{< figure src="/posts/mgmt/buy-vs-build/wardley.png" caption="Buy vs. Build in a Wardley Map" >}}

# Evolution

What is sometimes overlooked is that a Wardley map is always a visualization of a specific point in time.
Evolution is visible on the horizontal axis.
But evolution, well, it evolves.
What is a custom built solution today, can become a product in a year.

But if you have now built the system too integrated, replacing it is complex.
And I don't mean microservices.
Basic abstractions.
Those you also have in a large single repo code base.

Make sure you design the system with the right fracture planes in place.
Between the different capabilities.
With this in place, it will become easier to adapt later on.
Because the reason you think you need to build it from scratch today are likely to be irrelevant in the near future.
Reversing this decision might become really expensive when you missed those fracture planes.

# Wait, that's it?

There are of course a lot of other factors.
In addition to the value aspect discussed above, there are at least costs and risks associated.
Will Larson writes about this in his [Build versus Buy][0] article.
But I would argue they are secondary aspects.
At least in the vast majority of cases.

## Costs

*Costs* can become a problem in both cases, when you build and when you buy.
In the build case I am sure we constantly underestimate the **total cost of ownership**.
The amount of work required to keep the capability from deteriorating.
The technical debt incurred by not working on the code.
Updating libraries.
Fixing security problems.

But when you buy, *costs* can become as well.
The first contract runs out and you have to negotiate a new one.
I mean: if you are the vendor, you would likely increase your price after the first contract runs out.
You as the vendor are fully aware of the costs of change for the client.

## Risks

By using a vendor you also incur risks that would otherwise not be present.
Risks of a vendor going bankrupt.
Or being acquired.
The management might change and the new contract has a drastically increased price structure.

# Take Aways

The most important part of this decision is too look at the value creation.
Where and how is value (aka money) created in your business?
Those in _genesis_ you definitely build yourself.
And capabilities somewhere in or between _custom built_ and _product_ are worth exploring further.

With this at hand you should be able to put a price tag on a certain capability.
This price tag should allow you to compare offerings on the market.
And assess your capability and willingness to invest into building yourself.

Finally, as Will writes, I also believe that some of the risks need to be managed.
There will always be risks.
In both cases actually: buying **and** building.
But if you focus too much on the risks of buying, you underestimate the risks of building.
And then suffer from the **not invented here syndrome**

Coming back to the _clever tech_ I mentioned in the intro.
Of course it is important to use good technologies, great algorithms and data structures.
But I think they mainly control your cost structure.
The bottom line.
Use them where they bring the most value.

[0]: https://lethain.com/build-vs-buy/
[1]: {{< relref "/ideas-concepts#wardley-maps" >}}
