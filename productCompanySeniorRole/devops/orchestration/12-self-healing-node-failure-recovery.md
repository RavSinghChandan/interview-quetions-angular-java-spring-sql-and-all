Perfect.
Now we enter **real cluster resilience thinking**.

This is orchestration maturity beyond pods.

---

# 📁 File: `12-self-healing-node-failure-recovery.md`

# 🔥 STEP 12 — Node Failure + Self-Healing + Cluster Recovery

(Control Plane Resilience Mechanics — Senior Level)

Interviewers may ask:

* What happens if a node crashes?
* How does Kubernetes detect node failure?
* How are pods rescheduled?
* What happens to running traffic?
* How does cluster remain available?

You must answer step-by-step.

---

# 🧠 1️⃣ The Real Production Scenario

Imagine:

Node-1 running:

* 4 pods of your service

Suddenly:

* VM crashes
* Network failure
* Power loss

What happens?

---

# 🧠 2️⃣ Node Heartbeat Mechanism

Each node sends heartbeat to API Server via Kubelet.

Heartbeat interval ~10 seconds.

If API server does not receive heartbeat:

Node marked:

```
NotReady
```

Check:

```bash
kubectl get nodes
```

Status becomes:

```
NotReady
```

---

# 🧠 3️⃣ Node Controller Reaction

Node Controller in Control Plane detects:

Node missing heartbeat beyond threshold.

Actions:

1. Marks node NotReady
2. Marks pods on that node as Unknown
3. After grace period → deletes pods

This triggers rescheduling.

---

# 🧠 4️⃣ Pod Rescheduling Flow

Example:

Deployment replicas = 5
Node-1 dies → 2 pods lost

Controller sees:

Desired = 5
Actual running = 3

Action:

Create 2 new pods.

Scheduler assigns them to healthy nodes.

Self-healing at cluster level.

---

# 🧠 5️⃣ Traffic Impact During Node Failure

What happens to traffic?

Pods on failed node:

* Become unreachable
* Removed from Service endpoints

Service routes traffic only to healthy pods.

Users may see small spike in latency,
but system continues functioning.

---

# 🧠 6️⃣ Time Delay in Recovery

Important:

Node failure detection not instant.

Timeline:

* Heartbeat missed
* Node marked NotReady (~40s default)
* Pods terminated
* New pods scheduled
* Containers start
* Readiness passes
* Traffic resumes

Recovery takes time.

Design for redundancy.

---

# 🧠 7️⃣ Replica Importance

If replicas = 1

Node failure = total outage.

If replicas ≥ 2 across different nodes

System survives node failure.

High availability requires multiple replicas.

---

# 🧠 8️⃣ PodDisruptionBudget (PDB)

Protect availability during voluntary disruptions.

Example:

```yaml
apiVersion: policy/v1
kind: PodDisruptionBudget
metadata:
  name: myapp-pdb
spec:
  minAvailable: 2
  selector:
    matchLabels:
      app: myapp
```

Ensures at least 2 pods always available.

Prevents too many pods being disrupted at once.

---

# 🧠 9️⃣ Multi-Zone Deployment

In cloud:

Deploy across availability zones.

If one zone fails:

Pods in other zones continue.

Use:

```yaml
topologySpreadConstraints
```

Or node affinity.

Senior-level HA design.

---

# 🧠 🔟 Cluster Autoscaler Interaction

If new pods cannot schedule due to lack of nodes:

Cluster Autoscaler:

* Adds new nodes automatically
* Scheduler assigns pending pods

Scaling at infrastructure level.

---

# 🧠 1️⃣1️⃣ What If etcd Fails?

If etcd fails:

Cluster state unavailable.

Control plane becomes unstable.

Production clusters:

* Run etcd in HA mode
* Multiple replicas

Control plane HA critical.

---

# 🧠 1️⃣2️⃣ Control Plane HA

Production setup:

Multiple API servers
Multiple controller managers
Multiple scheduler instances

Behind load balancer.

Ensures control plane availability.

---

# 🧠 1️⃣3️⃣ Real Interview Scenario

Question:

“What happens if a node crashes?”

Strong answer:

* Kubelet heartbeat stops
* Node marked NotReady
* Node controller deletes pods
* ReplicaSet creates replacement pods
* Scheduler assigns to healthy nodes
* Service removes failed endpoints
* Traffic continues via remaining pods

Clear. Structured. Mature.

---

# 🧠 1️⃣4️⃣ Real Failure Scenario

Scenario:

Pods not rescheduling after node failure.

Possible causes:

* No available node capacity
* Taints preventing scheduling
* Resource requests too high
* Node autoscaler disabled

Debug:

```bash
kubectl describe pod mypod
```

---

# 🧠 1️⃣5️⃣ Senior-Level Mental Model

Kubernetes self-healing works at two layers:

Pod-level (container crash)
Node-level (infrastructure failure)

Control plane constantly reconciles desired state.

Cluster survives failure by:

Redundancy + reconciliation + rescheduling.

---

# 🎯 STEP 12 REVISION CHECKLIST

You must confidently explain:

✔ Node heartbeat detection
✔ NotReady state
✔ Pod rescheduling flow
✔ Service endpoint update
✔ Recovery timeline
✔ Importance of replicas
✔ PodDisruptionBudget
✔ Multi-zone deployment
✔ Control plane HA

If you can explain all clearly →
Cluster resilience mastery achieved.

---

When ready, type:

**13**

Next file:

📁 `13-statefulset-vs-deployment-storage-persistence.md`
(Stateful workloads + stable identity + persistent volumes + PVC + why DB needs StatefulSet deep dive)
