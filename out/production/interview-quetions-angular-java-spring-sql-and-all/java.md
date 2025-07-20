# Core Java Interview Questions for Senior Java Backend Developers

This document contains the most frequently asked Core Java interview questions, curated for senior Java backend developers transitioning to or maintaining expertise in full-stack or backend roles. Questions are organized by topic and include those repeatedly asked in interviews by companies like **TCS, Infosys, Cognizant, Capgemini**, etc., along with additional critical questions to ensure comprehensive coverage for senior-level interviews.

---

## 1. Object-Oriented Programming (OOP)

### Basic Questions

1. What are the core principles of OOP in Java? _(Asked in TCS, Infosys)_
2. What is the difference between abstraction and encapsulation? _(Asked in Capgemini)_
3. What is the difference between an interface and an abstract class? _(Asked in Wipro)_

### Intermediate Questions

1. How does Java achieve polymorphism? _(Asked in Accenture)_
2. What is the significance of the `final` keyword in Java? _(Asked in TCS)_
3. Explain the difference between method overloading and method overriding. _(Asked in Infosys)_

### Advanced Questions

1. How does the `super` keyword work in Java, and when would you use it? _(Asked in Cognizant)_
2. What is the difference between composition and inheritance? When would you use one over the other? _(Asked in Deloitte)_

### Tough Questions

1. How would you design a class hierarchy to ensure extensibility while preventing misuse through inheritance? _(Asked in Amazon)_

### Situational / Real-world Questions

1. You’re tasked with refactoring a legacy system with tightly coupled classes. How would you apply OOP principles to improve it? _(Asked in HCL)_

---

## 2. Java Classes and Objects

### Basic Questions

1. What is the difference between a class and an object in Java? _(Asked in TCS)_
2. How do you create a singleton class in Java? _(Asked in Capgemini)_

### Intermediate Questions

1. What are immutable objects, and how do you create one in Java? _(Asked in Infosys)_
2. Explain the role of the `this` keyword in Java. _(Asked in Wipro)_

### Advanced Questions

1. How does the `clone()` method work, and what precautions should be taken when implementing it? _(Asked in Cognizant)_
2. What is the purpose of the `Object` class in Java? _(Asked in TCS Digital)_

### Tough Questions

1. How would you implement a deep copy of an object with nested references? _(Asked in Deloitte)_

### Situational / Real-world Questions

1. You need to ensure thread-safe instantiation of a singleton in a high-concurrency environment. How would you achieve this? _(Asked in Oracle)_

---

## 3. Collections Framework

### Basic Questions

1. What is the Java Collections Framework, and what are its main interfaces? _(Asked in TCS, Infosys)_
2. What is the difference between `ArrayList` and `LinkedList`? _(Asked in Capgemini)_

### Intermediate Questions

1. What is the difference between `HashMap`, `LinkedHashMap`, and `TreeMap`? _(Asked in Wipro)_
2. How does a `HashSet` ensure uniqueness of elements? _(Asked in Accenture)_
3. What is the `ConcurrentHashMap`, and when would you use it? _(Asked in Infosys)_

### Advanced Questions

1. How does the internal implementation of `HashMap` handle collisions? _(Asked in Cognizant)_
2. What are the benefits of using `Collections.synchronizedList` vs. `CopyOnWriteArrayList`? _(Asked in TCS Digital)_

### Tough Questions

1. How would you design a custom collection to handle a large dataset with frequent reads and rare writes? _(Asked in Amazon)_

### Situational / Real-world Questions

1. Your application is experiencing performance issues with a large `HashMap`. How would you optimize it? _(Asked in HCL)_

---

## 4. Multithreading and Concurrency

### Basic Questions

1. What is a thread in Java, and how do you create one? _(Asked in TCS, Capgemini)_
2. What is the difference between `Thread` and `Runnable`? _(Asked in Infosys)_

### Intermediate Questions

1. What is the difference between synchronized block and synchronized method? _(Asked in Wipro)_
2. Explain the `ExecutorService` and how it simplifies thread management. _(Asked in Accenture)_
3. What is the purpose of the `volatile` keyword in Java? _(Asked in TCS)_

### Advanced Questions

