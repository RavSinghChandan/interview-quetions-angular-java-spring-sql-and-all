
---


# Spring Boot — Module 6: Data Access & Transaction Management
(Custom Notes | Senior Developer | Barclays / Mastercard Level)

==================================================

## WHY THIS MODULE MATTERS (FINTECH CORE)

In product companies, bugs here mean:
- money mismatch
- data corruption
- inconsistent reads
- deadlocks under load

Interviewers test:
- transactional thinking
- JPA behavior awareness
- performance & consistency trade-offs

==================================================


## BIG PICTURE: DATA ACCESS LAYERS

Typical Spring Boot stack:

Controller
↓
Service (Transactional Boundary)
↓
Repository (Data Access)
↓
ORM / JDBC
↓
Database

### Mental Anchor
> Transactions belong in services, not repositories.

==================================================


## SPRING DATA JPA — WHAT IT REALLY DOES

Spring Data JPA provides:
- Repository abstraction
- Query generation
- Entity management

```java
public interface PaymentRepository
    extends JpaRepository<Payment, Long> {
}
````

### Senior Insight

> Spring Data reduces boilerplate, not responsibility.

==================================================

## ENTITY LIFECYCLE (CRITICAL)

Entity states:

* Transient
* Managed
* Detached
* Removed

```java
Payment p = new Payment();   // Transient
repo.save(p);               // Managed
```

### Mental Anchor

> Only managed entities are tracked by JPA.

==================================================

## TRANSACTIONS — CORE CONCEPT

```java
@Transactional
public void transfer(...) { }
```

What @Transactional does:

* Opens transaction
* Binds it to thread
* Commits or rolls back

### Senior Rule

> @Transactional is a boundary, not decoration.

==================================================

## TRANSACTION PROPAGATION (INTERVIEW FAVORITE)

Common types:

* REQUIRED (default)
* REQUIRES_NEW
* SUPPORTS
* NOT_SUPPORTED

```java
@Transactional(propagation = REQUIRES_NEW)
```

### Senior Insight

> Propagation defines transaction nesting behavior.

==================================================

## ISOLATION LEVELS (FINTECH IMPORTANT)

| Level           | Prevents             |
| --------------- | -------------------- |
| READ_COMMITTED  | Dirty reads          |
| REPEATABLE_READ | Non-repeatable reads |
| SERIALIZABLE    | Phantom reads        |

```java
@Transactional(isolation = Isolation.READ_COMMITTED)
```

### Trade-off

Higher isolation = lower concurrency

### Mental Anchor

> Consistency vs throughput is a business decision.

==================================================

## ROLLBACK RULES (COMMON TRAP)

By default:

* RuntimeException → rollback
* Checked Exception → NO rollback

```java
@Transactional(rollbackFor = Exception.class)
```

### Senior Insight

> Always be explicit about rollback rules.

==================================================

## LAZY vs EAGER LOADING (PERFORMANCE HOTSPOT)

```java
@OneToMany(fetch = FetchType.LAZY)
```

### Lazy Loading

* Loaded on access
* Requires open session

### Eager Loading

* Loaded immediately
* Can cause N+1 issues

### Senior Rule

> Lazy by default, eager by intent.

==================================================

## N+1 QUERY PROBLEM (VERY COMMON)

```java
for (Order o : orders) {
    o.getItems().size(); // triggers query each time
}
```

### Fixes

* fetch join
* entity graph
* batch fetching

### Mental Anchor

> ORM hides queries, not cost.

==================================================

## TRANSACTIONAL PROXIES (SUBTLE BUT IMPORTANT)

* @Transactional works via proxy
* Self-invocation bypasses proxy

```java
this.innerMethod(); // ❌ no transaction
```

### Senior Fix

* Move method to another bean
* Or use ApplicationContext

==================================================

## CONNECTION POOLING (PRODUCTION CRITICAL)

Spring Boot uses:

* HikariCP (default)

Key properties:

```yaml
spring.datasource.hikari.maximum-pool-size: 20
```

### Senior Insight

> Pool size limits concurrency, not DB performance.

==================================================

## READ vs WRITE SEPARATION

```java
@Transactional(readOnly = true)
```

Benefits:

* Optimized queries
* No dirty checking

### Senior Rule

> Mark read-only transactions explicitly.

==================================================

## JVM & PERFORMANCE INSIGHT

* Transactions bind DB connections to threads
* Long transactions = thread starvation
* Lazy loading outside transaction = runtime error
* ORM allocations increase GC pressure

==================================================

## COMMON MISTAKES (INTERVIEW FILTER)

* @Transactional on controllers
* Long-running transactions
* Ignoring isolation
* Lazy loading outside tx
* N+1 queries

==================================================

## REAL-WORLD USAGE (PRODUCT SYSTEMS)

* Payment settlement
* Account balance updates
* Order processing
* Audit trails

==================================================

## 5-MINUTE REVISION SNAPSHOT

> Transactions in service layer
> Propagation defines nesting
> Isolation defines consistency
> Lazy loading needs tx
> Pooling controls concurrency

==================================================

## INTERVIEW PUNCHLINE (VERY STRONG)

> In Spring Boot, I treat transactions as explicit service-layer boundaries. I carefully choose propagation and isolation levels based on business consistency requirements, avoid long-running transactions, manage lazy loading deliberately, and tune connection pools to balance throughput and resource usage in production systems.

==================================================

END — SPRING BOOT MODULE 6 (DATA ACCESS & TRANSACTIONS)

```


