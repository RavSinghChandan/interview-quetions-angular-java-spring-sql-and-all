````md
# STEP 10: Executors & Thread Pools

---

## Why this step exists

After fixing race conditions with `synchronized`, things become correct — but heavy.

Blocking threads everywhere works,  
but it doesn’t scale.

At this point, the real question appears:

Do I need **mutual exclusion**, or do I only need **visibility**?

That distinction leads to `volatile` and atomic variables.

---

## What `volatile` actually gives

`volatile` guarantees:
- **Visibility** (changes are seen by other threads)
- **Ordering** (certain reordering is prevented)

It does **not** guarantee:
- Atomicity

This single misunderstanding causes many bugs.

---

## Code: visibility without locking

```java
class Flag {
    volatile boolean ready = false;
}
````

Writer thread:

```java
flag.ready = true;
```

Reader thread:

```java
while (!flag.ready) {
    // eventually exits
}
```

Why this works:

* Writes go to main memory
* Reads always see the latest value
* No blocking involved

---

## What `volatile` cannot fix

```java
volatile int count = 0;

void increment() {
    count++; // still broken
}
```

`count++` is:

* read
* modify
* write

`volatile` does not make compound operations atomic.

Visibility alone is not correctness.

---

## When `volatile` is the right choice

Use `volatile` when:

* One thread writes
* Others only read
* The operation is a single write
* No invariants span multiple fields

Typical examples:

* Shutdown flags
* Feature toggles
* Configuration refresh markers

---

## Atomic variables: correctness without blocking

Atomic classes provide:

* Atomicity
* Visibility
* Ordering

Without using locks.

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
* Retries instead of blocking
* Scales better under contention

---

## CAS intuition (easy memory hook)

1. Read current value
2. Compute new value
3. Update only if unchanged
4. Retry if someone else modified it

Optimistic, not blocking.

---

## The ABA problem (know this)

CAS can fail when:

* Value changes A → B → A
* CAS sees A again and succeeds incorrectly

This matters in lock-free algorithms.
That’s why `AtomicStampedReference` exists.

---

## Performance mindset

* `synchronized` → blocking, safest
* `volatile` → visibility only, fastest
* Atomic variables → lock-free, scalable

No primitive is “best”.
Only the **right one for the problem**.

---

## Senior takeaway

Before choosing a tool, ask:

* Do I need exclusivity?
* Or only visibility?
* Or atomic updates without blocking?

Concurrency bugs come from choosing the wrong guarantee.

---

## Where this leads next

As systems grow, managing threads manually becomes fragile.

The next step is:
**Executors and thread pools — controlling concurrency at scale.**

```
```
