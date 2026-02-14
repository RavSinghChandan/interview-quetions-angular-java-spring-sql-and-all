Excellent. From now on I’ll act as your **Senior Interview Architect Mentor** and we’ll build your **E-commerce System Design Blueprint** exactly like FAANG / Uber / Mastercard interview rounds.

We will proceed in **10 structured parts** as you requested.

You are now receiving:

> ✅ **PART 1 — HLD FOUNDATION + THINKING TEMPLATE**

This is your permanent reusable thinking framework.
You will use this for ANY system design question.

---

# PART 1 — HLD THINKING FRAMEWORK + E-COMMERCE START

---

```md
# UNIVERSAL HLD THOUGHT PROCESS TEMPLATE (MEMORIZE THIS)

Whenever interviewer says:
"Design X system"

Follow this structure:

---

STEP 1 — Clarify Requirements
STEP 2 — Define Scale
STEP 3 — Identify Core Entities
STEP 4 — Define APIs
STEP 5 — High Level Architecture
STEP 6 — Data Design
STEP 7 — Scalability Strategy
STEP 8 — Reliability Strategy
STEP 9 — Bottleneck Analysis
STEP 10 — Tradeoffs + Extensions

---

If you follow this order,
you will sound structured and senior instantly.
```

---

# APPLYING TEMPLATE → DESIGN E-COMMERCE SYSTEM

We now execute step-by-step like real interview.

---

## STEP 1 — REQUIREMENT CLARIFICATION

Say this in interview:

> Let me clarify functional and non-functional requirements first.

### Functional Requirements

* User signup/login
* Browse products
* Search products
* Add to cart
* Place order
* Payment
* Track order

### Non Functional Requirements

* Highly available
* Low latency browsing
* Scalable for peak sales
* Secure payments
* Fault tolerant

---

## STEP 2 — SCALE ASSUMPTIONS

Always define scale. Interviewers expect this.

Example:

* 10 million users
* 1 million daily active
* 100k concurrent users
* 10k orders/min peak sale

---

Why this matters:

Scale determines architecture.

Without scale = design is guesswork.

---

## STEP 3 — CORE ENTITIES

Senior engineers model domain early.

Entities:

* User
* Product
* Cart
* Order
* Payment
* Inventory
* Shipment

---

Strong signal line:

> These look like our core domain objects. We can refine later.

---

## STEP 4 — CORE APIS

Define interaction surface.

Examples:

POST /users
GET /products
POST /cart/add
POST /order
POST /payment
GET /order/status

---

Why interviewers like this:

APIs define system boundaries.

---

## STEP 5 — HIGH LEVEL ARCHITECTURE (BASELINE)

Start simple.

Never jump to microservices immediately.

Baseline architecture:

Client → Load Balancer → Application Server → Database

Explain:

* Clients send request
* LB distributes
* App servers process
* DB stores

---

Strong candidate line:

> I’ll start with a simple monolith and scale it gradually.

This shows maturity.

---

## STEP 6 — SCALE ARCHITECTURE (NOW DISTRIBUTE)

When scale increases → break into services.

Microservices:

* User Service
* Product Service
* Cart Service
* Order Service
* Payment Service
* Inventory Service

---

Why split?

Because each has:

* different scaling pattern
* different traffic
* different storage needs

---

## STEP 7 — DATABASE DESIGN CHOICES

Different services need different DBs.

| Service   | DB    |
| --------- | ----- |
| User      | SQL   |
| Product   | NoSQL |
| Cart      | Redis |
| Order     | SQL   |
| Inventory | SQL   |

---

Senior signal:

> Different workloads need different storage engines.

---

## STEP 8 — CACHE STRATEGY

Hot data must be cached.

Cache:

* product details
* search results
* homepage data

Use:

Redis / CDN

---

## STEP 9 — ASYNC PROCESSING

Never do everything synchronously.

Async tasks:

* email confirmation
* payment verification
* order processing
* shipment updates

Use:

Message Queue (Kafka / RabbitMQ)

---

## STEP 10 — HIGH AVAILABILITY

To survive failures:

* multiple servers
* replication
* failover
* load balancing

---

# WHAT YOU JUST BUILT

You completed:

✔ requirement thinking
✔ architecture thinking
✔ scale thinking
✔ distributed thinking

This already places you above 70% candidates.

---

