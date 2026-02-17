# CLOUD (AWS) — LEVEL 3 (INTERMEDIATE)

**Stage Title: “You Understand How Real Production Systems Run in the Cloud”**

*(Story continues — now you move from launching single servers → understanding how real companies run scalable production systems on cloud.)*

---

# 🎯 OBJECTIVE OF THIS LEVEL

After this stage you will be able to:

* understand real cloud architectures
* understand scaling systems
* understand load balancing
* understand managed services
* design backend deployments
* reason about production infrastructure

You move from:

```
Using Cloud → Understanding Cloud Systems
```

---

# 1️⃣ REALITY — PRODUCTION SYSTEMS ARE NOT ONE SERVER

Beginner architecture:

```
User → Server
```

Real production architecture:

```
User
 → DNS
 → Load Balancer
 → App Servers
 → Cache
 → Database
 → Storage
```

Real systems are distributed.

One server is never production.

---

# 2️⃣ SCALING PROBLEM

If 1 server handles:

```
100 users
```

What if users become:

```
100,000 ?
```

One machine can’t handle that.

Solution:

> horizontal scaling.

---

# 3️⃣ HORIZONTAL VS VERTICAL SCALING

Vertical scaling:

```
bigger machine
```

Horizontal scaling:

```
more machines
```

Cloud makes horizontal scaling easy.

Production systems always scale horizontally.

---

# 4️⃣ LOAD BALANCER — TRAFFIC DISTRIBUTOR

Instead of sending traffic to one server:

```
Load Balancer
 → Server1
 → Server2
 → Server3
```

Load balancer spreads load.

Benefits:

```
performance
fault tolerance
availability
```

Load balancer is core of production systems.

---

# 5️⃣ AUTO SCALING — CLOUD SUPERPOWER

Cloud can automatically scale servers.

Rules example:

```
CPU > 70% → add server
CPU < 20% → remove server
```

System scales automatically with demand.

No human required.

---

# 6️⃣ STATELESS SERVER DESIGN

Production servers must be:

> stateless.

Meaning:

Server does NOT store user data locally.

Because if server dies:

```
no data lost
```

User data stored in:

```
database
cache
storage service
```

Stateless design enables scaling.

---

# 7️⃣ DATABASE LAYER DESIGN

Databases must be separate from app servers.

Architecture:

```
App servers → Database cluster
```

Why separate?

Because database must persist data even if app servers replaced.

---

# 8️⃣ CACHING LAYER

Real systems use cache:

```
App → Cache → DB
```

Cache stores frequently used data.

Benefits:

```
faster responses
less DB load
better scalability
```

Cache is performance booster.

---

# 9️⃣ STORAGE LAYER

Files should not be stored on server disk.

Why?

Servers are temporary.

Instead use:

> object storage.

Example:

```
upload image → storage service
```

Storage persists independently of servers.

---

# 🔟 NETWORK LAYER

Cloud systems use network isolation.

Architecture:

```
Public subnet → load balancer
Private subnet → app servers
Private subnet → database
```

Sensitive resources never public.

Security through network design.

---

# 11️⃣ MULTI-AZ ARCHITECTURE

Production systems run across multiple availability zones.

Why?

If one data center fails → system still running.

Architecture:

```
AZ1 → servers
AZ2 → servers
AZ3 → servers
```

Traffic distributed across AZs.

---

# 12️⃣ HEALTH CHECK SYSTEM

Load balancers constantly check:

```
Is server alive?
```

If server unhealthy:

```
remove from traffic
```

This prevents failures reaching users.

---

# 13️⃣ FAILOVER DESIGN

If database fails:

Cloud automatically switches to replica.

This is:

> failover.

Failover ensures system continues running during failures.

---

# 14️⃣ DEPLOYMENT ARCHITECTURE

Production deployments must not break system.

Strategies:

```
rolling deploy
blue-green deploy
canary deploy
```

Cloud enables safe deployments.

---

# 15️⃣ MONITORING LAYER

Production systems must be monitored.

Cloud metrics track:

```
CPU
memory
traffic
errors
latency
```

Monitoring detects problems early.

---

# 16️⃣ REAL PRODUCTION FLOW

Real request path:

```
User request
→ DNS resolves
→ Load balancer receives
→ App server processes
→ Cache checked
→ DB queried
→ Response returned
```

Understanding flow = understanding system.

---

# 17️⃣ REAL INCIDENT ANALYSIS EXAMPLE

User complains:

> site slow

Engineer checks:

```
Load balancer latency
App CPU
Cache hit rate
DB latency
```

Finds DB slow.

Fixes DB.

System fast again.

This is real production thinking.

---

# 18️⃣ WHAT INTERVIEWERS TEST HERE

They don’t ask:

> What is S3?

They ask:

> How would you design scalable backend system on cloud?

Expected answer:

```
load balancer
auto scaling
stateless servers
database cluster
cache layer
monitoring
```

They test architecture thinking.

---

# 19️⃣ REAL DIFFERENCE BETWEEN LEVELS

| Level        | Cloud Skill         |
| ------------ | ------------------- |
| Beginner     | launch server       |
| Intermediate | design system       |
| Advanced     | debug production    |
| Pro          | architect platforms |

---

# 20️⃣ INTERMEDIATE COMPLETION CHECK

You now understand:

✔ real cloud architectures
✔ scaling systems
✔ load balancing
✔ stateless design
✔ database separation
✔ caching
✔ failover
✔ monitoring

You now understand how production systems run.

---

# FINAL LINE

At this point:

> Cloud is no longer servers.

Cloud is system architecture.

And engineers who understand architecture…

are the ones trusted with production systems.

---

END OF LEVEL 3 — CLOUD INTERMEDIATE
