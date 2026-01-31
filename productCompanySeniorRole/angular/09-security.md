
---

````md
# Angular — Module 9: Security (XSS, Sanitization, Guards & Token Handling)
(Custom Notes | Senior Full-Stack Engineer | Barclays / Mastercard Level)

==================================================

## WHY THIS MODULE MATTERS (TRUST FILTER)

In fintech-scale applications:
- the frontend is a public attack surface
- users control input
- attackers control browsers

Interviewers test:
✅ Do you understand frontend security threats?
✅ Do you know what Angular protects by default?
✅ Can you design secure navigation and token handling?

==================================================


## CORE IDEA: FRONTEND SECURITY IS DEFENSIVE

Angular security focuses on:
- preventing injection attacks
- reducing blast radius
- enforcing UX-level authorization

### Mental Anchor
> Frontend security reduces risk; backend security enforces truth.

==================================================


## XSS (CROSS-SITE SCRIPTING) — MAIN THREAT

XSS occurs when:
- untrusted data is rendered as executable code

Example attack:
```html
<script>alert('hacked')</script>
````

### Types

* Stored XSS
* Reflected XSS
* DOM-based XSS

==================================================

## ANGULAR’S BUILT-IN XSS PROTECTION

Angular automatically:

* escapes HTML in templates
* sanitizes dangerous bindings

```html
<!-- Safe -->
<div>{{ userInput }}</div>
```

### Senior Insight

> Interpolation is safe by default.

==================================================

## DANGEROUS BINDINGS (WATCH THESE)

```html
<div [innerHTML]="content"></div>
```

Angular sanitizes this, but:

```ts
this.content = '<script>alert(1)</script>';
```

Script is stripped.

### Mental Anchor

> innerHTML is sanitized, not trusted.

==================================================

## DomSanitizer (USE CAREFULLY)

```ts
constructor(private sanitizer: DomSanitizer) {}

safeHtml = this.sanitizer.bypassSecurityTrustHtml(html);
```

⚠️ Danger:

* bypasses Angular’s security checks

### Senior Rule

> Never sanitize user input blindly.

==================================================

## TEMPLATE SECURITY BEST PRACTICES

* Prefer interpolation (`{{ }}`)
* Avoid `[innerHTML]`
* Never evaluate strings as code
* Treat all external data as untrusted

==================================================

## ROUTE GUARDS (UX-LEVEL AUTHORIZATION)

```ts
@Injectable()
export class AuthGuard implements CanActivate {
  canActivate() {
    return this.auth.isLoggedIn();
  }
}
```

Use cases:

* protect routes
* role-based navigation
* prevent unauthorized access

### Mental Anchor

> Guards control navigation, not data access.

==================================================

## ROLE-BASED ACCESS (FRONTEND)

```ts
canActivate(): boolean {
  return this.auth.hasRole('ADMIN');
}
```

### Senior Rule

> Frontend roles are hints, backend roles are law.

==================================================

## TOKEN HANDLING (JWT AWARENESS)

Common storage options:

* Memory (best)
* sessionStorage
* localStorage (risky)

### Trade-offs

* localStorage → XSS risk
* cookies → CSRF risk

### Senior Insight

> Token storage is a risk trade-off, not a perfect solution.

==================================================

## HTTP SECURITY HEADERS (AWARENESS)

Set by backend, but frontend must respect:

* Content-Security-Policy (CSP)
* X-Frame-Options
* CORS headers

### Mental Anchor

> Security is cross-layer cooperation.

==================================================

## AVOIDING SENSITIVE DATA LEAKS

❌ Logging tokens
❌ Storing secrets in frontend
❌ Hardcoding credentials

### Senior Rule

> Frontend should never know secrets.

==================================================

## COMMON SECURITY MISTAKES (INTERVIEW FILTER)

* Trusting frontend validation
* Using DomSanitizer everywhere
* Storing JWT in localStorage blindly
* Exposing admin routes without guards
* Ignoring CSP

==================================================

## REAL-WORLD USAGE (ENTERPRISE APPS)

* Banking dashboards
* Admin portals
* Partner-facing UIs
* Compliance-driven apps

==================================================

## 5-MINUTE REVISION SNAPSHOT

> Angular escapes templates
> XSS is main threat
> DomSanitizer is dangerous
> Guards protect navigation
> Backend enforces real security

==================================================

## INTERVIEW PUNCHLINE (VERY STRONG)

> Angular provides strong built-in XSS protection through template escaping and sanitization. As a senior engineer, I avoid dangerous bindings, use DomSanitizer only when absolutely necessary, enforce navigation security with guards, and treat frontend security as a defensive layer that complements backend enforcement.

==================================================

END — ANGULAR MODULE 9 (SECURITY)

```

---


