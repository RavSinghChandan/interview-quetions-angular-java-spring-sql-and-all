# Java Collections Framework Interview Questions

This markdown file organizes commonly asked **Java Collections Framework** interview questions into four categories — **Basic**, **Intermediate**, **Advanced**, and **Real-World Applications** — for each major topic, gathered from real MNC interviews.

---

# Java Collections Framework: List - Interview Questions & Answers

## 1. List

---

### ✅ Basic

#### 1. What is the difference between `ArrayList` and `LinkedList`?

| Feature            | ArrayList                      | LinkedList                       |
| ------------------ | ------------------------------ | -------------------------------- |
| Internal Structure | Dynamic array                  | Doubly linked list               |
| Access Time        | Fast (O(1) for get)            | Slow (O(n) for get)              |
| Insertion/Deletion | Slow (shifting needed)         | Fast at head/tail (no shifting)  |
| Memory             | Less memory (no node overhead) | More memory (next/prev pointers) |

#### 2. How does `ArrayList` grow internally?

* **Default Capacity**: 10
* When full, capacity increases to **1.5 times** the current size.

```java
int newCapacity = oldCapacity + (oldCapacity >> 1);
```

#### 3. How to remove duplicates from a list?

```java
List<String> list = Arrays.asList("A", "B", "A", "C");
List<String> unique = new ArrayList<>(new HashSet<>(list));
System.out.println(unique); // [A, B, C]
```

---

### ✅ Intermediate

#### 4. When would you use `LinkedList` over `ArrayList`?

Use `LinkedList` when:

* You have frequent insertions/deletions at the **beginning/middle**.
* You do not need **random access** (no frequent get(index)).

#### 5. How to synchronize a list?

```java
List<String> syncList = Collections.synchronizedList(new ArrayList<>());
```

> ✅ Use `synchronized(syncList)` block while iterating.

#### 6. What happens when you use `subList()` in `ArrayList`?

* Returns a **view** (not a new list).
* Changes in subList reflect in original list.
* Structural modification outside the subList throws `ConcurrentModificationException`.

```java
List<String> list = new ArrayList<>(List.of("A", "B", "C", "D"));
List<String> sub = list.subList(1, 3);
sub.set(0, "X"); // Modifies original list as well
```

---

### ✅ Advanced

#### 7. How is fail-fast behavior implemented in `ArrayList`?

* `ArrayList` maintains a `modCount` variable.
* During iteration, Iterator keeps a snapshot of `modCount`.
* If `modCount` changes externally (outside iterator), it throws `ConcurrentModificationException`.

```java
List<String> list = new ArrayList<>();
list.add("A");
Iterator<String> it = list.iterator();
list.add("B"); // Structural modification
it.next(); // Throws ConcurrentModificationException
```

#### 8. What is `CopyOnWriteArrayList` and where is it used?

* Thread-safe variant of ArrayList where all mutative operations (`add`, `remove`, etc.) result in a **new copy** of the array.
* Ideal for **read-heavy** operations where write frequency is low.

```java
CopyOnWriteArrayList<String> cowList = new CopyOnWriteArrayList<>();
cowList.add("A");
for (String s : cowList) {
    cowList.add("B"); // No ConcurrentModificationException
}
```

> ✅ Used in cache lists, event listener lists, etc.

---

### ✅ Real-Life Application

#### 9. Design a recent search history feature using `ArrayList`.

```java
class RecentSearches {
    private final int MAX_SIZE = 5;
    private List<String> history = new ArrayList<>();

    public void addSearch(String query) {
        history.remove(query); // Remove if duplicate exists
        history.add(0, query); // Add to front
        if (history.size() > MAX_SIZE) {
            history.remove(history.size() - 1); // Maintain size
        }
    }

    public List<String> getHistory() {
        return history;
    }
}
```

#### 10. Implement undo/redo functionality using `List`

```java
class Editor {
    private List<String> states = new ArrayList<>();
    private int currentIndex = -1;

    public void type(String text) {
        while (states.size() > currentIndex + 1) {
            states.remove(states.size() - 1);
        }
        states.add(text);
        currentIndex++;
    }

    public String undo() {
        if (currentIndex > 0) currentIndex--;
        return states.get(currentIndex);
    }

    public String redo() {
        if (currentIndex < states.size() - 1) currentIndex++;
        return states.get(currentIndex);
    }

    public String current() {
        return currentIndex >= 0 ? states.get(currentIndex) : "";
    }
}
```

