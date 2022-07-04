---
title: "Buy versus Build"
date: 2022-06-14T21:28:45+02:00
draft: false
toc: false
images:
tags: 
  - strategy
  - business
---

The eternal question in any software business.
One I often see answered badly or even ignored all together.
Many ways lead to sub-optimal decisions and I'd argue it is a decision that is easier to get wrong than right.
But there are tools available to help get the decision right.
Tools ranging from business strategy to the technical architecture.

Many people have written about this and one of the best articles I've seen is [Build versus Buy][0] by Will Larson.
He decomposes the decision into three areas: **risk**, **value**, and **cost**.
By using a vendor, you incur _risks_ on two main dimensions: the vendor might go bankrupt, or take you hostage after a while, increasing prices to crazy levels, maybe triggered by an acquisition.
Both these risks do not follow automatically, but keep them in mind.
Will argues, that overly fixating on risks can culminate in a robust "not invented here syndrome".
You will always find a risk, the question is what is the probability of it materializing.

The second area is _value_ and where it is created.
The value of a vendor is the difference between the vendor's offering and the investment you are willing to make today.
However, the quality and capability of the vendor's offering and your solution can diverge over time!
Maybe you are willing to invest a certain sum today in your tool, but are you willing to invest in building yourself over a longer period of time?

With respect to value creation he notes that

> vendors usually generate significant value if they’re outside your company’s
> core competency; within your core competency, they generally slow you down.

I'll come back to this later.

The final area Will mentions are costs.
_Integration_ costs cover the initial work to make it work, _financial_ costs for the contract, _operational_ costs
caused by outages or degradations, and _evolution_ costs, for when you want to
change how you are using the vendor.
All those factors help you decide the price you are willing to pay for the vendor.

I want to add three thoughts to this decision making process: thinking about value creation using [Wardley Maps][1], how this map might **change over time**, and the effect this has on your **software architecture**.

# Value creation, Wardley Maps, and What to Build

Before we even go into the decision of buying or building, we first have to establish **what** to do.
For this we start with a Wardley map, identifying the user need and the capabilities required to fulfill it.
We then align those capabilities by their evolutionary stage on the x-axis: _genesis_, _custom
build_, _product_, and _commodity_.
Capabilities that exist and are easy to get access to are nothing you would want to build yourself.
_Cloud Computing_ is giving access to data centers for everyone and you are likely much better off not building a data center before writing code.
The same is true for project planning _products_ that are easy to use and buy.

Focus on building custom code and leverage as much product and commodity as
possible.

# 

[0]: https://lethain.com/build-vs-buy/
[1]: {{< relref "/ideas-concepts#wardley-maps" >}}
