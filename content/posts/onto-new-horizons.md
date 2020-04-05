---
title: "Onto new Horizons"
date: 2020-04-05T17:00:00+02:00
raft: false
toc: false
images:
tags: 
---

The date slowly approached and then it just happened. After almost four years I
left Zalando end of March. I joined Zalando as Senior Engineer in the search
team. In this role I helped migrate the search systems from the old monolith
(*Jimmy*) to a microservice based architecture. In those times, microservices
where meant literally and a lot of them were created.

The system was based on SolrCloud and basically a home-grown Kafka like
messaging system based on SQS and DynamoDB. A lot of assumptions were baked in
code. The first Black Friday we were live only on the mobile app. While this was
successful, extending this system to other clients like the web was a huge
undertaking. At this point, we decided to move to a much more simple, Kafka
based intake pipeline and Elasticsearch as the main search index. Back then we
felt this would be a much more sustainable architecture. And we were right. It
still has not changed significantly but has been extended over time.

For my second Black Friday we were live with the new system on all user facing
systems. From a technical point of view we were able to sustain all live
traffic, up to 12k QPS. At this point this has almost been a disappointment as
we had tested for even more traffic. This traffic number increased to 30k QPS on
my last Black Friday. But this number became uninteresting over time. It was
merely a matter of scaling the system enough.

Zalando also allowed me to transition into management. About two years ago I
became Engineering Lead in the search department. This was one of my main
reasons to join Zalando, so in that regard I achieved my goal. After a while I
realized the size of the challenge ahead. I basically stopped writing code from
one day to the next. I already reflected about this in my [first yearly][0]
review as manager, if you are interested.

At some point my [shields went down][1]. I cannot say it was this specific
meeting or that specific action someone took -- or did not take. I almost left
the company at that point, but then a lot of things changed. So much so that I
got energized again and stayed another year. It was well worth it, I learned a
lot and it helped me understand management much better.

My shields never went up again completely. And then, at some point, an
interesting opportunity came up. Interesting enough that I approached them and
found out about their plans and how I might contribute. Many things matched, the
interviews were great and I am now happy to say that I joined GetYourGuide on
April 1st. I will be working as Engineering Manager in the Search and Discovery
team and it's needless to say that the timing is insane. On-boarding in a new
company during a global pandemic is interesting, to say the least. Moving into
the travel industry at this point demands for a lot of positive thinking for the
next year or two.

What I will miss are the people and colleagues I worked with. But new
relationships will be developed and new bonds will be made. Once again I will
help migrate a Search system out of the monolith (*FishFarm*). After working in
management for two years now, I look forward to a change of scenery, new
inspiration and new management styles. I now am at a point in my career in which
I also want to get exposed to different types of management and widen my
horizon.

Onward, always...

[0]: {{< ref "/posts/retrospection/first-year.md">}}
[1]: https://randsinrepose.com/archives/shields-down/
