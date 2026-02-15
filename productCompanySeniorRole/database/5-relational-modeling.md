Good.
Now we stop “writing queries” mindset.
Now we start **designing databases like a senior engineer**.

This is where interviews shift from syntax → thinking.

---

# 🔥 STEP 5 — RELATIONAL MODELING

(Design Thinking Begins)

Goal:
When interviewer says:
“Design database for ride booking / e-commerce / payment system”
You should not panic.

You should think in structure.

---

# 🧠 CORE PRINCIPLE

Database design is about:

* Correct entity boundaries
* Relationship clarity
* Future scalability
* Query efficiency

Not about just making tables.

---

# 🧱 1️⃣ Identify Entities First (NOT Tables)

Example: Ride Booking System

What are real-world entities?

* User
* Driver
* Ride
* Payment
* Vehicle

These become tables.

---

# 🧱 2️⃣ Define Primary Keys

Rule:

Every table must have a stable unique identifier.

Example:

```sql
CREATE TABLE users (
  id BIGINT PRIMARY KEY,
  name VARCHAR(100),
  phone VARCHAR(20),
  created_at TIMESTAMP
);
```

Use:

BIGINT for scalability.
Avoid INT for large systems.

---

# 🧠 Natural vs Surrogate Key

Natural key:
email, phone

Surrogate key:
auto increment id

Senior preference:
Use surrogate key as PK.
Put unique constraint on natural key.

Example:

```sql
ALTER TABLE users
ADD CONSTRAINT unique_phone UNIQUE (phone);
```

---

# 🧱 3️⃣ One-to-Many Relationship

Example:

One user → many rides.

```sql
CREATE TABLE rides (
  id BIGINT PRIMARY KEY,
  user_id BIGINT,
  driver_id BIGINT,
  fare DECIMAL(10,2),
  status VARCHAR(20),
  created_at TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users(id)
);
```

This is 1:N.

---

# 🧠 Interview Question

Why not store ride IDs inside user table?

Because:

* Violates normalization
* Makes querying harder
* Explodes row size

Senior answer:
We model relationships relationally.

---

# 🧱 4️⃣ Many-to-Many Relationship

Example:

Students ↔ Courses

Solution:
Bridge table.

```sql
CREATE TABLE enrollments (
  student_id BIGINT,
  course_id BIGINT,
  enrolled_at TIMESTAMP,
  PRIMARY KEY (student_id, course_id)
);
```

Composite primary key is fine here.

---

# 🧠 When to Use Composite PK?

Use composite when:

* Natural relationship is composite
* No need for surrogate id

Avoid composite when:

* Table will be referenced heavily
* Surrogate simplifies joins

---

# 🧱 5️⃣ Ownership & Dependency

Ask:

If user is deleted → should rides delete?

If yes → use ON DELETE CASCADE.

```sql
FOREIGN KEY (user_id)
REFERENCES users(id)
ON DELETE CASCADE;
```

Be careful:
Cascade in high-traffic system can cause massive deletes.

Senior engineers think about delete impact.

---

# 🧱 6️⃣ Nullable vs Not Null

Every column must be intentional.

```sql
phone VARCHAR(20) NOT NULL
```

If nullable → ask why.

NULL often introduces bugs.

---

# 🧠 Senior Modeling Example — E-Commerce

Entities:

* User
* Product
* Order
* OrderItem
* Payment

Why separate OrderItem?

Because:

One order → many products.

```sql
CREATE TABLE order_items (
  order_id BIGINT,
  product_id BIGINT,
  quantity INT,
  price_at_purchase DECIMAL(10,2),
  PRIMARY KEY (order_id, product_id)
);
```

Notice:
We store price_at_purchase.

Why?

Because product price may change later.

This is senior-level thinking.

---

# 🧠 Avoid Common Junior Mistakes

❌ Storing comma-separated IDs
❌ Storing JSON for relational data unnecessarily
❌ Missing indexes on foreign keys
❌ No unique constraints

---

# 🧠 7️⃣ Index Strategy During Design

If you know query pattern:

Add index immediately.

Example:

Frequent query:
“Get rides by user ordered by date”

Then:

```sql
CREATE INDEX idx_user_created
ON rides(user_id, created_at DESC);
```

Design and query go together.

---

# 🧠 Senior Interview Simulation

Interviewer:

Design Splitwise database.

You say:

Entities:

* User
* Group
* Expense
* ExpenseSplit
* Settlement

Then define relationships clearly.

If you jump to SQL without entity thinking → red flag.

---

# 🎯 STEP 5 CHECKPOINT

You must be able to:

✔ Identify entities correctly
✔ Define 1:N and M:N
✔ Choose primary keys properly
✔ Think about constraints
✔ Think about query pattern while designing

---

# 🧠 MINI DESIGN TEST

Design minimal schema for:

“Rate Limiter per User”

What tables?
What keys?
How to store counters?

Think before answering.

---

Now we move to:

Normalization vs Denormalization
(Performance tradeoffs begin)

Type **6**.
