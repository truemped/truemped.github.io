{:title "Continuous delivery with automatic failover"
 :layout :post
 :tags ["digitalocean" "devops-series"]}

This post is kicking of a little blog series about my adventures into building a
fault tolerant Python based web site. The goals are simple:

- two VMs with automatic failover and floating IP
- shared filesystem
- Debian packages for the Python project
- continuous integration
- continuous delivery

In addition to this series of blog posts there will be an example Ansible
playbook on Github containing all the relevant roles.

### Agenda

The series is divided into 7 posts. With every new post I will update the
links here. They are also available under the tag
[devops-series](/devops-series.html).

1. [Basics: Debian VM, security and common packages](/posts/2016-06-07-failover-continuous-deployment.html)
2. Corosync, Pacemaker, Floating IP
3. Private Networking: TINC
4. GlusterFS: shared filesystem
5. Debian package for a Python project
6. Gitlab, Gitlab-CI and Docker Hub
7. Gitlab Webhook receiver for deployment

Obviously this is **a lot**. So be patient while I write this down.

### Digital Ocean

I chose [Digital Ocean (referral)](https://m.do.co/c/dd5d931d13ce) for this.
There are no particular reasons for it, except a datacenter in Frankfurt,
Germany and their great support for APIs. Nothing you wouldn't get at Amazon but
maybe a little less *enterpsisy* -- whatever that means.

### Gitlab

Github would be the obvious choice. But budget is small and I need a private
repository. Gitlab-CI is integrated directly so I wouldn't need to pay extra for
continuous integration. Also Gitlab-CI features build artifacts, which will be
the Debian package I then only need to download.

### Docker you say

Another possibility for deployment would be to run everything inside Docker
containers. Only for reasons as running out of time I chose not to do this now.
It would mean to run everything in containers beginning from Nginx and Varnish
down to the databases. Nothing too spectacular but then again I already have the
necessary Ansible roles for all components and you shouldn't run Docker in
production anyways. At least that is what the Internet tells me. Something like
Kubernetes or Docker Swarm would certainly be too much for this.

### Debian VM, security and common packages

Debian is my default solution for running a server. Tons of resources, battle
tested and I've been using Debian and/or Ubuntu for more than ten years now.
There are however a few things one needs to do in order to secure a server.

#### Firewall

I used to write my IPTables rules by hand, nowadays the
[UFW (uncomplicated firewall)](https://launchpad.net/ufw) does a decent job at
creating these rules for you in a pretty easy manner. The base setup usually
involves disabling all incoming traffic and then only allow the ports you care
about:

    $ ufw default deny incoming

You can even add simple connection rate limiting. Basically this will track
connection attempts and if there are more than 6 attempts in the last 30 seconds
the connecting IP is automatically banned:

    $ ufw limit ssh/tcp

Enable the firewall with:

    $ ufw enable

#### Fail2ban

Now that we have a rate-limiting firewall the next step is to add
[fail2ban](http://www.fail2ban.org/). Rate limiting is good but you do see many
bots that try to guess passwords that are not doing this trying to max-out your
bandwidth. This is what fail2ban is for.

It is a simple Python daemon that tails through logfiles and searches for
regular expressions. In the SSHD case this might be failed login attempts in
`/var/log/auth.log`, for NGINX you might find users trying all possible URLs for
attacking a Wordpress site, e.g. The default installation comes with many
predefined rules that you can enable in `/etc/fail2ban/jail.local`.

#### Secure SSHD

SSH is already pretty secure but you should follow some additional rules. First
we declare the allowed key exchange algorithms, ciphers and macs that the daemon
should use in `/etc/ssh/sshd_config`. Then you really don't want **root** to be
able to login via SSH and to be really safe we only want to use key-based
authentication and disable passwords:

    KexAlgorithms diffie-hellman-group-exchange-sha256
    Ciphers aes256-ctr,aes192-ctr,aes128-ctr
    MACs hmac-sha2-512,hmac-sha2-256,hmac-ripemd160
    PermitRootLogin no
    PubkeyAuthentication yes
    PasswordAuthentication no

#### RootkitHunter

The final barrier is a locally running root kit hunter. Of course you do your
best to not allow anyone gain access to your system but you can never be sure.
The [RootkitHunter](http://rkhunter.sourceforge.net/) is a simple package that
scans for known root kits. You can weekly update the database and have a cron
job running once a day to scan your system:

    $ rkhunter --update
    $ rkhunter --cron-job


#### Common packages

Obviously YMMV at this point but my list of common packages I always install
contains:

    $ apt-get install apt-transport-https debian-goodies git htop iftop iotop \\
        atop python-software-properties sudo unattended-upgrades


This is the basic setup of a new VM. Two of them will be used for our production
system. The next part will add corosync and pacemaker to both systems in order
to automatically set the floating IP to one of the VMs.
