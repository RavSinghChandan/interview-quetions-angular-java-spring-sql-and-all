# LINUX — LEVEL 5 (ADVANCED)

**Stage Title: “The Day You Become the Engineer Everyone Calls During Outages”**

*(Story continues — now you enter the level where systems fail… and you are the one who understands why.)*

---

## CHAPTER CONTEXT — WHAT CHANGES HERE

Until now:

* You learned commands
* You understood system behavior
* You understood internals

Now comes the real test:

> Can you diagnose problems nobody else understands?

This level is about **deep investigation**.

Not surface debugging.
Not restarting services.

Real root-cause analysis.

---

# 1. THE REAL WORLD TRUTH ABOUT FAILURES

Production systems rarely fail loudly.

They fail silently.

Symptoms might look like:

* API slow
* random timeouts
* CPU spikes sometimes
* memory slowly increasing
* disk randomly stuck

Beginner sees symptoms.
Advanced engineer finds cause.

---

# 2. CPU INVESTIGATION — NOT JUST USAGE

Anyone can check CPU %.

Advanced engineers ask:

> Which core? Which thread? Which function?

Check per-core usage:

```
mpstat -P ALL 1
```

Find heavy process:

```
ps -eo pid,cmd,%cpu --sort=-%cpu | head
```

Track CPU over time:

```
top
```

If CPU high but system slow → problem not CPU.

---

# 3. MEMORY LEAK DETECTION

Slow performance over time often means:

> memory leak

Check memory trend:

```
watch -n1 free -m
```

Find memory eaters:

```
ps aux --sort=-%mem | head
```

Check swap usage:

```
free -m
```

High swap usage = memory exhaustion.

---

# 4. DISK BOTTLENECK FORENSICS

System slow but CPU low?

Likely disk issue.

Check disk stats:

```
iostat -x 1
```

Important metrics:

```
await → request wait time
svctm → service time
%util → disk busy
```

Find disk-heavy process:

```
iotop
```

---

# 5. NETWORK BOTTLENECK FORENSICS

Check active connections:

```
ss -s
```

Count connections:

```
ss -ant | wc -l
```

Check traffic usage:

```
iftop
```

Packet stats:

```
netstat -s
```

Too many connections → connection flood or DDoS.

---

# 6. THREAD EXPLOSION — HIDDEN PERFORMANCE KILLER

Apps sometimes create too many threads.

Check thread count:

```
ps -eLf | grep java | wc -l
```

High thread count causes:

* context switching
* CPU overhead
* latency

---

# 7. CONTEXT SWITCH ANALYSIS

Check switching:

```
vmstat 1
```

Column:

```
cs
```

High value means CPU spending time switching tasks instead of executing them.

---

# 8. TRACING PROGRAM BEHAVIOR LIVE

When logs aren’t enough, trace system calls.

Trace process:

```
strace -p PID
```

Trace file activity:

```
strace -e trace=file -p PID
```

Trace network calls:

```
strace -e trace=network -p PID
```

This shows exactly what program is doing.

---

# 9. SHARED LIBRARY FAILURES

Programs depend on system libraries.

Check dependencies:

```
ldd binary_name
```

If library missing → app fails to start.

---

# 10. FILE DESCRIPTOR EXHAUSTION

Symptom:

> Server suddenly stops accepting requests.

Check limit:

```
ulimit -n
```

Check usage:

```
lsof | wc -l
```

Increase limit:

```
ulimit -n 100000
```

---

# 11. LOCKED FILE INVESTIGATION

Sometimes processes hang because files are locked.

Check:

```
lsof
```

Find which process uses file:

```
fuser filename
```

---

# 12. SYSTEM CALL PROFILING

To measure performance:

```
strace -c program
```

Shows which system calls consume most time.

Used for performance tuning.

---

# 13. SIGNAL FORENSICS

List signals:

```
kill -l
```

Graceful shutdown:

```
kill -15 PID
```

Force kill:

```
kill -9 PID
```

Reload config:

```
kill -1 PID
```

Understanding signals prevents data corruption.

---

# 14. ADVANCED LOG ANALYSIS

Time filtered logs:

```
journalctl --since "2 hours ago"
```

Follow logs live:

```
journalctl -f
```

Logs by PID:

```
journalctl _PID=1234
```

---

# 15. NETWORK PACKET CAPTURE

To inspect actual traffic:

```
tcpdump -i eth0 port 80
```

Used when requests fail mysteriously.

---

# 16. FILESYSTEM FORENSICS

Check inode usage:

```
df -i
```

If inode full → cannot create files.

Find deleted files still open:

```
lsof | grep deleted
```

These still consume disk space.

---

# 17. KERNEL CRASH INVESTIGATION

Kernel logs:

```
dmesg
```

OOM killer events:

```
dmesg | grep -i kill
```

This shows if kernel killed your process due to memory pressure.

---

# 18. SERVICE FAILURE ROOT CAUSE

Check service:

```
systemctl status service
```

Logs:

```
journalctl -u service
```

Service configuration:

```
systemctl cat service
```

---

# 19. REAL ENGINEER DASHBOARD

During incident, professionals run:

```
top
iostat 1
vmstat 1
ss -s
```

This gives:

CPU + disk + memory + network overview simultaneously.

---

# 20. THE PROFESSIONAL DEBUGGING MINDSET

Advanced engineers follow strict order:

```
Measure
Compare
Confirm
Fix
Verify
```

Never:

```
Guess → restart → hope
```

---

# ADVANCED LEVEL COMPLETION CHECK

You can now:

✔ identify root cause of slow systems
✔ trace process behavior
✔ analyze kernel logs
✔ detect leaks and limits
✔ inspect network traffic
✔ debug disk bottlenecks
✔ investigate crashes

---

# FINAL LINE FOR THIS CHAPTER

At this point:

> You are no longer someone who uses Linux.

You are someone Linux reveals secrets to.

Because you now know how to ask it the right questions.

---

END OF LEVEL 5 — ADVANCED

---

When ready for the next chapter, say:

**PRO**

Next stage:

> “You think like a systems architect, not just an engineer.”
