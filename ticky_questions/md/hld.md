

### 🔹 1. **Scalability & Load Handling**

* **Horizontal vs Vertical Scaling – when to use which?**
* **How do you design a system to handle 10K, 100K, or 1M requests per minute?**
* **What is a load balancer? How does it decide where to forward traffic?**
* **What are the bottlenecks in scaling a monolith?**

💡 **Use Case Example:** Design a URL shortener (e.g., Bitly)

---

### 🔹 2. **Microservices Architecture**

* **How do you split a monolith into microservices?**
* **How do services communicate – sync vs async?**
* **What happens when one service is down (resilience design)?**
* **How do you manage service discovery (Eureka/Consul)?**

💡 **Key Concepts:** Service registry, Circuit Breaker (Resilience4j), Load Balancer, Config Server

---

### 🔹 3. **Data Storage & Database Design**

* **How do you decide between SQL and NoSQL?**
* **How to design a schema for high write traffic (e.g., logging system)?**
* **How to scale a relational DB (read-replicas, sharding)?**
* **CAP Theorem – when would you sacrifice consistency?**

💡 **Use Case Example:** Design Instagram Feed (Read-heavy, eventually consistent)

---

### 🔹 4. **Caching & CDN**

* **Why do you need caching (Redis, Memcached)?**
* **What should not be cached?**
* **What’s cache invalidation, and why is it hard?**
* **What’s the difference between CDN and app-level cache?**

💡 **Interview Trick:** LRU cache implementation (LinkedHashMap in Java)

---

### 🔹 5. **Queueing & Async Processing**

* **Why use queues (Kafka, RabbitMQ, SQS)?**
* **How do you prevent message loss?**
* **What is message deduplication or idempotency?**
* **How does backpressure work in queues?**

💡 **Example:** Email/SMS sending after user signup (background processing)

---

### 🔹 6. **High Availability & Fault Tolerance**

* **How to make your service 99.99% available?**
* **What’s active-active vs active-passive?**
* **How to handle service failures gracefully?**
* **What happens when a database goes down?**

💡 **Related Concepts:** Retry, Timeout, Circuit Breaker, Health Checks

---

### 🔹 7. **API Design & Versioning**

* **How do you design RESTful APIs?**
* **How do you version APIs (URL vs header)?**
* **How do you handle breaking changes without affecting clients?**
* **What’s the role of API Gateway in a large system?**

💡 **Bonus:** Add Swagger/OpenAPI for contract-first design

---

### 🔹 8. **Security & Authentication**

* **JWT vs Session-based Auth – pros and cons?**
* **How do you secure APIs between services (mTLS, API Gateway JWT validation)?**
* **What’s the principle of least privilege in microservices?**
* **How would you prevent XSS, CSRF, SQLi attacks?**

💡 **Scenario:** Design Auth system for 3rd party apps (OAuth2)

---

### 🔹 9. **Observability (Logs, Metrics, Tracing)**

* **What’s the difference between logs, metrics, and traces?**
* **How do you trace a request across microservices?**
* **How do you debug latency issues in production?**
* **What tools do you use for alerting (Prometheus, Grafana, ELK, Zipkin, etc.)?**

💡 **Follow-up:** Design a logging service used by all microservices

---

### 🔹 10. **Deployment, CI/CD & DevOps**

* **What’s blue-green vs canary deployment?**
* **How do you design rollback strategy?**
* **How do you ensure zero-downtime deployments?**
* **What are containers, and why are they better than VMs?**

💡 **Stack:** Jenkins, Docker, Kubernetes, ArgoCD

---

