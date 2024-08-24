---
title: "On Writing Backwards"
date: 2024-08-24T10:30:00+02:00
draft: true
toc: false
images:
  - /posts/orga/on-writing-backwards/florian-klauer-vintage-typewriter-unsplash.jpg
tags: 
  - Writing
---

{{< figure src="florian-klauer-vintage-typewriter-unsplash.jpg"
    caption="Photo by [Florian Klauer](https://unsplash.com/@florianklauer) on [Unsplash](https://unsplash.com/photos/black-fayorit-typewriter-with-printer-paper-mk7D-4UCfmg)">}}

Five years of working backwards, writing a few and reading even more documents, I have a few thoughts.
Why writing is hard.
And the many mistakes I see people making.

## On Working Backwards
"Working Backwards" is a concept created at Amazon, emphasizing starting from the customer and working backward to ensure solutions are relevant.
This is of course an extremely short introduction into it.
If you are really interested in the details of how it is working and reasoning behind, get it from the original authors.
Colin Bryar and Bill Carr wrote about it in [Working Backwards: Insights, Stories, and Secrets from Inside Amazon][1].

It involves three main artifacts:

1. **Press Release**: Determines if the problem is worth solving.
1. **Solution Design**: Defines how the product should look.
1. **Technical Solution Design**: Outlines how to build the product.

Writing these documents in practice is challenging.
One misconception is that you just need to write the document, get some feedback, and you are done.
It is about achieving alignment through a well-thought-out process.

## Common Mistakes

The most common and fundamental mistake I see is the lack of understanding of writing itself.
Good writing follows a certain process.
You start with an outline, a list of ideas, in rough shape.
Check what the underlying story line works.
And then it is a lot about *editing* your writing.
Removing every word that does not add meaning.
Bring clarity to your writing and your own thought process.

> Writing is the process by which we discover we don’t really know what we are talking about.
> Shane Parrish

In practice, most people stop at *the braindump* and they forget the editing part.
Arguably this is the most important one.
We end up with long documents where the reader passes out after 10 pages.
Sentences filled with implicit context, where the reader cannot understand the words written.
No real story line.

I have collected a few blockers for myself that stop me from reading a document.
These are in no specific order: **length**, **bullet points**, and **pseudo code**.

A document with 50 pages?
We are not at university and this is not a PhD.
In many cases there is just *way* to much detail in those documents.
Especially in technical design documents.
It is **not** about laying out what you are specifically going to implement.
It is **not** a specification.
No matter how big the project will be, or how many teams will be involved.
Reduce it to the core properties and design decisions.
Details can be fletched out in subsequent, local design documents.

Paragraphs of *bullet points* in the doc: you must still be drafting the document.
Take the time and create real sentences.
Form a story line.
Remove content.
In my experience, with lists of bullet points, the likelihood of duplicating information is huge.
I would love to refuse readgin something like this, especially in a final review stage.

The worst is actually writing pseudo code.
Or even real code.
I'm not kidding: I have seen documents with actual code lines.
Google Docs is not an IDE.
And unless you are doing [literate programming][2], do not add code to your doc.
You might think it clarifies a topic.
But the code you write in the doc will be outdated as soon as someone actually starts writing code in an IDE.

## Six Pages

Bezos' 6-page maximum is a really good constraint.
It helps to focus on key decisions and abstractions without getting bogged down by excessive details.
It enforces clarity of thinking and precision in articulation.
However, many are not willing to invest this time.
But I would argue: *it is worth every second*.

## Conclusion

Writing documents is time-consuming but invaluable for clarifying thoughts and aligning topics in a large organization.
It’s a skill that enhances communication and decision-making.

Embrace the process, refine your approach, and remember that clarity in writing leads to clarity in thinking.

[0]: {{< relref "/posts/orga/aligning-through-writing-documents" >}}
[1]: https://www.amazon.de/Working-Backwards-Insights-Stories-Secrets/dp/1529033829
[2]: https://www-cs-faculty.stanford.edu/~knuth/lp.html