Excellent.
Now we move into **real business SQL**.

Most senior interview SQL questions live here.

---

# 🔥 STEP 3 — AGGREGATION + BUSINESS METRICS

(Where data becomes insight.)

Goal:
Answer business questions confidently.

---

# 🧱 Base Schema (same as before)

```sql
users(id, name, city, created_at)

orders(id, user_id, amount, status, created_at)
```

---

# 🧠 1️⃣ GROUP BY — Core Rule

Rule:

Every selected column must either:

* Be aggregated
  OR
* Be in GROUP BY

---

## Example 1 — Total orders per user

```sql
SELECT user_id,
       COUNT(*) AS total_orders
FROM orders
GROUP BY user_id;
```

---

## Example 2 — Total revenue per user

```sql
SELECT user_id,
       SUM(amount) AS total_spent
FROM orders
WHERE status = 'PAID'
GROUP BY user_id;
```

---

# 🧠 2️⃣ GROUP BY with JOIN

👉 Get user name + total spent

```sql
SELECT u.id,
       u.name,
       SUM(o.amount) AS total_spent
FROM users u
JOIN orders o ON u.id = o.user_id
WHERE o.status = 'PAID'
GROUP BY u.id, u.name;
```

---

# ⚠️ Senior Mistake

❌ Wrong:

```sql
SELECT u.name, SUM(o.amount)
FROM users u
JOIN orders o ON u.id = o.user_id
GROUP BY u.id;
```

You forgot `u.name` in GROUP BY.

Some DBs allow it (MySQL loose mode).
In strict mode → error.

Senior engineers write portable SQL.

---

# 🧠 3️⃣ HAVING (Very Important)

HAVING filters after grouping.

👉 Users who spent more than 10,000

```sql
SELECT user_id,
       SUM(amount) AS total_spent
FROM orders
GROUP BY user_id
HAVING SUM(amount) > 10000;
```

---

Difference:

WHERE → filters rows before grouping
HAVING → filters groups after grouping

Interview classic.

---

# 🧠 4️⃣ Conditional Aggregation (Senior Favorite)

👉 Count paid vs failed orders per user

```sql
SELECT user_id,
       SUM(CASE WHEN status = 'PAID' THEN 1 ELSE 0 END) AS paid_orders,
       SUM(CASE WHEN status = 'FAILED' THEN 1 ELSE 0 END) AS failed_orders
FROM orders
GROUP BY user_id;
```

This is extremely powerful.

Used in:

* dashboards
* metrics
* fraud systems

---

# 🧠 5️⃣ Top N Users by Revenue

```sql
SELECT user_id,
       SUM(amount) AS total_spent
FROM orders
WHERE status = 'PAID'
GROUP BY user_id
ORDER BY total_spent DESC
LIMIT 5;
```

---

# 🧠 6️⃣ Date-Based Aggregation

👉 Revenue per day

```sql
SELECT DATE(created_at) AS order_date,
       SUM(amount) AS daily_revenue
FROM orders
WHERE status = 'PAID'
GROUP BY DATE(created_at)
ORDER BY order_date;
```

---

Senior thought:
Function on column may prevent index usage.

Better:
If possible, store date separately or use range condition.

---

# 🧠 7️⃣ Distinct Count

👉 Count unique users who placed orders

```sql
SELECT COUNT(DISTINCT user_id)
FROM orders;
```

Be careful:
COUNT(DISTINCT) is expensive on large datasets.

---

# 🧠 8️⃣ Find Second Highest Revenue User

```sql
SELECT user_id,
       SUM(amount) AS total_spent
FROM orders
GROUP BY user_id
ORDER BY total_spent DESC
LIMIT 1 OFFSET 1;
```

Interview classic.

---

# 🧠 9️⃣ Find Users with More Than 3 Orders

```sql
SELECT user_id,
       COUNT(*) AS total_orders
FROM orders
GROUP BY user_id
HAVING COUNT(*) > 3;
```

---

# 🧠 10️⃣ Monthly Revenue

```sql
SELECT DATE_FORMAT(created_at, '%Y-%m') AS month,
       SUM(amount) AS revenue
FROM orders
WHERE status = 'PAID'
GROUP BY DATE_FORMAT(created_at, '%Y-%m')
ORDER BY month;
```

Again:
Function on column may disable index.

Better approach:
Use range query per month.

---

# 🧠 Senior Awareness — Performance Layer

When using GROUP BY:

Ask:

* Is grouping column indexed?
* Is dataset large?
* Can we pre-aggregate?
* Should we maintain summary table?

In production systems:
Heavy aggregation often moved to:

* Materialized views
* Precomputed tables
* Analytics DB

---

# 🎯 STEP 3 CHECKPOINT

You must be able to:

✔ Use GROUP BY confidently
✔ Use HAVING correctly
✔ Use conditional aggregation
✔ Write Top-N queries
✔ Think about index impact

---

# 🧠 MINI SENIOR TEST

Answer mentally:

1. Get top 3 cities by total revenue.
2. Find users whose paid orders > failed orders.
3. Find city with highest average order amount.

If you can write these cleanly → strong.

---

Next level is serious jump.

Type **4**.

🔥 Window Functions (Senior Filter Begins)
