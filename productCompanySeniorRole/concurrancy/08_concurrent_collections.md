# Module 8: Concurrent Collections (Self Notes)

---

## Core Purpose

Concurrent collections provide **thread-safe, high-performance data structures** without external synchronization.

**Golden Rule:**

> Never wrap Collections.synchronizedXxx() in high-scale systems. Use concurrent collections.

---

## Mental Model

* Regular collections = not thread-safe
* Synchronized collections = thread-safe but slow
* Concurrent collections = thread-safe + scalable

---

## Topic 58: ConcurrentHashMap

* Thread-safe Map
* Lock striping / CAS-based
* No global lock
* High concurrency for reads and writes

```java
ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
map.put("txn", 1);
map.computeIfAbsent("key", k -> 42);
```

---

## Topic 59: CopyOnWriteArrayList

* Creates new copy on write
* Read-optimized
* Expensive writes
* No ConcurrentModificationException

---

## Topic 60: CopyOnWriteArraySet

* Backed by CopyOnWriteArrayList
* Thread-safe set
* Best for read-heavy workloads

---

## Topic 61: BlockingQueue

* Thread-safe queue
* Blocks on full/empty
* Used for producer-consumer

---

## Topic 62: ArrayBlockingQueue

* Bounded queue
* Fixed size
* FIFO
* Predictable memory usage

---

## Topic 63: LinkedBlockingQueue

* Optionally bounded
* Higher throughput
* More memory overhead

---

## Topic 64: PriorityBlockingQueue

* Orders elements by priority
* Unbounded
* Not FIFO

---

## Topic 65: ConcurrentLinkedQueue

* Lock-free queue
* Non-blocking
* High throughput

---

## Topic 66: ConcurrentSkipListMap

* Sorted concurrent map
* Logarithmic performance
* NavigableMap support

---

## Execution Rules

* Use ConcurrentHashMap for shared maps
* Use BlockingQueue for producer-consumer
* Avoid CopyOnWrite for write-heavy use

---

## Real-World Mapping

* Session stores
* Rate limiter state
* Work queues
* Event processing buffers

---

## Performance Implications

* ConcurrentHashMap scales well
* CopyOnWrite is read-optimized
* BlockingQueue prevents busy-waiting
* Unbounded queues risk OOM

---

## Common Mistakes

* Using HashMap in multithreading
* Overusing CopyOnWrite
* Using unbounded queues blindly
* External synchronization on concurrent collections

---

## Design Rules

* Prefer ConcurrentHashMap
* Prefer bounded queues
* Match data structure to access pattern
* Avoid synchronized wrappers

---

## JVM Insight

* ConcurrentHashMap uses CAS
* CopyOnWrite uses array copying
* BlockingQueue uses locks/conditions

---

## Senior-Level Takeaway

> Concurrent collections provide scalable thread safety. Choose the right structure based on read/write patterns.

---

## Ultra-Crisp Recall

* ConcurrentHashMap = default map
* CopyOnWrite = read-heavy
* BlockingQueue = producer-consumer
* Avoid synchronized wrappers

---

## Interview Punchline

> Java concurrent collections provide thread-safe data structures optimized for scalability. ConcurrentHashMap is the default choice, BlockingQueue supports producer-consumer patterns, and CopyOnWrite collections suit read-heavy workloads.

---
