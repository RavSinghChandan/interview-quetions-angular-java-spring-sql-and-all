
---

````md
# Spring Boot — Module 7: Security Architecture (Spring Security, JWT, OAuth2)
(Custom Notes | Senior Developer | Barclays / Mastercard Level)

==================================================

## WHY THIS MODULE MATTERS (FINTECH REALITY)

Security failures = regulatory risk + brand damage.

Interviewers test:
- whether you understand the **filter chain**
- how authentication differs from authorization
- stateless vs stateful security
- JWT/OAuth2 trade-offs
- where security bugs actually come from

==================================================


## CORE IDEA: SECURITY IS A FILTER CHAIN

Spring Security is **filter-based**, not annotation-based.

HTTP Request
↓
Security Filter Chain
↓
Authentication
↓
Authorization
↓
Controller

### Mental Anchor
> Security happens *before* your controller is touched.

==================================================


## AUTHENTICATION vs AUTHORIZATION (MUST BE CRYSTAL CLEAR)

### Authentication
- Who are you?
- Identity verification

### Authorization
- What are you allowed to do?
- Permission check

### Senior Line
> Authentication establishes identity; authorization enforces access.

==================================================


## SECURITY FILTER CHAIN (VERY IMPORTANT)

Spring Security registers multiple filters, e.g.:
- SecurityContextPersistenceFilter
- UsernamePasswordAuthenticationFilter
- BearerTokenAuthenticationFilter
- ExceptionTranslationFilter
- FilterSecurityInterceptor

### Senior Insight
> Each request passes through ~10–15 security filters.

==================================================


## CONFIGURING SECURITY (MODERN WAY — BOOT 3+)

```java
@Bean
SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
      .csrf(csrf -> csrf.disable())
      .authorizeHttpRequests(auth -> auth
          .requestMatchers("/public/**").permitAll()
          .anyRequest().authenticated()
      )
      .oauth2ResourceServer(oauth ->
          oauth.jwt()
      );
    return http.build();
}
````

### Mental Anchor

> Security is declarative but enforced by filters.

==================================================

## STATEFUL vs STATELESS SECURITY

### Stateful (Session-based)

* Server stores session
* Scales poorly
* Traditional web apps

### Stateless (Token-based)

* No server-side state
* JWT in headers
* Scales horizontally

### Senior Rule

> APIs should be stateless.

==================================================

## JWT — HOW IT REALLY WORKS

JWT contains:

* Header (algo)
* Payload (claims)
* Signature

```http
Authorization: Bearer <JWT>
```

### Validation Flow

1. Extract token
2. Verify signature
3. Validate expiry
4. Build Authentication object

### Mental Anchor

> JWT is trusted only after signature verification.

==================================================

## JWT PITFALLS (INTERVIEW FAVORITES)

* Large tokens → network overhead
* Revocation is hard
* Clock skew issues
* Overloading claims

### Senior Rule

> JWT is identity proof, not a database.

==================================================

## OAUTH2 — BIG PICTURE

OAuth2 defines **authorization**, not authentication.

Roles:

* Resource Owner
* Client
* Authorization Server
* Resource Server

Spring Boot often acts as:

* Resource Server

### Mental Anchor

> OAuth2 is about delegated access.

==================================================

## METHOD-LEVEL SECURITY (FINE-GRAINED CONTROL)

```java
@PreAuthorize("hasRole('ADMIN')")
public void approvePayment() { }
```

Requires:

```java
@EnableMethodSecurity
```

### Senior Insight

> Method security complements URL security.

==================================================

## CSRF (COMMON CONFUSION)

* Relevant for browser + cookies
* Not needed for stateless APIs

```java
csrf(csrf -> csrf.disable())
```

### Senior Line

> CSRF protects browsers, not APIs.

==================================================

## EXCEPTION HANDLING IN SECURITY

* AuthenticationException → 401
* AccessDeniedException → 403

Handled by:

* ExceptionTranslationFilter

### Mental Anchor

> Security exceptions never reach controllers.

==================================================

## PASSWORD HANDLING (NEVER MESS THIS UP)

```java
@Bean
PasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
}
```

Rules:

* Never store plain text
* Never roll your own crypto
* Use strong hashing

==================================================

## SECURITY & PERFORMANCE

* Filters add latency
* Token parsing allocates memory
* Signature verification costs CPU
* Caching public keys helps

### Senior Rule

> Security is a performance feature too.

==================================================

## COMMON SECURITY MISTAKES (INTERVIEW FILTER)

* Putting auth logic in controllers
* Confusing roles & authorities
* Trusting JWT without verification
* Disabling security blindly
* Hardcoding secrets

==================================================

## REAL-WORLD USAGE (FINTECH SYSTEMS)

* Payment APIs
* Partner integrations
* Admin portals
* Internal microservices

==================================================

## 5-MINUTE REVISION SNAPSHOT

> Security is filter-based
> Auth ≠ Authorization
> APIs should be stateless
> JWT needs verification
> OAuth2 delegates access

==================================================

## INTERVIEW PUNCHLINE (VERY STRONG)

> Spring Security enforces authentication and authorization through a filter chain that operates before controller execution. In fintech systems, I design stateless, token-based security using JWT and OAuth2, apply fine-grained authorization, and balance security guarantees with performance and scalability concerns.

==================================================

END — SPRING BOOT MODULE 7 (SECURITY ARCHITECTURE)

```

---


