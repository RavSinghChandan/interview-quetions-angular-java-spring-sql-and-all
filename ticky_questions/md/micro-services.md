

**(Grouped topic-wise for senior-level backend/full-stack interviews)**

---

### 🔹 1. **Service Communication**

1. **How does Spring Cloud OpenFeign work under the hood? What happens during a FeignClient call?**
2. **What are the failure cases of using `RestTemplate` vs `WebClient` in high-traffic microservices?**
3. **How do you prevent client-side retries from becoming a cascading failure in a Feign-based system?**
4. **What is the circuit breaker pattern, and how does Resilience4j implement it internally?**
5. **Explain the difference between synchronous (REST) and asynchronous (event-driven) service calls.**

---

### 🔹 2. **Service Discovery & Load Balancing**

6. **How does Eureka service registry maintain heartbeats, and what happens when a node fails?**
7. **What's the difference between Ribbon and Spring Cloud LoadBalancer? Why was Ribbon deprecated?**
8. **How does client-side load balancing actually work in Spring Cloud?**
9. **What happens when a Eureka client is not registered but a Feign call is made to that service?**
10. **How would you prevent stale Eureka cache during autoscaling events?**

---

### 🔹 3. **API Gateway / Edge Services**

11. **How does Spring Cloud Gateway route requests, and how is it different from Zuul?**
12. **What filters can you apply in Gateway — and at what lifecycle stages (pre, post)?**
13. **How do you apply rate limiting in API Gateway for specific clients (like per IP)?**
14. **How do you do dynamic routing in API Gateway based on request content?**
15. **How do JWT tokens get validated at the Gateway level without hitting every microservice?**

---

### 🔹 4. **Resilience and Fault Tolerance**

16. **Explain bulkhead pattern with a real-world analogy — and how Resilience4j supports it.**
17. **What’s the difference between timeout at the HTTP level vs thread-pool isolation?**
18. **What is the fallback strategy in case of circuit breaker open? Can it be dynamic?**
19. **What happens if fallback itself fails in circuit breaker? How would you design that?**
20. **How do you detect and handle cascading failures across microservices?**

---

### 🔹 5. **Centralized Configuration and Secrets**

21. **How does Spring Cloud Config Server fetch and serve configuration properties?**
22. **How do you auto-refresh configs in microservices when the Git repo changes?**
23. **How to secure sensitive configs (DB password) in Config Server?**
24. **What happens if Config Server is down during app startup — how would you handle it?**
25. **What are the tradeoffs between HashiCorp Vault and Spring Cloud Config encryption?**

---

### 🔹 6. **Distributed Tracing and Logging**

26. **How does Sleuth propagate trace IDs across threads and HTTP calls?**
27. **What headers are involved in Zipkin tracing?**
28. **How do you correlate logs from multiple microservices for a single request?**
29. **How do you handle logging context (MDC) in async/non-blocking operations?**
30. **Explain the difference between tracing, logging, and metrics — and how they work together.**

---
