Good. Stay disciplined.
You now get **STEP 8 only**.

---

# 📁 File: `08-graceful-shutdown-signal-handling.md`

# 🔥 STEP 8 — Graceful Shutdown + Signal Handling + Request Draining

(Deep Runtime Control — Senior Backend Level)

This file is critical.

Interviewers at Uber/FAANG love asking:

* What happens when a pod is terminated?
* How does SIGTERM work?
* How do you prevent request loss during deployment?
* What is PID 1 inside container?

This is runtime maturity.

---

# 🧠 1️⃣ What Happens When You Stop a Container

When you run:

```bash
docker stop <container_id>
```

Docker sends:

```
SIGTERM
```

Waits 10 seconds (default).

If process does not exit → sends:

```
SIGKILL
```

SIGKILL cannot be handled.

Important:
You only get cleanup opportunity during SIGTERM.

---

# 🧠 2️⃣ PID 1 Inside Container

Inside container:

Your application runs as **PID 1**.

Check:

```bash
docker exec -it myapp ps aux
```

You will see:

```
PID 1 java -jar app.jar
```

PID 1 has special behavior:

* Must properly handle signals
* If not → zombie processes
* If ignores SIGTERM → force kill

Senior awareness required.

---

# 🧠 3️⃣ Kubernetes Pod Termination Flow

When deployment updates:

1. Pod marked “Terminating”
2. Removed from service endpoints
3. Readiness probe fails
4. SIGTERM sent to container
5. Wait for `terminationGracePeriodSeconds`
6. If still running → SIGKILL

Example:

```yaml
terminationGracePeriodSeconds: 30
```

App must finish shutdown within this time.

---

# 🧠 4️⃣ Request Draining Concept

Request draining means:

* Stop receiving new traffic
* Finish ongoing requests
* Exit cleanly

Mechanism:

✔ Readiness probe fails
✔ Load balancer stops routing
✔ In-flight requests complete
✔ App exits

Without readiness → new traffic may still hit terminating pod.

---

# 🧠 5️⃣ Graceful Shutdown in Spring Boot

Enable in `application.yml`:

```yaml
server:
  shutdown: graceful

spring:
  lifecycle:
    timeout-per-shutdown-phase: 30s
```

Behavior:

* Stops accepting new connections
* Waits for current requests
* Closes context
* Releases resources

If not configured → abrupt termination.

---

# 🧠 6️⃣ What If You Ignore SIGTERM?

If app ignores SIGTERM:

After timeout → SIGKILL.

Effects:

* Dropped requests
* Incomplete DB transactions
* Partial writes
* Data inconsistency

Production instability.

---

# 🧠 7️⃣ Handling Long-Running Requests

If API takes 2 minutes to process:

Options:

1. Increase `terminationGracePeriodSeconds`
2. Move processing to async queue
3. Return 202 Accepted and process in background

Bad practice:
Long blocking HTTP requests during deployment.

---

# 🧠 8️⃣ Shutdown Hook in Plain Java

If not using Spring:

```java
Runtime.getRuntime().addShutdownHook(new Thread(() -> {
    System.out.println("Shutdown initiated");
    // cleanup logic here
}));
```

Triggered on SIGTERM.

This is mandatory in custom frameworks.

---

# 🧠 9️⃣ DB and Resource Cleanup

On shutdown, must close:

* DataSource
* Thread pools
* HTTP clients
* Kafka consumers
* Redis connections

If not:

* Memory leaks
* Resource starvation
* Dirty shutdown

Spring handles most automatically.

---

# 🧠 🔟 Real Deployment Timeline Example

Imagine 3 replicas.

Deployment starts:

* New pod created
* New pod readiness false
* After init → readiness true
* Traffic shifts
* Old pod receives SIGTERM
* Old pod readiness false
* In-flight requests finish
* Old pod exits

Zero downtime.

---

# 🧠 1️⃣1️⃣ Debugging Shutdown Problems

If users report:

“Errors during deployment”

Check:

* Is graceful shutdown enabled?
* terminationGracePeriodSeconds too small?
* Readiness misconfigured?
* PreStop hook missing?

---

# 🧠 1️⃣2️⃣ PreStop Hook (Advanced Kubernetes Feature)

```yaml
lifecycle:
  preStop:
    exec:
      command: ["sleep", "10"]
```

Allows load balancer time to remove pod before shutdown.

Advanced reliability pattern.

---

# 🧠 1️⃣3️⃣ How to Test Locally

Run container:

```bash
docker run -p 8080:8080 myapp
```

Stop:

```bash
docker stop <container_id>
```

Watch logs:

You should see graceful shutdown messages.

If instant kill → misconfigured.

---

# 🧠 1️⃣4️⃣ Interview Scenario Simulation

---

### Scenario 1

2% requests fail during deployment.

Likely:

* No graceful shutdown
* terminationGracePeriod too small
* No readiness probe

---

### Scenario 2

Pod stuck in “Terminating” state.

Possible:

* Long-running request
* Deadlock
* Shutdown hook blocking

---

# 🧠 1️⃣5️⃣ Senior-Level Answer Structure

If asked:

“What happens when a pod is terminated?”

Answer:

* Kubernetes removes pod from service endpoints
* Sends SIGTERM
* App performs graceful shutdown
* In-flight requests complete
* After grace period, SIGKILL if needed

Structured. Calm. Clear.

---

# 🎯 STEP 8 REVISION CHECKLIST

You must confidently explain:

✔ SIGTERM vs SIGKILL
✔ PID 1 behavior
✔ Pod termination lifecycle
✔ Request draining
✔ Graceful shutdown config
✔ terminationGracePeriodSeconds
✔ Shutdown hooks
✔ PreStop hook
✔ Deployment failure scenarios

If you can explain these clearly →
Runtime shutdown mastery achieved.

---

When ready, type:

**9**

Next file:

📁 `09-resource-limits-jvm-tuning-performance.md`
(Memory limits + OOMKilled + CPU throttling + JVM tuning + performance debugging in containers)
