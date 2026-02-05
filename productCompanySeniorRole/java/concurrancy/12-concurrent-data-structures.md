````md
# STEP 12: Concurrent Data Structures

---

## Why this step exists

Up to now, `synchronized` solved correctness problems — but only at a basic level.

In real systems:
- Not all reads need to block writes
- Not all threads should wait the same way
- Coordination is more complex than mutual exclusion

This step exists because **real concurrency problems are about coordination, not just locking**.

---

## The limitation of `synchronized`

`synchronized` gives:
- Mutual exclusion
- Visibility
- Ordering

But it lacks:
- Lock polling
- Timed waits
- Fairness control
- Multiple condition queues

In large systems, these limitations start to hurt.

---

## Enter explicit locks

Java provides explicit locks to express **intent**, not just safety.

The core idea:
> Use stronger tools only when basic locking becomes insufficient.

---

## Lock vs synchronized (how seniors think)

`synchronized`:
- Simple
- Implicit
- JVM-managed

`Lock`:
- Explicit
- Flexible
- Programmer-controlled

You don’t replace `synchronized` blindly.  
You replace it **when intent cannot be expressed otherwise**.

---

## ReentrantLock (most common)

```java
Lock lock = new ReentrantLock();

lock.lock();
try {
    // critical section
} finally {
    lock.unlock();
}
````

What this adds:

* Explicit lock management
* Ability to try or time out
* Optional fairness

This matters when threads must not block indefinitely.

---

## Fair vs Unfair locks (important nuance)

```java
Lock fairLock = new ReentrantLock(true);
```

* Fair lock → first-come-first-served
* Unfair lock → higher throughput, possible starvation

Most systems prefer **unfair locks** for performance.
Fairness is a policy decision, not a default.

---

## ReadWriteLock (separating intent)

When reads dominate writes, blocking everything is wasteful.

```java
ReadWriteLock rwLock = new ReentrantReadWriteLock();

rwLock.readLock().lock();
try {
    // read-only work
} finally {
    rwLock.readLock().unlock();
}
```

This allows:

* Multiple readers simultaneously
* Writers get exclusive access

Used heavily in:

* Caches
* Configuration stores
* Reference data systems

---

## StampedLock (performance-first)

StampedLock introduces:

* Optimistic reads
* Versioned locking

```java
long stamp = lock.tryOptimisticRead();
int value = data;

if (!lock.validate(stamp)) {
    stamp = lock.readLock();
    try {
        value = data;
    } finally {
        lock.unlockRead(stamp);
    }
}
```

This reduces contention in read-heavy systems,
but increases complexity.

Used when:

* Reads vastly outnumber writes
* Latency matters

---

## Condition variables (fine-grained coordination)

`Condition` allows multiple wait-sets per lock.

```java
Condition notEmpty = lock.newCondition();
Condition notFull = lock.newCondition();
```

This avoids the bluntness of `wait/notifyAll`.

Used in:

* Bounded queues
* Resource pools
* Stateful workflows

---

## Synchronizers (coordination tools)

These are not locks — they coordinate threads.

### CountDownLatch

* One-time signal
* Threads wait until count reaches zero

### CyclicBarrier

* Threads wait for each other
* Reusable

### Semaphore

* Limits concurrent access
* Controls permits, not ownership

### Phaser

* Dynamic barrier
* Multi-phase workflows

These solve problems locks cannot express cleanly.

---

## Senior instinct

Locks protect **state**.
Synchronizers coordinate **progress**.

Using the wrong tool makes code harder than necessary.

---

## Interview signal

> “Locks protect critical sections, while synchronizers coordinate thread progress. Choosing between them depends on whether the problem is state protection or workflow coordination.”

That answer shows design maturity.

---

## Quick recall

* ReentrantLock → flexibility
* ReadWriteLock → read-heavy systems
* StampedLock → optimistic performance
* Condition → fine-grained waiting
* Synchronizers → coordination, not exclusion

---

## Where this leads next

Once locking and coordination are clear,
data structures themselves must become concurrency-safe.

That brings us to:
**Concurrent Collections.**

```
```
