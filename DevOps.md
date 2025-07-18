# DevOps Interview Questions for Senior Java Full-Stack Developers

This document contains the most frequently asked DevOps interview questions, tailored for senior Java full-stack developers. Questions are organized by topic and include those repeatedly asked in interviews by companies like **TCS, Infosys, Cognizant, Capgemini**, etc., ensuring comprehensive coverage for senior-level interviews. The focus is on DevOps practices relevant to Java-based full-stack development, including CI/CD, containerization, cloud platforms, and integration with Java/Spring Boot applications.

---

## 1. DevOps Fundamentals

### Basic Questions

1. What is DevOps, and how does it benefit Java full-stack development? _(Asked in TCS, Infosys)_
2. What are the key differences between DevOps and traditional software development? _(Asked in Capgemini)_
3. What tools are commonly used in a DevOps pipeline for Java applications? _(Asked in Wipro)_

### Basic Code Questions

1. Write a basic `.gitignore` file for a Java/Spring Boot project. _(Asked in TCS)_

```gitignore
/target/
/build/
*.class
*.log
.idea/
*.iml
```

### Intermediate Questions

1. How do you integrate DevOps practices into a Java full-stack project? _(Asked in Accenture)_
2. What is the role of automation in DevOps for Java applications? _(Asked in Infosys)_
3. How do you ensure code quality in a DevOps pipeline for a Java project? _(Asked in Wipro)_

### Intermediate Code Questions

1. Write a Maven `pom.xml` configuration to include a code quality check with SonarQube. _(Asked in Accenture)_

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.sonarsource.scanner.maven</groupId>
            <artifactId>sonar-maven-plugin</artifactId>
            <version>3.9.1.2184</version>
        </plugin>
    </plugins>
</build>
```

### Advanced Questions

1. How do you implement a zero-downtime deployment for a Java/Spring Boot application? _(Asked in Cognizant)_
2. What are the challenges of adopting DevOps in a legacy Java application? _(Asked in TCS Digital)_
3. How do you manage secrets in a DevOps pipeline for a Java application? _(Asked in Deloitte)_

### Tough Questions

1. How would you design a DevOps pipeline for a microservices-based Java application with high availability? _(Asked in Amazon)_
2. How do you handle rollback strategies in a DevOps pipeline for a Java full-stack application? _(Asked in Amazon)_

### Situational / Real-world Questions

1. Your Java application experiences downtime during deployment. How would you implement a solution to avoid this? _(Asked in HCL)_
2. How would you integrate DevOps practices into a Java full-stack project with a tight release schedule? _(Asked in Infosys)_

---

## 2. CI/CD Pipelines

### Basic Questions

1. What is a CI/CD pipeline, and why is it important for Java full-stack development? _(Asked in TCS, Infosys)_
2. What are the differences between continuous integration, continuous delivery, and continuous deployment? _(Asked in Capgemini)_

### Basic Code Questions

1. Write a basic GitHub Actions workflow for a Java/Spring Boot project to build and test. _(Asked in TCS)_

```yaml
name: CI Pipeline
on: [push]
jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - name: Set up JDK 17
        uses: actions/setup-java@v3
        with:
          java-version: '17'
          distribution: 'temurin'
      - name: Build with Maven
        run: mvn clean install
```

### Intermediate Questions

1. How do you configure a Jenkins pipeline for a Java/Spring Boot application? _(Asked in Wipro)_
2. What is the role of automated testing in a CI/CD pipeline for Java projects? _(Asked in Accenture)_
3. How do you handle environment-specific configurations in a CI/CD pipeline? _(Asked in Infosys)_

### Intermediate Code Questions

1. Write a Jenkins pipeline script to build and deploy a Java/Spring Boot application. _(Asked in Wipro)_

```groovy
pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'mvn clean package'
      }
    }
    stage('Test') {
      steps {
        sh 'mvn test'
      }
    }
    stage('Deploy') {
      steps {
        sh 'java -jar target/myapp.jar'
      }
    }
  }
}
```

### Advanced Questions

1. How do you implement blue-green deployment in a CI/CD pipeline for a Java application? _(Asked in Cognizant)_
2. What strategies do you use to optimize CI/CD pipeline performance for large Java projects? _(Asked in TCS Digital)_
3. How do you handle pipeline failures in a CI/CD process for a Java full-stack application? _(Asked in Deloitte)_

### Advanced Code Questions

1. Write a GitHub Actions workflow to implement blue-green deployment for a Java application. _(Asked in Cognizant)_

```yaml
name: Blue-Green Deployment
on: [push]
jobs:
  deploy:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - name: Set up JDK 17
        uses: actions/setup-java@v3
        with:
          java-version: '17'
      - name: Build
        run: mvn clean package
      - name: Deploy to Blue
        run: ssh user@blue-server 'deploy.sh target/myapp.jar'
      - name: Switch Traffic
        run: ssh user@load-balancer 'switch-to-blue.sh'
