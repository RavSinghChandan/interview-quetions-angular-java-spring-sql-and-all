# Core Java Interview Questions for Senior Java Backend Developers

This document contains the most frequently asked Core Java interview questions, curated for senior Java backend developers transitioning to or maintaining expertise in full-stack or backend roles. Questions are organized by topic and include those repeatedly asked in interviews by companies like **TCS, Infosys, Cognizant, Capgemini**, etc., along with additional critical questions to ensure comprehensive coverage for senior-level interviews.

---

## 1. Object-Oriented Programming (OOP)

### Basic Questions

1. **What are the core principles of OOP in Java?**

**🧩 Foundation:** The four core principles of OOP are:
- **Encapsulation:** Bundling data (fields) and methods that operate on the data into a single unit (class), and restricting access to some of the object's components.
- **Abstraction:** Hiding complex implementation details and exposing only the necessary features of an object.
- **Inheritance:** Mechanism by which one class acquires the properties and behaviors of another class.
- **Polymorphism:** Ability of an object to take many forms, typically via method overriding and interface implementation.

**🔁 Flow:**
```java
// Encapsulation
public class Account {
    private double balance; // private field
    public double getBalance() { return balance; }
    public void deposit(double amount) { balance += amount; }
}

// Abstraction
abstract class Vehicle {
    abstract void start();
}

// Inheritance
class Car extends Vehicle {
    void start() { System.out.println("Car started"); }
}

// Polymorphism
Vehicle v = new Car(); // v can refer to any subclass of Vehicle
v.start(); // Calls Car's start()
```
**🐞 Fixes:** Use encapsulation to protect data, abstraction to simplify interfaces, inheritance for code reuse, and polymorphism for flexibility.

2. **What is the difference between abstraction and encapsulation?**

**🧩 Foundation:**
- **Abstraction:** Focuses on exposing only relevant information and hiding implementation details (e.g., interfaces, abstract classes).
- **Encapsulation:** Focuses on bundling data and methods and restricting access (e.g., private fields, public getters/setters).

**⚙️ Function:**
- Abstraction is about "what" an object does; encapsulation is about "how" it does it.

**🔁 Flow:**
```java
// Abstraction - hiding complexity
interface PaymentProcessor {
    boolean processPayment(double amount);
}

// Encapsulation - bundling data with methods
class BankAccount {
    private double balance; // private data
    public void deposit(double amount) { balance += amount; }
    public double getBalance() { return balance; }
}
```

**🐞 Fixes:** Use abstraction for API design, encapsulation for data protection.

3. **What is the difference between an interface and an abstract class?**

**🧩 Foundation:**
- **Interface:** Defines a contract; all methods are abstract (until Java 8+ default/static methods). Cannot have state (except static/final fields).
- **Abstract class:** Can have both abstract and concrete methods, and can maintain state (fields).

**⚙️ Function:**
- Use interfaces for type contracts; abstract classes for base implementations.

**🔁 Flow:**
```java
// Interface - contract only
interface Drawable {
    void draw();
    default void resize() { System.out.println("Resizing..."); }
}

// Abstract class - can have state and implementation
abstract class Shape {
    protected String color;
    public abstract double area();
    public void setColor(String c) { color = c; }
}
```

**🐞 Fixes:** Prefer interfaces for multiple inheritance, abstract classes for shared code.

### Intermediate Questions

1. **How does Java achieve polymorphism?**

**🧩 Foundation:** Java achieves polymorphism through method overriding (runtime) and method overloading (compile-time).

**⚙️ Function:**
- Method overriding allows a subclass to provide a specific implementation of a method declared in its superclass.
- Method overloading allows multiple methods with the same name but different parameters.

**🔁 Flow:**
```java
// Runtime polymorphism (overriding)
class Animal { void speak() { System.out.println("Animal"); } }
class Dog extends Animal { void speak() { System.out.println("Dog"); } }
Animal a = new Dog();
a.speak(); // Outputs "Dog" (runtime polymorphism)

// Compile-time polymorphism (overloading)
class Calculator {
    int add(int a, int b) { return a + b; }
    double add(double a, double b) { return a + b; }
}
```

**🐞 Fixes:** Use polymorphism for flexible and extensible code.

2. **What is the significance of the `final` keyword in Java?**

**🧩 Foundation:** The `final` keyword restricts modification:
- **final variable:** Value cannot be changed after initialization.
- **final method:** Cannot be overridden by subclasses.
- **final class:** Cannot be subclassed.

**🔁 Flow:**
```java
public class FinalExample {
    final int MAX_SIZE = 100; // final variable
    final void display() { System.out.println("Cannot override"); } // final method
}

final class UtilityClass { // final class
    static void helper() { System.out.println("Helper method"); }
}
```

**🐞 Fixes:** Use `final` for constants, to prevent inheritance, and to ensure immutability.

3. **Explain the difference between method overloading and method overriding.**

**🧩 Foundation:**
- **Overloading:** Same method name, different parameter lists, within the same class.
- **Overriding:** Subclass provides a specific implementation of a method declared in its superclass.

**🔁 Flow:**
```java
// Method Overloading - same class, different parameters
class Printer {
    void print(String text) { System.out.println("Printing: " + text); }
    void print(int number) { System.out.println("Printing number: " + number); }
    void print(String text, int copies) { 
        for(int i = 0; i < copies; i++) System.out.println(text); 
    }
}

// Method Overriding - different class, same signature
class Animal {
    void makeSound() { System.out.println("Some sound"); }
}
class Cat extends Animal {
    void makeSound() { System.out.println("Meow"); } // overriding
}
```

**🐞 Fixes:** Use overloading for convenience, overriding for polymorphism.

### Advanced Questions

1. **How does the `super` keyword work in Java, and when would you use it?**

**🧩 Foundation:** `super` refers to the immediate parent class object.
- Access parent class methods/fields
- Call parent class constructor

**🔁 Flow:**
```java
class Parent { 
    String name = "Parent";
    void show() { System.out.println("Parent method"); } 
}
class Child extends Parent {
    String name = "Child";
    void show() { 
        super.show(); // calls parent method
        System.out.println("Child method");
        System.out.println("Parent name: " + super.name); // access parent field
    }
}
```

**🐞 Fixes:** Use `super` to avoid ambiguity and to extend parent behavior.

2. **What is the difference between composition and inheritance? When would you use one over the other?**

**🧩 Foundation:**
- **Inheritance:** "is-a" relationship; subclass extends superclass.
- **Composition:** "has-a" relationship; class contains references to other classes.

**⚙️ Function:** Prefer composition for flexibility and to avoid tight coupling.

**🔁 Flow:**
```java
// Inheritance - "is-a" relationship
class Vehicle { void start() { System.out.println("Starting vehicle"); } }
class Car extends Vehicle { } // Car is-a Vehicle

// Composition - "has-a" relationship
class Engine { void start() { System.out.println("Engine starting"); } }
class Car {
    private Engine engine; // Car has-a Engine
    public Car() { engine = new Engine(); }
    void start() { engine.start(); }
}
```

**🐞 Fixes:** Use inheritance for shared behavior, composition for code reuse and flexibility.

### Tough Questions

1. **How would you design a class hierarchy to ensure extensibility while preventing misuse through inheritance?**

**🧩 Foundation:**
- Use `final` for classes/methods you don't want extended/overridden.
- Use interfaces for extensibility.
- Use composition over inheritance.

**🔁 Flow:**
```java
// Use final to prevent inheritance
final class UtilityClass {
    static void helper() { System.out.println("Helper"); }
}

// Use interfaces for extensibility
interface PaymentMethod {
    boolean process(double amount);
}

// Use composition for flexibility
class PaymentProcessor {
    private List<PaymentMethod> methods = new ArrayList<>();
    public void addMethod(PaymentMethod method) { methods.add(method); }
}
```

**🐞 Fixes:** Document intended extension points, restrict inheritance where necessary.

### Situational / Real-world Questions

1. **You're tasked with refactoring a legacy system with tightly coupled classes. How would you apply OOP principles to improve it?**

**🧩 Foundation:**
- Identify tightly coupled code and extract interfaces.
- Apply encapsulation, abstraction, and composition.

**🔁 Flow:**
```java
// Before: Tightly coupled
class OrderProcessor {
    private DatabaseConnection db = new DatabaseConnection();
    private EmailService email = new EmailService();
    void processOrder(Order order) {
        db.save(order);
        email.sendConfirmation(order);
    }
}

// After: Loose coupling with interfaces
interface DataRepository { void save(Order order); }
interface NotificationService { void sendConfirmation(Order order); }

class OrderProcessor {
    private DataRepository repo;
    private NotificationService notifier;
    
    public OrderProcessor(DataRepository repo, NotificationService notifier) {
        this.repo = repo;
        this.notifier = notifier;
    }
    
    void processOrder(Order order) {
        repo.save(order);
        notifier.sendConfirmation(order);
    }
}
```

**🐞 Fixes:** Refactor to use interfaces, encapsulate state, and use composition for flexibility.

---

## 2. Java Classes and Objects

### Basic Questions

1. **What is the difference between a class and an object in Java?**

**🧩 Foundation:**
- **Class:** Blueprint or template for creating objects.
- **Object:** Instance of a class, with its own state and behavior.

**🔁 Flow:**
```java
// Class - blueprint
class Student {
    String name;
    int age;
    void study() { System.out.println(name + " is studying"); }
}

// Object - instance
Student student1 = new Student();
student1.name = "John";
student1.age = 20;
student1.study(); // John is studying
```

**🐞 Fixes:** Use classes to define structure, objects to represent data.

2. **How do you create a singleton class in Java?**

**🧩 Foundation:** Singleton ensures only one instance of a class exists.

**🔁 Flow:**
```java
public class Singleton {
    private static final Singleton INSTANCE = new Singleton();
    private Singleton() {}
    public static Singleton getInstance() { return INSTANCE; }
    
    public void doSomething() { System.out.println("Singleton working"); }
}

// Usage
Singleton s1 = Singleton.getInstance();
Singleton s2 = Singleton.getInstance();
// s1 == s2 (same instance)
```

**🐞 Fixes:** Use private constructor, static instance, and thread-safe access.

### Intermediate Questions

1. **What are immutable objects, and how do you create one in Java?**

**🧩 Foundation:** Immutable objects cannot be changed after creation (e.g., `String`).

**🔁 Flow:**
```java
public final class Person {
    private final String name;
    private final int age;
    
    public Person(String name, int age) { 
        this.name = name; 
        this.age = age; 
    }
    
    public String getName() { return name; }
    public int getAge() { return age; }
    
    // Return new object for modifications
    public Person withAge(int newAge) {
        return new Person(name, newAge);
    }
}
```

**🐞 Fixes:** Use `final` fields, no setters, and return new objects for modifications.

2. **Explain the role of the `this` keyword in Java.**

**🧩 Foundation:** `this` refers to the current object instance.
- Disambiguates fields and parameters
- Passes current object as a parameter
- Calls other constructors in the same class

**🔁 Flow:**
```java
class Employee {
    private String name;
    private int id;
    
    public Employee(String name, int id) {
        this.name = name; // disambiguate field from parameter
        this.id = id;
    }
    
    public Employee(String name) {
        this(name, 0); // call other constructor
    }
    
    public void printInfo() {
        System.out.println("Employee: " + this.name + ", ID: " + this.id);
    }
}
```

**🐞 Fixes:** Use `this` to avoid ambiguity and for fluent APIs.

### Advanced Questions

1. **How does the `clone()` method work, and what precautions should be taken when implementing it?**

**🧩 Foundation:** `clone()` creates a copy of an object. Implement `Cloneable` and override `clone()`.

**🔁 Flow:**
```java
class Student implements Cloneable {
    private String name;
    private int age;
    
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    @Override
    public Student clone() throws CloneNotSupportedException {
        return (Student) super.clone(); // shallow copy
    }
    
    // Deep copy example
    public Student deepClone() {
        return new Student(this.name, this.age);
    }
}
```

**🐞 Fixes:** Implement deep copy for mutable fields, handle `CloneNotSupportedException`, and prefer copy constructors or static factory methods for clarity.

2. **What is the purpose of the `Object` class in Java?**

**🧩 Foundation:** `Object` is the root class of all Java classes. It provides methods like `equals()`, `hashCode()`, `toString()`, `clone()`, and `finalize()`.

**🔁 Flow:**
```java
class Product {
    private String name;
    private double price;
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Product product = (Product) obj;
        return Objects.equals(name, product.name) && price == product.price;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
    
    @Override
    public String toString() {
        return "Product{name='" + name + "', price=" + price + "}";
    }
}
```

**🐞 Fixes:** Override `equals()`, `hashCode()`, and `toString()` for custom classes.

### Tough Questions

1. **How would you implement a deep copy of an object with nested references?**

**🧩 Foundation:** Deep copy duplicates all fields, including nested objects.

**🔁 Flow:**
```java
public class Node implements Cloneable {
    int value;
    Node next;
    public Node clone() {
        Node copy = new Node();
        copy.value = this.value;
        if (this.next != null) copy.next = this.next.clone();
        return copy;
    }
}
```
**🐞 Fixes:** Recursively clone nested objects, handle cycles carefully.

### Situational / Real-world Questions

1. **You need to ensure thread-safe instantiation of a singleton in a high-concurrency environment. How would you achieve this?**

**🧩 Foundation:** Use double-checked locking or enum singleton for thread safety.

**🔁 Flow:**
```java
public class ThreadSafeSingleton {
    private static volatile ThreadSafeSingleton instance;
    private ThreadSafeSingleton() {}
    public static ThreadSafeSingleton getInstance() {
        if (instance == null) {
            synchronized (ThreadSafeSingleton.class) {
                if (instance == null) instance = new ThreadSafeSingleton();
            }
        }
        return instance;
    }
}
```
**🐞 Fixes:** Use `volatile` and double-checked locking, or use enum for simple singletons.

---

## 3. Collections Framework

### Basic Questions

1. **What is the Java Collections Framework, and what are its main interfaces?**

**🧩 Foundation:** The Collections Framework is a unified architecture for representing and manipulating collections (groups of objects).

**⚙️ Function:** Provides interfaces (`Collection`, `List`, `Set`, `Map`, `Queue`) and implementations (`ArrayList`, `HashSet`, `HashMap`, etc.).

**🔁 Flow:**
```java
// List - ordered collection
List<String> names = new ArrayList<>();
names.add("John");
names.add("Jane");
System.out.println(names.get(0)); // John

// Set - unique elements
Set<Integer> numbers = new HashSet<>();
numbers.add(1);
numbers.add(1); // ignored
System.out.println(numbers.size()); // 1

// Map - key-value pairs
Map<String, Integer> scores = new HashMap<>();
scores.put("John", 85);
scores.put("Jane", 92);
System.out.println(scores.get("John")); // 85
```

**🐞 Fixes:** Use the right interface for the use case (e.g., `List` for ordered, `Set` for unique, `Map` for key-value).

2. **What is the difference between `ArrayList` and `LinkedList`?**

**🧩 Foundation:**
- **ArrayList:** Backed by a dynamic array; fast random access, slow inserts/removes in the middle.
- **LinkedList:** Doubly-linked list; fast inserts/removes, slow random access.

**🔁 Flow:**
```java
// ArrayList - fast random access
ArrayList<String> arrayList = new ArrayList<>();
arrayList.add("A");
arrayList.add("B");
arrayList.add("C");
System.out.println(arrayList.get(1)); // B (fast)

// LinkedList - fast inserts/removes
LinkedList<String> linkedList = new LinkedList<>();
linkedList.add("A");
linkedList.add("B");
linkedList.add("C");
linkedList.add(1, "X"); // fast insert
System.out.println(linkedList); // [A, X, B, C]
```

**🐞 Fixes:** Use `ArrayList` for frequent access, `LinkedList` for frequent inserts/removes.

### Intermediate Questions

1. **What is the difference between `HashMap`, `LinkedHashMap`, and `TreeMap`?**

**🧩 Foundation:**
- **HashMap:** Unordered, allows null keys/values, fast access.
- **LinkedHashMap:** Maintains insertion order.
- **TreeMap:** Sorted by keys, no null keys, slower than `HashMap`.