1. How does the `ForkJoinPool` work, and when is it appropriate to use? _(Asked in Cognizant)_
2. What are the differences between `ReentrantLock` and `synchronized`? _(Asked in Deloitte)_

### Tough Questions

1. How would you implement a thread-safe producer-consumer pattern using `BlockingQueue`? _(Asked in Amazon)_
2. How do you handle deadlock scenarios in a multi-threaded application? _(Asked in Oracle)_

### Situational / Real-world Questions

1. Your application is facing thread contention issues in a high-traffic system. How would you diagnose and resolve it? _(Asked in HCL)_

---

## 5. Exception Handling

### Basic Questions

1. What is the difference between checked and unchecked exceptions in Java? _(Asked in TCS, Infosys)_
2. What is the purpose of the try-catch block? _(Asked in Capgemini)_

### Intermediate Questions

1. What is the difference between `throw` and `throws` in Java? _(Asked in Wipro)_
2. How do you create a custom exception in Java? _(Asked in Accenture)_

### Advanced Questions

1. What is the role of the `finally` block, and when might it not execute? _(Asked in Cognizant)_
2. How do you use try-with-resources, and what are its benefits? _(Asked in TCS Digital)_

### Tough Questions

1. How would you design an exception handling strategy for a large-scale enterprise application? _(Asked in Deloitte)_

### Situational / Real-world Questions

1. Your REST API throws intermittent exceptions in production. How would you implement robust error handling? _(Asked in Infosys)_

---

## 6. Java 8+ Features

### Basic Questions

1. What are the key features introduced in Java 8? _(Asked in TCS, Infosys)_
2. What is a lambda expression, and how is it used in Java? _(Asked in Capgemini)_

### Intermediate Questions

1. What is the Stream API, and how does it differ from traditional loops? _(Asked in Wipro)_
2. Explain the difference between `map` and `flatMap` in the Stream API. _(Asked in Accenture)_
3. What is the purpose of the `Optional` class in Java? _(Asked in Infosys)_

### Advanced Questions

1. How does the `CompletableFuture` class support asynchronous programming? _(Asked in Cognizant)_
2. What are the benefits of using default methods in interfaces? _(Asked in TCS Digital)_

### Tough Questions

1. How would you process a large dataset using parallel streams while avoiding common pitfalls? _(Asked in Amazon)_

### Situational / Real-world Questions

1. You need to optimize a data processing pipeline using Java streams. How would you approach it? _(Asked in HCL)_

---

## 7. JVM Internals and Garbage Collection

### Basic Questions

1. What is the JVM, and what are its main components? _(Asked in TCS, Capgemini)_
2. What is garbage collection in Java? _(Asked in Infosys)_

### Intermediate Questions

1. What are the different types of garbage collectors in Java? _(Asked in Wipro)_
2. How does the String pool work in Java? _(Asked in Accenture)_

### Advanced Questions

1. Explain the difference between Young Generation and Old Generation in the JVM. _(Asked in Cognizant)_
2. How do you tune JVM parameters for a high-throughput application? _(Asked in Oracle)_

### Tough Questions

1. How would you debug a memory leak in a Java application? _(Asked in Deloitte)_

### Situational / Real-world Questions

1. Your application is experiencing frequent garbage collection pauses. How would you analyze and mitigate this? _(Asked in HCL)_

---

## 8. Design Patterns

### Basic Questions

1. What are design patterns, and why are they important in Java? _(Asked in TCS, Infosys)_
2. Explain the Singleton design pattern with an example. _(Asked in Capgemini)_

### Intermediate Questions

1. What is the Factory pattern, and when would you use it? _(Asked in Wipro)_
2. Explain the Observer pattern and its use in Java. _(Asked in Accenture)_

### Advanced Questions

1. How does the Builder pattern help in creating complex objects? _(Asked in Cognizant)_
2. What is the difference between Strategy and Template Method patterns? _(Asked in TCS Digital)_

### Tough Questions

1. How would you implement the Chain of Responsibility pattern in a Java-based microservice? _(Asked in Amazon)_

### Situational / Real-world Questions

1. You’re designing a logging system for a distributed application. Which design patterns would you use and why? _(Asked in Deloitte)_

