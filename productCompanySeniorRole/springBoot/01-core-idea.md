
---

````md
# Spring Boot — Module 1: Philosophy, Bootstrapping & Startup Model
(Custom Notes | Senior Developer | Barclays / Mastercard Level)

==================================================

## WHY THIS MODULE MATTERS (READ CAREFULLY)

At senior level, interviewers are NOT testing:
❌ “How to create a Spring Boot project?”

They ARE testing:
✅ Do you understand what actually happens when a Spring Boot app starts  
✅ Can you reason about startup time, bean loading, failures  
✅ Do you know what Spring Boot *adds* over Spring Framework  

If you master this module, Spring Boot stops feeling like magic.

==================================================


## CORE IDEA: WHAT SPRING BOOT REALLY IS

### Spring Boot is NOT:
- ❌ A replacement for Spring
- ❌ A new framework
- ❌ Just annotations

### Spring Boot IS:
- A **layer on top of Spring Framework**
- Focused on **auto-configuration**
- Opinionated defaults
- Production-first

### Mental Anchor
> Spring Framework gives you power.  
> Spring Boot gives you sane defaults.

==================================================


## WHY SPRING BOOT WAS CREATED (CONTEXT)

Before Spring Boot:
- XML-heavy configuration
- Manual bean wiring
- Manual server setup
- Environment-specific configs everywhere

Spring Boot solves:
- Boilerplate configuration
- Dependency hell
- Environment inconsistency
- Production readiness gaps

### Mental Anchor
> Spring Boot optimizes *time-to-production*, not flexibility.

==================================================


## ENTRY POINT: SpringApplication.run()

```java
@SpringBootApplication
public class PaymentApplication {
    public static void main(String[] args) {
        SpringApplication.run(PaymentApplication.class, args);
    }
}
````

This single line hides **a LOT**.

### Senior Insight

> Interviews often revolve around what *actually* happens inside `run()`.

==================================================

## WHAT HAPPENS STEP-BY-STEP AT STARTUP (HIGH LEVEL)

When `SpringApplication.run()` is called:

1. Create SpringApplication instance
2. Prepare Environment
3. Load ApplicationContext
4. Load BeanDefinitions
5. Apply Auto-Configuration
6. Instantiate Beans
7. Start Embedded Server
8. Publish Startup Events

We’ll break this down.

==================================================

## STEP 1: SpringApplication Object Creation

Internally:

```java
SpringApplication app = new SpringApplication(PaymentApplication.class);
```

What it does:

* Determines application type (Servlet / Reactive / None)
* Registers initializers
* Registers listeners

### Mental Anchor

> Spring Boot decides *what kind of app this is* before doing anything else.

==================================================

## STEP 2: Environment Preparation (VERY IMPORTANT)

Spring Boot builds an **Environment** from multiple sources:

Order of precedence (simplified):

1. Command-line args
2. OS environment variables
3. application-{profile}.yml
4. application.yml
5. Default properties

```yaml
server:
  port: 8081
spring:
  profiles:
    active: prod
```

### Senior Insight

> Most “it works locally but not in prod” bugs are Environment bugs.

==================================================

## STEP 3: ApplicationContext Creation

Spring Boot chooses the context implementation:

* Servlet app → `AnnotationConfigServletWebServerApplicationContext`
* Reactive app → `AnnotationConfigReactiveWebServerApplicationContext`

```java
ApplicationContext context = ...
```

### Mental Anchor

> Everything in Spring lives inside ApplicationContext.

==================================================

## STEP 4: Component Scanning & BeanDefinition Loading

```java
@SpringBootApplication
```

Internally includes:

```java
@ComponentScan
@EnableAutoConfiguration
@Configuration
```

Component scanning:

* @Component
* @Service
* @Repository
* @Controller

### Important

👉 Beans are NOT created yet
👉 Only **BeanDefinitions** are loaded

### Mental Anchor

> Spring first learns *what* to create, not *creates* it.

==================================================

## STEP 5: Auto-Configuration (BOOT MAGIC EXPLAINED)

Spring Boot checks:

* What’s on the classpath?
* What beans already exist?
* What properties are set?

Example:

```java
@Bean
@ConditionalOnClass(DataSource.class)
public JdbcTemplate jdbcTemplate() { ... }
```

### Mental Anchor

> Auto-config is conditional logic, not magic.

==================================================

## STEP 6: Bean Instantiation & Dependency Injection

Now Spring:

* Creates singleton beans
* Injects dependencies
* Resolves @Autowired
* Applies AOP proxies

Lifecycle:

1. Constructor
2. Dependency injection
3. @PostConstruct
4. Bean ready

### Senior Insight

> Most startup failures happen here.

==================================================

## STEP 7: Embedded Server Startup

Spring Boot starts:

* Tomcat / Jetty / Netty

```text
Tomcat started on port(s): 8080
```

DispatcherServlet is registered here.

### Mental Anchor

> Server startup happens AFTER context creation.

==================================================

## STEP 8: Application Events (IMPORTANT)

Spring publishes events:

* ApplicationStartingEvent
* ApplicationEnvironmentPreparedEvent
* ApplicationReadyEvent

```java
@EventListener(ApplicationReadyEvent.class)
public void onReady() {
    // app fully started
}
```

### Senior Insight

> Use events instead of hacks for startup hooks.

==================================================

## SPRING BOOT VS SPRING FRAMEWORK (INTERVIEW FAVORITE)

| Spring         | Spring Boot           |
| -------------- | --------------------- |
| Flexible       | Opinionated           |
| Manual config  | Auto-config           |
| XML/Java heavy | Minimal config        |
| Not prod-ready | Prod-ready by default |

### Senior Answer Line

> Spring Boot builds on Spring by providing auto-configuration and production defaults.

==================================================

## JVM & PERFORMANCE INSIGHT

* Startup time depends on:

    * number of beans
    * classpath size
    * auto-config checks
* More starters ≠ better
* Lazy init can help

```properties
spring.main.lazy-initialization=true
```

==================================================

## COMMON MISTAKES (INTERVIEW FILTER)

* Treating Boot as magic
* Ignoring startup time
* Overusing starters
* Putting logic in main()
* Not understanding Environment precedence

==================================================

## REAL-WORLD USAGE (PRODUCT SYSTEMS)

* Microservices startup
* Environment-based deployments
* CI/CD boot validation
* Cloud-native services

==================================================

## 5-MINUTE REVISION SNAPSHOT

> Boot is a layer on Spring
> run() hides startup phases
> Environment first
> Beans later
> Auto-config is conditional
> Server starts last

==================================================

## INTERVIEW PUNCHLINE (STRONG)

> Spring Boot is an opinionated layer over the Spring Framework that simplifies application startup and configuration through auto-configuration and convention-over-configuration. As a senior engineer, I understand its startup lifecycle, environment resolution, and bean creation flow to design performant and reliable production systems.

==================================================

END — SPRING BOOT MODULE 1 (PHILOSOPHY & STARTUP MODEL)

```

---


