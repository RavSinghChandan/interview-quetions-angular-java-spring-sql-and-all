
---


# Angular — Module 8: HTTP, Interceptors & API Design
(Custom Notes | Senior Full-Stack Engineer | Barclays / Mastercard Level)

==================================================

## WHY THIS MODULE MATTERS (FULL-STACK FILTER)

In enterprise systems:
- serviceBasedMNCLevel.frontend talks to many backends
- failures are normal
- security headers matter
- retries and timeouts are required

Interviewers test:
✅ Can you design robust API calls?
✅ Do you understand HTTP as a contract?
✅ Can you handle auth, errors, and retries centrally?

==================================================


## CORE IDEA: HTTP IS A CONTRACT, NOT A CALL

HTTP defines:
- request shape
- response shape
- error semantics
- performance expectations

### Mental Anchor
> Frontend and serviceBasedMNCLevel.backend must agree on contracts.

==================================================


## ANGULAR HttpClient BASICS

```ts
getPayments(): Observable<Payment[]> {
  return this.http.get<Payment[]>('/api/payments');
}
````

Characteristics:

* returns cold Observables
* typed responses
* JSON by default

### Senior Rule

> Services return Observables, components don’t subscribe manually.

==================================================

## HTTP LIFECYCLE (HIGH LEVEL)

Request flow:

1. Component triggers request
2. Interceptors run (request)
3. HTTP call sent
4. Interceptors run (response)
5. Observable emits

### Mental Anchor

> Interceptors wrap every request.

==================================================

## HTTP INTERCEPTORS (CRITICAL)

```ts
@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  intercept(req, next) {
    const authReq = req.clone({
      setHeaders: { Authorization: 'Bearer token' }
    });
    return next.handle(authReq);
  }
}
```

Use cases:

* auth headers
* logging
* retries
* error handling

==================================================

## MULTIPLE INTERCEPTORS (PIPELINE)

```ts
providers: [
  { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
  { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true }
]
```

Order matters.

### Senior Insight

> Interceptors form a chain.

==================================================

## ERROR HANDLING STRATEGY (ENTERPRISE GRADE)

```ts
catchError(err => {
  if (err.status === 401) { logout(); }
  return throwError(() => err);
})
```

Best practices:

* centralize error handling
* map serviceBasedMNCLevel.backend errors to UI errors
* avoid error handling in components

==================================================

## RETRIES & TIMEOUTS

```ts
this.http.get(url).pipe(
  timeout(3000),
  retry(2)
);
```

Trade-offs:

* retries increase load
* timeouts improve UX

### Senior Rule

> Retry idempotent calls only.

==================================================

## REQUEST CANCELLATION

```ts
switchMap(() => this.http.get(...))
```

Cancels:

* previous HTTP calls
* saves bandwidth
* prevents race conditions

### Mental Anchor

> switchMap cancels in-flight requests.

==================================================

## CACHING STRATEGIES (FRONTEND)

Options:

* in-memory caching
* shareReplay
* HTTP cache headers

```ts
payments$ = this.http.get('/api').pipe(shareReplay(1));
```

### Senior Insight

> Cache data, not requests.

==================================================

## AUTHENTICATION IN FRONTEND

* JWT in Authorization header
* Never trust serviceBasedMNCLevel.frontend alone
* Avoid storing tokens in localStorage if possible

### Senior Rule

> Frontend enforces UX security, serviceBasedMNCLevel.backend enforces real security.

==================================================

## HTTP PERFORMANCE CONSIDERATIONS

* Over-fetching data
* Large payloads
* Chattiness
* Duplicate calls

### Senior Rule

> Fewer, well-shaped APIs beat many small ones.

==================================================

## FRONTEND–BACKEND ALIGNMENT

Align on:

* pagination
* filtering
* error format
* versioning

### Mental Anchor

> APIs are products.

==================================================

## COMMON MISTAKES (INTERVIEW FILTER)

* HTTP logic in components
* Multiple subscriptions
* No interceptors
* Inconsistent error handling
* Ignoring cancellation

==================================================

## REAL-WORLD USAGE (ENTERPRISE APPS)

* Secure dashboards
* Data-heavy tables
* Workflow APIs
* Partner integrations

==================================================

## 5-MINUTE REVISION SNAPSHOT

> HttpClient returns Observables
> Interceptors wrap requests
> Centralize errors
> switchMap cancels requests
> APIs are contracts

==================================================

## INTERVIEW PUNCHLINE (VERY STRONG)

> In Angular, I design HTTP communication as a contract-driven layer. I centralize cross-cutting concerns like authentication, error handling, retries, and logging using interceptors, leverage RxJS operators for cancellation and caching, and align closely with serviceBasedMNCLevel.backend APIs to ensure performance, security, and resilience.

==================================================

END — ANGULAR MODULE 8 (HTTP & INTERCEPTORS)

```

---

