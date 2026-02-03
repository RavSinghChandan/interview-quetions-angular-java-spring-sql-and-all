
---


# Spring Boot — Module 2: Auto-Configuration Internals (The Real Power)
(Custom Notes | Senior Developer | Barclays / Mastercard Level)

==================================================

## WHY THIS MODULE MATTERS (HARD FILTER)

At senior level, Spring Boot interviews often pivot here.

Interviewers are silently asking:
- Do you know *why* a bean exists?
- Can you predict behavior without running the app?
- Can you override Boot safely?

If you master auto-configuration, **Spring Boot stops being a black box**.

==================================================


## CORE IDEA: WHAT AUTO-CONFIGURATION REALLY IS

Auto-configuration is:
- NOT magic
- NOT reflection-heavy guesswork
- PURE conditional bean registration

### Mental Anchor
> Auto-configuration = `if (conditions match) then register beans`.

==================================================


## WHERE AUTO-CONFIGURATION LIVES

Spring Boot auto-config classes live in:
```

spring-boot-autoconfigure.jar

````

Each auto-config is a **@Configuration class**.

Example:
```java
DataSourceAutoConfiguration
WebMvcAutoConfiguration
SecurityAutoConfiguration
````

### Senior Insight

> Every auto-config is just Java config with conditions.

==================================================

## HOW SPRING BOOT FINDS AUTO-CONFIG CLASSES

### Old (Spring Boot < 3)

```
META-INF/spring.factories
```

### New (Spring Boot 3+)

```
META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
```

This file lists auto-config classes.

### Mental Anchor

> Boot imports configurations the same way you import classes.

==================================================

## @EnableAutoConfiguration (THE SWITCH)

```java
@SpringBootApplication
→ @EnableAutoConfiguration
```

What it does:

* Loads all auto-config classes
* Applies conditions
* Registers beans conditionally

### Senior Insight

> Auto-config runs AFTER component scanning.

==================================================

## CONDITIONAL ANNOTATIONS (MOST IMPORTANT)

Auto-config relies on **@Conditional** family.

### Most Common Ones

```java
@ConditionalOnClass
@ConditionalOnMissingBean
@ConditionalOnProperty
@ConditionalOnBean
@ConditionalOnWebApplication
```

### Example

```java
@Bean
@ConditionalOnClass(DataSource.class)
@ConditionalOnMissingBean
public JdbcTemplate jdbcTemplate() {
    return new JdbcTemplate();
}
```

### Mental Anchor

> Boot never overrides your beans unless you allow it.

==================================================

## AUTO-CONFIG DECISION FLOW (VISUALIZE THIS)

For each auto-config class:

1. Is this app type compatible?
2. Is required class on classpath?
3. Is required property set?
4. Is bean already defined?

If YES → Register bean
If NO → Skip silently

### Senior Insight

> Missing beans are usually skipped, not broken.

==================================================

## WHY AUTO-CONFIG FAILS (COMMON CONFUSION)

### Case 1: Class missing

```text
@ConditionalOnClass failed
```

### Case 2: Property not set

```properties
spring.datasource.url missing
```

### Case 3: Bean already exists

Your config overrides Boot.

### Mental Anchor

> Most Boot issues are conditional mismatches.

==================================================

## DEBUGGING AUTO-CONFIG (SENIOR SKILL)

Enable:

```properties
debug=true
```

This prints:

* Which auto-configurations applied
* Which were skipped
* Why they were skipped

### Senior Rule

> Never debug auto-config blindly — always read the report.

==================================================

## OVERRIDING AUTO-CONFIG (SAFE WAY)

### Option 1: Define Your Own Bean

```java
@Bean
public DataSource dataSource() { ... }
```

Boot backs off automatically.

### Option 2: Disable Auto-Config

```java
@SpringBootApplication(
  exclude = DataSourceAutoConfiguration.class
)
```

### Mental Anchor

> Override by bean first, exclude as last resort.

==================================================

## AUTO-CONFIG ORDERING (SUBTLE BUT IMPORTANT)

Annotations:

```java
@AutoConfigureBefore
@AutoConfigureAfter
@AutoConfigureOrder
```

Used when auto-configs depend on each other.

### Senior Insight

> Ordering bugs cause startup-time failures.

==================================================

## AUTO-CONFIG VS MANUAL CONFIG (INTERVIEW FAVORITE)

| Auto-Config      | Manual Config |
| ---------------- | ------------- |
| Fast             | Explicit      |
| Opinionated      | Flexible      |
| Conditional      | Deterministic |
| Less boilerplate | More control  |

### Senior Answer

> I rely on auto-config for infrastructure and manual config for domain-specific behavior.

==================================================

## JVM & PERFORMANCE IMPACT

* Each auto-config checks conditions
* Classpath size affects startup
* Too many starters slow boot time

### Optimization

```properties
spring.autoconfigure.exclude=...
```

==================================================

## COMMON MISTAKES (INTERVIEW FILTER)

* Disabling auto-config blindly
* Not understanding @ConditionalOnMissingBean
* Overusing exclude
* Ignoring debug report
* Adding unnecessary starters

==================================================

## REAL-WORLD USAGE (PRODUCT SYSTEMS)

* Environment-specific configs
* Cloud vs on-prem behavior
* Feature toggles
* Gradual infra evolution

==================================================

## 5-MINUTE REVISION SNAPSHOT

> Auto-config is conditional
> Driven by classpath & properties
> MissingBean protects you
> debug=true explains everything
> Override safely

==================================================

## INTERVIEW PUNCHLINE (VERY STRONG)

> Spring Boot auto-configuration is a conditional bean registration mechanism that configures infrastructure based on classpath, properties, and existing beans. As a senior engineer, I rely on understanding these conditions to predict behavior, override safely, and debug startup issues effectively.

==================================================

END — SPRING BOOT MODULE 2 (AUTO-CONFIGURATION INTERNALS)

```

