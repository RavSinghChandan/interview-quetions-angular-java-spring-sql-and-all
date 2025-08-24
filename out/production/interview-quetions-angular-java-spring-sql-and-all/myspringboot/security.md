Awesome ⚡ Let’s now expand **Section 4 → Security** of Spring Boot.
This section covers **Spring Security basics, OAuth2/JWT, Method-Level Security, CSRF, and Modern Security Practices** — from **Spring Boot 1.x → 2.x → 3.x**.

Here’s the full `.md` content:

---

````markdown
# 4. Security

Spring Boot integrates **Spring Security** to provide authentication, authorization, and secure communication.  
It evolved from simple username/password setups → to **OAuth2, JWT, Reactive Security, and Zero-Trust architecture**.

---

## 4.1 Spring Security Basics

### Spring Boot 1.x (2014)
- Auto-configured **basic authentication** out of the box.  
- Default login page provided if no custom security config.  
- Allowed in-memory user setup via `application.properties`.  

```properties
spring.security.user.name=admin
spring.security.user.password=secret
````

```java
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .anyRequest().authenticated()
            .and().formLogin();
    }
}
```

---

### Spring Boot 2.x (2018)

* Enhanced **password storage** with `PasswordEncoder` (BCrypt by default).
* Added **first-class OAuth2 Client & Resource Server** support.
* Easier configuration with `spring-boot-starter-oauth2-client`.

---

### Spring Boot 3.x (2022–2025)

* Moved fully to **Jakarta APIs**.
* Deprecated `WebSecurityConfigurerAdapter` → replaced by **SecurityFilterChain**.

```java
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
        .formLogin();
    return http.build();
}
```

* Enhanced **observability hooks** for login attempts, failed logins, etc.

---

## 4.2 OAuth2 / JWT Authentication

### Spring Boot 1.x

* OAuth2 support existed but **required extra configuration** (Spring Security OAuth project).
* JWT had to be manually integrated.

---

### Spring Boot 2.x

* Introduced **Spring Security OAuth2 Client + Resource Server**.
* Native JWT support via `spring-security-oauth2-resource-server`.
* Standardized JWT validation & decoding.

```yaml
spring:
  security:
    oauth2:
      resourceserver:
        jwt:
          issuer-uri: https://example.com/issuer
```

---

### Spring Boot 3.x

* OAuth2/JWT upgraded to align with **modern OIDC standards**.
* Better integration with **Keycloak, Okta, Auth0**.
* Support for **opaque tokens** and **JWT with JSON Web Keys (JWK)**.
* Native image compatibility for JWT parsing.

---

## 4.3 Method-Level Security

### Spring Boot 1.x

* Basic annotations: `@PreAuthorize`, `@Secured`.

```java
@PreAuthorize("hasRole('ADMIN')")
public void adminMethod() { ... }
```

---

### Spring Boot 2.x

* Added support for **SpEL expressions** in method security.
* Improved fine-grained control with `@PostAuthorize`, `@PreFilter`, `@PostFilter`.

---

### Spring Boot 3.x

* Method security **revamped** with `@EnableMethodSecurity` (replacing `@EnableGlobalMethodSecurity`).
* Stronger integration with **reactive security** (WebFlux).

---

## 4.4 CSRF & Modern Security Practices

### Spring Boot 1.x

* CSRF enabled by default for web applications.
* APIs often required disabling CSRF manually.

```java
http.csrf().disable();
```

---

### Spring Boot 2.x

* Improved CSRF handling for REST APIs.
* Support for **stateless authentication** (JWT + CSRF off by default in APIs).

---

### Spring Boot 3.x

* Security practices aligned with **Zero Trust Architecture**.
* Stronger defaults:

    * HTTPS-first
    * CSRF enabled for web apps, disabled for stateless APIs
    * Strong password hashing (Argon2, BCrypt)
* Integrated with **Observability → failed login attempts, suspicious traffic**.

---

# ✅ Summary

* **Spring Security Basics** → From form login (1.x) → OAuth2 & JWT (2.x) → SecurityFilterChain & observability (3.x).
* **OAuth2/JWT** → From manual setup (1.x) → first-class JWT/OAuth2 support (2.x) → OIDC-native + JWK (3.x).
* **Method Security** → From `@PreAuthorize` (1.x) → advanced filters (2.x) → `@EnableMethodSecurity` (3.x).
* **CSRF & Practices** → From basic CSRF (1.x) → API-friendly configs (2.x) → Zero Trust defaults (3.x).

Spring Boot Security evolved from **simple form login** → **enterprise OAuth2/JWT** → **modern, observability-driven, zero-trust security**.

---

```

---

✅ This completes **Spring Boot Security**.  

Do you want me to now expand **Section 5 → Actuator & Monitoring (Health checks, Metrics, Micrometer, Observability)** in the same `.md` style?
```
