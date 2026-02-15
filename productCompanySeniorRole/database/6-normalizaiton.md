Excellent.
Now we move into **real senior tradeoff territory**.

This is where interviews stop being academic and start being architectural.

---

# 🔥 STEP 6 — NORMALIZATION vs DENORMALIZATION

(Performance vs Purity)

Goal:
Understand when to design clean data
and when to intentionally break rules.

---

# 🧠 1️⃣ Why Normalization Exists

Purpose:

* Avoid duplication
* Prevent update anomalies
* Maintain data integrity

---

## Example — Bad Design (Denormalized Wrongly)

```sql
orders (
  id BIGINT,
  user_id BIGINT,
  user_name VARCHAR(100),
  user_city VARCHAR(50),
  amount DECIMAL(10,2)
);
```

Problem:

If user changes city → update every order row.

This causes:

* Data inconsistency
* Massive updates
* Locking

---

## Correct Normalized Design

```sql
users (
  id BIGINT PRIMARY KEY,
  name VARCHAR(100),
  city VARCHAR(50)
);

orders (
  id BIGINT PRIMARY KEY,
  user_id BIGINT,
  amount DECIMAL(10,2),
  FOREIGN KEY (user_id) REFERENCES users(id)
);
```

Now user data stored once.

---

# 🧠 2️⃣ Normal Forms (Practical View)

You don’t need textbook definition in interview.
You need practical understanding.

---

## 1NF — No repeating groups

❌ Wrong:

```
order(id, product_ids="1,2,3")
```

✔ Correct:

Use order_items table.

---

## 2NF — No partial dependency

If composite key (order_id, product_id)
Don’t store order_date depending only on order_id inside that table.

---

## 3NF — No transitive dependency

If:

user → city_id
city_id → state

Then don’t store state inside user table.

---

# 🧠 Interview Trick Question

“Is 3NF always best?”

Correct answer:

For OLTP systems → yes, mostly.
But sometimes we denormalize for performance.

---

# 🔥 3️⃣ When to Denormalize

Senior thinking starts here.

---

## Case 1 — Read Heavy System

If 95% reads, very few writes.

Joining 5 tables repeatedly is expensive.

You may store:

* user_name in orders table
* precomputed total_amount

---

## Case 2 — Analytics / Reporting

Instead of:

Join orders + users + cities every time

You create:

```sql
daily_revenue_summary (
  date DATE,
  city VARCHAR(50),
  total_revenue DECIMAL(10,2)
);
```

Precomputed.

---

## Case 3 — Avoid Expensive Joins at Scale

At massive scale (Uber-like):

Joining huge tables becomes costly.

Denormalization reduces join cost.

---

# 🧠 Example — Denormalization Done Right

Store product price at purchase time:

```sql
order_items (
  order_id BIGINT,
  product_id BIGINT,
  price_at_purchase DECIMAL(10,2)
);
```

Why?

Because product price changes.

This is intentional duplication.

---

# 🧠 4️⃣ Tradeoff Thinking (Interview Gold)

Interviewer:

Why not normalize everything?

You say:

Normalization improves consistency
But increases join cost

Denormalization improves read performance
But increases update complexity

Choice depends on:

* Read/write ratio
* Query patterns
* Data growth
* Consistency requirement

This answer signals senior maturity.

---

# 🧠 5️⃣ Real Production Example

High-traffic ride system:

Option A (normalized):

rides → users → cities → regions

4 joins for one query.

Option B (denormalized):

Store region directly in rides table.

Faster read. Slight duplication.

At scale → B wins.

---

# 🧠 6️⃣ Materialized View Concept

Instead of complex aggregation each time:

Create summary table updated periodically.

Example:

```sql
CREATE TABLE monthly_user_spend (
  user_id BIGINT,
  month DATE,
  total_spent DECIMAL(10,2)
);
```

Updated via batch job.

This is common in fintech systems.

---

# 🧠 7️⃣ Interview Simulation

Question:

Design payment ledger system.

Should we normalize ledger entries?

Yes — ledger must be normalized for audit.

But for dashboard?

Use denormalized summary tables.

This shows layered thinking.

---

# 🎯 STEP 6 CHECKPOINT

You must be able to explain:

✔ Why normalization matters
✔ When to denormalize
✔ Performance tradeoffs
✔ Real-world use cases
✔ Materialized view idea

---

# 🧠 MINI TEST

1. Why not store user_name inside every table?
2. When would you denormalize city into rides?
3. What happens if denormalized field updates frequently?

If you can answer calmly → good.

---

Now next level:

Performance engineering starts.

Type **7**.

🔥 INDEX MASTERY (Senior Filter #2)
