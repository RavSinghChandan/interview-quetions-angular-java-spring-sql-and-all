Perfect 👍 You’re asking for **Section 3 → Components & Data Binding** in `.md` format (continuing the Angular handbook).

Here’s the full structured content:

---

````markdown
# 3. Components & Data Binding in Angular

Components are the **fundamental building blocks** of an Angular application.  
They control a **portion of the UI** and encapsulate **template, logic, and styling**.  

---

## 3.1 Component Basics

Each Angular component has:
1. **TypeScript Class** → defines logic (`@Component` decorator).
2. **Template (HTML)** → defines view.
3. **Styles (CSS/SCSS)** → defines design.

### Example: Simple Component

```ts
import { Component } from '@angular/core';

@Component({
  selector: 'app-hello',
  templateUrl: './hello.component.html',
  styleUrls: ['./hello.component.css']
})
export class HelloComponent {
  title = 'Welcome to Angular!';
}
````

---

## 3.2 Component Lifecycle Hooks

Angular provides hooks to tap into **component lifecycle events**:

* `ngOnInit()` → called once when component initializes.
* `ngOnChanges()` → called when input properties change.
* `ngDoCheck()` → detect custom changes.
* `ngAfterViewInit()` → after view loads.
* `ngOnDestroy()` → before component is destroyed.

These hooks help in **resource management, API calls, cleanup, etc.**

---

## 3.3 Data Binding in Angular

Angular supports **4 types of data binding**:

### 1. **Interpolation** (`{{ }}`)

* One-way binding (component → template).

```html
<h1>{{ title }}</h1>
```

### 2. **Property Binding** (`[property]`)

* Binds **DOM properties** to component values.

```html
<input [value]="title">
```

### 3. **Event Binding** (`(event)`)

* Binds **DOM events → component methods**.

```html
<button (click)="onClick()">Click Me</button>
```

```ts
onClick() {
  alert('Button clicked!');
}
```

### 4. **Two-way Binding** (`[(ngModel)]`)

* Combines **property + event binding**.
* Requires **FormsModule**.

```html
<input [(ngModel)]="username">
<p>Hello, {{ username }}</p>
```

---

## 3.4 Component Communication

* **Parent → Child**: Using `@Input()` decorator.
* **Child → Parent**: Using `@Output()` + EventEmitter.
* **Shared Service**: Common state management via services.

---

## ✅ Summary

* Components are the **heart of Angular apps**.
* Lifecycle hooks provide **control over initialization, updates, and destruction**.
* Angular offers **4 binding techniques**: Interpolation, Property, Event, and Two-way binding.
* Component communication ensures **scalability and modularity** in applications.

```

---

✅ Section 3 completed.  

👉 Do you want me to move ahead with **Section 4 → Directives** in the same `.md` format?
```
