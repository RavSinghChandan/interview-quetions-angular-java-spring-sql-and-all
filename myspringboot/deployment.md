Perfect 👌 Now let’s expand **Section 7 → Deployment & DevOps** in Spring Boot.
This covers **Packaging, Configurations, Docker, Kubernetes, and CI/CD** from **Spring Boot 1.x → 2.x → 3.x**.

Here’s the full `.md` content:

---

````markdown
# 7. Deployment & DevOps

Spring Boot revolutionized deployment by making apps **self-contained** (no external server required).  
It evolved from **fat JARs** → to **cloud-native, container-first deployments with Kubernetes & CI/CD pipelines**.

---

## 7.1 Packaging (JARs & WARs)

### Spring Boot 1.x (2014)
- Introduced **fat JARs** (a.k.a uber-JARs):
  - Embedded Tomcat/Jetty/Undertow.
  - Eliminated need for external app servers.
- Still supported **WAR packaging** for legacy systems.

```bash
mvn clean package
java -jar myapp-1.0.jar
````

---

### Spring Boot 2.x (2018)

* Improved **executable JAR layout**.
* Added support for **layered JARs** (optimized for Docker image layers).

```bash
mvn spring-boot:build-image
```

* Gradle plugin matured for boot JAR packaging.

---

### Spring Boot 3.x (2022–2025)

* Fully **native image support** with GraalVM:

    * Smaller footprint, faster startup.
    * First-class support with `spring-boot-maven-plugin`.
* JARs optimized for **AOT (Ahead-of-Time) compilation**.

---

## 7.2 External Configuration

### Spring Boot 1.x

* Supported `application.properties`.
* Profiles introduced with `spring.profiles.active`.

---

### Spring Boot 2.x

* Added `application.yml`.
* Hierarchical config properties.
* Integration with **Spring Cloud Config Server**.

```yaml
spring:
  profiles:
    active: prod
  datasource:
    url: jdbc:mysql://localhost:3306/mydb
```

---

### Spring Boot 3.x

* Enhanced externalized config:

    * ENV variables, YAML, JSON, Vault, Consul, Kubernetes ConfigMaps/Secrets.
* `ConfigData API` replaced legacy `bootstrap.properties`.

---

## 7.3 Dockerization

### Spring Boot 1.x

* Manual Dockerfile setup.

```dockerfile
FROM openjdk:8-jdk-alpine
COPY target/myapp.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

---

### Spring Boot 2.x

* Introduced **Cloud Native Buildpacks** (`pack`, `spring-boot:build-image`).
* Eliminated need for Dockerfile in many cases.
* Optimized for layered caching.

---

### Spring Boot 3.x

* Native image builds inside containers.
* Out-of-the-box support for **Docker Compose** via `spring-boot-docker-compose`.
* Dev services for DB, Kafka, Redis during local dev.

---

## 7.4 Kubernetes Deployment

### Spring Boot 1.x

* No official Kubernetes integration.
* Used raw YAML manifests.

---

### Spring Boot 2.x

* Kubernetes probes (`liveness`, `readiness`) via Actuator.
* Configurable resource usage & autoscaling.
* Spring Cloud Kubernetes project for service discovery & config.

---

### Spring Boot 3.x

* First-class Kubernetes-native features:

    * Native health probes (`startup`, `liveness`, `readiness`).
    * Seamless ConfigMap & Secret integration.
    * Enhanced observability with Prometheus + OpenTelemetry.
* Declarative deployment with Helm & Kustomize.

---

## 7.5 CI/CD Integration

### Spring Boot 1.x

* Basic Maven/Gradle pipelines for build & deploy.
* Manual Jenkins setups.

---

### Spring Boot 2.x

* DevOps-ready:

    * GitHub Actions, GitLab CI, CircleCI.
    * Automated testing with Testcontainers in pipelines.
* Cloud-native deployment to AWS, Azure, GCP.

---

### Spring Boot 3.x

* CI/CD pipelines optimized for **native builds**.
* Docker + Kubernetes + Helm automated via pipelines.
* Built-in support for SBOM (Software Bill of Materials) for security scans.
* GitOps integration with ArgoCD, FluxCD.

---

# ✅ Summary

* **Packaging** → From Fat JARs (1.x) → Layered JARs & buildpacks (2.x) → Native images (3.x).
* **Configuration** → From properties (1.x) → YAML & Config Server (2.x) → Kubernetes-native configs (3.x).
* **Dockerization** → From manual Dockerfiles (1.x) → Buildpacks (2.x) → Native builds + Docker Compose integration (3.x).
* **Kubernetes** → From DIY YAML (1.x) → probes & discovery (2.x) → full native integration (3.x).
* **CI/CD** → From basic pipelines (1.x) → DevOps-ready automation (2.x) → GitOps & SBOM compliance (3.x).

Spring Boot deployment evolved from **simple fat JARs** → to **cloud-native, Kubernetes-first DevOps workflows**.

---

```

---

✅ This completes **Spring Boot Deployment & DevOps**.  

Do you want me to now expand **Section 8 → Spring Cloud (Microservices)** in the same `.md` style?
```
