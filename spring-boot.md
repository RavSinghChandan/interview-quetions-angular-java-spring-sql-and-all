# Spring Boot Interview Questions for Senior Java Backend Developers (Full Stack)

This document contains the most frequently asked Spring Boot interview questions, including code-based and configuration-based questions, curated for senior Java backend developers transitioning to full-stack roles. Questions are organized by topic and include those repeatedly asked in interviews by companies like **TCS, Infosys, Cognizant, Capgemini**, etc., along with additional critical questions to ensure comprehensive coverage for senior-level interviews.

---

## 1. Spring Boot Basics

### Basic Questions

1. What is Spring Boot, and how does it differ from the Spring Framework? _(Asked in TCS, Infosys)_
2. What is the purpose of the `@SpringBootApplication` annotation? _(Asked in Capgemini)_
3. How does Spring Boot auto-configuration work? _(Asked in Wipro)_

### Basic Code Questions

1. Write a simple Spring Boot application class with the `@SpringBootApplication` annotation. _(Asked in TCS)_

```java
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

### Intermediate Questions

1. What are Spring Boot starters, and how do they simplify dependency management? _(Asked in Accenture)_
2. How do you configure properties in a Spring Boot application? _(Asked in Infosys)_

### Intermediate Code Questions

1. Write an `application.properties` file to configure a server port and database connection. _(Asked in Capgemini)_

```properties
server.port=8080
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=root
spring.datasource.password=password
```

### Advanced Questions

1. How does Spring Boot’s `SpringApplication` class initialize the application context? _(Asked in Cognizant)_
2. What is the role of the AutoConfiguration classes in Spring Boot? _(Asked in TCS Digital)_

### Tough Questions

1. How would you customize Spring Boot’s auto-configuration for a specific use case? _(Asked in Deloitte)_

### Situational / Real-world Questions

1. You need to migrate a legacy Spring application to Spring Boot. How would you approach it? _(Asked in HCL)_
2. Write a configuration to disable auto-configuration for a specific Spring Boot starter. _(Asked in HCL)_

```java
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

---

## 2. Dependency Injection and Spring Beans

### Basic Questions

1. What is dependency injection in Spring Boot, and why is it important? _(Asked in TCS, Infosys)_
2. What is the difference between `@Component`, `@Service`, and `@Repository` annotations? _(Asked in Capgemini)_

### Basic Code Questions

1. Write a Spring Boot service class with dependency injection using `@Autowired`. _(Asked in TCS)_

```java
@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
```

### Intermediate Questions

1. What is the difference between constructor injection and setter injection? _(Asked in Wipro)_
2. How do you define a bean using `@Bean` in a configuration class? _(Asked in Accenture)_

### Intermediate Code Questions

1. Write a configuration class to define a custom bean in Spring Boot. _(Asked in Wipro)_

```java
@Configuration
public class AppConfig {
    @Bean
    public MyService myService() {
        return new MyServiceImpl();
    }
}
```

### Advanced Questions

1. How does Spring Boot handle bean lifecycle management? _(Asked in Cognizant)_
2. What is the difference between `@Primary` and `@Qualifier` in dependency injection? _(Asked in TCS Digital)_

### Tough Questions

1. How would you resolve circular dependencies in a Spring Boot application? _(Asked in Deloitte)_

### Situational / Real-world Questions

1. Your application has conflicting bean definitions. How would you debug and resolve this? _(Asked in HCL)_
2. Write a configuration to define two beans of the same type with different qualifiers. _(Asked in HCL)_

```java
@Configuration
public class AppConfig {
    @Bean
    @Qualifier("primaryService")
    public MyService primaryService() {
        return new PrimaryServiceImpl();
    }

    @Bean
    @Qualifier("secondaryService")
    public MyService secondaryService() {
        return new SecondaryServiceImpl();
    }
}
```

---

## 3. REST APIs with Spring Boot

### Basic Questions

1. What is a REST API, and how does Spring Boot support it? _(Asked in TCS, Capgemini)_
2. What is the purpose of the `@RestController` annotation? _(Asked in Infosys)_

### Basic Code Questions

1. Write a REST controller to handle a GET request for retrieving a user by ID. _(Asked in TCS)_

```java
@RestController
@RequestMapping("/api/users")
public class UserController {
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return new User(id, "John Doe");
    }
}
```

