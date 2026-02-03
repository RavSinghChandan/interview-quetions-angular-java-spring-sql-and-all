
# STEP 7: Deadlock, Livelock, Starvation

---

## Why this step exists

After using `synchronized`, code becomes correct — but fragile.

Locks introduce new failure modes:
- Threads waiting forever
- Threads consuming CPU but making no progress
- Some threads never getting a chance to run

These are not bugs in logic.  
They are bugs in **coordination**.

---

## Core understanding

Three coordination failures:

- **Deadlock** – threads waiting for each other forever
- **Livelock** – threads keep trying but make no progress
- **Starvation** – some threads never get resources

All three are production killers.

---

## Deadlock (the classic problem)

A deadlock happens when:
- Thread A holds lock X, needs lock Y
- Thread B holds lock Y, needs lock X
- Both wait forever

This is a **circular dependency**.

---

## Code: creating a deadlock

```java
class Account {
    private int balance;
    private final Object lock = new Object();

    void transfer(Account other, int amount) {
        synchronized (this.lock) {
            synchronized (other.lock) {
                this.balance -= amount;
                other.balance += amount;
            }
        }
    }
}

// Thread 1: account1.transfer(account2, 100)
// Thread 2: account2.transfer(account1, 100)
// DEADLOCK
```

Thread 1 holds `account1.lock`, waits for `account2.lock`.  
Thread 2 holds `account2.lock`, waits for `account1.lock`.

Both wait forever.

---

## Deadlock conditions (all must be true)

1. **Mutual exclusion** – locks are exclusive
2. **Hold and wait** – thread holds one lock, waits for another
3. **No preemption** – locks cannot be forcibly released
4. **Circular wait** – circular dependency exists

Break any one, and deadlock is impossible.

---

## Preventing deadlocks

**Strategy 1: Lock ordering**
```java
void transfer(Account other, int amount) {
    Account first = this.id < other.id ? this : other;
    Account second = this.id < other.id ? other : this;

    synchronized (first.lock) {
        synchronized (second.lock) {
            this.balance -= amount;
            other.balance += amount;
        }
    }
}
```

Always acquire locks in the same order.

---

## Preventing deadlocks (continued)

**Strategy 2: Timeout**
```java
if (lock1.tryLock(100, TimeUnit.MILLISECONDS)) {
    try {
        if (lock2.tryLock(100, TimeUnit.MILLISECONDS)) {
            try {
                // Critical section
            } finally {
                lock2.unlock();
            }
        }
    } finally {
        lock1.unlock();
    }
}
```

Timeout prevents indefinite waiting.

---

## Detecting deadlocks

```bash
jstack <pid>
```

Thread dump shows:
```
"Thread-1" waiting for lock <0x...> held by "Thread-2"
"Thread-2" waiting for lock <0x...> held by "Thread-1"
```

This is a deadlock.

---

## Livelock (less common, more subtle)

A livelock happens when:
- Threads keep trying to resolve a conflict
- But their actions prevent resolution
- CPU is consumed, but no progress is made

Livelock is like deadlock, but threads are **active**.

---

## Code: creating a livelock

```java
class Person {
    private boolean movingRight = true;

    void stepAside(Person other) {
        if (other.movingRight) {
            movingRight = false; // Move left
        } else {
            movingRight = true; // Move right
        }
        // Both keep changing direction
        // Never pass each other
    }
}
```

Both threads keep reacting to each other,  
but never make progress.

---

## Livelock vs Deadlock

- **Deadlock** – threads are blocked, waiting
- **Livelock** – threads are active, but stuck

Both result in no progress.  
Livelock wastes CPU.

---

## Starvation (the silent killer)

Starvation happens when:
- Some threads never get CPU time
- Or never acquire a lock
- System appears to work, but some requests never complete

This is harder to detect than deadlock.

---

## Code: causing starvation

```java
class Resource {
    private final Object lock = new Object();

    void use() {
        synchronized (lock) {
            // Long-running operation
            Thread.sleep(1000);
        }
    }
}

// Many threads calling use()
// Some threads may never get the lock
```

If one thread always gets the lock first,  
others may starve.

---

## Fairness (preventing starvation)

```java
ReentrantLock lock = new ReentrantLock(true); // Fair lock
```

Fair locks ensure:
- Threads acquire locks in order
- No thread starves
- But with performance cost

---

## Real-world example: serviceBasedMNCLevel.database deadlock

```sql
Transaction 1: UPDATE account1 SET balance = balance - 100;
Transaction 2: UPDATE account2 SET balance = balance - 100;
Transaction 1: UPDATE account2 SET balance = balance + 100;
Transaction 2: UPDATE account1 SET balance = balance + 100;
```

If transactions interleave, deadlock occurs.

Databases detect and abort one transaction.

---

## Mental shortcut (easy recall)

- **Deadlock** → circular wait, threads blocked
- **Livelock** → threads active but stuck
- **Starvation** → some threads never progress
- Lock ordering prevents deadlock
- Fair locks prevent starvation

---

## Senior instinct

When designing concurrent systems:

1. Identify all locks
2. Establish lock ordering
3. Use timeouts
4. Monitor for deadlocks
5. Consider fairness

Prevention is easier than debugging.

---

## Interview signal

> "Deadlock requires four conditions: mutual exclusion, hold and wait, no preemption, and circular wait. Breaking any one prevents deadlock. Livelock is like deadlock but threads are active. Starvation occurs when some threads never get resources."

This shows systematic thinking.

---

## Quick recall

- Deadlock = circular dependency, threads blocked
- Livelock = threads active but making no progress
- Starvation = some threads never get resources
- Lock ordering prevents deadlock
- Fair locks prevent starvation

---

## Where this leads next

Once threads can coordinate safely,  
they need to **communicate** with each other.

That brings us to:
**`wait()`, `notify()`, and `notifyAll()` — inter-thread communication.**

```
```