---

## 9. Input/Output (I/O) and Serialization

### Basic Questions

1. What is the difference between `InputStream` and `Reader` in Java? _(Asked in TCS)_
2. What is serialization, and how is it implemented in Java? _(Asked in Capgemini)_

### Intermediate Questions

1. What is the difference between `ObjectOutputStream` and `DataOutputStream`? _(Asked in Infosys)_
2. How do you handle large file processing in Java efficiently? _(Asked in Wipro)_

### Advanced Questions

1. What is the purpose of the `transient` keyword in serialization? _(Asked in Cognizant)_
2. How do you implement custom serialization using `Externalizable`? _(Asked in TCS Digital)_

### Tough Questions

1. How would you optimize I/O operations for a high-throughput data streaming application? _(Asked in Deloitte)_

### Situational / Real-world Questions

1. Your application needs to serialize complex objects to a database. How would you ensure compatibility across versions? _(Asked in HCL)_

---

## 10. Security in Java

### Basic Questions

1. How does Java ensure security at the language level? _(Asked in TCS)_
2. What is the role of the `SecurityManager` in Java? _(Asked in Infosys)_

### Intermediate Questions

1. How do you prevent SQL injection when using JDBC? _(Asked in Capgemini)_
2. What are the best practices for secure coding in Java? _(Asked in Wipro)_

### Advanced Questions

1. How do you implement secure password storage in a Java application? _(Asked in Cognizant)_

### Tough Questions

1. How would you secure a Java application against deserialization vulnerabilities? _(Asked in Deloitte)_

### Situational / Real-world Questions

1. Your application is vulnerable to XSS attacks in a web context. How would you mitigate this using Java? _(Asked in TCS Digital)_

---

## 11. Error Handling and Debugging

### Basic Questions

1. What are best practices for exception handling in Java? _(Asked in TCS)_
2. What is the difference between `Error` and `Exception` in Java? _(Asked in Infosys)_

### Intermediate Questions

1. How do you implement a global exception handling mechanism in a Java application? _(Asked in Capgemini)_
2. How do you log exceptions effectively in a Java application? _(Asked in Wipro)_

### Advanced Questions

1. How do you debug a performance issue in a Java application in production? _(Asked in HCL)_

### Tough Questions

1. A Java application crashes intermittently in production. How would you identify and resolve the issue? _(Asked in Deloitte)_

### Situational / Real-world Questions

1. Your application logs sensitive data in error messages. How would you redesign the error handling to prevent this? _(Asked in Accenture)_

---

## 12. Java Memory Management

### Basic Questions

1. What is the difference between stack and heap memory in Java? _(Asked in TCS, Infosys)_
2. What is the role of the `finalize` method in Java? _(Asked in Capgemini)_

### Intermediate Questions

1. How does the JVM manage memory allocation for objects? _(Asked in Wipro)_
2. What is the difference between strong, weak, and soft references in Java? _(Asked in Accenture)_

### Advanced Questions

1. How do you use tools like VisualVM to analyze memory usage in Java? _(Asked in Cognizant)_

### Tough Questions

1. How would you optimize memory usage in a Java application handling large datasets? _(Asked in Amazon)_

### Situational / Real-world Questions

1. Your application is running out of memory with large data processing. How would you troubleshoot and resolve it? _(Asked in HCL)_

---

## 13. Java 11 to 17 Features

### Basic Questions

1. What are the key features introduced in Java 11? _(Asked in Infosys)_
2. What is the `var` keyword in Java, and how is it used? _(Asked in Capgemini)_

### Intermediate Questions

1. What are the benefits of the `HttpClient` introduced in Java 11? _(Asked in TCS)_
2. How do you use the switch expression introduced in Java 12–14? _(Asked in Wipro)_

### Advanced Questions

1. How do you leverage sealed classes and records in Java 15–17 for better design? _(Asked in Cognizant)_

### Tough Questions

1. How would you refactor a legacy Java application to use records and sealed classes? _(Asked in Deloitte)_

### Situational / Real-world Questions

1. Your team wants to adopt Java 17 for a new project. How would you justify the upgrade? _(Asked in Accenture)_ 
