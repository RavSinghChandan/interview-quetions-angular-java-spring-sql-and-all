# DEPLOYMENT STRATEGIES — LEVEL 2 (BASIC PRACTICAL)

**Stage Title: “You Deploy Real Code and Watch Traffic Move”**

*(Now theory ends. You start performing real deployments and actually see what happens when a system switches from old version → new version.)*

---

# 🎯 OBJECTIVE OF THIS LEVEL

After this stage you will be able to:

* deploy real applications
* switch versions safely
* restart services correctly
* verify deployments
* roll back broken releases
* understand real deployment behavior

You move from:

```
Understanding Deployment → Performing Deployment
```

Everything here is real-world practical foundation.

---

# 1️⃣ YOUR FIRST REAL DEPLOYMENT (LOCAL SIMULATION)

Create simple server:

```
python3 -m http.server 8000
```

Open browser:

```
localhost:8000
```

You now have running application.

Deployment means:

> replacing this running process with new version.

---

# 2️⃣ NAIVE DEPLOYMENT (WHAT NOT TO DO)

Stop server:

```
CTRL+C
```

Restart:

```
python3 -m http.server 8000
```

During restart:

```
site unavailable
```

That downtime is deployment failure.

---

# 3️⃣ REAL DEPLOYMENT METHOD — PARALLEL RUNNING

Correct approach:

Run new version alongside old.

Terminal 1:

```
python3 -m http.server 8000
```

Terminal 2:

```
python3 -m http.server 8001
```

Now you have:

```
old version → 8000
new version → 8001
```

Both running simultaneously.

This is foundation of all safe deployments.

---

# 4️⃣ TRAFFIC SWITCH SIMULATION

Use curl to test versions:

```
curl localhost:8000
curl localhost:8001
```

Traffic can be redirected from:

```
old → new
```

Real systems do this via load balancers.

---

# 5️⃣ VERSION SWITCH STRATEGY

Safe deployment process:

```
start new version
test new version
redirect traffic
stop old version
```

This avoids downtime.

---

# 6️⃣ VERIFY DEPLOYMENT

After release, always verify:

```
service running?
logs clean?
latency normal?
errors low?
```

Deployment is incomplete until verified.

---

# 7️⃣ ROLLBACK — MOST IMPORTANT SKILL

If new version broken:

Switch traffic back:

```
new → old
```

Rollback must be:

```
instant
safe
simple
```

Good deployment design always supports rollback.

---

# 8️⃣ SIMPLE ROLLBACK SIMULATION

Stop new server:

```
kill process 8001
```

Traffic automatically returns to:

```
old version
```

Rollback complete.

---

# 9️⃣ VERSION TAGGING

Real deployments label versions:

```
v1
v2
v3
```

This allows:

```
tracking
debugging
rollback
comparison
```

Never deploy unnamed builds.

---

# 🔟 ENVIRONMENT SEPARATION

Real systems have environments:

```
dev
staging
production
```

Flow:

```
deploy → staging
test → staging
deploy → production
```

Never deploy directly to production.

---

# 11️⃣ HEALTH CHECKING

Before switching traffic, verify new version healthy.

Test:

```
endpoint responding?
DB connection ok?
memory stable?
```

If unhealthy → do not release.

---

# 12️⃣ GRACEFUL SHUTDOWN

Old version must not be killed instantly.

Instead:

```
stop receiving traffic
finish existing requests
then shutdown
```

This prevents lost user requests.

---

# 13️⃣ COMMON DEPLOYMENT MISTAKES

Avoid:

```
deploying without testing
deploying all servers at once
no rollback plan
no monitoring
no logs
```

These cause outages.

---

# 14️⃣ BASIC DEPLOYMENT SCRIPT EXAMPLE

Example pseudo-script:

```
start new container
run health check
if healthy:
    switch traffic
    stop old container
else:
    stop new container
```

This logic powers real deployment systems.

---

# 15️⃣ REAL ENGINEER DEPLOYMENT CHECKLIST

Before deploy:

```
tests passed?
config correct?
DB migration ready?
rollback ready?
monitoring ready?
```

Professionals always check first.

---

# 16️⃣ WHAT YOU JUST BUILT

You now understand:

```
parallel versions
traffic switching
rollback logic
verification
safe shutdown
```

These are core deployment fundamentals.

---

# 17️⃣ REAL INTERVIEW QUESTION HERE

They won’t ask:

> What is deployment?

They ask:

> How would you deploy new version without downtime?

Expected answer:

```
run new version
test
switch traffic
monitor
rollback if needed
```

They test real understanding.

---

# 18️⃣ REAL PRODUCTION DEPLOYMENT FLOW

Professional deployment sequence:

```
build → test → deploy → verify → monitor → scale
```

Deployment is part of pipeline.

---

# 19️⃣ BASIC COMPLETION CHECK

You can now:

✔ run parallel versions
✔ switch traffic safely
✔ verify deployments
✔ roll back releases
✔ simulate real deployment
✔ understand downtime cause
✔ design safe release flow

You now understand real deployments.

---

# 20️⃣ FINAL LINE

At this point:

> Deployment is no longer mysterious.

You can perform it safely.

And engineers who can deploy safely…

are the ones trusted with production releases.

---

END OF LEVEL 2 — DEPLOYMENT BASIC
