---
title: "Death by a Thousand (Short-)Cuts"
date: 2022-08-12T07:51:17+02:00
draft: true
images:
tags: 
  - technical debt
---

There is a myth about this beautiful piece of software that we will write one day.
When we finally get enough time to fix the mess we are in.
Reduce all the [technical debt][1] we've acquired.
And when we've done that, we will never do this again.
The code will finally be right, and we don't have any hacks left.
Next month.
Next quarter.
Everything will be better.

It won't.

The shortcuts we take now will haunt us later.
Yet we continue to always take them.
In the interest of time, because we really need this project to finish.
Other projects depend on this, new projects are already lined up and cannot wait.

Before you blame your manager, or management in general, it's not their fault really.
It is everyone's and no one's fault.
For the front line managers it is often problematic to say no and limit the work in progress.
The engineering side applies quick fix after quick fix, introducing technical dept, a crime scene, that it is never cleaned up.
[Broken Windows theory][2] kicks in and soon everyone is used to this.
We go on, grow our career, move on.
Another company, another team.

This is the way.

This might sound depressing, but the mythical rebuild is an illusion we want to believe in.
Maybe it helps us remain _sane_ in difficult situations.
An all to human reaction to a perceived helplessness.
When you need to implement the next feature instead of repaying the debt you've added a month ago when you added the feature before.

# Move fast and break things

When Facebook came around with their mantra of moving fast at the cost of possibly breaking things, it created an environment where we stopped thinking about code quality.

actually broke things.  And when you have a lot of broken things,
you end up not moving at all anymore. Fun fact, Facebook has now changed their
motto to "Move fast with stable infra".

But why is moving fast creating a problem? "We want to and need to be fast when
executing, otherwise we loose the market" I hear you say. And that is of course
true. Not being able to execute fast means others will win in the long game.

But moving fast is not creating the problem. It's the *way* we move.

# Moving fast

What does this mean? In what way do we want to move fast?


Developing Software is getting increasingly complex in different aspects.  The
tools and frameworks we use add technical complexity.  We build simple web pages
using _modern_ technologies like React or Angular, where a classical HTML with
some CSS might be enough.

Complexity is not limited to technical aspects though.  Large corporations today
come with organizational complexity with a large work force.  Organizing many
people around objectives differs a lot from a small startup with less than a
hundred engineers.  What works in a small company, agile software engineering,
has limits when organizing work for one thousand engineers.  A _scrum of scrums_
does not get far.  So we add technical program management and suddenly everything
feels like a waterfall again.  Back to square one.



# The Management side

Management is at fault, because we all seem not to be capable of actually
prioritizing work. Not only are we not able to understand to limit work in
progress, we are also not capable to say no.

The main critique for my limiting WIP post was "my team has a WIP column that is
restricted". Sure, but how about the projects across the organization? Is your
team really, and truly autonomous?

# The Engineering side

The moment we suggest the hack to your boss, we have this thought to quickly get
this done and fix it later, we've lost.  You create the impression of moving
quickly, but what you have actually done is to take a shortcut today at the cost
of moving even slower tomorrow.  Keep doing this for a while, and you end up in
a situation where you drown in [Technical Debt][1].

# A root cause?

Is there a root cause for all of this? Not saying "no" or the hack you suggest
to get something done quickly?

No, but it is part of growing up as a business that you continue to work on this
understanding.

The only way to escape this situation is by not
getting sarcastic, and getting shit done.

# Getting shit done

Well, this is a stupid solution, you say: "Just work your ass off and everything
will be better".



There are different managerial tactics to deal with this sort of _debt_. Keeping
tasks in an issue tracker with the appropriate _labels_.

> We have taller smoke stacks on factories now, trying to prevent smog and acid
> rain. What we’re getting is that the fumes are traveling further, higher up,
> and still coming down in the form of acid rain. Let’s look at that. Someone
> has tried to solve a problem, which they did—they reduced smog. But we still
> put smoke up the chimney and think it disappears. It isn't gone. It’s gone
> somewhere. We need to look at the entire system. What happens to the smoke?
> What happens to the wash-off of fertilizer into brooks and streams? In that
> sense, we’re using the technology to correct a problem without understanding
> the epistemology of the problem. The problem is connected to a larger system,
> and it’s not solved by the quick fix.
> ([Mary Catherine Bateson][0])

Taking shortcuts fixes the issue at hand, but has effects down the line.

What I've seen a lot is people applying a quick fix but it's never cleaned up properly.

Time pressure leads to starting of new projects and ultimately increases WIP.

{{< tweet 1415696925312028686 >}}

#+BEGIN_QUOTE
"One of the behaviors due dates causes is early start (to show progress) which
increases WIP. More WIP mathematically means longer lead times. Longer lead
times mean less of what you want later. It's actually very simple." - Ian Larsen

Estimates causing themselves not to be met.
#+END_QUOTE


[0]: https://www.edge.org/conversation/mary_catherine_bateson-how-to-be-a-systems-thinker
[1]: https://www.martinfowler.com/bliki/TechnicalDebt.html
[2]: https://en.wikipedia.org/wiki/Broken_windows_theory