```

### Tough Questions

1. How would you design a CI/CD pipeline for a Java microservices architecture with multiple services? _(Asked in Amazon)_
2. How do you ensure security in a CI/CD pipeline for a Java full-stack application? _(Asked in Amazon)_

### Tough Code Questions

1. Write a GitLab CI configuration to deploy a Java microservice to Kubernetes. _(Asked in Amazon)_

```yaml
stages:
  - build
  - deploy
build_job:
  stage: build
  script:
    - mvn clean package
deploy_job:
  stage: deploy
  script:
    - kubectl apply -f k8s/deployment.yaml
```

### Situational / Real-world Questions

1. Your CI/CD pipeline for a Java application is slow. How would you optimize it? _(Asked in HCL)_
2. Write a CI/CD configuration to integrate SonarQube and deploy a Java application to AWS. _(Asked in HCL)_

```yaml
name: CI/CD with SonarQube
on: [push]
jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - name: Set up JDK 17
        uses: actions/setup-java@v3
        with:
          java-version: '17'
      - name: Run SonarQube
        run: mvn sonar:sonar
      - name: Deploy to AWS
        run: aws s3 cp target/myapp.jar s3://my-bucket/
```

---

## 3. Containerization (Docker and Kubernetes)

### Basic Questions

1. What is Docker, and how is it used in Java full-stack development? _(Asked in TCS, Infosys)_
2. What is the difference between a Docker container and a virtual machine? _(Asked in Capgemini)_

### Basic Code Questions

1. Write a Dockerfile for a Java/Spring Boot application. _(Asked in TCS)_

```dockerfile
FROM openjdk:17-jdk-slim
COPY target/myapp.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

### Intermediate Questions

1. How do you containerize a Java/Spring Boot application using Docker? _(Asked in Wipro)_
2. What is Kubernetes, and how does it manage Java application containers? _(Asked in Accenture)_
3. How do you optimize Docker images for Java applications? _(Asked in Infosys)_

### Intermediate Code Questions

1. Write a Docker Compose file to run a Java/Spring Boot application with a MySQL database. _(Asked in Wipro)_

```yaml
version: '3.8'
services:
  app:
    image: myapp:latest
    ports:
      - "8080:8080"
    depends_on:
      - db
    db:
      image: mysql:8.0
      environment:
        MYSQL_ROOT_PASSWORD: password
        MYSQL_DATABASE: mydb
      ports:
        - "3306:3306"
```

### Advanced Questions

1. How do you implement health checks for a Java application in Docker? _(Asked in Cognizant)_
2. How do you scale a Java application using Kubernetes? _(Asked in TCS Digital)_
3. What are the best practices for securing Docker containers for Java applications? _(Asked in Deloitte)_

### Advanced Code Questions

1. Write a Kubernetes deployment YAML for a Java/Spring Boot application with health checks. _(Asked in Cognizant)_

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: myapp
spec:
  replicas: 3
  selector:
    matchLabels:
      app: myapp
  template:
    metadata:
      labels:
        app: myapp
    spec:
      containers:
        - name: myapp
          image: myapp:latest
          ports:
            - containerPort: 8080
          livenessProbe:
            httpGet:
              path: /actuator/health
              port: 8080
            initialDelaySeconds: 15
            periodSeconds: 10