**🔁 Flow:**
```java
// HashMap - unordered
HashMap<String, Integer> hashMap = new HashMap<>();
hashMap.put("Zebra", 1);
hashMap.put("Apple", 2);
hashMap.put("Banana", 3);
System.out.println(hashMap); // {Apple=2, Zebra=1, Banana=3}

// LinkedHashMap - maintains order
LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();
linkedHashMap.put("Zebra", 1);
linkedHashMap.put("Apple", 2);
linkedHashMap.put("Banana", 3);
System.out.println(linkedHashMap); // {Zebra=1, Apple=2, Banana=3}

// TreeMap - sorted
TreeMap<String, Integer> treeMap = new TreeMap<>();
treeMap.put("Zebra", 1);
treeMap.put("Apple", 2);
treeMap.put("Banana", 3);
System.out.println(treeMap); // {Apple=2, Banana=3, Zebra=1}
```

**🐞 Fixes:** Use `HashMap` for general use, `LinkedHashMap` for order, `TreeMap` for sorted keys.

2. **How does a `HashSet` ensure uniqueness of elements?**

**🧩 Foundation:** `HashSet` uses a `HashMap` internally; uniqueness is enforced by keys in the map.

**🔁 Flow:**
```java
class Student {
    private String name;
    private int id;
    
    public Student(String name, int id) {
        this.name = name;
        this.id = id;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return id == student.id && Objects.equals(name, student.name);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
}

// HashSet uses equals() and hashCode() for uniqueness
HashSet<Student> students = new HashSet<>();
students.add(new Student("John", 1));
students.add(new Student("John", 1)); // ignored - same hashCode and equals
System.out.println(students.size()); // 1
```

**🐞 Fixes:** Override `equals()` and `hashCode()` for custom objects.

3. **What is the `ConcurrentHashMap`, and when would you use it?**

**🧩 Foundation:** `ConcurrentHashMap` is a thread-safe, high-performance map for concurrent access.

**🔁 Flow:**
```java
ConcurrentHashMap<String, Integer> scores = new ConcurrentHashMap<>();
scores.put("John", 85);
scores.put("Jane", 92);

// Thread-safe operations
scores.computeIfAbsent("Bob", k -> 75);
scores.computeIfPresent("John", (k, v) -> v + 5);
System.out.println(scores.get("John")); // 90
```

**🐞 Fixes:** Use for multi-threaded access, avoid `Hashtable` and synchronized maps for performance.

### Advanced Questions

1. **How does the internal implementation of `HashMap` handle collisions?**

**🧩 Foundation:** Collisions are handled by chaining (linked lists) or, since Java 8, by balanced trees for high-collision buckets.

**🔁 Flow:**
```java
// Custom class with poor hashCode to demonstrate collisions
class BadHash {
    private String value;
    
    public BadHash(String value) { this.value = value; }
    
    @Override
    public int hashCode() { return 1; } // Always same hash - causes collisions
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BadHash badHash = (BadHash) obj;
        return Objects.equals(value, badHash.value);
    }
}

HashMap<BadHash, String> map = new HashMap<>();
map.put(new BadHash("A"), "First");
map.put(new BadHash("B"), "Second"); // Collision - same bucket
map.put(new BadHash("C"), "Third");  // Collision - same bucket
```

**🐞 Fixes:** Use good hash functions, avoid excessive collisions.

2. **What are the benefits of using `Collections.synchronizedList` vs. `CopyOnWriteArrayList`?**

**🧩 Foundation:**
- `Collections.synchronizedList`: Synchronized wrapper, all methods are synchronized.
- `CopyOnWriteArrayList`: Thread-safe, optimized for frequent reads and infrequent writes.

**🔁 Flow:**
```java
// SynchronizedList - all operations synchronized
List<String> syncList = Collections.synchronizedList(new ArrayList<>());
synchronized(syncList) {
    syncList.add("Item");
    syncList.get(0);
}

// CopyOnWriteArrayList - optimized for reads
CopyOnWriteArrayList<String> cowList = new CopyOnWriteArrayList<>();
cowList.add("Item"); // Creates new array copy
String item = cowList.get(0); // No synchronization needed
```

**🐞 Fixes:** Use `CopyOnWriteArrayList` for read-heavy, write-light scenarios.

### Tough Questions

1. **How would you design a custom collection to handle a large dataset with frequent reads and rare writes?**

**🧩 Foundation:** Use a `CopyOnWriteArrayList` or a custom structure optimized for reads.

**🔁 Flow:**
```java
class ReadOptimizedList<T> {
    private volatile List<T> data = new ArrayList<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    
    public void add(T item) {
        lock.writeLock().lock();
        try {
            List<T> newData = new ArrayList<>(data);
            newData.add(item);
            data = newData;
        } finally {
            lock.writeLock().unlock();
        }
    }
    
    public T get(int index) {
        lock.readLock().lock();
        try {
            return data.get(index);
        } finally {
            lock.readLock().unlock();
        }
    }
}
```

**🐞 Fixes:** Minimize locking, use immutable snapshots for reads.

### Situational / Real-world Questions

1. **Your application is experiencing performance issues with a large `HashMap`. How would you optimize it?**

**🧩 Foundation:**
- Tune initial capacity and load factor
- Use efficient hash functions
- Profile and monitor memory usage

**🔁 Flow:**
```java
// Optimized HashMap with proper initial capacity
HashMap<String, Integer> optimizedMap = new HashMap<>(1000, 0.75f);

// Custom class with good hashCode
class OptimizedKey {
    private String value;
    
    public OptimizedKey(String value) { this.value = value; }
    
    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0; // Good distribution
    }
    
    @Override
    public boolean equals(Object obj) {
        return Objects.equals(value, ((OptimizedKey) obj).value);
    }
}
```

**🐞 Fixes:** Increase initial capacity, use better hash functions, and monitor memory.

---

## 4. Multithreading and Concurrency

### Basic Questions

1. **What is a thread in Java, and how do you create one?**

**🧩 Foundation:** A thread is a lightweight process for concurrent execution. Java supports multithreading via the `Thread` class and `Runnable` interface.

**🔁 Flow:**
```java
// Extending Thread
class MyThread extends Thread {
    public void run() { 
        System.out.println("Thread running: " + Thread.currentThread().getName()); 
    }
}

// Implementing Runnable
class MyRunnable implements Runnable {
    public void run() { 
        System.out.println("Runnable running: " + Thread.currentThread().getName()); 
    }
}

// Usage
MyThread thread1 = new MyThread();
thread1.start();

Thread thread2 = new Thread(new MyRunnable());
thread2.start();
```
**🐞 Fixes:** Prefer `Runnable` for flexibility and separation of task from thread.

2. **What is the difference between `Thread` and `Runnable`?**

**🧩 Foundation:**
- `Thread` is a class; you subclass it to create a thread.
- `Runnable` is a functional interface; you implement it and pass to a `Thread`.

**🔁 Flow:**
```java
// Extending Thread
class MyThread extends Thread {
    public void run() { 
        System.out.println("Thread running: " + Thread.currentThread().getName()); 
    }
}

// Implementing Runnable
class MyRunnable implements Runnable {
    public void run() { 
        System.out.println("Runnable running: " + Thread.currentThread().getName()); 
    }
}

// Usage
MyThread thread1 = new MyThread();
thread1.start();

Thread thread2 = new Thread(new MyRunnable());
thread2.start();
```

**🐞 Fixes:** Use `Runnable` for better OOP design and to allow multiple inheritance.

### Intermediate Questions

1. **What is the difference between synchronized block and synchronized method?**

**🧩 Foundation:**
- Synchronized method locks the entire method on the object/class.
- Synchronized block locks only the specified code block, allowing finer control.

**🔁 Flow:**
```java
class Counter {
    private int count = 0;
    
    // Synchronized method - locks entire method
    public synchronized void increment() {
        count++;
    }
    
    // Synchronized block - locks only critical section
    public void incrementWithBlock() {
        // Non-critical code here
        synchronized(this) {
            count++; // Only this part is synchronized
        }
        // More non-critical code here
    }
    
    public int getCount() { return count; }
}
```

**🐞 Fixes:** Use synchronized blocks for better performance and to reduce contention.

2. **Explain the `ExecutorService` and how it simplifies thread management.**

**🧩 Foundation:** `ExecutorService` is a high-level API for managing thread pools and asynchronous task execution.

**🔁 Flow:**
```java
ExecutorService executor = Executors.newFixedThreadPool(5);
executor.submit(() -> System.out.println("Task executed"));
executor.shutdown();
```

**🐞 Fixes:** Use `ExecutorService` for scalable, manageable concurrency.

3. **What is the purpose of the `volatile` keyword in Java?**

**🧩 Foundation:** `volatile` ensures visibility of changes to variables across threads. Prevents caching of variables in thread-local memory.

**🔁 Flow:**
```java
class SharedResource {
    private volatile boolean flag = false;
    
    public void setFlag() {
        flag = true; // Visible to all threads immediately
    }
    
    public boolean getFlag() {
        return flag; // Always reads latest value
    }
}

// Usage in multiple threads
SharedResource resource = new SharedResource();
// Thread 1: resource.setFlag();
// Thread 2: if (resource.getFlag()) { ... }
```

**🐞 Fixes:** Use for flags and simple state, not for compound actions.

### Advanced Questions

1. **How does the `ForkJoinPool` work, and when is it appropriate to use?**

**🧩 Foundation:** `ForkJoinPool` is designed for parallelism using the fork/join framework, ideal for divide-and-conquer tasks.

**🔁 Flow:**
```java
class ArraySumTask extends RecursiveTask<Long> {
    private final int[] array;
    private final int start, end;
    
    public ArraySumTask(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }
    
    @Override
    protected Long compute() {
        if (end - start <= 1000) {
            // Base case - compute directly
            long sum = 0;
            for (int i = start; i < end; i++) sum += array[i];
            return sum;
        } else {
            // Fork into subtasks
            int mid = (start + end) / 2;
            ArraySumTask left = new ArraySumTask(array, start, mid);
            ArraySumTask right = new ArraySumTask(array, mid, end);
            left.fork();
            return right.compute() + left.join();
        }
    }
}

// Usage
ForkJoinPool pool = new ForkJoinPool();
int[] array = new int[10000];
// ... fill array
long sum = pool.invoke(new ArraySumTask(array, 0, array.length));
```

**🐞 Fixes:** Use for recursive, parallelizable tasks (e.g., parallel streams).

2. **What are the differences between `ReentrantLock` and `synchronized`?**

**🧩 Foundation:**
- `ReentrantLock` offers advanced locking (tryLock, timed lock, fairness, interruptibility).
- `synchronized` is simpler, built-in, and less flexible.

**🔁 Flow:**
```java
class LockExample {
    private final ReentrantLock lock = new ReentrantLock();
    private int count = 0;
    
    public void incrementWithLock() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }
    
    public void incrementWithTryLock() {
        if (lock.tryLock(100, TimeUnit.MILLISECONDS)) {
            try {
                count++;
            } finally {
                lock.unlock();
            }
        }
    }
    
    public synchronized void incrementWithSync() {
        count++; // Simpler but less flexible
    }
}
```

**🐞 Fixes:** Use `ReentrantLock` for advanced scenarios, `synchronized` for simplicity.

### Tough Questions

1. **How would you implement a thread-safe producer-consumer pattern using `BlockingQueue`?**

**🧩 Foundation:** Use `BlockingQueue` to decouple producers and consumers, handling synchronization internally.

**🔁 Flow:**
```java
BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

// Producer
class Producer implements Runnable {
    private BlockingQueue<Integer> queue;
    
    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }
    
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                queue.put(i); // Blocks if queue is full
                System.out.println("Produced: " + i);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

// Consumer
class Consumer implements Runnable {
    private BlockingQueue<Integer> queue;
    
    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }
    
    public void run() {
        try {
            while (true) {
                Integer value = queue.take(); // Blocks if queue is empty
                System.out.println("Consumed: " + value);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

// Usage
ExecutorService executor = Executors.newFixedThreadPool(2);
executor.submit(new Producer(queue));
executor.submit(new Consumer(queue));
```

**🐞 Fixes:** Use `BlockingQueue` for safe, efficient producer-consumer patterns.

2. **How do you handle deadlock scenarios in a multi-threaded application?**

**🧩 Foundation:** Deadlock occurs when threads wait indefinitely for resources held by each other.

**🔁 Flow:**
```java
class DeadlockExample {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();
    
    // Deadlock-prone code
    public void method1() {
        synchronized(lock1) {
            synchronized(lock2) {
                System.out.println("Method 1");
            }
        }
    }
    
    public void method2() {
        synchronized(lock2) {
            synchronized(lock1) { // Different order - potential deadlock
                System.out.println("Method 2");
            }
        }
    }
    
    // Solution: Consistent lock ordering
    public void safeMethod1() {
        synchronized(lock1) {
            synchronized(lock2) {
                System.out.println("Safe Method 1");
            }
        }
    }
    
    public void safeMethod2() {
        synchronized(lock1) { // Same order as method1
            synchronized(lock2) {
                System.out.println("Safe Method 2");
            }
        }
    }
}
```

**🐞 Fixes:**
- Avoid nested locks
- Use lock ordering
- Use tryLock with timeout
- Detect and recover from deadlocks

### Situational / Real-world Questions

1. **Your application is facing thread contention issues in a high-traffic system. How would you diagnose and resolve it?**

**🧩 Foundation:**
- Use thread dumps and profilers (e.g., VisualVM, JMC)
- Identify hotspots and lock contention
- Refactor to reduce lock scope, use concurrent collections

**🔁 Flow:**
```java
// Before: High contention
class ContendedCounter {
    private int count = 0;
    
    public synchronized void increment() {
        count++; // Entire method synchronized
    }
    
    public synchronized int getCount() {
        return count; // Reading also synchronized
    }
}

// After: Reduced contention
class OptimizedCounter {
    private final AtomicInteger count = new AtomicInteger(0);
    
    public void increment() {
        count.incrementAndGet(); // Lock-free operation
    }
    
    public int getCount() {
        return count.get(); // No synchronization needed
    }
}

// Alternative: Use ReadWriteLock for read-heavy scenarios
class ReadWriteCounter {
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private int count = 0;
    
    public void increment() {
        lock.writeLock().lock();
        try {
            count++;
        } finally {
            lock.writeLock().unlock();
        }
    }
    
    public int getCount() {
        lock.readLock().lock();
        try {
            return count; // Multiple readers can proceed
        } finally {
            lock.readLock().unlock();
        }
    }
}
```

**🐞 Fixes:** Minimize synchronized blocks, use lock-free algorithms where possible.

---

## 5. Exception Handling

### Basic Questions

1. **What is the difference between checked and unchecked exceptions in Java?**

**🧩 Foundation:**
- **Checked exceptions:** Subclasses of `Exception` (excluding `RuntimeException`); must be declared or handled.
- **Unchecked exceptions:** Subclasses of `RuntimeException`; not required to be declared or handled.

**🔁 Flow:**
```java
// Checked exception - must be handled
public void readFile() throws IOException {
    FileReader reader = new FileReader("file.txt");
    reader.close();
}

// Unchecked exception - no handling required
public void divide(int a, int b) {
    if (b == 0) {
        throw new IllegalArgumentException("Cannot divide by zero");
    }
    int result = a / b;
}

// Usage
try {
    readFile(); // Must handle IOException
} catch (IOException e) {
    System.out.println("File error: " + e.getMessage());
}

divide(10, 0); // No try-catch required, but will throw exception
```

**🐞 Fixes:** Use checked exceptions for recoverable conditions, unchecked for programming errors.

2. **What is the purpose of the try-catch block?**

**🧩 Foundation:** try-catch handles exceptions, allowing graceful error recovery and preventing program crashes.

**🔁 Flow:**
```java
try {
    int x = 1 / 0;
} catch (ArithmeticException e) {
    System.out.println("Error: " + e.getMessage());
}
```

**🐞 Fixes:** Catch specific exceptions, avoid empty catch blocks.

