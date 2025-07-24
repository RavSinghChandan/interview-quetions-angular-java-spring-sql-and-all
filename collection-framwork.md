# Java Collections Framework Interview Questions

This markdown file organizes commonly asked **Java Collections Framework** interview questions into four categories — **Basic**, **Intermediate**, **Advanced**, and **Real-World Applications** — for each major topic, gathered from real MNC interviews.

---

## 1. List

### ✅ Basic
- What is the difference between `ArrayList` and `LinkedList`?
- How does `ArrayList` grow internally?
- How to remove duplicates from a list?

### ✅ Intermediate
- When would you use `LinkedList` over `ArrayList`?
- How to synchronize a list?
- What happens when you use `subList()` in `ArrayList`?

### ✅ Advanced
- How is fail-fast behavior implemented in `ArrayList`?
- What is `CopyOnWriteArrayList` and where is it used?

### ✅ Real-Life Application
- Design a recent search history feature using `ArrayList`.
- Implement undo/redo functionality using `List`.

---

## 2. Set

### ✅ Basic
- What is the difference between `HashSet`, `LinkedHashSet`, and `TreeSet`?
- Why are duplicates not allowed in a `Set`?

### ✅ Intermediate
- How does `HashSet` handle collisions?
- How to sort a `Set`?

### ✅ Advanced
- How does `TreeSet` maintain order?
- Custom comparator in `TreeSet`?
- Why should `equals()` and `hashCode()` be overridden?

### ✅ Real-Life Application
- Design a system to store unique user emails with quick lookup.
- Prevent duplicate login sessions using `Set`.

---

## 3. Map

### ✅ Basic
- Difference between `HashMap`, `LinkedHashMap`, and `TreeMap`?
- What happens if `hashCode()` returns same value?

### ✅ Intermediate
- How does `HashMap` work internally (pre and post Java 8)?
- Difference between `Hashtable` and `HashMap`?
- How to iterate over a `Map`?

### ✅ Advanced
- What is `ConcurrentHashMap` and its segmentation logic?
- Why is `HashMap` not thread-safe?
- What is the fail-safe behavior in `ConcurrentHashMap`?

### ✅ Real-Life Application
- Implement a caching mechanism using `LinkedHashMap` (LRU).
- Store student records with fast lookup by student ID using `Map`.

---

## 4. Queue / Deque

### ✅ Basic
- What is a `Queue` and how is it different from a `Stack`?
- Differences between `ArrayDeque` and `LinkedList`?

### ✅ Intermediate
- How to implement producer-consumer using `BlockingQueue`?
- When to use `PriorityQueue`?

### ✅ Advanced
- How does `PriorityQueue` order elements?
- What is `DelayQueue` or `ConcurrentLinkedQueue` used for?

### ✅ Real-Life Application
- Task scheduling using `PriorityQueue`.
- Message processing in order using `BlockingQueue`.

---

## 5. Iterator / Iterable

### ✅ Basic
- What is the difference between `Iterator` and `ListIterator`?
- How does `iterator.remove()` work?

### ✅ Intermediate
- What is fail-fast vs fail-safe?
- When to use `Spliterator`?

### ✅ Advanced
- Internal working of fail-fast iterator in `ArrayList`?
- Use of `Spliterator` in parallel stream processing?

### ✅ Real-Life Application
- Paginate through large data set with a custom iterator.

---

## 6. Collections Utility Class

### ✅ Basic
- What is the use of `Collections.sort()`?
- How to make a collection read-only?

### ✅ Intermediate
- How to perform binary search on a `List`?
- How to shuffle or rotate a list?

### ✅ Advanced
- How to create a synchronized version of a collection?

### ✅ Real-Life Application
- Read-only product catalog for end-users.

---

## 7. Comparable vs Comparator

### ✅ Basic
- What’s the difference between `Comparable` and `Comparator`?

### ✅ Intermediate
- How to sort using multiple fields?

### ✅ Advanced
- Difference in `Comparator.comparing()` vs lambda-based comparator?

### ✅ Real-Life Application
- Sort a list of employees based on department, then salary.

---

## 8. Thread-Safe Collections

### ✅ Basic
- What is the difference between `Collections.synchronizedList()` and `CopyOnWriteArrayList`?

### ✅ Intermediate
- How do concurrent collections prevent data inconsistency?

### ✅ Advanced
- Under the hood: `ConcurrentHashMap` segment locking (pre Java 8) and bucket-based locking (post Java 8)?

### ✅ Real-Life Application
- Build a thread-safe cache in a multi-threaded app.

---

## 9. Stream API + Collections

### ✅ Basic
- How to convert a list to a map using Stream?
- How to filter a collection?

### ✅ Intermediate
- Grouping and partitioning using Collectors?

### ✅ Advanced
- How to handle merge conflict in `Collectors.toMap()`?

### ✅ Real-Life Application
- Stream pipeline to transform a list of order objects to a summary report.

---

## ✅ Bonus: Conceptual and Tricky Interview Questions
- Why is `HashSet` faster than `TreeSet`?
- How to design a custom collection?
- What are memory implications of using `LinkedList` vs `ArrayList`?
- Why does `HashMap` allow one null key and multiple null values?
- Why is `HashMap` capacity always a power of 2?

---

This file is designed to help you practice and revise Java Collection topics thoroughly for interviews at MNCs and top product-based companies.
