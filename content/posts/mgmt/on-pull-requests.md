---
title: "On Pull Requests"
date: 2022-05-01T14:00:00+02:00
draft: true
toc: false
images:
tags: 
  - team processes
  - lean
---

Everyone uses them: pull requests (PRs) in GitHub or merge requests (MRs) in
Gitlab. The mechanism is the same: person A writes code and needs at least one
other reviewer, before the change can be merged to main. This is to ensure the
four-eyes-principle, share knowledge, and ensure code quality.

The standard way for teams to deliver software today. Especially in remote and
during covid, I have seen this pattern everywhere. And to be honest, I'm not a
big fan of it. What is happening is that people write code in isolation, throw a
PR into a team chat. When lucky, someone actually tries running the code. The
more likely scenario is the inversely proportional ratio of comments to lines of
code in the PR. The bigger the PR the less comments it gets.

The main reason I am not a fan of this is that it violates the ideas of [flow
][0] and [feedback loops][1]. For the sake of the argument, let's assume in a
team of two people, how this workflow is impacting productivity.

## Anti-Flow

A thorough review by someone else includes getting into the code, understanding
the constraints, and validating the design. While the person is doing this, they
cannot work on another part of the code.

This can easily take as much time as
writing the code in the first place. So you end up without a real review.

## Long Feedback Loop


[0]: {{< relref "ideas-concepts#fast-flow" >}}

[1]: {{< relref "ideas-concepts#feedback-loops" >}}
