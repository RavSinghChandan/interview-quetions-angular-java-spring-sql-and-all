
---

## 🚀 DevOps – Tricky & Deep-Dive Interview Questions

### For Java Backend Engineers (Topic-wise)

---

### 🔹 1. **CI/CD Pipelines (Jenkins, GitHub Actions, GitLab CI)**

1. **How does Jenkins detect changes in your Git repo without polling?**
2. **What is the difference between `Declarative` and `Scripted` Jenkins pipelines internally?**
3. **How do you pass sensitive credentials securely in a pipeline?**
4. **What is the purpose of `post` block in a Jenkins pipeline?**
5. **How would you optimize a pipeline that builds 4 microservices but only 2 changed?**

---

### 🔹 2. **Build Tools (Maven/Gradle)**

6. **What happens internally when you run `mvn clean install`? List each lifecycle phase.**
7. **How are dependencies resolved in Maven and what happens if there’s a version conflict?**
8. **What’s the difference between `compile`, `provided`, `runtime`, and `test` scopes?**
9. **How do you create a multi-module Maven project with shared libraries?**
10. **How do you skip tests in Maven but still compile test classes?**

---

### 🔹 3. **Containerization (Docker)**

11. **What is the difference between `COPY` and `ADD` in a Dockerfile?**
12. **What is the layer cache in Docker and when does it get invalidated?**
13. **Why is it recommended to place `RUN apt-get install...` after `COPY . .` in some Dockerfiles?**
14. **How would you reduce the size of a Java-based Docker image?**
15. **What is a multi-stage Docker build and how does it work?**

---

### 🔹 4. **Orchestration (Kubernetes)**

16. **What is the difference between a ReplicaSet and a Deployment?**
17. **How does Kubernetes perform rolling updates?**
18. **What is a liveness vs readiness probe? When should each be used in Spring Boot apps?**
19. **What is the function of the kube-proxy and how does it work under the hood?**
20. **How does Kubernetes handle service discovery within a cluster?**

---

### 🔹 5. **Monitoring / Logging / Alerting**

21. **How does Prometheus pull metrics from Spring Boot apps? What annotations are needed?**
22. **What is the difference between logs, metrics, and traces (observability trio)?**
23. **How would you identify a memory leak in a live Spring Boot app using DevOps tools?**
24. **How can you enable structured JSON logging in Spring Boot and ship it to ELK or Loki?**
25. **What’s the purpose of Fluentd or Logstash in a DevOps pipeline?**

---

### 🔹 6. **Infrastructure as Code (IaC) - Terraform / Ansible**

26. **What’s the difference between Terraform and Ansible?**
27. **What happens when you run `terraform plan` vs `terraform apply`?**
28. **How does Terraform maintain the state of infrastructure?**
29. **What is idempotency in IaC and how is it ensured?**
30. **How do you manage secrets in Terraform (without putting them in `.tf` files)?**

---

### 🔹 7. **Security in DevOps**

31. **How do you scan Docker images for vulnerabilities automatically in CI/CD?**
32. **What is SBOM (Software Bill of Materials) and why is it important?**
33. **How would you ensure secrets are not hardcoded in Git repos or config files?**
34. **What are some common OWASP vulnerabilities you can catch via DevSecOps?**
35. **How do you enable HTTPS termination at NGINX level for internal microservices?**

---

### 🔹 8. **Cloud & Deployment**

36. **What is the difference between blue-green and canary deployment?**
37. **How would you rollback a failed deployment automatically using Kubernetes?**
38. **How do you configure horizontal pod autoscaling in K8s based on CPU & custom metrics?**
39. **What’s the difference between StatefulSets and Deployments in K8s?**
40. **How would you securely inject config (like DB credentials) into a running container?**

---
