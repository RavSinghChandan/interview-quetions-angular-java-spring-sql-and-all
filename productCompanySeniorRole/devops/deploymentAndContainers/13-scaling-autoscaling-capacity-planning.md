Good. Now we move to scaling maturity.

---

# 📁 File: `13-scaling-autoscaling-capacity-planning.md`

# 🔥 STEP 13 — Horizontal Scaling + HPA + Capacity Planning

(Senior Backend Scaling Intelligence)

This is where backend engineers prove they understand **real traffic behavior**.

Interviewers ask:

* How do you scale a microservice?
* What is HPA?
* CPU-based vs memory-based scaling?
* What happens during traffic spike?
* What are scaling pitfalls?

This file gives you full clarity.

---

# 🧠 1️⃣ Types of Scaling

## 🔹 Vertical Scaling (Scale Up)

Increase resources of same instance.

Example:

* 2GB → 8GB RAM
* 1 CPU → 4 CPUs

Problems:

❌ Hardware limit
❌ Downtime required
❌ Expensive

Used rarely in cloud-native systems.

---

## 🔹 Horizontal Scaling (Scale Out)

Increase number of instances.

Example:

* 3 pods → 10 pods

✔ No downtime
✔ Cloud-native
✔ More resilient

This is default strategy in Kubernetes.

---

# 🧠 2️⃣ Horizontal Pod Autoscaler (HPA)

Kubernetes can auto-scale based on metrics.

Example:

```yaml
apiVersion: autoscaling/v2
kind: HorizontalPodAutoscaler
metadata:
  name: myapp-hpa
spec:
  scaleTargetRef:
    apiVersion: apps/v1
    kind: Deployment
    name: myapp
  minReplicas: 3
  maxReplicas: 10
  metrics:
  - type: Resource
    resource:
      name: cpu
      target:
        type: Utilization
        averageUtilization: 70
```

Meaning:

* If CPU > 70% → scale up
* If CPU < threshold → scale down

---

# 🧠 3️⃣ CPU-Based Scaling

Most common metric.

Why?

CPU correlates with:

* Request load
* Computation effort

But not always enough.

---

# 🧠 4️⃣ Memory-Based Scaling

Scale when memory usage high.

Example:

```yaml
resource:
  name: memory
  target:
    type: Utilization
    averageUtilization: 75
```

Be careful:

Memory may not drop immediately.
Scaling on memory can be tricky.

---

# 🧠 5️⃣ Custom Metrics Scaling (Advanced)

Scale based on:

* Request per second
* Queue length
* Kafka lag
* Prometheus metrics

Used in advanced systems.

---

# 🧠 6️⃣ Scaling Timeline Example

Initial:

3 pods
CPU 40%

Traffic spike:

CPU 85%

HPA triggers:

Scale to 5 pods
Then 7
Then 9

Once traffic drops → scale down gradually.

Autoscaling not instant.
Takes seconds/minutes.

---

# 🧠 7️⃣ Requests vs Limits Impact on Scaling

If CPU request too high:

Scheduler may not schedule pods.

If limit too low:

Pods get throttled.

Balanced configuration is critical.

Example:

```yaml
resources:
  requests:
    cpu: "250m"
  limits:
    cpu: "1"
```

---

# 🧠 8️⃣ Cold Start Problem

When scaling up:

* New pods must pull image
* Start JVM
* Initialize DB connections
* Warm cache

If image large → scaling slow.

Optimization needed (Step 5 importance).

---

# 🧠 9️⃣ Stateless Requirement for Scaling

Only stateless services scale easily.

Stateful apps require:

* Sticky sessions
* Shared storage
* Distributed cache

Backend principle:

Application containers must be stateless.

---

# 🧠 🔟 Capacity Planning Thinking

Senior-level thinking:

If:

1 pod handles 200 RPS
And traffic peak = 2000 RPS

Need:

~10 pods minimum

Add buffer.

Never rely only on autoscaling.

---

# 🧠 1️⃣1️⃣ Scaling Pitfalls

Common mistakes:

❌ Scaling CPU-bound app without DB scaling
❌ Scaling app but DB becomes bottleneck
❌ No connection pool tuning
❌ Too many pods causing DB overload

Scaling must consider entire system.

---

# 🧠 1️⃣2️⃣ Real Interview Scenarios

---

### Scenario 1

Traffic spike causes high latency.

Possible:

* HPA threshold too high
* Image pull delay
* CPU throttling
* DB bottleneck

---

### Scenario 2

Pods scale to 10 but latency still high.

Likely:

* DB overloaded
* Cache missing
* External API slow

Scaling app layer alone not enough.

---

### Scenario 3

Pods constantly scale up and down.

Called:

“Scaling thrashing”

Solution:

* Adjust HPA thresholds
* Add stabilization window

---

# 🧠 1️⃣3️⃣ Node Scaling

If cluster nodes full:

New pods cannot schedule.

Use:

Cluster Autoscaler.

Scaling layers:

* Pod scaling (HPA)
* Node scaling (Cluster Autoscaler)

---

# 🧠 1️⃣4️⃣ Production Scaling Strategy

✔ Define minReplicas
✔ Define maxReplicas
✔ Monitor CPU & memory
✔ Pre-warm frequently used services
✔ Monitor DB load
✔ Load test before release

---

# 🧠 1️⃣5️⃣ Senior-Level Answer Structure

If asked:

“How do you handle traffic spike?”

Answer:

* Use HPA for horizontal scaling
* Ensure readiness probe correct
* Keep image small for fast scaling
* Monitor DB and downstream services
* Use caching
* Plan capacity proactively

Clear. Structured. Mature.

---

# 🎯 STEP 13 REVISION CHECKLIST

You must confidently explain:

✔ Vertical vs horizontal scaling
✔ HPA configuration
✔ CPU vs memory scaling
✔ Custom metrics scaling
✔ Cold start problem
✔ Stateless requirement
✔ Capacity planning
✔ Scaling pitfalls
✔ Node autoscaling

If you can explain all clearly →
Scaling maturity achieved.

---

When ready, type:

**14**

Next file:

📁 `14-security-hardening-container-cloud.md`
(Container security + image scanning + least privilege + network policies + production hardening)
