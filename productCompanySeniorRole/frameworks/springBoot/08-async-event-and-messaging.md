
---


# Spring Boot — Module 8: Async Processing, Events & Messaging
(Custom Notes | Senior Developer | Barclays / Mastercard Level)

==================================================

## WHY THIS MODULE MATTERS (SCALABILITY CORE)

Modern fintech systems must:
- handle spikes
- decouple services
- process asynchronously
- remain resilient under failure

Interviewers test:
- async execution understanding
- thread pool control
- event-driven design
- messaging guarantees

==================================================


## BIG PICTURE: SYNC vs ASYNC

### Synchronous
- Request blocks thread
- Limited scalability

### Asynchronous
- Work offloaded
- Better throughput
- Requires discipline

### Mental Anchor
> Async improves throughput, not correctness.

==================================================


## @Async — SPRING ASYNC MODEL

```java
@Async
public void sendNotification(...) { }
````

What happens:

* Method executed in a thread pool
* Caller thread returns immediately

### Requirements

```java
@EnableAsync
```

### Senior Insight

> @Async is proxy-based, just like @Transactional.

==================================================

## THREAD POOLS (CRITICAL)

Default:

* SimpleAsyncTaskExecutor (NOT production-ready)

### Custom Executor (RECOMMENDED)

```java
@Bean
Executor taskExecutor() {
    ThreadPoolTaskExecutor exec = new ThreadPoolTaskExecutor();
    exec.setCorePoolSize(10);
    exec.setMaxPoolSize(50);
    exec.setQueueCapacity(100);
    exec.initialize();
    return exec;
}
```

### Senior Rule

> Never rely on default executors.

==================================================

## ASYNC PITFALLS (INTERVIEW FAVORITE)

* Self-invocation bypasses proxy
* Exceptions swallowed
* Transaction context lost
* Thread pool exhaustion

### Mental Anchor

> Async changes execution context.

==================================================

## APPLICATION EVENTS (DECOUPLING TOOL)

```java
applicationEventPublisher.publishEvent(new PaymentEvent(...));
```

```java
@EventListener
public void handle(PaymentEvent e) { }
```

### Characteristics

* In-process
* Synchronous by default
* Can be async

```java
@Async
@EventListener
```

### Mental Anchor

> Events decouple components, not services.

==================================================

## KAFKA INTEGRATION (ENTERPRISE STANDARD)

### Why Kafka

* High throughput
* Durable events
* Consumer groups

```java
@KafkaListener(topics = "payments")
public void consume(String msg) { }
```

### Senior Insight

> Kafka is not just messaging; it’s a log.

==================================================

## DELIVERY SEMANTICS (INTERVIEW GOLD)

* At-most-once
* At-least-once
* Exactly-once (hard)

### Spring Kafka Defaults

* At-least-once

### Mental Anchor

> Exactly-once requires coordination.

==================================================

## BACKPRESSURE & LOAD CONTROL

Problems:

* Producer faster than consumer
* Thread pool exhaustion
* Memory pressure

Solutions:

* Bounded queues
* Rate limiting
* Consumer lag monitoring

### Senior Rule

> Backpressure is a system concern, not a library feature.

==================================================

## TRANSACTIONS + ASYNC (SUBTLE)

* @Async breaks transaction context
* Kafka + DB needs careful ordering

### Common Pattern

* Transactional outbox
* Event after commit

```java
@TransactionalEventListener
```

### Mental Anchor

> Never publish events before commit.

==================================================

## ERROR HANDLING & RETRIES

* Retry templates
* Dead-letter topics
* Idempotent consumers

### Senior Insight

> Retries must be idempotent.

==================================================

## JVM & PERFORMANCE INSIGHT

* Threads consume stack memory
* Async increases context switching
* Messaging increases GC pressure
* Poor executor sizing kills throughput

==================================================

## COMMON MISTAKES (INTERVIEW FILTER)

* Blocking inside @Async
* Unbounded queues
* No retry strategy
* Publishing events inside transactions
* Ignoring consumer lag

==================================================

## REAL-WORLD USAGE (PRODUCT SYSTEMS)

* Payment processing
* Fraud detection
* Notification systems
* Audit pipelines

==================================================

## 5-MINUTE REVISION SNAPSHOT

> Async uses thread pools
> Default executors are unsafe
> Events decouple logic
> Kafka is durable log
> Backpressure matters

==================================================

## INTERVIEW PUNCHLINE (VERY STRONG)

> In Spring Boot, I design asynchronous and event-driven flows using explicit thread pools, application events, and messaging systems like Kafka. I carefully manage execution context, backpressure, delivery guarantees, and transactional boundaries to build scalable and resilient systems.

==================================================

END — SPRING BOOT MODULE 8 (ASYNC & MESSAGING)

```

---