### Intermediate Questions

1. What is the difference between `@GetMapping`, `@PostMapping`, and `@RequestMapping`? _(Asked in Wipro)_
2. How do you handle request validation in a Spring Boot REST API? _(Asked in Accenture)_

### Intermediate Code Questions

1. Write a REST controller to create a new user with validation using `@Valid`. _(Asked in Wipro)_

```java
@RestController
@RequestMapping("/api/users")
public class UserController {
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        return ResponseEntity.ok(user);
    }
}
```

### Advanced Questions

1. How do you implement pagination and sorting in a Spring Boot REST API? _(Asked in Cognizant)_
2. What is the role of `@ExceptionHandler` in handling API errors? _(Asked in TCS Digital)_

### Advanced Code Questions

1. Write a REST controller with pagination for a list of products. _(Asked in Cognizant)_

```java
@RestController
@RequestMapping("/api/products")
public class ProductController {
    @GetMapping
    public Page<Product> getProducts(Pageable pageable) {
        return productService.findAll(pageable);
    }
}
```

### Tough Questions

1. How would you design a REST API to handle high-concurrency requests? _(Asked in Amazon)_

### Tough Code Questions

1. Write a REST controller with global exception handling for a custom exception. _(Asked in Amazon)_

```java
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<String> handleOrderNotFound(OrderNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
```

### Situational / Real-world Questions

1. Your REST API is slow under heavy load. How would you optimize it? _(Asked in HCL)_
2. Write a REST controller to handle file uploads in a Spring Boot application. _(Asked in HCL)_

```java
@RestController
@RequestMapping("/api/files")
public class FileController {
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok("File uploaded: " + file.getOriginalFilename());
    }
}
```

---

## 4. Spring Data JPA

### Basic Questions

1. What is Spring Data JPA, and how does it simplify database access? _(Asked in TCS, Infosys)_
2. What is the purpose of the `@Entity` and `@Repository` annotations? _(Asked in Capgemini)_

### Basic Code Questions

1. Write a JPA entity class for a Product table. _(Asked in TCS)_

```java
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
}
```

### Intermediate Questions

1. How do you use Spring Data JPA repositories to perform CRUD operations? _(Asked in Wipro)_
2. What is the difference between `@Query` and derived queries in Spring Data JPA? _(Asked in Accenture)_

### Intermediate Code Questions

1. Write a Spring Data JPA repository with a custom query to find products by category. _(Asked in Wipro)_

```java
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.category = :category")
    List<Product> findByCategory(@Param("category") String category);
}
```

### Advanced Questions

1. How do you handle lazy loading and eager loading in Spring Data JPA? _(Asked in Cognizant)_
2. What is the role of the `@Transactional` annotation in Spring Data JPA? _(Asked in TCS Digital)_

### Advanced Code Questions

1. Write a repository method to fetch a product with its associated reviews using a JOIN FETCH query. _(Asked in Cognizant)_

```java
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p JOIN FETCH p.reviews WHERE p.id = :id")
    Optional<Product> findByIdWithReviews(@Param("id") Long id);
}
```

### Tough Questions

1. How would you optimize JPA queries for a large dataset with complex relationships? _(Asked in Deloitte)_

### Tough Code Questions

1. Write a repository method to perform a bulk update of product prices in a single transaction. _(Asked in Deloitte)_

```java
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.price = p.price * :factor WHERE p.category = :category")
    int updatePricesByCategory(@Param("factor") double factor, @Param("category") String category);
}
```

### Situational / Real-world Questions

1. Your Spring Boot application experiences N+1 query issues with JPA. How would you diagnose and fix it? _(Asked in HCL)_
2. Write a JPA repository query to soft-delete records by setting a deleted flag. _(Asked in HCL)_

```java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.deleted = true WHERE u.id = :id")
    int softDeleteById(@Param("id") Long id);
}
```

---

## 5. Spring Security

### Basic Questions

1. What is Spring Security, and how does it integrate with Spring Boot? _(Asked in TCS, Infosys)_
2. What is the purpose of the `@EnableWebSecurity` annotation? _(Asked in Capgemini)_

### Basic Code Questions

