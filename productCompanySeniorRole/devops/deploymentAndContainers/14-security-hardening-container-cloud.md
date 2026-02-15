Good. Now we enter security layer.

---

# 📁 File: `14-security-hardening-container-cloud.md`

# 🔥 STEP 14 — Container Security + Cloud Hardening + Production Safety

(Senior Backend Security Awareness)

Security is not only DevOps job.

Senior backend engineers must understand:

* Container attack surface
* Image vulnerabilities
* Running as non-root
* Secrets handling
* Network isolation
* Cloud IAM principles

Interviewers may ask:

* How do you secure containers?
* How do you prevent privilege escalation?
* How do you handle secrets?
* What is least privilege?

This file makes you security-aware.

---

# 🧠 1️⃣ Run Containers as Non-Root

Never run container as root.

Bad:

```dockerfile
FROM openjdk:17
```

Runs as root by default.

Better:

```dockerfile
RUN addgroup --system appgroup && \
    adduser --system appuser --ingroup appgroup

USER appuser
```

Why?

If container compromised → attacker doesn’t get root.

Security maturity signal.

---

# 🧠 2️⃣ Use Minimal Base Images

Avoid:

```dockerfile
FROM ubuntu
```

Use:

```dockerfile
FROM eclipse-temurin:17-jdk-jammy
```

Or even:

```dockerfile
FROM gcr.io/distroless/java17
```

Smaller image = fewer vulnerabilities.

---

# 🧠 3️⃣ Image Vulnerability Scanning

Scan image before deploy.

Using Trivy:

```bash
trivy image myapp:1.2.3
```

CI integration example:

```yaml
- name: Scan Docker image
  run: trivy image myapp:${{ github.sha }}
```

Fail build if critical vulnerabilities.

Security should be automated.

---

# 🧠 4️⃣ Avoid Secrets in Dockerfile

Never:

```dockerfile
ENV DB_PASSWORD=supersecret
```

Secrets become part of image layer.

Instead use:

* Kubernetes Secrets
* AWS Secrets Manager
* Vault

---

# 🧠 5️⃣ Kubernetes Secret Example

```bash
kubectl create secret generic db-secret \
  --from-literal=DB_PASSWORD=supersecret
```

Deployment:

```yaml
env:
  - name: DB_PASSWORD
    valueFrom:
      secretKeyRef:
        name: db-secret
        key: DB_PASSWORD
```

Secret stored base64-encoded in cluster (not encrypted by default unless configured).

---

# 🧠 6️⃣ Principle of Least Privilege (IAM)

Never give full admin role.

Instead:

* Give only required permissions.
* Use IAM Role for Service Account (IRSA in AWS).

Avoid hardcoding:

```bash
AWS_ACCESS_KEY=...
```

Use IAM roles instead.

---

# 🧠 7️⃣ Network Policies (Advanced Kubernetes Security)

By default:

Pods can talk to all pods.

Define NetworkPolicy:

```yaml
apiVersion: networking.k8s.io/v1
kind: NetworkPolicy
metadata:
  name: allow-user-service
spec:
  podSelector:
    matchLabels:
      app: user
  ingress:
  - from:
    - podSelector:
        matchLabels:
          app: order
```

Restricts communication.

Micro-segmentation.

---

# 🧠 8️⃣ Resource Limits Prevent Abuse

If container compromised:

Without memory/CPU limits → may consume all node resources.

Always define:

```yaml
resources:
  limits:
    cpu: "1"
    memory: "512Mi"
```

Limits act as security boundary.

---

# 🧠 9️⃣ Read-Only Root Filesystem

Advanced hardening:

```yaml
securityContext:
  readOnlyRootFilesystem: true
```

Prevents writing to container root.

---

# 🧠 🔟 Drop Linux Capabilities

Reduce container privileges:

```yaml
securityContext:
  capabilities:
    drop:
      - ALL
```

Prevents privilege escalation.

---

# 🧠 1️⃣1️⃣ Avoid Privileged Containers

Never use:

```yaml
securityContext:
  privileged: true
```

Unless absolutely required.

Privileged containers can access host resources.

Huge security risk.

---

# 🧠 1️⃣2️⃣ TLS Everywhere

Internal service communication should use:

* HTTPS
* mTLS (in advanced setups)

Avoid plain HTTP in production.

Ingress example:

```yaml
tls:
  - hosts:
    - api.myapp.com
    secretName: tls-secret
```

---

# 🧠 1️⃣3️⃣ Logging and Audit

Security monitoring requires:

* Centralized logs
* Audit logs
* Failed login tracking
* Access monitoring

Security without monitoring is blind.

---

# 🧠 1️⃣4️⃣ Real Interview Scenarios

---

### Scenario 1

Container compromised. What limits damage?

Answer:

* Non-root user
* Resource limits
* Network policies
* Least privilege IAM
* Read-only filesystem

---

### Scenario 2

Secret accidentally committed.

Correct action:

* Rotate secret immediately
* Remove from repo
* Use secret manager
* Audit access logs

---

### Scenario 3

Pod accessing services it shouldn’t.

Solution:

* Implement NetworkPolicy
* Restrict namespace communication

---

# 🧠 1️⃣5️⃣ Senior-Level Security Checklist

✔ Non-root containers
✔ Minimal base image
✔ Image scanning in CI
✔ No secrets in image
✔ Use secret manager
✔ IAM least privilege
✔ Network policies
✔ Resource limits
✔ TLS encryption

If you mention even 5 of these in interview → strong signal.

---

# 🎯 STEP 14 REVISION CHECKLIST

You must confidently explain:

✔ Why non-root user
✔ Image scanning
✔ Secrets management
✔ IAM least privilege
✔ NetworkPolicy
✔ Read-only filesystem
✔ Dropping capabilities
✔ TLS in production

If you can explain this calmly →
Security maturity achieved.

---

When ready, type:

**15**

Next file:

📁 `15-end-to-end-production-flow-checklist.md`
(Complete cloud deployment lifecycle — code → CI → image → registry → deploy → scale → monitor → secure → rollback — final mastery file)
