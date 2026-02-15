Good. Continue structured.

---

# 📁 File: `03-dockerfile-production-mastery.md`

# 🔥 STEP 3 — Production-Grade Dockerfile Mastery

(Multi-Stage, Caching, Security, JVM Optimization, CI Ready)

This file is critical.

Interviewers often say:

> “Show me how you would containerize a Spring Boot microservice.”

You must write it confidently and explain every line.

---

# 🧠 1️⃣ Naive Dockerfile (Junior Level)

```dockerfile
FROM openjdk:17
COPY . /app
WORKDIR /app
RUN mvn clean package
CMD ["java", "-jar", "target/app.jar"]
```

Problems:

❌ Includes source code
❌ Includes Maven
❌ Large image
❌ Slow rebuild
❌ Poor caching
❌ Not secure

Not senior-level.

---

# 🧠 2️⃣ Proper Multi-Stage Production Dockerfile

```dockerfile
# ---------- Stage 1: Build ----------
FROM maven:3.9-eclipse-temurin-17 AS builder

WORKDIR /build

# Copy dependency file first (better caching)
COPY pom.xml .

# Download dependencies
RUN mvn dependency:go-offline -B

# Copy source code separately
COPY src ./src

# Build application
RUN mvn clean package -DskipTests

# ---------- Stage 2: Runtime ----------
FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

# Copy only built artifact
COPY --from=builder /build/target/app.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
```

---

# 🧠 3️⃣ Why This Is Senior-Level

### ✔ Multi-stage build

Removes:

* Maven
* Source files
* Build dependencies

Final image = only runtime.

Smaller + safer.

---

### ✔ Dependency Caching Strategy

```dockerfile
COPY pom.xml .
RUN mvn dependency:go-offline -B
```

If only source changes →
Dependencies not re-downloaded.

Massively reduces CI time.

---

# 🧠 4️⃣ Image Size Optimization

Check size:

```bash
docker images
```

Use slim image:

```dockerfile
FROM eclipse-temurin:17-jdk-jammy
```

Avoid:

* Full Ubuntu
* Installing unnecessary packages

---

# 🧠 5️⃣ .dockerignore (Critical)

Create `.dockerignore`:

```
target/
.git/
logs/
node_modules/
*.log
```

Reduces build context size.

Without this → slow build.

---

# 🧠 6️⃣ Running as Non-Root (Security)

Add non-root user:

```dockerfile
RUN addgroup --system appgroup && \
    adduser --system appuser --ingroup appgroup

USER appuser
```

Why?

If container compromised →
No root access.

Security maturity signal.

---

# 🧠 7️⃣ ENTRYPOINT vs CMD

Better pattern:

```dockerfile
ENTRYPOINT ["java","-jar"]
CMD ["app.jar"]
```

Override default jar:

```bash
docker run myapp other.jar
```

Override ENTRYPOINT:

```bash
docker run --entrypoint sh myapp
```

---

# 🧠 8️⃣ JVM Optimization for Containers

Modern JVM auto-detects container memory.

But good practice:

```dockerfile
ENTRYPOINT ["java","-XX:+UseContainerSupport","-jar","app.jar"]
```

Align heap manually if needed:

```dockerfile
ENTRYPOINT ["java","-Xms256m","-Xmx512m","-jar","app.jar"]
```

Never let heap exceed container memory.

---

# 🧠 9️⃣ Healthcheck in Dockerfile

```dockerfile
HEALTHCHECK --interval=30s --timeout=5s \
  CMD curl -f http://localhost:8080/actuator/health || exit 1
```

Allows runtime to detect unhealthy container.

---

# 🧠 🔟 Layer Optimization Example

Bad:

```dockerfile
RUN apt-get update
RUN apt-get install -y curl
RUN apt-get install -y vim
```

Good:

```dockerfile
RUN apt-get update && \
    apt-get install -y curl vim && \
    rm -rf /var/lib/apt/lists/*
```

Fewer layers.
Smaller image.

---

# 🧠 1️⃣1️⃣ Distroless Runtime (Advanced)

```dockerfile
FROM gcr.io/distroless/java17
WORKDIR /app
COPY --from=builder /build/target/app.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
```

Pros:

✔ Minimal attack surface
✔ Smaller size

Cons:

❌ Harder debugging

Senior awareness needed.

---

# 🧠 1️⃣2️⃣ Tagging Strategy

Never deploy:

```bash
docker push myapp:latest
```

Instead:

```bash
docker build -t myapp:1.2.3 .
docker push myapp:1.2.3
```

Or Git SHA:

```bash
docker build -t myapp:$(git rev-parse --short HEAD) .
```

Enables rollback.

---

# 🧠 1️⃣3️⃣ Image Scanning (Security in CI)

Example using Trivy:

```bash
trivy image myapp:1.2.3
```

CI pipeline should fail on critical vulnerabilities.

---

# 🧠 1️⃣4️⃣ BuildKit Advanced Usage

Enable:

```bash
DOCKER_BUILDKIT=1 docker build .
```

Secret mount example:

```dockerfile
# syntax=docker/dockerfile:1.4
RUN --mount=type=secret,id=maven_settings \
    mvn clean package
```

Secret not stored in layer.

---

# 🧠 1️⃣5️⃣ Real Interview Question Simulation

### ❓ Why multi-stage builds?

Answer:

> To separate build environment from runtime environment, reduce image size, remove unnecessary build tools, and improve security.

---

### ❓ Why slim image?

> Smaller attack surface and faster scaling because smaller images pull faster.

---

### ❓ Why not log to file?

> Containers are ephemeral. Logs should go to stdout for centralized collection.

---

# 🎯 STEP 3 REVISION CHECKLIST

You must confidently explain:

✔ Multi-stage builds
✔ Dependency caching
✔ .dockerignore
✔ Non-root user
✔ ENTRYPOINT vs CMD
✔ JVM memory alignment
✔ Image tagging strategy
✔ Vulnerability scanning
✔ Distroless option
✔ Layer optimization

If you can explain these without confusion →
Production Dockerfile mastery achieved.

---

When ready, type:

**4**

Next file:

📁 `04-container-runtime-resource-control.md`
(Container lifecycle + namespaces + cgroups + memory + OOMKilled + debugging)