1. Write a Spring Security configuration to enable basic authentication for a REST API. _(Asked in TCS)_

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
            .httpBasic();
        return http.build();
    }
}
```

### Intermediate Questions

1. How do you implement role-based access control in Spring Security? _(Asked in Wipro)_
2. What is the difference between authentication and authorization in Spring Security? _(Asked in Accenture)_

### Intermediate Code Questions

1. Write a configuration to secure an endpoint with a specific role. _(Asked in Wipro)_

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth ->
            auth.requestMatchers("/api/admin/**").hasRole("ADMIN")
            .anyRequest().authenticated()
        ).httpBasic();
        return http.build();
    }
}
```

### Advanced Questions

1. How do you implement JWT-based authentication in Spring Boot? _(Asked in Cognizant)_
2. What is the role of the `AuthenticationManager` in Spring Security? _(Asked in TCS Digital)_

### Advanced Code Questions

1. Write a configuration to enable JWT authentication in Spring Boot. _(Asked in Cognizant)_

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtTokenFilter jwtTokenFilter) throws Exception {
        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
            .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }
}
```

### Tough Questions

1. How would you secure a microservices architecture with Spring Security and OAuth2? _(Asked in Deloitte)_

### Tough Code Questions

1. Write a configuration to secure a microservice with OAuth2 resource server. _(Asked in Deloitte)_

```java
@Configuration
@EnableResourceServer
public class ResourceServerConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
            .oauth2ResourceServer().jwt();
        return http.build();
    }
}
```

### Situational / Real-world Questions

1. Your application’s authentication is slow under high load. How would you optimize Spring Security? _(Asked in HCL)_
2. Write a configuration to handle CSRF protection for a REST API. _(Asked in HCL)_

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
            .csrf().disable();
        return http.build();
    }
}
```

---

## 6. Spring Boot Actuator

### Basic Questions

1. What is Spring Boot Actuator, and what are its key features? _(Asked in TCS, Infosys)_
2. How do you enable Actuator endpoints in a Spring Boot application? _(Asked in Capgemini)_

### Basic Code Questions

1. Write a configuration to enable specific Actuator endpoints. _(Asked in TCS)_

```properties
management.endpoints.web.exposure.include=health,info,metrics
```

### Intermediate Questions

1. How do you customize Actuator endpoints in Spring Boot? _(Asked in Wipro)_
2. What is the purpose of the `/health` and `/metrics` endpoints? _(Asked in Accenture)_

### Intermediate Code Questions

1. Write a custom Actuator endpoint to expose application-specific information. _(Asked in Wipro)_

```java
@Endpoint(id = "custom")
public class CustomEndpoint {
    @ReadOperation
    public Map<String, String> customInfo() {
        return Map.of("status", "Application is running");
    }
}
```

### Advanced Questions

1. How do you secure Actuator endpoints in a production environment? _(Asked in Cognizant)_

### Advanced Code Questions

