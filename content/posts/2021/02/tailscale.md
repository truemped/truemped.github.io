---
title: "Tailscale"
date: 2021-02-16T09:00:00+02:00
draft: false
toc: false
images:
tags: 
  - vpn
  - wiregard
aliases:
  - "/posts/engineering/tailscale"
---

Last Friday I got to know [Tailscale][0] by a complete coincidence. Someone I
follow on twitter mentioned it:

{{< oldtweet user="clofresh" id="1360105546712576006" >}}

What can I say: it blew my mind! I always wanted to setup a VPN connection
between my droplet and my NAS at home for backups. But of course I was way too
lazy to configure OpenVPN or something similar. I knew about WireGuard and the
progress they were making, but had been too lazy to set this up myself. And so
along comes Tailscale, a fully integrated WireGuard setup and almost a *one
click installation*.

After about one hour I had the setup complete. There is a package for Debian,
Synology, OS X, and even my phone. Create an account using Google as identity
provider and that's it. Mesh VPN between my devices.

Completely useless now that I'm basically 100% at home.

[0]: https://tailscale.com
