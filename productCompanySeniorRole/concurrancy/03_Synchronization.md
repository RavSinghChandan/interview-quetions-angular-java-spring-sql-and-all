# Topic 15: synchronized Keyword (Method vs Block)

---

## Core Purpose

`synchronized` ensures **mutual exclusion + memory visibility** for critical sections of code.

**Golden Rule:**

> Use the smallest possible synchronized block around the smallest critical section.

---

## Mental Model

* Lock = Bathroom key
* Only one thread can enter at a time
* Others must wait until lock is released

---

## What synchronized Really Does

* Ensures **only one thread** executes a critical section at a time
* Establishes a **happens-before** relationship
* Flushes writes to main memory on lock release
* Reads fresh values from main memory on lock acquisition

---

## Method-Level Synchronization

```java
public synchronized void increment() {
    count++;
}
```

**Lock Used:** `this` (object-level lock)

---

## Block-Level Synchronization

```java
public void increment() {
    synchronized(this) {
        count++;
    }
}
```

**Why Block-Level Is Better**

* Smaller critical section
* Less contention
* Better performance

---

## Class-Level Synchronization

```java
public static synchronized void log() {
    // locked on Class object
}

synchronized(MyClass.class) {
    // same class-level lock
}
```

---

## Lock Scope

| Type               | Lock Object         |
| ------------------ | ------------------- |
| Instance method    | this                |
| Static method      | Class object        |
| Synchronized block | Any explicit object |

---

## Performance Implications

* Blocks competing threads
* Causes context switches
* Reduces parallelism
* Increases latency under load

---

## JVM-Level Reality

* Modern JVM uses **biased locking** and **lightweight locks**
* Heavy locks only under contention
* Lock inflation occurs during high contention

---

## When to Use synchronized

* Simple shared state
* Low contention
* Critical correctness logic
* Legacy systems

---

## When NOT to Use synchronized

* High-throughput systems
* Hot code paths
* Long-running I/O operations
* Fine-grained locking needs

---

## Real-World Mapping

* Updating account balance
* Writing audit logs
* Managing in-memory caches
* Rate limiter counters

---

## Common Mistakes

* Synchronizing entire methods unnecessarily
* Synchronizing on wrong objects
* Nesting synchronized blocks → deadlocks
* Holding lock during I/O

---

## Memory Model Insight

* Entering synchronized block → refresh from main memory
* Exiting synchronized block → flush to main memory

---

## Design Rules

* Prefer block-level over method-level
* Minimize lock duration
* Never synchronize on public objects
* Avoid nested locks

---

## Senior-Level Takeaway

> synchronized is simple and safe but heavy. Use it sparingly and precisely.

---

## Ultra-Crisp Recall

* synchronized = mutual exclusion + visibility
* Method lock = this
* Static lock = Class
* Block lock = explicit object
* Avoid long locks

---

## Interview Punchline

> synchronized ensures thread-safe access by providing mutual exclusion and memory visibility. Method-level locking locks the whole object, while block-level locking allows fine-grained control and better performance.

---
