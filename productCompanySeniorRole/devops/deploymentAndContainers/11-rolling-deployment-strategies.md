Good. Now we move into deployment control strategies.

---

# 📁 File: `11-rolling-deployment-strategies.md`

# 🔥 STEP 11 — Rolling Updates + Blue-Green + Canary + Rollback

(Zero Downtime Deployment Deep Dive — Senior Level)

This is extremely important.

Interviewers ask:

* How do you deploy without downtime?
* What is rolling update?
* How do you rollback?
* What is blue-green?
* What is canary deployment?

You must answer with structure.

---

# 🧠 1️⃣ The Deployment Problem

You have:

* Version V1 running in production
* You want to deploy V2

Goal:

✔ No downtime
✔ No dropped requests
✔ Safe rollback

Naive approach:

Stop V1 → Deploy V2 → Start

Result:

❌ Downtime
❌ User errors

Not acceptable.

---

# 🧠 2️⃣ Rolling Deployment (Most Common)

Concept:

Replace old pods gradually.

Example:

Current state:

```
3 pods → V1
```

Rolling update:

1. Create 1 pod → V2
2. Wait until ready
3. Remove 1 pod → V1
4. Repeat

At no time are all pods down.

---

## Kubernetes Rolling Update Config

```yaml
strategy:
  type: RollingUpdate
  rollingUpdate:
    maxUnavailable: 1
    maxSurge: 1
```

Meaning:

* maxUnavailable: 1 old pod can go down
* maxSurge: 1 extra pod can be created

Zero downtime achieved.

---

# 🧠 3️⃣ What Makes Rolling Update Safe?

✔ Readiness probe
✔ Graceful shutdown
✔ terminationGracePeriodSeconds
✔ Proper resource allocation

Without readiness → unsafe.

---

# 🧠 4️⃣ Rollback Mechanism

If deployment fails:

```bash
kubectl rollout undo deployment myapp
```

Kubernetes keeps previous ReplicaSet.

Image versioning must support rollback.

Never use `latest`.

---

# 🧠 5️⃣ Blue-Green Deployment

Two identical environments:

Blue → current production
Green → new version

Steps:

1. Deploy V2 to Green
2. Test internally
3. Switch traffic from Blue → Green
4. Keep Blue for rollback

Advantages:

✔ Instant rollback
✔ Safer

Disadvantages:

❌ Double infrastructure cost

Used in critical systems.

---

# 🧠 6️⃣ Canary Deployment

Concept:

Release new version to small percentage.

Example:

* 5% traffic → V2
* Monitor metrics
* If stable → 50%
* Then 100%

Reduces risk.

Often implemented using:

* Service mesh (Istio)
* Advanced ingress controllers

---

# 🧠 7️⃣ Deployment Failure Scenario

Suppose:

After deploying V2:

* Error rate spikes
* Latency increases

Actions:

1. Check metrics
2. Rollback immediately
3. Investigate logs
4. Fix issue

Never debug in production live without rollback.

---

# 🧠 8️⃣ Backward-Compatible Database Migrations

Very common mistake.

Wrong:

* Remove column in V2
* V1 still running → breaks

Correct pattern:

1. Add new column (safe)
2. Deploy V2 using new column
3. Remove old column later

DB migration must be backward compatible.

Senior awareness.

---

# 🧠 9️⃣ Zero Downtime Timeline Example

3 replicas → V1

Rolling:

* Create V2-1
* Readiness false
* After ready → traffic allowed
* Terminate V1-1
* Repeat

Requests never dropped.

---

# 🧠 🔟 What If Pod Fails During Deployment?

If V2 pod fails readiness:

* Traffic not routed
* Deployment paused
* Old pods continue serving

Safe behavior.

---

# 🧠 1️⃣1️⃣ Autoscaling Interaction

During rolling deployment:

If traffic spike occurs:

* HPA (Horizontal Pod Autoscaler) may create more pods
* Resource planning important

Deployment strategy must consider scaling.

---

# 🧠 1️⃣2️⃣ Versioning Best Practices

Tag images:

```
myapp:1.4.2
```

Not:

```
myapp:latest
```

Rollback requires deterministic versioning.

---

# 🧠 1️⃣3️⃣ Observability During Deployment

Before declaring success, check:

✔ Error rate
✔ Latency
✔ CPU usage
✔ Memory usage
✔ Logs

Deployment without monitoring = blind risk.

---

# 🧠 1️⃣4️⃣ Real Interview Scenarios

---

### Scenario 1

After deployment, 2% requests fail.

Likely:

* No graceful shutdown
* readiness misconfigured
* DB migration incompatible

---

### Scenario 2

Deployment stuck in “Progressing”.

Possible:

* New pods failing readiness
* Image pull error
* Crash loop

---

# 🧠 1️⃣5️⃣ Senior-Level Answer Structure

If asked:

“How do you ensure zero downtime deployment?”

Answer:

* Use rolling update strategy
* Configure readiness probes
* Implement graceful shutdown
* Use versioned images
* Monitor metrics
* Rollback if failure detected

Clear. Structured. Confident.

---

# 🎯 STEP 11 REVISION CHECKLIST

You must confidently explain:

✔ Rolling update mechanics
✔ maxSurge & maxUnavailable
✔ Blue-Green strategy
✔ Canary strategy
✔ Rollback process
✔ DB migration safety
✔ Deployment monitoring
✔ Failure handling

If you can explain all calmly →
Deployment strategy mastery achieved.

---

When ready, type:

**12**

Next file:

📁 `12-container-crash-handling-self-healing.md`
(Container crashes + restart policies + CrashLoopBackOff + production resilience patterns)
