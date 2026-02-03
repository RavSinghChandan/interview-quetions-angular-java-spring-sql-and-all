
# STEP 6: Executors & Thread Pools

---

## Why this step exists

Up to this point, threads were created manually.

That works for learning.  
It completely breaks in production.

Creating threads directly ties **task submission** to **thread creation**, and that does not scale.

The moment traffic increases, systems fail due to:
- Too many threads
- Uncontrolled resource usage
- Unpredictable latency

This step exists to separate **what to run** from **how it runs**.

---

## The core shift in thinking

Instead of saying:
> “Create a thread and run this task”

We say:
> “Submit this task and let the system decide how to execute it”

That shift is the foundation of all scalable serviceBasedMNCLevel.backend systems.

---

## What Executors actually solve

Executors solve three real problems:
- Unbounded thread creation
- Lack of backpressure
- No lifecycle control

They introduce:
- Thread reuse
- Controlled concurrency
- Graceful shutdown

---

## Executor mental model

Think in terms of:
- **Tasks** (what needs to run)
- **Workers** (threads)
- **Queue** (waiting tasks)

You submit tasks.  
The executor manages threads.

---

## Code: naive thread creation (what not to do)

```java
for (int i = 0; i < 1000; i++) {
    new Thread(() -> process()).start();
}
````

Problems:

* 1000 threads created
* Context switching explosion
* Memory pressure
* No backpressure

This design collapses under real load.

---

## Code: ExecutorService (correct approach)

```java
ExecutorService executor =
        Executors.newFixedThreadPool(10);

for (int i = 0; i < 1000; i++) {
    executor.submit(() -> process());
}
```

What changed:

* Only 10 threads exist
* Tasks are queued
* Throughput is controlled
* System remains stable

---

## Thread pool types (know the intent)

### FixedThreadPool

* Fixed number of threads
* Best for CPU-bound tasks
* Predictable resource usage

### CachedThreadPool

* Creates threads as needed
* Risky under high load
* Avoid in serviceBasedMNCLevel.backend services

### SingleThreadExecutor

* One thread, ordered execution
* Useful for serialization

### ScheduledThreadPool

* Delayed and periodic tasks
* Heartbeats, retries, cleanup jobs

---

## Thread pool sizing intuition (senior-level)

* CPU-bound → number of cores
* I/O-bound → higher, but controlled
* Never unbounded

Rule of thumb:

> Threads should be a **limit**, not a guess.

---

## Task rejection (important in production)

When the queue is full, tasks must be rejected.

```java
new ThreadPoolExecutor(
    10,
    10,
    0L,
    TimeUnit.MILLISECONDS,
    new ArrayBlockingQueue<>(100),
    new ThreadPoolExecutor.AbortPolicy()
);
```

Rejection is not failure.
It is **backpressure**.

Better to reject than to crash.

---

## Graceful shutdown (often missed)

```java
executor.shutdown();

if (!executor.awaitTermination(30, TimeUnit.SECONDS)) {
    executor.shutdownNow();
}
```

Why this matters:

* Prevents task loss
* Allows clean service shutdown
* Required in regulated systems

---

## Senior instinct

Threads are infrastructure, not business logic.

Business code submits tasks.
Executors enforce limits.

If business logic knows about thread count, the design is wrong.

---

## Interview signal

> “Executors decouple task submission from execution and provide controlled concurrency through thread pooling and backpressure.”

That sentence signals production experience.

---

## Quick recall

* Never create threads directly in services
* Executors manage lifecycle and limits
* Pools control concurrency
* Rejection is a feature, not a bug

---

## Where this leads next

Once threads are managed,
coordination between them becomes the next challenge.

That leads to:
**Locks and advanced synchronizers.**

```
```
