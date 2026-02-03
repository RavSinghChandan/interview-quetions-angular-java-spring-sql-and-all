# Spring Boot Interview Questions for Senior Java Backend Developers (Full Stack)

This document contains the most frequently asked Spring Boot interview questions, including code-based and configuration-based questions, curated for senior Java serviceBasedMNCLevel.backend developers transitioning to full-stack roles. Questions are organized by topic and include those repeatedly asked in companyInterviews by companies like **TCS, Infosys, Cognizant, Capgemini**, etc., along with additional critical questions to ensure comprehensive coverage for senior-level companyInterviews.

---

## 1. Spring Boot Basics

### Basic Questions

1. **What is Spring Boot, and how does it differ from the Spring Framework?** *(Asked in TCS, Infosys)*

**🧩 Foundation:** Spring Boot is an opinionated framework built on top of the Spring Framework that simplifies the development of standalone, production-ready Spring applications with minimal configuration.

**⚙️ Function:** Spring Boot reduces boilerplate code and configuration by providing auto-configuration, embedded servers, and starter dependencies, making it easier to create Spring applications.

**🚀 Features:**
- **Auto-configuration:** Automatically configures beans based on classpath
- **Embedded Servers:** Built-in Tomcat, Jetty, or Undertow servers
- **Starter Dependencies:** Simplified dependency management
- **Actuator:** Production-ready features for monitoring and metrics
- **No XML Configuration:** Convention over configuration approach
- **Standalone Applications:** Self-contained executable JAR files

**🔁 Flow:**
```java
// 1. Traditional Spring Framework Setup
@Configuration
@EnableWebMvc
@ComponentScan("com.example")
public class WebConfig {
    // Manual configuration required
}

// 2. Spring Boot Equivalent
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

// 3. Key Differences
// Spring Framework: Requires manual configuration
// Spring Boot: Auto-configuration based on classpath

// 4. Embedded Server Example
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello, Spring Boot!";
    }
}
// No need to configure web.xml or servlet container
```

**🐞 Fixes:** Use Spring Boot for new applications, leverage auto-configuration, avoid manual configuration when possible, and use starter dependencies for consistent versions.

---

2. **What is the purpose of the `@SpringBootApplication` annotation?** *(Asked in Capgemini)*

**🧩 Foundation:** `@SpringBootApplication` is a convenience annotation that adds all of the following: `@Configuration`, `@EnableAutoConfiguration`, and `@ComponentScan`.

**⚙️ Function:** This annotation marks the main class of a Spring Boot application and enables auto-configuration, component scanning, and configuration capabilities in a single annotation.

**🚀 Features:**
- **@Configuration:** Marks the class as a source of bean definitions
- **@EnableAutoConfiguration:** Enables Spring Boot's auto-configuration mechanism
- **@ComponentScan:** Enables component scanning for Spring beans
- **Convenience:** Reduces boilerplate code
- **Flexibility:** Can be customized with additional attributes

**🔁 Flow:**
```java
// 1. Basic @SpringBootApplication
@SpringBootApplication
public class MyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}

// 2. Equivalent to writing:
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class MyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}

// 3. Customized @SpringBootApplication
@SpringBootApplication(
    scanBasePackages = "com.example.custom",
    exclude = {DataSourceAutoConfiguration.class}
)
public class CustomApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomApplication.class, args);
    }
}

// 4. Component Scanning Example
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);
        
        // All components in the same package and sub-packages are scanned
        String[] beanNames = context.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            System.out.println("Bean: " + beanName);
        }
    }
}
```

**🐞 Fixes:** Place `@SpringBootApplication` on the main class, ensure it's in the root package for component scanning, and customize scan base packages if needed.

---

3. **How does Spring Boot auto-configuration work?** *(Asked in Wipro)*

**🧩 Foundation:** Auto-configuration is Spring Boot's mechanism to automatically configure beans based on the classpath, existing beans, and property settings, reducing the need for manual configuration.

**⚙️ Function:** Auto-configuration analyzes the application context and classpath to determine what beans are needed and automatically configures them with sensible defaults.

**🚀 Features:**
- **Conditional Configuration:** Uses `@ConditionalOn*` annotations
- **Classpath Analysis:** Detects available classes and libraries
- **Property-Based Configuration:** Uses application properties for customization
- **Fallback Mechanisms:** Provides sensible defaults
- **Override Capability:** Can be disabled or customized
- **Starter Dependencies:** Triggers specific auto-configurations

**🔁 Flow:**
```java
// 1. Auto-configuration Class Example
@Configuration
@ConditionalOnClass(DataSource.class)
@EnableConfigurationProperties(DataSourceProperties.class)
public class DataSourceAutoConfiguration {
    
    @Bean
    @ConditionalOnMissingBean
    public DataSource dataSource(DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().build();
    }
}

// 2. Conditional Configuration
@Configuration
@ConditionalOnClass(WebMvcConfigurer.class)
@ConditionalOnMissingBean(WebMvcConfigurationSupport.class)
public class WebMvcAutoConfiguration {
    // Auto-configuration for Web MVC
}

// 3. Property-Based Configuration
@ConfigurationProperties(prefix = "spring.datasource")
public class DataSourceProperties {
    private String url;
    private String username;
    private String password;
    // getters and setters
}

// 4. application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=root
spring.datasource.password=password

// 5. Custom Auto-configuration
@Configuration
@ConditionalOnClass(MyService.class)
@ConditionalOnProperty(name = "my.service.enabled", havingValue = "true")
public class MyServiceAutoConfiguration {
    
    @Bean
    @ConditionalOnMissingBean
    public MyService myService() {
        return new MyServiceImpl();
    }
}
```

**🐞 Fixes:** Understand conditional annotations, use properties for customization, avoid conflicts with manual configurations, and leverage starter dependencies.

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

1. **What are Spring Boot starters, and how do they simplify dependency management?** *(Asked in Accenture)*

**🧩 Foundation:** Spring Boot starters are a set of convenient dependency descriptors that you can include in your application to get a curated set of dependencies for a specific functionality.

**⚙️ Function:** Starters simplify dependency management by providing pre-configured sets of dependencies with compatible versions, reducing version conflicts and configuration complexity.

**🚀 Features:**
- **Curated Dependencies:** Pre-selected compatible versions
- **Transitive Dependencies:** Automatically includes required dependencies
- **Version Management:** Handles version conflicts automatically
- **Functionality Grouping:** Groups related dependencies together
- **Custom Starters:** Can create application-specific starters
- **Reduced Boilerplate:** Less dependency management overhead

**🔁 Flow:**
```xml
<!-- 1. Spring Boot Starter Web -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<!-- Includes: spring-web, spring-webmvc, tomcat-embed-core, jackson-databind, etc. -->

<!-- 2. Spring Boot Starter Data JPA -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<!-- Includes: spring-data-jpa, hibernate-core, spring-jdbc, etc. -->

<!-- 3. Spring Boot Starter Security -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
<!-- Includes: spring-security-web, spring-security-config, etc. -->

<!-- 4. Custom Starter Example -->
<dependency>
    <groupId>com.example</groupId>
    <artifactId>my-custom-starter</artifactId>
    <version>1.0.0</version>
</dependency>
```

**🐞 Fixes:** Use official starters when possible, avoid mixing starter and manual dependencies, check starter documentation for included dependencies, and create custom starters for reusable configurations.

---

2. **How do you configure properties in a Spring Boot application?** *(Asked in Infosys)*

**🧩 Foundation:** Spring Boot provides multiple ways to configure application properties including properties files, YAML files, environment variables, command line arguments, and programmatic configuration.

**⚙️ Function:** Configuration properties allow you to externalize configuration, making applications environment-specific and easier to manage across different deployment scenarios.

**🚀 Features:**
- **Properties Files:** application.properties and application.yml
- **Environment-Specific:** application-{profile}.properties
- **Environment Variables:** System and application environment variables
- **Command Line:** Runtime configuration via command line arguments
- **Type Safety:** `@ConfigurationProperties` for type-safe configuration
- **Validation:** Built-in validation support for configuration properties

**🔁 Flow:**
```properties
# 1. application.properties
server.port=8080
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=root
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# 2. application.yml equivalent
server:
  port: 8080
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/mydb
    username: root
    password: password
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

# 3. Environment-specific properties
# application-dev.properties
spring.datasource.url=jdbc:mysql://localhost:3306/devdb
logging.level.com.example=DEBUG

# application-prod.properties
spring.datasource.url=jdbc:mysql://prod-server:3306/proddb
logging.level.com.example=WARN
```

```java
// 4. Type-safe Configuration Properties
@ConfigurationProperties(prefix = "app")
@Component
public class AppProperties {
    private String name;
    private int timeout;
    private List<String> allowedOrigins;
    
    // getters and setters
}

// 5. Using Configuration Properties
@Service
public class MyService {
    private final AppProperties appProperties;
    
    public MyService(AppProperties appProperties) {
        this.appProperties = appProperties;
    }
    
    public void doSomething() {
        System.out.println("App name: " + appProperties.getName());
    }
}

// 6. Validation in Configuration Properties
@ConfigurationProperties(prefix = "app")
@Validated
public class AppProperties {
    @NotNull
    private String name;
    
    @Min(1)
    @Max(100)
    private int timeout;
    
    // getters and setters
}
```

**🐞 Fixes:** Use environment-specific properties, validate configuration properties, avoid hardcoding values, and use type-safe configuration when possible.

### Intermediate Code Questions

1. Write an `application.properties` file to configure a server port and serviceBasedMNCLevel.database connection. _(Asked in Capgemini)_

```properties
server.port=8080
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=root
spring.datasource.password=password
```

### Advanced Questions

1. How does Spring Boot's `SpringApplication` class initialize the application context? _(Asked in Cognizant)_
2. What is the role of the AutoConfiguration classes in Spring Boot? _(Asked in TCS Digital)_

### Tough Questions

1. How would you customize Spring Boot's auto-configuration for a specific use case? _(Asked in Deloitte)_

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

1. **What is dependency injection in Spring Boot, and why is it important?** *(Asked in TCS, Infosys)*

**🧩 Foundation:** Dependency Injection (DI) is a design pattern where dependencies are provided to a class from the outside rather than being created internally, promoting loose coupling and testability.

**⚙️ Function:** Spring Boot's DI container manages object creation, lifecycle, and dependency resolution, automatically injecting required dependencies into beans based on configuration and annotations.

**🚀 Features:**
- **Loose Coupling:** Classes depend on abstractions, not concrete implementations
- **Testability:** Easy to mock dependencies for unit testing
- **Flexibility:** Can easily swap implementations without code changes
- **Lifecycle Management:** Spring manages bean creation, initialization, and destruction
- **Circular Dependency Resolution:** Handles complex dependency relationships
- **Scope Management:** Supports different bean scopes (singleton, prototype, etc.)

**🔁 Flow:**
```java
// 1. Traditional Tight Coupling
public class UserService {
    private UserRepository userRepository = new UserRepositoryImpl(); // Tight coupling
}

// 2. Dependency Injection with Constructor
@Service
public class UserService {
    private final UserRepository userRepository;
    
    @Autowired // Constructor injection (recommended)
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}

// 3. Dependency Injection with Field Injection
@Service
public class UserService {
    @Autowired // Field injection (not recommended)
    private UserRepository userRepository;
}

// 4. Dependency Injection with Setter Injection
@Service
public class UserService {
    private UserRepository userRepository;
    
    @Autowired // Setter injection
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}

// 5. Interface-based Dependency Injection
public interface UserRepository {
    User findById(Long id);
}

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Override
    public User findById(Long id) {
        // Implementation
        return new User(id, "John Doe");
    }
}

@Service
public class UserService {
    private final UserRepository userRepository; // Depends on interface
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
```

**🐞 Fixes:** Use constructor injection for required dependencies, prefer interfaces over concrete classes, avoid field injection, and use `@Qualifier` for multiple implementations.

---

2. **What is the difference between `@Component`, `@Service`, and `@Repository` annotations?** *(Asked in Capgemini)*

**🧩 Foundation:** All three annotations are stereotypes that mark classes as Spring-managed beans, but they serve different purposes and provide semantic meaning to the application architecture.

**⚙️ Function:** These annotations help Spring identify and manage beans while providing clear architectural boundaries and enabling specific functionality based on the stereotype.

**🚀 Features:**
- **@Component:** Generic stereotype for any Spring-managed component
- **@Service:** Indicates business logic layer components
- **@Repository:** Indicates data access layer components with exception translation
- **Semantic Meaning:** Provides clear architectural boundaries
- **Exception Translation:** @Repository automatically translates data access exceptions
- **AOP Support:** Enables aspect-oriented programming features

**🔁 Flow:**
```java
// 1. @Component - Generic Component
@Component
public class EmailService {
    public void sendEmail(String to, String subject, String body) {
        // Email sending logic
    }
}

// 2. @Service - Business Logic Layer
@Service
public class UserService {
    private final UserRepository userRepository;
    private final EmailService emailService;
    
    public UserService(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }
    
    public User createUser(User user) {
        // Business logic
        User savedUser = userRepository.save(user);
        emailService.sendEmail(user.getEmail(), "Welcome", "Account created!");
        return savedUser;
    }
}

// 3. @Repository - Data Access Layer
@Repository
public class UserRepositoryImpl implements UserRepository {
    private final JdbcTemplate jdbcTemplate;
    
    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public User save(User user) {
        // Data access logic with automatic exception translation
        String sql = "INSERT INTO users (name, email) VALUES (?, ?)";
        jdbcTemplate.update(sql, user.getName(), user.getEmail());
        return user;
    }
}

// 4. Custom Stereotype Annotation
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface BusinessService {
    String value() default "";
}

@BusinessService
public class OrderService {
    // Business service implementation
}

// 5. Exception Translation with @Repository
@Repository
public class ProductRepository {
    public Product findById(Long id) {
        try {
            // Database operation that might throw SQLException
            return jdbcTemplate.queryForObject("SELECT * FROM products WHERE id = ?", 
                new Object[]{id}, Product.class);
        } catch (DataAccessException e) {
            // Automatically translated to Spring's DataAccessException
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
    }
}
```

**🐞 Fixes:** Use appropriate stereotypes for semantic clarity, leverage @Repository for data access with exception translation, and create custom stereotypes for domain-specific components.

### Intermediate Questions

1. **What is the difference between constructor injection and setter injection?** *(Asked in Wipro)*

**🧩 Foundation:** Constructor injection and setter injection are two different approaches to dependency injection, each with their own advantages and use cases in Spring Boot applications.

**⚙️ Function:** Constructor injection ensures required dependencies are provided at object creation time, while setter injection allows optional dependencies to be set after object creation.

**🚀 Features:**
- **Constructor Injection:** Immutable dependencies, required at creation time
- **Setter Injection:** Mutable dependencies, can be optional
- **Immutability:** Constructor injection promotes immutable objects
- **Testability:** Both approaches support easy testing
- **Circular Dependencies:** Setter injection can help resolve circular dependencies
- **Performance:** Constructor injection is slightly more performant

