---
title: "Trunk Based Development: Stop PR Reviews!"
date: 2023-08-22T19:00:00+02:00
draft: false
toc: false
images:
  - /posts/mgmt/trunk-based-development/dalle-1692718678518.png
tags: 
  - limit wip
  - lean
  - engineering effectiveness
description:
  A post in which I describe the reasons for doing pair and mob programming together with trunk based development.
  Teams utilizing a pure pull request based review process are slowing themselves down.
keywords:
  - work in progress
  - pull request reviews
  - collaborative development
---

{{< figure src="dalle-1692718678518.png"
    caption="two people in front of a computer doing pair programming in the style of a picasso painting (DALLE)">}}

Engineering effectiveness is a prominent topic.
Traditional Pull Request (PR) reviews show their limitations when it comes to efficiency, knowledge sharing, and delivering value.
Instead, trunk based development, coupled with methodologies such as pair and mob programming, are a superior process.
Along with the strategic utilization of feature flags, this methodology presents an alluring argument for a more agile and streamlined software delivery.
I want to describe the challenges posed by conventional PR reviews and show the benefits of doing things differently.

## The Challenges of PR Reviews

1. Delayed Feedback Loops: PR reviews can create prolonged waiting periods before feedback is received.
   Particularly in remote teams or high-activity environments, this temporal lag translates into tangible delays, directly impeding the velocity of software deployment.
   The psychological ramifications of prolonged waiting are not to be underestimated.
   Such delays can demotivate developers and undermine trust of peers in the team.

2. Inconsistent Knowledge Transfer: The depth of a PR review hinges heavily on the reviewer's grasp of the context.
   In the absence of comprehensive insight, subtle intricacies or broader implications of a code modification might elude detection, potentially leading to incongruities or oversights.
   This leads to the creation of more meetings like a dedicated "knowledge sharing" meeting.
   More time we cannot spend on delivering value.

3. Swelling Work-In-Progress, a favorite of mine: As one PR awaits assessment, we tend to shift our attention to alternative tasks.
   This cyclic pattern can result in an accumulation of tasks in a 'pending review' state, clouding priorities and scattering team focus.

## The Allure of Trunk Based Development

Shifting towards trunk based development combined with pair or mob programming offers viable solutions to these challenges:

1. Instantaneous Feedback: Through an emphasis on frequent integrations and shorter branches, the risk of merge conflicts diminishes, thus paving the way for nearly immediate feedback.

2. Collaborative Code Generation: The realm of pair and mob programming takes center stage.
   In pair programming, two developers collaborate on the same code, with one crafting while the other reviews in real-time.
   This fosters instant feedback and collaborative problem-solving.
   Mob programming takes this a step further, engaging a larger group in the process.
   This not only enriches knowledge transfer but also ensures that code is scrutinized from a multitude of perspectives, bolstering its robustness.

3. Streamlined Task Management: With an increased frequency of commits and integrations, the journey of the code becomes more palpable.
   Developers witness their code traverse pipelines at an accelerated pace, thereby reducing the sprawl of concurrent work-in-progress endeavors.

4. Accelerated Release Cycles: Integrated with Continuous Integration (CI) and Continuous Deployment (CD) pipelines, trunk based development propels the pace of software releases.
   This ensures swifter feature delivery and expedited issue resolution.

## Feature Flags: Flexibility in Release Management

The synergy between feature flags and trunk based development is the real superpower.
Feature flags empower developers to integrate features into the mainline, maintaining control over their visibility.
Features can be introduced selectively, ensuring users encounter them only when they've reached a polished state.

## In Conclusion

Ironically none of this is really new.
Especially mob programming has its roots in the Extreme Programming movement that preceded Scrum and is the purest form of agile.

The demands of today's software engineering landscape demand methodologies that prioritize agility and prompt feedback.
While PR reviews carry historical significance, it's important to discern when they might prove more obstructive than beneficial.
The realm of trunk based development, elevated by practices like pair and mob programming, and enriched with feature flags, encapsulates the agility and collaborative ethos imperative for an efficient engineering team.
