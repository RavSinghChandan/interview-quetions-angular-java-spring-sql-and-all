
# 📦 Microservices Design Patterns

Microservices design patterns are categorized to manage different aspects like decomposition, communication, data, deployment, observability, etc.

---

## ✅ 📂 Categories of Microservices Design Patterns

| Category | Description |
|----------|-------------|
| **1. Decomposition Patterns** | Breaking down a monolith into microservices. |
| **2. Integration Patterns** | Communication between microservices. |
| **3. Database Patterns** | Managing data consistency and storage across services. |
| **4. Observability Patterns** | Monitoring, logging, and tracing. |
| **5. Deployment Patterns** | Managing deployments of microservices. |
| **6. Cross-Cutting Concern Patterns** | Security, configuration, resiliency, etc. |
| **7. Infrastructure/Operational Patterns** | Load balancing, service discovery, etc. |

---

## 🧩 1. Decomposition Patterns

| Pattern | Description |
|--------|-------------|
| **Decompose by Business Capability** | Break system into services aligned with business functions (e.g., Billing, Shipping). |
| **Decompose by Subdomain (DDD)** | Use Domain-Driven Design to split services based on bounded contexts. |
| **Strangler Fig** | Gradually replace legacy monolith by routing requests to new microservices. |

---

## 🔄 2. Integration Patterns (Communication)

| Pattern | Description |
|--------|-------------|
| **API Gateway** | Single entry point for client requests; routes to proper microservice. |
| **Aggregator** | Aggregates responses from multiple services into one. |
| **Proxy** | Similar to API Gateway; forwards requests. |
| **Client-side Service Discovery** | Client queries registry to find service location. |
| **Server-side Service Discovery** | Load balancer queries registry; client talks only to load balancer. |
| **Message Broker / Event-Driven** | Services communicate via asynchronous messages (Kafka, RabbitMQ). |

---

## 🗃️ 3. Database Patterns

| Pattern | Description |
|--------|-------------|
| **Database per Service** | Each microservice has its own private database schema. |
| **Shared Database** | Rare; multiple services use a single database (avoided for tight coupling). |
| **Saga Pattern** | Handle distributed transactions via coordination (sequence of local transactions). |
| **CQRS** | Separate models for read and write operations. |
| **Event Sourcing** | State is determined by a sequence of events, not current DB state. |

---

## 🔍 4. Observability Patterns

| Pattern | Description |
|--------|-------------|
| **Log Aggregation** | Centralized logging system (e.g., ELK, Fluentd). |
| **Distributed Tracing** | Track a request across services (e.g., Zipkin, Jaeger). |
| **Metrics Collection** | Monitor metrics like CPU, memory, latency (e.g., Prometheus + Grafana). |
| **Health Check API** | Expose endpoints to check service health. |

---

## 🚀 5. Deployment Patterns

| Pattern | Description |
|--------|-------------|
| **Service Instance per Host** | One service per VM or container. |
| **Multiple Services per Host** | Multiple services on the same container/VM. |
| **Serverless Deployment** | Functions deployed without managing server infra (e.g., AWS Lambda). |
| **Blue-Green Deployment** | Reduce downtime during deployments. |
| **Canary Deployment** | Release to small group before full rollout. |

---

## 🔐 6. Cross-Cutting Concern Patterns

| Pattern | Description |
|--------|-------------|
| **Externalized Configuration** | Store config outside the service (e.g., Spring Cloud Config). |
| **Centralized Logging** | Uniform log handling across services. |
| **Access Token / JWT** | Secure service-to-service and client-to-service communication. |
| **Rate Limiting** | Control incoming traffic to protect backend. |
| **Circuit Breaker** | Prevent cascading failures (e.g., Netflix Hystrix, Resilience4j). |
| **Bulkhead** | Isolate failures in one part to avoid crashing whole system. |
| **Retry Pattern** | Retry failed requests with backoff logic. |
| **Timeout** | Avoid hanging due to unresponsive service. |

---

## ⚙️ 7. Infrastructure/Operational Patterns

| Pattern | Description |
|--------|-------------|
| **Service Registry and Discovery** | Dynamic registration and lookup of services (e.g., Eureka, Consul). |
| **Sidecar Pattern** | Auxiliary service (e.g., proxy, config, logging) runs alongside main service. |
| **Ambassador Pattern** | Helper container that acts as a proxy for service communication. |
| **Adapter Pattern** | Bridge between incompatible systems or protocols. |
| **Load Balancer** | Distribute traffic among services (e.g., NGINX, Envoy). |

---

🧠 *Tip: Convert this into a mind map or flashcards for quick revision!*
# 🚀 Microservices Design Patterns with Interview DOM Examples

## 📂 Decomposition Patterns