**🔁 Flow:**
```java
// 1. Constructor Injection (Recommended)
@Service
public class UserService {
    private final UserRepository userRepository;
    private final EmailService emailService;
    
    // Required dependencies - must be provided
    public UserService(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }
    
    public User createUser(User user) {
        User savedUser = userRepository.save(user);
        emailService.sendEmail(user.getEmail(), "Welcome", "Account created!");
        return savedUser;
    }
}

// 2. Setter Injection (Optional Dependencies)
@Service
public class NotificationService {
    private EmailService emailService;
    private SmsService smsService;
    
    // Optional dependency - can be set later
    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }
    
    @Autowired
    public void setSmsService(SmsService smsService) {
        this.smsService = smsService;
    }
    
    public void sendNotification(String message) {
        if (emailService != null) {
            emailService.sendEmail("user@example.com", "Notification", message);
        }
        if (smsService != null) {
            smsService.sendSms("+1234567890", message);
        }
    }
}

// 3. Mixed Approach
@Service
public class OrderService {
    private final OrderRepository orderRepository; // Required
    private PaymentService paymentService; // Optional
    
    // Constructor for required dependencies
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    
    // Setter for optional dependencies
    @Autowired
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    
    public Order createOrder(Order order) {
        Order savedOrder = orderRepository.save(order);
        if (paymentService != null) {
            paymentService.processPayment(order);
        }
        return savedOrder;
    }
}

// 4. Testing Both Approaches
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    
    @Mock
    private UserRepository userRepository;
    
    @Mock
    private EmailService emailService;
    
    @InjectMocks
    private UserService userService; // Constructor injection works well with @InjectMocks
    
    @Test
    void testCreateUser() {
        // Test implementation
    }
}

// 5. Circular Dependency Resolution with Setter Injection
@Service
public class ServiceA {
    private ServiceB serviceB;
    
    @Autowired
    public void setServiceB(ServiceB serviceB) {
        this.serviceB = serviceB;
    }
}

@Service
public class ServiceB {
    private ServiceA serviceA;
    
    @Autowired
    public void setServiceA(ServiceA serviceA) {
        this.serviceA = serviceA;
    }
}
```

**🐞 Fixes:** Use constructor injection for required dependencies, setter injection for optional dependencies, avoid circular dependencies, and prefer immutability when possible.

---

2. **How do you define a bean using `@Bean` in a configuration class?** *(Asked in Accenture)*

**🧩 Foundation:** The `@Bean` annotation is used in configuration classes to define Spring beans programmatically, allowing fine-grained control over bean creation and configuration.

**⚙️ Function:** `@Bean` methods in `@Configuration` classes tell Spring how to create and configure beans, enabling custom initialization logic and dependency injection.

**🚀 Features:**
- **Programmatic Configuration:** Define beans with custom logic
- **Method-based Bean Definition:** Beans are created by method calls
- **Dependency Injection:** Method parameters are automatically injected
- **Custom Initialization:** Control bean creation and configuration
- **Conditional Beans:** Use with `@ConditionalOn*` annotations
- **Bean Lifecycle:** Control initialization and destruction

**🔁 Flow:**
```java
// 1. Basic @Bean Definition
@Configuration
public class AppConfig {
    
    @Bean
    public UserService userService() {
        return new UserService(userRepository());
    }
    
    @Bean
    public UserRepository userRepository() {
        return new UserRepositoryImpl();
    }
}

// 2. @Bean with Dependencies
@Configuration
public class DatabaseConfig {
    
    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
            .url("jdbc:mysql://localhost:3306/mydb")
            .username("root")
            .password("password")
            .build();
    }
    
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
    
    @Bean
    public UserRepository userRepository(JdbcTemplate jdbcTemplate) {
        return new UserRepositoryImpl(jdbcTemplate);
    }
}

// 3. @Bean with Custom Configuration
@Configuration
public class CacheConfig {
    
    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(Arrays.asList(
            new ConcurrentMapCache("users"),
            new ConcurrentMapCache("products")
        ));
        return cacheManager;
    }
    
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        
        // Custom serialization
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
        template.setDefaultSerializer(serializer);
        
        return template;
    }
}

// 4. Conditional @Bean
@Configuration
public class ConditionalConfig {
    
    @Bean
    @ConditionalOnProperty(name = "app.cache.enabled", havingValue = "true")
    public CacheService cacheService() {
        return new RedisCacheService();
    }
    
    @Bean
    @ConditionalOnMissingBean(CacheService.class)
    public CacheService defaultCacheService() {
        return new InMemoryCacheService();
    }
}

// 5. @Bean with Lifecycle Methods
@Configuration
public class LifecycleConfig {
    
    @Bean(initMethod = "init", destroyMethod = "cleanup")
    public DatabaseService databaseService() {
        return new DatabaseService();
    }
    
    @Bean
    public CustomBean customBean() {
        return new CustomBean() {
            @PostConstruct
            public void init() {
                System.out.println("Custom bean initialized");
            }
            
            @PreDestroy
            public void cleanup() {
                System.out.println("Custom bean destroyed");
            }
        };
    }
}

// 6. @Bean with Qualifiers
@Configuration
public class MultipleBeanConfig {
    
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

**🐞 Fixes:** Use meaningful method names for @Bean methods, provide proper dependencies, use conditional annotations when needed, and handle bean lifecycle appropriately.

### Advanced Questions

1. **How does Spring Boot handle bean lifecycle management?** *(Asked in Cognizant)*

**🧩 Foundation:** Spring Boot manages the complete lifecycle of beans from creation to destruction, providing hooks for custom initialization and cleanup logic.

**⚙️ Function:** The bean lifecycle includes instantiation, dependency injection, initialization, and destruction phases, with various interfaces and annotations to customize each phase.

**🚀 Features:**
- **Instantiation:** Bean creation using constructors or factory methods
- **Dependency Injection:** Injection of required dependencies
- **Initialization:** Post-construction setup and validation
- **Destruction:** Cleanup and resource release
- **Lifecycle Interfaces:** InitializingBean, DisposableBean, BeanPostProcessor
- **Annotations:** @PostConstruct, @PreDestroy for lifecycle hooks

**🔁 Flow:**
```java
// 1. Basic Bean Lifecycle
@Component
public class DatabaseConnection {
    private Connection connection;
    
    // 1. Constructor (Instantiation)
    public DatabaseConnection() {
        System.out.println("DatabaseConnection: Constructor called");
    }
    
    // 2. Dependency Injection
    @Autowired
    public void setDataSource(DataSource dataSource) {
        System.out.println("DatabaseConnection: Dependencies injected");
    }
    
    // 3. Initialization
    @PostConstruct
    public void init() {
        System.out.println("DatabaseConnection: @PostConstruct called");
        // Initialize connection
    }
    
    // 4. Destruction
    @PreDestroy
    public void cleanup() {
        System.out.println("DatabaseConnection: @PreDestroy called");
        // Close connection
    }
}

// 2. Implementing Lifecycle Interfaces
@Component
public class CacheManager implements InitializingBean, DisposableBean {
    
    private Map<String, Object> cache = new HashMap<>();
    
    // InitializingBean interface
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("CacheManager: afterPropertiesSet called");
        // Initialize cache
        cache.put("default", "value");
    }
    
    // DisposableBean interface
    @Override
    public void destroy() throws Exception {
        System.out.println("CacheManager: destroy called");
        // Cleanup cache
        cache.clear();
    }
}

// 3. BeanPostProcessor for Custom Processing
@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {
    
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("Before initialization: " + beanName);
        return bean;
    }
    
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("After initialization: " + beanName);
        return bean;
    }
}

// 4. @Bean with Lifecycle Methods
@Configuration
public class LifecycleConfig {
    
    @Bean(initMethod = "start", destroyMethod = "stop")
    public ApplicationService applicationService() {
        return new ApplicationService();
    }
}

public class ApplicationService {
    
    public void start() {
        System.out.println("ApplicationService: start method called");
        // Start application services
    }
    
    public void stop() {
        System.out.println("ApplicationService: stop method called");
        // Stop application services
    }
}

// 5. Lifecycle with Event Listeners
@Component
public class ApplicationLifecycleListener {
    
    @EventListener
    public void handleContextRefreshed(ContextRefreshedEvent event) {
        System.out.println("Application context refreshed");
    }
    
    @EventListener
    public void handleContextClosed(ContextClosedEvent event) {
        System.out.println("Application context closed");
    }
    
    @EventListener
    public void handleApplicationReady(ApplicationReadyEvent event) {
        System.out.println("Application is ready to serve requests");
    }
}

// 6. Custom Lifecycle Management
@Component
public class ResourceManager implements SmartLifecycle {
    
    private boolean running = false;
    
    @Override
    public void start() {
        System.out.println("ResourceManager: Starting resources");
        running = true;
    }
    
    @Override
    public void stop() {
        System.out.println("ResourceManager: Stopping resources");
        running = false;
    }
    
    @Override
    public boolean isRunning() {
        return running;
    }
    
    @Override
    public boolean isAutoStartup() {
        return true;
    }
    
    @Override
    public void stop(Runnable callback) {
        stop();
        callback.run();
    }
    
    @Override
    public int getPhase() {
        return 0;
    }
}
```

**🐞 Fixes:** Use appropriate lifecycle hooks, handle exceptions in lifecycle methods, implement proper cleanup, and use SmartLifecycle for complex lifecycle management.

---

2. **What is the difference between `@Primary` and `@Qualifier` in dependency injection?** *(Asked in TCS Digital)*

**🧩 Foundation:** `@Primary` and `@Qualifier` are annotations used to resolve ambiguity when multiple beans of the same type exist in the Spring context, but they work in different ways.

**⚙️ Function:** `@Primary` marks a bean as the default choice when multiple candidates exist, while `@Qualifier` provides explicit selection of a specific bean by name or custom qualifier.

**🚀 Features:**
- **@Primary:** Designates the default bean when multiple candidates exist
- **@Qualifier:** Explicitly selects a specific bean by qualifier
- **Ambiguity Resolution:** Both help resolve dependency injection conflicts
- **Flexibility:** @Qualifier provides more precise control
- **Default Behavior:** @Primary affects all injection points
- **Custom Qualifiers:** @Qualifier can use custom annotation-based qualifiers

**🔁 Flow:**
```java
// 1. @Primary Example
@Service
public class EmailService {
    public void sendEmail(String to, String subject, String body) {
        System.out.println("Sending email via EmailService");
    }
}

@Service
@Primary
public class PrimaryEmailService {
    public void sendEmail(String to, String subject, String body) {
        System.out.println("Sending email via PrimaryEmailService");
    }
}

@Service
public class NotificationService {
    private final EmailService emailService; // Will inject PrimaryEmailService
    
    public NotificationService(EmailService emailService) {
        this.emailService = emailService;
    }
}

// 2. @Qualifier Example
@Service
@Qualifier("smtp")
public class SmtpEmailService implements EmailService {
    @Override
    public void sendEmail(String to, String subject, String body) {
        System.out.println("Sending email via SMTP");
    }
}

@Service
@Qualifier("sendgrid")
public class SendGridEmailService implements EmailService {
    @Override
    public void sendEmail(String to, String subject, String body) {
        System.out.println("Sending email via SendGrid");
    }
}

@Service
public class NotificationService {
    private final EmailService emailService;
    
    public NotificationService(@Qualifier("smtp") EmailService emailService) {
        this.emailService = emailService; // Explicitly inject SmtpEmailService
    }
}

// 3. Custom Qualifier Annotations
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface Production {
}

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface Development {
}

@Service
@Production
public class ProductionDatabaseService implements DatabaseService {
    @Override
    public void connect() {
        System.out.println("Connecting to production serviceBasedMNCLevel.database");
    }
}

@Service
@Development
public class DevelopmentDatabaseService implements DatabaseService {
    @Override
    public void connect() {
        System.out.println("Connecting to development serviceBasedMNCLevel.database");
    }
}

@Service
public class DataService {
    private final DatabaseService databaseService;
    
    public DataService(@Production DatabaseService databaseService) {
        this.databaseService = databaseService; // Inject production serviceBasedMNCLevel.database
    }
}

// 4. @Primary vs @Qualifier Priority
@Service
@Primary
public class PrimaryService implements MyService {
    @Override
    public void doSomething() {
        System.out.println("Primary service");
    }
}

@Service
@Qualifier("specific")
public class SpecificService implements MyService {
    @Override
    public void doSomething() {
        System.out.println("Specific service");
    }
}

@Service
public class ClientService {
    private final MyService myService;
    
    // @Qualifier takes precedence over @Primary
    public ClientService(@Qualifier("specific") MyService myService) {
        this.myService = myService; // Will inject SpecificService
    }
}

// 5. Multiple Qualifiers
@Service
@Qualifier("fast")
@Qualifier("reliable")
public class FastReliableService implements MyService {
    @Override
    public void doSomething() {
        System.out.println("Fast and reliable service");
    }
}

@Service
public class ServiceClient {
    private final MyService myService;
    
    public ServiceClient(@Qualifier("fast") MyService myService) {
        this.myService = myService; // Will inject FastReliableService
    }
}

// 6. Configuration-based Bean Selection
@Configuration
public class ServiceConfig {
    
    @Bean
    @Primary
    public MyService primaryService() {
        return new PrimaryServiceImpl();
    }
    
    @Bean
    @Qualifier("backup")
    public MyService backupService() {
        return new BackupServiceImpl();
    }
    
    @Bean
    public ServiceManager serviceManager(@Qualifier("backup") MyService backupService) {
        return new ServiceManager(backupService);
    }
}
```

**🐞 Fixes:** Use @Primary for default beans, @Qualifier for explicit selection, create custom qualifiers for domain-specific selection, and understand precedence rules.

### Tough Questions

1. **How would you resolve circular dependencies in a Spring Boot application?** *(Asked in Deloitte)*

**🧩 Foundation:** Circular dependencies occur when two or more beans depend on each other, creating a dependency cycle that Spring needs to resolve during bean initialization.

**⚙️ Function:** Spring Boot provides several mechanisms to resolve circular dependencies, including setter injection, @Lazy annotation, and restructuring the application design.

**🚀 Features:**
- **Setter Injection:** Breaks circular dependencies by deferring injection
- **@Lazy Annotation:** Delays bean initialization until first use
- **Design Restructuring:** Extract common functionality to break cycles
- **Event-driven Communication:** Use events instead of direct dependencies
- **Interface Segregation:** Split interfaces to reduce coupling
- **Dependency Inversion:** Depend on abstractions, not concrete classes

**🔁 Flow:**
```java
// 1. Circular Dependency Problem
@Service
public class UserService {
    private final OrderService orderService;
    
    public UserService(OrderService orderService) { // Depends on OrderService
        this.orderService = orderService;
    }
    
    public User createUser(User user) {
        // User creation logic
        return user;
    }
}

