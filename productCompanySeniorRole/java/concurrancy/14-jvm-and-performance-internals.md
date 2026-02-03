
# STEP 9: Futures & Asynchronous Programming

---

## Why this step exists

Up to now, concurrency meant **multiple threads doing work**.

But threads are expensive, and blocking them is worse.

In real systems, most time is spent:
- Waiting for I/O
- Calling downstream services
- Waiting for responses

Blocking a thread while waiting is wasteful.

This step exists because **waiting should not block progress**.

---

## The shift in mindset

Earlier thinking:
> “Call a method and wait for the result.”

Asynchronous thinking:
> “Start the work, move on, and handle the result when it’s ready.”

This shift separates **execution** from **result handling**.

---

## Future: representing a result that arrives later

A `Future` represents a value that:
- Is not available now
- Will be available later
- Might fail

---

## Code: basic Future

```java
ExecutorService executor = Executors.newFixedThreadPool(2);

Future<Integer> future = executor.submit(() -> {
    Thread.sleep(1000);
    return 42;
});

Integer result = future.get(); // blocks
````

What this gives:

* Asynchronous execution
* Synchronous retrieval

This is only a partial solution.

---

## The problem with `Future`

* `get()` blocks
* No easy composition
* No built-in callbacks
* Error handling is clumsy

As systems grow, this model becomes restrictive.

---

## CompletableFuture: the real async tool

`CompletableFuture` solves what `Future` cannot.

It allows:

* Non-blocking callbacks
* Task composition
* Explicit error handling
* Clear async pipelines

---

## Code: non-blocking async execution

```java
CompletableFuture
    .supplyAsync(() -> fetchData())
    .thenApply(data -> transform(data))
    .thenAccept(result -> save(result));
```

What changed:

* No blocking
* Each stage is explicit
* Flow is easy to reason about

This is how modern serviceBasedMNCLevel.backend systems are written.

---

## Composition matters

### thenApply vs thenCompose

```java
CompletableFuture<User> user =
    fetchUserAsync(id);

CompletableFuture<Profile> profile =
    user.thenCompose(u -> fetchProfileAsync(u));
```

* `thenApply` → transforms data
* `thenCompose` → flattens async calls

Misusing these leads to nested futures and messy code.

---

## Combining multiple async tasks

```java
CompletableFuture<Void> all =
    CompletableFuture.allOf(f1, f2, f3);
```

Use cases:

* Calling multiple downstream services
* Aggregating results
* Parallel I/O

This is concurrency without explicit threads.

---

## Error handling (often ignored)

```java
future
    .exceptionally(ex -> fallback())
    .thenAccept(result -> process(result));
```

Errors are part of the flow, not an afterthought.

Senior systems handle failures explicitly.

---

## Execution context matters

By default, async tasks use:

* `ForkJoinPool.commonPool()`

This is shared across the JVM.

For serviceBasedMNCLevel.backend services:

* Use dedicated executors
* Avoid starving unrelated tasks

---

## ForkJoinPool intuition

ForkJoinPool:

* Splits work into smaller tasks
* Uses work-stealing
* Maximizes CPU utilization

Best suited for:

* CPU-bound parallel work
* Recursive task decomposition

Not ideal for blocking I/O.

---

## Mental model (easy recall)

* Threads → execution units
* Executors → resource control
* Futures → placeholders
* CompletableFuture → async pipelines

---

## Senior instinct

Asynchronous code is about **composability**, not just speed.

Good async design:

* Avoids blocking
* Makes flow explicit
* Handles failure naturally

---

## Interview signal

> “CompletableFuture enables non-blocking, composable async pipelines, unlike Future which blocks and doesn’t scale well.”

That signals real-world experience.

---

## Quick recall

* Future → async execution, blocking retrieval
* CompletableFuture → non-blocking, composable
* thenApply → transform
* thenCompose → flatten
* allOf → parallel coordination

---

## Where this leads next

Async pipelines run fast, but at scale:

* CPU caches matter
* Memory ordering matters
* Hardware behavior matters

That brings us to:
**Advanced concurrency concepts and JVM-level effects.**

```
```
