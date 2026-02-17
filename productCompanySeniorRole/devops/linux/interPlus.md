# LINUX — LEVEL 4 (INTERMEDIATE+)

**Stage Title: “You Stop Fixing Problems… and Start Understanding the System’s Mind”**

*(Story continues — you are no longer reacting to issues; now you understand why they happen.)*

---

## CHAPTER CONTEXT — WHAT CHANGES HERE

At Intermediate level you learned:

> how to detect problems.

At this level you learn:

> why problems happen.

This is where engineers cross a critical boundary:

```
Command User → System Thinker
```

You start seeing Linux not as commands…

…but as a living system with internal logic.

---

# 1. THE MOMENT YOU REALIZE SYSTEMS HAVE BEHAVIOR

Beginners think:

> CPU high = bad

Engineers ask:

> Why is CPU high?

Linux systems behave based on:

* scheduling
* memory allocation
* process states
* I/O waits
* interrupts

Understanding these is what separates senior engineers from average ones.

---

# 2. PROCESS STATES — PROGRAMS HAVE LIFE CYCLES

Processes are not simply running or stopped.

States:

```
R → Running
S → Sleeping
D → Waiting for I/O
Z → Zombie
T → Stopped
```

Check:

```
ps aux
```

Most processes are actually:

```
Sleeping
```

That is normal.

---

### Zombie Process (Important Concept)

Zombie = finished process whose parent hasn’t collected exit status.

It means:

> the program is dead but its record still exists.

Fix → kill parent process.

---

# 3. LOAD AVERAGE — TRUE SYSTEM STRESS METER

Check:

```
uptime
```

Output:

```
1.20 0.85 0.60
```

These numbers represent load over:

```
1 min
5 min
15 min
```

Interpretation rule:

```
Load > CPU cores → system overloaded
```

Example:

4-core machine
load = 6

Meaning → system is struggling.

---

# 4. CPU TIME BREAKDOWN

Inside `top` you see:

```
us → user CPU
sy → system CPU
id → idle
wa → I/O wait
```

Meaning:

| Metric  | Interpretation    |
| ------- | ----------------- |
| high us | application heavy |
| high sy | kernel workload   |
| high wa | disk slow         |
| low id  | CPU fully busy    |

Now you can tell *why* CPU is high.

---

# 5. MEMORY REALITY — THE BIGGEST MISUNDERSTANDING

Most beginners panic when memory usage is high.

Linux uses memory intentionally.

Check:

```
free -m
```

Important column:

```
available
```

Rule:

> High usage is normal. Low available is danger.

Linux caches data to speed up performance.

So full RAM ≠ problem.

---

### Memory Types

```
RSS  → real memory used
VIRT → total allocated
SWAP → disk memory
```

Check heavy processes:

```
ps aux --sort=-%mem
```

---

# 6. DISK I/O — THE INVISIBLE BOTTLENECK

A system can have:

* low CPU
* low RAM

…but still be slow.

Reason: disk.

Check:

```
iostat -x 1
```

Important values:

```
await → wait time
%util → disk busy
```

Rule:

```
%util > 80 → disk bottleneck
```

---

# 7. NETWORK — LINUX HANDLES IT ALL

Applications don’t manage network.

Linux kernel manages:

* sockets
* packets
* routing
* connections

Check connection stats:

```
ss -s
```

Routes:

```
ip route
```

Interfaces:

```
ip -s link
```

---

# 8. FILE DESCRIPTORS — THE LIMIT MOST ENGINEERS MISS

Every open connection uses one file descriptor.

Check limit:

```
ulimit -n
```

Check usage:

```
lsof | wc -l
```

If limit reached:

> server stops accepting connections

This is a common real production failure.

---

# 9. CONTEXT SWITCHING — CPU WASTE YOU CAN’T SEE

CPU constantly switches between processes.

Too many switches = slow system.

Check:

```
vmstat 1
```

Column:

```
cs
```

High value → too many processes competing.

---

# 10. INTERRUPTS — HARDWARE TALKING TO CPU

Hardware signals CPU using interrupts.

Check:

```
cat /proc/interrupts
```

If interrupts high → network or hardware overload.

---

# 11. SYSTEM CALLS — HOW PROGRAMS TALK TO KERNEL

Programs cannot directly access hardware.

They must request kernel using:

> system calls

Trace them:

```
strace ls
```

Attach to running process:

```
strace -p PID
```

This shows what program is actually doing.

---

# 12. PROCESS PRIORITY — WHO GETS CPU FIRST

Run with priority:

```
nice -n 10 command
```

Change priority:

```
renice -n 5 PID
```

Lower number = higher priority.

---

# 13. SIGNALS — HOW PROCESSES COMMUNICATE

Signals tell processes what to do.

Send signal:

```
kill -SIGTERM PID
kill -SIGKILL PID
kill -SIGHUP PID
```

Meanings:

| Signal | Meaning       |
| ------ | ------------- |
| TERM   | graceful stop |
| KILL   | force stop    |
| HUP    | reload config |

---

# 14. FILESYSTEM TYPES — NOT ALL DISKS ARE SAME

Check:

```
df -T
```

Common types:

```
ext4 → default
xfs → high performance
tmpfs → memory disk
```

Different filesystems behave differently under load.

---

# 15. BOOT PROCESS — HOW SYSTEM COMES ALIVE

Startup flow:

```
BIOS
→ Bootloader
→ Kernel
→ Init system
→ Services
```

Init system usually:

```
systemd
```

---

# 16. KERNEL PARAMETERS — TUNING SYSTEM BEHAVIOR

View all:

```
sysctl -a
```

Change value:

```
sysctl -w net.ipv4.ip_forward=1
```

Permanent change:

```
/etc/sysctl.conf
```

---

# 17. CONTAINERS EXIST BECAUSE OF LINUX FEATURES

Containers rely on:

* namespaces
* cgroups

Namespaces isolate:

* processes
* network
* filesystem

Cgroups limit:

* CPU
* RAM
* I/O

Without these → Docker wouldn’t exist.

---

# 18. THE ENGINEER MINDSET TRANSFORMATION

Before this level:

> You fix errors.

After this level:

> You predict failures.

You can now look at system metrics and foresee crashes.

That is rare skill.

---

# INTERMEDIATE+ COMPLETION CHECK

You now understand:

✔ why load matters
✔ how kernel schedules
✔ how memory behaves
✔ why disk causes slowdown
✔ how limits crash servers
✔ how processes interact
✔ how containers are possible

---

# FINAL LINE FOR THIS CHAPTER

At this point in the story:

> Linux stops looking like commands.

It starts looking like a system with logic.

And once you see the logic…

You can control the system.

---

END OF LEVEL 4 — INTERMEDIATE+

---

When you’re ready for the next chapter, say:

**ADVANCED**

Next stage:

> “You learn how expert engineers diagnose failures nobody else can solve.”
