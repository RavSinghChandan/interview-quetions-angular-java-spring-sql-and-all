
---

````md
# Spring Boot — Module 10: Performance & JVM Considerations
(Custom Notes | Senior Developer | Barclays / Mastercard Level)

==================================================

## WHY THIS MODULE MATTERS (SENIOR FILTER)

At scale, Spring Boot performance issues are rarely about:
❌ bad algorithms

They are usually about:
✅ startup time  
✅ memory footprint  
✅ GC pressure  
✅ thread exhaustion  
✅ blocking behavior  

Interviewers test whether you understand **how Spring Boot stresses the JVM**.

==================================================


## BIG IDEA: SPRING BOOT IS A JVM APPLICATION FIRST

Spring Boot performance = JVM performance + framework behavior.

### Mental Anchor
> If you don’t understand the JVM, you can’t tune Spring Boot.

==================================================


## STARTUP TIME (VERY IMPORTANT IN MICROSERVICES)

Startup cost comes from:
- classpath scanning
- auto-configuration checks
- bean creation
- proxy creation

### Common Problems
- too many starters
- unnecessary auto-config
- eager bean initialization

### Optimizations

```properties
spring.main.lazy-initialization=true
````

```java
@Lazy
@Component
class HeavyBean { }
```

### Senior Rule

> Faster startup = fewer beans + less classpath.

==================================================

## AUTO-CONFIGURATION & STARTUP COST

Each auto-config:

* checks classpath
* evaluates conditions
* may load metadata

### Senior Optimization

Exclude unused auto-configs:

```properties
spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration
```

### Mental Anchor

> Classpath size directly affects startup time.

==================================================

## MEMORY FOOTPRINT (HEAP & METASPACE)

Spring Boot memory usage comes from:

* bean instances
* proxies
* caches
* reflection metadata (Metaspace)

### Senior Concerns

* Too many singleton beans
* Large object graphs
* ClassLoader leaks (devtools, redeploys)

### JVM Flags (Example)

```bash
-Xms512m -Xmx512m
-XX:MaxMetaspaceSize=256m
```

==================================================

## GC PRESSURE & ALLOCATION PATTERNS

Common GC pressure sources:

* request/response objects
* JSON serialization
* logging
* messaging payloads

### Senior Insight

> Allocation rate matters more than heap size.

Avoid:

* excessive object creation in filters/interceptors
* logging large objects
* unnecessary DTO conversions

==================================================

## THREAD USAGE (CRITICAL IN PROD)

Threads consumed by:

* Tomcat request threads
* Async executors
* Kafka consumers
* DB connection pools

### Senior Rule

> Every blocking call consumes a thread.

### Common Failure

* Thread pool exhaustion
* Request timeouts
* Cascading failures

==================================================

## TOMCAT THREAD POOL TUNING

```yaml
server:
  tomcat:
    threads:
      max: 200
      min-spare: 20
```

### Trade-off

* Too many threads → context switching
* Too few threads → low throughput

==================================================

## CONNECTION POOLS & THREADS (TOGETHER)

DB pool size limits:

* max concurrent DB operations

Thread pool size limits:

* max concurrent requests

### Senior Rule

> DB pool size should be ≤ request thread pool.

==================================================

## BLOCKING VS NON-BLOCKING (IMPORTANT)

Spring MVC:

* blocking model
* thread per request

Reactive (WebFlux):

* event-loop model
* non-blocking IO

### Senior Insight

> Don’t mix blocking calls with non-blocking stacks.

==================================================

## LOGGING & PERFORMANCE

Logging costs:

* string creation
* IO
* synchronization

### Best Practices

* avoid logging in hot paths
* use async appenders
* avoid debug logs in prod

==================================================

## NATIVE IMAGES (AWARENESS)

Spring Boot + GraalVM:

* faster startup
* lower memory
* longer build time
* reflection constraints

### Senior Line

> Native images trade build complexity for runtime efficiency.

==================================================

## PROFILING & MEASUREMENT (NON-NEGOTIABLE)

Tools:

* JFR
* JVisualVM
* GC logs
* APM tools

### Senior Rule

> Never tune without measurement.

==================================================

## COMMON PERFORMANCE MISTAKES (INTERVIEW FILTER)

* Overusing starters
* Ignoring startup time
* Unbounded thread pools
* Excessive logging
* Blind JVM flag tuning

==================================================

## REAL-WORLD PERFORMANCE STRATEGY

* Measure startup & memory
* Limit beans & auto-config
* Tune thread pools consciously
* Control allocation rate
* Profile under load

==================================================

## 5-MINUTE REVISION SNAPSHOT

> Startup cost = beans + classpath
> Allocation drives GC
> Threads are finite
> Blocking consumes capacity
> Measure before tuning

==================================================

## INTERVIEW PUNCHLINE (VERY STRONG)

> Spring Boot performance is primarily driven by JVM behavior, startup lifecycle, bean count, thread usage, and allocation patterns. As a senior engineer, I focus on reducing startup overhead, controlling thread and connection pools, minimizing GC pressure, and validating performance decisions through profiling rather than assumptions.

==================================================

END — SPRING BOOT MODULE 10 (PERFORMANCE & JVM)

```

---

