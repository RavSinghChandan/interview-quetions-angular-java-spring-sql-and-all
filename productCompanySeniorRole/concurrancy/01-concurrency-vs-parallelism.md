
# STEP 1: Concurrency vs Parallelism

---

## Why this matters

Most concurrency bugs don’t start with locks or threads.  
They start with confusing **concurrency** and **parallelism**.

If this distinction is clear, design decisions become obvious.  
If it’s not, systems look correct but fail under load.

---

## Core idea

**Concurrency**  
Structuring a program so multiple tasks can *make progress* at the same time.  
Tasks may interleave on a single CPU core.

**Parallelism**  
Executing multiple tasks *at the same time* on multiple CPU cores.

> Concurrency is about **how work is structured**.  
> Parallelism is about **how work is executed**.

---

## Mental picture (easy to recall)

Concurrency:  
One person cooking and answering a phone by switching context.

Parallelism:  
Two people cooking two dishes simultaneously.

Context switching vs true simultaneity.

---

## Java perspective

Java provides **concurrency abstractions**, not guaranteed parallelism.

Threads, locks, executors, futures → express concurrency.  
CPU cores + OS scheduler → decide parallel execution.

This is why adding threads does not always improve performance.

---

## Code example

```java
Runnable task = () -> {
    System.out.println(Thread.currentThread().getName());
};

new Thread(task).start();
new Thread(task).start();
````

This code is **concurrent** by design.

It becomes **parallel** only if the JVM schedules these threads on different cores.

Parallelism here is an execution detail, not a programming guarantee.

---

## Why concurrency exists

Concurrency exists to:

* Keep applications responsive
* Hide I/O latency
* Handle many requests with limited resources
* Improve throughput without blocking

Examples:

* Web servers waiting on database calls
* API gateways orchestrating downstream services
* Asynchronous fraud checks

---

## Why parallelism exists

Parallelism exists to:

* Speed up CPU-bound work
* Reduce total computation time
* Utilize multi-core hardware efficiently

Examples:

* Encryption and decryption
* Risk calculations
* Batch settlement jobs
* Data processing pipelines

---

## Execution reality

* A concurrent program may not run in parallel
* A parallel program must be concurrent
* Async does not imply parallel
* More threads do not guarantee better performance

---

## Performance intuition

* Too much concurrency → context switching overhead
* Too much parallelism → CPU saturation
* Blocking threads → destroys concurrency benefits

Good systems balance both intentionally.

---

## Production takeaway

Concurrency is used to **manage waiting**.
Parallelism is used to **accelerate computation**.

Thread pools control concurrency.
ForkJoinPool exploits parallelism.

---

## Senior-level summary

Concurrency structures programs to handle multiple tasks efficiently.
Parallelism executes tasks simultaneously for performance.

Java enables concurrency; actual parallelism depends on hardware and scheduling.

---

## Quick recall

* Concurrency → task structure
* Parallelism → task execution
* Concurrency hides I/O latency
* Parallelism speeds up CPU work

---

## What this leads to

Once concurrency is introduced, tasks share memory.

The next question becomes:
**what exactly is running concurrently, and what do they share?**

That is where processes and threads enter the picture.

```
```
