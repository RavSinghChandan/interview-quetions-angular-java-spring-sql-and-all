
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
---------
# 🧠 Senior Developer Microservices Interview Questions (MNC-Level with Company Tags)

## 📂 Decomposition Patterns

### ❓ How would you break a monolithic application into microservices? *(Asked in Infosys, TCS)*

* **Facts**: Monoliths are broken down by business capabilities, team boundaries, scalability needs.
* **Flow**: Identify domain boundaries → Refactor into bounded contexts → Define service APIs → Extract services iteratively.
* **Failures**: Tight coupling, no domain-driven boundaries, or database sharing across services.
* **Fixes**: Apply Domain-Driven Design (DDD), enforce API contracts, use Strangler Fig Pattern.

### ❓ Difference: Decomposition by business capability vs. by subdomain? *(Wipro)*

* **Facts**: Business capability = "What" (e.g., Billing); Subdomain = "How" (e.g., Invoicing, Payment)
* **Flow**: Business capability → aligns to organization; Subdomain → aligns to code modules.
* **Failures**: Overlapping boundaries, team misalignment.
* **Fixes**: Use Event Storming to discover correct bounded contexts.

### ❓ Strangler Fig pattern usage? *(Tech Mahindra)*

* **Facts**: Gradual replacement of legacy code.
* **Flow**: Route traffic → Replace module → Repeat until full migration.
* **Failures**: Overlapping routes, parallel data writes.
* **Fixes**: Use API gateway for routing and feature toggles.

---

## 📂 Integration Patterns

### ❓ API Gateway importance? *(Accenture, Cognizant)*

* **Facts**: Central entry point; handles routing, auth, throttling.
* **Flow**: Client → Gateway → Internal services.
* **Failures**: Latency, single point of failure.
* **Fixes**: Use scalable gateways (e.g., Kong, Zuul) + fallback strategies.

### ❓ Sync vs Async communication? *(Capgemini)*

* **Facts**: Sync = HTTP/gRPC; Async = Kafka, RabbitMQ.
* **Flow**: Event-driven async for decoupling, resiliency.
* **Failures**: Over-reliance on sync = bottlenecks.
* **Fixes**: Use CQRS + event sourcing for async operations.

### ❓ What is service discovery? *(IBM)*

* **Facts**: Auto-discover running services (Eureka, Consul).
* **Flow**: Register on start → Discover via registry.
* **Failures**: Manual endpoint config → tight coupling.
* **Fixes**: Use client-side discovery in Spring Cloud or server-side with Istio.

### ❓ Kafka/RabbitMQ usage? *(HCL Technologies)*

* **Facts**: Kafka = distributed log, Rabbit = queue-based broker.
* **Flow**: Producer → Broker → Consumer.
* **Failures**: Message loss, no retries, dead-letter queue misuse.
* **Fixes**: Use idempotent consumers + back-off policies.

---

## 📂 Database Patterns

### ❓ Data consistency across microservices? *(Infosys, Capgemini)*

* **Facts**: Use eventual consistency.
* **Flow**: Local DBs → Event publishing → Sync.
* **Failures**: Cross-service DB calls, strong consistency.
* **Fixes**: Use Saga/Event Sourcing patterns.

### ❓ Explain Saga Pattern with example? *(TCS)*

* **Facts**: Sequence of local transactions.
* **Flow**: Service A does work → triggers B → failure triggers compensating txn.
* **Failures**: Incomplete rollback, orchestration mix-up.
* **Fixes**: Use choreography (event-based) or orchestration (central saga manager).

### ❓ What is CQRS? *(L\&T Infotech)*

* **Facts**: Command Query Responsibility Segregation.
* **Flow**: Write = Command model → DB; Read = Query model → denormalized DB.
* **Failures**: Complexity, sync lag.
* **Fixes**: Use when read/write load is high or projections needed.

### ❓ Event Sourcing? *(Cognizant)*

* **Facts**: Persist changes as a sequence of events.
* **Flow**: Store events → Replay to rebuild state.
* **Failures**: Complex rehydration, large event log.
* **Fixes**: Use snapshots + event versioning.

---

## 📂 Observability Patterns

### ❓ Centralized logging? *(Wipro)*

* **Facts**: Logs from all services into one place (ELK, EFK).
* **Flow**: App logs → Fluentd/Logstash → Elasticsearch → Kibana.
* **Failures**: Missing correlation IDs.
* **Fixes**: Add trace/request IDs in logs.

### ❓ Distributed tracing? *(Infosys, Zensar)*

* **Facts**: Trace a request across services (Zipkin, Jaeger).
* **Flow**: Inject trace IDs → Propagate → Visualize.
* **Failures**: Missing instrumentation.
* **Fixes**: Use OpenTelemetry/Brave libraries.