# INTERVIEW POWER PHRASE

Use this naturally:

> We’ll start simple and evolve architecture as scale demands.

Interviewers love this.

---

# MENTOR NOTE FOR YOU

Do NOT memorize architecture diagrams.

Memorize thinking order.

Senior engineers are evaluated on thinking, not diagrams.

---

# YOUR HOMEWORK (IMPORTANT)

Before next part, practice speaking aloud:

Design Twitter
Design Parking System
Design URL shortener

Follow same 10 steps.

---

---

✅ **Next Part:**
**PART 2 — Deep Architecture Design (Traffic, Load, Scaling Decisions)**
We’ll add:

* read/write scaling
* sharding
* replication
* API gateway
* service discovery

Reply:

> continue

when ready.
Excellent. Continuing your **Senior System Design Mentorship**.

You’re now getting:

> ✅ **PART 2 — ADVANCED HLD ARCHITECTURE (SCALING + TRAFFIC + REAL DISTRIBUTED DESIGN)**

This is where candidates get filtered for **Uber / Mastercard / FAANG-level roles**.

We now take your base E-commerce design and evolve it into **production-grade architecture**.

---

# PART 2 — SCALABLE ARCHITECTURE DESIGN

---

```md
# INTERVIEW STRUCTURE FOR THIS PHASE

When interviewer asks:
"How will this scale?"

You answer in this order:

1. Traffic handling
2. Read scaling
3. Write scaling
4. Caching
5. Async processing
6. DB scaling
7. Failure handling
```

---

# 1️⃣ TRAFFIC ENTRY LAYER

Requests must first enter system safely.

Architecture:

Client → CDN → Load Balancer → API Gateway → Services

---

### Why CDN first?

Static assets must not hit backend.

CDN serves:

* images
* CSS
* JS
* product images

Reduces backend load by **70%+**

---

### Why Load Balancer?

Distributes traffic across servers.

Prevents overload.

Algorithms:

* round robin
* least connections
* weighted routing

---

### Why API Gateway?

Single entry point.

Handles:

* authentication
* routing
* throttling
* logging

Senior signal:

> Gateway centralizes cross-cutting concerns.

---

# 2️⃣ SERVICE LAYER DESIGN

Break monolith into domain services:

User Service
Product Service
Cart Service
Order Service
Payment Service
Inventory Service

Each service:

* owns its database
* scales independently
* deploys independently

---

# 3️⃣ READ SCALING

Reads dominate e-commerce.

Strategies:

Read replicas
Caching
Search index

Example:

Product queries → read replicas

---

# 4️⃣ WRITE SCALING

Writes must remain consistent.

Critical writes:

orders
payments
inventory

Strategies:

* write master DB
* replicas sync async
* partition heavy tables

---

# 5️⃣ DATABASE SCALING

At scale single DB fails.

Solutions:

Vertical scaling → limited
Horizontal scaling → required

Methods:

Sharding by:

* user id
* region
* order id

---

# 6️⃣ CACHING STRATEGY (INTERVIEW FAVORITE)

Multi-layer cache:

Browser cache
CDN cache
Application cache
DB cache

Cache hot data only.

Example hot data:

* trending products
* product details
* search results

---

# 7️⃣ SEARCH SYSTEM

Search is special workload.

Do NOT use DB search.

Use:

Elasticsearch / Solr

Why?

Full-text search
Ranking
Filters

---

# 8️⃣ ASYNCHRONOUS ARCHITECTURE

Never block user request.

Use queues for:

payment verification
email sending
inventory update
recommendation updates

Queue examples:

Kafka
RabbitMQ
SQS

---

# 9️⃣ FAILURE HANDLING

Production systems must survive failures.

Strategies:

Retries
Timeouts
Circuit breakers
Fallback responses

Example:

Payment service down → retry queue

---

# 🔟 DATA CONSISTENCY MODEL

E-commerce requires mixed consistency.

Strong consistency:

payments
orders

Eventual consistency:

recommendations
analytics

Senior signal line:

> Different flows need different consistency guarantees.

---

# 1️⃣1️⃣ SERVICE DISCOVERY

In distributed system services must locate each other.

Use:

Consul
Eureka
Kubernetes DNS

---

# 1️⃣2️⃣ DEPLOYMENT STRATEGY

Production deployments must be safe.

Use:

Rolling deployment
Blue/green deployment
Canary release