1. Write a configuration to secure Actuator endpoints with Spring Security. _(Asked in Cognizant)_

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth ->
            auth.requestMatchers("/actuator/**").hasRole("ADMIN")
            .anyRequest().authenticated()
        ).httpBasic();
        return http.build();
    }
}
```

### Tough Questions

1. How would you use Actuator to monitor and troubleshoot a microservices architecture? _(Asked in Deloitte)_

### Situational / Real-world Questions

1. Your application’s Actuator endpoints expose sensitive data. How would you secure and manage them? _(Asked in HCL)_

---

## 7. Transaction Management

### Basic Questions

1. What is transaction management in Spring Boot, and why is it important? _(Asked in TCS, Infosys)_
2. How does the `@Transactional` annotation work in Spring Boot? _(Asked in Capgemini)_

### Basic Code Questions

1. Write a service method with `@Transactional` to save a user and their profile. _(Asked in TCS)_

```java
@Service
public class UserService {
    @Transactional
    public void saveUserAndProfile(User user, Profile profile) {
        userRepository.save(user);
        profileRepository.save(profile);
    }
}
```

### Intermediate Questions

1. What is the difference between declarative and programmatic transaction management? _(Asked in Wipro)_
2. How do you handle transaction rollback in Spring Boot? _(Asked in Accenture)_

### Intermediate Code Questions

1. Write a service method to handle rollback on a specific exception. _(Asked in Wipro)_

```java
@Service
public class OrderService {
    @Transactional(rollbackOn = OrderException.class)
    public void processOrder(Order order) throws OrderException {
        orderRepository.save(order);
        if (order.getTotal() < 0) {
            throw new OrderException("Invalid total");
        }
    }
}
```

### Advanced Questions

1. How do you manage distributed transactions in a Spring Boot microservices architecture? _(Asked in Cognizant)_
2. What is the impact of transaction propagation levels (e.g., REQUIRED, NESTED)? _(Asked in TCS Digital)_

### Tough Questions

1. How would you handle transaction timeouts in a high-latency database operation? _(Asked in Deloitte)_

### Situational / Real-world Questions

1. Your application experiences transaction deadlocks with a database. How would you diagnose and resolve this? _(Asked in HCL)_
2. Write a service method to handle a transaction across multiple database operations with rollback on failure. _(Asked in HCL)_

```java
@Service
public class PaymentService {
    @Transactional
    public void processPayment(Payment payment, Account account) {
        paymentRepository.save(payment);
        accountRepository.updateBalance(account);
    }
}
```

---

## 8. Microservices with Spring Boot

### Basic Questions

1. What are microservices, and how does Spring Boot support them? _(Asked in TCS, Infosys)_
2. What is the role of Spring Cloud in microservices development? _(Asked in Capgemini)_

### Basic Code Questions

1. Write a configuration to enable service discovery with Eureka in Spring Boot. _(Asked in TCS)_

```properties
spring.application.name=microservice
eureka.client.service-url.defaultZone=http://localhost:8761/eureka
```

### Intermediate Questions

1. How do you implement a circuit breaker in Spring Boot using Resilience4j? _(Asked in Wipro)_
2. What is the purpose of the `@LoadBalanced` annotation with RestTemplate? _(Asked in Accenture)_

### Intermediate Code Questions

1. Write a configuration to enable a circuit breaker for a REST client. _(Asked in Wipro)_

```java
@Configuration
public class ResilienceConfig {
    @Bean
    public CircuitBreaker circuitBreaker() {
        return CircuitBreaker.ofDefaults("myService");
    }
}
```

### Advanced Questions

1. How do you implement distributed tracing with Spring Cloud Sleuth and Zipkin? _(Asked in Cognizant)_
2. What is the role of API Gateway in a Spring Boot microservices architecture? _(Asked in TCS Digital)_

### Advanced Code Questions

1. Write a configuration to enable distributed tracing with Spring Cloud Sleuth. _(Asked in Cognizant)_

```properties
spring.sleuth.sampler.probability=1.0
spring.zipkin.base-url=http://localhost:9411
```

### Tough Questions

1. How would you scale a Spring Boot microservices application to handle millions of requests? _(Asked in Amazon)_

### Tough Code Questions

1. Write a REST controller to integrate with an external microservice via Feign client. _(Asked in Amazon)_

```java
@FeignClient(name = "external-service")
public interface ExternalServiceClient {
    @GetMapping("/api/data")
    String getExternalData();
}
```

### Situational / Real-world Questions

1. Your microservices application experiences service outages. How would you implement fault tolerance? _(Asked in HCL)_
2. Write a configuration to enable API Gateway routing in Spring Cloud Gateway. _(Asked in HCL)_

```java
@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
            .route(r -> r.path("/api/**").uri("lb://microservice"))
            .build();
    }
}
```

---

## 9. Error Handling and Debugging

### Basic Questions

1. How do you handle exceptions in a Spring Boot application? _(Asked in TCS, Infosys)_
2. What is the purpose of the `@ControllerAdvice` annotation? _(Asked in Capgemini)_

### Basic Code Questions

1. Write a global exception handler using `@ControllerAdvice`. _(Asked in TCS)_

```java
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
    }
}
```

### Intermediate Questions

1. How do you log errors effectively in a Spring Boot application? _(Asked in Wipro)_
2. What is the difference between `@ExceptionHandler` and `@ControllerAdvice`? _(Asked in Accenture)_

### Intermediate Code Questions

1. Write a custom exception handler for a specific business exception. _(Asked in Wipro)_

```java
@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> handleBusinessException(BusinessException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
```

### Advanced Questions

1. How do you debug a Spring Boot application in production? _(Asked in Cognizant)_
2. What tools do you use to monitor Spring Boot application performance? _(Asked in TCS Digital)_

### Tough Questions

1. How would you handle intermittent errors in a Spring Boot application caused by external services? _(Asked in Deloitte)_

### Situational / Real-world Questions

1. Your application logs sensitive data in error messages. How would you redesign error handling to prevent this? _(Asked in HCL)_
2. Write a configuration to enable logging of request and response details in a Spring Boot application. _(Asked in HCL)_

```properties
logging.level.org.springframework.web=DEBUG
```

---

## 10. Integration with Databases

### Basic Questions

1. How do you configure a database connection in Spring Boot? _(Asked in TCS, Infosys)_
2. What is the role of the `spring.datasource` properties? _(Asked in Capgemini)_

### Basic Code Questions

1. Write an `application.properties` file to configure a MySQL database connection. _(Asked in TCS)_

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=root
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
```

### Intermediate Questions

1. How do you handle database migrations in Spring Boot using Flyway or Liquibase? _(Asked in Wipro)_
2. What is the difference between `CrudRepository` and `JpaRepository`? _(Asked in Accenture)_

### Intermediate Code Questions

1. Write a Flyway migration script to create a users table. _(Asked in Wipro)_

```sql
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE
);
```

### Advanced Questions

1. How do you optimize database connections in a Spring Boot application? _(Asked in Cognizant)_
2. What is the impact of connection pooling on application performance? _(Asked in TCS Digital)_

### Tough Questions

1. How would you handle database connection leaks in a Spring Boot application? _(Asked in Deloitte)_

### Situational / Real-world Questions

1. Your Spring Boot application experiences slow database queries. How would you diagnose and optimize them? _(Asked in HCL)_
2. Write a configuration to enable connection pooling with HikariCP in Spring Boot. _(Asked in HCL)_

```properties
spring.datasource.hikari.maximum-pool-size=10
spring.datasource.hikari.minimum-idle=5
spring.datasource.hikari.idle-timeout=300000
```

---

## 11. Spring Boot Testing

### Basic Questions

1. What are the key testing frameworks used in Spring Boot? _(Asked in TCS, Infosys)_
2. What is the purpose of the `@SpringBootTest` annotation? _(Asked in Capgemini)_

### Basic Code Questions

1. Write a test case for a Spring Boot REST controller using `@SpringBootTest`. _(Asked in TCS)_

```java
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetUserById() throws Exception {
        mockMvc.perform(get("/api/users/1"))
            .andExpect(status().isOk());
    }
}
```

### Intermediate Questions

1. How do you mock dependencies in Spring Boot tests using Mockito? _(Asked in Wipro)_
2. What is the difference between `@MockBean` and `@Mock`? _(Asked in Accenture)_

### Intermediate Code Questions

1. Write a test case to mock a service in a Spring Boot controller test. _(Asked in Wipro)_

```java
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;

    @Test
    void testGetUserById() throws Exception {
        given(userService.findById(1L)).willReturn(new User(1L, "John Doe"));
        mockMvc.perform(get("/api/users/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("John Doe"));
    }
}
```

### Advanced Questions

1. How do you test Spring Data JPA repositories in Spring Boot? _(Asked in Cognizant)_
2. What is the role of `@DataJpaTest` in testing database interactions? _(Asked in TCS Digital)_

### Advanced Code Questions

1. Write a test case for a Spring Data JPA repository using `@DataJpaTest`. _(Asked in Cognizant)_

```java
@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void testFindByEmail() {
        User user = new User(1L, "John Doe");
        user.setEmail("john@example.com");
        userRepository.save(user);
        Optional<User> found = userRepository.findByEmail("john@example.com");
        assertTrue(found.isPresent());
    }
}
```

### Tough Questions

1. How would you test a Spring Boot application with external service dependencies? _(Asked in Deloitte)_

### Tough Code Questions

1. Write a test case to simulate an external API call in a Spring Boot service test. _(Asked in Deloitte)_

```java
@SpringBootTest
public class ExternalServiceTest {
    @MockBean
    private RestTemplate restTemplate;

    @Test
    void testExternalApiCall() {
        given(restTemplate.getForObject(anyString(), eq(String.class)))
            .willReturn("Mocked response");
        String result = externalService.callExternalApi();
        assertEquals("Mocked response", result);
    }
}
```

### Situational / Real-world Questions

1. You inherit a Spring Boot application with no tests. How would you add testing without slowing delivery? _(Asked in HCL)_
2. Write a test case to verify transaction rollback in a Spring Boot service. _(Asked in HCL)_

```java
@SpringBootTest
public class PaymentServiceTest {
    @Autowired
    private PaymentService paymentService;

    @Test
    void testTransactionRollback() {
        assertThrows(PaymentException.class, () -> {
            paymentService.processPayment(new Payment(-100));
        });
    }
}
```

---

## 12. Performance Optimization

### Basic Questions

1. What are common causes of performance issues in Spring Boot applications? _(Asked in TCS, Infosys)_
2. How do you configure caching in a Spring Boot application? _(Asked in Capgemini)_

### Basic Code Questions

1. Write a configuration to enable caching with Spring Boot. _(Asked in TCS)_

```java
@Configuration
@EnableCaching
public class CacheConfig {
    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("products");
    }
}
```

### Intermediate Questions

1. How do you use Spring Boot’s `@Cacheable` annotation? _(Asked in Wipro)_
2. What is the role of Spring Boot’s async methods for performance? _(Asked in Accenture)_

### Intermediate Code Questions

1. Write a service method to cache product data using `@Cacheable`. _(Asked in Wipro)_

```java
@Service
public class ProductService {
    @Cacheable("products")
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
}
```

### Advanced Questions

1. How do you optimize Spring Boot’s startup time for a large application? _(Asked in Cognizant)_
2. What is the impact of lazy initialization on Spring Boot performance? _(Asked in TCS Digital)_

### Tough Questions

1. How would you optimize a Spring Boot application for high throughput in a microservices environment? _(Asked in Amazon)_

### Situational / Real-world Questions

1. Your Spring Boot application is slow under heavy traffic. How would you identify and resolve the bottleneck? _(Asked in HCL)_
2. Write a configuration to enable async processing for a time-consuming task. _(Asked in HCL)_

```java
@Configuration
@EnableAsync
public class AsyncConfig {
    @Bean
    public Executor taskExecutor() {
        return Executors.newFixedThreadPool(10);
    }
}
@Service
public class TaskService {
    @Async
    public CompletableFuture<String> performTask() {
        return CompletableFuture.completedFuture("Task completed");
    }
}
```

---

## 13. Spring Boot with Microservices Architecture

### Basic Questions

1. What are the benefits of using Spring Boot for microservices? _(Asked in TCS, Infosys)_
2. How does Spring Boot support service discovery with Eureka? _(Asked in Capgemini)_

### Basic Code Questions

1. Write a configuration to register a Spring Boot application with Eureka. _(Asked in TCS)_

```properties
spring.application.name=microservice
eureka.client.service-url.defaultZone=http://localhost:8761/eureka
```

### Intermediate Questions

1. How do you implement inter-service communication in Spring Boot microservices? _(Asked in Wipro)_
2. What is the role of Spring Cloud Config in microservices? _(Asked in Accenture)_

### Intermediate Code Questions

1. Write a configuration to fetch properties from Spring Cloud Config Server. _(Asked in Wipro)_

```properties
spring.config.import=configserver:http://localhost:8888
spring.application.name=my-service
```

### Advanced Questions

1. How do you implement retry mechanisms for external service calls in Spring Boot? _(Asked in Cognizant)_
2. What is the role of Spring Cloud Gateway in routing microservices? _(Asked in TCS Digital)_

### Advanced Code Questions

1. Write a configuration to enable retry for a REST client in Spring Boot. _(Asked in Cognizant)_

```java
@Configuration
public class RestConfig {
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
        return restTemplate;
    }
}
```

### Tough Questions

1. How would you design a fault-tolerant microservices architecture with Spring Boot? _(Asked in Deloitte)_

### Tough Code Questions

1. Write a configuration to enable load balancing with Ribbon in Spring Boot. _(Asked in Deloitte)_

```java
@Configuration
public class RibbonConfig {
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
```

### Situational / Real-world Questions

1. Your microservices application experiences latency in inter-service calls. How would you optimize it? _(Asked in HCL)_
2. Write a configuration to enable distributed tracing with Zipkin in a microservices architecture. _(Asked in HCL)_

```properties
spring.sleuth.sampler.probability=1.0
spring.zipkin.base-url=http://localhost:9411
```
