
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

# 🧠 Senior Developer Microservices Interview Questions (MNC-Level with Company Tags)

## 📂 Decomposition Patterns

### 1. ❓ How would you break a monolithic application into microservices? *(Asked in Infosys, TCS)*

#### ✅ Answer:

Breaking a monolithic application into microservices is a multi-step process. The key is to gradually identify bounded contexts and migrate functionality in a controlled way.

#### 🔧 Steps:

1. **Understand the Monolith:**

    * Analyze the codebase and domain.
    * Identify modules, coupling, and dependencies.

2. **Define Bounded Contexts:**

    * Use Domain-Driven Design (DDD) to break into smaller domains.
    * Group functionality that changes together.

3. **Prioritize Components:**

    * Choose loosely coupled, high-value components first (e.g., User, Orders, Payments).

4. **Extract Microservices One-by-One:**

    * Use techniques like API Gateway and Backend-for-Frontend (BFF).
    * Extract a domain, expose REST/gRPC API.

5. **Set Up Communication:**

    * Use synchronous (REST/gRPC) or asynchronous (Kafka/RabbitMQ) messaging.

6. **Migrate Data:**

    * Either split the database (database-per-service) or introduce views initially.

7. **Deployment:**

    * Use containers (Docker, K8s) for individual deployment.


```text
[Client] → [API Gateway] → [Order Service] → [Inventory Service] → [Payment Service]
```

#### 🧪 Follow-up Questions:

* How do you manage shared authentication?
* What challenges did you face while decoupling the DB?
* How do you ensure backward compatibility during transition?

---

### 2. ❓ What’s the difference between decomposition by business capability vs. by subdomain? *(Asked in Wipro)*

#### ✅ Answer:

**Business Capability** and **Subdomain** based decomposition both aim to break large systems into manageable units, but with different philosophies.

| Aspect     | Business Capability                                   | Subdomain                                                               |
| ---------- | ----------------------------------------------------- | ----------------------------------------------------------------------- |
| Definition | High-level business function (e.g., Payments, Orders) | Logical breakdown of a domain (e.g., Catalog, Pricing inside eCommerce) |
| Scope      | Broader, based on enterprise goals                    | Finer-grained, based on domain models                                   |
| Tools      | Business Architecture, Process Maps                   | DDD (Domain-Driven Design), Bounded Contexts                            |

#### 🧠 Example:

In an eCommerce system:

* **Business Capabilities:** User Management, Order Processing, Payment Gateway
* **Subdomains:** Within Order Processing → Order Validation, Shipping, Invoicing

#### 🧪 Follow-up Questions:

* Which one would you prefer for legacy system decomposition?
* How do you map these to teams?

---

### 3. ❓ Can you explain the Strangler Fig pattern and where you've used it? *(Asked in Tech Mahindra)*

#### ✅ Answer:

The **Strangler Fig Pattern** is a technique to gradually replace a monolithic system by building a new system around the edges and redirecting traffic, eventually "strangling" the monolith.

#### 🌱 Origin:

It’s named after a fig tree that grows around a host tree and slowly replaces it.

#### 🔧 Steps:

1. Route requests through a **proxy/gateway**.
2. Implement new features in **microservices**.
3. Migrate existing features one-by-one.
4. Decommission parts of the monolith gradually.

#### 💡 Architecture Flow:

```text
          ┌────────────┐
Client →  │  Gateway   │
          └────┬───────┘
               ↓
 ┌─────────────────────────────┐
 │     Monolith (Old)          │
 └─────────────────────────────┘
 ┌─────────────────────────────┐
 │   New Microservices (New)   │
 └─────────────────────────────┘
```

#### 💼 Real-world Use Case:

In a legacy payroll system, we used the strangler fig pattern to migrate the attendance, tax, and leave modules individually to Spring Boot-based services while the remaining parts were kept in the monolith.

#### 🧪 Follow-up Questions:

* How do you route requests dynamically to old/new services?
* How do you track progress and rollback if a new service fails?


## 📂 Integration Patterns
# Microservices Communication & Integration

## ❓ What is an API Gateway, and why is it important in microservices? *(Asked in Accenture, Cognizant)*

### ✅ Answer:

An **API Gateway** is a single entry point for all clients to interact with the backend microservices. It acts as a reverse proxy, routing requests, handling authentication, rate limiting, caching, and protocol translation.

### 🧠 Why it’s Important:

