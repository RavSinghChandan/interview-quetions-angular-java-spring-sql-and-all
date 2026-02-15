Good. Now we go into deployment internals at orchestration level.

This is where FAANG/Uber interviews test real maturity.

---

# 📁 File: `08-rolling-update-zero-downtime-internals.md`

# 🔥 STEP 8 — Rolling Update Internals + Zero Downtime Mechanics

(Deployment Algorithm + maxSurge Math + Failure Handling)

You must understand:

* What exactly happens during rollout?
* How maxSurge and maxUnavailable interact?
* How zero downtime is achieved?
* What if new version fails?
* How rollback works internally?

No superficial answers.

---

# 🧠 1️⃣ What Rolling Update Really Means

Rolling update =

Gradually replace old Pods with new Pods
While keeping service available.

It is controlled by:

```yaml
strategy:
  type: RollingUpdate
  rollingUpdate:
    maxSurge: X
    maxUnavailable: Y
```

This defines rollout behavior.

---

# 🧠 2️⃣ maxSurge Explained

maxSurge =

Maximum extra Pods allowed above desired replicas.

Example:

Replicas = 4
maxSurge = 1

During rollout:

Max total pods = 5

This allows new pods to start before old ones die.

---

# 🧠 3️⃣ maxUnavailable Explained

maxUnavailable =

Maximum old Pods allowed to be unavailable.

Example:

Replicas = 4
maxUnavailable = 1

At least 3 pods must remain available.

---

# 🧠 4️⃣ Full Example With Numbers

Deployment:

```
replicas: 4
maxSurge: 1
maxUnavailable: 1
```

Initial:

4 old pods

Step 1:
Create 1 new pod → total = 5

Step 2:
Wait until new pod Ready

Step 3:
Terminate 1 old pod → total = 4

Repeat until all replaced.

At least 3 always running.

Zero downtime achieved.

---

# 🧠 5️⃣ Why Readiness Probe Is Mandatory

Without readiness:

New pod may receive traffic before fully initialized.

With readiness:

* New pod added to endpoints only after healthy.
* Old pod removed only after new pod ready.

Readiness controls traffic gating.

---

# 🧠 6️⃣ Deployment Timeline Internals

When image updated:

1. Deployment creates new ReplicaSet
2. New ReplicaSet scaled up
3. Old ReplicaSet scaled down
4. Pods gradually replaced
5. Old ReplicaSet retained for rollback

Two ReplicaSets coexist temporarily.

---

# 🧠 7️⃣ Rollout Status Check

Monitor rollout:

```bash
kubectl rollout status deployment myapp
```

See history:

```bash
kubectl rollout history deployment myapp
```

---

# 🧠 8️⃣ What If New Pods Fail?

Scenario:

New version has bug.

New pod:

* Crashes
* Fails readiness
* Fails liveness

Deployment behavior:

* Stops progressing
* Old pods continue serving traffic
* Rollout pauses

Zero downtime maintained.

---

# 🧠 9️⃣ Rollback Internals

Rollback:

```bash
kubectl rollout undo deployment myapp
```

What happens:

* Old ReplicaSet scaled up
* New ReplicaSet scaled down
* Traffic restored

Rollback uses previous ReplicaSet stored in history.

---

# 🧠 🔟 Deployment Pause Feature

You can pause rollout:

```bash
kubectl rollout pause deployment myapp
```

Useful for:

* Testing partial rollout
* Canary strategies

Resume:

```bash
kubectl rollout resume deployment myapp
```

---

# 🧠 1️⃣1️⃣ Failure Scenario — No maxSurge

If:

```
maxSurge: 0
maxUnavailable: 1
```

Step:

1. Kill 1 old pod
2. Create 1 new pod

If new pod slow → temporary capacity drop.

Better to allow small surge.

---

# 🧠 1️⃣2️⃣ Failure Scenario — No Readiness

If no readiness probe:

New pod immediately added to service.

If app not ready → errors.

Zero downtime fails.

Readiness is critical.

---

# 🧠 1️⃣3️⃣ High Traffic During Rollout

If traffic spike during deployment:

* HPA may increase replicas
* Deployment logic adapts accordingly
* Need proper resource planning

Deployment and scaling interact.

---

# 🧠 1️⃣4️⃣ Real Interview Simulation

Question:

“How does Kubernetes guarantee zero downtime?”

Strong answer:

* Rolling update strategy
* maxSurge ensures extra capacity
* maxUnavailable ensures minimum availability
* Readiness probes control traffic
* Graceful shutdown prevents dropped requests

Structured. Calm. Confident.

---

# 🧠 1️⃣5️⃣ Senior-Level Mental Model

Rolling update is:

ReplicaSet choreography.

Deployment manages two ReplicaSets:

Old version ↓
New version ↑

Traffic flows only through ready pods.

This is orchestration intelligence.

---

# 🎯 STEP 8 REVISION CHECKLIST

You must confidently explain:

✔ maxSurge math
✔ maxUnavailable impact
✔ ReplicaSet transition
✔ Readiness role in rollout
✔ Rollout status debugging
✔ Rollback internals
✔ Deployment pause
✔ Failure scenarios

If you can explain this clearly →
Deployment orchestration mastery achieved.

---

When ready, type:

**9**

Next file:

📁 `09-health-probes-liveness-readiness-startup-deep.md`
(Deep probe mechanics + probe misconfiguration failures + startupProbe + production debugging)
