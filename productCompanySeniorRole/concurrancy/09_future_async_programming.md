# Module 9: Futures & Async Programming (Self Notes)

---

## Core Purpose

This module enables **non-blocking execution**, **asynchronous pipelines**, and **parallel task composition** for scalable systems.

**Golden Rule:**

> Never block waiting for a Future if you can compose asynchronously.

---

## Mental Model

* Task = computation
* Future = promise of result
* CompletableFuture = pipeline of async stages
* ForkJoinPool = parallel task engine

---

## Topic 67: Callable

* Task that returns a value
* Can throw checked exceptions
* Used with ExecutorService

```java
Callable<Integer> task = () -> 42;
```

---

## Topic 68: Future

* Represents pending result
* Blocking `get()`
* Can be cancelled

```java
Future<Integer> f = es.submit(task);
Integer result = f.get();
```

---

## Topic 69: CompletableFuture Basics

* Non-blocking async API
* Supports chaining
* Built on ForkJoinPool by default

```java
CompletableFuture.supplyAsync(() -> fetchData());
```

---

## Topic 70: thenApply / thenCompose

* thenApply = transform result
* thenCompose = flatten nested futures

---

## Topic 71: thenCombine

* Combine two independent futures
* Parallel execution

---

## Topic 72: allOf / anyOf

* allOf = wait for all
* anyOf = wait for first

---

## Topic 73: Exception Handling

* exceptionally()
* handle()
* whenComplete()

---

## Topic 74: ForkJoinPool

* Work-stealing pool
* Optimized for CPU-bound tasks
* Recursive task splitting

---

## Topic 75: Work-Stealing Algorithm

* Idle threads steal work
* Improves CPU utilization
* Reduces idle time

---

## Execution Rules

* Avoid blocking get()
* Always provide custom executor
* Separate IO and CPU pools
* Handle exceptions explicitly

---

## Real-World Mapping

* Async API orchestration
* Fraud score pipelines
* Parallel data fetch
* Batch processing jobs

---

## Performance Implications

* Blocking kills async benefits
* ForkJoinPool suits CPU work
* Too many async stages → overhead

---

## Common Mistakes

* Calling get() too early
* Ignoring exceptions
* Using default ForkJoinPool blindly
* Mixing blocking IO with async

---

## Design Rules

* Use supplyAsync for IO
* Use thenCompose for dependent calls
* Always specify executor
* Avoid deep callback chains

---

## JVM Insight

* CompletableFuture uses ForkJoinPool.commonPool
* Work stealing minimizes idle threads
* Blocking tasks reduce pool efficiency

---

## Senior-Level Takeaway

> Futures enable async results. CompletableFuture enables async pipelines. ForkJoinPool powers parallel execution.

---

## Ultra-Crisp Recall

* Callable = returns value
* Future = blocking result
* CompletableFuture = async pipeline
* ForkJoinPool = parallel engine

---

## Interview Punchline

> Callable and Future support asynchronous execution, but CompletableFuture enables non-blocking async pipelines. ForkJoinPool uses work-stealing for efficient parallel execution. In production, always avoid blockin
