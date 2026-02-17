# DOCKER — LEVEL 5 (ADVANCED)

**Stage Title: “You Become the Engineer Called During Production Incidents”**

*(Story continues — now you enter the level where containers break… and you are the one who understands why.)*

---

# 🎯 OBJECTIVE OF THIS LEVEL

After this level you will be able to:

* diagnose production container failures
* debug performance issues
* investigate crashes
* analyze resource bottlenecks
* fix real deployment problems
* troubleshoot networking failures

This level = **real-world DevOps engineer skill**

---

# 1️⃣ REALITY — PRODUCTION CONTAINERS FAIL DIFFERENTLY

Containers rarely fail loudly.

They fail silently:

Symptoms you’ll see:

* container keeps restarting
* app slow
* memory spikes
* API timeout
* random crashes
* works locally but fails in prod

Advanced engineers don’t restart containers blindly.

They investigate.

---

# 2️⃣ FIRST RULE OF PRODUCTION DEBUGGING

When container fails:

Never guess.

Always inspect.

Checklist:

```
docker ps
docker logs
docker inspect
docker stats
```

This is your core debugging toolkit.

---

# 3️⃣ CONTAINER NOT STARTING

Check stopped containers:

```
docker ps -a
```

Inspect error:

```
docker logs containerID
```

Common cause:

```
wrong command
missing dependency
wrong path
permission error
```

---

# 4️⃣ CONTAINER EXITING IMMEDIATELY

Check exit code:

```
docker inspect containerID | grep ExitCode
```

Common reasons:

| Exit Code | Meaning          |
| --------- | ---------------- |
| 0         | process finished |
| 1         | app error        |
| 137       | killed (memory)  |
| 143       | stopped manually |

---

# 5️⃣ MEMORY CRASH (OOM KILL)

If container exits with code 137 → out of memory.

Check memory:

```
docker stats
```

Fix:

Increase memory:

```
docker run -m 1g app
```

Or optimize app memory usage.

---

# 6️⃣ CPU BOTTLENECK DEBUGGING

Check CPU usage:

```
docker stats
```

Limit CPU:

```
docker run --cpus="1.5" app
```

Investigate inside container:

```
docker exec -it containerID top
```

---

# 7️⃣ DISK SPACE ISSUES

Check disk usage:

```
docker system df
```

Clean unused resources:

```
docker system prune
```

Remove unused images:

```
docker image prune
```

---

# 8️⃣ NETWORK CONNECTION FAILURES

Check container IP:

```
docker inspect containerID | grep IPAddress
```

Test connectivity:

```
docker exec -it containerID ping db
```

Inspect network:

```
docker network inspect networkName
```

---

# 9️⃣ PORT NOT ACCESSIBLE

Check ports:

```
docker ps
```

Verify mapping:

```
docker port containerID
```

Correct run command:

```
docker run -p 8080:80 nginx
```

---

# 🔟 ENVIRONMENT VARIABLE BUGS

Check env variables:

```
docker exec containerID env
```

Run with env:

```
docker run -e DB_HOST=db app
```

Many production failures are just wrong env variables.

---

# 11️⃣ FILE PERMISSION ISSUES

Check user inside container:

```
docker exec containerID whoami
```

Fix permission:

```
docker run -u 1000 app
```

---

# 12️⃣ HEALTH CHECK FAILURES

Check container health:

```
docker ps
```

If unhealthy:

Inspect health logs:

```
docker inspect containerID
```

Health checks defined in Dockerfile:

```
HEALTHCHECK CMD curl -f http://localhost || exit 1
```

---

# 13️⃣ IMAGE SIZE PROBLEMS

Large images cause:

* slow deploy
* slow pull
* high cost

Check size:

```
docker images
```

Optimize:

Use slim base:

```
FROM node:18-alpine
```

---

# 14️⃣ SLOW BUILD TIMES

Check cache usage:

```
docker build .
```

If rebuilding everything → layer order wrong.

Fix Dockerfile order for caching.

---

# 15️⃣ LOG OVERFLOW

Containers can fill disk with logs.

Check log size:

```
docker inspect containerID | grep LogPath
```

Limit logs:

```
--log-opt max-size=10m
```

---

# 16️⃣ ZOMBIE CONTAINERS

Check exited containers:

```
docker ps -a
```

Clean:

```
docker container prune
```

---

# 17️⃣ DEBUGGING RUNNING CONTAINER LIKE SERVER

Enter container:

```
docker exec -it containerID bash
```

Check processes:

```
ps aux
```

Check ports:

```
ss -tulnp
```

Check files:

```
ls
```

---

# 18️⃣ REAL INCIDENT FLOW USED BY SENIOR ENGINEERS

When system fails:

Step-by-step:

```
Check container running?
Check logs?
Check resources?
Check network?
Check env?
Check filesystem?
```

Always this order.

Never random guessing.

---

# 19️⃣ MONITORING LIVE PERFORMANCE

Real-time monitoring:

```
docker stats
```

Advanced stats:

```
docker inspect containerID
```

---

# 20️⃣ PROFESSIONAL DEBUGGING MINDSET

Beginners restart containers.

Advanced engineers ask:

```
What killed it?
What resource was exhausted?
What dependency failed?
What changed?
```

Because:

> Containers don’t fail randomly. They fail for reasons.

---

# 🏁 ADVANCED COMPLETION CHECK

You can now:

✔ diagnose container crashes
✔ analyze resource bottlenecks
✔ debug networking issues
✔ fix environment problems
✔ detect memory failures
✔ inspect running containers
✔ investigate performance problems

---

# FINAL LINE

At this point:

> You don’t fear container failures.

Because you know how to investigate them.

That’s the difference between:

Developer → DevOps engineer.

---

END OF DOCKER LEVEL 5 — ADVANCED