### Intermediate Questions

1. **What is the difference between `throw` and `throws` in Java?**

**🧩 Foundation:**
- `throw` is used to explicitly throw an exception.
- `throws` declares that a method may throw exceptions.

**🔁 Flow:**
```java
// throws - declares exception
public void processFile(String filename) throws IOException {
    FileReader reader = new FileReader(filename);
    reader.close();
}

// throw - explicitly throws exception
public void validateAge(int age) {
    if (age < 0) {
        throw new IllegalArgumentException("Age cannot be negative");
    }
    if (age > 150) {
        throw new IllegalArgumentException("Age seems invalid");
    }
}

// Usage
try {
    processFile("data.txt");
} catch (IOException e) {
    System.out.println("File error: " + e.getMessage());
}

validateAge(-5); // Throws IllegalArgumentException
```

**🐞 Fixes:** Use `throws` for checked exceptions, `throw` to raise exceptions.

2. **How do you create a custom exception in Java?**

**🧩 Foundation:** Extend `Exception` or `RuntimeException`.

**🔁 Flow:**
```java
// Custom checked exception
class InsufficientFundsException extends Exception {
    private double amount;
    private double balance;
    
    public InsufficientFundsException(double amount, double balance) {
        super("Insufficient funds. Required: " + amount + ", Available: " + balance);
        this.amount = amount;
        this.balance = balance;
    }
    
    public double getAmount() { return amount; }
    public double getBalance() { return balance; }
}

// Custom unchecked exception
class InvalidUserException extends RuntimeException {
    public InvalidUserException(String message) {
        super(message);
    }
}

// Usage
class BankAccount {
    private double balance = 1000;
    
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException(amount, balance);
        }
        balance -= amount;
    }
    
    public void setUser(String user) {
        if (user == null || user.trim().isEmpty()) {
            throw new InvalidUserException("User cannot be null or empty");
        }
        // Process user
    }
}
```

**🐞 Fixes:** Provide constructors and meaningful messages.

### Advanced Questions

1. **What is the role of the `finally` block, and when might it not execute?**

**🧩 Foundation:** `finally` always executes after try-catch, used for cleanup. It may not execute if JVM exits or thread is killed.

**🔁 Flow:**
```java
public void processData() {
    FileReader reader = null;
    try {
        reader = new FileReader("data.txt");
        // Process file
        System.out.println("Processing file...");
    } catch (IOException e) {
        System.out.println("Error reading file: " + e.getMessage());
    } finally {
        if (reader != null) {
            try {
                reader.close(); // Always executed
                System.out.println("File closed");
            } catch (IOException e) {
                System.out.println("Error closing file: " + e.getMessage());
            }
        }
    }
}

// finally won't execute if JVM exits
public void criticalOperation() {
    try {
        System.exit(0); // JVM exits, finally won't execute
    } finally {
        System.out.println("This won't print");
    }
}
```

**🐞 Fixes:** Use for resource cleanup, but don't rely on it for critical operations.

2. **How do you use try-with-resources, and what are its benefits?**

**🧩 Foundation:** try-with-resources auto-closes resources implementing `AutoCloseable`.

**🔁 Flow:**
```java
// Before try-with-resources
public void readFileOld() {
    BufferedReader reader = null;
    try {
        reader = new BufferedReader(new FileReader("file.txt"));
        String line = reader.readLine();
        System.out.println(line);
    } catch (IOException e) {
        System.out.println("Error: " + e.getMessage());
    } finally {
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                System.out.println("Error closing: " + e.getMessage());
            }
        }
    }
}

// With try-with-resources
public void readFileNew() {
    try (BufferedReader reader = new BufferedReader(new FileReader("file.txt"))) {
        String line = reader.readLine();
        System.out.println(line);
    } catch (IOException e) {
        System.out.println("Error: " + e.getMessage());
    }
    // reader automatically closed
}

// Custom AutoCloseable resource
class DatabaseConnection implements AutoCloseable {
    public void connect() { System.out.println("Connected to database"); }
    public void execute(String query) { System.out.println("Executing: " + query); }
    
    @Override
    public void close() {
        System.out.println("Closing database connection");
    }
}

// Usage
public void processDatabase() {
    try (DatabaseConnection db = new DatabaseConnection()) {
        db.connect();
        db.execute("SELECT * FROM users");
    } catch (Exception e) {
        System.out.println("Database error: " + e.getMessage());
    }
    // db automatically closed
}
```

**🐞 Fixes:** Prevents resource leaks, simplifies code.

### Tough Questions

1. **How would you design an exception handling strategy for a large-scale enterprise application?**

**🧩 Foundation:**
- Centralize exception handling (e.g., global exception handler)
- Use custom exceptions for business logic
- Log and monitor exceptions

**🔁 Flow:**
```java
// Custom exception hierarchy
abstract class BusinessException extends Exception {
    private String errorCode;
    
    public BusinessException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
    
    public String getErrorCode() { return errorCode; }
}

class UserNotFoundException extends BusinessException {
    public UserNotFoundException(String userId) {
        super("User not found: " + userId, "USER_001");
    }
}

class InsufficientPermissionsException extends BusinessException {
    public InsufficientPermissionsException(String operation) {
        super("Insufficient permissions for: " + operation, "PERM_001");
    }
}

// Global exception handler (Spring-like)
@ControllerAdvice
class GlobalExceptionHandler {
    
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
        ErrorResponse error = new ErrorResponse(e.getErrorCode(), e.getMessage());
        return ResponseEntity.badRequest().body(error);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception e) {
        // Log the full exception
        logger.error("Unexpected error", e);
        
        // Return generic message to client
        ErrorResponse error = new ErrorResponse("SYS_001", "An unexpected error occurred");
        return ResponseEntity.status(500).body(error);
    }
}
```

**🐞 Fixes:** Use frameworks (e.g., Spring @ControllerAdvice), avoid exposing stack traces to clients.

### Situational / Real-world Questions

1. **Your REST API throws intermittent exceptions in production. How would you implement robust error handling?**

**🧩 Foundation:**
- Use global exception handlers
- Return meaningful error responses
- Log and monitor errors

**🔁 Flow:**
```java
// Error response model
class ErrorResponse {
    private String code;
    private String message;
    private String timestamp;
    
    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
        this.timestamp = Instant.now().toString();
    }
    // getters and setters
}

// REST controller with exception handling
@RestController
class UserController {
    
    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUser(@PathVariable String id) {
        try {
            User user = userService.findById(id);
            if (user == null) {
                throw new UserNotFoundException(id);
            }
            return ResponseEntity.ok(user);
        } catch (UserNotFoundException e) {
            ErrorResponse error = new ErrorResponse("USER_001", e.getMessage());
            return ResponseEntity.status(404).body(error);
        } catch (Exception e) {
            // Log the full exception
            logger.error("Error retrieving user: " + id, e);
            
            // Return generic error to client
            ErrorResponse error = new ErrorResponse("SYS_001", "Unable to retrieve user");
            return ResponseEntity.status(500).body(error);
        }
    }
}
```

**🐞 Fixes:** Implement error codes, user-friendly messages, and alerting.

---

## 6. Java 8+ Features

### Basic Questions

1. **What are the key features introduced in Java 8?**

**🧩 Foundation:**
- Lambda expressions
- Stream API
- Functional interfaces
- Default and static methods in interfaces
- java.time API
- Optional class
- CompletableFuture

**🔁 Flow:**
```java
// Lambda expressions
List<String> names = Arrays.asList("John", "Jane", "Bob");
names.forEach(name -> System.out.println("Hello " + name));

// Stream API
List<String> filtered = names.stream()
    .filter(name -> name.startsWith("J"))
    .map(String::toUpperCase)
    .collect(Collectors.toList());

// Optional
Optional<String> optional = Optional.ofNullable(getName());
String result = optional.orElse("Default");

// Default methods in interfaces
interface PaymentProcessor {
    default void process() {
        System.out.println("Processing payment...");
    }
}

// java.time API
LocalDate today = LocalDate.now();
LocalDateTime now = LocalDateTime.now();
Duration duration = Duration.between(start, end);
```

**🐞 Fixes:** Use these features for cleaner, more functional code.

2. **What is a lambda expression, and how is it used in Java?**

**🧩 Foundation:** Lambda is a concise way to represent an anonymous function.

**🔁 Flow:**
```java
// Before lambda
List<String> names = Arrays.asList("John", "Jane", "Bob");
names.forEach(new Consumer<String>() {
    @Override
    public void accept(String name) {
        System.out.println(name);
    }
});

// With lambda
names.forEach(name -> System.out.println(name));

// Lambda with multiple parameters
Map<String, Integer> scores = new HashMap<>();
scores.put("John", 85);
scores.put("Jane", 92);

scores.forEach((name, score) -> 
    System.out.println(name + " scored " + score));

// Lambda with custom functional interface
@FunctionalInterface
interface MathOperation {
    int operate(int a, int b);
}

MathOperation add = (a, b) -> a + b;
MathOperation multiply = (a, b) -> a * b;

System.out.println(add.operate(5, 3)); // 8
System.out.println(multiply.operate(5, 3)); // 15
```

**🐞 Fixes:** Use lambdas for functional programming and cleaner code.

### Intermediate Questions

1. **What is the Stream API, and how does it differ from traditional loops?**

**🧩 Foundation:** Stream API processes collections in a functional, declarative style, supporting operations like map, filter, reduce.

**🔁 Flow:**
```java
List<String> names = Arrays.asList("John", "Jane", "Bob", "Alice", "Jack");

// Traditional loop
List<String> filteredNames = new ArrayList<>();
for (String name : names) {
    if (name.startsWith("J")) {
        filteredNames.add(name.toUpperCase());
    }
}

// Stream API
List<String> streamFiltered = names.stream()
    .filter(name -> name.startsWith("J"))
    .map(String::toUpperCase)
    .collect(Collectors.toList());

// More stream operations
long count = names.stream()
    .filter(name -> name.length() > 3)
    .count();

String firstMatch = names.stream()
    .filter(name -> name.startsWith("A"))
    .findFirst()
    .orElse("Not found");

List<String> sorted = names.stream()
    .sorted()
    .collect(Collectors.toList());
```

**🐞 Fixes:** Use streams for concise, parallelizable data processing.

2. **Explain the difference between `map` and `flatMap` in the Stream API.**

**🧩 Foundation:**
- `map` transforms each element to another object.
- `flatMap` flattens nested structures (e.g., List<List<T>> to List<T>).

**🔁 Flow:**
```java
List<String> words = Arrays.asList("Hello", "World");

// map - transforms each element
List<Integer> lengths = words.stream()
    .map(String::length)
    .collect(Collectors.toList());
// Result: [5, 5]

// flatMap - flattens nested structures
List<String> characters = words.stream()
    .flatMap(word -> Arrays.stream(word.split("")))
    .collect(Collectors.toList());
// Result: [H, e, l, l, o, W, o, r, l, d]

// Another flatMap example
List<List<Integer>> numbers = Arrays.asList(
    Arrays.asList(1, 2, 3),
    Arrays.asList(4, 5, 6),
    Arrays.asList(7, 8, 9)
);

List<Integer> flattened = numbers.stream()
    .flatMap(List::stream)
    .collect(Collectors.toList());
// Result: [1, 2, 3, 4, 5, 6, 7, 8, 9]
```

**🐞 Fixes:** Use `flatMap` for flattening, `map` for one-to-one transformations.

3. **What is the purpose of the `Optional` class in Java?**

**🧩 Foundation:** `Optional` is a container for potentially-null values, helping to avoid `NullPointerException`.

**🔁 Flow:**
```java
// Creating Optional
Optional<String> optional1 = Optional.of("Hello");
Optional<String> optional2 = Optional.ofNullable(getName()); // may be null
Optional<String> optional3 = Optional.empty();

// Using Optional
optional1.ifPresent(name -> System.out.println("Name: " + name));

String result1 = optional2.orElse("Default");
String result2 = optional2.orElseGet(() -> "Generated default");
String result3 = optional2.orElseThrow(() -> new RuntimeException("Name not found"));

// Chaining with Optional
Optional<String> processed = optional2
    .filter(name -> name.length() > 3)
    .map(String::toUpperCase);

// Optional with custom class
class User {
    private String name;
    private String email;
    
    public Optional<String> getName() { return Optional.ofNullable(name); }
    public Optional<String> getEmail() { return Optional.ofNullable(email); }
}

User user = new User();
String displayName = user.getName()
    .orElse("Anonymous");
```

**🐞 Fixes:** Use `Optional` for return types, not fields or parameters.

### Advanced Questions

1. **How does the `CompletableFuture` class support asynchronous programming?**

**🧩 Foundation:** `CompletableFuture` enables non-blocking, async computation chains.

**🔁 Flow:**
```java
// Basic CompletableFuture
CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
    // Simulate long-running task
    try { Thread.sleep(1000); } catch (InterruptedException e) { }
    return "Hello World";
});

// Chaining operations
CompletableFuture<String> processed = future
    .thenApply(String::toUpperCase)
    .thenApply(s -> "Processed: " + s);

// Combining futures
CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "World");

CompletableFuture<String> combined = future1
    .thenCombine(future2, (s1, s2) -> s1 + " " + s2);

// Error handling
CompletableFuture<String> withErrorHandling = CompletableFuture
    .supplyAsync(() -> {
        if (Math.random() > 0.5) {
            throw new RuntimeException("Random error");
        }
        return "Success";
    })
    .exceptionally(throwable -> "Error occurred: " + throwable.getMessage());

// Real-world example
CompletableFuture<User> userFuture = CompletableFuture.supplyAsync(() -> 
    userService.findById(userId));

CompletableFuture<List<Order>> ordersFuture = CompletableFuture.supplyAsync(() -> 
    orderService.findByUserId(userId));

CompletableFuture<UserProfile> profileFuture = userFuture
    .thenCombine(ordersFuture, (user, orders) -> {
        UserProfile profile = new UserProfile();
        profile.setUser(user);
        profile.setOrders(orders);
        return profile;
    });
```

**🐞 Fixes:** Use for async workflows, handle exceptions with `exceptionally`.

2. **What are the benefits of using default methods in interfaces?**

**🧩 Foundation:** Default methods allow interfaces to have concrete methods, enabling interface evolution without breaking implementations.

**🔁 Flow:**
```java
// Interface with default method
interface PaymentProcessor {
    void processPayment(double amount);
    
    default void validateAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
    }
    
    default void logTransaction(double amount) {
        System.out.println("Processing payment: $" + amount);
    }
}

// Implementation can use default methods
class CreditCardProcessor implements PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        validateAmount(amount); // Uses default method
        logTransaction(amount); // Uses default method
        System.out.println("Processing credit card payment");
    }
}

// New implementation can override default methods
class PayPalProcessor implements PaymentProcessor {
    @Override
    public void processPayment(double amount) {
        validateAmount(amount);
        System.out.println("Processing PayPal payment");
    }
    
    @Override
    public void logTransaction(double amount) {
        System.out.println("PayPal transaction: $" + amount);
    }
}
```

**🐞 Fixes:** Use for backward compatibility, but avoid excessive logic in interfaces.

### Tough Questions

1. **How would you process a large dataset using parallel streams while avoiding common pitfalls?**

**🧩 Foundation:**
- Use parallel streams for CPU-bound, stateless, non-blocking operations
- Avoid shared mutable state
- Monitor performance and thread pool usage

**🔁 Flow:**
```java
// Good use of parallel streams
List<Integer> numbers = IntStream.range(0, 1000000)
    .boxed()
    .collect(Collectors.toList());

// CPU-intensive operation - good for parallel
long sum = numbers.parallelStream()
    .mapToLong(Integer::longValue)
    .sum();

// Avoid shared mutable state
List<String> results = Collections.synchronizedList(new ArrayList<>());
numbers.parallelStream()
    .forEach(n -> results.add("Processed: " + n)); // BAD - shared mutable state

// Better approach
List<String> results2 = numbers.parallelStream()
    .map(n -> "Processed: " + n)
    .collect(Collectors.toList()); // Thread-safe collection

// Custom thread pool for parallel streams
ForkJoinPool customPool = new ForkJoinPool(4);
long result = customPool.submit(() -> 
    numbers.parallelStream()
        .mapToLong(Integer::longValue)
        .sum()
).get();
```

