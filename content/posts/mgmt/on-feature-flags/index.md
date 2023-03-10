---
title: "On Feature Flags"
date: 2023-03-06T22:00:00+02:00
draft: false
toc: false
images:
  - /posts/mgmt/on-feature-flags/marco-bianchetti-UOAD9U-TYxc-unsplash.jpg
tags: 
  - feature flags
  - engineering effectiveness
  - software engineering
description:
  A post in which I describe my thoughts on feature flags.
  Why they are better than other ways of continuous delivery.
  How they make your organization better.
  More effective.
  And more successful.
keywords:
  - feature flags
  - engineering effectiveness
  - software engineering
  - technology
---

{{< figure src="marco-bianchetti-UOAD9U-TYxc-unsplash.jpg"
    caption="Photo by [Marco Bianchetti](https://unsplash.com/@marcobian) on [Unsplash](https://unsplash.com/photos/UOAD9U-TYxc)">}}
	
Continuous *delivery* is the holy grail of building software.
Not just continuous integration, like running your whole test suite on every push or pull request.
Deploying multiple times a day improves cycle times.

But if you are still trying to make this happen in your organization.
If you are still trying to workout the details of [canary releases][0], [Blue/Green][1] deployments, or traffic switching in Kubernetes: don't do it.
Those ideas are great, but they have also aged.
They require a lot of additional tooling and automation to get right.
And knowing when a traffic switch of a new feature is successful, is non trivial.

Spare the time investment and use it for something simple that scales across teams.
That helps you reducing infrastructure costs, provides visibility for product managers.
And in general that helps your organization deliver more features, faster, and safer.

# Feature Flags at Scale

The truth is: a good feature flagging system can make *the* difference.
Difference between meh, and great.
Between a high performing team and one that struggles with delivery.
And especially in an organization with multiple teams working on the same new feature.

You'll argue that "it's just a boolean condition"!
And yes, you are right.
Just on or off.
Or maybe A, B, or C.
But if you look at feature flags beyond the single piece, it is much, much more.

But the system I'm going to describe now is much more than that.
It is based on three basic capabilities:
a mechanism to query the state of an identifiable flag,
based on the current request data in combination with your customer data,
and a way to activate a feature based on a percentage of said customer groups.

With this basic set of capabilities you can achieve all the things outlined earlier.
A staging system?
Just a set of features activated for internal employees.
Amazon Prime membership?
Features enabled for all customers that have a certain property.
Customized experiences in different countries?
Just another property of users, their *country*.
Testing new features that need development of multiple teams?
Use the new "staging" feature.

And finally flags and their usage helps you manage technical debt.
When was the flag last active?
Is that more than a few weeks back?
Should we remove the code all together?
Or is the flag part of an emergency procedure?
You should probably make sure this still works as expected!
On parts of the user base, of course.

# Delivering Features at Scale

How does this help in delivering features that cross team boundaries?
Or even larger organizational boundaries?
In a large organization is it not uncommon to have a lot of teams working on a large feature.
Everyone has to deliver parts of the bigger picture.
And everyone creates a feature branch.
But in a large organization something else is present: heterogeneity.
Different teams develop in different programming languages.
A different framework.
Or different engineering maturity.
Some teams might have their local feature flagging solution.
Or thought about configuration beyond ports their code listens on.

Rolling out a new feature across teams now needs a lot of coordination.
Maybe teams have a checklist or best practices for go-lives.
But coordinating this globally involves keeping track of them from all involved teams.
And build a plan on _who_ needs to do _what_ and by _when_.

This comes at with two types of cost.
First, the roll out now takes time from engineers.
Time they cannot spend on something building the next feature.
Instead, they help rolling out something that might have been developed two months ago.

Second, and even more importantly: your feature might only be tested when you go live.
I don't mean tested locally, by team X or Y or Z.
I mean fully integrated, in a live environment, with live data.
Only in the moment you put the feature in front of actual customers you see how it behaves.
Of course you roll out the feature to only X% of your A/B testing group.
But only then you discover defects.
So you repeat the A/B test until the feature works.

# How the parts interact

The system I'm describing helps with feature releases.
Especially across multiple teams.
But the real magic is the integration with other systems.
Specifically the integration with tracking, analytics, and experimentation.

A feature flag is worth nothing if you cannot analyze the impact.
Does the new feature work?
Can the new version be activated for a larger part of the customer base?
Is the new button color better than the old one?
Are there any unforeseen side effects that influence adjacent KPIs?

And of course the best part of the integration: multi-armed bandits!
Can the analysis of different versions of a feature influence the feature selection?
In e-commerce for example, think about different sales periods.
Version A of a feature might work better during a sale event compared to version B.
At the same time, version B is much better during non-sales events.

But: why **one** system?
First of all: any system is built on subsystems.
So it is not really about one single code base or single deployment.
Not about *one team* that should build it.
Instead, take a look at how companies offer the functionality as a service.
What do they *always* need?
What features do all players in this market require from you?
Exactly what I have outlined above.
Feature flags to activate a feature based on rules.
Tracking to collect usage data.
Analytics to analyze the basic KPI tree of your business.
And experimentation, to test new features against the current status quo.

Some might provide integrations with other systems.
Like Google Analytics, or BigQuery, or RedShift.
But the SaaS companies need certain capabilities in order to guarantee correctness.
Correctness of analytics and experimentation results.
The only way they *can* guarantee this is by centralizing all of those parts in one system.
And from there you might be able to export the data somewhere else.

Popular solutions one can use are [LaunchDarkly][2], [StatSig][3], or [Unleash][4].
An interesting observation is that they basically provide a similar service, with a focus on the area from two different sides.
The more engineering focused offerings added experimentation later.
They have an emphasis on rolling out features safely.
The more experimentation focused offerings advertise the safe rollouts later.
Still, they all roughly have the same set of features.
With nuances in how they are built.

# Conclusion

Long story short: in my opinion, don't invest time into canary releases or similar constructs.
Using, leveraging, or *maybe* building a centralized feature flagging system pays off.
In terms of actual costs for example a staging system that is obsolete.
But also for increasing overall productivity of the organization.
In delivering features and managing technical debt.
And to increase developer productivity.
Because they don't have to coordinate go-lives of features.

Discuss and leave comments on [hacker news][5].

[0]: https://martinfowler.com/bliki/CanaryRelease.html
[1]: https://martinfowler.com/bliki/BlueGreenDeployment.html
[2]: https://launchdarkly.com/
[3]: https://statsig.com/
[4]: https://www.getunleash.io/
[5]: https://news.ycombinator.com/item?id=35091792
