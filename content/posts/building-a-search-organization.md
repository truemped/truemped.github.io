---
title: "On Search Organizational Structure"
date: 2019-02-09T10:24:11+01:00
draft: true
toc: false
images:
tags: 
  - search
  - teams
  - organization
---

Search and building a Search organization is something I am trying to get my
head around for a while now. Structuring Teams and Ownership for teams is a
non-trivial task in the domain of Search. Often times you see that ownership and
team structure is derived and bound to components and communication structure.
This invariably leads to [Conway's Law](0) in the organization. I've always seen
this law as something to avoid if possible but do admit that in larger
organizations the different sub-organizations (Search, Checkout, Logistics,
e.g.) this is required in order to allow for autonomy in the sub-organizations.

But what if you model your team, responsible for delivering search after
Conway's law? In Search you might model teams by responsibility and then create
components around them. There could be a component for query understanding,
one for orchestrating the search queries and managing indexing structure. All of
that is fine from a systems perspective. One component taking care of enriching
the user query with external information, spellchecking, etc. One main component
orchestrating the life-cycle of a query and essentially acting as a facade in
front of the underlying Search Engine. These engines are non-trivial to operate
at scale so you think about adding an operations team. And then there is the
user facing components using all that technology.

What you have now introduced though are artificial boundaries between the teams.
In the domain of Search this is problematic as so many user facing features
eventually depend on the way you index, enrich and query the data. Where do I
need a facet? How do I enable users to find items based on external knowledge
that might not exist in my data? Sure, the component for managing external
knowledge can handle this, but how do I use this to query the index?

The obvious answer to this is feature teams.


[0]: https://en.wikipedia.org/wiki/Conway%27s_law