**🐞 Fixes:** Profile before using parallel streams, avoid for I/O-bound tasks.

### Situational / Real-world Questions

1. **You need to optimize a data processing pipeline using Java streams. How would you approach it?**

**🧩 Foundation:**
- Use lazy evaluation, short-circuiting operations
- Minimize intermediate collections
- Profile and parallelize if appropriate

**🔁 Flow:**
```java
// Inefficient pipeline
List<String> names = Arrays.asList("John", "Jane", "Bob", "Alice", "Jack");

List<String> inefficient = names.stream()
    .filter(name -> name.startsWith("J"))
    .map(String::toUpperCase)
    .collect(Collectors.toList())
    .stream() // Creates intermediate collection
    .filter(name -> name.length() > 3)
    .collect(Collectors.toList());

// Optimized pipeline
List<String> optimized = names.stream()
    .filter(name -> name.startsWith("J"))
    .map(String::toUpperCase)
    .filter(name -> name.length() > 3)
    .collect(Collectors.toList());

// Using short-circuiting
Optional<String> firstMatch = names.stream()
    .filter(name -> name.startsWith("J"))
    .findFirst(); // Stops after first match

// Lazy evaluation
Stream<String> lazyStream = names.stream()
    .filter(name -> {
        System.out.println("Filtering: " + name);
        return name.startsWith("J");
    })
    .map(name -> {
        System.out.println("Mapping: " + name);
        return name.toUpperCase();
    });

// Nothing happens until terminal operation
System.out.println("About to process...");
List<String> result = lazyStream.collect(Collectors.toList());
```

**🐞 Fixes:** Use efficient stream operations, avoid unnecessary boxing/unboxing.

---

## 7. JVM Internals and Garbage Collection

### Basic Questions

1. **What is the JVM, and what are its main components?**

**🧩 Foundation:** The JVM (Java Virtual Machine) is the engine that runs Java bytecode. Its main components are:
- **Class Loader:** Loads class files.
- **Runtime Data Areas:** Includes Heap, Stack, Method Area, PC Register, Native Method Stack.
- **Execution Engine:** Interprets bytecode or uses JIT compilation.
- **Garbage Collector:** Manages memory.

**🔁 Flow:**
```java
// JVM components in action
public class JVMExample {
    // Static variables go to Method Area
    private static final String CONSTANT = "Hello";
    
    // Instance variables go to Heap
    private String instanceVar = "Instance";
    
    public void method() {
        // Local variables go to Stack
        int localVar = 42;
        String localString = "Local";
        
        // Objects created on Heap
        Object obj = new Object();
        
        // Method calls create stack frames
        anotherMethod();
    }
    
    private void anotherMethod() {
        // New stack frame created
        System.out.println("Method executed");
    }
}

// JVM startup and class loading
public class JVMStartup {
    public static void main(String[] args) {
        // 1. Bootstrap Class Loader loads core classes
        // 2. Extension Class Loader loads extension classes
        // 3. Application Class Loader loads application classes
        
        System.out.println("JVM started successfully");
        
        // Class loading example
        try {
            Class<?> clazz = Class.forName("java.lang.String");
            System.out.println("String class loaded: " + clazz.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
```

**🐞 Fixes:** Understanding JVM internals helps with performance tuning and debugging.

2. **What is garbage collection in Java?**

**🧩 Foundation:** Garbage collection (GC) is the process by which the JVM automatically frees memory by removing objects that are no longer reachable.

**🔁 Flow:**
```java
public class GarbageCollectionExample {
    public static void main(String[] args) {
        // Objects created
        Object obj1 = new Object();
        Object obj2 = new Object();
        
        // Reference to obj1 is lost - eligible for GC
        obj1 = null;
        
        // Force garbage collection (not recommended in production)
        System.gc();
        
        // Check memory before and after
        Runtime runtime = Runtime.getRuntime();
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        
        // Create many objects
        for (int i = 0; i < 1000000; i++) {
            new Object();
        }
        
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Memory used: " + (memoryAfter - memoryBefore) + " bytes");
    }
}

// Weak reference example - objects can be GC'd
class WeakReferenceExample {
    public static void main(String[] args) {
        Object strongRef = new Object();
        WeakReference<Object> weakRef = new WeakReference<>(strongRef);
        
        System.out.println("Weak reference: " + weakRef.get());
        
        // Remove strong reference
        strongRef = null;
        
        // Force GC
        System.gc();
        
        // Weak reference may be null now
        System.out.println("Weak reference after GC: " + weakRef.get());
    }
}
```

**🐞 Fixes:** Rely on GC for memory management, but be aware of its impact on performance.

### Intermediate Questions

1. **What are the different types of garbage collectors in Java?**

**🧩 Foundation:**
- **Serial GC:** Single-threaded, for small applications.
- **Parallel GC:** Multi-threaded, throughput-oriented.
- **CMS (Concurrent Mark Sweep):** Low pause times, deprecated in Java 14.
- **G1 GC:** Predictable pause times, for large heaps.
- **ZGC, Shenandoah:** Low-latency, scalable collectors (Java 11+).

**🔁 Flow:**
```java
// JVM flags for different GC types
public class GCConfiguration {
    public static void main(String[] args) {
        // Check current GC
        String gcName = System.getProperty("java.vm.name");
        System.out.println("JVM: " + gcName);
        
        // Memory info
        Runtime runtime = Runtime.getRuntime();
        System.out.println("Max memory: " + runtime.maxMemory() / 1024 / 1024 + " MB");
        System.out.println("Total memory: " + runtime.totalMemory() / 1024 / 1024 + " MB");
        System.out.println("Free memory: " + runtime.freeMemory() / 1024 / 1024 + " MB");
        
        // GC monitoring
        long startTime = System.currentTimeMillis();
        System.gc(); // Request GC
        long endTime = System.currentTimeMillis();
        System.out.println("GC time: " + (endTime - startTime) + " ms");
    }
}

// Example JVM flags for different GCs:
// Serial GC: -XX:+UseSerialGC
// Parallel GC: -XX:+UseParallelGC
// G1 GC: -XX:+UseG1GC
// ZGC: -XX:+UseZGC (Java 11+)
```

**🐞 Fixes:** Choose the right GC for your application's needs.

2. **How does the String pool work in Java?**

**🧩 Foundation:** The String pool is a special memory region where string literals are stored to save memory and improve performance. Strings created with literals are interned.

**🔁 Flow:**
```java
public class StringPoolExample {
    public static void main(String[] args) {
        // String literals - stored in pool
        String s1 = "Hello";
        String s2 = "Hello";
        System.out.println("s1 == s2: " + (s1 == s2)); // true - same reference
        
        // String objects - not in pool initially
        String s3 = new String("Hello");
        String s4 = new String("Hello");
        System.out.println("s3 == s4: " + (s3 == s4)); // false - different objects
        
        // Intern strings to add to pool
        String s5 = s3.intern();
        System.out.println("s1 == s5: " + (s1 == s5)); // true - now in pool
        
        // Concatenation and pool
        String s6 = "Hello" + " World"; // Compile-time concatenation
        String s7 = "Hello World";
        System.out.println("s6 == s7: " + (s6 == s7)); // true
        
        // Runtime concatenation - not in pool
        String s8 = s1 + " World";
        String s9 = "Hello World";
        System.out.println("s8 == s9: " + (s8 == s9)); // false
    }
}
```

**🐞 Fixes:** Use `String.intern()` to add strings to the pool.

### Advanced Questions

1. **Explain the difference between Young Generation and Old Generation in the JVM.**

**🧩 Foundation:**
- **Young Generation:** Where new objects are allocated; includes Eden and Survivor spaces.
- **Old Generation:** Where long-lived objects are promoted after surviving several GCs.

**🔁 Flow:**
```java
public class MemoryGenerationsExample {
    public static void main(String[] args) {
        // Young Generation - Eden space
        List<Object> youngObjects = new ArrayList<>();
        
        // Create many short-lived objects
        for (int i = 0; i < 100000; i++) {
            youngObjects.add(new Object());
        }
        
        // Most will be collected in minor GC
        youngObjects.clear();
        System.gc();
        
        // Old Generation - long-lived objects
        List<Object> oldObjects = new ArrayList<>();
        
        // Create objects that survive multiple GCs
        for (int i = 0; i < 1000; i++) {
            Object obj = new Object();
            oldObjects.add(obj);
            
            // Force minor GC every 1000 objects
            if (i % 1000 == 0) {
                System.gc();
            }
        }
        
        // These objects may be promoted to Old Generation
        System.out.println("Old generation objects: " + oldObjects.size());
    }
}

// Monitoring memory generations
class MemoryMonitor {
    public static void printMemoryInfo() {
        Runtime runtime = Runtime.getRuntime();
        
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usedMemory = totalMemory - freeMemory;
        
        System.out.println("Total Memory: " + totalMemory / 1024 / 1024 + " MB");
        System.out.println("Used Memory: " + usedMemory / 1024 / 1024 + " MB");
        System.out.println("Free Memory: " + freeMemory / 1024 / 1024 + " MB");
        
        double memoryUsage = (double) usedMemory / totalMemory * 100;
        System.out.println("Memory Usage: " + String.format("%.2f", memoryUsage) + "%");
    }
}
```

**🐞 Fixes:** Tune heap sizes and GC parameters for optimal performance.

2. **How do you tune JVM parameters for a high-throughput application?**

**🧩 Foundation:**
- `-Xms`, `-Xmx`: Set initial and max heap size.
- `-XX:NewRatio`, `-XX:SurvivorRatio`: Control heap space ratios.
- `-XX:+UseG1GC`, `-XX:MaxGCPauseMillis`: Select and tune GC.

**🔁 Flow:**
```java
public class JVMTuningExample {
    public static void main(String[] args) {
        // Monitor JVM parameters
        System.out.println("JVM Parameters:");
        System.out.println("Max Heap: " + Runtime.getRuntime().maxMemory() / 1024 / 1024 + " MB");
        System.out.println("Available Processors: " + Runtime.getRuntime().availableProcessors());
        
        // Performance test
        long startTime = System.currentTimeMillis();
        
        // Simulate high-throughput workload
        for (int i = 0; i < 1000000; i++) {
            new Object();
            if (i % 100000 == 0) {
                System.gc(); // Simulate GC pressure
            }
        }
        
        long endTime = System.currentTimeMillis();
        System.out.println("Processing time: " + (endTime - startTime) + " ms");
        
        // Memory usage after processing
        Runtime runtime = Runtime.getRuntime();
        long usedMemory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Final memory usage: " + usedMemory / 1024 / 1024 + " MB");
    }
}

// Example JVM tuning flags:
// -Xms2g -Xmx4g                    // Heap size
// -XX:NewRatio=3                   // Young:Old ratio
// -XX:SurvivorRatio=8              // Eden:Survivor ratio
// -XX:+UseG1GC                     // Use G1 GC
// -XX:MaxGCPauseMillis=200         // Target pause time
// -XX:+PrintGCDetails              // Print GC details
// -XX:+PrintGCTimeStamps           // Print GC timestamps
```

**🐞 Fixes:** Profile and adjust parameters based on application needs.

### Tough Questions

1. **How would you debug a memory leak in a Java application?**

**🧩 Foundation:**
- Use tools like VisualVM, jmap, jhat, or Eclipse MAT to analyze heap dumps.
- Look for objects that are not being garbage collected due to lingering references.

**🔁 Flow:**
```java
public class MemoryLeakExample {
    // Static collection that grows without bounds
    private static final List<Object> LEAKY_LIST = new ArrayList<>();
    
    public static void main(String[] args) {
        // Simulate memory leak
        for (int i = 0; i < 100000; i++) {
            LEAKY_LIST.add(new Object());
            
            if (i % 10000 == 0) {
                printMemoryUsage();
            }
        }
    }
    
    // Better approach - use WeakHashMap
    private static final Map<Object, String> WEAK_MAP = new WeakHashMap<>();
    
    public static void addToWeakMap(Object key, String value) {
        WEAK_MAP.put(key, value);
    }
    
    // Memory monitoring
    public static void printMemoryUsage() {
        Runtime runtime = Runtime.getRuntime();
        long usedMemory = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Memory used: " + usedMemory / 1024 / 1024 + " MB");
    }
}

// Heap dump analysis example
class HeapDumpAnalyzer {
    public static void createHeapDump() {
        try {
            // Create heap dump
            String fileName = "heapdump.hprof";
            System.out.println("Creating heap dump: " + fileName);
            
            // This would typically be done with jmap or VisualVM
            // jmap -dump:format=b,file=heapdump.hprof <pid>
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

**🐞 Fixes:** Remove unnecessary references, use weak references where appropriate.

### Situational / Real-world Questions

1. **Your application is experiencing frequent garbage collection pauses. How would you analyze and mitigate this?**

**🧩 Foundation:**
- Analyze GC logs with tools like GCViewer.
- Tune heap sizes, reduce object churn, use appropriate GC.

**🔁 Flow:**
```java
public class GCPauseAnalysis {
    public static void main(String[] args) {
        // Monitor GC pauses
        long startTime = System.currentTimeMillis();
        
        // Simulate application workload
        for (int i = 0; i < 1000000; i++) {
            // Create objects that will be collected
            new String("Object " + i);
            
            // Force GC periodically to simulate pauses
            if (i % 100000 == 0) {
                long gcStart = System.currentTimeMillis();
                System.gc();
                long gcEnd = System.currentTimeMillis();
                
                System.out.println("GC pause at iteration " + i + ": " + (gcEnd - gcStart) + " ms");
            }
        }
        
        long endTime = System.currentTimeMillis();
        System.out.println("Total time: " + (endTime - startTime) + " ms");
    }
}

// GC optimization strategies
class GCOptimization {
    // 1. Object pooling to reduce GC pressure
    private static final Queue<Object> OBJECT_POOL = new ConcurrentLinkedQueue<>();
    
    public static Object getObject() {
        Object obj = OBJECT_POOL.poll();
        return obj != null ? obj : new Object();
    }
    
    public static void returnObject(Object obj) {
        OBJECT_POOL.offer(obj);
    }
    
    // 2. Use primitive arrays instead of object arrays
    public static void usePrimitives() {
        // Better for performance
        int[] numbers = new int[1000000];
        
        // Instead of
        Integer[] objects = new Integer[1000000];
    }
    
    // 3. Avoid object churn
    public static void avoidObjectChurn() {
        // Reuse StringBuilder
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.setLength(0); // Clear instead of creating new
            sb.append("Item ").append(i);
        }
    }
}
```

**🐞 Fixes:** Optimize code to reduce allocations, tune JVM flags, consider upgrading GC.

---

## 8. Design Patterns

### Basic Questions

1. **What are design patterns, and why are they important in Java?**

**🧩 Foundation:** Design patterns are proven solutions to common software design problems. They improve code reusability, maintainability, and communication among developers.

**🔁 Flow:**
```java
// Example of applying design patterns
public class DesignPatternsExample {
    public static void main(String[] args) {
        // Singleton pattern
        DatabaseConnection db1 = DatabaseConnection.getInstance();
        DatabaseConnection db2 = DatabaseConnection.getInstance();
        System.out.println("Same instance: " + (db1 == db2));
        
        // Factory pattern
        PaymentProcessor creditCard = PaymentFactory.createPayment("CREDIT_CARD");
        PaymentProcessor paypal = PaymentFactory.createPayment("PAYPAL");
        
        // Strategy pattern
        SortingContext context = new SortingContext();
        context.setStrategy(new QuickSortStrategy());
        context.sort(new int[]{3, 1, 4, 1, 5});
        
        context.setStrategy(new BubbleSortStrategy());
        context.sort(new int[]{3, 1, 4, 1, 5});
    }
}
```

**🐞 Fixes:** Use patterns to avoid reinventing the wheel and to follow best practices.

2. **Explain the Singleton design pattern with an example.**

**🧩 Foundation:** Singleton ensures only one instance of a class exists.

**🔁 Flow:**
```java
public class Singleton {
    private static final Singleton INSTANCE = new Singleton();
    private Singleton() {}
    public static Singleton getInstance() { return INSTANCE; }
}
```

**🐞 Fixes:** Use private constructor, static instance, and thread-safe access.

### Intermediate Questions

1. **What is the Factory pattern, and when would you use it?**

**🧩 Foundation:** Factory pattern provides a way to create objects without specifying the exact class. Useful when the type of object to create is determined at runtime.

**🔁 Flow:**
```java
// Product interface
interface PaymentProcessor {
    void processPayment(double amount);
}

