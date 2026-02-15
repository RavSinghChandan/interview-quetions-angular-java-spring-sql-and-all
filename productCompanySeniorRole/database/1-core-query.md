Perfect.
We go deep. No fluff. Senior level.
This becomes your **personal SQL master notes**.

We start with:

---

# 🔥 STEP 1 — CORE QUERY FLUENCY

(You must become mechanically strong here.)

Goal:
You should be able to extract *exactly* what business wants without thinking twice.

---

# 🧱 1. SELECT — Not Just Basics

### Table: `users`

```sql
users (
  id INT PRIMARY KEY,
  name VARCHAR(100),
  email VARCHAR(100),
  city VARCHAR(50),
  created_at TIMESTAMP
);
```

---

## 1️⃣ Basic Filtering

👉 Get users from Pune

```sql
SELECT *
FROM users
WHERE city = 'Pune';
```

---

## 2️⃣ Multiple Conditions

👉 Users from Pune created after 2024

```sql
SELECT id, name, created_at
FROM users
WHERE city = 'Pune'
AND created_at >= '2024-01-01';
```

---

## 3️⃣ IN Clause

```sql
SELECT *
FROM users
WHERE city IN ('Pune', 'Mumbai', 'Delhi');
```

---

## 4️⃣ LIKE (Pattern Search)

```sql
SELECT *
FROM users
WHERE name LIKE 'A%';   -- starts with A
```

---

## 5️⃣ BETWEEN

```sql
SELECT *
FROM users
WHERE created_at BETWEEN '2024-01-01' AND '2024-12-31';
```

---

# 🧠 Senior Awareness #1

❌ Wrong thinking:
`SELECT *` everywhere.

✔ Senior thinking:
Select only required columns.

Because:

* Reduces IO
* Reduces memory
* Uses covering index better

---

# 🧱 2. ORDERING + LIMIT

👉 Latest 10 users

```sql
SELECT id, name, created_at
FROM users
ORDER BY created_at DESC
LIMIT 10;
```

---

# 🧠 Senior Awareness #2

If table is huge:

You must ask:
Is there index on `created_at`?

If not → full table scan + sort → slow.

---

# 🧱 3. DISTINCT

👉 Get unique cities

```sql
SELECT DISTINCT city
FROM users;
```

---

# ⚠️ Important

DISTINCT requires sorting or hashing.
On large tables → expensive.

Always question:
Do we really need distinct?

---

# 🧱 4. CASE (Business Logic Inside Query)

👉 Categorize users

```sql
SELECT name,
CASE
    WHEN city = 'Pune' THEN 'Tier-1'
    ELSE 'Other'
END AS city_type
FROM users;
```

---

# 🧠 Senior Awareness #3

CASE is powerful.
Often replaces post-processing in Java.

Push computation to DB when appropriate.

---

# 🧱 5. NULL Handling (Common Interview Trap)

👉 Users without email

```sql
SELECT *
FROM users
WHERE email IS NULL;
```

❌ Wrong:

```sql
WHERE email = NULL;
```

NULL comparison must use IS NULL.

---

# 🧱 6. COUNT vs COUNT(*)

```sql
SELECT COUNT(*) FROM users;
```

Counts rows.

```sql
SELECT COUNT(email) FROM users;
```

Counts non-null emails only.

---

# 🧠 Senior Awareness #4

COUNT(column) ignores NULL.
Very common bug.

---

# 🧱 7. UPDATE

```sql
UPDATE users
SET city = 'Bangalore'
WHERE id = 10;
```

⚠ Always include WHERE.
Otherwise full table update.

---

# 🧱 8. DELETE

```sql
DELETE FROM users
WHERE created_at < '2020-01-01';
```

---

# 🧠 Senior Awareness #5

DELETE large data?

Better:

* Soft delete flag
* Batch delete
* Archive table

Because large delete causes:

* Locks
* Transaction log growth

---

# 🧱 9. INSERT

```sql
INSERT INTO users (id, name, email, city, created_at)
VALUES (101, 'Rahul', 'rahul@email.com', 'Pune', NOW());
```

Bulk insert:

```sql
INSERT INTO users (id, name)
VALUES
(102, 'Amit'),
(103, 'Neha'),
(104, 'Vikas');
```

---

# 🧠 Senior Awareness #6

High write traffic?

Use batching.
Avoid one insert per request.

---

# 🧱 10. Subquery (Intro Level)

👉 Users created after average creation time

```sql
SELECT *
FROM users
WHERE created_at >
(
  SELECT AVG(created_at)
  FROM users
);
```

---

# ⚠️ Important

Subqueries may run per row depending on DB.
Sometimes rewrite using JOIN or CTE.

---

# 🎯 STEP 1 CHECKPOINT

You must be able to:

✔ Write clean filter query
✔ Use ordering
✔ Handle NULL
✔ Use CASE
✔ Use subquery
✔ Think about performance while writing

---

# 🧠 MINI INTERVIEW TEST (Answer mentally)

1. Fetch second latest user.
2. Get users whose email ends with gmail.com.
3. Count users per city (don’t worry, aggregation deeper in step 3).

---

If you are fully comfortable with this level,
type **2**.

Then we go to:

🔥 STEP 2 — JOIN THINKING (Real relational depth starts there)
