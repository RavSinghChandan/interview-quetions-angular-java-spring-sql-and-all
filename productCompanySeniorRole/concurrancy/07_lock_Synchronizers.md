Perfect. Here is **Module 7**, written **exactly in the same structure, tone, and senior depth** as Module 5 and Module 6.

You can paste this directly into your notes.

---

# Module 7: Locks & Advanced Synchronization (Self Notes)

---

## Core Purpose

Locks provide **explicit, flexible, and fine-grained control** over concurrency beyond `synchronized`.

They solve problems where:

* Intrinsic locking is too rigid
* Fairness, timeout, or multiple conditions are required

**Golden Rule:**

> Use `synchronized` for simplicity.
> Use Locks when you need control.

---

## Mental Model

* Lock = Explicit mutual exclusion tool
* Thread = Actor requesting access
* Critical section = Protected resource
* Condition = Waiting room with rules
* Fairness = Queue discipline

---

## Topic 48: Why Locks Exist (Beyond `synchronized`)

Limitations of `synchronized`:

* No timeout
* No fairness
* Single wait-set
* No interruptible lock acquisition

Locks solve all of these.

---

## Topic 49: Lock Interface

Located in:

```
java.util.concurrent.locks
```

Core methods:

* `lock()`
* `unlock()`
* `tryLock()`
* `lockInterruptibly()`

```java
Lock lock = new ReentrantLock();
lock.lock();
try {
    process();
} finally {
    lock.unlock();
}
```

---

## Topic 50: ReentrantLock

* Most common Lock implementation
* Same thread can acquire lock multiple times
* Must manually release

Key features:

* Reentrancy
* Optional fairness
* Better diagnostics

---

## Reentrancy Explained

```java
lock.lock();
lock.lock(); // same thread, allowed
```

Lock keeps a **hold count** per thread.

---

## Topic 51: Fair vs Unfair Locks

### Fair Lock

```java
new ReentrantLock(true);
```

* FIFO ordering
* Prevents starvation
* Lower throughput

### Unfair Lock (default)

* Higher throughput
* Possible starvation

---

## Rule of Thumb

* High contention → unfair lock
* User-facing systems → fair lock
* Latency-sensitive → unfair

---

## Topic 52: tryLock()

```java
if (lock.tryLock()) {
    try {
        process();
    } finally {
        lock.unlock();
    }
}
```

Advantages:

* Avoids deadlock
* Supports fallback logic
* Useful in high-load systems

---

## Topic 53: lockInterruptibly()

Allows thread to:

* Wait for lock
* Respond to interruption

```java
lock.lockInterruptibly();
```

Useful for:

* Cancellable tasks
* Graceful shutdown

---

## Topic 54: Condition Interface

Condition = Advanced replacement for `wait()` / `notify()`

```java
Condition notEmpty = lock.newCondition();
```

Key methods:

* `await()`
* `signal()`
* `signalAll()`

---

## Condition vs wait/notify

| Feature             | Condition | wait/notify |
| ------------------- | --------- | ----------- |
| Multiple conditions | Yes       | No          |
| Explicit lock       | Yes       | Implicit    |
| Readability         | High      | Low         |

---

## Topic 55: Multiple Conditions Pattern

```java
Condition notFull;
Condition notEmpty;
```

Used in:

* Bounded buffers
* Producer–consumer systems

---

## Topic 56: ReadWriteLock

Separates:

* Read access
* Write access

```java
ReadWriteLock rw = new ReentrantReadWriteLock();
```

---

## Read Lock

* Multiple readers allowed
* No writers

## Write Lock

* Exclusive
* Blocks readers and writers

---

## When ReadWriteLock Helps

* Read-heavy systems
* Low write contention
* Cache-like data

---

## Topic 57: StampedLock (Java 8+)

Advanced lock with:

* Optimistic reads
* Lower overhead

```java
long stamp = lock.tryOptimisticRead();
```

---

## Optimistic Read

* No blocking
* Must validate
* Falls back to read lock if invalid

---

## Topic 58: Deadlocks & Locks

Deadlock causes:

* Circular wait
* Multiple locks
* Inconsistent ordering

Prevention:

* Lock ordering
* tryLock()
* Timeouts

---

## Topic 59: Performance Tradeoffs

### synchronized

* Simple
* JVM-optimized
* Limited control

### ReentrantLock

* More flexible
* Slightly more overhead

### ReadWriteLock

* Scales reads
* Costly writes

### StampedLock

* Fast reads
* Complex logic

---

## JVM Insight

* Locks use OS mutexes under contention
* Biased / lightweight locking optimizations exist
* Excessive locking increases context switching
* Lock contention increases GC pressure

---

## Real-World Usage

* In-memory caches
* Order matching engines
* Inventory systems
* Rate limiters
* Shared configuration stores

---

## Common Mistakes

* Forgetting unlock()
* Using fair locks blindly
* Overusing ReadWriteLock
* Mixing intrinsic and explicit locks

---

## Design Rules

* Prefer synchronized for simple cases
* Use ReentrantLock for control
* Use ReadWriteLock for read-heavy
* Use StampedLock only if necessary
* Always unlock in finally

---

## Senior-Level Takeaway

> Locks trade simplicity for control.
> Use them when concurrency requirements demand fairness, timeouts, or multiple conditions.

---

## Ultra-Crisp Recall

* synchronized = simple
* ReentrantLock = control
* tryLock avoids deadlock
* Condition > wait/notify
* Always unlock

---

## Interview Punchline

> Java Locks provide explicit mutual exclusion with advanced features like fairness, timeout, interruptibility, and multiple conditions. ReentrantLock is the most common, while ReadWriteLock and StampedLock optimize read-heavy workloads.

---

