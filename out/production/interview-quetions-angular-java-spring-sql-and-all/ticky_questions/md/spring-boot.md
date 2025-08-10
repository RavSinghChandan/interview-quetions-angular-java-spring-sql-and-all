
---

### 1️⃣ **Spring Boot Startup & Application Lifecycle**

*(How the app starts, what's initialized, and in what order)*

1. What are the exact internal steps of `SpringApplication.run()`?
2. What is `SpringApplicationContextInitializer`, and when is it triggered?
3. How does Spring decide which `ApplicationListener`s to run?
4. What role does `SpringFactoriesLoader` play in startup?
5. What’s the order of events: `@PostConstruct`, `InitializingBean`, `ApplicationRunner`, `CommandLineRunner`, etc.?
6. What is `EnvironmentPreparedEvent`, and when is it published?
7. How does Spring Boot decide whether it's a web application or not?
8. Can you prevent an auto-configuration class from loading at runtime?
9. What happens if an exception is thrown during the context refresh phase?
10. How does Spring Boot create and initialize embedded Tomcat?

---

### 2️⃣ **Auto-Configuration & Conditional Annotations**

*(The engine behind Spring Boot's "magic")*

1. How does `@EnableAutoConfiguration` work under the hood?
2. What is the new `AutoConfiguration.imports` file in Spring Boot 3, and how is it different from `spring.factories`?
3. How does `@ConditionalOnMissingBean` work internally?
4. When is `@ConditionalOnProperty` evaluated? Before or after bean creation?
5. What happens if two auto-configuration classes try to configure the same bean?
6. How does Spring Boot ensure that user-defined beans override auto-configured ones?
7. What is `ConditionEvaluationReport`, and how can you use it for debugging?
8. Can two `@Conditional` classes evaluate differently based on the same property?
9. What are `AutoConfigurationMetadata` and its purpose in Spring Boot internals?
10. What’s the order of evaluation if multiple `@Conditional` annotations are stacked?

---

### 3️⃣ **Bean Lifecycle, Scopes & Injection**

*(The engine behind Spring’s DI system)*

1. What is the full lifecycle of a Spring Bean (creation to destruction)?
2. How does Spring handle circular dependencies in field vs constructor injection?
3. What does `@Lazy` really do? How is lazy bean loading achieved?
4. What’s the difference between `@Primary` and `@Qualifier`, and which wins in conflicts?
5. How do bean scopes affect lifecycle and thread safety (`singleton`, `prototype`, `request`, `session`)?
6. What happens if a `@Bean` method returns `null`?
7. What’s the actual class created when using `@Configuration`? (CGLIB enhancement)
8. Can `@DependsOn` force initialization order in `@Lazy` beans?
9. How does the container resolve ambiguous constructors in a component class?
10. Can we change a bean's lifecycle during runtime? If yes, how?

---

### 4️⃣ **Embedded Servers & Web Internals**

*(Tomcat, Jetty, Undertow, and web request handling)*

1. How is the embedded Tomcat server started in Spring Boot?
2. What thread actually executes a `@RestController` method?
3. How does Spring map a request from URL to method (`DispatcherServlet` internals)?
4. What’s the internal flow from HTTP request to `@GetMapping` method?
5. What’s the difference between `WebApplicationContext` and `ApplicationContext`?
6. How is the filter chain built in Spring Boot?
7. How does Spring Boot decide between Jetty, Undertow, or Tomcat?
8. What happens if you define your own `DispatcherServlet` bean?
9. How are custom servlet filters prioritized?
10. How do you configure thread pool size for embedded server? (e.g., max concurrent requests)

---

### 5️⃣ **Exception Handling & Controller Advice**

*(Global handling, traps, and smart layering)*

1. What happens if two `@ExceptionHandler` methods match the same exception?
2. How does `@ControllerAdvice` and `@RestControllerAdvice` differ in implementation?
3. What is the order of execution between `@ExceptionHandler` and custom `ErrorController`?
4. Can you override the default error JSON returned by Spring Boot? How?
5. What’s the fallback mechanism if an exception is thrown in `@ExceptionHandler`?
6. What is `BasicErrorController`, and how is it auto-configured?
7. What’s the difference between `HttpMessageNotReadableException` and `MethodArgumentNotValidException`?
8. Can exception handling logic return different formats (JSON/XML) based on content negotiation?
9. How are `BindingResult` and `MethodArgumentNotValidException` prioritized?
10. Can you write your own `HandlerExceptionResolver`? What’s the internal flow?

---

### 6️⃣ **Configuration & Property Binding**

*(YAML, `@ConfigurationProperties`, profiles, etc.)*

1. How does Spring Boot map nested YAML to Java classes via `@ConfigurationProperties`?
2. What is the binder engine used in Spring Boot 3 (hint: `Binder` class)?
3. How does Spring Boot resolve profile-specific properties (`application-dev.yml` etc.)?
4. What happens if a `@Value` property is not found? Can it cause startup failure?
5. How does type conversion work when injecting properties into custom objects?
6. Can you bind a property to a `Map<String, List<CustomType>>`? How?
7. What’s the order of precedence for property sources (env vars, system props, YAML, etc.)?
8. What’s the difference between `@ConfigurationProperties` and `@Value`?
9. How does `RelaxedBinding` work?
10. Can you inject properties into static fields or beans outside Spring context?

---