```

### Tough Questions

1. How would you orchestrate a Java microservices architecture using Kubernetes? _(Asked in Amazon)_
2. How do you handle resource limits and auto-scaling for Java applications in Kubernetes? _(Asked in Amazon)_

### Tough Code Questions

1. Write a Kubernetes service YAML to expose a Java microservice with load balancing. _(Asked in Amazon)_

```yaml
apiVersion: v1
kind: Service
metadata:
  name: myapp-service
spec:
  selector:
    app: myapp
  ports:
    - protocol: TCP
      port: 80
      targetPort: 8080
      type: LoadBalancer
```

### Situational / Real-world Questions

1. Your Java application in Kubernetes experiences resource contention. How would you diagnose and resolve it? _(Asked in HCL)_
2. Write a Dockerfile to optimize a Java/Spring Boot application for production. _(Asked in HCL)_

```dockerfile
FROM openjdk:17-jdk-slim
COPY target/myapp.jar /app.jar
ENV JAVA_OPTS="-Xms512m -Xmx512m"
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app.jar"]
```

---

## 4. Cloud Platforms (AWS, Azure)

### Basic Questions

1. What are the benefits of using AWS for deploying Java full-stack applications? _(Asked in TCS, Infosys)_
2. What is the role of AWS Elastic Beanstalk in deploying Java applications? _(Asked in Capgemini)_

### Basic Code Questions

1. Write an AWS CLI command to deploy a Java application to Elastic Beanstalk. _(Asked in TCS)_

```bash
aws elasticbeanstalk create-application-version --application-name myapp --version-label v1 --source-bundle S3Bucket=my-bucket,S3Key=myapp.jar
aws elasticbeanstalk update-environment --environment-name myapp-env --version-label v1
```

### Intermediate Questions

1. How do you deploy a Java/Spring Boot application to AWS ECS? _(Asked in Wipro)_
2. What is the role of AWS Lambda in a Java full-stack application? _(Asked in Accenture)_
3. How do you configure auto-scaling for a Java application on AWS? _(Asked in Infosys)_

### Intermediate Code Questions

1. Write an AWS CloudFormation template to deploy a Java application on EC2. _(Asked in Wipro)_

```yaml
Resources:
  EC2Instance:
    Type: AWS::EC2::Instance
    Properties:
      InstanceType: t2.micro
      ImageId: ami-12345678
      UserData:
        Fn::Base64: |
          #!/bin/bash
          java -jar /myapp.jar
```

### Advanced Questions

1. How do you implement serverless architecture for a Java application using AWS Lambda? _(Asked in Cognizant)_
2. What are the best practices for securing Java applications on AWS? _(Asked in TCS Digital)_
3. How do you integrate AWS S3 with a Java/Spring Boot application for file storage? _(Asked in Deloitte)_

### Advanced Code Questions

1. Write a Java/Spring Boot configuration to integrate with AWS S3. _(Asked in Deloitte)_

```java
@Configuration
public class S3Config {
  @Bean
  public AmazonS3 amazonS3() {
    return AmazonS3ClientBuilder
      .standard()
      .withRegion(Regions.US_EAST_1)
      .build();
  }
}
```

### Tough Questions

1. How would you design a highly available Java application architecture on AWS? _(Asked in Amazon)_
2. How do you optimize costs for a Java application running on AWS? _(Asked in Amazon)_

### Tough Code Questions

1. Write an AWS Lambda function in Java to process S3 events. _(Asked in Amazon)_

```java
public class S3EventHandler implements RequestHandler<S3Event, String> {
  @Override
  public String handleRequest(S3Event event, Context context) {
    for (S3EventNotificationRecord record : event.getRecords()) {
      String bucket = record.getS3().getBucket().getName();
      String key = record.getS3().getObject().getKey();
      // Process file
      return "Processed: " + bucket + "/" + key;
    }
    return "No records processed";
  }
}
```

### Situational / Real-world Questions

1. Your Java application on AWS experiences performance issues. How would you diagnose and resolve them? _(Asked in HCL)_
2. Write an AWS CLI command to scale an ECS service for a Java application. _(Asked in HCL)_

```bash
aws ecs update-service --cluster my-cluster --service my-service --desired-count 3
```

---

## 5. Infrastructure as Code (IaC)

### Basic Questions

1. What is Infrastructure as Code, and how is it used in Java full-stack development? _(Asked in TCS, Infosys)_
2. What are the benefits of using Terraform for Java application infrastructure? _(Asked in Capgemini)_

### Basic Code Questions

1. Write a Terraform configuration to provision an EC2 instance for a Java application. _(Asked in TCS)_

```terraform
resource "aws_instance" "app" {
  ami           = "ami-12345678"
  instance_type = "t2.micro"
  tags = {
    Name = "JavaAppInstance"
  }
}
```

### Intermediate Questions

1. How do you use Ansible to configure a Java application server? _(Asked in Wipro)_
2. What is the difference between Terraform and CloudFormation for Java deployments? _(Asked in Accenture)_
3. How do you manage state in Terraform for Java application infrastructure? _(Asked in Infosys)_

### Intermediate Code Questions

1. Write an Ansible playbook to deploy a Java/Spring Boot application. _(Asked in Wipro)_

```yaml
- hosts: app_servers
  tasks:
    - name: Copy Java application
      copy:
        src: target/myapp.jar
        dest: /app.jar
    - name: Run Java application
      command: java -jar /app.jar
