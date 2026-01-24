# Topic 7: Creating Threads (Thread vs Runnable vs Callable)

---

## Core Purpose

Java provides three primary ways to define a task for concurrent execution:

* Extend `Thread`
* Implement `Runnable`
* Implement `Callable`

**Golden Rule:**

> Always separate *task* from *thread*. Prefer Runnable/Callable over extending Thread.

---

## Mental Model

* `Thread` = Worker
* `Runnable` / `Callable` = Job description
* `ExecutorService` = Factory managing workers

---

## Method 1: Extending Thread

```java
class MyThread extends Thread {
    public void run() {
        System.out.println("Task running");
    }
}

new MyThread().start();
```

**Characteristics**

* Tight coupling between task & thread
* Cannot extend another class
* Not reusable
* Poor design for large systems

---

## Method 2: Implementing Runnable

```java
class MyTask implements Runnable {
    public void run() {
        System.out.println("Task running");
    }
}

new Thread(new MyTask()).start();
```

**Characteristics**

* Task is independent of thread
* Supports inheritance
* Reusable
* Preferred for most use cases

---

## Method 3: Implementing Callable

```java
Callable<Integer> task = () -> 42;
ExecutorService es = Executors.newSingleThreadExecutor();
Future<Integer> result = es.submit(task);
System.out.println(result.get());
```

**Characteristics**

* Returns a value
* Can throw checked exceptions
* Used with ExecutorService
* Best for async computation

---

## Runnable vs Callable

| Feature                  | Runnable        | Callable                |
| ------------------------ | --------------- | ----------------------- |
| Return Value             | No              | Yes                     |
| Throws Checked Exception | No              | Yes                     |
| Used With Executors      | Yes             | Yes                     |
| Typical Use              | Fire-and-forget | Async result processing |

---

## Thread Lifecycle Trigger

* `start()` → creates new OS thread
* `run()` → just a normal method call

**Never call `run()` directly**

---

## Production Reality

* Never create raw threads in enterprise apps
* Always use `ExecutorService`
* Thread creation is expensive
* Thread pooling is mandatory for scalability

---

## Real-World Mapping

* Runnable → API request handler
* Callable → Fraud score calculator
* ExecutorService → Payment processing pool

---

## Performance & Scalability

* Raw threads → memory heavy (~1MB stack each)
* Too many threads → context switching overhead
* Thread pools → controlled concurrency

---

## Design Rules

* Prefer Runnable for simple tasks
* Prefer Callable when a result is needed
* Use Executors to manage lifecycle
* Avoid extending Thread

---

## Common Mistakes

* Calling `run()` instead of `start()`
* Creating threads per request
* Ignoring thread pool shutdown
* Blocking inside thread pool threads

---

## Memory & JVM Insight

* Each thread has its own stack
* Threads share heap memory
* Excess threads increase GC pressure

---

## Senior-Level Takeaway

> Separate task from execution. Always use thread pools. Treat threads as scarce resources.

---

## Ultra-Crisp Recall

* Runnable = no result
* Callable = returns value
* start() ≠ run()
* Use ExecutorService
* Never extend Thread

---

## Interview Punchline

> Runnable and Callable define tasks, Thread executes them. Callable supports return values and exceptions. In production, always use ExecutorService instead of creating raw threads.

---