// Concrete products
class CreditCardProcessor implements PaymentProcessor {
    public void processPayment(double amount) {
        System.out.println("Processing credit card payment: $" + amount);
    }
}

class PayPalProcessor implements PaymentProcessor {
    public void processPayment(double amount) {
        System.out.println("Processing PayPal payment: $" + amount);
    }
}

// Factory
class PaymentFactory {
    public static PaymentProcessor createPayment(String type) {
        switch (type.toUpperCase()) {
            case "CREDIT_CARD":
                return new CreditCardProcessor();
            case "PAYPAL":
                return new PayPalProcessor();
            default:
                throw new IllegalArgumentException("Unknown payment type: " + type);
        }
    }
}

// Usage
PaymentProcessor processor = PaymentFactory.createPayment("CREDIT_CARD");
processor.processPayment(100.0);
```

**🐞 Fixes:** Use for decoupling object creation from usage.

2. **Explain the Observer pattern and its use in Java.**

**🧩 Foundation:** Observer pattern defines a one-to-many dependency so that when one object changes state, all dependents are notified.

**🔁 Flow:**
```java
// Subject interface
interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}

// Observer interface
interface Observer {
    void update(String message);
}

// Concrete subject
class NewsAgency implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private String news;
    
    public void setNews(String news) {
        this.news = news;
        notifyObservers();
    }
    
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }
    
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
    
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(news);
        }
    }
}

// Concrete observers
class NewsChannel implements Observer {
    private String name;
    
    public NewsChannel(String name) {
        this.name = name;
    }
    
    @Override
    public void update(String news) {
        System.out.println(name + " received news: " + news);
    }
}

// Usage
NewsAgency agency = new NewsAgency();
NewsChannel channel1 = new NewsChannel("CNN");
NewsChannel channel2 = new NewsChannel("BBC");

agency.registerObserver(channel1);
agency.registerObserver(channel2);

agency.setNews("Breaking news: Java 17 released!");
```

**🐞 Fixes:** Use for event-driven systems (e.g., GUI, messaging).

### Advanced Questions

1. **How does the Builder pattern help in creating complex objects?**

**🧩 Foundation:** Builder pattern separates object construction from its representation, useful for objects with many optional parameters.

**🔁 Flow:**
```java
// Complex object
class User {
    private final String name;
    private final String email;
    private final int age;
    private final String phone;
    private final String address;
    
    private User(Builder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.age = builder.age;
        this.phone = builder.phone;
        this.address = builder.address;
    }
    
    // Builder class
    public static class Builder {
        private String name;
        private String email;
        private int age;
        private String phone;
        private String address;
        
        public Builder name(String name) {
            this.name = name;
            return this;
        }
        
        public Builder email(String email) {
            this.email = email;
            return this;
        }
        
        public Builder age(int age) {
            this.age = age;
            return this;
        }
        
        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }
        
        public Builder address(String address) {
            this.address = address;
            return this;
        }
        
        public User build() {
            if (name == null || email == null) {
                throw new IllegalStateException("Name and email are required");
            }
            return new User(this);
        }
    }
    
    // Getters
    public String getName() { return name; }
    public String getEmail() { return email; }
    public int getAge() { return age; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
}

// Usage
User user = new User.Builder()
    .name("John Doe")
    .email("john@example.com")
    .age(30)
    .phone("123-456-7890")
    .address("123 Main St")
    .build();
```

**🐞 Fixes:** Use for immutable, complex objects.

2. **What is the difference between Strategy and Template Method patterns?**

**🧩 Foundation:**
- **Strategy:** Encapsulates interchangeable algorithms (e.g., sorting strategies).
- **Template Method:** Defines the skeleton of an algorithm, deferring steps to subclasses.

**🔁 Flow:**
```java
// Strategy Pattern
interface SortingStrategy {
    void sort(int[] array);
}

class QuickSortStrategy implements SortingStrategy {
    public void sort(int[] array) {
        System.out.println("Sorting with QuickSort");
        // QuickSort implementation
    }
}

class BubbleSortStrategy implements SortingStrategy {
    public void sort(int[] array) {
        System.out.println("Sorting with BubbleSort");
        // BubbleSort implementation
    }
}

class SortingContext {
    private SortingStrategy strategy;
    
    public void setStrategy(SortingStrategy strategy) {
        this.strategy = strategy;
    }
    
    public void sort(int[] array) {
        strategy.sort(array);
    }
}

// Template Method Pattern
abstract class DataProcessor {
    // Template method
    public final void process() {
        readData();
        processData();
        writeData();
    }
    
    // Abstract methods to be implemented by subclasses
    protected abstract void readData();
    protected abstract void processData();
    protected abstract void writeData();
}

class CSVProcessor extends DataProcessor {
    protected void readData() {
        System.out.println("Reading CSV data");
    }
    
    protected void processData() {
        System.out.println("Processing CSV data");
    }
    
    protected void writeData() {
        System.out.println("Writing CSV data");
    }
}

class XMLProcessor extends DataProcessor {
    protected void readData() {
        System.out.println("Reading XML data");
    }
    
    protected void processData() {
        System.out.println("Processing XML data");
    }
    
    protected void writeData() {
        System.out.println("Writing XML data");
    }
}
```

**🐞 Fixes:** Use Strategy for runtime flexibility, Template Method for code reuse.

### Tough Questions

1. **How would you implement the Chain of Responsibility pattern in a Java-based microservice?**

**🧩 Foundation:** Chain of Responsibility passes a request along a chain of handlers until one handles it.

**🔁 Flow:**
```java
// Request object
class Request {
    private String type;
    private String data;
    
    public Request(String type, String data) {
        this.type = type;
        this.data = data;
    }
    
    public String getType() { return type; }
    public String getData() { return data; }
}

// Handler interface
interface Handler {
    void handle(Request request);
    void setNext(Handler next);
}

// Abstract handler
abstract class AbstractHandler implements Handler {
    protected Handler nextHandler;
    
    @Override
    public void setNext(Handler next) {
        this.nextHandler = next;
    }
    
    protected void passToNext(Request request) {
        if (nextHandler != null) {
            nextHandler.handle(request);
        }
    }
}

// Concrete handlers
class AuthenticationHandler extends AbstractHandler {
    @Override
    public void handle(Request request) {
        System.out.println("Authenticating request: " + request.getType());
        // Authentication logic here
        passToNext(request);
    }
}

class ValidationHandler extends AbstractHandler {
    @Override
    public void handle(Request request) {
        System.out.println("Validating request: " + request.getType());
        // Validation logic here
        passToNext(request);
    }
}

class ProcessingHandler extends AbstractHandler {
    @Override
    public void handle(Request request) {
        System.out.println("Processing request: " + request.getType());
        // Processing logic here
        // No need to pass to next if this is the final handler
    }
}

// Usage
Handler auth = new AuthenticationHandler();
Handler validation = new ValidationHandler();
Handler processing = new ProcessingHandler();

auth.setNext(validation);
validation.setNext(processing);

Request request = new Request("API_CALL", "user data");
auth.handle(request);
```

**🐞 Fixes:** Use for request processing pipelines (e.g., filters, middleware).

### Situational / Real-world Questions

1. **You're designing a logging system for a distributed application. Which design patterns would you use and why?**

**🧩 Foundation:**
- **Singleton:** For a single logger instance.
- **Factory:** For creating loggers based on config.
- **Observer:** For notifying listeners.
- **Strategy:** For different logging outputs.

**🔁 Flow:**
```java
// Singleton Logger
class Logger {
    private static Logger instance;
    private List<LogHandler> handlers = new ArrayList<>();
    
    private Logger() {}
    
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }
    
    public void addHandler(LogHandler handler) {
        handlers.add(handler);
    }
    
    public void log(String message, LogLevel level) {
        for (LogHandler handler : handlers) {
            handler.handle(message, level);
        }
    }
}

// Strategy for different outputs
interface LogHandler {
    void handle(String message, LogLevel level);
}

class ConsoleHandler implements LogHandler {
    public void handle(String message, LogLevel level) {
        System.out.println("[" + level + "] " + message);
    }
}

class FileHandler implements LogHandler {
    public void handle(String message, LogLevel level) {
        // Write to file
        System.out.println("Writing to file: [" + level + "] " + message);
    }
}

class DatabaseHandler implements LogHandler {
    public void handle(String message, LogLevel level) {
        // Write to database
        System.out.println("Writing to database: [" + level + "] " + message);
    }
}

// Factory for creating handlers
class LogHandlerFactory {
    public static LogHandler createHandler(String type) {
        switch (type.toLowerCase()) {
            case "console":
                return new ConsoleHandler();
            case "file":
                return new FileHandler();
            case "database":
                return new DatabaseHandler();
            default:
                throw new IllegalArgumentException("Unknown handler type: " + type);
        }
    }
}

// Usage
Logger logger = Logger.getInstance();
logger.addHandler(LogHandlerFactory.createHandler("console"));
logger.addHandler(LogHandlerFactory.createHandler("file"));

logger.log("Application started", LogLevel.INFO);
logger.log("Error occurred", LogLevel.ERROR);
```

**🐞 Fixes:** Combine patterns for extensible, robust logging.

---

## 9. Input/Output (I/O) and Serialization

### Basic Questions

1. **What is the difference between `InputStream` and `Reader` in Java?**

**🧩 Foundation:**
- `InputStream`: Reads raw bytes (binary data).
- `Reader`: Reads characters (text data).

**🔁 Flow:**
```java
// InputStream - reading binary data
public void readBinaryFile() {
    try (InputStream is = new FileInputStream("image.jpg")) {
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = is.read(buffer)) != -1) {
            // Process binary data
            System.out.println("Read " + bytesRead + " bytes");
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

// Reader - reading text data
public void readTextFile() {
    try (Reader reader = new FileReader("text.txt")) {
        char[] buffer = new char[1024];
        int charsRead;
        while ((charsRead = reader.read(buffer)) != -1) {
            // Process text data
            String text = new String(buffer, 0, charsRead);
            System.out.println("Read: " + text);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

// BufferedReader for efficient text reading
public void readTextFileBuffered() {
    try (BufferedReader reader = new BufferedReader(new FileReader("text.txt"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println("Line: " + line);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
```

**🐞 Fixes:** Use `InputStream` for binary, `Reader` for text.

2. **What is serialization, and how is it implemented in Java?**

**🧩 Foundation:** Serialization converts an object into a byte stream for storage or transmission.

**🔁 Flow:**
```java
// Serializable class
class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int age;
    private transient String password; // Won't be serialized
    
    public Person(String name, int age, String password) {
        this.name = name;
        this.age = age;
        this.password = password;
    }
    
    // Getters
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getPassword() { return password; }
}

// Serialization
public void serializePerson() {
    Person person = new Person("John", 30, "secret123");
    
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("person.ser"))) {
        oos.writeObject(person);
        System.out.println("Person serialized successfully");
    } catch (IOException e) {
        e.printStackTrace();
    }
}

// Deserialization
public void deserializePerson() {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("person.ser"))) {
        Person person = (Person) ois.readObject();
        System.out.println("Deserialized: " + person.getName() + ", Age: " + person.getAge());
        System.out.println("Password: " + person.getPassword()); // null due to transient
    } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
    }
}
```

**🐞 Fixes:** Implement `Serializable`, use `ObjectOutputStream`/`ObjectInputStream`.

### Intermediate Questions

1. **What is the difference between `ObjectOutputStream` and `DataOutputStream`?**

**🧩 Foundation:**
- `ObjectOutputStream`: Serializes objects.
- `DataOutputStream`: Writes primitive types in a portable way.

**🔁 Flow:**
```java
// ObjectOutputStream - for objects
public void writeObject() {
    Person person = new Person("Alice", 25, "password");
    
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("object.dat"))) {
        oos.writeObject(person);
        System.out.println("Object written");
    } catch (IOException e) {
        e.printStackTrace();
    }
}

// DataOutputStream - for primitives
public void writePrimitives() {
    try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("primitives.dat"))) {
        dos.writeInt(42);
        dos.writeDouble(3.14);
        dos.writeUTF("Hello World");
        dos.writeBoolean(true);
        System.out.println("Primitives written");
    } catch (IOException e) {
        e.printStackTrace();
    }
}

// Reading primitives
public void readPrimitives() {
    try (DataInputStream dis = new DataInputStream(new FileInputStream("primitives.dat"))) {
        int number = dis.readInt();
        double pi = dis.readDouble();
        String text = dis.readUTF();
        boolean flag = dis.readBoolean();
        
        System.out.println("Read: " + number + ", " + pi + ", " + text + ", " + flag);
    } catch (IOException e) {
        e.printStackTrace();
    }
}
```

**🐞 Fixes:** Use the right stream for the data type.

2. **How do you handle large file processing in Java efficiently?**

**🧩 Foundation:**
- Use `BufferedReader`/`BufferedWriter` for text.
- Use `FileChannel` or `MappedByteBuffer` for large files.

**🔁 Flow:**
```java
// Efficient text file processing
public void processLargeTextFile() {
    try (BufferedReader reader = new BufferedReader(new FileReader("largefile.txt"));
         BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
        
        String line;
        int lineCount = 0;
        while ((line = reader.readLine()) != null) {
            // Process each line
            String processedLine = line.toUpperCase();
            writer.write(processedLine);
            writer.newLine();
            
            lineCount++;
            if (lineCount % 10000 == 0) {
                System.out.println("Processed " + lineCount + " lines");
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

// Using NIO for large files
public void processLargeFileNIO() {
    try (FileChannel inputChannel = new FileInputStream("largefile.dat").getChannel();
         FileChannel outputChannel = new FileOutputStream("output.dat").getChannel()) {
        
        // Transfer data efficiently
        long transferred = inputChannel.transferTo(0, inputChannel.size(), outputChannel);
        System.out.println("Transferred " + transferred + " bytes");
        
    } catch (IOException e) {
        e.printStackTrace();
    }
}

// Memory-mapped file for very large files
public void processWithMemoryMapping() {
    try (RandomAccessFile file = new RandomAccessFile("largefile.dat", "r")) {
        FileChannel channel = file.getChannel();
        MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
        
        // Process data in chunks
        byte[] chunk = new byte[8192];
        while (buffer.hasRemaining()) {
            int remaining = Math.min(chunk.length, buffer.remaining());
            buffer.get(chunk, 0, remaining);
            
            // Process chunk
            processChunk(chunk, remaining);
        }
        
    } catch (IOException e) {
        e.printStackTrace();
    }
}

private void processChunk(byte[] chunk, int length) {
    // Process the chunk of data
    System.out.println("Processing " + length + " bytes");
}
```

**🐞 Fixes:** Buffer I/O, process in chunks, avoid loading entire files into memory.

### Advanced Questions

1. **What is the purpose of the `transient` keyword in serialization?**

**🧩 Foundation:** `transient` marks fields to be excluded from serialization.

**🔁 Flow:**
```java
class User implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String username;
    private transient String password; // Won't be serialized
    private transient Date lastLogin; // Won't be serialized
    
    // Computed field that shouldn't be serialized
    private transient int sessionCount;
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.lastLogin = new Date();
        this.sessionCount = 0;
    }
    
    // Custom serialization
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject(); // Serialize non-transient fields
        // Add custom serialization logic if needed
    }
    
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject(); // Deserialize non-transient fields
        // Reconstruct transient fields
        this.lastLogin = new Date();
        this.sessionCount = 0;
    }
    
    // Getters
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public Date getLastLogin() { return lastLogin; }
    public int getSessionCount() { return sessionCount; }
}
```

**🐞 Fixes:** Use for sensitive or derived data.

2. **How do you implement custom serialization using `Externalizable`?**

**🧩 Foundation:** Implement `Externalizable` and override `writeExternal`/`readExternal` for custom logic.

**🔁 Flow:**
```java
class CustomPerson implements Externalizable {
    private String name;
    private int age;
    private List<String> hobbies;
    