@Service
public class OrderService {
    private final UserService userService;
    
    public OrderService(UserService userService) { // Depends on UserService
        this.userService = userService;
    }
    
    public Order createOrder(Order order) {
        // Order creation logic
        return order;
    }
}

// 2. Solution 1: Setter Injection
@Service
public class UserService {
    private OrderService orderService;
    
    public User createUser(User user) {
        // User creation logic
        return user;
    }
    
    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}

@Service
public class OrderService {
    private UserService userService;
    
    public Order createOrder(Order order) {
        // Order creation logic
        return order;
    }
    
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}

// 3. Solution 2: @Lazy Annotation
@Service
public class UserService {
    private final OrderService orderService;
    
    public UserService(@Lazy OrderService orderService) {
        this.orderService = orderService;
    }
    
    public User createUser(User user) {
        // User creation logic
        return user;
    }
}

@Service
public class OrderService {
    private final UserService userService;
    
    public OrderService(@Lazy UserService userService) {
        this.userService = userService;
    }
    
    public Order createOrder(Order order) {
        // Order creation logic
        return order;
    }
}

// 4. Solution 3: Design Restructuring
@Service
public class UserService {
    private final UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public User createUser(User user) {
        return userRepository.save(user);
    }
}

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    
    public OrderService(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }
    
    public Order createOrder(Order order) {
        // Use UserRepository directly instead of UserService
        User user = userRepository.findById(order.getUserId()).orElseThrow();
        return orderRepository.save(order);
    }
}

// 5. Solution 4: Event-driven Communication
@Service
public class UserService {
    private final ApplicationEventPublisher eventPublisher;
    
    public UserService(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }
    
    public User createUser(User user) {
        User savedUser = userRepository.save(user);
        eventPublisher.publishEvent(new UserCreatedEvent(savedUser));
        return savedUser;
    }
}

@Service
public class OrderService {
    
    @EventListener
    public void handleUserCreated(UserCreatedEvent event) {
        // Handle user creation event
        System.out.println("User created: " + event.getUser().getId());
    }
}

// 6. Solution 5: Interface Segregation
public interface UserOperations {
    User createUser(User user);
}

public interface OrderOperations {
    Order createOrder(Order order);
}

@Service
public class UserService implements UserOperations {
    private final OrderOperations orderOperations;
    
    public UserService(OrderOperations orderOperations) {
        this.orderOperations = orderOperations;
    }
    
    @Override
    public User createUser(User user) {
        // User creation logic
        return user;
    }
}

@Service
public class OrderService implements OrderOperations {
    private final UserOperations userOperations;
    
    public OrderService(UserOperations userOperations) {
        this.userOperations = userOperations;
    }
    
    @Override
    public Order createOrder(Order order) {
        // Order creation logic
        return order;
    }
}

// 7. Solution 6: Dependency Inversion
public interface UserRepository {
    User save(User user);
}

public interface OrderRepository {
    Order save(Order order);
}

@Service
public class UserService {
    private final UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public User createUser(User user) {
        return userRepository.save(user);
    }
}

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }
}
```

**🐞 Fixes:** Avoid circular dependencies through better design, use setter injection or @Lazy when necessary, restructure code to eliminate cycles, and prefer event-driven communication.

### Situational / Real-world Questions

1. **Your application has conflicting bean definitions. How would you debug and resolve this?** *(Asked in HCL)*

**🧩 Foundation:** Conflicting bean definitions occur when Spring finds multiple beans of the same type or with the same name, causing ambiguity in dependency injection.

**⚙️ Function:** Debugging involves identifying the conflicting beans, understanding their sources, and applying appropriate resolution strategies like @Primary, @Qualifier, or bean exclusion.

**🚀 Features:**
- **Bean Name Conflicts:** Multiple beans with the same name
- **Type Conflicts:** Multiple beans of the same type
- **Component Scanning Issues:** Duplicate beans from different packages
- **Auto-configuration Conflicts:** Conflicts with Spring Boot auto-configuration
- **Profile-based Conflicts:** Beans defined in different profiles
- **Conditional Bean Conflicts:** Beans with conflicting conditions

**🔁 Flow:**
```java
// 1. Bean Name Conflict
@Component("userService")
public class UserService {
    // Implementation
}

@Configuration
public class AppConfig {
    @Bean("userService") // Conflict: same name
    public UserService userService() {
        return new UserService();
    }
}

// 2. Type Conflict
@Service
public class EmailService {
    public void sendEmail(String to, String subject, String body) {
        System.out.println("Sending email via EmailService");
    }
}

@Configuration
public class EmailConfig {
    @Bean
    public EmailService emailService() { // Conflict: same type
        return new EmailService();
    }
}

// 3. Debugging Bean Conflicts
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);
        
        // List all beans to identify conflicts
        String[] beanNames = context.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            BeanDefinition beanDefinition = context.getBeanDefinition(beanName);
            System.out.println("Bean: " + beanName + " -> " + beanDefinition.getBeanClassName());
        }
        
        // Check for specific bean type conflicts
        Map<String, EmailService> emailServices = context.getBeansOfType(EmailService.class);
        System.out.println("EmailService beans: " + emailServices.keySet());
    }
}

// 4. Resolution Strategy 1: @Primary
@Service
@Primary
public class PrimaryEmailService implements EmailService {
    @Override
    public void sendEmail(String to, String subject, String body) {
        System.out.println("Sending email via PrimaryEmailService");
    }
}

@Service
public class SecondaryEmailService implements EmailService {
    @Override
    public void sendEmail(String to, String subject, String body) {
        System.out.println("Sending email via SecondaryEmailService");
    }
}

// 5. Resolution Strategy 2: @Qualifier
@Service
@Qualifier("smtp")
public class SmtpEmailService implements EmailService {
    @Override
    public void sendEmail(String to, String subject, String body) {
        System.out.println("Sending email via SMTP");
    }
}

@Service
@Qualifier("sendgrid")
public class SendGridEmailService implements EmailService {
    @Override
    public void sendEmail(String to, String subject, String body) {
        System.out.println("Sending email via SendGrid");
    }
}

@Service
public class NotificationService {
    private final EmailService emailService;
    
    public NotificationService(@Qualifier("smtp") EmailService emailService) {
        this.emailService = emailService;
    }
}

// 6. Resolution Strategy 3: Bean Exclusion
@SpringBootApplication(exclude = {
    DataSourceAutoConfiguration.class // Exclude conflicting auto-configuration
})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

// 7. Resolution Strategy 4: Conditional Beans
@Configuration
public class ConditionalConfig {
    
    @Bean
    @ConditionalOnMissingBean(EmailService.class)
    public EmailService defaultEmailService() {
        return new DefaultEmailService();
    }
    
    @Bean
    @ConditionalOnProperty(name = "email.provider", havingValue = "smtp")
    public EmailService smtpEmailService() {
        return new SmtpEmailService();
    }
    
    @Bean
    @ConditionalOnProperty(name = "email.provider", havingValue = "sendgrid")
    public EmailService sendGridEmailService() {
        return new SendGridEmailService();
    }
}

// 8. Debugging with Actuator
// application.properties
management.endpoints.web.exposure.include=beans
management.endpoint.beans.enabled=true

// Access: http://localhost:8080/actuator/beans
// Shows all beans with their definitions and dependencies

// 9. Custom Bean Post Processor for Debugging
@Component
public class BeanConflictDetector implements BeanPostProcessor {
    
    private final Map<String, List<String>> beanTypeMap = new HashMap<>();
    
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        String beanType = bean.getClass().getName();
        beanTypeMap.computeIfAbsent(beanType, k -> new ArrayList<>()).add(beanName);
        
        // Check for conflicts
        List<String> beansOfType = beanTypeMap.get(beanType);
        if (beansOfType.size() > 1) {
            System.out.println("WARNING: Multiple beans of type " + beanType + ": " + beansOfType);
        }
        
        return bean;
    }
}
```

**🐞 Fixes:** Use @Primary for default beans, @Qualifier for explicit selection, exclude conflicting auto-configurations, use conditional beans, and implement proper bean naming conventions.

---

2. **Write a configuration to define two beans of the same type with different qualifiers.** *(Asked in HCL)*

**🧩 Foundation:** Defining multiple beans of the same type with different qualifiers allows for flexible dependency injection based on specific requirements or contexts.

**⚙️ Function:** This approach enables the same interface to have multiple implementations, each serving different purposes or configurations, while maintaining type safety and explicit selection.

**🚀 Features:**
- **Multiple Implementations:** Same interface, different implementations
- **Qualifier-based Selection:** Explicit bean selection using @Qualifier
- **Context-specific Beans:** Different beans for different scenarios
- **Configuration Flexibility:** Easy to switch implementations
- **Type Safety:** Maintains compile-time type checking
- **Testing Support:** Easy to mock specific implementations

**🔁 Flow:**
```java
// 1. Basic Interface
public interface PaymentService {
    void processPayment(Payment payment);
}

// 2. Multiple Implementations
@Service
@Qualifier("creditCard")
public class CreditCardPaymentService implements PaymentService {
    @Override
    public void processPayment(Payment payment) {
        System.out.println("Processing credit card payment: " + payment.getAmount());
    }
}

@Service
@Qualifier("paypal")
public class PayPalPaymentService implements PaymentService {
    @Override
    public void processPayment(Payment payment) {
        System.out.println("Processing PayPal payment: " + payment.getAmount());
    }
}

@Service
@Qualifier("bankTransfer")
public class BankTransferPaymentService implements PaymentService {
    @Override
    public void processPayment(Payment payment) {
        System.out.println("Processing bank transfer: " + payment.getAmount());
    }
}

// 3. Configuration-based Bean Definition
@Configuration
public class PaymentConfig {
    
    @Bean
    @Qualifier("stripe")
    public PaymentService stripePaymentService() {
        return new StripePaymentService();
    }
    
    @Bean
    @Qualifier("square")
    public PaymentService squarePaymentService() {
        return new SquarePaymentService();
    }
}

// 4. Using Qualifiers in Services
@Service
public class OrderService {
    private final PaymentService creditCardPayment;
    private final PaymentService paypalPayment;
    
    public OrderService(
        @Qualifier("creditCard") PaymentService creditCardPayment,
        @Qualifier("paypal") PaymentService paypalPayment
    ) {
        this.creditCardPayment = creditCardPayment;
        this.paypalPayment = paypalPayment;
    }
    
    public void processOrder(Order order) {
        if (order.getPaymentMethod().equals("CREDIT_CARD")) {
            creditCardPayment.processPayment(order.getPayment());
        } else if (order.getPaymentMethod().equals("PAYPAL")) {
            paypalPayment.processPayment(order.getPayment());
        }
    }
}

// 5. Custom Qualifier Annotations
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface Secure {
}

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface Fast {
}

@Service
@Secure
public class SecurePaymentService implements PaymentService {
    @Override
    public void processPayment(Payment payment) {
        System.out.println("Processing secure payment with encryption");
    }
}

@Service
@Fast
public class FastPaymentService implements PaymentService {
    @Override
    public void processPayment(Payment payment) {
        System.out.println("Processing fast payment with minimal validation");
    }
}

@Service
public class PaymentProcessor {
    private final PaymentService securePayment;
    private final PaymentService fastPayment;
    
    public PaymentProcessor(
        @Secure PaymentService securePayment,
        @Fast PaymentService fastPayment
    ) {
        this.securePayment = securePayment;
        this.fastPayment = fastPayment;
    }
    
    public void processHighValuePayment(Payment payment) {
        securePayment.processPayment(payment);
    }
    
    public void processLowValuePayment(Payment payment) {
        fastPayment.processPayment(payment);
    }
}

// 6. Profile-based Bean Selection
@Service
@Profile("production")
@Qualifier("production")
public class ProductionPaymentService implements PaymentService {
    @Override
    public void processPayment(Payment payment) {
        System.out.println("Processing payment in production environment");
    }
}

@Service
@Profile("development")
@Qualifier("development")
public class DevelopmentPaymentService implements PaymentService {
    @Override
    public void processPayment(Payment payment) {
        System.out.println("Processing payment in development environment");
    }
}

// 7. Conditional Bean Definition
@Configuration
public class ConditionalPaymentConfig {
    
    @Bean
    @Qualifier("primary")
    @ConditionalOnProperty(name = "payment.provider", havingValue = "stripe")
    public PaymentService stripePaymentService() {
        return new StripePaymentService();
    }
    
    @Bean
    @Qualifier("primary")
    @ConditionalOnProperty(name = "payment.provider", havingValue = "paypal")
    public PaymentService paypalPaymentService() {
        return new PayPalPaymentService();
    }
    
    @Bean
    @Qualifier("backup")
    @ConditionalOnMissingBean(name = "backupPaymentService")
    public PaymentService backupPaymentService() {
        return new BackupPaymentService();
    }
}

// 8. Testing with Multiple Beans
@ExtendWith(SpringExtension.class)
@SpringBootTest
class PaymentServiceTest {
    
    @Autowired
    @Qualifier("creditCard")
    private PaymentService creditCardPayment;
    
    @Autowired
    @Qualifier("paypal")
    private PaymentService paypalPayment;
    
    @Test
    void testCreditCardPayment() {
        Payment payment = new Payment(100.0);
        creditCardPayment.processPayment(payment);
        // Assert credit card specific behavior
    }
    
    @Test
    void testPayPalPayment() {
        Payment payment = new Payment(100.0);
        paypalPayment.processPayment(payment);
        // Assert PayPal specific behavior
    }
}

// 9. Dynamic Bean Selection
@Service
public class PaymentSelector {
    
    private final Map<String, PaymentService> paymentServices;
    
    public PaymentSelector(@Qualifier("creditCard") PaymentService creditCard,
                          @Qualifier("paypal") PaymentService paypal,
                          @Qualifier("bankTransfer") PaymentService bankTransfer) {
        paymentServices = Map.of(
            "CREDIT_CARD", creditCard,
            "PAYPAL", paypal,
            "BANK_TRANSFER", bankTransfer
        );
    }
    
    public PaymentService selectPaymentService(String paymentMethod) {
        return paymentServices.get(paymentMethod);
    }
}
```

**🐞 Fixes:** Use meaningful qualifier names, create custom qualifier annotations for domain-specific selection, implement proper error handling for missing beans, and use conditional beans for environment-specific configurations.

---

## 3. REST APIs with Spring Boot

### Basic Questions

1. **What is a REST API, and how does Spring Boot support it?** *(Asked in TCS, Capgemini)*

**🧩 Foundation:** REST (Representational State Transfer) is an architectural style for designing networked applications that use HTTP methods to perform CRUD operations on resources, and Spring Boot provides comprehensive support for building RESTful APIs.

**⚙️ Function:** Spring Boot simplifies REST API development by providing annotations, automatic content negotiation, built-in serialization/deserialization, and seamless integration with Spring's dependency injection and validation frameworks.

**🚀 Features:**
- **HTTP Method Mapping:** `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`
- **Content Negotiation:** Automatic JSON/XML serialization
- **Request/Response Handling:** `@RequestBody`, `@ResponseBody`, `ResponseEntity`
- **Path Variables:** `@PathVariable` for dynamic URL segments
- **Request Parameters:** `@RequestParam` for query parameters
- **Validation:** Built-in support with `@Valid` and Bean Validation
- **Exception Handling:** Global and method-level exception handling
- **CORS Support:** Cross-origin resource sharing configuration

**🔁 Flow:**
```java
// 1. Basic REST Controller
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    private final UserService userService;
    
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    // GET /api/users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }
    
    // GET /api/users/{id}
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        return ResponseEntity.ok(user);
    }
    
    // POST /api/users
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
    
    // PUT /api/users/{id}
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User user) {
        User updatedUser = userService.update(id, user);
        return ResponseEntity.ok(updatedUser);
    }
    
    // DELETE /api/users/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