---

# 1️⃣3️⃣ OBSERVABILITY

Must monitor system.

Include:

Metrics
Logs
Tracing

Tools:

Prometheus
Grafana
ELK stack

---

# 1️⃣4️⃣ BOTTLENECK ANALYSIS (INTERVIEW GOLD)

Always mention bottlenecks.

Possible bottlenecks:

Database
Network
Cache miss storms
Payment gateway latency

Mentioning this signals senior thinking.

---

# 1️⃣5️⃣ FINAL ARCHITECTURE SUMMARY

Your architecture now includes:

CDN
LB
Gateway
Microservices
Queues
Caches
Sharded DB
Replicas
Search Engine

This is production-grade system.

---

# 🏆 SENIOR LEVEL LINE

Say this naturally:

> This architecture scales horizontally and isolates failures across services.

That line alone signals strong candidate.

---

# MENTOR NOTE

Most candidates stop at microservices.

Senior candidates discuss:

* failure
* scaling
* tradeoffs

---

# YOUR PRACTICE TASK

Practice explaining:

Why async > sync for order processing?

Record yourself.

Clarity matters.

---

---

✅ **Next Part:**
**PART 3 — Deep Data Design + Tradeoffs + Scaling Math**

We’ll cover:

* schema design
* indexing
* partitioning
* query optimization
* storage strategy
* cost tradeoffs

Reply:

> 3
Excellent. You’re progressing exactly like a **serious senior-level candidate** should.

Now we move to the layer where most engineers fail interviews:

> **Data Design + Storage Tradeoffs + Scaling Math**

This is what separates mid-level from senior.

---

# PART 3 — DATA DESIGN + STORAGE ARCHITECTURE

---

```md
GOAL OF THIS ROUND (WHAT INTERVIEWER IS TESTING)

Can this candidate design data systems that:

- scale
- stay fast
- remain consistent
- handle billions of records
- survive traffic spikes
```

---

# 1️⃣ DATABASE SELECTION STRATEGY (FIRST DECISION)

Never choose DB randomly.

Senior engineers always say:

> Storage choice depends on access pattern.

---

### Storage Mapping for E-commerce

| Service  | DB Type       | Why                      |
| -------- | ------------- | ------------------------ |
| Users    | SQL           | transactions + relations |
| Products | NoSQL         | flexible schema          |
| Orders   | SQL           | transactional            |
| Cart     | Redis         | fast access              |
| Search   | Elasticsearch | full text                |
| Logs     | Column store  | analytics                |

---

Interview signal:

> Different workloads need different storage engines.

---

# 2️⃣ SCHEMA DESIGN (ORDER SERVICE)

Example order table:

```
Orders
-------
order_id (PK)
user_id
status
total_price
created_at
```

Items table:

```
OrderItems
-----------
id
order_id
product_id
qty
price
```

---

### Why split tables?

Normalization improves:

* query speed
* storage efficiency
* update performance

---

# 3️⃣ INDEX DESIGN (CRITICAL TOPIC)

Indexes speed up reads.

Common indexes:

Orders:

* user_id
* created_at
* status

Products:

* category
* price
* rating

---

Senior signal line:

> We index based on query patterns, not columns randomly.

---

# 4️⃣ HOT PARTITION PROBLEM

If sharding by user_id:

Celebrity user → hotspot shard.

Solution:

Shard by hash(user_id)

Distributes evenly.

---

# 5️⃣ SHARDING STRATEGIES

Common shard keys:

User based sharding
Order based sharding
Region based sharding

Best choice depends on query patterns.

---

Example:

If queries are:

orders by user

→ shard by user_id

---

# 6️⃣ READ VS WRITE TRADEOFF

If system is read-heavy:

Add replicas.

If write-heavy:

Partition writes.

E-commerce is:

80–90% reads.

So optimize reads first.

---

# 7️⃣ PAGINATION STRATEGY

Never return large dataset.

Bad:

SELECT * FROM products

Good:

SELECT * FROM products LIMIT 20 OFFSET 0

Better:

Cursor-based pagination.

---

# 8️⃣ SEARCH OPTIMIZATION

Search should not hit main DB.

Instead:

DB → sync → Search Index

Pipeline:

Product added → event → search index update

---

# 9️⃣ DATA CONSISTENCY DECISION

Not all data must be strongly consistent.

