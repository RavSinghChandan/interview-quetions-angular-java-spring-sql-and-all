# Module 6: Executors Framework (Self Notes)

---

## Core Purpose

The Executors Framework provides a **high-level, managed, and scalable** way to execute asynchronous tasks without manually creating threads.

**Golden Rule:**

> Never create raw threads in production. Always use a properly sized thread pool.

---

## Mental Model

* Task = Job
* Thread = Worker
* ExecutorService = Factory managing workers
* Queue = Waiting line for jobs

---

## Topic 38: Executor Interface

* Minimal interface with `execute(Runnable)`
* Decouples task submission from execution
* Foundation for all executor implementations

---

## Topic 39: ExecutorService

* Extends Executor
* Adds lifecycle control
* Supports `submit()`, `invokeAll()`, `shutdown()`

```java
ExecutorService es = Executors.newFixedThreadPool(4);
es.submit(() -> processPayment());
```

---

## Topic 40: ScheduledExecutorService

* Runs tasks after delay or periodically
* Replaces Timer

```java
ScheduledExecutorService ses = Executors.newScheduledThreadPool(2);
ses.scheduleAtFixedRate(() -> refreshCache(), 0, 5, TimeUnit.MINUTES);
```

---

## Topic 41: FixedThreadPool

* Fixed number of threads
* Unbounded queue
* Stable resource usage
* Risk: memory leak under heavy load

---

## Topic 42: CachedThreadPool

* Creates threads on demand
* No queue
* Reuses idle threads
* Risk: unbounded thread creation

---

## Topic 43: SingleThreadExecutor

* One worker thread
* Serial task execution
* Guarantees ordering

---

## Topic 44: ScheduledThreadPool

* Fixed threads
* Supports delayed and periodic tasks

---

## Topic 45: ThreadPool Sizing

* CPU-bound: cores + 1
* IO-bound: cores * (1 + wait/compute)

---

## Topic 46: RejectedExecutionHandler

* Handles task rejection
* Types:

    * AbortPolicy
    * CallerRunsPolicy
    * DiscardPolicy
    * DiscardOldestPolicy

---

## Topic 47: Graceful Shutdown

```java
es.shutdown();
es.awaitTermination(10, TimeUnit.SECONDS);
```

---

## Execution Rules

* Always shutdown executors
* Never block pool threads
* Separate CPU and IO pools
* Monitor queue size

---

## Real-World Mapping

* API request handling
* Fraud scoring jobs
* Batch settlement tasks
* Notification services

---

## Performance Implications

* Too many threads → context switching
* Too few threads → underutilization
* Unbounded queues → OOM

---

## Common Mistakes

* Using CachedThreadPool blindly
* Forgetting shutdown()
* Blocking inside pool threads
* Single pool for all workloads

---

## Design Rules

* Prefer ThreadPoolExecutor over Executors factory
* Use bounded queues
* Separate pools by workload
* Monitor metrics

---

## JVM Insight

* Thread pools reduce GC pressure
* Idle threads consume stack memory
* ForkJoinPool optimized for parallelism

---

## Senior-Level Takeaway

> Executors provide controlled concurrency. Thread pool sizing and queue management decide performance and stability.

---

## Ultra-Crisp Recall

* Never use raw threads
* FixedPool = stable
* CachedPool = risky
* Always shutdown
* Size pools correctly

---

## Interview Punchline

> The Executors Framework abstracts thread management using thread pools. It improves scalability and stability. In production, always use bounded thread pools, proper sizing, and graceful shutdown.

---