// 2. Request/Response Models
public class User {
    private Long id;
    private String name;
    private String email;
    
    @JsonIgnore
    private String password; // Excluded from JSON serialization
    
    // Constructors, getters, setters
}

// 3. DTOs for API Responses
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private LocalDateTime createdAt;
    
    // Constructors, getters, setters
}

// 4. Content Negotiation
@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Product getProduct(@PathVariable Long id) {
        return productService.findById(id);
    }
}

// 5. Request Validation
public class CreateUserRequest {
    @NotNull
    @Size(min = 2, max = 50)
    private String name;
    
    @NotNull
    @Email
    private String email;
    
    @NotNull
    @Size(min = 8)
    private String password;
    
    // Getters and setters
}
```

**🐞 Fixes:** Use appropriate HTTP methods, implement proper error handling, validate request data, use DTOs for API responses, and follow REST conventions.

---

2. **What is the purpose of the `@RestController` annotation?** *(Asked in Infosys)*

**🧩 Foundation:** `@RestController` is a convenience annotation that combines `@Controller` and `@ResponseBody`, indicating that the class is a REST controller where all methods return data that should be serialized to the response body.

**⚙️ Function:** This annotation eliminates the need to annotate each method with `@ResponseBody` and clearly indicates that the controller is designed for REST API endpoints rather than view-based controllers.

**🚀 Features:**
- **@Controller + @ResponseBody:** Combines both annotations in one
- **Automatic Serialization:** Methods return objects that are automatically serialized
- **Content Negotiation:** Supports multiple content types (JSON, XML)
- **HTTP Status Codes:** Can return `ResponseEntity` for custom status codes
- **Exception Handling:** Integrates with global exception handlers
- **Validation Support:** Works seamlessly with `@Valid` annotations

**🔁 Flow:**
```java
// 1. @RestController vs @Controller
@RestController // Equivalent to @Controller + @ResponseBody on all methods
@RequestMapping("/api/users")
public class UserRestController {
    
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        // Automatically serialized to JSON/XML
        return userService.findById(id);
    }
}

// 2. Traditional @Controller approach (not recommended for REST APIs)
@Controller
@RequestMapping("/api/users")
public class UserController {
    
    @GetMapping("/{id}")
    @ResponseBody // Required for each method
    public User getUser(@PathVariable Long id) {
        return userService.findById(id);
    }
}

// 3. @RestController with ResponseEntity
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    
    @PostMapping
    public ResponseEntity<Order> createOrder(@Valid @RequestBody Order order) {
        Order savedOrder = orderService.create(order);
        return ResponseEntity.status(HttpStatus.CREATED)
            .header("Location", "/api/orders/" + savedOrder.getId())
            .body(savedOrder);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        return orderService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
}

// 4. @RestController with different response types
@RestController
@RequestMapping("/api/files")
public class FileController {
    
    @GetMapping("/download/{filename}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
        Resource file = fileService.loadFile(filename);
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
            .body(file);
    }
    
    @GetMapping("/content/{filename}")
    public String getFileContent(@PathVariable String filename) {
        return fileService.getContent(filename);
    }
}

// 5. @RestController with custom serialization
@RestController
@RequestMapping("/api/reports")
public class ReportController {
    
    @GetMapping(value = "/export", produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] exportReport() {
        return reportService.generatePdfReport();
    }
    
    @GetMapping(value = "/data", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> getReportData() {
        return reportService.getData();
    }
}

// 6. @RestController with exception handling
@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.findById(id)
            .orElseThrow(() -> new ProductNotFoundException("Product not found: " + id));
    }
    
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFound(ProductNotFoundException ex) {
        ErrorResponse error = new ErrorResponse("NOT_FOUND", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
```

**🐞 Fixes:** Use `@RestController` for REST APIs, return appropriate HTTP status codes, handle exceptions properly, and use `ResponseEntity` for complex responses.

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

1. **What is the difference between `@GetMapping`, `@PostMapping`, and `@RequestMapping`?** *(Asked in Wipro)*

**🧩 Foundation:** These annotations are used to map HTTP requests to controller methods, with each serving different purposes and providing different levels of specificity for HTTP method mapping.

**⚙️ Function:** `@GetMapping`, `@PostMapping`, etc. are specialized annotations for specific HTTP methods, while `@RequestMapping` is a general-purpose annotation that can handle multiple HTTP methods and provides more configuration options.

**🚀 Features:**
- **@GetMapping:** Maps to HTTP GET requests
- **@PostMapping:** Maps to HTTP POST requests
- **@RequestMapping:** General-purpose mapping with configurable HTTP methods
- **Method Specificity:** Specialized annotations are more readable and less error-prone
- **Configuration Flexibility:** `@RequestMapping` offers more configuration options
- **URL Patterns:** All support path patterns, path variables, and request parameters

**🔁 Flow:**
```java
// 1. Specialized HTTP Method Annotations
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    // GET /api/users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }
    
    // GET /api/users/{id}
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }
    
    // POST /api/users
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
    
    // PUT /api/users/{id}
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.update(id, user);
    }
    
    // DELETE /api/users/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    // PATCH /api/users/{id}
    @PatchMapping("/{id}")
    public User patchUser(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        return userService.patch(id, updates);
    }
}

// 2. @RequestMapping with HTTP Method Specification
@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    // GET /api/products
    @RequestMapping(method = RequestMethod.GET)
    public List<Product> getAllProducts() {
        return productService.findAll();
    }
    
    // POST /api/products
    @RequestMapping(method = RequestMethod.POST)
    public Product createProduct(@RequestBody Product product) {
        return productService.save(product);
    }
    
    // Multiple HTTP methods for the same endpoint
    @RequestMapping(value = "/{id}", method = {RequestMethod.GET, RequestMethod.HEAD})
    public Product getProduct(@PathVariable Long id) {
        return productService.findById(id);
    }
}

// 3. @RequestMapping with Advanced Configuration
@RestController
@RequestMapping(
    value = "/api/orders",
    produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE
)
public class OrderController {
    
    @RequestMapping(
        value = "/{id}",
        method = RequestMethod.GET,
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public Order getOrder(@PathVariable Long id) {
        return orderService.findById(id);
    }
    
    @RequestMapping(
        value = "/search",
        method = RequestMethod.GET,
        params = {"status", "date"}
    )
    public List<Order> searchOrders(
        @RequestParam String status,
        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date
    ) {
        return orderService.searchByStatusAndDate(status, date);
    }
}

// 4. Comparison of Approaches
@RestController
public class ComparisonController {
    
    // Approach 1: Specialized annotations (Recommended)
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.findById(id);
    }
    
    // Approach 2: @RequestMapping (More verbose but flexible)
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public User getUserAlternative(@PathVariable Long id) {
        return userService.findById(id);
    }
    
    // Approach 3: @RequestMapping with multiple methods
    @RequestMapping(value = "/users/{id}", method = {RequestMethod.GET, RequestMethod.HEAD})
    public User getUserWithHead(@PathVariable Long id) {
        return userService.findById(id);
    }
}

// 5. Custom HTTP Method Handling
@RestController
@RequestMapping("/api/custom")
public class CustomMethodController {
    
    // Custom HTTP method (not standard)
    @RequestMapping(value = "/process", method = RequestMethod.POST, headers = "X-Custom-Method=PROCESS")
    public ResponseEntity<String> customProcess(@RequestBody String data) {
        return ResponseEntity.ok("Processed: " + data);
    }
    
    // Conditional mapping based on headers
    @RequestMapping(
        value = "/data",
        method = RequestMethod.GET,
        headers = "Accept=application/json"
    )
    public ResponseEntity<Map<String, Object>> getDataAsJson() {
        return ResponseEntity.ok(Map.of("format", "json", "data", "sample"));
    }
    
    @RequestMapping(
        value = "/data",
        method = RequestMethod.GET,
        headers = "Accept=application/xml"
    )
    public ResponseEntity<String> getDataAsXml() {
        return ResponseEntity.ok("<data><format>xml</format><content>sample</content></data>");
    }
}
```

**🐞 Fixes:** Use specialized annotations for clarity, prefer `@RequestMapping` for complex configurations, ensure proper HTTP method usage, and maintain consistent URL patterns.

---

2. **How do you handle request validation in a Spring Boot REST API?** *(Asked in Accenture)*

**🧩 Foundation:** Request validation ensures that incoming data meets business rules and constraints before processing, preventing invalid data from reaching the business logic layer and providing meaningful error responses to clients.

**⚙️ Function:** Spring Boot integrates with Bean Validation (JSR-303) to automatically validate request bodies, path variables, and request parameters, with customizable error handling and response formatting.

**🚀 Features:**
- **Bean Validation:** `@NotNull`, `@Size`, `@Email`, `@Min`, `@Max`, etc.
- **Custom Validators:** Create domain-specific validation rules
- **Group Validation:** Validate different scenarios with validation groups
- **Error Handling:** Automatic `MethodArgumentNotValidException` handling
- **Response Formatting:** Customizable error response structures
- **Cross-field Validation:** Validate relationships between fields

**🔁 Flow:**
```java
// 1. Basic Request Validation
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody CreateUserRequest request) {
        User user = userService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
        @PathVariable @Min(1) Long id,
        @Valid @RequestBody UpdateUserRequest request
    ) {
        User user = userService.update(id, request);
        return ResponseEntity.ok(user);
    }
}

// 2. Validation Annotations in Request DTOs
public class CreateUserRequest {
    @NotNull(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;
    
    @NotNull(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;
    
    @NotNull(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$", 
             message = "Password must contain at least one digit, lowercase, uppercase, and special character")
    private String password;
    
    @NotNull(message = "Age is required")
    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 120, message = "Age must be less than 120")
    private Integer age;
    
    // Getters and setters
}

// 3. Custom Validators
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneNumberValidator.class)
public @interface ValidPhoneNumber {
    String message() default "Invalid phone number format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

public class PhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber, String> {
    
    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        if (phoneNumber == null) {
            return true; // Let @NotNull handle null validation
        }
        return phoneNumber.matches("^\\+?[1-9]\\d{1,14}$");
    }
}

// 4. Cross-field Validation
public class OrderRequest {
    @NotNull
    private LocalDateTime orderDate;
    
    @NotNull
    private LocalDateTime deliveryDate;
    
    @Valid
    @NotNull
    private List<OrderItem> items;
    
    @AssertTrue(message = "Delivery date must be after order date")
    public boolean isDeliveryDateValid() {
        if (orderDate == null || deliveryDate == null) {
            return true; // Let @NotNull handle null validation
        }
        return deliveryDate.isAfter(orderDate);
    }
    
    @AssertTrue(message = "Order must have at least one item")
    public boolean hasItems() {
        return items != null && !items.isEmpty();
    }
}

// 5. Validation Groups
public interface CreateValidation {}
public interface UpdateValidation {}

public class UserRequest {
    @NotNull(groups = {CreateValidation.class, UpdateValidation.class})
    @Size(min = 2, max = 50, groups = {CreateValidation.class, UpdateValidation.class})
    private String name;
    
    @NotNull(groups = CreateValidation.class)
    @Email(groups = CreateValidation.class)
    private String email;
    
    @NotNull(groups = CreateValidation.class)
    @Size(min = 8, groups = CreateValidation.class)
    private String password;
    
    // Getters and setters
}

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @PostMapping
    public ResponseEntity<User> createUser(@Validated(CreateValidation.class) @RequestBody UserRequest request) {
        User user = userService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
        @PathVariable Long id,
        @Validated(UpdateValidation.class) @RequestBody UserRequest request
    ) {
        User user = userService.update(id, request);
        return ResponseEntity.ok(user);
    }
}

// 6. Global Exception Handler for Validation
@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(error -> error.getField() + ": " + error.getDefaultMessage())
            .collect(Collectors.toList());
        
        ValidationErrorResponse response = new ValidationErrorResponse(
            "VALIDATION_FAILED",
            "Request validation failed",
            errors
        );
        
        return ResponseEntity.badRequest().body(response);
    }
    
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ValidationErrorResponse> handleConstraintViolation(ConstraintViolationException ex) {
        List<String> errors = ex.getConstraintViolations()
            .stream()
            .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
            .collect(Collectors.toList());
        
        ValidationErrorResponse response = new ValidationErrorResponse(
            "VALIDATION_FAILED",
            "Constraint validation failed",
            errors
        );
        
        return ResponseEntity.badRequest().body(response);
    }
}

// 7. Validation Error Response Model
public class ValidationErrorResponse {
    private String code;
    private String message;
    private List<String> errors;
    private LocalDateTime timestamp;
    
    public ValidationErrorResponse(String code, String message, List<String> errors) {
        this.code = code;
        this.message = message;
        this.errors = errors;
        this.timestamp = LocalDateTime.now();
    }
    
    // Getters and setters
}
```

**🐞 Fixes:** Use appropriate validation annotations, create custom validators for domain-specific rules, implement proper error handling, use validation groups for different scenarios, and provide meaningful error messages.

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

1. **What is Spring Data JPA, and how does it simplify serviceBasedMNCLevel.database access?**

**🧩 Foundation:** Spring Data JPA is a part of the Spring Data family, providing a high-level abstraction for data access using the Java Persistence API (JPA).

**⚙️ Function:** It eliminates boilerplate code for data access layers by providing repository interfaces, query derivation, and integration with JPA providers like Hibernate.

**🚀 Features:**
- Repository interfaces (`JpaRepository`, `CrudRepository`)
- Query derivation from method names
- Custom JPQL/SQL queries with `@Query`
- Pagination and sorting
- Transaction management
- Auditing and projections

**🔁 Flow:**
```java
// 1. Entity definition
@Entity
public class Product {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private double price;
    // getters/setters
}

// 2. Repository interface
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByName(String name);
    @Query("SELECT p FROM Product p WHERE p.price > :minPrice")
    List<Product> findExpensive(@Param("minPrice") double minPrice);
}

