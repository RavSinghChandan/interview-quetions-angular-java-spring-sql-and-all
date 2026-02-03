

---


# Java Stream API — Module 2: Stream Creation & Sources
(Custom Notes | Senior Developer | Product-Based Companies)

==================================================

## WHY THIS MODULE MATTERS

Streams don’t exist in isolation.
They are always created from **a source** — and the source determines:

- ordering
- size awareness
- splittability (parallelism)
- performance characteristics

Senior interviews test whether you understand:
👉 *what kind of stream you are creating*, not just how.

==================================================


## STREAM SOURCES — BIG PICTURE

Streams can be created from:
- Collections
- Arrays
- Static factory methods
- Primitive ranges
- Infinite generators
- Optional

Each source has **different guarantees**.

==================================================


## TOPIC 1: Streams from Collections (MOST COMMON)

```java
List<Integer> list = List.of(1, 2, 3);
Stream<Integer> s = list.stream();
````

### Key Properties

* Ordered (if collection is ordered)
* Sized
* Non-null source
* Safe for sequential processing

### Parallel Version

```java
list.parallelStream();
```

### Mental Anchor

> Collection-backed streams inherit collection behavior.

==================================================

## TOPIC 2: Streams from Arrays

```java
int[] arr = {1, 2, 3};
IntStream s = Arrays.stream(arr);
```

### Why Prefer This

* Avoids boxing
* Better performance
* Cleaner numeric pipelines

### Senior Rule

> Prefer primitive streams when data is numeric.

==================================================

## TOPIC 3: Stream.of() — Static Factory

```java
Stream<String> s = Stream.of("A", "B", "C");
```

### Characteristics

* Fixed-size
* Known at creation time
* Ordered

### Use Cases

* Test data
* Small pipelines
* Composing streams

==================================================

## TOPIC 4: Primitive Streams (VERY IMPORTANT)

### Types

* IntStream
* LongStream
* DoubleStream

```java
IntStream.range(1, 10)
         .filter(n -> n % 2 == 0)
         .sum();
```

### Why They Exist

* Avoid boxing/unboxing
* Better CPU cache usage
* Faster aggregation

### Mental Anchor

> Boxing kills performance silently.

==================================================

## TOPIC 5: Infinite Streams — generate()

```java
Stream<Double> randoms =
    Stream.generate(Math::random);
```

### Characteristics

* Infinite
* Lazy
* Must be bounded

```java
randoms.limit(5).forEach(System.out::println);
```

### Interview Trap

Forgetting `limit()` → infinite processing.

==================================================

## TOPIC 6: Infinite Streams — iterate()

```java
Stream<Integer> s =
    Stream.iterate(0, n -> n + 1);
```

### Java 9+ Enhancement

```java
Stream.iterate(0, n -> n < 100, n -> n + 1);
```

### Senior Insight

> Java 9 fixed infinite iterate misuse.

==================================================

## TOPIC 7: Optional as Stream (SUBTLE & USEFUL)

```java
Optional<String> opt = Optional.of("data");
opt.stream().forEach(System.out::println);
```

### Why This Matters

* Elegant null-handling
* No special branching
* Composable pipelines

### Mental Anchor

> Optional.stream() removes null checks cleanly.

==================================================

## TOPIC 8: Files & IO Streams (AWARENESS)

```java
try (Stream<String> lines = Files.lines(path)) {
    lines.forEach(System.out::println);
}
```

### Important Notes

* IO-backed streams
* Must be closed
* Resource-sensitive

### Senior Rule

> Streams don’t manage resources — you do.

==================================================

## STREAM SOURCE & PARALLELISM (IMPORTANT)

Not all sources split equally well:

| Source            | Parallel Friendly |
| ----------------- | ----------------- |
| Array / ArrayList | ✅ Very            |
| HashSet           | ⚠️ Moderate       |
| LinkedList        | ❌ Poor            |
| IO Streams        | ❌ Dangerous       |

### Mental Anchor

> Parallel streams depend on source splittability.

==================================================

## JVM & PERFORMANCE INSIGHT

* Sized streams optimize better
* Primitive streams reduce GC
* Infinite streams rely heavily on laziness
* Poor source choice limits parallel gains

==================================================

## COMMON MISTAKES (INTERVIEW FILTER)

* Using boxed streams unnecessarily
* Forgetting to bound infinite streams
* Parallelizing IO streams
* Ignoring resource closure
* Creating streams just to loop

==================================================

## REAL-WORLD USAGE (PRODUCT SYSTEMS)

* Request data → Collection streams
* Numeric metrics → Primitive streams
* Config seeds → Stream.of()
* Large files → Files.lines() (carefully)
* Optional fields → Optional.stream()

==================================================

## 5-MINUTE REVISION SNAPSHOT

> Streams come from sources
> Source defines behavior
> Primitive streams avoid boxing
> Infinite streams need limits
> IO streams need closing

==================================================

## INTERVIEW PUNCHLINE (USE THIS)

> Streams can be created from collections, arrays, primitives, generators, and IO sources. As a senior engineer, I choose stream sources carefully because ordering, size, and splittability directly affect performance, parallelism, and correctness.

==================================================

END — STREAM API MODULE 2 (STREAM CREATION & SOURCES)

```


