# DEPLOYMENT STRATEGIES — LEVEL 3 (INTERMEDIATE)

**Stage Title: “You Understand How Real Production Systems Deploy Without Downtime”**

*(Story continues — now you move from deploying simple apps → understanding how real companies deploy distributed systems safely while millions of users are online.)*

---

# 🎯 OBJECTIVE OF THIS LEVEL

After this stage you will:

* understand real production deployment architectures
* understand traffic shifting
* understand distributed rollout logic
* understand version coexistence
* understand zero-downtime deployments
* understand real release pipelines

You move from:

```
Performing Deployment → Understanding Production Deployment Systems
```

---

# 1️⃣ REALITY — PRODUCTION DEPLOYMENT IS NOT ONE SERVER

Beginner system:

```
User → Server
```

Real system:

```
Users
 → Load Balancer
 → 20 App Servers
 → Databases
 → Caches
```

When deploying…

You must update:

> all 20 servers without breaking users.

---

# 2️⃣ WHY WE CAN’T UPDATE ALL SERVERS AT ONCE

If you replace all servers instantly:

```
old version → stopped
new version → starts
```

Risk:

```
if new version broken → entire system down
```

So professionals never deploy all at once.

---

# 3️⃣ ROLLING DEPLOYMENT (MOST COMMON STRATEGY)

Instead of replacing all servers:

```
update few → test → update few → test
```

Example:

```
20 servers
deploy 2 at a time
```

Traffic flow:

```
18 old
2 new
```

If stable → continue.

If broken → stop rollout.

This minimizes risk.

---

# 4️⃣ ROLLING DEPLOYMENT FLOW

Professional rollout:

```
remove server from traffic
deploy new version
health check
add back to traffic
repeat next server
```

Users never see downtime.

---

# 5️⃣ WHY ROLLING DEPLOYMENTS WORK

Because system always has:

```
some healthy servers
```

Even if one fails, others handle traffic.

Rolling deploy ensures continuous availability.

---

# 6️⃣ BLUE-GREEN DEPLOYMENT

Architecture:

```
Blue = current version
Green = new version
```

Both environments exist simultaneously.

Flow:

```
deploy Green
test Green
switch traffic
delete Blue
```

Instant switch.

Rollback is instant too.

---

# 7️⃣ WHY BLUE-GREEN IS POWERFUL

Because rollback is easy:

```
switch traffic back to Blue
```

No redeploy needed.

Fast recovery = safer system.

---

# 8️⃣ CANARY DEPLOYMENT (SMART TESTING)

Instead of sending all users to new version:

Send small percentage:

```
95% → old version
5% → new version
```

Observe behavior.

If safe → increase traffic.

If broken → stop rollout.

Canary = early risk detection.

---

# 9️⃣ CANARY TRAFFIC PROGRESSION

Example progression:

```
5%
20%
50%
100%
```

Gradual rollout reduces blast radius.

---

# 🔟 SHADOW DEPLOYMENT

New version receives real traffic…

But responses ignored.

Used for testing performance + behavior.

Flow:

```
user → old version (response used)
     → new version (response ignored)
```

This allows testing safely.

---

# 11️⃣ VERSION COMPATIBILITY PROBLEM

During deployment:

Old + New versions run together.

They must be compatible.

Example issue:

```
new version expects new DB schema
old version uses old schema
```

Solution:

> backward compatible changes.

Real deployments must handle mixed versions.

---

# 12️⃣ DATABASE MIGRATION STRATEGY

Correct migration approach:

```
deploy DB change (compatible)
deploy code change
remove old support later
```

Never break old version during rollout.

---

# 13️⃣ SESSION PROBLEM

If user connected to server that restarts:

Session may be lost.

Solutions:

```
external session storage
stateless servers
sticky sessions
```

Stateless architecture makes deployments easier.

---

# 14️⃣ REAL DEPLOYMENT PIPELINE FLOW

Professional release pipeline:

```
code commit
→ build
→ test
→ package
→ deploy staging
→ test
→ deploy production gradually
→ monitor
```

Deployment is last step of pipeline.

---

# 15️⃣ HEALTH CHECK ROLE

Before sending traffic to new server:

System checks:

```
is service responding?
is DB connected?
is memory ok?
```

Only healthy servers receive traffic.

---

# 16️⃣ MONITORING DURING DEPLOYMENT

During rollout engineers watch:

```
error rate
latency
CPU
memory
traffic
logs
```

If anomaly detected → stop deployment.

Monitoring protects releases.

---

# 17️⃣ WHAT INTERVIEWERS TEST HERE

They don’t ask:

> What is rolling deployment?

They ask:

> How would you deploy update to 100 servers safely?

Expected reasoning:

```
rolling deployment
health checks
monitoring
rollback
```

They test system thinking.

---

# 18️⃣ REAL DIFFERENCE BETWEEN LEVELS

| Level        | Deployment Skill            |
| ------------ | --------------------------- |
| Beginner     | deploy app                  |
| Intermediate | deploy system               |
| Advanced     | debug failed deploy         |
| Pro          | design release architecture |

---

# 19️⃣ INTERMEDIATE COMPLETION CHECK

You now understand:

✔ rolling deployments
✔ blue-green releases
✔ canary releases
✔ traffic shifting
✔ compatibility issues
✔ database migrations
✔ deployment pipelines
✔ monitoring releases

You now understand real production deployment.

---

# 20️⃣ FINAL LINE

At this point:

> Deployment is no longer a step.

It is an orchestrated system process.

And engineers who understand deployment systems…

are the ones trusted with production releases.

---

END OF LEVEL 3 — DEPLOYMENT INTERMEDIATE
