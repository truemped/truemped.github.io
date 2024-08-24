---
title: "On Writing Backwards"
date: 2024-08-24T10:30:00+02:00
lastmod: 2024-08-24T11:50:00+02:00
draft: true
toc: false
images:
  - /posts/orga/on-writing-backwards/florian-klauer-vintage-typewriter-unsplash.jpg
tags: 
  - Writing
keywords:
  - Working Backwards
  - Amazon writing process
  - Document writing tips
  - Common writing mistakes
  - Technical design documents
  - Clarity in writing
  - Six-page document limit
  - Editing process for writing
  - Writing for alignment
  - Effective document writing
---

{{< figure src="florian-klauer-vintage-typewriter-unsplash.jpg"
    caption="Photo by [Florian Klauer](https://unsplash.com/@florianklauer) on [Unsplash](https://unsplash.com/photos/black-fayorit-typewriter-with-printer-paper-mk7D-4UCfmg)">}}

After five years of working backwards, writing some and reading even more documents, I've gathered a few thoughts on why writing is hard and the mistakes I often see people making.

## On Working Backwards

"Working Backwards" is a concept developed at Amazon.
It focuses on starting from the customer and working backward to ensure solutions are relevant.
This is, of course, just a brief introduction.
If you're interested in the details, I recommend reading [Working Backwards: Insights, Stories, and Secrets from Inside Amazon][1] by Colin Bryar and Bill Carr.

The approach involves three main artifacts:

1. **Press Release**: Determines if the problem is worth solving.
1. **Solution Design**: Defines how the product should look.
1. **Technical Solution Design**: Outlines how to build the product.

Writing these documents is challenging in practice.
A common misconception is that you just need to write the document, get some feedback, and you’re done.
In reality, it’s about achieving alignment through a well-thought-out process.

## Common Writing Mistakes

The most fundamental mistake I see is a lack of understanding of the writing process itself.
Good writing follows a structured approach.
You start with an outline—a list of ideas in rough shape.
Then, you refine the underlying storyline.
Editing is crucial: removing every word that doesn’t add meaning, bringing clarity to your writing, and refining your thought process.

> Writing is the process by which we discover we don’t really know what we are talking about.
> Shane Parrish

In practice, many people stop at the initial brain dump and skip the editing phase, which is arguably the most important part.
The result is often long documents where the reader loses interest after 10 pages, with sentences full of implicit context that the reader can’t decipher, and no real storyline.

I have collected a few blockers for myself that stop me from reading a document.
These are in no specific order: **length**, **bullet points**, and **pseudo code**.

### Length

A 50-page document?
We’re not in university, and this isn’t a PhD thesis.
Most of these documents contain far too much detail, especially in technical design documents.
It’s not about laying out every specific implementation detail—it’s not a specification.
No matter how large the project or how many teams are involved, focus on core properties and design decisions.
Details can be fleshed out in subsequent, localized design documents.

### Bullet Points

If your document is full of bullet points, you’re likely still drafting.
Take the time to form real sentences and create a coherent storyline.
In my experience, lists of bullet points often lead to duplicated information.
I’m tempted to refuse reading such documents, especially in the final review stage.

### Pseudo Code

The worst is seeing pseudo code—or even real code—in a document.
I’ve seen documents with actual code lines in them.
Google Docs is not an IDE, and unless you’re doing [literate programming][2], avoid adding code to your doc.
You might think it clarifies a topic, but it will be outdated as soon as someone starts coding in an IDE.

## Six Pages

Bezos’ six-page limit is an excellent constraint.
It forces you to focus on key decisions and abstractions without getting bogged down by excessive details.
It enforces clarity of thought and precision in expression.
However, many people aren’t willing to invest the time.
I would argue *it’s worth every second*.

## Conclusion

Writing documents is time-consuming but invaluable for clarifying thoughts and aligning topics within a large organization.
It’s a skill that enhances communication and decision-making.

Embrace the process, refine your approach, and remember: clarity in writing leads to clarity in thinking.

[0]: {{< relref "/posts/orga/aligning-through-writing-documents" >}}
[1]: https://www.amazon.de/Working-Backwards-Insights-Stories-Secrets/dp/1529033829
[2]: https://www-cs-faculty.stanford.edu/~knuth/lp.html