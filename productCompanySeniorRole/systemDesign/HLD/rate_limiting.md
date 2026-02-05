
---

# PART 1️⃣ — RATE LIMITING (MIND MAP)

This is how **senior engineers think** about rate limiting in HLD.

```md
RATE LIMITING (protecting systems under load)
│
├── 1. Why rate limiting exists?
│     ├── Prevent abuse
│     ├── Protect downstream systems
│     └── Ensure fairness
│
├── 2. What are we limiting?
│     ├── Requests
│     ├── Actions
│     └── Resources
│
├── 3. Where is it applied?
│     ├── Client-side
│     ├── API Gateway
│     └── Service-level
│
├── 4. How is it enforced?
│     ├── Fixed window
│     ├── Sliding window
│     ├── Token bucket
│     └── Leaky bucket
│
├── 5. What happens when limit exceeds?
│     ├── Reject
│     ├── Delay
│     └── Degrade
│
├── 6. What can go wrong?
│     ├── Burst traffic
│     ├── Clock skew
│     └── Distributed coordination
│
└── 7. Can I justify limits?
      └── If yes → design is sound
```

> **Interview control rule**
> If you can explain *why you limit*, *where you limit*, and *how you limit* → you control the round.

---

# PART 2️⃣ — THE CORE 80% (PARETO ZONE)

This is **mandatory knowledge** for senior backend interviews.

---

## 1️⃣ What is Rate Limiting? (HLD Definition)

```md
Rate limiting controls how frequently
a client or entity can perform an action
to protect system stability and fairness.
```

Key idea:

> Rate limiting protects **systems**, not users.

---

## 2️⃣ Why Rate Limiting Exists

Without rate limiting:

* one client can exhaust resources
* cascading failures occur
* SLAs break

Typical use cases:

* public APIs
* login attempts
* payment endpoints

HLD framing:

```md
Rate limiting = load control + abuse prevention
```

---

## 3️⃣ What Can Be Rate Limited?

* Requests per IP
* Requests per user
* Requests per API key
* Resource usage (CPU, DB queries)

Example:

```md
100 requests / minute / user
```

---

## 4️⃣ Where Rate Limiting Lives

### Client-Side

* fast feedback
* not trusted

### API Gateway (Most Common)

* centralized
* consistent enforcement

### Service-Level

* fine-grained
* higher complexity

Interview line:

> Rate limit at the edge first, then defend internally.

---

## 5️⃣ Core Rate Limiting Algorithms

### 1. Fixed Window

```md
Allow N requests per time window
```

Example:

* 100 req / minute

Problem:

* burst at window boundaries

---

### 2. Sliding Window (Improved)

Tracks requests over rolling time.

Better:

* smoother limits
* more computation

---

### 3. Token Bucket (Most Used)

Concept:

* bucket fills at fixed rate
* each request consumes a token

```md
Tokens refill over time
Bursts allowed until bucket empty
```

---

### Token Bucket (Pseudo-code)

```java
if (tokens > 0) {
    tokens--;
    allow();
} else {
    reject();
}
```

Why popular:

* allows bursts
* smooth average rate

---

### 4. Leaky Bucket

Concept:

* requests processed at constant rate
* excess requests queued or dropped

Tradeoff:

```md
Token Bucket → burst-friendly
Leaky Bucket → smooth output
```

---

## 6️⃣ What Happens When Limit Is Exceeded?

Options:

* HTTP 429 (Too Many Requests)
* Retry-After header
* Graceful degradation

Example:

```http
HTTP/1.1 429 Too Many Requests
Retry-After: 30
```

---

## 7️⃣ Rate Limiting & Fairness

You can rate limit by:

* IP (coarse)
* user (better)
* API key (best)

Senior insight:

> Identity-based limits are fairer than IP-based limits.

---

### ✅ If you stop here

You can:

* explain why rate limiting exists
* choose algorithms
* justify gateway placement
* crack most HLD interviews

This is your **80% confidence zone**.

---

# PART 3️⃣ — THE REMAINING 20% (SENIOR DIFFERENTIATOR)

This is where **experienced engineers stand out**.

---

## 8️⃣ Distributed Rate Limiting (Hard Part)

Problem:

* multiple instances
* shared limits

Solutions:

* centralized store
* consistent hashing
* approximate counters

Example store:

* Redis
* In-memory + sync

Senior line:

> Distributed rate limiting trades accuracy for scalability.

---

## 9️⃣ Atomicity in Rate Limiting

Important:

```md
Check + increment must be atomic
```

Example (pseudo):

```java
count = get(key);
if (count < limit) {
  increment(key);
  allow();
}
```

In distributed systems:

* use atomic ops
* avoid race conditions

---

## 🔟 Burst Handling

Bursts happen:

* retries
* spikes
* fan-out calls

Design choice:

```md
Allow small bursts, cap sustained rate
```

Token bucket naturally supports this.

---

## 1️⃣1️⃣ Rate Limiting vs Backpressure

Difference:

* Rate limiting → reject excess
* Backpressure → slow producers

Senior framing:

> Rate limiting protects services; backpressure protects pipelines.

---

## 1️⃣2️⃣ Adaptive Rate Limiting

Limits change based on:

* system load
* error rates
* latency

Example:

```md
High latency → lower rate limit
```

Used in:

* large-scale APIs
* traffic shaping

---

## 1️⃣3️⃣ Rate Limiting & Security

Used for:

* brute-force protection
* DDoS mitigation

Combine with:

* IP reputation
* CAPTCHA
* WAF

---

## 1️⃣4️⃣ Observability for Rate Limiting

Monitor:

* rejected requests
* per-client usage
* burst patterns

Without metrics:

> Rate limits become silent failures.

---

## 1️⃣5️⃣ How to DEFEND Rate Limiting in Interviews

Final framing:

```md
I use rate limiting to protect system stability,
ensure fair usage, and prevent abuse.

I enforce it at the gateway using token bucket,
accept small bursts, and reject sustained overload
with clear client feedback.
```

If you can say this calmly → **you win the round**.

---