* **Single Entry Point**: Centralizes access to services.
* **Security**: Handles authentication, authorization, and encryption (TLS).
* **Load Balancing**: Distributes traffic evenly across services.
* **Request Aggregation**: Combines responses from multiple services into one.
* **Cross-Cutting Concerns**: Logging, tracing, monitoring, retry policies, etc.

### 🛠️ Example Tools:

* **Spring Cloud Gateway**
* **Netflix Zuul**
* **Kong**
* **Nginx**

---

## ❓ How do you handle communication between services — sync vs async? *(Asked in Capgemini)*

### ✅ Answer:

### 1. **Synchronous Communication (Sync):**

* **Definition**: One service calls another via REST or gRPC and waits for the response.
* **Protocols**: HTTP/HTTPS, gRPC
* **Pros**: Simpler, direct responses
* **Cons**: Tightly coupled, failure-prone if downstream service is slow or down

### 2. **Asynchronous Communication (Async):**

* **Definition**: Services communicate via events or messages without waiting for a response.
* **Protocols**: Kafka, RabbitMQ, ActiveMQ
* **Pros**: Loose coupling, better resilience & scalability
* **Cons**: Complex to debug and monitor, eventual consistency

### 🛠️ When to Use What?

* **Sync** for user-facing requests that require immediate feedback (e.g., login, payments)
* **Async** for event-driven, background, or batch jobs (e.g., email notifications, audit logging)

---

## ❓ What is service discovery? How do client-side and server-side discovery differ? *(Asked in IBM)*

### ✅ Answer:

**Service discovery** is the process of automatically detecting and resolving the network location (host/port) of microservice instances.

### 🧭 Types:

#### 1. **Client-side discovery**

* The client queries a service registry to find available service instances.
* **Example**: Netflix Ribbon with Eureka

#### 2. **Server-side discovery**

* The client makes a request to a load balancer, which queries the registry and forwards the request.
* **Example**: AWS ALB + ECS, Kubernetes with CoreDNS

### 🔄 Service Registry Examples:

* **Eureka**
* **Consul**
* **Zookeeper**
* **Kubernetes** (via DNS)

---

## ❓ Have you worked with message brokers like Kafka or RabbitMQ? *(Asked in HCL Technologies)*

### ✅ Answer:

Yes. In several enterprise projects:

### Apache Kafka:

* Used for real-time data pipelines and streaming.
* Implemented **event sourcing** and **pub-sub architecture**.
* Features: High throughput, partitioning, message durability.
* Example: User sign-up events were published to Kafka and consumed by analytics, email, and audit services.

### RabbitMQ:

* Used for task queues and async processing.
* Implemented **delayed retry queues** and **dead-letter exchanges**.
* Suitable for request buffering and load smoothing.

### Key Differences:

| Feature       | Kafka                    | RabbitMQ              |
| ------------- | ------------------------ | --------------------- |
| Type          | Distributed log          | Message broker (AMQP) |
| Use-case      | Stream processing        | Task/message queuing  |
| Message Order | Maintained per partition | Not guaranteed        |
| Throughput    | High                     | Medium                |

Both tools were instrumental in building resilient, scalable, and decoupled services.


## 📂 Database Patterns

## 📂 Database Patterns in Microservices

---

### ❓ How do you maintain data consistency across microservices?

**Interviewed At:** Infosys, Capgemini

**Answer:**
Maintaining data consistency across distributed microservices is challenging because each service owns its own database. We handle it using:

1. **Eventual Consistency**: Rather than strict ACID guarantees, services communicate via events using brokers like Kafka. Updates propagate eventually.
2. **SAGA Pattern**: For long-running, multi-service transactions.
3. **Compensating Transactions**: Used to undo work when a downstream service fails.
4. **Idempotency & Retries**: Ensures message reprocessing doesn’t corrupt data.

> Example: In an e-commerce app, placing an order triggers events to reserve stock and initiate payment. If payment fails, inventory is released via a compensating transaction.

---

### ❓ What is the Saga pattern? Can you explain with an example?

**Interviewed At:** TCS

**Answer:**
The Saga Pattern is used to maintain data consistency in distributed transactions by breaking them into a series of local transactions, coordinated through events or commands.

Two main types:

1. **Choreography** – Events trigger actions across services without a central orchestrator.
2. **Orchestration** – A central saga coordinator issues commands to services.

