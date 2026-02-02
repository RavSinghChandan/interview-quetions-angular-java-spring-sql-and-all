
---


# Spring Boot — Module 3: ApplicationContext, Bean Lifecycle & Dependency Graph
(Custom Notes | Senior Developer | Barclays / Mastercard Level)

==================================================

## WHY THIS MODULE MATTERS (REAL PRODUCTION FAILURES)

Most Spring Boot production bugs are:
- startup failures
- circular dependencies
- unexpected bean behavior
- memory leaks

All of them live in:
👉 **ApplicationContext & Bean lifecycle**

If you master this, you stop fearing Spring internals.

==================================================


## CORE IDEA: APPLICATIONCONTEXT

### What ApplicationContext Is
- Central container for:
  - BeanDefinitions
  - Beans
  - Resources
  - Events
  - Environment

```java
ApplicationContext context;
````

### Mental Anchor

> If it’s Spring-managed, it lives in the ApplicationContext.

==================================================

## BEAN LIFECYCLE — HIGH-LEVEL FLOW

For a **singleton bean**:

1. BeanDefinition loaded
2. Constructor called
3. Dependency injection
4. Aware callbacks
5. BeanPostProcessors (before init)
6. @PostConstruct
7. BeanPostProcessors (after init)
8. Bean ready
9. Context shutdown
10. @PreDestroy

### Mental Anchor

> Spring wraps your bean with logic before you ever use it.

==================================================

## STEP 1: BEAN DEFINITION (NO OBJECT YET)

```java
@Component
class PaymentService { }
```

Spring registers:

* class
* scope
* dependencies
* metadata

### Senior Insight

> Spring separates **definition** from **instantiation**.

==================================================

## STEP 2: INSTANTIATION (CONSTRUCTOR PHASE)

```java
@Component
class PaymentService {
    public PaymentService() {
        // constructor
    }
}
```

* Object is created
* No dependencies injected yet

### Pitfall

Using injected fields in constructor logic.

==================================================

## STEP 3: DEPENDENCY INJECTION

```java
@Autowired
private TransactionService txService;
```

Injection happens:

* after construction
* before init methods

### Constructor Injection (RECOMMENDED)

```java
@Component
class PaymentService {
    private final TransactionService tx;

    public PaymentService(TransactionService tx) {
        this.tx = tx;
    }
}
```

### Senior Rule

> Constructor injection prevents half-initialized beans.

==================================================

## STEP 4: AWARE INTERFACES (ADVANCED)

Examples:

```java
BeanNameAware
ApplicationContextAware
EnvironmentAware
```

Used to:

* access container metadata
* avoid static hacks

### Mental Anchor

> Aware = controlled access to the container.

==================================================

## STEP 5: BeanPostProcessors (VERY IMPORTANT)

Spring applies **cross-cutting logic** here.

Examples:

* @Autowired handling
* @Transactional
* @Async
* @Cacheable

```java
BeanPostProcessor
```

### Senior Insight

> Most Spring “magic” happens via BeanPostProcessors.

==================================================

## STEP 6: INIT PHASE

```java
@PostConstruct
public void init() { }
```

or

```java
InitializingBean.afterPropertiesSet()
```

Use for:

* validation
* resource setup

### Rule

No heavy logic here.

==================================================

## STEP 7: PROXY CREATION (CRITICAL)

Spring may wrap your bean in a proxy:

* JDK dynamic proxy
* CGLIB proxy

Used for:

* transactions
* security
* async
* caching

### Pitfall

Calling proxied methods from same class.

### Mental Anchor

> Proxies intercept calls, not methods.

==================================================

## BEAN SCOPES (VERY IMPORTANT)

| Scope     | Behavior        |
| --------- | --------------- |
| singleton | One per context |
| prototype | New per request |
| request   | HTTP request    |
| session   | HTTP session    |

### Senior Rule

> Singletons should be stateless.

==================================================

## CIRCULAR DEPENDENCIES (COMMON FAILURE)

```java
A → B → A
```

* Constructor injection → FAIL
* Field injection → sometimes works (bad)

### Senior Fixes

* Refactor responsibilities
* Use events
* Use lazy injection sparingly

```java
@Lazy
@Autowired
private B b;
```

==================================================

## LAZY INITIALIZATION

```java
@Lazy
@Component
class HeavyBean { }
```

or global:

```properties
spring.main.lazy-initialization=true
```

### Trade-off

* Faster startup
* Slower first call

==================================================

## CONTEXT SHUTDOWN & CLEANUP

```java
@PreDestroy
public void cleanup() { }
```

Used for:

* closing resources
* stopping threads

### Senior Insight

> Memory leaks often come from missing cleanup.

==================================================

## JVM & MEMORY IMPACT

* Singleton beans live long
* Prototype beans may leak
* Proxies add memory overhead
* Too many beans slow startup

==================================================

## COMMON MISTAKES (INTERVIEW FILTER)

* Field injection everywhere
* Heavy logic in constructors
* Ignoring circular dependencies
* Misusing prototype scope
* Forgetting @PreDestroy

==================================================

## REAL-WORLD USAGE (PRODUCT SYSTEMS)

* Payment services
* Connection pools
* Kafka producers
* Cache managers

==================================================

## 5-MINUTE REVISION SNAPSHOT

> Context owns beans
> Definitions ≠ instances
> Constructor injection preferred
> Proxies add behavior
> Cleanup matters

==================================================

## INTERVIEW PUNCHLINE (STRONG)

> Spring’s ApplicationContext manages bean definitions, lifecycle, and dependencies. As a senior engineer, I rely on constructor injection, understand proxy-based behavior, and design beans to be stateless, lifecycle-aware, and safe under concurrency and shutdown scenarios.

==================================================

END — SPRING BOOT MODULE 3 (APPLICATIONCONTEXT & BEAN LIFECYCLE)

```

---
