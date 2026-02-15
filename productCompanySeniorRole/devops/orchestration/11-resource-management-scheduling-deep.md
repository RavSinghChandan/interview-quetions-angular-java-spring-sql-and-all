Excellent.
Now we move into **real cluster intelligence**.

This is where Kubernetes stops being “YAML” and becomes **resource orchestration logic**.

---

# 📁 File: `11-resource-management-scheduling-deep.md`

# 🔥 STEP 11 — Resource Management + Scheduling + QoS + Eviction

(Cluster Intelligence — Senior Orchestration Depth)

Interviewers may ask:

* How does scheduler decide node?
* What happens if cluster runs out of memory?
* What is QoS class?
* What is eviction?
* What happens under node pressure?

You must answer with control-plane understanding.

---

# 🧠 1️⃣ Why Resource Management Is Critical

Cluster has finite resources:

* CPU
* Memory
* Disk

If unmanaged:

* One pod can starve others
* Node crashes
* Cascading failure

Kubernetes enforces resource governance.

---

# 🧠 2️⃣ Requests vs Limits (Core Concept)

Example:

```yaml
resources:
  requests:
    cpu: "200m"
    memory: "256Mi"
  limits:
    cpu: "500m"
    memory: "512Mi"
```

### Requests

Minimum guaranteed resource.
Used by scheduler.

### Limits

Maximum allowed.
Enforced by kernel.

Very important distinction.

---

# 🧠 3️⃣ How Scheduler Uses Requests

Scheduler checks:

```
Node available resources ≥ Pod requests
```

If node has:

* 2 CPUs free
* Pod requests 0.5 CPU

Pod can schedule.

If no node fits → Pod stays Pending.

Limits are NOT considered for scheduling.

Only requests.

---

# 🧠 4️⃣ CPU Limit Behavior

If CPU limit exceeded:

Container is throttled.

Linux cgroups enforce CPU quota.

Result:

* Increased latency
* Slower response
* No crash

CPU overuse ≠ kill.

---

# 🧠 5️⃣ Memory Limit Behavior

If memory limit exceeded:

Kernel OOM killer kills container.

Result:

* OOMKilled event
* Container restarts
* Pod restart count increases

Memory overuse = kill.

Critical difference from CPU.

---

# 🧠 6️⃣ Quality of Service (QoS) Classes

Kubernetes assigns QoS class based on request/limit.

### Guaranteed

Requests = Limits for all containers.

Example:

```yaml
requests:
  cpu: "500m"
  memory: "512Mi"
limits:
  cpu: "500m"
  memory: "512Mi"
```

Highest priority.

---

### Burstable

Requests < Limits.

Common case.

---

### BestEffort

No requests, no limits.

Lowest priority.

Example:

```yaml
resources: {}
```

Dangerous in production.

---

# 🧠 7️⃣ Eviction Under Node Pressure

If node memory pressure occurs:

Kubernetes evicts pods in this order:

1. BestEffort
2. Burstable
3. Guaranteed

QoS determines survival.

Senior awareness point.

---

# 🧠 8️⃣ Node Pressure Conditions

Check node:

```bash
kubectl describe node mynode
```

Conditions:

* MemoryPressure
* DiskPressure
* PIDPressure

If MemoryPressure = True

Eviction may occur.

---

# 🧠 9️⃣ Pod Priority (Advanced)

Pods can define priority:

```yaml
priorityClassName: high-priority
```

Higher priority pods:

* Scheduled first
* Lower priority pods evicted first

Used in critical systems.

---

# 🧠 🔟 Scheduling Algorithm Overview

Scheduler steps:

1. Filter nodes (feasible nodes)
2. Score nodes
3. Select best node

Filters consider:

* Resource availability
* Node selectors
* Affinity rules
* Taints

Scoring considers:

* Least requested
* Balanced resource allocation
* Custom plugins

Advanced orchestration logic.

---

# 🧠 1️⃣1️⃣ Taints and Tolerations

Taint node:

```bash
kubectl taint nodes node1 key=value:NoSchedule
```

Only pods with matching toleration can schedule.

Used for:

* Dedicated nodes
* Special workloads

---

# 🧠 1️⃣2️⃣ Node Affinity

Example:

```yaml
affinity:
  nodeAffinity:
    requiredDuringSchedulingIgnoredDuringExecution:
      nodeSelectorTerms:
        - matchExpressions:
            - key: zone
              operator: In
              values:
                - us-east-1a
```

Force pod to schedule in specific zone.

Used for data locality.

---

# 🧠 1️⃣3️⃣ Real Failure Scenario

Scenario:

Pods stuck in Pending.

Check:

```bash
kubectl describe pod mypod
```

Likely:

* Insufficient CPU
* Insufficient memory
* Node taint mismatch
* Affinity mismatch

Most common real-world issue.

---

# 🧠 1️⃣4️⃣ Production Best Practices

✔ Always define requests
✔ Avoid BestEffort pods
✔ Tune limits properly
✔ Monitor node pressure
✔ Separate workloads by priority
✔ Consider node autoscaler

---

# 🧠 1️⃣5️⃣ Senior-Level Answer Structure

If asked:

“What happens if node runs out of memory?”

Strong answer:

* Node enters MemoryPressure state
* Kubernetes may evict lower QoS pods
* BestEffort evicted first
* Guaranteed pods protected
* Cluster autoscaler may add new node

Clear. Structured. Mature.

---

# 🎯 STEP 11 REVISION CHECKLIST

You must confidently explain:

✔ Requests vs limits
✔ Scheduler logic
✔ CPU throttling vs OOM kill
✔ QoS classes
✔ Eviction order
✔ Node pressure conditions
✔ Taints & tolerations
✔ Affinity rules
✔ Pending pod debugging

If you can explain all clearly →
Cluster resource mastery achieved.

---

When ready, type:

**12**

Next file:

📁 `12-self-healing-node-failure-recovery.md`
(Node failure detection + rescheduling + controller reaction + cluster resilience mechanics deep dive)