> Example:
>
> 1. Order Service creates order.
> 2. It emits an event → Inventory Service reserves stock.
> 3. If successful, → Payment Service charges customer.
> 4. If payment fails, a rollback event is sent → Inventory Service releases stock → Order Service marks order as failed.

---

### ❓ What is CQRS and how does it help in a microservices architecture?

**Interviewed At:** L\&T Infotech

**Answer:**
**CQRS (Command Query Responsibility Segregation)** separates read and write operations:

* **Commands**: Update state (Write DB).
* **Queries**: Retrieve data (Read DB).

**Benefits:**

* Scalability: Read/write parts scale independently.
* Optimized queries: Read models can be denormalized.
* Enhanced security: Separate models allow fine-grained permissioning.

> Use Case: In a banking app, `DepositMoneyCommand` updates balance, while `GetTransactionHistoryQuery` fetches past transactions from a separate, fast-read store.

---

### ❓ Can you describe Event Sourcing and when to use it?

**Interviewed At:** Cognizant

**Answer:**
**Event Sourcing** is a pattern where state changes are stored as a sequence of events instead of directly modifying and storing the current state.

**How it works:**

* Events (e.g., "OrderCreated", "StockReserved") are persisted.
* Current state is rebuilt by replaying events.

**Benefits:**

* Full audit trail.
* Replayability and debugging.
* Naturally fits with CQRS and Kafka.

**Use Cases:**

* Financial apps needing audit logs.
* Systems with high business rule complexity.

> Example: Instead of saving the latest bank balance, store all transactions (events). The current balance is derived by replaying them.

---

Let me know if you'd like visuals or example code added to any of these patterns.

## 📂 Observability Patterns

### ❓ How do you implement centralized logging in a microservices setup? *(Asked in Wipro)*

**Answer:**
Centralized logging involves aggregating logs from multiple services into a single location to simplify monitoring and debugging.

**Approach:**

* Use a logging agent like **Fluentd**, **Logstash**, or **Filebeat**.
* Ship logs to a centralized storage like **Elasticsearch**.
* Visualize using tools like **Kibana** or **Grafana Loki**.

**Stack Example:**

```
Service Logs --> Filebeat/Fluentd --> Logstash --> Elasticsearch --> Kibana (ELK Stack)
```

**Benefits:**

* Correlate logs across services.
* Easier root cause analysis.
* Alerting based on log patterns.

---

### ❓ What is distributed tracing and which tools have you used? *(Asked in Infosys, Zensar)*

**Answer:**
Distributed tracing is a technique to trace a request as it travels across multiple microservices. It provides visibility into latency and bottlenecks.

**Tools Used:**

* **Jaeger**
* **Zipkin**
* **OpenTelemetry** (standardized approach)

**How It Works:**

* Each service attaches a trace ID to the request.
* As the request flows, spans are created.
* The tracing system aggregates spans and shows a visual call tree.

**Use Case:**

> When a payment service was causing slow response time, tracing revealed a downstream fraud-check service was the bottleneck.

---

### ❓ How do you monitor microservices in production? *(Asked in IBM)*

**Answer:**
Monitoring involves tracking service health, metrics, and performance in real time.

**Tools & Techniques:**

* **Prometheus**: Metrics collection
* **Grafana**: Dashboard visualization
* **Alertmanager**: Threshold-based alerts
* **New Relic** / **Datadog**: Full-stack observability (APM)

**Metrics to Monitor:**

* CPU/memory usage
* Request rate and error rate
* Latency (p50, p90, p99)
* Uptime & availability

---

### ❓ What health checks do you configure for your services? *(Asked in TCS)*

**Answer:**
Health checks help load balancers or orchestration tools like Kubernetes determine if a service is running correctly.

**Types of Health Checks:**

* **Liveness Check:** Is the app running?
* **Readiness Check:** Is the app ready to receive traffic?

**Example Endpoint:**

```http
GET /actuator/health
```

**Spring Boot Example:**

```yaml
management:
  endpoints:
    web:
      exposure:
        include: health,info
```

**Benefits:**

* Prevent routing to unhealthy services.
* Enable auto-recovery through container restart.

---


## 📂 Deployment Patterns

## 📦 Deployment Patterns in Microservices

### ❓ What deployment strategies have you used — Blue-Green or Canary? *(Asked in Tech Mahindra)*

**Answer:**
I have implemented both **Blue-Green** and **Canary deployments** depending on the release requirements.

