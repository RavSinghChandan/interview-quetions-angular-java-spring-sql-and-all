
---

# PART 1️⃣ — DATABASE HLD MIND MAP (HOW YOU SHOULD THINK)

This is the **navigation system** for your brain in interviews.

```md
DATABASE (as a system under stress)
│
├── 1. Why does data need a DB?
│     └── Guarantees (persistence, concurrency, integrity)
│
├── 2. What does the business demand?
│     ├── Money? → ACID
│     ├── Scale? → CAP tradeoff
│     └── Latency? → Cache / replicas
│
├── 3. How is data shaped?
│     ├── Schema
│     ├── Relationships
│     └── Access patterns
│
├── 4. How does it scale?
│     ├── Reads → Replication
│     └── Writes → Sharding
│
├── 5. How does it stay fast?
│     ├── Indexing
│     ├── Caching
│     └── Query optimization
│
├── 6. What happens when things fail?
│     ├── Node crash
│     ├── Disk failure
│     └── Network partition
│
├── 7. What are we trading off?
│     ├── Consistency vs Availability
│     ├── Cost vs Reliability
│     └── Simplicity vs Scale
│
└── 8. Can I defend this design?
      └── If yes → HLD is successful
```

> **Interview truth:**
> If you can walk this tree **verbally**, you control the discussion.

---

# PART 2️⃣ — THE CORE 80% (PARETO ZONE)

👉 This is what **most interviewers expect**
👉 This alone gives you **confidence + clarity**

---

## 1️⃣ What is a Database (HLD Definition)

```md
A database is a persistent, concurrent, and fault-tolerant system
that enforces business correctness while data scales and failures occur.
```

In HLD:

> **Database = Contract, not storage**

---

## 2️⃣ ACID (When correctness matters)

### Why ACID exists

* Partial writes destroy money systems
* Concurrency destroys consistency

### Example (Transaction)

```sql
BEGIN;

UPDATE accounts
SET balance = balance - 100
WHERE id = 1;

UPDATE accounts
SET balance = balance + 100
WHERE id = 2;

COMMIT;
```

If crash happens:

* Either both updates apply
* Or none apply

That’s **Atomicity + Durability**.

Use ACID when:

* payments
* orders
* inventory

---

## 3️⃣ CAP Theorem (Distributed Reality)

In distributed DBs:

* **P is mandatory**
* You choose **C or A**

Examples:

* Banking DB → CP
* Social feed → AP

Interview sentence:

> “Since partition tolerance is mandatory, we trade between consistency and availability based on business needs.”

---

## 4️⃣ Data Modeling (Most underrated skill)

### Schema example

```sql
CREATE TABLE orders (
  id BIGINT PRIMARY KEY,
  user_id BIGINT,
  status VARCHAR(20),
  created_at TIMESTAMP
);
```

Bad schema = slow joins + slow indexes forever.

---

### Normalization vs Denormalization

**Normalized**

* fewer anomalies
* more joins

**Denormalized**

* faster reads
* complex writes

Rule:

```md
OLTP → normalize
Read-heavy → denormalize
```

---

## 5️⃣ Indexing (Read performance lever)

```sql
CREATE INDEX idx_orders_user_id
ON orders(user_id);
```

Tradeoff:

* Reads ↑
* Writes ↓
* Memory ↑

Golden rule:

> Index **queries**, not tables.

---

## 6️⃣ Scaling the Database

### Replication (Read scaling)

```
Client → Leader → Followers
                 ↑
              Read traffic
```

* improves availability
* replication lag exists

---

### Sharding (Write scaling)

```
orders_0 → node A
orders_1 → node B
orders_2 → node C
```

Shard key decides everything.

Interview line:

> Replication scales reads, sharding scales writes.

---

## 7️⃣ Performance Basics

Order of fixes:

1. Index
2. Cache
3. Replicas
4. Query tuning

Example cache flow:

```
App → Redis → DB
```

---

## 8️⃣ Failure Handling (Baseline)

Failures:

* disk
* node
* network

Defenses:

* replication
* WAL
* backups

Interview line:

> Databases don’t fail often — disks do.

---

### ✅ If you stop here

You can:

* design DB layer
* justify choices
* pass many HLD rounds

This is your **80% confidence zone**.

---

# PART 3️⃣ — THE REMAINING 20% (SENIOR DIFFERENTIATOR)

👉 This is what **separates you from mid-level candidates**

---

## 9️⃣ Transaction Internals (How ACID is enforced)

### Write-Ahead Log (WAL)

Flow:

```
Change → WAL → Memory → Disk
```

Crash recovery:

* replay WAL
* restore committed state

Senior sentence:

> Durability comes from logs, not disks.

---

## 🔟 Isolation Levels (Concurrency depth)

| Level           | Problem              |
| --------------- | -------------------- |
| Read Committed  | Non-repeatable reads |
| Repeatable Read | Phantom reads        |
| Serializable    | Lowest concurrency   |

Most prod systems:

```md
Read Committed / Repeatable Read
```

---

## 1️⃣1️⃣ MVCC (Postgres/MySQL)

Concept:

* multiple versions of rows
* readers don’t block writers

Tradeoff:

* memory overhead
* vacuum required

Senior insight:

> MVCC trades memory for concurrency.

---

## 1️⃣2️⃣ Schema Migrations (Real-world pain)

Bad:

```sql
ALTER TABLE orders ADD COLUMN x TEXT;
```

Good pattern:

```md
Expand → Migrate → Contract
```

This avoids downtime.

---

## 1️⃣3️⃣ Observability (Production maturity)

Monitor:

* slow queries
* replication lag
* connection pool
* disk growth

Without this:

> You’re blind in prod.

---

## 1️⃣4️⃣ Cost & Cloud Reality

Hidden costs:

* replicas
* backups
* IO
* cross-AZ traffic

Senior line:

> Reliability always increases cost.

---

## 1️⃣5️⃣ How to DEFEND your DB choice (Meta-skill)

Final interview framing:

```md
I chose this DB because:
- data shape = X
- read/write pattern = Y
- consistency need = Z
- failure tolerance = W
```

If you can say this calmly → **you win**.

---

