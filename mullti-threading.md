# 📘 Java Multithreading Interview Questions – Topic-Wise

> Structured as:
>
> * ✅ Basic
> * ⚙️ Intermediate
> * 🚀 Advanced
> * 💡 Real-life/Application-Based

---

## 🧵 1. Thread Basics

### ✅ Basic

**Q: What is a Thread in Java?**

> A thread is a lightweight subprocess, the smallest unit of processing. It allows concurrent execution of two or more parts of a program.

**Q: How do you create a thread using `Thread` class and `Runnable` interface?**

```java
// Using Thread class
class MyThread extends Thread {
    public void run() {
        System.out.println("Thread is running");
    }
}

// Using Runnable
class MyRunnable implements Runnable {
    public void run() {
        System.out.println("Runnable is running");
    }
}

public class Test {
    public static void main(String[] args) {
        new MyThread().start();
        new Thread(new MyRunnable()).start();
    }
}
```

**Q: What is the difference between `start()` and `run()` methods?**

> `start()` creates a new thread and calls `run()` internally. Calling `run()` directly doesn’t create a new thread.

**Q: What are the thread states in Java?**

> NEW → RUNNABLE → BLOCKED/WAITING/TIMED\_WAITING → TERMINATED

**Q: What is the default priority of a thread?**

> The default priority is 5 (NORM\_PRIORITY).

### ⚙️ Intermediate

**Q: Can we start a thread twice?**

> No. Once a thread is started, calling `start()` again will throw `IllegalThreadStateException`.

**Q: What happens if we call `run()` directly?**

> It executes on the current thread, not a new one.

**Q: How to set thread name and priority?**

```java
Thread t = new Thread(() -> {});
t.setName("WorkerThread");
t.setPriority(Thread.MAX_PRIORITY);
```

### 🚀 Advanced

**Q: How does thread lifecycle work internally in JVM?**

> The JVM uses OS-level threads and maps Java thread states to native thread states. Context switching and state transitions are managed by the OS scheduler.

**Q: When should you use `Runnable` vs `Callable`?**

> Use `Runnable` for fire-and-forget tasks, `Callable` when you need to return a result or throw checked exceptions.

### 💡 Real-life/Application-Based

**Q: Implement a file processor using a custom thread.**

```java
class FileProcessor extends Thread {
    private String fileName;
    public FileProcessor(String fileName) {
        this.fileName = fileName;
    }
    public void run() {
        System.out.println("Processing: " + fileName);
    }
}
new FileProcessor("input.txt").start();
```

**Q: Use thread naming for logging/debugging in a large-scale system.**

```java
Thread t = new Thread(() -> {
    System.out.println("Running: " + Thread.currentThread().getName());
});
t.setName("PaymentProcessor-1");
t.start();
```

---

## 🔁 2. Thread Lifecycle and Management

### ✅ Basic

**Q: What are the different states of a thread?**

> NEW, RUNNABLE, BLOCKED, WAITING, TIMED\_WAITING, TERMINATED

**Q: What are the lifecycle methods in Java?**

> `start()`, `sleep()`, `wait()`, `notify()`, `join()`

### ⚙️ Intermediate

**Q: Explain the difference between `wait()`, `sleep()`, and `join()`?**

* `sleep()` pauses current thread without releasing lock
* `wait()` pauses and releases lock (inside synchronized block)
* `join()` pauses current thread until another thread finishes

**Q: How do you pause/resume/stop a thread safely?**

> Use `volatile` flags and interruption pattern. Avoid `stop()` (unsafe).

```java
volatile boolean running = true;
public void run() {
    while(running) {
        // do work
    }
}
public void stopThread() {
    running = false;
}
```

### 🚀 Advanced

**Q: Internal working of `join()` and how it blocks the calling thread?**

> Internally, `join()` uses `wait()` and notifies when thread finishes, blocking the caller.

**Q: How does JVM switch between threads?**

