Excellent.
Now we enter **database-level cloud maturity**.

This is where backend + infra + failure-thinking combine.

---

# 📁 File: `05-managed-database-rds-cloudsql-ha.md`

# 🔥 STEP 5 — Managed Database (RDS / Cloud SQL)

(High Availability + Replication + Failover + Backups + Connection Strategy)

Interviewers may ask:

* Why use managed DB?
* What is Multi-AZ?
* How failover works?
* How do replicas work?
* How do you scale reads?
* What about connection limits?

You must answer structurally.

---

# 🧠 1️⃣ Why Managed Database?

Instead of running MySQL in EC2 or Kubernetes:

Use:

* AWS RDS
* GCP Cloud SQL
* Azure Database

Cloud manages:

✔ Backups
✔ Patching
✔ Replication
✔ Failover
✔ Monitoring

You focus on schema and queries.

---

# 🧠 2️⃣ Multi-AZ Deployment (High Availability)

In RDS Multi-AZ:

Primary DB in AZ1
Standby replica in AZ2

Replication is synchronous.

Flow:

App → Primary
Data replicated instantly → Standby

If primary fails:

Automatic failover to standby.

DNS endpoint remains same.

Minimal downtime (~30–60 seconds).

---

# 🧠 3️⃣ Read Replica (Scaling Reads)

Read replicas are different from standby.

Primary → Async replication → Read replica

Used for:

✔ Reporting queries
✔ Analytics
✔ Read-heavy workloads

Example architecture:

App writes → Primary
App reads → Replica

---

# 🧠 4️⃣ Multi-AZ vs Read Replica Difference

Multi-AZ:

* For availability
* Synchronous
* Automatic failover

Read Replica:

* For read scaling
* Asynchronous
* Manual promotion

Important distinction.

---

# 🧠 5️⃣ Backup Strategy

RDS supports:

✔ Automated daily backup
✔ Point-in-time recovery
✔ Manual snapshot

Senior answer should mention:

* Define backup retention period
* Test restore regularly
* Store snapshot cross-region (critical systems)

---

# 🧠 6️⃣ Disaster Recovery (Multi-Region)

If entire region fails:

Multi-AZ not enough.

Need:

* Cross-region replica
* Snapshot replication
* DNS failover strategy

DR planning is senior maturity.

---

# 🧠 7️⃣ Connection Pooling Problem

RDS has connection limits.

Example:

t3.medium → ~90–100 connections.

If:

10 pods
Each pod uses 20 connections

200 total → DB crash.

---

# 🧠 8️⃣ Use Connection Pool

In Spring Boot:

```properties
spring.datasource.hikari.maximum-pool-size=10
```

Or use:

RDS Proxy (AWS)

RDS Proxy:

* Manages connection pooling
* Reduces DB load
* Handles connection spikes

Important production pattern.

---

# 🧠 9️⃣ Scaling Compute vs Storage

RDS allows:

* Vertical scaling (instance size)
* Storage auto-scaling

Vertical scaling causes short downtime.

Plan capacity carefully.

---

# 🧠 🔟 Storage Performance Types

AWS RDS supports:

* General Purpose (gp2/gp3)
* Provisioned IOPS

High-transaction systems → Provisioned IOPS.

Know difference.

---

# 🧠 1️⃣1️⃣ Security Best Practices

✔ DB in private subnet
✔ No public internet exposure
✔ Security group restrict access to app servers only
✔ IAM authentication (optional)
✔ Encryption at rest enabled

Senior answer must mention private subnet.

---

# 🧠 1️⃣2️⃣ Encryption

Enable:

* Encryption at rest (KMS)
* SSL/TLS for DB connections

In Spring Boot:

```properties
spring.datasource.url=jdbc:mysql://...&useSSL=true
```

Security maturity.

---

# 🧠 1️⃣3️⃣ Real Interview Scenario

Question:

“What happens if primary RDS instance fails?”

Strong answer:

* Multi-AZ standby promoted automatically
* DNS endpoint updated
* Short failover window
* Application reconnects automatically

Clear. Structured.

---

# 🧠 1️⃣4️⃣ Monitoring DB

Monitor:

* CPU usage
* Connections
* Free storage
* Slow queries
* Replication lag

Set alerts.

DB often bottleneck.

---

# 🧠 1️⃣5️⃣ Senior-Level Mental Model

App layer scales horizontally.
Database layer scales vertically + replicas.

High availability requires:

Multi-AZ + backups + tested restore.

Database is single biggest failure risk.

Design carefully.

---

# 🎯 STEP 5 REVISION CHECKLIST

You must confidently explain:

✔ Why managed DB
✔ Multi-AZ concept
✔ Read replica difference
✔ Backup strategy
✔ Cross-region DR
✔ Connection pooling
✔ RDS Proxy
✔ Private subnet security
✔ Encryption best practices

If you can explain all clearly →
Cloud database maturity achieved.

---

When ready, type:

**6**

Next file:

📁 `06-object-storage-s3-design-patterns.md`
(S3/GCS deep dive + durability model + lifecycle rules + CDN integration + presigned URL pattern)
