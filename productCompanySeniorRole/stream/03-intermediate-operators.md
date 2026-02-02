
---


# Java Stream API — Module 3: Intermediate Operations (Transformation & Flow)
(Custom Notes | Senior Developer | Product-Based Companies)

==================================================

## WHY THIS MODULE MATTERS

Intermediate operations define:
- how data transforms
- how much work the JVM must do
- whether parallelism is safe
- whether your pipeline is predictable

Interviewers use this module to test:
- stateless vs stateful thinking
- ordering awareness
- ability to reason about hidden costs

==================================================


## INTERMEDIATE OPERATIONS — BIG PICTURE

Intermediate operations:
- return a new Stream
- are lazy
- can be chained
- do nothing until terminal operation

```java
stream.filter(...).map(...).limit(...)
````

### Mental Anchor

> Intermediate ops describe transformation, not execution.

==================================================

## TOPIC 1: filter() — Selective Flow

```java
stream.filter(n -> n > 10);
```

### Characteristics

* Stateless
* Does not change elements
* Preserves order

### Performance Insight

Filtering early reduces downstream work.

### Mental Anchor

> Filter early, reduce cost.

==================================================

## TOPIC 2: map() — One-to-One Transformation

```java
stream.map(String::toUpperCase);
```

### Characteristics

* Stateless
* One input → one output
* Preserves size

### Interview Trap

Using map when flatMap is required.

### Mental Anchor

> map transforms values, not structure.

==================================================

## TOPIC 3: flatMap() — Structure Flattening (VERY IMPORTANT)

```java
listOfLists.stream()
    .flatMap(List::stream)
    .toList();
```

### What flatMap Does

* One input → zero/many outputs
* Flattens nested structures

### Real-World Usage

* Optional.stream()
* One-to-many relationships

### Mental Anchor

> flatMap flattens streams of streams.

==================================================

## TOPIC 4: distinct() — Stateful Operation

```java
stream.distinct();
```

### Hidden Behavior

* Uses equals() & hashCode()
* Maintains internal state
* More memory usage

### Parallel Implication

* Synchronization overhead
* Reduced scalability

### Mental Anchor

> distinct trades memory for uniqueness.

==================================================

## TOPIC 5: sorted() — Expensive but Useful

```java
stream.sorted();
stream.sorted(Comparator.comparing(User::getAge));
```

### Characteristics

* Stateful
* Requires full materialization
* Breaks streaming nature

### Senior Insight

> sorted() destroys laziness beyond that point.

==================================================

## TOPIC 6: limit() & skip() — Short-Circuit Helpers

```java
stream.limit(10);
stream.skip(5);
```

### Characteristics

* Can short-circuit
* Useful for pagination

### Performance Note

Works best with ordered, sized sources.

### Mental Anchor

> limit saves work when used early.

==================================================

## TOPIC 7: peek() — Debugging Tool (DANGEROUS)

```java
stream.peek(System.out::println);
```

### Intended Purpose

* Debugging only

### Common Abuse

* Mutating objects
* Business logic

### Interview Killer

Using peek() for side effects.

### Mental Anchor

> peek observes, never mutates.

==================================================

## TOPIC 8: Stateless vs Stateful Ops (INTERVIEW GOLD)

### Stateless

* map
* filter

### Stateful

* distinct
* sorted
* limit (sometimes)

### Why This Matters

* Stateful ops limit parallelism
* Increase memory & synchronization

### Mental Anchor

> Stateless ops scale. Stateful ops constrain.

==================================================

## ORDERING & INTERMEDIATE OPS

* Ordered streams preserve order
* Some ops rely on order (limit)
* unordered() can improve performance

```java
stream.unordered().limit(10);
```

### Senior Insight

> Relaxing order can unlock parallelism.

==================================================

## JVM & PERFORMANCE INSIGHT

* Lambdas allocate objects
* flatMap expands pipelines
* sorted() allocates buffers
* distinct() allocates sets

### Mental Anchor

> Intermediate ops shape allocation rate.

==================================================

## COMMON MISTAKES (INTERVIEW FILTER)

* Using sorted unnecessarily
* Using peek for logic
* Forgetting flatMap
* Misunderstanding distinct cost
* Ignoring order dependency

==================================================

## REAL-WORLD USAGE (PRODUCT SYSTEMS)

* Data cleaning → filter + map
* API flattening → flatMap
* Deduplication → distinct
* Ranking → sorted
* Pagination → skip + limit

==================================================

## 5-MINUTE REVISION SNAPSHOT

> filter reduces
> map transforms
> flatMap flattens
> distinct remembers
> sorted materializes
> peek is for debugging

==================================================

## INTERVIEW PUNCHLINE (USE THIS)

> Intermediate operations in Java Streams define lazy transformation steps. Stateless operations like map and filter scale well, while stateful operations like distinct and sorted introduce memory and synchronization overhead. As a senior engineer, I order operations to minimize cost and maximize clarity.

==================================================

END — STREAM API MODULE 3 (INTERMEDIATE OPERATIONS)

```

---

