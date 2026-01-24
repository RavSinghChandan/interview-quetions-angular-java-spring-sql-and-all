# Module 11: JVM & Performance Tuning (Self Notes)

---

## Core Purpose

This module focuses on **observability**, **diagnostics**, and **performance tuning** of concurrent Java systems in production.

**Golden Rule:**

> You can’t tune what you can’t measure.

---

## Mental Model

* Thread dump = snapshot of thread states
* Deadlock detection = cycle finding
* Pool sizing = math + measurement
* GC = memory + latency tradeoff

---

## Topic 85: Thread Dump Analysis

* Captures all thread states
* Identifies BLOCKED, WAITING, RUNNABLE
* Shows lock ownership
* Used for debugging hangs

---

## Topic 86: Deadlock Detection

* Cyclic lock dependency
* Detected via jstack / JConsole
* JVM reports deadlock details

---

## Topic 87: CPU-bound vs IO-bound Threads

* CPU-bound: computation heavy
* IO-bound: waiting on network/disk
* Different pool sizing strategies

---

## Topic 88: Thread Pool Sizing Formula

* CPU-bound: cores + 1
* IO-bound: cores * (1 + wait/compute)

---

## Topic 89: Context Switching Cost

* Thread scheduling overhead
* Cache invalidation
* Pipeline flush

---

## Topic 90: Synchronization Overhead

* Lock contention
* Monitor inflation
* Reduced throughput

---

## Topic 91: GC and Threads Interaction

* Stop-the-world pauses
* GC threads compete for CPU
* Object allocation rate matters

---

## Topic 92: Profiling Tools

* JVisualVM
* JConsole
* Java Flight Recorder
* Mission Control

---

## Topic 93: Production Debugging Strategies

* Capture thread dumps
* Analyze GC logs
* Monitor metrics
* Reproduce issues

---

## Execution Rules

* Always monitor thread pools
* Log slow tasks
* Track queue sizes
* Tune GC for latency

---

## Real-World Mapping

* Payment system latency
* Fraud engine throughput
* API timeout issues
* Batch job slowdowns

---

## Performance Implications

* Oversized pools → context switching
* Undersized pools → underutilization
* GC pauses → latency spikes

---

## Common Mistakes

* Blind pool sizing
* Ignoring GC logs
* Not capturing thread dumps
* Over-synchronization

---

## Design Rules

* Measure before tuning
* Separate CPU and IO pools
* Prefer async over blocking
* Profile regularly

---

## JVM Insight

* GC algorithms impact latency
* Thread scheduling is OS-managed
* Heap sizing affects throughput

---

## Senior-Level Takeaway

> Performance tuning is an iterative, measurement-driven process.

---

## Ultra-Crisp Recall

* Thread dumps = diagnosis
* Deadlocks = cycles
* Pool sizing = math
* GC = latency factor

---

## Interview Punchline

> JVM performance tuning involves analyzing thread dumps, detecting deadlocks, sizing thread pools correctly, minimizing synchronization overhead, and tuning GC to balance latency and throughput.

---
