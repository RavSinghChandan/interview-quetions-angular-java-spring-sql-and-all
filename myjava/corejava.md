# 📘 Java Core Evolution (Java 1 → Java 23)

This document covers the **Core Java topics** and their evolution across versions from **Java 1 (1996) to Java 23 (2025)**.  
Each topic shows:
- How it started
- Key changes in each version
- Why the changes were introduced

---

## 🗂 Core Java Topics
1. Primitive Types
2. String & StringBuffer/StringBuilder
3. Object Class
4. Wrapper Classes
5. Arrays
6. Enums
7. Exceptions & Error Handling

---

# 1. Primitive Types

### Java 1 (1996)
- Introduced **8 primitive types**: `byte`, `short`, `int`, `long`, `float`, `double`, `char`, `boolean`.
- Focus: Platform independence → fixed sizes across platforms.

### Java 5 (2004)
- Introduced **autoboxing/unboxing** with wrapper classes.
    - Example: `int x = 10; Integer y = x;`
- Reason: Easier integration with Collections.

### Java 10 (2018)
- **Local variable type inference (`var`)** introduced.
    - Works with primitives too: `var x = 5;` (inferred as `int`).

### Java 23 (2025, upcoming Valhalla impact)
- Work on **inline/value types** may further optimize primitive handling.

---

# 2. String & StringBuffer/StringBuilder

### Java 1 (1996)
- **String class** introduced (immutable).
- Reason: Security, caching, thread-safety.

### Java 1.1 (1997)
- **StringBuffer** introduced for mutable strings.

### Java 1.4 (2002)
- Regex support (`matches`, `split`, `replaceAll`).

### Java 6 (2006)
- **StringBuilder** introduced (non-synchronized, faster).

### Java 7 (2011)
- Strings allowed in `switch`.

### Java 8 (2014)
- `join()`, `chars()`, `codePoints()` added.

### Java 9 (2017)
- **Compact Strings** (better memory efficiency).

### Java 11 (2018 LTS)
- Utility methods: `isBlank()`, `strip()`, `repeat()`, `lines()`.

### Java 13–15
- **Text Blocks** introduced and standardized.

### Java 22 (2024)
- **String Templates (Preview)** for easier concatenation.

### Java 23 (2025)
- Optimizations for String Templates + memory efficiency.

---

# 3. Object Class

### Java 1 (1996)
- Base class for all objects.
- Provided: `toString()`, `equals()`, `hashCode()`, `getClass()`, `clone()`, `finalize()`.

### Java 5 (2004)
- Generics enhanced `equals()` usage with type safety in collections.

### Java 9 (2017)
- Deprecation of `finalize()` (to be removed later).
- Reason: Unpredictable behavior, replaced by `try-with-resources` & cleaners.

### Java 18 (2022)
- `finalize()` **deprecated for removal** officially.

### Java 23 (2025)
- Cleaners fully replace `finalize()`.

---

# 4. Wrapper Classes

### Java 1 (1996)
- Wrapper classes introduced (`Integer`, `Double`, etc.) to use primitives as objects.

### Java 5 (2004)
- **Autoboxing/Unboxing** introduced.
- Static factory methods like `Integer.valueOf()` improved caching.

### Java 9 (2017)
- Factory methods enhanced.
- Deprecated constructors (e.g., `new Integer(5)`) → replaced by `valueOf()`.

---

# 5. Arrays

### Java 1 (1996)
- Introduced as fixed-size, strongly-typed collections.

### Java 5 (2004)
- Enhanced for-loop (`for-each`) introduced.

### Java 8 (2014)
- Arrays.stream() method for using arrays with Streams API.

### Java 9 (2017)
- `Objects.checkIndex()` utility for safer array index handling.

---

# 6. Enums

### Java 1–4
- No enum type, used `public static final` constants.

### Java 5 (2004)
- **Enums introduced** as a full-fledged type.
    - Allowed methods, fields, constructors inside enums.
    - Type safety and cleaner syntax.

### Java 8 (2014)
- Enums integrated with Streams and Lambdas.

---

# 7. Exceptions & Error Handling

### Java 1 (1996)
- Introduced **checked and unchecked exceptions**.
- Keywords: `try`, `catch`, `finally`, `throw`, `throws`.

### Java 1.4 (2002)
- **Exception chaining** (`Throwable.initCause`).
- Logging API introduced → better debugging.

### Java 5 (2004)
- **Generic exceptions** (works with generics).
- `@SuppressWarnings` for exception handling warnings.

### Java 7 (2011)
- **Multi-catch**: `catch (IOException | SQLException e)`.
- **try-with-resources** for auto-closing.

### Java 9 (2017)
- Enhanced try-with-resources → variables declared outside can be used.

### Java 14 (2020)
- Helpful NullPointerExceptions: shows variable that caused NPE.

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
