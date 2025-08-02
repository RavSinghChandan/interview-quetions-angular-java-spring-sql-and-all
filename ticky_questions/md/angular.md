
---

## 🧠 ANGULAR – Topic-wise Tricky & Concept-Proving Questions (10+ per topic)

---

### 1️⃣ **Change Detection & Zones**

*(The brain of Angular apps. Almost every smart Angular interview tests this.)*

1. What is a Zone in Angular? How does `zone.js` patch async APIs?
2. What triggers change detection in Angular?
3. How does Angular know what changed? Does it use dirty checking?
4. How does `ChangeDetectionStrategy.OnPush` work internally?
5. Why does using `setTimeout()` or `Promise` still trigger change detection?
6. Can change detection be manually triggered? How and when should you do it?
7. What happens if you disable zones (`ngZone: 'noop'`)? How do you detect changes then?
8. How does Angular handle change detection for detached components?
9. How are `NgZone`, `ApplicationRef.tick()` and `ChangeDetectorRef.detectChanges()` related?
10. What’s the performance impact of change detection in large apps, and how do you optimize it?

---

### 2️⃣ **Dependency Injection (DI) System**

*(One of the deepest and most misunderstood parts of Angular)*

1. How does Angular’s hierarchical injector system work?
2. What happens when a service is provided in both `@Component` and `@NgModule`?
3. What’s the difference between `providedIn: 'root'`, `'any'`, and `'platform'`?
4. Can two components receive two different instances of the same service?
5. How does Angular detect circular dependencies in services?
6. What is the injector tree, and how does it impact memory and reusability?
7. How can you provide a different implementation for a token?
8. What are `InjectionToken`s and when should you use them?
9. What’s the difference between `@Injectable`, `@Inject`, and `Injector.create()`?
10. Can you override Angular built-in services (e.g., HttpClient)? How?

---

### 3️⃣ **Component Lifecycle & Rendering**

*(How Angular builds and updates your UI)*

1. What is the order of Angular lifecycle hooks and why does it matter?
2. What happens when an `@Input()` value changes — which hooks are triggered?
3. What’s the difference between `ngOnInit` and `ngAfterViewInit`?
4. Can you manipulate the DOM in `ngOnInit` safely?
5. What is `ngDoCheck` and how does it relate to custom change detection?
6. When is `ngOnDestroy` called? Can a component be destroyed and recreated?
7. What happens when a component is reloaded via routing? Does the old one get destroyed?
8. Can you write a directive that intercepts lifecycle hooks of a component?
9. What’s the difference between `@ViewChild()` and `@ContentChild()`?
10. How does Angular's rendering pipeline differ between Ivy and View Engine?

---

### 4️⃣ **Modules, Lazy Loading & Bootstrapping**

*(Crucial for app startup performance & structure)*

1. What is the difference between `declarations`, `imports`, and `exports` in NgModules?
2. How does Angular handle duplicate modules in imports? Is it tree-shaken?
3. How does lazy loading work under the hood (via Router)?
4. Can services be singleton across multiple lazy-loaded modules?
5. What’s the purpose of `forRoot()` and `forChild()` in module setup?
6. What happens if a module is both eagerly and lazily loaded?
7. How does Angular compile modules (AOT vs JIT)?
8. How do route guards work with lazy modules?
9. What’s the difference between feature module and shared module?
10. How does the `AppModule` bootstrap and render the root component?

---

### 5️⃣ **Routing & Navigation**

*(Real-world apps live and die on good routing design)*

1. How does Angular's `Router` handle navigation between routes internally?
2. What’s the difference between `RouterLink`, `navigate()`, and `navigateByUrl()`?
3. What is a route guard? What’s the sequence of execution for `CanActivate`, `Resolve`, etc.?
4. What happens if a route guard returns an observable?
5. How can you lazy-load a module on route and preload it later?
6. How does Angular decide when to reuse or recreate a component when navigating?
7. What is `RouterStateSnapshot`, and how can it be useful in guards?
8. How do route parameters differ from query parameters internally?
9. What is a wildcard route and how is route resolution prioritized?
10. What is route reuse strategy and when should you implement a custom one?

---

### 6️⃣ **Forms (Reactive & Template-Driven)**

*(Often asked to test understanding of control flow & validation)*

1. What is the internal difference between reactive forms and template-driven forms?
2. How does Angular detect validation status in real time?
3. What’s the difference between `FormControl`, `FormGroup`, and `FormArray`?
4. How are custom validators evaluated and triggered?
5. How can you apply async validators in reactive forms?
6. How does Angular associate `FormControl` with a template element?
7. What’s the role of `ControlValueAccessor` and how do you write one?
8. How does Angular patch vs set form values?
9. What’s the `updateOn` strategy and how does it affect change detection in forms?
10. Can forms survive route changes without state management?

---

### 7️⃣ **Ivy Renderer & AOT Compilation (Advanced)**

*(Deep internals, rarely understood but powerful)*

1. What is Ivy, and how is it different from ViewEngine?
2. How does Angular’s AOT compiler work?
3. What are `ngFactory` files and what purpose did they serve?
4. How are templates compiled into instructions (`ɵɵ` instructions)?
5. What’s the purpose of `ngcc` in Ivy migration?
6. What is tree-shaking and how does Ivy improve it?
7. How does Ivy enable local compilation and partial distribution?
8. Can Ivy components be used in non-Ivy libraries or vice versa?
9. What is `ɵɵdefineComponent` and where does it come from?
10. What is `ngtsc`, and how does it differ from `tsc`?

---

