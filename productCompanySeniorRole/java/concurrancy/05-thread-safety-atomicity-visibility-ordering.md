
# STEP 5: Thread Safety, Atomicity, Visibility, Ordering

---

## Why this step exists

After race conditions are identified, the next mistake is assuming:

> “I used `synchronized`, so everything is safe.”

It isn’t.

Correct concurrent behavior depends on **four guarantees**, not one.  
Missing even one leads to bugs that are rare, silent, and painful.

---

## The four guarantees (think of them as a set)

- **Atomicity** – an operation happens as one indivisible unit  
- **Visibility** – changes made by one thread are seen by others  
- **Ordering** – operations happen in the expected sequence  
- **Thread Safety** – all of the above hold under concurrent access

Thread safety is not a feature.  
It is a **result**.

---

## Atomicity (what breaks first)

Atomicity means:
- An operation is **all-or-nothing**
- No other thread can observe a half-done state

Example that looks safe but isn’t:

```java
class BrokenCounter {
    int count = 0;

    void increment() {
        count++; // not atomic
        // This is actually:
        // 1. int temp = count;
        // 2. temp = temp + 1;
        // 3. count = temp;
        // Three separate operations!
    }
}
```

`count++` = read → modify → write
Three steps.
Multiple interleavings.

Atomicity is violated even if only one line exists.

---

## Visibility (what breaks next)

Visibility means:

* When one thread writes a value
* Other threads can **see the updated value**

Broken example:

```java
class Flag {
    boolean ready = false; // Not volatile, not synchronized
}

// Thread A (writer)
Flag flag = new Flag();
flag.ready = true; // Write may stay in CPU cache

// Thread B (reader)
while (!flag.ready) {
    // May loop forever!
    // Thread B may never see the update
}
```

Why?

* CPU caches
* Compiler reordering
* No visibility guarantee

The value changed.
The thread never sees it.

---

## Code: fixing visibility

```java
class Flag {
    volatile boolean ready = false; // Now visible across threads
}

// Or with synchronization
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

---

## Ordering (what surprises people)

Ordering means:

* Instructions appear to execute in a predictable order

The JVM is allowed to reorder instructions for performance.

Example:

```java
int x = 0;
boolean initialized = false;

void init() {
    x = 42;
    initialized = true;
}
```

Another thread might see:

* `initialized == true`
* but `x == 0`

Why?

* Reordering
* No happens-before relationship

The code is correct.
The assumption is not.

---

## Thread Safety (the combined effect)

A piece of code is **thread-safe** only if:

* Atomicity is guaranteed
* Visibility is guaranteed
* Ordering is guaranteed
* Under all interleavings

Using `synchronized` fixes all four — but not cheaply.

---

## Code: synchronized gives full guarantees

```java
class Counter {
    private int count = 0;

    synchronized void increment() {
        count++;
    }

    synchronized int get() {
        return count;
    }
}
```

What this guarantees:

* Atomicity
* Visibility
* Ordering
* Mutual exclusion

What it costs:

* Blocking
* Reduced scalability

Correctness is restored, performance is traded.

---

## Mental shortcut (easy recall)

* Atomicity → “Can this be interrupted?”
* Visibility → “Can others see my change?”
* Ordering → “Can steps be rearranged?”
* Thread safety → “All of the above, always”

If one is missing, safety is an illusion.

---

## Senior instinct

Before adding locks, ask:

* Do I really need mutation?
* Can this be immutable?
* Can ownership be restricted to one thread?

Concurrency is easier when **state is minimized**, not synchronized.

---

## Interview signal

> “Thread safety requires atomicity, visibility, and ordering guarantees. Synchronization provides all three, but at a performance cost.”

That answer shows understanding, not memorization.

---

## Quick recall

* Atomicity prevents partial updates
* Visibility prevents stale reads
* Ordering prevents surprising states
* Thread safety is the result, not the tool

---

## Where this leads next

Synchronization makes code correct,
but threads now need to **wait and coordinate**.

That brings us to:
**`synchronized` and intrinsic locks — the first tool for correctness.**

```
```
