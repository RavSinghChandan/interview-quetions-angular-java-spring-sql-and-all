Great 🚀 Let’s expand **Section 11 → Deployment & DevOps in Spring Boot**.
This section explains how deployment practices evolved from **Spring Boot 1.x → 2.x → 3.x**, including **JARs, WARs, Docker, Kubernetes, and CI/CD**.

Here’s the `.md` file content:

---

````markdown
# 11. Deployment & DevOps in Spring Boot

Spring Boot simplified Java deployment by moving from **traditional WAR deployments** → to **fat JARs, Docker, Kubernetes, and cloud-native CI/CD pipelines**.  

---

## 11.1 Spring Boot 1.x (2014–2017)

### Packaging & Deployment
- **Executable JARs** (fat JARs) introduced with embedded Tomcat/Jetty/Undertow.  
- Still supported **WAR files** for traditional app servers (JBoss, WebSphere, WebLogic).  
- Deployment Options:
  - Run as JAR: `java -jar app.jar`  
  - Deploy as WAR in servlet container.  

### Configuration Management
- Used externalized `application.properties`.  
- Profiles supported (`dev`, `test`, `prod`).  

### CI/CD
- Jenkins pipelines or manual scripts.  
- No official Spring Boot support for containerization.  

---

## 11.2 Spring Boot 2.x (2018–2021)

### Docker & Containers
- First-class support for **Docker builds**.  
- Cloud-Native Buildpacks introduced (`spring-boot:build-image`).  
- Example:  

```bash
./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=myapp:1.0
````

* Auto-generates OCI-compliant Docker images.

### Kubernetes

* Boot apps deployed on **Kubernetes & OpenShift**.
* Added **Liveness & Readiness probes** with Actuator.

```yaml
livenessProbe:
  httpGet:
    path: /actuator/health/liveness
    port: 8080
```

### CI/CD

* Spring Boot apps commonly deployed using:

    * Jenkins + Docker + Kubernetes
    * GitLab CI
    * CircleCI

---

## 11.3 Spring Boot 3.x (2022–2025)

### Cloud-Native by Default

* Native support for **GraalVM** → AOT compiled binaries for ultra-fast startup.
* **Podman** & containerd supported along with Docker.
* Enhanced **Spring Boot Buildpacks**:

    * Smaller image layers.
    * Auto-detects JDK version (Java 17–23).

### Kubernetes Enhancements

* Native integration with:

    * **Helm charts**
    * **Kubernetes ConfigMaps & Secrets**
    * **Service Meshes** (Istio, Linkerd)
* Actuator integrated with Kubernetes probes (liveness, readiness, startup).

### CI/CD + DevOps

* GitHub Actions, ArgoCD, and Tekton pipelines widely used.
* Zero-downtime deployments with **Blue/Green, Canary** strategies.
* Observability integrated in CI/CD pipelines:

    * Collecting metrics during deployments.
    * Automated rollbacks on failures.

### Example: Spring Boot + Kubernetes Deployment

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: springboot-app
spec:
  replicas: 3
  selector:
    matchLabels:
      app: springboot-app
  template:
    metadata:
      labels:
        app: springboot-app
    spec:
      containers:
      - name: app
        image: myrepo/springboot-app:1.0
        ports:
        - containerPort: 8080
        readinessProbe:
          httpGet:
            path: /actuator/health/readiness
            port: 8080
```

---

# ✅ Summary

* **Spring Boot 1.x** → Fat JARs + WARs, manual deployments, Jenkins CI.
* **Spring Boot 2.x** → Docker & Buildpacks, Kubernetes (liveness/readiness probes).
* **Spring Boot 3.x** → Cloud-native (GraalVM, native images, Helm, service mesh), modern CI/CD (GitHub Actions, ArgoCD).

Spring Boot evolved from **traditional WAR deployments** → to **containerized, cloud-native apps with full CI/CD pipelines**.

---

```

---

✅ This completes **Section 11 → Deployment & DevOps**.  

Do you want me to now expand **Section 12 → Security (Spring Security evolution, OAuth2, JWT, Zero-Trust)** in the same `.md` style?
```
