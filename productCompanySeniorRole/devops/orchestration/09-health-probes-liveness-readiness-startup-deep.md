Good. Now we go deeper than surface-level probes.

This is where many senior candidates fail.

---

# 📁 File: `09-health-probes-liveness-readiness-startup-deep.md`

# 🔥 STEP 9 — Liveness vs Readiness vs Startup Probe (Deep Internals)

(Probe Mechanics + Failure Modes + Real Production Debugging)

Interviewers love this topic.

They ask:

* What’s the exact difference between liveness and readiness?
* When does Kubernetes restart a pod?
* What is startupProbe?
* What happens if probe misconfigured?
* Can probes cause outages?

You must answer precisely.

---

# 🧠 1️⃣ Why Probes Exist

Containers can be:

* Running but hung
* Running but DB disconnected
* Running but not ready
* Running but overloaded

Without probes:

Traffic continues blindly.

Probes control:

✔ Restart behavior
✔ Traffic routing behavior

---

# 🧠 2️⃣ Liveness Probe (Restart Controller)

Purpose:

“Is the process alive?”

If liveness fails:

* Container restarted
* Pod remains same
* Kubelet restarts container

Example:

```yaml
livenessProbe:
  httpGet:
    path: /actuator/health
    port: 8080
  initialDelaySeconds: 30
  periodSeconds: 10
  failureThreshold: 3
```

If 3 failures → restart container.

---

# 🧠 3️⃣ Readiness Probe (Traffic Gatekeeper)

Purpose:

“Is the app ready to receive traffic?”

If readiness fails:

* Pod removed from Service endpoints
* No new traffic routed
* Container NOT restarted

Critical distinction.

---

# 🧠 4️⃣ Startup Probe (Slow Boot Protection)

Problem:

Spring Boot may take 40 seconds to start.

If liveness runs too early → container killed repeatedly.

Solution:

```yaml
startupProbe:
  httpGet:
    path: /actuator/health
    port: 8080
  failureThreshold: 30
  periodSeconds: 10
```

Startup probe disables liveness until startup complete.

Advanced but important.

---

# 🧠 5️⃣ Probe Types

Three types:

1. httpGet
2. tcpSocket
3. exec

Example exec:

```yaml
livenessProbe:
  exec:
    command:
      - cat
      - /tmp/healthy
```

Most common: HTTP probe.

---

# 🧠 6️⃣ Probe Timing Fields Explained

```yaml
initialDelaySeconds: 30
periodSeconds: 10
timeoutSeconds: 2
failureThreshold: 3
successThreshold: 1
```

Meaning:

* Wait 30s before first check
* Check every 10s
* Timeout if >2s response
* After 3 failures → action taken

Understanding these fields = maturity.

---

# 🧠 7️⃣ Readiness During Rolling Update

During deployment:

New pod:

* Created
* Not Ready
* Not added to endpoints

Once readiness passes:

* Added to service
* Starts receiving traffic

Old pod:

* Marked terminating
* Readiness fails
* Removed from endpoints

Traffic shift safe.

---

# 🧠 8️⃣ Misconfiguration Scenario #1 — Aggressive Liveness

If:

```yaml
periodSeconds: 2
timeoutSeconds: 1
```

Under slight CPU spike:

Health endpoint slow → liveness fails → restart → instability.

Never make liveness too aggressive.

---

# 🧠 9️⃣ Misconfiguration Scenario #2 — Health Endpoint Always Returns 200

If health endpoint does not check:

* DB connectivity
* Cache connectivity

Then readiness may return UP even if DB down.

Traffic routed → errors.

Health logic must be meaningful.

---

# 🧠 🔟 Misconfiguration Scenario #3 — Long GC Pause

During heavy GC:

App stops responding for 5 seconds.

If liveness timeout = 2 seconds → restart triggered.

Wrong configuration can cause cascading failures.

---

# 🧠 1️⃣1️⃣ Debugging Probe Failures

Check:

```bash
kubectl describe pod mypod
```

Look under:

Events:

```
Liveness probe failed
Readiness probe failed
```

Check logs:

```bash
kubectl logs mypod
```

---

# 🧠 1️⃣2️⃣ When NOT to Use Liveness

If app depends on external DB:

And DB temporarily down:

Liveness failing causes restart storm.

Better approach:

* Readiness fails
* Liveness continues
* Wait for DB recovery

Restart is expensive.

---

# 🧠 1️⃣3️⃣ Startup vs Readiness Interaction

StartupProbe active →
Liveness ignored until startup success.

After startupProbe success →
Liveness and readiness take over.

Correct for slow apps.

---

# 🧠 1️⃣4️⃣ Real Interview Simulation

Question:

“What happens if readiness fails?”

Answer:

Pod removed from Service endpoints, no new traffic routed, but container continues running.

Question:

“What happens if liveness fails?”

Answer:

Container restarted by kubelet.

Clear difference is critical.

---

# 🧠 1️⃣5️⃣ Senior-Level Mental Model

Liveness = Restart logic
Readiness = Traffic control logic
Startup = Boot protection logic

Each serves different orchestration purpose.

Misuse can cause outages.

---

# 🎯 STEP 9 REVISION CHECKLIST

You must confidently explain:

✔ Liveness vs readiness difference
✔ Startup probe purpose
✔ Probe timing parameters
✔ Failure threshold behavior
✔ Probe misconfiguration dangers
✔ Restart storm scenario
✔ Health endpoint design
✔ Debugging probe failures

If you can explain all clearly →
Probe-level orchestration mastery achieved.

---

When ready, type:

**10**

Next file:

📁 `10-configmap-secret-advanced-usage.md`
(ConfigMap + Secret deep usage + volume mount vs env injection + security risks + rotation strategies)
