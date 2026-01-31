
---

````md
# Angular — Module 6: RxJS & Async Programming
(Custom Notes | Senior Full-Stack Engineer | Barclays / Mastercard Level)

==================================================

## WHY THIS MODULE MATTERS (HARD FILTER)

In enterprise Angular apps:
- everything async flows through RxJS
- performance issues = RxJS misuse
- memory leaks = subscription misuse

Interviewers test:
✅ Do you understand Observables vs Promises?
✅ Can you compose async flows cleanly?
✅ Do you know when operators differ?
✅ Can you prevent leaks and race conditions?

==================================================


## CORE IDEA: RXJS IS A DATA FLOW TOOL

RxJS models **streams of data over time**.

Observable =
- lazy
- cancellable
- composable
- multi-value

### Mental Anchor
> Observables describe *when* and *how* data arrives.

==================================================


## OBSERVABLE vs PROMISE (INTERVIEW CLASSIC)

| Promise | Observable |
|------|------------|
| eager | lazy |
| single value | multiple values |
| not cancellable | cancellable |
| no operators | rich operators |

```ts
const p = fetch('/api');        // starts immediately
const o$ = http.get('/api');    // starts on subscribe
````

### Senior Line

> Promises represent a value; Observables represent a stream.

==================================================

## CREATING OBSERVABLES

```ts
of(1, 2, 3)
from([1, 2, 3])
fromEvent(button, 'click')
interval(1000)
```

### Mental Anchor

> Observable creation defines the source of truth.

==================================================

## SUBSCRIPTION (EXECUTION START)

```ts
const sub = obs$.subscribe(value => console.log(value));
```

Rules:

* Nothing happens before subscribe
* Unsubscribe stops execution

### Senior Rule

> Subscription = resource ownership.

==================================================

## PIPE & OPERATORS (COMPOSITION)

```ts
payments$
  .pipe(
    map(p => p.amount),
    filter(a => a > 1000)
  )
  .subscribe();
```

Operators are:

* pure
* chainable
* composable

==================================================

## MAP vs MERGEMAP vs SWITCHMAP (VERY IMPORTANT)

### map

Transforms value only

```ts
map(id => this.http.get(`/api/${id}`)) // ❌ nested observable
```

---

### mergeMap

Parallel inner subscriptions

```ts
mergeMap(id => this.http.get(`/api/${id}`))
```

---

### switchMap (MOST USED)

Cancels previous request

```ts
switchMap(term => this.search(term))
```

### Mental Anchor

> switchMap = latest request wins.

==================================================

## WHEN TO USE WHICH (INTERVIEW GOLD)

* User input / search → switchMap
* Independent requests → mergeMap
* Ordered execution → concatMap
* Simple transform → map

==================================================

## ERROR HANDLING (MUST KNOW)

```ts
pipe(
  catchError(err => of([]))
)
```

Rules:

* Errors terminate streams
* Handle errors inside pipe
* Avoid errors in subscribe

### Senior Insight

> Unhandled RxJS errors kill streams silently.

==================================================

## MULTICASTING & SHARING

```ts
shareReplay(1)
```

Use case:

* cache HTTP response
* avoid duplicate calls

### Mental Anchor

> HTTP Observables are cold by default.

==================================================

## SUBJECTS (USE CAREFULLY)

Types:

* Subject
* BehaviorSubject
* ReplaySubject

```ts
private state$ = new BehaviorSubject<State>(initial);
```

### Senior Rule

> Prefer Observables; use Subjects only as bridges.

==================================================

## ASYNC PIPE (BEST PRACTICE)

```html
<div *ngFor="let p of payments$ | async">
```

Benefits:

* auto subscribe
* auto unsubscribe
* triggers change detection

### Senior Rule

> Async pipe prevents memory leaks by design.

==================================================

## MEMORY LEAK PREVENTION (CRITICAL)

❌ Manual subscribe without unsubscribe

✅ Use:

* async pipe
* takeUntil
* first / take(1)

```ts
takeUntil(this.destroy$)
```

### Mental Anchor

> Unsubscribed streams = memory leaks.

==================================================

## COMBINING STREAMS

```ts
combineLatest([a$, b$])
forkJoin([a$, b$])
```

* combineLatest → continuous
* forkJoin → one-time completion

==================================================

## RXJS & HTTP IN ANGULAR

```ts
getPayments(): Observable<Payment[]> {
  return this.http.get<Payment[]>('/api/payments');
}
```

Best practices:

* services return Observables
* components subscribe via async pipe

==================================================

## COMMON RXJS ANTI-PATTERNS (INTERVIEW FILTER)

* Nested subscribes
* Manual state mutation
* Subjects everywhere
* No error handling
* Forgetting unsubscribe

==================================================

## REAL-WORLD USAGE (ENTERPRISE APPS)

* Search & filters
* Real-time dashboards
* Form streams
* API orchestration
* Event handling

==================================================

## 5-MINUTE REVISION SNAPSHOT

> Observables are lazy
> Subscribe starts execution
> switchMap cancels old work
> Async pipe is safest
> Unsubscribe prevents leaks

==================================================

## INTERVIEW PUNCHLINE (VERY STRONG)

> RxJS is the backbone of async programming in Angular. I design observable pipelines using the right flattening operators, handle errors explicitly, avoid nested subscriptions, and rely on async pipes and proper unsubscription patterns to ensure performance and memory safety in large-scale applications.

==================================================

END — ANGULAR MODULE 6 (RXJS & ASYNC PROGRAMMING)

```

---
