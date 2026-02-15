Good. Now we go into resilience layer.

---

# 📁 File: `12-container-crash-handling-self-healing.md`

# 🔥 STEP 12 — Container Crash Handling + Restart Policies + Self-Healing

(Production Resilience — Senior Backend Maturity)

This file is about failure.

Interviewers love asking:

* What happens if a container crashes?
* What is CrashLoopBackOff?
* How does Kubernetes self-heal?
* What would you check if pods keep restarting?

This is operational depth.

---

# 🧠 1️⃣ Container Crash — What Actually Happens?

If application throws fatal exception:

```java
System.exit(1);
```

Or unhandled runtime exception →
Main process exits → Container stops.

Docker behavior depends on restart policy.

---

# 🧠 2️⃣ Docker Restart Policies

Run container:

```bash
docker run --restart=always myapp
```

Options:

* no (default)
* on-failure
* always
* unless-stopped

Example:

```bash
docker run --restart=on-failure:3 myapp
```

Retries up to 3 times.

In production → Kubernetes handles restart.

---

# 🧠 3️⃣ Kubernetes Self-Healing Mechanism

If container crashes:

1. Kubelet detects container exit
2. Pod restarted automatically
3. If repeated failures → CrashLoopBackOff

Self-healing built-in.

---

# 🧠 4️⃣ What Is CrashLoopBackOff?

Meaning:

* Pod started
* Crashed
* Restarted
* Crashed again
* Exponential backoff applied

Check:

```bash
kubectl get pods
```

Status:

```
CrashLoopBackOff
```

---

# 🧠 5️⃣ Debugging CrashLoopBackOff

Check logs:

```bash
kubectl logs mypod
```

If restarted quickly:

```bash
kubectl logs mypod --previous
```

Common causes:

* Missing environment variable
* DB connection failure
* Wrong port
* OOMKilled
* Startup failure

---

# 🧠 6️⃣ Liveness Probe Interaction

If liveness probe fails repeatedly:

Kubernetes restarts pod.

Bad health check configuration → restart loop.

Be careful.

---

# 🧠 7️⃣ OOMKilled vs CrashLoopBackOff

OOMKilled:

Memory exceeded → kernel kill.

CrashLoopBackOff:

Application keeps failing to start.

Check:

```bash
kubectl describe pod mypod
```

Look under:

```
Last State
Reason
```

---

# 🧠 8️⃣ Backoff Mechanism

Kubernetes increases delay between restarts:

Restart after:

* 10s
* 20s
* 40s
* 80s

Prevents infinite restart storms.

---

# 🧠 9️⃣ Pod Restart Policy

In Kubernetes:

```yaml
restartPolicy: Always
```

Options:

* Always (default for Deployment)
* OnFailure
* Never (for Jobs)

Deployment pods → Always.

---

# 🧠 🔟 Production Resilience Strategy

Good design:

✔ Idempotent startup
✔ Retry logic for DB
✔ Retry external API
✔ Proper health checks
✔ Circuit breaker pattern

App must tolerate temporary failures.

---

# 🧠 1️⃣1️⃣ Startup Dependency Handling

Common mistake:

App fails immediately if DB unavailable.

Better:

Retry with backoff.

Example (pseudo-code):

```java
for (int i = 0; i < 5; i++) {
    try {
        connectToDatabase();
        break;
    } catch (Exception e) {
        Thread.sleep(2000);
    }
}
```

Better approach:

Use Spring retry mechanism.

---

# 🧠 1️⃣2️⃣ Readiness vs Crash

If DB temporarily down:

Better behavior:

* Readiness fails
* Traffic removed
* Container not restarted

Not everything requires restart.

Restart is expensive.

---

# 🧠 1️⃣3️⃣ Observability During Crash

Check:

```bash
kubectl describe pod mypod
kubectl logs mypod
kubectl get events
```

Look for:

* OOMKilled
* Probe failure
* Image pull error
* Mount failure

---

# 🧠 1️⃣4️⃣ Real Interview Scenarios

---

### Scenario 1

Pod stuck in CrashLoopBackOff.

What do you check?

Answer structure:

* Logs
* Resource limits
* Env variables
* Health probe config
* Image correctness

---

### Scenario 2

Pod running but not serving traffic.

Likely:

* Readiness failing
* Service selector mismatch
* Port mismatch

---

### Scenario 3

Pods restarting under load.

Possible:

* OOMKilled
* CPU throttling
* DB timeout
* External dependency crash

---

# 🧠 1️⃣5️⃣ Senior-Level Explanation Structure

If asked:

“How does Kubernetes ensure high availability?”

Answer:

* Restart failed containers automatically
* Reschedule pods if node fails
* Use readiness probes to avoid routing traffic to unhealthy pods
* Rolling updates prevent downtime
* Horizontal autoscaling handles load

Clear. Structured. Mature.

---

# 🎯 STEP 12 REVISION CHECKLIST

You must confidently explain:

✔ Docker restart policies
✔ Kubernetes self-healing
✔ CrashLoopBackOff
✔ OOMKilled vs crash
✔ Debugging pods
✔ Restart backoff logic
✔ Readiness vs restart distinction
✔ Resilience best practices

If you can explain these clearly →
Production resilience mastery achieved.

---

When ready, type:

**13**

Next file:

📁 `13-scaling-autoscaling-capacity-planning.md`
(Horizontal scaling + HPA + CPU-based scaling + memory-based scaling + scaling pitfalls)
