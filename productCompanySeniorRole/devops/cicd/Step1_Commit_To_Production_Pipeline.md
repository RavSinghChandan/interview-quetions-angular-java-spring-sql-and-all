Perfect. We go **Step-by-Step Mode** now.
You’ll say **“2”** when ready for next.

---

# 📁 FILE: `Step1_Commit_To_Production_Pipeline.md`

---

## 🎯 Goal of This Step

You must be able to explain **exactly how code travels from your laptop to production users** — with real tooling, commands, configs, and architecture clarity.

If you master this step → you already outperform 70% candidates.

---

## 🧠 Mental Model (Must Memorize)

```
Local Code
   ↓
Git Push
   ↓
CI Pipeline
   ↓
Build + Test
   ↓
Artifact (Jar/Docker)
   ↓
Artifact Registry
   ↓
CD Pipeline
   ↓
Kubernetes Deploy
   ↓
Load Balancer
   ↓
Users
```

---

## 🧑‍💻 STEP 1 — Developer Commits Code

```bash
git add .
git commit -m "Added payment retry logic"
git push origin main
```

Trigger starts here.

Senior Insight:

> Production lifecycle starts at commit, not deployment.

---

## ⚙️ STEP 2 — CI Trigger

Example: **GitHub Actions trigger**

```yaml
on:
  push:
    branches:
      - main
```

Other triggers senior engineers mention:

```
pull_request
tag push
manual trigger
cron build
```

---

## 🏗 STEP 3 — Build Application

Java Example:

```yaml
- name: Build
  run: mvn clean package -DskipTests=false
```

Output:

```
target/app.jar
```

Senior signal:

> Build must be deterministic + reproducible.

---

## 🧪 STEP 4 — Run Tests

```yaml
- name: Run Tests
  run: mvn test
```

Types you should mention in interviews:

```
Unit tests
Integration tests
Contract tests
Smoke tests
```

Senior statement:

> CI fails → deployment blocked.

---

## 📦 STEP 5 — Build Docker Image

Dockerfile:

```dockerfile
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app
COPY target/app.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]
```

Build:

```bash
docker build -t myapp:1.0.3 .
```

---

## 📤 STEP 6 — Push Image to Registry

Login:

```bash
docker login registry.company.com
```

Push:

```bash
docker push registry.company.com/myapp:1.0.3
```

Registry examples you should name:

```
AWS ECR
DockerHub
GCR
Azure Container Registry
JFrog Artifactory
```

---

## 🚀 STEP 7 — Trigger Deployment Pipeline

Usually triggered by:

```
New image tag pushed
GitOps commit
Manual approval
Webhook
```

GitOps Example:

```bash
git commit -m "Update image tag to 1.0.3"
git push
```

---

## ☸️ STEP 8 — Kubernetes Deploys

Deployment YAML:

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: payment-service

spec:
  replicas: 3

  strategy:
    type: RollingUpdate

  template:
    spec:
      containers:
        - name: app
          image: registry.company.com/myapp:1.0.3
          ports:
            - containerPort: 8080
```

Apply:

```bash
kubectl apply -f deploy.yaml
```

---

## 🔄 STEP 9 — Load Balancer Routes Traffic

Service YAML:

```yaml
apiVersion: v1
kind: Service
metadata:
  name: payment-service

spec:
  type: LoadBalancer

  selector:
    app: payment

  ports:
    - port: 80
      targetPort: 8080
```

---

## 🛡 STEP 10 — Health Checks Before Traffic

```yaml
readinessProbe:
  httpGet:
    path: /health
    port: 8080
  initialDelaySeconds: 5
  periodSeconds: 5
```

If probe fails → pod receives **NO TRAFFIC**

Senior engineers always mention probes.

---

# 🧠 Elite Interview Answer (Memorize)

If asked:

**How does your code reach production?**

Answer:

> I push code → CI triggers → builds + tests → Docker image created → pushed to registry → CD updates Kubernetes deployment → rolling update starts → readiness probes validate pods → load balancer routes traffic → monitoring confirms stability.

This answer signals:
✔ real-world exposure
✔ production ownership mindset
✔ system understanding

---

# 🏆 Company Level Signals

If you say only:

> Jenkins deploys code

→ Rejected at product companies.

If you say full flow above:

→ Pass senior rounds at

```
Amazon
Uber
Flipkart
Razorpay
Swiggy
Stripe-tier companies
```

---

# 📌 Mastery Checklist

You must be able to explain without notes:

* CI trigger
* Build
* Tests
* Artifact
* Registry
* Deploy
* Load balancing
* Health checks

If any missing → not senior yet.

---

✅ **Your move:**
Reply **"2"** to unlock next file:

> `Step2_CI_Pipeline_Internal_Architecture.md`

This next step goes **deep inside CI engine internals** (how Jenkins/GitHub Actions actually works behind scenes).
