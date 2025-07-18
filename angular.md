# Angular Interview Questions for Senior Java Backend Developers (Full Stack)

This document contains the most frequently asked Angular interview questions, curated for senior Java backend developers transitioning to full-stack roles. Questions are organized by topic and include those repeatedly asked in interviews by companies like **TCS, Infosys, Cognizant, Capgemini**, etc., along with additional critical questions to ensure comprehensive coverage for senior-level interviews.

---

## 1. Components

### Basic Questions

1. What is a component in Angular? _(Asked in TCS, Infosys)_
2. How do you create a component using Angular CLI? _(Asked in Capgemini)_
3. What are the different parts of an Angular component? _(Asked in Wipro)_

### Intermediate Questions

1. How does component communication happen in Angular? _(Asked in Accenture)_
2. What is the difference between `@Input()` and `@Output()`? _(Asked in TCS)_
3. Explain the lifecycle hooks of a component. _(Asked in Infosys)_

### Advanced Questions

1. How does OnPush change detection strategy work? _(Asked in Cognizant)_
2. How do you use `ViewChild` to interact with a child component or DOM element? _(Asked in TCS Digital)_

### Situational / Real-world Questions

1. A component is causing performance bottlenecks in production, how would you debug and resolve it? _(Asked in HCL)_
2. You need to create a reusable component library for a multi-team project. How would you design and share it? _(Asked in Infosys)_

---

## 2. Services and Dependency Injection

### Basic Questions

1. What is a service in Angular? _(Asked in TCS)_
2. How do you create and inject a service? _(Asked in Capgemini)_

### Intermediate Questions

1. What is the difference between providing a service in root vs component level? _(Asked in Wipro)_

### Advanced Questions

1. How do you ensure a service is a singleton across lazy-loaded modules? _(Asked in Cognizant)_

### Situational / Real-world Questions

1. You need to use different service implementations in different environments (dev/prod). How would you design it? _(Asked in Deloitte)_

---

## 3. Routing and Navigation

### Basic Questions

1. What is Angular Router and why is it used? _(Asked in TCS, Capgemini)_
2. How do you configure routes in Angular? _(Asked in Infosys)_
3. What is a `router-outlet` and how does it work? _(Asked in Accenture)_

### Intermediate Questions

1. What is the difference between `ActivatedRoute` and `Router`? _(Asked in Wipro)_
2. How do you configure child routes and nested routes? _(Asked in HCL)_
3. How do you implement lazy loading with feature modules? _(Asked in Tech Mahindra)_
4. How can you use route guards like `CanActivate` and `CanDeactivate`? _(Asked in Persistent)_

### Advanced Questions

1. What is the purpose of route resolvers and how do you use them? _(Asked in Cognizant)_
2. How do you implement server-side rendering (SSR) with Angular Universal for routing? _(Asked in Accenture)_

### Tough Questions

1. How would you implement role-based dynamic routing in a large enterprise app? _(Asked in Amazon)_
2. How do you optimize route loading and transitions for performance? _(Asked in Oracle)_
3. Explain how you’d secure Angular routes with authentication and authorization workflows. _(Asked in Deloitte)_

### Situational / Real-world Questions

1. Your enterprise app has hundreds of routes and nested modules. How would you manage routing configuration and scalability? _(Asked in IBM)_

---

## 4. Directives

### Basic Questions

1. What is a directive in Angular? _(Asked in TCS)_
2. What is the difference between structural and attribute directives? _(Asked in Wipro)_
3. How do you use `*ngIf` and `*ngFor` in templates? _(Asked in Infosys)_

### Intermediate Questions

1. How do you create a custom attribute directive? _(Asked in Capgemini)_

### Advanced Questions

1. How do you build a structural directive like `*ngIf` from scratch? _(Asked in Accenture)_

### Tough Questions

1. You need to conditionally render a section of the DOM based on complex business logic involving observables. How would you build a custom directive for it? _(Asked in Cognizant)_

### Situational / Real-world Questions

1. You are building a dynamic form with repeated sections and visibility toggles. How would you design and use directives to reduce template clutter? _(Asked in HCL)_

---

## 5. Pipes

### Basic Questions

1. What is a pipe in Angular? _(Asked in Capgemini)_
2. What are some commonly used built-in pipes? _(Asked in Infosys)_

### Intermediate Questions

1. How do you create a custom pipe in Angular? _(Asked in TCS)_

### Advanced Questions

1. How do impure pipes affect performance and change detection? _(Asked in Cognizant)_

### Tough Questions

1. How would you implement a pipe to format and filter data from a live stream or observable? _(Asked in HCL)_

---

## 6. Forms

### Basic Questions

1. What are the two types of forms supported by Angular? _(Asked in TCS, Capgemini)_
2. How do you create a template-driven form in Angular? _(Asked in Infosys)_

### Intermediate Questions

1. How do you build a reactive form in Angular? _(Asked in Infosys)_
2. How can you validate form inputs using both built-in and custom validators? _(Asked in HCL)_

### Advanced Questions

1. How do you create and use custom form validators? _(Asked in Cognizant)_
2. How do you integrate Angular Material components with reactive forms? _(Asked in Wipro)_