### ❓ Monitoring production? *(IBM)*

* **Facts**: Prometheus + Grafana, CloudWatch, Datadog.
* **Flow**: Metrics → Exporter → Dashboard/Alert.
* **Failures**: Metric overload or under-monitoring.
* **Fixes**: Use RED (Rate, Error, Duration) or USE method.

### ❓ Health checks? *(TCS)*

* **Facts**: Liveness vs Readiness probes.
* **Flow**: /actuator/health or custom.
* **Failures**: One check = multiple responsibilities.
* **Fixes**: Separate checks for DB, cache, dependencies.

---

## 📂 Deployment Patterns

### ❓ Blue-Green/Canary Deployments? *(Tech Mahindra)*

* **Facts**: Two environments (Blue-Green) or partial rollout (Canary).
* **Flow**: Route % traffic → test → full rollout.
* **Failures**: Routing failure, insufficient testing.
* **Fixes**: Automate rollback on alerts, use feature toggles.

### ❓ Zero Downtime? *(Capgemini)*

* **Facts**: No user impact during deployment.
* **Flow**: Health-checks + Load Balancer + Rolling updates.
* **Failures**: Sticky sessions, schema changes.
* **Fixes**: Backward compatible deployments, DB versioning.

### ❓ Serverless pros/cons? *(Accenture)*

* **Facts**: Function-as-a-Service (e.g., AWS Lambda).
* **Flow**: Event → Function → Result.
* **Failures**: Cold start, limited runtime.
* **Fixes**: Use for infrequent or spiky workloads.

### ❓ Docker/K8s deployment? *(HCL Technologies)*

* **Facts**: Docker = Container runtime; K8s = Orchestration.
* **Flow**: Dockerfile → Image → Pod → Service.
* **Failures**: Image bloat, lack of auto-recovery.
* **Fixes**: Use multistage builds, liveness probes, HPA.

---

## 📂 Cross-Cutting Concern Patterns

### ❓ Config management? *(TCS)*

* **Facts**: Centralized config (Spring Cloud Config, Consul).
* **Flow**: App → Config Server → Refresh Scope.
* **Failures**: Hardcoded values.
* **Fixes**: Externalize all configs + version control.

### ❓ Circuit Breaker? *(Infosys)*

* **Facts**: Prevents cascading failures.
* **Flow**: Fail fast → Wait → Retry.
* **Failures**: No fallback logic.
* **Fixes**: Use Resilience4j/Hystrix.

### ❓ Secure service communication? *(Cognizant)*

* **Facts**: HTTPS, mutual TLS, API keys.
* **Flow**: mTLS handshake → token verification.
* **Failures**: Plain-text communication.
* **Fixes**: Use OAuth2 + TLS by default.

### ❓ Rate Limiting? *(Capgemini)*

* **Facts**: Prevent abuse.
* **Flow**: Token bucket or Leaky bucket algorithms.
* **Failures**: Poor thresholding.
* **Fixes**: Use API gateway rate limiters.

### ❓ Bulkhead and Retry? *(Wipro)*

* **Facts**: Bulkhead = isolate failures; Retry = transient error recovery.
* **Flow**: Thread pools per service + Retry policies.
* **Failures**: Retry storms, shared resources.
* **Fixes**: Set timeouts + backoff + circuit breaker.

---

## 📂 Infrastructure / Operational Patterns

### ❓ Service Registry role? *(IBM)*

* **Facts**: Auto registration + discovery.
* **Flow**: Service registers → Clients discover.
* **Failures**: Manual configs → tight coupling.
* **Fixes**: Use Spring Cloud Eureka, Consul, or Istio.

### ❓ Sidecar and Ambassador? *(Infosys)*

* **Facts**: Sidecar = helper container; Ambassador = proxy container.
* **Flow**: Service + Sidecar → Add logging, mesh, etc.
* **Failures**: Wrong sidecar injection.
* **Fixes**: Use Helm or Istio sidecar injection.

### ❓ Load balancing? *(Tech Mahindra)*

* **Facts**: Distribute traffic evenly.
* **Flow**: Client → Load Balancer (Round Robin/LeastConn) → Services.
* **Failures**: Uneven load or single LB.
* **Fixes**: Use L4/L7 load balancers with sticky sessions.

### ❓ Scaling services dynamically? *(Cognizant, HCL)*

* **Facts**: Horizontal/Vertical Scaling.
* **Flow**: Metric → Auto-scaler → Scale out/in.
* **Failures**: Latency in scale, no metric threshold.
* **Fixes**: HPA in Kubernetes, resource limits, Prometheus alerts.
