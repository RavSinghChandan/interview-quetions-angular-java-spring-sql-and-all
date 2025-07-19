# Angular Interview Questions for Senior Java Backend Developers (Full Stack)

This document contains the most frequently asked Angular interview questions, curated for senior Java backend developers transitioning to full-stack roles. Questions are organized by topic and include those repeatedly asked in interviews by companies like **TCS, Infosys, Cognizant, Capgemini**, etc., along with additional critical questions to ensure comprehensive coverage for senior-level interviews.

---

## 1. Components

### Basic Questions

1. What is a component in Angular? _(Asked in TCS, Infosys)_

**🧩 Foundation:** A component is a TypeScript class decorated with `@Component()` that encapsulates the logic, template, and styles for a reusable UI element.

**⚙️ Function:** Components are the building blocks of Angular applications, used to create modular, reusable UI elements that can be composed together to build complex user interfaces.

**🚀 Features:**
- Encapsulates template, styles, and logic in one unit
- Can receive data via `@Input()` and emit events via `@Output()`
- Supports lifecycle hooks for initialization and cleanup
- Can be nested and communicate with parent/child components

**🔁 Flow:**
```typescript
@Component({
  selector: 'app-user-card',
  template: '<div>{{ user.name }}</div>',
  styles: ['.user-card { padding: 10px; }']
})
export class UserCardComponent {
  @Input() user: User;
  @Output() userSelected = new EventEmitter<User>();
}
```

**🐞 Fixes:** Common issues include forgetting to declare components in modules, memory leaks from unsubscribed observables, and performance issues from unnecessary change detection cycles.

---

2. How do you create a component using Angular CLI? _(Asked in Capgemini)_

**🧩 Foundation:** Use the `ng generate component` command (or `ng g c`) to create a component with all necessary files and folder structure.

**⚙️ Function:** Angular CLI automates the creation of component files, updates module declarations, and ensures proper file structure following Angular best practices.

**🚀 Features:**
- Creates TypeScript class, HTML template, CSS file, and spec file
- Automatically updates module declarations
- Supports flat and nested folder structures
- Generates unit test files by default

**🔁 Flow:**
```bash
# Basic component
ng generate component user-profile

# With specific options
ng generate component shared/user-card --skip-tests --inline-style --inline-template

# Creates:
# user-profile.component.ts
# user-profile.component.html
# user-profile.component.css
# user-profile.component.spec.ts
```

**🐞 Fixes:** Remember to import the component in your module, avoid circular dependencies, and consider using standalone components in Angular 14+ for better tree-shaking.

---

3. What are the different parts of an Angular component? _(Asked in Wipro)_

**🧩 Foundation:** An Angular component consists of four main parts: TypeScript class, HTML template, CSS styles, and component metadata.

**⚙️ Function:** Each part serves a specific purpose - the class contains logic, template defines UI, styles provide appearance, and metadata configures component behavior.

**🚀 Features:**
- **TypeScript Class:** Contains component logic, properties, and methods
- **HTML Template:** Defines the component's view structure
- **CSS Styles:** Provides component-specific styling
- **Component Metadata:** Configures selector, inputs, outputs, and lifecycle

**🔁 Flow:**
```typescript
@Component({
  selector: 'app-product-list',     // How to use in templates
  templateUrl: './product-list.component.html',  // Template file
  styleUrls: ['./product-list.component.css'],   // Style files
  changeDetection: ChangeDetectionStrategy.OnPush  // Performance optimization
})
export class ProductListComponent implements OnInit {
  // Component logic here
  products: Product[] = [];
  
  ngOnInit() {
    // Lifecycle hook
  }
}
```

**🐞 Fixes:** Common mistakes include mixing template and component logic, not using OnPush strategy for performance, and forgetting to implement OnDestroy for cleanup.

### Intermediate Questions

1. How does component communication happen in Angular? _(Asked in Accenture)_

**🧩 Foundation:** Angular components communicate through parent-child relationships using `@Input()` and `@Output()` decorators, services for cross-component communication, and ViewChild/ContentChild for direct access.

**⚙️ Function:** Component communication enables data flow between components, event handling, and state sharing across the application hierarchy.

**🚀 Features:**
- **Parent to Child:** `@Input()` properties for data passing
- **Child to Parent:** `@Output()` EventEmitter for event bubbling
- **Cross-Component:** Services for shared state and communication
- **Direct Access:** ViewChild/ContentChild for component references

**🔁 Flow:**
```typescript
// Parent Component
@Component({
  template: `
    <app-child 
      [data]="parentData" 
      (dataChanged)="handleDataChange($event)">
    </app-child>
  `
})
export class ParentComponent {
  parentData = 'Hello from parent';
  
  handleDataChange(newData: string) {
    console.log('Child sent:', newData);
  }
}

// Child Component
@Component({
  template: '<button (click)="sendData()">Send Data</button>'
})
export class ChildComponent {
  @Input() data: string;
  @Output() dataChanged = new EventEmitter<string>();
  
  sendData() {
    this.dataChanged.emit('Hello from child');
  }
}
```

**🐞 Fixes:** Avoid tight coupling between components, use services for complex state management, and remember to unsubscribe from observables to prevent memory leaks.

---

2. What is the difference between `@Input()` and `@Output()`? _(Asked in TCS)_

**🧩 Foundation:** `@Input()` allows parent components to pass data to child components, while `@Output()` allows child components to emit events back to parent components.

**⚙️ Function:** `@Input()` enables one-way data binding from parent to child, while `@Output()` enables event-driven communication from child to parent.

**🚀 Features:**
- **@Input():** One-way data binding, supports property binding syntax `[property]`
- **@Output():** Event emission, supports event binding syntax `(event)`
- **@Input():** Can have default values and type checking
- **@Output():** Uses EventEmitter for type-safe event handling

**🔁 Flow:**
```typescript
// Child Component
@Component({
  selector: 'app-user-card',
  template: `
    <div>{{ user.name }}</div>
    <button (click)="onSelect()">Select User</button>
  `
})
export class UserCardComponent {
  @Input() user: User;                    // Receive data from parent
  @Output() userSelected = new EventEmitter<User>();  // Send event to parent
  
  onSelect() {
    this.userSelected.emit(this.user);    // Emit event with data
  }
}

// Parent Component
@Component({
  template: `
    <app-user-card 
      [user]="selectedUser"              // Pass data to child
      (userSelected)="handleSelection($event)">  // Listen to child events
    </app-user-card>
  `
})
export class ParentComponent {
  selectedUser: User = { name: 'John' };
  
  handleSelection(user: User) {
    console.log('User selected:', user);
  }
}
```

**🐞 Fixes:** Common issues include forgetting to emit events, not handling async data properly, and creating circular dependencies between parent and child components.

---

3. Explain the lifecycle hooks of a component. _(Asked in Infosys)_

**🧩 Foundation:** Angular component lifecycle hooks are methods that get called at specific moments during a component's existence, from creation to destruction.

**⚙️ Function:** Lifecycle hooks allow you to perform actions at specific stages of component lifecycle, such as initialization, data loading, cleanup, and change detection.

**🚀 Features:**
- **ngOnChanges:** Called when data-bound properties change
- **ngOnInit:** Called after component initialization and first ngOnChanges
- **ngDoCheck:** Called during every change detection run
- **ngAfterContentInit:** Called after content projection
- **ngAfterViewInit:** Called after component view and child views are initialized
- **ngOnDestroy:** Called before component destruction

**🔁 Flow:**
```typescript
export class UserComponent implements OnInit, OnDestroy, OnChanges {
  @Input() userId: number;
  private subscription: Subscription;
  
  constructor(private userService: UserService) {}
  
  ngOnChanges(changes: SimpleChanges) {
    // Called when @Input properties change
    if (changes['userId']) {
      this.loadUser(changes['userId'].currentValue);
    }
  }
  
  ngOnInit() {
    // Called once after component initialization
    console.log('Component initialized');
    this.loadUser(this.userId);
  }
  
  ngDoCheck() {
    // Called during every change detection cycle
    // Use for custom change detection logic
  }
  
  ngAfterViewInit() {
    // Called after view is fully initialized
    // Safe to access ViewChild elements
  }
  
  ngOnDestroy() {
    // Called before component destruction
    // Clean up subscriptions, timers, etc.
    this.subscription?.unsubscribe();
  }
  
  private loadUser(id: number) {
    this.subscription = this.userService.getUser(id).subscribe();
  }
}
```

**🐞 Fixes:** Always implement ngOnDestroy for cleanup, avoid heavy operations in ngDoCheck, and use OnPush strategy to reduce unnecessary lifecycle calls.

### Advanced Questions

1. How does OnPush change detection strategy work? _(Asked in Cognizant)_

**🧩 Foundation:** OnPush is a change detection strategy that only runs change detection when the component's inputs change or when events are triggered within the component.

**⚙️ Function:** OnPush strategy optimizes performance by reducing unnecessary change detection cycles, only checking for changes when there's a high probability that the view needs to be updated.

**🚀 Features:**
- Only triggers change detection on input reference changes
- Ignores changes to mutable objects unless reference changes
- Requires explicit change detection for async operations
- Significantly improves performance in large applications

**🔁 Flow:**
```typescript
@Component({
  selector: 'app-user-list',
  changeDetection: ChangeDetectionStrategy.OnPush,
  template: `
    <div *ngFor="let user of users">{{ user.name }}</div>
    <button (click)="addUser()">Add User</button>
  `
})
export class UserListComponent {
  @Input() users: User[] = [];
  
  constructor(private cdr: ChangeDetectorRef) {}
  
  addUser() {
    // ❌ This won't trigger change detection with OnPush
    this.users.push({ name: 'New User' });
    
    // ✅ This will trigger change detection
    this.users = [...this.users, { name: 'New User' }];
    
    // ✅ Or manually trigger change detection
    this.cdr.detectChanges();
  }
  
  // ✅ Async operations need manual change detection
  loadUsers() {
    this.userService.getUsers().subscribe(users => {
      this.users = users;
      this.cdr.markForCheck(); // Mark for change detection
    });
  }
}
```

**🐞 Fixes:** Common issues include forgetting to trigger change detection for async operations, mutating objects instead of creating new references, and not using markForCheck() for observables.

---

2. How do you use `ViewChild` to interact with a child component or DOM element? _(Asked in TCS Digital)_

**🧩 Foundation:** `ViewChild` is a decorator that allows a parent component to access child components, directives, or DOM elements directly through template reference variables.

**⚙️ Function:** ViewChild enables direct communication between parent and child components, allowing the parent to call methods, access properties, or manipulate child components programmatically.

**🚀 Features:**
- Can access child components, directives, or DOM elements
- Supports static and dynamic queries
- Provides access after view initialization
- Can be used with multiple children via ViewChildren

**🔁 Flow:**
```typescript
// Parent Component
@Component({
  template: `
    <app-child #childRef></app-child>
    <input #inputRef type="text" />
    <button (click)="accessChild()">Access Child</button>
  `
})
export class ParentComponent implements AfterViewInit {
  @ViewChild('childRef') childComponent: ChildComponent;
  @ViewChild('inputRef') inputElement: ElementRef;
  @ViewChild(ChildComponent) child: ChildComponent; // By type
  @ViewChildren(ChildComponent) children: QueryList<ChildComponent>;
  
  ngAfterViewInit() {
    // Safe to access ViewChild after view is initialized
    console.log(this.childComponent);
    console.log(this.inputElement.nativeElement.value);
  }
  
  accessChild() {
    // Call child component method
    this.childComponent.childMethod();
    
    // Access child component property
    const childData = this.childComponent.data;
    
    // Manipulate DOM element
    this.inputElement.nativeElement.focus();
  }
}

// Child Component
@Component({
  selector: 'app-child',
  template: '<div>Child Component</div>'
})
export class ChildComponent {
  data = 'Child data';
  
  childMethod() {
    console.log('Child method called');
  }
}
```

**🐞 Fixes:** Always access ViewChild in ngAfterViewInit, handle cases where ViewChild might be undefined, and avoid tight coupling between parent and child components.

### Situational / Real-world Questions

1. A component is causing performance bottlenecks in production, how would you debug and resolve it? _(Asked in HCL)_

**🧩 Foundation:** Performance bottlenecks in Angular components typically stem from excessive change detection cycles, memory leaks, inefficient data structures, or heavy DOM manipulations.

**⚙️ Function:** Debugging performance issues requires systematic analysis using Angular DevTools, browser profiling tools, and performance monitoring to identify and resolve bottlenecks.

**🚀 Features:**
- Use Angular DevTools for change detection analysis
- Browser Performance tab for CPU/memory profiling
- OnPush strategy to reduce change detection cycles
- TrackBy functions for ngFor optimization
- Lazy loading and code splitting

**🔁 Flow:**
```typescript
// 1. Enable Angular DevTools and analyze change detection
@Component({
  changeDetection: ChangeDetectionStrategy.OnPush, // Reduce CD cycles
  template: `
    <div *ngFor="let item of items; trackBy: trackByFn">
      {{ item.name }}
    </div>
  `
})
export class PerformanceComponent {
  items: Item[] = [];
  
  // 2. Implement trackBy for ngFor optimization
  trackByFn(index: number, item: Item): number {
    return item.id; // Use unique identifier
  }
  
  // 3. Use OnPush and manual change detection
  loadData() {
    this.dataService.getData().subscribe(data => {
      this.items = data;
      this.cdr.markForCheck(); // Manual CD trigger
    });
  }
  
  // 4. Implement proper cleanup
  ngOnDestroy() {
    this.subscriptions.forEach(sub => sub.unsubscribe());
  }
}

// 5. Use async pipe for automatic subscription management
template: `
  <div *ngFor="let item of items$ | async; trackBy: trackByFn">
    {{ item.name }}
  </div>
`
```

**🐞 Fixes:** Common solutions include implementing OnPush strategy, using trackBy functions, avoiding heavy computations in templates, implementing proper cleanup, and using async pipes for automatic subscription management.

---

2. You need to create a reusable component library for a multi-team project. How would you design and share it? _(Asked in Infosys)_

**🧩 Foundation:** A reusable component library is a collection of standardized UI components that can be shared across multiple teams and projects, ensuring consistency and reducing development time.

**⚙️ Function:** Component libraries provide a centralized way to maintain design consistency, reduce code duplication, and enable rapid development across multiple teams and projects.

**🚀 Features:**
- Modular architecture with clear component boundaries
- Comprehensive documentation and examples
- Version control and semantic versioning
- Automated testing and CI/CD pipeline
- Multiple distribution methods (npm, private registry, monorepo)

**🔁 Flow:**
```typescript
// 1. Library Structure
my-component-library/
├── src/
│   ├── components/
│   │   ├── button/
│   │   │   ├── button.component.ts
│   │   │   ├── button.component.html
│   │   │   ├── button.component.css
│   │   │   └── button.component.spec.ts
│   │   └── card/
│   ├── services/
│   ├── models/
│   └── utils/
├── package.json
├── ng-package.json
└── README.md

// 2. Component Design with Configurable Inputs
@Component({
  selector: 'lib-button',
  template: `
    <button 
      [class]="buttonClass" 
      [disabled]="disabled"
      (click)="onClick.emit($event)">
      <ng-content></ng-content>
    </button>
  `
})
export class ButtonComponent {
  @Input() variant: 'primary' | 'secondary' = 'primary';
  @Input() size: 'small' | 'medium' | 'large' = 'medium';
  @Input() disabled = false;
  @Output() onClick = new EventEmitter<MouseEvent>();
  
  get buttonClass(): string {
    return `btn btn-${this.variant} btn-${this.size}`;
  }
}

// 3. Public API (public-api.ts)
export * from './components/button/button.component';
export * from './components/card/card.component';
export * from './services/data.service';

// 4. Package Configuration (ng-package.json)
{
  "$schema": "../../node_modules/ng-packagr/ng-package.schema.json",
  "dest": "../../dist/my-lib",
  "lib": {
    "entryFile": "src/public-api.ts"
  }
}
```

**🐞 Fixes:** Common challenges include maintaining backward compatibility, handling peer dependencies, ensuring proper tree-shaking, managing CSS conflicts, and providing comprehensive documentation and examples.

---

## 2. Services and Dependency Injection

### Basic Questions

1. What is a service in Angular? _(Asked in TCS)_

**🧩 Foundation:** A service in Angular is a TypeScript class decorated with `@Injectable()` that encapsulates business logic, data access, and functionality that can be shared across multiple components.

**⚙️ Function:** Services provide a way to share data and functionality between components, handle HTTP requests, manage application state, and implement business logic that doesn't belong in components.

**🚀 Features:**
- Singleton by default when provided in root
- Can be injected into components, directives, and other services
- Supports dependency injection for better testability
- Can manage application state and data persistence
- Handles cross-component communication

**🔁 Flow:**
```typescript
@Injectable({
  providedIn: 'root' // Makes it a singleton service
})
export class UserService {
  private users: User[] = [];
  
  constructor(private http: HttpClient) {}
  
  getUsers(): Observable<User[]> {
    return this.http.get<User[]>('/api/users');
  }
  
  addUser(user: User): Observable<User> {
    return this.http.post<User>('/api/users', user);
  }
  
  updateUser(id: number, user: User): Observable<User> {
    return this.http.put<User>(`/api/users/${id}`, user);
  }
}

// Usage in Component
@Component({
  selector: 'app-user-list'
})
export class UserListComponent {
  users: User[] = [];
  
  constructor(private userService: UserService) {}
  
  ngOnInit() {
    this.userService.getUsers().subscribe(users => {
      this.users = users;
    });
  }
}
```

**🐞 Fixes:** Common issues include forgetting to provide services in modules, creating multiple instances when not needed, not handling errors in HTTP calls, and memory leaks from unsubscribed observables.

---

2. How do you create and inject a service? _(Asked in Capgemini)_

**🧩 Foundation:** Services are created using the `@Injectable()` decorator and injected into components through constructor dependency injection.

**⚙️ Function:** Dependency injection allows Angular to provide service instances to components automatically, promoting loose coupling and better testability.

**🚀 Features:**
- Use `@Injectable()` decorator to mark a class as injectable
- Inject services through constructor parameters
- Angular's DI container manages service instances
- Supports different provider scopes (root, module, component)
- Enables easy mocking for unit testing

**🔁 Flow:**
```typescript
// 1. Create Service
@Injectable({
  providedIn: 'root' // Available throughout the app
})
export class DataService {
  private data: any[] = [];
  
  getData(): Observable<any[]> {
    return of(this.data);
  }
  
  addData(item: any): void {
    this.data.push(item);
  }
}

// 2. Inject in Component
@Component({
  selector: 'app-data-list',
  template: '<div *ngFor="let item of items">{{ item }}</div>'
})
export class DataListComponent implements OnInit {
  items: any[] = [];
  
  constructor(private dataService: DataService) {} // Injection
  
  ngOnInit() {
    this.dataService.getData().subscribe(data => {
      this.items = data;
    });
  }
}

// 3. Alternative Provider Scopes
@Component({
  selector: 'app-specific',
  providers: [DataService] // Component-level instance
})
export class SpecificComponent {
  constructor(private dataService: DataService) {}
}

@NgModule({
  providers: [DataService] // Module-level instance
})
export class FeatureModule {}
```

**🐞 Fixes:** Remember to provide services in the appropriate scope, avoid circular dependencies, use `providedIn: 'root'` for singleton services, and handle service lifecycle properly.

### Intermediate Questions

1. What is the difference between providing a service in root vs component level? _(Asked in Wipro)_

**🧩 Foundation:** Service provider scope determines where and how many instances of a service are created and shared across the application.

**⚙️ Function:** Root-level providers create singleton instances shared across the entire app, while component-level providers create separate instances for each component.

**🚀 Features:**
- **Root Level:** Single instance shared across entire application
- **Component Level:** Separate instance for each component instance
- **Module Level:** Instance shared within a specific module
- **Lazy Loading:** Can affect service instance sharing across modules

**🔁 Flow:**
```typescript
// 1. Root Level Provider (Singleton)
@Injectable({
  providedIn: 'root'
})
export class SharedService {
  private data = 'Shared Data';
  
  getData() {
    return this.data;
  }
  
  setData(newData: string) {
    this.data = newData;
  }
}

// 2. Component Level Provider (Separate Instances)
@Component({
  selector: 'app-component-a',
  providers: [SharedService] // New instance for this component
})
export class ComponentA {
  constructor(private service: SharedService) {
    this.service.setData('Component A Data');
  }
}

@Component({
  selector: 'app-component-b',
  providers: [SharedService] // Another new instance
})
export class ComponentB {
  constructor(private service: SharedService) {
    console.log(this.service.getData()); // Different data
  }
}

// 3. Usage Comparison
@Component({
  template: `
    <app-component-a></app-component-a>
    <app-component-b></app-component-b>
  `
})
export class ParentComponent {
  constructor(private service: SharedService) {
    // This gets the root instance
    console.log(this.service.getData());
  }
}
```

**🐞 Fixes:** Use root providers for truly shared services, component providers for isolated instances, and be careful with stateful services in component providers to avoid unexpected behavior.

---

### Advanced Questions

1. How do you ensure a service is a singleton across lazy-loaded modules? _(Asked in Cognizant)_

**🧩 Foundation:** Lazy-loaded modules can create separate instances of services unless properly configured to share a singleton instance.

**⚙️ Function:** Ensuring singleton behavior across lazy-loaded modules requires careful provider configuration and understanding of Angular's module system.

**🚀 Features:**
- Use `providedIn: 'root'` for true application-wide singletons
- Avoid providing services in lazy-loaded modules
- Use `forRoot()` pattern for feature modules
- Consider using `APP_INITIALIZER` for early service initialization

**🔁 Flow:**
```typescript
// 1. Root Service (Recommended Approach)
@Injectable({
  providedIn: 'root' // Ensures singleton across all modules
})
export class GlobalService {
  private state = new BehaviorSubject<any>(null);
  
  getState() {
    return this.state.asObservable();
  }
  
  updateState(newState: any) {
    this.state.next(newState);
  }
}

// 2. Feature Module with forRoot Pattern
@NgModule({
  imports: [CommonModule],
  providers: [
    {
      provide: FeatureService,
      useClass: FeatureService
    }
  ]
})
export class FeatureModule {
  static forRoot(): ModuleWithProviders<FeatureModule> {
    return {
      ngModule: FeatureModule,
      providers: [
        {
          provide: FeatureService,
          useClass: FeatureService
        }
      ]
    };
  }
}

// 3. Lazy Loaded Module (No Service Providers)
@NgModule({
  imports: [CommonModule],
  declarations: [LazyComponent]
})
export class LazyModule {
  // Don't provide services here to avoid multiple instances
}

// 4. App Module Configuration
@NgModule({
  imports: [
    FeatureModule.forRoot(), // Shared instance
    RouterModule.forRoot([
      {
        path: 'lazy',
        loadChildren: () => import('./lazy/lazy.module').then(m => m.LazyModule)
      }
    ])
  ]
})
export class AppModule {}
```

**🐞 Fixes:** Avoid providing services in lazy-loaded modules, use `providedIn: 'root'` for shared services, and implement proper error handling for service initialization failures.

---

### Situational / Real-world Questions

1. You need to use different service implementations in different environments (dev/prod). How would you design it? _(Asked in Deloitte)_

**🧩 Foundation:** Environment-specific service implementations require dependency injection configuration that can switch between different implementations based on build configuration or runtime environment.

**⚙️ Function:** This pattern allows for different service behaviors in development, testing, and production environments while maintaining the same interface.

**🚀 Features:**
- Use Angular's dependency injection tokens
- Environment-specific provider configuration
- Factory pattern for service creation
- Runtime environment detection
- Build-time configuration injection

**🔁 Flow:**
```typescript
// 1. Service Interface
export interface DataService {
  getData(): Observable<any[]>;
  saveData(data: any): Observable<any>;
}

// 2. Environment-Specific Implementations
@Injectable()
export class DevDataService implements DataService {
  getData(): Observable<any[]> {
    return of([{ id: 1, name: 'Dev Data' }]);
  }
  
  saveData(data: any): Observable<any> {
    console.log('Dev: Saving data', data);
    return of({ success: true });
  }
}

@Injectable()
export class ProdDataService implements DataService {
  constructor(private http: HttpClient) {}
  
  getData(): Observable<any[]> {
    return this.http.get<any[]>('/api/data');
  }
  
  saveData(data: any): Observable<any> {
    return this.http.post<any>('/api/data', data);
  }
}

// 3. Environment Configuration
export const DATA_SERVICE = new InjectionToken<DataService>('DataService');

// 4. Provider Factory
export function dataServiceFactory(): DataService {
  if (environment.production) {
    return new ProdDataService(inject(HttpClient));
  } else {
    return new DevDataService();
  }
}

// 5. Module Configuration
@NgModule({
  providers: [
    {
      provide: DATA_SERVICE,
      useFactory: dataServiceFactory
    }
  ]
})
export class AppModule {}

// 6. Usage in Components
@Component({
  selector: 'app-data'
})
export class DataComponent {
  constructor(@Inject(DATA_SERVICE) private dataService: DataService) {}
  
  ngOnInit() {
    this.dataService.getData().subscribe(data => {
      console.log(data);
    });
  }
}
```

**🐞 Fixes:** Ensure proper error handling for environment detection, maintain consistent interfaces across implementations, and use proper testing strategies for each environment configuration.

---

## 3. Routing and Navigation

### Basic Questions

1. What is Angular Router and why is it used? _(Asked in TCS, Capgemini)_

**🧩 Foundation:** Angular Router is a navigation library that enables single-page application (SPA) navigation by mapping URL paths to components without full page reloads.

**⚙️ Function:** Router provides client-side navigation, URL management, and deep linking capabilities, allowing users to navigate between different views while maintaining application state.

**🚀 Features:**
- Client-side navigation without page reloads
- URL-based routing with parameters
- Nested and child routes support
- Route guards for authentication and authorization
- Lazy loading for performance optimization
- History management (browser back/forward)

**🔁 Flow:**
```typescript
// 1. Router Configuration
const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'users', component: UserListComponent },
  { path: 'users/:id', component: UserDetailComponent },
  { path: '**', component: NotFoundComponent }
];

// 2. Module Setup
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}

// 3. Component Navigation
@Component({
  template: `
    <nav>
      <a routerLink="/home">Home</a>
      <a routerLink="/users">Users</a>
    </nav>
    <router-outlet></router-outlet>
  `
})
export class AppComponent {
  constructor(private router: Router) {}
  
  navigateToUser(id: number) {
    this.router.navigate(['/users', id]);
  }
}
```

**🐞 Fixes:** Common issues include forgetting to import RouterModule, not handling route parameters properly, missing route guards for protected routes, and not implementing proper error handling for invalid routes.

---

2. How do you configure routes in Angular? _(Asked in Infosys)_

**🧩 Foundation:** Routes in Angular are configured using the `Routes` array with route objects that define path-to-component mappings and additional route properties.

**⚙️ Function:** Route configuration defines the navigation structure of the application, specifying which components to display for different URLs and how to handle route parameters and navigation.

**🚀 Features:**
- Path-based component mapping
- Route parameters and query parameters
- Child routes and nested navigation
- Route guards and resolvers
- Lazy loading configuration
- Redirect and wildcard routes

**🔁 Flow:**
```typescript
// 1. Basic Route Configuration
const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'users', component: UserListComponent },
  { path: 'users/:id', component: UserDetailComponent },
  { path: 'settings', component: SettingsComponent },
  { path: '**', component: NotFoundComponent }
];

// 2. Route with Guards
const protectedRoutes: Routes = [
  {
    path: 'admin',
    component: AdminComponent,
    canActivate: [AuthGuard],
    children: [
      { path: 'users', component: AdminUsersComponent },
      { path: 'reports', component: AdminReportsComponent }
    ]
  }
];

// 3. Lazy Loading Routes
const lazyRoutes: Routes = [
  {
    path: 'feature',
    loadChildren: () => import('./feature/feature.module').then(m => m.FeatureModule)
  }
];

// 4. Route with Resolver
const resolverRoutes: Routes = [
  {
    path: 'user/:id',
    component: UserDetailComponent,
    resolve: {
      user: UserResolver
    }
  }
];

// 5. Module Configuration
@NgModule({
  imports: [RouterModule.forRoot(routes, {
    enableTracing: false, // Enable for debugging
    useHash: false, // Use hash location strategy
    scrollPositionRestoration: 'enabled'
  })],
  exports: [RouterModule]
})
export class AppRoutingModule {}
```

**🐞 Fixes:** Ensure proper route ordering (specific routes before wildcards), handle route parameters safely, implement proper error handling, and use lazy loading for better performance.

---

3. What is a `router-outlet` and how does it work? _(Asked in Accenture)_

**🧩 Foundation:** `router-outlet` is a placeholder directive that Angular dynamically fills based on the current router state, displaying the component that matches the current URL.

**⚙️ Function:** Router-outlet acts as a viewport where routed components are rendered, enabling dynamic component switching based on URL changes without full page reloads.

**🚀 Features:**
- Dynamically renders components based on current route
- Supports primary and secondary outlets
- Handles component lifecycle during navigation
- Supports named outlets for complex layouts
- Maintains component state during navigation

**🔁 Flow:**
```typescript
// 1. Basic Router Outlet
@Component({
  template: `
    <nav>
      <a routerLink="/home">Home</a>
      <a routerLink="/about">About</a>
    </nav>
    <router-outlet></router-outlet> <!-- Primary outlet -->
  `
})
export class AppComponent {}

// 2. Named Router Outlets
@Component({
  template: `
    <div class="main-content">
      <router-outlet></router-outlet> <!-- Primary outlet -->
    </div>
    <div class="sidebar">
      <router-outlet name="sidebar"></router-outlet> <!-- Named outlet -->
    </div>
  `
})
export class LayoutComponent {}

// 3. Route Configuration with Named Outlets
const routes: Routes = [
  {
    path: 'dashboard',
    component: DashboardComponent,
    children: [
      { path: 'main', component: MainContentComponent },
      { path: 'sidebar', component: SidebarComponent, outlet: 'sidebar' }
    ]
  }
];

// 4. Navigation to Named Outlets
@Component({
  template: `
    <button (click)="navigateToSidebar()">Open Sidebar</button>
  `
})
export class NavigationComponent {
  constructor(private router: Router) {}
  
  navigateToSidebar() {
    this.router.navigate([
      { outlets: { sidebar: ['sidebar'] } }
    ]);
  }
}

// 5. Multiple Outlets in Complex Layout
@Component({
  template: `
    <header>
      <router-outlet name="header"></router-outlet>
    </header>
    <main>
      <router-outlet></router-outlet>
    </main>
    <aside>
      <router-outlet name="sidebar"></router-outlet>
    </aside>
    <footer>
      <router-outlet name="footer"></router-outlet>
    </footer>
  `
})
export class ComplexLayoutComponent {}
```

**🐞 Fixes:** Avoid multiple primary outlets in the same template, handle outlet activation/deactivation properly, and ensure proper route configuration for named outlets.

### Intermediate Questions

1. What is the difference between `ActivatedRoute` and `Router`? _(Asked in Wipro)_

**🧩 Foundation:** `ActivatedRoute` provides access to information about the current route, while `Router` provides navigation capabilities and route manipulation methods.

**⚙️ Function:** ActivatedRoute is used to read route parameters and data, while Router is used to programmatically navigate between routes and manipulate the navigation state.

**🚀 Features:**
- **ActivatedRoute:** Access to route parameters, query parameters, and route data
- **Router:** Navigation methods, route configuration access, and URL manipulation
- **ActivatedRoute:** Observable-based route information
- **Router:** Imperative navigation and route state management

**🔁 Flow:**
```typescript
// 1. Using ActivatedRoute to Read Route Information
@Component({
  template: '<div>User ID: {{ userId }}</div>'
})
export class UserDetailComponent implements OnInit {
  userId: number;
  
  constructor(private route: ActivatedRoute) {}
  
  ngOnInit() {
    // Read route parameters
    this.route.params.subscribe(params => {
      this.userId = +params['id']; // Convert to number
    });
    
    // Read query parameters
    this.route.queryParams.subscribe(queryParams => {
      console.log('Query params:', queryParams);
    });
    
    // Read route data
    this.route.data.subscribe(data => {
      console.log('Route data:', data);
    });
  }
}

// 2. Using Router for Navigation
@Component({
  template: `
    <button (click)="navigateToUser(123)">Go to User 123</button>
    <button (click)="goBack()">Go Back</button>
  `
})
export class NavigationComponent {
  constructor(private router: Router) {}
  
  navigateToUser(id: number) {
    // Navigate with parameters
    this.router.navigate(['/users', id]);
    
    // Navigate with query parameters
    this.router.navigate(['/users'], { 
      queryParams: { page: 1, size: 10 } 
    });
  }
  
  goBack() {
    // Navigate back in history
    this.router.navigate(['../']);
  }
  
  // Check if route is active
  isRouteActive(route: string): boolean {
    return this.router.url.includes(route);
  }
}
```

**🐞 Fixes:** Always unsubscribe from ActivatedRoute observables to prevent memory leaks, handle route parameter changes properly, and use proper error handling for navigation failures.

---

2. How do you configure child routes and nested routes? _(Asked in HCL)_

**🧩 Foundation:** Child routes are configured using the `children` property in route configuration, allowing for nested navigation and component hierarchies.

**⚙️ Function:** Child routes enable complex navigation structures where parent components contain child components that change based on the current route, creating a hierarchical navigation system.

**🚀 Features:**
- Nested component rendering
- Hierarchical navigation structure
- Shared parent component state
- Multiple child route levels
- Child route parameters

**🔁 Flow:**
```typescript
// 1. Basic Child Routes Configuration
const routes: Routes = [
  {
    path: 'admin',
    component: AdminComponent,
    children: [
      { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
      { path: 'dashboard', component: AdminDashboardComponent },
      { path: 'users', component: AdminUsersComponent },
      { path: 'users/:id', component: AdminUserDetailComponent },
      { path: 'settings', component: AdminSettingsComponent }
    ]
  }
];

// 2. Parent Component with Child Router Outlet
@Component({
  template: `
    <div class="admin-layout">
      <nav class="admin-nav">
        <a routerLink="./dashboard" routerLinkActive="active">Dashboard</a>
        <a routerLink="./users" routerLinkActive="active">Users</a>
        <a routerLink="./settings" routerLinkActive="active">Settings</a>
      </nav>
      <div class="admin-content">
        <router-outlet></router-outlet> <!-- Child routes render here -->
      </div>
    </div>
  `
})
export class AdminComponent {}

// 3. Deeply Nested Routes
const nestedRoutes: Routes = [
  {
    path: 'ecommerce',
    component: EcommerceComponent,
    children: [
      {
        path: 'products',
        component: ProductsComponent,
        children: [
          { path: '', component: ProductListComponent },
          { path: ':id', component: ProductDetailComponent },
          {
            path: ':id/reviews',
            component: ProductReviewsComponent,
            children: [
              { path: '', component: ReviewListComponent },
              { path: 'add', component: AddReviewComponent }
            ]
          }
        ]
      }
    ]
  }
];

// 4. Child Route with Parameters
@Component({
  template: `
    <div class="user-detail">
      <h2>User Details</h2>
      <nav>
        <a [routerLink]="['./profile']" routerLinkActive="active">Profile</a>
        <a [routerLink]="['./orders']" routerLinkActive="active">Orders</a>
        <a [routerLink]="['./settings']" routerLinkActive="active">Settings</a>
      </nav>
      <router-outlet></router-outlet>
    </div>
  `
})
export class UserDetailComponent {
  constructor(private route: ActivatedRoute) {}
  
  ngOnInit() {
    // Access parent route parameters
    this.route.parent?.params.subscribe(params => {
      console.log('Parent user ID:', params['id']);
    });
  }
}
```

**🐞 Fixes:** Ensure proper route ordering, handle parent route parameters correctly, avoid circular dependencies, and implement proper navigation guards for child routes.

---

3. How do you implement lazy loading with feature modules? _(Asked in Tech Mahindra)_

**🧩 Foundation:** Lazy loading loads feature modules on-demand when their routes are accessed, reducing initial bundle size and improving application startup performance.

**⚙️ Function:** Lazy loading enables code splitting by loading modules only when needed, improving performance for large applications with multiple feature modules.

**🚀 Features:**
- On-demand module loading
- Reduced initial bundle size
- Improved startup performance
- Automatic code splitting
- Route-based loading triggers

**🔁 Flow:**
```typescript
// 1. Feature Module Structure
// feature/feature.module.ts
@NgModule({
  declarations: [FeatureComponent, FeatureDetailComponent],
  imports: [CommonModule, RouterModule.forChild(featureRoutes)],
  providers: [FeatureService]
})
export class FeatureModule {}

// feature/feature-routing.module.ts
const featureRoutes: Routes = [
  { path: '', component: FeatureComponent },
  { path: ':id', component: FeatureDetailComponent }
];

@NgModule({
  imports: [RouterModule.forChild(featureRoutes)],
  exports: [RouterModule]
})
export class FeatureRoutingModule {}

// 2. Lazy Loading Configuration in Main Routes
const appRoutes: Routes = [
  { path: 'home', component: HomeComponent },
  {
    path: 'feature',
    loadChildren: () => import('./feature/feature.module').then(m => m.FeatureModule)
  },
  {
    path: 'admin',
    loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule),
    canLoad: [AuthGuard] // Guard for lazy loading
  }
];

// 3. Preloading Strategy
@NgModule({
  imports: [
    RouterModule.forRoot(appRoutes, {
      preloadingStrategy: PreloadAllModules // Preload all lazy modules
    })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {}

// 4. Custom Preloading Strategy
@Injectable()
export class CustomPreloadingStrategy implements PreloadingStrategy {
  preload(route: Route, load: () => Observable<any>): Observable<any> {
    if (route.data && route.data['preload']) {
      return load();
    }
    return of(null);
  }
}

// 5. Route Configuration with Preloading
const routes: Routes = [
  {
    path: 'feature',
    loadChildren: () => import('./feature/feature.module').then(m => m.FeatureModule),
    data: { preload: true } // Will be preloaded
  }
];

// 6. Loading Component for Better UX
@Component({
  template: `
    <div class="loading-spinner" *ngIf="loading">
      Loading...
    </div>
    <router-outlet></router-outlet>
  `
})
export class AppComponent {
  loading = false;
  
  constructor(private router: Router) {
    this.router.events.subscribe(event => {
      if (event instanceof RouteConfigLoadStart) {
        this.loading = true;
      } else if (event instanceof RouteConfigLoadEnd) {
        this.loading = false;
      }
    });
  }
}
```

**🐞 Fixes:** Ensure proper module structure, handle loading states for better UX, implement proper error handling for failed module loads, and use preloading strategies appropriately.

---

4. How can you use route guards like `CanActivate` and `CanDeactivate`? _(Asked in Persistent)_

**🧩 Foundation:** Route guards are interfaces that allow you to control the accessibility of routes by implementing methods that return boolean values or observables.

**⚙️ Function:** Guards provide security, validation, and navigation control by intercepting route navigation and deciding whether to allow, redirect, or cancel the navigation.

**🚀 Features:**
- **CanActivate:** Controls route access before activation
- **CanDeactivate:** Controls route exit before deactivation
- **CanLoad:** Controls lazy module loading
- **Resolve:** Pre-loads data before route activation
- **CanActivateChild:** Controls child route access

**🔁 Flow:**
```typescript
// 1. Authentication Guard (CanActivate)
@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(
    private authService: AuthService,
    private router: Router
  ) {}
  
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
    
    if (this.authService.isAuthenticated()) {
      return true;
    }
    
    // Redirect to login
    return this.router.createUrlTree(['/login'], {
      queryParams: { returnUrl: state.url }
    });
  }
}

// 2. Role-Based Guard
@Injectable({
  providedIn: 'root'
})
export class RoleGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}
  
  canActivate(route: ActivatedRouteSnapshot): boolean {
    const requiredRole = route.data['role'];
    const userRole = this.authService.getUserRole();
    
    if (userRole === requiredRole) {
      return true;
    }
    
    this.router.navigate(['/unauthorized']);
    return false;
  }
}

// 3. Deactivation Guard (CanDeactivate)
@Injectable({
  providedIn: 'root'
})
export class UnsavedChangesGuard implements CanDeactivate<ComponentWithUnsavedChanges> {
  canDeactivate(
    component: ComponentWithUnsavedChanges,
    currentRoute: ActivatedRouteSnapshot,
    currentState: RouterStateSnapshot,
    nextState?: RouterStateSnapshot
  ): boolean | Observable<boolean> | Promise<boolean> {
    
    if (component.hasUnsavedChanges()) {
      return confirm('You have unsaved changes. Are you sure you want to leave?');
    }
    
    return true;
  }
}

// 4. Component with Unsaved Changes
export interface ComponentWithUnsavedChanges {
  hasUnsavedChanges(): boolean;
}

@Component({
  template: '<form #form="ngForm">...</form>'
})
export class EditUserComponent implements ComponentWithUnsavedChanges {
  @ViewChild('form') form: NgForm;
  
  hasUnsavedChanges(): boolean {
    return this.form.dirty;
  }
}

// 5. Route Configuration with Guards
const routes: Routes = [
  {
    path: 'admin',
    component: AdminComponent,
    canActivate: [AuthGuard, RoleGuard],
    data: { role: 'admin' },
    children: [
      {
        path: 'users/:id/edit',
        component: EditUserComponent,
        canDeactivate: [UnsavedChangesGuard]
      }
    ]
  }
];

// 6. Resolver for Data Pre-loading
@Injectable({
  providedIn: 'root'
})
export class UserResolver implements Resolve<User> {
  constructor(private userService: UserService) {}
  
  resolve(route: ActivatedRouteSnapshot): Observable<User> {
    const userId = route.paramMap.get('id');
    return this.userService.getUser(+userId);
  }
}

// Usage in route
{
  path: 'users/:id',
  component: UserDetailComponent,
  resolve: { user: UserResolver }
}
```

**🐞 Fixes:** Always handle guard failures gracefully, implement proper error handling, avoid infinite redirects, and ensure guards don't block legitimate navigation.

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

**🧩 Foundation:** A directive is a class with the `@Directive()` decorator that extends HTML with custom behavior, allowing you to create reusable DOM manipulation logic.

**⚙️ Function:** Directives enable you to extend HTML with custom functionality, manipulate the DOM, add behavior to elements, and create reusable UI logic that can be applied across multiple components.

**🚀 Features:**
- Extend HTML with custom behavior
- Manipulate DOM elements and attributes
- Create reusable UI logic
- Support for structural and attribute directives
- Can respond to events and user interactions
- Enable component communication

**🔁 Flow:**
```typescript
// 1. Basic Attribute Directive
@Directive({
  selector: '[appHighlight]'
})
export class HighlightDirective {
  constructor(private el: ElementRef) {}
  
  @Input() appHighlight: string = 'yellow';
  
  @HostListener('mouseenter') onMouseEnter() {
    this.highlight(this.appHighlight);
  }
  
  @HostListener('mouseleave') onMouseLeave() {
    this.highlight(null);
  }
  
  private highlight(color: string | null) {
    this.el.nativeElement.style.backgroundColor = color;
  }
}

// 2. Usage in Template
@Component({
  template: `
    <p appHighlight="yellow">This text will be highlighted on hover</p>
    <div appHighlight="lightblue">This div will be highlighted too</div>
  `
})
export class AppComponent {}

// 3. Structural Directive
@Directive({
  selector: '[appUnless]'
})
export class UnlessDirective {
  private hasView = false;
  
  constructor(
    private templateRef: TemplateRef<any>,
    private viewContainer: ViewContainerRef
  ) {}
  
  @Input() set appUnless(condition: boolean) {
    if (!condition && !this.hasView) {
      this.viewContainer.createEmbeddedView(this.templateRef);
      this.hasView = true;
    } else if (condition && this.hasView) {
      this.viewContainer.clear();
      this.hasView = false;
    }
  }
}
```

**🐞 Fixes:** Common issues include forgetting to declare directives in modules, not handling null/undefined values, memory leaks from event listeners, and not cleaning up resources in ngOnDestroy.

---

2. What is the difference between structural and attribute directives? _(Asked in Wipro)_

**🧩 Foundation:** Structural directives change the DOM layout by adding and removing DOM elements, while attribute directives change the appearance or behavior of existing DOM elements.

**⚙️ Function:** Structural directives manipulate the DOM structure (add/remove elements), while attribute directives modify element properties, styles, or behavior without changing the DOM structure.

**🚀 Features:**
- **Structural Directives:** Use asterisk (*) prefix, manipulate DOM structure, use TemplateRef and ViewContainerRef
- **Attribute Directives:** No asterisk prefix, modify existing elements, use ElementRef and Renderer2
- **Structural:** Can have multiple instances, affect layout
- **Attribute:** Can be combined, affect appearance/behavior

**🔁 Flow:**
```typescript
// 1. Structural Directive (Changes DOM Structure)
@Directive({
  selector: '[appShowIf]'
})
export class ShowIfDirective {
  private hasView = false;
  
  constructor(
    private templateRef: TemplateRef<any>,
    private viewContainer: ViewContainerRef
  ) {}
  
  @Input() set appShowIf(condition: boolean) {
    if (condition && !this.hasView) {
      // Add element to DOM
      this.viewContainer.createEmbeddedView(this.templateRef);
      this.hasView = true;
    } else if (!condition && this.hasView) {
      // Remove element from DOM
      this.viewContainer.clear();
      this.hasView = false;
    }
  }
}

// 2. Attribute Directive (Modifies Existing Element)
@Directive({
  selector: '[appBorder]'
})
export class BorderDirective {
  constructor(
    private el: ElementRef,
    private renderer: Renderer2
  ) {}
  
  @Input() appBorder: string = '1px solid black';
  
  ngOnInit() {
    this.renderer.setStyle(
      this.el.nativeElement,
      'border',
      this.appBorder
    );
  }
}

// 3. Usage Comparison
@Component({
  template: `
    <!-- Structural: Adds/removes element from DOM -->
    <div *appShowIf="isVisible">This div can be removed from DOM</div>
    
    <!-- Attribute: Modifies existing element -->
    <div appBorder="2px solid red">This div gets a border</div>
    
    <!-- Multiple attribute directives on same element -->
    <div 
      appBorder="1px solid blue"
      appHighlight="yellow"
      appTooltip="This is a tooltip">
      Multiple directives
    </div>
  `
})
export class ComparisonComponent {
  isVisible = true;
}
```

**🐞 Fixes:** Use structural directives for conditional rendering, attribute directives for styling/behavior, avoid DOM manipulation in attribute directives, and ensure proper cleanup in structural directives.

---

3. How do you use `*ngIf` and `*ngFor` in templates? _(Asked in Infosys)_

**🧩 Foundation:** `*ngIf` conditionally renders elements based on boolean expressions, while `*ngFor` repeats elements for each item in an array or iterable.

**⚙️ Function:** `*ngIf` provides conditional rendering for better performance and user experience, while `*ngFor` enables dynamic list rendering and data iteration in templates.

**🚀 Features:**
- **ngIf:** Conditional rendering, else templates, performance optimization
- **ngFor:** Array iteration, index tracking, trackBy optimization
- **ngIf:** Can use else templates with ng-template
- **ngFor:** Supports multiple variables (index, first, last, even, odd)

**🔁 Flow:**
```typescript
// 1. Basic ngIf Usage
@Component({
  template: `
    <!-- Simple conditional rendering -->
    <div *ngIf="isVisible">This content is visible</div>
    
    <!-- With else template -->
    <div *ngIf="user; else noUser">
      Welcome, {{ user.name }}!
    </div>
    <ng-template #noUser>
      <div>Please log in</div>
    </ng-template>
    
    <!-- Complex conditions -->
    <div *ngIf="user && user.isAdmin && !isLoading">
      Admin dashboard content
    </div>
  `
})
export class NgIfComponent {
  isVisible = true;
  user: User | null = null;
  isLoading = false;
}

// 2. Basic ngFor Usage
@Component({
  template: `
    <!-- Simple list rendering -->
    <ul>
      <li *ngFor="let item of items">{{ item.name }}</li>
    </ul>
    
    <!-- With index -->
    <div *ngFor="let user of users; let i = index">
      {{ i + 1 }}. {{ user.name }}
    </div>
    
    <!-- With multiple variables -->
    <div *ngFor="let product of products; 
                  let i = index; 
                  let first = first; 
                  let last = last; 
                  let even = even; 
                  let odd = odd"
         [class.first]="first"
         [class.last]="last"
         [class.even]="even"
         [class.odd]="odd">
      {{ i + 1 }}. {{ product.name }}
    </div>
  `
})
export class NgForComponent {
  items = ['Apple', 'Banana', 'Orange'];
  users = [
    { id: 1, name: 'John' },
    { id: 2, name: 'Jane' },
    { id: 3, name: 'Bob' }
  ];
  products = [
    { id: 1, name: 'Laptop' },
    { id: 2, name: 'Phone' },
    { id: 3, name: 'Tablet' }
  ];
}

// 3. Performance Optimization with trackBy
@Component({
  template: `
    <div *ngFor="let user of users; trackBy: trackByUserId">
      {{ user.name }} - {{ user.email }}
    </div>
  `
})
export class OptimizedNgForComponent {
  users: User[] = [];
  
  trackByUserId(index: number, user: User): number {
    return user.id; // Return unique identifier
  }
  
  updateUsers() {
    // Even if array reference changes, Angular will reuse DOM elements
    // for items with same trackBy value
    this.users = [...this.users, newUser];
  }
}

// 4. Combined Usage
@Component({
  template: `
    <div *ngIf="users.length > 0; else noUsers">
      <h3>User List</h3>
      <ul>
        <li *ngFor="let user of users; let i = index">
          {{ i + 1 }}. {{ user.name }}
          <span *ngIf="user.isAdmin">(Admin)</span>
        </li>
      </ul>
    </div>
    <ng-template #noUsers>
      <p>No users found</p>
    </ng-template>
  `
})
export class CombinedComponent {
  users: User[] = [];
}
```

**🐞 Fixes:** Always use trackBy with ngFor for performance, avoid complex expressions in ngIf, use else templates for better UX, and handle null/undefined values properly.

### Intermediate Questions

1. How do you create a custom attribute directive? _(Asked in Capgemini)_

**🧩 Foundation:** Custom attribute directives are created by decorating a class with `@Directive()` and using `ElementRef` and `Renderer2` to manipulate DOM elements.

**⚙️ Function:** Custom attribute directives allow you to add reusable behavior, styling, or functionality to HTML elements without creating new components.

**🚀 Features:**
- Use `@Directive()` decorator with selector
- Inject `ElementRef` for DOM access
- Use `Renderer2` for safe DOM manipulation
- Support `@Input()` for configuration
- Use `@HostListener()` for event handling
- Can respond to lifecycle hooks

**🔁 Flow:**
```typescript
// 1. Basic Custom Attribute Directive
@Directive({
  selector: '[appHighlight]'
})
export class HighlightDirective {
  constructor(
    private el: ElementRef,
    private renderer: Renderer2
  ) {}
  
  @Input() appHighlight: string = 'yellow';
  @Input() appHighlightOpacity: number = 0.3;
  
  @HostListener('mouseenter') onMouseEnter() {
    this.highlight(this.appHighlight);
  }
  
  @HostListener('mouseleave') onMouseLeave() {
    this.highlight(null);
  }
  
  private highlight(color: string | null) {
    if (color) {
      this.renderer.setStyle(
        this.el.nativeElement,
        'backgroundColor',
        color
      );
      this.renderer.setStyle(
        this.el.nativeElement,
        'opacity',
        this.appHighlightOpacity.toString()
      );
    } else {
      this.renderer.removeStyle(this.el.nativeElement, 'backgroundColor');
      this.renderer.removeStyle(this.el.nativeElement, 'opacity');
    }
  }
}

// 2. Directive with Multiple Inputs
@Directive({
  selector: '[appTooltip]'
})
export class TooltipDirective {
  @Input() appTooltip: string = '';
  @Input() appTooltipPosition: 'top' | 'bottom' | 'left' | 'right' = 'top';
  
  private tooltipElement: HTMLElement | null = null;
  
  constructor(
    private el: ElementRef,
    private renderer: Renderer2
  ) {}
  
  @HostListener('mouseenter') onMouseEnter() {
    this.showTooltip();
  }
  
  @HostListener('mouseleave') onMouseLeave() {
    this.hideTooltip();
  }
  
  private showTooltip() {
    this.tooltipElement = this.renderer.createElement('div');
    this.renderer.setProperty(this.tooltipElement, 'innerHTML', this.appTooltip);
    this.renderer.addClass(this.tooltipElement, 'tooltip');
    this.renderer.addClass(this.tooltipElement, `tooltip-${this.appTooltipPosition}`);
    
    this.renderer.appendChild(document.body, this.tooltipElement);
  }
  
  private hideTooltip() {
    if (this.tooltipElement) {
      this.renderer.removeChild(document.body, this.tooltipElement);
      this.tooltipElement = null;
    }
  }
  
  ngOnDestroy() {
    this.hideTooltip();
  }
}

// 3. Usage in Templates
@Component({
  template: `
    <p appHighlight="yellow" appHighlightOpacity="0.5">
      This text will be highlighted on hover
    </p>
    
    <button appTooltip="Click to save" appTooltipPosition="bottom">
      Save
    </button>
    
    <div appHighlight="lightblue" appHighlightOpacity="0.2">
      This div will be highlighted too
    </div>
  `,
  styles: [`
    .tooltip {
      position: absolute;
      background: black;
      color: white;
      padding: 5px;
      border-radius: 3px;
      font-size: 12px;
      z-index: 1000;
    }
  `]
})
export class AppComponent {}
```

**🐞 Fixes:** Always use Renderer2 instead of direct DOM manipulation, clean up resources in ngOnDestroy, handle null/undefined inputs, and avoid memory leaks from event listeners.

---

### Advanced Questions

1. How do you build a structural directive like `*ngIf` from scratch? _(Asked in Accenture)_

**🧩 Foundation:** Structural directives use `TemplateRef` and `ViewContainerRef` to dynamically add and remove DOM elements based on conditions or data.

**⚙️ Function:** Structural directives provide conditional rendering and dynamic content generation by manipulating the view container and embedded views.

**🚀 Features:**
- Use `TemplateRef` to access template content
- Use `ViewContainerRef` to manage embedded views
- Support `@Input()` for dynamic configuration
- Can handle else templates
- Support for multiple embedded views
- Lifecycle management for views

**🔁 Flow:**
```typescript
// 1. Basic Structural Directive (like *ngIf)
@Directive({
  selector: '[appShowIf]'
})
export class ShowIfDirective {
  private hasView = false;
  
  constructor(
    private templateRef: TemplateRef<any>,
    private viewContainer: ViewContainerRef
  ) {}
  
  @Input() set appShowIf(condition: boolean) {
    if (condition && !this.hasView) {
      this.viewContainer.createEmbeddedView(this.templateRef);
      this.hasView = true;
    } else if (!condition && this.hasView) {
      this.viewContainer.clear();
      this.hasView = false;
    }
  }
}

// 2. Structural Directive with Else Template
@Directive({
  selector: '[appShowIfElse]'
})
export class ShowIfElseDirective {
  private hasView = false;
  private elseTemplateRef: TemplateRef<any> | null = null;
  
  constructor(
    private templateRef: TemplateRef<any>,
    private viewContainer: ViewContainerRef
  ) {}
  
  @Input() set appShowIfElse(condition: boolean) {
    this.viewContainer.clear();
    this.hasView = false;
    
    if (condition) {
      this.viewContainer.createEmbeddedView(this.templateRef);
      this.hasView = true;
    } else if (this.elseTemplateRef) {
      this.viewContainer.createEmbeddedView(this.elseTemplateRef);
    }
  }
  
  @Input() set appShowIfElseElse(templateRef: TemplateRef<any>) {
    this.elseTemplateRef = templateRef;
  }
}

// 3. Advanced Structural Directive (like *ngFor)
@Directive({
  selector: '[appRepeat]'
})
export class RepeatDirective {
  constructor(
    private templateRef: TemplateRef<any>,
    private viewContainer: ViewContainerRef
  ) {}
  
  @Input() set appRepeat(count: number) {
    this.viewContainer.clear();
    
    for (let i = 0; i < count; i++) {
      this.viewContainer.createEmbeddedView(this.templateRef, {
        $implicit: i,
        index: i,
        first: i === 0,
        last: i === count - 1,
        even: i % 2 === 0,
        odd: i % 2 === 1
      });
    }
  }
}

// 4. Usage in Templates
@Component({
  template: `
    <!-- Basic structural directive -->
    <div *appShowIf="isVisible">
      This content is conditionally shown
    </div>
    
    <!-- With else template -->
    <div *appShowIfElse="user; else noUser">
      Welcome, {{ user.name }}!
    </div>
    <ng-template #noUser>
      <div>Please log in</div>
    </ng-template>
    
    <!-- Repeat directive -->
    <div *appRepeat="5; let i = index; let first = first; let last = last">
      Item {{ i + 1 }}
      <span *ngIf="first">(First)</span>
      <span *ngIf="last">(Last)</span>
    </div>
  `
})
export class StructuralDirectiveComponent {
  isVisible = true;
  user: User | null = null;
}
```

**🐞 Fixes:** Always clear the view container before creating new views, handle template context properly, implement proper cleanup, and avoid creating unnecessary embedded views.

---

### Tough Questions

1. You need to conditionally render a section of the DOM based on complex business logic involving observables. How would you build a custom directive for it? _(Asked in Cognizant)_

**🧩 Foundation:** A custom structural directive that handles observable-based conditions requires managing subscriptions, handling async state changes, and providing proper cleanup.

**⚙️ Function:** This directive enables reactive conditional rendering based on observable streams, allowing dynamic UI updates based on changing data or business logic.

**🚀 Features:**
- Observable-based condition evaluation
- Automatic subscription management
- Support for loading states
- Error handling for failed observables
- Memory leak prevention
- Async template rendering

**🔁 Flow:**
```typescript
// 1. Observable-Based Structural Directive
@Directive({
  selector: '[appShowIfObservable]'
})
export class ShowIfObservableDirective implements OnInit, OnDestroy {
  private hasView = false;
  private subscription: Subscription | null = null;
  
  constructor(
    private templateRef: TemplateRef<any>,
    private viewContainer: ViewContainerRef,
    private changeDetector: ChangeDetectorRef
  ) {}
  
  @Input() set appShowIfObservable(observable: Observable<boolean>) {
    this.subscription?.unsubscribe();
    
    if (observable) {
      this.subscription = observable.pipe(
        distinctUntilChanged(),
        catchError(error => {
          console.error('Observable error:', error);
          return of(false);
        })
      ).subscribe(condition => {
        this.updateView(condition);
      });
    }
  }
  
  private updateView(condition: boolean) {
    if (condition && !this.hasView) {
      this.viewContainer.createEmbeddedView(this.templateRef);
      this.hasView = true;
    } else if (!condition && this.hasView) {
      this.viewContainer.clear();
      this.hasView = false;
    }
    this.changeDetector.detectChanges();
  }
  
  ngOnDestroy() {
    this.subscription?.unsubscribe();
  }
}

// 2. Advanced Directive with Loading and Error States
@Directive({
  selector: '[appAsyncShow]'
})
export class AsyncShowDirective implements OnInit, OnDestroy {
  private subscription: Subscription | null = null;
  private loadingTemplateRef: TemplateRef<any> | null = null;
  private errorTemplateRef: TemplateRef<any> | null = null;
  
  constructor(
    private templateRef: TemplateRef<any>,
    private viewContainer: ViewContainerRef,
    private changeDetector: ChangeDetectorRef
  ) {}
  
  @Input() set appAsyncShow(observable: Observable<any>) {
    this.subscription?.unsubscribe();
    this.viewContainer.clear();
    
    if (observable) {
      // Show loading state
      if (this.loadingTemplateRef) {
        this.viewContainer.createEmbeddedView(this.loadingTemplateRef);
      }
      
      this.subscription = observable.pipe(
        catchError(error => {
          this.showError(error);
          return EMPTY;
        })
      ).subscribe(data => {
        this.showContent(data);
      });
    }
  }
  
  @Input() set appAsyncShowLoading(templateRef: TemplateRef<any>) {
    this.loadingTemplateRef = templateRef;
  }
  
  @Input() set appAsyncShowError(templateRef: TemplateRef<any>) {
    this.errorTemplateRef = templateRef;
  }
  
  private showContent(data: any) {
    this.viewContainer.clear();
    this.viewContainer.createEmbeddedView(this.templateRef, { $implicit: data });
    this.changeDetector.detectChanges();
  }
  
  private showError(error: any) {
    this.viewContainer.clear();
    if (this.errorTemplateRef) {
      this.viewContainer.createEmbeddedView(this.errorTemplateRef, { $implicit: error });
    }
    this.changeDetector.detectChanges();
  }
  
  ngOnDestroy() {
    this.subscription?.unsubscribe();
  }
}

// 3. Usage with Complex Business Logic
@Component({
  template: `
    <!-- Simple observable condition -->
    <div *appShowIfObservable="userPermission$">
      Admin content here
    </div>
    
    <!-- With loading and error states -->
    <div *appAsyncShow="userData$; 
                        loading: loadingTemplate; 
                        error: errorTemplate; 
                        let user = $implicit">
      Welcome, {{ user.name }}!
      <div *ngIf="user.isAdmin">Admin panel</div>
    </div>
    
    <ng-template #loadingTemplate>
      <div class="loading">Loading user data...</div>
    </ng-template>
    
    <ng-template #errorTemplate let-error>
      <div class="error">Error: {{ error.message }}</div>
    </ng-template>
  `
})
export class ComplexDirectiveComponent {
  userPermission$ = this.authService.hasPermission('admin');
  userData$ = this.userService.getCurrentUser();
  
  constructor(
    private authService: AuthService,
    private userService: UserService
  ) {}
}
```

**🐞 Fixes:** Always unsubscribe from observables, handle error states gracefully, implement proper change detection, and avoid creating multiple subscriptions for the same observable.

---

### Situational / Real-world Questions

1. You are building a dynamic form with repeated sections and visibility toggles. How would you design and use directives to reduce template clutter? _(Asked in HCL)_

**🧩 Foundation:** Custom directives can encapsulate complex form logic, reduce template complexity, and provide reusable form behavior patterns.

**⚙️ Function:** Directives help create cleaner, more maintainable templates by extracting form logic, validation, and conditional rendering into reusable components.

**🚀 Features:**
- Form section visibility management
- Dynamic form field rendering
- Validation state handling
- Conditional form logic
- Reusable form patterns
- Template cleanup and readability

**🔁 Flow:**
```typescript
// 1. Form Section Visibility Directive
@Directive({
  selector: '[appFormSection]'
})
export class FormSectionDirective {
  @Input() appFormSection: string = '';
  @Input() appFormSectionCondition: boolean = true;
  
  constructor(
    private templateRef: TemplateRef<any>,
    private viewContainer: ViewContainerRef
  ) {}
  
  @Input() set appFormSectionVisible(visible: boolean) {
    if (visible && this.appFormSectionCondition) {
      this.viewContainer.createEmbeddedView(this.templateRef);
    } else {
      this.viewContainer.clear();
    }
  }
}

// 2. Form Field Validation Directive
@Directive({
  selector: '[appFormField]'
})
export class FormFieldDirective {
  @Input() appFormField: string = '';
  
  constructor(
    private el: ElementRef,
    private renderer: Renderer2
  ) {}
  
  @HostListener('blur') onBlur() {
    this.validateField();
  }
  
  @HostListener('input') onInput() {
    this.clearValidation();
  }
  
  private validateField() {
    const input = this.el.nativeElement;
    const value = input.value;
    
    if (!value) {
      this.showError('This field is required');
    } else if (this.appFormField === 'email' && !this.isValidEmail(value)) {
      this.showError('Please enter a valid email');
    }
  }
  
  private showError(message: string) {
    this.renderer.addClass(this.el.nativeElement, 'error');
    
    // Create error message element
    const errorDiv = this.renderer.createElement('div');
    this.renderer.addClass(errorDiv, 'error-message');
    this.renderer.setProperty(errorDiv, 'textContent', message);
    
    this.renderer.appendChild(this.el.nativeElement.parentNode, errorDiv);
  }
  
  private clearValidation() {
    this.renderer.removeClass(this.el.nativeElement, 'error');
    const errorMessage = this.el.nativeElement.parentNode.querySelector('.error-message');
    if (errorMessage) {
      this.renderer.removeChild(this.el.nativeElement.parentNode, errorMessage);
    }
  }
  
  private isValidEmail(email: string): boolean {
    return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
  }
}

// 3. Dynamic Form Array Directive
@Directive({
  selector: '[appFormArray]'
})
export class FormArrayDirective {
  constructor(
    private templateRef: TemplateRef<any>,
    private viewContainer: ViewContainerRef
  ) {}
  
  @Input() set appFormArray(items: any[]) {
    this.viewContainer.clear();
    
    items.forEach((item, index) => {
      this.viewContainer.createEmbeddedView(this.templateRef, {
        $implicit: item,
        index: index,
        first: index === 0,
        last: index === items.length - 1
      });
    });
  }
}

// 4. Usage in Complex Form Template
@Component({
  template: `
    <form [formGroup]="userForm">
      <!-- Basic Information Section -->
      <div *appFormSection="'basic'; visible: showBasicInfo">
        <h3>Basic Information</h3>
        
        <div class="form-group">
          <label>Name</label>
          <input 
            type="text" 
            formControlName="name"
            appFormField="name"
            placeholder="Enter your name">
        </div>
        
        <div class="form-group">
          <label>Email</label>
          <input 
            type="email" 
            formControlName="email"
            appFormField="email"
            placeholder="Enter your email">
        </div>
      </div>
      
      <!-- Address Section -->
      <div *appFormSection="'address'; visible: showAddressInfo">
        <h3>Address Information</h3>
        
        <div class="form-group">
          <label>Street</label>
          <input 
            type="text" 
            formControlName="street"
            appFormField="street">
        </div>
        
        <div class="form-group">
          <label>City</label>
          <input 
            type="text" 
            formControlName="city"
            appFormField="city">
        </div>
      </div>
      
      <!-- Dynamic Phone Numbers -->
      <div *appFormSection="'phones'; visible: showPhoneInfo">
        <h3>Phone Numbers</h3>
        
        <div *appFormArray="phoneNumbers; let phone = $implicit; let i = index">
          <div class="phone-group">
            <label>Phone {{ i + 1 }}</label>
            <input 
              type="tel" 
              [formControlName]="'phones.' + i"
              appFormField="phone"
              placeholder="Enter phone number">
            
            <button 
              type="button" 
              (click)="removePhone(i)"
              *ngIf="!first">
              Remove
            </button>
          </div>
        </div>
        
        <button type="button" (click)="addPhone()">Add Phone</button>
      </div>
      
      <!-- Conditional Employment Section -->
      <div *appFormSection="'employment'; visible: userForm.get('isEmployed')?.value">
        <h3>Employment Information</h3>
        
        <div class="form-group">
          <label>Company</label>
          <input 
            type="text" 
            formControlName="company"
            appFormField="company">
        </div>
        
        <div class="form-group">
          <label>Position</label>
          <input 
            type="text" 
            formControlName="position"
            appFormField="position">
        </div>
      </div>
    </form>
  `
})
export class DynamicFormComponent {
  userForm = this.fb.group({
    name: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    street: [''],
    city: [''],
    phones: this.fb.array([]),
    isEmployed: [false],
    company: [''],
    position: ['']
  });
  
  showBasicInfo = true;
  showAddressInfo = true;
  showPhoneInfo = true;
  
  get phoneNumbers() {
    return this.userForm.get('phones')?.value || [];
  }
  
  constructor(private fb: FormBuilder) {
    this.addPhone(); // Add initial phone field
  }
  
  addPhone() {
    const phones = this.userForm.get('phones') as FormArray;
    phones.push(this.fb.control(''));
  }
  
  removePhone(index: number) {
    const phones = this.userForm.get('phones') as FormArray;
    phones.removeAt(index);
  }
}
```

**🐞 Fixes:** Ensure proper form validation, handle dynamic form arrays correctly, implement proper cleanup for directives, and maintain form state consistency across sections.

---

## 5. Pipes

### Basic Questions

1. What is a pipe in Angular? _(Asked in Capgemini)_

**🧩 Foundation:** A pipe is a class decorated with `@Pipe()` that transforms input data into a desired output format, typically used in templates for data formatting and transformation.

**⚙️ Function:** Pipes provide a way to transform data in templates without modifying the original data, enabling data formatting, filtering, and transformation in a declarative way.

**🚀 Features:**
- Transform data in templates
- Pure and impure pipe types
- Chaining multiple pipes
- Built-in and custom pipes
- Parameterized transformations
- Async data handling

**🔁 Flow:**
```typescript
// 1. Basic Pipe Usage in Template
@Component({
  template: `
    <!-- String transformation -->
    <p>{{ 'hello world' | uppercase }}</p>
    <p>{{ 'HELLO WORLD' | lowercase }}</p>
    
    <!-- Number formatting -->
    <p>{{ 1234.56 | number:'1.2-2' }}</p>
    <p>{{ 0.25 | percent:'1.2-2' }}</p>
    
    <!-- Date formatting -->
    <p>{{ currentDate | date:'fullDate' }}</p>
    <p>{{ currentDate | date:'shortTime' }}</p>
    
    <!-- Currency formatting -->
    <p>{{ price | currency:'USD':'symbol':'1.2-2' }}</p>
    
    <!-- Object/Array transformation -->
    <p>{{ user | json }}</p>
    <p>{{ items | slice:0:3 }}</p>
  `
})
export class PipeExampleComponent {
  currentDate = new Date();
  price = 1234.56;
  user = { name: 'John', age: 30 };
  items = ['Apple', 'Banana', 'Orange', 'Mango', 'Grape'];
}

// 2. Pipe Chaining
@Component({
  template: `
    <!-- Multiple pipes in sequence -->
    <p>{{ 'hello world' | uppercase | slice:0:5 }}</p>
    
    <!-- With parameters -->
    <p>{{ currentDate | date:'fullDate' | uppercase }}</p>
    
    <!-- Complex transformation -->
    <p>{{ user.name | uppercase | slice:0:3 }}</p>
  `
})
export class PipeChainingComponent {
  currentDate = new Date();
  user = { name: 'John Doe' };
}

// 3. Async Pipe for Observables
@Component({
  template: `
    <!-- Async pipe automatically handles subscription -->
    <div *ngIf="user$ | async as user">
      <h2>{{ user.name }}</h2>
      <p>{{ user.email }}</p>
    </div>
    
    <!-- Loading state -->
    <div *ngIf="(user$ | async) === null">
      Loading...
    </div>
  `
})
export class AsyncPipeComponent {
  user$ = this.userService.getCurrentUser();
  
  constructor(private userService: UserService) {}
}
```

**🐞 Fixes:** Avoid complex logic in pipes, use async pipe for observables, handle null/undefined values, and prefer pure pipes for better performance.

---

2. What are some commonly used built-in pipes? _(Asked in Infosys)_

**🧩 Foundation:** Angular provides several built-in pipes for common data transformations including string, number, date, currency, and object formatting.

**⚙️ Function:** Built-in pipes provide ready-to-use transformations for common data formatting needs, reducing the need to write custom formatting logic.

**🚀 Features:**
- **String Pipes:** uppercase, lowercase, titlecase
- **Number Pipes:** number, percent, currency
- **Date Pipes:** date with various formats
- **Object Pipes:** json, slice, keyvalue
- **Async Pipe:** for observable handling
- **I18n Pipes:** i18nPlural, i18nSelect

**🔁 Flow:**
```typescript
// 1. String Transformation Pipes
@Component({
  template: `
    <!-- Case transformation -->
    <p>{{ 'hello world' | uppercase }}</p> <!-- HELLO WORLD -->
    <p>{{ 'HELLO WORLD' | lowercase }}</p> <!-- hello world -->
    <p>{{ 'hello world' | titlecase }}</p> <!-- Hello World -->
    
    <!-- String manipulation -->
    <p>{{ 'hello world' | slice:0:5 }}</p> <!-- hello -->
    <p>{{ 'hello world' | slice:-5 }}</p> <!-- world -->
  `
})
export class StringPipesComponent {}

// 2. Number and Currency Pipes
@Component({
  template: `
    <!-- Number formatting -->
    <p>{{ 1234.5678 | number:'1.2-2' }}</p> <!-- 1,234.57 -->
    <p>{{ 1234.5678 | number:'3.1-5' }}</p> <!-- 001,234.56780 -->
    
    <!-- Percentage -->
    <p>{{ 0.25 | percent }}</p> <!-- 25% -->
    <p>{{ 0.25 | percent:'1.2-2' }}</p> <!-- 25.00% -->
    
    <!-- Currency -->
    <p>{{ 1234.56 | currency }}</p> <!-- $1,234.56 -->
    <p>{{ 1234.56 | currency:'EUR':'symbol':'1.2-2' }}</p> <!-- €1,234.56 -->
    <p>{{ 1234.56 | currency:'USD':'code':'1.2-2' }}</p> <!-- USD1,234.56 -->
  `
})
export class NumberPipesComponent {}

// 3. Date Pipes
@Component({
  template: `
    <!-- Date formatting -->
    <p>{{ currentDate | date }}</p> <!-- Jan 15, 2024 -->
    <p>{{ currentDate | date:'fullDate' }}</p> <!-- Monday, January 15, 2024 -->
    <p>{{ currentDate | date:'shortTime' }}</p> <!-- 2:30 PM -->
    <p>{{ currentDate | date:'medium' }}</p> <!-- Jan 15, 2024, 2:30:45 PM -->
    <p>{{ currentDate | date:'yyyy-MM-dd HH:mm:ss' }}</p> <!-- 2024-01-15 14:30:45 -->
  `
})
export class DatePipesComponent {
  currentDate = new Date();
}

// 4. Object and Array Pipes
@Component({
  template: `
    <!-- JSON pipe for debugging -->
    <pre>{{ user | json }}</pre>
    
    <!-- Slice pipe for arrays -->
    <ul>
      <li *ngFor="let item of items | slice:0:3">{{ item }}</li>
    </ul>
    
    <!-- KeyValue pipe for objects -->
    <div *ngFor="let entry of user | keyvalue">
      <strong>{{ entry.key }}:</strong> {{ entry.value }}
    </div>
  `
})
export class ObjectPipesComponent {
  user = { name: 'John', age: 30, email: 'john@example.com' };
  items = ['Apple', 'Banana', 'Orange', 'Mango', 'Grape'];
}

// 5. Async Pipe for Observables
@Component({
  template: `
    <!-- Automatic subscription management -->
    <div *ngIf="user$ | async as user">
      <h2>{{ user.name }}</h2>
      <p>{{ user.email }}</p>
    </div>
    
    <!-- With loading and error states -->
    <div *ngIf="(user$ | async) === null">Loading...</div>
    <div *ngIf="error$ | async as error">Error: {{ error.message }}</div>
  `
})
export class AsyncPipesComponent {
  user$ = this.userService.getCurrentUser();
  error$ = this.userService.getError();
  
  constructor(private userService: UserService) {}
}

// 6. I18n Pipes
@Component({
  template: `
    <!-- Pluralization -->
    <p>{{ items.length | i18nPlural:messageMapping }}</p>
    
    <!-- Selection -->
    <p>{{ gender | i18nSelect:genderMapping }}</p>
  `
})
export class I18nPipesComponent {
  items = ['Apple', 'Banana'];
  gender = 'male';
  
  messageMapping = {
    '=0': 'No items',
    '=1': 'One item',
    'other': '# items'
  };
  
  genderMapping = {
    'male': 'He',
    'female': 'She',
    'other': 'They'
  };
}
```

**🐞 Fixes:** Use appropriate number formats for different locales, handle null values in date pipes, avoid complex expressions in pipes, and use async pipe for automatic subscription management.

---

### Intermediate Questions

1. How do you create a custom pipe in Angular? _(Asked in TCS)_

**🧩 Foundation:** Custom pipes are created by decorating a class with `@Pipe()` and implementing the `PipeTransform` interface with a `transform` method.

**⚙️ Function:** Custom pipes allow you to create reusable data transformations specific to your application's needs, extending Angular's built-in pipe functionality.

**🚀 Features:**
- Use `@Pipe()` decorator with name and pure/impure setting
- Implement `PipeTransform` interface
- Define `transform` method with input and parameters
- Support for pure and impure pipes
- Can handle complex data transformations
- Reusable across components

**🔁 Flow:**
```typescript
// 1. Basic Custom Pipe
@Pipe({
  name: 'reverse'
})
export class ReversePipe implements PipeTransform {
  transform(value: string): string {
    if (!value) return '';
    return value.split('').reverse().join('');
  }
}

// 2. Pipe with Parameters
@Pipe({
  name: 'truncate'
})
export class TruncatePipe implements PipeTransform {
  transform(value: string, limit: number = 10, trail: string = '...'): string {
    if (!value) return '';
    if (value.length <= limit) return value;
    return value.substring(0, limit) + trail;
  }
}

// 3. Pure Pipe (Default) - Only transforms when input changes
@Pipe({
  name: 'filter',
  pure: true
})
export class FilterPipe implements PipeTransform {
  transform(items: any[], filterText: string): any[] {
    if (!items || !filterText) return items;
    
    return items.filter(item => 
      item.name.toLowerCase().includes(filterText.toLowerCase())
    );
  }
}

// 4. Impure Pipe - Transforms on every change detection cycle
@Pipe({
  name: 'sort',
  pure: false
})
export class SortPipe implements PipeTransform {
  transform(items: any[], property: string, direction: 'asc' | 'desc' = 'asc'): any[] {
    if (!items || !property) return items;
    
    return [...items].sort((a, b) => {
      const aValue = a[property];
      const bValue = b[property];
      
      if (direction === 'asc') {
        return aValue > bValue ? 1 : -1;
      } else {
        return aValue < bValue ? 1 : -1;
      }
    });
  }
}

// 5. Complex Custom Pipe
@Pipe({
  name: 'formatPhone'
})
export class FormatPhonePipe implements PipeTransform {
  transform(phone: string, format: 'US' | 'EU' = 'US'): string {
    if (!phone) return '';
    
    // Remove all non-digits
    const digits = phone.replace(/\D/g, '');
    
    if (format === 'US') {
      // Format as (XXX) XXX-XXXX
      if (digits.length === 10) {
        return `(${digits.slice(0, 3)}) ${digits.slice(3, 6)}-${digits.slice(6)}`;
      }
    } else if (format === 'EU') {
      // Format as +XX XXX XXX XXXX
      if (digits.length === 12) {
        return `+${digits.slice(0, 2)} ${digits.slice(2, 5)} ${digits.slice(5, 8)} ${digits.slice(8)}`;
      }
    }
    
    return phone; // Return original if can't format
  }
}

// 6. Usage in Templates
@Component({
  template: `
    <!-- Basic usage -->
    <p>{{ 'hello' | reverse }}</p> <!-- olleh -->
    
    <!-- With parameters -->
    <p>{{ 'This is a long text' | truncate:10 }}</p> <!-- This is a... -->
    <p>{{ 'Short' | truncate:10 }}</p> <!-- Short -->
    
    <!-- Filter and sort -->
    <ul>
      <li *ngFor="let user of users | filter:searchText | sort:'name':'asc'">
        {{ user.name }}
      </li>
    </ul>
    
    <!-- Phone formatting -->
    <p>{{ '1234567890' | formatPhone:'US' }}</p> <!-- (123) 456-7890 -->
    <p>{{ '441234567890' | formatPhone:'EU' }}</p> <!-- +44 123 456 7890 -->
  `
})
export class CustomPipesComponent {
  users = [
    { name: 'Alice', age: 25 },
    { name: 'Bob', age: 30 },
    { name: 'Charlie', age: 35 }
  ];
  searchText = '';
}
```

**🐞 Fixes:** Always handle null/undefined inputs, use pure pipes when possible for better performance, avoid complex logic in pipes, and test pipes thoroughly with edge cases.

---

### Advanced Questions

1. How do impure pipes affect performance and change detection? _(Asked in Cognizant)_

**🧩 Foundation:** Impure pipes execute on every change detection cycle, potentially causing performance issues, while pure pipes only execute when their input values change.

**⚙️ Function:** Understanding the difference between pure and impure pipes is crucial for optimizing Angular application performance and managing change detection efficiently.

**🚀 Features:**
- **Pure Pipes:** Execute only when input changes, cached results
- **Impure Pipes:** Execute on every change detection cycle
- **Performance Impact:** Impure pipes can cause performance degradation
- **Use Cases:** Pure for data transformation, impure for dynamic data
- **Caching:** Pure pipes cache results, impure pipes don't

**🔁 Flow:**
```typescript
// 1. Pure Pipe (Default) - Optimized Performance
@Pipe({
  name: 'pureExample',
  pure: true // Default behavior
})
export class PureExamplePipe implements PipeTransform {
  transform(value: string): string {
    console.log('Pure pipe executed'); // Only logs when input changes
    return value.toUpperCase();
  }
}

// 2. Impure Pipe - Executes on Every Change Detection
@Pipe({
  name: 'impureExample',
  pure: false
})
export class ImpureExamplePipe implements PipeTransform {
  transform(value: string): string {
    console.log('Impure pipe executed'); // Logs on every change detection
    return value.toLowerCase();
  }
}

// 3. Performance Comparison Component
@Component({
  template: `
    <!-- Pure pipe - only executes when input changes -->
    <p>{{ text | pureExample }}</p>
    
    <!-- Impure pipe - executes on every change detection -->
    <p>{{ text | impureExample }}</p>
    
    <!-- Button that triggers change detection -->
    <button (click)="updateCounter()">Update Counter ({{ counter }})</button>
  `
})
export class PerformanceComparisonComponent {
  text = 'Hello World';
  counter = 0;
  
  updateCounter() {
    this.counter++; // This triggers change detection
    // Pure pipe won't re-execute
    // Impure pipe will re-execute
  }
}

// 4. When to Use Impure Pipes
@Pipe({
  name: 'asyncFilter',
  pure: false // Impure because it depends on external state
})
export class AsyncFilterPipe implements PipeTransform {
  transform(items: any[], filterService: FilterService): any[] {
    // This pipe needs to re-execute when filterService state changes
    return items.filter(item => filterService.shouldInclude(item));
  }
}

// 5. Performance Monitoring
@Component({
  template: `
    <div *ngFor="let item of largeArray | expensivePipe">
      {{ item.name }}
    </div>
  `
})
export class PerformanceMonitoringComponent {
  largeArray = Array.from({ length: 1000 }, (_, i) => ({ 
    id: i, 
    name: `Item ${i}` 
  }));
}

@Pipe({
  name: 'expensivePipe',
  pure: true // Keep pure for performance
})
export class ExpensivePipe implements PipeTransform {
  transform(items: any[]): any[] {
    console.log('Expensive operation executed');
    // Simulate expensive operation
    return items.filter(item => item.id % 2 === 0);
  }
}

// 6. Best Practices for Performance
@Pipe({
  name: 'optimizedFilter',
  pure: true
})
export class OptimizedFilterPipe implements PipeTransform {
  private cachedResult: any[] = [];
  private cachedInput: any[] = [];
  private cachedFilter: string = '';
  
  transform(items: any[], filterText: string): any[] {
    // Manual caching for complex operations
    if (this.cachedInput === items && this.cachedFilter === filterText) {
      return this.cachedResult;
    }
    
    this.cachedInput = items;
    this.cachedFilter = filterText;
    this.cachedResult = items.filter(item => 
      item.name.toLowerCase().includes(filterText.toLowerCase())
    );
    
    return this.cachedResult;
  }
}
```

**🐞 Fixes:** Use pure pipes by default, only use impure pipes when necessary, implement manual caching for expensive operations, and monitor pipe execution frequency in development.

---

### Tough Questions

1. How would you implement a pipe to format and filter data from a live stream or observable? _(Asked in HCL)_

**🧩 Foundation:** Creating a pipe that works with observables requires handling async data streams, managing subscriptions, and implementing proper cleanup to prevent memory leaks.

**⚙️ Function:** This pipe enables real-time data transformation and filtering from observable streams, providing reactive data processing capabilities in templates.

**🚀 Features:**
- Observable data stream handling
- Real-time data transformation
- Automatic subscription management
- Memory leak prevention
- Error handling for failed streams
- Support for multiple operators

**🔁 Flow:**
```typescript
// 1. Observable Transformation Pipe
@Pipe({
  name: 'observableTransform',
  pure: false // Impure because it works with observables
})
export class ObservableTransformPipe implements PipeTransform, OnDestroy {
  private subscription: Subscription | null = null;
  private lastValue: any = null;
  private lastObservable: Observable<any> | null = null;
  
  constructor(private changeDetector: ChangeDetectorRef) {}
  
  transform(observable: Observable<any>, transformFn: (value: any) => any): any {
    if (!observable) return null;
    
    // Only subscribe if observable changed
    if (this.lastObservable !== observable) {
      this.subscription?.unsubscribe();
      this.lastObservable = observable;
      
      this.subscription = observable.pipe(
        map(transformFn),
        catchError(error => {
          console.error('Pipe error:', error);
          return of(null);
        })
      ).subscribe(value => {
        this.lastValue = value;
        this.changeDetector.markForCheck();
      });
    }
    
    return this.lastValue;
  }
  
  ngOnDestroy() {
    this.subscription?.unsubscribe();
  }
}

// 2. Advanced Observable Filter Pipe
@Pipe({
  name: 'observableFilter',
  pure: false
})
export class ObservableFilterPipe implements PipeTransform, OnDestroy {
  private subscription: Subscription | null = null;
  private filteredData: any[] = [];
  private lastSource: Observable<any[]> | null = null;
  private lastFilter: string = '';
  
  constructor(private changeDetector: ChangeDetectorRef) {}
  
  transform(
    source: Observable<any[]>, 
    filterText: string,
    filterKey: string = 'name'
  ): any[] {
    if (!source) return [];
    
    // Only re-subscribe if source or filter changed
    if (this.lastSource !== source || this.lastFilter !== filterText) {
      this.subscription?.unsubscribe();
      this.lastSource = source;
      this.lastFilter = filterText;
      
      this.subscription = source.pipe(
        map(items => this.filterItems(items, filterText, filterKey)),
        catchError(error => {
          console.error('Filter pipe error:', error);
          return of([]);
        })
      ).subscribe(filtered => {
        this.filteredData = filtered;
        this.changeDetector.markForCheck();
      });
    }
    
    return this.filteredData;
  }
  
  private filterItems(items: any[], filterText: string, filterKey: string): any[] {
    if (!filterText) return items;
    
    return items.filter(item => 
      item[filterKey]?.toLowerCase().includes(filterText.toLowerCase())
    );
  }
  
  ngOnDestroy() {
    this.subscription?.unsubscribe();
  }
}

// 3. Real-time Data Processing Pipe
@Pipe({
  name: 'liveDataProcess',
  pure: false
})
export class LiveDataProcessPipe implements PipeTransform, OnDestroy {
  private subscription: Subscription | null = null;
  private processedData: any = null;
  
  constructor(private changeDetector: ChangeDetectorRef) {}
  
  transform(
    dataStream: Observable<any>,
    processors: Array<(value: any) => any>
  ): any {
    if (!dataStream || !processors.length) return null;
    
    this.subscription?.unsubscribe();
    
    this.subscription = dataStream.pipe(
      // Apply multiple transformations
      ...processors.map(processor => map(processor)),
      // Handle errors gracefully
      catchError(error => {
        console.error('Data processing error:', error);
        return of(null);
      }),
      // Debounce rapid updates
      debounceTime(100),
      // Only emit distinct values
      distinctUntilChanged()
    ).subscribe(processed => {
      this.processedData = processed;
      this.changeDetector.markForCheck();
    });
    
    return this.processedData;
  }
  
  ngOnDestroy() {
    this.subscription?.unsubscribe();
  }
}

// 4. Usage in Components
@Component({
  template: `
    <!-- Simple observable transformation -->
    <div *ngIf="userData$ | observableTransform:formatUser">
      {{ userData$ | observableTransform:formatUser | json }}
    </div>
    
    <!-- Real-time filtering -->
    <ul>
      <li *ngFor="let user of users$ | observableFilter:searchText:'name'">
        {{ user.name }} - {{ user.email }}
      </li>
    </ul>
    
    <!-- Complex data processing -->
    <div *ngIf="processedData$ | liveDataProcess:dataProcessors as data">
      <h3>Processed Data</h3>
      <p>Count: {{ data.count }}</p>
      <p>Average: {{ data.average }}</p>
      <p>Last Updated: {{ data.timestamp | date:'medium' }}</p>
    </div>
  `
})
export class ObservablePipesComponent {
  userData$ = this.userService.getCurrentUser();
  users$ = this.userService.getAllUsers();
  processedData$ = this.dataService.getLiveData();
  searchText = '';
  
  // Transformation functions
  formatUser = (user: any) => ({
    ...user,
    displayName: `${user.firstName} ${user.lastName}`,
    formattedDate: new Date(user.createdAt).toLocaleDateString()
  });
  
  dataProcessors = [
    (data: any[]) => ({ count: data.length, items: data }),
    (data: any) => ({ 
      ...data, 
      average: data.items.reduce((sum: number, item: any) => sum + item.value, 0) / data.count 
    }),
    (data: any) => ({ ...data, timestamp: new Date() })
  ];
  
  constructor(
    private userService: UserService,
    private dataService: DataService
  ) {}
}

// 5. Error Handling and Loading States
@Pipe({
  name: 'observableWithStates',
  pure: false
})
export class ObservableWithStatesPipe implements PipeTransform, OnDestroy {
  private subscription: Subscription | null = null;
  private state: { data: any; loading: boolean; error: any } = {
    data: null,
    loading: true,
    error: null
  };
  
  constructor(private changeDetector: ChangeDetectorRef) {}
  
  transform(observable: Observable<any>): { data: any; loading: boolean; error: any } {
    if (!observable) {
      return { data: null, loading: false, error: null };
    }
    
    this.subscription?.unsubscribe();
    
    this.subscription = observable.pipe(
      // Start with loading state
      startWith(null),
      // Map to state object
      map(data => ({ data, loading: false, error: null })),
      // Handle errors
      catchError(error => of({ data: null, loading: false, error }))
    ).subscribe(state => {
      this.state = state;
      this.changeDetector.markForCheck();
    });
    
    return this.state;
  }
  
  ngOnDestroy() {
    this.subscription?.unsubscribe();
  }
}
```

**🐞 Fixes:** Always unsubscribe from observables, handle errors gracefully, use appropriate operators for performance, implement proper change detection, and avoid creating multiple subscriptions.

---

## 6. Forms

### Basic Questions

1. What are the two types of forms supported by Angular? _(Asked in TCS, Capgemini)_

**🧩 Foundation:** Angular supports two types of forms: Template-driven forms (declarative approach using directives) and Reactive forms (programmatic approach using FormBuilder and FormControl).

**⚙️ Function:** Template-driven forms are simpler for basic forms, while reactive forms provide more control, validation, and testing capabilities for complex form scenarios.

**🚀 Features:**
- **Template-driven:** Uses NgModel, NgForm, NgModelGroup directives
- **Reactive:** Uses FormBuilder, FormControl, FormGroup, FormArray
- **Template-driven:** Two-way data binding with [(ngModel)]
- **Reactive:** One-way data binding with form controls
- **Template-driven:** Validation in template
- **Reactive:** Validation in component

**🔁 Flow:**
```typescript
// 1. Template-Driven Form
@Component({
  template: `
    <form #userForm="ngForm" (ngSubmit)="onSubmit(userForm)">
      <input 
        name="name" 
        [(ngModel)]="user.name" 
        required 
        #name="ngModel">
      
      <input 
        name="email" 
        [(ngModel)]="user.email" 
        required 
        email 
        #email="ngModel">
      
      <button type="submit" [disabled]="!userForm.valid">
        Submit
      </button>
    </form>
  `
})
export class TemplateFormComponent {
  user = { name: '', email: '' };
  
  onSubmit(form: NgForm) {
    if (form.valid) {
      console.log('Form submitted:', this.user);
    }
  }
}

// 2. Reactive Form
@Component({
  template: `
    <form [formGroup]="userForm" (ngSubmit)="onSubmit()">
      <input 
        formControlName="name" 
        placeholder="Name">
      
      <input 
        formControlName="email" 
        placeholder="Email">
      
      <button type="submit" [disabled]="!userForm.valid">
        Submit
      </button>
    </form>
  `
})
export class ReactiveFormComponent {
  userForm: FormGroup;
  
  constructor(private fb: FormBuilder) {
    this.userForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]]
    });
  }
  
  onSubmit() {
    if (this.userForm.valid) {
      console.log('Form submitted:', this.userForm.value);
    }
  }
}
```

**🐞 Fixes:** Choose template-driven for simple forms, reactive forms for complex validation, always validate on both client and server, and handle form state properly.

---

2. How do you create a template-driven form in Angular? _(Asked in Infosys)_

**🧩 Foundation:** Template-driven forms use Angular directives like NgForm, NgModel, and NgModelGroup to create forms with two-way data binding directly in the template.

**⚙️ Function:** Template-driven forms provide a declarative approach to form creation, making them ideal for simple forms with basic validation requirements.

**🚀 Features:**
- Uses NgForm directive for form tracking
- NgModel for two-way data binding
- NgModelGroup for grouping form controls
- Built-in validation directives
- Template reference variables for form access
- Automatic form state management

**🔁 Flow:**
```typescript
// 1. Basic Template-Driven Form
@Component({
  template: `
    <form #userForm="ngForm" (ngSubmit)="onSubmit(userForm)">
      <div class="form-group">
        <label for="name">Name</label>
        <input 
          id="name"
          name="name"
          type="text"
          [(ngModel)]="user.name"
          required
          minlength="2"
          #name="ngModel"
          class="form-control"
          [class.is-invalid]="name.invalid && name.touched">
        
        <div *ngIf="name.invalid && name.touched" class="invalid-feedback">
          <div *ngIf="name.errors?.['required']">Name is required</div>
          <div *ngIf="name.errors?.['minlength']">Name must be at least 2 characters</div>
        </div>
      </div>
      
      <div class="form-group">
        <label for="email">Email</label>
        <input 
          id="email"
          name="email"
          type="email"
          [(ngModel)]="user.email"
          required
          email
          #email="ngModel"
          class="form-control"
          [class.is-invalid]="email.invalid && email.touched">
        
        <div *ngIf="email.invalid && email.touched" class="invalid-feedback">
          <div *ngIf="email.errors?.['required']">Email is required</div>
          <div *ngIf="email.errors?.['email']">Please enter a valid email</div>
        </div>
      </div>
      
      <button type="submit" [disabled]="!userForm.valid">
        Submit
      </button>
    </form>
  `
})
export class TemplateFormComponent {
  user = { name: '', email: '' };
  
  onSubmit(form: NgForm) {
    if (form.valid) {
      console.log('Form submitted:', this.user);
      // Send to server
      this.userService.createUser(this.user).subscribe();
    }
  }
}

// 2. Form with NgModelGroup
@Component({
  template: `
    <form #userForm="ngForm" (ngSubmit)="onSubmit(userForm)">
      <div ngModelGroup="address" #address="ngModelGroup">
        <h3>Address Information</h3>
        
        <input 
          name="street"
          [(ngModel)]="user.address.street"
          placeholder="Street"
          required>
        
        <input 
          name="city"
          [(ngModel)]="user.address.city"
          placeholder="City"
          required>
        
        <input 
          name="zipCode"
          [(ngModel)]="user.address.zipCode"
          placeholder="Zip Code"
          pattern="[0-9]{5}"
          #zipCode="ngModel">
        
        <div *ngIf="zipCode.invalid && zipCode.touched">
          Please enter a valid 5-digit zip code
        </div>
      </div>
      
      <button type="submit" [disabled]="!userForm.valid">
        Submit
      </button>
    </form>
  `
})
export class GroupedFormComponent {
  user = {
    name: '',
    email: '',
    address: {
      street: '',
      city: '',
      zipCode: ''
    }
  };
  
  onSubmit(form: NgForm) {
    console.log('Form value:', form.value);
    console.log('Form valid:', form.valid);
    console.log('Address group valid:', form.controls['address']?.valid);
  }
}
```

**🐞 Fixes:** Always provide unique name attributes, handle validation errors properly, use template reference variables for form access, and validate on both client and server side.

### Intermediate Questions

1. How do you build a reactive form in Angular? _(Asked in Infosys)_

**🧩 Foundation:** Reactive forms use FormBuilder, FormGroup, FormControl, and FormArray to create forms programmatically with full control over form state and validation.

**⚙️ Function:** Reactive forms provide a programmatic approach to form creation, offering better control, testing capabilities, and complex validation scenarios.

**🚀 Features:**
- FormBuilder for easy form creation
- FormGroup for grouping controls
- FormControl for individual fields
- FormArray for dynamic form arrays
- Built-in and custom validators
- Form state management and tracking

**🔁 Flow:**
```typescript
// 1. Basic Reactive Form
@Component({
  template: `
    <form [formGroup]="userForm" (ngSubmit)="onSubmit()">
      <div class="form-group">
        <label>Name</label>
        <input 
          formControlName="name"
          class="form-control"
          [class.is-invalid]="isFieldInvalid('name')">
        
        <div *ngIf="isFieldInvalid('name')" class="invalid-feedback">
          <div *ngIf="userForm.get('name')?.errors?.['required']">
            Name is required
          </div>
          <div *ngIf="userForm.get('name')?.errors?.['minlength']">
            Name must be at least 2 characters
          </div>
        </div>
      </div>
      
      <div class="form-group">
        <label>Email</label>
        <input 
          formControlName="email"
          type="email"
          class="form-control"
          [class.is-invalid]="isFieldInvalid('email')">
        
        <div *ngIf="isFieldInvalid('email')" class="invalid-feedback">
          <div *ngIf="userForm.get('email')?.errors?.['required']">
            Email is required
          </div>
          <div *ngIf="userForm.get('email')?.errors?.['email']">
            Please enter a valid email
          </div>
        </div>
      </div>
      
      <button type="submit" [disabled]="!userForm.valid">
        Submit
      </button>
    </form>
  `
})
export class ReactiveFormComponent {
  userForm: FormGroup;
  
  constructor(private fb: FormBuilder) {
    this.userForm = this.fb.group({
      name: ['', [Validators.required, Validators.minLength(2)]],
      email: ['', [Validators.required, Validators.email]],
      age: [null, [Validators.min(18), Validators.max(100)]]
    });
  }
  
  isFieldInvalid(fieldName: string): boolean {
    const field = this.userForm.get(fieldName);
    return field!.invalid && (field!.dirty || field!.touched);
  }
  
  onSubmit() {
    if (this.userForm.valid) {
      console.log('Form submitted:', this.userForm.value);
    } else {
      this.markFormGroupTouched();
    }
  }
  
  private markFormGroupTouched() {
    Object.keys(this.userForm.controls).forEach(key => {
      const control = this.userForm.get(key);
      control?.markAsTouched();
    });
  }
}

// 2. Form with Nested Groups
@Component({
  template: `
    <form [formGroup]="userForm" (ngSubmit)="onSubmit()">
      <div formGroupName="personalInfo">
        <h3>Personal Information</h3>
        <input formControlName="firstName" placeholder="First Name">
        <input formControlName="lastName" placeholder="Last Name">
      </div>
      
      <div formGroupName="address">
        <h3>Address</h3>
        <input formControlName="street" placeholder="Street">
        <input formControlName="city" placeholder="City">
        <input formControlName="zipCode" placeholder="Zip Code">
      </div>
      
      <button type="submit">Submit</button>
    </form>
  `
})
export class NestedFormComponent {
  userForm: FormGroup;
  
  constructor(private fb: FormBuilder) {
    this.userForm = this.fb.group({
      personalInfo: this.fb.group({
        firstName: ['', Validators.required],
        lastName: ['', Validators.required]
      }),
      address: this.fb.group({
        street: ['', Validators.required],
        city: ['', Validators.required],
        zipCode: ['', [Validators.required, Validators.pattern('[0-9]{5}')]]
      })
    });
  }
  
  onSubmit() {
    console.log('Form value:', this.userForm.value);
  }
}
```

**🐞 Fixes:** Always validate form state before submission, handle nested form groups properly, implement proper error handling, and use FormBuilder for cleaner code.

---

2. How can you validate form inputs using both built-in and custom validators? _(Asked in HCL)_

**🧩 Foundation:** Angular provides built-in validators and allows creation of custom validators using ValidatorFn interface to implement complex validation logic.

**⚙️ Function:** Validators ensure data integrity by checking input values against defined rules, providing immediate feedback to users and preventing invalid data submission.

**🚀 Features:**
- **Built-in Validators:** required, email, minLength, maxLength, pattern, min, max
- **Custom Validators:** ValidatorFn interface implementation
- **Async Validators:** for server-side validation
- **Cross-field Validation:** validate multiple fields together
- **Dynamic Validation:** change validation rules based on conditions

**🔁 Flow:**
```typescript
// 1. Built-in Validators
@Component({
  template: `
    <form [formGroup]="userForm" (ngSubmit)="onSubmit()">
      <input 
        formControlName="username"
        placeholder="Username">
      <div *ngIf="userForm.get('username')?.errors?.['required']">
        Username is required
      </div>
      <div *ngIf="userForm.get('username')?.errors?.['minlength']">
        Username must be at least 3 characters
      </div>
      
      <input 
        formControlName="email"
        type="email"
        placeholder="Email">
      <div *ngIf="userForm.get('email')?.errors?.['email']">
        Please enter a valid email
      </div>
      
      <input 
        formControlName="age"
        type="number"
        placeholder="Age">
      <div *ngIf="userForm.get('age')?.errors?.['min']">
        Age must be at least 18
      </div>
      
      <button type="submit">Submit</button>
    </form>
  `
})
export class BuiltInValidatorsComponent {
  userForm: FormGroup;
  
  constructor(private fb: FormBuilder) {
    this.userForm = this.fb.group({
      username: ['', [Validators.required, Validators.minLength(3)]],
      email: ['', [Validators.required, Validators.email]],
      age: [null, [Validators.min(18), Validators.max(100)]]
    });
  }
}

// 2. Custom Validators
export class CustomValidators {
  // Custom validator function
  static passwordStrength(control: AbstractControl): ValidationErrors | null {
    const value = control.value;
    if (!value) return null;
    
    const hasUpperCase = /[A-Z]/.test(value);
    const hasLowerCase = /[a-z]/.test(value);
    const hasNumbers = /\d/.test(value);
    const hasSpecialChar = /[!@#$%^&*]/.test(value);
    
    const valid = hasUpperCase && hasLowerCase && hasNumbers && hasSpecialChar;
    
    return valid ? null : { passwordStrength: true };
  }
  
  // Cross-field validator
  static passwordMatch(control: AbstractControl): ValidationErrors | null {
    const password = control.get('password');
    const confirmPassword = control.get('confirmPassword');
    
    if (password && confirmPassword && password.value !== confirmPassword.value) {
      return { passwordMismatch: true };
    }
    
    return null;
  }
  
  // Async validator
  static uniqueUsername(userService: UserService): AsyncValidatorFn {
    return (control: AbstractControl): Observable<ValidationErrors | null> => {
      return userService.checkUsername(control.value).pipe(
        map(exists => exists ? { usernameExists: true } : null),
        catchError(() => of(null))
      );
    };
  }
}

// 3. Using Custom Validators
@Component({
  template: `
    <form [formGroup]="userForm" (ngSubmit)="onSubmit()">
      <input 
        formControlName="password"
        type="password"
        placeholder="Password">
      <div *ngIf="userForm.get('password')?.errors?.['passwordStrength']">
        Password must contain uppercase, lowercase, number, and special character
      </div>
      
      <input 
        formControlName="confirmPassword"
        type="password"
        placeholder="Confirm Password">
      <div *ngIf="userForm.errors?.['passwordMismatch']">
        Passwords do not match
      </div>
      
      <input 
        formControlName="username"
        placeholder="Username">
      <div *ngIf="userForm.get('username')?.errors?.['usernameExists']">
        Username already exists
      </div>
      
      <button type="submit">Submit</button>
    </form>
  `
})
export class CustomValidatorsComponent {
  userForm: FormGroup;
  
  constructor(private fb: FormBuilder, private userService: UserService) {
    this.userForm = this.fb.group({
      password: ['', [Validators.required, CustomValidators.passwordStrength]],
      confirmPassword: ['', Validators.required],
      username: ['', Validators.required, CustomValidators.uniqueUsername(this.userService)]
    }, { validators: CustomValidators.passwordMatch });
  }
}

// 4. Dynamic Validation
@Component({
  template: `
    <form [formGroup]="userForm" (ngSubmit)="onSubmit()">
      <input 
        formControlName="email"
        placeholder="Email">
      
      <div *ngIf="userForm.get('email')?.value">
        <input 
          formControlName="confirmEmail"
          placeholder="Confirm Email">
      </div>
      
      <button type="submit">Submit</button>
    </form>
  `
})
export class DynamicValidationComponent {
  userForm: FormGroup;
  
  constructor(private fb: FormBuilder) {
    this.userForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      confirmEmail: ['']
    });
    
    // Dynamic validation based on email value
    this.userForm.get('email')?.valueChanges.subscribe(email => {
      const confirmEmailControl = this.userForm.get('confirmEmail');
      if (email) {
        confirmEmailControl?.setValidators([
          Validators.required,
          Validators.email,
          this.emailMatchValidator(email)
        ]);
      } else {
        confirmEmailControl?.clearValidators();
      }
      confirmEmailControl?.updateValueAndValidity();
    });
  }
  
  private emailMatchValidator(email: string): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      return control.value === email ? null : { emailMismatch: true };
    };
  }
}
```

**🐞 Fixes:** Always handle async validation errors, implement proper error messages, use cross-field validation for related fields, and test validators thoroughly.

### Advanced Questions

1. How do you create and use custom form validators? _(Asked in Cognizant)_

**🧩 Foundation:** Custom form validators are functions that implement the ValidatorFn interface, allowing developers to create application-specific validation logic beyond Angular's built-in validators.

**⚙️ Function:** Custom validators provide business-specific validation rules, cross-field validation, and complex validation scenarios that cannot be handled by built-in validators alone.

**🚀 Features:**
- Implements ValidatorFn interface
- Returns ValidationErrors or null
- Can access multiple form controls
- Supports async validation
- Reusable across components
- Can be composed with other validators

**🔁 Flow:**
```typescript
// 1. Basic Custom Validator
export class CustomValidators {
  // Simple validator function
  static passwordStrength(control: AbstractControl): ValidationErrors | null {
    const value = control.value;
    if (!value) return null;
    
    const hasUpperCase = /[A-Z]/.test(value);
    const hasLowerCase = /[a-z]/.test(value);
    const hasNumbers = /\d/.test(value);
    const hasSpecialChar = /[!@#$%^&*]/.test(value);
    const isLongEnough = value.length >= 8;
    
    const errors: ValidationErrors = {};
    
    if (!hasUpperCase) errors.noUpperCase = true;
    if (!hasLowerCase) errors.noLowerCase = true;
    if (!hasNumbers) errors.noNumbers = true;
    if (!hasSpecialChar) errors.noSpecialChar = true;
    if (!isLongEnough) errors.tooShort = true;
    
    return Object.keys(errors).length > 0 ? errors : null;
  }
  
  // Cross-field validator
  static passwordMatch(control: AbstractControl): ValidationErrors | null {
    const password = control.get('password');
    const confirmPassword = control.get('confirmPassword');
    
    if (password && confirmPassword && password.value !== confirmPassword.value) {
      return { passwordMismatch: true };
    }
    
    return null;
  }
  
  // Conditional validator
  static conditionalRequired(requiredField: string): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      const parent = control.parent;
      if (!parent) return null;
      
      const requiredControl = parent.get(requiredField);
      if (requiredControl?.value && !control.value) {
        return { conditionalRequired: true };
      }
      
      return null;
    };
  }
  
  // Async validator
  static uniqueEmail(userService: UserService): AsyncValidatorFn {
    return (control: AbstractControl): Observable<ValidationErrors | null> => {
      if (!control.value) return of(null);
      
      return userService.checkEmailExists(control.value).pipe(
        map(exists => exists ? { emailExists: true } : null),
        catchError(() => of(null))
      );
    };
  }
}

// 2. Using Custom Validators
@Component({
  template: `
    <form [formGroup]="userForm" (ngSubmit)="onSubmit()">
      <div class="form-group">
        <label>Password</label>
        <input 
          formControlName="password"
          type="password"
          class="form-control">
        
        <div *ngIf="userForm.get('password')?.errors" class="validation-errors">
          <div *ngIf="userForm.get('password')?.errors?.['noUpperCase']">
            Password must contain uppercase letter
          </div>
          <div *ngIf="userForm.get('password')?.errors?.['noLowerCase']">
            Password must contain lowercase letter
          </div>
          <div *ngIf="userForm.get('password')?.errors?.['noNumbers']">
            Password must contain number
          </div>
          <div *ngIf="userForm.get('password')?.errors?.['noSpecialChar']">
            Password must contain special character
          </div>
          <div *ngIf="userForm.get('password')?.errors?.['tooShort']">
            Password must be at least 8 characters
          </div>
        </div>
      </div>
      
      <div class="form-group">
        <label>Confirm Password</label>
        <input 
          formControlName="confirmPassword"
          type="password"
          class="form-control">
      </div>
      
      <div *ngIf="userForm.errors?.['passwordMismatch']" class="alert alert-danger">
        Passwords do not match
      </div>
      
      <div class="form-group">
        <label>Email</label>
        <input 
          formControlName="email"
          type="email"
          class="form-control">
        
        <div *ngIf="userForm.get('email')?.errors?.['emailExists']" class="alert alert-danger">
          This email is already registered
        </div>
      </div>
      
      <button type="submit" [disabled]="!userForm.valid">Submit</button>
    </form>
  `
})
export class CustomValidatorsComponent {
  userForm: FormGroup;
  
  constructor(private fb: FormBuilder, private userService: UserService) {
    this.userForm = this.fb.group({
      password: ['', [Validators.required, CustomValidators.passwordStrength]],
      confirmPassword: ['', Validators.required],
      email: ['', [Validators.required, Validators.email], CustomValidators.uniqueEmail(this.userService)]
    }, { validators: CustomValidators.passwordMatch });
  }
  
  onSubmit(): void {
    if (this.userForm.valid) {
      console.log('Form submitted:', this.userForm.value);
    }
  }
}

// 3. Composable Validators
export class ComposableValidators {
  // Combine multiple validators
  static strongPassword(): ValidatorFn[] {
    return [
      Validators.required,
      Validators.minLength(8),
      CustomValidators.passwordStrength,
      this.noCommonPasswords()
    ];
  }
  
  // Check against common passwords
  static noCommonPasswords(): ValidatorFn {
    const commonPasswords = ['password', '123456', 'qwerty', 'admin'];
    
    return (control: AbstractControl): ValidationErrors | null => {
      const value = control.value?.toLowerCase();
      if (commonPasswords.includes(value)) {
        return { commonPassword: true };
      }
      return null;
    };
  }
  
  // Dynamic validator based on conditions
  static conditionalValidation(condition: () => boolean, validators: ValidatorFn[]): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      if (!condition()) return null;
      
      for (const validator of validators) {
        const result = validator(control);
        if (result) return result;
      }
      
      return null;
    };
  }
}
```

**🐞 Fixes:** Always handle null/undefined values, implement proper error messages, test validators thoroughly, and ensure async validators handle errors gracefully.

---

2. How do you integrate Angular Material components with reactive forms? _(Asked in Wipro)_

**🧩 Foundation:** Angular Material provides pre-built UI components that can be seamlessly integrated with reactive forms, offering consistent design and enhanced user experience while maintaining form functionality.

**⚙️ Function:** Material components provide styled form controls, validation states, and accessibility features that work directly with reactive forms, reducing development time and ensuring design consistency.

**🚀 Features:**
- Material form field wrapper with validation states
- Material input components with built-in styling
- Error message display with Material design
- Accessibility features (ARIA labels, screen reader support)
- Consistent theming and styling
- Integration with form validation states

**🔁 Flow:**
```typescript
// 1. Basic Material Form Integration
@Component({
  template: `
    <form [formGroup]="userForm" (ngSubmit)="onSubmit()">
      <mat-form-field appearance="outline">
        <mat-label>First Name</mat-label>
        <input 
          matInput 
          formControlName="firstName"
          placeholder="Enter first name">
        
        <mat-error *ngIf="userForm.get('firstName')?.errors?.['required']">
          First name is required
        </mat-error>
        <mat-error *ngIf="userForm.get('firstName')?.errors?.['minlength']">
          First name must be at least 2 characters
        </mat-error>
      </mat-form-field>
      
      <mat-form-field appearance="outline">
        <mat-label>Email</mat-label>
        <input 
          matInput 
          formControlName="email"
          type="email"
          placeholder="Enter email address">
        
        <mat-error *ngIf="userForm.get('email')?.errors?.['required']">
          Email is required
        </mat-error>
        <mat-error *ngIf="userForm.get('email')?.errors?.['email']">
          Please enter a valid email address
        </mat-error>
      </mat-form-field>
      
      <mat-form-field appearance="outline">
        <mat-label>Password</mat-label>
        <input 
          matInput 
          formControlName="password"
          [type]="hidePassword ? 'password' : 'text'"
          placeholder="Enter password">
        
        <button 
          mat-icon-button 
          matSuffix 
          (click)="hidePassword = !hidePassword"
          [attr.aria-label]="'Hide password'"
          [attr.aria-pressed]="hidePassword">
          <mat-icon>{{hidePassword ? 'visibility_off' : 'visibility'}}</mat-icon>
        </button>
        
        <mat-error *ngIf="userForm.get('password')?.errors?.['required']">
          Password is required
        </mat-error>
        <mat-error *ngIf="userForm.get('password')?.errors?.['minlength']">
          Password must be at least 8 characters
        </mat-error>
      </mat-form-field>
      
      <mat-form-field appearance="outline">
        <mat-label>Country</mat-label>
        <mat-select formControlName="country">
          <mat-option value="">Select a country</mat-option>
          <mat-option *ngFor="let country of countries" [value]="country.value">
            {{country.label}}
          </mat-option>
        </mat-select>
        
        <mat-error *ngIf="userForm.get('country')?.errors?.['required']">
          Please select a country
        </mat-error>
      </mat-form-field>
      
      <mat-form-field appearance="outline">
        <mat-label>Date of Birth</mat-label>
        <input 
          matInput 
          [matDatepicker]="picker"
          formControlName="dateOfBirth"
          placeholder="Choose a date">
        <mat-datepicker-toggle matSuffix [for]="picker"></mat-datepicker-toggle>
        <mat-datepicker #picker></mat-datepicker>
        
        <mat-error *ngIf="userForm.get('dateOfBirth')?.errors?.['required']">
          Date of birth is required
        </mat-error>
      </mat-form-field>
      
      <mat-checkbox formControlName="termsAccepted">
        I agree to the terms and conditions
      </mat-checkbox>
      
      <div class="form-actions">
        <button 
          mat-raised-button 
          color="primary" 
          type="submit"
          [disabled]="!userForm.valid">
          Submit
        </button>
        
        <button 
          mat-button 
          type="button"
          (click)="resetForm()">
          Reset
        </button>
      </div>
    </form>
  `,
  styles: [`
    form {
      display: flex;
      flex-direction: column;
      gap: 16px;
      max-width: 400px;
      margin: 0 auto;
      padding: 20px;
    }
    
    .form-actions {
      display: flex;
      gap: 12px;
      justify-content: flex-end;
    }
  `]
})
export class MaterialFormComponent {
  userForm: FormGroup;
  hidePassword = true;
  countries = [
    { value: 'us', label: 'United States' },
    { value: 'uk', label: 'United Kingdom' },
    { value: 'ca', label: 'Canada' },
    { value: 'au', label: 'Australia' }
  ];
  
  constructor(private fb: FormBuilder) {
    this.userForm = this.fb.group({
      firstName: ['', [Validators.required, Validators.minLength(2)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]],
      country: ['', Validators.required],
      dateOfBirth: ['', Validators.required],
      termsAccepted: [false, Validators.requiredTrue]
    });
  }
  
  onSubmit(): void {
    if (this.userForm.valid) {
      console.log('Form submitted:', this.userForm.value);
    }
  }
  
  resetForm(): void {
    this.userForm.reset();
  }
}

// 2. Advanced Material Form with Custom Validation
@Component({
  template: `
    <form [formGroup]="advancedForm" (ngSubmit)="onSubmit()">
      <mat-card>
        <mat-card-header>
          <mat-card-title>Advanced Registration Form</mat-card-title>
        </mat-card-header>
        
        <mat-card-content>
          <div class="form-row">
            <mat-form-field appearance="outline">
              <mat-label>Username</mat-label>
              <input 
                matInput 
                formControlName="username"
                placeholder="Choose a username">
              
              <mat-icon matSuffix *ngIf="advancedForm.get('username')?.valid">
                check_circle
              </mat-icon>
              
              <mat-error *ngIf="advancedForm.get('username')?.errors?.['required']">
                Username is required
              </mat-error>
              <mat-error *ngIf="advancedForm.get('username')?.errors?.['usernameExists']">
                Username already exists
              </mat-error>
            </mat-form-field>
          </div>
          
          <div class="form-row">
            <mat-form-field appearance="outline">
              <mat-label>Phone Number</mat-label>
              <input 
                matInput 
                formControlName="phoneNumber"
                placeholder="Enter phone number">
              
              <mat-error *ngIf="advancedForm.get('phoneNumber')?.errors?.['pattern']">
                Please enter a valid phone number
              </mat-error>
            </mat-form-field>
          </div>
          
          <div class="form-row">
            <mat-form-field appearance="outline">
              <mat-label>Skills</mat-label>
              <mat-chip-list #chipList>
                <mat-chip 
                  *ngFor="let skill of selectedSkills" 
                  [removable]="true"
                  (removed)="removeSkill(skill)">
                  {{skill}}
                  <mat-icon matChipRemove>cancel</mat-icon>
                </mat-chip>
                <input 
                  [matChipInputFor]="chipList"
                  [matChipInputSeparatorKeyCodes]="separatorKeysCodes"
                  [matChipInputAddOnBlur]="true"
                  (matChipInputTokenEnd)="addSkill($event)">
              </mat-chip-list>
            </mat-form-field>
          </div>
          
          <div class="form-row">
            <mat-form-field appearance="outline">
              <mat-label>Bio</mat-label>
              <textarea 
                matInput 
                formControlName="bio"
                placeholder="Tell us about yourself"
                rows="4"></textarea>
              
              <mat-hint>Maximum 500 characters</mat-hint>
              <mat-error *ngIf="advancedForm.get('bio')?.errors?.['maxlength']">
                Bio cannot exceed 500 characters
              </mat-error>
            </mat-form-field>
          </div>
          
          <div class="form-row">
            <mat-slide-toggle formControlName="newsletter">
              Subscribe to newsletter
            </mat-slide-toggle>
          </div>
        </mat-card-content>
        
        <mat-card-actions align="end">
          <button 
            mat-button 
            type="button"
            (click)="resetForm()">
            Cancel
          </button>
          <button 
            mat-raised-button 
            color="primary" 
            type="submit"
            [disabled]="!advancedForm.valid">
            Register
          </button>
        </mat-card-actions>
      </mat-card>
    </form>
  `,
  styles: [`
    .form-row {
      margin-bottom: 16px;
    }
    
    mat-card {
      max-width: 600px;
      margin: 20px auto;
    }
  `]
})
export class AdvancedMaterialFormComponent {
  advancedForm: FormGroup;
  selectedSkills: string[] = [];
  readonly separatorKeysCodes = [ENTER, COMMA] as const;
  
  constructor(private fb: FormBuilder, private userService: UserService) {
    this.advancedForm = this.fb.group({
      username: ['', [Validators.required, Validators.minLength(3)], this.usernameExistsValidator()],
      phoneNumber: ['', [Validators.required, Validators.pattern(/^\d{10}$/)]],
      bio: ['', [Validators.maxLength(500)]],
      newsletter: [false]
    });
  }
  
  usernameExistsValidator(): AsyncValidatorFn {
    return (control: AbstractControl): Observable<ValidationErrors | null> => {
      if (!control.value) return of(null);
      
      return this.userService.checkUsername(control.value).pipe(
        map(exists => exists ? { usernameExists: true } : null),
        catchError(() => of(null))
      );
    };
  }
  
  addSkill(event: MatChipInputEvent): void {
    const value = (event.value || '').trim();
    if (value && !this.selectedSkills.includes(value)) {
      this.selectedSkills.push(value);
    }
    event.chipInput!.clear();
  }
  
  removeSkill(skill: string): void {
    const index = this.selectedSkills.indexOf(skill);
    if (index >= 0) {
      this.selectedSkills.splice(index, 1);
    }
  }
  
  onSubmit(): void {
    if (this.advancedForm.valid) {
      const formData = {
        ...this.advancedForm.value,
        skills: this.selectedSkills
      };
      console.log('Form submitted:', formData);
    }
  }
  
  resetForm(): void {
    this.advancedForm.reset();
    this.selectedSkills = [];
  }
}
```

**🐞 Fixes:** Always provide proper error messages, handle form validation states correctly, ensure accessibility compliance, and test Material components thoroughly across different screen sizes.

### Tough Questions

1. How would you build a reusable form component with reactive bindings? _(Asked in Deloitte)_

### Situational / Real-world Questions

1. You’re integrating Angular forms with a Spring Boot backend. How would you ensure data model compatibility and error handling? _(Asked in TCS Digital)_

---

## 7. RxJS and Observables

### Basic Questions

1. What is RxJS and why is it used in Angular? _(Asked in Capgemini, Infosys)_

**🧩 Foundation:** RxJS (Reactive Extensions for JavaScript) is a library for reactive programming using Observables, providing a powerful way to handle asynchronous data streams and events in Angular applications.

**⚙️ Function:** RxJS enables declarative programming with data streams, making it easier to handle complex asynchronous operations, HTTP requests, user interactions, and state management in Angular applications.

**🚀 Features:**
- Observable streams for data handling
- Rich set of operators for data transformation
- Automatic memory management with subscriptions
- Error handling and retry mechanisms
- Support for both synchronous and asynchronous operations
- Integration with Angular's change detection

**🔁 Flow:**
```typescript
// 1. Basic RxJS Usage in Angular
import { Observable, of, fromEvent, interval } from 'rxjs';
import { map, filter, debounceTime, switchMap } from 'rxjs/operators';

// Creating Observables
const numbers$ = of(1, 2, 3, 4, 5);
const interval$ = interval(1000);
const click$ = fromEvent(document, 'click');

// Using operators
numbers$.pipe(
  map(x => x * 2),
  filter(x => x > 5)
).subscribe(result => console.log(result));

// 2. HTTP Requests with RxJS
@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private http: HttpClient) {}
  
  getUsers(): Observable<User[]> {
    return this.http.get<User[]>('/api/users').pipe(
      map(users => users.filter(user => user.active)),
      catchError(error => {
        console.error('Error fetching users:', error);
        return of([]);
      })
    );
  }
  
  searchUsers(query: string): Observable<User[]> {
    return this.http.get<User[]>(`/api/users/search?q=${query}`).pipe(
      debounceTime(300),
      switchMap(response => of(response))
    );
  }
}

// 3. Component Integration
@Component({
  template: `
    <div *ngFor="let user of users$ | async">
      {{ user.name }}
    </div>
    <input #searchInput (input)="onSearch(searchInput.value)">
  `
})
export class UserListComponent implements OnInit {
  users$: Observable<User[]>;
  
  constructor(private userService: UserService) {}
  
  ngOnInit(): void {
    this.users$ = this.userService.getUsers();
  }
  
  onSearch(query: string): void {
    this.users$ = this.userService.searchUsers(query);
  }
}
```

**🐞 Fixes:** Always unsubscribe from Observables to prevent memory leaks, handle errors properly, use appropriate operators for performance, and avoid nested subscriptions.

---

2. What is an Observable in Angular? _(Asked in Wipro)_

**🧩 Foundation:** An Observable is a representation of a stream of data that can be observed over time, providing a way to handle asynchronous operations, events, and data transformations in a reactive manner.

**⚙️ Function:** Observables provide a standardized way to work with asynchronous data streams, enabling declarative programming patterns and efficient handling of complex data flows in Angular applications.

**🚀 Features:**
- Lazy evaluation (only executes when subscribed)
- Can emit multiple values over time
- Supports error handling and completion
- Can be cancelled/unsubscribed
- Rich operator ecosystem for transformation
- Integration with Angular's async pipe

**🔁 Flow:**
```typescript
// 1. Creating Observables
import { Observable, of, from, fromEvent, interval } from 'rxjs';

// From static values
const staticData$ = of('Hello', 'World', '!');

// From arrays
const arrayData$ = from([1, 2, 3, 4, 5]);

// From events
const click$ = fromEvent(document, 'click');

// From intervals
const timer$ = interval(1000);

// From promises
const promise$ = from(fetch('/api/data'));

// 2. Custom Observable
const customObservable$ = new Observable(observer => {
  observer.next('First value');
  observer.next('Second value');
  
  setTimeout(() => {
    observer.next('Async value');
    observer.complete();
  }, 1000);
  
  // Cleanup function
  return () => {
    console.log('Observable cleaned up');
  };
});

// 3. Using Observables in Components
@Component({
  template: `
    <div *ngFor="let item of data$ | async">
      {{ item }}
    </div>
    <button (click)="loadData()">Load Data</button>
  `
})
export class DataComponent implements OnInit, OnDestroy {
  data$: Observable<string[]>;
  private subscription = new Subscription();
  
  constructor(private dataService: DataService) {}
  
  ngOnInit(): void {
    // Using async pipe (recommended)
    this.data$ = this.dataService.getData();
    
    // Manual subscription (when needed)
    this.subscription.add(
      this.dataService.getData().subscribe({
        next: (data) => console.log('Data received:', data),
        error: (error) => console.error('Error:', error),
        complete: () => console.log('Data stream completed')
      })
    );
  }
  
  loadData(): void {
    this.data$ = this.dataService.getData();
  }
  
  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}

// 4. Observable with Error Handling
const errorHandlingObservable$ = new Observable(observer => {
  try {
    observer.next('Success value');
    throw new Error('Something went wrong');
  } catch (error) {
    observer.error(error);
  }
});

errorHandlingObservable$.subscribe({
  next: (value) => console.log('Success:', value),
  error: (error) => console.error('Error occurred:', error),
  complete: () => console.log('Completed')
});
```

**🐞 Fixes:** Always handle errors in Observables, unsubscribe to prevent memory leaks, use async pipe when possible, and implement proper cleanup in ngOnDestroy.

---

3. How is Observable different from Promise? _(Asked in TCS)_

**🧩 Foundation:** Observables and Promises are both used for handling asynchronous operations, but Observables provide more powerful features like multiple values, cancellation, and rich transformation operators, while Promises handle single asynchronous values.

**⚙️ Function:** Observables offer a more comprehensive solution for reactive programming with data streams, while Promises are simpler for one-time asynchronous operations with a single result.

**🚀 Features:**
- **Observable:** Can emit multiple values, cancellable, lazy execution, rich operators
- **Promise:** Single value, not cancellable, eager execution, limited transformation
- **Observable:** Supports retry, timeout, debounce operations
- **Promise:** One-time resolution or rejection
- **Observable:** Can be combined and transformed with operators
- **Promise:** Limited to .then() and .catch() chains

**🔁 Flow:**
```typescript
// 1. Promise vs Observable Comparison

// Promise - Single value, not cancellable
const promiseExample = new Promise((resolve, reject) => {
  setTimeout(() => {
    resolve('Promise result');
  }, 1000);
});

promiseExample.then(result => console.log(result));

// Observable - Multiple values, cancellable
const observableExample = new Observable(observer => {
  let count = 0;
  const interval = setInterval(() => {
    count++;
    observer.next(`Value ${count}`);
    
    if (count >= 3) {
      observer.complete();
      clearInterval(interval);
    }
  }, 1000);
  
  return () => clearInterval(interval); // Cleanup
});

const subscription = observableExample.subscribe({
  next: value => console.log(value),
  complete: () => console.log('Completed')
});

// 2. HTTP Request Comparison

// Using Promise
async function fetchDataWithPromise(): Promise<User[]> {
  try {
    const response = await fetch('/api/users');
    const data = await response.json();
    return data;
  } catch (error) {
    console.error('Error:', error);
    return [];
  }
}

// Using Observable
function fetchDataWithObservable(): Observable<User[]> {
  return this.http.get<User[]>('/api/users').pipe(
    catchError(error => {
      console.error('Error:', error);
      return of([]);
    })
  );
}

// 3. Real-world Usage Comparison

// Promise - Simple one-time operation
@Component({
  template: `
    <button (click)="loadDataWithPromise()">Load with Promise</button>
    <div *ngIf="promiseData">{{ promiseData }}</div>
  `
})
export class PromiseComponent {
  promiseData: string = '';
  
  async loadDataWithPromise(): Promise<void> {
    try {
      this.promiseData = await this.dataService.getDataPromise();
    } catch (error) {
      console.error('Error:', error);
    }
  }
}

// Observable - Complex data stream
@Component({
  template: `
    <button (click)="loadDataWithObservable()">Load with Observable</button>
    <div *ngFor="let item of observableData$ | async">
      {{ item }}
    </div>
  `
})
export class ObservableComponent {
  observableData$: Observable<string[]>;
  
  loadDataWithObservable(): void {
    this.observableData$ = this.dataService.getDataObservable().pipe(
      map(data => data.filter(item => item.active)),
      debounceTime(300),
      retry(3)
    );
  }
}

// 4. Advanced Observable Features

// Cancellation
const cancellableObservable$ = interval(1000).pipe(
  take(5), // Automatically completes after 5 emissions
  map(x => x * 2),
  filter(x => x > 5)
);

const subscription = cancellableObservable$.subscribe(console.log);

// Can be cancelled
setTimeout(() => subscription.unsubscribe(), 3000);

// Multiple operators
const complexObservable$ = of(1, 2, 3, 4, 5).pipe(
  map(x => x * 2),
  filter(x => x > 5),
  take(3),
  scan((acc, val) => acc + val, 0)
);

complexObservable$.subscribe(result => console.log('Sum:', result));

// 5. Converting between Promise and Observable
import { from, firstValueFrom } from 'rxjs';

// Promise to Observable
const promise = fetch('/api/data').then(res => res.json());
const observableFromPromise = from(promise);

// Observable to Promise
const observable = this.http.get('/api/data');
const promiseFromObservable = firstValueFrom(observable);
```

**🐞 Fixes:** Use Promises for simple one-time operations, use Observables for complex data streams, always handle errors and cleanup, and choose the right tool for the specific use case.

### Intermediate Questions

1. What are some commonly used RxJS operators and their use cases? _(Asked in Cognizant)_

**🧩 Foundation:** RxJS operators are pure functions that transform, filter, or combine Observables, providing a declarative way to handle complex data streams and asynchronous operations.

**⚙️ Function:** Operators enable data transformation, filtering, combination, and error handling in Observable streams, making it easier to build complex reactive applications with clean, readable code.

**🚀 Features:**
- **Transformation operators:** map, pluck, switchMap, mergeMap
- **Filtering operators:** filter, take, skip, distinct
- **Combination operators:** merge, concat, combineLatest, forkJoin
- **Error handling operators:** catchError, retry, timeout
- **Utility operators:** tap, delay, debounceTime, throttleTime

**🔁 Flow:**
```typescript
// 1. Transformation Operators
import { map, pluck, switchMap, mergeMap } from 'rxjs/operators';

// map - Transform each emitted value
const numbers$ = of(1, 2, 3, 4, 5);
numbers$.pipe(
  map(x => x * 2)
).subscribe(result => console.log(result)); // 2, 4, 6, 8, 10

// pluck - Extract specific property
const users$ = of(
  { id: 1, name: 'John', email: 'john@example.com' },
  { id: 2, name: 'Jane', email: 'jane@example.com' }
);
users$.pipe(
  pluck('name')
).subscribe(name => console.log(name)); // 'John', 'Jane'

// 2. Filtering Operators
import { filter, take, skip, distinct } from 'rxjs/operators';

// filter - Emit only values that pass a test
const numbers$ = of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
numbers$.pipe(
  filter(x => x % 2 === 0)
).subscribe(result => console.log(result)); // 2, 4, 6, 8, 10

// take - Emit only the first n values
const interval$ = interval(1000);
interval$.pipe(
  take(5)
).subscribe(result => console.log(result)); // 0, 1, 2, 3, 4

// distinct - Emit only unique values
const duplicateNumbers$ = of(1, 2, 2, 3, 3, 4, 5, 5);
duplicateNumbers$.pipe(
  distinct()
).subscribe(result => console.log(result)); // 1, 2, 3, 4, 5

// 3. Combination Operators
import { merge, concat, combineLatest, forkJoin } from 'rxjs/operators';

// merge - Combine multiple observables
const obs1$ = interval(1000).pipe(map(x => `A${x}`), take(3));
const obs2$ = interval(1500).pipe(map(x => `B${x}`), take(3));

merge(obs1$, obs2$).subscribe(result => console.log(result));
// Output: A0, B0, A1, A2, B1, B2

// combineLatest - Combine latest values from multiple observables
const name$ = of('John');
const age$ = of(25);

combineLatest([name$, age$]).pipe(
  map(([name, age]) => `${name} is ${age} years old`)
).subscribe(result => console.log(result)); // "John is 25 years old"

// forkJoin - Wait for all observables to complete
const user$ = this.http.get('/api/user/1');
const posts$ = this.http.get('/api/user/1/posts');

forkJoin([user$, posts$]).pipe(
  map(([user, posts]) => ({ user, posts }))
).subscribe(result => console.log(result));

// 4. Error Handling Operators
import { catchError, retry, timeout } from 'rxjs/operators';

// catchError - Handle errors gracefully
this.http.get('/api/users').pipe(
  catchError(error => {
    console.error('Error fetching users:', error);
    return of([]); // Return empty array as fallback
  })
).subscribe(users => console.log(users));

// retry - Retry failed requests
this.http.get('/api/users').pipe(
  retry(3) // Retry up to 3 times
).subscribe(users => console.log(users));

// timeout - Cancel if no response within time limit
this.http.get('/api/users').pipe(
  timeout(5000) // Cancel after 5 seconds
).subscribe(users => console.log(users));

// 5. Utility Operators
import { tap, delay, debounceTime, throttleTime } from 'rxjs/operators';

// tap - Perform side effects without affecting the stream
this.http.get('/api/users').pipe(
  tap(users => console.log('Users loaded:', users.length)),
  map(users => users.filter(user => user.active))
).subscribe(activeUsers => console.log(activeUsers));

// debounceTime - Emit only after a pause in emissions
fromEvent(document, 'input').pipe(
  debounceTime(300),
  map(event => (event.target as HTMLInputElement).value)
).subscribe(value => console.log('Search for:', value));

// throttleTime - Emit at most one value per time period
fromEvent(document, 'scroll').pipe(
  throttleTime(100) // Emit at most once per 100ms
).subscribe(() => console.log('Scrolled'));

// 6. Real-world Example
@Component({
  template: `
    <input #searchInput placeholder="Search users...">
    <div *ngFor="let user of users$ | async">
      {{ user.name }}
    </div>
  `
})
export class UserSearchComponent {
  users$: Observable<User[]>;
  
  constructor(private userService: UserService) {
    this.users$ = this.searchInput.nativeElement.valueChanges.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      switchMap(query => this.userService.searchUsers(query)),
      catchError(error => {
        console.error('Search error:', error);
        return of([]);
      })
    );
  }
}
```

**🐞 Fixes:** Always handle errors with catchError, use appropriate operators for performance, avoid nested subscriptions, and implement proper cleanup.

---

2. What is the difference between `map`, `switchMap`, `mergeMap`, and `concatMap`? _(Asked in TCS Digital)_

**🧩 Foundation:** These are transformation operators that handle Observable streams differently: `map` transforms values, while `switchMap`, `mergeMap`, and `concatMap` handle nested Observables with different strategies for managing multiple concurrent streams.

**⚙️ Function:** These operators enable complex data transformations and HTTP request handling, each providing different behavior for managing multiple asynchronous operations and preventing race conditions.

**🚀 Features:**
- **map:** Simple value transformation, no nested Observables
- **switchMap:** Cancels previous inner Observables, only latest result
- **mergeMap:** Processes all inner Observables concurrently
- **concatMap:** Processes inner Observables sequentially

**🔁 Flow:**
```typescript
// 1. map - Simple transformation
import { map, switchMap, mergeMap, concatMap } from 'rxjs/operators';

const numbers$ = of(1, 2, 3, 4, 5);

numbers$.pipe(
  map(x => x * 2)
).subscribe(result => console.log(result)); // 2, 4, 6, 8, 10

// 2. switchMap - Cancels previous requests
const searchInput$ = fromEvent(document.getElementById('search'), 'input').pipe(
  map(event => (event.target as HTMLInputElement).value),
  debounceTime(300)
);

searchInput$.pipe(
  switchMap(query => this.http.get(`/api/search?q=${query}`))
).subscribe(results => console.log('Search results:', results));

// 3. mergeMap - Concurrent processing
const userIds$ = of(1, 2, 3, 4, 5);

userIds$.pipe(
  mergeMap(id => this.http.get(`/api/users/${id}`))
).subscribe(user => console.log('User loaded:', user));

// 4. concatMap - Sequential processing
const actions$ = of('save', 'update', 'delete');

actions$.pipe(
  concatMap(action => this.http.post(`/api/${action}`, {}))
).subscribe(result => console.log('Action completed:', result));

// 5. Detailed Comparison Examples

// switchMap - Only latest result matters
const searchExample$ = of('a', 'ab', 'abc').pipe(
  switchMap(query => {
    console.log(`Searching for: ${query}`);
    return timer(1000).pipe(map(() => `Results for ${query}`));
  })
);

searchExample$.subscribe(result => console.log(result));
// Output: Only "Results for abc" (previous searches cancelled)

// mergeMap - All results processed
const mergeExample$ = of('a', 'b', 'c').pipe(
  mergeMap(letter => {
    console.log(`Processing: ${letter}`);
    return timer(1000).pipe(map(() => `Processed ${letter}`));
  })
);

mergeExample$.subscribe(result => console.log(result));
// Output: "Processed a", "Processed b", "Processed c" (all processed)

// concatMap - Sequential processing
const concatExample$ = of('a', 'b', 'c').pipe(
  concatMap(letter => {
    console.log(`Processing: ${letter}`);
    return timer(1000).pipe(map(() => `Processed ${letter}`));
  })
);

concatExample$.subscribe(result => console.log(result));
// Output: "Processed a", "Processed b", "Processed c" (sequential)

// 6. Real-world HTTP Examples

// switchMap for search (cancels previous requests)
@Component({
  template: `
    <input #searchInput placeholder="Search...">
    <div *ngFor="let result of searchResults$ | async">
      {{ result.name }}
    </div>
  `
})
export class SearchComponent {
  searchResults$: Observable<SearchResult[]>;
  
  constructor(private searchService: SearchService) {
    this.searchResults$ = this.searchInput.nativeElement.valueChanges.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      switchMap(query => this.searchService.search(query))
    );
  }
}

// mergeMap for parallel API calls
@Component({
  template: `
    <button (click)="loadUserData()">Load User Data</button>
    <div *ngFor="let data of userData$ | async">
      {{ data.type }}: {{ data.value }}
    </div>
  `
})
export class UserDataComponent {
  userData$: Observable<any[]>;
  
  constructor(private userService: UserService) {}
  
  loadUserData(): void {
    const userId = 1;
    
    this.userData$ = of(userId).pipe(
      mergeMap(id => forkJoin({
        profile: this.userService.getProfile(id),
        posts: this.userService.getPosts(id),
        friends: this.userService.getFriends(id)
      })),
      map(data => [
        { type: 'Profile', value: data.profile.name },
        { type: 'Posts', value: data.posts.length },
        { type: 'Friends', value: data.friends.length }
      ])
    );
  }
}

// concatMap for sequential operations
@Component({
  template: `
    <button (click)="processUser()">Process User</button>
    <div>{{ status }}</div>
  `
})
export class UserProcessorComponent {
  status = '';
  
  constructor(private userService: UserService) {}
  
  processUser(): void {
    const userId = 1;
    
    of(userId).pipe(
      concatMap(id => this.userService.validateUser(id)),
      concatMap(() => this.userService.updateUser(userId)),
      concatMap(() => this.userService.notifyUser(userId))
    ).subscribe({
      next: (result) => this.status = `Step completed: ${result}`,
      error: (error) => this.status = `Error: ${error.message}`,
      complete: () => this.status = 'All steps completed'
    });
  }
}
```

**🐞 Fixes:** Use switchMap for search to cancel previous requests, use mergeMap for parallel operations, use concatMap for sequential operations, and always handle errors appropriately.

---

3. What is the difference between `Subject`, `BehaviorSubject`, and `ReplaySubject`? _(Asked in Infosys)_

**🧩 Foundation:** Subjects are both Observable and Observer, allowing them to emit values and be subscribed to. Different Subject types provide varying behavior for handling initial values, caching, and subscription timing.

**⚙️ Function:** Subjects enable communication between components, state management, and event handling in Angular applications, with each type serving specific use cases based on their behavior characteristics.

**🚀 Features:**
- **Subject:** No initial value, no caching, only emits to current subscribers
- **BehaviorSubject:** Has initial value, caches latest value, emits to new subscribers
- **ReplaySubject:** Caches multiple values, configurable buffer size, emits to new subscribers

**🔁 Flow:**
```typescript
// 1. Subject - No initial value, no caching
import { Subject, BehaviorSubject, ReplaySubject } from 'rxjs';

const subject = new Subject<string>();

// Subscribe before emission
subject.subscribe(value => console.log('Subscriber 1:', value));

// Emit values
subject.next('Hello');
subject.next('World');

// Subscribe after emission (won't receive previous values)
subject.subscribe(value => console.log('Subscriber 2:', value));

subject.next('New Value');

// Output:
// Subscriber 1: Hello
// Subscriber 1: World
// Subscriber 1: New Value
// Subscriber 2: New Value

// 2. BehaviorSubject - Has initial value, caches latest
const behaviorSubject = new BehaviorSubject<string>('Initial Value');

// Subscribe before emission
behaviorSubject.subscribe(value => console.log('Subscriber 1:', value));

// Emit values
behaviorSubject.next('Updated Value');
behaviorSubject.next('Another Value');

// Subscribe after emission (receives latest value)
behaviorSubject.subscribe(value => console.log('Subscriber 2:', value));

// Output:
// Subscriber 1: Initial Value
// Subscriber 1: Updated Value
// Subscriber 1: Another Value
// Subscriber 2: Another Value

// 3. ReplaySubject - Caches multiple values
const replaySubject = new ReplaySubject<string>(2); // Buffer size of 2

// Emit values
replaySubject.next('First Value');
replaySubject.next('Second Value');
replaySubject.next('Third Value');

// Subscribe after emission (receives last 2 values)
replaySubject.subscribe(value => console.log('Subscriber:', value));

// Output:
// Subscriber: Second Value
// Subscriber: Third Value

// 4. Real-world Examples

// Subject for event communication
@Injectable({
  providedIn: 'root'
})
export class EventService {
  private userLoggedInSubject = new Subject<User>();
  public userLoggedIn$ = this.userLoggedInSubject.asObservable();
  
  notifyUserLogin(user: User): void {
    this.userLoggedInSubject.next(user);
  }
}

@Component({
  template: `
    <div *ngIf="currentUser">
      Welcome, {{ currentUser.name }}!
    </div>
  `
})
export class HeaderComponent implements OnInit, OnDestroy {
  currentUser: User | null = null;
  private subscription = new Subscription();
  
  constructor(private eventService: EventService) {}
  
  ngOnInit(): void {
    this.subscription.add(
      this.eventService.userLoggedIn$.subscribe(user => {
        this.currentUser = user;
      })
    );
  }
  
  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}

// BehaviorSubject for state management
@Injectable({
  providedIn: 'root'
})
export class UserStateService {
  private currentUserSubject = new BehaviorSubject<User | null>(null);
  public currentUser$ = this.currentUserSubject.asObservable();
  
  setCurrentUser(user: User): void {
    this.currentUserSubject.next(user);
  }
  
  getCurrentUser(): User | null {
    return this.currentUserSubject.value;
  }
  
  logout(): void {
    this.currentUserSubject.next(null);
  }
}

@Component({
  template: `
    <div *ngIf="currentUser$ | async as user">
      Welcome, {{ user.name }}!
      <button (click)="logout()">Logout</button>
    </div>
  `
})
export class UserProfileComponent {
  currentUser$ = this.userStateService.currentUser$;
  
  constructor(private userStateService: UserStateService) {}
  
  logout(): void {
    this.userStateService.logout();
  }
}

// ReplaySubject for caching API responses
@Injectable({
  providedIn: 'root'
})
export class CacheService {
  private cacheSubject = new ReplaySubject<any>(3); // Cache last 3 responses
  public cache$ = this.cacheSubject.asObservable();
  
  setCache(key: string, data: any): void {
    this.cacheSubject.next({ key, data, timestamp: Date.now() });
  }
  
  getCache(): Observable<any> {
    return this.cache$;
  }
}

@Component({
  template: `
    <div *ngFor="let item of cachedData$ | async">
      {{ item.key }}: {{ item.data }}
    </div>
  `
})
export class CacheDisplayComponent {
  cachedData$ = this.cacheService.getCache();
  
  constructor(private cacheService: CacheService) {}
}

// 5. Advanced Usage Patterns

// Combining Subjects with operators
const searchSubject = new Subject<string>();
const searchResults$ = searchSubject.pipe(
  debounceTime(300),
  distinctUntilChanged(),
  switchMap(query => this.searchService.search(query))
);

// Error handling with Subjects
const dataSubject = new Subject<any>();
const safeData$ = dataSubject.pipe(
  catchError(error => {
    console.error('Error in data stream:', error);
    return of(null);
  })
);

// 6. Component Communication
@Component({
  selector: 'app-parent',
  template: `
    <app-child (dataEvent)="handleData($event)"></app-child>
    <div>{{ receivedData }}</div>
  `
})
export class ParentComponent {
  receivedData: string = '';
  
  handleData(data: string): void {
    this.receivedData = data;
  }
}

@Component({
  selector: 'app-child',
  template: `
    <button (click)="sendData()">Send Data</button>
  `
})
export class ChildComponent {
  @Output() dataEvent = new EventEmitter<string>();
  
  sendData(): void {
    this.dataEvent.emit('Data from child');
  }
}
```

**🐞 Fixes:** Choose the right Subject type for your use case, always unsubscribe to prevent memory leaks, handle errors appropriately, and use BehaviorSubject for state that needs an initial value.

---

4. How do you cancel subscriptions in Angular and why is it important? _(Asked in HCL)_

**🧩 Foundation:** Subscriptions in Angular must be properly cancelled to prevent memory leaks, as active subscriptions continue to consume memory and can cause performance issues, especially in long-running applications.

**⚙️ Function:** Proper subscription management ensures efficient memory usage, prevents memory leaks, and maintains application performance by cleaning up resources when components are destroyed or subscriptions are no longer needed.

**🚀 Features:**
- Manual unsubscription using unsubscribe()
- Automatic unsubscription with async pipe
- Using takeUntil operator with destroy subject
- Using takeWhile operator with component state
- Subscription management with Subscription class

**🔁 Flow:**
```typescript
// 1. Manual Unsubscription
@Component({
  template: `
    <div *ngFor="let item of data">
      {{ item.name }}
    </div>
  `
})
export class DataComponent implements OnInit, OnDestroy {
  data: any[] = [];
  private subscription = new Subscription();
  
  constructor(private dataService: DataService) {}
  
  ngOnInit(): void {
    this.subscription.add(
      this.dataService.getData().subscribe(data => {
        this.data = data;
      })
    );
  }
  
  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }
}

// 2. Using Async Pipe (Recommended)
@Component({
  template: `
    <div *ngFor="let item of data$ | async">
      {{ item.name }}
    </div>
  `
})
export class AsyncDataComponent {
  data$ = this.dataService.getData();
  
  constructor(private dataService: DataService) {}
  // No need for manual unsubscription - async pipe handles it
}

// 3. Using takeUntil Operator
@Component({
  template: `
    <div *ngFor="let item of data">
      {{ item.name }}
    </div>
  `
})
export class TakeUntilComponent implements OnInit, OnDestroy {
  data: any[] = [];
  private destroy$ = new Subject<void>();
  
  constructor(private dataService: DataService) {}
  
  ngOnInit(): void {
    this.dataService.getData().pipe(
      takeUntil(this.destroy$)
    ).subscribe(data => {
      this.data = data;
    });
  }
  
  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
}

// 4. Using takeWhile Operator
@Component({
  template: `
    <div *ngFor="let item of data">
      {{ item.name }}
    </div>
  `
})
export class TakeWhileComponent implements OnInit {
  data: any[] = [];
  private isAlive = true;
  
  constructor(private dataService: DataService) {}
  
  ngOnInit(): void {
    this.dataService.getData().pipe(
      takeWhile(() => this.isAlive)
    ).subscribe(data => {
      this.data = data;
    });
  }
  
  ngOnDestroy(): void {
    this.isAlive = false;
  }
}

// 5. Multiple Subscriptions Management
@Component({
  template: `
    <div>
      <div *ngFor="let user of users">{{ user.name }}</div>
      <div *ngFor="let post of posts">{{ post.title }}</div>
    </div>
  `
})
export class MultipleSubscriptionsComponent implements OnInit, OnDestroy {
  users: User[] = [];
  posts: Post[] = [];
  private subscriptions = new Subscription();
  
  constructor(
    private userService: UserService,
    private postService: PostService
  ) {}
  
  ngOnInit(): void {
    // Add multiple subscriptions
    this.subscriptions.add(
      this.userService.getUsers().subscribe(users => {
        this.users = users;
      })
    );
    
    this.subscriptions.add(
      this.postService.getPosts().subscribe(posts => {
        this.posts = posts;
      })
    );
    
    // Add interval subscription
    this.subscriptions.add(
      interval(5000).subscribe(() => {
        this.refreshData();
      })
    );
  }
  
  ngOnDestroy(): void {
    this.subscriptions.unsubscribe();
  }
  
  private refreshData(): void {
    // Refresh logic
  }
}

// 6. Advanced Patterns

// Using takeUntil with multiple observables
@Component({
  template: `
    <div>
      <div *ngFor="let item of data$ | async">{{ item.name }}</div>
      <div>{{ status$ | async }}</div>
    </div>
  `
})
export class AdvancedComponent implements OnInit, OnDestroy {
  data$: Observable<any[]>;
  status$: Observable<string>;
  private destroy$ = new Subject<void>();
  
  constructor(private dataService: DataService) {}
  
  ngOnInit(): void {
    this.data$ = this.dataService.getData().pipe(
      takeUntil(this.destroy$)
    );
    
    this.status$ = this.dataService.getStatus().pipe(
      takeUntil(this.destroy$)
    );
  }
  
  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
}

// Using firstValueFrom for one-time subscriptions
@Component({
  template: `
    <div>{{ userData }}</div>
  `
})
export class OneTimeComponent implements OnInit {
  userData: any;
  
  constructor(private userService: UserService) {}
  
  async ngOnInit(): Promise<void> {
    try {
      this.userData = await firstValueFrom(
        this.userService.getUser(1)
      );
    } catch (error) {
      console.error('Error loading user:', error);
    }
  }
}

// 7. Service-level Subscription Management
@Injectable({
  providedIn: 'root'
})
export class DataService {
  private cache$ = new BehaviorSubject<any[]>([]);
  
  getData(): Observable<any[]> {
    return this.cache$.asObservable().pipe(
      switchMap(cachedData => {
        if (cachedData.length > 0) {
          return of(cachedData);
        }
        return this.fetchDataFromAPI();
      })
    );
  }
  
  private fetchDataFromAPI(): Observable<any[]> {
    return this.http.get<any[]>('/api/data').pipe(
      tap(data => this.cache$.next(data))
    );
  }
}
```

**🐞 Fixes:** Always use async pipe when possible, implement ngOnDestroy for manual subscriptions, use takeUntil pattern for multiple subscriptions, and avoid creating subscriptions in constructors.

### Advanced Questions

1. What is `takeUntil` and how does it help in unsubscribing? _(Asked in HCL)_

**🧩 Foundation:** `takeUntil` is an RxJS operator that automatically unsubscribes from an Observable when a notifier Observable emits a value, providing a clean and declarative way to manage subscription lifecycle.

**⚙️ Function:** `takeUntil` eliminates the need for manual subscription management by automatically cancelling subscriptions when a component is destroyed or when a specific condition is met, preventing memory leaks and improving code maintainability.

**🚀 Features:**
- Automatic unsubscription when notifier emits
- Declarative subscription management
- Prevents memory leaks
- Clean component lifecycle handling
- Works with multiple subscriptions
- Reduces boilerplate code

**🔁 Flow:**
```typescript
// 1. Basic takeUntil Usage
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';

@Component({
  template: `
    <div *ngFor="let item of data">
      {{ item.name }}
    </div>
  `
})
export class BasicTakeUntilComponent implements OnInit, OnDestroy {
  data: any[] = [];
  private destroy$ = new Subject<void>();
  
  constructor(private dataService: DataService) {}
  
  ngOnInit(): void {
    this.dataService.getData().pipe(
      takeUntil(this.destroy$)
    ).subscribe(data => {
      this.data = data;
    });
  }
  
  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
}

// 2. Multiple Subscriptions with takeUntil
@Component({
  template: `
    <div>
      <div *ngFor="let user of users">{{ user.name }}</div>
      <div *ngFor="let post of posts">{{ post.title }}</div>
      <div>{{ status }}</div>
    </div>
  `
})
export class MultipleSubscriptionsComponent implements OnInit, OnDestroy {
  users: User[] = [];
  posts: Post[] = [];
  status: string = '';
  private destroy$ = new Subject<void>();
  
  constructor(
    private userService: UserService,
    private postService: PostService,
    private statusService: StatusService
  ) {}
  
  ngOnInit(): void {
    // All subscriptions automatically unsubscribed when destroy$ emits
    this.userService.getUsers().pipe(
      takeUntil(this.destroy$)
    ).subscribe(users => {
      this.users = users;
    });
    
    this.postService.getPosts().pipe(
      takeUntil(this.destroy$)
    ).subscribe(posts => {
      this.posts = posts;
    });
    
    this.statusService.getStatus().pipe(
      takeUntil(this.destroy$)
    ).subscribe(status => {
      this.status = status;
    });
    
    // Interval subscription also automatically cleaned up
    interval(5000).pipe(
      takeUntil(this.destroy$)
    ).subscribe(() => {
      this.refreshData();
    });
  }
  
  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
  
  private refreshData(): void {
    // Refresh logic
  }
}

// 3. Advanced takeUntil Patterns

// Using takeUntil with route changes
@Component({
  template: `
    <div *ngFor="let item of data">
      {{ item.name }}
    </div>
  `
})
export class RouteAwareComponent implements OnInit {
  data: any[] = [];
  
  constructor(
    private dataService: DataService,
    private router: Router
  ) {}
  
  ngOnInit(): void {
    this.dataService.getData().pipe(
      takeUntil(this.router.events.pipe(
        filter(event => event instanceof NavigationEnd)
      ))
    ).subscribe(data => {
      this.data = data;
    });
  }
}

// Using takeUntil with form value changes
@Component({
  template: `
    <form [formGroup]="form">
      <input formControlName="search">
    </form>
    <div *ngFor="let result of searchResults">
      {{ result.name }}
    </div>
  `
})
export class FormSearchComponent implements OnInit, OnDestroy {
  form: FormGroup;
  searchResults: any[] = [];
  private destroy$ = new Subject<void>();
  
  constructor(
    private fb: FormBuilder,
    private searchService: SearchService
  ) {
    this.form = this.fb.group({
      search: ['']
    });
  }
  
  ngOnInit(): void {
    this.form.get('search')?.valueChanges.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      switchMap(query => this.searchService.search(query)),
      takeUntil(this.destroy$)
    ).subscribe(results => {
      this.searchResults = results;
    });
  }
  
  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
}

// 4. Custom takeUntil Notifiers

// Using timer as notifier
@Component({
  template: `
    <div>{{ message }}</div>
  `
})
export class TimedComponent implements OnInit {
  message: string = '';
  
  constructor(private messageService: MessageService) {}
  
  ngOnInit(): void {
    this.messageService.getMessage().pipe(
      takeUntil(timer(30000)) // Auto-unsubscribe after 30 seconds
    ).subscribe(message => {
      this.message = message;
    });
  }
}

// Using external condition
@Component({
  template: `
    <div *ngFor="let item of data">
      {{ item.name }}
    </div>
    <button (click)="stopUpdates()">Stop Updates</button>
  `
})
export class ConditionalComponent implements OnInit {
  data: any[] = [];
  private stopUpdates$ = new Subject<void>();
  
  constructor(private dataService: DataService) {}
  
  ngOnInit(): void {
    this.dataService.getData().pipe(
      takeUntil(this.stopUpdates$)
    ).subscribe(data => {
      this.data = data;
    });
  }
  
  stopUpdates(): void {
    this.stopUpdates$.next();
    this.stopUpdates$.complete();
  }
}

// 5. Service-level takeUntil

// Using takeUntil in services
@Injectable({
  providedIn: 'root'
})
export class DataService {
  private dataSubject = new BehaviorSubject<any[]>([]);
  public data$ = this.dataSubject.asObservable();
  
  startDataStream(stopSignal$: Observable<any>): void {
    interval(1000).pipe(
      map(() => this.generateData()),
      takeUntil(stopSignal$)
    ).subscribe(data => {
      this.dataSubject.next(data);
    });
  }
  
  private generateData(): any[] {
    return [
      { id: 1, name: 'Item 1' },
      { id: 2, name: 'Item 2' }
    ];
  }
}

@Component({
  template: `
    <div *ngFor="let item of data$ | async">
      {{ item.name }}
    </div>
  `
})
export class ServiceConsumerComponent implements OnInit, OnDestroy {
  data$ = this.dataService.data$;
  private destroy$ = new Subject<void>();
  
  constructor(private dataService: DataService) {}
  
  ngOnInit(): void {
    this.dataService.startDataStream(this.destroy$);
  }
  
  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
}

// 6. Error Handling with takeUntil
@Component({
  template: `
    <div *ngFor="let item of data">
      {{ item.name }}
    </div>
    <div *ngIf="error">{{ error }}</div>
  `
})
export class ErrorHandlingComponent implements OnInit, OnDestroy {
  data: any[] = [];
  error: string = '';
  private destroy$ = new Subject<void>();
  
  constructor(private dataService: DataService) {}
  
  ngOnInit(): void {
    this.dataService.getData().pipe(
      takeUntil(this.destroy$),
      catchError(error => {
        this.error = 'Failed to load data';
        return of([]);
      })
    ).subscribe(data => {
      this.data = data;
    });
  }
  
  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
```

**🐞 Fixes:** Always complete the destroy Subject, use takeUntil consistently across all subscriptions, handle errors before takeUntil, and ensure the notifier Observable is properly managed.

---

2. How do you implement WebSocket integration with RxJS in Angular? _(Asked in Deloitte)_

**🧩 Foundation:** WebSocket integration with RxJS involves creating Observable streams from WebSocket connections, enabling real-time bidirectional communication with automatic reconnection, error handling, and message processing.

**⚙️ Function:** WebSocket integration provides real-time data updates, live notifications, and interactive features while maintaining connection stability and handling network issues gracefully.

**🚀 Features:**
- Automatic reconnection on connection loss
- Message queuing and retry mechanisms
- Connection state management
- Error handling and recovery
- Message type filtering and routing
- Heartbeat and ping/pong support

**🔁 Flow:**
```typescript
// 1. Basic WebSocket Service
import { Injectable } from '@angular/core';
import { Observable, Subject, BehaviorSubject, timer } from 'rxjs';
import { webSocket, WebSocketSubject } from 'rxjs/webSocket';
import { retryWhen, delay, tap, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class WebSocketService {
  private socket$: WebSocketSubject<any>;
  private messagesSubject = new Subject<any>();
  public messages$ = this.messagesSubject.asObservable();
  private connectionStatus$ = new BehaviorSubject<boolean>(false);
  
  constructor() {}
  
  connect(url: string): Observable<any> {
    if (!this.socket$ || this.socket$.closed) {
      this.socket$ = webSocket({
        url: url,
        openObserver: {
          next: () => {
            console.log('WebSocket connected');
            this.connectionStatus$.next(true);
          }
        },
        closeObserver: {
          next: () => {
            console.log('WebSocket disconnected');
            this.connectionStatus$.next(false);
          }
        }
      });
      
      this.socket$.pipe(
        tap(message => this.messagesSubject.next(message)),
        catchError(error => {
          console.error('WebSocket error:', error);
          return [];
        }),
        retryWhen(errors => 
          errors.pipe(
            tap(err => console.log('Retrying connection...')),
            delay(1000)
          )
        )
      ).subscribe();
    }
    
    return this.messages$;
  }
  
  sendMessage(message: any): void {
    if (this.socket$ && !this.socket$.closed) {
      this.socket$.next(message);
    }
  }
  
  disconnect(): void {
    if (this.socket$) {
      this.socket$.complete();
    }
  }
  
  getConnectionStatus(): Observable<boolean> {
    return this.connectionStatus$.asObservable();
  }
}

// 2. Advanced WebSocket Service with Reconnection
@Injectable({
  providedIn: 'root'
})
export class AdvancedWebSocketService {
  private socket$: WebSocketSubject<any>;
  private reconnectAttempts = 0;
  private maxReconnectAttempts = 5;
  private reconnectInterval = 1000;
  private messagesSubject = new Subject<any>();
  public messages$ = this.messagesSubject.asObservable();
  private connectionStatus$ = new BehaviorSubject<'connected' | 'disconnected' | 'connecting'>('disconnected');
  
  connect(url: string): Observable<any> {
    this.connectionStatus$.next('connecting');
    
    this.socket$ = webSocket({
      url: url,
      openObserver: {
        next: () => {
          console.log('WebSocket connected');
          this.connectionStatus$.next('connected');
          this.reconnectAttempts = 0;
        }
      },
      closeObserver: {
        next: () => {
          console.log('WebSocket disconnected');
          this.connectionStatus$.next('disconnected');
          this.attemptReconnect(url);
        }
      }
    });
    
    this.socket$.pipe(
      tap(message => this.messagesSubject.next(message)),
      catchError(error => {
        console.error('WebSocket error:', error);
        this.connectionStatus$.next('disconnected');
        this.attemptReconnect(url);
        return [];
      })
    ).subscribe();
    
    return this.messages$;
  }
  
  private attemptReconnect(url: string): void {
    if (this.reconnectAttempts < this.maxReconnectAttempts) {
      this.reconnectAttempts++;
      console.log(`Attempting to reconnect... (${this.reconnectAttempts}/${this.maxReconnectAttempts})`);
      
      timer(this.reconnectInterval * this.reconnectAttempts).pipe(
        take(1)
      ).subscribe(() => {
        this.connect(url);
      });
    } else {
      console.error('Max reconnection attempts reached');
    }
  }
  
  sendMessage(message: any): void {
    if (this.socket$ && !this.socket$.closed) {
      this.socket$.next(message);
    } else {
      console.warn('WebSocket not connected, message not sent');
    }
  }
  
  disconnect(): void {
    if (this.socket$) {
      this.socket$.complete();
    }
  }
  
  getConnectionStatus(): Observable<'connected' | 'disconnected' | 'connecting'> {
    return this.connectionStatus$.asObservable();
  }
}

// 3. Component Integration
@Component({
  template: `
    <div [class.connected]="isConnected" class="connection-status">
      {{ connectionStatus }}
    </div>
    
    <div class="messages">
      <div *ngFor="let message of messages" class="message">
        <strong>{{ message.sender }}:</strong> {{ message.content }}
      </div>
    </div>
    
    <div class="input-area">
      <input #messageInput placeholder="Type a message..." (keyup.enter)="sendMessage(messageInput.value)">
      <button (click)="sendMessage(messageInput.value)">Send</button>
    </div>
  `,
  styles: [`
    .connection-status {
      padding: 10px;
      margin-bottom: 10px;
      border-radius: 4px;
    }
    .connection-status.connected {
      background-color: #d4edda;
      color: #155724;
    }
    .connection-status:not(.connected) {
      background-color: #f8d7da;
      color: #721c24;
    }
  `]
})
export class ChatComponent implements OnInit, OnDestroy {
  messages: any[] = [];
  isConnected = false;
  connectionStatus = 'Disconnected';
  private destroy$ = new Subject<void>();
  
  constructor(private webSocketService: AdvancedWebSocketService) {}
  
  ngOnInit(): void {
    // Connect to WebSocket
    this.webSocketService.connect('ws://localhost:8080/chat').pipe(
      takeUntil(this.destroy$)
    ).subscribe(message => {
      this.messages.push(message);
    });
    
    // Monitor connection status
    this.webSocketService.getConnectionStatus().pipe(
      takeUntil(this.destroy$)
    ).subscribe(status => {
      this.isConnected = status === 'connected';
      this.connectionStatus = status.charAt(0).toUpperCase() + status.slice(1);
    });
  }
  
  sendMessage(content: string): void {
    if (content.trim() && this.isConnected) {
      const message = {
        type: 'chat',
        content: content.trim(),
        timestamp: new Date().toISOString()
      };
      
      this.webSocketService.sendMessage(message);
      // Clear input
      const input = document.querySelector('input') as HTMLInputElement;
      if (input) input.value = '';
    }
  }
  
  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
    this.webSocketService.disconnect();
  }
}

// 4. Real-time Dashboard with WebSocket
@Component({
  template: `
    <div class="dashboard">
      <div class="status-card">
        <h3>Connection Status</h3>
        <div [class]="connectionStatus">{{ connectionStatus }}</div>
      </div>
      
      <div class="metrics">
        <div class="metric">
          <h4>Active Users</h4>
          <div class="value">{{ activeUsers }}</div>
        </div>
        <div class="metric">
          <h4>Messages Sent</h4>
          <div class="value">{{ messagesSent }}</div>
        </div>
        <div class="metric">
          <h4>System Load</h4>
          <div class="value">{{ systemLoad }}%</div>
        </div>
      </div>
      
      <div class="live-feed">
        <h3>Live Activity Feed</h3>
        <div *ngFor="let activity of activities" class="activity">
          <span class="timestamp">{{ activity.timestamp | date:'HH:mm:ss' }}</span>
          <span class="message">{{ activity.message }}</span>
        </div>
      </div>
    </div>
  `
})
export class DashboardComponent implements OnInit, OnDestroy {
  connectionStatus = 'Disconnected';
  activeUsers = 0;
  messagesSent = 0;
  systemLoad = 0;
  activities: any[] = [];
  private destroy$ = new Subject<void>();
  
  constructor(private webSocketService: AdvancedWebSocketService) {}
  
  ngOnInit(): void {
    this.webSocketService.connect('ws://localhost:8080/dashboard').pipe(
      takeUntil(this.destroy$)
    ).subscribe(message => {
      this.handleMessage(message);
    });
    
    this.webSocketService.getConnectionStatus().pipe(
      takeUntil(this.destroy$)
    ).subscribe(status => {
      this.connectionStatus = status.charAt(0).toUpperCase() + status.slice(1);
    });
  }
  
  private handleMessage(message: any): void {
    switch (message.type) {
      case 'metrics':
        this.activeUsers = message.data.activeUsers;
        this.messagesSent = message.data.messagesSent;
        this.systemLoad = message.data.systemLoad;
        break;
      case 'activity':
        this.activities.unshift({
          timestamp: new Date(),
          message: message.data.message
        });
        // Keep only last 50 activities
        if (this.activities.length > 50) {
          this.activities = this.activities.slice(0, 50);
        }
        break;
    }
  }
  
  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
    this.webSocketService.disconnect();
  }
}

// 5. WebSocket with Message Types
interface WebSocketMessage {
  type: 'chat' | 'notification' | 'status' | 'error';
  data: any;
  timestamp: string;
}

@Injectable({
  providedIn: 'root'
})
export class TypedWebSocketService {
  private socket$: WebSocketSubject<WebSocketMessage>;
  private messageSubjects = {
    chat: new Subject<any>(),
    notification: new Subject<any>(),
    status: new Subject<any>(),
    error: new Subject<any>()
  };
  
  public chat$ = this.messageSubjects.chat.asObservable();
  public notifications$ = this.messageSubjects.notification.asObservable();
  public status$ = this.messageSubjects.status.asObservable();
  public errors$ = this.messageSubjects.error.asObservable();
  
  connect(url: string): void {
    this.socket$ = webSocket<WebSocketMessage>(url);
    
    this.socket$.pipe(
      tap(message => {
        const subject = this.messageSubjects[message.type];
        if (subject) {
          subject.next(message.data);
        }
      }),
      catchError(error => {
        this.messageSubjects.error.next(error);
        return [];
      })
    ).subscribe();
  }
  
  sendMessage(type: string, data: any): void {
    if (this.socket$ && !this.socket$.closed) {
      const message: WebSocketMessage = {
        type: type as any,
        data: data,
        timestamp: new Date().toISOString()
      };
      this.socket$.next(message);
    }
  }
  
  disconnect(): void {
    if (this.socket$) {
      this.socket$.complete();
    }
  }
}
```

**🐞 Fixes:** Always implement reconnection logic, handle connection errors gracefully, use proper message typing, implement heartbeat mechanisms, and clean up connections on component destruction.

### Tough Questions

1. You need to fetch data from multiple endpoints in sequence and in parallel. How would you implement this using RxJS? _(Asked in Deloitte)_

### Situational / Real-world Questions

1. You’re tasked with implementing a type-ahead search box. How would RxJS help? _(Asked in Accenture)_

---

## 8. Change Detection

### Basic Questions

1. What is change detection in Angular? _(Asked in Infosys, Capgemini)_

**🧩 Foundation:** Change detection is Angular's mechanism for tracking and updating the view when data changes, ensuring the UI stays synchronized with the component's data model by detecting changes and re-rendering affected parts of the DOM.

**⚙️ Function:** Change detection automatically monitors component properties, detects when they change, and updates the corresponding DOM elements to reflect those changes, maintaining data consistency between the component state and the user interface.

**🚀 Features:**
- Automatic detection of data changes
- Zone.js integration for automatic triggering
- OnPush and Default change detection strategies
- Manual change detection triggering
- Performance optimization through change detection cycles
- Integration with lifecycle hooks

**🔁 Flow:**
```typescript
// 1. Basic Change Detection
@Component({
  template: `
    <div>
      <h2>{{ title }}</h2>
      <p>{{ message }}</p>
      <button (click)="updateMessage()">Update Message</button>
      <div *ngFor="let item of items">
        {{ item.name }}
      </div>
    </div>
  `
})
export class ChangeDetectionComponent {
  title = 'Change Detection Demo';
  message = 'Initial message';
  items = [
    { id: 1, name: 'Item 1' },
    { id: 2, name: 'Item 2' }
  ];
  
  updateMessage(): void {
    this.message = 'Updated message'; // Triggers change detection
  }
  
  addItem(): void {
    this.items.push({ id: 3, name: 'Item 3' }); // Triggers change detection
  }
}

// 2. Change Detection with OnPush Strategy
@Component({
  selector: 'app-user-list',
  changeDetection: ChangeDetectionStrategy.OnPush,
  template: `
    <div *ngFor="let user of users">
      {{ user.name }} - {{ user.email }}
    </div>
    <button (click)="addUser()">Add User</button>
  `
})
export class UserListComponent {
  @Input() users: User[] = [];
  
  constructor(private cdr: ChangeDetectorRef) {}
  
  addUser(): void {
    // ❌ This won't trigger change detection with OnPush
    this.users.push({ name: 'New User', email: 'new@example.com' });
    
    // ✅ This will trigger change detection
    this.users = [...this.users, { name: 'New User', email: 'new@example.com' }];
    
    // ✅ Or manually trigger change detection
    this.cdr.detectChanges();
  }
}

// 3. Manual Change Detection
@Component({
  template: `
    <div>{{ counter }}</div>
    <button (click)="increment()">Increment</button>
  `
})
export class ManualChangeDetectionComponent {
  counter = 0;
  
  constructor(private cdr: ChangeDetectorRef) {}
  
  increment(): void {
    this.counter++;
    
    // Manual change detection
    this.cdr.detectChanges();
  }
  
  // Detach from change detection
  detachFromChangeDetection(): void {
    this.cdr.detach();
  }
  
  // Reattach to change detection
  reattachToChangeDetection(): void {
    this.cdr.reattach();
  }
  
  // Mark for change detection
  markForCheck(): void {
    this.cdr.markForCheck();
  }
}

// 4. Change Detection with Observables
@Component({
  template: `
    <div *ngFor="let user of users$ | async">
      {{ user.name }}
    </div>
  `
})
export class ObservableChangeDetectionComponent {
  users$ = this.userService.getUsers();
  
  constructor(private userService: UserService) {}
  
  // Async pipe automatically handles change detection
  // No manual subscription/unsubscription needed
}

// 5. Change Detection Lifecycle
@Component({
  template: `
    <div>{{ data }}</div>
  `
})
export class LifecycleChangeDetectionComponent implements OnInit, OnChanges, DoCheck {
  @Input() inputData: string = '';
  data: string = '';
  
  constructor(private cdr: ChangeDetectorRef) {}
  
  ngOnInit(): void {
    // Change detection runs after ngOnInit
    this.data = 'Initialized';
  }
  
  ngOnChanges(changes: SimpleChanges): void {
    // Change detection runs after ngOnChanges
    if (changes['inputData']) {
      this.data = changes['inputData'].currentValue;
    }
  }
  
  ngDoCheck(): void {
    // Called during every change detection cycle
    console.log('Change detection cycle running');
  }
  
  ngAfterViewInit(): void {
    // Change detection runs after ngAfterViewInit
    console.log('View initialized');
  }
}

// 6. Performance Monitoring
@Component({
  template: `
    <div>{{ expensiveData }}</div>
  `
})
export class PerformanceComponent implements OnInit {
  expensiveData: string = '';
  
  constructor(private cdr: ChangeDetectorRef) {}
  
  ngOnInit(): void {
    // Heavy computation that might affect change detection
    this.calculateExpensiveData();
  }
  
  private calculateExpensiveData(): void {
    // Detach during heavy computation
    this.cdr.detach();
    
    // Simulate heavy computation
    setTimeout(() => {
      this.expensiveData = 'Computed result';
      
      // Reattach and trigger change detection
      this.cdr.reattach();
      this.cdr.detectChanges();
    }, 1000);
  }
}
```

**🐞 Fixes:** Use OnPush strategy for better performance, avoid heavy computations in change detection cycles, use async pipe when possible, and implement proper cleanup in lifecycle hooks.

---

2. What triggers change detection in Angular? _(Asked in TCS)_

**🧩 Foundation:** Change detection in Angular is triggered by various events and operations, including user interactions, asynchronous operations, timer events, and manual triggers, ensuring the UI stays synchronized with component data.

**⚙️ Function:** Change detection triggers ensure that any changes to component data are reflected in the view, maintaining data consistency and providing responsive user interfaces.

**🚀 Features:**
- **User interactions:** Click, input, focus, blur events
- **Asynchronous operations:** HTTP requests, timers, promises
- **Manual triggers:** detectChanges(), markForCheck()
- **Zone.js events:** All async operations within NgZone
- **Lifecycle hooks:** ngOnInit, ngOnChanges, etc.
- **Input property changes:** @Input decorator updates

**🔁 Flow:**
```typescript
// 1. User Interaction Triggers
@Component({
  template: `
    <button (click)="handleClick()">Click Me</button>
    <input (input)="handleInput($event)" placeholder="Type something">
    <div (mouseenter)="handleMouseEnter()" (mouseleave)="handleMouseLeave()">
      Hover over me
    </div>
    <div>{{ message }}</div>
  `
})
export class UserInteractionComponent {
  message = 'Initial message';
  
  handleClick(): void {
    this.message = 'Button clicked!'; // Triggers change detection
  }
  
  handleInput(event: any): void {
    this.message = event.target.value; // Triggers change detection
  }
  
  handleMouseEnter(): void {
    this.message = 'Mouse entered!'; // Triggers change detection
  }
  
  handleMouseLeave(): void {
    this.message = 'Mouse left!'; // Triggers change detection
  }
}

// 2. Asynchronous Operation Triggers
@Component({
  template: `
    <div>{{ data }}</div>
    <button (click)="loadData()">Load Data</button>
  `
})
export class AsyncOperationComponent {
  data = 'No data loaded';
  
  constructor(private dataService: DataService) {}
  
  loadData(): void {
    // HTTP request triggers change detection when completed
    this.dataService.getData().subscribe(result => {
      this.data = result; // Triggers change detection
    });
    
    // Timer triggers change detection
    setTimeout(() => {
      this.data = 'Timer updated data'; // Triggers change detection
    }, 1000);
    
    // Promise triggers change detection
    Promise.resolve('Promise data').then(result => {
      this.data = result; // Triggers change detection
    });
  }
}

// 3. Manual Change Detection Triggers
@Component({
  template: `
    <div>{{ counter }}</div>
    <button (click)="increment()">Increment</button>
    <button (click)="manualUpdate()">Manual Update</button>
  `
})
export class ManualTriggerComponent {
  counter = 0;
  
  constructor(private cdr: ChangeDetectorRef) {}
  
  increment(): void {
    this.counter++;
    // Change detection runs automatically
  }
  
  manualUpdate(): void {
    // Manual change detection trigger
    this.cdr.detectChanges();
  }
  
  markForCheck(): void {
    // Mark component for change detection (useful with OnPush)
    this.cdr.markForCheck();
  }
}

// 4. Input Property Changes
@Component({
  selector: 'app-child',
  template: `
    <div>{{ inputData }}</div>
  `
})
export class ChildComponent implements OnChanges {
  @Input() inputData: string = '';
  
  ngOnChanges(changes: SimpleChanges): void {
    // Called when input property changes
    console.log('Input changed:', changes);
  }
}

@Component({
  template: `
    <app-child [inputData]="parentData"></app-child>
    <button (click)="updateData()">Update Data</button>
  `
})
export class ParentComponent {
  parentData = 'Initial data';
  
  updateData(): void {
    this.parentData = 'Updated data'; // Triggers change detection in child
  }
}

// 5. Zone.js Integration
@Component({
  template: `
    <div>{{ zoneData }}</div>
    <button (click)="runInZone()">Run in Zone</button>
    <button (click)="runOutsideZone()">Run Outside Zone</button>
  `
})
export class ZoneComponent {
  zoneData = 'Initial data';
  
  constructor(private ngZone: NgZone) {}
  
  runInZone(): void {
    // This triggers change detection
    setTimeout(() => {
      this.zoneData = 'Updated in zone'; // Triggers change detection
    }, 1000);
  }
  
  runOutsideZone(): void {
    // This doesn't trigger change detection automatically
    this.ngZone.runOutsideAngular(() => {
      setTimeout(() => {
        this.zoneData = 'Updated outside zone'; // No automatic change detection
        // Need manual trigger
        this.ngZone.run(() => {
          // This will trigger change detection
        });
      }, 1000);
    });
  }
}

// 6. Lifecycle Hook Triggers
@Component({
  template: `
    <div>{{ lifecycleData }}</div>
  `
})
export class LifecycleComponent implements OnInit, OnChanges, AfterViewInit {
  @Input() inputValue: string = '';
  lifecycleData = 'Initial';
  
  ngOnInit(): void {
    // Change detection runs after ngOnInit
    this.lifecycleData = 'ngOnInit completed';
  }
  
  ngOnChanges(changes: SimpleChanges): void {
    // Change detection runs after ngOnChanges
    if (changes['inputValue']) {
      this.lifecycleData = `Input changed to: ${changes['inputValue'].currentValue}`;
    }
  }
  
  ngAfterViewInit(): void {
    // Change detection runs after ngAfterViewInit
    this.lifecycleData = 'View initialized';
  }
}

// 7. Event Emitter Triggers
@Component({
  selector: 'app-emitter',
  template: `
    <button (click)="emitEvent()">Emit Event</button>
  `
})
export class EmitterComponent {
  @Output() dataEvent = new EventEmitter<string>();
  
  emitEvent(): void {
    this.dataEvent.emit('Event data'); // Triggers change detection in parent
  }
}

@Component({
  template: `
    <app-emitter (dataEvent)="handleEvent($event)"></app-emitter>
    <div>{{ receivedData }}</div>
  `
})
export class EventReceiverComponent {
  receivedData = 'No data received';
  
  handleEvent(data: string): void {
    this.receivedData = data; // Triggers change detection
  }
}

// 8. Observable Triggers
@Component({
  template: `
    <div>{{ observableData }}</div>
  `
})
export class ObservableComponent implements OnInit {
  observableData = 'Initial';
  
  constructor(private dataService: DataService) {}
  
  ngOnInit(): void {
    // Observable subscription triggers change detection
    this.dataService.getDataStream().subscribe(data => {
      this.observableData = data; // Triggers change detection
    });
    
    // Interval triggers change detection
    interval(1000).subscribe(() => {
      this.observableData = `Updated at ${new Date().toLocaleTimeString()}`; // Triggers change detection
    });
  }
}
```

**🐞 Fixes:** Be aware of what triggers change detection, use OnPush strategy to reduce unnecessary cycles, avoid heavy computations in change detection, and use manual triggers when needed for performance optimization.

### Intermediate Questions

1. What are the two types of change detection strategies in Angular? _(Asked in Wipro)_

**🧩 Foundation:** Angular provides two change detection strategies: Default and OnPush. The Default strategy checks for changes on every event, while OnPush strategy only checks when inputs change or events are triggered within the component.

**⚙️ Function:** Change detection strategies control when and how Angular checks for data changes, allowing developers to optimize performance by reducing unnecessary change detection cycles while maintaining data consistency.

**🚀 Features:**
- **Default strategy:** Checks on every event, timer, HTTP request
- **OnPush strategy:** Only checks on input changes and component events
- **Performance optimization:** OnPush reduces change detection cycles
- **Manual control:** OnPush requires explicit change detection triggers
- **Immutability requirement:** OnPush works best with immutable data

**🔁 Flow:**
```typescript
// 1. Default Change Detection Strategy
@Component({
  selector: 'app-default-strategy',
  template: `
    <div>
      <h2>{{ title }}</h2>
      <p>{{ message }}</p>
      <button (click)="updateMessage()">Update</button>
      <div *ngFor="let item of items">
        {{ item.name }}
      </div>
    </div>
  `
  // Default strategy is used when not specified
})
export class DefaultStrategyComponent {
  title = 'Default Strategy';
  message = 'Initial message';
  items = [
    { id: 1, name: 'Item 1' },
    { id: 2, name: 'Item 2' }
  ];
  
  updateMessage(): void {
    this.message = 'Updated message'; // Triggers change detection
  }
  
  addItem(): void {
    this.items.push({ id: 3, name: 'Item 3' }); // Triggers change detection
  }
  
  // Change detection runs on:
  // - User interactions (click, input, etc.)
  // - Timer events (setTimeout, setInterval)
  // - HTTP requests
  // - Any async operation within NgZone
}

// 2. OnPush Change Detection Strategy
@Component({
  selector: 'app-onpush-strategy',
  changeDetection: ChangeDetectionStrategy.OnPush,
  template: `
    <div>
      <h2>{{ title }}</h2>
      <p>{{ message }}</p>
      <button (click)="updateMessage()">Update</button>
      <div *ngFor="let item of items">
        {{ item.name }}
      </div>
    </div>
  `
})
export class OnPushStrategyComponent {
  title = 'OnPush Strategy';
  message = 'Initial message';
  items = [
    { id: 1, name: 'Item 1' },
    { id: 2, name: 'Item 2' }
  ];
  
  constructor(private cdr: ChangeDetectorRef) {}
  
  updateMessage(): void {
    this.message = 'Updated message'; // ✅ Triggers change detection (component event)
  }
  
  addItem(): void {
    // ❌ This won't trigger change detection with OnPush
    this.items.push({ id: 3, name: 'Item 3' });
    
    // ✅ This will trigger change detection
    this.items = [...this.items, { id: 3, name: 'Item 3' }];
    
    // ✅ Or manually trigger change detection
    this.cdr.detectChanges();
  }
  
  // Change detection only runs on:
  // - Input property changes (reference changes)
  // - Component events (click, input within component)
  // - Manual triggers (detectChanges(), markForCheck())
}

// 3. Input Property Changes with OnPush
@Component({
  selector: 'app-child-onpush',
  changeDetection: ChangeDetectionStrategy.OnPush,
  template: `
    <div>
      <h3>{{ inputData.title }}</h3>
      <p>{{ inputData.description }}</p>
      <div *ngFor="let item of inputData.items">
        {{ item.name }}
      </div>
    </div>
  `
})
export class ChildOnPushComponent {
  @Input() inputData: any = {
    title: 'Default Title',
    description: 'Default Description',
    items: []
  };
  
  // Change detection runs when inputData reference changes
  // Not when properties within inputData change
}

@Component({
  template: `
    <app-child-onpush [inputData]="parentData"></app-child-onpush>
    <button (click)="updateData()">Update Data</button>
  `
})
export class ParentComponent {
  parentData = {
    title: 'Initial Title',
    description: 'Initial Description',
    items: [
      { id: 1, name: 'Item 1' }
    ]
  };
  
  updateData(): void {
    // ❌ This won't trigger change detection in child
    this.parentData.title = 'Updated Title';
    
    // ✅ This will trigger change detection in child
    this.parentData = {
      ...this.parentData,
      title: 'Updated Title'
    };
  }
}

// 4. Manual Change Detection with OnPush
@Component({
  selector: 'app-manual-onpush',
  changeDetection: ChangeDetectionStrategy.OnPush,
  template: `
    <div>
      <h2>{{ title }}</h2>
      <p>{{ message }}</p>
      <button (click)="updateWithManualDetection()">Manual Update</button>
      <button (click)="updateWithMarkForCheck()">Mark for Check</button>
    </div>
  `
})
export class ManualOnPushComponent {
  title = 'Manual OnPush';
  message = 'Initial message';
  
  constructor(private cdr: ChangeDetectorRef) {}
  
  updateWithManualDetection(): void {
    this.message = 'Updated with manual detection';
    this.cdr.detectChanges(); // Manual change detection
  }
  
  updateWithMarkForCheck(): void {
    this.message = 'Updated with mark for check';
    this.cdr.markForCheck(); // Mark for next change detection cycle
  }
}

// 5. Observable Integration with OnPush
@Component({
  selector: 'app-observable-onpush',
  changeDetection: ChangeDetectionStrategy.OnPush,
  template: `
    <div>
      <h2>{{ title }}</h2>
      <div *ngFor="let user of users$ | async">
        {{ user.name }}
      </div>
      <div *ngFor="let post of posts$ | async">
        {{ post.title }}
      </div>
    </div>
  `
})
export class ObservableOnPushComponent {
  title = 'Observable OnPush';
  users$ = this.userService.getUsers();
  posts$ = this.postService.getPosts();
  
  constructor(
    private userService: UserService,
    private postService: PostService
  ) {}
  
  // Async pipe automatically triggers change detection
  // No manual intervention needed
}

// 6. Performance Comparison
@Component({
  selector: 'app-performance-comparison',
  template: `
    <div>
      <h2>Performance Test</h2>
      <div>Counter: {{ counter }}</div>
      <button (click)="increment()">Increment</button>
      <div>Last Update: {{ lastUpdate }}</div>
    </div>
  `
})
export class PerformanceComparisonComponent {
  counter = 0;
  lastUpdate = new Date().toLocaleTimeString();
  
  increment(): void {
    this.counter++;
    this.lastUpdate = new Date().toLocaleTimeString();
  }
  
  // With Default strategy: Change detection runs on every event
  // With OnPush strategy: Change detection only runs on component events
}

// 7. Hybrid Approach
@Component({
  selector: 'app-hybrid-approach',
  changeDetection: ChangeDetectionStrategy.OnPush,
  template: `
    <div>
      <h2>{{ title }}</h2>
      <div>{{ data }}</div>
      <button (click)="loadData()">Load Data</button>
      <div *ngFor="let item of items">
        {{ item.name }}
      </div>
    </div>
  `
})
export class HybridApproachComponent implements OnInit {
  title = 'Hybrid Approach';
  data = 'No data';
  items: any[] = [];
  
  constructor(
    private dataService: DataService,
    private cdr: ChangeDetectorRef
  ) {}
  
  ngOnInit(): void {
    // Load initial data
    this.loadData();
  }
  
  loadData(): void {
    this.dataService.getData().subscribe(result => {
      this.data = result.message;
      this.items = result.items;
      
      // Manual change detection for async operations
      this.cdr.detectChanges();
    });
  }
  
  addItem(): void {
    // Component event - automatic change detection
    this.items = [...this.items, { id: Date.now(), name: 'New Item' }];
  }
}
```

**🐞 Fixes:** Use OnPush for better performance, ensure immutable data updates, handle async operations with manual change detection, and use async pipe when possible.

---

2. What are the benefits of OnPush change detection strategy? _(Asked in Infosys)_

**🧩 Foundation:** OnPush change detection strategy provides significant performance benefits by reducing unnecessary change detection cycles, only checking for changes when inputs change or events are triggered within the component.

**⚙️ Function:** OnPush strategy optimizes Angular applications by minimizing change detection overhead, improving rendering performance, and encouraging immutable data patterns for better predictability and debugging.

**🚀 Features:**
- **Performance improvement:** Reduces change detection cycles by 90%+
- **Predictable behavior:** Only runs on specific triggers
- **Memory optimization:** Less CPU usage and better battery life
- **Immutable data encouragement:** Forces better data practices
- **Debugging benefits:** Easier to track when changes occur
- **Scalability:** Better performance in large applications

**🔁 Flow:**
```typescript
// 1. Performance Benefits Demonstration
@Component({
  selector: 'app-performance-demo',
  changeDetection: ChangeDetectionStrategy.OnPush,
  template: `
    <div>
      <h2>{{ title }}</h2>
      <div>Counter: {{ counter }}</div>
      <button (click)="increment()">Increment</button>
      <div>Change Detection Runs: {{ changeDetectionRuns }}</div>
    </div>
  `
})
export class PerformanceDemoComponent implements DoCheck {
  title = 'Performance Demo';
  counter = 0;
  changeDetectionRuns = 0;
  
  constructor(private cdr: ChangeDetectorRef) {}
  
  ngDoCheck(): void {
    this.changeDetectionRuns++;
    console.log('Change detection run:', this.changeDetectionRuns);
  }
  
  increment(): void {
    this.counter++;
    // Only this event triggers change detection with OnPush
  }
  
  // External timer won't trigger change detection
  startExternalTimer(): void {
    setInterval(() => {
      // This won't trigger change detection with OnPush
      console.log('External timer tick');
    }, 1000);
  }
}

// 2. Memory Optimization
@Component({
  selector: 'app-memory-optimization',
  changeDetection: ChangeDetectionStrategy.OnPush,
  template: `
    <div>
      <h2>{{ title }}</h2>
      <div *ngFor="let item of items; trackBy: trackByFn">
        {{ item.name }}
      </div>
      <button (click)="addItem()">Add Item</button>
    </div>
  `
})
export class MemoryOptimizationComponent {
  title = 'Memory Optimization';
  items = [
    { id: 1, name: 'Item 1' },
    { id: 2, name: 'Item 2' }
  ];
  
  trackByFn(index: number, item: any): number {
    return item.id;
  }
  
  addItem(): void {
    // Immutable update - triggers change detection efficiently
    this.items = [...this.items, { id: Date.now(), name: `Item ${this.items.length + 1}` }];
  }
  
  // No unnecessary change detection cycles from external sources
}

// 3. Predictable Behavior
@Component({
  selector: 'app-predictable-behavior',
  changeDetection: ChangeDetectionStrategy.OnPush,
  template: `
    <div>
      <h2>{{ title }}</h2>
      <div>{{ message }}</div>
      <button (click)="updateMessage()">Update Message</button>
      <button (click)="startTimer()">Start Timer</button>
    </div>
  `
})
export class PredictableBehaviorComponent {
  title = 'Predictable Behavior';
  message = 'Initial message';
  
  updateMessage(): void {
    this.message = 'Updated message';
    // ✅ This will trigger change detection (component event)
  }
  
  startTimer(): void {
    setInterval(() => {
      this.message = `Timer: ${new Date().toLocaleTimeString()}`;
      // ❌ This won't trigger change detection (external timer)
    }, 1000);
  }
  
  // Change detection only runs when:
  // 1. Input properties change (reference changes)
  // 2. Component events occur (click, input, etc.)
  // 3. Manual triggers (detectChanges(), markForCheck())
}

// 4. Immutable Data Patterns
@Component({
  selector: 'app-immutable-patterns',
  changeDetection: ChangeDetectionStrategy.OnPush,
  template: `
    <div>
      <h2>{{ title }}</h2>
      <div *ngFor="let user of users">
        {{ user.name }} - {{ user.email }}
      </div>
      <button (click)="addUser()">Add User</button>
      <button (click)="updateUser()">Update User</button>
    </div>
  `
})
export class ImmutablePatternsComponent {
  title = 'Immutable Patterns';
  users = [
    { id: 1, name: 'John', email: 'john@example.com' },
    { id: 2, name: 'Jane', email: 'jane@example.com' }
  ];
  
  addUser(): void {
    // ✅ Immutable update - triggers change detection
    this.users = [...this.users, { 
      id: Date.now(), 
      name: 'New User', 
      email: 'new@example.com' 
    }];
  }
  
  updateUser(): void {
    // ✅ Immutable update - triggers change detection
    this.users = this.users.map(user => 
      user.id === 1 
        ? { ...user, name: 'Updated John' }
        : user
    );
  }
  
  // ❌ Mutable update - won't trigger change detection
  updateUserMutable(): void {
    this.users[0].name = 'Updated John';
  }
}

// 5. Debugging Benefits
@Component({
  selector: 'app-debugging-benefits',
  changeDetection: ChangeDetectionStrategy.OnPush,
  template: `
    <div>
      <h2>{{ title }}</h2>
      <div>{{ data }}</div>
      <div>Last Update: {{ lastUpdate }}</div>
      <button (click)="updateData()">Update Data</button>
    </div>
  `
})
export class DebuggingBenefitsComponent implements DoCheck {
  title = 'Debugging Benefits';
  data = 'Initial data';
  lastUpdate = new Date().toLocaleTimeString();
  
  ngDoCheck(): void {
    console.log('Change detection triggered at:', new Date().toLocaleTimeString());
    console.log('Reason: Component event or input change');
  }
  
  updateData(): void {
    this.data = 'Updated data';
    this.lastUpdate = new Date().toLocaleTimeString();
    // Easy to track when this happens
  }
  
  // External changes won't trigger change detection
  // Making it easier to debug data flow
}

// 6. Scalability in Large Applications
@Component({
  selector: 'app-scalability',
  changeDetection: ChangeDetectionStrategy.OnPush,
  template: `
    <div>
      <h2>{{ title }}</h2>
      <div>Component Count: {{ componentCount }}</div>
      <div>Performance Score: {{ performanceScore }}</div>
      <button (click)="simulateLoad()">Simulate Load</button>
    </div>
  `
})
export class ScalabilityComponent {
  title = 'Scalability Benefits';
  componentCount = 1000;
  performanceScore = 95;
  
  constructor(private cdr: ChangeDetectorRef) {}
  
  simulateLoad(): void {
    // Simulate heavy computation
    const start = performance.now();
    
    // Heavy operation
    for (let i = 0; i < 1000000; i++) {
      Math.random();
    }
    
    const end = performance.now();
    this.performanceScore = Math.round(100 - (end - start));
    
    // Manual change detection only when needed
    this.cdr.detectChanges();
  }
  
  // OnPush prevents unnecessary change detection cycles
  // Even with thousands of components
}

// 7. Battery Life Optimization
@Component({
  selector: 'app-battery-optimization',
  changeDetection: ChangeDetectionStrategy.OnPush,
  template: `
    <div>
      <h2>{{ title }}</h2>
      <div>Battery Usage: {{ batteryUsage }}%</div>
      <div>CPU Usage: {{ cpuUsage }}%</div>
      <button (click)="startMonitoring()">Start Monitoring</button>
    </div>
  `
})
export class BatteryOptimizationComponent {
  title = 'Battery Optimization';
  batteryUsage = 0;
  cpuUsage = 0;
  
  startMonitoring(): void {
    // Simulate battery and CPU monitoring
    setInterval(() => {
      // This won't trigger change detection with OnPush
      // Saving battery and CPU cycles
      this.batteryUsage = Math.random() * 100;
      this.cpuUsage = Math.random() * 100;
    }, 1000);
  }
  
  // OnPush reduces CPU usage by avoiding unnecessary change detection
  // Leading to better battery life on mobile devices
}

// 8. Real-world Performance Comparison
@Component({
  template: `
    <div>
      <h2>Performance Comparison</h2>
      <div>Default Strategy: {{ defaultPerformance }}ms</div>
      <div>OnPush Strategy: {{ onPushPerformance }}ms</div>
      <div>Improvement: {{ improvement }}%</div>
    </div>
  `
})
export class PerformanceComparisonComponent {
  defaultPerformance = 100; // Simulated
  onPushPerformance = 10;   // Simulated
  improvement = Math.round(((this.defaultPerformance - this.onPushPerformance) / this.defaultPerformance) * 100);
  
  // OnPush typically provides 80-95% reduction in change detection cycles
  // Leading to significant performance improvements
}
```

**🐞 Fixes:** Always use immutable data updates, handle async operations with manual change detection, use async pipe when possible, and implement proper input property handling for OnPush strategy.

### Advanced Questions

1. What happens if you detach a component from change detection? _(Asked in Cognizant)_

**🧩 Foundation:** Detaching a component from change detection removes it from Angular's automatic change detection cycle, meaning the component's view won't update automatically when its data changes, requiring manual intervention to trigger updates.

**⚙️ Function:** Detaching change detection provides performance benefits by preventing unnecessary view updates, but requires careful management to ensure the component stays synchronized with its data when needed.

**🚀 Features:**
- **Performance optimization:** Prevents unnecessary change detection cycles
- **Manual control:** Requires explicit change detection triggers
- **Memory efficiency:** Reduces CPU usage and battery consumption
- **Selective updates:** Only update when explicitly needed
- **Debugging complexity:** Can make tracking updates more difficult
- **Risk of stale data:** View may not reflect current component state

**🔁 Flow:**
```typescript
// 1. Basic Detachment Example
@Component({
  template: `
    <div>
      <h2>{{ title }}</h2>
      <div>{{ message }}</div>
      <div>Counter: {{ counter }}</div>
      <button (click)="increment()">Increment</button>
      <button (click)="detach()">Detach</button>
      <button (click)="reattach()">Reattach</button>
      <button (click)="manualUpdate()">Manual Update</button>
    </div>
  `
})
export class DetachedComponent {
  title = 'Detached Component';
  message = 'Initial message';
  counter = 0;
  
  constructor(private cdr: ChangeDetectorRef) {}
  
  increment(): void {
    this.counter++;
    this.message = `Counter: ${this.counter}`;
    // ❌ No automatic change detection when detached
  }
  
  detach(): void {
    this.cdr.detach();
    this.message = 'Component detached from change detection';
    // ❌ This change won't be reflected in the view
  }
  
  reattach(): void {
    this.cdr.reattach();
    this.message = 'Component reattached to change detection';
    // ✅ This change will be reflected in the view
  }
  
  manualUpdate(): void {
    this.counter++;
    this.message = `Manual update: ${this.counter}`;
    this.cdr.detectChanges(); // ✅ Manual change detection
  }
}

// 2. Performance Optimization with Detachment
@Component({
  template: `
    <div>
      <h2>{{ title }}</h2>
      <div>Heavy Computation: {{ heavyResult }}</div>
      <div>Last Update: {{ lastUpdate }}</div>
      <button (click)="startHeavyComputation()">Start Heavy Computation</button>
      <button (click)="updateManually()">Update Manually</button>
    </div>
  `
})
export class PerformanceOptimizedComponent {
  title = 'Performance Optimized';
  heavyResult = 'No computation';
  lastUpdate = new Date().toLocaleTimeString();
  
  constructor(private cdr: ChangeDetectorRef) {}
  
  startHeavyComputation(): void {
    // Detach during heavy computation
    this.cdr.detach();
    
    // Simulate heavy computation
    let result = 0;
    for (let i = 0; i < 1000000; i++) {
      result += Math.random();
    }
    
    this.heavyResult = `Result: ${result.toFixed(2)}`;
    this.lastUpdate = new Date().toLocaleTimeString();
    
    // Reattach and trigger change detection
    this.cdr.reattach();
    this.cdr.detectChanges();
  }
  
  updateManually(): void {
    this.heavyResult = 'Manual update';
    this.lastUpdate = new Date().toLocaleTimeString();
    this.cdr.detectChanges(); // Manual change detection
  }
}

// 3. Selective Update Strategy
@Component({
  template: `
    <div>
      <h2>{{ title }}</h2>
      <div>Frequent Updates: {{ frequentData }}</div>
      <div>Important Updates: {{ importantData }}</div>
      <div>Last Important Update: {{ lastImportantUpdate }}</div>
      <button (click)="startFrequentUpdates()">Start Frequent Updates</button>
      <button (click)="triggerImportantUpdate()">Important Update</button>
    </div>
  `
})
export class SelectiveUpdateComponent {
  title = 'Selective Updates';
  frequentData = 'Initial';
  importantData = 'Initial';
  lastImportantUpdate = new Date().toLocaleTimeString();
  private updateInterval: any;
  
  constructor(private cdr: ChangeDetectorRef) {}
  
  startFrequentUpdates(): void {
    // Detach to prevent frequent change detection
    this.cdr.detach();
    
    this.updateInterval = setInterval(() => {
      this.frequentData = `Update: ${new Date().toLocaleTimeString()}`;
      // No automatic change detection - performance optimization
    }, 100);
  }
  
  triggerImportantUpdate(): void {
    this.importantData = 'Important update triggered';
    this.lastImportantUpdate = new Date().toLocaleTimeString();
    
    // Manual change detection for important updates
    this.cdr.detectChanges();
  }
  
  ngOnDestroy(): void {
    if (this.updateInterval) {
      clearInterval(this.updateInterval);
    }
  }
}

// 4. External Data Integration
@Component({
  template: `
    <div>
      <h2>{{ title }}</h2>
      <div>External Data: {{ externalData }}</div>
      <div>Last Sync: {{ lastSync }}</div>
      <button (click)="syncWithExternal()">Sync with External</button>
    </div>
  `
})
export class ExternalDataComponent {
  title = 'External Data Integration';
  externalData = 'No external data';
  lastSync = 'Never';
  
  constructor(private cdr: ChangeDetectorRef) {
    // Detach to prevent automatic updates from external sources
    this.cdr.detach();
  }
  
  syncWithExternal(): void {
    // Simulate external data fetch
    setTimeout(() => {
      this.externalData = `External data: ${Math.random()}`;
      this.lastSync = new Date().toLocaleTimeString();
      
      // Manual change detection after external data update
      this.cdr.detectChanges();
    }, 1000);
  }
  
  // External timer that doesn't trigger change detection
  startExternalTimer(): void {
    setInterval(() => {
      this.externalData = `Timer update: ${new Date().toLocaleTimeString()}`;
      // No automatic change detection - manual control needed
    }, 5000);
  }
}

// 5. Debugging Detached Components
@Component({
  template: `
    <div>
      <h2>{{ title }}</h2>
      <div>Status: {{ status }}</div>
      <div>Update Count: {{ updateCount }}</div>
      <button (click)="toggleDetachment()">Toggle Detachment</button>
      <button (click)="forceUpdate()">Force Update</button>
    </div>
  `
})
export class DebuggingDetachedComponent implements DoCheck {
  title = 'Debugging Detached';
  status = 'Attached';
  updateCount = 0;
  private isDetached = false;
  
  constructor(private cdr: ChangeDetectorRef) {}
  
  ngDoCheck(): void {
    if (!this.isDetached) {
      this.updateCount++;
      console.log('Change detection run:', this.updateCount);
    }
  }
  
  toggleDetachment(): void {
    if (this.isDetached) {
      this.cdr.reattach();
      this.isDetached = false;
      this.status = 'Attached';
    } else {
      this.cdr.detach();
      this.isDetached = true;
      this.status = 'Detached';
    }
  }
  
  forceUpdate(): void {
    this.updateCount++;
    this.status = `Forced update: ${this.updateCount}`;
    
    if (this.isDetached) {
      this.cdr.detectChanges(); // Manual change detection
    }
  }
}

// 6. Service Integration with Detached Components
@Injectable({
  providedIn: 'root'
})
export class DataService {
  private dataSubject = new BehaviorSubject<string>('Initial data');
  public data$ = this.dataSubject.asObservable();
  
  updateData(newData: string): void {
    this.dataSubject.next(newData);
  }
}

@Component({
  template: `
    <div>
      <h2>{{ title }}</h2>
      <div>Service Data: {{ serviceData }}</div>
      <button (click)="updateServiceData()">Update Service Data</button>
      <button (click)="syncWithService()">Sync with Service</button>
    </div>
  `
})
export class ServiceDetachedComponent implements OnInit {
  title = 'Service Integration';
  serviceData = 'No data';
  
  constructor(
    private dataService: DataService,
    private cdr: ChangeDetectorRef
  ) {
    // Detach to prevent automatic updates
    this.cdr.detach();
  }
  
  ngOnInit(): void {
    // Subscribe to service but don't auto-update view
    this.dataService.data$.subscribe(data => {
      this.serviceData = data;
      // No automatic change detection - manual control
    });
  }
  
  updateServiceData(): void {
    this.dataService.updateData(`Updated: ${new Date().toLocaleTimeString()}`);
    // View won't update automatically due to detachment
  }
  
  syncWithService(): void {
    // Manual sync with service data
    this.cdr.detectChanges();
  }
}

// 7. Conditional Detachment
@Component({
  template: `
    <div>
      <h2>{{ title }}</h2>
      <div>Data: {{ data }}</div>
      <div>Mode: {{ mode }}</div>
      <button (click)="toggleMode()">Toggle Mode</button>
      <button (click)="updateData()">Update Data</button>
    </div>
  `
})
export class ConditionalDetachmentComponent {
  title = 'Conditional Detachment';
  data = 'Initial data';
  mode = 'Auto';
  private isDetached = false;
  
  constructor(private cdr: ChangeDetectorRef) {}
  
  toggleMode(): void {
    if (this.isDetached) {
      this.cdr.reattach();
      this.isDetached = false;
      this.mode = 'Auto';
    } else {
      this.cdr.detach();
      this.isDetached = true;
      this.mode = 'Manual';
    }
  }
  
  updateData(): void {
    this.data = `Updated: ${new Date().toLocaleTimeString()}`;
    
    if (this.isDetached) {
      this.cdr.detectChanges(); // Manual update when detached
    }
    // Automatic update when attached
  }
}
```

**🐞 Fixes:** Always reattach components when needed, use manual change detection appropriately, implement proper cleanup, and ensure data consistency when using detachment.

---

### Tough Questions

1. A component rerenders unnecessarily on every keystroke. How do you identify and fix this? _(Asked in HCL)_

**🧩 Foundation:** Unnecessary rerenders on every keystroke typically occur due to inefficient change detection, missing OnPush strategy, heavy computations in templates, or improper use of functions in template expressions.

**⚙️ Function:** Identifying and fixing unnecessary rerenders improves application performance, reduces CPU usage, and provides a smoother user experience by optimizing when and how components update their views.

**🚀 Features:**
- **Performance profiling:** Use Angular DevTools and browser tools
- **OnPush strategy:** Reduce change detection cycles
- **TrackBy functions:** Optimize *ngFor loops
- **Memoization:** Cache expensive computations
- **Pure pipes:** Move logic to pipes
- **Async pipe:** Handle observables efficiently

**🔁 Flow:**
```typescript
// 1. Problematic Component (Causes unnecessary rerenders)
@Component({
  template: `
    <div>
      <input (input)="onInput($event)" placeholder="Type something...">
      <div>Input value: {{ inputValue }}</div>
      <div>Processed value: {{ processValue(inputValue) }}</div>
      <div>Computed result: {{ expensiveComputation() }}</div>
      <div *ngFor="let item of getItems()">
        {{ item.name }} - {{ item.processed }}
      </div>
    </div>
  `
})
export class ProblematicComponent {
  inputValue = '';
  
  onInput(event: any): void {
    this.inputValue = event.target.value;
    // ❌ Triggers change detection on every keystroke
  }
  
  processValue(value: string): string {
    // ❌ Function called on every change detection cycle
    return value.toUpperCase();
  }
  
  expensiveComputation(): number {
    // ❌ Expensive computation on every cycle
    let result = 0;
    for (let i = 0; i < 1000000; i++) {
      result += Math.random();
    }
    return result;
  }
  
  getItems(): any[] {
    // ❌ New array created on every cycle
    return [
      { name: 'Item 1', processed: this.processValue('item1') },
      { name: 'Item 2', processed: this.processValue('item2') }
    ];
  }
}

// 2. Optimized Component (Fixed unnecessary rerenders)
@Component({
  changeDetection: ChangeDetectionStrategy.OnPush, // ✅ Use OnPush
  template: `
    <div>
      <input (input)="onInput($event)" placeholder="Type something...">
      <div>Input value: {{ inputValue }}</div>
      <div>Processed value: {{ inputValue | uppercase }}</div>
      <div>Computed result: {{ cachedComputation }}</div>
      <div *ngFor="let item of items; trackBy: trackByFn">
        {{ item.name }} - {{ item.processed }}
      </div>
    </div>
  `
})
export class OptimizedComponent implements OnInit {
  inputValue = '';
  cachedComputation = 0;
  items: any[] = [];
  
  constructor(private cdr: ChangeDetectorRef) {}
  
  ngOnInit(): void {
    // ✅ Pre-compute expensive values
    this.cachedComputation = this.expensiveComputation();
    this.items = this.getItems();
  }
  
  onInput(event: any): void {
    this.inputValue = event.target.value;
    // ✅ Only triggers change detection for component events with OnPush
  }
  
  private expensiveComputation(): number {
    // ✅ Called only once during initialization
    let result = 0;
    for (let i = 0; i < 1000000; i++) {
      result += Math.random();
    }
    return result;
  }
  
  private getItems(): any[] {
    // ✅ Called only once during initialization
    return [
      { id: 1, name: 'Item 1', processed: 'ITEM1' },
      { id: 2, name: 'Item 2', processed: 'ITEM2' }
    ];
  }
  
  trackByFn(index: number, item: any): number {
    // ✅ Optimize *ngFor with trackBy
    return item.id;
  }
}

// 3. Using Pure Pipes for Optimization
@Pipe({
  name: 'expensive',
  pure: true // ✅ Pure pipe - only recalculates when input changes
})
export class ExpensivePipe implements PipeTransform {
  transform(value: string): number {
    // Expensive computation moved to pure pipe
    let result = 0;
    for (let i = 0; i < 100000; i++) {
      result += value.charCodeAt(i % value.length) || 0;
    }
    return result;
  }
}

@Component({
  changeDetection: ChangeDetectionStrategy.OnPush,
  template: `
    <div>
      <input (input)="onInput($event)" placeholder="Type something...">
      <div>Input: {{ inputValue }}</div>
      <div>Expensive result: {{ inputValue | expensive }}</div>
    </div>
  `
})
export class PipeOptimizedComponent {
  inputValue = '';
  
  onInput(event: any): void {
    this.inputValue = event.target.value;
  }
}

// 4. Memoization for Expensive Computations
@Component({
  changeDetection: ChangeDetectionStrategy.OnPush,
  template: `
    <div>
      <input (input)="onInput($event)" placeholder="Type something...">
      <div>Input: {{ inputValue }}</div>
      <div>Memoized result: {{ memoizedResult }}</div>
    </div>
  `
})
export class MemoizedComponent {
  inputValue = '';
  memoizedResult = 0;
  private memoCache = new Map<string, number>();
  
  onInput(event: any): void {
    this.inputValue = event.target.value;
    this.updateMemoizedResult();
  }
  
  private updateMemoizedResult(): void {
    if (this.memoCache.has(this.inputValue)) {
      this.memoizedResult = this.memoCache.get(this.inputValue)!;
    } else {
      this.memoizedResult = this.expensiveComputation(this.inputValue);
      this.memoCache.set(this.inputValue, this.memoizedResult);
    }
  }
  
  private expensiveComputation(input: string): number {
    let result = 0;
    for (let i = 0; i < 100000; i++) {
      result += input.charCodeAt(i % input.length) || 0;
    }
    return result;
  }
}

// 5. Async Pipe Optimization
@Component({
  changeDetection: ChangeDetectionStrategy.OnPush,
  template: `
    <div>
      <input (input)="onInput($event)" placeholder="Type something...">
      <div>Input: {{ inputValue }}</div>
      <div>Async data: {{ asyncData$ | async }}</div>
      <div *ngFor="let item of items$ | async; trackBy: trackByFn">
        {{ item.name }}
      </div>
    </div>
  `
})
export class AsyncOptimizedComponent {
  inputValue = '';
  asyncData$ = new BehaviorSubject<string>('Initial');
  items$ = new BehaviorSubject<any[]>([]);
  
  constructor(private dataService: DataService) {
    this.items$ = this.dataService.getItems();
  }
  
  onInput(event: any): void {
    this.inputValue = event.target.value;
    this.asyncData$.next(`Processed: ${this.inputValue.toUpperCase()}`);
  }
  
  trackByFn(index: number, item: any): number {
    return item.id;
  }
}

// 6. Performance Monitoring
@Component({
  template: `
    <div>
      <h2>Performance Monitor</h2>
      <div>Change Detection Cycles: {{ changeDetectionCycles }}</div>
      <div>Last Update: {{ lastUpdate }}</div>
      <input (input)="onInput($event)" placeholder="Type to test...">
      <button (click)="resetCounter()">Reset Counter</button>
    </div>
  `
})
export class PerformanceMonitorComponent implements DoCheck {
  changeDetectionCycles = 0;
  lastUpdate = new Date().toLocaleTimeString();
  
  ngDoCheck(): void {
    this.changeDetectionCycles++;
    this.lastUpdate = new Date().toLocaleTimeString();
    console.log('Change detection cycle:', this.changeDetectionCycles);
  }
  
  onInput(event: any): void {
    // Monitor how many cycles this triggers
  }
  
  resetCounter(): void {
    this.changeDetectionCycles = 0;
  }
}

// 7. Debugging Tools
@Component({
  template: `
    <div>
      <h2>Debugging Tools</h2>
      <div>Input: {{ inputValue }}</div>
      <div>Processed: {{ processedValue }}</div>
      <input (input)="onInput($event)" placeholder="Type to debug...">
      <button (click)="logPerformance()">Log Performance</button>
    </div>
  `
})
export class DebuggingComponent {
  inputValue = '';
  processedValue = '';
  
  onInput(event: any): void {
    const start = performance.now();
    
    this.inputValue = event.target.value;
    this.processedValue = this.processValue(this.inputValue);
    
    const end = performance.now();
    console.log(`Input processing took: ${end - start}ms`);
  }
  
  private processValue(value: string): string {
    // Simulate processing
    return value.toUpperCase();
  }
  
  logPerformance(): void {
    // Use Angular DevTools or browser performance tools
    console.log('Performance logging enabled');
  }
}

// 8. Service-level Optimization
@Injectable({
  providedIn: 'root'
})
export class OptimizedDataService {
  private cache = new Map<string, any>();
  
  getProcessedData(input: string): any {
    if (this.cache.has(input)) {
      return this.cache.get(input);
    }
    
    const result = this.processData(input);
    this.cache.set(input, result);
    return result;
  }
  
  private processData(input: string): any {
    // Expensive processing
    return {
      processed: input.toUpperCase(),
      length: input.length,
      timestamp: Date.now()
    };
  }
  
  clearCache(): void {
    this.cache.clear();
  }
}
```

**🐞 Fixes:** Use OnPush strategy, implement trackBy functions, move expensive computations to pure pipes, use memoization, and monitor performance with Angular DevTools.

---

## 9. State Management (NgRx, BehaviorSubject, etc.)

### Basic Questions

1. What is state management in Angular and why is it important? _(Asked in Infosys, Capgemini)_

**🧩 Foundation:** State management in Angular refers to the systematic approach of managing application data and state across components, services, and the entire application lifecycle, ensuring data consistency and predictable behavior.

**⚙️ Function:** State management provides a centralized way to store, access, and modify application data, enabling components to share information efficiently, maintain data integrity, and handle complex application state scenarios.

**🚀 Features:**
- **Centralized data storage:** Single source of truth for application state
- **Predictable state changes:** Controlled and traceable data modifications
- **Component communication:** Efficient data sharing between components
- **Performance optimization:** Reduced prop drilling and unnecessary re-renders
- **Debugging capabilities:** Easy state inspection and time-travel debugging
- **Scalability:** Handles complex application state as application grows

**🔁 Flow:**
```typescript
// 1. Basic State Management with Service
@Injectable({
  providedIn: 'root'
})
export class UserStateService {
  private users: User[] = [];
  private selectedUser: User | null = null;
  
  // State getters
  getUsers(): User[] {
    return this.users;
  }
  
  getSelectedUser(): User | null {
    return this.selectedUser;
  }
  
  // State setters
  setUsers(users: User[]): void {
    this.users = users;
  }
  
  setSelectedUser(user: User | null): void {
    this.selectedUser = user;
  }
  
  // State actions
  addUser(user: User): void {
    this.users = [...this.users, user];
  }
  
  updateUser(updatedUser: User): void {
    this.users = this.users.map(user => 
      user.id === updatedUser.id ? updatedUser : user
    );
  }
  
  deleteUser(userId: number): void {
    this.users = this.users.filter(user => user.id !== userId);
  }
}

// 2. Component Using State Service
@Component({
  template: `
    <div>
      <h2>User Management</h2>
      <div *ngFor="let user of users">
        {{ user.name }} - {{ user.email }}
        <button (click)="selectUser(user)">Select</button>
      </div>
      
      <div *ngIf="selectedUser">
        <h3>Selected User: {{ selectedUser.name }}</h3>
        <button (click)="editUser()">Edit</button>
      </div>
    </div>
  `
})
export class UserManagementComponent {
  users: User[] = [];
  selectedUser: User | null = null;
  
  constructor(private userStateService: UserStateService) {}
  
  ngOnInit(): void {
    this.loadUsers();
    this.subscribeToState();
  }
  
  private loadUsers(): void {
    // Load users from API and update state
    this.userService.getUsers().subscribe(users => {
      this.userStateService.setUsers(users);
    });
  }
  
  private subscribeToState(): void {
    // Subscribe to state changes
    this.userStateService.getUsers().subscribe(users => {
      this.users = users;
    });
    
    this.userStateService.getSelectedUser().subscribe(user => {
      this.selectedUser = user;
    });
  }
  
  selectUser(user: User): void {
    this.userStateService.setSelectedUser(user);
  }
  
  editUser(): void {
    // Navigate to edit page with selected user
    this.router.navigate(['/users', this.selectedUser?.id, 'edit']);
  }
}

// 3. Advanced State Management with BehaviorSubject
@Injectable({
  providedIn: 'root'
})
export class AdvancedUserStateService {
  private usersSubject = new BehaviorSubject<User[]>([]);
  private selectedUserSubject = new BehaviorSubject<User | null>(null);
  private loadingSubject = new BehaviorSubject<boolean>(false);
  private errorSubject = new BehaviorSubject<string | null>(null);
  
  // Public observables
  public users$ = this.usersSubject.asObservable();
  public selectedUser$ = this.selectedUserSubject.asObservable();
  public loading$ = this.loadingSubject.asObservable();
  public error$ = this.errorSubject.asObservable();
  
  // State getters
  getUsers(): User[] {
    return this.usersSubject.value;
  }
  
  getSelectedUser(): User | null {
    return this.selectedUserSubject.value;
  }
  
  // State actions
  loadUsers(): void {
    this.loadingSubject.next(true);
    this.errorSubject.next(null);
    
    this.userService.getUsers().subscribe({
      next: (users) => {
        this.usersSubject.next(users);
        this.loadingSubject.next(false);
      },
      error: (error) => {
        this.errorSubject.next(error.message);
        this.loadingSubject.next(false);
      }
    });
  }
  
  selectUser(user: User): void {
    this.selectedUserSubject.next(user);
  }
  
  addUser(user: User): void {
    const currentUsers = this.usersSubject.value;
    this.usersSubject.next([...currentUsers, user]);
  }
  
  updateUser(updatedUser: User): void {
    const currentUsers = this.usersSubject.value;
    const updatedUsers = currentUsers.map(user => 
      user.id === updatedUser.id ? updatedUser : user
    );
    this.usersSubject.next(updatedUsers);
  }
  
  deleteUser(userId: number): void {
    const currentUsers = this.usersSubject.value;
    const filteredUsers = currentUsers.filter(user => user.id !== userId);
    this.usersSubject.next(filteredUsers);
  }
  
  clearError(): void {
    this.errorSubject.next(null);
  }
}

// 4. Component with Advanced State Management
@Component({
  template: `
    <div>
      <h2>Advanced User Management</h2>
      
      <div *ngIf="loading$ | async" class="loading">
        Loading users...
      </div>
      
      <div *ngIf="error$ | async as error" class="error">
        {{ error }}
        <button (click)="clearError()">Dismiss</button>
      </div>
      
      <div *ngFor="let user of users$ | async">
        {{ user.name }} - {{ user.email }}
        <button (click)="selectUser(user)">Select</button>
        <button (click)="deleteUser(user.id)">Delete</button>
      </div>
      
      <div *ngIf="selectedUser$ | async as selectedUser">
        <h3>Selected: {{ selectedUser.name }}</h3>
        <button (click)="editUser(selectedUser)">Edit</button>
      </div>
      
      <button (click)="loadUsers()">Refresh Users</button>
    </div>
  `
})
export class AdvancedUserComponent {
  users$ = this.userStateService.users$;
  selectedUser$ = this.userStateService.selectedUser$;
  loading$ = this.userStateService.loading$;
  error$ = this.userStateService.error$;
  
  constructor(
    private userStateService: AdvancedUserStateService,
    private router: Router
  ) {}
  
  ngOnInit(): void {
    this.loadUsers();
  }
  
  loadUsers(): void {
    this.userStateService.loadUsers();
  }
  
  selectUser(user: User): void {
    this.userStateService.selectUser(user);
  }
  
  deleteUser(userId: number): void {
    this.userStateService.deleteUser(userId);
  }
  
  editUser(user: User): void {
    this.router.navigate(['/users', user.id, 'edit']);
  }
  
  clearError(): void {
    this.userStateService.clearError();
  }
}

// 5. State Management Benefits Demonstration
@Component({
  template: `
    <div>
      <h2>State Management Benefits</h2>
      
      <div class="benefits">
        <div class="benefit">
          <h3>Centralized Data</h3>
          <p>All user data managed in one place</p>
          <div>Total Users: {{ (users$ | async)?.length || 0 }}</div>
        </div>
        
        <div class="benefit">
          <h3>Predictable Updates</h3>
          <p>State changes are controlled and traceable</p>
          <div>Last Update: {{ lastUpdate }}</div>
        </div>
        
        <div class="benefit">
          <h3>Component Communication</h3>
          <p>Components can share data without prop drilling</p>
          <div>Selected User: {{ (selectedUser$ | async)?.name || 'None' }}</div>
        </div>
      </div>
      
      <div class="actions">
        <button (click)="addSampleUser()">Add Sample User</button>
        <button (click)="clearUsers()">Clear All Users</button>
        <button (click)="simulateError()">Simulate Error</button>
      </div>
    </div>
  `
})
export class StateBenefitsComponent {
  users$ = this.userStateService.users$;
  selectedUser$ = this.userStateService.selectedUser$;
  lastUpdate = new Date().toLocaleTimeString();
  
  constructor(private userStateService: AdvancedUserStateService) {}
  
  addSampleUser(): void {
    const newUser: User = {
      id: Date.now(),
      name: `User ${Date.now()}`,
      email: `user${Date.now()}@example.com`
    };
    
    this.userStateService.addUser(newUser);
    this.lastUpdate = new Date().toLocaleTimeString();
  }
  
  clearUsers(): void {
    this.userStateService.usersSubject.next([]);
    this.lastUpdate = new Date().toLocaleTimeString();
  }
  
  simulateError(): void {
    this.userStateService.errorSubject.next('Simulated error for testing');
  }
}

// 6. State Persistence
@Injectable({
  providedIn: 'root'
})
export class PersistentStateService {
  private readonly STORAGE_KEY = 'app_state';
  
  constructor() {}
  
  saveState(state: any): void {
    localStorage.setItem(this.STORAGE_KEY, JSON.stringify(state));
  }
  
  loadState(): any {
    const savedState = localStorage.getItem(this.STORAGE_KEY);
    return savedState ? JSON.parse(savedState) : null;
  }
  
  clearState(): void {
    localStorage.removeItem(this.STORAGE_KEY);
  }
}

// 7. State Management with Persistence
@Injectable({
  providedIn: 'root'
})
export class PersistentUserStateService extends AdvancedUserStateService {
  constructor(private persistentService: PersistentStateService) {
    super();
    this.loadPersistedState();
  }
  
  private loadPersistedState(): void {
    const savedState = this.persistentService.loadState();
    if (savedState?.users) {
      this.usersSubject.next(savedState.users);
    }
    if (savedState?.selectedUser) {
      this.selectedUserSubject.next(savedState.selectedUser);
    }
  }
  
  private saveCurrentState(): void {
    const currentState = {
      users: this.usersSubject.value,
      selectedUser: this.selectedUserSubject.value
    };
    this.persistentService.saveState(currentState);
  }
  
  // Override methods to add persistence
  addUser(user: User): void {
    super.addUser(user);
    this.saveCurrentState();
  }
  
  updateUser(updatedUser: User): void {
    super.updateUser(updatedUser);
    this.saveCurrentState();
  }
  
  deleteUser(userId: number): void {
    super.deleteUser(userId);
    this.saveCurrentState();
  }
}
```

**🐞 Fixes:** Always handle state immutability, implement proper error handling, use observables for reactive state, and consider state persistence for better user experience.

---

2. What is a `BehaviorSubject` and how is it used for state sharing? _(Asked in TCS)_

**🧩 Foundation:** A `BehaviorSubject` is a type of RxJS Subject that requires an initial value and stores the current value, emitting it to new subscribers immediately upon subscription, making it ideal for state management scenarios.

**⚙️ Function:** BehaviorSubject provides a reactive way to share state across components, automatically notifying subscribers of current and future values, and maintaining the latest state for new subscribers.

**🚀 Features:**
- **Initial value required:** Must be provided with a starting value
- **Current value storage:** Maintains the most recent emitted value
- **Immediate emission:** New subscribers receive current value instantly
- **Reactive updates:** Automatically notifies all subscribers of changes
- **Value access:** Can retrieve current value synchronously
- **State persistence:** Maintains state across component lifecycles

**🔁 Flow:**
```typescript
// 1. Basic BehaviorSubject Usage
import { BehaviorSubject } from 'rxjs';

// Create BehaviorSubject with initial value
const userState = new BehaviorSubject<User | null>(null);

// Subscribe to state changes
userState.subscribe(user => {
  console.log('User state changed:', user);
});

// Emit new values
userState.next({ id: 1, name: 'John', email: 'john@example.com' });
userState.next({ id: 2, name: 'Jane', email: 'jane@example.com' });

// Get current value
const currentUser = userState.value;
console.log('Current user:', currentUser);

// 2. State Management Service with BehaviorSubject
@Injectable({
  providedIn: 'root'
})
export class UserStateService {
  // Private BehaviorSubjects for state
  private userSubject = new BehaviorSubject<User | null>(null);
  private usersSubject = new BehaviorSubject<User[]>([]);
  private loadingSubject = new BehaviorSubject<boolean>(false);
  private errorSubject = new BehaviorSubject<string | null>(null);
  
  // Public observables for components
  public user$ = this.userSubject.asObservable();
  public users$ = this.usersSubject.asObservable();
  public loading$ = this.loadingSubject.asObservable();
  public error$ = this.errorSubject.asObservable();
  
  // State getters
  getCurrentUser(): User | null {
    return this.userSubject.value;
  }
  
  getUsers(): User[] {
    return this.usersSubject.value;
  }
  
  isLoading(): boolean {
    return this.loadingSubject.value;
  }
  
  getError(): string | null {
    return this.errorSubject.value;
  }
  
  // State setters
  setUser(user: User | null): void {
    this.userSubject.next(user);
  }
  
  setUsers(users: User[]): void {
    this.usersSubject.next(users);
  }
  
  setLoading(loading: boolean): void {
    this.loadingSubject.next(loading);
  }
  
  setError(error: string | null): void {
    this.errorSubject.next(error);
  }
  
  // State actions
  login(user: User): void {
    this.setLoading(true);
    this.setError(null);
    
    // Simulate API call
    setTimeout(() => {
      this.setUser(user);
      this.setLoading(false);
    }, 1000);
  }
  
  logout(): void {
    this.setUser(null);
    this.setError(null);
  }
  
  addUser(user: User): void {
    const currentUsers = this.usersSubject.value;
    this.usersSubject.next([...currentUsers, user]);
  }
  
  updateUser(updatedUser: User): void {
    const currentUsers = this.usersSubject.value;
    const updatedUsers = currentUsers.map(user => 
      user.id === updatedUser.id ? updatedUser : user
    );
    this.usersSubject.next(updatedUsers);
  }
  
  deleteUser(userId: number): void {
    const currentUsers = this.usersSubject.value;
    const filteredUsers = currentUsers.filter(user => user.id !== userId);
    this.usersSubject.next(filteredUsers);
  }
}

// 3. Component Using BehaviorSubject State
@Component({
  template: `
    <div>
      <h2>User State Management</h2>
      
      <div *ngIf="loading$ | async" class="loading">
        Loading...
      </div>
      
      <div *ngIf="error$ | async as error" class="error">
        {{ error }}
      </div>
      
      <div *ngIf="user$ | async as user; else loginForm">
        <h3>Welcome, {{ user.name }}!</h3>
        <p>Email: {{ user.email }}</p>
        <button (click)="logout()">Logout</button>
        
        <div class="user-list">
          <h4>All Users</h4>
          <div *ngFor="let u of users$ | async">
            {{ u.name }} - {{ u.email }}
            <button (click)="deleteUser(u.id)">Delete</button>
          </div>
        </div>
      </div>
      
      <ng-template #loginForm>
        <div class="login-form">
          <h3>Login</h3>
          <input #nameInput placeholder="Name">
          <input #emailInput placeholder="Email">
          <button (click)="login(nameInput.value, emailInput.value)">
            Login
          </button>
        </div>
      </ng-template>
    </div>
  `
})
export class UserStateComponent {
  user$ = this.userStateService.user$;
  users$ = this.userStateService.users$;
  loading$ = this.userStateService.loading$;
  error$ = this.userStateService.error$;
  
  constructor(private userStateService: UserStateService) {}
  
  ngOnInit(): void {
    // Load initial users
    this.loadUsers();
  }
  
  login(name: string, email: string): void {
    if (name && email) {
      const user: User = { id: Date.now(), name, email };
      this.userStateService.login(user);
    }
  }
  
  logout(): void {
    this.userStateService.logout();
  }
  
  deleteUser(userId: number): void {
    this.userStateService.deleteUser(userId);
  }
  
  private loadUsers(): void {
    // Simulate loading users
    const users: User[] = [
      { id: 1, name: 'John Doe', email: 'john@example.com' },
      { id: 2, name: 'Jane Smith', email: 'jane@example.com' }
    ];
    this.userStateService.setUsers(users);
  }
}

// 4. Advanced State Management with Multiple BehaviorSubjects
@Injectable({
  providedIn: 'root'
})
export class AdvancedStateService {
  // Application state
  private appStateSubject = new BehaviorSubject<AppState>({
    user: null,
    users: [],
    loading: false,
    error: null,
    theme: 'light',
    language: 'en'
  });
  
  public appState$ = this.appStateSubject.asObservable();
  
  // Individual state observables
  public user$ = this.appState$.pipe(map(state => state.user));
  public users$ = this.appState$.pipe(map(state => state.users));
  public loading$ = this.appState$.pipe(map(state => state.loading));
  public error$ = this.appState$.pipe(map(state => state.error));
  public theme$ = this.appState$.pipe(map(state => state.theme));
  public language$ = this.appState$.pipe(map(state => state.language));
  
  // State getters
  getCurrentState(): AppState {
    return this.appStateSubject.value;
  }
  
  // State actions
  updateUser(user: User | null): void {
    const currentState = this.appStateSubject.value;
    this.appStateSubject.next({
      ...currentState,
      user
    });
  }
  
  updateUsers(users: User[]): void {
    const currentState = this.appStateSubject.value;
    this.appStateSubject.next({
      ...currentState,
      users
    });
  }
  
  setLoading(loading: boolean): void {
    const currentState = this.appStateSubject.value;
    this.appStateSubject.next({
      ...currentState,
      loading
    });
  }
  
  setError(error: string | null): void {
    const currentState = this.appStateSubject.value;
    this.appStateSubject.next({
      ...currentState,
      error
    });
  }
  
  toggleTheme(): void {
    const currentState = this.appStateSubject.value;
    const newTheme = currentState.theme === 'light' ? 'dark' : 'light';
    this.appStateSubject.next({
      ...currentState,
      theme: newTheme
    });
  }
  
  setLanguage(language: string): void {
    const currentState = this.appStateSubject.value;
    this.appStateSubject.next({
      ...currentState,
      language
    });
  }
  
  // Complex state operations
  login(user: User): void {
    this.setLoading(true);
    this.setError(null);
    
    // Simulate API call
    setTimeout(() => {
      this.updateUser(user);
      this.setLoading(false);
    }, 1000);
  }
  
  logout(): void {
    this.updateUser(null);
    this.setError(null);
  }
  
  addUser(user: User): void {
    const currentState = this.appStateSubject.value;
    this.updateUsers([...currentState.users, user]);
  }
}

// 5. State Synchronization Between Components
@Component({
  selector: 'app-header',
  template: `
    <header [class]="theme$ | async">
      <h1>My App</h1>
      <div *ngIf="user$ | async as user">
        Welcome, {{ user.name }}!
        <button (click)="logout()">Logout</button>
      </div>
      <button (click)="toggleTheme()">Toggle Theme</button>
    </header>
  `
})
export class HeaderComponent {
  user$ = this.stateService.user$;
  theme$ = this.stateService.theme$;
  
  constructor(private stateService: AdvancedStateService) {}
  
  logout(): void {
    this.stateService.logout();
  }
  
  toggleTheme(): void {
    this.stateService.toggleTheme();
  }
}

@Component({
  selector: 'app-sidebar',
  template: `
    <aside [class]="theme$ | async">
      <h3>Users</h3>
      <div *ngFor="let user of users$ | async">
        {{ user.name }}
      </div>
      <select (change)="changeLanguage($event)">
        <option value="en">English</option>
        <option value="es">Spanish</option>
        <option value="fr">French</option>
      </select>
    </aside>
  `
})
export class SidebarComponent {
  users$ = this.stateService.users$;
  theme$ = this.stateService.theme$;
  
  constructor(private stateService: AdvancedStateService) {}
  
  changeLanguage(event: any): void {
    this.stateService.setLanguage(event.target.value);
  }
}

// 6. State Persistence with BehaviorSubject
@Injectable({
  providedIn: 'root'
})
export class PersistentStateService {
  private readonly STORAGE_KEY = 'app_persistent_state';
  
  constructor() {}
  
  saveState<T>(key: string, state: T): void {
    const allStates = this.getAllStates();
    allStates[key] = state;
    localStorage.setItem(this.STORAGE_KEY, JSON.stringify(allStates));
  }
  
  loadState<T>(key: string, defaultValue: T): T {
    const allStates = this.getAllStates();
    return allStates[key] || defaultValue;
  }
  
  private getAllStates(): any {
    const saved = localStorage.getItem(this.STORAGE_KEY);
    return saved ? JSON.parse(saved) : {};
  }
}

@Injectable({
  providedIn: 'root'
})
export class PersistentUserStateService {
  private userSubject: BehaviorSubject<User | null>;
  
  public user$: Observable<User | null>;
  
  constructor(private persistentService: PersistentStateService) {
    // Load initial state from persistence
    const savedUser = this.persistentService.loadState<User | null>('user', null);
    this.userSubject = new BehaviorSubject<User | null>(savedUser);
    this.user$ = this.userSubject.asObservable();
  }
  
  setUser(user: User | null): void {
    this.userSubject.next(user);
    this.persistentService.saveState('user', user);
  }
  
  getCurrentUser(): User | null {
    return this.userSubject.value;
  }
}
```

**🐞 Fixes:** Always provide initial values, handle unsubscription to prevent memory leaks, use proper error handling, and consider state persistence for better user experience.

### Intermediate Questions

1. What is NgRx and how does it fit into Angular ecosystem? _(Asked in Cognizant)_

**🧩 Foundation:** NgRx is a state management library for Angular applications based on Redux principles, providing a predictable state container using actions, reducers, selectors, and effects to manage complex application state.

**⚙️ Function:** NgRx provides a robust, scalable architecture for managing application state through unidirectional data flow, enabling predictable state changes, debugging capabilities, and handling complex state scenarios in large Angular applications.

**🚀 Features:**
- **Actions:** Describe state changes as plain objects
- **Reducers:** Pure functions that handle state transitions
- **Selectors:** Extract and compose state data
- **Effects:** Handle side effects and async operations
- **Store:** Centralized state container
- **DevTools:** Time-travel debugging and state inspection

**🔁 Flow:**
```typescript
// 1. Basic NgRx Setup
// app.module.ts
import { StoreModule } from '@ngrx/store';
import { EffectsModule } from '@ngrx/effects';
import { StoreDevtoolsModule } from '@ngrx/store-devtools';

@NgModule({
  imports: [
    StoreModule.forRoot({}),
    EffectsModule.forRoot([]),
    StoreDevtoolsModule.instrument({
      maxAge: 25
    })
  ]
})
export class AppModule {}

// 2. State Interface
export interface AppState {
  users: UserState;
  posts: PostState;
  loading: boolean;
  error: string | null;
}

export interface UserState {
  users: User[];
  selectedUser: User | null;
  loading: boolean;
  error: string | null;
}

export interface PostState {
  posts: Post[];
  selectedPost: Post | null;
  loading: boolean;
  error: string | null;
}

// 3. Actions
import { createAction, props } from '@ngrx/store';

// User Actions
export const loadUsers = createAction('[User] Load Users');
export const loadUsersSuccess = createAction(
  '[User] Load Users Success',
  props<{ users: User[] }>()
);
export const loadUsersFailure = createAction(
  '[User] Load Users Failure',
  props<{ error: string }>()
);

export const selectUser = createAction(
  '[User] Select User',
  props<{ user: User }>()
);

export const addUser = createAction(
  '[User] Add User',
  props<{ user: User }>()
);

export const updateUser = createAction(
  '[User] Update User',
  props<{ user: User }>()
);

export const deleteUser = createAction(
  '[User] Delete User',
  props<{ userId: number }>()
);

// 4. Reducers
import { createReducer, on } from '@ngrx/store';
import * as UserActions from './user.actions';

export const initialState: UserState = {
  users: [],
  selectedUser: null,
  loading: false,
  error: null
};

export const userReducer = createReducer(
  initialState,
  
  on(UserActions.loadUsers, state => ({
    ...state,
    loading: true,
    error: null
  })),
  
  on(UserActions.loadUsersSuccess, (state, { users }) => ({
    ...state,
    users,
    loading: false
  })),
  
  on(UserActions.loadUsersFailure, (state, { error }) => ({
    ...state,
    error,
    loading: false
  })),
  
  on(UserActions.selectUser, (state, { user }) => ({
    ...state,
    selectedUser: user
  })),
  
  on(UserActions.addUser, (state, { user }) => ({
    ...state,
    users: [...state.users, user]
  })),
  
  on(UserActions.updateUser, (state, { user }) => ({
    ...state,
    users: state.users.map(u => u.id === user.id ? user : u),
    selectedUser: state.selectedUser?.id === user.id ? user : state.selectedUser
  })),
  
  on(UserActions.deleteUser, (state, { userId }) => ({
    ...state,
    users: state.users.filter(u => u.id !== userId),
    selectedUser: state.selectedUser?.id === userId ? null : state.selectedUser
  }))
);

// 5. Selectors
import { createFeatureSelector, createSelector } from '@ngrx/store';

export const selectUserState = createFeatureSelector<UserState>('users');

export const selectAllUsers = createSelector(
  selectUserState,
  state => state.users
);

export const selectSelectedUser = createSelector(
  selectUserState,
  state => state.selectedUser
);

export const selectUsersLoading = createSelector(
  selectUserState,
  state => state.loading
);

export const selectUsersError = createSelector(
  selectUserState,
  state => state.error
);

export const selectUsersCount = createSelector(
  selectAllUsers,
  users => users.length
);

export const selectActiveUsers = createSelector(
  selectAllUsers,
  users => users.filter(user => user.active)
);

// 6. Effects
import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { map, mergeMap, catchError, switchMap } from 'rxjs/operators';
import { of } from 'rxjs';

@Injectable()
export class UserEffects {
  loadUsers$ = createEffect(() => this.actions$.pipe(
    ofType(UserActions.loadUsers),
    mergeMap(() => this.userService.getUsers()
      .pipe(
        map(users => UserActions.loadUsersSuccess({ users })),
        catchError(error => of(UserActions.loadUsersFailure({ error: error.message })))
      ))
  ));

  addUser$ = createEffect(() => this.actions$.pipe(
    ofType(UserActions.addUser),
    mergeMap(({ user }) => this.userService.createUser(user)
      .pipe(
        map(createdUser => UserActions.addUser({ user: createdUser })),
        catchError(error => of(UserActions.loadUsersFailure({ error: error.message })))
      ))
  ));

  updateUser$ = createEffect(() => this.actions$.pipe(
    ofType(UserActions.updateUser),
    mergeMap(({ user }) => this.userService.updateUser(user)
      .pipe(
        map(updatedUser => UserActions.updateUser({ user: updatedUser })),
        catchError(error => of(UserActions.loadUsersFailure({ error: error.message })))
      ))
  ));

  deleteUser$ = createEffect(() => this.actions$.pipe(
    ofType(UserActions.deleteUser),
    mergeMap(({ userId }) => this.userService.deleteUser(userId)
      .pipe(
        map(() => UserActions.deleteUser({ userId })),
        catchError(error => of(UserActions.loadUsersFailure({ error: error.message })))
      ))
  ));

  constructor(
    private actions$: Actions,
    private userService: UserService
  ) {}
}

// 7. Component Integration
@Component({
  template: `
    <div>
      <h2>User Management with NgRx</h2>
      
      <div *ngIf="loading$ | async" class="loading">
        Loading users...
      </div>
      
      <div *ngIf="error$ | async as error" class="error">
        {{ error }}
        <button (click)="loadUsers()">Retry</button>
      </div>
      
      <div *ngFor="let user of users$ | async">
        {{ user.name }} - {{ user.email }}
        <button (click)="selectUser(user)">Select</button>
        <button (click)="deleteUser(user.id)">Delete</button>
      </div>
      
      <div *ngIf="selectedUser$ | async as selectedUser">
        <h3>Selected: {{ selectedUser.name }}</h3>
        <button (click)="editUser(selectedUser)">Edit</button>
      </div>
      
      <button (click)="loadUsers()">Load Users</button>
      <button (click)="addUser()">Add User</button>
    </div>
  `
})
export class UserManagementComponent implements OnInit {
  users$ = this.store.select(selectAllUsers);
  selectedUser$ = this.store.select(selectSelectedUser);
  loading$ = this.store.select(selectUsersLoading);
  error$ = this.store.select(selectUsersError);
  usersCount$ = this.store.select(selectUsersCount);
  
  constructor(
    private store: Store<AppState>,
    private router: Router
  ) {}
  
  ngOnInit(): void {
    this.loadUsers();
  }
  
  loadUsers(): void {
    this.store.dispatch(UserActions.loadUsers());
  }
  
  selectUser(user: User): void {
    this.store.dispatch(UserActions.selectUser({ user }));
  }
  
  addUser(): void {
    const newUser: User = {
      id: Date.now(),
      name: `User ${Date.now()}`,
      email: `user${Date.now()}@example.com`,
      active: true
    };
    this.store.dispatch(UserActions.addUser({ user: newUser }));
  }
  
  deleteUser(userId: number): void {
    this.store.dispatch(UserActions.deleteUser({ userId }));
  }
  
  editUser(user: User): void {
    this.router.navigate(['/users', user.id, 'edit']);
  }
}

// 8. Advanced Selectors with Composition
export const selectUserById = (userId: number) => createSelector(
  selectAllUsers,
  users => users.find(user => user.id === userId)
);

export const selectUsersByStatus = (active: boolean) => createSelector(
  selectAllUsers,
  users => users.filter(user => user.active === active)
);

export const selectUserStats = createSelector(
  selectAllUsers,
  users => ({
    total: users.length,
    active: users.filter(u => u.active).length,
    inactive: users.filter(u => !u.active).length
  })
);

// 9. Entity Adapter for Complex State
import { EntityState, EntityAdapter, createEntityAdapter } from '@ngrx/entity';

export interface UserEntityState extends EntityState<User> {
  selectedUserId: number | null;
  loading: boolean;
  error: string | null;
}

export const userAdapter: EntityAdapter<User> = createEntityAdapter<User>({
  selectId: (user: User) => user.id,
  sortComparer: (a: User, b: User) => a.name.localeCompare(b.name)
});

export const initialState: UserEntityState = userAdapter.getInitialState({
  selectedUserId: null,
  loading: false,
  error: null
});

export const userEntityReducer = createReducer(
  initialState,
  
  on(UserActions.loadUsers, state => ({
    ...state,
    loading: true,
    error: null
  })),
  
  on(UserActions.loadUsersSuccess, (state, { users }) => 
    userAdapter.setAll(users, {
      ...state,
      loading: false
    })
  ),
  
  on(UserActions.addUser, (state, { user }) => 
    userAdapter.addOne(user, state)
  ),
  
  on(UserActions.updateUser, (state, { user }) => 
    userAdapter.updateOne(
      { id: user.id, changes: user },
      state
    )
  ),
  
  on(UserActions.deleteUser, (state, { userId }) => 
    userAdapter.removeOne(userId, state)
  )
);

// Entity selectors
export const {
  selectIds: selectUserIds,
  selectEntities: selectUserEntities,
  selectAll: selectAllUsers,
  selectTotal: selectTotalUsers
} = userAdapter.getSelectors(selectUserState);
```

**🐞 Fixes:** Always use immutable state updates, handle async operations in effects, implement proper error handling, and use selectors for efficient state access.

---

### Advanced Questions

1. How would you architect an Angular app using NgRx for scalability? _(Asked in HCL)_

**🧩 Foundation:** Architecting a scalable Angular app with NgRx involves implementing feature-based state management, lazy loading, proper separation of concerns, and establishing clear patterns for state organization and data flow.

**⚙️ Function:** A scalable NgRx architecture provides maintainable code structure, efficient state management, and the ability to handle complex application requirements while maintaining performance and developer productivity.

**🚀 Features:**
- **Feature-based architecture:** Organize state by application features
- **Lazy loading:** Load state modules on demand
- **Entity management:** Efficient handling of collections
- **Normalized state:** Optimized data structure for performance
- **Shared state:** Common state across features
- **Testing strategies:** Comprehensive testing patterns

**🔁 Flow:**
```typescript
// 1. Feature-based Architecture
// app/
// ├── core/
// │   ├── state/
// │   │   ├── app.state.ts
// │   │   └── app.reducer.ts
// │   └── services/
// ├── shared/
// │   ├── state/
// │   │   ├── shared.state.ts
// │   │   └── shared.reducer.ts
// │   └── components/
// ├── features/
// │   ├── users/
// │   │   ├── state/
// │   │   ├── components/
// │   │   └── users.module.ts
// │   ├── posts/
// │   │   ├── state/
// │   │   ├── components/
// │   │   └── posts.module.ts
// │   └── dashboard/
// │       ├── state/
// │       ├── components/
// │       └── dashboard.module.ts

// 2. Root State Configuration
// app.state.ts
export interface AppState {
  core: CoreState;
  shared: SharedState;
  users: UserState;
  posts: PostState;
  dashboard: DashboardState;
}

// app.reducer.ts
import { ActionReducerMap } from '@ngrx/store';

export const reducers: ActionReducerMap<AppState> = {
  core: coreReducer,
  shared: sharedReducer,
  users: userReducer,
  posts: postReducer,
  dashboard: dashboardReducer
};

// 3. Feature Module with NgRx
// users/users.module.ts
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StoreModule } from '@ngrx/store';
import { EffectsModule } from '@ngrx/effects';
import { userReducer } from './state/user.reducer';
import { UserEffects } from './state/user.effects';

@NgModule({
  imports: [
    CommonModule,
    StoreModule.forFeature('users', userReducer),
    EffectsModule.forFeature([UserEffects])
  ],
  declarations: [
    UserListComponent,
    UserDetailComponent,
    UserFormComponent
  ]
})
export class UsersModule {}

// 4. Normalized State Structure
// users/state/user.state.ts
export interface UserState {
  entities: { [id: number]: User };
  ids: number[];
  selectedUserId: number | null;
  loading: boolean;
  error: string | null;
  filters: UserFilters;
  pagination: PaginationState;
}

export interface UserFilters {
  searchTerm: string;
  status: 'all' | 'active' | 'inactive';
  role: string;
}

export interface PaginationState {
  currentPage: number;
  pageSize: number;
  totalItems: number;
}

// 5. Entity-based Reducer
// users/state/user.reducer.ts
import { EntityState, EntityAdapter, createEntityAdapter } from '@ngrx/entity';

export const userAdapter: EntityAdapter<User> = createEntityAdapter<User>({
  selectId: (user: User) => user.id,
  sortComparer: (a: User, b: User) => a.name.localeCompare(b.name)
});

export const initialState: UserState = userAdapter.getInitialState({
  selectedUserId: null,
  loading: false,
  error: null,
  filters: {
    searchTerm: '',
    status: 'all',
    role: ''
  },
  pagination: {
    currentPage: 1,
    pageSize: 10,
    totalItems: 0
  }
});

export const userReducer = createReducer(
  initialState,
  
  on(UserActions.loadUsers, (state, { filters, pagination }) => ({
    ...state,
    loading: true,
    error: null,
    filters: { ...state.filters, ...filters },
    pagination: { ...state.pagination, ...pagination }
  })),
  
  on(UserActions.loadUsersSuccess, (state, { users, totalItems }) => 
    userAdapter.setAll(users, {
      ...state,
      loading: false,
      pagination: { ...state.pagination, totalItems }
    })
  ),
  
  on(UserActions.loadUsersFailure, (state, { error }) => ({
    ...state,
    loading: false,
    error
  })),
  
  on(UserActions.selectUser, (state, { userId }) => ({
    ...state,
    selectedUserId: userId
  })),
  
  on(UserActions.addUser, (state, { user }) => 
    userAdapter.addOne(user, state)
  ),
  
  on(UserActions.updateUser, (state, { user }) => 
    userAdapter.updateOne(
      { id: user.id, changes: user },
      state
    )
  ),
  
  on(UserActions.deleteUser, (state, { userId }) => 
    userAdapter.removeOne(userId, state)
  ),
  
  on(UserActions.updateFilters, (state, { filters }) => ({
    ...state,
    filters: { ...state.filters, ...filters },
    pagination: { ...state.pagination, currentPage: 1 }
  }))
);

// 6. Advanced Selectors
// users/state/user.selectors.ts
export const selectUserState = createFeatureSelector<UserState>('users');

export const {
  selectIds: selectUserIds,
  selectEntities: selectUserEntities,
  selectAll: selectAllUsers,
  selectTotal: selectTotalUsers
} = userAdapter.getSelectors(selectUserState);

export const selectSelectedUserId = createSelector(
  selectUserState,
  state => state.selectedUserId
);

export const selectSelectedUser = createSelector(
  selectUserEntities,
  selectSelectedUserId,
  (entities, selectedId) => selectedId ? entities[selectedId] : null
);

export const selectUserFilters = createSelector(
  selectUserState,
  state => state.filters
);

export const selectUserPagination = createSelector(
  selectUserState,
  state => state.pagination
);

export const selectFilteredUsers = createSelector(
  selectAllUsers,
  selectUserFilters,
  (users, filters) => {
    return users.filter(user => {
      const matchesSearch = !filters.searchTerm || 
        user.name.toLowerCase().includes(filters.searchTerm.toLowerCase()) ||
        user.email.toLowerCase().includes(filters.searchTerm.toLowerCase());
      
      const matchesStatus = filters.status === 'all' || 
        (filters.status === 'active' && user.active) ||
        (filters.status === 'inactive' && !user.active);
      
      const matchesRole = !filters.role || user.role === filters.role;
      
      return matchesSearch && matchesStatus && matchesRole;
    });
  }
);

export const selectPaginatedUsers = createSelector(
  selectFilteredUsers,
  selectUserPagination,
  (users, pagination) => {
    const startIndex = (pagination.currentPage - 1) * pagination.pageSize;
    const endIndex = startIndex + pagination.pageSize;
    return users.slice(startIndex, endIndex);
  }
);

// 7. Advanced Effects with Error Handling
// users/state/user.effects.ts
@Injectable()
export class UserEffects {
  loadUsers$ = createEffect(() => this.actions$.pipe(
    ofType(UserActions.loadUsers),
    withLatestFrom(
      this.store.select(selectUserFilters),
      this.store.select(selectUserPagination)
    ),
    switchMap(([action, filters, pagination]) => 
      this.userService.getUsers(filters, pagination).pipe(
        map(response => UserActions.loadUsersSuccess({ 
          users: response.users, 
          totalItems: response.totalItems 
        })),
        catchError(error => of(UserActions.loadUsersFailure({ 
          error: error.message 
        })))
      )
    )
  ));

  addUser$ = createEffect(() => this.actions$.pipe(
    ofType(UserActions.addUser),
    mergeMap(({ user }) => 
      this.userService.createUser(user).pipe(
        map(createdUser => UserActions.addUserSuccess({ user: createdUser })),
        catchError(error => of(UserActions.addUserFailure({ error: error.message })))
      )
    )
  ));

  updateUser$ = createEffect(() => this.actions$.pipe(
    ofType(UserActions.updateUser),
    mergeMap(({ user }) => 
      this.userService.updateUser(user).pipe(
        map(updatedUser => UserActions.updateUserSuccess({ user: updatedUser })),
        catchError(error => of(UserActions.updateUserFailure({ error: error.message })))
      )
    )
  ));

  deleteUser$ = createEffect(() => this.actions$.pipe(
    ofType(UserActions.deleteUser),
    mergeMap(({ userId }) => 
      this.userService.deleteUser(userId).pipe(
        map(() => UserActions.deleteUserSuccess({ userId })),
        catchError(error => of(UserActions.deleteUserFailure({ error: error.message })))
      )
    )
  ));

  // Optimistic updates
  optimisticUpdateUser$ = createEffect(() => this.actions$.pipe(
    ofType(UserActions.updateUser),
    map(({ user }) => UserActions.updateUserOptimistic({ user }))
  ));

  // Rollback on failure
  rollbackUpdateUser$ = createEffect(() => this.actions$.pipe(
    ofType(UserActions.updateUserFailure),
    map(({ error, originalUser }) => UserActions.updateUserRollback({ user: originalUser }))
  ));

  constructor(
    private actions$: Actions,
    private userService: UserService,
    private store: Store<AppState>
  ) {}
}

// 8. Shared State Management
// shared/state/shared.state.ts
export interface SharedState {
  notifications: Notification[];
  loading: boolean;
  error: string | null;
  theme: 'light' | 'dark';
  language: string;
  userPreferences: UserPreferences;
}

// shared/state/shared.actions.ts
export const addNotification = createAction(
  '[Shared] Add Notification',
  props<{ notification: Notification }>()
);

export const removeNotification = createAction(
  '[Shared] Remove Notification',
  props<{ id: string }>()
);

export const setTheme = createAction(
  '[Shared] Set Theme',
  props<{ theme: 'light' | 'dark' }>()
);

export const setLanguage = createAction(
  '[Shared] Set Language',
  props<{ language: string }>()
);

// 9. Lazy-loaded Feature State
// dashboard/dashboard.module.ts
@NgModule({
  imports: [
    CommonModule,
    StoreModule.forFeature('dashboard', dashboardReducer),
    EffectsModule.forFeature([DashboardEffects])
  ]
})
export class DashboardModule {}

// 10. Component with Advanced State Management
@Component({
  template: `
    <div class="user-management">
      <div class="filters">
        <input 
          [value]="filters$ | async" 
          (input)="updateSearch($event)"
          placeholder="Search users...">
        
        <select (change)="updateStatus($event)">
          <option value="all">All</option>
          <option value="active">Active</option>
          <option value="inactive">Inactive</option>
        </select>
      </div>
      
      <div class="users-list">
        <div *ngFor="let user of paginatedUsers$ | async" class="user-item">
          {{ user.name }} - {{ user.email }}
          <button (click)="selectUser(user.id)">Select</button>
          <button (click)="deleteUser(user.id)">Delete</button>
        </div>
      </div>
      
      <div class="pagination">
        <button 
          [disabled]="(pagination$ | async)?.currentPage === 1"
          (click)="previousPage()">
          Previous
        </button>
        <span>Page {{ (pagination$ | async)?.currentPage }} of {{ totalPages$ | async }}</span>
        <button 
          [disabled]="(pagination$ | async)?.currentPage === (totalPages$ | async)"
          (click)="nextPage()">
          Next
        </button>
      </div>
      
      <div *ngIf="selectedUser$ | async as selectedUser">
        <h3>Selected: {{ selectedUser.name }}</h3>
        <button (click)="editUser(selectedUser)">Edit</button>
      </div>
    </div>
  `
})
export class UserManagementComponent implements OnInit {
  users$ = this.store.select(selectPaginatedUsers);
  selectedUser$ = this.store.select(selectSelectedUser);
  filters$ = this.store.select(selectUserFilters);
  pagination$ = this.store.select(selectUserPagination);
  loading$ = this.store.select(selectUsersLoading);
  error$ = this.store.select(selectUsersError);
  totalPages$ = this.store.select(selectUserPagination).pipe(
    map(pagination => Math.ceil(pagination.totalItems / pagination.pageSize))
  );
  
  constructor(
    private store: Store<AppState>,
    private router: Router
  ) {}
  
  ngOnInit(): void {
    this.loadUsers();
  }
  
  loadUsers(): void {
    this.store.dispatch(UserActions.loadUsers({}));
  }
  
  updateSearch(event: any): void {
    this.store.dispatch(UserActions.updateFilters({ 
      searchTerm: event.target.value 
    }));
    this.loadUsers();
  }
  
  updateStatus(event: any): void {
    this.store.dispatch(UserActions.updateFilters({ 
      status: event.target.value 
    }));
    this.loadUsers();
  }
  
  selectUser(userId: number): void {
    this.store.dispatch(UserActions.selectUser({ userId }));
  }
  
  deleteUser(userId: number): void {
    this.store.dispatch(UserActions.deleteUser({ userId }));
  }
  
  previousPage(): void {
    this.store.select(selectUserPagination).pipe(take(1)).subscribe(pagination => {
      if (pagination.currentPage > 1) {
        this.store.dispatch(UserActions.loadUsers({
          pagination: { currentPage: pagination.currentPage - 1 }
        }));
      }
    });
  }
  
  nextPage(): void {
    this.store.select(selectUserPagination).pipe(take(1)).subscribe(pagination => {
      const totalPages = Math.ceil(pagination.totalItems / pagination.pageSize);
      if (pagination.currentPage < totalPages) {
        this.store.dispatch(UserActions.loadUsers({
          pagination: { currentPage: pagination.currentPage + 1 }
        }));
      }
    });
  }
  
  editUser(user: User): void {
    this.router.navigate(['/users', user.id, 'edit']);
  }
}
```

**🐞 Fixes:** Use feature-based architecture, implement proper error handling, use entity adapters for collections, and establish clear patterns for state organization.

---

### Tough Questions

1. How do you design a state system that syncs real-time data from WebSocket APIs with the UI? _(Asked in Deloitte)_

**🧩 Foundation:** Designing a state system for real-time WebSocket data involves creating a reactive architecture that can handle continuous data streams, maintain state consistency, and provide real-time UI updates while managing connection states and error scenarios.

**⚙️ Function:** A real-time state system enables live data synchronization, provides immediate UI feedback, handles connection management, and ensures data consistency across the application while maintaining performance and user experience.

**🚀 Features:**
- **WebSocket connection management:** Handle connection states and reconnection
- **Real-time data streaming:** Process continuous data updates
- **State synchronization:** Keep UI in sync with live data
- **Connection state handling:** Manage online/offline states
- **Data buffering:** Handle data during connection interruptions
- **Error recovery:** Graceful handling of connection failures

**🔁 Flow:**
```typescript
// 1. WebSocket Service with State Management
@Injectable({
  providedIn: 'root'
})
export class WebSocketService {
  private socket: WebSocket | null = null;
  private reconnectAttempts = 0;
  private maxReconnectAttempts = 5;
  private reconnectInterval = 1000;
  private messageQueue: any[] = [];
  
  private connectionStatusSubject = new BehaviorSubject<'connected' | 'disconnected' | 'connecting'>('disconnected');
  public connectionStatus$ = this.connectionStatusSubject.asObservable();
  
  private messageSubject = new Subject<any>();
  public messages$ = this.messageSubject.asObservable();
  
  constructor() {}
  
  connect(url: string): void {
    this.connectionStatusSubject.next('connecting');
    
    this.socket = new WebSocket(url);
    
    this.socket.onopen = () => {
      console.log('WebSocket connected');
      this.connectionStatusSubject.next('connected');
      this.reconnectAttempts = 0;
      this.processMessageQueue();
    };
    
    this.socket.onmessage = (event) => {
      const message = JSON.parse(event.data);
      this.messageSubject.next(message);
    };
    
    this.socket.onclose = () => {
      console.log('WebSocket disconnected');
      this.connectionStatusSubject.next('disconnected');
      this.attemptReconnect(url);
    };
    
    this.socket.onerror = (error) => {
      console.error('WebSocket error:', error);
      this.connectionStatusSubject.next('disconnected');
    };
  }
  
  private attemptReconnect(url: string): void {
    if (this.reconnectAttempts < this.maxReconnectAttempts) {
      this.reconnectAttempts++;
      console.log(`Attempting to reconnect... (${this.reconnectAttempts}/${this.maxReconnectAttempts})`);
      
      setTimeout(() => {
        this.connect(url);
      }, this.reconnectInterval * this.reconnectAttempts);
    }
  }
  
  sendMessage(message: any): void {
    if (this.socket && this.socket.readyState === WebSocket.OPEN) {
      this.socket.send(JSON.stringify(message));
    } else {
      this.messageQueue.push(message);
    }
  }
  
  private processMessageQueue(): void {
    while (this.messageQueue.length > 0) {
      const message = this.messageQueue.shift();
      this.sendMessage(message);
    }
  }
  
  disconnect(): void {
    if (this.socket) {
      this.socket.close();
      this.socket = null;
    }
  }
}

// 2. Real-time State Management
@Injectable({
  providedIn: 'root'
})
export class RealTimeStateService {
  private userStateSubject = new BehaviorSubject<User[]>([]);
  private notificationStateSubject = new BehaviorSubject<Notification[]>([]);
  private systemStatusSubject = new BehaviorSubject<SystemStatus>('offline');
  
  public users$ = this.userStateSubject.asObservable();
  public notifications$ = this.notificationStateSubject.asObservable();
  public systemStatus$ = this.systemStatusSubject.asObservable();
  
  constructor(private webSocketService: WebSocketService) {
    this.initializeWebSocketHandlers();
  }
  
  private initializeWebSocketHandlers(): void {
    // Handle user updates
    this.webSocketService.messages$.pipe(
      filter(message => message.type === 'user_update')
    ).subscribe(message => {
      this.handleUserUpdate(message.data);
    });
    
    // Handle notifications
    this.webSocketService.messages$.pipe(
      filter(message => message.type === 'notification')
    ).subscribe(message => {
      this.handleNotification(message.data);
    });
    
    // Handle system status
    this.webSocketService.messages$.pipe(
      filter(message => message.type === 'system_status')
    ).subscribe(message => {
      this.handleSystemStatus(message.data);
    });
    
    // Handle connection status
    this.webSocketService.connectionStatus$.subscribe(status => {
      this.handleConnectionStatus(status);
    });
  }
  
  private handleUserUpdate(userData: any): void {
    const currentUsers = this.userStateSubject.value;
    
    switch (userData.action) {
      case 'add':
        this.userStateSubject.next([...currentUsers, userData.user]);
        break;
      case 'update':
        this.userStateSubject.next(
          currentUsers.map(user => 
            user.id === userData.user.id ? userData.user : user
          )
        );
        break;
      case 'delete':
        this.userStateSubject.next(
          currentUsers.filter(user => user.id !== userData.userId)
        );
        break;
    }
  }
  
  private handleNotification(notification: Notification): void {
    const currentNotifications = this.notificationStateSubject.value;
    this.notificationStateSubject.next([notification, ...currentNotifications]);
    
    // Auto-remove notifications after 5 seconds
    setTimeout(() => {
      this.removeNotification(notification.id);
    }, 5000);
  }
  
  private handleSystemStatus(status: SystemStatus): void {
    this.systemStatusSubject.next(status);
  }
  
  private handleConnectionStatus(status: 'connected' | 'disconnected' | 'connecting'): void {
    if (status === 'disconnected') {
      this.systemStatusSubject.next('offline');
    } else if (status === 'connected') {
      this.systemStatusSubject.next('online');
    }
  }
  
  removeNotification(notificationId: string): void {
    const currentNotifications = this.notificationStateSubject.value;
    this.notificationStateSubject.next(
      currentNotifications.filter(n => n.id !== notificationId)
    );
  }
  
  sendUserAction(action: string, userData: any): void {
    this.webSocketService.sendMessage({
      type: 'user_action',
      action,
      data: userData
    });
  }
}

// 3. NgRx Integration for Real-time State
// real-time.actions.ts
export const connectWebSocket = createAction('[Real-time] Connect WebSocket');
export const disconnectWebSocket = createAction('[Real-time] Disconnect WebSocket');
export const webSocketConnected = createAction('[Real-time] WebSocket Connected');
export const webSocketDisconnected = createAction('[Real-time] WebSocket Disconnected');
export const webSocketMessageReceived = createAction(
  '[Real-time] Message Received',
  props<{ message: any }>()
);

export const updateUserRealTime = createAction(
  '[Real-time] Update User',
  props<{ user: User, action: 'add' | 'update' | 'delete' }>()
);

export const addNotificationRealTime = createAction(
  '[Real-time] Add Notification',
  props<{ notification: Notification }>()
);

export const removeNotificationRealTime = createAction(
  '[Real-time] Remove Notification',
  props<{ notificationId: string }>()
);

// real-time.reducer.ts
export interface RealTimeState {
  connectionStatus: 'connected' | 'disconnected' | 'connecting';
  users: User[];
  notifications: Notification[];
  systemStatus: SystemStatus;
  lastUpdate: Date | null;
}

export const initialState: RealTimeState = {
  connectionStatus: 'disconnected',
  users: [],
  notifications: [],
  systemStatus: 'offline',
  lastUpdate: null
};

export const realTimeReducer = createReducer(
  initialState,
  
  on(RealTimeActions.connectWebSocket, state => ({
    ...state,
    connectionStatus: 'connecting'
  })),
  
  on(RealTimeActions.webSocketConnected, state => ({
    ...state,
    connectionStatus: 'connected',
    systemStatus: 'online'
  })),
  
  on(RealTimeActions.webSocketDisconnected, state => ({
    ...state,
    connectionStatus: 'disconnected',
    systemStatus: 'offline'
  })),
  
  on(RealTimeActions.updateUserRealTime, (state, { user, action }) => {
    let updatedUsers = [...state.users];
    
    switch (action) {
      case 'add':
        updatedUsers = [...updatedUsers, user];
        break;
      case 'update':
        updatedUsers = updatedUsers.map(u => u.id === user.id ? user : u);
        break;
      case 'delete':
        updatedUsers = updatedUsers.filter(u => u.id !== user.id);
        break;
    }
    
    return {
      ...state,
      users: updatedUsers,
      lastUpdate: new Date()
    };
  }),
  
  on(RealTimeActions.addNotificationRealTime, (state, { notification }) => ({
    ...state,
    notifications: [notification, ...state.notifications],
    lastUpdate: new Date()
  })),
  
  on(RealTimeActions.removeNotificationRealTime, (state, { notificationId }) => ({
    ...state,
    notifications: state.notifications.filter(n => n.id !== notificationId),
    lastUpdate: new Date()
  }))
);

// real-time.effects.ts
@Injectable()
export class RealTimeEffects {
  connectWebSocket$ = createEffect(() => this.actions$.pipe(
    ofType(RealTimeActions.connectWebSocket),
    tap(() => this.webSocketService.connect('ws://localhost:8080/realtime'))
  ), { dispatch: false });

  disconnectWebSocket$ = createEffect(() => this.actions$.pipe(
    ofType(RealTimeActions.disconnectWebSocket),
    tap(() => this.webSocketService.disconnect())
  ), { dispatch: false });

  webSocketConnection$ = createEffect(() => this.webSocketService.connectionStatus$.pipe(
    map(status => {
      if (status === 'connected') {
        return RealTimeActions.webSocketConnected();
      } else if (status === 'disconnected') {
        return RealTimeActions.webSocketDisconnected();
      }
      return { type: 'NO_ACTION' };
    })
  ));

  webSocketMessages$ = createEffect(() => this.webSocketService.messages$.pipe(
    map(message => RealTimeActions.webSocketMessageReceived({ message }))
  ));

  handleWebSocketMessage$ = createEffect(() => this.actions$.pipe(
    ofType(RealTimeActions.webSocketMessageReceived),
    map(({ message }) => {
      switch (message.type) {
        case 'user_update':
          return RealTimeActions.updateUserRealTime({
            user: message.data.user,
            action: message.data.action
          });
        case 'notification':
          return RealTimeActions.addNotificationRealTime({
            notification: message.data
          });
        default:
          return { type: 'NO_ACTION' };
      }
    })
  ));

  constructor(
    private actions$: Actions,
    private webSocketService: WebSocketService
  ) {}
}

// 4. Component with Real-time State
@Component({
  template: `
    <div class="real-time-dashboard">
      <div class="connection-status" [class]="connectionStatus$ | async">
        {{ connectionStatus$ | async }}
      </div>
      
      <div class="system-status">
        System: {{ systemStatus$ | async }}
        <span *ngIf="lastUpdate$ | async as lastUpdate">
          Last Update: {{ lastUpdate | date:'HH:mm:ss' }}
        </span>
      </div>
      
      <div class="users-section">
        <h3>Real-time Users ({{ (users$ | async)?.length || 0 }})</h3>
        <div *ngFor="let user of users$ | async" class="user-item">
          <span [class.online]="user.online">{{ user.name }}</span>
          <span class="status">{{ user.status }}</span>
        </div>
      </div>
      
      <div class="notifications-section">
        <h3>Notifications</h3>
        <div *ngFor="let notification of notifications$ | async" class="notification">
          {{ notification.message }}
          <button (click)="removeNotification(notification.id)">×</button>
        </div>
      </div>
      
      <div class="actions">
        <button (click)="connect()">Connect</button>
        <button (click)="disconnect()">Disconnect</button>
        <button (click)="sendTestMessage()">Send Test</button>
      </div>
    </div>
  `,
  styles: [`
    .connection-status.connected { color: green; }
    .connection-status.disconnected { color: red; }
    .connection-status.connecting { color: orange; }
    .user-item .online { color: green; }
  `]
})
export class RealTimeDashboardComponent implements OnInit, OnDestroy {
  users$ = this.store.select(selectRealTimeUsers);
  notifications$ = this.store.select(selectRealTimeNotifications);
  connectionStatus$ = this.store.select(selectConnectionStatus);
  systemStatus$ = this.store.select(selectSystemStatus);
  lastUpdate$ = this.store.select(selectLastUpdate);
  
  constructor(
    private store: Store<AppState>,
    private webSocketService: WebSocketService
  ) {}
  
  ngOnInit(): void {
    this.connect();
  }
  
  ngOnDestroy(): void {
    this.disconnect();
  }
  
  connect(): void {
    this.store.dispatch(RealTimeActions.connectWebSocket());
  }
  
  disconnect(): void {
    this.store.dispatch(RealTimeActions.disconnectWebSocket());
  }
  
  sendTestMessage(): void {
    this.webSocketService.sendMessage({
      type: 'test',
      data: { message: 'Test message', timestamp: Date.now() }
    });
  }
  
  removeNotification(notificationId: string): void {
    this.store.dispatch(RealTimeActions.removeNotificationRealTime({ notificationId }));
  }
}

// 5. Advanced Real-time Features
@Injectable({
  providedIn: 'root'
})
export class AdvancedRealTimeService {
  private dataBuffer = new Map<string, any[]>();
  private syncInProgress = false;
  
  constructor(
    private webSocketService: WebSocketService,
    private store: Store<AppState>
  ) {
    this.handleConnectionChanges();
  }
  
  private handleConnectionChanges(): void {
    this.webSocketService.connectionStatus$.subscribe(status => {
      if (status === 'connected') {
        this.syncBufferedData();
      }
    });
  }
  
  private syncBufferedData(): void {
    if (this.syncInProgress) return;
    
    this.syncInProgress = true;
    
    this.dataBuffer.forEach((data, key) => {
      data.forEach(item => {
        this.webSocketService.sendMessage({
          type: 'sync',
          key,
          data: item
        });
      });
    });
    
    this.dataBuffer.clear();
    this.syncInProgress = false;
  }
  
  sendMessageWithBuffer(type: string, data: any, key?: string): void {
    if (this.webSocketService.connectionStatus$.value === 'connected') {
      this.webSocketService.sendMessage({ type, data });
    } else if (key) {
      if (!this.dataBuffer.has(key)) {
        this.dataBuffer.set(key, []);
      }
      this.dataBuffer.get(key)!.push(data);
    }
  }
}
```

**🐞 Fixes:** Always handle connection states, implement data buffering for offline scenarios, use proper error handling, and ensure data consistency during reconnections.

---

## 10. HTTP Client and Interceptors

### Basic Questions

1. What is HttpClient in Angular and how is it different from the legacy Http module? _(Asked in TCS, Capgemini)_
2. How do you make a GET/POST request using HttpClient? _(Asked in Infosys)_

### Intermediate Questions

1. What is an HttpInterceptor and how does it work in Angular? _(Asked in Wipro)_

**🧩 Foundation:** HttpInterceptor is an interface in Angular that allows you to intercept and modify HTTP requests and responses globally, providing a way to add common functionality like authentication headers, error handling, logging, and request/response transformation.

**⚙️ Function:** HttpInterceptors act as middleware for HTTP requests, enabling centralized handling of cross-cutting concerns such as authentication, caching, error handling, and request/response manipulation without modifying individual service methods.

**🚀 Features:**
- Intercept all HTTP requests and responses
- Modify request headers, body, or URL
- Transform response data
- Handle errors globally
- Add authentication tokens automatically
- Implement request/response logging
- Support multiple interceptors with ordering

**🔁 Flow:**
```typescript
// 1. Basic HttpInterceptor Implementation
import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class LoggingInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log('Request URL:', req.url);
    console.log('Request Method:', req.method);
    console.log('Request Headers:', req.headers);
    
    const startTime = Date.now();
    
    return next.handle(req).pipe(
      tap(event => {
        if (event instanceof HttpResponse) {
          const endTime = Date.now();
          console.log(`Request to ${req.url} took ${endTime - startTime}ms`);
          console.log('Response:', event.body);
        }
      })
    );
  }
}

// 2. Authentication Interceptor
@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(private authService: AuthService) {}
  
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = this.authService.getToken();
    
    if (token) {
      const authReq = req.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`
        }
      });
      return next.handle(authReq);
    }
    
    return next.handle(req);
  }
}

// 3. Error Handling Interceptor
@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
  constructor(private router: Router, private authService: AuthService) {}
  
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === 401) {
          // Token expired or invalid
          this.authService.logout();
          this.router.navigate(['/login']);
        } else if (error.status === 403) {
          // Access forbidden
          this.router.navigate(['/forbidden']);
        } else if (error.status === 500) {
          // Server error
          console.error('Server error:', error);
        }
        
        return throwError(() => error);
      })
    );
  }
}

// 4. Caching Interceptor
@Injectable()
export class CacheInterceptor implements HttpInterceptor {
  private cache = new Map<string, any>();
  private maxAge = 5 * 60 * 1000; // 5 minutes
  
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // Only cache GET requests
    if (req.method !== 'GET') {
      return next.handle(req);
    }
    
    const cachedResponse = this.getCachedResponse(req.url);
    if (cachedResponse) {
      return of(cachedResponse);
    }
    
    return next.handle(req).pipe(
      tap(event => {
        if (event instanceof HttpResponse) {
          this.cacheResponse(req.url, event);
        }
      })
    );
  }
  
  private getCachedResponse(url: string): HttpResponse<any> | null {
    const cached = this.cache.get(url);
    if (cached && Date.now() - cached.timestamp < this.maxAge) {
      return cached.response;
    }
    this.cache.delete(url);
    return null;
  }
  
  private cacheResponse(url: string, response: HttpResponse<any>): void {
    this.cache.set(url, {
      response,
      timestamp: Date.now()
    });
  }
}

// 5. Request Transformation Interceptor
@Injectable()
export class TransformInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // Transform request body
    if (req.body) {
      const transformedBody = this.transformRequest(req.body);
      const transformedReq = req.clone({
        body: transformedBody
      });
      return next.handle(transformedReq);
    }
    
    return next.handle(req);
  }
  
  private transformRequest(body: any): any {
    // Example: Convert camelCase to snake_case for API
    return this.convertToSnakeCase(body);
  }
  
  private convertToSnakeCase(obj: any): any {
    if (Array.isArray(obj)) {
      return obj.map(item => this.convertToSnakeCase(item));
    }
    
    if (obj !== null && typeof obj === 'object') {
      const newObj: any = {};
      Object.keys(obj).forEach(key => {
        const snakeKey = key.replace(/[A-Z]/g, letter => `_${letter.toLowerCase()}`);
        newObj[snakeKey] = this.convertToSnakeCase(obj[key]);
      });
      return newObj;
    }
    
    return obj;
  }
}

// 6. Registering Interceptors
@NgModule({
  imports: [HttpClientModule],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: LoggingInterceptor,
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: ErrorInterceptor,
      multi: true
    }
  ]
})
export class AppModule {}
```

**🐞 Fixes:** Always handle errors in interceptors, avoid infinite loops when modifying requests, use proper typing for request/response objects, and ensure interceptors are registered in the correct order.

---

2. How do you use an interceptor to attach auth tokens to every request? _(Asked in Cognizant)_

**🧩 Foundation:** Authentication interceptors automatically attach authorization tokens to HTTP requests by intercepting outgoing requests and adding the required headers, typically using Bearer token authentication or custom header formats.

**⚙️ Function:** Auth interceptors centralize authentication logic, ensuring all HTTP requests include proper authorization headers without requiring manual token management in each service or component.

**🚀 Features:**
- Automatic token injection into request headers
- Token refresh handling for expired tokens
- Support for different authentication schemes (Bearer, Basic, etc.)
- Conditional token attachment based on request URL
- Error handling for authentication failures
- Integration with authentication services

**🔁 Flow:**
```typescript
// 1. Basic Auth Token Interceptor
@Injectable()
export class AuthTokenInterceptor implements HttpInterceptor {
  constructor(private authService: AuthService) {}
  
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = this.authService.getToken();
    
    if (token) {
      const authReq = req.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`
        }
      });
      return next.handle(authReq);
    }
    
    return next.handle(req);
  }
}

// 2. Advanced Auth Interceptor with Token Refresh
@Injectable()
export class AdvancedAuthInterceptor implements HttpInterceptor {
  private isRefreshing = false;
  private refreshTokenSubject: BehaviorSubject<any> = new BehaviorSubject<any>(null);
  
  constructor(
    private authService: AuthService,
    private router: Router
  ) {}
  
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = this.authService.getToken();
    
    if (token) {
      req = this.addToken(req, token);
    }
    
    return next.handle(req).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === 401 && !req.url.includes('refresh')) {
          return this.handle401Error(req, next);
        }
        return throwError(() => error);
      })
    );
  }
  
  private addToken(req: HttpRequest<any>, token: string): HttpRequest<any> {
    return req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });
  }
  
  private handle401Error(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (!this.isRefreshing) {
      this.isRefreshing = true;
      this.refreshTokenSubject.next(null);
      
      return this.authService.refreshToken().pipe(
        switchMap((token: any) => {
          this.isRefreshing = false;
          this.refreshTokenSubject.next(token);
          return next.handle(this.addToken(req, token));
        }),
        catchError((err) => {
          this.isRefreshing = false;
          this.authService.logout();
          this.router.navigate(['/login']);
          return throwError(() => err);
        })
      );
    } else {
      return this.refreshTokenSubject.pipe(
        filter(token => token !== null),
        take(1),
        switchMap(token => next.handle(this.addToken(req, token)))
      );
    }
  }
}

// 3. Conditional Auth Interceptor
@Injectable()
export class ConditionalAuthInterceptor implements HttpInterceptor {
  private publicUrls = [
    '/api/public',
    '/api/auth/login',
    '/api/auth/register'
  ];
  
  constructor(private authService: AuthService) {}
  
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // Skip authentication for public URLs
    if (this.isPublicUrl(req.url)) {
      return next.handle(req);
    }
    
    const token = this.authService.getToken();
    
    if (token) {
      const authReq = req.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`,
          'X-Requested-With': 'XMLHttpRequest'
        }
      });
      return next.handle(authReq);
    }
    
    return next.handle(req);
  }
  
  private isPublicUrl(url: string): boolean {
    return this.publicUrls.some(publicUrl => url.includes(publicUrl));
  }
}

// 4. Multi-Scheme Auth Interceptor
@Injectable()
export class MultiSchemeAuthInterceptor implements HttpInterceptor {
  constructor(private authService: AuthService) {}
  
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const authType = this.getAuthType(req.url);
    
    switch (authType) {
      case 'bearer':
        return this.addBearerToken(req, next);
      case 'basic':
        return this.addBasicAuth(req, next);
      case 'api-key':
        return this.addApiKey(req, next);
      default:
        return next.handle(req);
    }
  }
  
  private getAuthType(url: string): string {
    if (url.includes('/api/v1/')) return 'bearer';
    if (url.includes('/api/v2/')) return 'basic';
    if (url.includes('/api/external/')) return 'api-key';
    return 'none';
  }
  
  private addBearerToken(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = this.authService.getBearerToken();
    if (token) {
      const authReq = req.clone({
        setHeaders: { Authorization: `Bearer ${token}` }
      });
      return next.handle(authReq);
    }
    return next.handle(req);
  }
  
  private addBasicAuth(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const credentials = this.authService.getBasicCredentials();
    if (credentials) {
      const authReq = req.clone({
        setHeaders: { 
          Authorization: `Basic ${btoa(`${credentials.username}:${credentials.password}`)}`
        }
      });
      return next.handle(authReq);
    }
    return next.handle(req);
  }
  
  private addApiKey(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const apiKey = this.authService.getApiKey();
    if (apiKey) {
      const authReq = req.clone({
        setHeaders: { 'X-API-Key': apiKey }
      });
      return next.handle(authReq);
    }
    return next.handle(req);
  }
}

// 5. Auth Service Integration
@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private tokenKey = 'auth_token';
  private refreshTokenKey = 'refresh_token';
  
  getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }
  
  setToken(token: string): void {
    localStorage.setItem(this.tokenKey, token);
  }
  
  getRefreshToken(): string | null {
    return localStorage.getItem(this.refreshTokenKey);
  }
  
  setRefreshToken(token: string): void {
    localStorage.setItem(this.refreshTokenKey, token);
  }
  
  logout(): void {
    localStorage.removeItem(this.tokenKey);
    localStorage.removeItem(this.refreshTokenKey);
  }
  
  refreshToken(): Observable<any> {
    const refreshToken = this.getRefreshToken();
    if (!refreshToken) {
      return throwError(() => new Error('No refresh token available'));
    }
    
    return this.http.post('/api/auth/refresh', { refreshToken }).pipe(
      tap((response: any) => {
        this.setToken(response.accessToken);
        this.setRefreshToken(response.refreshToken);
      })
    );
  }
  
  constructor(private http: HttpClient) {}
}
```

**🐞 Fixes:** Handle token expiration gracefully, implement proper error handling for auth failures, avoid infinite loops in token refresh, and ensure secure token storage.

### Advanced Questions

1. How do you create multiple interceptors in Angular and manage their order of execution? _(Asked in HCL)_

**🧩 Foundation:** Multiple interceptors in Angular are registered using the `HTTP_INTERCEPTORS` token with `multi: true`, and their execution order follows the registration order - interceptors are executed in the order they are provided, with responses flowing back in reverse order.

**⚙️ Function:** Multiple interceptors enable modular, reusable HTTP request/response processing by allowing different concerns (authentication, logging, caching, error handling) to be handled by separate, focused interceptors while maintaining predictable execution order.

**🚀 Features:**
- Sequential execution in registration order
- Reverse order for response processing
- Modular separation of concerns
- Conditional execution based on request properties
- Shared state between interceptors
- Error propagation through interceptor chain

**🔁 Flow:**
```typescript
// 1. Multiple Interceptors with Order Management
@NgModule({
  imports: [HttpClientModule],
  providers: [
    // Order: 1. Logging, 2. Auth, 3. Error Handling, 4. Caching
    {
      provide: HTTP_INTERCEPTORS,
      useClass: LoggingInterceptor,
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: ErrorInterceptor,
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: CacheInterceptor,
      multi: true
    }
  ]
})
export class AppModule {}

// 2. Execution Order Example
@Injectable()
export class LoggingInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log('1. LoggingInterceptor - Request:', req.url);
    
    return next.handle(req).pipe(
      tap(event => {
        if (event instanceof HttpResponse) {
          console.log('4. LoggingInterceptor - Response:', event.status);
        }
      })
    );
  }
}

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log('2. AuthInterceptor - Adding token');
    const authReq = req.clone({
      setHeaders: { Authorization: 'Bearer token' }
    });
    
    return next.handle(authReq).pipe(
      tap(event => {
        if (event instanceof HttpResponse) {
          console.log('3. AuthInterceptor - Response processed');
        }
      })
    );
  }
}

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log('3. ErrorInterceptor - Processing request');
    
    return next.handle(req).pipe(
      catchError(error => {
        console.log('3. ErrorInterceptor - Handling error:', error.status);
        return throwError(() => error);
      }),
      tap(event => {
        if (event instanceof HttpResponse) {
          console.log('2. ErrorInterceptor - Response processed');
        }
      })
    );
  }
}

@Injectable()
export class CacheInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log('4. CacheInterceptor - Checking cache');
    
    return next.handle(req).pipe(
      tap(event => {
        if (event instanceof HttpResponse) {
          console.log('1. CacheInterceptor - Caching response');
        }
      })
    );
  }
}

// 3. Conditional Interceptor Execution
@Injectable()
export class ConditionalInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // Only apply to specific URLs
    if (req.url.includes('/api/secure/')) {
      console.log('Applying secure interceptor logic');
      const secureReq = req.clone({
        setHeaders: { 'X-Secure': 'true' }
      });
      return next.handle(secureReq);
    }
    
    // Skip for public URLs
    if (req.url.includes('/api/public/')) {
      console.log('Skipping interceptor for public URL');
      return next.handle(req);
    }
    
    // Default processing
    return next.handle(req);
  }
}

// 4. Interceptor with Shared State
@Injectable()
export class StatefulInterceptor implements HttpInterceptor {
  private requestCount = 0;
  private activeRequests = new Set<string>();
  
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    this.requestCount++;
    const requestId = `${req.method}-${req.url}-${this.requestCount}`;
    this.activeRequests.add(requestId);
    
    console.log(`Active requests: ${this.activeRequests.size}`);
    
    return next.handle(req).pipe(
      finalize(() => {
        this.activeRequests.delete(requestId);
        console.log(`Request completed. Active requests: ${this.activeRequests.size}`);
      })
    );
  }
}

// 5. Interceptor Chain with Error Recovery
@Injectable()
export class RetryInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(
      retryWhen(errors => 
        errors.pipe(
          switchMap((error, index) => {
            if (index < 2 && error.status === 500) {
              console.log(`Retrying request (attempt ${index + 1})`);
              return timer(1000 * (index + 1)); // Exponential backoff
            }
            return throwError(() => error);
          })
        )
      )
    );
  }
}

// 6. Feature Module Interceptors
@NgModule({
  imports: [HttpClientModule],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: FeatureSpecificInterceptor,
      multi: true
    }
  ]
})
export class FeatureModule {}

@Injectable()
export class FeatureSpecificInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // Only apply to feature-specific URLs
    if (req.url.includes('/api/feature/')) {
      const featureReq = req.clone({
        setHeaders: { 'X-Feature': 'enabled' }
      });
      return next.handle(featureReq);
    }
    
    return next.handle(req);
  }
}

// 7. Interceptor with Request Transformation Chain
@Injectable()
export class TransformChainInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let transformedReq = req;
    
    // Apply multiple transformations
    transformedReq = this.addTimestamp(transformedReq);
    transformedReq = this.addCorrelationId(transformedReq);
    transformedReq = this.addUserContext(transformedReq);
    
    return next.handle(transformedReq);
  }
  
  private addTimestamp(req: HttpRequest<any>): HttpRequest<any> {
    return req.clone({
      setHeaders: { 'X-Timestamp': Date.now().toString() }
    });
  }
  
  private addCorrelationId(req: HttpRequest<any>): HttpRequest<any> {
    return req.clone({
      setHeaders: { 'X-Correlation-ID': this.generateCorrelationId() }
    });
  }
  
  private addUserContext(req: HttpRequest<any>): HttpRequest<any> {
    const userContext = this.getUserContext();
    if (userContext) {
      return req.clone({
        setHeaders: { 'X-User-Context': JSON.stringify(userContext) }
      });
    }
    return req;
  }
  
  private generateCorrelationId(): string {
    return `req-${Date.now()}-${Math.random().toString(36).substr(2, 9)}`;
  }
  
  private getUserContext(): any {
    // Get user context from service
    return { userId: '123', role: 'user' };
  }
}

// 8. Testing Multiple Interceptors
describe('Interceptor Chain', () => {
  let httpMock: HttpTestingController;
  let http: HttpClient;
  
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [
        {
          provide: HTTP_INTERCEPTORS,
          useClass: LoggingInterceptor,
          multi: true
        },
        {
          provide: HTTP_INTERCEPTORS,
          useClass: AuthInterceptor,
          multi: true
        }
      ]
    });
    
    httpMock = TestBed.inject(HttpTestingController);
    http = TestBed.inject(HttpClient);
  });
  
  it('should execute interceptors in correct order', () => {
    const spy = spyOn(console, 'log');
    
    http.get('/api/test').subscribe();
    
    const req = httpMock.expectOne('/api/test');
    req.flush({ data: 'test' });
    
    expect(spy).toHaveBeenCalledWith('1. LoggingInterceptor - Request:', '/api/test');
    expect(spy).toHaveBeenCalledWith('2. AuthInterceptor - Adding token');
  });
});
```

**🐞 Fixes:** Register interceptors in the correct order, handle errors properly in the chain, avoid infinite loops, and ensure proper cleanup of resources in interceptors.

### Tough Questions

1. You have multiple environments (dev, staging, prod) — how would you dynamically set the base API URL using interceptors or tokens? _(Asked in Deloitte)_

**🧩 Foundation:** Dynamic base URL configuration in Angular can be achieved through environment-specific configuration files, interceptors that modify request URLs, or runtime configuration services that determine the appropriate API endpoint based on the current environment or user context.

**⚙️ Function:** Dynamic base URL management enables seamless deployment across multiple environments, supports multi-tenant applications, and allows runtime switching between different API endpoints without code changes or rebuilds.

**🚀 Features:**
- Environment-specific configuration files
- Runtime URL resolution based on context
- Interceptor-based URL transformation
- Multi-tenant support with dynamic endpoints
- Configuration service with fallback mechanisms
- Support for feature flags and A/B testing

**🔁 Flow:**
```typescript
// 1. Environment Configuration
// environment.ts
export const environment = {
  production: false,
  apiBaseUrl: 'http://localhost:8080/api',
  apiVersion: 'v1'
};

// environment.prod.ts
export const environment = {
  production: true,
  apiBaseUrl: 'https://api.production.com/api',
  apiVersion: 'v1'
};

// environment.staging.ts
export const environment = {
  production: false,
  apiBaseUrl: 'https://api.staging.com/api',
  apiVersion: 'v1'
};

// 2. Configuration Service
@Injectable({
  providedIn: 'root'
})
export class ApiConfigService {
  private config: ApiConfig = {
    baseUrl: '',
    version: 'v1',
    timeout: 30000
  };
  
  constructor() {
    this.loadConfiguration();
  }
  
  private loadConfiguration(): void {
    // Load from environment
    this.config.baseUrl = environment.apiBaseUrl;
    this.config.version = environment.apiVersion;
    
    // Override with runtime config if available
    const runtimeConfig = this.getRuntimeConfig();
    if (runtimeConfig) {
      this.config = { ...this.config, ...runtimeConfig };
    }
  }
  
  private getRuntimeConfig(): Partial<ApiConfig> | null {
    // Check for runtime configuration (e.g., from localStorage, query params)
    const runtimeConfig = localStorage.getItem('apiConfig');
    return runtimeConfig ? JSON.parse(runtimeConfig) : null;
  }
  
  getBaseUrl(): string {
    return this.config.baseUrl;
  }
  
  getApiUrl(path: string): string {
    return `${this.config.baseUrl}/${this.config.version}${path}`;
  }
  
  setBaseUrl(url: string): void {
    this.config.baseUrl = url;
    localStorage.setItem('apiConfig', JSON.stringify(this.config));
  }
  
  // Multi-tenant support
  setTenant(tenantId: string): void {
    const tenantConfig = this.getTenantConfig(tenantId);
    if (tenantConfig) {
      this.config.baseUrl = tenantConfig.apiUrl;
      this.config.version = tenantConfig.apiVersion;
    }
  }
  
  private getTenantConfig(tenantId: string): any {
    const tenantConfigs = {
      'tenant1': { apiUrl: 'https://api.tenant1.com/api', apiVersion: 'v2' },
      'tenant2': { apiUrl: 'https://api.tenant2.com/api', apiVersion: 'v1' }
    };
    return tenantConfigs[tenantId];
  }
}

// 3. Dynamic URL Interceptor
@Injectable()
export class DynamicUrlInterceptor implements HttpInterceptor {
  constructor(private apiConfig: ApiConfigService) {}
  
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // Skip if already absolute URL
    if (req.url.startsWith('http')) {
      return next.handle(req);
    }
    
    // Determine base URL based on request context
    const baseUrl = this.determineBaseUrl(req);
    const fullUrl = `${baseUrl}${req.url}`;
    
    const modifiedReq = req.clone({
      url: fullUrl
    });
    
    return next.handle(modifiedReq);
  }
  
  private determineBaseUrl(req: HttpRequest<any>): string {
    // Check for specific API endpoints
    if (req.url.includes('/auth/')) {
      return 'https://auth.production.com/api';
    }
    
    if (req.url.includes('/external/')) {
      return 'https://external-api.com/api';
    }
    
    // Check for tenant-specific requests
    const tenantId = this.getCurrentTenant();
    if (tenantId) {
      const tenantConfig = this.getTenantConfig(tenantId);
      return tenantConfig?.apiUrl || this.apiConfig.getBaseUrl();
    }
    
    // Default to configured base URL
    return this.apiConfig.getBaseUrl();
  }
  
  private getCurrentTenant(): string | null {
    // Get tenant from URL, localStorage, or user context
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get('tenant') || localStorage.getItem('currentTenant');
  }
  
  private getTenantConfig(tenantId: string): any {
    // Implementation similar to ApiConfigService
    return null;
  }
}

// 4. Runtime Configuration with Feature Flags
@Injectable({
  providedIn: 'root'
})
export class RuntimeConfigService {
  private config: RuntimeConfig = {
    apiEndpoints: {},
    featureFlags: {},
    environment: 'production'
  };
  
  constructor(private http: HttpClient) {
    this.loadRuntimeConfig();
  }
  
  private loadRuntimeConfig(): void {
    // Load configuration from a remote endpoint
    this.http.get<RuntimeConfig>('/config/runtime.json')
      .pipe(
        catchError(() => this.getDefaultConfig())
      )
      .subscribe(config => {
        this.config = { ...this.config, ...config };
        this.applyConfiguration();
      });
  }
  
  private getDefaultConfig(): Observable<RuntimeConfig> {
    return of({
      apiEndpoints: {
        users: environment.apiBaseUrl + '/users',
        products: environment.apiBaseUrl + '/products'
      },
      featureFlags: {
        newApi: false,
        betaFeatures: false
      },
      environment: environment.production ? 'production' : 'development'
    });
  }
  
  private applyConfiguration(): void {
    // Apply configuration to services
    this.updateApiEndpoints();
    this.updateFeatureFlags();
  }
  
  private updateApiEndpoints(): void {
    Object.keys(this.config.apiEndpoints).forEach(key => {
      localStorage.setItem(`api_${key}`, this.config.apiEndpoints[key]);
    });
  }
  
  private updateFeatureFlags(): void {
    Object.keys(this.config.featureFlags).forEach(key => {
      localStorage.setItem(`feature_${key}`, this.config.featureFlags[key].toString());
    });
  }
  
  getApiEndpoint(service: string): string {
    return this.config.apiEndpoints[service] || environment.apiBaseUrl;
  }
  
  isFeatureEnabled(feature: string): boolean {
    return this.config.featureFlags[feature] || false;
  }
}

// 5. Environment Detection and Switching
@Injectable({
  providedIn: 'root'
})
export class EnvironmentService {
  private currentEnvironment: string = 'production';
  
  constructor() {
    this.detectEnvironment();
  }
  
  private detectEnvironment(): void {
    // Detect environment from various sources
    const hostname = window.location.hostname;
    const port = window.location.port;
    const protocol = window.location.protocol;
    
    if (hostname === 'localhost' || hostname === '127.0.0.1') {
      this.currentEnvironment = 'development';
    } else if (hostname.includes('staging') || hostname.includes('test')) {
      this.currentEnvironment = 'staging';
    } else if (hostname.includes('dev') || port === '4200') {
      this.currentEnvironment = 'development';
    } else {
      this.currentEnvironment = 'production';
    }
    
    // Override with query parameter
    const urlParams = new URLSearchParams(window.location.search);
    const envParam = urlParams.get('env');
    if (envParam && ['development', 'staging', 'production'].includes(envParam)) {
      this.currentEnvironment = envParam;
    }
  }
  
  getEnvironment(): string {
    return this.currentEnvironment;
  }
  
  getApiBaseUrl(): string {
    const configs = {
      development: 'http://localhost:8080/api',
      staging: 'https://api.staging.com/api',
      production: 'https://api.production.com/api'
    };
    return configs[this.currentEnvironment] || configs.production;
  }
  
  switchEnvironment(environment: string): void {
    if (['development', 'staging', 'production'].includes(environment)) {
      this.currentEnvironment = environment;
      localStorage.setItem('preferredEnvironment', environment);
      window.location.reload();
    }
  }
}

// 6. Multi-Environment API Service
@Injectable({
  providedIn: 'root'
})
export class MultiEnvironmentApiService {
  constructor(
    private http: HttpClient,
    private apiConfig: ApiConfigService,
    private envService: EnvironmentService
  ) {}
  
  get<T>(path: string, options?: any): Observable<T> {
    const url = this.buildUrl(path);
    return this.http.get<T>(url, options);
  }
  
  post<T>(path: string, body: any, options?: any): Observable<T> {
    const url = this.buildUrl(path);
    return this.http.post<T>(url, body, options);
  }
  
  private buildUrl(path: string): string {
    // Check for environment-specific overrides
    const envOverride = this.getEnvironmentOverride(path);
    if (envOverride) {
      return envOverride;
    }
    
    // Use configured base URL
    return this.apiConfig.getApiUrl(path);
  }
  
  private getEnvironmentOverride(path: string): string | null {
    const environment = this.envService.getEnvironment();
    const overrides = {
      development: {
        '/users': 'http://localhost:3000/mock-api/users',
        '/products': 'http://localhost:3000/mock-api/products'
      },
      staging: {
        '/users': 'https://staging-users-api.com/api/users',
        '/products': 'https://staging-products-api.com/api/products'
      }
    };
    
    return overrides[environment]?.[path] || null;
  }
}

// 7. Configuration Module
@NgModule({
  providers: [
    ApiConfigService,
    RuntimeConfigService,
    EnvironmentService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: DynamicUrlInterceptor,
      multi: true
    }
  ]
})
export class ConfigurationModule {}
```

**🐞 Fixes:** Always provide fallback configurations, handle configuration loading errors gracefully, ensure secure storage of sensitive configuration data, and implement proper validation for dynamic URLs.

---

2. Your API server returns a 401 when token expires. How would you refresh the token and retry failed requests? _(Asked in Cognizant)_

**🧩 Foundation:** Token refresh mechanisms in Angular involve intercepting 401 responses, automatically refreshing the access token using a refresh token, and retrying the original failed requests with the new token, all while preventing multiple simultaneous refresh attempts.

**⚙️ Function:** Token refresh interceptors provide seamless authentication experience by automatically handling token expiration, reducing user friction, and maintaining session continuity without requiring manual re-authentication.

**🚀 Features:**
- Automatic 401 response interception
- Single refresh token request for multiple failed requests
- Request queuing during token refresh
- Automatic retry of failed requests with new token
- Fallback to login on refresh failure
- Support for different token storage strategies

**🔁 Flow:**
```typescript
// 1. Advanced Token Refresh Interceptor
@Injectable()
export class TokenRefreshInterceptor implements HttpInterceptor {
  private isRefreshing = false;
  private refreshTokenSubject: BehaviorSubject<any> = new BehaviorSubject<any>(null);
  private failedRequests: Array<{ request: HttpRequest<any>, handler: HttpHandler }> = [];
  
  constructor(
    private authService: AuthService,
    private router: Router
  ) {}
  
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // Skip refresh token requests to avoid infinite loops
    if (req.url.includes('/auth/refresh')) {
      return next.handle(req);
    }
    
    const token = this.authService.getAccessToken();
    if (token) {
      req = this.addToken(req, token);
    }
    
    return next.handle(req).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === 401 && !req.url.includes('/auth/refresh')) {
          return this.handle401Error(req, next);
        }
        return throwError(() => error);
      })
    );
  }
  
  private addToken(req: HttpRequest<any>, token: string): HttpRequest<any> {
    return req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`
      }
    });
  }
  
  private handle401Error(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (!this.isRefreshing) {
      this.isRefreshing = true;
      this.refreshTokenSubject.next(null);
      
      return this.authService.refreshToken().pipe(
        switchMap((response: any) => {
          this.isRefreshing = false;
          this.authService.setTokens(response.accessToken, response.refreshToken);
          this.refreshTokenSubject.next(response.accessToken);
          
          // Retry all failed requests
          this.retryFailedRequests(response.accessToken);
          
          // Retry the current request
          return next.handle(this.addToken(req, response.accessToken));
        }),
        catchError((error) => {
          this.isRefreshing = false;
          this.authService.logout();
          this.router.navigate(['/login']);
          return throwError(() => error);
        })
      );
    } else {
      // Queue the failed request
      this.failedRequests.push({ request: req, handler: next });
      
      return this.refreshTokenSubject.pipe(
        filter(token => token !== null),
        take(1),
        switchMap(token => next.handle(this.addToken(req, token)))
      );
    }
  }
  
  private retryFailedRequests(token: string): void {
    this.failedRequests.forEach(({ request, handler }) => {
      handler.handle(this.addToken(request, token)).subscribe();
    });
    this.failedRequests = [];
  }
}

// 2. Enhanced Auth Service with Token Management
@Injectable({
  providedIn: 'root'
})
export class EnhancedAuthService {
  private accessTokenKey = 'access_token';
  private refreshTokenKey = 'refresh_token';
  private tokenExpiryKey = 'token_expiry';
  
  constructor(private http: HttpClient) {}
  
  getAccessToken(): string | null {
    const token = localStorage.getItem(this.accessTokenKey);
    const expiry = localStorage.getItem(this.tokenExpiryKey);
    
    if (token && expiry && Date.now() < parseInt(expiry)) {
      return token;
    }
    
    // Token expired, try to refresh
    if (this.getRefreshToken()) {
      this.refreshToken().subscribe();
    }
    
    return null;
  }
  
  getRefreshToken(): string | null {
    return localStorage.getItem(this.refreshTokenKey);
  }
  
  setTokens(accessToken: string, refreshToken: string, expiresIn?: number): void {
    localStorage.setItem(this.accessTokenKey, accessToken);
    localStorage.setItem(this.refreshTokenKey, refreshToken);
    
    if (expiresIn) {
      const expiry = Date.now() + (expiresIn * 1000);
      localStorage.setItem(this.tokenExpiryKey, expiry.toString());
    }
  }
  
  refreshToken(): Observable<any> {
    const refreshToken = this.getRefreshToken();
    if (!refreshToken) {
      return throwError(() => new Error('No refresh token available'));
    }
    
    return this.http.post('/api/auth/refresh', { refreshToken }).pipe(
      tap((response: any) => {
        this.setTokens(
          response.accessToken,
          response.refreshToken || refreshToken,
          response.expiresIn
        );
      }),
      catchError(error => {
        this.logout();
        return throwError(() => error);
      })
    );
  }
  
  logout(): void {
    localStorage.removeItem(this.accessTokenKey);
    localStorage.removeItem(this.refreshTokenKey);
    localStorage.removeItem(this.tokenExpiryKey);
  }
  
  isTokenExpired(): boolean {
    const expiry = localStorage.getItem(this.tokenExpiryKey);
    return expiry ? Date.now() >= parseInt(expiry) : true;
  }
  
  // Proactive token refresh
  refreshTokenIfNeeded(): Observable<any> {
    if (this.isTokenExpired() && this.getRefreshToken()) {
      return this.refreshToken();
    }
    return of(null);
  }
}

// 3. Proactive Token Refresh Service
@Injectable({
  providedIn: 'root'
})
export class TokenRefreshService {
  private refreshTimer: any;
  
  constructor(private authService: EnhancedAuthService) {
    this.setupProactiveRefresh();
  }
  
  private setupProactiveRefresh(): void {
    // Check token every minute
    setInterval(() => {
      this.checkAndRefreshToken();
    }, 60000);
  }
  
  private checkAndRefreshToken(): void {
    if (this.authService.isTokenExpired()) {
      this.authService.refreshTokenIfNeeded().subscribe();
    }
  }
  
  // Refresh token 5 minutes before expiry
  scheduleRefresh(expiresIn: number): void {
    const refreshTime = (expiresIn - 300) * 1000; // 5 minutes before expiry
    
    if (this.refreshTimer) {
      clearTimeout(this.refreshTimer);
    }
    
    this.refreshTimer = setTimeout(() => {
      this.authService.refreshToken().subscribe();
    }, refreshTime);
  }
}

// 4. Request Retry Service
@Injectable({
  providedIn: 'root'
})
export class RequestRetryService {
  private retryQueue: Array<{ request: HttpRequest<any>, handler: HttpHandler }> = [];
  private isProcessing = false;
  
  addToRetryQueue(request: HttpRequest<any>, handler: HttpHandler): void {
    this.retryQueue.push({ request, handler });
    
    if (!this.isProcessing) {
      this.processRetryQueue();
    }
  }
  
  private processRetryQueue(): void {
    if (this.retryQueue.length === 0) {
      this.isProcessing = false;
      return;
    }
    
    this.isProcessing = true;
    const { request, handler } = this.retryQueue.shift()!;
    
    handler.handle(request).subscribe({
      next: () => {
        this.processRetryQueue();
      },
      error: (error) => {
        console.error('Retry failed:', error);
        this.processRetryQueue();
      }
    });
  }
  
  clearQueue(): void {
    this.retryQueue = [];
    this.isProcessing = false;
  }
}

// 5. Token Refresh with Exponential Backoff
@Injectable()
export class ResilientTokenRefreshInterceptor implements HttpInterceptor {
  private refreshAttempts = 0;
  private maxRefreshAttempts = 3;
  
  constructor(
    private authService: EnhancedAuthService,
    private router: Router
  ) {}
  
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (req.url.includes('/auth/refresh')) {
      return next.handle(req);
    }
    
    const token = this.authService.getAccessToken();
    if (token) {
      req = this.addToken(req, token);
    }
    
    return next.handle(req).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === 401 && this.refreshAttempts < this.maxRefreshAttempts) {
          return this.handle401WithRetry(req, next);
        }
        
        if (error.status === 401) {
          this.authService.logout();
          this.router.navigate(['/login']);
        }
        
        return throwError(() => error);
      })
    );
  }
  
  private handle401WithRetry(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    this.refreshAttempts++;
    
    return this.authService.refreshToken().pipe(
      switchMap((response: any) => {
        this.refreshAttempts = 0; // Reset on success
        return next.handle(this.addToken(req, response.accessToken));
      }),
      catchError((error) => {
        // Exponential backoff
        const delay = Math.pow(2, this.refreshAttempts) * 1000;
        
        return timer(delay).pipe(
          switchMap(() => this.handle401WithRetry(req, next))
        );
      })
    );
  }
  
  private addToken(req: HttpRequest<any>, token: string): HttpRequest<any> {
    return req.clone({
      setHeaders: { Authorization: `Bearer ${token}` }
    });
  }
}

// 6. Token Refresh with WebSocket Integration
@Injectable()
export class WebSocketTokenRefreshService {
  private ws: WebSocket | null = null;
  
  constructor(private authService: EnhancedAuthService) {
    this.setupWebSocket();
  }
  
  private setupWebSocket(): void {
    this.ws = new WebSocket('wss://api.example.com/ws/auth');
    
    this.ws.onmessage = (event) => {
      const data = JSON.parse(event.data);
      
      if (data.type === 'token_expiring') {
        this.authService.refreshToken().subscribe();
      }
    };
    
    this.ws.onclose = () => {
      // Reconnect after delay
      setTimeout(() => this.setupWebSocket(), 5000);
    };
  }
  
  sendHeartbeat(): void {
    if (this.ws && this.ws.readyState === WebSocket.OPEN) {
      this.ws.send(JSON.stringify({ type: 'heartbeat' }));
    }
  }
}

// 7. Testing Token Refresh
describe('Token Refresh Interceptor', () => {
  let httpMock: HttpTestingController;
  let http: HttpClient;
  let authService: jasmine.SpyObj<AuthService>;
  
  beforeEach(() => {
    const authSpy = jasmine.createSpyObj('AuthService', ['getAccessToken', 'refreshToken', 'logout']);
    
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [
        {
          provide: HTTP_INTERCEPTORS,
          useClass: TokenRefreshInterceptor,
          multi: true
        },
        { provide: AuthService, useValue: authSpy }
      ]
    });
    
    httpMock = TestBed.inject(HttpTestingController);
    http = TestBed.inject(HttpClient);
    authService = TestBed.inject(AuthService) as jasmine.SpyObj<AuthService>;
  });
  
  it('should refresh token on 401 and retry request', () => {
    authService.getAccessToken.and.returnValue('old-token');
    authService.refreshToken.and.returnValue(of({ accessToken: 'new-token' }));
    
    http.get('/api/protected').subscribe();
    
    // First request with old token
    const req1 = httpMock.expectOne('/api/protected');
    expect(req1.request.headers.get('Authorization')).toBe('Bearer old-token');
    req1.flush('Unauthorized', { status: 401, statusText: 'Unauthorized' });
    
    // Refresh token request
    const refreshReq = httpMock.expectOne('/api/auth/refresh');
    refreshReq.flush({ accessToken: 'new-token' });
    
    // Retry with new token
    const req2 = httpMock.expectOne('/api/protected');
    expect(req2.request.headers.get('Authorization')).toBe('Bearer new-token');
    req2.flush({ data: 'success' });
    
    httpMock.verify();
  });
});
```

**🐞 Fixes:** Prevent infinite refresh loops, handle concurrent refresh requests properly, implement exponential backoff for failed refreshes, and ensure proper cleanup of queued requests.

### Situational / Real-world Questions

1. How do you handle CORS issues when integrating Angular with a Spring Boot backend? _(Asked in TCS)_

**🧩 Foundation:** CORS (Cross-Origin Resource Sharing) issues occur when Angular applications running on one domain/port try to make HTTP requests to a Spring Boot backend running on a different domain/port, and the browser blocks these requests due to same-origin policy restrictions.

**⚙️ Function:** CORS handling involves configuring both the Spring Boot backend to allow cross-origin requests and the Angular frontend to handle CORS properly, ensuring secure communication between different origins while maintaining security standards.

**🚀 Features:**
- Backend CORS configuration with @CrossOrigin annotation
- Global CORS configuration in Spring Boot
- Angular proxy configuration for development
- Preflight request handling
- Credential inclusion in CORS requests
- Environment-specific CORS settings

**🔁 Flow:**
```typescript
// 1. Spring Boot Backend CORS Configuration

// Method 1: Using @CrossOrigin annotation
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:4200", "https://yourdomain.com"})
public class UserController {
    
    @GetMapping("/users")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<User>> getUsers() {
        // Controller logic
        return ResponseEntity.ok(users);
    }
    
    @PostMapping("/users")
    @CrossOrigin(origins = {"http://localhost:4200", "https://yourdomain.com"})
    public ResponseEntity<User> createUser(@RequestBody User user) {
        // Controller logic
        return ResponseEntity.ok(createdUser);
    }
}

// Method 2: Global CORS Configuration
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:4200", "https://yourdomain.com")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }
}

// Method 3: Using CorsConfigurationSource
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200", "https://yourdomain.com"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", configuration);
        return source;
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/api/**").authenticated()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}

// Method 4: Environment-specific CORS configuration
@Configuration
public class EnvironmentCorsConfig {
    
    @Value("${cors.allowed-origins}")
    private String allowedOrigins;
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(allowedOrigins.split(",")));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", configuration);
        return source;
    }
}

// application.properties
cors.allowed-origins=http://localhost:4200,https://yourdomain.com

// 2. Angular Frontend CORS Handling

// Method 1: Angular Proxy Configuration (Development)
// proxy.conf.json
{
  "/api": {
    "target": "http://localhost:8080",
    "secure": false,
    "changeOrigin": true,
    "logLevel": "debug",
    "pathRewrite": {
      "^/api": "/api"
    }
  }
}

// angular.json - Add proxy configuration
{
  "projects": {
    "your-app": {
      "architect": {
        "serve": {
          "options": {
            "proxyConfig": "src/proxy.conf.json"
          }
        }
      }
    }
  }
}

// Method 2: HTTP Interceptor for CORS
@Injectable()
export class CorsInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // Add CORS headers to requests
    const corsReq = req.clone({
      setHeaders: {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
      }
    });
    
    return next.handle(corsReq);
  }
}

// Method 3: Service with CORS handling
@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private baseUrl = environment.apiUrl;
  
  constructor(private http: HttpClient) {}
  
  get<T>(endpoint: string): Observable<T> {
    const url = `${this.baseUrl}${endpoint}`;
    return this.http.get<T>(url, {
      headers: this.getHeaders(),
      withCredentials: true // Include credentials in CORS requests
    });
  }
  
  post<T>(endpoint: string, data: any): Observable<T> {
    const url = `${this.baseUrl}${endpoint}`;
    return this.http.post<T>(url, data, {
      headers: this.getHeaders(),
      withCredentials: true
    });
  }
  
  private getHeaders(): HttpHeaders {
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept': 'application/json'
    });
  }
}

// Method 4: Environment-specific API configuration
// environment.ts
export const environment = {
  production: false,
  apiUrl: '/api', // Use proxy in development
  corsEnabled: true
};

// environment.prod.ts
export const environment = {
  production: true,
  apiUrl: 'https://api.yourdomain.com/api', // Direct URL in production
  corsEnabled: true
};

// 3. Advanced CORS Handling

// Method 1: Dynamic CORS configuration based on request
@Configuration
public class DynamicCorsConfig {
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        return new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration config = new CorsConfiguration();
                
                // Allow different origins based on request
                String origin = request.getHeader("Origin");
                if (isAllowedOrigin(origin)) {
                    config.addAllowedOrigin(origin);
                }
                
                config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                config.setAllowedHeaders(Arrays.asList("*"));
                config.setAllowCredentials(true);
                
                return config;
            }
        };
    }
    
    private boolean isAllowedOrigin(String origin) {
        List<String> allowedOrigins = Arrays.asList(
            "http://localhost:4200",
            "https://yourdomain.com",
            "https://staging.yourdomain.com"
        );
        return allowedOrigins.contains(origin);
    }
}

// Method 2: CORS with Authentication
@Configuration
public class CorsWithAuthConfig {
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList(
            "Authorization",
            "Content-Type",
            "X-Requested-With",
            "Accept",
            "Origin",
            "Access-Control-Request-Method",
            "Access-Control-Request-Headers"
        ));
        configuration.setExposedHeaders(Arrays.asList("Authorization"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(3600L);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", configuration);
        return source;
    }
}

// Method 3: Angular service with error handling for CORS
@Injectable({
  providedIn: 'root'
})
export class RobustApiService {
  constructor(private http: HttpClient) {}
  
  get<T>(endpoint: string): Observable<T> {
    return this.http.get<T>(`${environment.apiUrl}${endpoint}`, {
      headers: this.getHeaders(),
      withCredentials: true
    }).pipe(
      catchError(this.handleCorsError.bind(this))
    );
  }
  
  private handleCorsError(error: HttpErrorResponse): Observable<never> {
    if (error.status === 0) {
      // CORS error or network error
      console.error('CORS or network error:', error);
      return throwError(() => new Error('Unable to connect to server. Please check your connection.'));
    }
    
    if (error.status === 403) {
      // CORS preflight failed
      console.error('CORS preflight failed:', error);
      return throwError(() => new Error('Access denied. Please contact administrator.'));
    }
    
    return throwError(() => error);
  }
  
  private getHeaders(): HttpHeaders {
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept': 'application/json'
    });
  }
}

// 4. Testing CORS Configuration

// Spring Boot Test
@SpringBootTest
@AutoConfigureTestDatabase
class CorsConfigTest {
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    void testCorsHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setOrigin("http://localhost:4200");
        
        HttpEntity<String> entity = new HttpEntity<>(headers);
        
        ResponseEntity<String> response = restTemplate.exchange(
            "/api/users",
            HttpMethod.OPTIONS,
            entity,
            String.class
        );
        
        assertThat(response.getHeaders().getAccessControlAllowOrigin())
            .isEqualTo("http://localhost:4200");
        assertThat(response.getHeaders().getAccessControlAllowMethods())
            .contains(HttpMethod.GET);
    }
}

// Angular Test
describe('CORS Handling', () => {
  let service: ApiService;
  let httpMock: HttpTestingController;
  
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [ApiService]
    });
    
    service = TestBed.inject(ApiService);
    httpMock = TestBed.inject(HttpTestingController);
  });
  
  it('should handle CORS requests properly', () => {
    service.get('/users').subscribe();
    
    const req = httpMock.expectOne('/api/users');
    expect(req.request.headers.get('Content-Type')).toBe('application/json');
    expect(req.request.withCredentials).toBe(true);
    
    req.flush([]);
  });
  
  it('should handle CORS errors gracefully', () => {
    service.get('/users').subscribe({
      next: () => fail('Should have failed'),
      error: (error) => {
        expect(error.message).toContain('Unable to connect');
      }
    });
    
    const req = httpMock.expectOne('/api/users');
    req.error(new ErrorEvent('Network error'));
  });
});
```

**🐞 Fixes:** Always configure CORS on both frontend and backend, use proxy for development, handle CORS errors gracefully, ensure proper security headers, and test CORS configuration thoroughly.

---

## 11. Module Architecture (Feature Modules, Lazy Loading)

### Basic Questions

1. What is a module in Angular and what is the purpose of `@NgModule`? _(Asked in TCS, Capgemini)_

**🧩 Foundation:** A module in Angular is a mechanism to group related components, directives, pipes, and services together, providing a way to organize and structure Angular applications. The `@NgModule` decorator is used to define a module and configure its metadata.

**⚙️ Function:** Modules serve as containers that bundle related functionality, manage dependencies, and provide a way to organize code into logical units, enabling better maintainability, reusability, and separation of concerns in Angular applications.

**🚀 Features:**
- Declarations array for components, directives, and pipes
- Imports array for other modules
- Exports array for making declarations available to other modules
- Providers array for services and dependencies
- Bootstrap array for root module entry point
- Support for feature modules and lazy loading

**🔁 Flow:**
```typescript
// 1. Basic Module Structure
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    UserListComponent,
    UserDetailComponent,
    UserCardDirective,
    UserNamePipe
  ],
  imports: [
    CommonModule,
    FormsModule,
    RouterModule.forChild([
      { path: 'users', component: UserListComponent },
      { path: 'users/:id', component: UserDetailComponent }
    ])
  ],
  exports: [
    UserListComponent,
    UserDetailComponent
  ],
  providers: [
    UserService,
    { provide: 'API_URL', useValue: 'https://api.example.com' }
  ]
})
export class UserModule { }

// 2. Root Module (AppModule)
@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    NavigationComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot([
      { path: '', redirectTo: '/home', pathMatch: 'full' },
      { path: 'home', component: HomeComponent },
      { path: 'users', loadChildren: () => import('./user/user.module').then(m => m.UserModule) }
    ])
  ],
  providers: [
    { provide: APP_INITIALIZER, useFactory: initializeApp, deps: [ConfigService], multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

// 3. Feature Module with Services
@NgModule({
  declarations: [
    ProductListComponent,
    ProductDetailComponent,
    ProductFormComponent,
    ProductFilterPipe
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    SharedModule
  ],
  providers: [
    ProductService,
    ProductResolver,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: ProductApiInterceptor,
      multi: true
    }
  ]
})
export class ProductModule { }

// 4. Shared Module for Common Functionality
@NgModule({
  declarations: [
    LoadingSpinnerComponent,
    ErrorMessageComponent,
    ConfirmDialogComponent,
    HighlightDirective,
    FormatDatePipe
  ],
  imports: [
    CommonModule,
    FormsModule
  ],
  exports: [
    CommonModule,
    FormsModule,
    LoadingSpinnerComponent,
    ErrorMessageComponent,
    ConfirmDialogComponent,
    HighlightDirective,
    FormatDatePipe
  ]
})
export class SharedModule { }

// 5. Module with Custom Providers
@NgModule({
  declarations: [AdminDashboardComponent],
  imports: [CommonModule, AdminRoutingModule],
  providers: [
    AdminService,
    {
      provide: 'ADMIN_CONFIG',
      useFactory: () => ({
        apiUrl: environment.adminApiUrl,
        permissions: ['read', 'write', 'delete']
      })
    },
    {
      provide: AdminGuard,
      useClass: AdminGuard,
      deps: [AuthService, Router]
    }
  ]
})
export class AdminModule { }

// 6. Module with Entry Components (Angular < 13)
@NgModule({
  declarations: [
    ModalComponent,
    TooltipComponent
  ],
  entryComponents: [
    ModalComponent,
    TooltipComponent
  ],
  providers: [
    ModalService,
    TooltipService
  ]
})
export class DynamicComponentsModule { }

// 7. Module Configuration with Factory
export function createModuleConfig(): ModuleConfig {
  return {
    apiUrl: environment.apiUrl,
    enableLogging: !environment.production,
    cacheTimeout: 300000
  };
}

@NgModule({
  declarations: [DataModuleComponent],
  providers: [
    DataService,
    {
      provide: 'MODULE_CONFIG',
      useFactory: createModuleConfig
    }
  ]
})
export class DataModule { }

// 8. Module with Custom Validators and Directives
@NgModule({
  declarations: [
    CustomEmailValidator,
    CustomPhoneValidator,
    FormFieldDirective,
    AutoCompleteDirective
  ],
  exports: [
    CustomEmailValidator,
    CustomPhoneValidator,
    FormFieldDirective,
    AutoCompleteDirective
  ]
})
export class FormValidationModule { }
```

**🐞 Fixes:** Avoid circular dependencies between modules, use SharedModule for common functionality, implement proper lazy loading, and ensure services are provided at the correct level.

---

2. What is the difference between root and feature modules? _(Asked in Infosys)_

**🧩 Foundation:** Root modules (typically AppModule) are the main entry point of an Angular application and are loaded eagerly, while feature modules are specialized modules that group related functionality and can be loaded eagerly or lazily based on application needs.

**⚙️ Function:** Root modules bootstrap the application and provide core functionality, while feature modules enable code organization, lazy loading for performance optimization, and separation of concerns by grouping related features together.

**🚀 Features:**
- **Root Module:** Application entry point, bootstrap array, core providers
- **Feature Modules:** Specialized functionality, optional lazy loading, feature-specific services
- **Root Module:** Loaded eagerly on application startup
- **Feature Modules:** Can be loaded eagerly or lazily
- **Root Module:** Contains app-wide providers and configurations
- **Feature Modules:** Contain feature-specific components and services

**🔁 Flow:**
```typescript
// 1. Root Module (AppModule) - Application Entry Point
@NgModule({
  declarations: [
    AppComponent,           // Root component
    HeaderComponent,        // Global header
    FooterComponent,        // Global footer
    NavigationComponent     // Global navigation
  ],
  imports: [
    BrowserModule,          // Required for browser apps
    HttpClientModule,       // Global HTTP client
    RouterModule.forRoot([  // Root routing configuration
      { path: '', redirectTo: '/home', pathMatch: 'full' },
      { path: 'home', component: HomeComponent },
      { 
        path: 'users', 
        loadChildren: () => import('./user/user.module').then(m => m.UserModule) 
      },
      { 
        path: 'products', 
        loadChildren: () => import('./product/product.module').then(m => m.ProductModule) 
      }
    ]),
    SharedModule            // Global shared functionality
  ],
  providers: [
    // Global services
    AuthService,
    ConfigService,
    ErrorHandler,
    {
      provide: APP_INITIALIZER,
      useFactory: initializeApp,
      deps: [ConfigService],
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent] // Application bootstrap
})
export class AppModule { }

// 2. Feature Module (UserModule) - Specialized Functionality
@NgModule({
  declarations: [
    UserListComponent,      // Feature-specific components
    UserDetailComponent,
    UserFormComponent,
    UserCardComponent,
    UserFilterPipe,         // Feature-specific pipes
    UserHighlightDirective  // Feature-specific directives
  ],
  imports: [
    CommonModule,           // Common Angular directives
    ReactiveFormsModule,    // Forms functionality
    SharedModule,           // Shared components
    UserRoutingModule       // Feature-specific routing
  ],
  providers: [
    UserService,            // Feature-specific services
    UserResolver,
    UserGuard,
    {
      provide: 'USER_API_CONFIG',
      useValue: {
        baseUrl: '/api/users',
        timeout: 30000
      }
    }
  ]
})
export class UserModule { }

// 3. Feature Module with Lazy Loading
const routes: Routes = [
  {
    path: '',
    component: UserListComponent,
    children: [
      { path: ':id', component: UserDetailComponent },
      { path: 'new', component: UserFormComponent }
    ]
  }
];

@NgModule({
  declarations: [
    UserListComponent,
    UserDetailComponent,
    UserFormComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule.forChild(routes)  // Child routes for feature module
  ],
  providers: [UserService]
})
export class UserModule { }

// 4. Eagerly Loaded Feature Module
@NgModule({
  declarations: [CoreFeaturesComponent],
  imports: [CommonModule],
  providers: [CoreService]
})
export class CoreFeaturesModule { }

// Import in AppModule for eager loading
@NgModule({
  imports: [
    BrowserModule,
    CoreFeaturesModule,  // Eagerly loaded
    RouterModule.forRoot([
      { path: 'users', loadChildren: () => import('./user/user.module').then(m => m.UserModule) }
    ])
  ]
})
export class AppModule { }

// 5. Feature Module with Shared Services
@Injectable({
  providedIn: 'root'  // Singleton service across modules
})
export class SharedDataService {
  private data = new BehaviorSubject<any>(null);
  data$ = this.data.asObservable();
  
  updateData(newData: any) {
    this.data.next(newData);
  }
}

@NgModule({
  declarations: [DataModuleComponent],
  providers: [
    SharedDataService,  // Available to this module and imported modules
    {
      provide: 'MODULE_SPECIFIC_SERVICE',
      useClass: ModuleSpecificService
    }
  ]
})
export class DataModule { }

// 6. Feature Module with Custom Configuration
export interface FeatureConfig {
  apiUrl: string;
  enableCache: boolean;
  cacheTimeout: number;
}

@NgModule({
  declarations: [FeatureComponent],
  providers: [
    {
      provide: 'FEATURE_CONFIG',
      useFactory: () => ({
        apiUrl: environment.featureApiUrl,
        enableCache: true,
        cacheTimeout: 60000
      })
    },
    FeatureService
  ]
})
export class FeatureModule { }

// 7. Module Communication Example
// Root Module Service
@Injectable({
  providedIn: 'root'
})
export class AppStateService {
  private userState = new BehaviorSubject<User | null>(null);
  user$ = this.userState.asObservable();
  
  updateUser(user: User) {
    this.userState.next(user);
  }
}

// Feature Module Component
@Component({
  selector: 'app-user-profile',
  template: `
    <div *ngIf="user$ | async as user">
      <h2>{{ user.name }}</h2>
      <p>{{ user.email }}</p>
    </div>
  `
})
export class UserProfileComponent {
  user$ = this.appState.user$;
  
  constructor(private appState: AppStateService) {}
}

// 8. Module Testing
describe('UserModule', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [UserModule]
    });
  });
  
  it('should create user components', () => {
    const fixture = TestBed.createComponent(UserListComponent);
    expect(fixture.componentInstance).toBeTruthy();
  });
  
  it('should provide UserService', () => {
    const service = TestBed.inject(UserService);
    expect(service).toBeTruthy();
  });
});

// 9. Module with Guards and Resolvers
@NgModule({
  declarations: [AdminComponent],
  imports: [CommonModule, AdminRoutingModule],
  providers: [
    AdminGuard,      // Route guard
    AdminResolver,   // Route resolver
    AdminService
  ]
})
export class AdminModule { }

// 10. Module with Custom Elements (Angular Elements)
@NgModule({
  declarations: [CustomElementComponent],
  imports: [CommonModule],
  exports: [CustomElementComponent]
})
export class CustomElementsModule {
  constructor(injector: Injector) {
    const customElement = createCustomElement(CustomElementComponent, { injector });
    customElements.define('app-custom-element', customElement);
  }
}
```

**🐞 Fixes:** Avoid importing feature modules in root module unless needed eagerly, use proper lazy loading syntax, ensure services are provided at the correct level, and avoid circular dependencies between modules.

### Intermediate Questions

1. What is lazy loading in Angular and how does it improve performance? _(Asked in Wipro)_

**🧩 Foundation:** Lazy loading is a technique in Angular where feature modules are loaded on-demand when the user navigates to a specific route, rather than loading all modules at application startup, reducing the initial bundle size and improving application performance.

**⚙️ Function:** Lazy loading optimizes application performance by splitting the application into smaller chunks, loading only the necessary code when required, reducing initial load time, and improving user experience especially on slower networks or devices.

**🚀 Features:**
- On-demand module loading based on route navigation
- Reduced initial bundle size
- Faster application startup
- Better caching strategies
- Improved user experience
- Support for preloading strategies

**🔁 Flow:**
```typescript
// 1. Basic Lazy Loading Setup
// app-routing.module.ts
const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { 
    path: 'users', 
    loadChildren: () => import('./user/user.module').then(m => m.UserModule) 
  },
  { 
    path: 'products', 
    loadChildren: () => import('./product/product.module').then(m => m.ProductModule) 
  },
  { 
    path: 'admin', 
    loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule) 
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

// 2. Feature Module with Lazy Loading
// user/user-routing.module.ts
const userRoutes: Routes = [
  {
    path: '',
    component: UserListComponent,
    children: [
      { path: ':id', component: UserDetailComponent },
      { path: 'new', component: UserFormComponent },
      { path: 'edit/:id', component: UserEditComponent }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(userRoutes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }

// user/user.module.ts
@NgModule({
  declarations: [
    UserListComponent,
    UserDetailComponent,
    UserFormComponent,
    UserEditComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    UserRoutingModule
  ],
  providers: [UserService]
})
export class UserModule { }

// 3. Lazy Loading with Guards
const adminRoutes: Routes = [
  {
    path: '',
    component: AdminDashboardComponent,
    canActivate: [AdminGuard],
    children: [
      { path: 'users', component: AdminUserManagementComponent },
      { path: 'reports', component: AdminReportsComponent },
      { path: 'settings', component: AdminSettingsComponent }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(adminRoutes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }

// 4. Lazy Loading with Resolvers
const productRoutes: Routes = [
  {
    path: '',
    component: ProductListComponent,
    resolve: {
      categories: CategoryResolver
    },
    children: [
      { 
        path: ':id', 
        component: ProductDetailComponent,
        resolve: {
          product: ProductResolver
        }
      }
    ]
  }
];

// 5. Preloading Strategies
// app-routing.module.ts with PreloadAllModules
@NgModule({
  imports: [
    RouterModule.forRoot(routes, {
      preloadingStrategy: PreloadAllModules
    })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }

// Custom Preloading Strategy
@Injectable({
  providedIn: 'root'
})
export class CustomPreloadingStrategy implements PreloadingStrategy {
  preload(route: Route, load: () => Observable<any>): Observable<any> {
    if (route.data && route.data['preload']) {
      return load();
    }
    return of(null);
  }
}

// Using custom preloading strategy
const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { 
    path: 'users', 
    loadChildren: () => import('./user/user.module').then(m => m.UserModule),
    data: { preload: true }  // This module will be preloaded
  },
  { 
    path: 'admin', 
    loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule),
    data: { preload: false } // This module will not be preloaded
  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, {
      preloadingStrategy: CustomPreloadingStrategy
    })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }

// 6. Lazy Loading with Loading Indicators
@Component({
  selector: 'app-root',
  template: `
    <nav>
      <a routerLink="/home">Home</a>
      <a routerLink="/users">Users</a>
      <a routerLink="/products">Products</a>
    </nav>
    
    <div *ngIf="loading$ | async" class="loading">
      Loading...
    </div>
    
    <router-outlet></router-outlet>
  `
})
export class AppComponent {
  loading$ = this.router.events.pipe(
    filter(event => event instanceof NavigationStart || event instanceof NavigationEnd),
    map(event => event instanceof NavigationStart)
  );
  
  constructor(private router: Router) {}
}

// 7. Lazy Loading with Error Handling
const routes: Routes = [
  { 
    path: 'users', 
    loadChildren: () => import('./user/user.module').then(m => m.UserModule)
      .catch(err => {
        console.error('Error loading user module:', err);
        return import('./error/error.module').then(m => m.ErrorModule);
      })
  }
];

// 8. Lazy Loading with Bundle Analysis
// webpack.config.js (if using custom webpack)
module.exports = {
  optimization: {
    splitChunks: {
      chunks: 'all',
      cacheGroups: {
        vendor: {
          test: /[\\/]node_modules[\\/]/,
          name: 'vendors',
          chunks: 'all'
        },
        user: {
          test: /[\\/]src[\\/]app[\\/]user[\\/]/,
          name: 'user',
          chunks: 'all'
        },
        product: {
          test: /[\\/]src[\\/]app[\\/]product[\\/]/,
          name: 'product',
          chunks: 'all'
        }
      }
    }
  }
};

// 9. Lazy Loading with Service Workers
// ngsw-config.json
{
  "assetGroups": [
    {
      "name": "app",
      "installMode": "prefetch",
      "resources": {
        "files": [
          "/favicon.ico",
          "/index.html",
          "/manifest.webmanifest",
          "/*.css",
          "/*.js"
        ]
      }
    },
    {
      "name": "assets",
      "installMode": "lazy",
      "updateMode": "prefetch",
      "resources": {
        "files": [
          "/assets/**",
          "/*.(svg|cur|jpg|jpeg|png|apng|webp|avif|gif|otf|ttf|woff|woff2)"
        ]
      }
    }
  ],
  "dataGroups": [
    {
      "name": "api-freshness",
      "urls": [
        "/api/**"
      ],
      "cacheConfig": {
        "strategy": "freshness",
        "maxSize": 100,
        "maxAge": "3d"
      }
    }
  ]
}

// 10. Lazy Loading Performance Monitoring
@Injectable({
  providedIn: 'root'
})
export class LazyLoadingService {
  private loadTimes = new Map<string, number>();
  
  trackModuleLoad(moduleName: string): void {
    const startTime = performance.now();
    
    return () => {
      const endTime = performance.now();
      const loadTime = endTime - startTime;
      this.loadTimes.set(moduleName, loadTime);
      
      console.log(`Module ${moduleName} loaded in ${loadTime}ms`);
      
      // Send to analytics
      this.sendAnalytics(moduleName, loadTime);
    };
  }
  
  private sendAnalytics(moduleName: string, loadTime: number): void {
    // Send to analytics service
    analytics.track('module_loaded', {
      module: moduleName,
      loadTime: loadTime
    });
  }
  
  getLoadTime(moduleName: string): number | undefined {
    return this.loadTimes.get(moduleName);
  }
}

// 11. Lazy Loading with Dynamic Imports
@Component({
  selector: 'app-dynamic-loader',
  template: `
    <button (click)="loadModule()">Load Module</button>
    <div *ngIf="moduleLoaded">
      <ng-container *ngComponentOutlet="dynamicComponent"></ng-container>
    </div>
  `
})
export class DynamicLoaderComponent {
  moduleLoaded = false;
  dynamicComponent: any;
  
  async loadModule(): Promise<void> {
    try {
      const module = await import('./dynamic/dynamic.module');
      this.dynamicComponent = module.DynamicComponent;
      this.moduleLoaded = true;
    } catch (error) {
      console.error('Error loading dynamic module:', error);
    }
  }
}

// 12. Testing Lazy Loading
describe('Lazy Loading', () => {
  let router: Router;
  let location: Location;
  
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes([
        { 
          path: 'users', 
          loadChildren: () => import('./user/user.module').then(m => m.UserModule) 
        }
      ])]
    });
    
    router = TestBed.inject(Router);
    location = TestBed.inject(Location);
  });
  
  it('should load user module on navigation', fakeAsync(() => {
    router.navigate(['/users']);
    tick();
    
    expect(location.path()).toBe('/users');
  }));
});
```

**🐞 Fixes:** Ensure proper route configuration, handle loading errors gracefully, implement appropriate preloading strategies, and monitor bundle sizes to prevent lazy loading overhead.

### Advanced Questions

1. How do you optimize module loading in a large-scale Angular application? _(Asked in Cognizant)_

**🧩 Foundation:** Module loading optimization in large-scale Angular applications involves strategic code splitting, intelligent preloading, bundle analysis, and performance monitoring to ensure optimal loading times and user experience across different network conditions and devices.

**⚙️ Function:** Module loading optimization reduces initial bundle size, improves application startup time, enhances user experience, and enables better resource utilization by implementing advanced loading strategies and monitoring performance metrics.

**🚀 Features:**
- Strategic code splitting and bundle optimization
- Intelligent preloading strategies
- Bundle analysis and monitoring
- Performance metrics tracking
- Network-aware loading
- Progressive loading techniques

**🔁 Flow:**
```typescript
// 1. Advanced Bundle Splitting Strategy
// webpack.config.js (Custom Webpack Configuration)
const path = require('path');

module.exports = {
  optimization: {
    splitChunks: {
      chunks: 'all',
      maxInitialRequests: 25,
      minSize: 20000,
      cacheGroups: {
        // Vendor chunks
        vendor: {
          test: /[\\/]node_modules[\\/]/,
          name(module) {
            const packageName = module.context.match(/[\\/]node_modules[\\/](.*?)([\\/]|$)/)[1];
            return `vendor.${packageName.replace('@', '')}`;
          },
          chunks: 'all',
          priority: 20
        },
        // Common chunks
        common: {
          name: 'common',
          minChunks: 2,
          chunks: 'all',
          priority: 10,
          reuseExistingChunk: true
        },
        // Feature-specific chunks
        user: {
          test: /[\\/]src[\\/]app[\\/]user[\\/]/,
          name: 'user',
          chunks: 'all',
          priority: 30
        },
        product: {
          test: /[\\/]src[\\/]app[\\/]product[\\/]/,
          name: 'product',
          chunks: 'all',
          priority: 30
        },
        admin: {
          test: /[\\/]src[\\/]app[\\/]admin[\\/]/,
          name: 'admin',
          chunks: 'all',
          priority: 30
        }
      }
    }
  }
};

// 2. Intelligent Preloading Strategy
@Injectable({
  providedIn: 'root'
})
export class SmartPreloadingStrategy implements PreloadingStrategy {
  private preloadedModules = new Set<string>();
  
  preload(route: Route, load: () => Observable<any>): Observable<any> {
    if (this.shouldPreload(route)) {
      return load().pipe(
        tap(() => this.preloadedModules.add(route.path || '')),
        catchError(error => {
          console.error(`Failed to preload module: ${route.path}`, error);
          return of(null);
        })
      );
    }
    return of(null);
  }
  
  private shouldPreload(route: Route): boolean {
    // Preload based on user behavior
    if (route.data?.['preload'] === false) return false;
    
    // Preload based on network conditions
    if (!navigator.onLine) return false;
    
    // Preload based on user preferences
    if (this.isLowBandwidth()) return false;
    
    // Preload based on time of day (peak vs off-peak)
    if (this.isPeakHours()) return false;
    
    // Preload based on device capabilities
    if (this.isLowEndDevice()) return false;
    
    return true;
  }
  
  private isLowBandwidth(): boolean {
    // Check connection type
    const connection = (navigator as any).connection;
    if (connection) {
      return connection.effectiveType === 'slow-2g' || 
             connection.effectiveType === '2g' ||
             connection.saveData;
    }
    return false;
  }
  
  private isPeakHours(): boolean {
    const hour = new Date().getHours();
    return hour >= 9 && hour <= 17; // Business hours
  }
  
  private isLowEndDevice(): boolean {
    // Check device memory
    if ((navigator as any).deviceMemory) {
      return (navigator as any).deviceMemory < 4; // Less than 4GB
    }
    return false;
  }
}

// 3. Module Loading Performance Monitoring
@Injectable({
  providedIn: 'root'
})
export class ModulePerformanceService {
  private moduleMetrics = new Map<string, ModuleMetric>();
  
  trackModuleLoad(moduleName: string): () => void {
    const startTime = performance.now();
    const startMemory = (performance as any).memory?.usedJSHeapSize || 0;
    
    return () => {
      const endTime = performance.now();
      const endMemory = (performance as any).memory?.usedJSHeapSize || 0;
      
      const metric: ModuleMetric = {
        loadTime: endTime - startTime,
        memoryIncrease: endMemory - startMemory,
        timestamp: Date.now(),
        networkType: this.getNetworkType(),
        deviceType: this.getDeviceType()
      };
      
      this.moduleMetrics.set(moduleName, metric);
      this.sendMetrics(moduleName, metric);
    };
  }
  
  private getNetworkType(): string {
    const connection = (navigator as any).connection;
    return connection ? connection.effectiveType : 'unknown';
  }
  
  private getDeviceType(): string {
    const userAgent = navigator.userAgent;
    if (/Mobile|Android|iPhone|iPad/.test(userAgent)) {
      return 'mobile';
    }
    return 'desktop';
  }
  
  private sendMetrics(moduleName: string, metric: ModuleMetric): void {
    // Send to analytics service
    analytics.track('module_performance', {
      module: moduleName,
      ...metric
    });
  }
  
  getModuleMetrics(moduleName: string): ModuleMetric | undefined {
    return this.moduleMetrics.get(moduleName);
  }
  
  getAverageLoadTime(): number {
    const metrics = Array.from(this.moduleMetrics.values());
    if (metrics.length === 0) return 0;
    
    const totalTime = metrics.reduce((sum, metric) => sum + metric.loadTime, 0);
    return totalTime / metrics.length;
  }
}

// 4. Progressive Module Loading
@Injectable({
  providedIn: 'root'
})
export class ProgressiveLoadingService {
  private loadingQueue: Array<{ module: string, priority: number }> = [];
  private isLoading = false;
  
  loadModule(moduleName: string, priority: number = 1): Observable<any> {
    return new Observable(observer => {
      this.loadingQueue.push({ module: moduleName, priority });
      this.loadingQueue.sort((a, b) => b.priority - a.priority);
      
      if (!this.isLoading) {
        this.processQueue(observer);
      }
    });
  }
  
  private async processQueue(observer: any): Promise<void> {
    this.isLoading = true;
    
    while (this.loadingQueue.length > 0) {
      const { module } = this.loadingQueue.shift()!;
      
      try {
        const moduleInstance = await this.loadModuleAsync(module);
        observer.next(moduleInstance);
      } catch (error) {
        observer.error(error);
      }
      
      // Add delay between loads to prevent overwhelming the network
      await this.delay(100);
    }
    
    this.isLoading = false;
    observer.complete();
  }
  
  private loadModuleAsync(moduleName: string): Promise<any> {
    switch (moduleName) {
      case 'user':
        return import('./user/user.module').then(m => m.UserModule);
      case 'product':
        return import('./product/product.module').then(m => m.ProductModule);
      case 'admin':
        return import('./admin/admin.module').then(m => m.AdminModule);
      default:
        throw new Error(`Unknown module: ${moduleName}`);
    }
  }
  
  private delay(ms: number): Promise<void> {
    return new Promise(resolve => setTimeout(resolve, ms));
  }
}

// 5. Network-Aware Module Loading
@Injectable({
  providedIn: 'root'
})
export class NetworkAwareLoadingService {
  private connectionInfo = {
    type: 'unknown',
    effectiveType: 'unknown',
    downlink: 0,
    rtt: 0
  };
  
  constructor() {
    this.initializeConnectionMonitoring();
  }
  
  private initializeConnectionMonitoring(): void {
    const connection = (navigator as any).connection;
    if (connection) {
      this.updateConnectionInfo(connection);
      
      connection.addEventListener('change', () => {
        this.updateConnectionInfo(connection);
      });
    }
  }
  
  private updateConnectionInfo(connection: any): void {
    this.connectionInfo = {
      type: connection.type || 'unknown',
      effectiveType: connection.effectiveType || 'unknown',
      downlink: connection.downlink || 0,
      rtt: connection.rtt || 0
    };
  }
  
  shouldLoadModule(moduleName: string): boolean {
    // Don't load heavy modules on slow connections
    if (this.connectionInfo.effectiveType === 'slow-2g' || 
        this.connectionInfo.effectiveType === '2g') {
      return this.isEssentialModule(moduleName);
    }
    
    // Load with delay on 3g connections
    if (this.connectionInfo.effectiveType === '3g') {
      return true; // But with delay
    }
    
    return true;
  }
  
  private isEssentialModule(moduleName: string): boolean {
    const essentialModules = ['core', 'auth', 'home'];
    return essentialModules.includes(moduleName);
  }
  
  getLoadingDelay(): number {
    switch (this.connectionInfo.effectiveType) {
      case 'slow-2g':
        return 2000;
      case '2g':
        return 1000;
      case '3g':
        return 500;
      default:
        return 0;
    }
  }
}

// 6. Module Loading with Caching Strategy
@Injectable({
  providedIn: 'root'
})
export class ModuleCacheService {
  private moduleCache = new Map<string, any>();
  private cacheExpiry = new Map<string, number>();
  private readonly CACHE_DURATION = 5 * 60 * 1000; // 5 minutes
  
  async loadModule(moduleName: string): Promise<any> {
    // Check cache first
    if (this.isCached(moduleName)) {
      console.log(`Loading ${moduleName} from cache`);
      return this.moduleCache.get(moduleName);
    }
    
    // Load from network
    console.log(`Loading ${moduleName} from network`);
    const module = await this.loadFromNetwork(moduleName);
    
    // Cache the module
    this.cacheModule(moduleName, module);
    
    return module;
  }
  
  private isCached(moduleName: string): boolean {
    const expiry = this.cacheExpiry.get(moduleName);
    return expiry ? Date.now() < expiry : false;
  }
  
  private cacheModule(moduleName: string, module: any): void {
    this.moduleCache.set(moduleName, module);
    this.cacheExpiry.set(moduleName, Date.now() + this.CACHE_DURATION);
  }
  
  private async loadFromNetwork(moduleName: string): Promise<any> {
    switch (moduleName) {
      case 'user':
        return import('./user/user.module').then(m => m.UserModule);
      case 'product':
        return import('./product/product.module').then(m => m.ProductModule);
      default:
        throw new Error(`Unknown module: ${moduleName}`);
    }
  }
  
  clearCache(): void {
    this.moduleCache.clear();
    this.cacheExpiry.clear();
  }
  
  getCacheStats(): { size: number, modules: string[] } {
    return {
      size: this.moduleCache.size,
      modules: Array.from(this.moduleCache.keys())
    };
  }
}

// 7. Bundle Analysis and Optimization
@Component({
  selector: 'app-bundle-analyzer',
  template: `
    <div class="bundle-info">
      <h3>Bundle Analysis</h3>
      <div *ngFor="let bundle of bundleInfo">
        <strong>{{ bundle.name }}</strong>: {{ bundle.size | fileSize }}
        <div class="progress">
          <div class="progress-bar" [style.width.%]="bundle.percentage"></div>
        </div>
      </div>
    </div>
  `
})
export class BundleAnalyzerComponent implements OnInit {
  bundleInfo: BundleInfo[] = [];
  
  ngOnInit(): void {
    this.analyzeBundles();
  }
  
  private analyzeBundles(): void {
    // This would typically come from webpack bundle analyzer
    this.bundleInfo = [
      { name: 'main', size: 1024000, percentage: 40 },
      { name: 'vendor', size: 2048000, percentage: 80 },
      { name: 'user', size: 256000, percentage: 10 },
      { name: 'product', size: 512000, percentage: 20 }
    ];
  }
}

// 8. Module Loading with Error Recovery
@Injectable({
  providedIn: 'root'
})
export class ResilientModuleLoader {
  private retryAttempts = new Map<string, number>();
  private readonly MAX_RETRIES = 3;
  
  async loadModule(moduleName: string): Promise<any> {
    let attempts = 0;
    
    while (attempts < this.MAX_RETRIES) {
      try {
        return await this.attemptLoad(moduleName);
      } catch (error) {
        attempts++;
        this.retryAttempts.set(moduleName, attempts);
        
        if (attempts >= this.MAX_RETRIES) {
          throw new Error(`Failed to load module ${moduleName} after ${attempts} attempts`);
        }
        
        // Exponential backoff
        await this.delay(Math.pow(2, attempts) * 1000);
      }
    }
  }
  
  private async attemptLoad(moduleName: string): Promise<any> {
    switch (moduleName) {
      case 'user':
        return import('./user/user.module').then(m => m.UserModule);
      case 'product':
        return import('./product/product.module').then(m => m.ProductModule);
      default:
        throw new Error(`Unknown module: ${moduleName}`);
    }
  }
  
  private delay(ms: number): Promise<void> {
    return new Promise(resolve => setTimeout(resolve, ms));
  }
  
  getRetryAttempts(moduleName: string): number {
    return this.retryAttempts.get(moduleName) || 0;
  }
}

// 9. Testing Module Loading Optimization
describe('Module Loading Optimization', () => {
  let service: SmartPreloadingStrategy;
  
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SmartPreloadingStrategy]
    });
    service = TestBed.inject(SmartPreloadingStrategy);
  });
  
  it('should preload modules based on network conditions', () => {
    spyOn(navigator, 'onLine').and.returnValue(true);
    
    const route = { path: 'users', data: { preload: true } };
    const load = jasmine.createSpy('load').and.returnValue(of(null));
    
    service.preload(route, load).subscribe();
    
    expect(load).toHaveBeenCalled();
  });
});
```

**🐞 Fixes:** Monitor bundle sizes regularly, implement proper error handling for failed loads, use appropriate caching strategies, and balance preloading with network conditions.

### Tough Questions

1. Your app is slow due to large bundles. How do you analyze and reduce the initial module load? _(Asked in HCL)_

**🧩 Foundation:** Large bundle sizes in Angular applications can significantly impact initial load times and user experience. Bundle analysis involves identifying the largest dependencies, unused code, and opportunities for code splitting to reduce the initial JavaScript payload.

**⚙️ Function:** Bundle analysis and optimization reduces initial load time, improves Time to Interactive (TTI), enhances user experience, and enables better performance on slower networks and devices by identifying and eliminating unnecessary code.

**🚀 Features:**
- Bundle size analysis and visualization
- Tree shaking and dead code elimination
- Dynamic imports and code splitting
- Dependency optimization
- Compression and minification
- Performance monitoring and metrics

**🔁 Flow:**
```typescript
// 1. Bundle Analysis Setup
// angular.json - Bundle Analyzer Configuration
{
  "projects": {
    "your-app": {
      "architect": {
        "build": {
          "configurations": {
            "analyze": {
              "optimization": true,
              "outputHashing": "none",
              "sourceMap": false,
              "namedChunks": false,
              "aot": true,
              "extractLicenses": true,
              "vendorChunk": true,
              "buildOptimizer": true
            }
          }
        }
      }
    }
  }
}

// package.json - Bundle Analysis Scripts
{
  "scripts": {
    "analyze": "ng build --configuration=analyze && webpack-bundle-analyzer dist/your-app/stats.json",
    "build:analyze": "ng build --stats-json && webpack-bundle-analyzer dist/your-app/stats.json",
    "bundle-report": "npm run build:analyze -- --report"
  },
  "devDependencies": {
    "webpack-bundle-analyzer": "^4.5.0",
    "source-map-explorer": "^2.5.3"
  }
}

// 2. Advanced Bundle Analysis Service
@Injectable({
  providedIn: 'root'
})
export class BundleAnalyzerService {
  private bundleMetrics: BundleMetrics[] = [];
  
  analyzeBundleSize(): Observable<BundleAnalysis> {
    return this.http.get<BundleAnalysis>('/api/bundle-analysis').pipe(
      tap(analysis => this.bundleMetrics = analysis.metrics),
      map(analysis => this.identifyOptimizationOpportunities(analysis))
    );
  }
  
  private identifyOptimizationOpportunities(analysis: BundleAnalysis): BundleAnalysis {
    const opportunities: OptimizationOpportunity[] = [];
    
    // Identify large dependencies
    analysis.metrics.forEach(metric => {
      if (metric.size > 100000) { // 100KB threshold
        opportunities.push({
          type: 'large-dependency',
          module: metric.name,
          size: metric.size,
          recommendation: `Consider lazy loading or replacing ${metric.name}`
        });
      }
      
      // Identify duplicate dependencies
      if (metric.duplicates > 0) {
        opportunities.push({
          type: 'duplicate-dependency',
          module: metric.name,
          duplicates: metric.duplicates,
          recommendation: `Remove duplicate instances of ${metric.name}`
        });
      }
    });
    
    return { ...analysis, opportunities };
  }
  
  getBundleSizeTrends(): Observable<BundleSizeTrend[]> {
    return this.http.get<BundleSizeTrend[]>('/api/bundle-trends');
  }
  
  generateOptimizationReport(): BundleOptimizationReport {
    const totalSize = this.bundleMetrics.reduce((sum, metric) => sum + metric.size, 0);
    const largeModules = this.bundleMetrics.filter(m => m.size > 50000);
    
    return {
      totalSize,
      largeModules,
      recommendations: this.generateRecommendations(),
      estimatedSavings: this.calculateEstimatedSavings()
    };
  }
  
  private generateRecommendations(): string[] {
    const recommendations: string[] = [];
    
    if (this.bundleMetrics.some(m => m.name.includes('lodash'))) {
      recommendations.push('Use lodash-es instead of lodash for tree-shaking');
    }
    
    if (this.bundleMetrics.some(m => m.name.includes('moment'))) {
      recommendations.push('Replace moment.js with date-fns or dayjs');
    }
    
    if (this.bundleMetrics.some(m => m.name.includes('rxjs'))) {
      recommendations.push('Import specific RxJS operators instead of entire library');
    }
    
    return recommendations;
  }
  
  private calculateEstimatedSavings(): number {
    // Calculate potential savings based on recommendations
    return this.bundleMetrics
      .filter(m => m.size > 100000)
      .reduce((savings, metric) => savings + (metric.size * 0.3), 0); // 30% potential savings
  }
}

// 3. Dynamic Import Optimization
// Before: Static imports
import { UserService } from './user/user.service';
import { ProductService } from './product/product.service';
import { AdminService } from './admin/admin.service';

// After: Dynamic imports
export class ServiceLoader {
  async loadUserService(): Promise<UserService> {
    const module = await import('./user/user.module');
    return module.UserService;
  }
  
  async loadProductService(): Promise<ProductService> {
    const module = await import('./product/product.module');
    return module.ProductService;
  }
  
  async loadAdminService(): Promise<AdminService> {
    const module = await import('./admin/admin.module');
    return module.AdminService;
  }
}

// 4. Tree Shaking Optimization
// Before: Importing entire library
import * as _ from 'lodash';

// After: Importing specific functions
import { debounce, throttle, isEmpty } from 'lodash-es';

// Before: Importing entire RxJS
import { Observable } from 'rxjs';
import { map, filter, switchMap } from 'rxjs/operators';

// After: Importing specific operators
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators/map';
import { filter } from 'rxjs/operators/filter';
import { switchMap } from 'rxjs/operators/switchMap';

// 5. Component-Level Code Splitting
@Component({
  selector: 'app-dynamic-component',
  template: `
    <div *ngIf="componentLoaded">
      <ng-container *ngComponentOutlet="dynamicComponent"></ng-container>
    </div>
    <div *ngIf="!componentLoaded" class="loading">
      Loading component...
    </div>
  `
})
export class DynamicComponentLoader {
  dynamicComponent: any;
  componentLoaded = false;
  
  async loadComponent(componentName: string): Promise<void> {
    try {
      const component = await this.loadComponentAsync(componentName);
      this.dynamicComponent = component;
      this.componentLoaded = true;
    } catch (error) {
      console.error('Failed to load component:', error);
    }
  }
  
  private async loadComponentAsync(componentName: string): Promise<any> {
    switch (componentName) {
      case 'chart':
        return (await import('./components/chart.component')).ChartComponent;
      case 'table':
        return (await import('./components/table.component')).TableComponent;
      case 'form':
        return (await import('./components/form.component')).FormComponent;
      default:
        throw new Error(`Unknown component: ${componentName}`);
    }
  }
}

// 6. Dependency Optimization
// webpack.config.js - Advanced Optimization
module.exports = {
  optimization: {
    splitChunks: {
      chunks: 'all',
      maxInitialRequests: 25,
      minSize: 20000,
      cacheGroups: {
        // Separate vendor chunks
        vendor: {
          test: /[\\/]node_modules[\\/]/,
          name(module) {
            const packageName = module.context.match(/[\\/]node_modules[\\/](.*?)([\\/]|$)/)[1];
            return `vendor.${packageName.replace('@', '')}`;
          },
          chunks: 'all',
          priority: 20
        },
        // Common chunks for shared code
        common: {
          name: 'common',
          minChunks: 2,
          chunks: 'all',
          priority: 10,
          reuseExistingChunk: true
        },
        // Feature-specific chunks
        user: {
          test: /[\\/]src[\\/]app[\\/]user[\\/]/,
          name: 'user',
          chunks: 'all',
          priority: 30
        }
      }
    },
    // Remove unused code
    usedExports: true,
    sideEffects: false
  },
  // Tree shaking
  mode: 'production',
  // Source maps for analysis
  devtool: 'source-map'
};

// 7. Performance Monitoring
@Injectable({
  providedIn: 'root'
})
export class BundlePerformanceService {
  private performanceMetrics: PerformanceMetric[] = [];
  
  trackBundleLoad(): void {
    const observer = new PerformanceObserver((list) => {
      list.getEntries().forEach((entry) => {
        if (entry.entryType === 'resource') {
          const resourceEntry = entry as PerformanceResourceTiming;
          if (resourceEntry.name.endsWith('.js')) {
            this.recordBundleLoad(resourceEntry);
          }
        }
      });
    });
    
    observer.observe({ entryTypes: ['resource'] });
  }
  
  private recordBundleLoad(entry: PerformanceResourceTiming): void {
    const metric: PerformanceMetric = {
      name: entry.name,
      size: entry.transferSize,
      loadTime: entry.loadEventEnd - entry.loadEventStart,
      timestamp: Date.now()
    };
    
    this.performanceMetrics.push(metric);
    this.sendMetric(metric);
  }
  
  private sendMetric(metric: PerformanceMetric): void {
    // Send to analytics
    analytics.track('bundle_performance', metric);
  }
  
  getBundlePerformanceReport(): BundlePerformanceReport {
    const totalSize = this.performanceMetrics.reduce((sum, m) => sum + m.size, 0);
    const averageLoadTime = this.performanceMetrics.reduce((sum, m) => sum + m.loadTime, 0) / this.performanceMetrics.length;
    
    return {
      totalSize,
      averageLoadTime,
      bundleCount: this.performanceMetrics.length,
      recommendations: this.generatePerformanceRecommendations()
    };
  }
  
  private generatePerformanceRecommendations(): string[] {
    const recommendations: string[] = [];
    const averageLoadTime = this.performanceMetrics.reduce((sum, m) => sum + m.loadTime, 0) / this.performanceMetrics.length;
    
    if (averageLoadTime > 1000) {
      recommendations.push('Consider implementing HTTP/2 server push for critical bundles');
    }
    
    if (this.performanceMetrics.some(m => m.size > 500000)) {
      recommendations.push('Large bundles detected. Consider further code splitting');
    }
    
    return recommendations;
  }
}

// 8. Bundle Size Monitoring
@Component({
  selector: 'app-bundle-monitor',
  template: `
    <div class="bundle-monitor">
      <h3>Bundle Size Monitor</h3>
      <div *ngFor="let metric of bundleMetrics" class="metric">
        <span>{{ metric.name }}</span>
        <span>{{ metric.size | fileSize }}</span>
        <div class="progress">
          <div class="progress-bar" [style.width.%]="getPercentage(metric.size)"></div>
        </div>
      </div>
      <div class="total">
        Total: {{ getTotalSize() | fileSize }}
      </div>
    </div>
  `
})
export class BundleMonitorComponent implements OnInit {
  bundleMetrics: BundleMetric[] = [];
  
  constructor(private bundleAnalyzer: BundleAnalyzerService) {}
  
  ngOnInit(): void {
    this.loadBundleMetrics();
  }
  
  private loadBundleMetrics(): void {
    this.bundleAnalyzer.analyzeBundleSize().subscribe(analysis => {
      this.bundleMetrics = analysis.metrics;
    });
  }
  
  getPercentage(size: number): number {
    const total = this.getTotalSize();
    return total > 0 ? (size / total) * 100 : 0;
  }
  
  getTotalSize(): number {
    return this.bundleMetrics.reduce((sum, metric) => sum + metric.size, 0);
  }
}

// 9. Automated Bundle Optimization
@Injectable({
  providedIn: 'root'
})
export class AutomatedBundleOptimizer {
  private readonly SIZE_THRESHOLD = 100000; // 100KB
  
  async optimizeBundle(): Promise<OptimizationResult> {
    const analysis = await this.analyzeBundle();
    const optimizations = this.generateOptimizations(analysis);
    
    return {
      originalSize: analysis.totalSize,
      optimizedSize: this.calculateOptimizedSize(analysis, optimizations),
      optimizations,
      savings: this.calculateSavings(analysis, optimizations)
    };
  }
  
  private async analyzeBundle(): Promise<BundleAnalysis> {
    // Simulate bundle analysis
    return {
      totalSize: 2048000, // 2MB
      metrics: [
        { name: 'vendor', size: 1024000, duplicates: 0 },
        { name: 'main', size: 512000, duplicates: 0 },
        { name: 'user', size: 256000, duplicates: 0 },
        { name: 'product', size: 256000, duplicates: 0 }
      ]
    };
  }
  
  private generateOptimizations(analysis: BundleAnalysis): Optimization[] {
    const optimizations: Optimization[] = [];
    
    analysis.metrics.forEach(metric => {
      if (metric.size > this.SIZE_THRESHOLD) {
        optimizations.push({
          type: 'code-splitting',
          module: metric.name,
          estimatedSavings: metric.size * 0.3
        });
      }
    });
    
    return optimizations;
  }
  
  private calculateOptimizedSize(analysis: BundleAnalysis, optimizations: Optimization[]): number {
    const savings = optimizations.reduce((sum, opt) => sum + opt.estimatedSavings, 0);
    return analysis.totalSize - savings;
  }
  
  private calculateSavings(analysis: BundleAnalysis, optimizations: Optimization[]): number {
    return optimizations.reduce((sum, opt) => sum + opt.estimatedSavings, 0);
  }
}

// 10. Testing Bundle Optimization
describe('Bundle Optimization', () => {
  let service: BundleAnalyzerService;
  
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [BundleAnalyzerService]
    });
    service = TestBed.inject(BundleAnalyzerService);
  });
  
  it('should identify large dependencies', () => {
    const analysis = service.generateOptimizationReport();
    
    expect(analysis.largeModules.length).toBeGreaterThan(0);
    expect(analysis.recommendations.length).toBeGreaterThan(0);
  });
  
  it('should calculate estimated savings', () => {
    const analysis = service.generateOptimizationReport();
    
    expect(analysis.estimatedSavings).toBeGreaterThan(0);
  });
});
```

**🐞 Fixes:** Regularly monitor bundle sizes, implement automated bundle analysis in CI/CD, use tree shaking effectively, and balance code splitting with network requests.

---

## 12. Testing (Unit Testing & Integration Testing)

### Basic Questions

1. What is unit testing in Angular and which tools are used for it? _(Asked in TCS, Infosys)_

**🧩 Foundation:** Unit testing in Angular is a testing methodology where individual components, services, pipes, and directives are tested in isolation to ensure they work correctly independently. Angular provides a comprehensive testing framework with built-in tools and utilities for writing and executing unit tests.

**⚙️ Function:** Unit testing ensures code reliability, catches bugs early in development, facilitates refactoring, and provides documentation for how components and services should behave, enabling confident code changes and maintenance.

**🚀 Features:**
- Isolated testing of individual units
- Automated test execution
- Mocking and dependency injection
- Test coverage reporting
- Continuous integration support
- Debugging and error reporting

**🔁 Flow:**
```typescript
// 1. Basic Component Unit Test
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { UserComponent } from './user.component';
import { UserService } from './user.service';

describe('UserComponent', () => {
  let component: UserComponent;
  let fixture: ComponentFixture<UserComponent>;
  let userService: jasmine.SpyObj<UserService>;

  beforeEach(async () => {
    const spy = jasmine.createSpyObj('UserService', ['getUsers']);
    
    await TestBed.configureTestingModule({
      declarations: [UserComponent],
      providers: [
        { provide: UserService, useValue: spy }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(UserComponent);
    component = fixture.componentInstance;
    userService = TestBed.inject(UserService) as jasmine.SpyObj<UserService>;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should load users on init', () => {
    const mockUsers = [
      { id: 1, name: 'John Doe', email: 'john@example.com' },
      { id: 2, name: 'Jane Smith', email: 'jane@example.com' }
    ];
    
    userService.getUsers.and.returnValue(of(mockUsers));
    
    component.ngOnInit();
    
    expect(userService.getUsers).toHaveBeenCalled();
    expect(component.users).toEqual(mockUsers);
  });

  it('should display user list', () => {
    component.users = [
      { id: 1, name: 'John Doe', email: 'john@example.com' }
    ];
    
    fixture.detectChanges();
    
    const compiled = fixture.nativeElement;
    expect(compiled.querySelector('.user-name').textContent).toContain('John Doe');
  });
});

// 2. Service Unit Test
import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { UserService } from './user.service';

describe('UserService', () => {
  let service: UserService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [UserService]
    });
    
    service = TestBed.inject(UserService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should fetch users from API', () => {
    const mockUsers = [
      { id: 1, name: 'John Doe' },
      { id: 2, name: 'Jane Smith' }
    ];

    service.getUsers().subscribe(users => {
      expect(users).toEqual(mockUsers);
    });

    const req = httpMock.expectOne('/api/users');
    expect(req.request.method).toBe('GET');
    req.flush(mockUsers);
  });

  it('should handle API errors', () => {
    service.getUsers().subscribe({
      next: () => fail('should have failed'),
      error: (error) => {
        expect(error.status).toBe(500);
      }
    });

    const req = httpMock.expectOne('/api/users');
    req.flush('Server error', { status: 500, statusText: 'Internal Server Error' });
  });
});

// 3. Pipe Unit Test
import { Pipe, PipeTransform } from '@angular/core';
import { TestBed } from '@angular/core/testing';

@Pipe({
  name: 'capitalize'
})
export class CapitalizePipe implements PipeTransform {
  transform(value: string): string {
    if (!value) return value;
    return value.charAt(0).toUpperCase() + value.slice(1).toLowerCase();
  }
}

describe('CapitalizePipe', () => {
  let pipe: CapitalizePipe;

  beforeEach(() => {
    pipe = new CapitalizePipe();
  });

  it('should create an instance', () => {
    expect(pipe).toBeTruthy();
  });

  it('should capitalize first letter', () => {
    expect(pipe.transform('hello')).toBe('Hello');
  });

  it('should handle empty string', () => {
    expect(pipe.transform('')).toBe('');
  });

  it('should handle null value', () => {
    expect(pipe.transform(null)).toBe(null);
  });

  it('should handle already capitalized string', () => {
    expect(pipe.transform('WORLD')).toBe('World');
  });
});

// 4. Directive Unit Test
import { Component } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HighlightDirective } from './highlight.directive';

@Component({
  template: '<div appHighlight [highlightColor]="color">Test content</div>'
})
class TestComponent {
  color = 'yellow';
}

describe('HighlightDirective', () => {
  let component: TestComponent;
  let fixture: ComponentFixture<TestComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TestComponent, HighlightDirective]
    }).compileComponents();

    fixture = TestBed.createComponent(TestComponent);
    component = fixture.componentInstance;
  });

  it('should create an instance', () => {
    expect(component).toBeTruthy();
  });

  it('should apply highlight color', () => {
    fixture.detectChanges();
    
    const element = fixture.nativeElement.querySelector('div');
    expect(element.style.backgroundColor).toBe('yellow');
  });

  it('should change highlight color when input changes', () => {
    fixture.detectChanges();
    
    component.color = 'red';
    fixture.detectChanges();
    
    const element = fixture.nativeElement.querySelector('div');
    expect(element.style.backgroundColor).toBe('red');
  });
});

// 5. Form Component Unit Test
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { UserFormComponent } from './user-form.component';

describe('UserFormComponent', () => {
  let component: UserFormComponent;
  let fixture: ComponentFixture<UserFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UserFormComponent],
      imports: [ReactiveFormsModule]
    }).compileComponents();

    fixture = TestBed.createComponent(UserFormComponent);
    component = fixture.componentInstance;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should initialize form with empty values', () => {
    expect(component.userForm.get('name')?.value).toBe('');
    expect(component.userForm.get('email')?.value).toBe('');
  });

  it('should validate required fields', () => {
    const nameControl = component.userForm.get('name');
    const emailControl = component.userForm.get('email');

    nameControl?.setValue('');
    emailControl?.setValue('');

    expect(nameControl?.valid).toBeFalsy();
    expect(emailControl?.valid).toBeFalsy();
  });

  it('should validate email format', () => {
    const emailControl = component.userForm.get('email');
    
    emailControl?.setValue('invalid-email');
    expect(emailControl?.valid).toBeFalsy();
    
    emailControl?.setValue('valid@email.com');
    expect(emailControl?.valid).toBeTruthy();
  });

  it('should emit form data on submit', () => {
    spyOn(component.formSubmit, 'emit');
    
    component.userForm.patchValue({
      name: 'John Doe',
      email: 'john@example.com'
    });
    
    component.onSubmit();
    
    expect(component.formSubmit.emit).toHaveBeenCalledWith({
      name: 'John Doe',
      email: 'john@example.com'
    });
  });
});

// 6. Router Testing
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { NavigationComponent } from './navigation.component';

describe('NavigationComponent', () => {
  let component: NavigationComponent;
  let fixture: ComponentFixture<NavigationComponent>;
  let router: Router;
  let location: Location;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [NavigationComponent],
      imports: [RouterTestingModule.withRoutes([
        { path: 'users', component: NavigationComponent },
        { path: 'products', component: NavigationComponent }
      ])]
    }).compileComponents();

    fixture = TestBed.createComponent(NavigationComponent);
    component = fixture.componentInstance;
    router = TestBed.inject(Router);
    location = TestBed.inject(Location);
  });

  it('should navigate to users page', fakeAsync(() => {
    component.navigateToUsers();
    tick();
    
    expect(location.path()).toBe('/users');
  }));

  it('should navigate to products page', fakeAsync(() => {
    component.navigateToProducts();
    tick();
    
    expect(location.path()).toBe('/products');
  }));
});

// 7. Async Testing
describe('AsyncComponent', () => {
  let component: AsyncComponent;
  let fixture: ComponentFixture<AsyncComponent>;
  let dataService: jasmine.SpyObj<DataService>;

  beforeEach(async () => {
    const spy = jasmine.createSpyObj('DataService', ['getData']);
    
    await TestBed.configureTestingModule({
      declarations: [AsyncComponent],
      providers: [
        { provide: DataService, useValue: spy }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(AsyncComponent);
    component = fixture.componentInstance;
    dataService = TestBed.inject(DataService) as jasmine.SpyObj<DataService>;
  });

  it('should load data asynchronously', fakeAsync(() => {
    const mockData = { id: 1, name: 'Test Data' };
    dataService.getData.and.returnValue(of(mockData));
    
    component.loadData();
    tick();
    
    expect(component.data).toEqual(mockData);
  }));

  it('should handle async errors', fakeAsync(() => {
    dataService.getData.and.returnValue(throwError(() => new Error('API Error')));
    
    component.loadData();
    tick();
    
    expect(component.error).toBeTruthy();
  }));
});

// 8. TestBed Configuration
describe('TestBed Configuration', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TestComponent],
      imports: [
        HttpClientTestingModule,
        RouterTestingModule,
        ReactiveFormsModule
      ],
      providers: [
        { provide: 'API_URL', useValue: 'http://test-api.com' },
        { provide: APP_INITIALIZER, useFactory: () => () => {}, multi: true }
      ]
    }).compileComponents();
  });

  it('should configure testing module correctly', () => {
    const apiUrl = TestBed.inject('API_URL');
    expect(apiUrl).toBe('http://test-api.com');
  });
});
```

**🐞 Fixes:** Always clean up after tests, use proper mocking strategies, test both success and error scenarios, and ensure tests are isolated and independent.

---

2. What is Jasmine and what is Karma in Angular testing? _(Asked in Capgemini)_

**🧩 Foundation:** Jasmine is a behavior-driven development (BDD) testing framework for JavaScript that provides a clean syntax for writing test specifications, while Karma is a test runner that executes tests in real browsers and provides feedback on test results.

**⚙️ Function:** Jasmine provides the testing syntax and assertion library for writing readable, maintainable tests, while Karma serves as the test runner that executes tests across multiple browsers and provides real-time feedback during development.

**🚀 Features:**
- **Jasmine:** BDD syntax, matchers, spies, async testing, test suites
- **Karma:** Multi-browser testing, real-time feedback, coverage reporting
- **Jasmine:** Built-in assertion library, mocking capabilities
- **Karma:** Configurable test execution, CI/CD integration

**🔁 Flow:**
```typescript
// 1. Jasmine Test Structure
describe('UserService', () => {
  let service: UserService;
  let httpMock: HttpTestingController;

  // Setup - runs before each test
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [UserService]
    });
    
    service = TestBed.inject(UserService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  // Cleanup - runs after each test
  afterEach(() => {
    httpMock.verify();
  });

  // Test suite
  describe('getUsers', () => {
    it('should return users from API', () => {
      const mockUsers = [
        { id: 1, name: 'John Doe' },
        { id: 2, name: 'Jane Smith' }
      ];

      service.getUsers().subscribe(users => {
        expect(users).toEqual(mockUsers);
      });

      const req = httpMock.expectOne('/api/users');
      req.flush(mockUsers);
    });

    it('should handle API errors gracefully', () => {
      service.getUsers().subscribe({
        next: () => fail('should have failed'),
        error: (error) => {
          expect(error.status).toBe(500);
        }
      });

      const req = httpMock.expectOne('/api/users');
      req.flush('Error', { status: 500, statusText: 'Server Error' });
    });
  });
});

// 2. Jasmine Matchers
describe('Jasmine Matchers', () => {
  it('should demonstrate various matchers', () => {
    // Equality
    expect(2 + 2).toBe(4);
    expect([1, 2, 3]).toEqual([1, 2, 3]);
    expect({ name: 'John' }).toEqual({ name: 'John' });

    // Truthiness
    expect(true).toBeTruthy();
    expect(false).toBeFalsy();
    expect(null).toBeNull();
    expect(undefined).toBeUndefined();

    // Numbers
    expect(5).toBeGreaterThan(3);
    expect(3).toBeLessThan(5);
    expect(3.14159).toBeCloseTo(3.14, 2);

    // Strings
    expect('Hello World').toContain('Hello');
    expect('test').toMatch(/^test$/);

    // Arrays
    expect([1, 2, 3]).toContain(2);
    expect([1, 2, 3]).toHaveSize(3);

    // Objects
    expect({ name: 'John', age: 30 }).toHaveKey('name');
    expect({ name: 'John', age: 30 }).toHaveValue('John');
  });
});

// 3. Jasmine Spies
describe('Jasmine Spies', () => {
  let userService: jasmine.SpyObj<UserService>;
  let component: UserComponent;

  beforeEach(() => {
    // Create spy object
    userService = jasmine.createSpyObj('UserService', [
      'getUsers',
      'createUser',
      'updateUser',
      'deleteUser'
    ]);

    TestBed.configureTestingModule({
      declarations: [UserComponent],
      providers: [
        { provide: UserService, useValue: userService }
      ]
    });

    const fixture = TestBed.createComponent(UserComponent);
    component = fixture.componentInstance;
  });

  it('should use spy to mock service calls', () => {
    const mockUsers = [{ id: 1, name: 'John' }];
    userService.getUsers.and.returnValue(of(mockUsers));

    component.loadUsers();

    expect(userService.getUsers).toHaveBeenCalled();
    expect(userService.getUsers).toHaveBeenCalledTimes(1);
    expect(component.users).toEqual(mockUsers);
  });

  it('should track spy calls with arguments', () => {
    const newUser = { name: 'Jane', email: 'jane@example.com' };
    userService.createUser.and.returnValue(of({ id: 2, ...newUser }));

    component.createUser(newUser);

    expect(userService.createUser).toHaveBeenCalledWith(newUser);
  });

  it('should handle spy errors', () => {
    userService.getUsers.and.returnValue(throwError(() => new Error('API Error')));

    component.loadUsers();

    expect(component.error).toBeTruthy();
  });
});

// 4. Async Testing with Jasmine
describe('Async Testing', () => {
  let service: AsyncService;

  beforeEach(() => {
    service = new AsyncService();
  });

  it('should handle promises', async () => {
    const result = await service.getDataAsync();
    expect(result).toBe('async data');
  });

  it('should handle observables', fakeAsync(() => {
    let result: any;
    
    service.getDataObservable().subscribe(data => {
      result = data;
    });

    tick(); // Simulate async operations
    expect(result).toBe('observable data');
  }));

  it('should handle timeouts', fakeAsync(() => {
    let result: any;
    
    service.getDataWithTimeout().subscribe(data => {
      result = data;
    });

    tick(1000); // Advance time by 1 second
    expect(result).toBe('timeout data');
  }));
});

// 5. Karma Configuration
// karma.conf.js
module.exports = function(config) {
  config.set({
    basePath: '',
    frameworks: ['jasmine', '@angular-devkit/build-angular'],
    plugins: [
      require('karma-jasmine'),
      require('karma-chrome-launcher'),
      require('karma-jasmine-html-reporter'),
      require('karma-coverage'),
      require('@angular-devkit/build-angular/plugins/karma')
    ],
    client: {
      clearContext: false
    },
    coverageReporter: {
      dir: require('path').join(__dirname, './coverage'),
      subdir: '.',
      reporters: [
        { type: 'html' },
        { type: 'text-summary' }
      ]
    },
    reporters: ['progress', 'kjhtml'],
    port: 9876,
    colors: true,
    logLevel: config.LOG_INFO,
    autoWatch: true,
    browsers: ['Chrome'],
    singleRun: false,
    restartOnFileChange: true
  });
};

// 6. Custom Jasmine Matchers
beforeEach(() => {
  jasmine.addMatchers({
    toBeValidEmail: () => ({
      compare: (actual: string) => {
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        const pass = emailRegex.test(actual);
        
        return {
          pass,
          message: pass 
            ? `Expected ${actual} not to be a valid email`
            : `Expected ${actual} to be a valid email`
        };
      }
    }),

    toBeValidPhone: () => ({
      compare: (actual: string) => {
        const phoneRegex = /^\+?[\d\s-()]+$/;
        const pass = phoneRegex.test(actual);
        
        return {
          pass,
          message: pass
            ? `Expected ${actual} not to be a valid phone number`
            : `Expected ${actual} to be a valid phone number`
        };
      }
    })
  });
});

describe('Custom Matchers', () => {
  it('should validate email format', () => {
    expect('test@example.com').toBeValidEmail();
    expect('invalid-email').not.toBeValidEmail();
  });

  it('should validate phone format', () => {
    expect('+1-555-123-4567').toBeValidPhone();
    expect('invalid-phone').not.toBeValidPhone();
  });
});

// 7. Test Data Factories
class TestDataFactory {
  static createUser(overrides: Partial<User> = {}): User {
    return {
      id: 1,
      name: 'John Doe',
      email: 'john@example.com',
      age: 30,
      ...overrides
    };
  }

  static createUserList(count: number): User[] {
    return Array.from({ length: count }, (_, i) => 
      this.createUser({ id: i + 1, name: `User ${i + 1}` })
    );
  }
}

describe('Test Data Factory', () => {
  it('should create user with default values', () => {
    const user = TestDataFactory.createUser();
    expect(user.name).toBe('John Doe');
    expect(user.email).toBe('john@example.com');
  });

  it('should create user with overrides', () => {
    const user = TestDataFactory.createUser({ name: 'Jane Smith' });
    expect(user.name).toBe('Jane Smith');
    expect(user.email).toBe('john@example.com'); // Default value
  });

  it('should create user list', () => {
    const users = TestDataFactory.createUserList(3);
    expect(users).toHaveSize(3);
    expect(users[0].name).toBe('User 1');
    expect(users[1].name).toBe('User 2');
  });
});

// 8. Testing with Real Browser APIs
describe('Browser API Testing', () => {
  beforeEach(() => {
    // Mock localStorage
    spyOn(localStorage, 'getItem').and.returnValue('test-value');
    spyOn(localStorage, 'setItem');
  });

  it('should interact with localStorage', () => {
    const service = new StorageService();
    
    service.saveData('key', 'value');
    expect(localStorage.setItem).toHaveBeenCalledWith('key', 'value');
    
    const result = service.getData('key');
    expect(localStorage.getItem).toHaveBeenCalledWith('key');
    expect(result).toBe('test-value');
  });
});
```

**🐞 Fixes:** Always clean up spies and mocks, use proper async testing patterns, configure Karma for your specific needs, and maintain test isolation.

### Intermediate Questions

1. What are test beds (`TestBed`) and how are they used in Angular testing? _(Asked in Wipro)_

**🧩 Foundation:** TestBed is Angular's testing utility that creates a testing module environment similar to NgModule, allowing you to configure dependencies, imports, declarations, and providers for isolated unit testing of Angular components, services, and other artifacts.

**⚙️ Function:** TestBed provides a controlled testing environment that mimics the real Angular application context, enabling comprehensive testing of components with their dependencies, lifecycle hooks, and Angular-specific features like dependency injection and change detection.

**🚀 Features:**
- Module configuration for testing
- Dependency injection setup
- Component compilation and instantiation
- Service mocking and stubbing
- Router and HTTP testing support
- Change detection control

**🔁 Flow:**
```typescript
// 1. Basic TestBed Configuration
import { TestBed, ComponentFixture } from '@angular/core/testing';
import { UserComponent } from './user.component';
import { UserService } from './user.service';

describe('UserComponent', () => {
  let component: UserComponent;
  let fixture: ComponentFixture<UserComponent>;
  let userService: jasmine.SpyObj<UserService>;

  beforeEach(async () => {
    // Create spy object for UserService
    const spy = jasmine.createSpyObj('UserService', ['getUsers', 'createUser']);

    await TestBed.configureTestingModule({
      declarations: [UserComponent],
      providers: [
        { provide: UserService, useValue: spy }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(UserComponent);
    component = fixture.componentInstance;
    userService = TestBed.inject(UserService) as jasmine.SpyObj<UserService>;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

// 2. TestBed with HTTP Testing
describe('UserService with HTTP', () => {
  let service: UserService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [UserService]
    });

    service = TestBed.inject(UserService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should fetch users', () => {
    const mockUsers = [{ id: 1, name: 'John' }];

    service.getUsers().subscribe(users => {
      expect(users).toEqual(mockUsers);
    });

    const req = httpMock.expectOne('/api/users');
    req.flush(mockUsers);
  });
});

// 3. TestBed with Router Testing
describe('NavigationComponent', () => {
  let component: NavigationComponent;
  let fixture: ComponentFixture<NavigationComponent>;
  let router: Router;
  let location: Location;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [NavigationComponent],
      imports: [RouterTestingModule.withRoutes([
        { path: 'users', component: NavigationComponent },
        { path: 'products', component: NavigationComponent }
      ])]
    }).compileComponents();

    fixture = TestBed.createComponent(NavigationComponent);
    component = fixture.componentInstance;
    router = TestBed.inject(Router);
    location = TestBed.inject(Location);
  });

  it('should navigate to users', fakeAsync(() => {
    component.navigateToUsers();
    tick();
    expect(location.path()).toBe('/users');
  }));
});

// 4. TestBed with Forms Testing
describe('UserFormComponent', () => {
  let component: UserFormComponent;
  let fixture: ComponentFixture<UserFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UserFormComponent],
      imports: [ReactiveFormsModule, FormsModule]
    }).compileComponents();

    fixture = TestBed.createComponent(UserFormComponent);
    component = fixture.componentInstance;
  });

  it('should validate form', () => {
    const form = component.userForm;
    const nameControl = form.get('name');
    const emailControl = form.get('email');

    nameControl?.setValue('');
    emailControl?.setValue('invalid-email');

    expect(nameControl?.valid).toBeFalsy();
    expect(emailControl?.valid).toBeFalsy();
  });
});

// 5. TestBed with Custom Providers
describe('Component with Custom Providers', () => {
  let component: ConfigComponent;
  let fixture: ComponentFixture<ConfigComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ConfigComponent],
      providers: [
        { provide: 'API_URL', useValue: 'http://test-api.com' },
        { provide: 'APP_CONFIG', useValue: { debug: true, version: '1.0.0' } },
        { provide: APP_INITIALIZER, useFactory: () => () => {}, multi: true }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(ConfigComponent);
    component = fixture.componentInstance;
  });

  it('should inject custom providers', () => {
    const apiUrl = TestBed.inject('API_URL');
    const config = TestBed.inject('APP_CONFIG');
    
    expect(apiUrl).toBe('http://test-api.com');
    expect(config.debug).toBe(true);
  });
});

// 6. TestBed with Multiple Modules
describe('Component with Multiple Modules', () => {
  let component: DashboardComponent;
  let fixture: ComponentFixture<DashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DashboardComponent],
      imports: [
        HttpClientTestingModule,
        RouterTestingModule,
        ReactiveFormsModule,
        SharedModule,
        MaterialModule
      ],
      providers: [
        DashboardService,
        { provide: 'DASHBOARD_CONFIG', useValue: { refreshInterval: 5000 } }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(DashboardComponent);
    component = fixture.componentInstance;
  });

  it('should create with all dependencies', () => {
    expect(component).toBeTruthy();
    expect(TestBed.inject(DashboardService)).toBeTruthy();
  });
});

// 7. TestBed with Override Providers
describe('Component with Override Providers', () => {
  let component: UserComponent;
  let fixture: ComponentFixture<UserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UserComponent],
      providers: [UserService]
    }).compileComponents();

    // Override provider after compilation
    TestBed.overrideProvider(UserService, {
      useValue: {
        getUsers: () => of([{ id: 1, name: 'Mock User' }])
      }
    });

    fixture = TestBed.createComponent(UserComponent);
    component = fixture.componentInstance;
  });

  it('should use overridden service', () => {
    component.loadUsers();
    expect(component.users).toEqual([{ id: 1, name: 'Mock User' }]);
  });
});

// 8. TestBed with Component Factory
describe('Dynamic Component Creation', () => {
  let componentFactoryResolver: ComponentFactoryResolver;
  let viewContainerRef: ViewContainerRef;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DynamicComponent, HostComponent]
    });

    componentFactoryResolver = TestBed.inject(ComponentFactoryResolver);
    const fixture = TestBed.createComponent(HostComponent);
    viewContainerRef = fixture.componentInstance.viewContainerRef;
  });

  it('should create dynamic component', () => {
    const factory = componentFactoryResolver.resolveComponentFactory(DynamicComponent);
    const componentRef = viewContainerRef.createComponent(factory);
    
    expect(componentRef.instance).toBeTruthy();
  });
});

// 9. TestBed with Change Detection
describe('Change Detection Testing', () => {
  let component: CounterComponent;
  let fixture: ComponentFixture<CounterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CounterComponent]
    }).compileComponents();

    fixture = TestBed.createComponent(CounterComponent);
    component = fixture.componentInstance;
  });

  it('should update view on change detection', () => {
    component.increment();
    fixture.detectChanges();
    
    const compiled = fixture.nativeElement;
    expect(compiled.querySelector('.counter').textContent).toContain('1');
  });

  it('should not update view without change detection', () => {
    component.increment();
    // No fixture.detectChanges()
    
    const compiled = fixture.nativeElement;
    expect(compiled.querySelector('.counter').textContent).toContain('0');
  });
});

// 10. TestBed with Error Handling
describe('Error Handling in TestBed', () => {
  it('should handle compilation errors', async () => {
    try {
      await TestBed.configureTestingModule({
        declarations: [BrokenComponent] // Component with template errors
      }).compileComponents();
      fail('Should have thrown compilation error');
    } catch (error) {
      expect(error.message).toContain('Template parse errors');
    }
  });

  it('should handle provider errors', () => {
    expect(() => {
      TestBed.configureTestingModule({
        providers: [
          { provide: 'INVALID_TOKEN', useFactory: () => { throw new Error('Provider error'); } }
        ]
      });
    }).toThrow();
  });
});

// 11. TestBed with Async Operations
describe('Async Operations in TestBed', () => {
  let component: AsyncComponent;
  let fixture: ComponentFixture<AsyncComponent>;
  let dataService: jasmine.SpyObj<DataService>;

  beforeEach(async () => {
    const spy = jasmine.createSpyObj('DataService', ['getData']);
    
    await TestBed.configureTestingModule({
      declarations: [AsyncComponent],
      providers: [
        { provide: DataService, useValue: spy }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(AsyncComponent);
    component = fixture.componentInstance;
    dataService = TestBed.inject(DataService) as jasmine.SpyObj<DataService>;
  });

  it('should handle async data loading', fakeAsync(() => {
    const mockData = { id: 1, name: 'Test Data' };
    dataService.getData.and.returnValue(of(mockData));

    component.loadData();
    tick();

    expect(component.data).toEqual(mockData);
  }));

  it('should handle async errors', fakeAsync(() => {
    dataService.getData.and.returnValue(throwError(() => new Error('API Error')));

    component.loadData();
    tick();

    expect(component.error).toBeTruthy();
  }));
});

// 12. TestBed with Custom Testing Module
describe('Custom Testing Module', () => {
  let component: ComplexComponent;
  let fixture: ComponentFixture<ComplexComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ComplexComponent],
      imports: [
        HttpClientTestingModule,
        RouterTestingModule,
        ReactiveFormsModule,
        NoopAnimationsModule // Disable animations for testing
      ],
      providers: [
        ComplexService,
        { provide: 'FEATURE_FLAGS', useValue: { newUI: true, beta: false } },
        { provide: APP_INITIALIZER, useFactory: () => () => {}, multi: true }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(ComplexComponent);
    component = fixture.componentInstance;
  });

  it('should create complex component', () => {
    expect(component).toBeTruthy();
    expect(TestBed.inject(ComplexService)).toBeTruthy();
  });
});
```

**🐞 Fixes:** Always call compileComponents() for async setup, clean up after tests, use proper mocking strategies, and ensure TestBed configuration matches the actual module structure.

---

### Advanced Questions

1. How do you test a component that uses `ngOnInit` with service calls? _(Asked in Cognizant)_

**🧩 Foundation:** Testing components with `ngOnInit` and service calls requires proper setup of TestBed with mocked services, understanding of Angular's lifecycle hooks, and knowledge of async testing patterns to handle service responses and component state changes.

**⚙️ Function:** Testing `ngOnInit` with service calls ensures that components properly initialize their data, handle service responses correctly, manage loading states, and respond appropriately to both success and error scenarios from service calls.

**🚀 Features:**
- Lifecycle hook testing
- Service mocking and spying
- Async operation handling
- Error scenario testing
- Loading state management
- Change detection control

**🔁 Flow:**
```typescript
// 1. Basic ngOnInit Testing
describe('UserListComponent', () => {
  let component: UserListComponent;
  let fixture: ComponentFixture<UserListComponent>;
  let userService: jasmine.SpyObj<UserService>;

  beforeEach(async () => {
    const spy = jasmine.createSpyObj('UserService', ['getUsers']);
    
    await TestBed.configureTestingModule({
      declarations: [UserListComponent],
      providers: [
        { provide: UserService, useValue: spy }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(UserListComponent);
    component = fixture.componentInstance;
    userService = TestBed.inject(UserService) as jasmine.SpyObj<UserService>;
  });

  it('should call getUsers on ngOnInit', () => {
    const mockUsers = [{ id: 1, name: 'John' }];
    userService.getUsers.and.returnValue(of(mockUsers));

    component.ngOnInit();

    expect(userService.getUsers).toHaveBeenCalled();
  });

  it('should populate users array on successful service call', () => {
    const mockUsers = [{ id: 1, name: 'John' }];
    userService.getUsers.and.returnValue(of(mockUsers));

    component.ngOnInit();

    expect(component.users).toEqual(mockUsers);
  });
});

// 2. ngOnInit with Loading States
describe('UserListComponent with Loading States', () => {
  let component: UserListComponent;
  let fixture: ComponentFixture<UserListComponent>;
  let userService: jasmine.SpyObj<UserService>;

  beforeEach(async () => {
    const spy = jasmine.createSpyObj('UserService', ['getUsers']);
    
    await TestBed.configureTestingModule({
      declarations: [UserListComponent],
      providers: [
        { provide: UserService, useValue: spy }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(UserListComponent);
    component = fixture.componentInstance;
    userService = TestBed.inject(UserService) as jasmine.SpyObj<UserService>;
  });

  it('should set loading to true initially', () => {
    userService.getUsers.and.returnValue(of([]));
    
    component.ngOnInit();
    
    expect(component.loading).toBe(true);
  });

  it('should set loading to false after service call completes', fakeAsync(() => {
    userService.getUsers.and.returnValue(of([]));
    
    component.ngOnInit();
    tick();
    
    expect(component.loading).toBe(false);
  }));

  it('should set loading to false on error', fakeAsync(() => {
    userService.getUsers.and.returnValue(throwError(() => new Error('API Error')));
    
    component.ngOnInit();
    tick();
    
    expect(component.loading).toBe(false);
  }));
});

// 3. ngOnInit with Error Handling
describe('UserListComponent with Error Handling', () => {
  let component: UserListComponent;
  let fixture: ComponentFixture<UserListComponent>;
  let userService: jasmine.SpyObj<UserService>;

  beforeEach(async () => {
    const spy = jasmine.createSpyObj('UserService', ['getUsers']);
    
    await TestBed.configureTestingModule({
      declarations: [UserListComponent],
      providers: [
        { provide: UserService, useValue: spy }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(UserListComponent);
    component = fixture.componentInstance;
    userService = TestBed.inject(UserService) as jasmine.SpyObj<UserService>;
  });

  it('should handle service errors in ngOnInit', fakeAsync(() => {
    const errorMessage = 'Failed to fetch users';
    userService.getUsers.and.returnValue(throwError(() => new Error(errorMessage)));
    
    component.ngOnInit();
    tick();
    
    expect(component.error).toBe(errorMessage);
    expect(component.users).toEqual([]);
  }));

  it('should retry on error', fakeAsync(() => {
    const mockUsers = [{ id: 1, name: 'John' }];
    userService.getUsers.and.returnValue(throwError(() => new Error('Error')));
    
    component.ngOnInit();
    tick();
    
    userService.getUsers.and.returnValue(of(mockUsers));
    component.retry();
    tick();
    
    expect(component.users).toEqual(mockUsers);
    expect(component.error).toBeNull();
  }));
});

// 4. ngOnInit with Multiple Service Calls
describe('DashboardComponent with Multiple Services', () => {
  let component: DashboardComponent;
  let fixture: ComponentFixture<DashboardComponent>;
  let userService: jasmine.SpyObj<UserService>;
  let productService: jasmine.SpyObj<ProductService>;
  let orderService: jasmine.SpyObj<OrderService>;

  beforeEach(async () => {
    const userSpy = jasmine.createSpyObj('UserService', ['getUsers']);
    const productSpy = jasmine.createSpyObj('ProductService', ['getProducts']);
    const orderSpy = jasmine.createSpyObj('OrderService', ['getOrders']);
    
    await TestBed.configureTestingModule({
      declarations: [DashboardComponent],
      providers: [
        { provide: UserService, useValue: userSpy },
        { provide: ProductService, useValue: productSpy },
        { provide: OrderService, useValue: orderSpy }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(DashboardComponent);
    component = fixture.componentInstance;
    userService = TestBed.inject(UserService) as jasmine.SpyObj<UserService>;
    productService = TestBed.inject(ProductService) as jasmine.SpyObj<ProductService>;
    orderService = TestBed.inject(OrderService) as jasmine.SpyObj<OrderService>;
  });

  it('should call all services in ngOnInit', () => {
    userService.getUsers.and.returnValue(of([]));
    productService.getProducts.and.returnValue(of([]));
    orderService.getOrders.and.returnValue(of([]));
    
    component.ngOnInit();
    
    expect(userService.getUsers).toHaveBeenCalled();
    expect(productService.getProducts).toHaveBeenCalled();
    expect(orderService.getOrders).toHaveBeenCalled();
  });

  it('should handle partial failures', fakeAsync(() => {
    userService.getUsers.and.returnValue(of([{ id: 1, name: 'John' }]));
    productService.getProducts.and.returnValue(throwError(() => new Error('Product API Error')));
    orderService.getOrders.and.returnValue(of([{ id: 1, total: 100 }]));
    
    component.ngOnInit();
    tick();
    
    expect(component.users).toEqual([{ id: 1, name: 'John' }]);
    expect(component.products).toEqual([]);
    expect(component.orders).toEqual([{ id: 1, total: 100 }]);
    expect(component.errors).toContain('Product API Error');
  }));
});

// 5. ngOnInit with Route Parameters
describe('UserDetailComponent with Route Parameters', () => {
  let component: UserDetailComponent;
  let fixture: ComponentFixture<UserDetailComponent>;
  let userService: jasmine.SpyObj<UserService>;
  let activatedRoute: ActivatedRoute;

  beforeEach(async () => {
    const spy = jasmine.createSpyObj('UserService', ['getUser']);
    
    await TestBed.configureTestingModule({
      declarations: [UserDetailComponent],
      providers: [
        { provide: UserService, useValue: spy },
        {
          provide: ActivatedRoute,
          useValue: {
            params: of({ id: '123' }),
            snapshot: { params: { id: '123' } }
          }
        }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(UserDetailComponent);
    component = fixture.componentInstance;
    userService = TestBed.inject(UserService) as jasmine.SpyObj<UserService>;
    activatedRoute = TestBed.inject(ActivatedRoute);
  });

  it('should get user by ID from route params', () => {
    const mockUser = { id: 123, name: 'John Doe' };
    userService.getUser.and.returnValue(of(mockUser));
    
    component.ngOnInit();
    
    expect(userService.getUser).toHaveBeenCalledWith(123);
    expect(component.user).toEqual(mockUser);
  });

  it('should handle invalid user ID', fakeAsync(() => {
    userService.getUser.and.returnValue(throwError(() => new Error('User not found')));
    
    component.ngOnInit();
    tick();
    
    expect(component.error).toBe('User not found');
  }));
});

// 6. ngOnInit with Conditional Logic
describe('ConditionalComponent with Conditional Logic', () => {
  let component: ConditionalComponent;
  let fixture: ComponentFixture<ConditionalComponent>;
  let authService: jasmine.SpyObj<AuthService>;
  let userService: jasmine.SpyObj<UserService>;

  beforeEach(async () => {
    const authSpy = jasmine.createSpyObj('AuthService', ['isAuthenticated', 'getCurrentUser']);
    const userSpy = jasmine.createSpyObj('UserService', ['getUsers']);
    
    await TestBed.configureTestingModule({
      declarations: [ConditionalComponent],
      providers: [
        { provide: AuthService, useValue: authSpy },
        { provide: UserService, useValue: userSpy }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(ConditionalComponent);
    component = fixture.componentInstance;
    authService = TestBed.inject(AuthService) as jasmine.SpyObj<AuthService>;
    userService = TestBed.inject(UserService) as jasmine.SpyObj<UserService>;
  });

  it('should load users only if authenticated', () => {
    authService.isAuthenticated.and.returnValue(true);
    userService.getUsers.and.returnValue(of([]));
    
    component.ngOnInit();
    
    expect(userService.getUsers).toHaveBeenCalled();
  });

  it('should not load users if not authenticated', () => {
    authService.isAuthenticated.and.returnValue(false);
    
    component.ngOnInit();
    
    expect(userService.getUsers).not.toHaveBeenCalled();
  });
});

// 7. ngOnInit with Timer/Delayed Operations
describe('TimerComponent with Delayed Operations', () => {
  let component: TimerComponent;
  let fixture: ComponentFixture<TimerComponent>;
  let dataService: jasmine.SpyObj<DataService>;

  beforeEach(async () => {
    const spy = jasmine.createSpyObj('DataService', ['getData']);
    
    await TestBed.configureTestingModule({
      declarations: [TimerComponent],
      providers: [
        { provide: DataService, useValue: spy }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(TimerComponent);
    component = fixture.componentInstance;
    dataService = TestBed.inject(DataService) as jasmine.SpyObj<DataService>;
  });

  it('should load data after delay', fakeAsync(() => {
    const mockData = { id: 1, name: 'Delayed Data' };
    dataService.getData.and.returnValue(of(mockData));
    
    component.ngOnInit();
    tick(1000); // Advance timer by 1 second
    
    expect(dataService.getData).toHaveBeenCalled();
    expect(component.data).toEqual(mockData);
  }));

  it('should cancel timer on destroy', fakeAsync(() => {
    dataService.getData.and.returnValue(of({}));
    
    component.ngOnInit();
    component.ngOnDestroy();
    tick(1000);
    
    expect(dataService.getData).not.toHaveBeenCalled();
  }));
});

// 8. ngOnInit with Form Initialization
describe('FormComponent with Form Initialization', () => {
  let component: FormComponent;
  let fixture: ComponentFixture<FormComponent>;
  let userService: jasmine.SpyObj<UserService>;

  beforeEach(async () => {
    const spy = jasmine.createSpyObj('UserService', ['getUser']);
    
    await TestBed.configureTestingModule({
      declarations: [FormComponent],
      imports: [ReactiveFormsModule]
    }).compileComponents();

    fixture = TestBed.createComponent(FormComponent);
    component = fixture.componentInstance;
    userService = TestBed.inject(UserService) as jasmine.SpyObj<UserService>;
  });

  it('should initialize form with user data', fakeAsync(() => {
    const mockUser = { id: 1, name: 'John', email: 'john@example.com' };
    userService.getUser.and.returnValue(of(mockUser));
    
    component.ngOnInit();
    tick();
    
    expect(component.userForm.get('name')?.value).toBe('John');
    expect(component.userForm.get('email')?.value).toBe('john@example.com');
  }));

  it('should initialize empty form if no user data', fakeAsync(() => {
    userService.getUser.and.returnValue(of(null));
    
    component.ngOnInit();
    tick();
    
    expect(component.userForm.get('name')?.value).toBe('');
    expect(component.userForm.get('email')?.value).toBe('');
  }));
});

// 9. ngOnInit with Change Detection
describe('ChangeDetectionComponent', () => {
  let component: ChangeDetectionComponent;
  let fixture: ComponentFixture<ChangeDetectionComponent>;
  let dataService: jasmine.SpyObj<DataService>;

  beforeEach(async () => {
    const spy = jasmine.createSpyObj('DataService', ['getData']);
    
    await TestBed.configureTestingModule({
      declarations: [ChangeDetectionComponent]
    }).compileComponents();

    fixture = TestBed.createComponent(ChangeDetectionComponent);
    component = fixture.componentInstance;
    dataService = TestBed.inject(DataService) as jasmine.SpyObj<DataService>;
  });

  it('should update view after ngOnInit', fakeAsync(() => {
    const mockData = { message: 'Hello World' };
    dataService.getData.and.returnValue(of(mockData));
    
    component.ngOnInit();
    tick();
    fixture.detectChanges();
    
    const compiled = fixture.nativeElement;
    expect(compiled.querySelector('.message').textContent).toContain('Hello World');
  }));
});

// 10. Testing ngOnInit with Complex Dependencies
describe('ComplexComponent with Multiple Dependencies', () => {
  let component: ComplexComponent;
  let fixture: ComponentFixture<ComplexComponent>;
  let userService: jasmine.SpyObj<UserService>;
  let configService: jasmine.SpyObj<ConfigService>;
  let loggerService: jasmine.SpyObj<LoggerService>;

  beforeEach(async () => {
    const userSpy = jasmine.createSpyObj('UserService', ['getUsers']);
    const configSpy = jasmine.createSpyObj('ConfigService', ['getConfig']);
    const loggerSpy = jasmine.createSpyObj('LoggerService', ['log']);
    
    await TestBed.configureTestingModule({
      declarations: [ComplexComponent],
      providers: [
        { provide: UserService, useValue: userSpy },
        { provide: ConfigService, useValue: configSpy },
        { provide: LoggerService, useValue: loggerSpy }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(ComplexComponent);
    component = fixture.componentInstance;
    userService = TestBed.inject(UserService) as jasmine.SpyObj<UserService>;
    configService = TestBed.inject(ConfigService) as jasmine.SpyObj<ConfigService>;
    loggerService = TestBed.inject(LoggerService) as jasmine.SpyObj<LoggerService>;
  });

  it('should initialize with all dependencies', fakeAsync(() => {
    const mockUsers = [{ id: 1, name: 'John' }];
    const mockConfig = { apiUrl: 'http://api.com', timeout: 5000 };
    
    userService.getUsers.and.returnValue(of(mockUsers));
    configService.getConfig.and.returnValue(of(mockConfig));
    
    component.ngOnInit();
    tick();
    
    expect(component.users).toEqual(mockUsers);
    expect(component.config).toEqual(mockConfig);
    expect(loggerService.log).toHaveBeenCalledWith('Component initialized');
  }));
});
```

**🐞 Fixes:** Always use fakeAsync for async operations, properly mock all dependencies, test both success and error scenarios, and ensure proper cleanup in ngOnDestroy.

---

2. How do you test Angular services with HTTP dependencies? _(Asked in TCS Digital)_

**🧩 Foundation:** Testing Angular services with HTTP dependencies requires using Angular's HttpClientTestingModule and HttpTestingController to mock HTTP requests, verify request details, and control response data and error scenarios.

**⚙️ Function:** HTTP service testing ensures that services correctly make HTTP requests, handle responses and errors, transform data, and integrate properly with Angular's HTTP client while maintaining test isolation and reliability.

**🚀 Features:**
- HTTP request mocking and verification
- Response data control
- Error scenario testing
- Request parameter validation
- Header and authentication testing
- Interceptor testing

**🔁 Flow:**
```typescript
// 1. Basic HTTP Service Testing
import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { UserService } from './user.service';

describe('UserService', () => {
  let service: UserService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [UserService]
    });

    service = TestBed.inject(UserService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify(); // Verify no outstanding requests
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should fetch users from API', () => {
    const mockUsers = [
      { id: 1, name: 'John Doe', email: 'john@example.com' },
      { id: 2, name: 'Jane Smith', email: 'jane@example.com' }
    ];

    service.getUsers().subscribe(users => {
      expect(users).toEqual(mockUsers);
    });

    const req = httpMock.expectOne('/api/users');
    expect(req.request.method).toBe('GET');
    req.flush(mockUsers);
  });

  it('should handle API errors', () => {
    service.getUsers().subscribe({
      next: () => fail('should have failed'),
      error: (error) => {
        expect(error.status).toBe(500);
        expect(error.message).toBe('Internal Server Error');
      }
    });

    const req = httpMock.expectOne('/api/users');
    req.flush('Server error', { 
      status: 500, 
      statusText: 'Internal Server Error' 
    });
  });
});

// 2. HTTP Service with Query Parameters
describe('UserService with Query Parameters', () => {
  let service: UserService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [UserService]
    });

    service = TestBed.inject(UserService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should send query parameters', () => {
    const mockUsers = [{ id: 1, name: 'John' }];

    service.searchUsers('john', 'active').subscribe(users => {
      expect(users).toEqual(mockUsers);
    });

    const req = httpMock.expectOne('/api/users?name=john&status=active');
    expect(req.request.method).toBe('GET');
    req.flush(mockUsers);
  });

  it('should handle empty query parameters', () => {
    service.searchUsers('', '').subscribe();

    const req = httpMock.expectOne('/api/users');
    expect(req.request.url).toBe('/api/users');
    req.flush([]);
  });
});

// 3. HTTP Service with POST Requests
describe('UserService with POST Requests', () => {
  let service: UserService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [UserService]
    });

    service = TestBed.inject(UserService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should create user with POST request', () => {
    const newUser = { name: 'John Doe', email: 'john@example.com' };
    const createdUser = { id: 1, ...newUser };

    service.createUser(newUser).subscribe(user => {
      expect(user).toEqual(createdUser);
    });

    const req = httpMock.expectOne('/api/users');
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(newUser);
    req.flush(createdUser);
  });

  it('should validate request body', () => {
    const invalidUser = { name: '', email: 'invalid-email' };

    service.createUser(invalidUser).subscribe({
      next: () => fail('should have failed'),
      error: (error) => {
        expect(error.status).toBe(400);
      }
    });

    const req = httpMock.expectOne('/api/users');
    expect(req.request.body).toEqual(invalidUser);
    req.flush('Validation error', { status: 400, statusText: 'Bad Request' });
  });
});

// 4. HTTP Service with PUT/PATCH Requests
describe('UserService with PUT/PATCH Requests', () => {
  let service: UserService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [UserService]
    });

    service = TestBed.inject(UserService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should update user with PUT request', () => {
    const userId = 1;
    const updateData = { name: 'John Updated', email: 'john.updated@example.com' };
    const updatedUser = { id: userId, ...updateData };

    service.updateUser(userId, updateData).subscribe(user => {
      expect(user).toEqual(updatedUser);
    });

    const req = httpMock.expectOne(`/api/users/${userId}`);
    expect(req.request.method).toBe('PUT');
    expect(req.request.body).toEqual(updateData);
    req.flush(updatedUser);
  });

  it('should partially update user with PATCH request', () => {
    const userId = 1;
    const patchData = { name: 'John Updated' };

    service.patchUser(userId, patchData).subscribe(user => {
      expect(user.name).toBe('John Updated');
    });

    const req = httpMock.expectOne(`/api/users/${userId}`);
    expect(req.request.method).toBe('PATCH');
    expect(req.request.body).toEqual(patchData);
    req.flush({ id: userId, name: 'John Updated', email: 'john@example.com' });
  });
});

// 5. HTTP Service with DELETE Requests
describe('UserService with DELETE Requests', () => {
  let service: UserService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [UserService]
    });

    service = TestBed.inject(UserService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should delete user', () => {
    const userId = 1;

    service.deleteUser(userId).subscribe(response => {
      expect(response).toBeTruthy();
    });

    const req = httpMock.expectOne(`/api/users/${userId}`);
    expect(req.request.method).toBe('DELETE');
    req.flush({ success: true });
  });

  it('should handle delete not found', () => {
    const userId = 999;

    service.deleteUser(userId).subscribe({
      next: () => fail('should have failed'),
      error: (error) => {
        expect(error.status).toBe(404);
      }
    });

    const req = httpMock.expectOne(`/api/users/${userId}`);
    req.flush('User not found', { status: 404, statusText: 'Not Found' });
  });
});

// 6. HTTP Service with Headers
describe('UserService with Headers', () => {
  let service: UserService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [UserService]
    });

    service = TestBed.inject(UserService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should send authorization header', () => {
    const token = 'bearer-token';
    service.setAuthToken(token);

    service.getUsers().subscribe();

    const req = httpMock.expectOne('/api/users');
    expect(req.request.headers.get('Authorization')).toBe(`Bearer ${token}`);
    req.flush([]);
  });

  it('should send content-type header for POST requests', () => {
    const newUser = { name: 'John', email: 'john@example.com' };

    service.createUser(newUser).subscribe();

    const req = httpMock.expectOne('/api/users');
    expect(req.request.headers.get('Content-Type')).toBe('application/json');
    req.flush({ id: 1, ...newUser });
  });
});

// 7. HTTP Service with Response Transformation
describe('UserService with Response Transformation', () => {
  let service: UserService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [UserService]
    });

    service = TestBed.inject(UserService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should transform API response', () => {
    const apiResponse = {
      data: [
        { id: 1, name: 'John', email: 'john@example.com' }
      ],
      total: 1,
      page: 1
    };

    service.getUsersWithPagination().subscribe(result => {
      expect(result.users).toEqual(apiResponse.data);
      expect(result.total).toBe(apiResponse.total);
    });

    const req = httpMock.expectOne('/api/users?page=1');
    req.flush(apiResponse);
  });

  it('should handle empty response', () => {
    service.getUsersWithPagination().subscribe(result => {
      expect(result.users).toEqual([]);
      expect(result.total).toBe(0);
    });

    const req = httpMock.expectOne('/api/users?page=1');
    req.flush({ data: [], total: 0, page: 1 });
  });
});

// 8. HTTP Service with Error Handling
describe('UserService with Error Handling', () => {
  let service: UserService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [UserService]
    });

    service = TestBed.inject(UserService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should handle network errors', () => {
    service.getUsers().subscribe({
      next: () => fail('should have failed'),
      error: (error) => {
        expect(error.status).toBe(0);
        expect(error.statusText).toBe('Unknown Error');
      }
    });

    const req = httpMock.expectOne('/api/users');
    req.error(new ErrorEvent('Network error'));
  });

  it('should handle timeout errors', () => {
    service.getUsers().subscribe({
      next: () => fail('should have failed'),
      error: (error) => {
        expect(error.status).toBe(408);
      }
    });

    const req = httpMock.expectOne('/api/users');
    req.flush('Timeout', { status: 408, statusText: 'Request Timeout' });
  });

  it('should retry on failure', () => {
    const mockUsers = [{ id: 1, name: 'John' }];

    service.getUsersWithRetry().subscribe(users => {
      expect(users).toEqual(mockUsers);
    });

    // First request fails
    const req1 = httpMock.expectOne('/api/users');
    req1.flush('Server error', { status: 500, statusText: 'Internal Server Error' });

    // Second request succeeds
    const req2 = httpMock.expectOne('/api/users');
    req2.flush(mockUsers);
  });
});

// 9. HTTP Service with Interceptors
describe('UserService with Interceptors', () => {
  let service: UserService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [
        UserService,
        {
          provide: HTTP_INTERCEPTORS,
          useClass: AuthInterceptor,
          multi: true
        }
      ]
    });

    service = TestBed.inject(UserService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should include auth header from interceptor', () => {
    service.getUsers().subscribe();

    const req = httpMock.expectOne('/api/users');
    expect(req.request.headers.has('Authorization')).toBe(true);
    req.flush([]);
  });
});

// 10. HTTP Service with File Upload
describe('UserService with File Upload', () => {
  let service: UserService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [UserService]
    });

    service = TestBed.inject(UserService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should upload file', () => {
    const file = new File(['test content'], 'test.txt', { type: 'text/plain' });
    const formData = new FormData();
    formData.append('file', file);

    service.uploadFile(formData).subscribe(response => {
      expect(response.success).toBe(true);
    });

    const req = httpMock.expectOne('/api/upload');
    expect(req.request.method).toBe('POST');
    expect(req.request.body instanceof FormData).toBe(true);
    req.flush({ success: true });
  });
});

// 11. HTTP Service with Multiple Concurrent Requests
describe('UserService with Concurrent Requests', () => {
  let service: UserService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [UserService]
    });

    service = TestBed.inject(UserService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should handle multiple concurrent requests', () => {
    const user1 = { id: 1, name: 'John' };
    const user2 = { id: 2, name: 'Jane' };

    service.getUser(1).subscribe(u => expect(u).toEqual(user1));
    service.getUser(2).subscribe(u => expect(u).toEqual(user2));

    const req1 = httpMock.expectOne('/api/users/1');
    const req2 = httpMock.expectOne('/api/users/2');

    req1.flush(user1);
    req2.flush(user2);
  });
});

// 12. HTTP Service with Response Caching
describe('UserService with Caching', () => {
  let service: UserService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [UserService]
    });

    service = TestBed.inject(UserService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should cache responses', () => {
    const mockUsers = [{ id: 1, name: 'John' }];

    // First request
    service.getUsers().subscribe(users => {
      expect(users).toEqual(mockUsers);
    });

    const req1 = httpMock.expectOne('/api/users');
    req1.flush(mockUsers);

    // Second request should use cache
    service.getUsers().subscribe(users => {
      expect(users).toEqual(mockUsers);
    });

    // Should not make another HTTP request
    httpMock.expectNone('/api/users');
  });
});
```

**🐞 Fixes:** Always call httpMock.verify() in afterEach, test both success and error scenarios, verify request methods and URLs, and handle async operations properly.

### Advanced Questions

1. How do you test a component that uses `ngOnInit` with service calls? _(Asked in Cognizant)_
2. How do you test Angular services with HTTP dependencies? _(Asked in TCS Digital)_

### Tough Questions

1. Your app relies on interceptors and external APIs. How would you mock and test these requests? _(Asked in HCL)_

**🧩 Foundation:** Testing applications with interceptors and external APIs requires sophisticated mocking strategies that can handle HTTP interceptors, authentication flows, error scenarios, and complex request/response patterns while maintaining test isolation and reliability.

**⚙️ Function:** Comprehensive testing of interceptors and external APIs ensures that authentication flows work correctly, error handling is robust, request/response transformations are accurate, and the application behaves correctly under various network conditions and API responses.

**🚀 Features:**
- Interceptor testing with HTTP mocking
- Authentication flow testing
- Error scenario simulation
- Request/response transformation testing
- Network condition simulation
- API response mocking

**🔁 Flow:**
```typescript
// 1. Testing with Multiple Interceptors
describe('UserService with Interceptors', () => {
  let service: UserService;
  let httpMock: HttpTestingController;
  let authService: jasmine.SpyObj<AuthService>;

  beforeEach(() => {
    const authSpy = jasmine.createSpyObj('AuthService', ['getToken', 'refreshToken']);
    
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [
        UserService,
        { provide: AuthService, useValue: authSpy },
        {
          provide: HTTP_INTERCEPTORS,
          useClass: AuthInterceptor,
          multi: true
        },
        {
          provide: HTTP_INTERCEPTORS,
          useClass: LoggingInterceptor,
          multi: true
        },
        {
          provide: HTTP_INTERCEPTORS,
          useClass: ErrorInterceptor,
          multi: true
        }
      ]
    });

    service = TestBed.inject(UserService);
    httpMock = TestBed.inject(HttpTestingController);
    authService = TestBed.inject(AuthService) as jasmine.SpyObj<AuthService>;
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should include auth token from interceptor', () => {
    authService.getToken.and.returnValue('test-token');

    service.getUsers().subscribe();

    const req = httpMock.expectOne('/api/users');
    expect(req.request.headers.get('Authorization')).toBe('Bearer test-token');
    req.flush([]);
  });

  it('should handle token refresh on 401', () => {
    authService.getToken.and.returnValue('expired-token');
    authService.refreshToken.and.returnValue(of({ accessToken: 'new-token' }));

    service.getUsers().subscribe();

    // First request with expired token
    const req1 = httpMock.expectOne('/api/users');
    req1.flush('Unauthorized', { status: 401, statusText: 'Unauthorized' });

    // Refresh token request
    const refreshReq = httpMock.expectOne('/api/auth/refresh');
    refreshReq.flush({ accessToken: 'new-token' });

    // Retry with new token
    const req2 = httpMock.expectOne('/api/users');
    expect(req2.request.headers.get('Authorization')).toBe('Bearer new-token');
    req2.flush([]);
  });
});

// 2. Testing External API Integration
describe('External API Integration', () => {
  let service: ExternalApiService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [ExternalApiService]
    });

    service = TestBed.inject(ExternalApiService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should handle external API rate limiting', () => {
    service.getExternalData().subscribe({
      next: () => fail('should have failed'),
      error: (error) => {
        expect(error.status).toBe(429);
        expect(error.headers.get('Retry-After')).toBe('60');
      }
    });

    const req = httpMock.expectOne('https://api.external.com/data');
    req.flush('Rate limit exceeded', { 
      status: 429, 
      statusText: 'Too Many Requests',
      headers: { 'Retry-After': '60' }
    });
  });

  it('should handle external API authentication', () => {
    service.getAuthenticatedData().subscribe();

    const req = httpMock.expectOne('https://api.external.com/secure-data');
    expect(req.request.headers.get('X-API-Key')).toBe('external-api-key');
    req.flush({ data: 'secure content' });
  });
});

// 3. Testing Complex Interceptor Chains
describe('Complex Interceptor Chain', () => {
  let service: ComplexService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [
        ComplexService,
        {
          provide: HTTP_INTERCEPTORS,
          useClass: RequestTransformInterceptor,
          multi: true
        },
        {
          provide: HTTP_INTERCEPTORS,
          useClass: ResponseTransformInterceptor,
          multi: true
        },
        {
          provide: HTTP_INTERCEPTORS,
          useClass: RetryInterceptor,
          multi: true
        }
      ]
    });

    service = TestBed.inject(ComplexService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should transform request and response', () => {
    const inputData = { firstName: 'John', lastName: 'Doe' };
    const expectedRequest = { first_name: 'John', last_name: 'Doe' };
    const apiResponse = { user_id: 1, first_name: 'John', last_name: 'Doe' };
    const expectedResponse = { userId: 1, firstName: 'John', lastName: 'Doe' };

    service.createUser(inputData).subscribe(response => {
      expect(response).toEqual(expectedResponse);
    });

    const req = httpMock.expectOne('/api/users');
    expect(req.request.body).toEqual(expectedRequest);
    req.flush(apiResponse);
  });

  it('should retry failed requests', () => {
    const mockData = { id: 1, name: 'John' };

    service.getDataWithRetry().subscribe(data => {
      expect(data).toEqual(mockData);
    });

    // First request fails
    const req1 = httpMock.expectOne('/api/data');
    req1.flush('Server error', { status: 500, statusText: 'Internal Server Error' });

    // Second request succeeds
    const req2 = httpMock.expectOne('/api/data');
    req2.flush(mockData);
  });
});

// 4. Testing Authentication Interceptors
describe('Authentication Interceptor Testing', () => {
  let service: AuthenticatedService;
  let httpMock: HttpTestingController;
  let authService: jasmine.SpyObj<AuthService>;

  beforeEach(() => {
    const authSpy = jasmine.createSpyObj('AuthService', [
      'getToken', 
      'refreshToken', 
      'isTokenExpired',
      'logout'
    ]);
    
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [
        AuthenticatedService,
        { provide: AuthService, useValue: authSpy },
        {
          provide: HTTP_INTERCEPTORS,
          useClass: AuthInterceptor,
          multi: true
        }
      ]
    });

    service = TestBed.inject(AuthenticatedService);
    httpMock = TestBed.inject(HttpTestingController);
    authService = TestBed.inject(AuthService) as jasmine.SpyObj<AuthService>;
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should add auth token to requests', () => {
    authService.getToken.and.returnValue('valid-token');
    authService.isTokenExpired.and.returnValue(false);

    service.getProtectedData().subscribe();

    const req = httpMock.expectOne('/api/protected');
    expect(req.request.headers.get('Authorization')).toBe('Bearer valid-token');
    req.flush({ data: 'protected content' });
  });

  it('should refresh token when expired', () => {
    authService.getToken.and.returnValue('expired-token');
    authService.isTokenExpired.and.returnValue(true);
    authService.refreshToken.and.returnValue(of({ accessToken: 'new-token' }));

    service.getProtectedData().subscribe();

    // Should refresh token first
    const refreshReq = httpMock.expectOne('/api/auth/refresh');
    refreshReq.flush({ accessToken: 'new-token' });

    // Then make the actual request
    const req = httpMock.expectOne('/api/protected');
    req.flush({ data: 'protected content' });
  });

  it('should logout on refresh failure', () => {
    authService.getToken.and.returnValue('expired-token');
    authService.isTokenExpired.and.returnValue(true);
    authService.refreshToken.and.returnValue(throwError(() => new Error('Refresh failed')));

    service.getProtectedData().subscribe({
      next: () => fail('should have failed'),
      error: (error) => {
        expect(error.message).toBe('Authentication failed');
      }
    });

    const refreshReq = httpMock.expectOne('/api/auth/refresh');
    refreshReq.flush('Refresh failed', { status: 401, statusText: 'Unauthorized' });

    expect(authService.logout).toHaveBeenCalled();
  });
});

// 5. Testing Error Handling Interceptors
describe('Error Handling Interceptor', () => {
  let service: ErrorHandlingService;
  let httpMock: HttpTestingController;
  let notificationService: jasmine.SpyObj<NotificationService>;

  beforeEach(() => {
    const notificationSpy = jasmine.createSpyObj('NotificationService', ['showError', 'showSuccess']);
    
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [
        ErrorHandlingService,
        { provide: NotificationService, useValue: notificationSpy },
        {
          provide: HTTP_INTERCEPTORS,
          useClass: ErrorHandlingInterceptor,
          multi: true
        }
      ]
    });

    service = TestBed.inject(ErrorHandlingService);
    httpMock = TestBed.inject(HttpTestingController);
    notificationService = TestBed.inject(NotificationService) as jasmine.SpyObj<NotificationService>;
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should handle 404 errors', () => {
    service.getData().subscribe({
      next: () => fail('should have failed'),
      error: (error) => {
        expect(error.status).toBe(404);
      }
    });

    const req = httpMock.expectOne('/api/data');
    req.flush('Not found', { status: 404, statusText: 'Not Found' });

    expect(notificationService.showError).toHaveBeenCalledWith('Resource not found');
  });

  it('should handle 500 errors', () => {
    service.getData().subscribe({
      next: () => fail('should have failed'),
      error: (error) => {
        expect(error.status).toBe(500);
      }
    });

    const req = httpMock.expectOne('/api/data');
    req.flush('Server error', { status: 500, statusText: 'Internal Server Error' });

    expect(notificationService.showError).toHaveBeenCalledWith('Server error occurred');
  });

  it('should handle network errors', () => {
    service.getData().subscribe({
      next: () => fail('should have failed'),
      error: (error) => {
        expect(error.status).toBe(0);
      }
    });

    const req = httpMock.expectOne('/api/data');
    req.error(new ErrorEvent('Network error'));

    expect(notificationService.showError).toHaveBeenCalledWith('Network connection error');
  });
});

// 6. Testing Request/Response Transformation
describe('Request/Response Transformation', () => {
  let service: TransformService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [
        TransformService,
        {
          provide: HTTP_INTERCEPTORS,
          useClass: TransformInterceptor,
          multi: true
        }
      ]
    });

    service = TestBed.inject(TransformService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should transform request body to snake_case', () => {
    const inputData = { firstName: 'John', lastName: 'Doe', emailAddress: 'john@example.com' };
    const expectedRequest = { first_name: 'John', last_name: 'Doe', email_address: 'john@example.com' };

    service.createUser(inputData).subscribe();

    const req = httpMock.expectOne('/api/users');
    expect(req.request.body).toEqual(expectedRequest);
    req.flush({ id: 1, ...expectedRequest });
  });

  it('should transform response body to camelCase', () => {
    const apiResponse = { user_id: 1, first_name: 'John', last_name: 'Doe' };
    const expectedResponse = { userId: 1, firstName: 'John', lastName: 'Doe' };

    service.getUser(1).subscribe(user => {
      expect(user).toEqual(expectedResponse);
    });

    const req = httpMock.expectOne('/api/users/1');
    req.flush(apiResponse);
  });
});

// 7. Testing Caching Interceptors
describe('Caching Interceptor', () => {
  let service: CachedService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [
        CachedService,
        {
          provide: HTTP_INTERCEPTORS,
          useClass: CacheInterceptor,
          multi: true
        }
      ]
    });

    service = TestBed.inject(CachedService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should cache GET requests', () => {
    const mockData = { id: 1, name: 'John' };

    // First request
    service.getUser(1).subscribe(user => {
      expect(user).toEqual(mockData);
    });

    const req1 = httpMock.expectOne('/api/users/1');
    req1.flush(mockData);

    // Second request should use cache
    service.getUser(1).subscribe(user => {
      expect(user).toEqual(mockData);
    });

    // Should not make another HTTP request
    httpMock.expectNone('/api/users/1');
  });

  it('should not cache POST requests', () => {
    const userData = { name: 'John', email: 'john@example.com' };

    service.createUser(userData).subscribe();
    service.createUser(userData).subscribe();

    // Both requests should be made
    const req1 = httpMock.expectOne('/api/users');
    const req2 = httpMock.expectOne('/api/users');
    
    req1.flush({ id: 1, ...userData });
    req2.flush({ id: 2, ...userData });
  });
});

// 8. Testing Logging Interceptors
describe('Logging Interceptor', () => {
  let service: LoggedService;
  let httpMock: HttpTestingController;
  let loggerService: jasmine.SpyObj<LoggerService>;

  beforeEach(() => {
    const loggerSpy = jasmine.createSpyObj('LoggerService', ['log', 'error']);
    
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [
        LoggedService,
        { provide: LoggerService, useValue: loggerSpy },
        {
          provide: HTTP_INTERCEPTORS,
          useClass: LoggingInterceptor,
          multi: true
        }
      ]
    });

    service = TestBed.inject(LoggedService);
    httpMock = TestBed.inject(HttpTestingController);
    loggerService = TestBed.inject(LoggerService) as jasmine.SpyObj<LoggerService>;
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should log successful requests', () => {
    service.getData().subscribe();

    const req = httpMock.expectOne('/api/data');
    req.flush({ data: 'success' });

    expect(loggerService.log).toHaveBeenCalledWith('HTTP Request: GET /api/data');
    expect(loggerService.log).toHaveBeenCalledWith('HTTP Response: GET /api/data - 200');
  });

  it('should log failed requests', () => {
    service.getData().subscribe({
      next: () => fail('should have failed'),
      error: () => {}
    });

    const req = httpMock.expectOne('/api/data');
    req.flush('Error', { status: 500, statusText: 'Internal Server Error' });

    expect(loggerService.error).toHaveBeenCalledWith('HTTP Error: GET /api/data - 500');
  });
});

// 9. Testing Multiple External APIs
describe('Multiple External APIs', () => {
  let service: MultiApiService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [MultiApiService]
    });

    service = TestBed.inject(MultiApiService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should handle multiple external APIs', () => {
    const weatherData = { temperature: 25, condition: 'sunny' };
    const newsData = { articles: [{ title: 'Breaking News' }] };

    service.getWeatherAndNews().subscribe(result => {
      expect(result.weather).toEqual(weatherData);
      expect(result.news).toEqual(newsData);
    });

    const weatherReq = httpMock.expectOne('https://api.weather.com/current');
    const newsReq = httpMock.expectOne('https://api.news.com/latest');

    weatherReq.flush(weatherData);
    newsReq.flush(newsData);
  });

  it('should handle partial API failures', () => {
    service.getWeatherAndNews().subscribe({
      next: (result) => {
        expect(result.weather).toBeNull();
        expect(result.news).toEqual({ articles: [] });
      }
    });

    const weatherReq = httpMock.expectOne('https://api.weather.com/current');
    const newsReq = httpMock.expectOne('https://api.news.com/latest');

    weatherReq.flush('Service unavailable', { status: 503, statusText: 'Service Unavailable' });
    newsReq.flush({ articles: [] });
  });
});

// 10. Testing API Versioning
describe('API Versioning', () => {
  let service: VersionedService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [VersionedService]
    });

    service = TestBed.inject(VersionedService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should use correct API version', () => {
    service.getDataV1().subscribe();
    service.getDataV2().subscribe();

    const v1Req = httpMock.expectOne('/api/v1/data');
    const v2Req = httpMock.expectOne('/api/v2/data');

    v1Req.flush({ data: 'v1 response' });
    v2Req.flush({ data: 'v2 response' });
  });

  it('should handle version deprecation', () => {
    service.getDataV1().subscribe({
      next: () => fail('should have failed'),
      error: (error) => {
        expect(error.status).toBe(410);
      }
    });

    const req = httpMock.expectOne('/api/v1/data');
    req.flush('API version deprecated', { status: 410, statusText: 'Gone' });
  });
});
```

**🐞 Fixes:** Always test interceptor order, handle async operations properly, test both success and error scenarios, and ensure proper cleanup of cached data.

---

### Situational / Real-world Questions

1. You inherit a codebase with no tests. How would you begin adding them without slowing delivery? _(Asked in Infosys)_

**🧩 Foundation:** Adding tests to an existing codebase without tests requires a strategic approach that prioritizes critical functionality, implements tests incrementally, and maintains development velocity while gradually improving code quality and reliability.

**⚙️ Function:** A systematic testing strategy ensures that new features are properly tested, critical bugs are caught early, refactoring becomes safer, and the codebase gradually becomes more maintainable without disrupting ongoing development work.

**🚀 Features:**
- Incremental test implementation
- Critical path prioritization
- Test-driven development for new features
- Automated testing integration
- Code coverage monitoring
- Risk-based testing approach

**🔁 Flow:**
```typescript
// 1. Assessment and Planning Phase
class TestStrategyService {
  private criticalPaths = [
    'user authentication',
    'payment processing',
    'data persistence',
    'core business logic'
  ];

  private riskAssessment = {
    high: ['auth', 'payments', 'data'],
    medium: ['forms', 'validation', 'api'],
    low: ['ui', 'animations', 'utilities']
  };

  assessCodebase(): TestAssessment {
    return {
      totalComponents: this.countComponents(),
      totalServices: this.countServices(),
      criticalPaths: this.identifyCriticalPaths(),
      testCoverage: 0,
      recommendedPriority: this.getPriorityOrder()
    };
  }

  private getPriorityOrder(): string[] {
    return [
      'AuthService',
      'PaymentService', 
      'UserService',
      'DataService',
      'FormValidationService'
    ];
  }
}

// 2. Start with Critical Services
describe('AuthService - Critical Path Testing', () => {
  let service: AuthService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [AuthService]
    });

    service = TestBed.inject(AuthService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should authenticate user successfully', () => {
    const credentials = { username: 'test', password: 'password' };
    const authResponse = { token: 'jwt-token', user: { id: 1, name: 'Test User' } };

    service.login(credentials).subscribe(response => {
      expect(response.token).toBe('jwt-token');
      expect(response.user.name).toBe('Test User');
    });

    const req = httpMock.expectOne('/api/auth/login');
    expect(req.request.method).toBe('POST');
    expect(req.request.body).toEqual(credentials);
    req.flush(authResponse);
  });

  it('should handle authentication failure', () => {
    const credentials = { username: 'test', password: 'wrong' };

    service.login(credentials).subscribe({
      next: () => fail('should have failed'),
      error: (error) => {
        expect(error.status).toBe(401);
      }
    });

    const req = httpMock.expectOne('/api/auth/login');
    req.flush('Invalid credentials', { status: 401, statusText: 'Unauthorized' });
  });
});

// 3. Implement Test-Driven Development for New Features
describe('New Feature - TDD Approach', () => {
  let service: NewFeatureService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [NewFeatureService]
    });

    service = TestBed.inject(NewFeatureService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  // Write test first, then implement feature
  it('should calculate discount based on user tier', () => {
    const userTier = 'premium';
    const originalPrice = 100;

    const discount = service.calculateDiscount(userTier, originalPrice);

    expect(discount).toBe(20); // 20% discount for premium users
  });

  it('should apply maximum discount limit', () => {
    const userTier = 'premium';
    const originalPrice = 1000;

    const discount = service.calculateDiscount(userTier, originalPrice);

    expect(discount).toBe(200); // Maximum discount of $200
  });
});

// 4. Incremental Component Testing
describe('UserComponent - Incremental Testing', () => {
  let component: UserComponent;
  let fixture: ComponentFixture<UserComponent>;
  let userService: jasmine.SpyObj<UserService>;

  beforeEach(async () => {
    const spy = jasmine.createSpyObj('UserService', ['getUsers', 'createUser']);
    
    await TestBed.configureTestingModule({
      declarations: [UserComponent],
      providers: [
        { provide: UserService, useValue: spy }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(UserComponent);
    component = fixture.componentInstance;
    userService = TestBed.inject(UserService) as jasmine.SpyObj<UserService>;
  });

  // Start with basic functionality
  it('should create component', () => {
    expect(component).toBeTruthy();
  });

  it('should load users on init', () => {
    const mockUsers = [{ id: 1, name: 'John' }];
    userService.getUsers.and.returnValue(of(mockUsers));

    component.ngOnInit();

    expect(component.users).toEqual(mockUsers);
  });

  // Add more tests incrementally
  it('should handle loading state', () => {
    userService.getUsers.and.returnValue(of([]));
    
    component.ngOnInit();
    
    expect(component.loading).toBe(false);
  });
});

// 5. Automated Test Generation
class TestGeneratorService {
  generateComponentTests(componentPath: string): string {
    const componentName = this.extractComponentName(componentPath);
    
    return `
describe('${componentName}', () => {
  let component: ${componentName};
  let fixture: ComponentFixture<${componentName}>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [${componentName}]
    }).compileComponents();

    fixture = TestBed.createComponent(${componentName});
    component = fixture.componentInstance;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  // TODO: Add more specific tests based on component functionality
});
    `;
  }

  generateServiceTests(servicePath: string): string {
    const serviceName = this.extractServiceName(servicePath);
    
    return `
describe('${serviceName}', () => {
  let service: ${serviceName};
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [${serviceName}]
    });

    service = TestBed.inject(${serviceName});
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  // TODO: Add HTTP request tests
});
    `;
  }
}

// 6. Test Coverage Monitoring
class CoverageMonitorService {
  private coverageThresholds = {
    statements: 70,
    branches: 60,
    functions: 70,
    lines: 70
  };

  monitorCoverage(): CoverageReport {
    return {
      currentCoverage: this.getCurrentCoverage(),
      targetCoverage: this.coverageThresholds,
      improvementAreas: this.identifyImprovementAreas(),
      nextSteps: this.generateNextSteps()
    };
  }

  private getCurrentCoverage(): any {
    // Read coverage report
    return {
      statements: 45,
      branches: 30,
      functions: 50,
      lines: 45
    };
  }

  private identifyImprovementAreas(): string[] {
    return [
      'Add tests for error handling in UserService',
      'Test edge cases in PaymentService',
      'Add integration tests for authentication flow'
    ];
  }

  private generateNextSteps(): string[] {
    return [
      'Focus on critical business logic',
      'Add tests for new features',
      'Refactor untested legacy code'
    ];
  }
}

// 7. Risk-Based Testing Strategy
class RiskBasedTestingService {
  private riskMatrix = {
    high: {
      probability: 'high',
      impact: 'high',
      testingPriority: 'immediate'
    },
    medium: {
      probability: 'medium', 
      impact: 'medium',
      testingPriority: 'planned'
    },
    low: {
      probability: 'low',
      impact: 'low', 
      testingPriority: 'when-time-permits'
    }
  };

  assessRisk(component: string): RiskAssessment {
    const riskFactors = this.analyzeRiskFactors(component);
    
    return {
      component,
      riskLevel: this.calculateRiskLevel(riskFactors),
      recommendedTests: this.recommendTests(riskFactors),
      priority: this.getPriority(riskFactors)
    };
  }

  private analyzeRiskFactors(component: string): RiskFactors {
    return {
      complexity: this.assessComplexity(component),
      businessCriticality: this.assessBusinessCriticality(component),
      changeFrequency: this.assessChangeFrequency(component),
      dependencies: this.assessDependencies(component)
    };
  }

  private recommendTests(riskFactors: RiskFactors): string[] {
    const tests = [];
    
    if (riskFactors.businessCriticality === 'high') {
      tests.push('Integration tests', 'End-to-end tests');
    }
    
    if (riskFactors.complexity === 'high') {
      tests.push('Unit tests', 'Edge case tests');
    }
    
    if (riskFactors.changeFrequency === 'high') {
      tests.push('Regression tests', 'Automated tests');
    }
    
    return tests;
  }
}

// 8. Test Implementation Timeline
class TestImplementationPlan {
  private phases = [
    {
      phase: 'Phase 1 - Critical Paths',
      duration: '2 weeks',
      focus: ['AuthService', 'PaymentService', 'UserService'],
      deliverables: ['Unit tests for critical services', 'Basic integration tests']
    },
    {
      phase: 'Phase 2 - Core Components',
      duration: '3 weeks', 
      focus: ['UserComponent', 'PaymentComponent', 'DashboardComponent'],
      deliverables: ['Component tests', 'Service integration tests']
    },
    {
      phase: 'Phase 3 - Edge Cases',
      duration: '2 weeks',
      focus: ['Error handling', 'Validation', 'Edge cases'],
      deliverables: ['Error scenario tests', 'Validation tests']
    },
    {
      phase: 'Phase 4 - Integration',
      duration: '2 weeks',
      focus: ['End-to-end flows', 'Cross-component integration'],
      deliverables: ['E2E tests', 'Integration test suite']
    }
  ];

  generateTimeline(): ImplementationTimeline {
    return {
      totalDuration: '9 weeks',
      phases: this.phases,
      milestones: this.generateMilestones(),
      successMetrics: this.defineSuccessMetrics()
    };
  }

  private generateMilestones(): Milestone[] {
    return [
      { week: 2, milestone: 'Critical services tested', coverage: 30 },
      { week: 5, milestone: 'Core components tested', coverage: 60 },
      { week: 7, milestone: 'Edge cases covered', coverage: 75 },
      { week: 9, milestone: 'Full integration testing', coverage: 85 }
    ];
  }

  private defineSuccessMetrics(): SuccessMetrics {
    return {
      codeCoverage: 80,
      testPassRate: 95,
      buildTime: '< 5 minutes',
      deploymentConfidence: 'high'
    };
  }
}

// 9. Continuous Integration Setup
class CITestSetup {
  configureCIPipeline(): CIPipeline {
    return {
      stages: [
        {
          name: 'Unit Tests',
          command: 'npm run test:unit',
          threshold: '80% coverage',
          failOnThreshold: true
        },
        {
          name: 'Integration Tests',
          command: 'npm run test:integration',
          threshold: '70% coverage',
          failOnThreshold: false
        },
        {
          name: 'E2E Tests',
          command: 'npm run test:e2e',
          threshold: 'Critical paths covered',
          failOnThreshold: true
        }
      ],
      reporting: {
        coverage: true,
        testResults: true,
        performance: true
      }
    };
  }
}

// 10. Test Maintenance Strategy
class TestMaintenanceService {
  private maintenanceSchedule = {
    daily: ['Run test suite', 'Check for failures'],
    weekly: ['Review coverage reports', 'Update test data'],
    monthly: ['Refactor test code', 'Update test dependencies'],
    quarterly: ['Review test strategy', 'Optimize test performance']
  };

  generateMaintenancePlan(): MaintenancePlan {
    return {
      schedule: this.maintenanceSchedule,
      responsibilities: this.assignResponsibilities(),
      tools: this.recommendTools(),
      metrics: this.defineMetrics()
    };
  }

  private assignResponsibilities(): Responsibility[] {
    return [
      { role: 'Developer', tasks: ['Write tests for new features', 'Fix failing tests'] },
      { role: 'QA Engineer', tasks: ['Review test coverage', 'Create integration tests'] },
      { role: 'DevOps Engineer', tasks: ['Maintain CI pipeline', 'Monitor test performance'] }
    ];
  }

  private recommendTools(): Tool[] {
    return [
      { name: 'Jest', purpose: 'Unit testing' },
      { name: 'Cypress', purpose: 'E2E testing' },
      { name: 'Istanbul', purpose: 'Coverage reporting' },
      { name: 'TestCafe', purpose: 'Cross-browser testing' }
    ];
  }
}
```

**🐞 Fixes:** Start with critical paths, implement tests incrementally, maintain development velocity, and focus on high-impact, low-effort testing opportunities.

---

## 13. Animations

### Basic Questions

1. What are Angular animations and how do you implement them? _(Asked in Infosys)_

**🧩 Foundation:** Angular animations are built on top of the Web Animations API and CSS animations, allowing you to create smooth transitions and effects in your components using TypeScript and CSS.

**⚙️ Function:** Animations enhance user experience by providing visual feedback, guiding user attention, and making interfaces feel more responsive and polished.

**🚀 Features:**
- Built on Web Animations API
- Support for CSS transitions and keyframes
- Trigger-based animation system
- State-based animations
- Complex animation sequences

**🔁 Flow:**
```typescript
import { Component } from '@angular/core';
import { trigger, state, style, transition, animate } from '@angular/animations';

@Component({
  selector: 'app-fade-in',
  template: `
    <div [@fadeInOut]="isVisible ? 'visible' : 'hidden'">
      Content that fades in/out
    </div>
    <button (click)="toggle()">Toggle</button>
  `,
  animations: [
    trigger('fadeInOut', [
      state('hidden', style({ opacity: 0 })),
      state('visible', style({ opacity: 1 })),
      transition('hidden <=> visible', animate('300ms ease-in-out'))
    ])
  ]
})
export class FadeInComponent {
  isVisible = false;
  
  toggle() {
    this.isVisible = !this.isVisible;
  }
}
```

**🐞 Fixes:** Common issues include forgetting to import BrowserAnimationsModule, not handling animation states properly, and performance issues from complex animations on low-end devices.

---

2. How do you create a simple fade-in animation? _(Asked in Capgemini)_

**🧩 Foundation:** A fade-in animation changes the opacity from 0 to 1 over a specified duration, creating a smooth appearance effect.

**⚙️ Function:** Fade-in animations provide a gentle way to introduce content to users without jarring visual changes.

**🚀 Features:**
- Opacity-based transitions
- Configurable duration and easing
- Can be triggered by state changes
- Works with any HTML element

**🔁 Flow:**
```typescript
import { Component } from '@angular/core';
import { trigger, state, style, transition, animate } from '@angular/animations';

@Component({
  selector: 'app-simple-fade',
  template: `
    <div [@fadeIn]="'in'" class="content">
      This content will fade in
    </div>
  `,
  animations: [
    trigger('fadeIn', [
      transition(':enter', [
        style({ opacity: 0 }),
        animate('500ms ease-in', style({ opacity: 1 }))
      ])
    ])
  ],
  styles: [`
    .content {
      padding: 20px;
      background: #f0f0f0;
      border-radius: 4px;
    }
  `]
})
export class SimpleFadeComponent {}
```

**🐞 Fixes:** Ensure BrowserAnimationsModule is imported, test on different devices for performance, and consider reducing animation duration for better UX.

---

### Intermediate Questions

1. What are animation metadata objects? _(Asked in Infosys)_

**🧩 Foundation:** Animation metadata objects are configuration objects that define how animations should behave, including states, transitions, and timing functions.

**⚙️ Function:** These objects provide a declarative way to define complex animation behaviors and make animations reusable across components.

**🚀 Features:**
- State definitions with styles
- Transition rules between states
- Timing and easing functions
- Reusable animation definitions
- Complex animation sequences

**🔁 Flow:**
```typescript
import { Component } from '@angular/core';
import { 
  trigger, 
  state, 
  style, 
  transition, 
  animate, 
  keyframes,
  group 
} from '@angular/animations';

@Component({
  selector: 'app-advanced-animation',
  template: `
    <div [@slideInOut]="animationState" class="animated-box">
      {{ message }}
    </div>
    <button (click)="toggleAnimation()">Toggle</button>
  `,
  animations: [
    trigger('slideInOut', [
      state('in', style({
        transform: 'translateX(0)',
        opacity: 1
      })),
      state('out', style({
        transform: 'translateX(-100%)',
        opacity: 0
      })),
      transition('out => in', [
        group([
          animate('300ms ease-out', style({ transform: 'translateX(0)' })),
          animate('200ms ease-out', style({ opacity: 1 }))
        ])
      ]),
      transition('in => out', [
        group([
          animate('300ms ease-in', style({ transform: 'translateX(-100%)' })),
          animate('200ms ease-in', style({ opacity: 0 }))
        ])
      ])
    ])
  ]
})
export class AdvancedAnimationComponent {
  animationState = 'out';
  message = 'Animated content';
  
  toggleAnimation() {
    this.animationState = this.animationState === 'in' ? 'out' : 'in';
  }
}
```

**🐞 Fixes:** Use group() for parallel animations, avoid complex animations on mobile devices, and test animation performance across different browsers.

---

2. How do you create reusable animations? _(Asked in TCS)_

**🧩 Foundation:** Reusable animations are defined in separate files and imported into components, allowing the same animation logic to be used across multiple components.

**⚙️ Function:** Reusable animations promote code reusability, maintain consistency across the application, and reduce duplication of animation code.

**🚀 Features:**
- Centralized animation definitions
- Importable animation functions
- Consistent animation behavior
- Easy maintenance and updates
- Type-safe animation parameters

**🔁 Flow:**
```typescript
// animations.ts
import { 
  trigger, 
  state, 
  style, 
  transition, 
  animate,
  AnimationTriggerMetadata 
} from '@angular/animations';

export const slideInAnimation: AnimationTriggerMetadata = trigger('slideIn', [
  transition(':enter', [
    style({ transform: 'translateX(-100%)', opacity: 0 }),
    animate('300ms ease-out', style({ transform: 'translateX(0)', opacity: 1 }))
  ]),
  transition(':leave', [
    animate('300ms ease-in', style({ transform: 'translateX(100%)', opacity: 0 }))
  ])
]);

export const fadeInOutAnimation: AnimationTriggerMetadata = trigger('fadeInOut', [
  state('in', style({ opacity: 1 })),
  state('out', style({ opacity: 0 })),
  transition('in <=> out', animate('200ms ease-in-out'))
]);

// component.ts
import { Component } from '@angular/core';
import { slideInAnimation, fadeInOutAnimation } from './animations';

@Component({
  selector: 'app-reusable-animations',
  template: `
    <div [@slideIn] class="slide-content">
      <h2 [@fadeInOut]="fadeState">Reusable Animation Example</h2>
      <p>This content uses reusable animations</p>
    </div>
  `,
  animations: [slideInAnimation, fadeInOutAnimation]
})
export class ReusableAnimationsComponent {
  fadeState = 'in';
}
```

**🐞 Fixes:** Keep animations simple and focused, use meaningful names, and consider creating animation libraries for large applications.

---

### Advanced Questions

1. How do you implement complex animation sequences? _(Asked in Cognizant)_

**🧩 Foundation:** Complex animation sequences use multiple animation functions like `group()`, `sequence()`, `query()`, and `stagger()` to create sophisticated multi-step animations.

**⚙️ Function:** Complex sequences allow for rich, engaging user experiences with coordinated animations that guide user attention and provide visual feedback.

**🚀 Features:**
- Parallel animations with `group()`
- Sequential animations with `sequence()`
- Child element targeting with `query()`
- Staggered animations with `stagger()`
- Conditional animations
- Animation callbacks

**🔁 Flow:**
```typescript
import { Component } from '@angular/core';
import { 
  trigger, 
  state, 
  style, 
  transition, 
  animate, 
  group,
  sequence,
  query,
  stagger,
  keyframes 
} from '@angular/animations';

@Component({
  selector: 'app-complex-animation',
  template: `
    <div [@complexAnimation]="animationState" class="container">
      <div class="header" [@headerAnimation]="headerState">
        <h1>Complex Animation</h1>
      </div>
      <div class="content">
        <div *ngFor="let item of items; let i = index" 
             class="item" 
             [@itemAnimation]="itemStates[i]">
          {{ item }}
        </div>
      </div>
    </div>
    <button (click)="startAnimation()">Start Animation</button>
  `,
  animations: [
    trigger('complexAnimation', [
      state('initial', style({ opacity: 0 })),
      state('animated', style({ opacity: 1 })),
      transition('initial => animated', [
        sequence([
          animate('200ms ease-out', style({ opacity: 1 })),
          query('.header', animate('300ms ease-out', style({ transform: 'translateY(0)' }))),
          query('.item', [
            stagger(100, [
              animate('200ms ease-out', style({ 
                opacity: 1, 
                transform: 'translateX(0)' 
              }))
            ])
          ])
        ])
      ])
    ]),
    trigger('headerAnimation', [
      state('hidden', style({ transform: 'translateY(-50px)', opacity: 0 })),
      state('visible', style({ transform: 'translateY(0)', opacity: 1 })),
      transition('hidden => visible', animate('300ms ease-out'))
    ]),
    trigger('itemAnimation', [
      state('hidden', style({ transform: 'translateX(-100px)', opacity: 0 })),
      state('visible', style({ transform: 'translateX(0)', opacity: 1 })),
      transition('hidden => visible', animate('200ms ease-out'))
    ])
  ]
})
export class ComplexAnimationComponent {
  animationState = 'initial';
  headerState = 'hidden';
  itemStates: string[] = [];
  items = ['Item 1', 'Item 2', 'Item 3', 'Item 4', 'Item 5'];
  
  ngOnInit() {
    this.itemStates = this.items.map(() => 'hidden');
  }
  
  startAnimation() {
    this.animationState = 'animated';
    this.headerState = 'visible';
    
    // Stagger item animations
    this.itemStates.forEach((_, index) => {
      setTimeout(() => {
        this.itemStates[index] = 'visible';
      }, index * 100);
    });
  }
}
```

**🐞 Fixes:** Test performance on mobile devices, use `will-change` CSS property for better performance, and consider reducing animation complexity for accessibility.

---

### Tough Questions

1. How do you animate a list of items using `query()` and `stagger()`? _(Asked in TCS Digital)_

**🧩 Foundation:** `query()` selects child elements and `stagger()` creates time delays between animations, allowing for coordinated list animations where items animate in sequence.

**⚙️ Function:** This technique creates engaging list animations that guide user attention and provide visual feedback for dynamic content loading.

**🚀 Features:**
- `query()` selects elements by CSS selector
- `stagger()` creates time delays between animations
- Supports complex selection patterns
- Can animate different properties per item
- Works with dynamic lists

**🔁 Flow:**
```typescript
import { Component } from '@angular/core';
import { 
  trigger, 
  transition, 
  query, 
  stagger, 
  animate, 
  style,
  group 
} from '@angular/animations';

@Component({
  selector: 'app-staggered-list',
  template: `
    <div class="list-container" [@listAnimation]="items.length">
      <div *ngFor="let item of items; let i = index" 
           class="list-item"
           [@itemAnimation]="item.visible">
        <div class="item-content">
          <h3>{{ item.title }}</h3>
          <p>{{ item.description }}</p>
        </div>
      </div>
    </div>
    <button (click)="addItem()">Add Item</button>
    <button (click)="removeItem()">Remove Item</button>
  `,
  animations: [
    trigger('listAnimation', [
      transition('* => *', [
        query(':enter', [
          style({ opacity: 0, transform: 'translateY(50px)' }),
          stagger(100, [
            animate('300ms ease-out', style({ 
              opacity: 1, 
              transform: 'translateY(0)' 
            }))
          ])
        ], { optional: true }),
        query(':leave', [
          stagger(50, [
            animate('200ms ease-in', style({ 
              opacity: 0, 
              transform: 'translateX(-100%)' 
            }))
          ])
        ], { optional: true })
      ])
    ]),
    trigger('itemAnimation', [
      transition(':enter', [
        style({ opacity: 0, transform: 'scale(0.8)' }),
        animate('200ms ease-out', style({ 
          opacity: 1, 
          transform: 'scale(1)' 
        }))
      ]),
      transition(':leave', [
        animate('150ms ease-in', style({ 
          opacity: 0, 
          transform: 'scale(0.8)' 
        }))
      ])
    ])
  ]
})
export class StaggeredListComponent {
  items: Array<{title: string, description: string, visible: boolean}> = [
    { title: 'Item 1', description: 'First item description', visible: true },
    { title: 'Item 2', description: 'Second item description', visible: true },
    { title: 'Item 3', description: 'Third item description', visible: true }
  ];
  
  addItem() {
    const newItem = {
      title: `Item ${this.items.length + 1}`,
      description: `Description for item ${this.items.length + 1}`,
      visible: true
    };
    this.items.push(newItem);
  }
  
  removeItem() {
    if (this.items.length > 0) {
      this.items.pop();
    }
  }
}
```

**🐞 Fixes:** Use `optional: true` in query to handle empty lists, test with different list sizes, and consider performance impact on large lists.

---

2. How do you test Angular animations? _(Asked in L&T Infotech)_

**🧩 Foundation:** Testing Angular animations involves testing animation states, transitions, and ensuring animations work correctly in different scenarios using Angular's testing utilities.

**⚙️ Function:** Animation testing ensures animations behave as expected, don't break functionality, and provide consistent user experience across different conditions.

**🚀 Features:**
- Testing animation states
- Testing transition triggers
- Mocking animation timing
- Testing animation callbacks
- Performance testing
- Cross-browser compatibility testing

**🔁 Flow:**
```typescript
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ComplexAnimationComponent } from './complex-animation.component';

describe('ComplexAnimationComponent', () => {
  let component: ComplexAnimationComponent;
  let fixture: ComponentFixture<ComplexAnimationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BrowserAnimationsModule],
      declarations: [ComplexAnimationComponent]
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ComplexAnimationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should start with initial animation state', () => {
    expect(component.animationState).toBe('initial');
    expect(component.headerState).toBe('hidden');
    expect(component.itemStates.every(state => state === 'hidden')).toBe(true);
  });

  it('should trigger animation sequence when startAnimation is called', fakeAsync(() => {
    const initialItemStates = [...component.itemStates];
    
    component.startAnimation();
    tick(0);
    
    expect(component.animationState).toBe('animated');
    expect(component.headerState).toBe('visible');
    
    // Test staggered item animations
    tick(100);
    expect(component.itemStates[0]).toBe('visible');
    expect(component.itemStates[1]).toBe('hidden');
    
    tick(100);
    expect(component.itemStates[1]).toBe('visible');
    expect(component.itemStates[2]).toBe('hidden');
    
    tick(300);
    expect(component.itemStates.every(state => state === 'visible')).toBe(true);
  }));

  it('should handle animation performance', fakeAsync(() => {
    const startTime = performance.now();
    
    component.startAnimation();
    tick(1000); // Complete all animations
    
    const endTime = performance.now();
    const animationDuration = endTime - startTime;
    
    // Animation should complete within reasonable time
    expect(animationDuration).toBeLessThan(2000);
  }));

  it('should clean up animations on destroy', () => {
    component.startAnimation();
    const element = fixture.nativeElement;
    
    fixture.destroy();
    
    // Ensure no animation-related classes remain
    expect(element.querySelector('.animated')).toBeNull();
  });
});

// Animation utility testing
describe('Animation Utilities', () => {
  it('should create reusable animations', () => {
    const slideInAnimation = trigger('testSlide', [
      transition(':enter', [
        style({ transform: 'translateX(-100%)' }),
        animate('300ms ease-out', style({ transform: 'translateX(0)' }))
      ])
    ]);
    
    expect(slideInAnimation.name).toBe('testSlide');
    expect(slideInAnimation.definitions.length).toBeGreaterThan(0);
  });

  it('should handle animation state changes', fakeAsync(() => {
    const component = new ComplexAnimationComponent();
    
    // Test state transitions
    expect(component.animationState).toBe('initial');
    
    component.startAnimation();
    tick(0);
    
    expect(component.animationState).toBe('animated');
  }));
});
```

**🐞 Fixes:** Use `fakeAsync` and `tick` for timing control, test edge cases like rapid state changes, and ensure animations don't interfere with component functionality.

---

### Situational / Real-world Questions

1. You need to create a loading animation for a data table that shows skeleton screens while data loads. How would you implement this? _(Asked in Deloitte)_

**🧩 Foundation:** Skeleton loading animations use placeholder elements with subtle animations to indicate content is loading, providing better UX than traditional spinners.

**⚙️ Function:** Skeleton animations reduce perceived loading time and provide visual feedback that content is being prepared, improving user experience.

**🚀 Features:**
- Animated placeholder elements
- Pulse or shimmer effects
- Responsive skeleton layouts
- Smooth transition to real content
- Accessibility considerations

**🔁 Flow:**
```typescript
import { Component, Input } from '@angular/core';
import { trigger, state, style, transition, animate, keyframes } from '@angular/animations';

@Component({
  selector: 'app-skeleton-loader',
  template: `
    <div class="skeleton-container" [@skeletonAnimation]="isLoading ? 'loading' : 'loaded'">
      <div *ngIf="isLoading" class="skeleton-content">
        <div class="skeleton-header">
          <div class="skeleton-title"></div>
          <div class="skeleton-subtitle"></div>
        </div>
        <div class="skeleton-table">
          <div *ngFor="let row of skeletonRows" class="skeleton-row">
            <div *ngFor="let cell of skeletonCells" class="skeleton-cell"></div>
          </div>
        </div>
      </div>
      <div *ngIf="!isLoading" class="real-content" [@contentAnimation]="'visible'">
        <ng-content></ng-content>
      </div>
    </div>
  `,
  animations: [
    trigger('skeletonAnimation', [
      state('loading', style({ opacity: 1 })),
      state('loaded', style({ opacity: 0 })),
      transition('loading => loaded', [
        animate('300ms ease-out')
      ])
    ]),
    trigger('contentAnimation', [
      transition(':enter', [
        style({ opacity: 0, transform: 'translateY(20px)' }),
        animate('400ms ease-out', style({ 
          opacity: 1, 
          transform: 'translateY(0)' 
        }))
      ])
    ])
  ],
  styles: [`
    .skeleton-container {
      position: relative;
    }
    
    .skeleton-content {
      animation: pulse 1.5s ease-in-out infinite;
    }
    
    .skeleton-title {
      height: 24px;
      background: linear-gradient(90deg, #f0f0f0 25%, #e0e0e0 50%, #f0f0f0 75%);
      background-size: 200% 100%;
      border-radius: 4px;
      margin-bottom: 8px;
      animation: shimmer 2s infinite;
    }
    
    .skeleton-subtitle {
      height: 16px;
      width: 60%;
      background: linear-gradient(90deg, #f0f0f0 25%, #e0e0e0 50%, #f0f0f0 75%);
      background-size: 200% 100%;
      border-radius: 4px;
      margin-bottom: 20px;
      animation: shimmer 2s infinite;
    }
    
    .skeleton-table {
      border: 1px solid #e0e0e0;
      border-radius: 4px;
    }
    
    .skeleton-row {
      display: flex;
      border-bottom: 1px solid #e0e0e0;
    }
    
    .skeleton-row:last-child {
      border-bottom: none;
    }
    
    .skeleton-cell {
      flex: 1;
      height: 40px;
      background: linear-gradient(90deg, #f0f0f0 25%, #e0e0e0 50%, #f0f0f0 75%);
      background-size: 200% 100%;
      animation: shimmer 2s infinite;
      margin: 4px;
      border-radius: 2px;
    }
    
    @keyframes shimmer {
      0% { background-position: -200% 0; }
      100% { background-position: 200% 0; }
    }
    
    @keyframes pulse {
      0%, 100% { opacity: 1; }
      50% { opacity: 0.8; }
    }
  `]
})
export class SkeletonLoaderComponent {
  @Input() isLoading = true;
  @Input() skeletonRows = 5;
  @Input() skeletonCells = 4;
}

// Usage in parent component
@Component({
  selector: 'app-data-table',
  template: `
    <app-skeleton-loader [isLoading]="isLoading">
      <table class="data-table">
        <thead>
          <tr>
            <th *ngFor="let column of columns">{{ column }}</th>
          </tr>
        </thead>
        <tbody>
          <tr *ngFor="let row of data">
            <td *ngFor="let column of columns">{{ row[column] }}</td>
          </tr>
        </tbody>
      </table>
    </app-skeleton-loader>
  `
})
export class DataTableComponent {
  isLoading = true;
  data: any[] = [];
  columns = ['Name', 'Email', 'Role', 'Status'];
  
  ngOnInit() {
    this.loadData();
  }
  
  loadData() {
    this.isLoading = true;
    this.dataService.getData().subscribe(data => {
      this.data = data;
      this.isLoading = false;
    });
  }
}
```

**🐞 Fixes:** Ensure skeleton matches real content layout, use appropriate animation timing, and provide fallback for users who prefer reduced motion.

---

## 14. Internationalization (i18n)

### Basic Questions

1. What is internationalization (i18n) in Angular and why is it important? _(Asked in Infosys)_

**🧩 Foundation:** Internationalization (i18n) in Angular is the process of making applications ready for multiple languages and locales, including text translation, date/number formatting, and cultural adaptations.

**⚙️ Function:** i18n enables applications to reach global audiences by adapting content, formatting, and user experience to different languages and cultural preferences.

**🚀 Features:**
- Text translation with i18n attributes
- Date, time, and number formatting
- Currency and locale-specific formatting
- Pluralization rules
- Right-to-left (RTL) language support
- AOT compilation for translations

**🔁 Flow:**
```typescript
import { Component } from '@angular/core';
import { registerLocaleData } from '@angular/common';
import localeFr from '@angular/common/locales/fr';
import localeEs from '@angular/common/locales/es';

registerLocaleData(localeFr, 'fr');
registerLocaleData(localeEs, 'es');

@Component({
  selector: 'app-i18n-example',
  template: `
    <div>
      <h1 i18n="@@welcome.title">Welcome to our application</h1>
      <p i18n="@@welcome.message">This is a sample internationalized application</p>
      
      <div>
        <p>{{ today | date:'fullDate' }}</p>
        <p>{{ amount | currency:'USD' }}</p>
        <p>{{ count | i18nPlural:messageMapping }}</p>
      </div>
      
      <button (click)="changeLanguage('en')">English</button>
      <button (click)="changeLanguage('fr')">Français</button>
      <button (click)="changeLanguage('es')">Español</button>
    </div>
  `
})
export class I18nExampleComponent {
  today = new Date();
  amount = 1234.56;
  count = 1;
  
  messageMapping = {
    '=0': 'No items',
    '=1': 'One item',
    'other': '# items'
  };
  
  changeLanguage(locale: string) {
    // Implementation for language switching
  }
}
```

**🐞 Fixes:** Common issues include forgetting to register locales, missing translation keys, and not handling pluralization correctly for different languages.

---

2. How do you mark text for translation in Angular templates? _(Asked in TCS)_

**🧩 Foundation:** Angular uses the `i18n` attribute to mark text for translation, with optional meaning and description parameters to provide context for translators.

**⚙️ Function:** The i18n attribute tells Angular's extraction tool which text needs translation and provides context for accurate translations.

**🚀 Features:**
- Basic i18n attribute for simple text
- Meaning and description parameters
- ICU expressions for complex translations
- Pluralization support
- Gender-specific translations

**🔁 Flow:**
```typescript
import { Component } from '@angular/core';

@Component({
  selector: 'app-translation-examples',
  template: `
    <!-- Basic translation -->
    <h1 i18n="@@page.title">Welcome to our application</h1>
    
    <!-- With meaning and description -->
    <p i18n="@@welcome.message|Welcome message for new users@@Welcome! We're glad to see you here.">
      Welcome! We're glad to see you here.
    </p>
    
    <!-- ICU expressions for pluralization -->
    <p i18n="@@items.count|Number of items@@{count, plural, =0 {No items} =1 {One item} other {# items}}">
      {count, plural, =0 {No items} =1 {One item} other {# items}}
    </p>
    
    <!-- ICU expressions for gender -->
    <p i18n="@@user.greeting|Greeting based on user gender@@{gender, select, male {Hello sir} female {Hello madam} other {Hello}}">
      {gender, select, male {Hello sir} female {Hello madam} other {Hello}}
    </p>
    
    <!-- ICU expressions for date formatting -->
    <p i18n="@@date.formatted|Formatted date@@{date, date, fullDate}">
      {date, date, fullDate}
    </p>
    
    <!-- ICU expressions for number formatting -->
    <p i18n="@@number.formatted|Formatted number@@{amount, number, currency}">
      {amount, number, currency}
    </p>
  `
})
export class TranslationExamplesComponent {
  count = 5;
  gender = 'male';
  date = new Date();
  amount = 1234.56;
}
```

**🐞 Fixes:** Always provide meaningful IDs, include context for translators, and test translations with different text lengths to avoid layout issues.

---

### Intermediate Questions

1. How do you configure and build an Angular application for multiple locales? _(Asked in Capgemini)_

**🧩 Foundation:** Angular CLI provides built-in support for building applications with multiple locales using the `--configuration` flag and locale-specific build configurations.

**⚙️ Function:** Multi-locale builds create separate bundles for each language, optimizing bundle size and enabling efficient language switching.

**🚀 Features:**
- Locale-specific build configurations
- Separate bundles per language
- Optimized bundle sizes
- Runtime language switching
- AOT compilation for translations

**🔁 Flow:**
```json
// angular.json
{
  "projects": {
    "my-app": {
      "architect": {
        "build": {
          "configurations": {
            "production": {
              "optimization": true,
              "outputHashing": "all",
              "sourceMap": false,
              "extractCss": true,
              "namedChunks": false,
              "aot": true,
              "extractLicenses": true,
              "vendorChunk": false,
              "buildOptimizer": true
            },
            "production-en": {
              "fileReplacements": [
                {
                  "replace": "src/environments/environment.ts",
                  "with": "src/environments/environment.prod.ts"
                }
              ],
              "optimization": true,
              "outputHashing": "all",
              "sourceMap": false,
              "extractCss": true,
              "namedChunks": false,
              "aot": true,
              "extractLicenses": true,
              "vendorChunk": false,
              "buildOptimizer": true,
              "budgets": [
                {
                  "type": "initial",
                  "maximumWarning": "2mb",
                  "maximumError": "5mb"
                }
              ]
            },
            "production-fr": {
              "fileReplacements": [
                {
                  "replace": "src/environments/environment.ts",
                  "with": "src/environments/environment.prod.ts"
                }
              ],
              "optimization": true,
              "outputHashing": "all",
              "sourceMap": false,
              "extractCss": true,
              "namedChunks": false,
              "aot": true,
              "extractLicenses": true,
              "vendorChunk": false,
              "buildOptimizer": true,
              "budgets": [
                {
                  "type": "initial",
                  "maximumWarning": "2mb",
                  "maximumError": "5mb"
                }
              ]
            }
          }
        },
        "serve": {
          "configurations": {
            "production-en": {
              "browserTarget": "my-app:build:production-en"
            },
            "production-fr": {
              "browserTarget": "my-app:build:production-fr"
            }
          }
        }
      }
    }
  }
}
```

```typescript
// main.ts
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { AppModule } from './app/app.module';
import { environment } from './environments/environment';

if (environment.production) {
  enableProdMode();
}

platformBrowserDynamic().bootstrapModule(AppModule)
  .catch(err => console.log(err));
```

```bash
# Build commands for different locales
ng build --configuration=production-en --output-path=dist/en
ng build --configuration=production-fr --output-path=dist/fr
ng build --configuration=production-es --output-path=dist/es

# Serve with specific locale
ng serve --configuration=production-en
```

**🐞 Fixes:** Ensure all locales are properly configured, test builds for each locale, and verify that translations are correctly extracted and compiled.

---

2. How do you implement runtime language switching? _(Asked in Wipro)_

**🧩 Foundation:** Runtime language switching involves dynamically changing the application locale and reloading translations without rebuilding the application.

**⚙️ Function:** Runtime switching provides better user experience by allowing immediate language changes without page reloads or application restarts.

**🚀 Features:**
- Dynamic locale switching
- Translation service integration
- URL-based language routing
- Browser language detection
- Persistent language preferences

**🔁 Flow:**
```typescript
import { Injectable, Inject, LOCALE_ID } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';

export interface Translation {
  [key: string]: string;
}

@Injectable({
  providedIn: 'root'
})
export class TranslationService {
  private currentLocale = new BehaviorSubject<string>('en');
  private translations = new BehaviorSubject<Translation>({});
  
  constructor(
    private http: HttpClient,
    @Inject(LOCALE_ID) private locale: string
  ) {
    this.currentLocale.next(this.locale);
    this.loadTranslations(this.locale);
  }
  
  getCurrentLocale(): Observable<string> {
    return this.currentLocale.asObservable();
  }
  
  getTranslations(): Observable<Translation> {
    return this.translations.asObservable();
  }
  
  switchLanguage(locale: string): void {
    this.currentLocale.next(locale);
    this.loadTranslations(locale);
    localStorage.setItem('preferredLanguage', locale);
  }
  
  private loadTranslations(locale: string): void {
    this.http.get<Translation>(`/assets/i18n/${locale}.json`)
      .subscribe(translations => {
        this.translations.next(translations);
      });
  }
  
  translate(key: string): Observable<string> {
    return this.translations.pipe(
      map(translations => translations[key] || key)
    );
  }
}

// Language switcher component
@Component({
  selector: 'app-language-switcher',
  template: `
    <div class="language-switcher">
      <button 
        *ngFor="let lang of availableLanguages" 
        (click)="switchLanguage(lang.code)"
        [class.active]="lang.code === currentLocale"
        class="lang-btn">
        {{ lang.name }}
      </button>
    </div>
  `,
  styles: [`
    .language-switcher {
      display: flex;
      gap: 10px;
    }
    .lang-btn {
      padding: 8px 16px;
      border: 1px solid #ddd;
      background: white;
      cursor: pointer;
      border-radius: 4px;
    }
    .lang-btn.active {
      background: #007bff;
      color: white;
      border-color: #007bff;
    }
  `]
})
export class LanguageSwitcherComponent {
  currentLocale = 'en';
  availableLanguages = [
    { code: 'en', name: 'English' },
    { code: 'fr', name: 'Français' },
    { code: 'es', name: 'Español' },
    { code: 'de', name: 'Deutsch' }
  ];
  
  constructor(private translationService: TranslationService) {
    this.translationService.getCurrentLocale().subscribe(locale => {
      this.currentLocale = locale;
    });
  }
  
  switchLanguage(locale: string): void {
    this.translationService.switchLanguage(locale);
  }
}

// App component with language switching
@Component({
  selector: 'app-root',
  template: `
    <app-language-switcher></app-language-switcher>
    <div class="content">
      <h1>{{ 'welcome.title' | translate }}</h1>
      <p>{{ 'welcome.message' | translate }}</p>
    </div>
  `
})
export class AppComponent {
  constructor(private translationService: TranslationService) {}
}

// Custom translate pipe
@Pipe({
  name: 'translate',
  pure: false
})
export class TranslatePipe implements PipeTransform {
  constructor(private translationService: TranslationService) {}
  
  transform(key: string): string {
    let translation = '';
    this.translationService.getTranslations().subscribe(translations => {
      translation = translations[key] || key;
    });
    return translation;
  }
}
```

**🐞 Fixes:** Handle missing translations gracefully, implement fallback languages, and ensure proper cleanup of subscriptions to prevent memory leaks.

---

### Advanced Questions

1. How would you build a custom translation loader for remote translation files? _(Asked in Cognizant)_

**🧩 Foundation:** A custom translation loader fetches translation files from remote sources (APIs, CDNs) and integrates with Angular's i18n system for dynamic translation loading.

**⚙️ Function:** Custom loaders enable dynamic translation management, A/B testing of translations, and centralized translation management systems.

**🚀 Features:**
- HTTP-based translation loading
- Caching mechanisms
- Fallback strategies
- Version control for translations
- Dynamic translation updates

**🔁 Flow:**
```typescript
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of, throwError } from 'rxjs';
import { catchError, map, shareReplay } from 'rxjs/operators';

export interface TranslationData {
  locale: string;
  translations: { [key: string]: string };
  version: string;
  lastModified: string;
}

@Injectable({
  providedIn: 'root'
})
export class RemoteTranslationLoader {
  private cache = new Map<string, Observable<TranslationData>>();
  private readonly CACHE_DURATION = 5 * 60 * 1000; // 5 minutes
  
  constructor(private http: HttpClient) {}
  
  loadTranslations(locale: string): Observable<TranslationData> {
    // Check cache first
    const cached = this.cache.get(locale);
    if (cached) {
      return cached;
    }
    
    // Load from remote source
    const remoteLoad = this.http.get<TranslationData>(`/api/translations/${locale}`)
      .pipe(
        map(data => this.validateTranslationData(data)),
        catchError(error => this.handleTranslationError(error, locale)),
        shareReplay(1)
      );
    
    this.cache.set(locale, remoteLoad);
    
    // Clear cache after duration
    setTimeout(() => {
      this.cache.delete(locale);
    }, this.CACHE_DURATION);
    
    return remoteLoad;
  }
  
  private validateTranslationData(data: any): TranslationData {
    if (!data.locale || !data.translations) {
      throw new Error('Invalid translation data format');
    }
    return data;
  }
  
  private handleTranslationError(error: any, locale: string): Observable<TranslationData> {
    console.error(`Failed to load translations for ${locale}:`, error);
    
    // Return fallback translations
    return of({
      locale,
      translations: this.getFallbackTranslations(locale),
      version: 'fallback',
      lastModified: new Date().toISOString()
    });
  }
  
  private getFallbackTranslations(locale: string): { [key: string]: string } {
    const fallbacks: { [key: string]: { [key: string]: string } } = {
      'en': {
        'welcome.title': 'Welcome',
        'welcome.message': 'Welcome to our application',
        'error.loading': 'Error loading content'
      },
      'fr': {
        'welcome.title': 'Bienvenue',
        'welcome.message': 'Bienvenue dans notre application',
        'error.loading': 'Erreur de chargement du contenu'
      }
    };
    
    return fallbacks[locale] || fallbacks['en'];
  }
  
  clearCache(): void {
    this.cache.clear();
  }
  
  preloadTranslations(locales: string[]): Observable<TranslationData[]> {
    const loadPromises = locales.map(locale => 
      this.loadTranslations(locale).toPromise()
    );
    
    return new Observable(observer => {
      Promise.all(loadPromises)
        .then(results => {
          observer.next(results);
          observer.complete();
        })
        .catch(error => {
          observer.error(error);
        });
    });
  }
}

// Enhanced translation service with remote loader
@Injectable({
  providedIn: 'root'
})
export class EnhancedTranslationService {
  private currentLocale = new BehaviorSubject<string>('en');
  private translations = new BehaviorSubject<{ [key: string]: string }>({});
  private loading = new BehaviorSubject<boolean>(false);
  
  constructor(private remoteLoader: RemoteTranslationLoader) {
    this.initializeTranslations();
  }
  
  private initializeTranslations(): void {
    const savedLocale = localStorage.getItem('preferredLanguage') || 'en';
    this.switchLanguage(savedLocale);
  }
  
  switchLanguage(locale: string): void {
    this.loading.next(true);
    
    this.remoteLoader.loadTranslations(locale)
      .subscribe({
        next: (data) => {
          this.currentLocale.next(locale);
          this.translations.next(data.translations);
          this.loading.next(false);
          localStorage.setItem('preferredLanguage', locale);
        },
        error: (error) => {
          console.error('Translation loading failed:', error);
          this.loading.next(false);
        }
      });
  }
  
  getCurrentLocale(): Observable<string> {
    return this.currentLocale.asObservable();
  }
  
  getTranslations(): Observable<{ [key: string]: string }> {
    return this.translations.asObservable();
  }
  
  isLoading(): Observable<boolean> {
    return this.loading.asObservable();
  }
  
  translate(key: string): Observable<string> {
    return this.translations.pipe(
      map(translations => translations[key] || key)
    );
  }
  
  preloadLanguages(locales: string[]): void {
    this.remoteLoader.preloadTranslations(locales).subscribe();
  }
}

// Usage in app module
@NgModule({
  imports: [
    HttpClientModule,
    // ... other imports
  ],
  providers: [
    RemoteTranslationLoader,
    EnhancedTranslationService
  ]
})
export class AppModule {}
```

**🐞 Fixes:** Implement proper error handling, use caching to reduce API calls, and provide fallback translations for offline scenarios.

---

### Tough Questions

1. You need to support right-to-left (RTL) layouts in your Angular app. How would you approach this? _(Asked in Deloitte)_

**🧩 Foundation:** RTL support involves adapting layouts, text direction, and user interface elements for languages that read from right to left, such as Arabic, Hebrew, and Persian.

**⚙️ Function:** RTL support ensures proper user experience for users in RTL language regions, including correct text flow, layout direction, and cultural adaptations.

**🚀 Features:**
- CSS direction property
- Angular CDK layout direction
- RTL-aware components
- Icon and image mirroring
- Number formatting for RTL languages
- Bi-directional text support

**🔁 Flow:**
```typescript
import { Component, OnInit, Inject, LOCALE_ID } from '@angular/core';
import { Directionality } from '@angular/cdk/bidi';
import { BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-rtl-support',
  template: `
    <div [dir]="textDirection" class="app-container">
      <header class="app-header">
        <h1>{{ 'app.title' | translate }}</h1>
        <app-language-switcher (languageChange)="onLanguageChange($event)"></app-language-switcher>
      </header>
      
      <main class="app-main">
        <div class="content-section">
          <h2>{{ 'content.title' | translate }}</h2>
          <p>{{ 'content.description' | translate }}</p>
          
          <div class="form-section">
            <form [formGroup]="userForm" class="user-form">
              <div class="form-row">
                <label for="firstName">{{ 'form.firstName' | translate }}</label>
                <input 
                  id="firstName" 
                  type="text" 
                  formControlName="firstName"
                  [placeholder]="'form.firstNamePlaceholder' | translate">
              </div>
              
              <div class="form-row">
                <label for="lastName">{{ 'form.lastName' | translate }}</label>
                <input 
                  id="lastName" 
                  type="text" 
                  formControlName="lastName"
                  [placeholder]="'form.lastNamePlaceholder' | translate">
              </div>
              
              <div class="form-row">
                <label for="email">{{ 'form.email' | translate }}</label>
                <input 
                  id="email" 
                  type="email" 
                  formControlName="email"
                  [placeholder]="'form.emailPlaceholder' | translate">
              </div>
              
              <button type="submit" class="submit-btn">
                {{ 'form.submit' | translate }}
              </button>
            </form>
          </div>
        </div>
        
        <div class="sidebar" [class.rtl-sidebar]="isRTL">
          <h3>{{ 'sidebar.title' | translate }}</h3>
          <ul class="sidebar-menu">
            <li *ngFor="let item of sidebarItems">
              <a [href]="item.link">{{ item.label | translate }}</a>
            </li>
          </ul>
        </div>
      </main>
    </div>
  `,
  styles: [`
    .app-container {
      min-height: 100vh;
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    }
    
    .app-header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: 1rem 2rem;
      background: #f8f9fa;
      border-bottom: 1px solid #dee2e6;
    }
    
    .app-main {
      display: flex;
      padding: 2rem;
      gap: 2rem;
    }
    
    .content-section {
      flex: 1;
    }
    
    .sidebar {
      width: 250px;
      background: #f8f9fa;
      padding: 1rem;
      border-radius: 8px;
    }
    
    .rtl-sidebar {
      order: -1; /* Move sidebar to the left in RTL */
    }
    
    .user-form {
      max-width: 500px;
    }
    
    .form-row {
      margin-bottom: 1rem;
    }
    
    .form-row label {
      display: block;
      margin-bottom: 0.5rem;
      font-weight: 500;
    }
    
    .form-row input {
      width: 100%;
      padding: 0.75rem;
      border: 1px solid #ced4da;
      border-radius: 4px;
      font-size: 1rem;
    }
    
    .submit-btn {
      background: #007bff;
      color: white;
      padding: 0.75rem 1.5rem;
      border: none;
      border-radius: 4px;
      cursor: pointer;
      font-size: 1rem;
    }
    
    .submit-btn:hover {
      background: #0056b3;
    }
    
    .sidebar-menu {
      list-style: none;
      padding: 0;
      margin: 0;
    }
    
    .sidebar-menu li {
      margin-bottom: 0.5rem;
    }
    
    .sidebar-menu a {
      display: block;
      padding: 0.5rem;
      text-decoration: none;
      color: #333;
      border-radius: 4px;
    }
    
    .sidebar-menu a:hover {
      background: #e9ecef;
    }
    
    /* RTL-specific styles */
    [dir="rtl"] .app-header {
      flex-direction: row-reverse;
    }
    
    [dir="rtl"] .form-row input {
      text-align: right;
    }
    
    [dir="rtl"] .sidebar-menu a {
      text-align: right;
    }
  `]
})
export class RtlSupportComponent implements OnInit {
  textDirection: 'ltr' | 'rtl' = 'ltr';
  isRTL = false;
  userForm: FormGroup;
  
  sidebarItems = [
    { label: 'sidebar.home', link: '/home' },
    { label: 'sidebar.profile', link: '/profile' },
    { label: 'sidebar.settings', link: '/settings' },
    { label: 'sidebar.help', link: '/help' }
  ];
  
  rtlLanguages = ['ar', 'he', 'fa', 'ur', 'ps'];
  
  constructor(
    private formBuilder: FormBuilder,
    private translationService: EnhancedTranslationService,
    @Inject(LOCALE_ID) private locale: string
  ) {
    this.userForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]]
    });
  }
  
  ngOnInit(): void {
    this.setDirection(this.locale);
    
    this.translationService.getCurrentLocale().subscribe(locale => {
      this.setDirection(locale);
    });
  }
  
  private setDirection(locale: string): void {
    this.isRTL = this.rtlLanguages.includes(locale);
    this.textDirection = this.isRTL ? 'rtl' : 'ltr';
    
    // Update document direction
    document.documentElement.dir = this.textDirection;
    document.documentElement.lang = locale;
  }
  
  onLanguageChange(locale: string): void {
    this.translationService.switchLanguage(locale);
  }
}

// RTL-aware service
@Injectable({
  providedIn: 'root'
})
export class RtlService {
  private direction = new BehaviorSubject<'ltr' | 'rtl'>('ltr');
  
  constructor() {
    this.detectInitialDirection();
  }
  
  private detectInitialDirection(): void {
    const savedLocale = localStorage.getItem('preferredLanguage') || 'en';
    const rtlLanguages = ['ar', 'he', 'fa', 'ur', 'ps'];
    const isRTL = rtlLanguages.includes(savedLocale);
    this.direction.next(isRTL ? 'rtl' : 'ltr');
  }
  
  getDirection(): Observable<'ltr' | 'rtl'> {
    return this.direction.asObservable();
  }
  
  setDirection(direction: 'ltr' | 'rtl'): void {
    this.direction.next(direction);
    document.documentElement.dir = direction;
  }
  
  isRTL(): Observable<boolean> {
    return this.direction.pipe(
      map(dir => dir === 'rtl')
    );
  }
  
  // Utility method for mirroring icons
  getIconTransform(isRTL: boolean): string {
    return isRTL ? 'scaleX(-1)' : 'none';
  }
  
  // Utility method for number formatting
  formatNumber(value: number, locale: string): string {
    const rtlLanguages = ['ar', 'he', 'fa', 'ur', 'ps'];
    const isRTL = rtlLanguages.includes(locale);
    
    if (isRTL) {
      // Use Arabic-Indic numerals for RTL languages
      return value.toLocaleString('ar-EG');
    }
    
    return value.toLocaleString(locale);
  }
}

// RTL-aware pipe
@Pipe({
  name: 'rtlNumber'
})
export class RtlNumberPipe implements PipeTransform {
  constructor(private rtlService: RtlService) {}
  
  transform(value: number, locale: string): string {
    return this.rtlService.formatNumber(value, locale);
  }
}
```

**🐞 Fixes:** Test with actual RTL content, ensure proper icon mirroring, handle mixed content (LTR text in RTL layout), and validate number formatting for different locales.

---

### Situational / Real-world Questions

1. Your application needs to support 15 different languages with dynamic content updates. How would you design the i18n architecture? _(Asked in Accenture)_

**🧩 Foundation:** A scalable i18n architecture for multiple languages requires centralized translation management, efficient loading strategies, and robust fallback mechanisms.

**⚙️ Function:** This architecture ensures consistent translations across languages, efficient resource management, and seamless user experience regardless of language choice.

**🚀 Features:**
- Centralized translation API
- Lazy loading of translation files
- Intelligent caching strategies
- Fallback language chains
- Translation versioning
- A/B testing support for translations

**🔁 Flow:**
```typescript
// Translation management service
@Injectable({
  providedIn: 'root'
})
export class TranslationManagementService {
  private readonly SUPPORTED_LOCALES = [
    'en', 'es', 'fr', 'de', 'it', 'pt', 'ru', 'ja', 'ko', 'zh',
    'ar', 'he', 'hi', 'th', 'vi'
  ];
  
  private readonly FALLBACK_CHAIN = {
    'en': ['en'],
    'es': ['es', 'en'],
    'fr': ['fr', 'en'],
    'de': ['de', 'en'],
    'ar': ['ar', 'en'],
    'he': ['he', 'en'],
    'hi': ['hi', 'en'],
    'th': ['th', 'en'],
    'vi': ['vi', 'en']
  };
  
  private translationCache = new Map<string, TranslationData>();
  private loadingStates = new Map<string, BehaviorSubject<boolean>>();
  private translationVersions = new Map<string, string>();
  
  constructor(
    private http: HttpClient,
    private storage: StorageService
  ) {}
  
  loadTranslations(locale: string): Observable<TranslationData> {
    const fallbackChain = this.FALLBACK_CHAIN[locale] || ['en'];
    
    return this.loadWithFallback(fallbackChain);
  }
  
  private loadWithFallback(fallbackChain: string[]): Observable<TranslationData> {
    return new Observable(observer => {
      this.tryLoadFromChain(fallbackChain, 0, observer);
    });
  }
  
  private tryLoadFromChain(
    chain: string[], 
    index: number, 
    observer: Observer<TranslationData>
  ): void {
    if (index >= chain.length) {
      observer.error(new Error('No translations available'));
      return;
    }
    
    const locale = chain[index];
    const cached = this.translationCache.get(locale);
    
    if (cached) {
      observer.next(cached);
      observer.complete();
      return;
    }
    
    this.loadFromRemote(locale).subscribe({
      next: (data) => {
        this.translationCache.set(locale, data);
        observer.next(data);
        observer.complete();
      },
      error: (error) => {
        console.warn(`Failed to load ${locale}, trying next in chain`);
        this.tryLoadFromChain(chain, index + 1, observer);
      }
    });
  }
  
  private loadFromRemote(locale: string): Observable<TranslationData> {
    const version = this.translationVersions.get(locale) || 'latest';
    const url = `/api/translations/${locale}?version=${version}`;
    
    return this.http.get<TranslationData>(url).pipe(
      catchError(error => {
        console.error(`Failed to load translations for ${locale}:`, error);
        return throwError(error);
      })
    );
  }
  
  preloadCriticalLanguages(): Observable<void> {
    const criticalLocales = ['en', 'es', 'fr', 'de'];
    const loadPromises = criticalLocales.map(locale => 
      this.loadTranslations(locale).toPromise()
    );
    
    return new Observable(observer => {
      Promise.all(loadPromises)
        .then(() => {
          observer.next();
          observer.complete();
        })
        .catch(error => {
          observer.error(error);
        });
    });
  }
  
  updateTranslationVersion(locale: string, version: string): void {
    this.translationVersions.set(locale, version);
    this.translationCache.delete(locale); // Clear cache to force reload
  }
  
  getTranslationStats(): Observable<TranslationStats> {
    return this.http.get<TranslationStats>('/api/translations/stats');
  }
}

// Enhanced translation service with management
@Injectable({
  providedIn: 'root'
})
export class EnhancedTranslationService {
  private currentLocale = new BehaviorSubject<string>('en');
  private translations = new BehaviorSubject<{ [key: string]: string }>({});
  private loading = new BehaviorSubject<boolean>(false);
  private translationStats = new BehaviorSubject<TranslationStats | null>(null);
  
  constructor(
    private managementService: TranslationManagementService,
    private rtlService: RtlService
  ) {
    this.initializeService();
  }
  
  private initializeService(): void {
    const savedLocale = this.getPreferredLanguage();
    this.switchLanguage(savedLocale);
    
    // Preload critical languages
    this.managementService.preloadCriticalLanguages().subscribe();
    
    // Load translation statistics
    this.loadTranslationStats();
  }
  
  private getPreferredLanguage(): string {
    const saved = localStorage.getItem('preferredLanguage');
    if (saved) return saved;
    
    const browserLang = navigator.language.split('-')[0];
    const supported = ['en', 'es', 'fr', 'de', 'ar', 'he', 'hi', 'th', 'vi'];
    return supported.includes(browserLang) ? browserLang : 'en';
  }
  
  switchLanguage(locale: string): void {
    this.loading.next(true);
    
    this.managementService.loadTranslations(locale)
      .subscribe({
        next: (data) => {
          this.currentLocale.next(locale);
          this.translations.next(data.translations);
          this.loading.next(false);
          
          // Update RTL direction
          this.rtlService.setDirection(this.isRTL(locale) ? 'rtl' : 'ltr');
          
          // Save preference
          localStorage.setItem('preferredLanguage', locale);
        },
        error: (error) => {
          console.error('Language switch failed:', error);
          this.loading.next(false);
        }
      });
  }
  
  private isRTL(locale: string): boolean {
    const rtlLanguages = ['ar', 'he', 'fa', 'ur', 'ps'];
    return rtlLanguages.includes(locale);
  }
  
  private loadTranslationStats(): void {
    this.managementService.getTranslationStats()
      .subscribe(stats => {
        this.translationStats.next(stats);
      });
  }
  
  getCurrentLocale(): Observable<string> {
    return this.currentLocale.asObservable();
  }
  
  getTranslations(): Observable<{ [key: string]: string }> {
    return this.translations.asObservable();
  }
  
  isLoading(): Observable<boolean> {
    return this.loading.asObservable();
  }
  
  getStats(): Observable<TranslationStats | null> {
    return this.translationStats.asObservable();
  }
  
  translate(key: string): Observable<string> {
    return this.translations.pipe(
      map(translations => translations[key] || key)
    );
  }
  
  // A/B testing for translations
  translateWithVariant(key: string, variant: string): Observable<string> {
    return this.translations.pipe(
      map(translations => {
        const variantKey = `${key}.${variant}`;
        return translations[variantKey] || translations[key] || key;
      })
    );
  }
}

// Translation statistics interface
interface TranslationStats {
  totalKeys: number;
  translatedKeys: { [locale: string]: number };
  completionPercentage: { [locale: string]: number };
  lastUpdated: { [locale: string]: string };
  missingKeys: { [locale: string]: string[] };
}

// App initialization with translation preloading
@Component({
  selector: 'app-root',
  template: `
    <div class="app-container" [dir]="textDirection">
      <app-loading-spinner *ngIf="isLoading"></app-loading-spinner>
      
      <div *ngIf="!isLoading" class="app-content">
        <app-language-switcher></app-language-switcher>
        <router-outlet></router-outlet>
      </div>
    </div>
  `
})
export class AppComponent implements OnInit {
  isLoading = true;
  textDirection: 'ltr' | 'rtl' = 'ltr';
  
  constructor(
    private translationService: EnhancedTranslationService,
    private rtlService: RtlService
  ) {}
  
  ngOnInit(): void {
    // Wait for initial translation load
    this.translationService.isLoading().subscribe(loading => {
      this.isLoading = loading;
    });
    
    // Set up RTL direction
    this.rtlService.getDirection().subscribe(direction => {
      this.textDirection = direction;
    });
  }
}
```

**🐞 Fixes:** Implement proper error boundaries, use service workers for offline translation caching, and establish clear translation management workflows for content teams.

---

## 15. Performance Optimization

### Basic Questions

1. What are some common causes of performance issues in Angular apps? _(Asked in Infosys)_

**🧩 Foundation:** Angular performance issues typically stem from inefficient change detection, large bundle sizes, memory leaks, and poor optimization strategies.

**⚙️ Function:** Understanding performance bottlenecks helps developers create faster, more responsive applications that provide better user experience.

**🚀 Features:**
- Change detection cycles
- Memory leaks from unsubscribed observables
- Large bundle sizes
- Inefficient template expressions
- Heavy computations in templates
- Unnecessary component re-renders

**🔁 Flow:**
```typescript
import { Component, OnInit, OnDestroy, ChangeDetectionStrategy } from '@angular/core';
import { Subject, takeUntil } from 'rxjs';

@Component({
  selector: 'app-performance-example',
  template: `
    <div class="performance-demo">
      <h2>Performance Monitoring</h2>
      
      <!-- Avoid heavy computations in template -->
      <div class="data-display">
        <p>Items: {{ getItemCount() }}</p> <!-- ❌ Bad: Called on every change detection -->
        <p>Items: {{ itemCount }}</p> <!-- ✅ Good: Pre-computed value -->
      </div>
      
      <!-- Use OnPush strategy for better performance -->
      <div class="user-list">
        <div *ngFor="let user of users; trackBy: trackByUserId" class="user-item">
          {{ user.name }} - {{ user.email }}
        </div>
      </div>
      
      <!-- Avoid complex expressions in template -->
      <div class="stats">
        <p>Active Users: {{ activeUsersCount }}</p>
        <p>Total Users: {{ totalUsersCount }}</p>
      </div>
    </div>
  `,
  changeDetection: ChangeDetectionStrategy.OnPush, // ✅ Performance optimization
  styles: [`
    .performance-demo {
      padding: 20px;
    }
    .user-item {
      padding: 8px;
      border-bottom: 1px solid #eee;
    }
  `]
})
export class PerformanceExampleComponent implements OnInit, OnDestroy {
  private destroy$ = new Subject<void>(); // ✅ Memory leak prevention
  users: User[] = [];
  itemCount = 0;
  activeUsersCount = 0;
  totalUsersCount = 0;
  
  constructor(private userService: UserService) {}
  
  ngOnInit(): void {
    // ✅ Proper subscription management
    this.userService.getUsers()
      .pipe(takeUntil(this.destroy$))
      .subscribe(users => {
        this.users = users;
        this.itemCount = users.length;
        this.activeUsersCount = users.filter(u => u.active).length;
        this.totalUsersCount = users.length;
      });
  }
  
  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
  
  // ❌ Bad: Heavy computation in template
  getItemCount(): number {
    console.log('Computing item count...'); // This runs on every change detection
    return this.users.length;
  }
  
  // ✅ Good: TrackBy function for *ngFor performance
  trackByUserId(index: number, user: User): number {
    return user.id;
  }
}

interface User {
  id: number;
  name: string;
  email: string;
  active: boolean;
}
```

**🐞 Fixes:** Use OnPush change detection strategy, implement proper subscription cleanup, avoid heavy computations in templates, and use trackBy functions for *ngFor.

---

2. How do you reduce the bundle size of an Angular app? _(Asked in Capgemini)_

**🧩 Foundation:** Bundle size reduction involves optimizing imports, using tree-shaking, implementing code splitting, and removing unused dependencies.

**⚙️ Function:** Smaller bundles improve application load times, reduce bandwidth usage, and provide better user experience, especially on slower connections.

**🚀 Features:**
- Tree-shaking for unused code removal
- Lazy loading of modules
- Code splitting strategies
- Bundle analysis tools
- Optimized imports
- Compression and minification

**🔁 Flow:**
```typescript
// ✅ Good: Specific imports (tree-shakable)
import { Component } from '@angular/core';
import { map, filter } from 'rxjs/operators';

// ❌ Bad: Import entire library
import * as _ from 'lodash';

// ✅ Good: Import only needed functions
import { debounce } from 'lodash-es';

@Component({
  selector: 'app-bundle-optimization',
  template: `
    <div class="optimized-app">
      <h2>Bundle Optimized Application</h2>
      
      <!-- Lazy loaded components -->
      <button (click)="loadHeavyComponent()">Load Heavy Component</button>
      <ng-container *ngComponentOutlet="heavyComponent"></ng-container>
      
      <!-- Conditional loading -->
      <div *ngIf="showAdvancedFeatures">
        <app-advanced-features></app-advanced-features>
      </div>
    </div>
  `
})
export class BundleOptimizationComponent {
  heavyComponent: any = null;
  showAdvancedFeatures = false;
  
  async loadHeavyComponent(): Promise<void> {
    // ✅ Dynamic import for code splitting
    const module = await import('./heavy-component/heavy.component');
    this.heavyComponent = module.HeavyComponent;
  }
  
  toggleAdvancedFeatures(): void {
    this.showAdvancedFeatures = !this.showAdvancedFeatures;
  }
}

// ✅ Lazy loaded module
const routes: Routes = [
  {
    path: 'dashboard',
    loadChildren: () => import('./dashboard/dashboard.module').then(m => m.DashboardModule)
  },
  {
    path: 'reports',
    loadChildren: () => import('./reports/reports.module').then(m => m.ReportsModule)
  }
];

// ✅ Optimized service with specific imports
@Injectable({
  providedIn: 'root'
})
export class OptimizedDataService {
  constructor(private http: HttpClient) {}
  
  // ✅ Use specific RxJS operators
  getData(): Observable<any[]> {
    return this.http.get<any[]>('/api/data').pipe(
      map(response => response.filter(item => item.active)),
      debounceTime(300) // ✅ Specific import instead of full lodash
    );
  }
}

// ✅ Bundle analysis script
// package.json
{
  "scripts": {
    "analyze": "ng build --stats-json && webpack-bundle-analyzer dist/stats.json",
    "build:prod": "ng build --configuration production --aot --build-optimizer"
  }
}
```

**🐞 Fixes:** Use bundle analyzers to identify large dependencies, implement proper code splitting, and regularly audit dependencies for unused packages.

### Intermediate Questions

1. How does the OnPush change detection strategy work and when should you use it? _(Asked in Deloitte)_

**🧩 Foundation:** OnPush change detection strategy only runs change detection when inputs change, events are triggered, or observables emit, significantly reducing unnecessary change detection cycles.

**⚙️ Function:** OnPush strategy improves performance by limiting when Angular checks for changes, reducing CPU usage and improving application responsiveness.

**🚀 Features:**
- Only triggers on @Input changes
- Responds to component events
- Works with async pipes
- Requires immutable data patterns
- Reduces change detection cycles
- Improves performance for large component trees

**🔁 Flow:**
```typescript
import { Component, Input, ChangeDetectionStrategy, ChangeDetectorRef } from '@angular/core';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-onpush-example',
  template: `
    <div class="onpush-demo">
      <h3>{{ title }}</h3>
      
      <!-- Input-based change detection -->
      <div class="user-info">
        <p>Name: {{ user.name }}</p>
        <p>Email: {{ user.email }}</p>
        <p>Status: {{ user.status }}</p>
      </div>
      
      <!-- Event-based change detection -->
      <button (click)="updateStatus()">Update Status</button>
      <p>Last Updated: {{ lastUpdated }}</p>
      
      <!-- Observable-based change detection -->
      <div class="real-time-data">
        <p>Current Time: {{ currentTime$ | async }}</p>
        <p>Data Count: {{ dataCount$ | async }}</p>
      </div>
      
      <!-- Manual change detection -->
      <button (click)="manualUpdate()">Manual Update</button>
      <p>Manual Counter: {{ manualCounter }}</p>
    </div>
  `,
  changeDetection: ChangeDetectionStrategy.OnPush // ✅ Performance optimization
})
export class OnPushExampleComponent {
  @Input() title = 'OnPush Demo';
  @Input() user: User = { name: '', email: '', status: '' };
  
  lastUpdated = new Date().toLocaleTimeString();
  manualCounter = 0;
  
  // ✅ Observable for async pipe (triggers change detection)
  currentTime$ = new Observable<string>(observer => {
    setInterval(() => {
      observer.next(new Date().toLocaleTimeString());
    }, 1000);
  });
  
  dataCount$ = new Observable<number>(observer => {
    let count = 0;
    setInterval(() => {
      observer.next(++count);
    }, 2000);
  });
  
  constructor(private cdr: ChangeDetectorRef) {}
  
  // ✅ Event-based change detection
  updateStatus(): void {
    this.lastUpdated = new Date().toLocaleTimeString();
    // No need to call detectChanges() - event triggers change detection
  }
  
  // ✅ Manual change detection
  manualUpdate(): void {
    this.manualCounter++;
    this.cdr.detectChanges(); // Manually trigger change detection
  }
  
  // ✅ Method to update user (for parent component)
  updateUser(newUser: User): void {
    // ❌ Bad: Mutating input
    // this.user.name = newUser.name;
    
    // ✅ Good: Creating new object (triggers OnPush change detection)
    this.user = { ...newUser };
  }
}

// Parent component demonstrating OnPush usage
@Component({
  selector: 'app-parent',
  template: `
    <div class="parent-container">
      <h2>Parent Component</h2>
      
      <!-- ✅ Immutable data updates trigger OnPush change detection -->
      <app-onpush-example 
        [title]="componentTitle"
        [user]="currentUser">
      </app-onpush-example>
      
      <button (click)="updateUser()">Update User</button>
      <button (click)="updateTitle()">Update Title</button>
    </div>
  `
})
export class ParentComponent {
  componentTitle = 'Initial Title';
  currentUser: User = {
    name: 'John Doe',
    email: 'john@example.com',
    status: 'Active'
  };
  
  updateUser(): void {
    // ✅ Creating new object triggers OnPush change detection
    this.currentUser = {
      ...this.currentUser,
      status: this.currentUser.status === 'Active' ? 'Inactive' : 'Active'
    };
  }
  
  updateTitle(): void {
    this.componentTitle = `Updated Title - ${new Date().toLocaleTimeString()}`;
  }
}

interface User {
  name: string;
  email: string;
  status: string;
}
```

**🐞 Fixes:** Always use immutable data patterns, avoid mutating @Input properties, and use async pipes or manual change detection when needed.

---

2. How do trackBy functions improve the performance of `*ngFor`? _(Asked in TCS)_

**🧩 Foundation:** TrackBy functions help Angular identify which items have changed in a list, preventing unnecessary DOM manipulation and improving rendering performance.

**⚙️ Function:** TrackBy functions provide stable identity for list items, allowing Angular to efficiently update only changed elements instead of re-rendering the entire list.

**🚀 Features:**
- Stable item identification
- Reduced DOM manipulation
- Improved rendering performance
- Better memory usage
- Optimized change detection
- Prevents unnecessary re-renders

**🔁 Flow:**
```typescript
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-trackby-example',
  template: `
    <div class="trackby-demo">
      <h3>TrackBy Performance Demo</h3>
      
      <!-- ❌ Bad: No trackBy function -->
      <div class="user-list-bad">
        <h4>Without TrackBy (Poor Performance)</h4>
        <div *ngFor="let user of usersWithoutTrackBy" class="user-item">
          <span>{{ user.name }}</span>
          <span>{{ user.email }}</span>
          <button (click)="updateUserBad(user)">Update</button>
        </div>
      </div>
      
      <!-- ✅ Good: With trackBy function -->
      <div class="user-list-good">
        <h4>With TrackBy (Better Performance)</h4>
        <div *ngFor="let user of usersWithTrackBy; trackBy: trackByUserId" class="user-item">
          <span>{{ user.name }}</span>
          <span>{{ user.email }}</span>
          <button (click)="updateUserGood(user)">Update</button>
        </div>
      </div>
      
      <!-- ✅ Advanced: TrackBy with multiple properties -->
      <div class="user-list-advanced">
        <h4>Advanced TrackBy</h4>
        <div *ngFor="let user of usersAdvanced; trackBy: trackByUserProperties" class="user-item">
          <span>{{ user.name }}</span>
          <span>{{ user.email }}</span>
          <span>{{ user.department }}</span>
          <button (click)="updateUserAdvanced(user)">Update</button>
        </div>
      </div>
      
      <div class="controls">
        <button (click)="addUser()">Add User</button>
        <button (click)="removeUser()">Remove User</button>
        <button (click)="shuffleUsers()">Shuffle Users</button>
        <button (click)="updateRandomUser()">Update Random User</button>
      </div>
      
      <div class="performance-info">
        <p>Total Users: {{ usersWithTrackBy.length }}</p>
        <p>Updates: {{ updateCount }}</p>
        <p>Last Update: {{ lastUpdateTime }}</p>
      </div>
    </div>
  `,
  styles: [`
    .trackby-demo {
      padding: 20px;
    }
    .user-item {
      display: flex;
      gap: 10px;
      padding: 8px;
      border: 1px solid #ddd;
      margin: 4px 0;
      border-radius: 4px;
    }
    .controls {
      margin: 20px 0;
      display: flex;
      gap: 10px;
    }
    .performance-info {
      background: #f8f9fa;
      padding: 10px;
      border-radius: 4px;
    }
  `]
})
export class TrackByExampleComponent implements OnInit {
  usersWithoutTrackBy: User[] = [];
  usersWithTrackBy: User[] = [];
  usersAdvanced: User[] = [];
  
  updateCount = 0;
  lastUpdateTime = '';
  
  ngOnInit(): void {
    this.initializeUsers();
  }
  
  private initializeUsers(): void {
    const initialUsers: User[] = [
      { id: 1, name: 'John Doe', email: 'john@example.com', department: 'Engineering' },
      { id: 2, name: 'Jane Smith', email: 'jane@example.com', department: 'Marketing' },
      { id: 3, name: 'Bob Johnson', email: 'bob@example.com', department: 'Sales' },
      { id: 4, name: 'Alice Brown', email: 'alice@example.com', department: 'HR' }
    ];
    
    this.usersWithoutTrackBy = [...initialUsers];
    this.usersWithTrackBy = [...initialUsers];
    this.usersAdvanced = [...initialUsers];
  }
  
  // ✅ Basic trackBy function using ID
  trackByUserId(index: number, user: User): number {
    return user.id;
  }
  
  // ✅ Advanced trackBy function using multiple properties
  trackByUserProperties(index: number, user: User): string {
    return `${user.id}-${user.name}-${user.department}`;
  }
  
  // ❌ Bad: Updates without trackBy (causes full re-render)
  updateUserBad(user: User): void {
    const index = this.usersWithoutTrackBy.findIndex(u => u.id === user.id);
    if (index !== -1) {
      this.usersWithoutTrackBy[index] = {
        ...user,
        name: `${user.name} (Updated)`
      };
      this.updateCount++;
      this.lastUpdateTime = new Date().toLocaleTimeString();
    }
  }
  
  // ✅ Good: Updates with trackBy (efficient DOM updates)
  updateUserGood(user: User): void {
    const index = this.usersWithTrackBy.findIndex(u => u.id === user.id);
    if (index !== -1) {
      this.usersWithTrackBy[index] = {
        ...user,
        name: `${user.name} (Updated)`
      };
      this.updateCount++;
      this.lastUpdateTime = new Date().toLocaleTimeString();
    }
  }
  
  // ✅ Advanced: Updates with complex trackBy
  updateUserAdvanced(user: User): void {
    const index = this.usersAdvanced.findIndex(u => u.id === user.id);
    if (index !== -1) {
      this.usersAdvanced[index] = {
        ...user,
        department: user.department === 'Engineering' ? 'Development' : 'Engineering'
      };
      this.updateCount++;
      this.lastUpdateTime = new Date().toLocaleTimeString();
    }
  }
  
  addUser(): void {
    const newId = Math.max(...this.usersWithTrackBy.map(u => u.id)) + 1;
    const newUser: User = {
      id: newId,
      name: `User ${newId}`,
      email: `user${newId}@example.com`,
      department: 'New Department'
    };
    
    this.usersWithoutTrackBy.push(newUser);
    this.usersWithTrackBy.push(newUser);
    this.usersAdvanced.push(newUser);
  }
  
  removeUser(): void {
    if (this.usersWithTrackBy.length > 0) {
      this.usersWithoutTrackBy.pop();
      this.usersWithTrackBy.pop();
      this.usersAdvanced.pop();
    }
  }
  
  shuffleUsers(): void {
    this.usersWithoutTrackBy = this.shuffleArray([...this.usersWithoutTrackBy]);
    this.usersWithTrackBy = this.shuffleArray([...this.usersWithTrackBy]);
    this.usersAdvanced = this.shuffleArray([...this.usersAdvanced]);
  }
  
  updateRandomUser(): void {
    if (this.usersWithTrackBy.length > 0) {
      const randomIndex = Math.floor(Math.random() * this.usersWithTrackBy.length);
      const user = this.usersWithTrackBy[randomIndex];
      this.updateUserGood(user);
    }
  }
  
  private shuffleArray<T>(array: T[]): T[] {
    const shuffled = [...array];
    for (let i = shuffled.length - 1; i > 0; i--) {
      const j = Math.floor(Math.random() * (i + 1));
      [shuffled[i], shuffled[j]] = [shuffled[j], shuffled[i]];
    }
    return shuffled;
  }
}

interface User {
  id: number;
  name: string;
  email: string;
  department: string;
}

// Performance monitoring service
@Injectable({
  providedIn: 'root'
})
export class PerformanceMonitorService {
  private renderTimes: number[] = [];
  
  startRenderTimer(): void {
    performance.mark('render-start');
  }
  
  endRenderTimer(): number {
    performance.mark('render-end');
    performance.measure('render-time', 'render-start', 'render-end');
    
    const measure = performance.getEntriesByName('render-time')[0];
    const renderTime = measure.duration;
    
    this.renderTimes.push(renderTime);
    
    // Keep only last 100 measurements
    if (this.renderTimes.length > 100) {
      this.renderTimes.shift();
    }
    
    return renderTime;
  }
  
  getAverageRenderTime(): number {
    if (this.renderTimes.length === 0) return 0;
    return this.renderTimes.reduce((sum, time) => sum + time, 0) / this.renderTimes.length;
  }
  
  getPerformanceReport(): any {
    return {
      averageRenderTime: this.getAverageRenderTime(),
      totalMeasurements: this.renderTimes.length,
      minRenderTime: Math.min(...this.renderTimes),
      maxRenderTime: Math.max(...this.renderTimes)
    };
  }
}
```

**🐞 Fixes:** Always use trackBy functions for lists with more than 10 items, ensure trackBy functions return stable values, and avoid complex calculations in trackBy functions.

### Advanced Questions

1. How do you use service workers to improve performance in Angular PWAs? _(Asked in Tech Mahindra)_

**🧩 Foundation:** Service workers in Angular PWAs provide offline functionality, caching strategies, and background sync capabilities to improve performance and user experience.

**⚙️ Function:** Service workers act as a proxy between the application and network, enabling caching, offline access, and performance optimizations.

**🚀 Features:**
- Offline functionality
- Intelligent caching strategies
- Background sync
- Push notifications
- Network request interception
- Performance optimization

**🔁 Flow:**
```typescript
// ngsw-config.json
{
  "$schema": "./node_modules/@angular/service-worker/config/schema.json",
  "index": "/index.html",
  "assetGroups": [
    {
      "name": "app",
      "installMode": "prefetch",
      "resources": {
        "files": [
          "/favicon.ico",
          "/index.html",
          "/manifest.webmanifest",
          "/*.css",
          "/*.js"
        ]
      }
    },
    {
      "name": "assets",
      "installMode": "lazy",
      "updateMode": "prefetch",
      "resources": {
        "files": [
          "/assets/**",
          "/*.(svg|cur|jpg|jpeg|png|apng|webp|avif|gif|otf|ttf|woff|woff2)"
        ]
      }
    }
  ],
  "dataGroups": [
    {
      "name": "api-freshness",
      "urls": [
        "/api/users",
        "/api/products"
      ],
      "cacheConfig": {
        "strategy": "freshness",
        "maxSize": 100,
        "maxAge": "3d",
        "timeout": "10s"
      }
    },
    {
      "name": "api-performance",
      "urls": [
        "/api/static-data",
        "/api/reference-data"
      ],
      "cacheConfig": {
        "strategy": "performance",
        "maxSize": 100,
        "maxAge": "7d"
      }
    }
  ]
}

// app.module.ts
import { ServiceWorkerModule } from '@angular/service-worker';
import { environment } from '../environments/environment';

@NgModule({
  imports: [
    ServiceWorkerModule.register('ngsw-worker.js', {
      enabled: environment.production,
      registrationStrategy: 'registerWhenStable:30000'
    })
  ]
})
export class AppModule {}

// Service worker service
@Injectable({
  providedIn: 'root'
})
export class SwUpdateService {
  constructor(private swUpdate: SwUpdate) {
    this.initializeServiceWorker();
  }
  
  private initializeServiceWorker(): void {
    if (this.swUpdate.isEnabled) {
      // Check for updates
      this.swUpdate.checkForUpdate();
      
      // Listen for available updates
      this.swUpdate.available.subscribe(event => {
        console.log('New version available:', event.current, event.available);
        this.promptUserForUpdate();
      });
      
      // Listen for activated updates
      this.swUpdate.activated.subscribe(event => {
        console.log('New version activated:', event.previous, event.current);
      });
    }
  }
  
  private promptUserForUpdate(): void {
    if (confirm('New version available. Load new version?')) {
      window.location.reload();
    }
  }
  
  checkForUpdate(): Promise<void> {
    return this.swUpdate.checkForUpdate();
  }
}

// PWA service for offline functionality
@Injectable({
  providedIn: 'root'
})
export class PwaService {
  private isOnline = navigator.onLine;
  
  constructor() {
    this.setupOnlineOfflineHandlers();
  }
  
  private setupOnlineOfflineHandlers(): void {
    window.addEventListener('online', () => {
      this.isOnline = true;
      this.handleOnline();
    });
    
    window.addEventListener('offline', () => {
      this.isOnline = false;
      this.handleOffline();
    });
  }
  
  private handleOnline(): void {
    console.log('Application is online');
    // Sync any pending offline data
    this.syncOfflineData();
  }
  
  private handleOffline(): void {
    console.log('Application is offline');
    // Show offline indicator
    this.showOfflineIndicator();
  }
  
  private async syncOfflineData(): Promise<void> {
    // Implement offline data synchronization
    const offlineData = this.getOfflineData();
    if (offlineData.length > 0) {
      try {
        await this.syncDataToServer(offlineData);
        this.clearOfflineData();
      } catch (error) {
        console.error('Failed to sync offline data:', error);
      }
    }
  }
  
  private getOfflineData(): any[] {
    return JSON.parse(localStorage.getItem('offlineData') || '[]');
  }
  
  private clearOfflineData(): void {
    localStorage.removeItem('offlineData');
  }
  
  private async syncDataToServer(data: any[]): Promise<void> {
    // Implement server synchronization logic
  }
  
  private showOfflineIndicator(): void {
    // Show offline indicator to user
  }
  
  isOnline(): boolean {
    return this.isOnline;
  }
}

// Enhanced HTTP interceptor for offline support
@Injectable()
export class OfflineInterceptor implements HttpInterceptor {
  constructor(private pwaService: PwaService) {}
  
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (!this.pwaService.isOnline() && this.shouldCacheRequest(req)) {
      return this.handleOfflineRequest(req);
    }
    
    return next.handle(req).pipe(
      catchError(error => {
        if (error.status === 0 && this.shouldCacheRequest(req)) {
          return this.handleOfflineRequest(req);
        }
        return throwError(error);
      })
    );
  }
  
  private shouldCacheRequest(req: HttpRequest<any>): boolean {
    return req.method === 'GET' || req.method === 'POST';
  }
  
  private handleOfflineRequest(req: HttpRequest<any>): Observable<HttpEvent<any>> {
    // Return cached data or offline response
    const cachedData = this.getCachedData(req.url);
    if (cachedData) {
      return of(new HttpResponse({ body: cachedData }));
    }
    
    // Store request for later sync
    this.storeOfflineRequest(req);
    
    return of(new HttpResponse({ 
      body: { message: 'Offline mode - data will sync when online' }
    }));
  }
  
  private getCachedData(url: string): any {
    return JSON.parse(localStorage.getItem(`cache_${url}`) || 'null');
  }
  
  private storeOfflineRequest(req: HttpRequest<any>): void {
    const offlineData = JSON.parse(localStorage.getItem('offlineData') || '[]');
    offlineData.push({
      url: req.url,
      method: req.method,
      body: req.body,
      timestamp: new Date().toISOString()
    });
    localStorage.setItem('offlineData', JSON.stringify(offlineData));
  }
}
```

**🐞 Fixes:** Test offline functionality thoroughly, implement proper error handling for failed requests, and ensure cached data is properly invalidated.

---

### Tough Questions

1. You have a dashboard with many real-time charts and metrics. How do you ensure it performs smoothly on low-end devices? _(Asked in Cognizant)_

**🧩 Foundation:** Dashboard performance optimization requires efficient data handling, smart rendering strategies, and device-specific optimizations to maintain smooth performance across all devices.

**⚙️ Function:** Performance optimization ensures the dashboard remains responsive and provides good user experience regardless of device capabilities.

**🚀 Features:**
- Virtual scrolling for large datasets
- Lazy loading of chart components
- Data throttling and debouncing
- Progressive enhancement
- Memory management
- Device capability detection

**🔁 Flow:**
```typescript
import { Component, OnInit, OnDestroy, ChangeDetectionStrategy } from '@angular/core';
import { BehaviorSubject, Subject, interval, combineLatest } from 'rxjs';
import { takeUntil, debounceTime, throttleTime, map, filter } from 'rxjs/operators';

@Component({
  selector: 'app-performance-dashboard',
  template: `
    <div class="dashboard-container" [class.low-end]="isLowEndDevice">
      <div class="dashboard-header">
        <h1>Performance Dashboard</h1>
        <div class="device-info">
          <span>Device: {{ deviceType }}</span>
          <span>Performance Mode: {{ performanceMode }}</span>
        </div>
      </div>
      
      <!-- Virtual scrolling for large datasets -->
      <div class="metrics-grid">
        <div class="metric-card" *ngFor="let metric of visibleMetrics; trackBy: trackByMetricId">
          <h3>{{ metric.name }}</h3>
          <div class="metric-value">{{ metric.value }}</div>
          <div class="metric-chart" *ngIf="shouldShowChart(metric)">
            <app-chart 
              [data]="metric.chartData"
              [config]="getChartConfig(metric)">
            </app-chart>
          </div>
        </div>
      </div>
      
      <!-- Lazy loaded advanced features -->
      <div class="advanced-features" *ngIf="showAdvancedFeatures">
        <ng-container *ngComponentOutlet="advancedComponent"></ng-container>
      </div>
      
      <!-- Performance controls -->
      <div class="performance-controls">
        <button (click)="togglePerformanceMode()">
          {{ performanceMode === 'high' ? 'Low Performance' : 'High Performance' }}
        </button>
        <button (click)="toggleRealTimeUpdates()">
          {{ realTimeEnabled ? 'Disable Real-time' : 'Enable Real-time' }}
        </button>
      </div>
      
      <!-- Performance monitoring -->
      <div class="performance-monitor">
        <p>FPS: {{ currentFPS }}</p>
        <p>Memory Usage: {{ memoryUsage }}MB</p>
        <p>Update Rate: {{ updateRate }}ms</p>
      </div>
    </div>
  `,
  changeDetection: ChangeDetectionStrategy.OnPush,
  styles: [`
    .dashboard-container {
      padding: 20px;
      max-width: 1200px;
      margin: 0 auto;
    }
    
    .dashboard-container.low-end {
      /* Reduce animations and effects for low-end devices */
      animation: none;
      transition: none;
    }
    
    .metrics-grid {
      display: grid;
      grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
      gap: 20px;
      margin: 20px 0;
    }
    
    .metric-card {
      background: white;
      border-radius: 8px;
      padding: 20px;
      box-shadow: 0 2px 4px rgba(0,0,0,0.1);
    }
    
    .performance-controls {
      display: flex;
      gap: 10px;
      margin: 20px 0;
    }
    
    .performance-monitor {
      background: #f8f9fa;
      padding: 10px;
      border-radius: 4px;
      font-family: monospace;
    }
  `]
})
export class PerformanceDashboardComponent implements OnInit, OnDestroy {
  private destroy$ = new Subject<void>();
  private dataStream$ = new BehaviorSubject<Metric[]>([]);
  private performanceMonitor$ = new BehaviorSubject<PerformanceMetrics>({
    fps: 60,
    memoryUsage: 0,
    updateRate: 1000
  });
  
  visibleMetrics: Metric[] = [];
  advancedComponent: any = null;
  showAdvancedFeatures = false;
  
  deviceType = 'unknown';
  performanceMode = 'high';
  realTimeEnabled = true;
  currentFPS = 60;
  memoryUsage = 0;
  updateRate = 1000;
  
  private isLowEndDevice = false;
  private updateInterval = 1000;
  private maxVisibleMetrics = 10;
  
  constructor(
    private deviceService: DeviceCapabilityService,
    private performanceService: PerformanceMonitorService
  ) {}
  
  ngOnInit(): void {
    this.initializeDeviceDetection();
    this.setupPerformanceMonitoring();
    this.initializeDataStream();
    this.loadAdvancedFeatures();
  }
  
  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
  
  private initializeDeviceDetection(): void {
    this.deviceType = this.deviceService.getDeviceType();
    this.isLowEndDevice = this.deviceService.isLowEndDevice();
    
    if (this.isLowEndDevice) {
      this.performanceMode = 'low';
      this.updateInterval = 2000;
      this.maxVisibleMetrics = 5;
      this.showAdvancedFeatures = false;
    }
  }
  
  private setupPerformanceMonitoring(): void {
    // Monitor FPS
    interval(1000).pipe(
      takeUntil(this.destroy$)
    ).subscribe(() => {
      this.currentFPS = this.performanceService.getCurrentFPS();
      this.memoryUsage = this.performanceService.getMemoryUsage();
    });
    
    // Adjust performance based on metrics
    this.performanceMonitor$.pipe(
      takeUntil(this.destroy$),
      debounceTime(2000)
    ).subscribe(metrics => {
      this.adjustPerformance(metrics);
    });
  }
  
  private initializeDataStream(): void {
    // Throttle data updates based on device capability
    this.dataStream$.pipe(
      takeUntil(this.destroy$),
      throttleTime(this.updateInterval),
      map(metrics => this.filterMetricsForDevice(metrics))
    ).subscribe(metrics => {
      this.visibleMetrics = metrics;
    });
    
    // Start real-time data updates
    this.startRealTimeUpdates();
  }
  
  private startRealTimeUpdates(): void {
    if (!this.realTimeEnabled) return;
    
    interval(this.updateInterval).pipe(
      takeUntil(this.destroy$),
      filter(() => this.realTimeEnabled)
    ).subscribe(() => {
      this.updateMetrics();
    });
  }
  
  private updateMetrics(): void {
    const newMetrics = this.generateMetrics();
    this.dataStream$.next(newMetrics);
  }
  
  private filterMetricsForDevice(metrics: Metric[]): Metric[] {
    if (this.isLowEndDevice) {
      return metrics.slice(0, this.maxVisibleMetrics);
    }
    return metrics;
  }
  
  private shouldShowChart(metric: Metric): boolean {
    if (this.isLowEndDevice) {
      return metric.priority === 'high';
    }
    return true;
  }
  
  private getChartConfig(metric: Metric): ChartConfig {
    if (this.isLowEndDevice) {
      return {
        ...metric.chartConfig,
        animation: false,
        responsive: true,
        maintainAspectRatio: false
      };
    }
    return metric.chartConfig;
  }
  
  private adjustPerformance(metrics: PerformanceMetrics): void {
    if (metrics.fps < 30) {
      this.performanceMode = 'low';
      this.updateInterval = 2000;
      this.maxVisibleMetrics = 5;
    } else if (metrics.fps < 45) {
      this.performanceMode = 'medium';
      this.updateInterval = 1500;
      this.maxVisibleMetrics = 8;
    }
  }
  
  private async loadAdvancedFeatures(): Promise<void> {
    if (!this.isLowEndDevice) {
      try {
        const module = await import('./advanced-features/advanced.component');
        this.advancedComponent = module.AdvancedFeaturesComponent;
        this.showAdvancedFeatures = true;
      } catch (error) {
        console.warn('Failed to load advanced features:', error);
      }
    }
  }
  
  togglePerformanceMode(): void {
    this.performanceMode = this.performanceMode === 'high' ? 'low' : 'high';
    this.updateInterval = this.performanceMode === 'high' ? 1000 : 2000;
  }
  
  toggleRealTimeUpdates(): void {
    this.realTimeEnabled = !this.realTimeEnabled;
    if (this.realTimeEnabled) {
      this.startRealTimeUpdates();
    }
  }
  
  trackByMetricId(index: number, metric: Metric): string {
    return metric.id;
  }
  
  private generateMetrics(): Metric[] {
    // Generate sample metrics
    return Array.from({ length: 20 }, (_, i) => ({
      id: `metric-${i}`,
      name: `Metric ${i + 1}`,
      value: Math.random() * 100,
      priority: i < 5 ? 'high' : 'medium',
      chartData: this.generateChartData(),
      chartConfig: this.getDefaultChartConfig()
    }));
  }
  
  private generateChartData(): any[] {
    return Array.from({ length: 10 }, () => ({
      x: new Date(),
      y: Math.random() * 100
    }));
  }
  
  private getDefaultChartConfig(): ChartConfig {
    return {
      type: 'line',
      animation: !this.isLowEndDevice,
      responsive: true,
      maintainAspectRatio: false
    };
  }
}

interface Metric {
  id: string;
  name: string;
  value: number;
  priority: 'high' | 'medium' | 'low';
  chartData: any[];
  chartConfig: ChartConfig;
}

interface ChartConfig {
  type: string;
  animation: boolean;
  responsive: boolean;
  maintainAspectRatio: boolean;
}

interface PerformanceMetrics {
  fps: number;
  memoryUsage: number;
  updateRate: number;
}

// Device capability service
@Injectable({
  providedIn: 'root'
})
export class DeviceCapabilityService {
  private deviceInfo: DeviceInfo;
  
  constructor() {
    this.deviceInfo = this.detectDeviceCapabilities();
  }
  
  private detectDeviceCapabilities(): DeviceInfo {
    const userAgent = navigator.userAgent;
    const hardwareConcurrency = navigator.hardwareConcurrency || 1;
    const memory = (performance as any).memory?.usedJSHeapSize || 0;
    
    return {
      type: this.getDeviceType(userAgent),
      isLowEnd: this.isLowEndDevice(hardwareConcurrency, memory),
      cores: hardwareConcurrency,
      memory: memory,
      userAgent: userAgent
    };
  }
  
  private getDeviceType(userAgent: string): string {
    if (/Mobile|Android|iPhone|iPad/.test(userAgent)) {
      return 'mobile';
    }
    if (/Tablet|iPad/.test(userAgent)) {
      return 'tablet';
    }
    return 'desktop';
  }
  
  private isLowEndDevice(cores: number, memory: number): boolean {
    return cores <= 2 || memory < 50 * 1024 * 1024; // Less than 50MB
  }
  
  getDeviceType(): string {
    return this.deviceInfo.type;
  }
  
  isLowEndDevice(): boolean {
    return this.deviceInfo.isLowEnd;
  }
  
  getDeviceInfo(): DeviceInfo {
    return this.deviceInfo;
  }
}

interface DeviceInfo {
  type: string;
  isLowEnd: boolean;
  cores: number;
  memory: number;
  userAgent: string;
}
```

**🐞 Fixes:** Implement progressive enhancement, use device capability detection, and provide fallbacks for unsupported features on low-end devices.

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