// 3. Service usage
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public List<Product> getExpensiveProducts(double minPrice) {
        return productRepository.findExpensive(minPrice);
    }
}
```
**🐞 Fixes:** Use repository interfaces for CRUD, prefer query derivation for simple queries, use `@Query` for complex queries, and leverage pagination/sorting for large datasets.

2. **What is the purpose of the `@Entity` and `@Repository` annotations?**

**🧩 Foundation:**
- `@Entity` marks a class as a JPA entity mapped to a serviceBasedMNCLevel.database table.
- `@Repository` marks a class or interface as a Spring Data repository, enabling exception translation and component scanning.

**⚙️ Function:**
- `@Entity` enables ORM mapping and persistence.
- `@Repository` enables CRUD operations, query methods, and exception translation.

**🚀 Features:**
- Automatic table mapping
- Primary key and relationship mapping
- Exception translation for serviceBasedMNCLevel.database errors
- Integration with Spring Data repositories

**🔁 Flow:**
```java
@Entity
public class User {
    @Id @GeneratedValue
    private Long id;
    private String username;
    // ...
}

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
```
**🐞 Fixes:** Always annotate persistent classes with `@Entity`, use `@Repository` for data access interfaces, and handle exceptions at the service/controller layer.

### Basic Code Questions

1. Write a JPA entity class for a Product table.

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

1. **How do you use Spring Data JPA repositories to perform CRUD operations?**

**🧩 Foundation:** Spring Data JPA repositories provide built-in methods for Create, Read, Update, and Delete (CRUD) operations, eliminating the need for boilerplate DAO code.

**⚙️ Function:** By extending `JpaRepository` or `CrudRepository`, you get access to methods like `save`, `findById`, `findAll`, `deleteById`, etc.

**🚀 Features:**
- `save(entity)`: Insert or update an entity
- `findById(id)`: Retrieve an entity by primary key
- `findAll()`: Retrieve all entities
- `deleteById(id)`: Delete by primary key
- `count()`: Count total records
- Query derivation for custom finders

**🔁 Flow:**
```java
// Repository interface
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByLastName(String lastName);
}

// Service usage
@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Optional<Customer> getCustomer(Long id) {
        return customerRepository.findById(id);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
```
**🐞 Fixes:** Use repository methods for standard CRUD, prefer query derivation for simple queries, and handle `Optional` for null safety.

2. **What is the difference between `@Query` and derived queries in Spring Data JPA?**

**🧩 Foundation:**
- **Derived queries** use method names to generate queries automatically.
- **@Query** allows you to write custom JPQL or native SQL queries.

**⚙️ Function:**
- Use derived queries for simple lookups (e.g., `findByEmail`).
- Use `@Query` for complex queries, joins, or native SQL.

**🚀 Features:**
- Query derivation from method names
- JPQL and native SQL support with `@Query`
- Named parameters with `@Param`
- Pagination and sorting in custom queries

**🔁 Flow:**
```java
// Derived query
List<Order> findByStatusAndCustomerId(String status, Long customerId);

// Custom JPQL query
@Query("SELECT o FROM Order o WHERE o.status = :status AND o.customer.id = :customerId")
List<Order> findOrders(@Param("status") String status, @Param("customerId") Long customerId);

// Native SQL query
@Query(value = "SELECT * FROM orders WHERE status = :status", nativeQuery = true)
List<Order> findByStatusNative(@Param("status") String status);
```
**🐞 Fixes:** Use derived queries for simple cases, `@Query` for complex logic, always use `@Param` for named parameters, and prefer JPQL for portability.

### Intermediate Code Questions

1. Write a Spring Data JPA repository with a custom query to find products by category.

```java
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.category = :category")
    List<Product> findByCategory(@Param("category") String category);
}
```

### Advanced Questions

1. **How do you handle lazy loading and eager loading in Spring Data JPA?**

**🧩 Foundation:** JPA supports two fetching strategies for entity relationships:
- **Lazy loading:** Related entities are loaded on-demand (default for `@OneToMany`, `@ManyToMany`).
- **Eager loading:** Related entities are loaded immediately with the parent (default for `@ManyToOne`, `@OneToOne`).

**⚙️ Function:** Use the `fetch` attribute in relationship annotations to control loading strategy.

**🚀 Features:**
- `@OneToMany(fetch = FetchType.LAZY)`
- `@ManyToOne(fetch = FetchType.EAGER)`
- Use `JOIN FETCH` in JPQL for eager loading in queries
- Avoid N+1 query problem with fetch joins

**🔁 Flow:**
```java
@Entity
public class Order {
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderItem> items;
}

// Eager fetch with JPQL
@Query("SELECT o FROM Order o JOIN FETCH o.items WHERE o.id = :id")
Order findOrderWithItems(@Param("id") Long id);
```
**🐞 Fixes:** Use lazy loading for large collections, eager loading for small/essential relationships, and fetch joins to optimize queries and avoid N+1 issues.

2. **What is the role of the `@Transactional` annotation in Spring Data JPA?**

**🧩 Foundation:** `@Transactional` defines the scope of a single serviceBasedMNCLevel.database transaction.

**⚙️ Function:** It ensures that a method (or class) runs within a transaction, so all DB operations are committed or rolled back atomically.

**🚀 Features:**
- Declarative transaction management
- Rollback on exceptions
- Read-only transactions for optimization
- Nested transactions and propagation control

**🔁 Flow:**
```java
@Service
public class PaymentService {
    @Transactional
    public void processPayment(Order order) {
        // All DB operations here are atomic
        updateOrderStatus(order);
        savePaymentRecord(order);
        // If any exception occurs, everything is rolled back
    }

    @Transactional(readOnly = true)
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }
}
```
**🐞 Fixes:** Use `@Transactional` at the service layer, mark read-only queries for optimization, and handle checked exceptions for rollback as needed.

### Advanced Code Questions

1. Write a repository method to fetch a product with its associated reviews using a JOIN FETCH query.

```java
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p JOIN FETCH p.reviews WHERE p.id = :id")
    Optional<Product> findByIdWithReviews(@Param("id") Long id);
}
```

### Tough Questions

1. **How would you optimize JPA queries for a large dataset with complex relationships?**

**🧩 Foundation:** Large datasets and complex relationships can cause performance issues (N+1 queries, slow joins, memory bloat).

**⚙️ Function:** Optimize with fetch joins, pagination, projections, and query tuning.

**🚀 Features:**
- Use `JOIN FETCH` to avoid N+1
- Use DTO projections for partial data
- Apply pagination with `Pageable`
- Use indexes and query hints
- Batch fetching for collections

**🔁 Flow:**
```java
// Fetch join to avoid N+1
@Query("SELECT p FROM Product p JOIN FETCH p.category WHERE p.price > :minPrice")
List<Product> findWithCategory(@Param("minPrice") double minPrice);

// DTO projection
@Query("SELECT new com.example.dto.ProductSummary(p.id, p.name) FROM Product p")
List<ProductSummary> findProductSummaries();

// Pagination
Page<Product> findByCategory(String category, Pageable pageable);
```
**🐞 Fixes:** Profile queries with tools like Hibernate Statistics, use fetch joins wisely, prefer DTOs for large result sets, and always paginate large queries.

### Tough Code Questions

1. Write a repository method to perform a bulk update of product prices in a single transaction.

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

1. **Your Spring Boot application experiences N+1 query issues with JPA. How would you diagnose and fix it?**

**🧩 Foundation:** N+1 occurs when fetching a parent entity causes additional queries for each child entity.

**⚙️ Function:** Diagnose with SQL logs or profilers, fix with fetch joins or entity graphs.

**🚀 Features:**
- Enable SQL logging (`spring.jpa.show-sql=true`)
- Use `JOIN FETCH` in queries
- Use `@EntityGraph` for dynamic fetch plans

**🔁 Flow:**
```java
// Enable SQL logging in application.properties
spring.jpa.show-sql=true

// Use fetch join
@Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.id = :id")
User findUserWithRoles(@Param("id") Long id);

// Use entity graph
@EntityGraph(attributePaths = {"roles"})
User findById(Long id);
```
**🐞 Fixes:** Always check for N+1 in logs, use fetch joins or entity graphs, and avoid lazy loading in critical queries.

2. **Write a JPA repository query to soft-delete records by setting a deleted flag.**

**🧩 Foundation:** Soft delete marks records as deleted without removing them from the serviceBasedMNCLevel.database.

**⚙️ Function:** Use an `isDeleted` or `deleted` flag and update queries to filter out deleted records.

**🚀 Features:**
- Update queries for soft delete
- Query methods to exclude deleted records
- Optional: Use `@SQLDelete` and `@Where` (Hibernate)

**🔁 Flow:**
```java
@Entity
public class User {
    @Id @GeneratedValue
    private Long id;
    private String username;
    private boolean deleted = false;
    // ...
}

// Soft delete method
@Modifying
@Transactional
@Query("UPDATE User u SET u.deleted = true WHERE u.id = :id")
int softDeleteById(@Param("id") Long id);

// Exclude deleted users in queries
List<User> findByDeletedFalse();
```
**🐞 Fixes:** Always filter out deleted records in queries, use soft delete for auditability, and consider Hibernate's `@Where` for global filtering.

---

## 5. Spring Security

### Basic Questions

1. **What is Spring Security, and how does it integrate with Spring Boot?**

**🧩 Foundation:** Spring Security is a powerful and customizable authentication and access-control framework for Java applications.

**⚙️ Function:** It provides declarative security for Spring-based applications, handling authentication, authorization, CSRF, CORS, and more. Spring Boot auto-configures Spring Security when it detects it on the classpath, providing sensible defaults and easy customization.

**🚀 Features:**
- Authentication (form, basic, JWT, OAuth2, LDAP, etc.)
- Authorization (role-based, method-level, URL-based)
- CSRF and CORS protection
- Password encoding and user management
- Security filters and customizations

**🔁 Flow:**
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .antMatchers("/api/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .formLogin()
            .and()
            .httpBasic();
        return http.build();
    }
}
```
**🐞 Fixes:** Use Spring Boot starters for quick setup, override defaults in a `SecurityConfig`, and always encode passwords.

2. **What is the purpose of the `@EnableWebSecurity` annotation?**

**🧩 Foundation:** `@EnableWebSecurity` enables Spring Security's web security support and provides the Spring MVC integration.

**⚙️ Function:** It imports the Spring Security configuration and allows you to customize security settings via a `WebSecurityConfigurerAdapter` or `SecurityFilterChain`.

**🚀 Features:**
- Activates security filter chain
- Enables method and URL security
- Allows custom security configuration

**🔁 Flow:**
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    // Custom security beans and configuration
}
```
**🐞 Fixes:** Always use `@EnableWebSecurity` in your security configuration class to activate Spring Security.

### Basic Code Questions

1. Write a Spring Security configuration to enable basic authentication for a REST API.

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

1. **How do you implement role-based access control in Spring Security?**

**🧩 Foundation:** Role-based access control (RBAC) restricts access to resources based on user roles.

**⚙️ Function:** Use `hasRole`, `hasAuthority`, or `@PreAuthorize` to secure endpoints or methods.

**🚀 Features:**
- URL-based security with `.antMatchers()` or `.requestMatchers()`
- Method-level security with `@PreAuthorize`, `@Secured`
- Custom roles and authorities

**🔁 Flow:**
```java
// URL-based
http.authorizeHttpRequests(auth -> auth
    .antMatchers("/api/admin/**").hasRole("ADMIN")
    .antMatchers("/api/user/**").hasAnyRole("USER", "ADMIN")
    .anyRequest().authenticated()
);

// Method-level
@Service
public class AdminService {
    @PreAuthorize("hasRole('ADMIN')")
    public void performAdminTask() { /* ... */ }
}
```
**🐞 Fixes:** Use roles for coarse-grained access, authorities for fine-grained, and enable method security with `@EnableGlobalMethodSecurity`.

2. **What is the difference between authentication and authorization in Spring Security?**

**🧩 Foundation:**
- **Authentication:** Verifies user identity (who you are).
- **Authorization:** Determines what actions a user can perform (what you can do).

**⚙️ Function:**
- Authentication is handled by `AuthenticationManager` and providers.
- Authorization is enforced by access decision managers and security filters.

**🚀 Features:**
- Multiple authentication mechanisms (form, basic, JWT, OAuth2)
- Role and permission checks
- Custom access decision logic

**🔁 Flow:**
```java
// Authentication: login form, HTTP basic, JWT, etc.
// Authorization: .antMatchers("/admin/**").hasRole("ADMIN")
```
**🐞 Fixes:** Always separate authentication and authorization logic, and use the right mechanism for your application's needs.

### Intermediate Code Questions

1. Write a configuration to secure an endpoint with a specific role.

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

1. **How do you implement JWT-based authentication in Spring Boot?**

**🧩 Foundation:** JWT (JSON Web Token) is a stateless, token-based authentication mechanism.

**⚙️ Function:** On successful login, the server issues a JWT, which the client includes in the `Authorization` header for subsequent requests.

**🚀 Features:**
- Stateless authentication
- Token expiration and signature validation
- Custom claims and roles
- Integration with Spring Security filters

**🔁 Flow:**
```java
// 1. Generate JWT on login
public String generateToken(UserDetails userDetails) {
    return Jwts.builder()
        .setSubject(userDetails.getUsername())
        .claim("roles", userDetails.getAuthorities())
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + 86400000))
        .signWith(SignatureAlgorithm.HS512, secretKey)
        .compact();
}

// 2. JWT filter
public class JwtTokenFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String token = getJwtFromRequest(request);
        if (token != null && validateToken(token)) {
            Authentication auth = getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        chain.doFilter(request, response);
    }
}

// 3. Register filter in SecurityConfig
http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
```
**🐞 Fixes:** Always validate JWT signature and expiration, use HTTPS, and never store sensitive data in the token.

2. **What is the role of the `AuthenticationManager` in Spring Security?**

**🧩 Foundation:** `AuthenticationManager` is the main interface for authenticating user credentials.

**⚙️ Function:** It delegates authentication requests to a chain of `AuthenticationProvider`s.

**🚀 Features:**
- Central authentication entry point
- Supports multiple authentication mechanisms
- Customizable with custom providers

**🔁 Flow:**
```java
@Autowired
private AuthenticationManager authenticationManager;

public void authenticate(String username, String password) {
    Authentication auth = new UsernamePasswordAuthenticationToken(username, password);
    authenticationManager.authenticate(auth);
}
```
**🐞 Fixes:** Use `AuthenticationManager` for custom login flows, and configure providers for different authentication types.

### Advanced Code Questions

1. Write a configuration to enable JWT authentication in Spring Boot.

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtTokenFilter jwtTokenFilter) throws Exception {
        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
            .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
```

### Tough Questions

1. **How would you secure REST APIs for stateless authentication and scalability?**

**🧩 Foundation:** Stateless authentication means the server does not store session state; each request must be authenticated independently (e.g., JWT).

**⚙️ Function:** Use token-based authentication, disable session creation, and secure endpoints with filters.

**🚀 Features:**
- JWT or OAuth2 tokens
- Stateless session management
- CORS and CSRF protection
- HTTPS for all endpoints

