
---


# Angular — Module 7: State Management (NgRx, ComponentStore & Design Trade-offs)
(Custom Notes | Senior Full-Stack Engineer | Barclays / Mastercard Level)

==================================================

## WHY THIS MODULE MATTERS (ENTERPRISE FILTER)

In small apps:
- local state works

In large apps:
- state becomes the system

Interviewers test:
✅ Can you decide *where* state should live?
✅ Can you avoid over-engineering?
✅ Do you understand trade-offs between simplicity and scalability?

==================================================


## CORE IDEA: STATE IS DATA OVER TIME

State includes:
- UI state (loading, selection)
- Business state (payments, users)
- Session state (auth, roles)

### Mental Anchor
> State problems grow faster than code size.

==================================================


## STATE LOCATION OPTIONS (BIG PICTURE)

1. Component local state
2. Shared service state
3. Global store (NgRx)
4. ComponentStore (scoped store)

### Senior Rule
> Start local, grow deliberately.

==================================================


## COMPONENT LOCAL STATE (DEFAULT)

```ts
payments: Payment[] = [];
loading = false;
````

Use when:

* state is short-lived
* not shared
* simple UI logic

### Limitation

* hard to share
* hard to debug globally

==================================================

## SHARED SERVICE STATE (COMMON STEP)

```ts
@Injectable({ providedIn: 'root' })
export class PaymentStateService {
  payments$ = new BehaviorSubject<Payment[]>([]);
}
```

Pros:

* simple sharing
* easy to implement

Cons:

* hidden mutations
* difficult to trace updates

### Senior Insight

> Service-based state scales until it doesn’t.

==================================================

## WHEN YOU NEED A STORE

Use a store when:

* many components share state
* updates come from many places
* debugging matters
* time-travel / traceability helps

### Mental Anchor

> Global problems need global visibility.

==================================================

## NGRX — CORE CONCEPTS

NgRx is based on:

* Redux principles
* Unidirectional data flow
* Immutability

Flow:

```
Component → Action → Reducer → Store → Selector → View
```

==================================================

## ACTIONS (INTENT)

```ts
export const loadPayments =
  createAction('[Payment] Load');
```

Actions:

* describe *what happened*
* not *how*

### Senior Rule

> Actions are events, not commands.

==================================================

## REDUCERS (STATE TRANSITION)

```ts
const reducer = createReducer(
  initialState,
  on(loadSuccess, (state, { data }) => ({
    ...state,
    payments: data
  }))
);
```

Rules:

* pure functions
* immutable updates
* no side effects

==================================================

## SELECTORS (READ MODEL)

```ts
export const selectPayments =
  createSelector(selectState, s => s.payments);
```

Benefits:

* memoization
* performance
* separation of read logic

==================================================

## EFFECTS (SIDE EFFECTS)

```ts
load$ = createEffect(() =>
  this.actions$.pipe(
    ofType(loadPayments),
    switchMap(() => this.api.getPayments())
  )
);
```

Use effects for:

* HTTP calls
* navigation
* logging

### Senior Insight

> Reducers change state, effects talk to the world.

==================================================

## COMPONENTSTORE (SCOPED STATE)

```ts
@Injectable()
export class PaymentStore extends ComponentStore<State> {
  readonly payments$ = this.select(s => s.payments);
}
```

Use when:

* state belongs to a feature
* avoid global complexity
* need reactive state

### Senior Rule

> ComponentStore is NgRx without global cost.

==================================================

## IMMUTABILITY (NON-NEGOTIABLE)

```ts
// ❌ Wrong
state.items.push(item);

// ✅ Correct
items: [...state.items, item]
```

Why:

* change detection
* predictability
* selector memoization

==================================================

## PERFORMANCE CONSIDERATIONS

* Large stores increase memory
* Too many actions = noise
* Poor selectors = re-renders

### Senior Insight

> State size affects UI performance.

==================================================

## WHEN NOT TO USE NGRX (IMPORTANT)

Avoid NgRx when:

* app is small
* state is simple
* team is inexperienced
* time-to-market matters more

### Senior Line

> NgRx is powerful — not mandatory.

==================================================

## COMMON STATE MANAGEMENT MISTAKES (INTERVIEW FILTER)

* NgRx everywhere
* Mutating state
* Business logic in components
* No selectors
* Debugging via console.log

==================================================

## REAL-WORLD USAGE (ENTERPRISE APPS)

* Authentication state
* User profiles
* Dashboard data
* Cross-feature workflows

==================================================

## 5-MINUTE REVISION SNAPSHOT

> State grows with app
> Start local
> Stores add visibility
> NgRx enforces discipline
> ComponentStore scopes complexity

==================================================

## INTERVIEW PUNCHLINE (VERY STRONG)

> In Angular, I choose state management strategies deliberately. I start with local or service-based state, introduce ComponentStore for feature-level complexity, and adopt NgRx only when global visibility, traceability, and scalability justify its overhead.

==================================================

END — ANGULAR MODULE 7 (STATE MANAGEMENT)

```

---
