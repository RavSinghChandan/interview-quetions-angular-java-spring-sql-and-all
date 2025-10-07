
---

# The Epic Journey of Records & Sealed Classes in Java: From Boilerplate Hell to Data Safety

## 🚀 The Genesis: Plain Old Java Objects (POJOs) - Java 1.0 → 14

In early Java, representing simple data structures required **POJOs** — classes with fields, getters, setters, `equals()`, `hashCode()`, and `toString()`.

```java
// Java 1.0+ - POJO for User
public class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    @Override
    public String toString() {
        return "User{name='" + name + "', age=" + age + "}";
    }
}
````

**The Problem**:

* Too much boilerplate
* Error-prone `equals()` & `hashCode()`
* Tedious to maintain in large projects

---

## 🌟 Java 14 (2020) - The Birth of Records (Preview)

Java 14 introduced **Records** as a concise way to model immutable data carriers.

```java
// Java 14 - Record
public record User(String name, int age) { }
```

**The Breakthrough**:

* **Immutable by default**
* **Automatic generation** of constructor, `equals()`, `hashCode()`, and `toString()`
* Greatly reduces boilerplate
* Explicitly signals “this class is just data”

### Edge Case: Custom Methods

```java
public record User(String name, int age) {
    public String greeting() {
        return "Hello, I'm " + name + "!";
    }
}
```

✅ Works fine — records are not limited to just fields

### Edge Case: Immutability

```java
User u = new User("Alice", 25);
// u.name = "Bob"; ❌ Compilation error
```

* Records are **final and immutable** by default
* Can’t change fields after creation → ensures data integrity

---

## ⚡ Java 15 (2020) - Sealed Classes Preview

Sealed classes were introduced to **restrict which classes can extend or implement a type**.

```java
// Java 15 - Sealed class example
public sealed class Shape permits Circle, Rectangle {}

public final class Circle extends Shape {}
public final class Rectangle extends Shape {}
```

**The Breakthrough**:

* Prevents uncontrolled inheritance
* Improves **pattern matching safety**
* Makes **class hierarchies predictable**

### Edge Case: Non-Permitted Subclasses

```java
public class Triangle extends Shape {} // ❌ Compilation error
```

* Only **permitted subclasses** can extend a sealed class
* Helps maintain controlled and safe hierarchies

---

## 🌟 Java 16-17: Records & Sealed Classes Stabilize

* Records became **production-ready** → widely adopted in microservices and DTOs
* Sealed classes stabilized → useful for **exhaustive `switch` expressions**
* Simplifies **pattern matching** with known subclasses

```java
// Example: Using sealed classes with switch (Java 17)
Shape shape = new Circle();
switch (shape) {
    case Circle c -> System.out.println("Circle radius: " + c.radius());
    case Rectangle r -> System.out.println("Rectangle width: " + r.width());
}
```

---

## 🔥 Java 18+: Modern Usage

* Records are now **standard for data transfer objects (DTOs)**
* Sealed classes enforce **domain constraints**
* Together, they **reduce boilerplate** and **ensure safety & readability**

```java
// Record inside sealed class
public sealed interface Event permits UserCreated, UserDeleted {}

public record UserCreated(String username) implements Event {}
public record UserDeleted(String username) implements Event {}
```

---

## 💡 Key Takeaways: Records & Sealed Classes

1. **Records** → concise, immutable data holders
2. **Sealed Classes** → controlled inheritance, safe hierarchy
3. **Boilerplate Reduction** → fewer mistakes, faster development
4. **Data Integrity** → immutability ensures consistent state
5. **Predictable Design** → easier to reason about code

---

## 🚀 Mastery Path

```java
// Challenge: Model a domain with records and sealed classes
public sealed interface Payment permits CreditCardPayment, PayPalPayment {}

public record CreditCardPayment(String cardNumber, double amount) implements Payment {}
public record PayPalPayment(String email, double amount) implements Payment {}
```

**Remember**: Every record and sealed class you use today reflects **Java’s 25-year journey** from boilerplate-heavy objects to **safe, readable, and maintainable code**. 🚀

```


