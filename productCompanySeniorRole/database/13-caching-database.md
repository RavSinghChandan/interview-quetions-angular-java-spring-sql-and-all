Excellent.
Now we move into **real-world performance engineering**.

At scale, DB alone is never enough.
This is where senior backend engineers shine.

---

# 🔥 STEP 13 — CACHING + DATABASE INTERACTION

(Performance Engineering Layer)

Goal:
Reduce DB load without breaking consistency.

---

# 🧠 1️⃣ Why Caching Exists

Database is:

* Disk-based
* Limited connections
* Expensive for repetitive reads

Most production systems:

90% reads
10% writes

Instead of hitting DB every time → use cache.

---

# 🧱 2️⃣ Typical Architecture

Client
→ App Server
→ Cache (Redis)
→ Database

---

# 🧠 3️⃣ Cache-Aside Pattern (Most Common)

Flow:

1. App checks cache
2. If present → return
3. If not → fetch from DB
4. Store in cache

Example (pseudo):

```java
User user = redis.get("user:101");

if (user == null) {
    user = db.getUser(101);
    redis.set("user:101", user);
}
```

This is cache-aside.

---

# 🧠 4️⃣ Write Handling (Critical)

Case: user updates profile.

Flow:

```sql
UPDATE users
SET name = 'Rahul'
WHERE id = 101;
```

Then:

Invalidate cache:

```
redis.delete("user:101");
```

Next read → fresh DB → cache again.

This avoids stale data.

---

# 🧠 5️⃣ Cache Invalidation Problem

Hardest problem in CS.

If you forget to invalidate:

Cache becomes stale.

Solution options:

* Time-to-live (TTL)
* Explicit invalidation
* Versioning

---

# 🧠 6️⃣ TTL Strategy

Set expiration:

```
redis.set("user:101", data, TTL=300 seconds);
```

After 5 minutes → auto-expire.

Tradeoff:
Possible stale data within TTL window.

---

# 🧠 7️⃣ Write-Through Cache

Instead of:

Write to DB then invalidate.

You:

1. Write to cache
2. Cache writes to DB

Ensures consistency.

More complex.

---

# 🧠 8️⃣ Write-Back (Dangerous)

Write only to cache first.
DB updated later asynchronously.

Fast but risky.

Used in high-throughput systems.

---

# 🧠 9️⃣ Cache Stampede Problem

If key expires:

1000 requests hit DB simultaneously.

DB overload.

Solution:

* Mutex lock
* Request coalescing
* Staggered TTL
* Pre-warming

Senior must mention this.

---

# 🧠 10️⃣ Hot Key Problem

If one key extremely popular:

All traffic hits one Redis node.

Solution:

* Shard cache
* Replicate hot key
* Use local in-memory cache

---

# 🧠 11️⃣ When NOT to Cache

Don’t cache:

* Frequently updated data
* Low reuse queries
* Huge result sets

Cache is not magic.

---

# 🧠 12️⃣ Read-Through vs Cache-Aside

Cache-aside:
App manages logic.

Read-through:
Cache layer handles DB fetch automatically.

Most systems use cache-aside.

---

# 🧠 13️⃣ Query-Level Caching Example

Heavy query:

```sql
SELECT SUM(amount)
FROM orders
WHERE created_at > NOW() - INTERVAL 1 DAY;
```

Instead of running repeatedly:

Cache result for 1 minute.

---

# 🧠 14️⃣ Consistency Tradeoff

Strong consistency:
Always hit DB.

High performance:
Use cache.

Senior answer:
Depends on business criticality.

Payment ledger → avoid stale cache.

User profile → cache OK.

---

# 🎯 HOW TO ANSWER IN INTERVIEW

If asked:

“DB under heavy read load. What will you do?”

You say:

1. Add read replicas
2. Introduce Redis cache
3. Cache hot queries
4. Handle invalidation
5. Monitor hit ratio

Structured answer.

---

# 🎯 STEP 13 CHECKPOINT

You must be able to:

✔ Explain cache-aside
✔ Handle invalidation
✔ Explain cache stampede
✔ Explain hot key
✔ Explain TTL tradeoff

---

# 🧠 MINI TEST

1. Why caching can cause stale reads?
2. What is cache stampede?
3. Why write-back risky?
4. Why not cache everything?

If you answer calmly → strong.

---

Next:

🔥 STEP 14 — FAILURE HANDLING + RETRY + IDEMPOTENCY
(This is fintech-level maturity)

Type **14**.
