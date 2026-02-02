````md
# STEP 6: `synchronized` & Intrinsic Locks

---

## Why this step exists

After identifying race conditions and critical sections,  
the first tool Java gives you is `synchronized`.

It's simple to use.  
It's easy to misuse.

Understanding `synchronized` deeply prevents:
- Performance bottlenecks
- Deadlocks
- False sense of security

---

## Core understanding

`synchronized` provides:
- **Mutual exclusion** – only one thread executes the block
- **Visibility** – changes are visible to other threads
- **Ordering** – happens-before relationships

All three guarantees come from one keyword.

---

## The two forms

**Method synchronization:**
```java
synchronized void method() {
    // Critical section
}
```

**Block synchronization:**
```java
void method() {
    synchronized (this) {
        // Critical section
    }
}
```

Both use the same mechanism: **intrinsic locks**.

---

## What is an intrinsic lock?

Every Java object has an **intrinsic lock** (monitor lock).

When a thread enters a `synchronized` block:
- It acquires the lock
- Other threads block until the lock is released
- Lock is released when the block exits

> The lock is on the **object**, not the code.

---

## Code: method synchronization

```java
class Counter {
    private int count = 0;

    synchronized void increment() {
        count++;
    }
}
```

What happens:
- Thread acquires lock on `this`
- Executes `count++`
- Releases lock
- Other threads can now enter

This is **object-level locking**.

---

## Code: block synchronization

```java
class Counter {
    private int count = 0;
    private final Object lock = new Object();

    void increment() {
        synchronized (lock) {
            count++;
        }
    }
}
```

Why use a separate lock object?
- Finer-grained control
- Avoids locking the entire object
- Reduces contention

---

## Class-level locking

```java
class Counter {
    private static int count = 0;

    static synchronized void increment() {
        count++;
    }
}
```

Static `synchronized` locks the **class object**, not the instance.

All instances share the same lock.

---

## Reentrancy (critical to understand)

```java
class Example {
    synchronized void method1() {
        method2(); // Same thread, same lock
    }

    synchronized void method2() {
        // This works because locks are reentrant
    }
}
```

**Reentrant** means:
- A thread can acquire the same lock multiple times
- Lock count is tracked
- Lock is released when count reaches zero

This prevents deadlocks in recursive code.

---

## Visibility guarantee (often overlooked)

```java
class Flag {
    private boolean ready = false;

    synchronized void setReady() {
        ready = true;
    }

    synchronized boolean isReady() {
        return ready;
    }
}
```

Both methods must be `synchronized` for visibility.

If `isReady()` is not synchronized:
- Thread may see stale value
- No visibility guarantee

> Synchronization is about **both** exclusion and visibility.

---

## The performance cost

```java
synchronized void method() {
    // Only one thread at a time
}
```

Costs:
- Blocking other threads
- Context switching overhead
- Reduced parallelism

Benefits:
- Correctness
- Simplicity

Trade-off is unavoidable.

---

## Common mistake: synchronized on wrong object

```java
class Example {
    private final List<String> list = new ArrayList<>();

    void add(String item) {
        synchronized (list) {
            list.add(item);
        }
    }
}
```

Problem:
- `ArrayList` is not thread-safe
- Synchronizing on `list` doesn't help if other code accesses it unsynchronized

Better:
```java
private final List<String> list = Collections.synchronizedList(new ArrayList<>());
```

Or use a dedicated lock object.

---

## Code: protecting multiple fields

```java
class Account {
    private int balance;
    private int transactionCount;
    private final Object lock = new Object();

    void transfer(int amount) {
        synchronized (lock) {
            balance += amount;
            transactionCount++;
        }
    }
}
```

All related fields must be protected by the same lock.

---

## Mental shortcut (easy recall)

- `synchronized` method → locks `this`
- `synchronized` block → locks specified object
- Static `synchronized` → locks class object
- Locks are reentrant
- Locks provide visibility

---

## Senior instinct

Before using `synchronized`, ask:

- What is the critical section?
- What object should I lock on?
- Do I need method-level or block-level synchronization?
- Are all related fields protected by the same lock?

Wrong synchronization is worse than no synchronization.

---

## Interview signal

> "`synchronized` provides mutual exclusion, visibility, and ordering guarantees through intrinsic locks. Every object has a monitor lock, and locks are reentrant to prevent deadlocks in recursive code."

This shows understanding, not memorization.

---

## Quick recall

- `synchronized` uses intrinsic locks (monitor locks)
- Locks are reentrant
- Method `synchronized` locks `this`
- Block `synchronized` locks specified object
- Synchronization provides exclusion, visibility, and ordering

---

## Where this leads next

Once locks exist, new failure modes appear:

- Threads waiting for locks that never release
- Circular dependencies between locks
- Threads that never make progress

That brings us to:
**Deadlock, livelock, and starvation — when synchronization breaks down.**

```
```

