Good. Stay sharp.
Now we enter performance depth.

---

# 📁 File: `09-resource-limits-jvm-tuning-performance.md`

# 🔥 STEP 9 — Resource Limits + OOMKilled + CPU Throttling + JVM Tuning

(Senior Backend Performance Awareness in Containers)

This is where strong backend engineers stand out.

Interviewers ask:

* What is OOMKilled?
* Why does JVM crash in container?
* How do CPU limits affect latency?
* How do you debug memory leak in Kubernetes?

This file makes you production-ready.

---

# 🧠 1️⃣ Resource Limits in Containers

Containers are limited via **cgroups**.

You define limits at runtime:

### Docker:

```bash
docker run --memory="512m" --cpus="1.0" myapp
```

### Kubernetes:

```yaml
resources:
  requests:
    memory: "256Mi"
    cpu: "500m"
  limits:
    memory: "512Mi"
    cpu: "1"
```

Difference:

* Requests → guaranteed minimum
* Limits → maximum allowed

---

# 🧠 2️⃣ What Is OOMKilled?

OOM = Out Of Memory.

If container memory usage exceeds limit:

Linux kernel kills process.

Check:

```bash
docker inspect <container_id>
```

Look for:

```json
"OOMKilled": true
```

In Kubernetes:

```bash
kubectl describe pod mypod
```

Look for:

```
Reason: OOMKilled
```

Very common real-world issue.

---

# 🧠 3️⃣ Why JVM Gets OOMKilled in Container

Scenario:

Container memory limit = 512MB
JVM default heap = 1GB

JVM tries to allocate >512MB →
Kernel kills container.

This is NOT Java OOM.
This is Linux OOM kill.

Important distinction.

---

# 🧠 4️⃣ JVM Heap Tuning for Containers

Correct practice:

Heap must be less than container limit.

Example:

Container = 512MB

Set:

```dockerfile
ENTRYPOINT ["java","-Xms256m","-Xmx384m","-jar","app.jar"]
```

Keep headroom for:

* Metaspace
* Thread stacks
* Native memory
* GC overhead

Rule of thumb:

Heap ≈ 60–70% of container memory.

---

# 🧠 5️⃣ Modern JVM Container Awareness

Java 10+ supports container memory detection.

Option:

```bash
-XX:+UseContainerSupport
```

Now JVM detects cgroup limits.

Still recommended to explicitly control heap.

---

# 🧠 6️⃣ CPU Limits and Throttling

If set:

```bash
docker run --cpus="0.5" myapp
```

App gets half CPU core.

If traffic high:

* CPU throttling occurs
* Response time increases
* GC pauses longer

In Kubernetes:

```yaml
limits:
  cpu: "500m"
```

500m = 0.5 CPU.

Throttling is real. It impacts latency.

---

# 🧠 7️⃣ Requests vs Limits (Kubernetes Critical Concept)

Example:

```yaml
requests:
  cpu: "250m"
limits:
  cpu: "1"
```

Meaning:

* Scheduler reserves 0.25 CPU
* Pod allowed up to 1 CPU

If you set limit too low → throttling.

If you set no limit → may starve other pods.

Senior balance required.

---

# 🧠 8️⃣ Monitoring Resource Usage

Docker:

```bash
docker stats
```

Kubernetes:

```bash
kubectl top pod mypod
```

You must correlate:

* CPU spikes
* Memory spikes
* GC pauses
* Traffic patterns

---

# 🧠 9️⃣ Debugging High Memory Usage

Steps:

1. Check container limit
2. Check JVM heap size
3. Check GC logs
4. Check object retention (heap dump)
5. Check connection pool size
6. Check caching layer

Heap dump inside container:

```bash
jmap -dump:live,format=b,file=heap.hprof <pid>
```

Advanced but strong signal.

---

# 🧠 🔟 Debugging CPU Spikes

Possible causes:

* Infinite loop
* Blocking I/O
* Thread starvation
* Too many threads
* GC overhead

Check thread dump:

```bash
jstack <pid>
```

Inside container:

```bash
docker exec -it myapp jstack 1
```

---

# 🧠 1️⃣1️⃣ GC Behavior in Containers

If heap too large:

* Long GC pauses

If heap too small:

* Frequent GC
* High CPU usage

Balance is required.

Common JVM flags:

```bash
-XX:+UseG1GC
```

G1GC is default in modern JVM.

---

# 🧠 1️⃣2️⃣ Memory Leak in Kubernetes Scenario

Symptoms:

* Memory gradually increases
* Eventually OOMKilled
* Pod restarts repeatedly

Investigate:

* Heap dump
* Caching strategy
* Unclosed resources
* Static collections

---

# 🧠 1️⃣3️⃣ Real Interview Scenarios

---

### Scenario 1 — Pod keeps restarting every few hours

Likely:

* Memory leak
* OOMKilled
* GC misconfiguration

---

### Scenario 2 — Latency increases under load

Possible:

* CPU throttling
* DB bottleneck
* Thread pool exhaustion
* GC pressure

---

### Scenario 3 — Works locally, fails in container

Likely:

* Local machine unlimited memory
* Container limited
* JVM not tuned

---

# 🧠 1️⃣4️⃣ Production Resource Strategy

Good practice:

✔ Define requests and limits
✔ Tune JVM heap properly
✔ Monitor metrics
✔ Use autoscaling
✔ Avoid unlimited memory

---

# 🧠 1️⃣5️⃣ Senior-Level Explanation Structure

If asked:

“How do you handle memory management in Kubernetes?”

Answer:

* Define memory requests and limits
* Tune JVM heap relative to limit
* Monitor OOMKilled events
* Analyze heap dump if leak suspected
* Use autoscaling for traffic spikes

Clear. Structured. Confident.

---

# 🎯 STEP 9 REVISION CHECKLIST

You must confidently explain:

✔ cgroups resource limits
✔ OOMKilled vs Java OOM
✔ JVM heap tuning in container
✔ CPU throttling
✔ requests vs limits
✔ docker stats / kubectl top
✔ GC impact
✔ Debugging memory leak
✔ Thread dump usage

If you can explain all clearly →
Performance maturity achieved.

---

When ready, type:

**10**

Next file:

📁 `10-networking-service-communication.md`
(Container networking + bridge + service discovery + internal DNS + load balancing basics)