**🔁 Flow:**
```java
http
    .csrf().disable()
    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    .and()
    .authorizeHttpRequests(auth -> auth
        .antMatchers("/api/public/**").permitAll()
        .anyRequest().authenticated()
    )
    .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
```
**🐞 Fixes:** Always use HTTPS, validate tokens, and disable sessions for stateless APIs.

### Tough Code Questions

1. Write a REST controller with global exception handling for a custom exception.

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

1. **How do you customize error handling and access denied responses in Spring Security?**

**🧩 Foundation:** Custom error handling improves API usability and security.

**⚙️ Function:** Implement custom `AuthenticationEntryPoint` and `AccessDeniedHandler`.

**🚀 Features:**
- Custom JSON error responses
- Logging and auditing
- User-friendly error messages

**🔁 Flow:**
```java
@Component
public class CustomAuthEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        response.getWriter().write("{\"error\": \"Unauthorized\"}");
    }
}

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json");
        response.getWriter().write("{\"error\": \"Forbidden\"}");
    }
}

// Register in SecurityConfig
http
    .exceptionHandling()
    .authenticationEntryPoint(customAuthEntryPoint)
    .accessDeniedHandler(customAccessDeniedHandler);
```
**🐞 Fixes:** Always return appropriate HTTP status codes, avoid leaking sensitive info, and log security events.

2. **How do you test Spring Security configurations and secured endpoints?**

**🧩 Foundation:** Testing ensures your security rules work as intended and prevent regressions.

**⚙️ Function:** Use Spring Boot Test, MockMvc, and test-specific users/roles.

**🚀 Features:**
- MockMvc for endpoint testing
- `@WithMockUser` for role-based tests
- Integration tests for authentication flows

**🔁 Flow:**
```java
@SpringBootTest
@AutoConfigureMockMvc
public class SecurityTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void adminEndpointShouldBeAccessible() throws Exception {
        mockMvc.perform(get("/api/admin/dashboard"))
            .andExpect(status().isOk());
    }

    @Test
    void anonymousShouldBeDenied() throws Exception {
        mockMvc.perform(get("/api/admin/dashboard"))
            .andExpect(status().isUnauthorized());
    }
}
```
**🐞 Fixes:** Always test both positive and negative cases, use `@WithMockUser` for role-based tests, and verify custom error responses.

---

## 6. Spring Boot Caching

### Basic Questions

1. **What is caching, and why is it important in Spring Boot applications?**

**🧩 Foundation:** Caching is the process of storing frequently accessed data in memory so that future requests for that data can be served faster, reducing serviceBasedMNCLevel.database or computation load.

**⚙️ Function:** In Spring Boot, caching can be enabled declaratively using annotations, allowing you to cache method results, serviceBasedMNCLevel.database queries, or expensive computations.

**🚀 Features:**
- Declarative caching with annotations (`@Cacheable`, `@CachePut`, `@CacheEvict`)
- Pluggable cache providers (EhCache, Caffeine, Redis, etc.)
- Cache abstraction for easy switching
- Support for conditional and key-based caching
- Integration with Spring's dependency injection

**🔁 Flow:**
```java
// 1. Enable caching in your application
@SpringBootApplication
@EnableCaching
public class MyApp {}

// 2. Use @Cacheable to cache method results
@Service
public class ProductService {
    @Cacheable("products")
    public Product getProductById(Long id) {
        // Simulate expensive DB call
        return productRepository.findById(id).orElse(null);
    }
}

// 3. Use @CacheEvict to remove from cache
@CacheEvict(value = "products", key = "#id")
public void deleteProduct(Long id) {
    productRepository.deleteById(id);
}

// 4. Use @CachePut to update cache
@CachePut(value = "products", key = "#product.id")
public Product updateProduct(Product product) {
    return productRepository.save(product);
}
```
**🐞 Fixes:** Use caching for expensive or frequently accessed data, always evict or update cache on data changes, and choose the right cache provider for your use case.

2. **How do you enable and configure caching in a Spring Boot application?**

**🧩 Foundation:** Caching is enabled with `@EnableCaching` and configured via properties or custom cache manager beans.

**⚙️ Function:** You can use built-in cache managers or define your own, and configure cache names, TTL, and serviceBasedMNCLevel.backend (e.g., Redis, EhCache).

**🚀 Features:**
- `@EnableCaching` to activate caching
- `CacheManager` bean for custom configuration
- Application properties for provider-specific settings
- Support for multiple caches

**🔁 Flow:**
```java
// 1. Enable caching
@SpringBootApplication
@EnableCaching
public class MyApp {}

// 2. Default cache manager (SimpleCacheManager)
// 3. Custom cache manager
@Bean
public CacheManager cacheManager() {
    return new ConcurrentMapCacheManager("products", "users");
}

// 4. Redis cache manager example
@Bean
public RedisCacheManager cacheManager(RedisConnectionFactory factory) {
    return RedisCacheManager.builder(factory).build();
}

// 5. application.properties for EhCache
spring.cache.type=ehcache
spring.cache.ehcache.config=classpath:ehcache.xml
```
**🐞 Fixes:** Always enable caching with `@EnableCaching`, configure cache manager as needed, and use provider-specific settings for advanced features.

### Basic Code Questions

1. Write a service method using `@Cacheable` to cache a serviceBasedMNCLevel.database query result.

```java
@Service
public class UserService {
    @Cacheable("users")
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
```

### Intermediate Questions

1. **How do you use Spring Boot's `@Cacheable` annotation?**

**🧩 Foundation:** `@Cacheable` marks a method so that the result is stored in a cache, and subsequent calls with the same arguments return the cached value.

**⚙️ Function:** The cache key is generated from method parameters by default, but can be customized.

**🚀 Features:**
- Key-based caching
- Conditional caching with `condition` and `unless`
- Custom key generation
- Multiple cache names

**🔁 Flow:**
```java
// Basic usage
@Cacheable("products")
public Product getProduct(Long id) { ... }

// Custom key
@Cacheable(value = "products", key = "#id + '-' + #type")
public Product getProduct(Long id, String type) { ... }

// Conditional caching
@Cacheable(value = "products", condition = "#id > 10")
public Product getProduct(Long id) { ... }

// Unless (skip caching if result is null)
@Cacheable(value = "products", unless = "#result == null")
public Product getProduct(Long id) { ... }
```
**🐞 Fixes:** Use custom keys for composite parameters, use `unless` to avoid caching nulls, and use `condition` for selective caching.

2. **What is the role of Spring Boot's async methods for performance?**

**🧩 Foundation:** Asynchronous methods allow tasks to run in the background, freeing up the main thread and improving scalability.

**⚙️ Function:** Annotate methods with `@Async` to run them asynchronously. Requires `@EnableAsync` in your configuration.

**🚀 Features:**
- Parallel execution of tasks
- Return types: `void`, `Future`, `CompletableFuture`
- Exception handling for async methods

**🔁 Flow:**
```java
// Enable async support
@SpringBootApplication
@EnableAsync
public class MyApp {}

// Async service method
@Async
public CompletableFuture<User> findUserAsync(Long id) {
    return CompletableFuture.completedFuture(userRepository.findById(id).orElse(null));
}
```
**🐞 Fixes:** Always enable async with `@EnableAsync`, use `CompletableFuture` for non-blocking results, and handle exceptions in async methods.

### Intermediate Code Questions

1. Write a service method using `@Async` to fetch user data in the background.

```java
@Service
public class UserService {
    @Async
    public CompletableFuture<User> getUserAsync(Long id) {
        return CompletableFuture.completedFuture(userRepository.findById(id).orElse(null));
    }
}
```

### Advanced Questions

1. **How do you optimize Spring Boot's startup time for a large application?**

**🧩 Foundation:** Large applications can have slow startup due to bean initialization, classpath scanning, and configuration loading.

**⚙️ Function:** Optimize by enabling lazy initialization, reducing classpath, and using lighter dependencies.

**🚀 Features:**
- Lazy initialization (`spring.main.lazy-initialization=true`)
- Profile-specific beans
- Exclude unused auto-configurations
- Use lighter cache providers

**🔁 Flow:**
```properties
# Enable lazy initialization
spring.main.lazy-initialization=true

# Exclude auto-configurations
spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration
```
**🐞 Fixes:** Enable lazy initialization for non-critical beans, use profiles to load only necessary beans, and monitor startup logs for bottlenecks.

2. **What is the impact of lazy initialization on Spring Boot performance?**

**🧩 Foundation:** Lazy initialization defers bean creation until they are needed, reducing startup time but possibly increasing first-use latency.

**⚙️ Function:** Use for non-critical or rarely used beans, but avoid for core services.

**🚀 Features:**
- Reduces startup time
- Defers resource usage
- Can be enabled globally or per-bean

**🔁 Flow:**
```java
// Enable lazy initialization globally
@Configuration
public class AppConfig {
    @Bean
    @Lazy
    public ExpensiveService expensiveService() {
        return new ExpensiveService();
    }
}
```
**🐞 Fixes:** Use lazy initialization for optional or heavy beans, but not for core application logic.

---

## 7. Spring Boot Actuator

### Basic Questions

1. **What is Spring Boot Actuator, and why is it useful?**

**🧩 Foundation:** Spring Boot Actuator is a set of built-in production-ready features that help you monitor and manage your Spring Boot application.

**⚙️ Function:** It exposes endpoints for health checks, metrics, environment info, application status, and more, making it easy to monitor, manage, and troubleshoot applications in production.

**🚀 Features:**
- Health checks (`/actuator/health`)
- Metrics (`/actuator/metrics`)
- Environment info (`/actuator/env`)
- Application info (`/actuator/info`)
- Custom endpoints
- Integration with monitoring tools (Prometheus, Grafana, etc.)

**🔁 Flow:**
```java
// 1. Add dependency in build.gradle or pom.xml
// implementation 'org.springframework.boot:spring-boot-starter-actuator'

// 2. Enable actuator endpoints in application.properties
yaml
management.endpoints.web.exposure.include=health,info,metrics,env

// 3. Access endpoints
// http://localhost:8080/actuator/health
// http://localhost:8080/actuator/metrics
// http://localhost:8080/actuator/info

// 4. Custom info
management.info.env.enabled=true
info.app.name=My Spring Boot App
info.app.version=1.0.0
```
**🐞 Fixes:** Only expose necessary endpoints, secure sensitive endpoints, and use Actuator for real-time monitoring and troubleshooting.

2. **How do you secure Actuator endpoints in a Spring Boot application?**

**🧩 Foundation:** Actuator endpoints can expose sensitive data and should be protected in production.

**⚙️ Function:** Use Spring Security to restrict access, and configure which endpoints are exposed.

**🚀 Features:**
- Endpoint whitelisting/blacklisting
- Role-based access control
- Custom security configuration for endpoints

**🔁 Flow:**
```java
// application.properties
yaml
management.endpoints.web.exposure.include=health,info
management.endpoint.health.show-details=when-authorized

// Secure endpoints with Spring Security
@Configuration
public class ActuatorSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/actuator/health").permitAll()
                .requestMatchers("/actuator/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .httpBasic();
        return http.build();
    }
}
```
**🐞 Fixes:** Never expose all actuator endpoints in production, restrict access to sensitive endpoints, and use HTTPS for all management endpoints.

### Basic Code Questions

1. Write a configuration to expose only the health and info endpoints and secure all others.

```java
// application.properties
yaml
management.endpoints.web.exposure.include=health,info

@Configuration
public class ActuatorSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/actuator/health").permitAll()
                .requestMatchers("/actuator/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .httpBasic();
        return http.build();
    }
}
```

### Intermediate Questions

1. **How do you create a custom Actuator endpoint?**

**🧩 Foundation:** Custom endpoints allow you to expose application-specific health, metrics, or management data.

**⚙️ Function:** Implement a class with `@Endpoint` or `@RestControllerEndpoint` and define read/write operations.

**🚀 Features:**
- Custom health checks
- Custom metrics
- Read and write operations
- Integration with monitoring tools

**🔁 Flow:**
```java
@Component
@Endpoint(id = "custom")
public class CustomEndpoint {
    @ReadOperation
    public Map<String, Object> customInfo() {
        return Map.of("status", "ok", "timestamp", Instant.now());
    }
}
// Access: /actuator/custom
```
**🐞 Fixes:** Use custom endpoints for app-specific monitoring, document all custom endpoints, and secure them as needed.

2. **How do you monitor application metrics with Actuator and external tools?**

**🧩 Foundation:** Actuator exposes metrics in a format consumable by external monitoring tools (Prometheus, Grafana, etc.).

**⚙️ Function:** Enable and configure metrics endpoints, and use exporters or integrations for your monitoring stack.

**🚀 Features:**
- `/actuator/metrics` endpoint
- Prometheus, JMX, and other exporters
- Custom metrics with `MeterRegistry`

**🔁 Flow:**
```java
// application.properties
yaml
management.endpoints.web.exposure.include=metrics,prometheus
management.metrics.export.prometheus.enabled=true

// Custom metric example
@Autowired
private MeterRegistry meterRegistry;

public void recordCustomMetric() {
    meterRegistry.counter("custom.event.count").increment();
}
```
**🐞 Fixes:** Enable only required metrics, use tags for filtering, and monitor custom business metrics as needed.

### Intermediate Code Questions

1. Write a custom Actuator endpoint to expose the current application time.

```java
@Component
@Endpoint(id = "time")
public class TimeEndpoint {
    @ReadOperation
    public Map<String, Object> currentTime() {
        return Map.of("time", Instant.now());
    }
}
// Access: /actuator/time
```

### Advanced Questions

1. **How do you extend or customize built-in Actuator endpoints?**

**🧩 Foundation:** You can extend built-in endpoints by providing custom health indicators, info contributors, or metrics.

**⚙️ Function:** Implement `HealthIndicator`, `InfoContributor`, or use `MeterRegistry` for custom metrics.

**🚀 Features:**
- Custom health checks (e.g., serviceBasedMNCLevel.database, external API)
- Additional info properties
- Custom metrics and tags

**🔁 Flow:**
```java
// Custom health indicator
@Component
public class CustomHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        boolean healthy = checkSomething();
        return healthy ? Health.up().build() : Health.down().withDetail("error", "Something failed").build();
    }
}

// Custom info contributor
@Component
public class BuildInfoContributor implements InfoContributor {
    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("build", Map.of("version", "1.0.0", "timestamp", Instant.now()));
    }
}
```
**🐞 Fixes:** Use custom health indicators for critical dependencies, add business info to `/actuator/info`, and tag custom metrics for filtering.

2. **How do you use Actuator for health checks in cloud or container environments?**

**🧩 Foundation:** Actuator health endpoints are commonly used by orchestrators (Kubernetes, AWS, etc.) for liveness and readiness checks.

**⚙️ Function:** Configure endpoints and health groups for integration with your platform.

**🚀 Features:**
- Liveness and readiness probes
- Health groups for different checks
- Custom health indicators for cloud dependencies

