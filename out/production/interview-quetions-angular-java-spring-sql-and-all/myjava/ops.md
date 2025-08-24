# 📘 Java OOP & Language Features Evolution (Java 1 → Java 23)

This document covers the **OOP principles and language features** from **Java 1 (1996) to Java 23 (2025)**.

---

## 🗂 OOP & Language Topics
1. Classes & Objects
2. Interfaces & Abstract Classes
3. Generics
4. Annotations
5. Records
6. Sealed Classes
7. Pattern Matching

---

# 1. Classes & Objects

### Java 1 (1996)
- Core of Java – supported **encapsulation, inheritance, polymorphism, abstraction**.
- Classes defined as blueprints for objects.

### Java 5 (2004)
- Enhanced with **metadata annotations** on classes.

### Java 7 (2011)
- **Diamond operator (`<>`)** simplified object instantiation with generics.

### Java 14 (2020)
- **Helpful NullPointerException messages** to debug class field issues.

### Java 23 (2025)
- Value types (Project Valhalla work) may redefine how objects are stored (lightweight objects without identity).

---

# 2. Interfaces & Abstract Classes

### Java 1 (1996)
- Interfaces introduced: only abstract methods allowed.
- Abstract classes: mix of concrete + abstract methods.

### Java 8 (2014)
- Interfaces enhanced with **default methods** and **static methods**.
- Reason: To evolve APIs without breaking implementations.

### Java 9 (2017)
- **Private methods** allowed inside interfaces (for code reuse).

### Java 23 (2025)
- Interfaces tightly integrated with pattern matching for better polymorphism.

---

# 3. Generics

### Before Java 5
- Collections stored `Object`, required casting → unsafe.

### Java 5 (2004)
- **Generics introduced**.
    - Example: `List<String> names = new ArrayList<>();`
- Reason: Type safety + avoid casting.

### Java 7 (2011)
- **Diamond operator** (`<>`) for type inference.

### Java 8 (2014)
- Better type inference with lambdas.

### Java 10 (2018)
- `var` keyword integrated smoothly with generics.

---

# 4. Annotations

### Java 1.5 (2004)
- **Annotations introduced** (`@Override`, `@Deprecated`, `@SuppressWarnings`).
- Reason: Metadata for compilers/tools.

### Java 6 (2006)
- **Built-in annotations** for dependency injection (`@Resource`, `@PostConstruct`).

### Java 8 (2014)
- **Type annotations** and **repeating annotations**.
    - Example: `@NonNull String name;`

### Java 9+ (2017–23)
- Modular annotations support with `module-info.java`.

---

# 5. Records

### Java 14 (2020, preview)
- **Records introduced** → concise way to model data.
    - Example: `record User(String name, int age) {}`

### Java 16 (2021)
- Records became a standard feature.
- Reason: Reduce boilerplate (getters, equals, hashCode, toString auto-generated).

---

# 6. Sealed Classes

### Java 15 (2020, preview)
- **Sealed classes** introduced.
- Syntax:
  ```java
  public sealed class Shape permits Circle, Rectangle {}
