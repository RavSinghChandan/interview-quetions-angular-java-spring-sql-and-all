
---

````md
# Angular — Module 10: Performance, Build & Optimization
(Custom Notes | Senior Full-Stack Engineer | Barclays / Mastercard Level)

==================================================

## WHY THIS MODULE MATTERS (PRODUCTION FILTER)

In large Angular apps, issues are rarely about:
❌ correctness

They are about:
✅ startup time
✅ bundle size
✅ runtime performance
✅ memory leaks
✅ slow change detection

Interviewers test whether you can **optimize without guessing**.

==================================================


## CORE IDEA: PERFORMANCE IS A DESIGN OUTCOME

Angular performance depends on:
- architecture choices
- change detection strategy
- bundle composition
- runtime allocations

### Mental Anchor
> You don’t “add” performance later — you design for it.

==================================================


## AOT vs JIT COMPILATION

### JIT (Development)
- Compiles templates in browser
- Faster builds
- Slower startup

### AOT (Production)
- Compiles at build time
- Smaller bundles
- Faster startup
- Better security

```bash
ng build --configuration production
````

### Senior Rule

> Always use AOT in production.

==================================================

## TREE SHAKING (BUNDLE SIZE CORE)

Tree shaking removes:

* unused code
* unused providers
* unused modules

Requirements:

* ES modules
* no side-effect imports

### Senior Insight

> Standalone APIs improve tree shaking.

==================================================

## LAZY LOADING (REVISITED)

Lazy loading reduces:

* initial JS payload
* time-to-interactive

```ts
loadChildren: () =>
  import('./reports/reports.module')
    .then(m => m.ReportsModule)
```

### Senior Rule

> Lazy load by feature boundaries.

==================================================

## CHANGE DETECTION OPTIMIZATION (RECAP)

Key levers:

* OnPush strategy
* immutability
* async pipe
* trackBy

### Mental Anchor

> Less CD = less CPU = faster UI.

==================================================

## LIST & DOM OPTIMIZATION

### trackBy

```html
*ngFor="let item of items; trackBy: trackById"
```

### Virtual Scrolling

```html
<cdk-virtual-scroll-viewport>
```

Use when:

* large lists
* data-heavy tables

==================================================

## BUNDLE ANALYSIS (MEASURE FIRST)

```bash
ng build --stats-json
npx webpack-bundle-analyzer dist/stats.json
```

Find:

* large dependencies
* duplicate libraries
* unused code

### Senior Rule

> Measure bundle size, don’t assume.

==================================================

## RUNTIME PERFORMANCE PITFALLS

❌ Functions in templates
❌ Heavy pipes
❌ Excessive bindings
❌ Deep component trees

### Senior Insight

> Templates should be cheap to evaluate.

==================================================

## MEMORY MANAGEMENT

Common memory leaks:

* forgotten subscriptions
* event listeners
* setInterval
* long-lived services

### Prevention

* async pipe
* ngOnDestroy
* takeUntil

==================================================

## BUILD CONFIGURATION (ENTERPRISE READY)

* environment-based configs
* file replacements
* optimization flags

```ts
fileReplacements: [
  { replace: 'env.ts', with: 'env.prod.ts' }
]
```

==================================================

## CI/CD CONSIDERATIONS

* deterministic builds
* reproducible bundles
* build-time checks
* lint + test gates

### Senior Rule

> Frontend builds must be boring and predictable.

==================================================

## PERFORMANCE PROFILING TOOLS

* Chrome DevTools
* Angular DevTools
* Lighthouse
* Web Vitals

Metrics:

* TTI
* FPS
* memory usage

==================================================

## COMMON PERFORMANCE MISTAKES (INTERVIEW FILTER)

* No lazy loading
* Default CD everywhere
* Ignoring bundle size
* No profiling
* Overusing libraries

==================================================

## REAL-WORLD USAGE (ENTERPRISE APPS)

* Dashboards with live data
* High-traffic admin panels
* Data-heavy workflows
* Compliance UIs

==================================================

## 5-MINUTE REVISION SNAPSHOT

> AOT for prod
> Tree shaking matters
> Lazy loading saves startup
> OnPush saves CPU
> Measure before tuning

==================================================

## INTERVIEW PUNCHLINE (VERY STRONG)

> Angular performance is driven by build-time optimization, bundle composition, and runtime design choices. As a senior engineer, I use AOT compilation, lazy loading, OnPush change detection, and profiling tools to minimize startup cost, reduce memory usage, and maintain consistent UI performance at scale.

==================================================

END — ANGULAR MODULE 10 (PERFORMANCE & BUILD)

```

---

