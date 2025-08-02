
---

### 🔹 **S — Single Responsibility Principle (SRP)**

1. **What is the violation of SRP in a typical Spring Boot `Service` class?**
2. **Can a Controller violate SRP? If so, how would you refactor it?**
3. **How do you identify SRP violations in a legacy codebase?**
4. **If one class only changes in response to security updates and another for data logic — is that one or two responsibilities?**
5. **How does SRP relate to the Open/Closed Principle?**

---

### 🔹 **O — Open/Closed Principle (OCP)**

6. **How do annotations like `@ControllerAdvice` or `@Configuration` follow OCP in Spring?**
7. **How can you apply OCP using interfaces and dependency injection in Spring Boot?**
8. **Is using `if-else` or `switch-case` on enums a violation of OCP? How would you refactor it?**
9. **Give an example of a real Spring project class you extended instead of modifying (OCP in practice).**
10. **How does the `Strategy Pattern` help enforce OCP in Java or Spring Boot?**

---

### 🔹 **L — Liskov Substitution Principle (LSP)**

11. **Why is violating LSP dangerous in REST controllers returning subtypes?**
12. **Give an example of LSP violation using Java collections or lists.**
13. **If a subclass throws a new unchecked exception, is that an LSP violation?**
14. **Can `@Transactional` behavior on subclassed methods violate LSP?**
15. **How does polymorphism enforce or break LSP in real-world Spring apps?**

---

### 🔹 **I — Interface Segregation Principle (ISP)**

16. **Why should a microservice not expose a "God interface" with too many methods?**
17. **How can `FeignClient` interfaces violate ISP?**
18. **How do you apply ISP when designing a shared SDK or library for multiple services?**
19. **Can a REST API violate ISP even with Swagger documentation?**
20. **How do default methods in Java 8 interfaces support or break ISP?**

---

### 🔹 **D — Dependency Inversion Principle (DIP)**

21. **How does Spring Boot naturally encourage DIP via `@Autowired`?**
22. **What’s the difference between dependency injection and dependency inversion?**
23. **How can tight coupling in a configuration class violate DIP?**
24. **Why is injecting a concrete class instead of an interface considered a DIP violation?**
25. **How do you apply DIP when writing unit tests using mocks?**

---