> JVM relies on OS-level preemptive multitasking using time-slicing. Context switching is handled by the OS scheduler.

### 💡 Real-life/Application-Based

**Q: Managing thread states in an ETL pipeline.**

> Use `join()` to wait for extraction before transformation. Use `ExecutorService` to coordinate task dependencies.

**Q: Implement retry mechanism with timed sleep for failed jobs.**

```java
int attempts = 0;
while (attempts < 3) {
    try {
        // attempt task
        break;
    } catch (Exception e) {
        attempts++;
        Thread.sleep(1000); // backoff
    }
}
```

---

## 🔐 3. Synchronization and Locks

### ✅ Basic

**Q: What is synchronization in Java?**

> It’s a mechanism to control access to shared resources to avoid race conditions.

**Q: What is a race condition?**

> When two threads access shared data and try to change it at the same time, leading to unpredictable results.

### ⚙️ Intermediate

**Q: Difference between synchronized method and synchronized block?**

* Synchronized method locks the whole method (object/class)
* Synchronized block locks a specific code block/object

**Q: What is intrinsic lock/monitor lock?**

> Every object in Java has a monitor lock (intrinsic lock) associated with it used by `synchronized`.

**Q: Can a static method be synchronized?**

> Yes, it locks the `Class` object, not the instance.

### 🚀 Advanced

**Q: Reentrant synchronization and `ReentrantLock` vs `synchronized`.**

* `ReentrantLock` gives more control (tryLock, fairness)
* `synchronized` is simpler but less flexible

```java
Lock lock = new ReentrantLock();
lock.lock();
try {
    // critical section
} finally {
    lock.unlock();
}
```

**Q: Deadlock: What is it and how to prevent it?**

> A situation where two or more threads are blocked forever, waiting on each other. Prevent by:

* Lock ordering
* Using `tryLock()` with timeout

**Q: Difference between `wait()`, `notify()`, and `notifyAll()`**

* `wait()` pauses and releases lock
* `notify()` wakes one waiting thread
* `notifyAll()` wakes all waiting threads

### 💡 Real-life/Application-Based

**Q: Implement thread-safe banking system.**

```java
class Account {
    private int balance = 1000;
    public synchronized void withdraw(int amount) {
        if(balance >= amount) {
            balance -= amount;
        }
    }
}
```

# 🧠 5. Callable, Future, and FutureTask

## ✅ Basic

### 🔹 What is Callable in Java?

Callable is an interface in `java.util.concurrent` introduced in Java 5. Unlike Runnable, Callable can return a result and throw checked exceptions.

```java
Callable<Integer> task = () -> {
    return 10 + 20;
};
```

### 🔹 Difference between Runnable and Callable?

| Feature        | Runnable             | Callable                     |
| -------------- | -------------------- | ---------------------------- |
| Return Value   | No                   | Yes                          |
| Exceptions     | Cannot throw checked | Can throw checked exceptions |
| Functional Int | Yes                  | Yes                          |

---

## ⚙️ Intermediate

### 🔹 How to retrieve results from a Callable task?

Using `ExecutorService` and `Future`:

```java
ExecutorService executor = Executors.newSingleThreadExecutor();
Callable<String> task = () -> "Hello Callable";
Future<String> future = executor.submit(task);
String result = future.get();
executor.shutdown();
```

### 🔹 What is `FutureTask`?

`FutureTask` implements both `Runnable` and `Future`. It can wrap a `Callable` or `Runnable` and be executed by a thread or executor.

```java
FutureTask<Integer> futureTask = new FutureTask<>(() -> 10 + 20);
Thread thread = new Thread(futureTask);
thread.start();
int result = futureTask.get();
```

---

## 🚀 Advanced

### 🔹 How to cancel a Future task?

```java
future.cancel(true); // Attempts to cancel if not completed
```

### 🔹 What are the problems with blocking `get()`?