    // Required no-arg constructor
    public CustomPerson() {
        this.hobbies = new ArrayList<>();
    }
    
    public CustomPerson(String name, int age, List<String> hobbies) {
        this.name = name;
        this.age = age;
        this.hobbies = hobbies;
    }
    
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        // Custom serialization logic
        out.writeUTF(name);
        out.writeInt(age);
        out.writeInt(hobbies.size());
        for (String hobby : hobbies) {
            out.writeUTF(hobby);
        }
    }
    
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        // Custom deserialization logic
        this.name = in.readUTF();
        this.age = in.readInt();
        int hobbyCount = in.readInt();
        this.hobbies = new ArrayList<>();
        for (int i = 0; i < hobbyCount; i++) {
            hobbies.add(in.readUTF());
        }
    }
    
    // Getters
    public String getName() { return name; }
    public int getAge() { return age; }
    public List<String> getHobbies() { return hobbies; }
}

// Usage
public void testExternalizable() {
    List<String> hobbies = Arrays.asList("Reading", "Swimming", "Coding");
    CustomPerson person = new CustomPerson("Jane", 28, hobbies);
    
    // Serialize
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("external.dat"))) {
        oos.writeObject(person);
        System.out.println("Person externalized");
    } catch (IOException e) {
        e.printStackTrace();
    }
    
    // Deserialize
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("external.dat"))) {
        CustomPerson restored = (CustomPerson) ois.readObject();
        System.out.println("Restored: " + restored.getName() + ", Hobbies: " + restored.getHobbies());
    } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
    }
}
```

**🐞 Fixes:** Use for fine-grained control over serialization.

### Tough Questions

1. **How would you optimize I/O operations for a high-throughput data streaming application?**

**🧩 Foundation:**
- Use Java NIO for non-blocking I/O.
- Use direct buffers, async channels, and batching.

**🔁 Flow:**
```java
// NIO for high-throughput I/O
public class HighThroughputIO {
    
    // Non-blocking server
    public void startNonBlockingServer() throws IOException {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.socket().bind(new InetSocketAddress(8080));
        serverChannel.configureBlocking(false);
        
        Selector selector = Selector.open();
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        
        while (true) {
            selector.select();
            Set<SelectionKey> keys = selector.selectedKeys();
            
            for (SelectionKey key : keys) {
                if (key.isAcceptable()) {
                    SocketChannel client = serverChannel.accept();
                    client.configureBlocking(false);
                    client.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    SocketChannel client = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    client.read(buffer);
                    buffer.flip();
                    // Process data
                    client.write(buffer);
                }
            }
            keys.clear();
        }
    }
    
