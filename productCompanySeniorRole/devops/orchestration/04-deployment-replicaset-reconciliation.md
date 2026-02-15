Good. Now we move to the object that actually runs production systems.

---

# 📁 File: `04-deployment-replicaset-reconciliation.md`

# 🔥 STEP 4 — Deployment + ReplicaSet + Reconciliation Deep Dive

(Core Production Controller — Senior Level Understanding)

This is extremely important.

Interviewers ask:

* What is Deployment?
* What is ReplicaSet?
* How does Kubernetes maintain replicas?
* What happens during rollout?
* How does rollback work internally?

You must answer structurally.

---

# 🧠 1️⃣ Why Deployment Exists

You should NOT deploy raw Pods in production.

Why?

Because:

* Pods are ephemeral
* Pods don’t self-heal by themselves
* No replica management
* No rolling updates

Deployment provides:

✔ Replica management
✔ Rolling updates
✔ Rollback capability
✔ Self-healing

---

# 🧠 2️⃣ Deployment vs ReplicaSet

Important hierarchy:

```
Deployment
    ↓
ReplicaSet
    ↓
Pods
```

You create Deployment.

Deployment creates ReplicaSet.

ReplicaSet creates Pods.

---

# 🧠 3️⃣ Basic Deployment YAML

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: myapp
spec:
  replicas: 3
  selector:
    matchLabels:
      app: myapp
  template:
    metadata:
      labels:
        app: myapp
    spec:
      containers:
        - name: app
          image: myapp:1.0
          ports:
            - containerPort: 8080
```

Key sections:

* replicas
* selector
* template

Template defines Pod spec.

---

# 🧠 4️⃣ What Is ReplicaSet?

ReplicaSet ensures:

```
N number of Pods are always running
```

If 1 pod crashes:

ReplicaSet detects:

Desired = 3
Actual = 2

Action:
Create 1 more pod.

Self-healing at replica level.

---

# 🧠 5️⃣ Reconciliation Loop (Core Concept)

Controller continuously checks:

Desired state (replicas: 3)

vs

Actual state (pods running)

If mismatch → reconcile.

This loop runs constantly.

This is Kubernetes power.

---

# 🧠 6️⃣ What Happens During Update?

You change image:

```yaml
image: myapp:2.0
```

Apply:

```bash
kubectl apply -f deployment.yaml
```

Now:

1. Deployment creates new ReplicaSet
2. New ReplicaSet creates pods (V2)
3. Old ReplicaSet scaled down gradually

Two ReplicaSets temporarily coexist.

---

# 🧠 7️⃣ Rolling Update Strategy

Default strategy:

```yaml
strategy:
  type: RollingUpdate
  rollingUpdate:
    maxSurge: 1
    maxUnavailable: 1
```

Meaning:

* maxSurge: extra pod allowed
* maxUnavailable: old pods that can go down

Example:

Replicas = 3
During update:

At most:

* 4 total pods (3 + 1 surge)
* At least 2 available

Zero downtime.

---

# 🧠 8️⃣ Version History

Check rollout history:

```bash
kubectl rollout history deployment myapp
```

Kubernetes stores previous ReplicaSets.

Enables rollback.

---

# 🧠 9️⃣ Rollback Mechanism

Rollback:

```bash
kubectl rollout undo deployment myapp
```

Deployment scales down new ReplicaSet
Scales up old ReplicaSet.

Fast recovery.

---

# 🧠 🔟 What If New Version Fails?

If new pods fail readiness:

Deployment pauses.

Check status:

```bash
kubectl rollout status deployment myapp
```

Fix issue or rollback.

Old version still serving traffic.

Safe deployment.

---

# 🧠 1️⃣1️⃣ Scaling Deployment

Scale manually:

```bash
kubectl scale deployment myapp --replicas=5
```

ReplicaSet ensures 5 pods.

Or use HPA (later step).

---

# 🧠 1️⃣2️⃣ Selector Importance

Selector must match template labels.

Example:

```yaml
selector:
  matchLabels:
    app: myapp
```

If mismatch → Deployment fails.

Critical YAML understanding.

---

# 🧠 1️⃣3️⃣ Deployment Failure Scenarios

---

### Scenario 1

Pods stuck in Pending.

Possible:

* Not enough node resources
* Image pull failure
* Node selector mismatch

---

### Scenario 2

Deployment stuck in Progressing.

Possible:

* Readiness failing
* CrashLoopBackOff
* Resource limits too low

---

### Scenario 3

All pods restarted during deployment.

Likely:

* maxUnavailable too high
* No readiness probe
* Graceful shutdown missing

---

# 🧠 1️⃣4️⃣ Real Interview Question Simulation

Question:

“How does Kubernetes perform rolling updates?”

Answer:

* Deployment creates new ReplicaSet
* Gradually scales up new pods
* Gradually scales down old pods
* Controlled via maxSurge and maxUnavailable
* Readiness ensures safe traffic routing

Clear. Structured. Mature.

---

# 🧠 1️⃣5️⃣ Senior-Level Mental Model

Deployment = Version manager
ReplicaSet = Replica enforcer
Pod = Execution unit

Deployment manages lifecycle across versions.

---

# 🎯 STEP 4 REVISION CHECKLIST

You must confidently explain:

✔ Deployment vs ReplicaSet
✔ Reconciliation loop
✔ Rolling update mechanism
✔ maxSurge & maxUnavailable
✔ Rollout history
✔ Rollback process
✔ Selector importance
✔ Deployment failure debugging

If you can explain this clearly →
Deployment-level maturity achieved.

---

When ready, type:

**5**

Next file:

📁 `05-service-deep-dive-clusterip-nodeport-loadbalancer.md`
(Service internals + stable IP + kube-proxy + load balancing mechanics deep dive)
