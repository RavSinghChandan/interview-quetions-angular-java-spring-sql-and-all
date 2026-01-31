
---

````md
# Java Stream API — Module 5: Collectors (Aggregation & Modeling Data)
(Custom Notes | Senior Developer | Product-Based Companies)

==================================================

## WHY THIS MODULE MATTERS (CRITICAL)

Most real-world Stream pipelines end with:
```java
.collect(...)
````

Interviewers use collectors to test:

* data modeling ability
* understanding of mutable reduction
* correctness under parallelism
* ability to express business logic cleanly

This module alone can **decide offers**.

==================================================

## WHAT A COLLECTOR REALLY IS

A Collector defines **how data is accumulated**.

Internally it has:

* supplier → creates container
* accumulator → adds element
* combiner → merges containers (parallel)
* finisher → final transformation

### Mental Anchor

> A Collector is a recipe for building results.

==================================================

## TOPIC 1: toList(), toSet(), toMap()

### toList()

```java
List<String> list = stream.collect(Collectors.toList());
```

* Mutable list
* No guarantees on implementation

### toSet()

```java
Set<String> set = stream.collect(Collectors.toSet());
```

* No ordering guarantee

### toMap() (INTERVIEW TRAP)

```java
Map<String, Integer> map =
    stream.collect(Collectors.toMap(
        User::getId,
        User::getAge
    ));
```

### Duplicate Key Problem

```java
Collectors.toMap(k, v, (a, b) -> a);
```

### Mental Anchor

> toMap always needs a merge strategy in real systems.

==================================================

## TOPIC 2: groupingBy() — CLASSIC INTERVIEW FAVORITE

```java
Map<String, List<User>> usersByCity =
    users.stream()
         .collect(Collectors.groupingBy(User::getCity));
```

### What groupingBy Does

* Groups elements by classifier
* Values are collections by default

### Variants

```java
groupingBy(key)
groupingBy(key, downstreamCollector)
groupingBy(key, mapFactory, downstream)
```

### Mental Anchor

> groupingBy models one-to-many relationships.

==================================================

## TOPIC 3: partitioningBy() — TWO-WAY SPLIT

```java
Map<Boolean, List<User>> result =
    users.stream()
         .collect(Collectors.partitioningBy(User::isActive));
```

### Difference from groupingBy

* Always two keys: true / false
* Optimized for boolean logic

### Mental Anchor

> partitioningBy is specialized groupingBy.

==================================================

## TOPIC 4: Downstream Collectors (SENIOR-LEVEL)

```java
Map<String, Long> countByCity =
    users.stream()
         .collect(Collectors.groupingBy(
             User::getCity,
             Collectors.counting()
         ));
```

### Other Downstreams

* summingInt
* averagingDouble
* mapping
* reducing

### Mental Anchor

> Downstream collectors express nested aggregation cleanly.

==================================================

## TOPIC 5: mapping() — Transform Before Collecting

```java
Map<String, List<String>> namesByCity =
    users.stream()
         .collect(Collectors.groupingBy(
             User::getCity,
             Collectors.mapping(User::getName, Collectors.toList())
         ));
```

### Why mapping Exists

* Avoid extra stream
* Cleaner pipelines

### Mental Anchor

> mapping keeps pipelines flat.

==================================================

## TOPIC 6: reducing() Collector

```java
Map<String, Integer> ageSumByCity =
    users.stream()
         .collect(Collectors.groupingBy(
             User::getCity,
             Collectors.reducing(
                 0,
                 User::getAge,
                 Integer::sum
             )
         ));
```

### When to Use

* Custom aggregation
* Non-trivial reduction

### Mental Anchor

> reducing gives full control at the cost of complexity.

==================================================

## TOPIC 7: collectingAndThen()

```java
List<User> immutableList =
    users.stream()
         .collect(Collectors.collectingAndThen(
             Collectors.toList(),
             List::copyOf
         ));
```

### Use Case

* Post-processing result
* Creating immutable views

### Senior Insight

> collectingAndThen bridges mutability and immutability.

==================================================

## COLLECTORS & PARALLEL STREAMS (IMPORTANT)

* Collector must be:

    * associative
    * non-interfering
    * stateless

### Safe in Parallel

* groupingBy
* partitioningBy
* summing / counting

### Dangerous

* Custom mutable collectors without combiner

### Mental Anchor

> Parallel streams depend on correct combiner logic.

==================================================

## JVM & PERFORMANCE INSIGHT

* Collectors allocate containers
* groupingBy allocates many lists
* mapping avoids intermediate collections
* collectingAndThen adds extra step

### Senior Rule

> Choose collectors to minimize allocations.

==================================================

## COMMON MISTAKES (INTERVIEW FILTER)

* Forgetting merge function in toMap
* Overusing reducing
* Using groupingBy when partitioningBy fits
* Ignoring parallel behavior
* Returning mutable collections blindly

==================================================

## REAL-WORLD USAGE (PRODUCT SYSTEMS)

* Analytics → groupingBy + counting
* Reporting → groupingBy + mapping
* Feature flags → partitioningBy
* API DTO building → collectingAndThen

==================================================

## 5-MINUTE REVISION SNAPSHOT

> collect is mutable reduction
> groupingBy models relationships
> partitioningBy splits boolean logic
> toMap needs merge
> downstream collectors matter

==================================================

## INTERVIEW PUNCHLINE (USE THIS)

> Collectors define how stream elements are accumulated into result structures. Using groupingBy, partitioningBy, and downstream collectors, we can model complex business aggregations efficiently. As a senior engineer, I choose collectors based on correctness, readability, and parallel execution safety.

==================================================

END — STREAM API MODULE 5 (COLLECTORS)

```

---