    // Direct buffer for better performance
    public void useDirectBuffer() {
        ByteBuffer directBuffer = ByteBuffer.allocateDirect(1024);
        ByteBuffer heapBuffer = ByteBuffer.allocate(1024);
        
        // Direct buffer is better for I/O operations
        directBuffer.put("Hello World".getBytes());
        directBuffer.flip();
        
        // Transfer to file
        try (FileChannel channel = new FileOutputStream("direct.dat").getChannel()) {
            channel.write(directBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Batch processing
    public void batchProcess(List<String> data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("batch.txt"))) {
            StringBuilder batch = new StringBuilder();
            int batchSize = 1000;
            
            for (int i = 0; i < data.size(); i++) {
                batch.append(data.get(i)).append("\n");
                
                if ((i + 1) % batchSize == 0 || i == data.size() - 1) {
                    writer.write(batch.toString());
                    batch.setLength(0); // Clear buffer
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

**🐞 Fixes:** Profile and tune buffer sizes, use async I/O.

### Situational / Real-world Questions

1. **Your application needs to serialize complex objects to a database. How would you ensure compatibility across versions?**

**🧩 Foundation:**
- Use `serialVersionUID` for versioning.
- Write custom read/write logic for backward compatibility.

**🔁 Flow:**
```java
class VersionedObject implements Serializable {
    private static final long serialVersionUID = 2L; // Increment for new versions
    
    private String name;
    private int age;
    private String email; // New field in version 2
    
    // Constructor for version 1 compatibility
    public VersionedObject(String name, int age) {
        this.name = name;
        this.age = age;
        this.email = "default@example.com"; // Default value
    }
    
    // Constructor for version 2
    public VersionedObject(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }
    
    // Custom serialization for version compatibility
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        // Add version-specific logic here
    }
    
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        
        // Handle missing fields for backward compatibility
        if (email == null) {
            email = "unknown@example.com";
        }
    }
    
    // Getters
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getEmail() { return email; }
}

// Database serialization helper
class DatabaseSerializer {
    public void saveToDatabase(VersionedObject obj, Connection conn) throws SQLException {
        String sql = "INSERT INTO objects (data, version) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
                oos.writeObject(obj);
            }
            stmt.setBytes(1, baos.toByteArray());
            stmt.setLong(2, VersionedObject.serialVersionUID);
            stmt.executeUpdate();
        }
    }
    
    public VersionedObject loadFromDatabase(Connection conn) throws SQLException, IOException, ClassNotFoundException {
        String sql = "SELECT data FROM objects ORDER BY id DESC LIMIT 1";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            if (rs.next()) {
                byte[] data = rs.getBytes("data");
                try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data))) {
                    return (VersionedObject) ois.readObject();
                }
            }
        }
        return null;
    }
}
```

**🐞 Fixes:** Document changes, test deserialization across versions.

---

## 10. Security in Java

### Basic Questions

1. **How does Java ensure security at the language level?**

**🧩 Foundation:**
- Access control (private, protected, public)
- Type safety
- Automatic memory management
- Bytecode verification

**🔁 Flow:**
```java
// Access control example
public class SecureClass {
    private String secretData; // private - only accessible within class
    protected String sharedData; // protected - accessible in package and subclasses
    public String publicData; // public - accessible everywhere
    
    public void processData() {
        // Type safety - compile-time checking
        String data = "secure";
        // int number = data; // Compilation error - type safety
    }
}
```

**🐞 Fixes:** Use secure coding practices, avoid reflection for sensitive code.

2. **What is the role of the `SecurityManager` in Java?**

**🧩 Foundation:** `SecurityManager` enforces runtime security policies, restricting file, network, and other operations.

**🔁 Flow:**
```java
// SecurityManager example (deprecated in Java 17)
public class SecurityExample {
    public static void main(String[] args) {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            // Check if we can read a file
            sm.checkRead("/etc/passwd");
            // Check if we can connect to network
            sm.checkConnect("example.com", 80);
        }
    }
}
```

**🐞 Fixes:** Use for sandboxing untrusted code.

### Intermediate Questions

1. **How do you prevent SQL injection when using JDBC?**

**🧩 Foundation:** Use `PreparedStatement` to parameterize queries.

**🔁 Flow:**
```java
PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
ps.setInt(1, userId);
```
**🐞 Fixes:** Never concatenate user input into SQL.

2. **What are the best practices for secure coding in Java?**

**🧩 Foundation:**
- Validate all inputs
- Encode outputs
- Use least privilege
- Handle errors securely
- Log security events

**🔁 Flow:**
```java
public class SecureCodingExample {
    public String processUserInput(String input) {
        // 1. Validate input
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null or empty");
        }
        
        // 2. Sanitize input
        String sanitized = input.replaceAll("<script>", "");
        
        // 3. Encode output
        String encoded = StringEscapeUtils.escapeHtml4(sanitized);
        
        // 4. Log security events
        logger.info("Processed user input: {}", encoded);
        
        return encoded;
    }
}
```

**🐞 Fixes:** Follow OWASP guidelines.

### Advanced Questions

1. **How do you implement secure password storage in a Java application?**

**🧩 Foundation:**
- Use strong hash functions (e.g., bcrypt, PBKDF2)
- Use random salt per password
- Store only hash and salt

**🔁 Flow:**
```java
// Use a library like BCrypt
String hash = BCrypt.hashpw(password, BCrypt.gensalt());
```
**🐞 Fixes:** Never store plain-text passwords.

### Tough Questions

1. **How would you secure a Java application against deserialization vulnerabilities?**

**🧩 Foundation:**
- Use `ObjectInputFilter` (Java 9+)
- Avoid deserializing untrusted data
- Validate object types during deserialization

**🔁 Flow:**
```java
// Secure deserialization with ObjectInputFilter (Java 9+)
public class SecureDeserialization {
    public Object deserializeSecurely(byte[] data) throws Exception {
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data))) {
            // Set filter to only allow specific classes
            ois.setObjectInputFilter(filter -> {
                if (filter.serialClass() == null) return ObjectInputFilter.Status.ALLOWED;
                String className = filter.serialClass().getName();
                return className.startsWith("com.mycompany.") ? 
                    ObjectInputFilter.Status.ALLOWED : 
                    ObjectInputFilter.Status.REJECTED;
            });
            return ois.readObject();
        }
    }
}
```

**🐞 Fixes:** Use whitelisting, prefer JSON/XML for external data.

### Situational / Real-world Questions

1. **Your application is vulnerable to XSS attacks in a web context. How would you mitigate this using Java?**

**🧩 Foundation:**
- Sanitize user input
- Encode output (e.g., Apache Commons Text, Spring's HtmlUtils)
- Use security headers (Content-Security-Policy)

**🔁 Flow:**
```java
public class XSSProtection {
    public String sanitizeInput(String userInput) {
        if (userInput == null) return "";
        
        // Remove script tags and dangerous content
        String sanitized = userInput
            .replaceAll("<script[^>]*>.*?</script>", "")
            .replaceAll("javascript:", "")
            .replaceAll("on\\w+\\s*=", "");
            
        return sanitized;
    }
    
    public String encodeOutput(String content) {
        // HTML encode special characters
        return content
            .replace("&", "&amp;")
            .replace("<", "&lt;")
            .replace(">", "&gt;")
            .replace("\"", "&quot;")
            .replace("'", "&#x27;");
    }
}
```

**🐞 Fixes:** Use frameworks with built-in XSS protection.

---

## 11. Error Handling and Debugging

### Basic Questions

1. **What are best practices for exception handling in Java?**

**🧩 Foundation:**
- Catch specific exceptions
- Avoid empty catch blocks
- Log exceptions
- Use try-with-resources
- Don't suppress or ignore exceptions

**🔁 Flow:**
```java
public class ExceptionHandlingExample {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlingExample.class);
    
    public void processFile(String filename) {
        // Use try-with-resources for automatic cleanup
        try (FileReader reader = new FileReader(filename);
             BufferedReader br = new BufferedReader(reader)) {
            
            String line;
            while ((line = br.readLine()) != null) {
                processLine(line);
            }
            
        } catch (FileNotFoundException e) {
            // Specific exception handling
            logger.error("File not found: {}", filename, e);
            throw new RuntimeException("File not accessible", e);
            
        } catch (IOException e) {
            // Log and rethrow with context
            logger.error("Error reading file: {}", filename, e);
            throw new RuntimeException("IO error occurred", e);
        }
    }
    
    private void processLine(String line) {
        try {
            // Process the line
            Integer.parseInt(line);
        } catch (NumberFormatException e) {
            // Don't ignore - log it
            logger.warn("Invalid number format in line: {}", line);
        }
    }
}
```

**🐞 Fixes:** Centralize error handling, use logging frameworks.

2. **What is the difference between `Error` and `Exception` in Java?**

**🧩 Foundation:**
- `Error`: Serious problems (e.g., OutOfMemoryError), not meant to be caught.
- `Exception`: Recoverable conditions, can be caught and handled.

**🔁 Flow:**
```java
public class ErrorVsExceptionExample {
    public void demonstrateDifference() {
        try {
            // This throws an Exception - can be caught and handled
            throw new IllegalArgumentException("Invalid argument");
            
        } catch (Exception e) {
            System.out.println("Caught Exception: " + e.getMessage());
        }
        
        // This would throw an Error - should NOT be caught
        // try {
        //     throw new OutOfMemoryError("Memory exhausted");
        // } catch (Error e) { // DON'T DO THIS!
        //     System.out.println("Caught Error: " + e.getMessage());
        // }
    }
    
    public void handleErrorsProperly() {
        // Instead of catching Error, handle the root cause
        Runtime runtime = Runtime.getRuntime();
        long maxMemory = runtime.maxMemory();
        long usedMemory = runtime.totalMemory() - runtime.freeMemory();
        
        if (usedMemory > maxMemory * 0.8) {
            // Proactive memory management instead of catching OutOfMemoryError
            System.gc();
        }
    }
}
```

**🐞 Fixes:** Only catch `Exception`, not `Error`.

### Intermediate Questions

1. **How do you implement a global exception handling mechanism in a Java application?**

**🧩 Foundation:** Use frameworks (e.g., Spring's `@ControllerAdvice`) or a top-level handler in main.

**🔁 Flow:**
```java
// Global exception handler for Spring Boot
@ControllerAdvice
public class GlobalExceptionHandler {
    
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        logger.error("Unhandled exception occurred", ex);
        ErrorResponse error = new ErrorResponse("Internal Server Error", 500);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException ex) {
        logger.warn("Invalid argument: {}", ex.getMessage());
        ErrorResponse error = new ErrorResponse(ex.getMessage(), 400);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}

// For non-Spring applications
public class ApplicationExceptionHandler {
    public static void main(String[] args) {
        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
            System.err.println("Uncaught exception in thread: " + thread.getName());
            throwable.printStackTrace();
        });
    }
}
```

**🐞 Fixes:** Return consistent error responses, log all exceptions.

2. **How do you log exceptions effectively in a Java application?**

**🧩 Foundation:**
- Use logging frameworks (SLF4J, Log4j, java.util.logging)
- Include context (user, request, stack trace)

**🔁 Flow:**
```java
public class EffectiveLoggingExample {
    private static final Logger logger = LoggerFactory.getLogger(EffectiveLoggingExample.class);
    
    public void processUserRequest(String userId, String requestData) {
        try {
            // Include context in log messages
            logger.info("Processing request for user: {}, data: {}", userId, maskSensitiveData(requestData));
            
            // Process the request
            String result = processData(requestData);
            
            logger.info("Request completed successfully for user: {}", userId);
            
        } catch (Exception e) {
            // Log with context and appropriate level
            logger.error("Failed to process request for user: {}, error: {}", 
                        userId, e.getMessage(), e);
            
            // Include additional context
            logger.debug("Request data that caused failure: {}", maskSensitiveData(requestData));
        }
    }
    
    private String maskSensitiveData(String data) {
        // Mask sensitive information in logs
        return data.replaceAll("password=\\w+", "password=***")
                   .replaceAll("creditCard=\\d+", "creditCard=***");
    }
    
    private String processData(String data) {
        // Simulate processing
        if (data.contains("error")) {
            throw new RuntimeException("Processing error");
        }
        return "processed";
    }
}
```

**🐞 Fixes:** Avoid logging sensitive data, use log levels appropriately.

### Advanced Questions

1. **How do you debug a performance issue in a Java application in production?**

**🧩 Foundation:**
- Use profilers (VisualVM, JMC)
- Analyze thread dumps, heap dumps
- Monitor GC logs

**🔁 Flow:**
```java
public class PerformanceDebuggingExample {
    
    public void monitorPerformance() {
        // Monitor memory usage
        Runtime runtime = Runtime.getRuntime();
        long usedMemory = runtime.totalMemory() - runtime.freeMemory();
        long maxMemory = runtime.maxMemory();
        
        System.out.println("Memory usage: " + (usedMemory / 1024 / 1024) + "MB");
        System.out.println("Max memory: " + (maxMemory / 1024 / 1024) + "MB");
        
        // Monitor thread count
        ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
        int threadCount = threadBean.getThreadCount();
        System.out.println("Active threads: " + threadCount);
        
        // Generate thread dump if needed
        if (threadCount > 100) {
            generateThreadDump();
        }
    }
    
    private void generateThreadDump() {
        ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadBean.dumpAllThreads(true, true);
        
        for (ThreadInfo info : threadInfos) {
            System.out.println("Thread: " + info.getThreadName() + 
                             " State: " + info.getThreadState() +
                             " Stack: " + Arrays.toString(info.getStackTrace()));
        }
    }
    
    // Method to identify performance bottlenecks
    public void identifyBottleneck() {
        long startTime = System.currentTimeMillis();
        
        // Suspect operation
        performExpensiveOperation();
        
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        
        if (duration > 1000) { // Log if operation takes more than 1 second
            System.out.println("Performance issue detected: " + duration + "ms");
        }
    }
    
    private void performExpensiveOperation() {
        // Simulate expensive operation
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
```

**🐞 Fixes:** Profile under load, automate monitoring.

### Tough Questions

1. **A Java application crashes intermittently in production. How would you identify and resolve the issue?**

**🧩 Foundation:**
- Collect and analyze logs, thread/heap dumps
- Look for patterns (e.g., memory leaks, deadlocks)

**🐞 Fixes:** Use monitoring and alerting, automate crash analysis.

### Situational / Real-world Questions

1. **Your application logs sensitive data in error messages. How would you redesign the error handling to prevent this?**

**🧩 Foundation:**
- Centralize logging
- Mask or redact sensitive fields
- Review logs for compliance

**🐞 Fixes:** Use secure logging libraries, train developers on secure practices.

---

## 12. Java Memory Management

### Basic Questions

1. **What is the difference between stack and heap memory in Java?**

**🧩 Foundation:**
- **Stack:** Stores method frames, local variables, function calls.
- **Heap:** Stores objects, arrays, class instances.

**🔁 Flow:**
```java
public class StackVsHeapExample {
    
    // This object is stored in HEAP
    private String instanceVariable = "I'm in heap";
    
    public void demonstrateMemory() {
        // These are stored in STACK
        int localVariable = 42;
        String localString = "I'm in stack";
        
        // This object is stored in HEAP, but reference is in STACK
        List<String> heapObject = new ArrayList<>();
        heapObject.add("I'm in heap");
        
        // Method call creates new stack frame
        recursiveMethod(5);
    }
    
    private void recursiveMethod(int depth) {
        // Each call creates new stack frame with its own variables
        int localVar = depth * 2;
        
        if (depth > 0) {
            recursiveMethod(depth - 1); // New stack frame
        }
        
        // Stack overflow example (uncomment to see)
        // recursiveMethod(depth + 1); // Will cause StackOverflowError
    }
    
    public void demonstrateHeapUsage() {
        // Creating objects in heap
        StringBuilder sb = new StringBuilder(); // Object in heap
        for (int i = 0; i < 1000000; i++) {
            sb.append("data"); // Modifying heap object
        }
        
        // Large array in heap
        int[] largeArray = new int[1000000]; // Array in heap
    }
}
```

**🐞 Fixes:** Stack is fast but limited, heap is larger but managed by GC.

2. **What is the role of the `finalize` method in Java?**

**🧩 Foundation:** `finalize()` is called by GC before reclaiming an object's memory. Deprecated in Java 9+.

**🔁 Flow:**
```java
public class FinalizeExample {
    
    // DEPRECATED - Don't use in new code
    @Deprecated
    @Override
    protected void finalize() throws Throwable {
        try {
            // Cleanup code here
            System.out.println("Finalizing object: " + this);
            // Close resources, etc.
        } finally {
            super.finalize(); // Always call super.finalize()
        }
    }
    
    // BETTER APPROACH - Use try-with-resources
    public void properResourceManagement() {
        try (FileInputStream fis = new FileInputStream("file.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(fis))) {
            
            String line = reader.readLine();
            System.out.println("Read: " + line);
            
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        // Resources automatically closed here
    }
    
    // BETTER APPROACH - Implement AutoCloseable
    public static class ResourceManager implements AutoCloseable {
        private boolean closed = false;
        
        public void doSomething() {
            if (closed) {
                throw new IllegalStateException("Resource is closed");
            }
            System.out.println("Doing something...");
        }
        
        @Override
        public void close() {
            if (!closed) {
                System.out.println("Cleaning up resources...");
                closed = true;
            }
        }
    }
}
```

**🐞 Fixes:** Use try-with-resources or explicit cleanup instead.

### Intermediate Questions

1. **How does the JVM manage memory allocation for objects?**

**🧩 Foundation:**
- New objects go to Eden space (Young Gen)
- Surviving objects move to Survivor, then Old Gen
- GC reclaims unreachable objects

**🔁 Flow:**
```java
public class JVMMemoryAllocationExample {
    
    public void demonstrateMemoryAllocation() {
        // New objects go to Eden space
        List<String> shortLivedObjects = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            shortLivedObjects.add("Object " + i); // Eden space
        }
        // These objects will be garbage collected quickly
        
        // Long-lived objects survive and move to Old Gen
        List<String> longLivedObjects = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            String obj = "LongLived " + i;
            longLivedObjects.add(obj);
            
            // Simulate object surviving multiple GC cycles
            if (i % 10 == 0) {
                System.gc(); // Request garbage collection
            }
        }
        
        // Large objects may go directly to Old Gen
        byte[] largeObject = new byte[1024 * 1024]; // 1MB - might go to Old Gen
    }
    
    public void demonstrateMemoryGenerations() {
        // Young Generation (Eden + Survivor spaces)
        String[] youngObjects = new String[1000];
        for (int i = 0; i < 1000; i++) {
            youngObjects[i] = "Young Object " + i;
        }
        
        // Force minor GC
        System.gc();
        
        // Some objects survive and move to Survivor space
        // After multiple survivals, they move to Old Generation
        
        // Old Generation
        List<String> oldGenObjects = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            oldGenObjects.add("Old Gen Object " + i);
            if (i % 1000 == 0) {
                System.gc(); // Multiple GC cycles
            }
        }
    }
    
    public void monitorMemoryUsage() {
        Runtime runtime = Runtime.getRuntime();
        
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usedMemory = totalMemory - freeMemory;
        long maxMemory = runtime.maxMemory();
        
        System.out.println("Total Memory: " + (totalMemory / 1024 / 1024) + "MB");
        System.out.println("Used Memory: " + (usedMemory / 1024 / 1024) + "MB");
        System.out.println("Free Memory: " + (freeMemory / 1024 / 1024) + "MB");
        System.out.println("Max Memory: " + (maxMemory / 1024 / 1024) + "MB");
    }
}
```

**🐞 Fixes:** Tune heap and GC for your workload.

2. **What is the difference between strong, weak, and soft references in Java?**

**🧩 Foundation:**
- **Strong:** Default, prevents GC
- **Weak:** GC if no strong refs
- **Soft:** GC only if memory is low

**🔁 Flow:**
```java
import java.lang.ref.WeakReference;
import java.lang.ref.SoftReference;
import java.lang.ref.ReferenceQueue;
import java.util.WeakHashMap;

public class ReferenceTypesExample {
    
    public void demonstrateStrongReferences() {
        // Strong reference - object won't be GC'd as long as this reference exists
        String strongRef = new String("I'm strongly referenced");
        
        // Object is eligible for GC only when strongRef goes out of scope
        strongRef = null; // Now object can be garbage collected
    }
    
    public void demonstrateWeakReferences() {
        // Weak reference - object can be GC'd if no strong references exist
        String original = new String("Original object");
        WeakReference<String> weakRef = new WeakReference<>(original);
        
        System.out.println("Weak ref before GC: " + weakRef.get());
        
        original = null; // Remove strong reference
        System.gc(); // Request garbage collection
        
        System.out.println("Weak ref after GC: " + weakRef.get()); // Likely null
        
        // WeakHashMap example
        WeakHashMap<String, String> weakMap = new WeakHashMap<>();
        String key = new String("key");
        weakMap.put(key, "value");
        
        System.out.println("Map size before: " + weakMap.size());
        key = null; // Remove strong reference to key
        System.gc();
        System.out.println("Map size after GC: " + weakMap.size()); // Likely 0
    }
    
    public void demonstrateSoftReferences() {
        // Soft reference - object is GC'd only when memory is low
        String original = new String("Soft referenced object");
        SoftReference<String> softRef = new SoftReference<>(original);
        
        System.out.println("Soft ref: " + softRef.get());
        
        original = null; // Remove strong reference
        
        // Soft reference will be cleared only under memory pressure
        // This is useful for memory-sensitive caches
    }
    
    public void demonstrateReferenceQueue() {
        ReferenceQueue<String> queue = new ReferenceQueue<>();
        String original = new String("Queued object");
        WeakReference<String> weakRef = new WeakReference<>(original, queue);
        
        original = null;
        System.gc();
        
        // Check if reference was enqueued
        WeakReference<?> enqueued = (WeakReference<?>) queue.poll();
        if (enqueued != null) {
            System.out.println("Reference was enqueued for cleanup");
        }
    }
    
    // Practical example: Cache with weak references
    public static class Cache<K, V> {
        private final WeakHashMap<K, V> cache = new WeakHashMap<>();
        
        public V get(K key) {
            return cache.get(key);
        }
        
        public void put(K key, V value) {
            cache.put(key, value);
        }
        
        public int size() {
            return cache.size();
        }
    }
}
```

**🐞 Fixes:** Use weak/soft refs for caches, listeners.

### Advanced Questions

1. **How do you use tools like VisualVM to analyze memory usage in Java?**

**🧩 Foundation:** VisualVM can profile memory, CPU, threads, and analyze heap dumps.

**🔁 Flow:**
```java
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.ThreadMXBean;
import java.lang.management.ThreadInfo;

public class VisualVMAnalysisExample {
    
    public void generateMemoryProfile() {
        // Enable JMX for VisualVM connection
        System.setProperty("com.sun.management.jmxremote", "true");
        System.setProperty("com.sun.management.jmxremote.port", "9999");
        System.setProperty("com.sun.management.jmxremote.authenticate", "false");
        System.setProperty("com.sun.management.jmxremote.ssl", "false");
        
        // Create objects to analyze
        List<String> objects = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            objects.add("Object " + i);
        }
        
        // Generate heap dump programmatically
        generateHeapDump();
        
        // Monitor memory usage
        monitorMemoryUsage();
    }
    
    private void generateHeapDump() {
        try {
            // Generate heap dump (requires HotSpotDiagnosticMXBean)
            System.out.println("Generating heap dump...");
            // In real scenario, use: jmap -dump:format=b,file=heap.hprof <pid>
        } catch (Exception e) {
            System.err.println("Failed to generate heap dump: " + e.getMessage());
        }
    }
    
    private void monitorMemoryUsage() {
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapUsage = memoryBean.getHeapMemoryUsage();
        MemoryUsage nonHeapUsage = memoryBean.getNonHeapMemoryUsage();
        
        System.out.println("=== Memory Usage ===");
        System.out.println("Heap Used: " + (heapUsage.getUsed() / 1024 / 1024) + "MB");
        System.out.println("Heap Max: " + (heapUsage.getMax() / 1024 / 1024) + "MB");
        System.out.println("Non-Heap Used: " + (nonHeapUsage.getUsed() / 1024 / 1024) + "MB");
        
        // Thread analysis
        ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threadInfos = threadBean.dumpAllThreads(false, false);
        
        System.out.println("\n=== Thread Analysis ===");
        for (ThreadInfo info : threadInfos) {
            System.out.println("Thread: " + info.getThreadName() + 
                             " State: " + info.getThreadState() +
                             " CPU Time: " + threadBean.getThreadCpuTime(info.getThreadId()) + "ns");
        }
    }
    
    // Simulate memory leak for analysis
    public void simulateMemoryLeak() {
        List<byte[]> leakyList = new ArrayList<>();
        
        // Add objects without removing them (memory leak)
        for (int i = 0; i < 1000; i++) {
            leakyList.add(new byte[1024 * 1024]); // 1MB each
            System.out.println("Added object " + i + ", list size: " + leakyList.size());
            
            if (i % 100 == 0) {
                monitorMemoryUsage();
            }
        }
        
        // This list is never cleared - memory leak!
        // In VisualVM, you would see growing heap usage
    }
    
    // Method to help with VisualVM analysis
    public void createProfilingPoints() {
        // Create identifiable objects for VisualVM analysis
        String[] profilingPoints = {
            "PROFILING_POINT_1",
            "PROFILING_POINT_2", 
            "PROFILING_POINT_3"
        };
        
        // Create objects with specific patterns
        for (String point : profilingPoints) {
            List<String> objects = new ArrayList<>();
            for (int i = 0; i < 1000; i++) {
                objects.add(point + "_Object_" + i);
            }
            System.out.println("Created " + objects.size() + " objects for " + point);
        }
    }
}
```

**🐞 Fixes:** Use for leak detection, performance tuning.

### Tough Questions

1. **How would you optimize memory usage in a Java application handling large datasets?**

**🧩 Foundation:**
- Use primitive arrays, efficient collections
- Avoid object churn
- Pool/reuse objects

**🔁 Flow:**
```java
import java.util.stream.IntStream;

public class MemoryOptimizationExample {
    
    // BAD: Using objects for primitive data
    public void inefficientMemoryUsage() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            numbers.add(i); // Boxing overhead
        }
    }
    
    // GOOD: Using primitive arrays
    public void efficientMemoryUsage() {
        int[] numbers = new int[1000000];
        for (int i = 0; i < 1000000; i++) {
            numbers[i] = i; // No boxing
        }
    }
    
    // Object pooling for expensive objects
    public static class ObjectPool<T> {
        private final Queue<T> pool;
        private final Supplier<T> factory;
        
        public ObjectPool(int size, Supplier<T> factory) {
            this.pool = new ConcurrentLinkedQueue<>();
            this.factory = factory;
            
            // Pre-populate pool
            for (int i = 0; i < size; i++) {
                pool.offer(factory.get());
            }
        }
        
        public T borrow() {
            T obj = pool.poll();
            return obj != null ? obj : factory.get();
        }
        
        public void returnObject(T obj) {
            pool.offer(obj);
        }
    }
    
    // Streaming for large datasets
    public void processLargeDataset() {
        // Instead of loading all data into memory
        IntStream.range(0, 10000000)
                .filter(i -> i % 2 == 0)
                .map(i -> i * 2)
                .forEach(System.out::println);
    }
    
    // Memory-efficient data structures
    public void useEfficientCollections() {
        // Use specific initial capacity to avoid resizing
        List<String> list = new ArrayList<>(10000);
        
        // Use primitive collections (if available)
        // IntArrayList, LongHashMap, etc.
        
        // Use weak references for caches
        WeakHashMap<String, Object> cache = new WeakHashMap<>();
    }
    
    // Batch processing to reduce memory footprint
    public void processInBatches() {
        int batchSize = 1000;
        int totalItems = 100000;
        
        for (int i = 0; i < totalItems; i += batchSize) {
            int end = Math.min(i + batchSize, totalItems);
            processBatch(i, end);
            
            // Force GC after each batch
            System.gc();
        }
    }
    
    private void processBatch(int start, int end) {
        List<String> batch = new ArrayList<>();
        for (int i = start; i < end; i++) {
            batch.add("Item " + i);
        }
        
        // Process batch
        batch.forEach(System.out::println);
        
        // Clear batch to free memory
        batch.clear();
    }
    
    // Use try-with-resources for automatic cleanup
    public void processWithAutoCleanup() {
        try (var resource = new AutoCloseableResource()) {
            resource.process();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        // Resource automatically cleaned up
    }
    
    private static class AutoCloseableResource implements AutoCloseable {
        public void process() {
            System.out.println("Processing...");
        }
        
        @Override
        public void close() {
            System.out.println("Cleaning up resources...");
        }
    }
}
```

**🐞 Fixes:** Profile, tune, and refactor for memory efficiency.

### Situational / Real-world Questions

1. **Your application is running out of memory with large data processing. How would you troubleshoot and resolve it?**

**🧩 Foundation:**
- Analyze heap dumps
- Identify leaks, large objects
- Optimize data structures, algorithms

**🔁 Flow:**
```java
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

public class MemoryTroubleshootingExample {
    
    public void troubleshootMemoryIssues() {
        // Step 1: Monitor memory usage
        monitorMemoryUsage();
        
        // Step 2: Identify memory leaks
        detectMemoryLeaks();
        
        // Step 3: Optimize memory usage
        optimizeMemoryUsage();
        
        // Step 4: Implement memory-safe processing
        processDataSafely();
    }
    
    private void monitorMemoryUsage() {
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapUsage = memoryBean.getHeapMemoryUsage();
        
        long usedMemory = heapUsage.getUsed();
        long maxMemory = heapUsage.getMax();
        double usagePercentage = (double) usedMemory / maxMemory * 100;
        
        System.out.println("Memory Usage: " + (usedMemory / 1024 / 1024) + "MB");
        System.out.println("Max Memory: " + (maxMemory / 1024 / 1024) + "MB");
        System.out.println("Usage: " + String.format("%.2f", usagePercentage) + "%");
        
        if (usagePercentage > 80) {
            System.out.println("WARNING: High memory usage detected!");
        }
    }
    
    private void detectMemoryLeaks() {
        // Simulate memory leak detection
        List<byte[]> leakyObjects = new ArrayList<>();
        
        try {
            for (int i = 0; i < 1000; i++) {
                leakyObjects.add(new byte[1024 * 1024]); // 1MB each
                
                if (i % 100 == 0) {
                    monitorMemoryUsage();
                }
            }
        } catch (OutOfMemoryError e) {
            System.err.println("OutOfMemoryError detected: " + e.getMessage());
            // Analyze heap dump here
            analyzeHeapDump();
        }
    }
    
    private void analyzeHeapDump() {
        System.out.println("=== Heap Analysis ===");
        System.out.println("1. Generate heap dump: jmap -dump:format=b,file=heap.hprof <pid>");
        System.out.println("2. Analyze with VisualVM or MAT");
        System.out.println("3. Look for large object arrays");
        System.out.println("4. Check for retained objects");
    }
    
    private void optimizeMemoryUsage() {
        // Use memory-efficient data structures
        Map<String, Integer> efficientMap = new HashMap<>(1000); // Pre-size
        
        // Use primitive arrays instead of objects
        int[] numbers = new int[1000000]; // Instead of List<Integer>
        
        // Clear references when done
        numbers = null;
        efficientMap.clear();
        
        // Force garbage collection
        System.gc();
    }
    
    private void processDataSafely() {
        // Process data in chunks to avoid memory issues
        int chunkSize = 1000;
        int totalItems = 100000;
        
        for (int i = 0; i < totalItems; i += chunkSize) {
            processChunk(i, Math.min(i + chunkSize, totalItems));
            
            // Check memory after each chunk
            if (isMemoryPressureHigh()) {
                System.gc();
                Thread.sleep(100); // Give GC time to work
            }
        }
    }
    
    private void processChunk(int start, int end) {
        List<String> chunk = new ArrayList<>();
        
        for (int i = start; i < end; i++) {
            chunk.add("Data " + i);
        }
        
        // Process chunk
        chunk.forEach(System.out::println);
        
        // Clear chunk immediately
        chunk.clear();
    }
    
    private boolean isMemoryPressureHigh() {
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapUsage = memoryBean.getHeapMemoryUsage();
        
        double usagePercentage = (double) heapUsage.getUsed() / heapUsage.getMax() * 100;
        return usagePercentage > 70;
    }
    
    // Memory-safe data processing with streaming
    public void processWithStreaming() {
        // Use streams for memory-efficient processing
        IntStream.range(0, 1000000)
                .filter(i -> i % 2 == 0)
                .map(i -> i * 2)
                .forEach(System.out::println);
    }
    
    // Implement memory monitoring thread
    public void startMemoryMonitoring() {
        Thread monitorThread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                monitorMemoryUsage();
                
                try {
                    Thread.sleep(5000); // Check every 5 seconds
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        
        monitorThread.setDaemon(true);
        monitorThread.start();
    }
}
```

**🐞 Fixes:** Use tools, refactor code, increase heap if needed.

---

## 13. Java 11 to 17 Features

### Basic Questions

1. **What are the key features introduced in Java 11?**

**🧩 Foundation:**
- Local variable type inference (`var`)
- New HTTP client API
- String methods (repeat, isBlank, lines)
- ZGC, Epsilon GC
- Flight Recorder, Mission Control

**🐞 Fixes:** Use new features for productivity and performance.

2. **What is the `var` keyword in Java, and how is it used?**

**🧩 Foundation:** `var` enables local variable type inference.

**🔁 Flow:**
```java
var list = List.of("A", "B");
```
**🐞 Fixes:** Use for readability, but not for public APIs.

### Intermediate Questions

1. **What are the benefits of the `HttpClient` introduced in Java 11?**

**🧩 Foundation:**
- Asynchronous, non-blocking
- HTTP/2 support
- Simpler API than `HttpURLConnection`

**🔁 Flow:**
```java
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.time.Duration;

public class HttpClientExample {
    
    public void demonstrateHttpClient() {
        // Create HTTP client
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        
        // Synchronous request
        performSynchronousRequest(client);
        
        // Asynchronous request
        performAsynchronousRequest(client);
        
        // POST request with JSON
        performPostRequest(client);
    }
    
    private void performSynchronousRequest(HttpClient client) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.example.com/data"))
                    .timeout(Duration.ofSeconds(30))
                    .header("Accept", "application/json")
                    .GET()
                    .build();
            
            HttpResponse<String> response = client.send(request, 
                    HttpResponse.BodyHandlers.ofString());
            
            System.out.println("Status: " + response.statusCode());
            System.out.println("Body: " + response.body());
            
        } catch (Exception e) {
            System.err.println("Request failed: " + e.getMessage());
        }
    }
    
    private void performAsynchronousRequest(HttpClient client) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.example.com/async"))
                .GET()
                .build();
        
        // Non-blocking request
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println)
                .exceptionally(throwable -> {
                    System.err.println("Async request failed: " + throwable.getMessage());
                    return null;
                });
    }
    
    private void performPostRequest(HttpClient client) {
        String jsonBody = "{\"name\":\"John\",\"age\":30}";
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.example.com/users"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();
        
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenAccept(response -> {
                    System.out.println("POST Response: " + response.body());
                });
    }
    
    // Compare with old HttpURLConnection
    public void oldHttpURLConnection() {
        // Old way - more verbose and blocking
        try {
            java.net.URL url = new java.net.URL("https://api.example.com/data");
            java.net.HttpURLConnection connection = (java.net.HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(10000);
            
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            
            // Read response manually
            try (java.io.BufferedReader reader = new java.io.BufferedReader(
                    new java.io.InputStreamReader(connection.getInputStream()))) {
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                System.out.println("Response: " + response.toString());
            }
            
        } catch (Exception e) {
            System.err.println("Old HTTP request failed: " + e.getMessage());
        }
    }
    
    // HTTP/2 support demonstration
    public void demonstrateHttp2Support() {
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2) // Explicitly use HTTP/2
                .build();
        
        // HTTP/2 provides better performance for multiple requests
        for (int i = 0; i < 5; i++) {
            final int requestId = i;
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://http2.example.com/data/" + i))
                    .GET()
                    .build();
            
            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenAccept(response -> {
                        System.out.println("Request " + requestId + " completed");
                    });
        }
    }
}
```

**🐞 Fixes:** Use for modern HTTP communication.

2. **How do you use the switch expression introduced in Java 12–14?**

**🧩 Foundation:** Switch expressions allow returning values and using arrow syntax.

**🔁 Flow:**
```java
String result = switch (day) {
    case MONDAY, FRIDAY -> "Weekend";
    default -> "Weekday";
};
```
**🐞 Fixes:** Use for concise, expressive code.

### Advanced Questions

1. **How do you leverage sealed classes and records in Java 15–17 for better design?**

**🧩 Foundation:**
- **Sealed classes:** Restrict which classes can extend them.
- **Records:** Immutable data carriers with concise syntax.

**🔁 Flow:**
```java
// Sealed classes for type safety
public sealed abstract class Shape permits Circle, Square, Rectangle {
    public abstract double area();
    public abstract double perimeter();
}

public final class Circle extends Shape {
    private final double radius;
    
    public Circle(double radius) {
        this.radius = radius;
    }
    
    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
    
    @Override
    public double perimeter() {
        return 2 * Math.PI * radius;
    }
}

public final class Square extends Shape {
    private final double side;
    
    public Square(double side) {
        this.side = side;
    }
    
    @Override
    public double area() {
        return side * side;
    }
    
    @Override
    public double perimeter() {
        return 4 * side;
    }
}

public final class Rectangle extends Shape {
    private final double width;
    private final double height;
    
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }
    
    @Override
    public double area() {
        return width * height;
    }
    
    @Override
    public double perimeter() {
        return 2 * (width + height);
    }
}

// Records for immutable data
public record Point(int x, int y) {
    // Compact constructor for validation
    public Point {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Coordinates must be non-negative");
        }
    }
    
    // Custom method
    public double distanceTo(Point other) {
        int dx = x - other.x;
        int dy = y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}

public record Person(String name, int age, String email) {
    // Static factory method
    public static Person of(String name, int age) {
        return new Person(name, age, name.toLowerCase() + "@example.com");
    }
}

// Using sealed classes with pattern matching (Java 17+)
public class ShapeProcessor {
    public String processShape(Shape shape) {
        return switch (shape) {
            case Circle c -> "Circle with area: " + c.area();
            case Square s -> "Square with area: " + s.area();
            case Rectangle r -> "Rectangle with area: " + r.area();
            // No default needed - sealed class ensures all cases covered
        };
    }
    
    public void demonstrateRecords() {
        Point p1 = new Point(3, 4);
        Point p2 = new Point(0, 0);
        
        System.out.println("Point: " + p1); // toString() auto-generated
        System.out.println("Distance: " + p1.distanceTo(p2));
        
        // Record destructuring
        var (x, y) = p1;
        System.out.println("X: " + x + ", Y: " + y);
        
        Person person = Person.of("John", 30);
        System.out.println("Person: " + person);
    }
}

// Sealed interface example
public sealed interface PaymentMethod permits CreditCard, DebitCard, BankTransfer {
    double processPayment(double amount);
}

public record CreditCard(String number, String expiry) implements PaymentMethod {
    @Override
    public double processPayment(double amount) {
        System.out.println("Processing credit card payment: " + amount);
        return amount;
    }
}

public record DebitCard(String number, String pin) implements PaymentMethod {
    @Override
    public double processPayment(double amount) {
        System.out.println("Processing debit card payment: " + amount);
        return amount;
    }
}

public record BankTransfer(String accountNumber, String routingNumber) implements PaymentMethod {
    @Override
    public double processPayment(double amount) {
        System.out.println("Processing bank transfer: " + amount);
        return amount;
    }
}
```

**🐞 Fixes:** Use for type safety and concise data modeling.

### Tough Questions

1. **How would you refactor a legacy Java application to use records and sealed classes?**

**🧩 Foundation:**
- Identify POJOs and base classes
- Replace with records and sealed classes
- Refactor code to use new types

**🐞 Fixes:** Test thoroughly, ensure compatibility.

### Situational / Real-world Questions

1. **Your team wants to adopt Java 17 for a new project. How would you justify the upgrade?**

**🧩 Foundation:**
- Long-term support (LTS)
- Modern language features
- Performance and security improvements

**🔁 Flow:**
```java
public class Java17UpgradeJustification {
    
    public void demonstrateJava17Benefits() {
        // 1. Enhanced Switch Expressions
        demonstrateSwitchExpressions();
        
        // 2. Pattern Matching for Switch
        demonstratePatternMatching();
        
        // 3. Records for Data Classes
        demonstrateRecords();
        
        // 4. Sealed Classes for Type Safety
        demonstrateSealedClasses();
        
        // 5. Text Blocks for Readable Code
        demonstrateTextBlocks();
        
        // 6. Performance Improvements
        demonstratePerformance();
    }
    
    private void demonstrateSwitchExpressions() {
        // Java 17: Enhanced switch expressions
        String day = "MONDAY";
        String result = switch (day) {
            case "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY" -> "Weekday";
            case "SATURDAY", "SUNDAY" -> "Weekend";
            default -> "Unknown";
        };
        
        System.out.println("Day type: " + result);
        
        // Multiple statements in switch
        int value = 5;
        String description = switch (value) {
            case 1 -> "One";
            case 2 -> "Two";
            case 3, 4, 5 -> {
                String temp = "Multiple: " + value;
                yield temp; // Use yield for multiple statements
            }
            default -> "Other";
        };
    }
    
    private void demonstratePatternMatching() {
        Object obj = "Hello";
        
        // Pattern matching in switch
        String result = switch (obj) {
            case String s -> "String: " + s;
            case Integer i -> "Integer: " + i;
            case null -> "Null";
            default -> "Unknown type";
        };
        
        System.out.println("Pattern match result: " + result);
        
        // Pattern matching with guards
        Object number = 42;
        String numberType = switch (number) {
            case Integer i when i > 0 -> "Positive integer: " + i;
            case Integer i when i < 0 -> "Negative integer: " + i;
            case Integer i -> "Zero: " + i;
            default -> "Not an integer";
        };
    }
    
    private void demonstrateRecords() {
        // Concise data classes
        record Employee(String name, int age, String department) {
            // Compact constructor with validation
            public Employee {
                if (age < 0) throw new IllegalArgumentException("Age cannot be negative");
            }
            
            // Custom method
            public boolean isSenior() {
                return age >= 50;
            }
        }
        
        Employee emp = new Employee("John Doe", 45, "Engineering");
        System.out.println("Employee: " + emp); // Auto-generated toString()
        System.out.println("Is senior: " + emp.isSenior());
        
        // Record destructuring
        var (name, age, dept) = emp;
        System.out.println("Name: " + name + ", Age: " + age + ", Dept: " + dept);
    }
    
    private void demonstrateSealedClasses() {
        // Type-safe hierarchies
        sealed interface Shape permits Circle, Rectangle {}
        
        record Circle(double radius) implements Shape {}
        record Rectangle(double width, double height) implements Shape {}
        
        Shape shape = new Circle(5.0);
        
        // Exhaustive pattern matching
        double area = switch (shape) {
            case Circle c -> Math.PI * c.radius() * c.radius();
            case Rectangle r -> r.width() * r.height();
            // No default needed - compiler ensures all cases covered
        };
        
        System.out.println("Area: " + area);
    }
    
    private void demonstrateTextBlocks() {
        // Multi-line strings without escaping
        String sql = """
            SELECT id, name, email 
            FROM users 
            WHERE active = true 
            AND created_date > ?
            ORDER BY name
            """;
        
        String json = """
            {
                "name": "John Doe",
                "age": 30,
                "email": "john@example.com"
            }
            """;
        
        System.out.println("SQL: " + sql);
        System.out.println("JSON: " + json);
    }
    
    private void demonstratePerformance() {
        // Java 17 performance improvements
        long startTime = System.currentTimeMillis();
        
        // Enhanced performance for common operations
        for (int i = 0; i < 1000000; i++) {
            String result = "Value: " + i; // String concatenation optimization
        }
        
        long endTime = System.currentTimeMillis();
        System.out.println("Performance test completed in: " + (endTime - startTime) + "ms");
    }
    
    // Business case presentation
    public void presentBusinessCase() {
        System.out.println("=== Java 17 Upgrade Business Case ===");
        System.out.println("1. Long-term Support (LTS) until 2029");
        System.out.println("2. Security updates and patches");
        System.out.println("3. Performance improvements (10-20% faster startup)");
        System.out.println("4. Modern language features reduce boilerplate code");
        System.out.println("5. Better developer productivity");
        System.out.println("6. Future-proofing for upcoming Java versions");
        System.out.println("7. Improved garbage collection (G1GC enhancements)");
        System.out.println("8. Enhanced security features");
    }
}
```

**🐞 Fixes:** Present a business case for maintainability and future-proofing. 
