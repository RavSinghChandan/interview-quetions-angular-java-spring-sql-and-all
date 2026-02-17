# LINUX — LEVEL 3 (INTERMEDIATE)

**Stage Title: “The Day Production Breaks — And You Don’t Panic”**

*(Story continues: Now you’re not just operating Linux… you’re trusted with real servers.)*

---

## CHAPTER CONTEXT — WHAT CHANGES HERE

At Basic level → you learned commands.
At Intermediate level → you learn **judgment**.

Before this stage:

> You run commands.

After this stage:

> You read systems.

This level is about learning how Linux behaves when something goes wrong.

Because real engineers are measured not when things work…

…but when they break.

---

# 1. THE FIRST REAL PRODUCTION LESSON

When a system is slow, beginners restart server.

Engineers ask:

```
WHY is it slow?
```

Linux always shows the reason.

You just need to know where to look.

---

# 2. PROCESSES — FINDING THE TROUBLEMAKER

Check running processes:

```
ps aux
```

Find heavy CPU users:

```
ps aux --sort=-%cpu | head
```

Find memory eaters:

```
ps aux --sort=-%mem | head
```

See process tree:

```
pstree
```

Why this matters:

> Every performance problem is caused by a process.

---

# 3. PORT PROBLEMS — THE INVISIBLE BLOCKER

Common production error:

> Application won’t start.

Most common cause:

> Port already in use.

Check port:

```
ss -lntp | grep 8080
```

Or:

```
lsof -i :8080
```

Kill blocking process:

```
kill -9 PID
```

---

# 4. RESOURCE MONITORING — READING MACHINE HEALTH

Real-time stats:

```
top
```

Memory:

```
free -m
```

Live resource flow:

```
vmstat 1
```

Disk I/O:

```
iostat
```

Reality:

> Slow system is always a resource problem.

One of four things is overloaded:

* CPU
* memory
* disk
* network

---

# 5. LOGS — THE SYSTEM’S DIARY

Logs explain everything.

Last lines:

```
tail -n 100 app.log
```

Search errors:

```
grep ERROR app.log
```

Recent logs:

```
journalctl --since "1 hour ago"
```

Service logs:

```
journalctl -u nginx
```

Rule:

> Never debug without logs.

---

# 6. DISK PROBLEMS — SILENT SYSTEM KILLERS

Check disk space:

```
df -h
```

Find largest folders:

```
du -sh /*
```

Find huge files:

```
find / -size +500M
```

Why disk issues dangerous:

If disk full → system stops writing logs → crashes silently.

---

# 7. NETWORK DEBUGGING — WHEN REQUESTS FAIL

Test connection:

```
ping server-ip
```

Trace route:

```
traceroute google.com
```

Test API:

```
curl -v localhost:8080
```

Check connections:

```
ss -s
```

Reality:

> Many “application bugs” are actually network issues.

---

# 8. FILE DESCRIPTORS — THE HIDDEN LIMIT

Every open file or connection consumes a file descriptor.

Check limit:

```
ulimit -n
```

Check usage:

```
lsof | wc -l
```

If limit reached:

Server stops accepting requests.

---

# 9. BACKGROUND JOBS — INVISIBLE PROGRAMS

Run process in background:

```
nohup java -jar app.jar &
```

List background jobs:

```
jobs
```

Bring to foreground:

```
fg %1
```

---

# 10. AUTOMATION — CRON JOBS

Servers automate tasks.

Edit scheduler:

```
crontab -e
```

Run every 5 minutes:

```
*/5 * * * * script.sh
```

View cron logs:

```
grep CRON /var/log/syslog
```

---

# 11. SYSTEM STARTUP INVESTIGATION

When server reboots unexpectedly:

Check boot logs:

```
journalctl -b
```

Previous boot:

```
journalctl -b -1
```

This reveals crash causes.

---

# 12. ENVIRONMENT ISSUES

Sometimes app fails because environment variables missing.

Check environment:

```
printenv
```

Run program with env:

```
ENV=prod node app.js
```

---

# 13. REAL DEBUGGING ORDER (IMPORTANT)

Professional engineers always inspect system in this sequence:

```
CPU
Memory
Disk
Network
Processes
Logs
```

Never random guessing.

Always structured diagnosis.

---

# 14. LOAD AVERAGE — SYSTEM STRESS LEVEL

Check:

```
uptime
```

Example output:

```
1.50 1.20 0.90
```

Meaning:

System workload in last:

* 1 min
* 5 min
* 15 min

Rule:

If load > CPU cores → system overloaded.

---

# 15. REAL INCIDENT TOOLKIT

Memorize these commands forever:

```
top
df -h
free -m
ps aux
ss -lntp
journalctl -xe
tail -f log
```

With only these, you can solve most outages.

---

# INTERMEDIATE LEVEL COMPLETION CHECK

You can now:

✔ find which process causing issue
✔ detect memory exhaustion
✔ identify disk bottleneck
✔ diagnose network failures
✔ analyze logs effectively
✔ debug port conflicts
✔ understand system load

---

# FINAL LINE FOR THIS CHAPTER

At this point in your journey:

> Servers don’t intimidate you anymore.

You can walk into a failing system
and calmly understand what’s happening.

That is the moment someone becomes a real engineer.

---

END OF LEVEL 3 — INTERMEDIATE

---

When you’re ready to continue the story, say:

**PLUS**

Next chapter:

> “You stop debugging symptoms and start understanding how Linux works internally.”
