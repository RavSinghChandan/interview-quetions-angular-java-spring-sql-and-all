
# STEP 5: `volatile` & Atomic Variables

---

## Why this step exists

After using `synchronized`, things become correct — but slow.

Blocking threads everywhere fixes bugs,  
but it doesn’t scale.

The next natural question is:

> Do I really need a lock here, or do I just need visibility?

That question leads to `volatile` and atomic variables.

---

## The real problem being solved

Sometimes:
- Only **one thread writes**
- Many threads **only read**
- No compound updates are needed

Using locks here is overkill.

What’s missing is **visibility**, not mutual exclusion.

---

## `volatile`: what it actually guarantees

`volatile` guarantees:
- Visibility
- Ordering

It does **not** guarantee:
- Atomicity

This is the most misunderstood part.

---

## Code: visibility without locking

```java
class Flag {
    volatile boolean ready = false;
}
````

Thread A:

```java
flag.ready = true;
```

Thread B:

```java
while (!flag.ready) {
    // will eventually exit
}
```

Why this works:

* Writes to `ready` are flushed to main memory
* Reads always see the latest value
* Reordering around `ready` is restricted

No locking.
No blocking.

---

## What `volatile` cannot do

```java
volatile int count = 0;

void increment() {
    count++; // still broken
}
```

`count++` is still:

* read
* modify
* write

`volatile` does not make this atomic.

Visibility ≠ correctness.

---

## When `volatile` is enough

Use `volatile` when:

* One thread writes
* Others only read
* The operation is a single write
* No invariants depend on multiple fields

Common examples:

* Feature flags
* Shutdown signals
* Configuration refresh markers

---

## Atomic variables: correctness without locks

Atomic classes give:

* Atomicity
* Visibility
* Ordering

Without blocking.

---

## Code: atomic increment

```java
AtomicInteger count = new AtomicInteger(0);

void increment() {
    count.incrementAndGet();
}
```

Internally:

* Uses CAS (Compare-And-Swap)
* Retries on contention
* Avoids thread blocking

Correct and scalable.

---

## CAS mental model (easy recall)

1. Read current value
2. Compute new value
3. Update **only if unchanged**
4. Retry if someone else changed it

Optimistic, not blocking.

---

## The ABA problem (know this as a senior)

CAS can fail silently if:

* Value changes A → B → A
* CAS sees A again and succeeds

This matters in:

* Lock-free algorithms
* High-performance systems

AtomicStampedReference exists because of this.

---

## Performance intuition

* `synchronized` → blocking, safe, slower
* `volatile` → visibility only, very fast
* Atomic variables → lock-free, scalable

There is no “best” option.
Only the **right one**.

---

## Senior instinct

Before choosing:

* Do I need mutual exclusion?
* Or just visibility?
* Or atomic updates without blocking?

The wrong primitive is worse than no primitive.

---

## Interview signal

> “`volatile` guarantees visibility and ordering, not atomicity. Atomic classes provide lock-free atomic operations using CAS.”

That sentence is usually enough.

---

## Quick recall

* `volatile` → visibility + ordering
* Atomic classes → atomicity without locks
* `count++` is never atomic
* CAS trades blocking for retries

---

## Where this leads next

As systems grow, managing threads manually becomes fragile.

The next step is:
**Executors and thread pools — controlling concurrency at scale.**

```
```
