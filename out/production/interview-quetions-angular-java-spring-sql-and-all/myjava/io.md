# 📘 Java I/O & Networking Evolution (Java 1 → Java 23)

This document covers the **Java I/O system and Networking APIs** from **Java 1 (1996) to Java 23 (2025)**.

---

## 🗂 Java I/O & Networking Topics
1. java.io.* (Classic I/O)
2. NIO & NIO.2
3. Serialization
4. HTTP Client

---

# 1. java.io.* (Classic I/O)

### Java 1 (1996)
- Core I/O API introduced in `java.io`.
- Classes: `File`, `InputStream`, `OutputStream`, `Reader`, `Writer`.
- Supported file handling, streams, and basic console input/output.

### Java 1.1 (1997)
- **Object Serialization** introduced with `Serializable` interface.
- `ObjectInputStream` and `ObjectOutputStream` added.

### Java 1.4 (2002)
- New I/O (`java.nio`) introduced for scalable I/O.

---

# 2. NIO & NIO.2

### Java 1.4 (2002)
- **NIO (Non-blocking I/O)** introduced.
- Features: Buffers, Channels, Selectors.
- Provided high-performance I/O for network and file operations.

### Java 7 (2011)
- **NIO.2 introduced** with `java.nio.file` package.
- Features: `Path`, `Files`, `FileSystems`.
- Added support for symbolic links, better error handling, and asynchronous file channels.

### Java 8 (2014)
- Stream API integrated well with file I/O via `Files.lines()`, `Files.list()`.

### Java 11 (2018)
- `Files.readString()` and `Files.writeString()` simplified reading/writing small files.

---

# 3. Serialization

### Java 1.1 (1997)
- **Serialization** introduced to persist objects.

### Java 5–8
- Serialization used widely but became a source of vulnerabilities.

### Java 9 (2017)
- Serialization warnings and strong encapsulation added.

### Java 17+ (2021–23)
- Movement towards safer alternatives (e.g., JSON, protobuf).
- Serialization APIs marked as legacy in many cases.

---

# 4. HTTP Client

### Java 1–8
- Only legacy **`HttpURLConnection`** API existed → verbose and hard to use.

### Java 9 (2017)
- New **HTTP Client API (incubator)** introduced (`java.net.http`).
- Supported modern features: HTTP/2, WebSockets, async calls.

### Java 11 (2018 LTS)
- HTTP Client became standard.
```java
HttpClient client = HttpClient.newHttpClient();
HttpRequest request = HttpRequest.newBuilder()
    .uri(URI.create("https://example.com"))
    .build();
HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
System.out.println(response.body());
