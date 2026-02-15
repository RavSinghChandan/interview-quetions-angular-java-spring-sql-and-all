Good. Now real SQL starts.

---

# 🔥 STEP 2 — JOIN THINKING

(Where 70% of candidates get exposed.)

Goal:
Think relationally.
Stop thinking in single tables.

---

# 🧱 Base Schema for This Phase

```sql
users (
  id INT PRIMARY KEY,
  name VARCHAR(100),
  city VARCHAR(50),
  created_at TIMESTAMP
);

orders (
  id INT PRIMARY KEY,
  user_id INT,
  amount DECIMAL(10,2),
  status VARCHAR(20),
  created_at TIMESTAMP
);

payments (
  id INT PRIMARY KEY,
  order_id INT,
  payment_mode VARCHAR(20),
  paid_at TIMESTAMP
);
```

Relationships:

users 1 —— N orders
orders 1 —— 1 payments

---

# 🧠 1️⃣ INNER JOIN

👉 Get users with their orders

```sql
SELECT u.id, u.name, o.id AS order_id, o.amount
FROM users u
INNER JOIN orders o
ON u.id = o.user_id;
```

✔ Returns only users who have orders.

---

# 🧠 2️⃣ LEFT JOIN (Very Important)

👉 Get all users, even if no orders

```sql
SELECT u.id, u.name, o.id AS order_id
FROM users u
LEFT JOIN orders o
ON u.id = o.user_id;
```

If user has no order → order columns = NULL.

---

# ⚠️ Senior Trap

Where clause after LEFT JOIN can convert it to INNER JOIN.

❌ Wrong:

```sql
SELECT *
FROM users u
LEFT JOIN orders o
ON u.id = o.user_id
WHERE o.status = 'PAID';
```

This removes NULL rows → becomes inner join.

✔ Correct:

```sql
SELECT *
FROM users u
LEFT JOIN orders o
ON u.id = o.user_id
AND o.status = 'PAID';
```

---

# 🧠 3️⃣ Multi-table Join

👉 Get users, their orders, and payment mode

```sql
SELECT u.name,
       o.id AS order_id,
       p.payment_mode
FROM users u
JOIN orders o ON u.id = o.user_id
JOIN payments p ON o.id = p.order_id;
```

---

# 🧠 4️⃣ Self Join (Advanced Thinking)

Assume:

```sql
employees (
  id INT,
  name VARCHAR(100),
  manager_id INT
);
```

👉 Get employee with manager name

```sql
SELECT e.name AS employee,
       m.name AS manager
FROM employees e
LEFT JOIN employees m
ON e.manager_id = m.id;
```

This is senior relational thinking.

---

# 🧠 5️⃣ Join + Filter

👉 Get paid orders only

```sql
SELECT u.name, o.amount
FROM users u
JOIN orders o ON u.id = o.user_id
WHERE o.status = 'PAID';
```

Always ask:
Is there index on orders.user_id?
Is there index on status?

Otherwise join becomes expensive.

---

# 🧠 6️⃣ Join + Aggregation

👉 Total amount spent per user

```sql
SELECT u.id,
       u.name,
       SUM(o.amount) AS total_spent
FROM users u
JOIN orders o ON u.id = o.user_id
GROUP BY u.id, u.name;
```

---

# 🧠 7️⃣ Join vs Subquery (Interview Favorite)

👉 Users who placed orders

Subquery:

```sql
SELECT *
FROM users
WHERE id IN (
  SELECT user_id
  FROM orders
);
```

Join:

```sql
SELECT DISTINCT u.*
FROM users u
JOIN orders o
ON u.id = o.user_id;
```

---

# 🎯 When to Use What?

IN subquery:

* Cleaner
* Sometimes slower on large datasets

JOIN:

* Usually better optimized
* More flexible

Senior answer:
"It depends on execution plan."

---

# 🧠 8️⃣ Anti Join (Very Important)

👉 Users with no orders

```sql
SELECT u.*
FROM users u
LEFT JOIN orders o
ON u.id = o.user_id
WHERE o.id IS NULL;
```

Very common interview question.

---

# 🧠 9️⃣ Top User by Orders

```sql
SELECT u.id, u.name, COUNT(o.id) AS total_orders
FROM users u
JOIN orders o ON u.id = o.user_id
GROUP BY u.id, u.name
ORDER BY total_orders DESC
LIMIT 1;
```

---

# 🧠 10️⃣ Duplicate Detection (Classic)

👉 Find duplicate emails

```sql
SELECT email, COUNT(*)
FROM users
GROUP BY email
HAVING COUNT(*) > 1;
```

---

# 🔥 Senior Awareness — Performance Thinking

When writing join:

Always mentally check:

* Is join column indexed?
* Is dataset large?
* Is join selective?
* Is this many-to-many explosion?

Join explosion example:

10k users × 100 orders each = 1M rows result.

Be aware.

---

# 🎯 STEP 2 CHECKPOINT

You must be able to:

✔ Write inner join
✔ Write left join safely
✔ Do self join
✔ Write anti join
✔ Combine join + aggregation
✔ Explain join performance

---

# 🧠 MINI SENIOR TEST

Answer mentally:

1. Get users who placed more than 3 orders.
2. Get latest order per user (don’t use window yet).
3. Find users who never paid successfully.

If you can write these cleanly → good.

---

Type **3** when ready.

Next level:
🔥 Aggregation + Business Metrics Mastery