Use:

Strong consistency:

* payments
* orders

Eventual consistency:

* search
* recommendations

---

# 🔟 CACHE + DATABASE INTERACTION

Cache patterns:

Cache Aside (most common)

Flow:

Check cache → miss → DB → update cache

---

# 1️⃣1️⃣ CACHE INVALIDATION (IMPORTANT)

When product updates:

DB updated → cache must refresh.

Solutions:

TTL expiration
Event invalidation

---

# 1️⃣2️⃣ STORAGE COST THINKING

Senior engineers mention cost.

Storage types:

Hot storage → SSD → expensive
Cold storage → S3 → cheap

Strategy:

Recent orders → hot
Old orders → archive

---

# 1️⃣3️⃣ QUERY OPTIMIZATION SIGNAL

Say this:

> We must analyze slow queries and optimize indexes based on production metrics.

This shows real-world experience.

---

# 1️⃣4️⃣ SCALING MATH (INTERVIEW FAVORITE)

Example:

10M users
100k concurrent users
Each request = 2 KB

Bandwidth:

100k × 2KB = 200MB per second

Always estimate numbers.

Interviewers LOVE candidates who calculate.

---

# 1️⃣5️⃣ DATA BACKUP + RECOVERY

Production DB must support:

Snapshots
Point-in-time recovery
Replication

Never skip this.

---

# 1️⃣6️⃣ FINAL DATA ARCHITECTURE SUMMARY

Your system now supports:

* sharding
* replication
* indexing
* caching
* search index
* backups

This is production-grade data architecture.

---

# 🏆 SENIOR SIGNAL SENTENCE

Say:

> We choose storage, indexing, and sharding based on access patterns and scale projections.

That sentence screams senior engineer.

---

# MENTOR INSIGHT

Most candidates talk architecture.

Top candidates talk:

data design.

Because real bottlenecks happen in database layer.

---

# PRACTICE TASK

Answer aloud:

Why not store products in SQL?

If you can explain clearly → you’re improving fast.

---

---

✅ **Next Part:**
**PART 4 — Reliability + Failures + Production Hardening**

We’ll cover:

* retries
* circuit breakers
* rate limiting
* fallback
* resilience design
* disaster recovery

Reply:

> 4
Excellent. You’re now entering the phase that **separates senior engineers from architects**.

Most candidates can design systems.
Few can design systems that **survive failure**.

This round is where interviewers check:

> Can this person run production systems responsibly?

---

# PART 4 — RELIABILITY + FAILURE + RESILIENCE DESIGN

---

```md
INTERVIEWER INTENT

They now test:

Does candidate think about:

- outages
- retries
- failure isolation
- recovery
- stability under load
```

---

# 1️⃣ FAILURE IS NORMAL (MENTAL MODEL)

Always say:

> In distributed systems, failures are expected, not exceptions.

This is a senior-level mindset.

---

# 2️⃣ TYPES OF FAILURES IN E-COMMERCE

You must anticipate failures.

Common ones:

Payment service down
Database slow
Cache crash
Network latency spike
Third-party API timeout

---

# 3️⃣ TIMEOUT STRATEGY (MANDATORY)

Never wait forever for response.

Each service call must have timeout.

Example:

Payment API timeout = 2 sec

Why?

Without timeout → threads block → system crash.

---

# 4️⃣ RETRY STRATEGY

Some failures are temporary.

Retry logic helps recover.

But never retry blindly.

Use:

Exponential backoff.

Example delays:

100ms → 300ms → 900ms → stop

---

# 5️⃣ CIRCUIT BREAKER PATTERN

If service keeps failing:

Stop calling it temporarily.

Why?

Prevents cascading failure.

Flow:

Failure rate high → circuit opens → skip calls → fallback response

---

# 6️⃣ FALLBACK RESPONSES

System should degrade gracefully.

Examples:

Payment down → show retry message
Recommendation down → hide section
Search slow → show cached results

---

# 7️⃣ BULKHEAD ISOLATION

Different components must not share resources.

Example:

Order service thread pool separate from payment thread pool.

So payment failure doesn’t kill order system.

---

# 8️⃣ RATE LIMITING

Protect system from overload.

Limit requests per user.

Algorithms:

Token bucket
Sliding window

Use cases:

Login attempts
Checkout requests
Search queries

---