### 🔸 Decompose by Business Capability

```java
Service: OrderService, PaymentService, InventoryService — Each handles its own business logic.
```

### 🔸 Decompose by Subdomain (DDD)

```java
Service: CatalogService (Product context), OrderService (Order context) — Bound by domain logic.
```

### 🔸 Strangler Fig

```java
Route '/new-api/*' to new microservice while keeping legacy '/old-api/*' in monolith.
```

## 📂 Integration Patterns

### 🔸 API Gateway

```java
Zuul / Spring Cloud Gateway - routes `/order` to OrderService, `/payment` to PaymentService.
```

### 🔸 Aggregator

```java
Gateway calls OrderService and PaymentService, merges result to single response.
```

### 🔸 Client-side Service Discovery

```java
Netflix Eureka + Ribbon: Client discovers service from Eureka registry.
```

### 🔸 Server-side Service Discovery

```java
Client → Load Balancer → Eureka → Service (e.g., via AWS ELB).
```

### 🔸 Message Broker / Event-Driven

```java
OrderService → Kafka Topic → InventoryService (listens asynchronously).
```

## 📂 Database Patterns

### 🔸 Database per Service

```java
OrderService → orders_db, PaymentService → payments_db — no shared schema.
```

### 🔸 Shared Database

```java
Both OrderService and PaymentService connect to same DB (Not recommended).
```

### 🔸 Saga Pattern

```java
OrderPlaced → ReserveInventory → Payment → Ship → if fail → Rollback each step.
```

### 🔸 CQRS

```java
Command API writes to DB, Query API reads from read-optimized DB/view.
```

### 🔸 Event Sourcing

```java
OrderCreated, OrderPaid, OrderShipped events replayed to rebuild order state.
```

## 📂 Observability Patterns

### 🔸 Log Aggregation

```java
Logstash collects logs → Elasticsearch → Kibana dashboard for visualization.
```

### 🔸 Distributed Tracing

```java
Trace-ID flows via headers across services, visualized in Jaeger.
```

### 🔸 Metrics Collection

```java
Prometheus scrapes metrics → Grafana shows CPU, memory, API latency.
```

### 🔸 Health Check API

```java
`/actuator/health` in Spring Boot to check DB, Kafka, etc.
```

## 📂 Deployment Patterns

### 🔸 Service Instance per Host

```java
Docker container for each service → Kubernetes Pod per service.
```

### 🔸 Multiple Services per Host

```java
Monolithic container (anti-pattern) with multiple services.
```

### 🔸 Serverless Deployment

```java
AWS Lambda function `processPayment()` triggered by SQS.
```

### 🔸 Blue-Green Deployment

```java
Blue (v1) running live, Green (v2) deployed and tested → switch traffic.
```

### 🔸 Canary Deployment

```java
Route 10% traffic to new v2 → observe metrics → then rollout to 100%.
```

## 📂 Cross-Cutting Concern Patterns

### 🔸 Externalized Configuration

```java
Spring Cloud Config: `config-server` serves `application.yml` to services.
```

### 🔸 Circuit Breaker

```java
Resilience4j: `@CircuitBreaker(name = 'orderService', fallbackMethod = 'fallback')`
```

### 🔸 Rate Limiting

```java
Bucket4j or API Gateway limiting: max 100 requests/min per user.
```

### 🔸 Retry Pattern

```java
Spring Retry: `@Retryable(value = Exception.class, maxAttempts = 3)`
```

### 🔸 Bulkhead

```java
Separate thread pool for InventoryService calls to isolate from others.
```

### 🔸 Timeout

```java
`RestTemplate` or `WebClient` with `.timeout(Duration.ofSeconds(5))`
```

### 🔸 Access Token / JWT

```java
Clients send JWT in `Authorization` header → validated by services.
```

### 🔸 Centralized Logging

```java
Use Sleuth + Zipkin for tracing logs end-to-end.
```

## 📂 Infrastructure/Operational Patterns

### 🔸 Service Registry and Discovery

```java
Netflix Eureka: Services register with Eureka and discover each other.
```

### 🔸 Sidecar Pattern

```java
Istio sidecar proxy (Envoy) handles logging, config for main container.
```

### 🔸 Ambassador Pattern

```java
Ambassador proxy exposes internal service securely to external consumers.
```

### 🔸 Adapter Pattern

```java
LegacyOrderServiceAdapter translates legacy SOAP to REST internally.
```

### 🔸 Load Balancer

```java
NGINX or Spring Cloud LoadBalancer routes to healthy instances.
```
# 🧠 Microservices Design Patterns – Mind Map

## 📦 Microservices Patterns

- 🔹 **1. Decomposition Patterns**
    - 🧩 Decompose by Business Capability
    - 🧠 Decompose by Subdomain (DDD)
    - 🌿 Strangler Fig

