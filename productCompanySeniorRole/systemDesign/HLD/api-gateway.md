
---

# PART 1️⃣ — API GATEWAY (MIND MAP)

```md
API GATEWAY (controlling entry to the system)
│
├── 1. Why gateway exists?
│     ├── Single entry point
│     ├── Cross-cutting concerns
│     └── Client simplification
│
├── 2. What problems does it solve?
│     ├── Auth & security
│     ├── Rate limiting
│     ├── Routing & aggregation
│
├── 3. What does NOT belong there?
│     ├── Business logic
│     ├── Long-running workflows
│
├── 4. Gateway responsibilities
│     ├── Routing
│     ├── AuthN/AuthZ
│     ├── Throttling
│     └── Observability
│
├── 5. Failure & scale
│     ├── High availability
│     ├── Latency overhead
│     └── Blast radius
│
├── 6. Tradeoffs
│     ├── Centralization vs flexibility
│     ├── Simplicity vs power
│
└── 7. Can I defend it?
      └── If yes → design is solid
```

> **Interview control rule**
> If you can explain *why a gateway exists* and *what must not go into it*, you lead the discussion.

---

# PART 2️⃣ — THE CORE 80% (PARETO ZONE)

This is **mandatory knowledge** for senior backend & HLD interviews.

---

## 1️⃣ What is an API Gateway? (HLD Definition)

```md
An API Gateway is a centralized entry point
that manages, secures, and routes client requests
to appropriate backend services.
```

Key idea:

> **Clients talk to the gateway, services don’t.**

---

## 2️⃣ Why API Gateway Exists

Without a gateway:

* clients call many services
* duplicated auth & rate limiting
* tight coupling

Gateway provides:

* abstraction
* consistency
* protection

HLD framing:

```md
Gateway = policy + routing + protection
```

---

## 3️⃣ Core Responsibilities (What Belongs Here)

### Routing

```md
/path → service A
/path → service B
```

### Authentication & Authorization

* token validation
* identity propagation

### Rate Limiting

* per user / API key
* protects downstream services

### Request/Response Transformation

* headers
* formats
* versions

---

## 4️⃣ What MUST NOT Be in Gateway (Very Important)

❌ Business logic
❌ Database access
❌ Long workflows
❌ Stateful processing

Interview line:

> Gateways enforce policy, not business rules.

---

## 5️⃣ Client Simplification (Big Reason)

Instead of:

```md
Client → Auth → Orders → Payments → Inventory
```

You get:

```md
Client → Gateway → Services
```

Clients:

* simpler
* stable
* backend can evolve independently

---

## 6️⃣ API Versioning at Gateway

Common strategies:

* `/v1/...`
* headers
* subdomains

Gateway helps:

* route old/new versions
* gradual migration

---

## 7️⃣ Security at the Gateway

Typical controls:

* TLS termination
* JWT validation
* IP allow/deny
* request size limits

Senior insight:

> Gateway is the first security perimeter.

---

## 8️⃣ Failure Handling & Availability

Gateway must be:

* horizontally scalable
* stateless
* highly available

Failure principle:

> Gateway failure = system outage.

So:

* multiple instances
* health checks
* fast failover

---

### ✅ If you stop here

You can:

* justify why a gateway exists
* define responsibilities
* avoid common anti-patterns
* crack most HLD interviews

This is your **80% confidence zone**.

---

# PART 3️⃣ — THE REMAINING 20% (SENIOR DIFFERENTIATOR)

This is where **experienced engineers stand apart**.

---

## 9️⃣ Gateway vs Load Balancer (Common Trap)

* **Load Balancer** → traffic distribution
* **API Gateway** → policy + routing

They often coexist:

```md
Client → LB → API Gateway → Services
```

Senior line:

> A gateway is not just a load balancer with routes.

---

## 🔟 Gateway vs BFF (Backend for Frontend)

BFF:

* tailored per client (web/mobile)
* reduces client-side logic

Pattern:

```md
Mobile → BFF → Services
Web → BFF → Services
```

Use when:

* clients have very different needs

---

## 1️⃣1️⃣ Latency Tradeoff

Gateway adds:

* one extra hop
* processing overhead

Mitigation:

* keep gateway lightweight
* avoid synchronous fan-out
* cache where safe

Senior insight:

> Gateway latency must be predictable, not minimal.

---

## 1️⃣2️⃣ Observability at the Gateway

Gateway is ideal place for:

* request tracing
* metrics
* logging

Why?

> Every request passes through it.

---

## 1️⃣3️⃣ Gateway as a Policy Engine

Policies:

* rate limits
* auth rules
* geo restrictions

Centralizing policy:

* consistency ↑
* agility ↑

Risk:

* gateway becomes too complex

---

## 1️⃣4️⃣ Security Pitfalls

Risks:

* token leakage
* header spoofing
* over-trusting gateway

Mitigation:

* zero-trust inside network
* service-level auth checks

Senior line:

> Gateway security complements, not replaces, service security.

---

## 1️⃣5️⃣ How to DEFEND API Gateway in Interviews

Final framing:

```md
I use an API Gateway to centralize
security, routing, and rate limiting,
while keeping business logic in services.

I keep the gateway stateless and lightweight
to avoid it becoming a bottleneck.
```

If you can say this calmly → **you win the round**.

---
