
# STEP 8: Concurrent Collections

---

## Why this step exists

After learning locks and synchronizers, a pattern becomes obvious:

Correctness is hard.  
Correctness everywhere is harder.

If every access to a data structure requires manual locking, systems become:
- Fragile
- Slow
- Difficult to reason about

This step exists because **data structures themselves must understand concurrency**.

---

## The core shift

Instead of asking:
“How do I lock around this collection?”

We ask:
“Can the collection manage concurrency for me?”

Concurrent collections encode **battle-tested concurrency decisions** so application code stays simple.

---

## Why normal collections fail

```java
Map<String, Integer> map = new HashMap<>();

map.put("a", 1);
map.get("a");
````

`HashMap` assumes:

* Single-threaded access
* No concurrent modification

Under concurrency:

* Lost updates
* Infinite loops (rehashing)
* Corrupted internal state

These failures are silent and catastrophic.

---

## The design goal of concurrent collections

Concurrent collections aim to:

* Reduce contention
* Avoid global locks
* Maintain correctness
* Scale with thread count

They trade **strong consistency everywhere** for **practical performance**.

---

## ConcurrentHashMap (the workhorse)

```java
ConcurrentHashMap<String, Integer> map =
        new ConcurrentHashMap<>();

map.put("a", 1);
map.computeIfAbsent("b", k -> 2);
```

Key properties:

* No global lock
* Fine-grained synchronization
* Safe concurrent reads and writes
* Atomic compound operations

Reads never block writes.
Writes don’t block unrelated reads.

This is why it’s everywhere in backend systems.

---

## Mental model (easy recall)

* HashMap → unsafe, fast
* Hashtable → safe, slow
* ConcurrentHashMap → safe, scalable

---

## Atomic operations matter

```java
map.putIfAbsent("key", value);
```

This is not just convenience.
It is **atomic correctness**.

Doing the same with `get()` + `put()` is broken under concurrency.

---

## Copy-on-write collections (specialized tools)

```java
List<String> list = new CopyOnWriteArrayList<>();
```

Behavior:

* Reads are lock-free
* Writes copy the entire structure

This is efficient only when:

* Reads vastly outnumber writes
* Writes are rare and acceptable to copy

Used in:

* Listener lists
* Configuration snapshots

Wrong choice for write-heavy workloads.

---

## Blocking queues (coordination + data)

```java
BlockingQueue<Task> queue =
        new LinkedBlockingQueue<>();

queue.put(task);   // may block
Task task = queue.take(); // may block
```

Blocking queues:

* Combine data storage with coordination
* Handle waiting internally
* Remove need for manual `wait/notify`

Common in:

* Producer–consumer systems
* Task pipelines
* Thread pools

---

## Queue choice matters

* ArrayBlockingQueue → bounded, predictable memory
* LinkedBlockingQueue → scalable, unbounded by default
* PriorityBlockingQueue → ordered execution

Bounded queues introduce **backpressure**.
Unbounded queues risk **memory exhaustion**.

---

## Non-blocking queues

```java
Queue<Event> queue = new ConcurrentLinkedQueue<>();
```

Characteristics:

* Lock-free
* High throughput
* No blocking

Used when:

* Low latency matters
* Dropping or retrying is acceptable
* Coordination is handled elsewhere

---

## Sorted concurrent structures

```java
ConcurrentSkipListMap<Integer, String> map =
        new ConcurrentSkipListMap<>();
```

Why it exists:

* Concurrent + sorted
* Logarithmic performance
* No global lock

Used for:

* Time-based data
* Ranked structures
* Range queries

---

## Senior instinct

Use concurrent collections when:

* Access patterns are well understood
* Correctness matters more than raw speed
* You want fewer locks in business logic

Do not wrap normal collections with external locks unless necessary.

---

## Interview signal

> “Concurrent collections encapsulate synchronization and scalability decisions, allowing safe concurrent access without explicit locking in application code.”

That answer signals production experience.

---

## Quick recall

* ConcurrentHashMap → general-purpose
* CopyOnWrite → read-heavy
* BlockingQueue → coordination + storage
* ConcurrentLinkedQueue → non-blocking
* SkipList → concurrent + sorted

---

## Where this leads next

Once data structures are safe,
the next challenge is **asynchronous execution and composition**.

That brings us to:
**Futures and CompletableFuture.**

```
```
