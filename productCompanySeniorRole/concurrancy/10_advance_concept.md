# Module 10: Advanced Concurrency Concepts (Self Notes)

---

## Core Purpose

This module focuses on **high-performance**, **low-latency**, and **lock-free** concurrency techniques used in large-scale systems.

**Golden Rule:**

> Avoid locks where possible. Prefer immutability and non-blocking designs.

---

## Mental Model

* ThreadLocal = private thread storage
* Immutable objects = share safely
* Lock-free = progress without blocking
* Reactive streams = data flows

---

## Topic 76: ThreadLocal

* Stores data per thread
* Avoids shared state
* Risk of memory leaks

```java
ThreadLocal<String> ctx = new ThreadLocal<>();
ctx.set("userId");
```

---

## Topic 77: Immutable Objects

* State never changes
* Thread-safe by design
* Safe sharing

---

## Topic 78: Lock-Free Programming

* Uses CAS
* No thread blocking
* High throughput

---

## Topic 79: Non-Blocking Algorithms

* Guarantee progress
* Avoid deadlocks
* Use atomic operations

---

## Topic 80: Memory Consistency Errors

* Stale reads
* Reordering bugs
* Visibility issues

---

## Topic 81: False Sharing

* Threads modify adjacent memory
* Cache line contention
* Performance collapse

---

## Topic 82: Cache Coherence

* CPU cores sync memory
* MESI protocol
* Costly synchronization

---

## Topic 83: Reactive Streams

* Async data flow
* Non-blocking
* Backpressure support

---

## Topic 84: Backpressure

* Flow control
* Prevent overload
* Reactive stability

---

## Execution Rules

* Avoid ThreadLocal leaks
* Prefer immutability
* Use atomics carefully
* Design backpressure

---

## Real-World Mapping

* Request context storage
* Event streaming
* High-frequency trading
* Async microservices

---

## Performance Implications

* Lock-free = high throughput
* False sharing = hidden killer
* ThreadLocal leaks = memory bloat

---

## Common Mistakes

* Overusing ThreadLocal
* Mutable shared state
* Ignoring backpressure
* CAS overuse

---

## Design Rules

* Favor immutability
* Minimize shared state
* Use reactive for streaming
* Profile false sharing

---

## JVM Insight

* VarHandles power CAS
* ThreadLocalMap stores per-thread data
* Cache line = 64 bytes

---

## Senior-Level Takeaway

> Advanced concurrency is about avoiding contention, not adding locks.

---

## Ultra-Crisp Recall

* ThreadLocal = per-thread data
* Immutability = thread safety
* Lock-free = CAS-based
* Backpressure = overload control

---

## Interview Punchline

> Advanced concurrency focuses on minimizing contention using immutability, ThreadLocal, lock-free programming, and reactive streams with backpressure for scalable systems.

---
