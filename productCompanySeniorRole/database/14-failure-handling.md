Excellent.
Now we enter **fintech / Uber / payment-system maturity level**.

This is where many senior candidates fail.

Because real systems don’t just scale —
they **fail**, retry, and recover.

---

# 🔥 STEP 14 — FAILURE HANDLING + RETRY + IDEMPOTENCY

(Production Survival Layer)

Goal:
Handle crashes, timeouts, duplicate requests safely.

---

# 🧠 1️⃣ The Real World Problem

User clicks “Pay”.

App calls:

```sql
INSERT INTO payments (...)
```

Network fails after DB commit.

User retries payment.

Now system may create duplicate payment.

This is dangerous.

---

# 🧠 2️⃣ Idempotency (Critical Concept)

Idempotent operation:
Running it multiple times → same result.

---

## ❌ Non-idempotent

```sql
UPDATE accounts
SET balance = balance - 100
WHERE id = 1;
```

If retried twice → deduct twice.

---

## ✔ Idempotent Approach

Use unique transaction ID.

```sql
CREATE TABLE payments (
  id BIGINT PRIMARY KEY,
  transaction_id VARCHAR(100) UNIQUE,
  user_id BIGINT,
  amount DECIMAL(10,2)
);
```

Now insert:

```sql
INSERT INTO payments (transaction_id, user_id, amount)
VALUES ('txn_123', 1, 100);
```

If retried → unique constraint prevents duplicate.

This is real production thinking.

---

# 🧠 3️⃣ Retry Logic

Failures happen because of:

* Deadlock
* Timeout
* Network glitch

Correct pattern:

Retry only safe operations.

Pseudo:

```
try {
   execute transaction
} catch (DeadlockException) {
   retry 2-3 times
}
```

Never infinite retry.

---

# 🧠 4️⃣ Deadlock Retry Pattern

When DB kills transaction due to deadlock:

You catch error and retry transaction.

Common in high concurrency systems.

---

# 🧠 5️⃣ Timeout Handling

Long-running query:

```sql
SELECT *
FROM huge_table
JOIN other_huge_table;
```

Timeout may occur.

Solutions:

* Optimize query
* Add index
* Break into smaller queries

Never blindly increase timeout.

---

# 🧠 6️⃣ Partial Failure Problem

Example:

Step 1: Create order
Step 2: Charge payment

If payment fails after order creation:

Now system inconsistent.

Solution:

Use transaction OR compensation logic.

---

# 🧠 7️⃣ Saga Pattern (High-Level)

For distributed systems:

If step 3 fails → compensate step 2.

But inside single DB:
Prefer atomic transaction.

---

# 🧠 8️⃣ Exactly Once vs At Least Once

In distributed system:

Retry may happen.

If not idempotent → duplicate actions.

Senior engineers design APIs to be idempotent.

---

# 🧠 9️⃣ Unique Constraints for Safety

Example:

Prevent duplicate email:

```sql
ALTER TABLE users
ADD CONSTRAINT unique_email UNIQUE (email);
```

DB-level constraint is strongest safety.

---

# 🧠 10️⃣ Optimistic Locking (Advanced)

Add version column:

```sql
users (
  id BIGINT,
  balance INT,
  version INT
);
```

Update:

```sql
UPDATE users
SET balance = 900,
    version = version + 1
WHERE id = 1
AND version = 3;
```

If 0 rows affected → conflict.

This prevents lost updates.

Very senior-level concept.

---

# 🧠 11️⃣ Lost Update Problem

Transaction A reads balance 1000
Transaction B reads balance 1000

Both subtract 100 → write 900.

Final balance 900 instead of 800.

Use:

* SELECT FOR UPDATE
  OR
* Optimistic locking

---

# 🧠 12️⃣ Graceful Degradation

If DB overloaded:

* Disable non-critical features
* Serve cached data
* Queue writes

Senior thinking includes fallback strategy.

---

# 🎯 HOW TO ANSWER IN INTERVIEW

If asked:

“How do you prevent duplicate payment?”

You answer:

1. Unique transaction ID
2. Unique constraint
3. Idempotent API
4. Retry only safe operations
5. Deadlock retry

Complete answer = senior signal.

---

# 🎯 STEP 14 CHECKPOINT

You must be able to:

✔ Explain idempotency
✔ Design unique transaction pattern
✔ Handle retries safely
✔ Explain deadlock retry
✔ Use optimistic locking
✔ Prevent lost updates

---

# 🧠 MINI TEST

1. Why retry without idempotency is dangerous?
2. How unique constraint helps distributed system?
3. What is lost update?
4. Difference between optimistic and pessimistic locking?

If you answer smoothly → strong.

---

Now we enter final integration layer.

This is where you combine everything.

Type **15**.

🔥 REAL INTERVIEW SIMULATION — DESIGN + QUERY + SCALE + FAILURE