```

### Advanced Questions

1. How do you implement modular Terraform configurations for a Java microservices architecture? _(Asked in Cognizant)_
2. How do you handle rollbacks in Infrastructure as Code for Java applications? _(Asked in TCS Digital)_
3. What are the security best practices for IaC in Java projects? _(Asked in Deloitte)_

### Advanced Code Questions

1. Write a Terraform module to deploy a Java application with an RDS database. _(Asked in Cognizant)_

```terraform
module "app" {
  source = "./modules/app"
  ami    = "ami-12345678"
  db_name = "mydb"
}
```

### Tough Questions

1. How would you design an IaC solution for a multi-region Java application deployment? _(Asked in Amazon)_
2. How do you ensure idempotency in IaC for Java application infrastructure? _(Asked in Amazon)_

### Tough Code Questions

1. Write a Terraform configuration for a multi-region Java application deployment. _(Asked in Amazon)_

```terraform
resource "aws_instance" "app_us" {
  provider      = aws.us-east-1
  ami           = "ami-12345678"
  instance_type = "t2.micro"
}
resource "aws_instance" "app_eu" {
  provider      = aws.eu-west-1
  ami           = "ami-87654321"
  instance_type = "t2.micro"
}
```

### Situational / Real-world Questions

1. Your IaC deployment for a Java application fails due to misconfiguration. How would you debug it? _(Asked in HCL)_
2. Write an Ansible playbook to configure a Java application server with environment variables. _(Asked in HCL)_

```yaml
- hosts: app_servers
  tasks:
    - name: Set environment variables
      lineinfile:
        path: /etc/environment
        line: "{{ item }}"
        with_items:
          - 'JAVA_HOME=/usr/lib/jvm/java-17'
          - 'SPRING_PROFILES_ACTIVE=prod'
```

---

## 6. Monitoring and Logging

### Basic Questions

1. Why is monitoring important for Java full-stack applications? _(Asked in TCS, Infosys)_
2. What tools are used for logging in Java applications? _(Asked in Capgemini)_

### Basic Code Questions

1. Write a Logback configuration for a Java/Spring Boot application. _(Asked in TCS)_

```xml
<configuration>
    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{yyyy-MM-dd HH:mm:ss} %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>
    <root level="info">
        <appender-ref ref="CONSOLE" />
    </root>
</configuration>
```

### Intermediate Questions

1. How do you integrate Spring Boot Actuator with Prometheus for monitoring? _(Asked in Wipro)_
2. What is the role of ELK Stack in logging Java applications? _(Asked in Accenture)_
3. How do you monitor the performance of a Java application in production? _(Asked in Infosys)_

### Intermediate Code Questions

1. Write a Spring Boot configuration to expose Actuator endpoints for monitoring. _(Asked in Wipro)_

```yaml
management.endpoints.web.exposure.include=health,metrics,prometheus
```

### Advanced Questions

1. How do you implement distributed tracing for a Java microservices application? _(Asked in Cognizant)_
2. How do you set up alerts for a Java application using Prometheus and Grafana? _(Asked in TCS Digital)_
3. What are the best practices for logging sensitive data in a Java application? _(Asked in Deloitte)_

### Advanced Code Questions

1. Write a configuration for Spring Cloud Sleuth to enable distributed tracing in a Java application. _(Asked in Cognizant)_

```yaml
spring.sleuth.sampler.probability=1.0
spring.zipkin.base-url=http://localhost:9411
```

### Tough Questions

1. How would you implement a centralized monitoring system for a Java microservices architecture? _(Asked in Amazon)_
2. How do you optimize logging performance in a high-throughput Java application? _(Asked in Amazon)_

### Tough Code Questions

1. Write a Prometheus configuration to scrape metrics from a Java/Spring Boot application. _(Asked in Amazon)_

```yaml
scrape_configs:
  - job_name: 'spring-boot'
    metrics_path: '/actuator/prometheus'
    static_configs:
      - targets: ['localhost:8080']
