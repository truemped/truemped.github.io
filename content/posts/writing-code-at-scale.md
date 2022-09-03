---
title: "Writing Code at Scale"
date: 2021-11-26T17:30:21+01:00
draft: true
toc: false
images:
tags: 
  - code at scale
  - ways of working
  - technical debt
---

There is a myth about this beautiful piece of software that we will write one
day.  When we finally get enough time to fix the mess we are in.  Reduce all the
[technical debt][0] we've acquired.  And when we've done that, we will never do
this again.  The code will finally be right, and we don't have any hacks left.
Next month, next quarter.  Everything will get better.

It won't.

We focus on new projects that line up, new features to build, and we cannot
delay that other project yet again.  So we take shortcuts.  We know we will have
to pay them back.  We have a label in Jira for the issues we need to fix later
on.  But we never do.  The [Broken Windows theory][1] kicks in and soon everyone
ignores the situation.  Another project comes, we grow our careers and
eventually move on.  Another team, another company, "I have heard good things
about them".  Rinse and repeat.

This is the way.

# Acceptance is the first step to recovery

It might sound depressing, but the idealized rebuild is an illusion we want to
believe in.  Maybe it helps us remain _sane_ in stressful situations.  An all
too human reaction to a perceived helplessness.  When you start to implement the
next feature instead of repaying the debt you've added a month ago while adding
the previous feature.

Does that mean we can never develop any good software?  Do we ever only have
this one shot: the greenfield project we all want to do?  Are we really unable
to fix software engineering?

> Software Engineering is Programming over Time
>
> Google

I would not be writing this piece if I believed software engineering is beyond
repair and that changing jobs every two years is the best course of action.  But
I do believe that the currently accepted standard way of software engineering is
flawed.  And I don't mean we should all be writing Clojure instead of Scala or
use Kafka instead of PostgreSQL.  We got accustomed to a certain way of working
that stops us from actually being effective.  The teams and organizations we
build are getting in the way of excellence and efficiency.  And yes, at the very
core this is about how we build and run a software business.  Because, whatever
the current en-vogue language there is, at the end it's about business.

The core of all activities is the aspect of working with and for people.  In
contrast to popular stereotypes, software engineering is a social endeavor.  The
lone wolf, the hero, the 10x engineer, they all fail in the long run.  Because
this is the game we are in: it never ends.  Rules change, technologies
come and go, but winning at the game of business does not depend on a certain
piece of technology.  It is all about what kind of teams, culture, and social
systems we build that determine success:

> We find a simple investment strategy of buying an equally weighted portfolio
> of each year’s U.S. large BPTW[^1] winners outperforms the S&P 500 index in
> nine of the 11 years we examined. On average, stocks of BPTW winners earned
> 20.3 percent per year between 2009 and 2019, compared to 12.9 percent for the
> S&P 500. 
>
> [Glassdoor][3]

How do we build such and organization? How do we build these high performing
teams?  Unfortunately, this is not _that_ simple and there is no simple recipe
to apply.  If it were, everyone would do it, right?  However, I think there are
a number of ingredients we need to look out for.  Ideas that help us bring
clarity into our thinking and the way we build systems -- technical and social
ones.


Obviously I'm not the first one to think about this, and I'm standing on the
shoulders of so many giants.  The brilliant Gene Kim, with his books and
podcasts has a strong influence on my thinking.  Nicole Forsgren wrote a great
analysis on what makes a good software organization.  

Mikio Braun wrote a nice post about how we are all
still terrible at building software at scale.

This touches a lot of areas in the ways we work, the decisions we take, the
architecture we build, and the organizations we create around the systems.


[0]: https://www.martinfowler.com/bliki/TechnicalDebt.html
[1]: https://en.wikipedia.org/wiki/Broken_windows_theory
[2]: https://en.wikipedia.org/wiki/Flow_(psychology)
[3]: https://www.glassdoor.com/research/stock-returns-bptw-2020
[4]: https://www.goodreads.com/book/show/44333183-the-unicorn-project
[5]: https://www.goodreads.com/book/show/53138083-working-backwards

[^1]: Best Place to Work
