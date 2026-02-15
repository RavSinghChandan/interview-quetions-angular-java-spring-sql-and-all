Good. Continuing strictly step-by-step.

---

# 📁 File: `05-image-optimization-build-performance.md`

# 🔥 STEP 5 — Image Optimization + Build Performance + CI Efficiency

(Senior Backend Production Maturity)

This step answers real senior questions:

* Why is your image 1.2GB?
* Why scaling takes 2 minutes?
* Why CI build takes 12 minutes?
* How do you optimize Docker builds?

This is where depth shows.

---

# 🧠 1️⃣ Why Image Size Matters in Production

Large image consequences:

* Slow CI build
* Slow push to registry
* Slow pull during scaling
* Slower rolling deployment
* Higher cloud cost
* Bigger attack surface

Example:

If image size = 900MB
Scaling 20 pods = 18GB network transfer.

Under traffic spike → slow scaling.

Senior principle:
Image size directly impacts autoscaling speed.

---

# 🧠 2️⃣ Understanding Docker Layer Cache Rules

Docker rebuild rule:

If a layer changes → all subsequent layers rebuild.

Example (bad):

```dockerfile
COPY . .
RUN mvn clean package
```

If any file changes → entire layer invalidated → full rebuild.

---

# ✅ Correct Layer Strategy

```dockerfile
COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package -DskipTests
```

Why?

* Dependencies cached separately.
* Code changes do not re-download dependencies.

CI build time drastically reduced.

---

# 🧠 3️⃣ Multi-Stage Build Optimization (Production Pattern)

Full optimized version:

```dockerfile
# -------- Builder --------
FROM maven:3.9-eclipse-temurin-17 AS builder
WORKDIR /build

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package -DskipTests

# -------- Runtime --------
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app

COPY --from=builder /build/target/app.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]
```

Final image contains:

✔ Only JDK
✔ Only app.jar
✔ No source
✔ No Maven

Smaller + secure.

---

# 🧠 4️⃣ .dockerignore (Build Context Optimization)

Create `.dockerignore`:

```
target/
.git/
node_modules/
logs/
*.log
README.md
```

Why?

Docker sends entire build context to daemon.

Without .dockerignore → slow build.

Check context size:

```bash
docker build .
```

It prints:

```
Sending build context to Docker daemon  45.6MB
```

Reduce this.

---

# 🧠 5️⃣ Layer Explosion Avoidance

Bad:

```dockerfile
RUN apt-get update
RUN apt-get install -y curl
RUN apt-get install -y vim
```

Creates 3 layers.

Better:

```dockerfile
RUN apt-get update && \
    apt-get install -y curl vim && \
    rm -rf /var/lib/apt/lists/*
```

Single layer.
Smaller image.

---

# 🧠 6️⃣ Using Distroless Images (Advanced Production)

Ultra-minimal runtime:

```dockerfile
FROM gcr.io/distroless/java17
WORKDIR /app
COPY --from=builder /build/target/app.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
```

Benefits:

✔ Very small
✔ No shell
✔ Reduced attack surface

Tradeoff:

❌ Hard debugging

Senior awareness required.

---

# 🧠 7️⃣ Running as Non-Root (Security Optimization)

Add user:

```dockerfile
RUN addgroup --system appgroup && \
    adduser --system appuser --ingroup appgroup

USER appuser
```

Never run production containers as root.

Security maturity signal.

---

# 🧠 8️⃣ BuildKit Optimization (Advanced CI)

Enable BuildKit:

```bash
DOCKER_BUILDKIT=1 docker build -t myapp .
```

Benefits:

* Parallel build
* Improved caching
* Secret mount support

Secret mount example:

```dockerfile
# syntax=docker/dockerfile:1.4
RUN --mount=type=secret,id=maven_settings \
    mvn clean package
```

Secret not stored in image.

CI maturity.

---

# 🧠 9️⃣ Maven Caching in CI (GitHub Actions Example)

```yaml
- name: Cache Maven
  uses: actions/cache@v3
  with:
    path: ~/.m2
    key: ${{ runner.os }}-m2-${{ hashFiles('**/pom.xml') }}
```

Reduces CI build time.

Senior awareness: optimize pipeline, not just Dockerfile.

---

# 🧠 🔟 Image Tagging Strategy (Production Safe)

Never deploy:

```bash
docker push myapp:latest
```

Instead:

```bash
docker build -t myapp:1.3.4 .
docker push myapp:1.3.4
```

Or Git SHA:

```bash
docker build -t myapp:$(git rev-parse --short HEAD) .
```

Rollback safe.

---

# 🧠 1️⃣1️⃣ Image Vulnerability Scanning

Scan image:

```bash
trivy image myapp:1.3.4
```

Or use CI integrated scanning.

If critical vulnerabilities → fail pipeline.

Security maturity.

---

# 🧠 1️⃣2️⃣ Scaling Impact of Image Size

If image = 800MB
Scaling 10 pods → 8GB transfer.

If image = 150MB
Scaling 10 pods → 1.5GB transfer.

Under autoscaling events → speed matters.

---

# 🧠 1️⃣3️⃣ Immutable Infrastructure Principle

Never:

```bash
docker exec -it container bash
# modify file manually
```

Correct flow:

1. Modify code
2. Build new image
3. Deploy new version

Immutable infra = reproducible infra.

---

# 🧠 1️⃣4️⃣ Debugging Image Bloat

Check image history:

```bash
docker history myapp
```

Look for:

* Large RUN layers
* Accidental file copies
* Duplicate installs

---

# 🧠 1️⃣5️⃣ Real Interview Question Simulation

### ❓ How do you reduce Docker image size?

Strong structured answer:

* Use multi-stage build
* Use slim/distroless base
* Avoid unnecessary layers
* Use .dockerignore
* Remove package cache
* Do not include source code

---

### ❓ Why does image size affect scaling?

Because Kubernetes must pull image before starting container. Large images slow down scaling and rolling deployments.

---

# 🎯 STEP 5 REVISION CHECKLIST

You must confidently explain:

✔ Layer caching strategy
✔ Multi-stage build optimization
✔ .dockerignore importance
✔ Layer minimization
✔ Distroless vs slim
✔ BuildKit usage
✔ CI caching
✔ Image tagging strategy
✔ Vulnerability scanning
✔ Scaling impact of image size
✔ Immutable infrastructure

If you can explain all calmly →
Image optimization mastery achieved.

---

When ready, type:

**6**

Next file:

📁 `06-configuration-management-secrets.md`
(Environment variables + profiles + secrets + ConfigMap + IAM roles + feature flags)