**🔁 Flow:**
```properties
# application.properties
management.endpoint.health.probes.enabled=true
management.endpoint.health.group.liveness.include=livenessState
management.endpoint.health.group.readiness.include=readinessState

# Kubernetes example
livenessProbe:
  httpGet:
    path: /actuator/health/liveness
    port: 8080
readinessProbe:
  httpGet:
    path: /actuator/health/readiness
    port: 8080
```
**🐞 Fixes:** Always configure health endpoints for your deployment environment, use custom indicators for external dependencies, and monitor probe results.

---

## 8. Transaction Management

### Basic Questions

1. **What is transaction management in Spring Boot, and why is it important?**

**🧩 Foundation:** Transaction management ensures that a series of operations on a serviceBasedMNCLevel.database are executed as a single unit of work, either fully completed or fully rolled back, maintaining data integrity.

**⚙️ Function:** Spring Boot provides declarative transaction management using the `@Transactional` annotation, which can be applied at the class or method level.

**🚀 Features:**
- Declarative and programmatic transaction management
- Automatic rollback on exceptions
- Support for nested and distributed transactions
- Integration with JPA, JDBC, and other data sources

**🔁 Flow:**
```java
@Service
public class OrderService {
    @Transactional
    public void placeOrder(Order order) {
        // All DB operations here are atomic
        saveOrder(order);
        updateInventory(order);
        // If any exception occurs, everything is rolled back
    }
}
```
**🐞 Fixes:** Use transactions for multi-step DB operations, always handle exceptions, and avoid long-running transactions.

2. **How does the `@Transactional` annotation work in Spring Boot?**

**🧩 Foundation:** `@Transactional` marks a method or class as transactional, so all operations within are executed in a single transaction context.

**⚙️ Function:** It manages transaction boundaries, commits on success, and rolls back on runtime exceptions by default.

**🚀 Features:**
- Rollback on unchecked exceptions
- Customizable rollback rules
- Read-only transactions for optimization
- Propagation and isolation level control

**🔁 Flow:**
```java
@Transactional(rollbackFor = Exception.class, readOnly = false, propagation = Propagation.REQUIRED)
public void updateAccount(Account account) {
    // ...
}
```
**🐞 Fixes:** Use `rollbackFor` for checked exceptions, set `readOnly` for queries, and choose the right propagation level for your use case.

### Basic Code Questions

1. Write a service method to transfer money between two accounts using transactions.

```java
@Service
public class BankService {
    @Transactional
    public void transfer(Long fromId, Long toId, double amount) {
        Account from = accountRepository.findById(fromId).orElseThrow();
        Account to = accountRepository.findById(toId).orElseThrow();
        from.debit(amount);
        to.credit(amount);
        accountRepository.save(from);
        accountRepository.save(to);
    }
}
```

### Intermediate Questions

1. **What is the difference between declarative and programmatic transaction management?**

**🧩 Foundation:**
- **Declarative:** Uses annotations or XML to define transaction boundaries (recommended in Spring Boot).
- **Programmatic:** Manually manages transactions in code using `PlatformTransactionManager`.

**⚙️ Function:** Declarative is less error-prone and easier to maintain; programmatic gives more control for complex scenarios.

**🚀 Features:**
- Declarative: `@Transactional` annotation
- Programmatic: `TransactionTemplate`, `PlatformTransactionManager`

**🔁 Flow:**
```java
// Declarative
@Transactional
public void processOrder(Order order) { ... }

// Programmatic
@Autowired
private PlatformTransactionManager txManager;

public void processOrder(Order order) {
    TransactionTemplate template = new TransactionTemplate(txManager);
    template.execute(status -> {
        // transactional code
        return null;
    });
}
```
**🐞 Fixes:** Prefer declarative for most cases, use programmatic for advanced needs, and avoid mixing both in the same class.

2. **How do you handle transaction rollback in Spring Boot?**

**🧩 Foundation:** By default, transactions roll back on unchecked (runtime) exceptions. You can customize rollback behavior using `rollbackFor` and `noRollbackFor`.

**⚙️ Function:** Use `@Transactional(rollbackFor = Exception.class)` to roll back on checked exceptions.

**🚀 Features:**
- Rollback on specific exceptions
- No rollback for certain exceptions
- Manual rollback with `TransactionStatus.setRollbackOnly()`

**🔁 Flow:**
```java
@Transactional(rollbackFor = CustomCheckedException.class)
public void processPayment(Payment payment) throws CustomCheckedException {
    // ...
    if (someCondition) {
        throw new CustomCheckedException("Payment failed");
    }
}
```
**🐞 Fixes:** Always test rollback scenarios, use custom exceptions for business logic, and log rollbacks for auditing.

### Intermediate Code Questions

1. Write a service method to handle a transaction with manual rollback on a business condition.

```java
@Service
public class InventoryService {
    @Autowired
    private PlatformTransactionManager txManager;

    public void updateStock(Long productId, int quantity) {
        TransactionTemplate template = new TransactionTemplate(txManager);
        template.execute(status -> {
            Product product = productRepository.findById(productId).orElseThrow();
            if (quantity < 0) {
                status.setRollbackOnly();
                return null;
            }
            product.setStock(product.getStock() + quantity);
            productRepository.save(product);
            return null;
        });
    }
}
```

### Advanced Questions

1. **How do you manage distributed transactions in a Spring Boot microservices architecture?**

**🧩 Foundation:** Distributed transactions span multiple resources (databases, services) and require coordination to ensure atomicity.

**⚙️ Function:** Use two-phase commit (XA), SAGA pattern, or event-driven compensation for distributed transactions.

**🚀 Features:**
- JTA/XA for two-phase commit
- SAGA pattern for long-running transactions
- Eventual consistency
- Integration with frameworks like Atomikos, Narayana, or Spring Cloud Data Flow

**🔁 Flow:**
```java
// SAGA pattern example (conceptual)
@Service
public class OrderSagaService {
    public void placeOrder(Order order) {
        // Step 1: Reserve inventory
        // Step 2: Process payment
        // Step 3: Confirm shipment
        // If any step fails, trigger compensating transactions
    }
}
```
**🐞 Fixes:** Use SAGA for microservices, avoid XA unless necessary, and design for eventual consistency.

2. **What is the impact of transaction propagation levels (e.g., REQUIRED, NESTED)?**

**🧩 Foundation:** Propagation defines how transactions relate when calling transactional methods from other transactional methods.

**⚙️ Function:**
- `REQUIRED`: Join existing or create new (default)
- `REQUIRES_NEW`: Always create new, suspending existing
- `NESTED`: Create nested transaction if supported

**🚀 Features:**
- Fine-grained control over transaction boundaries
- Support for nested and independent transactions

**🔁 Flow:**
```java
@Transactional(propagation = Propagation.REQUIRED)
public void methodA() { ... }

@Transactional(propagation = Propagation.REQUIRES_NEW)
public void methodB() { ... }

@Transactional(propagation = Propagation.NESTED)
public void methodC() { ... }
```
**🐞 Fixes:** Use `REQUIRES_NEW` for independent operations, `NESTED` for savepoints, and avoid complex propagation chains unless necessary.

### Tough Questions

1. **How would you handle transaction timeouts in a high-latency serviceBasedMNCLevel.database operation?**

**🧩 Foundation:** Transaction timeouts prevent long-running transactions from holding resources indefinitely.

**⚙️ Function:** Set timeouts using `@Transactional(timeout = seconds)` or in configuration.

**🚀 Features:**
- Per-method or global timeouts
- Automatic rollback on timeout
- Logging and monitoring of timeouts

**🔁 Flow:**
```java
@Transactional(timeout = 5) // 5 seconds
public void processLargeBatch() {
    // ...
}
```
**🐞 Fixes:** Set realistic timeouts, monitor for timeouts in logs, and optimize queries to avoid long transactions.

### Situational / Real-world Questions

1. **Your application experiences transaction deadlocks with a serviceBasedMNCLevel.database. How would you diagnose and resolve this?**

**🧩 Foundation:** Deadlocks occur when two or more transactions block each other, waiting for resources.

**⚙️ Function:** Diagnose with DB logs, monitoring tools, and by analyzing transaction patterns.

**🚀 Features:**
- Deadlock detection in DB logs
- Retry logic for deadlock exceptions
- Optimistic/pessimistic locking
- Reducing transaction scope

**🔁 Flow:**
```java
try {
    // transactional code
} catch (DeadlockLoserDataAccessException ex) {
    // Retry logic or alert
}
```
**🐞 Fixes:** Keep transactions short, avoid lock escalation, use proper isolation levels, and implement retry logic for deadlocks.

2. **Write a service method to handle a transaction across multiple serviceBasedMNCLevel.database operations with rollback on failure.**

```java
@Service
public class PaymentService {
    @Transactional
    public void processPayment(Order order, Payment payment) {
        orderRepository.save(order);
        if (!paymentService.charge(payment)) {
            throw new PaymentFailedException("Payment failed");
        }
        paymentRepository.save(payment);
    }
}
```

---

## 9. Microservices with Spring Boot

### Basic Questions

1. **What are microservices, and how does Spring Boot support them?**

**🧩 Foundation:** Microservices is an architectural style where an application is composed of small, independent services that communicate over APIs.

**⚙️ Function:** Spring Boot simplifies microservices development by providing embedded servers, auto-configuration, REST support, and seamless integration with Spring Cloud for distributed systems.

**🚀 Features:**
- Independent deployment and scaling
- RESTful APIs with Spring MVC
- Embedded Tomcat/Jetty/Undertow
- Integration with Spring Cloud (service discovery, config, circuit breakers, etc.)
- Lightweight, container-friendly

**🔁 Flow:**
```java
// 1. Simple microservice with REST endpoint
@SpringBootApplication
@RestController
public class ProductServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApp.class, args);
    }
    @GetMapping("/api/products/{id}")
    public Product getProduct(@PathVariable Long id) {
        return new Product(id, "Sample Product");
    }
}
```
**🐞 Fixes:** Use Spring Boot starters for microservices, keep services focused, and use REST for communication.

2. **What is the role of Spring Cloud in microservices development?**

**🧩 Foundation:** Spring Cloud provides tools for building distributed systems, including service discovery, configuration management, load balancing, circuit breakers, and distributed tracing.

**⚙️ Function:** It extends Spring Boot with patterns and integrations for resilient, scalable microservices.

**🚀 Features:**
- Service discovery (Eureka, Consul)
- Centralized configuration (Config Server)
- Load balancing (Ribbon, Spring Cloud LoadBalancer)
- Circuit breakers (Resilience4j, Hystrix)
- API Gateway (Spring Cloud Gateway)
- Distributed tracing (Sleuth, Zipkin)

**🔁 Flow:**
```java
// 1. Enable Eureka client for service discovery
@SpringBootApplication
@EnableEurekaClient
public class InventoryServiceApp { ... }

// 2. Use @LoadBalanced RestTemplate for client-side load balancing
@Bean
@LoadBalanced
public RestTemplate restTemplate() {
    return new RestTemplate();
}

// 3. Centralized config with Spring Cloud Config
# bootstrap.properties
spring.cloud.config.uri=http://localhost:8888
```
**🐞 Fixes:** Use Spring Cloud for distributed patterns, externalize config, and enable service discovery for dynamic scaling.

### Basic Code Questions

1. Write a simple REST-based microservice using Spring Boot.

```java
@SpringBootApplication
@RestController
public class UserServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApp.class, args);
    }
    @GetMapping("/api/users/{id}")
    public User getUser(@PathVariable Long id) {
        return new User(id, "John Doe");
    }
}
```

### Intermediate Questions

1. **How do you implement a circuit breaker in Spring Boot using Resilience4j?**

**🧩 Foundation:** Circuit breakers prevent cascading failures by stopping calls to a failing service and providing fallback logic.

**⚙️ Function:** Use Resilience4j's `@CircuitBreaker` annotation or programmatic API to wrap remote calls.

**🚀 Features:**
- Automatic failure detection
- Fallback methods
- Metrics and monitoring
- Integration with Spring Cloud Circuit Breaker

**🔁 Flow:**
```java
@Service
public class OrderService {
    @CircuitBreaker(name = "inventoryService", fallbackMethod = "fallbackInventory")
    public Inventory getInventory(Long productId) {
        // Call remote inventory service
    }
    public Inventory fallbackInventory(Long productId, Throwable t) {
        return new Inventory(productId, 0);
    }
}
```
**🐞 Fixes:** Use circuit breakers for all remote calls, monitor metrics, and provide meaningful fallbacks.

2. **What is the purpose of the `@LoadBalanced` annotation with RestTemplate?**

**🧩 Foundation:** `@LoadBalanced` enables client-side load balancing for `RestTemplate` by integrating with service discovery.

**⚙️ Function:** It allows `RestTemplate` to resolve service names to actual instances using Eureka, Consul, or other discovery services.

**🚀 Features:**
- Transparent load balancing
- Service name resolution
- Integration with Ribbon or Spring Cloud LoadBalancer

**🔁 Flow:**
```java
@Bean
@LoadBalanced
public RestTemplate restTemplate() {
    return new RestTemplate();
}

// Usage
ResponseEntity<User> response = restTemplate.getForEntity("http://user-service/api/users/1", User.class);
```
**🐞 Fixes:** Always use `@LoadBalanced` for service-to-service calls, and avoid hardcoding service URLs.

### Intermediate Code Questions

1. Write a configuration to enable service discovery with Eureka in a Spring Boot microservice.

```java
@SpringBootApplication
@EnableEurekaClient
public class CatalogServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(CatalogServiceApp.class, args);
    }
}
```

### Advanced Questions

1. **How do you implement distributed tracing with Spring Cloud Sleuth and Zipkin?**

**🧩 Foundation:** Distributed tracing tracks requests as they flow through multiple microservices, helping diagnose latency and failures.

**⚙️ Function:** Spring Cloud Sleuth adds trace and span IDs to logs; Zipkin collects and visualizes traces.

**🚀 Features:**
- Automatic trace/span propagation
- Integration with logging frameworks
- Zipkin UI for trace analysis

**🔁 Flow:**
```java
// Add dependencies: spring-cloud-starter-sleuth, spring-cloud-starter-zipkin
// application.properties
spring.zipkin.base-url=http://localhost:9411
spring.sleuth.sampler.probability=1.0

// Traces are automatically propagated in logs and HTTP headers
```
**🐞 Fixes:** Enable tracing in all services, monitor Zipkin UI, and use trace IDs for debugging distributed issues.

2. **What is the role of API Gateway in a Spring Boot microservices architecture?**

**🧩 Foundation:** API Gateway acts as a single entry point for clients, routing requests to appropriate microservices and handling cross-cutting concerns.

**⚙️ Function:** Use Spring Cloud Gateway or Zuul to implement API Gateway patterns.

**🚀 Features:**
- Routing and load balancing
- Authentication and authorization
- Rate limiting and throttling
- Request/response transformation

