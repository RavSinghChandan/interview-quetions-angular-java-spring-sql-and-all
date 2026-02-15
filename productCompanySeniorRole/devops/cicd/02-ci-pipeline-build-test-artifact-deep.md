Excellent.
Now we enter **real CI execution maturity**.

This is where you move from “I know CI exists”
to
“I understand exactly what happens in build pipeline.”

---

# 📁 File: `02-ci-pipeline-build-test-artifact-deep.md`

# 🔥 STEP 2 — CI Pipeline Deep Dive

(Build → Test → Docker → Scan → Tag → Push)

Senior interviewers may ask:

* What happens in your CI pipeline?
* How do you ensure build reliability?
* Where do tests run?
* When is Docker image built?
* How do you tag images?
* How do you prevent insecure images?

You must answer layer by layer.

---

# 🧠 1️⃣ CI Trigger

Pipeline starts when:

* PR created
* Code merged to main
* Tag created

Example GitHub Actions trigger:

```yaml
on:
  push:
    branches: [ "main" ]
  pull_request:
    branches: [ "main" ]
```

CI should run automatically.
Never manually triggered for normal flow.

---

# 🧠 2️⃣ Build Stage (Compile)

For Java:

```yaml
- name: Build project
  run: mvn clean package -DskipTests=false
```

This:

✔ Compiles code
✔ Runs unit tests
✔ Produces JAR

Build must fail if tests fail.

No bypassing.

---

# 🧠 3️⃣ Testing Layers in CI

You must know test hierarchy:

1️⃣ Unit tests (fast)
2️⃣ Integration tests (DB / Testcontainers)
3️⃣ Contract tests (API validation)
4️⃣ Static analysis

Example static check:

```yaml
- name: Run SonarQube
  run: mvn sonar:sonar
```

Shift-left quality enforcement.

---

# 🧠 4️⃣ Fail Fast Principle

If test fails:

Pipeline stops.

Never build Docker image if tests fail.

Fail early reduces waste.

---

# 🧠 5️⃣ Docker Image Build Stage

After tests pass:

```yaml
- name: Build Docker image
  run: docker build -t myapp:${{ github.sha }} .
```

Tag image using commit SHA.

Why?

Immutable version reference.

---

# 🧠 6️⃣ Image Tagging Strategy

Good tagging strategy:

```
myapp:1.2.0
myapp:commitSHA
myapp:latest (optional)
```

Best practice:

Avoid using only “latest” in production.

Always use version tag.

---

# 🧠 7️⃣ Multi-Stage Docker in CI

CI uses production Dockerfile:

```dockerfile
FROM maven:3.9 AS builder
WORKDIR /app
COPY . .
RUN mvn package

FROM openjdk:17-jdk-slim
COPY --from=builder /app/target/app.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
```

CI builds production-grade image.

---

# 🧠 8️⃣ Push to Registry

After image built:

```yaml
- name: Login to registry
  run: docker login -u $USER -p $PASSWORD registry.example.com

- name: Push image
  run: docker push registry.example.com/myapp:${{ github.sha }}
```

Registry examples:

* Docker Hub
* ECR
* GCR
* ACR

Registry is artifact store.

---

# 🧠 9️⃣ Image Security Scanning (Senior Signal)

Before push or after push:

Scan image for vulnerabilities.

Example with Trivy:

```yaml
- name: Scan Docker image
  run: trivy image myapp:${{ github.sha }}
```

Fail pipeline if critical vulnerabilities found.

Security integrated into CI.

---

# 🧠 🔟 Dependency Vulnerability Scanning

Scan dependencies:

```bash
mvn dependency-check:check
```

Or:

Snyk integration.

Prevents shipping vulnerable libraries.

---

# 🧠 1️⃣1️⃣ Artifact Immutability Reminder

Build once.

Do NOT rebuild image in staging.

Promote same image from staging → production.

CI builds artifact.
CD promotes artifact.

Strict separation.

---

# 🧠 1️⃣2️⃣ Parallelization for Speed

CI pipelines should:

* Run tests in parallel
* Cache dependencies

Example caching:

```yaml
- uses: actions/cache@v3
  with:
    path: ~/.m2
```

Faster pipeline = faster feedback.

---

# 🧠 1️⃣3️⃣ Real Interview Scenario

Question:

“What happens when you merge to main?”

Strong answer:

1. CI pipeline triggers
2. Code compiled
3. Tests executed
4. Static analysis runs
5. Docker image built
6. Image scanned
7. Image tagged with commit SHA
8. Image pushed to registry

Clear and structured.

---

# 🧠 1️⃣4️⃣ Common CI Mistakes

✘ Skipping tests
✘ Building image before tests
✘ Using “latest” tag
✘ No vulnerability scanning
✘ No pipeline logs retention

Senior engineers avoid these.

---

# 🧠 1️⃣5️⃣ Senior-Level Mental Model

CI ensures:

✔ Code quality
✔ Artifact correctness
✔ Security compliance
✔ Traceable versioning

CD should never fix CI problems.

CI must produce production-ready artifact.

---

# 🎯 STEP 2 REVISION CHECKLIST

You must confidently explain:

✔ CI trigger flow
✔ Build + test layers
✔ Docker build stage
✔ Image tagging strategy
✔ Registry push
✔ Security scanning
✔ Artifact immutability
✔ Pipeline optimization

If you can explain clearly →
CI execution maturity achieved.

---

When ready, type:

**3**

Next file:

📁 `03-cd-pipeline-kubernetes-deployment-deep.md`
(Rolling updates + ReplicaSet + Helm + GitOps + zero downtime + promotion strategy deep dive)
