
---


# Spring Boot — Module 4: Configuration Management & Profiles
(Custom Notes | Senior Developer | Barclays / Mastercard Level)

==================================================

## WHY THIS MODULE MATTERS (ENTERPRISE REALITY)

Most production incidents are not code bugs.
They are **configuration bugs**.

Interviewers use this module to test:
- environment discipline
- predictability across deployments
- ability to manage secrets safely
- operational maturity

==================================================


## CORE IDEA: EXTERNALIZED CONFIGURATION

Spring Boot separates:
- code (static)
- configuration (dynamic)

This allows:
- same binary
- multiple environments

### Mental Anchor
> Code is immutable. Configuration is not.

==================================================


## CONFIGURATION SOURCES (ORDER MATTERS)

Spring Boot resolves properties from multiple sources.

### Simplified Precedence (High → Low)
1. Command-line arguments
2. Environment variables
3. application-{profile}.yml
4. application.yml
5. Default values

```properties
server.port=8081
````

### Senior Insight

> Most “why didn’t this apply?” issues are precedence misunderstandings.

==================================================

## application.properties vs application.yml

### Properties

```properties
server.port=8080
```

### YAML (Preferred for complex config)

```yaml
server:
  port: 8080
```

### Senior Rule

> Use YAML for structure, properties for overrides.

==================================================

## SPRING PROFILES (CRITICAL CONCEPT)

Profiles allow:

* environment-specific behavior
* bean activation control

```yaml
spring:
  profiles:
    active: prod
```

Files:

* application-dev.yml
* application-test.yml
* application-prod.yml

### Mental Anchor

> Profiles partition configuration cleanly.

==================================================

## PROFILE-BASED BEANS

```java
@Profile("prod")
@Bean
public DataSource prodDataSource() { ... }
```

```java
@Profile("test")
@Bean
public DataSource testDataSource() { ... }
```

### Senior Insight

> Profiles apply to beans, not just properties.

==================================================

## @Value (LIMITED USE)

```java
@Value("${timeout.ms}")
private int timeout;
```

### Problems

* No validation
* Hard to refactor
* Scattered usage

### Senior Rule

> Use @Value only for small, simple values.

==================================================

## @ConfigurationProperties (SENIOR STANDARD)

```java
@ConfigurationProperties(prefix = "payment")
public class PaymentProperties {
    private int timeout;
    private boolean retry;
}
```

```yaml
payment:
  timeout: 2000
  retry: true
```

### Benefits

* Type safety
* Validation
* Centralized config

### Mental Anchor

> Config is data, model it like data.

==================================================

## VALIDATING CONFIGURATION

```java
@ConfigurationProperties(prefix = "payment")
@Validated
public class PaymentProperties {

    @Min(100)
    private int timeout;
}
```

### Senior Insight

> Fail fast on startup, not in production.

==================================================

## SECRETS MANAGEMENT (VERY IMPORTANT)

❌ Never hardcode secrets:

```yaml
password: admin123
```

### Preferred Approaches

* Environment variables
* Vaults (HashiCorp, AWS Secrets Manager)
* Kubernetes secrets

```yaml
spring:
  datasource:
    password: ${DB_PASSWORD}
```

### Mental Anchor

> Secrets live outside the repo.

==================================================

## CONFIGURATION OVERRIDES (REAL WORLD)

Use cases:

* Feature flags
* Hot fixes
* Canary deployments

```bash
java -jar app.jar --featureX.enabled=true
```

### Senior Insight

> Command-line overrides are powerful and dangerous.

==================================================

## MULTI-MODULE / MULTI-SERVICE CONFIG

Best Practices:

* Shared config conventions
* Service-specific prefixes
* Avoid global property pollution

```yaml
payment.service.timeout
order.service.timeout
```

==================================================

## COMMON CONFIG PITFALLS (INTERVIEW FILTER)

* Hardcoded secrets
* Too many profiles
* Overusing @Value
* Ignoring validation
* Environment drift

==================================================

## JVM & OPERATIONS INSIGHT

* Config changes may require restart
* Misconfig causes startup failure
* External config enables immutable infra

==================================================

## REAL-WORLD USAGE (PRODUCT SYSTEMS)

* DB connection config
* API timeouts
* Retry policies
* Feature toggles
* Cloud environment differences

==================================================

## 5-MINUTE REVISION SNAPSHOT

> Config is external
> Profiles isolate envs
> @ConfigurationProperties > @Value
> Validate early
> Secrets outside code

==================================================

## INTERVIEW PUNCHLINE (STRONG)

> Spring Boot’s externalized configuration model allows the same application binary to run across environments by resolving properties from multiple sources with clear precedence. As a senior engineer, I use profiles, type-safe configuration, and external secret management to ensure predictable, secure, and maintainable deployments.

==================================================

END — SPRING BOOT MODULE 4 (CONFIGURATION & PROFILES)

```

---

