````md
# STEP 3: Race Conditions & Critical Sections

---

## Why this step exists

Once threads share memory, correctness becomes fragile.

Nothing crashes.  
Nothing looks wrong.  
But data slowly becomes incorrect.

This is where most production bugs are born — silently.

---

## The core problem

A **race condition** happens when:
- Multiple threads access shared data
- At least one thread modifies it
- The result depends on timing

The program is correct in isolation,  
but incorrect when execution interleaves.

---

## The smallest broken example

```java
class Counter {
    int count = 0;

    void increment() {
        count++;
    }
}
````

Looks harmless.
But `count++` is **not a single operation**.

It expands to:

1. Read `count`
2. Increment value
3. Write back

Two threads can interleave these steps and lose updates.

---

## What actually goes wrong

Thread A reads `count = 5`
Thread B reads `count = 5`
Thread A writes `6`
Thread B writes `6`

Expected: `7`
Actual: `6`

No exception.
No warning.
Just wrong state.

---

## Why this is dangerous in production

* Counters drift
* Balances mismatch
* Limits are bypassed
* Idempotency breaks

These bugs:

* Appear only under load
* Disappear in local testing
* Reappear randomly in production

---

## Critical Section (the key idea)

A **critical section** is a block of code that:

* Accesses shared mutable state
* Must be executed by only one thread at a time

Identifying critical sections is more important than writing locks.

---

## Code: protecting a critical section

```java
class Counter {
    private int count = 0;

    synchronized void increment() {
        count++;
    }
}
```

Now:

* Only one thread can execute `increment()` at a time
* Lost updates are prevented

Correctness is restored — but at a cost.

---

## The hidden trade-off

Synchronization gives:

* Safety
* Visibility guarantees
* Ordering guarantees

It also introduces:

* Blocking
* Reduced throughput
* Risk of contention

Concurrency is never free.

---

## Memory hook (easy to remember)

> Race conditions are not bugs in logic.
> They are bugs in **timing**.

If timing matters, correctness matters.

---

## Senior-level instinct

Before asking *how to synchronize*, ask:

* What data is shared?
* Who owns it?
* Can it be made immutable?
* Can writes be isolated?

Locks are a solution — not the first one.

---

## Interview signal

> “Race conditions happen when correctness depends on execution order. Identifying critical sections is more important than choosing synchronization primitives.”

This shows judgment, not memorization.

---

## Quick recall

* Race condition → timing-dependent bug
* Shared mutable state is the root cause
* Critical section → must be executed atomically
* Synchronization restores correctness but affects performance

---

## Where this leads next

Once critical sections are protected,
new problems appear:

* Blocking
* Ordering guarantees
* Memory visibility

That leads directly to **thread safety, atomicity, visibility, and ordering**.

```
```