# 9️⃣ IDEMPOTENCY (CRITICAL FOR PAYMENTS)

Duplicate requests must not duplicate actions.

Example:

User clicks Pay twice.

Solution:

Idempotency key.

Server ensures only one payment processed.

---

# 🔟 DATABASE FAILURE STRATEGY

Primary DB can fail.

Solution:

Replica promotion.

Primary → replica becomes primary.

Handled automatically using failover.

---

# 1️⃣1️⃣ CACHE FAILURE STRATEGY

If cache crashes:

Fallback → DB.

System slows but survives.

Never design system dependent solely on cache.

---

# 1️⃣2️⃣ MESSAGE QUEUE FAILURE

Queue may go down.

Solutions:

Retry queue
Dead letter queue

Failed messages stored for later processing.

---

# 1️⃣3️⃣ GRACEFUL DEGRADATION

System should degrade instead of crash.

Priority order:

Checkout > Orders > Login > Recommendations

Low priority features can fail first.

---

# 1️⃣4️⃣ DISASTER RECOVERY

Worst case: data center fails.

Solution:

Multi-region deployment.

Primary region + backup region.

Data replicated asynchronously.

---

# 1️⃣5️⃣ HEALTH CHECKS

Load balancer must know if service alive.

Services expose:

/health endpoint

If unhealthy → traffic stopped.

---

# 1️⃣6️⃣ DEPLOYMENT SAFETY

Never deploy directly to all servers.

Use:

Canary deployment
Blue-green deployment

Prevents breaking system.

---

# 1️⃣7️⃣ MONITORING + ALERTING

Production must be monitored.

Metrics:

Latency
Error rate
Traffic
CPU

Alerts when thresholds exceeded.

---

# 1️⃣8️⃣ BOTTLENECK PROTECTION

Must anticipate spikes.

Example:

Flash sale traffic spike.

Solutions:

Queue orders
Throttle requests
Preload cache

---

# 1️⃣9️⃣ REAL INTERVIEW DIFFERENTIATOR

Strong candidate:

Designs system.

Elite candidate:

Designs failure recovery.

---

# 🏆 SENIOR SENTENCE (USE THIS)

> The system should fail gracefully and isolate failures so one component doesn’t cascade into system-wide outage.

That sentence alone signals production experience.

---

# MENTOR NOTE FOR YOU

Most candidates forget:

* idempotency
* rate limiting
* fallback

Mentioning these → instant credibility boost.

---

# QUICK REVISION FORMULA

If asked reliability:

Answer in order:

Timeout → Retry → Circuit breaker → Fallback → Monitoring

Memorize this chain.

---

# PRACTICE QUESTION

Answer aloud:

What happens if payment gateway times out during checkout?

If you can answer smoothly → you're improving.

---

---

✅ **Next Part:**
**PART 5 — TRADEOFFS + BOTTLENECKS + INTERVIEW CLOSING**

This final HLD part teaches you:

* how to impress interviewer
* how to close answer like senior engineer
* what tradeoffs to mention
* how to handle follow-up questions

Reply:

> 5
Excellent. You’ve now reached the **final and most powerful HLD phase** — the part that actually determines whether you get selected or rejected in a senior system design round.

This section teaches you how **real interviewers evaluate you**, how to present tradeoffs, and how to close your design like a senior engineer.

---

# PART 5 — TRADEOFFS, BOTTLENECK THINKING & INTERVIEW CLOSING MASTERY

---

```md
THIS ROUND TESTS

Do you think like someone trusted with production systems?

Not:
Can you design?

But:
Can you reason?
Can you prioritize?
Can you choose wisely?
```

---

# 1️⃣ TRADEOFF THINKING (MOST IMPORTANT SKILL)

Senior engineers always mention tradeoffs.

There is no perfect design.

Every decision sacrifices something.

---

### Example Tradeoffs in E-commerce System

---

**SQL vs NoSQL**

SQL:

* strong consistency
  − harder to scale

NoSQL:

* scalable
  − eventual consistency

---

**Caching**

* faster reads
  − stale data risk

---

**Microservices**

* independent scaling
  − operational complexity

---

**Replication**

* availability
  − replication lag

---

Strong candidate line:

> This improves performance but increases operational complexity.

Interviewers LOVE this.

---

# 2️⃣ BOTTLENECK IDENTIFICATION (ELITE SIGNAL)

