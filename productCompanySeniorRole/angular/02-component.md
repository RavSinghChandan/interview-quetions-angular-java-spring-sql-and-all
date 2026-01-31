
---

````md
# Angular — Module 2: Component Architecture & Lifecycle
(Custom Notes | Senior Full-Stack Engineer | Barclays / Mastercard Level)

==================================================

## WHY THIS MODULE MATTERS (SENIOR FILTER)

At senior level, interviewers are NOT testing:
❌ “Can you write a component?”

They ARE testing:
✅ Do you understand component lifecycles?
✅ Can you design maintainable component boundaries?
✅ Can you reason about performance and memory?
✅ Can you avoid UI architecture decay?

==================================================


## CORE IDEA: COMPONENTS ARE RUNTIME OBJECTS

Angular components are:
- instantiated classes
- managed by Angular runtime
- tracked for change detection
- destroyed deterministically

### Mental Anchor
> Components are objects with lifecycle, not HTML files.

==================================================


## BASIC COMPONENT STRUCTURE (RECAP)

```ts
@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent { }
````

Angular manages:

* instance creation
* template binding
* lifecycle hooks
* destruction

==================================================

## COMPONENT LIFECYCLE (COMPLETE FLOW)

Lifecycle sequence:

1. constructor
2. ngOnChanges
3. ngOnInit
4. ngDoCheck
5. ngAfterContentInit
6. ngAfterContentChecked
7. ngAfterViewInit
8. ngAfterViewChecked
9. ngOnDestroy

### Mental Anchor

> Lifecycle hooks reflect when Angular touches your component.

==================================================

## CONSTRUCTOR vs ngOnInit (COMMON CONFUSION)

### constructor

* Dependency injection only
* No bindings available

```ts
constructor(private service: PaymentService) {}
```

### ngOnInit

* Component initialized
* Inputs resolved

```ts
ngOnInit() {
  this.loadPayments();
}
```

### Senior Rule

> Never put business logic in constructors.

==================================================

## ngOnChanges (INPUT BOUNDARY CONTROL)

```ts
@Input() amount!: number;

ngOnChanges(changes: SimpleChanges) {
  if (changes['amount']) {
    this.recalculate();
  }
}
```

Triggered when:

* parent changes input reference

### Senior Insight

> ngOnChanges detects *input changes*, not internal mutations.

==================================================

## ngOnDestroy (MEMORY & RESOURCE SAFETY)

```ts
ngOnDestroy() {
  this.subscription.unsubscribe();
}
```

Used to clean:

* subscriptions
* timers
* event listeners

### Mental Anchor

> Every created resource must be destroyed.

==================================================

## SMART vs DUMB COMPONENTS (ARCHITECTURE PATTERN)

### Smart (Container)

* Fetches data
* Handles logic
* Talks to services

### Dumb (Presentational)

* Receives inputs
* Emits outputs
* No business logic

```ts
@Output() selected = new EventEmitter<Item>();
```

### Senior Rule

> Separate state management from presentation.

==================================================

## INPUT / OUTPUT CONTRACTS (EXPLICIT API)

```ts
@Input() payments: Payment[];
@Output() approve = new EventEmitter<number>();
```

Benefits:

* Reusability
* Testability
* Clear ownership

### Mental Anchor

> Components communicate via contracts, not shared state.

==================================================

## VIEW ENCAPSULATION (CSS SCOPING)

Modes:

* Emulated (default)
* None
* ShadowDom

```ts
encapsulation: ViewEncapsulation.Emulated
```

### Senior Insight

> Encapsulation prevents style leaks, not logic leaks.

==================================================

## CONTENT PROJECTION (ng-content)

```html
<app-card>
  <h2>Title</h2>
</app-card>
```

```html
<ng-content></ng-content>
```

Use case:

* layout components
* reusable shells

### Mental Anchor

> ng-content enables composition, not inheritance.

==================================================

## COMPONENT REUSABILITY RULES

Reusable components:

* have minimal inputs
* emit events
* avoid service calls
* avoid global state

### Anti-pattern

❌ Components fetching their own data

==================================================

## PERFORMANCE AWARENESS (PREVIEW)

* Every component participates in change detection
* Too many bindings → more checks
* Deep component trees → performance impact

### Senior Insight

> Architecture decisions affect performance long-term.

(Deep dive in Module 3.)

==================================================

## COMMON MISTAKES (INTERVIEW FILTER)

* Business logic in templates
* Services injected everywhere
* Ignoring ngOnDestroy
* Tight parent-child coupling
* Overusing shared state

==================================================

## REAL-WORLD USAGE (ENTERPRISE APPS)

* Dashboards
* Forms
* Tables
* Workflow UIs
* Admin panels

==================================================

## 5-MINUTE REVISION SNAPSHOT

> Components are runtime objects
> Lifecycle matters
> Logic goes in smart components
> Inputs/outputs define contracts
> Destroy what you create

==================================================

## INTERVIEW PUNCHLINE (STRONG)

> In Angular, I design components as runtime-managed objects with clear lifecycle awareness. I separate smart and presentational components, define explicit input/output contracts, manage resources carefully, and structure component trees for maintainability and performance.

==================================================

END — ANGULAR MODULE 2 (COMPONENT ARCHITECTURE & LIFECYCLE)

```

