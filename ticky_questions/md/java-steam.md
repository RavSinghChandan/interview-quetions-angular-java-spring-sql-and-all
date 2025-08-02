

---

### 🔹 1. **Lazy Evaluation & Execution Traps**

1. **What is lazy evaluation in Java Streams?**
   Why are intermediate operations not executed immediately?

2. **Which operation triggers the stream execution?**
   (Hint: terminal ops like `collect`, `forEach`.)

3. **What happens if you call a terminal operation twice on the same stream?**
   Can you reuse a stream?

4. **Can you debug inside a stream chain?**
   (How to use `.peek()` and when not to use it?)

---

### 🔹 2. **Map vs FlatMap vs Filter — Internal Behavior**

5. **What’s the internal difference between `map()` and `flatMap()`?**
   When would `map()` result in nested streams?

6. **What is the difference between `filter().findFirst()` and `findAny()` in parallel streams?**

7. **What happens when you use `map()` with `Optional` inside a stream chain?**

---

### 🔹 3. **Performance & Parallel Streams**

8. **How does `parallelStream()` work internally?**
   What is the ForkJoinPool it uses?

9. **When would a `parallelStream()` perform worse than a normal stream?**

10. **Is `parallel()` thread-safe by default?**
    What if I modify a shared variable in `.forEach()`?

---

### 🔹 4. **Collector Tricks**

11. **What happens if `Collectors.toMap()` encounters duplicate keys?**
    *(This throws `IllegalStateException` unless merge function is given.)*

12. **How would you group data using `Collectors.groupingBy()` but with a custom comparator?**

13. **How can you collect into a `TreeMap` or `LinkedHashMap` using a collector?**

---

### 🔹 5. **Optional & Streams Combined**

14. **How does `Optional.flatMap()` help when chaining multiple `Optional` values inside a stream?**

15. **How to convert `Optional<List<T>>` to `Stream<T>` safely in one line?**

---

### 🔹 6. **Order, Short-Circuiting, and Edge Cases**

16. **Which operations in stream are short-circuiting?**
    (Hint: `anyMatch`, `findFirst`, `limit`.)

17. **What happens if you modify the source list while processing a stream?**

18. **Does `forEachOrdered()` preserve the encounter order in parallel streams?**

---

### 🔹 7. **Custom Collector / Advanced Usage**

19. **How do you write a custom collector?**
    What are its 5 components? (supplier, accumulator, combiner, finisher, characteristics)

20. **What is `Collectors.reducing()` and how is it different from `reduce()`?**

---

### 🔹 8. **Bonus Smart Checks**

21. **How to flatten a `List<List<String>>` into `List<String>` using streams?**
    (Classic `flatMap` test.)

22. **How to find duplicate elements in a list using streams?**
    (Tricky – needs `Collectors.groupingBy()` with count.)

23. **Why shouldn't you use `.collect(Collectors.toList())` in performance-critical paths?**
    (Hint: it uses `ArrayList`, unmodifiable unless wrapped.)

24. **What does `.stream().sorted(Comparator.comparing(...))` do internally? Is it stable sort?**

---