* Blocks the calling thread.
* If task is long-running, it can lead to UI freeze or thread starvation.
* Solution: Use `future.get(timeout, TimeUnit)`

```java
String result = future.get(5, TimeUnit.SECONDS);
```

---

## 💡 Real-life/Application-Based

### 🔹 Submit multiple Callable tasks and collect results

```java
ExecutorService executor = Executors.newFixedThreadPool(3);
List<Callable<String>> tasks = Arrays.asList(
    () -> "Task1", () -> "Task2", () -> "Task3"
);
List<Future<String>> results = executor.invokeAll(tasks);
for (Future<String> f : results) {
    System.out.println(f.get());
}
executor.shutdown();
```

### 🔹 Asynchronous data fetch with `Future`

Useful in REST clients to parallelize multiple API calls.

```java
Callable<String> apiCall = () -> restClient.get("/api/data");
Future<String> result = executor.submit(apiCall);
```

---

# ⏱ 6. ScheduledExecutorService

## ✅ Basic

### 🔹 What is `ScheduledExecutorService`?

A special executor that supports periodic and delayed task execution.

```java
ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
```

---

## ⚙️ Intermediate

### 🔹 How do you schedule periodic tasks?

```java
scheduler.scheduleAtFixedRate(() -> {
    System.out.println("Running every 5 seconds");
}, 0, 5, TimeUnit.SECONDS);
```

---

## 🚀 Advanced

### 🔹 Handling missed execution windows

Use `scheduleWithFixedDelay()` to wait for previous task to finish before scheduling next.

```java
scheduler.scheduleWithFixedDelay(() -> {
    // long-running task
}, 0, 5, TimeUnit.SECONDS);
```

### 🔹 Using delays dynamically

Delay values can be dynamically computed using a wrapper task or condition.

---

## 💡 Real-life/Application-Based

### 🔹 Scheduled health-checks in microservices

Use to periodically ping dependent services or endpoints.

### 🔹 Cache invalidation using scheduled tasks

Invalidate cache entries after a specific duration to avoid stale data.

```java
scheduler.schedule(() -> cache.invalidate("key"), 10, TimeUnit.MINUTES);
```

---

# 🧮 7. Concurrency Utilities (`java.util.concurrent`)

## ✅ Basic

### 🔹 What is the use of `ConcurrentHashMap`, `CopyOnWriteArrayList`?

* `ConcurrentHashMap`: High-performance, thread-safe hash map.
* `CopyOnWriteArrayList`: Safe for iteration when writes are rare.

---

## ⚙️ Intermediate

### 🔹 Difference between `synchronizedMap` and `ConcurrentHashMap`?

* `synchronizedMap` locks entire map
* `ConcurrentHashMap` uses segment or bucket-based locking

### 🔹 When to use `CountDownLatch` and `CyclicBarrier`?

* `CountDownLatch`: One-time latch, used for task completion wait.
* `CyclicBarrier`: Reusable barrier for task synchronization.

---

## 🚀 Advanced

### 🔹 Difference between `Semaphore`, `CountDownLatch`, and `Phaser`

* `Semaphore`: Limit concurrent access to a resource
* `CountDownLatch`: Wait for a set of events
* `Phaser`: Advanced, reusable barrier with dynamic registration

### 🔹 Thread-safe collections: internal mechanisms

* `ConcurrentHashMap`: Bucket locking (pre-Java 8: Segment locking)
* `CopyOnWriteArrayList`: Copy on mutation, great for read-heavy ops

---

## 💡 Real-life/Application-Based

### 🔹 Multithreaded downloader using `CountDownLatch`

```java
CountDownLatch latch = new CountDownLatch(3);
for (int i = 0; i < 3; i++) {
    final int index = i;
    new Thread(() -> {
        // download file part[index]
        latch.countDown();
    }).start();
}
latch.await(); // Wait until all downloads complete
```

### 🔹 Traffic control system using `Semaphore`

