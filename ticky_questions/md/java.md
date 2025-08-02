
---

## ☕ JAVA – Topic-wise Tricky & Under-the-Hood Interview Questions (10+ per topic)

---

### 1️⃣ **Memory Model, GC, and JVM Internals**

*(True backend engineer questions — JVM-level understanding separates seniors from juniors)*

1. What are the different memory areas in JVM? (heap, stack, metaspace, etc.)
2. How does Java manage memory allocation between Eden, Survivor, and Old Generation?
3. What is a stop-the-world event? Which GCs cause it and when?
4. What’s the difference between G1, ZGC, and Shenandoah?
5. Can a Java program cause memory leaks without using any static variables?
6. How does `finalize()` work and why is it deprecated?
7. What is the difference between strong, soft, weak, and phantom references?
8. What is escape analysis and how does it help with performance?
9. What happens when PermGen is full (pre-Java 8)? What replaced it in Java 8?
10. What tools would you use to debug a memory leak or GC performance issue?

---

### 2️⃣ **Multithreading & Concurrency (Deep Concepts)**

*(One of the most favorite areas for hard interviews)*

1. What is the difference between `synchronized`, `ReentrantLock`, and `StampedLock`?
2. How does `volatile` really work under the hood?
3. What is a **happens-before** relationship in Java memory model?
4. Can two threads read the same volatile variable at the same time?
5. What is a thread-safe class? Is `ArrayList` thread-safe?
6. What are atomic classes (`AtomicInteger`, etc.), and how are they implemented?
7. Explain the difference between `ExecutorService`, `ThreadPoolExecutor`, and `ForkJoinPool`.
8. How does `CompletableFuture` differ from `Future`?
9. What are deadlocks, livelocks, and starvation? How would you detect/fix them?
10. What’s the role of `ThreadLocal`, and how can it cause memory leaks?

---

### 3️⃣ **Collections Framework – Internals & Traps**

*(Another heavily targeted area in interviews)*

1. What is the load factor and capacity in `HashMap`, and how do they affect performance?
2. What happens internally when two keys have the same hashcode in a `HashMap`?
3. How does `ConcurrentHashMap` handle concurrent updates without locking the entire map?
4. What’s the difference between `HashMap`, `LinkedHashMap`, and `TreeMap`?
5. Why is `HashSet` internally a `HashMap`?
6. What is the time complexity of `containsKey()` in a `HashMap` and when can it degrade?
7. What is the difference between fail-fast and fail-safe iterators?
8. Can you store `null` as a key and value in a `TreeMap`?
9. How does Java’s `PriorityQueue` maintain order internally?
10. What’s the difference between a `List` and a `Deque`, and when to use which?

---

### 4️⃣ **OOP Principles & Design Patterns (Real Concepts)**

*(These test your *thought process* more than syntax)*

1. What is polymorphism vs method overloading vs overriding — under the hood?
2. What is the real use of abstract classes if interfaces can now have default methods?
3. What is the difference between composition and inheritance? Which is preferred and why?
4. How does the Liskov Substitution Principle apply in real-world code?
5. Can an interface extend another interface? Can a class implement multiple interfaces?
6. What design pattern does `Spring’s BeanFactory` use?
7. When would you use the Strategy pattern over State pattern?
8. What is the Visitor pattern? Where does it apply in Java ecosystem?
9. How does the Singleton pattern differ when using Enum vs private constructor?
10. What is the difference between `Factory` and `Builder` pattern in object construction?

---

### 5️⃣ **Exception Handling & Edge Cases**

*(Tests depth of understanding and control flow)*

1. What’s the difference between `throw` and `throws`?
2. What happens if you return a value in both `try` and `finally`? Which one is returned?
3. Can `finally` block suppress an exception thrown in `catch`?
4. What’s the use case of `Throwable` and when would you catch it?
5. Can you override a method that throws checked exceptions with one that doesn’t?
6. What is exception chaining, and why is it useful?
7. What happens if an exception is thrown inside a static block?
8. How does `try-with-resources` work internally?
9. Can you catch multiple exception types in one `catch` block? How is ambiguity resolved?
10. Can you throw exceptions from `main()`? What’s the behavior?

---

### 6️⃣ **Streams, Lambdas, and Functional Style**

*(Very commonly tested in Java 8+ interviews)*

1. How does `Stream.map()` differ from `flatMap()`?
2. What happens when you call `terminal operation` twice on the same stream?
3. What are intermediate and terminal operations? Give real examples.
4. How are streams lazy in Java? What triggers execution?
5. Can streams be reused? Why not?
6. What’s the difference between `filter().findFirst()` vs `findAny()`?
7. What’s the performance benefit of parallel streams? What are the risks?
8. What is `Collectors.toMap()` trap when duplicate keys are present?
9. How is a lambda compiled in bytecode? Is it a class?
10. How does `Optional.map()` differ from `Optional.flatMap()`?

---

### 7️⃣ **Serialization, ClassLoaders, Reflection (Rare but Smart)**

1. How does Java serialization work internally? What is written to the stream?
2. Can you serialize a class that contains non-serializable fields?
3. What is the role of `serialVersionUID`?
4. What is a ClassLoader? What are its types and hierarchy?
5. Can two classes with the same name coexist in JVM?
6. How can you break singleton using serialization or reflection?
7. What’s the difference between `Class.forName()` and `.class`?
8. Can you load a class at runtime from a remote JAR? How?
9. How does reflection bypass access modifiers?
10. What’s the danger of improper reflection use in production systems?

---
