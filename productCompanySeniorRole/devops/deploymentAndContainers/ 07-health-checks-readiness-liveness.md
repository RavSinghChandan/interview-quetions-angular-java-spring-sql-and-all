Good. Continuing properly.

---

# 📁 File: `07-health-checks-readiness-liveness.md`

# 🔥 STEP 7 — Health Checks + Readiness vs Liveness + Zero-Downtime Mechanics

(Senior Backend Production Control Layer)

This is where many candidates fail.

Interviewers ask:

* What is readiness probe?
* What happens if readiness fails?
* Difference between readiness and liveness?
* How does zero downtime actually work?
* Why do we need graceful shutdown?

You must answer confidently and structurally.

---

# 🧠 1️⃣ Why Health Checks Exist

Without health checks:

* Load balancer sends traffic blindly
* App may not be ready
* DB may not be connected
* External dependency may be down

Result:
Users hit broken service.

Health checks = traffic control gate.

---

# 🧠 2️⃣ Two Core Probes

## 🔹 Liveness Probe

“Is the process alive?”

If liveness fails → Kubernetes restarts container.

---

## 🔹 Readiness Probe

“Is the application ready to receive traffic?”

If readiness fails →
Pod removed from load balancer
BUT container is NOT restarted.

This difference is critical.

---

# 🧠 3️⃣ Spring Boot Health Endpoint Setup

Add dependency:

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

application.yml:

```yaml
management:
  endpoints:
    web:
      exposure:
        include: health
```

Now endpoint:

```
GET /actuator/health
```

Example response:

```json
{
  "status": "UP"
}
```

This becomes readiness/liveness endpoint.

---

# 🧠 4️⃣ Docker Healthcheck Example

In Dockerfile:

```dockerfile
HEALTHCHECK --interval=30s --timeout=5s \
  CMD curl -f http://localhost:8080/actuator/health || exit 1
```

Marks container unhealthy if endpoint fails.

Note:
Kubernetes typically uses its own probes instead.

---

# 🧠 5️⃣ Kubernetes Readiness Probe Example

```yaml
readinessProbe:
  httpGet:
    path: /actuator/health
    port: 8080
  initialDelaySeconds: 10
  periodSeconds: 5
  failureThreshold: 3
```

Meaning:

* Check every 5 seconds
* After 3 failures → remove from service endpoints

Traffic stops immediately.

---

# 🧠 6️⃣ Kubernetes Liveness Probe Example

```yaml
livenessProbe:
  httpGet:
    path: /actuator/health
    port: 8080
  initialDelaySeconds: 30
  periodSeconds: 10
```

If liveness fails repeatedly →
Pod restarted.

---

# 🧠 7️⃣ Zero Downtime Deployment Flow (Step-by-Step)

Rolling deployment example:

1. V1 running (3 pods)
2. V2 pod created
3. V2 starts
4. Readiness = false
5. No traffic yet
6. DB connections initialized
7. Readiness = true
8. Traffic begins to V2
9. V1 pod receives SIGTERM
10. V1 readiness fails
11. No new traffic to V1
12. In-flight requests finish
13. V1 shuts down

At no point are all pods down.

This is zero downtime.

---

# 🧠 8️⃣ What Happens If Readiness Not Configured?

During deployment:

* New pod may receive traffic before fully initialized
* Old pod may still receive traffic after shutdown started
* Requests fail

Readiness probe is mandatory for production.

---

# 🧠 9️⃣ Graceful Shutdown in Spring Boot

Enable:

```yaml
server:
  shutdown: graceful

spring:
  lifecycle:
    timeout-per-shutdown-phase: 30s
```

When SIGTERM received:

* Stop accepting new connections
* Finish active requests
* Close resources
* Exit cleanly

Without this → dropped requests.

---

# 🧠 🔟 Startup Probe (Advanced Kubernetes Concept)

If app takes long to boot:

```yaml
startupProbe:
  httpGet:
    path: /actuator/health
    port: 8080
  failureThreshold: 30
  periodSeconds: 10
```

Prevents liveness from killing slow-starting app.

Senior awareness.

---

# 🧠 1️⃣1️⃣ What Should Health Endpoint Check?

Good health endpoint:

✔ DB connectivity
✔ Cache connectivity
✔ External API reachability (optional)
✔ Disk space

Bad health endpoint:

Always returns 200.

---

# 🧠 1️⃣2️⃣ Failure Scenarios

---

### Scenario 1 — Readiness fails

Effect:

* Pod removed from service
* No traffic
* Container continues running

---

### Scenario 2 — Liveness fails

Effect:

* Pod restarted
* New instance created

---

### Scenario 3 — DB temporarily down

Correct behavior:

* Readiness returns DOWN
* Traffic stopped
* Container not restarted unnecessarily

---

# 🧠 1️⃣3️⃣ Deployment Debugging Checklist

If users report errors during deployment:

Check:

* Was readiness configured?
* Is graceful shutdown enabled?
* terminationGracePeriodSeconds correct?
* Health endpoint accurate?

---

# 🧠 1️⃣4️⃣ terminationGracePeriodSeconds

Example:

```yaml
terminationGracePeriodSeconds: 30
```

Kubernetes waits 30 seconds after SIGTERM before SIGKILL.

Must match your app shutdown time.

---

# 🧠 1️⃣5️⃣ Senior Interview Answer Structure

If asked:

“What happens during rolling deployment?”

Answer structure:

* New pod created
* Readiness blocks traffic
* Traffic shifts gradually
* Old pod receives SIGTERM
* Graceful shutdown completes
* No request loss

Clear. Structured. Confident.

---

# 🎯 STEP 7 REVISION CHECKLIST

You must confidently explain:

✔ Readiness vs Liveness difference
✔ How zero downtime works
✔ Rolling deployment flow
✔ Graceful shutdown importance
✔ Startup probe usage
✔ terminationGracePeriodSeconds
✔ Health endpoint best practices
✔ Failure scenarios

If you can explain all calmly →
Deployment control mastery achieved.

---

When ready, type:

**8**

Next file:

📁 `08-graceful-shutdown-signal-handling.md`
(Deep SIGTERM mechanics + PID 1 + request draining + shutdown hooks + production failure analysis)
