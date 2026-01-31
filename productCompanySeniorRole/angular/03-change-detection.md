
---

````md
# Angular — Module 3: Change Detection & Performance
(Custom Notes | Senior Full-Stack Engineer | Barclays / Mastercard Level)

==================================================

## WHY THIS MODULE MATTERS (HARD SENIOR FILTER)

Most Angular performance problems come from:
❌ HTTP
❌ RxJS
❌ DOM size

They come from:
✅ Change detection misuse

Interviewers test:
- Do you know WHEN Angular runs CD?
- Do you know HOW to control it?
- Can you design performance-aware components?

==================================================


## CORE IDEA: CHANGE DETECTION EXPLAINS EVERYTHING

Angular uses **unidirectional change detection**:
- Parent → Child
- Tree-based traversal

### Mental Anchor
> Angular checks components, not variables.

==================================================


## WHAT TRIGGERS CHANGE DETECTION

Angular runs CD when:
- HTTP response arrives
- Promise resolves
- setTimeout fires
- DOM event occurs

Why?
Because **Zone.js intercepts async operations**.

### Mental Anchor
> Zone.js tells Angular *something changed*.

==================================================


## DEFAULT CHANGE DETECTION STRATEGY

```ts
@Component({
  selector: 'app-default',
  changeDetection: ChangeDetectionStrategy.Default
})
````

Behavior:

* Every async event
* Entire component tree is checked

Pros:

* Safe
* Easy

Cons:

* Can be expensive at scale

==================================================

## ONPUSH CHANGE DETECTION (SENIOR TOOL)

```ts
@Component({
  selector: 'app-fast',
  changeDetection: ChangeDetectionStrategy.OnPush
})
```

OnPush re-runs CD only when:

* @Input reference changes
* Event originates in component
* Observable emits (async pipe)
* Manually triggered

### Mental Anchor

> OnPush works with immutability.

==================================================

## IMMUTABILITY (REQUIRED FOR ONPUSH)

```ts
// ❌ Wrong
this.items.push(newItem);

// ✅ Correct
this.items = [...this.items, newItem];
```

Why?
Angular checks **references**, not deep equality.

### Senior Rule

> Mutate state → CD breaks silently.

==================================================

## ASYNC PIPE (PERFORMANCE HERO)

```html
<div *ngFor="let p of payments$ | async">
```

Benefits:

* Automatic subscription
* Automatic unsubscribe
* Triggers CD correctly

### Senior Rule

> Prefer async pipe over manual subscribe.

==================================================

## MANUAL CHANGE DETECTION (RARE BUT IMPORTANT)

```ts
constructor(private cdr: ChangeDetectorRef) {}

this.cdr.markForCheck();
```

Other APIs:

* detectChanges()
* detach()
* reattach()

### Use Case

* External libraries
* WebSockets
* Manual optimizations

### Mental Anchor

> Manual CD is a scalpel, not a hammer.

==================================================

## TRACKBY (LIST PERFORMANCE)

```html
<li *ngFor="let item of items; trackBy: trackById">
```

```ts
trackById(index: number, item: Item) {
  return item.id;
}
```

Prevents:

* DOM re-creation
* Unnecessary rendering

### Senior Insight

> trackBy saves DOM, not CD.

==================================================

## CHANGE DETECTION TREE (VISUAL)

```
AppComponent
 ├─ DashboardComponent
 │   ├─ ChartComponent
 │   └─ TableComponent
 └─ FooterComponent
```

* Default → all checked
* OnPush → selective

==================================================

## COMMON PERFORMANCE ANTI-PATTERNS

❌ Functions in templates

```html
{{ calculateTotal() }}
```

❌ Deep object mutation
❌ Too many bindings
❌ Default CD everywhere

### Senior Rule

> Templates must be pure.

==================================================

## ZONE-LESS ANGULAR (AWARENESS)

Modern Angular allows:

* disabling Zone.js
* manual CD control

Trade-offs:

* faster
* more complex

### Senior Line

> Zone-less Angular trades simplicity for control.

==================================================

## PERFORMANCE DEBUGGING TOOLS

* Angular DevTools
* Chrome Performance tab
* profiler timelines

What to look for:

* CD frequency
* component re-renders
* DOM churn

==================================================

## REAL-WORLD USAGE (ENTERPRISE SCALE)

* Large tables
* Dashboards
* Real-time updates
* High-frequency events

==================================================

## 5-MINUTE REVISION SNAPSHOT

> Zone triggers CD
> Default checks everything
> OnPush limits CD
> Immutability is mandatory
> Async pipe is gold

==================================================

## INTERVIEW PUNCHLINE (VERY STRONG)

> Angular change detection is a tree-based, unidirectional process triggered by Zone.js–intercepted async events. For performance-sensitive applications, I use OnPush change detection with immutable data, async pipes, and trackBy functions to minimize unnecessary checks and DOM updates.

==================================================

END — ANGULAR MODULE 3 (CHANGE DETECTION & PERFORMANCE)

```

---

