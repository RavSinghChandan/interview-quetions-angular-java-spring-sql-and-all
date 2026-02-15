Excellent.
Now we enter one of the **strongest senior filters**.

Most developers “use” indexes.
Very few **understand** them.

If you master this section → you clear 70% backend SQL interviews.

---

# 🔥 STEP 7 — INDEX MASTERY

(Senior Filter #2)

Goal:
Predict performance before running query.

---

# 🧠 1️⃣ What is an Index (Practical View)

Without index:

DB scans entire table (Full Table Scan).

With index:

DB jumps directly to required rows.

Think of it like:
Book without index vs book with index.

---

# 🧱 Example Table

```sql
orders (
  id BIGINT PRIMARY KEY,
  user_id BIGINT,
  amount DECIMAL(10,2),
  status VARCHAR(20),
  created_at TIMESTAMP
);
```

---

# 🧠 2️⃣ Basic Index

Frequent query:

```sql
SELECT *
FROM orders
WHERE user_id = 1001;
```

Add index:

```sql
CREATE INDEX idx_user
ON orders(user_id);
```

Now lookup becomes fast.

---

# 🧠 3️⃣ B-Tree Concept (Interview Level)

Most relational DBs use B-Tree index.

It allows:

* O(log n) search
* Efficient range scans

You don’t need internals.
But you must know:

Indexes are sorted structures.

---

# 🧠 4️⃣ Composite Index (Very Important)

Frequent query:

```sql
SELECT *
FROM orders
WHERE user_id = 1001
AND status = 'PAID'
ORDER BY created_at DESC;
```

Better index:

```sql
CREATE INDEX idx_user_status_created
ON orders(user_id, status, created_at DESC);
```

---

# 🧠 Leftmost Prefix Rule

Composite index works from left.

Index (user_id, status, created_at)

Works for:

✔ WHERE user_id = ?
✔ WHERE user_id = ? AND status = ?
✔ WHERE user_id = ? AND status = ? ORDER BY created_at

Does NOT work for:

❌ WHERE status = ?
❌ WHERE created_at = ?

This is interview classic.

---

# 🧠 5️⃣ Covering Index

Query:

```sql
SELECT user_id, status
FROM orders
WHERE user_id = 1001;
```

If index contains both columns:

(user_id, status)

DB can fetch data directly from index
Without touching main table.

This is called covering index.

Reduces IO.

---

# 🧠 6️⃣ When Index is Ignored

Case 1:

```sql
WHERE user_id + 1 = 1001
```

Index not used (function applied).

Case 2:

```sql
WHERE DATE(created_at) = '2024-01-01'
```

Index not used (function on column).

Correct way:

```sql
WHERE created_at >= '2024-01-01'
AND created_at < '2024-01-02';
```

---

# 🧠 7️⃣ Low Selectivity Problem

Column:

status = PAID / FAILED

If 95% rows = PAID
Index on status is useless.

Because DB still scans large portion.

Index is powerful when:

High selectivity.

Example:

email, user_id

---

# 🧠 8️⃣ Index on Foreign Keys

Always index foreign keys manually.

Example:

```sql
CREATE INDEX idx_order_user
ON orders(user_id);
```

Otherwise joins slow.

---

# 🧠 9️⃣ Too Many Indexes Problem

Each index:

* Consumes memory
* Slows INSERT
* Slows UPDATE
* Slows DELETE

Senior answer:

Indexes improve reads
But hurt writes.

Balance is key.

---

# 🧠 10️⃣ Order of Columns in Composite Index

Golden Rule:

Put most selective column first.

Bad:

(status, user_id)

Better:

(user_id, status)

Because user_id is more selective.

---

# 🧠 11️⃣ EXPLAIN Plan (Preview)

Before running slow query:

```sql
EXPLAIN
SELECT *
FROM orders
WHERE user_id = 1001;
```

Check:

* type (ALL = bad)
* index used
* rows examined

We go deeper next step.

---

# 🧠 Interview Simulation

Interviewer:

Query is slow.

```sql
SELECT *
FROM orders
WHERE status = 'PAID'
AND created_at > '2024-01-01';
```

You say:

Check index on (status, created_at)

If high PAID ratio → maybe reverse index order.

This is senior thinking.

---

# 🎯 STEP 7 CHECKPOINT

You must be able to:

✔ Explain B-tree
✔ Design composite index
✔ Explain leftmost rule
✔ Detect when index ignored
✔ Talk about write penalty
✔ Suggest index based on query

---

# 🧠 MINI TEST

1. Query by user_id and date range — what index?
2. Why index on boolean column is often useless?
3. Why too many indexes slow writes?

If you answer calmly → strong.

---

Next:

🔥 STEP 8 — QUERY OPTIMIZATION + EXPLAIN PLAN
(This is production debugging level)

Type **8**.