- 🔹 **2. Integration Patterns (Communication)**
    - 🛡️ API Gateway
    - 🧱 Aggregator
    - 🧭 Proxy
    - 🔍 Client-side Service Discovery
    - 🌐 Server-side Service Discovery
    - 📬 Message Broker / Event-Driven

- 🔹 **3. Database Patterns**
    - 🗄️ Database per Service
    - 🧍 Shared Database (anti-pattern)
    - 🔁 Saga Pattern
    - 📑 CQRS
    - 📜 Event Sourcing

- 🔹 **4. Observability Patterns**
    - 📊 Log Aggregation
    - 🧵 Distributed Tracing
    - 📈 Metrics Collection
    - ❤️ Health Check API

- 🔹 **5. Deployment Patterns**
    - 🧳 Service Instance per Host
    - 🧳 Multiple Services per Host
    - ⚡ Serverless Deployment
    - 💚💙 Blue-Green Deployment
    - 🐤 Canary Deployment

- 🔹 **6. Cross-Cutting Concern Patterns**
    - ⚙️ Externalized Configuration
    - 🔌 Centralized Logging
    - 🔐 Access Token / JWT
    - 🚦 Rate Limiting
    - 🚫 Circuit Breaker
    - 🚧 Bulkhead
    - 🔁 Retry Pattern
    - ⏱️ Timeout

- 🔹 **7. Infrastructure/Operational Patterns**
    - 🗃️ Service Registry and Discovery
    - 🧳 Sidecar Pattern
    - 🤝 Ambassador Pattern
    - 🔄 Adapter Pattern
    - ⚖️ Load Balancer
----------------------------

# 🧠 Senior Developer Microservices Interview Questions (MNC-Level with Company Tags)

## 📂 Decomposition Patterns

- ❓ How would you break a monolithic application into microservices? *(Asked in Infosys, TCS)*
- ❓ What’s the difference between decomposition by business capability vs. by subdomain? *(Asked in Wipro)*
- ❓ Can you explain the Strangler Fig pattern and where you've used it? *(Asked in Tech Mahindra)*

## 📂 Integration Patterns

- ❓ What is an API Gateway, and why is it important in microservices? *(Asked in Accenture, Cognizant)*
- ❓ How do you handle communication between services — sync vs async? *(Asked in Capgemini)*
- ❓ What is service discovery? How do client-side and server-side discovery differ? *(Asked in IBM)*
- ❓ Have you worked with message brokers like Kafka or RabbitMQ? *(Asked in HCL Technologies)*

## 📂 Database Patterns

- ❓ How do you maintain data consistency across microservices? *(Asked in Infosys, Capgemini)*
- ❓ What is the Saga pattern? Can you explain with an example? *(Asked in TCS)*
- ❓ What is CQRS and how does it help in a microservices architecture? *(Asked in L&T Infotech)*
- ❓ Can you describe Event Sourcing and when to use it? *(Asked in Cognizant)*

## 📂 Observability Patterns

- ❓ How do you implement centralized logging in a microservices setup? *(Asked in Wipro)*
- ❓ What is distributed tracing and which tools have you used? *(Asked in Infosys, Zensar)*
- ❓ How do you monitor microservices in production? *(Asked in IBM)*
- ❓ What health checks do you configure for your services? *(Asked in TCS)*

## 📂 Deployment Patterns

- ❓ What deployment strategies have you used — Blue-Green or Canary? *(Asked in Tech Mahindra)*
- ❓ How do you achieve zero downtime deployments in microservices? *(Asked in Capgemini)*
- ❓ What are the pros and cons of serverless for microservices? *(Asked in Accenture)*
- ❓ Explain how you use containers (Docker/K8s) for deployment. *(Asked in HCL Technologies)*

## 📂 Cross-Cutting Concern Patterns

- ❓ How do you handle configuration management across services? *(Asked in TCS)*
- ❓ What is a Circuit Breaker and how have you implemented it? *(Asked in Infosys)*
- ❓ How do you secure communication between services? *(Asked in Cognizant)*
- ❓ What is rate limiting and how do you implement it in a gateway? *(Asked in Capgemini)*
- ❓ Explain bulkhead and retry patterns with real-life examples. *(Asked in Wipro)*

## 📂 Infrastructure/Operational Patterns

- ❓ What is the role of a service registry like Eureka or Consul? *(Asked in IBM)*
- ❓ Can you explain the Sidecar and Ambassador patterns in service mesh? *(Asked in Infosys)*
- ❓ How does load balancing work in your architecture? *(Asked in Tech Mahindra)*
- ❓ How do you scale services dynamically? *(Asked in Cognizant, HCL)*
