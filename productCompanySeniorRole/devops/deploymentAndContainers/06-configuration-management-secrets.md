Good. Continue disciplined.

---

# 📁 File: `06-configuration-management-secrets.md`

# 🔥 STEP 6 — Configuration Management + Secrets + Environment Strategy

(Senior Backend Production Depth)

This step is heavily tested in cloud/system interviews.

They want to see:

* Can you build once and deploy everywhere?
* Do you understand secret management?
* Can you separate config from code?
* Can you design config strategy for microservices?

This is not optional. This is maturity.

---

# 🧠 1️⃣ Core Principle — Build Once, Deploy Everywhere

Golden rule:

> Docker image must be identical across dev, staging, prod.

Only configuration changes.

If you build separate images per environment → not mature.

---

# 🧠 2️⃣ Wrong Approach (Junior Mistake)

Hardcoding config:

```java
String dbUrl = "jdbc:mysql://localhost:3306/dev";
String password = "root123";
```

Or in Dockerfile:

```dockerfile
ENV DB_PASSWORD=secret
```

Problems:

❌ Security risk
❌ Need rebuild for change
❌ Environment mismatch

---

# 🧠 3️⃣ Correct Approach — Environment Variables

Run container with env:

```bash
docker run \
  -e DB_URL=jdbc:mysql://prod-db:3306/app \
  -e DB_USER=prod_user \
  -e DB_PASSWORD=supersecret \
  myapp:1.0
```

Spring Boot `application.yml`:

```yaml
spring:
  datasource:
    url: ${DB_URL}
    username: ${DB_USER}
    password: ${DB_PASSWORD}
```

Same image → different environment config.

---

# 🧠 4️⃣ Spring Profiles Strategy

`application.yml`:

```yaml
spring:
  profiles:
    active: ${SPRING_PROFILES_ACTIVE}
```

`application-dev.yml`:

```yaml
spring:
  datasource:
    url: jdbc:h2:mem:testdb
```

`application-prod.yml`:

```yaml
spring:
  datasource:
    url: jdbc:mysql://prod-db:3306/app
```

Run:

```bash
docker run -e SPRING_PROFILES_ACTIVE=prod myapp
```

No rebuild needed.

---

# 🧠 5️⃣ Secrets Management (Critical)

Never store secrets in:

* Git
* Dockerfile
* Image layer
* Plain YAML

---

## 🔐 Kubernetes Secret Example

Create secret:

```bash
kubectl create secret generic db-secret \
  --from-literal=DB_PASSWORD=supersecret
```

Deployment YAML:

```yaml
env:
  - name: DB_PASSWORD
    valueFrom:
      secretKeyRef:
        name: db-secret
        key: DB_PASSWORD
```

Container reads via env variable.

---

## 🔐 Mount Secret as File

```yaml
volumeMounts:
  - name: secret-volume
    mountPath: "/etc/secrets"
    readOnly: true
```

---

# 🧠 6️⃣ ConfigMap vs Secret

| ConfigMap            | Secret         |
| -------------------- | -------------- |
| Non-sensitive config | Sensitive data |
| Feature flags        | Passwords      |
| Service URLs         | Tokens         |
| Logging level        | API keys       |

Separation is mandatory.

---

# 🧠 7️⃣ AWS IAM Role Instead of Hardcoded Credentials

Instead of:

```bash
AWS_ACCESS_KEY=...
AWS_SECRET_KEY=...
```

Use IAM Role attached to:

* EC2
* EKS Pod

Application auto-authenticates.

This is senior-level security.

---

# 🧠 8️⃣ Feature Flags (Production Strategy)

Instead of redeploying for small changes:

`application.yml`:

```yaml
feature:
  newCheckoutFlow: ${NEW_CHECKOUT_FLOW:false}
```

Run:

```bash
docker run -e NEW_CHECKOUT_FLOW=true myapp
```

Safer rollout.

Used heavily in large systems.

---

# 🧠 9️⃣ Logging Configuration via Env

```yaml
logging:
  level:
    root: ${LOG_LEVEL:INFO}
```

Run:

```bash
docker run -e LOG_LEVEL=DEBUG myapp
```

No rebuild.

---

# 🧠 🔟 External Config Server (Advanced Awareness)

Used in large systems:

* Spring Cloud Config
* Consul
* Vault

Allows dynamic config refresh.

Not mandatory, but awareness helps.

---

# 🧠 1️⃣1️⃣ Build Secrets (During Docker Build)

Use BuildKit:

```dockerfile
# syntax=docker/dockerfile:1.4
RUN --mount=type=secret,id=maven_settings \
    mvn clean package
```

Secret not stored in layer.

Very advanced CI awareness.

---

# 🧠 1️⃣2️⃣ Avoid Environment-Specific Images

Wrong:

```
myapp-dev
myapp-prod
```

Correct:

```
myapp:1.4.2
```

Config injected at runtime.

---

# 🧠 1️⃣3️⃣ Real Interview Scenarios

---

### Scenario 1 — Works in Dev, fails in Prod

Likely causes:

* Wrong env variable name
* Missing secret
* Wrong profile active
* DB endpoint mismatch

---

### Scenario 2 — Secret leaked in Git

Correct action:

* Rotate secret immediately
* Remove from repo
* Move to secret manager

---

### Scenario 3 — Deployment fails due to missing config

Check:

* Kubernetes env mapping
* Secret existence
* ConfigMap key
* Typo in variable

---

# 🧠 1️⃣4️⃣ 12-Factor Config Principle

Apps must:

✔ Store config in environment
✔ Not commit secrets
✔ Separate config from code

This is cloud-native maturity.

---

# 🧠 1️⃣5️⃣ Production-Ready Config Checklist

✔ All config externalized
✔ No secrets in image
✔ Profiles used properly
✔ Secrets managed securely
✔ IAM roles preferred
✔ Feature flags supported
✔ Logging configurable

---

# 🎯 STEP 6 REVISION CHECKLIST

You must confidently explain:

✔ Build once deploy everywhere
✔ Env variable strategy
✔ Spring profile usage
✔ Secret vs ConfigMap difference
✔ IAM role concept
✔ Feature flags
✔ Build secret handling
✔ Avoid environment-specific images

If you can explain this clearly →
Configuration maturity strong.

---

When ready, type:

**7**

Next file:

📁 `07-health-checks-readiness-liveness.md`
(Health checks + readiness vs liveness + zero downtime behavior + Kubernetes probes deep dive)