* **Blue-Green Deployment:**

    * Two identical environments (Blue & Green).
    * New version is deployed to the idle environment (e.g., Green).
    * After testing, traffic is switched from Blue to Green.
    * Rollback is instant — just switch back to Blue.
    * **Used when:** There is high risk, and rollback needs to be fast.

* **Canary Deployment:**

    * New version is released to a small subset of users first.
    * If no issues, traffic is gradually increased.
    * **Used when:** You want to test new features in production slowly.

---

### ❓ How do you achieve zero downtime deployments in microservices? *(Asked in Capgemini)*

**Answer:**
To ensure **zero downtime**, I follow these practices:

1. **Rolling Updates**: Update services one pod/container at a time.
2. **Load Balancer + Health Checks**: Route traffic only to healthy instances.
3. **Blue-Green or Canary Deployments**: As discussed above.
4. **Feature Flags**: Enable/disable features without redeploying code.
5. **Graceful Shutdown**: Services stop accepting traffic and finish ongoing tasks before termination.

**Tools used:** Kubernetes (`rollingUpdate` strategy), Spring Boot Actuator for health endpoints, LaunchDarkly for feature flags.

---

### ❓ What are the pros and cons of serverless for microservices? *(Asked in Accenture)*

**Answer:**
**Pros:**

* **No server management:** Fully managed runtime.
* **Auto-scaling:** Based on actual usage.
* **Cost-effective:** Pay only for what you use.
* **Faster time to market.**

**Cons:**

* **Cold starts:** Can lead to latency.
* **Debugging and monitoring** is harder.
* **Vendor lock-in.**
* **Limited execution time and resources**.

**When I used it:**

* For lightweight, event-driven tasks (e.g., image processing, notification services) using AWS Lambda.

---

### ❓ Explain how you use containers (Docker/K8s) for deployment. *(Asked in HCL Technologies)*

**Answer:**
I containerize each microservice using **Docker**, then manage deployment using **Kubernetes**.

**Steps:**

1. **Dockerfile**: Define environment, dependencies, and entry point.
2. **CI/CD Pipeline**: Build Docker images → Push to container registry (e.g., ECR/DockerHub).
3. **Kubernetes Manifests**:

    * **Deployments** for rolling updates
    * **Services** for service discovery
    * **Ingress** for routing external traffic
4. **Helm**: For templated deployments.

**Benefits:**

* Environment consistency
* Scalable, resilient deployments
* Easy rollback and horizontal scaling

---

Let me know if you'd like diagrams or YAML examples for these answers!


# 📂 Resilience Patterns in Microservices

## ❓ How do you handle configuration management across services? *(Asked in TCS)*

**Answer**:
In a microservices architecture, centralizing configuration helps ensure consistency and easier updates. I use Spring Cloud Config Server backed by Git to externalize configurations for all services. This allows changes without restarting the services.

- For dynamic refresh, I enable `@RefreshScope` and use Actuator `/refresh` endpoint.
- Secrets and sensitive values are managed using HashiCorp Vault or AWS Parameter Store.

**Example**: In a project for a retail client, we externalized database URLs, feature toggles, and limits. One update to `application.yml` in Git reflected across environments without redeployments.

---

## ❓ What is a Circuit Breaker and how have you implemented it? *(Asked in Infosys)*

**Answer**:
A circuit breaker prevents cascading failures by detecting failed remote service calls and halting further calls temporarily.

I’ve implemented it using **Resilience4j** in Spring Boot:

```java
@CircuitBreaker(name = "inventoryService", fallbackMethod = "fallbackInventory")
public Inventory checkInventory(String productId) {
    return inventoryClient.getInventory(productId);
}

public Inventory fallbackInventory(String productId, Throwable t) {
    return new Inventory(productId, 0); // Default response
}
```

**Real-life**: When an external inventory service was down, circuit breaker reduced downtime by stopping repetitive failing calls and using fallback stock level.

---

## ❓ How do you secure communication between services? *(Asked in Cognizant)*

**Answer**:
I use **mTLS (Mutual TLS)** and **OAuth2/JWT** for securing service-to-service communication.

- **mTLS** ensures both client and server authenticate each other using certificates.
- **OAuth2** with Keycloak/Azure AD issues access tokens validated at each service boundary.

**Real-life**: In a fintech project, we used Istio with mTLS enabled by default in the mesh, and each service validated JWT issued by a centralized identity provider.

---

## ❓ What is rate limiting and how do you implement it in a gateway? *(Asked in Capgemini)*

