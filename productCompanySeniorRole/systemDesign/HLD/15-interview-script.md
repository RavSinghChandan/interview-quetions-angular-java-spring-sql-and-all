
---

# 1️⃣ UNIVERSAL HLD OPENING SCRIPT (MEMORISE THIS)

Start every HLD like this 👇

> “Before jumping into design, I’d like to clarify the requirements.
> I’ll first confirm functional requirements, then non-functional ones like scale, availability, and consistency.
> After that, I’ll design a simple baseline and then evolve it for scale, reliability, and failure handling.”

This immediately signals:

* seniority
* structure
* control of discussion

---

# 2️⃣ CASE STUDY 1 — ORDER MANAGEMENT SYSTEM (SPOKEN)

## 🔹 Step 1: Requirements (spoken)

> “Functionally, the system should allow users to place orders, process payments, reserve inventory, and track order status.
> Non-functionally, the system should be highly available, scalable to millions of users, and must not allow double payments.
> Eventual consistency is acceptable, but correctness of money is critical.”

Pause. Let interviewer confirm.

---

## 🔹 Step 2: High-Level Architecture (spoken)

> “At a high level, I’ll separate concerns into independent services.
> The client talks to an API Gateway, which routes to an Order Service.
> Payment and Inventory are separate services, and communication between them is asynchronous using messaging to improve resilience.”

Say **why**:

> “I’m choosing async communication because payment and inventory are slow and failure-prone, and I don’t want order creation to block.”

---

## 🔹 Step 3: Service Ownership (spoken)

> “Each service owns its own database to avoid tight coupling.
> Order Service owns order state, Payment Service owns payments, and Inventory Service owns stock.”

Then this **gold line**:

> “There are no cross-service database transactions.”

This is a **huge senior signal**.

---

## 🔹 Step 4: Write Flow (spoken)

> “When a user places an order, Order Service creates an order in PENDING state and emits an OrderCreated event.
> Payment Service consumes the event, processes payment, and emits PaymentSuccess or PaymentFailure.
> Inventory Service reserves stock.
> Once both succeed, Order Service marks the order as CONFIRMED.”

Pause. Let them interrupt if needed.

---

## 🔹 Step 5: Distributed Transaction Handling (spoken)

> “I’m using the Saga pattern instead of two-phase commit.
> If payment fails, I compensate by cancelling the order.
> If inventory fails after payment, I trigger a refund.”

Then explicitly say:

> “This trades strict atomicity for availability and scalability, which is acceptable for this business.”

---

## 🔹 Step 6: Failure & Reliability (spoken)

> “All consumers are idempotent because messaging is at-least-once.
> I use retries with backoff, circuit breakers for payment providers, and a DLQ for poison messages.”

This line wins interviews:

> “Failures are expected; the system is designed to recover, not prevent them.”

---

## 🔹 Step 7: Scalability (spoken)

> “All services are stateless and scale horizontally.
> Reads like product and order status are cached.
> Databases use replication for reads and sharding if write volume grows.”

---

## 🔹 Step 8: Security & Observability (spoken)

> “Authentication and rate limiting happen at the gateway, but authorization is enforced again at service level.
> Each request carries a trace ID so I can trace an order end-to-end across services.”

---

## 🔹 Step 9: Tradeoff Statement (DO NOT SKIP)

> “The key tradeoff here is eventual consistency.
> In exchange, we get high availability, resilience, and horizontal scalability.”

Stop. Don’t overtalk.

---

# 3️⃣ CASE STUDY 2 — NOTIFICATION SYSTEM (SPOKEN)

## 🔹 Step 1: Requirements (spoken)

> “The system should send notifications via email, SMS, and push.
> It must handle very high throughput.
> Delivery can be eventual, but messages must not be lost.”

---

## 🔹 Step 2: Architecture (spoken)

> “I’ll use an asynchronous, event-driven design.
> Producers publish notification events to a message broker.
> Dedicated workers for each channel consume and send notifications.”

Why async:

> “External providers are slow and unreliable, so synchronous calls would hurt availability.”

---

## 🔹 Step 3: Message Design (spoken)

> “Messages are immutable, versioned, and self-contained.
> They include event type, user ID, channels, and correlation ID.”

This line matters:

> “This allows independent evolution of producers and consumers.”

---

## 🔹 Step 4: Processing & Retries (spoken)

> “Workers process messages with at-least-once delivery.
> All handlers are idempotent.
> Transient failures are retried with backoff, permanent failures go to a DLQ.”

---

## 🔹 Step 5: Scaling (spoken)

> “Scaling is done by increasing partitions and consumer instances.
> Ordering is guaranteed per user, not globally, to maximize throughput.”

---

## 🔹 Step 6: Rate Limiting & Protection (spoken)

> “I apply rate limits per user and per channel to protect providers and avoid abuse.”

---

## 🔹 Step 7: Observability (spoken)

> “I track sent, failed, retried notifications, DLQ size, and latency per channel.
> Alerts are based on failure rate, not raw volume.”

---

## 🔹 Step 8: Tradeoff (spoken)

> “We prioritize throughput and availability over strict ordering and immediacy.”

---

# 4️⃣ HOW TO HANDLE INTERRUPTIONS (VERY IMPORTANT)

If interviewer interrupts with:

> “What if X fails?”

Answer like this:

> “Good question. Let’s walk through that failure scenario.”

Never say:

* “I already covered that”
* “That’s out of scope”

---

If you don’t know something:

> “That’s a good point. Given the constraints, I’d start with X and evolve it if needed.”

This is **senior humility**, not weakness.

---

# 5️⃣ PERFECT CLOSING SCRIPT (ALWAYS END LIKE THIS)

End every HLD with:

> “To summarize, I started with a simple, correct design, then added scalability, resilience, and observability.
> The system favors availability and recoverability over strict consistency, which aligns with real-world production systems.”

Then stop talking.

---

