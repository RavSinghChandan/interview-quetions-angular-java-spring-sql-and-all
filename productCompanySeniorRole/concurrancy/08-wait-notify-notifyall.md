
# STEP 8: `wait()`, `notify()`, `notifyAll()`

---

## Why this step exists

Synchronization alone is not enough.

Threads must:
- Pause safely when conditions aren't met
- Signal when conditions become true
- Resume correctly when notified

`wait()` and `notify()` enable **condition-based coordination**.

---

## Core understanding

`wait()` and `notify()` are methods on `Object`.

They work together:
- `wait()` – thread releases lock and waits
- `notify()` – wakes one waiting thread
- `notifyAll()` – wakes all waiting threads

> These methods **must** be called from a `synchronized` block.

---

## The basic pattern

```java
synchronized (lock) {
    while (!condition) {
        lock.wait(); // Release lock, wait
    }
    // Condition is true, proceed
}
```

Another thread:
```java
synchronized (lock) {
    condition = true;
    lock.notify(); // Wake one waiting thread
}
```

This is the **guarded block** pattern.

---

## Why `wait()` releases the lock

If `wait()` didn't release the lock:
- Waiting thread holds the lock
- Other threads cannot change the condition
- Deadlock occurs

`wait()` releases the lock **before** waiting,  
and reacquires it **after** being notified.

---

## Code: producer-consumer (classic example)

```java
class Buffer {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int capacity = 10;
    private final Object lock = new Object();

    void produce(int value) throws InterruptedException {
        synchronized (lock) {
            while (queue.size() == capacity) {
                lock.wait(); // Wait until space available
            }
            queue.offer(value);
            lock.notifyAll(); // Notify consumers
        }
    }

    int consume() throws InterruptedException {
        synchronized (lock) {
            while (queue.isEmpty()) {
                lock.wait(); // Wait until data available
            }
            int value = queue.poll();
            lock.notifyAll(); // Notify producers
            return value;
        }
    }
}
```

This is the **bounded buffer** problem.

---

## Why `while`, not `if`

```java
// WRONG
if (!condition) {
    lock.wait();
}

// CORRECT
while (!condition) {
    lock.wait();
}
```

Why?

**Spurious wakeups** can occur:
- Thread wakes up without being notified
- Condition may still be false
- `while` rechecks the condition

Always use `while` with `wait()`.

---

## `notify()` vs `notifyAll()`

**`notify()`:**
- Wakes **one** waiting thread
- Which thread is unspecified
- Faster, but may miss notifications

**`notifyAll()`:**
- Wakes **all** waiting threads
- All threads recheck condition
- Slower, but safer

> Use `notifyAll()` unless you're certain only one thread should proceed.

---

## Code: missed notification

```java
// Thread 1
synchronized (lock) {
    while (!ready) {
        lock.wait();
    }
}

// Thread 2
synchronized (lock) {
    ready = true;
    lock.notify(); // Only wakes one thread
}
```

If multiple threads are waiting,  
only one is notified.

Others may wait forever.

---

## The `InterruptedException`

```java
try {
    lock.wait();
} catch (InterruptedException e) {
    Thread.currentThread().interrupt();
    // Handle interruption
}
```

`wait()` can be interrupted.

Always handle `InterruptedException` properly.

---

## Code: timeout with `wait()`

```java
synchronized (lock) {
    long timeout = 1000; // 1 second
    long endTime = System.currentTimeMillis() + timeout;

    while (!condition && System.currentTimeMillis() < endTime) {
        long remaining = endTime - System.currentTimeMillis();
        if (remaining > 0) {
            lock.wait(remaining);
        }
    }

    if (!condition) {
        // Timeout occurred
    }
}
```

`wait(timeout)` supports timeouts.

---

## Common mistake: calling `wait()` without lock

```java
// WRONG
lock.wait(); // IllegalMonitorStateException

// CORRECT
synchronized (lock) {
    lock.wait();
}
```

`wait()` must be called from a `synchronized` block  
that holds the lock on the same object.

---

## Common mistake: forgetting to notify

```java
synchronized (lock) {
    condition = true;
    // Forgot to call notify()
}
```

Waiting threads never wake up.

Always notify after changing conditions.

---

## The lost wakeup problem

```java
// Thread 1
synchronized (lock) {
    if (!ready) {
        lock.wait(); // May wait forever
    }
}

// Thread 2
ready = true; // Not synchronized!
// Thread 1 may never see this
```

If condition changes outside `synchronized`,  
notification may be lost.

Always modify conditions inside `synchronized` blocks.

---

## Code: proper wait-notify pattern

```java
class Condition {
    private boolean flag = false;
    private final Object lock = new Object();

    void waitForFlag() throws InterruptedException {
        synchronized (lock) {
            while (!flag) {
                lock.wait();
            }
        }
    }

    void setFlag() {
        synchronized (lock) {
            flag = true;
            lock.notifyAll();
        }
    }
}
```

This pattern is correct and safe.

---

## Mental shortcut (easy recall)

- `wait()` releases lock, waits, reacquires lock
- `notify()` wakes one thread
- `notifyAll()` wakes all threads
- Always use `while` with `wait()`
- Always call from `synchronized` block
- Always notify after changing condition

---

## Senior instinct

When using `wait()` and `notify()`:

1. Identify the condition
2. Use `while`, not `if`
3. Modify condition inside `synchronized`
4. Call `notifyAll()` after changing condition
5. Handle `InterruptedException`

This pattern prevents most bugs.

---

## Interview signal

> "`wait()` releases the lock and waits until notified. It must be called from a `synchronized` block. Always use `while` to recheck conditions due to spurious wakeups. `notifyAll()` is safer than `notify()` when multiple threads may be waiting."

This shows production experience.

---

## Quick recall

- `wait()` releases lock and waits
- `notify()` wakes one thread
- `notifyAll()` wakes all threads
- Use `while`, not `if`
- Must be called from `synchronized` block
- Always notify after changing condition

---

## Where this leads next

Locks fix correctness but hurt performance.

The next question is:
> Do I really need mutual exclusion, or just visibility?

That leads to:
**`volatile` and atomic variables — performance without blocking.**

```
```

