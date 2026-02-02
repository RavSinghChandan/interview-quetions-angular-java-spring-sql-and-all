
---


# Angular — Module 4: Dependency Injection & Providers
(Custom Notes | Senior Full-Stack Engineer | Barclays / Mastercard Level)

==================================================

## WHY THIS MODULE MATTERS (SENIOR FILTER)

Most Angular developers:
- use services
- inject them
- move on

Senior Angular engineers:
- control **scope**
- control **lifecycle**
- control **memory**
- control **testability**

Interviewers test:
✅ Do you understand Angular’s DI hierarchy?
✅ Can you reason about provider scope and instantiation?
✅ Can you avoid accidental shared state?

==================================================


## CORE IDEA: ANGULAR DI IS HIERARCHICAL

Angular does NOT have a single global injector.

Instead, it has a **tree of injectors**:
- Platform injector
- Root injector
- Module injector
- Component injectors

### Mental Anchor
> Where you provide a service determines how many instances exist.

==================================================


## BASIC SERVICE DEFINITION

```ts
@Injectable({ providedIn: 'root' })
export class PaymentService {
  getPayments() {}
}
````

What this means:

* Service is registered in **root injector**
* Singleton across the app
* Tree-shakable

### Senior Rule

> `providedIn: 'root'` is the default for shared services.

==================================================

## PROVIDER SCOPES (VERY IMPORTANT)

### Root-level (Singleton)

```ts
@Injectable({ providedIn: 'root' })
```

* One instance
* Shared state
* App-wide lifecycle

---

### Module-level

```ts
@NgModule({
  providers: [AuditService]
})
```

* One instance per module
* Dangerous with lazy modules

---

### Component-level

```ts
@Component({
  providers: [LocalCacheService]
})
```

* New instance per component
* Destroyed with component

### Mental Anchor

> Component providers = controlled isolation.

==================================================

## HIERARCHICAL RESOLUTION (HOW ANGULAR DECIDES)

When Angular injects a dependency:

1. Check current component injector
2. Walk up component tree
3. Check module injector
4. Check root injector

### Senior Insight

> Child injectors override parent providers.

==================================================

## WHEN COMPONENT-SCOPED SERVICES ARE USEFUL

Use component providers for:

* per-component state
* wizard flows
* temporary caches
* form orchestration

Example:

```ts
@Component({
  providers: [FormStateService]
})
```

### Senior Rule

> Stateful UI logic belongs close to the component.

==================================================

## SINGLETON PITFALLS (INTERVIEW FAVORITE)

❌ Using singleton services for UI state
❌ Forgetting lazy module boundaries
❌ Unexpected shared mutations

### Mental Anchor

> Singleton services = shared memory.

==================================================

## INJECTION TOKENS (ADVANCED BUT IMPORTANT)

Used when:

* injecting primitives
* multiple implementations
* configuration objects

```ts
export const API_URL = new InjectionToken<string>('API_URL');
```

```ts
providers: [{ provide: API_URL, useValue: 'https://api.example.com' }]
```

### Senior Insight

> InjectionToken avoids string-based DI bugs.

==================================================

## MULTI-PROVIDERS

```ts
providers: [
  { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
]
```

Use cases:

* interceptors
* plugins
* extensible pipelines

### Mental Anchor

> Multi-providers allow composition.

==================================================

## USECLASS vs USEVALUE vs USEFACTORY

```ts
{ provide: Logger, useClass: FileLogger }
{ provide: API_URL, useValue: '...' }
{ provide: Config, useFactory: configFactory }
```

### Senior Rule

> Factories are best for environment-based logic.

==================================================

## DEPENDENCY INJECTION & TESTING

DI enables:

* mocking
* swapping implementations
* isolation

```ts
TestBed.configureTestingModule({
  providers: [{ provide: PaymentService, useClass: MockPaymentService }]
});
```

### Mental Anchor

> Good DI = easy testing.

==================================================

## MEMORY & LIFECYCLE IMPLICATIONS

* Root services live for app lifetime
* Component services are GC’d on destroy
* Overusing root services increases memory footprint

### Senior Insight

> DI decisions affect memory and leaks.

==================================================

## COMMON MISTAKES (INTERVIEW FILTER)

* Everything in root
* UI state in singleton services
* Ignoring lazy module injectors
* Using strings instead of InjectionToken
* Not understanding provider overrides

==================================================

## REAL-WORLD USAGE (ENTERPRISE APPS)

* Authentication services
* Feature-scoped logic
* Wizard state handling
* Config-driven behavior

==================================================

## 5-MINUTE REVISION SNAPSHOT

> DI is hierarchical
> Provider scope controls instances
> Root = singleton
> Component providers isolate state
> InjectionToken avoids collisions

==================================================

## INTERVIEW PUNCHLINE (VERY STRONG)

> Angular uses a hierarchical dependency injection system where provider scope determines service lifetime and instance sharing. As a senior engineer, I deliberately choose provider scopes to control state isolation, memory usage, and testability rather than relying blindly on root-level singletons.

==================================================

END — ANGULAR MODULE 4 (DEPENDENCY INJECTION & PROVIDERS)

```

---