### Tough Questions

1. How would you build a reusable form component with reactive bindings? _(Asked in Deloitte)_

### Situational / Real-world Questions

1. You’re integrating Angular forms with a Spring Boot backend. How would you ensure data model compatibility and error handling? _(Asked in TCS Digital)_

---

## 7. RxJS and Observables

### Basic Questions

1. What is RxJS and why is it used in Angular? _(Asked in Capgemini, Infosys)_
2. What is an Observable in Angular? _(Asked in Wipro)_
3. How is Observable different from Promise? _(Asked in TCS)_

### Intermediate Questions

1. What are some commonly used RxJS operators and their use cases? _(Asked in Cognizant)_
2. What is the difference between `map`, `switchMap`, `mergeMap`, and `concatMap`? _(Asked in TCS Digital)_
3. What is the difference between `Subject`, `BehaviorSubject`, and `ReplaySubject`? _(Asked in Infosys)_
4. How do you cancel subscriptions in Angular and why is it important? _(Asked in HCL)_

### Advanced Questions

1. What is `takeUntil` and how does it help in unsubscribing? _(Asked in HCL)_
2. How do you implement WebSocket integration with RxJS in Angular? _(Asked in Deloitte)_

### Tough Questions

1. You need to fetch data from multiple endpoints in sequence and in parallel. How would you implement this using RxJS? _(Asked in Deloitte)_

### Situational / Real-world Questions

1. You’re tasked with implementing a type-ahead search box. How would RxJS help? _(Asked in Accenture)_

---

## 8. Change Detection

### Basic Questions

1. What is change detection in Angular? _(Asked in Infosys, Capgemini)_
2. What triggers change detection in Angular? _(Asked in TCS)_

### Intermediate Questions

1. What are the two types of change detection strategies in Angular? _(Asked in Wipro)_
2. What are the benefits of OnPush change detection strategy? _(Asked in Infosys)_

### Advanced Questions

1. What happens if you detach a component from change detection? _(Asked in Cognizant)_

### Tough Questions

1. A component rerenders unnecessarily on every keystroke. How do you identify and fix this? _(Asked in HCL)_

---

## 9. State Management (NgRx, BehaviorSubject, etc.)

### Basic Questions

1. What is state management in Angular and why is it important? _(Asked in Infosys, Capgemini)_
2. What is a `BehaviorSubject` and how is it used for state sharing? _(Asked in TCS)_

### Intermediate Questions

1. What is NgRx and how does it fit into Angular ecosystem? _(Asked in Cognizant)_

### Advanced Questions

1. How would you architect an Angular app using NgRx for scalability? _(Asked in HCL)_

### Tough Questions

1. How do you design a state system that syncs real-time data from WebSocket APIs with the UI? _(Asked in Deloitte)_

---

## 10. HTTP Client and Interceptors

### Basic Questions

1. What is HttpClient in Angular and how is it different from the legacy Http module? _(Asked in TCS, Capgemini)_
2. How do you make a GET/POST request using HttpClient? _(Asked in Infosys)_

### Intermediate Questions

1. What is an HttpInterceptor and how does it work in Angular? _(Asked in Wipro)_
2. How do you use an interceptor to attach auth tokens to every request? _(Asked in Cognizant)_

### Advanced Questions

1. How do you create multiple interceptors in Angular and manage their order of execution? _(Asked in HCL)_

### Tough Questions

1. You have multiple environments (dev, staging, prod) — how would you dynamically set the base API URL using interceptors or tokens? _(Asked in Deloitte)_
2. Your API server returns a 401 when token expires. How would you refresh the token and retry failed requests? _(Asked in Cognizant)_

### Situational / Real-world Questions

1. How do you handle CORS issues when integrating Angular with a Spring Boot backend? _(Asked in TCS)_

---

## 11. Module Architecture (Feature Modules, Lazy Loading)

### Basic Questions

1. What is a module in Angular and what is the purpose of `@NgModule`? _(Asked in TCS, Capgemini)_
2. What is the difference between root and feature modules? _(Asked in Infosys)_

### Intermediate Questions

1. What is lazy loading in Angular and how does it improve performance? _(Asked in Wipro)_

### Advanced Questions

1. How do you optimize module loading in a large-scale Angular application? _(Asked in Cognizant)_

### Tough Questions

1. Your app is slow due to large bundles. How do you analyze and reduce the initial module load? _(Asked in HCL)_

---

## 12. Testing (Unit Testing & Integration Testing)

### Basic Questions

1. What is unit testing in Angular and which tools are used for it? _(Asked in TCS, Infosys)_
2. What is Jasmine and what is Karma in Angular testing? _(Asked in Capgemini)_

### Intermediate Questions

1. What are test beds (`TestBed`) and how are they used in Angular testing? _(Asked in Wipro)_

### Advanced Questions

1. How do you test a component that uses `ngOnInit` with service calls? _(Asked in Cognizant)_
2. How do you test Angular services with HTTP dependencies? _(Asked in TCS Digital)_

### Tough Questions

1. Your app relies on interceptors and external APIs. How would you mock and test these requests? _(Asked in HCL)_

