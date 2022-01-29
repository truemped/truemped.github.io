---
title: "Leadership is Language"
date: 2020-12-28T17:50:00+02:00
draft: false
toc: false
images:
tags: 
  - leadership
  - language
---

I'm currently reading [Leadership is language][1] by L. David Marquet, the same
author of [Turn the Ship Around][2]. In this book, Marquet describes the
different ways people communicate and how it influences the outcomes we get.
This should not be any news really, but the book explains in much more detail
how this happens. Especially when there is a power dynamic between the
participants of a conversation or meeting.

The main theme across the book is the sinking of a ship called *El Faro* caused
by a hurricane. The National Transportation Safety Board (NTSB) found the ship
and recovered it's voice recorder. It contained the last 25 hours of
conversation on the bridge. The sad story is that the people on board were aware
of the dangerous situation but the environment created by the company and
captain did not allow for them to speak up. Seeing what they were getting into,
the subordinates were not capable of convincing the captain to change route. The
ship sank and all 35 people on board died.

In the domain of tech the consequences usually are not that drastic on an
individual level. Hopefully no one gets killed because of a bad running project.
Exceptions like writing software for medical equipment persist of course.

On an organizational level though, the consequences might just be that: life or
death. [Stafford Beer][4] teaches in the [Viable System Model][5] that the main
purpose of an organization is to survive. Thus the environment inside an
organization influences survival and we need to be mindful of it. Does it work
in the organization's favour? Does it unwillingly work against us?

I want to describe how the language we use sometimes results in the exact
opposite of what we aim for. Instead of the expected outcomes, it created
side-effects that hindered the organization of moving faster or even prevented
collaboration between teams.

# "Own your development"

There is hardly any on-boarding material handed out by HR that does not include
this statement. Obviously this is right: when *you* don't work on *your*
development, who will? Promotions and pay rises are not given automatically
based on the number of years people spend in a company. Working for the state
might include a different point of view.

This statement alone is not enough though. There is also an organizational
responsibility to it. If only **you** own your development, the smallest
important work unit in the organization is the individual, not the team. People
do not have a direct incentive to cooperate. Apart from the "we are all in this
together" mantra. But when there is no cooporation within the team, everyone is
on their own.

Consider the situation of senior engineers in a team. They will only get a
promotion to something like a principal/staff engineer when they demonstrated
they can run projects or initiatives across multiple teams. If the Engineering
Manager's task is **not** to find good projects for them when they are ready,
everyone will fight about the projects that appear. Whether they are ready for
the next step or not. If those projects don't appear, they might find projects
from other teams. Or make projects larger than they need to be, just because
they want to advance their career.

Looking at this from a systems perspective a *balancing feedback loop* is
missing. There is a *reenforcing feedback loop* (*you own your career*), which
initially provides the intended results. But at some point the system becomes
instable, because the process requires a controlling hand. Everyone only owns
*their* career and suddenly it is not about the company anymore but careers. The
Engineering Managers task is to be part of this *balancing feedback loop*: is
the engineer capable of working on the next level. If yes, then go search for a
project that would help them demonstrate they are ready for the step.

# "Even better if"

This was the main part of a *Post Mortem* or *Incident Review* template I
noticed. It was the main section in the "what happened" part. No real *root
cause analysis* like the [5-why's][3]. This technique helps to get to the core
of a problem that happened. I have used this extensively to dig as deep as
possible to find out what happened and how we could prevent it from happening
again. My personal most memorable one was were we found a bug in the AWS network
driver for a certain instance type.

But don't get me wrong, the idea behind it is obviously right: there rarely is
this **one** root cause for an incident. Most of the time an incident happened
because a number of factors were at play.

But:

{{< tweet ebowman 1144152536808611840 >}}

And it's true. If the broader organization is not good at post-incident handling
and improving the systems, *not* doing 5-why's is an invitation for getting
sloppy. And *even better if* states this plain and simple: whishful thinking.

# "I'm open to feedback"

I've heard this from a senior leader in an organization. It is probably meant as
encouragement *for* feedback, but it does result in the opposite. Feedback is a
two-way street and if you want to *get* feedback, especially as a leader, the
first step is to *provide* feedback.

If the leader needs to tell their subordinates, that they "are open to feedback"
this might show the opposite. It seems like they do not receive regular feedback
and maybe need to tell their subordinates to give them feedback. Either way:
this statement alone made from *a boss* is a the prime example that there
probably is not a feedback culture.

# "Strong opinions, loosely held"

Michael Natkin writes in [Strong ideas loosely held might be the worst idea in
tech][0], "The loudest, most bombastic engineer states their case with
certainty, and that shuts down discussion." In the presence of a power dynamic I
would go even further though:

{{< tweet shreyas 1292324824530878474 >}}

If the top executive in the company has "strong opinions" it effectively
destroys creativity.  Everyone will always only want to do things that fit the
opinions of that executive. But what if their reality does not match the reality
*on the ground*?  What if the people actually implementing the things the exec
ask them to have a lot more insights? What if they could find a much better
solution, if the leader would allow them to think for themselves? The *outcome*
could actually be the same or close enough, but they way to it might be simpler
or adjusted more to the situation engineers have in *this* environment.

And what does this say about the person stating it?

> You have to accept that we actually need others to make us smart. And if we
> surround ourselves with people who confirm our existing thoughts, we get
> stupid. Quite frankly, stupid.
> ([Jonathan Haidt in The Knowledge Project][6])

There is a simple *hack* that can give a way out of this: state the *strong
opinions* in probabilities ("I'm 90% sure we should not use MongoDB"). This has
at least two benefits: 1/ it opens the possibility of disagreement. People have
the option of saying something contrary. Probably still only those with a lot of
courage, but at least some can speak up.

And 2/, as Annie Duke writes in [Thinking in Bets: Making Smarter Decisions When
You Don't Have All the Facts][7]: it does enable better decision making.
Especially when one does not have all the facts.

# Conclusion

Language is important in any social endeavour, private or business. Its
influence goes a lot further and dependeing on what language a leader chooses,
they get a different result and a different culture.

Be mindful of it and choose words wisely.

I have linked a number of books, blog posts, and podcasts on this topic, they
are well worth the time invested.

[0]: https://blog.glowforge.com/strong-opinions-loosely-held-might-be-the-worst-idea-in-tech/
[1]: https://www.goodreads.com/book/show/42774083-leadership-is-language
[2]: https://www.goodreads.com/book/show/16158601-turn-the-ship-around
[3]: https://en.wikipedia.org/wiki/Five_whys
[4]: https://en.wikipedia.org/wiki/Stafford_Beer
[5]: https://en.wikipedia.org/wiki/Viable_system_model
[6]: https://fs.blog/knowledge-project/jonathan-haidt/
[7]: https://www.goodreads.com/book/show/35957157-thinking-in-bets