```

### Situational / Real-world Questions

1. Your Java application generates excessive logs, impacting performance. How would you optimize logging? _(Asked in HCL)_
2. Write a configuration to integrate a Java application with ELK Stack for logging. _(Asked in HCL)_

```yaml
logging.level.org.springframework=INFO
logging.file.name=/logs/myapp.log
```

---

## 7. Version Control and Collaboration

### Basic Questions

1. What is Git, and how is it used in Java full-stack development? _(Asked in TCS, Infosys)_
2. What are the differences between git merge and git rebase? _(Asked in Capgemini)_

### Basic Code Questions

1. Write a .gitignore file for a Java/Spring Boot project with Maven. _(Asked in TCS)_

```gitignore
/target/
/*.log
.idea/
*.iml
```

### Intermediate Questions

1. How do you resolve merge conflicts in a Java project using Git? _(Asked in Wipro)_
2. What is the role of branching strategies in a DevOps pipeline for Java projects? _(Asked in Accenture)_
3. How do you use Git hooks to enforce code quality in a Java project? _(Asked in Infosys)_

### Intermediate Code Questions

1. Write a Git pre-commit hook to run Maven tests for a Java project. _(Asked in Wipro)_

```bash
#!/bin/sh
mvn test
if [ $? -ne 0 ]; then
  echo "Tests failed. Commit aborted."
  exit 1
fi
```

### Advanced Questions

1. How do you implement a Gitflow branching strategy for a Java full-stack project? _(Asked in Cognizant)_
2. How do you manage large repositories in a Java project with multiple teams? _(Asked in TCS Digital)_
3. What are the best practices for code reviews in a Java DevOps pipeline? _(Asked in Deloitte)_

### Advanced Code Questions

1. Write a Git hook to enforce code formatting for a Java project using Checkstyle. _(Asked in Cognizant)_

```bash
#!/bin/sh
mvn checkstyle:check
if [ $? -ne 0 ]; then
  echo "Checkstyle violations detected. Commit aborted."
  exit 1
fi
```

### Tough Questions

1. How would you manage Git repositories for a distributed Java microservices architecture? _(Asked in Amazon)_
2. How do you handle Git repository migrations for a large Java project? _(Asked in Amazon)_

### Tough Code Questions

1. Write a script to automate Git repository backups for a Java project. _(Asked in Amazon)_

```bash
#!/bin/bash
git clone --mirror git@repo:myapp.git /backup/myapp.git
cd /backup/myapp.git
git remote update
```

### Situational / Real-world Questions

1. Your team faces frequent merge conflicts in a Java project. How would you resolve and prevent them? _(Asked in HCL)_
2. Write a Git workflow to automate pull request validation for a Java project. _(Asked in HCL)_

```yaml
name: Pull Request Validation
on: [pull_request]
jobs:
  validate:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - name: Set up JDK 17
        uses: actions/setup-java@v3
        with:
          java-version: '17'
      - name: Run Tests
        run: mvn test
```

---

## 8. Security in DevOps

### Basic Questions

1. Why is security important in a DevOps pipeline for Java applications? _(Asked in TCS, Infosys)_
2. What are common security vulnerabilities in Java full-stack applications? _(Asked in Capgemini)_

### Basic Code Questions

1. Write a Spring Boot configuration to enable HTTPS for a Java application. _(Asked in TCS)_

```yaml
server.ssl.key-store=classpath:keystore.p12
server.ssl.key-store-password=password
server.ssl.key-store-type=PKCS12
server.port=8443
```

### Intermediate Questions

1. How do you secure environment variables in a Java DevOps pipeline? _(Asked in Wipro)_
2. What is the role of OWASP in securing Java applications? _(Asked in Accenture)_
3. How do you implement role-based access control in a DevOps pipeline? _(Asked in Infosys)_

### Intermediate Code Questions

1. Write a configuration to secure a Java application’s secrets using AWS Secrets Manager. _(Asked in Wipro)_

```java
@Configuration
public class SecretsConfig {
  @Bean
  public AWSSecretsManager secretsManager() {
    return AWSSecretsManagerClientBuilder.standard().build();
  }
}
```

### Advanced Questions

1. How do you implement security scanning in a CI/CD pipeline for a Java application? _(Asked in Cognizant)_
2. How do you secure a Java microservices architecture in a DevOps pipeline? _(Asked in TCS Digital)_
3. What are the best practices for securing Docker containers running Java applications? _(Asked in Deloitte)_

### Advanced Code Questions

1. Write a configuration to integrate Dependabot for dependency scanning in a Java project. _(Asked in Cognizant)_

```yaml
version: 2
updates:
  - package-ecosystem: "maven"
    directory: "/"
    schedule:
      interval: "daily"
```

### Tough Questions

1. How would you design a secure DevOps pipeline for a Java application handling sensitive data? _(Asked in Amazon)_
2. How do you implement zero-trust security in a Java microservices architecture? _(Asked in Amazon)_

### Tough Code Questions

1. Write a configuration to secure a Java application with OAuth2 in a DevOps pipeline. _(Asked in Amazon)_

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
      .oauth2ResourceServer().jwt();
    return http.build();
  }
}
```

### Situational / Real-world Questions

1. Your Java application is vulnerable to SQL injection. How would you secure it in a DevOps pipeline? _(Asked in HCL)_
2. Write a configuration to scan a Java application for vulnerabilities using OWASP ZAP. _(Asked in HCL)_

```yaml
name: Security Scan
on: [push]
jobs:
  scan:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - name: Run OWASP ZAP
        uses: zaproxy/action-full-scan@v0.4.0
        with:
          target:Jagadish
```

---

## 9. Testing and Quality Assurance in DevOps

### Basic Questions

1. Why is automated testing critical in a DevOps pipeline for Java applications? _(Asked in TCS, Infosys)_
2. What are the differences between unit, integration, and end-to-end tests in Java projects? _(Asked in Capgemini)_

### Basic Code Questions

1. Write a JUnit test case for a Java/Spring Boot service. _(Asked in TCS)_

```java
@SpringBootTest
public class UserServiceTest {
  @Autowired
  private UserService userService;

  @Test
  void testGetUserById() {
    User user = userService.getUserById(1L);
    assertEquals("John", user.getName());
  }
}
```

### Intermediate Questions

1. How do you integrate automated tests into a CI/CD pipeline for a Java application? _(Asked in Wipro)_
2. What is the role of code coverage tools in Java DevOps pipelines? _(Asked in Accenture)_
3. How do you ensure test reliability in a Java full-stack application? _(Asked in Infosys)_

### Intermediate Code Questions

1. Write a GitHub Actions workflow to run JUnit tests for a Java/Spring Boot application. _(Asked in Wipro)_

```yaml
name: Run Tests
on: [push]
jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - name: Set up JDK 17
        uses: actions/setup-java@v3
        with:
          java-version: '17'
      - name: Run Tests
        run: mvn test
```

### Advanced Questions

1. How do you implement performance testing in a DevOps pipeline for a Java application? _(Asked in Cognizant)_
2. How do you handle flaky tests in a Java DevOps pipeline? _(Asked in TCS Digital)_
3. What are the best practices for testing Java microservices in a DevOps environment? _(Asked in Deloitte)_

### Advanced Code Questions

1. Write a JMeter test script to perform load testing on a Java/Spring Boot REST API. _(Asked in Cognizant)_

```yaml
<jmeterTestPlan>
  <ThreadGroup guiclass="ThreadGroupGui" testclass="ThreadGroup">
    <stringProp name="ThreadGroup.num_threads">100</stringProp>
    <HTTPSamplerProxy guiclass="HttpTestSampleGui" testclass="HTTPSamplerProxy">
      <stringProp name="HTTPSampler.path">/api/users</stringProp>
      <stringProp name="HTTPSampler.method">GET</stringProp>
    </HTTPSamplerProxy>
  </ThreadGroup>
</jmeterTestPlan>
```

### Tough Questions

1. How would you design a testing strategy for a Java microservices architecture in a DevOps pipeline? _(Asked in Amazon)_
2. How do you ensure test coverage for a complex Java full-stack application? _(Asked in Amazon)_

### Tough Code Questions

1. Write a configuration to integrate performance tests into a CI/CD pipeline for a Java application. _(Asked in Amazon)_

```yaml
name: Performance Test
on: [push]
jobs:
  perf-test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - name: Run JMeter
        run: jmeter -n -t test.jmx -l results.jtl
```

### Situational / Real-world Questions

1. Your Java application fails tests in the CI/CD pipeline intermittently. How would you diagnose and fix this? _(Asked in HCL)_
2. Write a configuration to run end-to-end tests for a Java/Spring Boot application in a CI/CD pipeline. _(Asked in HCL)_

```yaml
name: E2E Tests
on: [push]
jobs:
  e2e:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - name: Set up JDK 17
        uses: actions/setup-java@v3
        with:
          java-version: '17'
      - name: Run E2E Tests
        run: mvn verify -P e2e-tests
```

---

## 10. Microservices and DevOps

### Basic Questions

1. How does DevOps support Java microservices development? _(Asked in TCS, Infosys)_
2. What is the role of service discovery in a Java microservices architecture? _(Asked in Capgemini)_

### Basic Code Questions

1. Write a configuration to enable Eureka service discovery in a Java/Spring Boot microservice. _(Asked in TCS)_

```yaml
spring.application.name=microservice
eureka.client.service-url.defaultZone=http://localhost:8761/eureka
```

### Intermediate Questions

1. How do you implement circuit breakers in a Java microservices architecture? _(Asked in Wipro)_
2. What is the role of API Gateway in a Java microservices DevOps pipeline? _(Asked in Accenture)_
3. How do you handle inter-service communication in a Java microservices architecture? _(Asked in Infosys)_

### Intermediate Code Questions

1. Write a configuration to enable Resilience4j circuit breaker in a Java/Spring Boot microservice. _(Asked in Wipro)_

```yaml
resilience4j.circuitbreaker.instances.myService.backend=myService
resilience4j.circuitbreaker.instances.myService.slidingWindowSize=10
```

### Advanced Questions

1. How do you implement distributed tracing for Java microservices in a DevOps pipeline? _(Asked in Cognizant)_
2. How do you manage configuration for multiple Java microservices? _(Asked in TCS Digital)_
3. What are the challenges of deploying Java microservices in a DevOps environment? _(Asked in Deloitte)_

### Advanced Code Questions

1. Write a configuration to integrate Spring Cloud Config Server for a Java microservice. _(Asked in TCS Digital)_

```yaml
spring.config.import=configserver:http://localhost:8888
spring.application.name=my-service
```

### Tough Questions

1. How would you design a fault-tolerant Java microservices architecture in a DevOps pipeline? _(Asked in Amazon)_
2. How do you handle data consistency across Java microservices in a DevOps environment? _(Asked in Amazon)_

### Tough Code Questions

1. Write a configuration to enable retry logic for a Java microservice using Spring Retry. _(Asked in Amazon)_

```java
@Service
public class MyService {
  @Retryable(maxAttempts = 3, backoff = @Backoff(delay = 1000))
  public void callExternalService() {
    // Call external service
  }
}
```

### Situational / Real-world Questions

1. Your Java microservices architecture experiences latency in inter-service calls. How would you optimize it? _(Asked in HCL)_
2. Write a Kubernetes configuration to deploy multiple Java microservices with service discovery. _(Asked in HCL)_

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: microservice
spec:
  replicas: 2
  selector:
    matchLabels:
      app: microservice
  template:
    metadata:
      labels:
        app: microservice
    spec:
      containers:
        - name: microservice
          image: microservice:latest
          env:
            - name: EUREKA_SERVER
              value: "http://eureka:8761/eureka"