---

✅ **Prepared for Interviews** – Covers theory, examples, and real-world use cases in one place.

---

## Java Collections: Set and Map - Complete Guide with Answers

---

## 2. Set

### ✅ Basic

#### Q1. What is the difference between `HashSet`, `LinkedHashSet`, and `TreeSet`?

| Feature       | HashSet             | LinkedHashSet                | TreeSet                                         |
| ------------- | ------------------- | ---------------------------- | ----------------------------------------------- |
| Ordering      | No                  | Maintains insertion order    | Sorted according to natural order or comparator |
| Performance   | Fastest             | Slightly slower than HashSet | Slower than HashSet & LinkedHashSet             |
| Allows null?  | Yes (only one null) | Yes (only one null)          | No (if comparator doesn't handle nulls)         |
| Underlying DS | HashMap             | LinkedHashMap                | TreeMap                                         |

---

#### Q2. Why are duplicates not allowed in a `Set`?

* Set is based on the concept of **uniqueness**.
* `HashSet` uses `hashCode()` and `equals()` to detect duplicates.
* If `hashCode` and `equals` indicate equality, the element is considered duplicate and not added.

---

### ✅ Intermediate

#### Q3. How does `HashSet` handle collisions?

* Internally, `HashSet` uses a `HashMap`.
* When two objects have the same hash code, they're placed in the same bucket (collision).
* Then `equals()` is used to resolve which object is unique.
* Java 8+: If too many collisions occur, it uses **balanced trees** instead of linked lists.

---

#### Q4. How to sort a `Set`?

* Convert to a `List`, sort it using `Collections.sort()` or `stream().sorted()`.
* Use `TreeSet` with Comparator or natural ordering.

```java
Set<String> unsorted = new HashSet<>();
List<String> sorted = new ArrayList<>(unsorted);
Collections.sort(sorted);
```

OR

```java
Set<String> sortedSet = new TreeSet<>(Comparator.reverseOrder());
```

---

### ✅ Advanced

#### Q5. How does `TreeSet` maintain order?

* Uses a **Red-Black Tree** (self-balancing binary search tree).
* Keeps elements sorted by natural order or provided Comparator.
* All operations are O(log n).

---

#### Q6. Custom Comparator in TreeSet?

```java
Set<String> set = new TreeSet<>((a, b) -> b.length() - a.length());
```

This TreeSet will sort strings based on their length in descending order.

---

#### Q7. Why should `equals()` and `hashCode()` be overridden?

* Needed for objects stored in `HashSet` to check uniqueness.
* `hashCode()` helps locate the correct bucket.
* `equals()` checks if objects are actually equal.
* Violating this contract may lead to unexpected behavior (duplicates or missing elements).

---

### ✅ Real-Life Application

#### Q8. Design a system to store unique user emails with quick lookup.

```java
Set<String> emailSet = new HashSet<>();

public boolean addEmail(String email) {
    return emailSet.add(email.toLowerCase()); // Case-insensitive uniqueness
}
```

---

#### Q9. Prevent duplicate login sessions using Set

```java
Set<String> activeUsers = ConcurrentHashMap.newKeySet();

public boolean login(String userId) {
    return activeUsers.add(userId); // returns false if already logged in
}

public void logout(String userId) {
    activeUsers.remove(userId);
}
```

---

## 3. Map

### ✅ Basic

#### Q1. Difference between `HashMap`, `LinkedHashMap`, and `TreeMap`?

| Feature     | HashMap    | LinkedHashMap   | TreeMap                   |
| ----------- | ---------- | --------------- | ------------------------- |
| Ordering    | No order   | Insertion order | Sorted by keys            |
| Performance | Fast       | Slightly slower | Slower (log n ops)        |
| Null keys   | 1 null key | 1 null key      | No null keys (throws NPE) |

---

#### Q2. What happens if `hashCode()` returns same value?

* Collision happens.
* Entries are placed in the same bucket.
* Java uses `equals()` to differentiate the keys.
* Java 8+: Converts bucket to tree if collisions > threshold.

---

### ✅ Intermediate

#### Q3. How does `HashMap` work internally (pre and post Java 8)?

* Pre-Java 8:

    * Bucket → linked list of entries → O(n)
* Java 8+:

    * Bucket → linked list OR red-black tree (if size > 8) → O(log n)
* Hashing:

    * Key → hashCode → index = hash % capacity

---

#### Q4. Difference between `Hashtable` and `HashMap`?

| Feature        | Hashtable          | HashMap                          |
| -------------- | ------------------ | -------------------------------- |
| Thread-safe    | Yes (synchronized) | No                               |
| Performance    | Slower             | Faster                           |
| Nulls allowed? | No null key/value  | 1 null key, multiple null values |

---

#### Q5. How to iterate over a `Map`?

```java
Map<String, Integer> map = new HashMap<>();

for (Map.Entry<String, Integer> entry : map.entrySet()) {
    System.out.println(entry.getKey() + " -> " + entry.getValue());
}

// OR using stream
map.forEach((k, v) -> System.out.println(k + ": " + v));
```

---

### ✅ Advanced

#### Q6. What is `ConcurrentHashMap` and its segmentation logic?

* A thread-safe version of `HashMap`.
* Java 7: used **segments** (locks for each segment).
* Java 8+: replaced with **synchronized blocks + CAS** (lock striping).
* Supports concurrent read/write without blocking full map.

---

#### Q7. Why is `HashMap` not thread-safe?

* Multiple threads can corrupt internal structure.
* Infinite loop or data loss in concurrent access.
* Use `Collections.synchronizedMap()` or `ConcurrentHashMap`.

---

#### Q8. Fail-safe behavior in `ConcurrentHashMap`?

* Unlike `HashMap` (fail-fast), `ConcurrentHashMap` allows safe iteration during concurrent modification.
* Iterator reflects changes made during iteration.

---

### ✅ Real-Life Application

#### Q9. Implement a caching mechanism using `LinkedHashMap` (LRU).

```java
class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }
}

// Usage
LRUCache<String, String> cache = new LRUCache<>(3);
cache.put("A", "Apple");
cache.put("B", "Ball");
cache.put("C", "Cat");
cache.get("A"); // Access A → becomes recent
cache.put("D", "Dog"); // B removed
```

---

#### Q10. Store student records with fast lookup by student ID using `Map`

```java
class Student {
    String id;
    String name;
    // constructor, getters
}

Map<String, Student> studentMap = new HashMap<>();
studentMap.put("S123", new Student("S123", "John"));
Student s = studentMap.get("S123");
```

---

## Java Collection Framework – Set, Map, Queue, Iterator, Collections, Comparator

## 2. Set

### ✅ Basic

**Q: What is the difference between `HashSet`, `LinkedHashSet`, and `TreeSet`?**

* `HashSet`: Unordered, allows one null, backed by HashMap.
* `LinkedHashSet`: Maintains insertion order.
* `TreeSet`: Sorted order, based on `Comparable` or `Comparator`, does not allow null.

**Q: Why are duplicates not allowed in a `Set`?**

* `Set` is designed to store *unique* elements. Under the hood, it uses `equals()` and `hashCode()` to check for duplicates.

### ✅ Intermediate

**Q: How does `HashSet` handle collisions?**

* Collisions are handled using chaining (LinkedList or Tree structure after Java 8).
* Elements with the same hashCode are stored in a bucket and differentiated using `equals()`.

**Q: How to sort a `Set`?**

* Use `TreeSet` or convert the set to a `List`, sort it, then convert it back if needed.

```java
Set<String> set = new TreeSet<>(unsortedSet);
```

### ✅ Advanced

**Q: How does `TreeSet` maintain order?**

* Internally backed by a Red-Black Tree.
* Uses `compareTo()` or a `Comparator` to maintain sorted order.

**Q: Custom comparator in `TreeSet`?**

```java
TreeSet<String> set = new TreeSet<>((a, b) -> b.compareTo(a)); // reverse order
```

**Q: Why should `equals()` and `hashCode()` be overridden?**

* To ensure consistent behavior in hash-based collections (`HashSet`, `HashMap`).
* Violating the contract leads to duplicates or loss of elements.

### ✅ Real-Life Application

**Q: Design a system to store unique user emails with quick lookup.**

```java
Set<String> userEmails = new HashSet<>();
userEmails.add("test@example.com");
```

**Q: Prevent duplicate login sessions using `Set`.**

```java
Set<String> activeSessions = new HashSet<>();
if (!activeSessions.contains(sessionId)) {
    activeSessions.add(sessionId);
    // allow login
}
```

---

## 3. Map

### ✅ Basic

**Q: Difference between `HashMap`, `LinkedHashMap`, and `TreeMap`?**

* `HashMap`: Unordered, allows one null key, many null values.
* `LinkedHashMap`: Maintains insertion order.
* `TreeMap`: Sorted by key using natural order or custom `Comparator`.

**Q: What happens if `hashCode()` returns same value?**

* Hash collision occurs. HashMap stores multiple entries in the same bucket and resolves them using `equals()`.

### ✅ Intermediate

**Q: How does `HashMap` work internally (pre and post Java 8)?**

* Pre Java 8: Array + LinkedList (bucket chaining)
* Post Java 8: Array + LinkedList + Balanced Tree (Red-Black tree when threshold > 8)

**Q: Difference between `Hashtable` and `HashMap`?**

* `Hashtable` is synchronized and legacy.
* `HashMap` is not synchronized, hence faster.

**Q: How to iterate over a `Map`?**

```java
for (Map.Entry<K, V> entry : map.entrySet()) {
    System.out.println(entry.getKey() + ": " + entry.getValue());
}
```

### ✅ Advanced

**Q: What is `ConcurrentHashMap` and its segmentation logic?**

* Thread-safe map using segments (pre-Java 8) or CAS & synchronized blocks (post-Java 8).
* Avoids locking the entire map.

**Q: Why is `HashMap` not thread-safe?**

* Concurrent modification may cause data inconsistency or infinite loops.

**Q: What is the fail-safe behavior in `ConcurrentHashMap`?**

* Allows concurrent read/writes without `ConcurrentModificationException`.

### ✅ Real-Life Application

**Q: Implement a caching mechanism using `LinkedHashMap` (LRU).**

```java
class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;

    LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }
}
```

**Q: Store student records with fast lookup by student ID using `Map`.**

```java
Map<String, Student> studentMap = new HashMap<>();
studentMap.put("123", new Student("John", "CS"));
```

---

## 4. Queue / Deque

### ✅ Basic

**Q: What is a `Queue` and how is it different from a `Stack`?**

* `Queue`: FIFO (First-In-First-Out), useful for scheduling.
* `Stack`: LIFO (Last-In-First-Out), useful for backtracking, undo, etc.

**Q: Differences between `ArrayDeque` and `LinkedList`?**

* `ArrayDeque`: Faster for add/remove, no nulls allowed.
* `LinkedList`: Slower but more flexible (can act as queue, stack).

### ✅ Intermediate

**Q: How to implement producer-consumer using `BlockingQueue`?**

```java
BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);

// Producer
new Thread(() -> {
    try {
        queue.put("task");
    } catch (InterruptedException e) {}
}).start();

// Consumer
new Thread(() -> {
    try {
        String task = queue.take();
    } catch (InterruptedException e) {}
}).start();
```

**Q: When to use `PriorityQueue`?**

* When you need elements sorted by priority rather than insertion order (e.g., task scheduling).

### ✅ Advanced

**Q: How does `PriorityQueue` order elements?**

* Uses a binary heap (min-heap by default).
* Orders based on natural ordering or a `Comparator`.

**Q: What is `DelayQueue` or `ConcurrentLinkedQueue` used for?**

* `DelayQueue`: Elements become available only after a delay.
* `ConcurrentLinkedQueue`: Thread-safe, non-blocking queue for high-performance use cases.

### ✅ Real-Life Application

**Q: Task scheduling using `PriorityQueue`.**

```java
PriorityQueue<Task> pq = new PriorityQueue<>((a, b) -> a.getPriority() - b.getPriority());
```

**Q: Message processing in order using `BlockingQueue`.**

```java
BlockingQueue<Message> messageQueue = new LinkedBlockingQueue<>();
```

---

## 5. Iterator / Iterable

### ✅ Basic

**Q: What is the difference between `Iterator` and `ListIterator`?**

* `Iterator`: Can iterate forward only.
* `ListIterator`: Can iterate in both directions, available only for List types.

**Q: How does `iterator.remove()` work?**

* Removes the last element returned by `next()`.
* Must call `next()` before calling `remove()`.

### ✅ Intermediate

**Q: What is fail-fast vs fail-safe?**

* Fail-fast: Throws `ConcurrentModificationException` on modification during iteration (e.g., `ArrayList`).
* Fail-safe: Works on a copy of the collection (e.g., `CopyOnWriteArrayList`).

**Q: When to use `Spliterator`?**

* Use when you need to support parallel stream processing with efficient splitting.

### ✅ Advanced

**Q: Internal working of fail-fast iterator in `ArrayList`?**

* Maintains a `modCount`.
* On modification, `modCount` is updated, and iterator compares it with expected count.

**Q: Use of `Spliterator` in parallel stream processing?**

* Supports splitting data into smaller parts for efficient parallelism.
* Used in Java 8 Stream API.

### ✅ Real-Life Application

**Q: Paginate through large data set with a custom iterator.**

* Custom iterator to fetch data in batches from DB or large collections.

---

## 6. Collections Utility Class

### ✅ Basic

**Q: What is the use of `Collections.sort()`?**

* Sorts a list in natural order or using a custom comparator.

**Q: How to make a collection read-only?**

```java
List<String> list = Collections.unmodifiableList(originalList);
```

### ✅ Intermediate

**Q: How to perform binary search on a `List`?**

```java
Collections.binarySearch(sortedList, "key");
```

**Q: How to shuffle or rotate a list?**

```java
Collections.shuffle(list);
Collections.rotate(list, 2);
```

### ✅ Advanced

**Q: How to create a synchronized version of a collection?**

```java
List<String> syncList = Collections.synchronizedList(new ArrayList<>());
```

### ✅ Real-Life Application

**Q: Read-only product catalog for end-users.**

* Use `Collections.unmodifiableList()` to prevent modifications.

---

## 7. Comparable vs Comparator

### ✅ Basic

**Q: What’s the difference between `Comparable` and `Comparator`?**

* `Comparable`: Natural order. Class implements `compareTo()`.
* `Comparator`: Custom order. Separate class or lambda passed to sort method.

### ✅ Intermediate

**Q: How to sort using multiple fields?**

```java
list.sort(Comparator.comparing(Employee::getDept).thenComparing(Employee::getSalary));
```

### ✅ Advanced

**Q: Difference in `Comparator.comparing()` vs lambda-based comparator?**

* `comparing()`: Cleaner and readable.
* Lambda: More control for complex logic.

### ✅ Real-Life Application

**Q: Sort a list of employees based on department, then salary.**

```java
list.sort(Comparator.comparing(Employee::getDepartment)
                    .thenComparing(Employee::getSalary));
```

---


## Java Collections Interview Guide

This guide covers the most frequently asked Java Collections interview questions across all difficulty levels, with real-world examples and code snippets. Each section helps you build a complete understanding of Java Collections.

---

## 8. Thread-Safe Collections

### ✅ Basic

**Q: What is the difference between `Collections.synchronizedList()` and `CopyOnWriteArrayList`?**

**Answer:**

* `Collections.synchronizedList()` wraps a normal list and synchronizes each method. It's suitable when writes are frequent.
* `CopyOnWriteArrayList` creates a new copy of the array on each write. Best when reads vastly outnumber writes.

```java
List<String> syncList = Collections.synchronizedList(new ArrayList<>());
List<String> cowList = new CopyOnWriteArrayList<>();
```

* **syncList**: Locking overhead, but consistent with traditional behavior.
* **cowList**: Thread-safe without external synchronization, but expensive for writes.

---

### ✅ Intermediate

**Q: How do concurrent collections prevent data inconsistency?**

**Answer:**
Concurrent collections use non-blocking algorithms, fine-grained locks, or atomic operations:

* `ConcurrentHashMap` uses segment or bucket locking to allow concurrent read/write.
* `CopyOnWriteArrayList` avoids locking by copying the entire array.
* `BlockingQueue` (e.g., `LinkedBlockingQueue`) uses internal locks and conditions.

This ensures thread-safety while maximizing performance in multi-threaded environments.

---

### ✅ Advanced

**Q: Under the hood: `ConcurrentHashMap` segment locking (pre Java 8) and bucket-based locking (post Java 8)?**

**Answer:**

* **Pre-Java 8**: `ConcurrentHashMap` used segment locking. The map was split into 16 or more segments, each having its own lock. Only one thread could modify a segment at a time.

* **Java 8+**: Introduced bucket-level synchronization. It uses:

    * CAS (Compare-And-Swap) operations for low-contention updates.
    * Synchronized blocks for structural changes like resizing.
    * Tree-based bins (`TreeBin`) to prevent hash collision attacks.

This fine-grained approach increased concurrency.

---

### ✅ Real-Life Application

**Q: Build a thread-safe cache in a multi-threaded app.**

```java
class ThreadSafeCache<K, V> {
    private final ConcurrentHashMap<K, V> cache = new ConcurrentHashMap<>();

    public V get(K key, Function<? super K, ? extends V> mappingFunction) {
        return cache.computeIfAbsent(key, mappingFunction);
    }
}

// Usage
ThreadSafeCache<String, String> cache = new ThreadSafeCache<>();
String result = cache.get("userId123", id -> fetchFromDB(id));
```

* Uses `computeIfAbsent` to ensure atomic put-if-absent behavior.

---

## 9. Stream API + Collections

### ✅ Basic

**Q1: How to convert a list to a map using Stream?**

```java
List<Employee> employees = ...;
Map<Integer, String> idToName = employees.stream()
    .collect(Collectors.toMap(Employee::getId, Employee::getName));
```

**Q2: How to filter a collection?**

```java
List<Employee> filtered = employees.stream()
    .filter(e -> e.getSalary() > 50000)
    .collect(Collectors.toList());
```

---

### ✅ Intermediate

**Q: Grouping and partitioning using Collectors?**

```java
Map<String, List<Employee>> deptMap = employees.stream()
    .collect(Collectors.groupingBy(Employee::getDepartment));

Map<Boolean, List<Employee>> partitioned = employees.stream()
    .collect(Collectors.partitioningBy(e -> e.getSalary() > 60000));
```

* `groupingBy` – groups by keys (e.g., department)
* `partitioningBy` – splits into `true` or `false` groups

---

### ✅ Advanced

**Q: How to handle merge conflict in `Collectors.toMap()`?**

```java
Map<String, Integer> nameToAge = employees.stream()
    .collect(Collectors.toMap(
        Employee::getName,
        Employee::getAge,
        (age1, age2) -> Math.max(age1, age2)
    ));
```

* The third parameter `(u, v) -> ...` resolves conflicts when duplicate keys exist.

---

### ✅ Real-Life Application

**Q: Stream pipeline to transform a list of order objects to a summary report**

```java
class OrderSummary {
    private String customer;
    private long totalOrders;
    private double totalAmount;
}

Map<String, OrderSummary> report = orders.stream()
    .collect(Collectors.groupingBy(
        Order::getCustomer,
        Collectors.collectingAndThen(
            Collectors.toList(),
            list -> {
                long count = list.size();
                double sum = list.stream().mapToDouble(Order::getAmount).sum();
                return new OrderSummary(list.get(0).getCustomer(), count, sum);
            }
        )
    ));
```

---

## ✅ Bonus: Conceptual and Tricky Interview Questions

**Q: Why is `HashSet` faster than `TreeSet`?**

* `HashSet` uses hashing (O(1) avg), `TreeSet` uses Red-Black tree (O(log n)).

**Q: How to design a custom collection?**

* Implement `Collection` or `List`/`Set` interfaces.
* Override core methods (`add()`, `iterator()`, etc.) and define internal data structure.

**Q: Memory implications of `LinkedList` vs `ArrayList`?**

* `ArrayList` uses contiguous memory, lower overhead but resizing cost.
* `LinkedList` uses more memory (node objects) but efficient insert/delete.

**Q: Why does `HashMap` allow one null key and multiple null values?**

* Internally, null key is handled in a special bucket (index 0).
* Values can be null as it doesn’t affect hashing or equality.

**Q: Why is `HashMap` capacity always a power of 2?**

* Makes indexing faster using `hash & (capacity - 1)` instead of modulo.
* Avoids collision and ensures uniform bucket distribution.

---

This file is designed to help you practice and revise Java Collection topics thoroughly for interviews at MNCs and top product-based companies.
