...existing code...

# 7. Exceptions & Error Handling

### Java 1 (1996)
- Introduced **checked and unchecked exceptions**.
- Keywords: `try`, `catch`, `finally`, `throw`, `throws`.
```java
try {
    int x = 1 / 0;
} catch (ArithmeticException e) {
    System.out.println("Error: " + e.getMessage());
} finally {
    System.out.println("Done");
}
```
<!-- commit: Java 1 introduced robust exception handling with checked/unchecked exceptions. -->

### Java 1.4 (2002)
- **Exception chaining** (`Throwable.initCause`).
- Logging API introduced → better debugging.
```java
Exception e1 = new Exception("Root");
Exception e2 = new Exception("Wrapper", e1);
System.out.println(e2.getCause()); // prints "Root"
```
<!-- commit: Java 1.4 added exception chaining for better error context. -->

### Java 5 (2004)
- **Generic exceptions** (works with generics).
- `@SuppressWarnings` for exception handling warnings.
```java
@SuppressWarnings("unchecked")
public <T extends Exception> void throwGeneric(T e) throws T {
    throw e;
}
```
<!-- commit: Java 5 enabled generic exceptions and better warning suppression. -->

### Java 7 (2011)
- **Multi-catch**: `catch (IOException | SQLException e)`.
- **try-with-resources** for auto-closing.
```java
try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
    System.out.println(br.readLine());
} catch (IOException | SQLException e) {
    e.printStackTrace();
}
```
<!-- commit: Java 7 made exception handling concise with multi-catch and try-with-resources. -->

### Java 9 (2017)
- Enhanced try-with-resources → variables declared outside can be used.
```java
BufferedReader br = new BufferedReader(new FileReader("file.txt"));
try (br) {
    System.out.println(br.readLine());
}
```
<!-- commit: Java 9 improved try-with-resources for better resource management. -->

### Java 14 (2020)
- Helpful NullPointerExceptions: shows variable that caused NPE.
```java
String s = null;
System.out.println(s.length()); // Java 14+ shows which variable was null
```
<!-- commit: Java 14 made debugging NPEs much easier. -->

---

# ✅ Summary

Core Java evolved from **basic OOP support in Java 1** to a **rich, modern language by Java 23**.  
Key themes in evolution:
- **Primitives** → better integration with objects.
- **Strings** → from immutable basics to templates.
- **Object class** → modernization, removal of legacy `finalize`.
- **Wrappers & Enums** → safer, type-friendly coding.
- **Arrays & Exceptions** → better ergonomics and safety.

---