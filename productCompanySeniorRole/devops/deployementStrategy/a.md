# DEPLOYMENT STRATEGIES — LEVEL 5 (ADVANCED)

**Stage Title: “You Become the Engineer Called When Deployments Break Production”**

*(Story continues — now you enter real incident territory. Deployments fail. Users complain. Metrics spike. And you must diagnose what went wrong.)*

---

# 🎯 OBJECTIVE OF THIS LEVEL

After this stage you will be able to:

* debug failed deployments
* identify rollout issues
* detect release-related outages
* trace root cause of production failures
* analyze rollout metrics
* recover systems safely

You move from:

```
Understanding Deployment Systems → Fixing Deployment Failures
```

---

# 1️⃣ REALITY — MOST PRODUCTION OUTAGES HAPPEN DURING DEPLOYMENT

Why?

Because deployment changes:

```
code
config
versions
dependencies
network routes
scaling behavior
```

Changes are risk.

Deployment = highest-risk event.

---

# 2️⃣ GOLDEN RULE OF DEPLOYMENT INCIDENTS

When system breaks after release:

Never assume coincidence.

Always assume:

> deployment caused it.

Check recent changes first.

---

# 3️⃣ FAILURE TYPE #1 — CRASHING INSTANCES

Symptoms:

```
pods restarting
process exiting
health checks failing
```

Cause:

```
runtime error
missing dependency
bad config
startup failure
```

Check logs first.

---

# 4️⃣ FAILURE TYPE #2 — HEALTH CHECK FAILURE

Symptoms:

```
deployment stuck
new instances never receive traffic
```

Cause:

```
wrong health endpoint
startup delay too short
port mismatch
readiness probe failure
```

Deployment waits for health before routing traffic.

---

# 5️⃣ FAILURE TYPE #3 — VERSION INCOMPATIBILITY

Symptoms:

```
old version works
new version errors
mixed environment failures
```

Cause:

```
API contract change
schema mismatch
protocol change
```

Mixed versions must remain compatible.

---

# 6️⃣ FAILURE TYPE #4 — DATABASE MIGRATION ISSUE

Symptoms:

```
queries fail
errors spike
new version crashes
```

Cause:

```
migration ran too early
schema mismatch
missing column
wrong index
```

DB changes must be staged carefully.

---

# 7️⃣ FAILURE TYPE #5 — CONFIGURATION ERROR

Symptoms:

```
service fails immediately
connection errors
access denied
```

Cause:

```
wrong environment variable
missing secret
bad config file
```

Config mistakes are extremely common.

---

# 8️⃣ FAILURE TYPE #6 — RESOURCE SPIKE

Symptoms:

```
CPU spike
memory spike
latency spike
timeouts
```

Cause:

```
inefficient code
memory leak
heavy query
infinite loop
```

New version may consume more resources.

---

# 9️⃣ FAILURE TYPE #7 — TRAFFIC SHIFT TOO FAST

Symptoms:

```
system stable initially
then suddenly fails
```

Cause:

```
traffic switched too quickly
new version overloaded
```

Gradual rollout prevents this.

---

# 🔟 FAILURE TYPE #8 — DEPENDENCY FAILURE

Symptoms:

```
new version fails
old version works
```

Cause:

```
new dependency version incompatible
external service mismatch
API contract change
```

Dependencies must be tested before release.

---

# 11️⃣ FAILURE TYPE #9 — CACHE ISSUE

Symptoms:

```
wrong data
inconsistent responses
random failures
```

Cause:

```
cache schema change
stale data
serialization mismatch
```

Cache must be compatible across versions.

---

# 12️⃣ FAILURE TYPE #10 — ROLLBACK FAILURE

Worst-case scenario:

Rollback fails too.

Cause:

```
database already migrated
old version incompatible
config overwritten
state changed
```

This is why rollback planning matters.

---

# 13️⃣ REAL INCIDENT DEBUG FLOW (USED BY SENIORS)

When deployment breaks system:

Follow exact order:

```
Check metrics
Check logs
Check rollout status
Check health checks
Check recent config
Check DB changes
Check traffic shift
```

Never guess.

Always trace.

---

# 14️⃣ ROOT CAUSE THINKING MODEL

Senior engineers ask:

```
What changed?
When did failure start?
Which component failed first?
```

Failures almost always correlate with change.

---

# 15️⃣ CASCADING DEPLOYMENT FAILURE MODEL

Example chain:

```
new version slow
→ latency increases
→ retries increase
→ load increases
→ servers overload
→ system crashes
```

Root cause = slow code.

Symptoms everywhere.

---

# 16️⃣ METRIC ANALYSIS DURING DEPLOYMENT

Metrics reveal deployment issues:

| Metric       | Signal            |
| ------------ | ----------------- |
| error rate ↑ | bug               |
| latency ↑    | performance issue |
| CPU ↑        | inefficient code  |
| memory ↑     | leak              |
| traffic ↓    | routing issue     |

Metrics are deployment truth.

---

# 17️⃣ INTERVIEW QUESTION THEY ASK HERE

They don’t ask:

> What is canary deployment?

They ask:

> After deploying new version, errors increased. What do you do?

Expected reasoning:

```
check metrics
check logs
compare versions
roll back if needed
find root cause
```

They test incident thinking.

---

# 18️⃣ REAL DIFFERENCE BETWEEN LEVELS

| Level        | Deployment Relationship |
| ------------ | ----------------------- |
| Beginner     | deploys                 |
| Intermediate | understands             |
| Advanced     | debugs                  |
| Pro          | designs release systems |

---

# 19️⃣ ADVANCED COMPLETION CHECK

You can now:

✔ diagnose failed deployments
✔ analyze rollout failures
✔ detect config issues
✔ debug version mismatch
✔ trace deployment root causes
✔ interpret release metrics
✔ recover systems safely
✔ reason about failures

---

# 20️⃣ FINAL LINE

At this point:

> Deployment is no longer scary.

It is predictable.

And engineers who can predict deployment behavior…

are the ones trusted during releases.

---

END OF LEVEL 5 — DEPLOYMENT ADVANCED