### Situational / Real-world Questions

1. You inherit a codebase with no tests. How would you begin adding them without slowing delivery? _(Asked in Infosys)_

---

## 13. Animations

### Intermediate Questions

1. What are animation metadata objects? _(Asked in Infosys)_

### Tough Questions

1. How do you animate a list of items using `query()` and `stagger()`? _(Asked in TCS Digital)_
2. How do you test Angular animations? _(Asked in L&T Infotech)_

---

## 14. Internationalization (i18n)

### Basic Questions

1. What is internationalization (i18n) in Angular and why is it important? _(Asked in Infosys)_

### Intermediate Questions

1. How do you configure and build an Angular application for multiple locales? _(Asked in Capgemini)_

### Advanced Questions

1. How would you build a custom translation loader for remote translation files? _(Asked in Cognizant)_

### Tough Questions

1. You need to support right-to-left (RTL) layouts in your Angular app. How would you approach this? _(Asked in Deloitte)_

---

## 15. Performance Optimization

### Basic Questions

1. What are some common causes of performance issues in Angular apps? _(Asked in Infosys)_
2. How do you reduce the bundle size of an Angular app? _(Asked in Capgemini)_

### Intermediate Questions

1. How does the OnPush change detection strategy work and when should you use it? _(Asked in Deloitte)_
2. How do trackBy functions improve the performance of `*ngFor`? _(Asked in TCS)_

### Advanced Questions

1. How do you use service workers to improve performance in Angular PWAs? _(Asked in Tech Mahindra)_

### Tough Questions

1. You have a dashboard with many real-time charts and metrics. How do you ensure it performs smoothly on low-end devices? _(Asked in Cognizant)_

---

## 16. Angular 14 to 17 Features & Version Differences

### Basic Questions

1. What are the key features introduced in Angular 14? _(Asked in Infosys)_
2. What is a standalone component? _(Asked in Cognizant)_

### Intermediate Questions

1. What are the benefits of standalone components over NgModules? _(Asked in Tech Mahindra)_

### Advanced Questions

1. What is hydration in Angular 17 and why is it important? _(Asked in Accenture)_

### Tough Questions

1. How would you refactor a legacy app with modules to use Angular 17’s standalone structure? _(Asked in Publicis Sapient)_

### Situational / Real-world Questions

1. A client demands better SEO and faster page loads. How would you use Angular 17’s hydration and SSR? _(Asked in Capgemini)_

---

## 17. Angular Universal (Server-Side Rendering)

### Basic Questions

1. What is Angular Universal and why is it used? _(Asked in Infosys)_

### Intermediate Questions

1. How do you configure Angular Universal in an existing Angular application? _(Asked in Cognizant)_

### Advanced Questions

1. How do you debug hydration issues in Angular Universal? _(Asked in Accenture)_

### Tough Questions

1. How would you optimize server-side rendering performance in a large application? _(Asked in Deloitte)_

### Situational / Real-world Questions

1. Your app needs to support SEO-friendly dynamic content. How would you implement Angular Universal to achieve this? _(Asked in TCS)_

---

## 18. Angular CLI and Build Optimization

### Basic Questions

1. What are the key Angular CLI commands for generating components, services, and modules? _(Asked in TCS)_

### Intermediate Questions

1. How do you use Angular CLI to optimize production builds? _(Asked in Capgemini)_

### Advanced Questions

1. How do you configure differential script loading in Angular CLI for legacy browsers? _(Asked in Cognizant)_

### Tough Questions

1. How would you analyze and optimize bundle size using Angular CLI tools? _(Asked in HCL)_

---

## 19. Security in Angular

### Basic Questions

1. How does Angular prevent Cross-Site Scripting (XSS) attacks? _(Asked in Infosys)_

### Intermediate Questions

1. How do you use `DomSanitizer` to safely handle dynamic content? _(Asked in Wipro)_

### Advanced Questions

1. How would you implement CSRF protection in an Angular app with a Spring Boot backend? _(Asked in Deloitte)_

### Tough Questions

1. How would you secure an Angular app against template injection attacks? _(Asked in Cognizant)_

---

## 20. Error Handling and Debugging

### Basic Questions

1. What are best practices for error handling in Angular applications? _(Asked in TCS)_

### Intermediate Questions

1. How do you implement a global error handling mechanism for HTTP calls? _(Asked in Capgemini)_

### Advanced Questions

1. How do you debug performance issues in an Angular app in production? _(Asked in HCL)_

### Tough Questions

1. A user reports intermittent UI errors that don’t occur locally. How would you identify and resolve this? _(Asked in Deloitte)_

---

## 21. Angular Elements

### Basic Questions

1. What are Angular Elements and what are they used for? _(Asked in Infosys)_

### Intermediate Questions

1. How do you create a custom Angular Element and integrate it with a non-Angular app? _(Asked in Cognizant)_

### Advanced Questions

1. How would you implement Angular Elements in a micro-frontends architecture? _(Asked in TCS Digital)_

### Tough Questions

1. How do you optimize the performance of Angular Elements in a multi-framework application? _(Asked in Deloitte)_ 