```java
Semaphore semaphore = new Semaphore(2); // Only 2 cars allowed at a time
for (int i = 0; i < 5; i++) {
    new Thread(() -> {
        try {
            semaphore.acquire();
            System.out.println("Car entered bridge");
            Thread.sleep(1000);
        } catch (InterruptedException e) {}
        finally {
            System.out.println("Car exited bridge");
```
----

# Java Concurrency: Advanced Topics

---

## 💥 8. Deadlock, Livelock, Starvation

### ✅ Basic

#### 🔹 What is deadlock?

Deadlock is a condition where two or more threads are blocked forever, each waiting on the other to release a lock.

#### 🔹 Conditions for deadlock?

Deadlock occurs when the following four conditions hold true:

1. **Mutual Exclusion** – Only one thread can hold a lock at a time.
2. **Hold and Wait** – Thread holds a lock and waits for another.
3. **No Preemption** – Locks can’t be forcibly taken.
4. **Circular Wait** – Threads form a circular chain holding and waiting for resources.

---

### ⚙️ Intermediate

#### 🔹 What is starvation?

Starvation happens when a thread waits indefinitely to acquire a lock because other threads are continuously granted access before it.

#### 🔹 What is livelock?

Livelock is similar to deadlock, but threads keep changing state in response to each other without making any progress.

---

### 🚀 Advanced

#### 🔹 How to detect and resolve deadlocks?

* **Thread Dump Analysis** using tools like VisualVM or `jstack`
* **Using `ThreadMXBean`** to detect cycles in the lock graph
* **Resolution**:

    * Kill or restart affected threads
    * Introduce timeout/tryLock

#### 🔹 Deadlock prevention strategies:

* **Lock Ordering** – Always acquire locks in a global predefined order
* **Using tryLock** – Acquire lock with timeout to avoid blocking indefinitely

```java
if (lock1.tryLock(100, TimeUnit.MILLISECONDS)) {
    try {
        if (lock2.tryLock(100, TimeUnit.MILLISECONDS)) {
            try {
                // business logic
            } finally {
                lock2.unlock();
            }
        }
    } finally {
        lock1.unlock();
    }
}
```

---

### 💡 Real-life/Application-Based

#### 🔹 Simulate deadlock using threads

```java
public class DeadlockDemo {
    static final Object Lock1 = new Object();
    static final Object Lock2 = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (Lock1) {
                try { Thread.sleep(100); } catch (InterruptedException ignored) {}
                synchronized (Lock2) {
                    System.out.println("Thread 1 done");
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (Lock2) {
                try { Thread.sleep(100); } catch (InterruptedException ignored) {}
                synchronized (Lock1) {
                    System.out.println("Thread 2 done");
                }
            }
        }).start();
    }
}
```

#### 🔹 Deadlock avoidance in database locking

Use **lock ordering**, **short transactions**, and **optimistic locking**.

---

## 🌊 9. Volatile, Atomic, and Visibility

### ✅ Basic

#### 🔹 What is `volatile` keyword in Java?

`volatile` ensures that the value of a variable is always read from and written to main memory (no caching).

#### 🔹 Difference between `volatile` and `synchronized`

| Feature    | `volatile`     | `synchronized` |
| ---------- | -------------- | -------------- |
| Visibility | ✅ Yes          | ✅ Yes          |
| Atomicity  | ❌ No           | ✅ Yes          |
| Blocking   | ❌ Non-blocking | ✅ Blocking     |

---

### ⚙️ Intermediate

#### 🔹 Use of `AtomicInteger`, `AtomicBoolean`

They provide lock-free thread-safe operations using CAS (Compare-And-Swap).

```java
AtomicInteger counter = new AtomicInteger();
counter.incrementAndGet();
```

---

### 🚀 Advanced

#### 🔹 Memory visibility issues and `happens-before` relationship

The Java Memory Model defines visibility rules:

* Actions before a `lock` or `volatile write` **happen-before** actions after `unlock` or `volatile read`.

#### 🔹 Compare-and-swap (CAS) mechanism

* **CAS** is an atomic operation: checks expected value and updates only if match.
* Used internally in `AtomicXXX` classes.

```java
int expected = 5;
int newVal = 6;
boolean success = atomicRef.compareAndSet(expected, newVal);
```

---

### 💡 Real-life/Application-Based

#### 🔹 Use `volatile` in configuration reload scenarios

```java
volatile boolean configEnabled = false;
// another thread can set configEnabled = true to reload config
```

#### 🔹 Counters with `AtomicInteger`

```java
AtomicInteger pageViews = new AtomicInteger(0);
pageViews.incrementAndGet();
```

---

## 💫 10. CompletableFuture & Asynchronous Programming (Java 8+)

### ✅ Basic

#### 🔹 What is `CompletableFuture`?

It represents a future result of an asynchronous computation.

```java
CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");
```

---

### ⚙️ Intermediate

#### 🔹 Difference between `thenApply`, `thenAccept`, `thenRun`

| Method       | Takes Input | Returns Output | Use Case                   |
| ------------ | ----------- | -------------- | -------------------------- |
| `thenApply`  | ✅           | ✅              | Transform value            |
| `thenAccept` | ✅           | ❌              | Consume result (no return) |
| `thenRun`    | ❌           | ❌              | Just run next task         |

#### 🔹 How to combine multiple futures?

Use `thenCombine`, `thenCompose`, or `allOf`.

```java
CompletableFuture<String> f1 = ...;
CompletableFuture<String> f2 = ...;
CompletableFuture<String> combined = f1.thenCombine(f2, (r1, r2) -> r1 + r2);
```

---

### 🚀 Advanced

#### 🔹 Exception handling in `CompletableFuture`

```java
future.exceptionally(ex -> {
    System.out.println("Error: " + ex);
    return "default";
});
```

#### 🔹 Chaining and parallel composition

You can chain using `thenApply`, `thenCompose`, and run in parallel using `allOf`.

```java
CompletableFuture.allOf(f1, f2, f3).join();
```

---

### 💡 Real-life/Application-Based

#### 🔹 Call multiple APIs in parallel and merge results

```java
CompletableFuture<String> a = getAPI("url1");
CompletableFuture<String> b = getAPI("url2");
CompletableFuture<String> result = a.thenCombine(b, (r1, r2) -> r1 + r2);
```

#### 🔹 Non-blocking DB access using `CompletableFuture`

Use async drivers (R2DBC, Vert.x) and wrap logic inside `supplyAsync()`.

---

## 📦 11. ForkJoinPool and Parallel Streams

### ✅ Basic

#### 🔹 What is ForkJoinPool?

A thread pool designed for **divide-and-conquer** tasks.

#### 🔹 Role of `RecursiveTask` and `RecursiveAction`

* `RecursiveTask<T>` returns a result.
* `RecursiveAction` returns nothing.

```java
class SumTask extends RecursiveTask<Integer> { ... }
```

---

### ⚙️ Intermediate

#### 🔹 When should you use ForkJoinPool?

When tasks can be broken into smaller subtasks and executed in parallel (e.g., matrix multiplication).

---

### 🚀 Advanced

#### 🔹 ForkJoin vs ThreadPoolExecutor

* **ForkJoinPool**: better for recursive, fine-grained parallelism
* **ThreadPoolExecutor**: general purpose, task queue-based

#### 🔹 Pitfalls using parallel streams

* Unpredictable performance on small tasks
* Side-effects (like modifying shared variables) lead to bugs

---

### 💡 Real-life/Application-Based

#### 🔹 Parallel matrix multiplication

Split rows/columns and process in parallel using ForkJoin.

#### 🔹 Web scraping using ForkJoinPool

Submit scraping tasks recursively per domain or URL group.
