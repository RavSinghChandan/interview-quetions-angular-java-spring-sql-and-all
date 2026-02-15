Excellent.
Now we move into **runtime behavior and production realism**.

This is where interviews become scenario-based:

* “Container is crashing, what happens?”
* “Memory leak in container, what do you do?”
* “How does resource limit actually work?”
* “How do you persist data?”

This step makes you production-ready.

---

# 🔥 STEP 4 — CONTAINER RUNTIME BEHAVIOR

(Resource Control, Lifecycle, Volumes, Failure Handling, Advanced Production Concerns)

This is 100% senior-level awareness.

---

# 🧠 1️⃣ What Actually Happens When Container Starts

When you run:

```bash
docker run myapp
```

Internal flow:

1. Docker daemon checks image locally
2. Pulls image from registry if missing
3. Creates writable container layer
4. Creates isolated namespaces:

    * PID namespace
    * Network namespace
    * Mount namespace
5. Applies cgroup limits
6. Starts main process (PID 1)

Critical:

The container lives as long as the main process runs.

If PID 1 exits → container stops.

---

# 🧠 2️⃣ PID 1 Problem (Advanced Concept)

Inside container:

Your application runs as PID 1.

Why this matters?

PID 1:

* Handles signals differently
* Must handle SIGTERM properly

If your app ignores SIGTERM:
Container may not shut down gracefully.

Senior awareness:
Always ensure graceful shutdown logic.

---

# 🧠 3️⃣ Container Lifecycle

States:

* Created
* Running
* Paused
* Stopped
* Removed

Restart policies:

```bash
docker run --restart=always app
```

Options:

* no
* on-failure
* always
* unless-stopped

In production:
Kubernetes handles restart.

---

# 🧠 4️⃣ Resource Control via cgroups

Limit memory:

```bash
docker run --memory="512m" app
```

Limit CPU:

```bash
docker run --cpus="1.5" app
```

What happens if memory exceeds?

Container gets OOMKilled.

Important interview answer:

Container memory limit is hard limit.

If JVM tries to exceed → killed.

---

# 🧠 5️⃣ JVM Inside Container (Critical for Backend)

Older JVMs didn’t detect container limits.

Modern JVM uses:

```
-XX:+UseContainerSupport
```

Without proper memory tuning:
App may crash due to OOM.

Senior awareness:
Always align JVM heap size with container memory.

Example:

If container memory = 512MB
Do not let JVM allocate 1GB heap.

---

# 🧠 6️⃣ Writable Layer Behavior

Image layers are read-only.

Container adds writable layer.

All file changes go there.

If container deleted → writable layer gone.

Therefore:

Never store:

* Uploaded files
* User data
* Database data

Inside container filesystem.

---

# 🧠 7️⃣ Volumes — Data Persistence

To persist data:

```bash
docker run -v myvolume:/data app
```

Volume lives outside container lifecycle.

Types:

1. Named volume
2. Bind mount (host path)

Example:

```bash
docker run -v /host/path:/container/path app
```

Senior-level thought:

In cloud, persistent storage handled by:

* EBS
* EFS
* PersistentVolume (Kubernetes)

---

# 🧠 8️⃣ Logs in Containers

Never:

Log to local file inside container.

Because:

* Container ephemeral
* Logs lost on restart

Correct pattern:

Log to STDOUT.

Container platform captures logs.

Senior phrase:

“Containers follow 12-factor app logging principle.”

---

# 🧠 9️⃣ Networking Isolation

Each container:

* Gets its own network namespace
* Own IP inside bridge network

Port mapping:

```bash
docker run -p 8080:8080 app
```

Host:8080 → Container:8080

In production:
Load balancer routes traffic.

---

# 🧠 10️⃣ Signal Handling (Very Important)

When container stops:

Docker sends SIGTERM first.

Your app must:

* Stop accepting new requests
* Finish ongoing requests
* Close DB connections
* Exit gracefully

If app ignores SIGTERM:
Force killed with SIGKILL.

Zero-downtime depends on graceful shutdown.

---

# 🧠 11️⃣ Crash Scenarios

If container crashes due to:

* Exception
* OOM
* Process exit

What happens?

Plain Docker:
Depends on restart policy.

Kubernetes:
Pod recreated automatically.

Design assumption:

Containers can die anytime.

---

# 🧠 12️⃣ Health Check Importance

Without healthcheck:

Orchestrator doesn’t know if app is broken.

With healthcheck:

Orchestrator can restart unhealthy container.

Example:

```dockerfile
HEALTHCHECK CMD curl -f http://localhost:8080/actuator/health || exit 1
```

In Kubernetes:
Readiness + liveness probes.

---

# 🧠 13️⃣ Ephemeral vs Persistent Thinking

Stateless services:

* Easy to scale
* Easy to replace

Stateful services:

* Require persistent volumes
* Harder to scale

Senior maturity:

Application containers → stateless
Databases → stateful

---

# 🧠 14️⃣ Real Interview Scenario Questions

### Q1: Container memory keeps increasing. What do you check?

Answer:

1. JVM heap configuration
2. Memory leak
3. GC logs
4. Container memory limit
5. OOMKilled events

Structured debugging answer.

---

### Q2: Container keeps restarting. What do you check?

1. Application logs
2. Crash reason
3. Healthcheck failures
4. Resource limits
5. Dependency failures

---

### Q3: Why is container CPU high?

1. Traffic spike
2. Infinite loop
3. Missing scaling
4. GC pressure

---

# 🧠 15️⃣ Advanced Production Concern — Image Immutability

Images should be immutable.

Never:

* Modify container manually in production.

If change needed:
Build new image.

Senior principle:
Immutable infrastructure.

---

# 🎯 STEP 4 MASTER CHECKLIST

You must confidently explain:

✔ What happens during docker run
✔ PID 1 behavior
✔ Container lifecycle
✔ Memory & CPU limits
✔ JVM tuning in container
✔ Writable layer concept
✔ Volumes
✔ Logging best practice
✔ Signal handling
✔ Crash scenarios
✔ Immutable infrastructure

If you can explain these clearly → you are production-aware.

---

Pause.
Mentally simulate explaining this to interviewer.

When ready, type:

**5**

Next:

🔥 STEP 5 — Deployment Strategies + Zero Downtime + Production Readiness + Real Interview Scenarios
