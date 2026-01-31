

````md
# Angular — Module 5: Routing, Lazy Loading & Navigation
(Custom Notes | Senior Full-Stack Engineer | Barclays / Mastercard Level)

==================================================

## WHY THIS MODULE MATTERS (ENTERPRISE FILTER)

In large Angular apps:
- routing defines architecture
- lazy loading controls startup time
- guards enforce security
- navigation affects UX & performance

Interviewers test:
✅ Can you design scalable route structures?
✅ Do you understand lazy loading boundaries?
✅ Can you secure navigation paths?

==================================================


## CORE IDEA: ROUTER = APPLICATION SKELETON

Angular Router:
- maps URLs → components
- controls module loading
- enforces access rules

### Mental Anchor
> Routes define feature boundaries.

==================================================


## BASIC ROUTE CONFIGURATION

```ts
const routes: Routes = [
  { path: 'dashboard', component: DashboardComponent },
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' }
];
````

Registered via:

```ts
RouterModule.forRoot(routes);
```

### Senior Rule

> Root routes should be minimal.

==================================================

## ROUTER LIFECYCLE (HIGH-LEVEL)

Navigation flow:

1. URL change
2. Route matching
3. Guards execution
4. Resolver execution
5. Component activation
6. Change detection

### Mental Anchor

> Guards run before components exist.

==================================================

## LAZY LOADING (PERFORMANCE CORE)

```ts
{
  path: 'payments',
  loadChildren: () =>
    import('./payments/payments.module')
      .then(m => m.PaymentsModule)
}
```

Benefits:

* smaller initial bundle
* faster startup
* feature isolation

### Senior Rule

> Lazy load by feature, not by component.

==================================================

## STANDALONE + LAZY LOADING (MODERN)

```ts
{
  path: 'reports',
  loadComponent: () =>
    import('./reports/reports.component')
      .then(c => c.ReportsComponent)
}
```

### Senior Insight

> Standalone APIs reduce module overhead.

==================================================

## ROUTE GUARDS (SECURITY & FLOW CONTROL)

Types:

* CanActivate
* CanLoad
* CanDeactivate
* Resolve

```ts
canActivate(): boolean {
  return this.auth.isLoggedIn();
}
```

### Mental Anchor

> Guards prevent navigation, not rendering.

==================================================

## AUTHORIZATION VIA ROUTES

```ts
{
  path: 'admin',
  canActivate: [AdminGuard]
}
```

### Senior Rule

> UI security complements backend security, never replaces it.

==================================================

## ROUTE RESOLVERS (DATA BEFORE NAV)

```ts
resolve(): Observable<Data> {
  return this.service.loadData();
}
```

Used for:

* preload data
* avoid loading screens
* ensure data availability

### Senior Insight

> Resolvers delay navigation, not rendering.

==================================================

## PRELOADING STRATEGIES

```ts
RouterModule.forRoot(routes, {
  preloadingStrategy: PreloadAllModules
});
```

Trade-off:

* faster subsequent navigation
* more network usage

### Senior Rule

> Preload critical paths only.

==================================================

## ROUTER OUTLET & NESTED ROUTES

```html
<router-outlet></router-outlet>
```

Nested routes enable:

* layouts
* dashboards
* tabbed UIs

### Mental Anchor

> Router outlet defines UI composition.

==================================================

## NAVIGATION METHODS

```ts
this.router.navigate(['/payments', id]);
```

or

```html
<a [routerLink]="['/payments', id]">
```

### Senior Insight

> Prefer declarative navigation.

==================================================

## COMMON ROUTING MISTAKES (INTERVIEW FILTER)

* No lazy loading
* Overloaded root routes
* Business logic in guards
* Blocking resolvers
* Unprotected admin routes

==================================================

## REAL-WORLD USAGE (ENTERPRISE APPS)

* Feature modules
* Role-based navigation
* Secure dashboards
* Large admin panels

==================================================

## 5-MINUTE REVISION SNAPSHOT

> Routes define architecture
> Lazy loading improves startup
> Guards enforce access
> Resolvers preload data
> Preloading balances UX

==================================================

## INTERVIEW PUNCHLINE (STRONG)

> In Angular, I treat routing as an architectural boundary. I design minimal root routes, lazy-load feature modules, enforce authorization with guards, and use resolvers and preloading strategies to balance startup performance with user experience in large-scale applications.

==================================================

END — ANGULAR MODULE 5 (ROUTING & LAZY LOADING)

```

