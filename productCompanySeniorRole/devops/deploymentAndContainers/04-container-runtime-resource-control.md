Good. Continuing properly.

---

# 📁 File: `04-container-runtime-resource-control.md`

# 🔥 STEP 4 — Container Runtime + Resource Control + OOM + Debugging

(Deep Production Runtime Awareness)

This file separates:

“I know Docker commands”
from
“I understand production runtime behavior.”

Uber/FAANG love asking:

* What happens when container crashes?
* What is OOMKilled?
* How does CPU throttling work?
* What is PID 1?
* Why does JVM crash in container?

Let’s go deep.

---

# 🧠 1️⃣ Container Lifecycle

States:

* Created
* Running
* Paused
* Stopped
* Removed

Check running containers:

```bash
docker ps
```

Check all:

```bash
docker ps -a
```

Stop container:

```bash
docker stop <container_id>
```

Remove container:

```bash
docker rm <container_id>
```

---

# 🧠 2️⃣ What Happens During `docker run`

Internal flow:

1. CLI sends request to daemon
2. Daemon checks image
3. Pulls if not found
4. Creates writable layer
5. Creates namespaces
6. Applies cgroups
7. Starts process (PID 1)

Container exists as long as PID 1 runs.

If PID 1 exits → container stops.

---

# 🧠 3️⃣ PID 1 Behavior (Important)

Inside container:

Your app is PID 1.

PID 1:

* Must handle signals properly
* If ignores SIGTERM → forced kill

Check PID inside container:

```bash
docker exec -it myapp ps aux
```

You’ll see your Java app as PID 1.

---

# 🧠 4️⃣ Signal Handling (Basic Understanding)

When stopping container:

Docker sends:

```
SIGTERM
```

If not exited after timeout:

```
SIGKILL
```

SIGKILL = cannot be caught.

Therefore:
App must shutdown gracefully.

---

# 🧠 5️⃣ cgroups — Resource Limits

Limit memory:

```bash
docker run --memory="512m" myapp
```

Limit CPU:

```bash
docker run --cpus="1.5" myapp
```

Check resource limits:

```bash
docker inspect myapp
```

Look for:

```
Memory
NanoCpus
```

---

# 🧠 6️⃣ What Is OOMKilled?

If container exceeds memory limit:

Linux kills process.

Container status:

```
OOMKilled
```

Check:

```bash
docker inspect myapp
```

Look for:

```
"OOMKilled": true
```

Very common interview topic.

---

# 🧠 7️⃣ JVM Inside Container (Critical for Backend)

If container memory = 512MB
And JVM heap = default 1GB
→ OOMKilled.

Better:

```dockerfile
ENTRYPOINT ["java","-Xms256m","-Xmx384m","-jar","app.jar"]
```

Heap must be < container memory.

---

# 🧠 8️⃣ CPU Throttling

If you set:

```bash
docker run --cpus="0.5" myapp
```

App only gets 50% of one CPU.

If traffic increases:

* Latency increases
* GC pressure increases

CPU throttling is real.

Senior must understand.

---

# 🧠 9️⃣ Debugging High Memory Usage

Steps:

1. Check container memory limit
2. Check JVM heap size
3. Check GC logs
4. Check memory leak
5. Check object retention

Inside container:

```bash
docker stats
```

Shows real-time usage.

---

# 🧠 🔟 Debugging High CPU Usage

Check:

```bash
docker stats
```

Inside container:

```bash
top
```

Possible reasons:

* Infinite loop
* High GC
* Traffic spike
* Blocking threads

---

# 🧠 1️⃣1️⃣ Writable Layer Behavior

Container FS:

Image layers (read-only)
+
Writable layer

If you modify:

```bash
docker exec -it myapp touch file.txt
```

File exists only until container removed.

Delete container → file gone.

Never store persistent data inside container.

---

# 🧠 1️⃣2️⃣ Volume for Persistence

Create volume:

```bash
docker volume create myvolume
```

Run container:

```bash
docker run -v myvolume:/data myapp
```

Data survives container removal.

---

# 🧠 1️⃣3️⃣ Logs in Container

Correct logging pattern:

Write logs to stdout.

Check logs:

```bash
docker logs myapp
```

Never rely on local file logs.

Production uses:

* ELK
* CloudWatch
* Loki

---

# 🧠 1️⃣4️⃣ Real Production Failure Scenarios

---

### Scenario 1 — Container keeps restarting

Check:

* OOMKilled?
* Health check failing?
* Missing env variable?
* Crash loop?

---

### Scenario 2 — High latency after deployment

Check:

* CPU throttling
* GC
* DB connection pool
* Thread starvation

---

### Scenario 3 — Works locally but fails in container

Likely:

* Wrong port
* Missing env variable
* File path mismatch
* Memory limit issue

---

# 🧠 1️⃣5️⃣ Immutable Infrastructure Principle

Never:

```bash
docker exec -it myapp bash
# modify config manually
```

If change required:

1. Modify code
2. Build new image
3. Redeploy

Containers are immutable.

---

# 🎯 STEP 4 REVISION CHECKLIST

You must confidently explain:

✔ Container lifecycle
✔ docker run internals
✔ PID 1 behavior
✔ SIGTERM vs SIGKILL
✔ Memory limits
✔ OOMKilled
✔ CPU throttling
✔ docker stats usage
✔ Volume persistence
✔ Debugging strategy

If you can explain all calmly →
Runtime understanding strong.

---

When ready, type:

**5**

Next file:

📁 `05-image-optimization-build-performance.md`
(Advanced caching + layer optimization + BuildKit + CI integration + scaling impact)
