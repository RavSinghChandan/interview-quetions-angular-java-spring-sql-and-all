````md
# STEP 3: Thread Lifecycle & States

---

## Why this step exists

Threads are not just "running" or "stopped".

They move through **states** that explain:
- Why threads appear stuck
- Why CPU is idle but requests are slow
- Why debugging concurrent code is hard

Understanding thread states is the foundation of debugging production issues.

---

## Core understanding

A thread moves through these states:

- **NEW** – created but not started
- **RUNNABLE** – ready to run, may be executing or waiting for CPU
- **BLOCKED** – waiting for a monitor lock
- **WAITING** – waiting indefinitely for another thread
- **TIMED_WAITING** – waiting with a timeout
- **TERMINATED** – finished execution

> Thread states explain **why** threads aren't making progress, not just **that** they aren't.

---

## The state transitions (visualize this)

```
NEW → start() → RUNNABLE → run() completes → TERMINATED
                ↓
            (wait for lock)
                ↓
            BLOCKED
                ↓
            (lock acquired)
                ↓
            RUNNABLE
                ↓
            (wait() / join() / sleep())
                ↓
            WAITING / TIMED_WAITING
                ↓
            (notify() / timeout)
                ↓
            RUNNABLE
```

This is not just theory.  
This is what thread dumps show.

---

## NEW state

```java
Thread t = new Thread(() -> System.out.println("Running"));
// t is in NEW state
```

Characteristics:
- Thread exists but hasn't started
- `start()` must be called
- Calling `start()` twice throws `IllegalThreadStateException`

---

## RUNNABLE state

```java
Thread t = new Thread(() -> {
    // This code runs in RUNNABLE state
    System.out.println("Executing");
});
t.start(); // Moves to RUNNABLE
```

RUNNABLE means:
- Thread is eligible to run
- May be executing on CPU
- May be waiting for CPU time slice

> RUNNABLE does not mean "currently executing".  
> It means "ready to execute".

This is why thread dumps show many threads in RUNNABLE during high load.

---

## BLOCKED state

```java
class Example {
    synchronized void method() {
        // Only one thread can be here
    }
}

Thread t1 = new Thread(() -> example.method());
Thread t2 = new Thread(() -> example.method());

t1.start();
t2.start();
// One thread is RUNNABLE, one is BLOCKED
```

BLOCKED happens when:
- Thread tries to enter a `synchronized` block
- Another thread holds the lock
- Thread waits for the lock to be released

This is **not** the same as WAITING.

---

## WAITING state

```java
synchronized (lock) {
    lock.wait(); // Thread moves to WAITING
}
```

WAITING happens when:
- `wait()` is called
- `join()` is called (without timeout)
- `LockSupport.park()` is called

Thread stays in WAITING until:
- `notify()` or `notifyAll()` is called
- Thread is interrupted

---

## TIMED_WAITING state

```java
Thread.sleep(1000); // TIMED_WAITING

synchronized (lock) {
    lock.wait(1000); // TIMED_WAITING
}

thread.join(1000); // TIMED_WAITING
```

TIMED_WAITING is like WAITING, but with a timeout.

Thread automatically moves to RUNNABLE when:
- Timeout expires
- Notification arrives
- Thread is interrupted

---

## TERMINATED state

```java
Thread t = new Thread(() -> {
    // Code runs
});
t.start();
// ... later ...
// t is TERMINATED
```

Once a thread finishes `run()`, it cannot be restarted.

---

## Why states matter in production

Thread dumps show states, not code.

Seeing many threads in:
- **BLOCKED** → lock contention
- **WAITING** → coordination issues
- **RUNNABLE** → CPU saturation or context switching overhead

This is how you diagnose performance problems.

---

## Code: observing thread states

```java
Thread t = new Thread(() -> {
    try {
        Thread.sleep(1000);
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
});

System.out.println(t.getState()); // NEW

t.start();
System.out.println(t.getState()); // RUNNABLE or TIMED_WAITING

Thread.sleep(100);
System.out.println(t.getState()); // TIMED_WAITING

t.join();
System.out.println(t.getState()); // TERMINATED
```

State transitions are observable, not theoretical.

---

## The RUNNABLE confusion (important)

Many developers think RUNNABLE means "running".

It doesn't.

RUNNABLE means:
- Thread is ready to execute
- OS scheduler decides when it actually runs
- Thread may be waiting for CPU time

On a single-core machine, only one thread executes at a time,  
but many threads can be RUNNABLE.

---

## Mental shortcut (easy recall)

- **NEW** → "Created, not started"
- **RUNNABLE** → "Ready to run"
- **BLOCKED** → "Waiting for a lock"
- **WAITING** → "Waiting indefinitely"
- **TIMED_WAITING** → "Waiting with timeout"
- **TERMINATED** → "Done"

If a thread isn't making progress, check its state.

---

## Senior instinct

When debugging production issues:

1. Take a thread dump
2. Count threads by state
3. Identify the bottleneck state
4. Fix the root cause

States are symptoms, not causes.

---

## Interview signal

> "Thread states explain why threads aren't making progress. BLOCKED indicates lock contention, WAITING indicates coordination issues, and many RUNNABLE threads may indicate CPU saturation."

This shows production debugging experience.

---

## Quick recall

- Thread states are observable via `getState()`
- RUNNABLE doesn't mean "currently executing"
- BLOCKED is about locks, WAITING is about coordination
- Thread dumps show states, which reveal bottlenecks

---

## Where this leads next

Once multiple threads run and share memory,  
they start interfering with each other.

That brings us to:
**Race conditions and critical sections — where correctness breaks first.**

```
```