Always ask yourself:

> What breaks first?

Typical bottlenecks:

Database connections
Payment gateway latency
Cache miss storm
Search indexing lag

Mentioning bottlenecks shows real experience.

---

# 3️⃣ PRIORITIZATION THINKING

Not all problems matter equally.

Senior engineers prioritize.

---

Example priorities in e-commerce:

1. Checkout reliability
2. Payment correctness
3. Order consistency
4. Search speed
5. Recommendations

Why?

Because revenue depends on checkout.

---

Strong signal line:

> We optimize revenue-critical flows first.

---

# 4️⃣ COST AWARENESS (RARE BUT POWERFUL)

Senior engineers mention cost.

Example:

Redis cache improves speed but increases infra cost.

CDN reduces backend load but adds cost.

Sharding increases complexity and cost.

---

Mention cost once → interviewer sees real-world maturity.

---

# 5️⃣ SIMPLICITY PRINCIPLE

Overengineering is a red flag.

Best design is:

> simplest architecture that satisfies requirements.

Say this in interview.

It signals senior judgment.

---

# 6️⃣ SCALING STRATEGY THINKING

Good engineers design for scale.

Great engineers design for gradual scale.

Example evolution:

Monolith → Modular monolith → Microservices → Distributed

Strong signal:

> We’ll evolve architecture as scale grows instead of overengineering early.

---

# 7️⃣ SECURITY AWARENESS

Mention briefly:

* authentication
* authorization
* encryption
* validation

Not deep — just awareness.

---

# 8️⃣ EDGE CASE THINKING

Interviewers check if you think about rare cases.

Examples:

Duplicate payments
Cart race conditions
Inventory oversell
Partial failures

Mentioning edge cases is huge.

---

# 9️⃣ FOLLOW-UP QUESTION HANDLING

Interviewers often ask:

"What would you improve if scale grows 10×?"

Best response pattern:

Scaling layer → bottleneck → solution

Example:

If traffic increases 10×:

Cache → add layers
DB → shard
Search → scale cluster

---

# 🔟 HOW TO CLOSE DESIGN LIKE A SENIOR ENGINEER

Never say:

That’s it.

Always summarize system.

---

Perfect closing structure:

Architecture summary
Scaling approach
Reliability approach
Future extensibility

---

### PERFECT CLOSING SCRIPT (MEMORIZE)

> This design is horizontally scalable, failure-resilient, and modular. It isolates services, supports growth through sharding and caching, and can evolve as traffic increases.

That sentence alone signals senior engineer.

---

# 1️⃣1️⃣ INTERVIEWER PSYCHOLOGY SECRET

Interviewers look for signals:

Structured thinking
Calm explanation
Tradeoffs awareness
Failure awareness
Scale awareness

Not perfection.

---

# 1️⃣2️⃣ REAL DIFFERENCE BETWEEN CANDIDATES

Average candidate:
Designs system.

Strong candidate:
Designs + scales.

Elite candidate:
Designs + scales + anticipates failures + explains tradeoffs.

---

# 🏆 MASTER PHRASE SET

Use these naturally:

This could become a bottleneck.
We should cache this.
We should isolate this service.
This should be asynchronous.
This improves speed but adds complexity.
We can evolve this later.

These phrases dramatically improve interviewer perception.

---

# FINAL HLD MENTAL MODEL

Whenever designing system think:

Scale
Failure
Cost
Tradeoff
Future

If you mention these five → you sound senior.

---

# PRACTICE TASK

Explain aloud:

Why not design microservices from day one?

If you can explain clearly → you are thinking like architect.

---

# 🎯 HLD MASTER CHECKLIST (FINAL REVISION)

Before finishing any system design:

✔ scalable
✔ fault tolerant
✔ observable
✔ secure
✔ cost aware
✔ extensible

---

---

## ✅ YOU HAVE NOW COMPLETED FULL HLD MASTER TRAINING

You now possess a **complete senior-level HLD mental framework**.

This is equivalent to:

* FAANG prep
* Uber design interviews
* Stripe backend interviews
* Mastercard architecture rounds

---

# NEXT PHASE

Now we begin:

> **LLD MASTER SERIES (5 Parts)**

This will train you for:

* object modeling
* class design
* patterns
* concurrency
* clean architecture

---

Reply:

> start LLD

and we begin Part 1 of LLD mastery.