**🔁 Flow:**
```java
@SpringBootApplication
public class ApiGatewayApp {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApp.class, args);
    }
}
// application.properties
spring.cloud.gateway.routes[0].id=product-service
spring.cloud.gateway.routes[0].uri=lb://product-service
spring.cloud.gateway.routes[0].predicates[0]=Path=/api/products/**
```
**🐞 Fixes:** Use API Gateway for all external traffic, centralize security, and monitor gateway metrics.

### Advanced Code Questions

1. Write a configuration to enable distributed tracing with Zipkin in a Spring Boot microservice.

```properties
# application.properties
spring.zipkin.base-url=http://localhost:9411
spring.sleuth.sampler.probability=1.0
```

### Tough Questions

1. **How would you scale a Spring Boot microservices application to handle millions of requests?**

**🧩 Foundation:** Scalability is achieved by running multiple instances, load balancing, and optimizing resource usage.

**⚙️ Function:** Use container orchestration (Kubernetes, Docker Swarm), stateless services, and distributed caching.

**🚀 Features:**
- Horizontal scaling with containers
- Centralized configuration and discovery
- Distributed caching (Redis, Hazelcast)
- Asynchronous messaging (Kafka, RabbitMQ)
- Health checks and auto-scaling

**🔁 Flow:**
```yaml
# Kubernetes deployment example
apiVersion: apps/v1
kind: Deployment
metadata:
  name: product-service
spec:
  replicas: 10
  selector:
    matchLabels:
      app: product-service
  template:
    metadata:
      labels:
        app: product-service
    spec:
      containers:
      - name: product-service
        image: myrepo/product-service:latest
        ports:
        - containerPort: 8080
```
**🐞 Fixes:** Design stateless services, use health checks, and monitor resource usage for scaling.

### Tough Code Questions

1. Write a configuration to enable API Gateway routing in Spring Cloud Gateway.

```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: user-service
          uri: lb://user-service
          predicates:
            - Path=/api/users/**
        - id: order-service
          uri: lb://order-service
          predicates:
            - Path=/api/orders/**
```

### Situational / Real-world Questions

1. **Your microservices application experiences latency in inter-service calls. How would you optimize it?**

**🧩 Foundation:** Latency in inter-service calls can be caused by network delays, service discovery overhead, or inefficient communication protocols.

**⚙️ Function:** Optimize by reducing the number of remote calls, using lightweight protocols, and caching frequently accessed data.

**🚀 Features:**
- Service discovery optimization
- Caching frequently accessed data
- Asynchronous messaging
- API Gateway routing

**🔁 Flow:**
```java
// 1. Optimize service discovery
@SpringBootApplication
@EnableEurekaClient
public class CatalogServiceApp { ... }

// 2. Use @LoadBalanced RestTemplate for client-side load balancing
@Bean
@LoadBalanced
public RestTemplate restTemplate() {
    return new RestTemplate();
}

// 3. Centralized config with Spring Cloud Config
# bootstrap.properties
spring.cloud.config.uri=http://localhost:8888
```
**🐞 Fixes:** Use Spring Cloud for service discovery and configuration management, optimize network latency, and use caching for frequently accessed data.

2. **Your microservices application experiences service outages. How would you implement fault tolerance?**

**🧩 Foundation:** Fault tolerance ensures the system remains available despite failures in some services.

**⚙️ Function:** Use circuit breakers, retries, bulkheads, and fallback logic to handle failures gracefully.

**🚀 Features:**
- Circuit breakers (Resilience4j)
- Retry and fallback mechanisms
- Bulkhead isolation
- Monitoring and alerting

**🔁 Flow:**
```java
@Service
public class PaymentService {
    @CircuitBreaker(name = "paymentService", fallbackMethod = "fallbackPayment")
    public Payment processPayment(PaymentRequest request) {
        // Call remote payment provider
    }
    public Payment fallbackPayment(PaymentRequest request, Throwable t) {
        // Return default response or alert
        return new Payment("FAILED");
    }
}
```
**🐞 Fixes:** Always implement fallbacks for remote calls, monitor circuit breaker metrics, and use bulkheads to isolate failures.

---


## 10. Error Handling and Debugging

### Basic Questions

1. **How do you handle exceptions in a Spring Boot application?** _(Asked in TCS, Infosys)_

   **Fact:** Use `@ExceptionHandler` with custom exception classes.

   **Feeling:** Developers often feel overwhelmed when exceptions aren’t caught cleanly.

   **Finding:** `@ControllerAdvice` provides centralized exception handling across all controllers.

   **Forward:** Always create a global error handler to catch common exceptions like `NullPointerException`, `IllegalArgumentException`, etc.

2. **What is the purpose of the `@ControllerAdvice` annotation?** _(Asked in Capgemini)_

   **Fact:** It is used for global exception handling.

   **Feeling:** Helps reduce repetitive code and promotes cleaner architecture.

   **Finding:** Acts as an interceptor of exceptions thrown by methods annotated with `@RequestMapping`.

   **Forward:** Use it for consistent error responses and better maintainability.

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

1. **How do you log errors effectively in a Spring Boot application?** _(Asked in Wipro)_

   **Fact:** Use `SLF4J` or `Logback` with structured logging.

   **Feeling:** Logging without structure can feel like finding a needle in a haystack.

   **Finding:** Use MDC for traceability, and configure logging levels appropriately.

   **Forward:** Centralize logs using ELK or tools like Grafana + Loki.

2. **What is the difference between `@ExceptionHandler` and `@ControllerAdvice`?** _(Asked in Accenture)_

   **Fact:** `@ExceptionHandler` is used within a controller, `@ControllerAdvice` is global.

   **Feeling:** Knowing the scope of your error handling can prevent debugging nightmares.

   **Finding:** `@ControllerAdvice` reduces code duplication across controllers.

   **Forward:** Use both as needed: local for specific cases, global for consistency.

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

1. **How do you debug a Spring Boot application in production?** _(Asked in Cognizant)_

   **Fact:** Enable remote debugging or use logging + monitoring tools.

   **Feeling:** Debugging in production feels risky without observability.

   **Finding:** Tools like Spring Boot Actuator, Sentry, and New Relic help track production issues.

   **Forward:** Always log with context and avoid printing stack traces directly.

2. **What tools do you use to monitor Spring Boot application performance?** _(Asked in TCS Digital)_

   **Fact:** Actuator, Micrometer, Prometheus, Grafana.

   **Feeling:** Monitoring gives peace of mind and alerting on thresholds.

   **Finding:** Real-time dashboards and alerts help preempt outages.

   **Forward:** Automate metrics collection and alerting setup in CI/CD pipelines.

### Tough Questions

1. **How would you handle intermittent errors in a Spring Boot application caused by external services?** _(Asked in Deloitte)_

   **Fact:** Use circuit breakers like Resilience4j.

   **Feeling:** External service failures can feel out of control without fail-safes.

   **Finding:** Retry, fallback, timeout, and circuit breakers are key.

   **Forward:** Apply resilience patterns for stability under failure.

### Situational / Real-world Questions

1. **Your application logs sensitive data in error messages. How would you redesign error handling to prevent this?** _(Asked in HCL)_

   **Fact:** Mask or redact sensitive fields in logs.

   **Feeling:** Leaking PII can lead to legal and trust issues.

   **Finding:** Use custom serializers and centralized error logging with filters.

   **Forward:** Implement security logging standards and audit regularly.

2. **Write a configuration to enable logging of request and response details in a Spring Boot application.** _(Asked in HCL)_

```properties
logging.level.org.springframework.web=DEBUG
```



## 11. Integration with Databases (Using 4F Formula)

---

### 🔹 Basic Questions

---

**1. How do you configure a serviceBasedMNCLevel.database connection in Spring Boot?**  
**_(Asked in TCS, Infosys)_**

**🧠 Fact:** Spring Boot simplifies DB connectivity using `application.properties` or `application.yml`.

**🔁 Flow:** You define JDBC URL, username, password, and JPA/Hibernate settings.

**🎯 Focus:** Spring Boot auto-configures `DataSource` and uses Hibernate as default JPA provider.

**💡 Final Tip:** Make sure your DB driver is on the classpath (e.g., `mysql-connector-java` for MySQL).

---

**2. What is the role of the `spring.datasource` properties?**  
**_(Asked in Capgemini)_**

**🧠 Fact:** These properties configure your application's DataSource.

**🔁 Flow:** Spring reads these to bootstrap serviceBasedMNCLevel.database connectivity and connection pool behavior.

**🎯 Focus:** Correct configuration avoids issues like timeouts or authentication failures.

**💡 Final Tip:** Also tune the pool using HikariCP properties (default pooling engine in Spring Boot).

---

### 💻 Basic Code Question

---

**Write an `application.properties` file to configure a MySQL serviceBasedMNCLevel.database connection.**  
**_(Asked in TCS)_**

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=root
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

---

### 🔸 Intermediate Questions

---

**1. How do you handle serviceBasedMNCLevel.database migrations in Spring Boot using Flyway or Liquibase?**  
**_(Asked in Wipro)_**

**🧠 Fact:** Tools like Flyway/Liquibase handle schema versioning and evolution.

**🔁 Flow:** You place SQL scripts in `db/migration` (Flyway) or use XML/YAML for Liquibase.

**🎯 Focus:** They auto-run on app startup and apply deltas (version control for DB).

**💡 Final Tip:** Version your scripts like `V1__init.sql`, `V2__add_column.sql` for Flyway.

---

**2. What is the difference between `CrudRepository` and `JpaRepository`?**  
**_(Asked in Accenture)_**

**🧠 Fact:** Both are interfaces for Spring Data JPA.

**🔁 Flow:** `CrudRepository` gives basic CRUD methods, `JpaRepository` adds pagination + batch methods.

**🎯 Focus:** Use `JpaRepository` for richer querying support.

**💡 Final Tip:** Extend `JpaRepository<T, ID>` for most use cases.

---

### 💻 Intermediate Code Question

---

**Write a Flyway migration script to create a users table.**  
**_(Asked in Wipro)_**

```sql
-- File: V1__create_users_table.sql
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE
);
```

---

### 🔺 Advanced Questions

---

**1. How do you optimize serviceBasedMNCLevel.database connections in a Spring Boot application?**  
**_(Asked in Cognizant)_**

**🧠 Fact:** Connection optimization ensures high throughput under load.

**🔁 Flow:** Use HikariCP (default), adjust pool sizes, timeout, and validation queries.

**🎯 Focus:** Prevent bottlenecks with optimal `maximum-pool-size` and `idle-timeout`.

**💡 Final Tip:** Monitor with Spring Actuator and DB connection pool metrics.

---

**2. What is the impact of connection pooling on application performance?**  
**_(Asked in TCS Digital)_**

**🧠 Fact:** Connection pooling reduces overhead by reusing DB connections.

**🔁 Flow:** It creates a pool of connections at startup and hands them out to threads.

**🎯 Focus:** Minimizes latency compared to opening/closing DB connections per request.

**💡 Final Tip:** Use `HikariCP`'s built-in monitoring to track pool efficiency.

---

### 🔥 Tough Question

---

**How would you handle serviceBasedMNCLevel.database connection leaks in a Spring Boot application?**  
**_(Asked in Deloitte)_**

**🧠 Fact:** Leaks occur when DB connections aren't returned to the pool.

**🔁 Flow:** Set leak detection thresholds and monitor with pool metrics.

**🎯 Focus:** Use `spring.datasource.hikari.leak-detection-threshold`.

**💡 Final Tip:** Add logging to track unclosed connections and use `try-with-resources`.

---

### 🌍 Situational / Real-world Questions

---

**1. Your Spring Boot application experiences slow serviceBasedMNCLevel.database queries. How would you diagnose and optimize them?**  
**_(Asked in HCL)_**

**🧠 Fact:** Query slowness can come from poor indexing, N+1 selects, or large data loads.

**🔁 Flow:** Use tools like Hibernate logs, Spring Actuator, and DB query profilers.

**🎯 Focus:** Add proper indexing, optimize joins, paginate results.

**💡 Final Tip:** Enable Hibernate statistics or use JPA `@EntityGraph` for eager fetch tuning.

---

**2. Write a configuration to enable connection pooling with HikariCP in Spring Boot.**  
**_(Asked in HCL)_**

```properties
spring.datasource.hikari.maximum-pool-size=10
spring.datasource.hikari.minimum-idle=5
spring.datasource.hikari.idle-timeout=300000
spring.datasource.hikari.leak-detection-threshold=2000
spring.datasource.hikari.pool-name=custom-hikari-pool
```

---


## 12. Spring Boot Testing

### Basic Questions

1. **What are the key testing frameworks used in Spring Boot?** _(Asked in TCS, Infosys)_
2. **What is the purpose of the `@SpringBootTest` annotation?** _(Asked in Capgemini)_

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

1. **How do you mock dependencies in Spring Boot tests using Mockito?** _(Asked in Wipro)_
2. **What is the difference between `@MockBean` and `@Mock`?** _(Asked in Accenture)_

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

1. **How do you test Spring Data JPA repositories in Spring Boot?** _(Asked in Cognizant)_
2. **What is the role of `@DataJpaTest` in testing serviceBasedMNCLevel.database interactions?** _(Asked in TCS Digital)_

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

1. **How would you test a Spring Boot application with external service dependencies?** _(Asked in Deloitte)_

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


## 13. Spring Boot with Microservices Architecture

### Basic Questions

1. **What are the benefits of using Spring Boot for microservices?** _(Asked in TCS, Infosys)_
2. **How does Spring Boot support service discovery with Eureka?** _(Asked in Capgemini)_

### Basic Code Questions

1. Write a configuration to register a Spring Boot application with Eureka. _(Asked in TCS)_

```properties
spring.application.name=microservice
eureka.client.service-url.defaultZone=http://localhost:8761/eureka
```

### Intermediate Questions

1. **How do you implement inter-service communication in Spring Boot microservices?** _(Asked in Wipro)_
2. **What is the role of Spring Cloud Config in microservices?** _(Asked in Accenture)_

### Intermediate Code Questions

1. Write a configuration to fetch properties from Spring Cloud Config Server. _(Asked in Wipro)_

```properties
spring.config.import=configserver:http://localhost:8888
spring.application.name=my-service
```

### Advanced Questions

1. **How do you implement retry mechanisms for external service calls in Spring Boot?** _(Asked in Cognizant)_
2. **What is the role of Spring Cloud Gateway in routing microservices?** _(Asked in TCS Digital)_

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

1. **How would you design a fault-tolerant microservices architecture with Spring Boot?** _(Asked in Deloitte)_

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

1. **Your microservices application experiences latency in inter-service calls. How would you optimize it?** _(Asked in HCL)_
2. **Write a configuration to enable distributed tracing with Zipkin in a microservices architecture.** _(Asked in HCL)_

```properties
spring.sleuth.sampler.probability=1.0
spring.zipkin.base-url=http://localhost:9411
```