**Answer**:
Rate limiting restricts the number of requests a client can make in a time window to protect services from abuse.

I use **Spring Cloud Gateway** with Redis-backed rate limiting filters:

```yaml
spring:
  cloud:
    gateway:
      routes:
      - id: product-api
        uri: lb://product-service
        predicates:
        - Path=/product/**
        filters:
        - name: RequestRateLimiter
          args:
            redis-rate-limiter.replenishRate: 5
            redis-rate-limiter.burstCapacity: 10
```

**Real-life**: In an e-commerce app, we applied per-user rate limiting at gateway level to prevent scraping and brute-force attacks.

---

## ❓ Explain bulkhead and retry patterns with real-life examples. *(Asked in Wipro)*

**Answer**:

- **Bulkhead Pattern**: Isolates service dependencies in separate thread pools to avoid one failing dependency affecting others.

```java
@Bulkhead(name = "orderService", type = Bulkhead.Type.THREADPOOL)
public OrderResponse getOrder(String id) { ... }
```

- **Retry Pattern**: Automatically retries a failed operation a limited number of times.

```java
@Retry(name = "paymentService", fallbackMethod = "fallbackPayment")
public Payment processPayment(Request request) { ... }
```

**Real-life**: In a travel booking system:
- We used **bulkhead** to separate calls to flight, hotel, and cab services.
- **Retry** helped recover from transient network issues to external APIs like payment gateways.

---

✅ **Tip**: Use Resilience4j with Spring Boot for all resilience patterns: Circuit Breaker, Bulkhead, Retry, RateLimiter, and TimeLimiter.

## 📂 Infrastructure/Operational Patterns

# 📂 Microservices Communication & Discovery Patterns

## ❓ What is the role of a service registry like Eureka or Consul? *(Asked in IBM)*

A **service registry** is a database of services and their locations. It enables **service discovery**, which allows microservices to dynamically find and connect to each other without hardcoding IPs or ports.

### ✅ Example:
- `Eureka` (Spring Cloud Netflix) or `Consul` (HashiCorp) are commonly used.
- A client registers itself on startup.
- Other services query the registry to find its instance location.

### 📦 Benefits:
- Load balancing and fault tolerance (with Ribbon or Spring Cloud LoadBalancer).
- Helps scale services elastically and avoid stale configs.

---

## ❓ Can you explain the Sidecar and Ambassador patterns in service mesh? *(Asked in Infosys)*

These are **service mesh patterns** that abstract infrastructure concerns from the main application logic.

### 🧩 Sidecar Pattern:
- A helper container runs alongside your service container in the same pod.
- It handles logging, metrics, routing, proxying (e.g., Istio’s Envoy proxy).

### 🤝 Ambassador Pattern:
- A separate service acts as a proxy between your application and external services.
- Useful for offloading cross-cutting concerns (e.g., rate-limiting, auth).

---

## ❓ How does load balancing work in your architecture? *(Asked in Tech Mahindra)*

Load balancing ensures incoming requests are distributed evenly across multiple instances.

### ⚙️ Common Techniques:
- **Client-side LB**: e.g., Ribbon, Spring Cloud LoadBalancer, uses service registry to choose a healthy instance.
- **Server-side LB**: e.g., NGINX, HAProxy, Kubernetes Services, or Ingress Controllers.
- **Global LB**: e.g., AWS Elastic Load Balancer, GCP Load Balancing.

### 🔁 Example:
```java
@LoadBalanced
@Bean
public RestTemplate restTemplate() {
    return new RestTemplate();
}
```

---

## ❓ How do you scale services dynamically? *(Asked in Cognizant, HCL)*

### 🛠️ Manual Scaling:
- Increase/decrease instances manually (e.g., using Kubernetes CLI or Spring Boot Admin).

### 🤖 Auto-Scaling:
- **Kubernetes Horizontal Pod Autoscaler** based on CPU/memory or custom metrics.
- **Cloud-native** scaling using AWS EC2 Auto Scaling Groups, GCP App Engine, etc.

### 💡 Example:
```yaml
apiVersion: autoscaling/v2
kind: HorizontalPodAutoscaler
spec:
  scaleTargetRef:
    apiVersion: apps/v1
    kind: Deployment
    name: my-service
  minReplicas: 2
  maxReplicas: 10
  metrics:
    - type: Resource
      resource:
        name: cpu
        target:
          type: Utilization
          averageUtilization: 50
```

---------
