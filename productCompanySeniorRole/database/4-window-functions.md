Good.
Now you enter **Senior SQL territory**.

Most mid-level engineers stop at GROUP BY.
Window functions separate strong backend engineers from average ones.

---

# 🔥 STEP 4 — WINDOW FUNCTIONS

(Senior Filter #1)

Goal:
Solve ranking, deduplication, and “per group latest” problems cleanly.

---

# 🧱 Same Base Schema

```sql
orders (
  id INT,
  user_id INT,
  amount DECIMAL,
  status VARCHAR(20),
  created_at TIMESTAMP
);
```

---

# 🧠 1️⃣ What is Window Function?

Difference:

GROUP BY → collapses rows
WINDOW → keeps rows, adds computed column

That’s the key.

---

# 🧠 2️⃣ ROW_NUMBER()

👉 Get latest order per user (very common interview question)

```sql
SELECT *
FROM (
    SELECT o.*,
           ROW_NUMBER() OVER (
               PARTITION BY user_id
               ORDER BY created_at DESC
           ) AS rn
    FROM orders o
) t
WHERE rn = 1;
```

Explanation:

PARTITION BY → group logic
ORDER BY → ranking logic
rn = 1 → latest per user

This is clean. No messy subqueries.

---

# 🧠 3️⃣ RANK() vs DENSE_RANK()

👉 Rank users by total spending

First compute total:

```sql
SELECT user_id,
       SUM(amount) AS total_spent
FROM orders
GROUP BY user_id;
```

Now ranking:

```sql
SELECT user_id,
       total_spent,
       RANK() OVER (ORDER BY total_spent DESC) AS rnk
FROM (
    SELECT user_id,
           SUM(amount) AS total_spent
    FROM orders
    GROUP BY user_id
) t;
```

Difference:

RANK → skips numbers on tie
DENSE_RANK → no gaps

Example:

1000 → rank 1
1000 → rank 1
900 → rank 3 (RANK)
900 → rank 2 (DENSE_RANK)

Interview favorite.

---

# 🧠 4️⃣ Running Total

👉 Daily cumulative revenue

```sql
SELECT order_date,
       SUM(daily_revenue) OVER (
           ORDER BY order_date
           ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW
       ) AS running_total
FROM (
    SELECT DATE(created_at) AS order_date,
           SUM(amount) AS daily_revenue
    FROM orders
    WHERE status = 'PAID'
    GROUP BY DATE(created_at)
) t;
```

Used in:

* financial systems
* dashboards

---

# 🧠 5️⃣ Find Duplicate Latest Records (Dedup Pattern)

👉 Keep latest record per user

```sql
DELETE FROM orders
WHERE id NOT IN (
    SELECT id FROM (
        SELECT id,
               ROW_NUMBER() OVER (
                   PARTITION BY user_id
                   ORDER BY created_at DESC
               ) AS rn
        FROM orders
    ) t
    WHERE rn = 1
);
```

Senior-level cleanup query.

---

# 🧠 6️⃣ Top 3 Orders Per User

```sql
SELECT *
FROM (
    SELECT o.*,
           ROW_NUMBER() OVER (
               PARTITION BY user_id
               ORDER BY amount DESC
           ) AS rn
    FROM orders o
) t
WHERE rn <= 3;
```

Interview classic.

---

# 🧠 7️⃣ Difference Between LAG and LEAD

👉 Compare current order amount with previous order

```sql
SELECT user_id,
       amount,
       LAG(amount) OVER (
           PARTITION BY user_id
           ORDER BY created_at
       ) AS previous_amount
FROM orders;
```

Used for:

* growth analysis
* fraud detection
* anomaly detection

---

# 🧠 Senior Awareness — Performance

Window functions require:

* Sorting
* Memory
* Partition buffering

Always ask:

* Is dataset large?
* Can index help ORDER BY?
* Should this be done in analytics DB?

---

# 🎯 STEP 4 CHECKPOINT

You must be able to:

✔ Get latest per group
✔ Rank results
✔ Do running totals
✔ Do Top-N per group
✔ Use LAG/LEAD

If you can write these confidently → you are above 80% engineers.

---

# 🧠 MINI SENIOR TEST

1. Get second highest order per user.
2. Find users whose latest order amount > previous order amount.
3. Rank cities by revenue.

If you can write without panic → strong.

---

Now we move from writing queries
to designing schema.

Type **5**.

🔥 Relational Modeling (Design Thinking Starts)
