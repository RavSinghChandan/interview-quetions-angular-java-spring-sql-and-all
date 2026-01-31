
---

````md
# Spring Boot — Module 5: Web Layer & REST Architecture
(Custom Notes | Senior Developer | Barclays / Mastercard Level)

==================================================

## WHY THIS MODULE MATTERS (API REALITY)

At senior level, interviewers are NOT testing:
❌ “Can you write a controller?”

They ARE testing:
✅ Do you understand request flow end-to-end  
✅ Can you control cross-cutting concerns  
✅ Can you design stable, secure REST APIs  
✅ Can you debug production web issues  

==================================================


## BIG PICTURE: HOW A REQUEST FLOWS

Incoming HTTP Request
↓
Servlet Container (Tomcat)
↓
Filters
↓
DispatcherServlet
↓
Handler Interceptors
↓
Controller
↓
Service / Domain
↓
Response Serialization
↓
Outgoing HTTP Response

### Mental Anchor
> Controllers are just one stop in a long pipeline.

==================================================


## DISPATCHERSERVLET (CENTRAL PIECE)

Spring MVC is **front-controller based**.

```text
DispatcherServlet
````

Responsibilities:

* Route requests
* Select controller
* Invoke handler methods
* Handle exceptions
* Write response

### Senior Insight

> Every web request goes through DispatcherServlet.

==================================================

## CONTROLLERS (BOUNDARY LAYER)

```java
@RestController
@RequestMapping("/payments")
public class PaymentController {

    @GetMapping("/{id}")
    public Payment get(@PathVariable String id) {
        return service.find(id);
    }
}
```

### Senior Rules

* Controllers = orchestration only
* No business logic
* Validate inputs early

==================================================

## REQUEST MAPPING & RESOLUTION

Spring resolves handlers using:

* URL path
* HTTP method
* Headers
* Content type

```java
@PostMapping(
  value = "/pay",
  consumes = "application/json",
  produces = "application/json"
)
```

### Mental Anchor

> Mapping is multi-dimensional, not just URL-based.

==================================================

## FILTERS (LOW-LEVEL, CONTAINER SIDE)

Filters are part of **Servlet spec**.

Use cases:

* Authentication
* Logging
* CORS
* Request wrapping

```java
@Component
public class LoggingFilter implements Filter {
    public void doFilter(...) { }
}
```

### Key Properties

* Runs before Spring MVC
* Can block requests
* Order matters

### Senior Rule

> Filters handle infra-level concerns.

==================================================

## INTERCEPTORS (SPRING MVC LEVEL)

Interceptors sit **inside Spring MVC**.

```java
public class AuthInterceptor implements HandlerInterceptor {
    public boolean preHandle(...) { }
}
```

Lifecycle:

* preHandle
* postHandle
* afterCompletion

### Use cases

* Authorization checks
* Request metrics
* Locale resolution

### Mental Anchor

> Interceptors are Spring-aware, filters are not.

==================================================

## FILTERS vs INTERCEPTORS (INTERVIEW FAVORITE)

| Filters           | Interceptors     |
| ----------------- | ---------------- |
| Servlet level     | Spring MVC level |
| Before Dispatcher | After Dispatcher |
| Infra concerns    | App concerns     |
| Not Spring-aware  | Spring-aware     |

### Senior Answer

> Filters for infrastructure, interceptors for application logic.

==================================================

## REQUEST VALIDATION (IMPORTANT)

```java
@PostMapping("/pay")
public Response pay(@Valid @RequestBody PaymentRequest req) { }
```

```java
public class PaymentRequest {
    @NotNull
    private String accountId;
}
```

### Senior Rule

> Validate at the boundary, not in service logic.

==================================================

## EXCEPTION HANDLING (PRODUCTION-CRITICAL)

### Global Handling

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handle(...) { }
}
```

### Why This Matters

* Consistent error responses
* No stack traces leaking
* Clean API contracts

### Mental Anchor

> Exceptions are part of API design.

==================================================

## RESPONSE SERIALIZATION (JACKSON)

Spring uses Jackson by default.

Potential pitfalls:

* Lazy loading issues
* Infinite recursion
* Large payloads

```java
@JsonIgnoreProperties
@JsonView
@JsonInclude
```

### Senior Insight

> Serialization issues often appear only in prod.

==================================================

## HTTP STATUS CODES (SENIOR SIGNAL)

Correct usage matters:

* 200 OK
* 201 Created
* 400 Bad Request
* 401 Unauthorized
* 403 Forbidden
* 404 Not Found
* 500 Internal Server Error

### Senior Rule

> Status codes are part of API semantics.

==================================================

## API VERSIONING (ENTERPRISE NEED)

Common strategies:

* URL based (`/v1/payments`)
* Header based
* Media type based

### Senior Insight

> Version APIs early, regret less later.

==================================================

## JVM & PERFORMANCE INSIGHT

* Filters add latency
* Serialization allocates memory
* Large payloads stress GC
* Connection pool & thread pool matter

==================================================

## COMMON MISTAKES (INTERVIEW FILTER)

* Business logic in controllers
* No global exception handling
* Mixing filters & interceptors
* Poor status code usage
* Ignoring serialization cost

==================================================

## REAL-WORLD USAGE (PRODUCT SYSTEMS)

* Payment APIs
* Fraud detection endpoints
* Internal admin APIs
* Partner integrations

==================================================

## 5-MINUTE REVISION SNAPSHOT

> DispatcherServlet routes
> Filters = infra
> Interceptors = app logic
> Validate at boundary
> Exceptions shape APIs

==================================================

## INTERVIEW PUNCHLINE (STRONG)

> In Spring Boot, the web layer is built around DispatcherServlet with filters and interceptors handling cross-cutting concerns. As a senior engineer, I design thin controllers, enforce validation and exception handling at boundaries, and carefully manage serialization, status codes, and request flow to build robust and maintainable REST APIs.

==================================================

END — SPRING BOOT MODULE 5 (WEB & REST ARCHITECTURE)

```

---
