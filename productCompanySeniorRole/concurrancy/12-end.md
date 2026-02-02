````md
# STEP 11: JVM, Thread Dumps & Production Debugging

---

## Why this step exists

At senior level, concurrency is no longer about *writing* code.  
It’s about **understanding what the system is doing when it misbehaves**.

Production issues rarely say:
> “Here is the bug.”

They say:
- Latency spikes
- CPU at 90%
- Requests stuck
- System slow, then fine, then slow again

This step exists because **debugging concurrency is a skill of observation, not syntax**.

---

## The mindset shift

Junior thinking:
> “Where is the bug in my code?”

Senior thinking:
> “What is the system telling me about its state?”

Threads, CPU, GC, locks — all leave signals.  
You learn to read them.

---

## Thread dumps (the first tool)

A thread dump is a snapshot of:
- All threads
- Their states
- What they are waiting on
- What they are holding

It answers:
- Who is running?
- Who is blocked?
- Who is waiting?
- Why nothing is progressing?

---

## Thread states that matter

- **RUNNABLE** → using CPU or ready to
- **BLOCKED** → waiting for a lock
- **WAITING** → waiting indefinitely
- **TIMED_WAITING** → sleeping or timed wait

Seeing many threads in BLOCKED or WAITING is a signal, not a problem by itself.

---

## Reading a thread dump (how seniors do it)

They don’t read all threads.  
They look for patterns:

- Many threads blocked on the same lock
- One thread holding a lock too long
- Thread pool threads stuck waiting
- Application threads blocked on I/O

Patterns matter more than individual lines.

---

## Deadlock detection

Deadlocks are rare but decisive.

The JVM can detect them:

```bash
jstack <pid>
````

If present, it will clearly say:

* Which threads are deadlocked
* Which locks are involved

Deadlocks are design failures, not runtime accidents.

---

## CPU-bound vs I/O-bound diagnosis

High CPU:

* Many RUNNABLE threads
* Hot methods repeat in stack traces
* Possible infinite loops or heavy computation

I/O bottleneck:

* Threads in WAITING / TIMED_WAITING
* Blocking on sockets, DB calls, file I/O

Different causes.
Different fixes.

---

## Thread pools under the lens

Common production issues:

* Thread pool exhausted
* Tasks queued indefinitely
* Requests timing out upstream

Symptoms:

* Threads alive but not progressing
* Queue growing
* Rejections or silent slowdown

Thread dumps + metrics tell the story together.

---

## GC and threads (often overlooked)

Garbage Collection:

* Pauses all threads (Stop-The-World)
* Affects latency directly

If latency spikes align with GC:

* The problem is memory pressure
* Not concurrency logic

Concurrency issues and GC issues often look similar until measured.

---

## Profiling vs debugging

Thread dumps answer:

> “What is happening right now?”

Profilers answer:

> “Where is time being spent over time?”

Both are needed:

* Dumps for correctness
* Profilers for performance

Never guess when tools can tell you.

---

## Production debugging principles

* Observe before changing
* Reproduce before fixing
* Measure before optimizing
* Fix the cause, not the symptom

Concurrency bugs punish guesswork.

---

## Senior instinct

When a system is slow:

* Don’t add threads
* Don’t restart blindly
* Don’t optimize randomly

First understand:

* Thread states
* Lock contention
* Resource limits

Then act.

---

## Interview signal

> “In production, I rely on thread dumps, metrics, and profiling to understand concurrency issues before making changes.”

That tells the interviewer you’ve been on-call.

---

## Quick recall

* Thread dump → snapshot of execution
* Patterns > individual threads
* RUNNABLE vs BLOCKED vs WAITING
* Deadlocks are design flaws
* GC pauses affect latency
* Measure before fixing

---

## End of the roadmap

At this point, concurrency is no longer a topic.
It’s a **lens** through which you see systems.

You don’t just write concurrent code.
You **reason about behavior under load**.

That’s what senior backend engineering looks like.

```
```
