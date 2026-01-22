# 🧠 Apache Kafka Interview Questions for Senior Backend Developers (Complete 360° Blueprint)

This document contains the most comprehensive Apache Kafka interview questions, curated for senior backend developers transitioning to full-stack or advanced roles. Questions are organized by topic and include those repeatedly asked in companyInterviews by companies like **TCS, Infosys, Cognizant, Capgemini**, etc., along with additional critical questions to ensure comprehensive coverage for senior-level companyInterviews.

---

## 1. Apache Kafka Fundamentals

### Basic Questions

1. What is Apache Kafka and how does it work? _(Asked in TCS, Infosys)_

**🧩 Foundation:** Apache Kafka is a distributed event streaming platform built for high-throughput, fault-tolerant, real-time data processing. It's designed as a publish-subscribe messaging system that can handle trillions of events per day across distributed systems.

**⚙️ Function:** Kafka serves as the backbone for real-time data pipelines, enabling decoupled communication between microservices, event sourcing, stream processing, and data integration across organizations.

**🚀 Features:**
- Distributed and fault-tolerant architecture
- High throughput and low latency
- Persistent message storage
- Horizontal scalability
- Real-time stream processing
- Exactly-once semantics support

**🔁 Flow:**
```java
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.clients.consumer.*;
import java.util.Properties;
import java.util.Collections;
import java.time.Duration;

// Producer Configuration
Properties producerProps = new Properties();
producerProps.put("bootstrap.servers", "localhost:9092");
producerProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
producerProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
producerProps.put("acks", "all");
producerProps.put("retries", 3);
producerProps.put("batch.size", 16384);
producerProps.put("linger.ms", 1);
producerProps.put("buffer.memory", 33554432);

// Create Producer
KafkaProducer<String, String> producer = new KafkaProducer<>(producerProps);

// Send Message
ProducerRecord<String, String> record = new ProducerRecord<>(
    "user-events", 
    "user-123", 
    "{\"action\":\"login\",\"timestamp\":\"2024-01-01T10:00:00Z\"}"
);

producer.send(record, (metadata, exception) -> {
    if (exception != null) {
        System.err.println("Error sending record: " + exception);
    } else {
        System.out.printf("Record sent to topic %s partition %d offset %d%n",
            metadata.topic(), metadata.partition(), metadata.offset());
    }
});

producer.close();
```

**🐞 Fixes:** Common issues include improper configuration of acks, not handling producer exceptions, forgetting to close producers, and not implementing proper error handling for network failures.

---

2. How is Kafka different from traditional message brokers like RabbitMQ? _(Asked in Capgemini)_

**🧩 Foundation:** Kafka differs fundamentally from traditional message brokers in architecture, data retention, and use cases. While RabbitMQ is a message broker, Kafka is an event streaming platform designed for different paradigms.

**⚙️ Function:** Understanding these differences helps in choosing the right technology for specific use cases and architectural patterns.

**🚀 Features:**
- **Message Retention:** Kafka persists messages to disk; RabbitMQ keeps in memory
- **Throughput:** Kafka handles millions of messages/sec; RabbitMQ handles thousands
- **Consumption Model:** Kafka allows multiple consumers to read same message; RabbitMQ delivers once
- **Ordering:** Kafka maintains order per partition; RabbitMQ maintains global order
- **Scaling:** Kafka scales horizontally; RabbitMQ scales vertically primarily

**🔁 Flow:**
```java
// Kafka Consumer Group (Multiple consumers can read same messages)
Properties consumerProps = new Properties();
consumerProps.put("bootstrap.servers", "localhost:9092");
consumerProps.put("group.id", "user-processing-group");
consumerProps.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
consumerProps.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
consumerProps.put("auto.offset.reset", "earliest");
consumerProps.put("enable.auto.commit", "false");

KafkaConsumer<String, String> consumer = new KafkaConsumer<>(consumerProps);
consumer.subscribe(Collections.singletonList("user-events"));

try {
    while (true) {
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
        for (ConsumerRecord<String, String> record : records) {
            System.out.printf("Consumer Group: %s, Topic: %s, Partition: %d, Offset: %d, Key: %s, Value: %s%n",
                "user-processing-group", record.topic(), record.partition(), 
                record.offset(), record.key(), record.value());
            
            // Process the message
            processUserEvent(record.value());
            
            // Manual commit after successful processing
            consumer.commitSync();
        }
    }
} finally {
    consumer.close();
}

private void processUserEvent(String eventData) {
    // Business logic for processing user events
    // This method can be called by multiple consumers in the same group
    // Each message will be processed by only one consumer in the group
}
```

**🐞 Fixes:** Avoid using Kafka for simple request-reply patterns, don't use Kafka for small message volumes where RabbitMQ is more suitable, and ensure proper consumer group configuration for load balancing.

---

3. What are the key components of Kafka architecture? _(Asked in Wipro)_

**🧩 Foundation:** Kafka's architecture consists of several key components that work together to provide a distributed, fault-tolerant messaging system: Producers, Consumers, Topics, Partitions, Brokers, and Zookeeper (or KRaft in newer versions).

**⚙️ Function:** Each component has a specific role in ensuring reliable message delivery, scalability, and fault tolerance across distributed systems.

**🚀 Features:**
- **Producer:** Publishes messages to topics
- **Consumer:** Subscribes to topics and processes messages
- **Topic:** Logical channel for message categorization
- **Partition:** Physical division of topics for parallel processing
- **Broker:** Kafka server that stores and serves messages
- **Zookeeper/KRaft:** Cluster coordination and metadata management

**🔁 Flow:**
```java
// Topic Creation and Management
import org.apache.kafka.clients.admin.*;

Properties adminProps = new Properties();
adminProps.put("bootstrap.servers", "localhost:9092");

try (AdminClient admin = AdminClient.create(adminProps)) {
    // Create topic with partitions and replication
    NewTopic newTopic = new NewTopic("orders", 3, (short) 1);
    CreateTopicsResult result = admin.createTopics(Collections.singleton(newTopic));
    
    // Wait for creation to complete
    result.all().get();
    
    // List all topics
    ListTopicsResult topics = admin.listTopics();
    Set<String> topicNames = topics.names().get();
    System.out.println("Available topics: " + topicNames);
    
    // Describe topic details
    DescribeTopicsResult describeResult = admin.describeTopics(Collections.singletonList("orders"));
    Map<String, TopicDescription> topicDescriptions = describeResult.all().get();
    
    for (Map.Entry<String, TopicDescription> entry : topicDescriptions.entrySet()) {
        TopicDescription description = entry.getValue();
        System.out.printf("Topic: %s, Partitions: %d%n", 
            entry.getKey(), description.partitions().size());
        
        for (TopicPartitionInfo partitionInfo : description.partitions()) {
            System.out.printf("  Partition %d: Leader=%d, Replicas=%s%n",
                partitionInfo.partition(), partitionInfo.leader().id(),
                partitionInfo.replicas().stream()
                    .map(Node::id)
                    .collect(Collectors.toList()));
        }
    }
}
```

**🐞 Fixes:** Ensure proper partition count planning based on throughput requirements, configure appropriate replication factors for fault tolerance, and monitor topic metadata for cluster health.

---

4. What is a Kafka topic and how do partitions work? _(Asked in Accenture)_

**🧩 Foundation:** A Kafka topic is a category or feed name to which records are published. Topics are divided into partitions, which are ordered, immutable sequences of messages that enable parallel processing and horizontal scaling.

**⚙️ Function:** Topics provide logical separation of data streams, while partitions enable parallel processing, fault tolerance, and horizontal scaling within each topic.

**🚀 Features:**
- **Ordering:** Messages within a partition are ordered by offset
- **Parallelism:** Multiple partitions allow concurrent processing
- **Scaling:** Can increase partitions (but not decrease) for more parallelism
- **Replication:** Each partition can be replicated across brokers
- **Distribution:** Partitions are distributed across brokers in the cluster

**🔁 Flow:**
```java
// Producer with Custom Partitioning
public class CustomPartitioner implements Partitioner {
    
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, 
                        Object value, byte[] valueBytes, Cluster cluster) {
        
        List<PartitionInfo> partitions = cluster.partitionsForTopic(topic);
        int numPartitions = partitions.size();
        
        if (keyBytes == null) {
            // Round-robin for null keys
            return ThreadLocalRandom.current().nextInt(numPartitions);
        }
        
        // Hash-based partitioning for consistent key routing
        return Math.abs(Objects.hash(key)) % numPartitions;
    }
    
    @Override
    public void close() {}
    
    @Override
    public void configure(Map<String, ?> configs) {}
}

// Producer Configuration with Custom Partitioner
Properties producerProps = new Properties();
producerProps.put("bootstrap.servers", "localhost:9092");
producerProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
producerProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
producerProps.put("partitioner.class", CustomPartitioner.class.getName());

KafkaProducer<String, String> producer = new KafkaProducer<>(producerProps);

// Send messages with different keys to demonstrate partitioning
Map<String, String> messages = Map.of(
    "user-123", "Login event for user 123",
    "user-456", "Purchase event for user 456", 
    "user-789", "Logout event for user 789"
);

for (Map.Entry<String, String> entry : messages.entrySet()) {
    ProducerRecord<String, String> record = new ProducerRecord<>(
        "user-events", entry.getKey(), entry.getValue());
    
    producer.send(record, (metadata, exception) -> {
        if (exception == null) {
            System.out.printf("Message sent to partition %d with offset %d for key %s%n",
                metadata.partition(), metadata.offset(), entry.getKey());
        }
    });
}

producer.close();
```

**🐞 Fixes:** Plan partition count carefully as it cannot be reduced, ensure even distribution of keys to avoid partition hotspots, and monitor partition-level metrics for performance optimization.

---

5. How does Kafka ensure message durability and reliability? _(Asked in Deloitte)_

**🧩 Foundation:** Kafka ensures message durability through multiple mechanisms: replication, acknowledgments, commit logs, and configurable retention policies. It provides different levels of reliability guarantees based on configuration.

**⚙️ Function:** These mechanisms work together to prevent data loss, ensure message persistence, and provide fault tolerance against broker failures and network issues.

**🚀 Features:**
- **Replication:** Multiple copies of data across brokers
- **Acknowledgment Levels:** Configurable acknowledgment requirements
- **Commit Logs:** Persistent storage to disk
- **Retention Policies:** Time and size-based message retention
- **Fault Tolerance:** Automatic failover and recovery

**🔁 Flow:**
```java
// High Reliability Producer Configuration
Properties reliableProducerProps = new Properties();
reliableProducerProps.put("bootstrap.servers", "localhost:9092");
reliableProducerProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
reliableProducerProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

// Reliability Configuration
reliableProducerProps.put("acks", "all"); // Wait for all replicas to acknowledge
reliableProducerProps.put("retries", Integer.MAX_VALUE); // Retry indefinitely
reliableProducerProps.put("max.in.flight.requests.per.connection", 1); // Prevent reordering
reliableProducerProps.put("enable.idempotence", true); // Prevent duplicates
reliableProducerProps.put("retry.backoff.ms", 100); // Retry delay

// Compression for better performance
reliableProducerProps.put("compression.type", "snappy");

KafkaProducer<String, String> reliableProducer = new KafkaProducer<>(reliableProducerProps);

// Send critical business message with callback
ProducerRecord<String, String> criticalRecord = new ProducerRecord<>(
    "financial-transactions", 
    "txn-12345", 
    "{\"amount\":1000,\"currency\":\"USD\",\"timestamp\":\"2024-01-01T10:00:00Z\"}"
);

reliableProducer.send(criticalRecord, new Callback() {
    @Override
    public void onCompletion(RecordMetadata metadata, Exception exception) {
        if (exception != null) {
            System.err.println("Failed to send critical message: " + exception);
            // Implement retry logic or alert system
            handleCriticalMessageFailure(criticalRecord, exception);
        } else {
            System.out.printf("Critical message successfully sent to partition %d offset %d%n",
                metadata.partition(), metadata.offset());
            // Log successful delivery for audit trail
            logSuccessfulDelivery(metadata);
        }
    }
});

// Flush to ensure all messages are sent before closing
reliableProducer.flush();
reliableProducer.close();

private void handleCriticalMessageFailure(ProducerRecord<String, String> record, Exception exception) {
    // Implement dead letter queue or retry mechanism
    System.err.println("Critical message failed: " + record.value());
    // Could send to a separate topic for manual processing
}

private void logSuccessfulDelivery(RecordMetadata metadata) {
    // Audit logging for compliance
    System.out.println("Message delivered successfully: " + metadata.toString());
}
```

**🐞 Fixes:** Balance reliability with performance by choosing appropriate ack levels, monitor replication lag, implement proper error handling and retry mechanisms, and use idempotent producers to prevent duplicates.

---

### 2. **What are the key components of Kafka?**

**Definition:**
Kafka consists of the following components:

* **Producer:** Publishes messages to Kafka topics.
* **Consumer:** Subscribes to topics and processes messages.
* **Topic:** Logical channel to which producers write and consumers read.
* **Broker:** Kafka server that stores and serves messages.
* **Zookeeper:** Coordinates and manages the Kafka cluster.

**Code Snippet (Consumer):**

```java
Properties props = new Properties();
props.put("bootstrap.servers", "localhost:9092");
props.put("group.id", "test-group");
props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
consumer.subscribe(Collections.singletonList("my-topic"));

while (true) {
    ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
    for (ConsumerRecord<String, String> record : records) {
        System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
    }
}
```

**Follow-up Questions:**

* What happens if Zookeeper goes down?
* Can Kafka work without Zookeeper (e.g., in newer versions)?

---

### 3. **What is a Kafka topic and partition?**

**Definition:**
A **topic** is a category/feed name to which records are sent. Each topic can be split into **partitions**, which allow Kafka to parallelize processing and scale horizontally.

**Code Example (Creating Topic via CLI):**

```bash
kafka-topics.sh --create --topic my-topic --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1
```

**Follow-up Questions:**

* How does partitioning help with load balancing?
* How are records assigned to partitions?

---

### 4. **What is a Kafka Producer and Consumer?**

**Definition:**

* A **producer** sends records to a topic.
* A **consumer** subscribes to one or more topics and reads records from them.

**Code Example (Producer & Consumer Summary):**
*See Producer code in Question 1 and Consumer code in Question 2.*

**Follow-up Questions:**

* What is the difference between Consumer Group and Consumer?
* What is at-least-once vs. exactly-once delivery in Kafka?

---

### 5. **What is a Kafka Broker?**

**Definition:**
A Kafka **broker** is a Kafka server that receives and stores messages from producers and serves them to consumers.

**Command Example (Listing Brokers):**

```bash
zookeeper-shell.sh localhost:2181 ls /brokers/ids
```

**Follow-up Questions:**

* How does Kafka distribute partitions among brokers?
* What happens when a broker goes down?

---

### 6. **What is Zookeeper and why is it used in Kafka?**

**Definition:**
Zookeeper is a centralized service used by Kafka for cluster coordination, leader election for partitions, and storing metadata.

**Important Note:**
Kafka newer versions (from 2.8+) are moving towards **KRaft mode**, eliminating the need for Zookeeper.

**Follow-up Questions:**

* What is the role of Zookeeper in leader election?
* How does Kafka behave in a Zookeeper outage?

---

### 7. **What is a Kafka Consumer Group?**

**Definition:**
A Consumer Group is a group of consumers sharing the same group id. Each message from a partition is consumed by only one consumer in the group.

**Follow-up Questions:**

* What happens when a new consumer joins the group?
* How does Kafka ensure message consumption by only one consumer in a group?

---

### 8. **How does Kafka ensure message durability?**

**Definition:**
Kafka ensures message durability by writing records to disk and replicating them across multiple brokers (using replication factor).

**Follow-up Questions:**

* What happens if a broker with the leader partition crashes?
* How does Kafka choose a new leader?

---

### 9. **What is offset in Kafka?**

**Definition:**
Offset is a unique identifier assigned to each record within a partition. It helps consumers track their position in the stream.

**Code Example (Commit Offset):**

```java
consumer.commitSync();
```

**Follow-up Questions:**

* What is the difference between auto-commit and manual commit?
* Can offsets be stored externally?

---

### 10. **What are the delivery guarantees provided by Kafka?**

**Definition:**
Kafka provides three delivery guarantees:

* **At most once:** Messages may be lost but never redelivered.
* **At least once:** Messages are never lost but may be redelivered.
* **Exactly once:** No loss, no duplication (complex).

**Follow-up Questions:**

* How to implement exactly-once delivery in Kafka?
* What configuration affects delivery guarantees?

---


---

## 2. Kafka Consumer Groups and Offset Management

### Intermediate Questions

1. What is a Kafka Consumer Group and how does it work? _(Asked in Cognizant)_

**🧩 Foundation:** A Consumer Group is a collection of consumers that work together to consume messages from one or more topics. Each message in a partition is consumed by only one consumer within the group, enabling parallel processing while preventing duplicate message consumption.

**⚙️ Function:** Consumer groups provide load balancing, fault tolerance, and horizontal scaling for message processing, allowing multiple consumers to work together to process messages from partitions in parallel.

**🚀 Features:**
- **Load Balancing:** Messages are distributed across consumers in the group
- **Fault Tolerance:** If a consumer fails, its partitions are reassigned to other consumers
- **Scalability:** Add or remove consumers dynamically
- **Offset Management:** Group-level offset tracking and management
- **Rebalancing:** Automatic partition reassignment when group membership changes

**🔁 Flow:**
```java
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import java.util.*;
import java.time.Duration;

// Consumer Group Configuration
Properties consumerGroupProps = new Properties();
consumerGroupProps.put("bootstrap.servers", "localhost:9092");
consumerGroupProps.put("group.id", "order-processing-group");
consumerGroupProps.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
consumerGroupProps.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
consumerGroupProps.put("auto.offset.reset", "earliest");
consumerGroupProps.put("enable.auto.commit", "false");
consumerGroupProps.put("session.timeout.ms", 30000);
consumerGroupProps.put("heartbeat.interval.ms", 3000);
consumerGroupProps.put("max.poll.records", 500);
consumerGroupProps.put("max.poll.interval.ms", 300000);

// Create Consumer
KafkaConsumer<String, String> consumer = new KafkaConsumer<>(consumerGroupProps);

// Subscribe to topics
consumer.subscribe(Arrays.asList("orders", "payments", "inventory"), new ConsumerRebalanceListener() {
    @Override
    public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
        System.out.println("Partitions revoked: " + partitions);
        // Commit offsets before partition reassignment
        consumer.commitSync();
    }
    
    @Override
    public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
        System.out.println("Partitions assigned: " + partitions);
        // Can reset offsets here if needed
        // consumer.seekToBeginning(partitions);
    }
});

try {
    while (true) {
        ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
        
        for (ConsumerRecord<String, String> record : records) {
            System.out.printf("Consumer Group: %s, Topic: %s, Partition: %d, Offset: %d, Key: %s, Value: %s%n",
                "order-processing-group", record.topic(), record.partition(), 
                record.offset(), record.key(), record.value());
            
            try {
                // Process the message
                processOrderMessage(record);
                
                // Commit offset after successful processing
                consumer.commitSync(Collections.singletonMap(
                    new TopicPartition(record.topic(), record.partition()),
                    new OffsetAndMetadata(record.offset() + 1)
                ));
                
            } catch (Exception e) {
                System.err.println("Error processing message: " + e);
                // Handle error - could send to DLQ or retry
                handleProcessingError(record, e);
            }
        }
    }
} finally {
    consumer.close();
}

private void processOrderMessage(ConsumerRecord<String, String> record) {
    // Business logic for processing order messages
    // This will be called by only one consumer in the group for each message
    System.out.println("Processing order: " + record.value());
    
    // Simulate processing time
    try {
        Thread.sleep(100);
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
}

private void handleProcessingError(ConsumerRecord<String, String> record, Exception e) {
    // Implement error handling strategy
    // Could send to dead letter queue, retry, or alert
    System.err.println("Failed to process message: " + record.value() + ", Error: " + e.getMessage());
}
```

**🐞 Fixes:** Avoid committing offsets before processing is complete, handle rebalancing properly to prevent data loss, and implement proper error handling for failed message processing.

---

2. How do you manage Kafka offsets effectively? _(Asked in Tech Mahindra)_

**🧩 Foundation:** Offset management is crucial for ensuring reliable message processing. Kafka provides both automatic and manual offset management options, each with different trade-offs in terms of performance, reliability, and complexity.

**⚙️ Function:** Proper offset management ensures that consumers can resume processing from the correct position after failures, prevents message loss or duplication, and enables precise control over message processing flow.

**🚀 Features:**
- **Auto-commit:** Automatic offset commits at configurable intervals
- **Manual-commit:** Explicit control over when offsets are committed
- **Commit Strategies:** Sync vs async commit options
- **Offset Reset:** Configurable behavior when no committed offset exists
- **External Offset Storage:** Store offsets in external systems for complex scenarios

**🔁 Flow:**
```java
// Advanced Offset Management
public class AdvancedOffsetManager {
    
    private final KafkaConsumer<String, String> consumer;
    private final Map<TopicPartition, OffsetAndMetadata> offsetsToCommit = new HashMap<>();
    private final AtomicLong commitCounter = new AtomicLong(0);
    
    public AdvancedOffsetManager() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "advanced-offset-group");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("auto.offset.reset", "earliest");
        props.put("enable.auto.commit", "false");
        
        this.consumer = new KafkaConsumer<>(props);
    }
    
    public void processWithBatchCommit() {
        consumer.subscribe(Arrays.asList("orders", "payments"));
        
        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
                
                for (ConsumerRecord<String, String> record : records) {
                    try {
                        // Process message
                        processMessage(record);
                        
                        // Track offset for batch commit
                        TopicPartition partition = new TopicPartition(record.topic(), record.partition());
                        offsetsToCommit.put(partition, new OffsetAndMetadata(record.offset() + 1));
                        
                        // Commit every 100 messages or every 30 seconds
                        if (commitCounter.incrementAndGet() % 100 == 0) {
                            commitOffsets();
                        }
                        
                    } catch (Exception e) {
                        System.err.println("Error processing message: " + e);
                        // Don't commit offset for failed messages
                        // Could implement retry logic or send to DLQ
                    }
                }
                
                // Time-based commit (every 30 seconds)
                if (System.currentTimeMillis() - lastCommitTime > 30000) {
                    commitOffsets();
                }
            }
        } finally {
            // Final commit before closing
            commitOffsets();
            consumer.close();
        }
    }
    
    private void commitOffsets() {
        if (!offsetsToCommit.isEmpty()) {
            try {
                // Sync commit for reliability
                consumer.commitSync(offsetsToCommit);
                System.out.println("Committed offsets: " + offsetsToCommit);
                offsetsToCommit.clear();
                lastCommitTime = System.currentTimeMillis();
            } catch (CommitFailedException e) {
                System.err.println("Commit failed: " + e);
                // Handle commit failure
            }
        }
    }
    
    public void resetOffsetsToTimestamp(String topic, long timestamp) {
        TopicPartition partition = new TopicPartition(topic, 0);
        Map<TopicPartition, OffsetAndTimestamp> offsets = consumer.offsetsForTimes(
            Collections.singletonMap(partition, timestamp));
        
        if (offsets.get(partition) != null) {
            consumer.seek(partition, offsets.get(partition).offset());
            System.out.println("Reset offset for " + partition + " to " + offsets.get(partition).offset());
        }
    }
    
    public void seekToBeginning(String topic) {
        TopicPartition partition = new TopicPartition(topic, 0);
        consumer.seekToBeginning(Collections.singleton(partition));
        System.out.println("Seeked to beginning of " + partition);
    }
    
    public void seekToEnd(String topic) {
        TopicPartition partition = new TopicPartition(topic, 0);
        consumer.seekToEnd(Collections.singleton(partition));
        System.out.println("Seeked to end of " + partition);
    }
    
    private void processMessage(ConsumerRecord<String, String> record) {
        // Business logic
        System.out.println("Processing: " + record.value());
    }
    
    private long lastCommitTime = System.currentTimeMillis();
}

// Usage example
public class OffsetManagementExample {
    public static void main(String[] args) {
        AdvancedOffsetManager offsetManager = new AdvancedOffsetManager();
        
        // Process with batch commits
        offsetManager.processWithBatchCommit();
        
        // Or reset offsets to specific timestamp
        // offsetManager.resetOffsetsToTimestamp("orders", System.currentTimeMillis() - 3600000);
    }
}
```

**🐞 Fixes:** Always commit offsets after successful processing, implement proper error handling for failed commits, use batch commits for better performance, and consider using external offset storage for complex scenarios.

---

3. How does Kafka handle consumer group rebalancing? _(Asked in HCL)_

**🧩 Foundation:** Consumer group rebalancing is the process of redistributing partitions among consumers when the group membership changes (consumers join, leave, or fail). This ensures load balancing and fault tolerance across the consumer group.

**⚙️ Function:** Rebalancing maintains optimal partition distribution, handles consumer failures gracefully, and ensures that all partitions are actively consumed by available consumers in the group.

**🚀 Features:**
- **Automatic Rebalancing:** Triggered by membership changes
- **Rebalancing Strategies:** Range, RoundRobin, Sticky, Cooperative strategies
- **Rebalance Listeners:** Custom logic during rebalancing
- **Session Management:** Heartbeat and session timeout configuration
- **Partition Assignment:** Fair distribution of partitions among consumers

**🔁 Flow:**
```java
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.consumer.internals.AbstractPartitionAssignor;
import org.apache.kafka.common.TopicPartition;
import java.util.*;
import java.time.Duration;

// Custom Rebalancing Strategy
public class CustomPartitionAssignor extends AbstractPartitionAssignor {
    
    @Override
    public String name() {
        return "custom";
    }
    
    @Override
    public Map<String, List<TopicPartition>> assign(Map<String, Integer> partitionsPerTopic,
                                                   Map<String, Subscription> subscriptions) {
        
        Map<String, List<TopicPartition>> assignment = new HashMap<>();
        
        for (String memberId : subscriptions.keySet()) {
            assignment.put(memberId, new ArrayList<>());
        }
        
        // Custom assignment logic - distribute partitions evenly
        List<String> members = new ArrayList<>(subscriptions.keySet());
        int memberIndex = 0;
        
        for (Map.Entry<String, Integer> entry : partitionsPerTopic.entrySet()) {
            String topic = entry.getKey();
            int numPartitions = entry.getValue();
            
            for (int partition = 0; partition < numPartitions; partition++) {
                TopicPartition topicPartition = new TopicPartition(topic, partition);
                String memberId = members.get(memberIndex % members.size());
                assignment.get(memberId).add(topicPartition);
                memberIndex++;
            }
        }
        
        return assignment;
    }
}

// Consumer with Custom Rebalancing
public class RebalancingConsumer {
    
    private final KafkaConsumer<String, String> consumer;
    private final String consumerId;
    
    public RebalancingConsumer(String consumerId) {
        this.consumerId = consumerId;
        
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "rebalancing-group");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("partition.assignment.strategy", CustomPartitionAssignor.class.getName());
        props.put("session.timeout.ms", 30000);
        props.put("heartbeat.interval.ms", 3000);
        props.put("max.poll.interval.ms", 300000);
        props.put("enable.auto.commit", "false");
        
        this.consumer = new KafkaConsumer<>(props);
    }
    
    public void startConsuming() {
        consumer.subscribe(Arrays.asList("orders", "payments"), new ConsumerRebalanceListener() {
            
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
                System.out.printf("[%s] Partitions revoked: %s%n", consumerId, partitions);
                
                // Commit offsets before losing partitions
                try {
                    consumer.commitSync();
                    System.out.printf("[%s] Offsets committed before rebalancing%n", consumerId);
                } catch (Exception e) {
                    System.err.printf("[%s] Failed to commit offsets: %s%n", consumerId, e);
                }
                
                // Cleanup resources for revoked partitions
                cleanupPartitions(partitions);
            }
            
            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
                System.out.printf("[%s] Partitions assigned: %s%n", consumerId, partitions);
                
                // Initialize resources for new partitions
                initializePartitions(partitions);
                
                // Optionally seek to specific offset
                // consumer.seekToBeginning(partitions);
                // consumer.seekToEnd(partitions);
            }
        });
        
        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
                
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("[%s] Processing: Topic=%s, Partition=%d, Offset=%d, Key=%s, Value=%s%n",
                        consumerId, record.topic(), record.partition(), record.offset(), 
                        record.key(), record.value());
                    
                    try {
                        // Process message
                        processMessage(record);
                        
                        // Commit offset after successful processing
                        consumer.commitSync(Collections.singletonMap(
                            new TopicPartition(record.topic(), record.partition()),
                            new OffsetAndMetadata(record.offset() + 1)
                        ));
                        
                    } catch (Exception e) {
                        System.err.printf("[%s] Error processing message: %s%n", consumerId, e);
                        // Handle error appropriately
                    }
                }
            }
        } finally {
            consumer.close();
        }
    }
    
    private void processMessage(ConsumerRecord<String, String> record) {
        // Simulate processing time
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    private void cleanupPartitions(Collection<TopicPartition> partitions) {
        // Cleanup any resources associated with revoked partitions
        System.out.printf("[%s] Cleaning up resources for partitions: %s%n", consumerId, partitions);
    }
    
    private void initializePartitions(Collection<TopicPartition> partitions) {
        // Initialize resources for newly assigned partitions
        System.out.printf("[%s] Initializing resources for partitions: %s%n", consumerId, partitions);
    }
}

// Multiple Consumers Example
public class ConsumerGroupExample {
    public static void main(String[] args) {
        // Start multiple consumers in the same group
        Thread consumer1 = new Thread(() -> {
            RebalancingConsumer consumer = new RebalancingConsumer("consumer-1");
            consumer.startConsuming();
        });
        
        Thread consumer2 = new Thread(() -> {
            RebalancingConsumer consumer = new RebalancingConsumer("consumer-2");
            consumer.startConsuming();
        });
        
        Thread consumer3 = new Thread(() -> {
            RebalancingConsumer consumer = new RebalancingConsumer("consumer-3");
            consumer.startConsuming();
        });
        
        consumer1.start();
        consumer2.start();
        consumer3.start();
        
        // Simulate consumer failure after 30 seconds
        try {
            Thread.sleep(30000);
            consumer1.interrupt();
            System.out.println("Consumer 1 failed - rebalancing should occur");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
```

**🐞 Fixes:** Implement proper rebalance listeners to handle partition assignment/revocation, commit offsets before losing partitions, configure appropriate session and heartbeat timeouts, and handle rebalancing gracefully to prevent data loss.

---

---

## 3. Advanced Kafka Concepts and Enterprise Features

### Advanced Questions

1. What is Kafka's Exactly-Once Semantics (EOS) and how does it work? _(Asked in Goldman Sachs)_

**🧩 Foundation:** Exactly-Once Semantics (EOS) in Kafka ensures that records are neither lost nor duplicated during processing, providing the highest level of data integrity guarantees. This is achieved through idempotent producers, transactional APIs, and exactly-once processing semantics.

**⚙️ Function:** EOS is critical for financial applications, payment processing, and any system where data consistency is paramount. It prevents the common issues of message duplication and data loss that can occur in distributed systems.

**🚀 Features:**
- **Idempotent Producers:** Prevent duplicate message production
- **Transactional APIs:** Enable atomic writes across multiple partitions
- **Exactly-Once Processing:** Guarantee each message is processed exactly once
- **Transactional Consumer:** Read committed messages only
- **Cross-Partition Atomicity:** Ensure all-or-nothing semantics

**🔁 Flow:**
```java
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import java.util.Properties;
import java.util.Collections;
import java.time.Duration;

// Exactly-Once Producer Configuration
public class ExactlyOnceProducer {
    
    private final KafkaProducer<String, String> producer;
    
    public ExactlyOnceProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        
        // Exactly-Once Configuration
        props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.RETRIES_CONFIG, Integer.MAX_VALUE);
        props.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, 5);
        props.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "txn-producer-1");
        
        this.producer = new KafkaProducer<>(props);
        this.producer.initTransactions();
    }
    
    public void sendTransactionalMessages(String transactionId) {
        try {
            producer.beginTransaction();
            
            // Send multiple messages atomically
            ProducerRecord<String, String> record1 = new ProducerRecord<>(
                "orders", "order-123", "{\"amount\":100.0,\"status\":\"pending\"}");
            ProducerRecord<String, String> record2 = new ProducerRecord<>(
                "inventory", "item-456", "{\"quantity\":1,\"action\":\"reserve\"}");
            ProducerRecord<String, String> record3 = new ProducerRecord<>(
                "payments", "payment-789", "{\"amount\":100.0,\"method\":\"credit_card\"}");
            
            producer.send(record1, (metadata, exception) -> {
                if (exception != null) {
                    System.err.println("Failed to send order record: " + exception);
                } else {
                    System.out.println("Order record sent: " + metadata);
                }
            });
            
            producer.send(record2);
            producer.send(record3);
            
            // Commit transaction - all messages are sent atomically
            producer.commitTransaction();
            System.out.println("Transaction committed successfully");
            
        } catch (Exception e) {
            System.err.println("Transaction failed, aborting: " + e);
            producer.abortTransaction();
            throw new RuntimeException("Transaction failed", e);
        }
    }
    
    public void close() {
        producer.close();
    }
}

// Exactly-Once Consumer Configuration
public class ExactlyOnceConsumer {
    
    private final KafkaConsumer<String, String> consumer;
    
    public ExactlyOnceConsumer() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "exactly-once-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        
        // Exactly-Once Consumer Configuration
        props.put(ConsumerConfig.ISOLATION_LEVEL_CONFIG, "read_committed");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        
        this.consumer = new KafkaConsumer<>(props);
    }
    
    public void consumeTransactionalMessages() {
        consumer.subscribe(Collections.singletonList("orders"));
        
        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
                
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("Received transactional message: Topic=%s, Partition=%d, Offset=%d, Key=%s, Value=%s%n",
                        record.topic(), record.partition(), record.offset(), record.key(), record.value());
                    
                    try {
                        // Process the message
                        processOrderMessage(record);
                        
                        // Commit offset after successful processing
                        consumer.commitSync(Collections.singletonMap(
                            new TopicPartition(record.topic(), record.partition()),
                            new OffsetAndMetadata(record.offset() + 1)
                        ));
                        
                    } catch (Exception e) {
                        System.err.println("Error processing message: " + e);
                        // Handle error appropriately
                    }
                }
            }
        } finally {
            consumer.close();
        }
    }
    
    private void processOrderMessage(ConsumerRecord<String, String> record) {
        // Business logic for processing order
        System.out.println("Processing order: " + record.value());
        
        // Simulate processing
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

// Usage Example
public class ExactlyOnceExample {
    public static void main(String[] args) {
        ExactlyOnceProducer producer = new ExactlyOnceProducer();
        ExactlyOnceConsumer consumer = new ExactlyOnceConsumer();
        
        try {
            // Send transactional messages
            producer.sendTransactionalMessages("txn-12345");
            
            // Consume messages with exactly-once semantics
            consumer.consumeTransactionalMessages();
            
        } finally {
            producer.close();
        }
    }
}
```

**🐞 Fixes:** Always use transactional IDs for exactly-once producers, enable idempotence, use read_committed isolation level for consumers, and handle transaction failures appropriately with proper rollback.

---

2. How does Kafka handle message compression and when should you use it? _(Asked in Amazon)_

**🧩 Foundation:** Kafka supports message compression using various algorithms (GZIP, Snappy, LZ4, Zstd) to reduce network bandwidth, storage requirements, and improve throughput. Compression is applied at the batch level for better efficiency.

**⚙️ Function:** Compression reduces the size of message batches, leading to better network utilization, reduced storage costs, and improved overall system performance, especially for high-throughput scenarios.

**🚀 Features:**
- **Multiple Algorithms:** GZIP, Snappy, LZ4, Zstd support
- **Batch-Level Compression:** More efficient than message-level
- **Producer-Side Compression:** Reduces network overhead
- **Broker-Side Decompression:** For consumer compatibility
- **Configurable Compression:** Per-producer or per-topic settings

**🔁 Flow:**
```java
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.clients.consumer.*;
import java.util.Properties;
import java.util.Arrays;
import java.time.Duration;

// Compression Comparison and Configuration
public class CompressionExample {
    
    // GZIP Compression (Best compression ratio, higher CPU usage)
    public static KafkaProducer<String, String> createGzipProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "gzip");
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        props.put(ProducerConfig.LINGER_MS_CONFIG, 5);
        
        return new KafkaProducer<>(props);
    }
    
    // Snappy Compression (Good balance of compression and speed)
    public static KafkaProducer<String, String> createSnappyProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "snappy");
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        props.put(ProducerConfig.LINGER_MS_CONFIG, 5);
        
        return new KafkaProducer<>(props);
    }
    
    // LZ4 Compression (Fast compression, good for real-time)
    public static KafkaProducer<String, String> createLZ4Producer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "lz4");
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        props.put(ProducerConfig.LINGER_MS_CONFIG, 5);
        
        return new KafkaProducer<>(props);
    }
    
    // Zstd Compression (Best compression with good speed)
    public static KafkaProducer<String, String> createZstdProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "zstd");
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        props.put(ProducerConfig.LINGER_MS_CONFIG, 5);
        
        return new KafkaProducer<>(props);
    }
    
    // Performance Testing for Different Compression Types
    public static void performanceTest() {
        String topic = "compression-test";
        int messageCount = 10000;
        String largeMessage = generateLargeMessage();
        
        // Test different compression types
        CompressionType[] compressionTypes = {
            CompressionType.NONE, CompressionType.GZIP, 
            CompressionType.SNAPPY, CompressionType.LZ4, CompressionType.ZSTD
        };
        
        for (CompressionType compressionType : compressionTypes) {
            System.out.println("\n=== Testing " + compressionType + " Compression ===");
            
            Properties props = new Properties();
            props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
            props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
            props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
            props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, compressionType.name);
            props.put(ProducerConfig.BATCH_SIZE_CONFIG, 32768);
            props.put(ProducerConfig.LINGER_MS_CONFIG, 10);
            
            try (KafkaProducer<String, String> producer = new KafkaProducer<>(props)) {
                long startTime = System.currentTimeMillis();
                
                for (int i = 0; i < messageCount; i++) {
                    ProducerRecord<String, String> record = new ProducerRecord<>(
                        topic, "key-" + i, largeMessage);
                    producer.send(record);
                }
                
                producer.flush();
                long endTime = System.currentTimeMillis();
                
                System.out.printf("Compression: %s, Messages: %d, Time: %d ms, Throughput: %.2f msg/sec%n",
                    compressionType, messageCount, (endTime - startTime), 
                    (messageCount * 1000.0) / (endTime - startTime));
            }
        }
    }
    
    // Adaptive Compression Based on Message Size
    public static class AdaptiveCompressionProducer {
        
        private final KafkaProducer<String, String> uncompressedProducer;
        private final KafkaProducer<String, String> compressedProducer;
        
        public AdaptiveCompressionProducer() {
            // Uncompressed producer for small messages
            Properties uncompressedProps = new Properties();
            uncompressedProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
            uncompressedProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
            uncompressedProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
            uncompressedProps.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "none");
            
            // Compressed producer for large messages
            Properties compressedProps = new Properties();
            compressedProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
            compressedProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
            compressedProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
            compressedProps.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "snappy");
            
            this.uncompressedProducer = new KafkaProducer<>(uncompressedProps);
            this.compressedProducer = new KafkaProducer<>(compressedProps);
        }
        
        public void sendMessage(String topic, String key, String value) {
            KafkaProducer<String, String> producer;
            
            // Choose compression based on message size
            if (value.length() > 1024) {
                producer = compressedProducer;
                System.out.println("Using compressed producer for large message");
            } else {
                producer = uncompressedProducer;
                System.out.println("Using uncompressed producer for small message");
            }
            
            ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);
            producer.send(record);
        }
        
        public void close() {
            uncompressedProducer.close();
            compressedProducer.close();
        }
    }
    
    private static String generateLargeMessage() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append("This is a large message for compression testing. ");
        }
        return sb.toString();
    }
}

// Consumer that handles compressed messages transparently
public class CompressionConsumer {
    
    public static void consumeCompressedMessages() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "compression-consumer-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        
        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)) {
            consumer.subscribe(Arrays.asList("compression-test"));
            
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
                
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("Received compressed message: Key=%s, Value length=%d%n",
                        record.key(), record.value().length());
                    
                    // Consumer automatically handles decompression
                    // No special configuration needed
                }
            }
        }
    }
}
```

**🐞 Fixes:** Choose compression algorithm based on use case (GZIP for storage, Snappy for real-time, LZ4 for low latency), monitor CPU usage with compression, and consider message size thresholds for adaptive compression.

---


---

## 4. Practical Architecture and Production Scenarios

### Scenario-Based Questions

1. How would you design a Kafka architecture for a payment processing system? _(Asked in JP Morgan Chase)_

**🧩 Foundation:** A payment processing system requires the highest level of reliability, security, and consistency. Kafka provides the perfect foundation with its exactly-once semantics, durability guarantees, and event-driven architecture that enables real-time processing with audit trails.

**⚙️ Function:** The architecture must handle high-volume transactions, ensure zero data loss, provide real-time fraud detection, maintain audit trails, and support multiple downstream systems while maintaining strict ordering and consistency.

**🚀 Features:**
- **High Availability:** Multi-broker cluster with replication factor 3
- **Exactly-Once Semantics:** Transactional producers and consumers
- **Security:** SSL/TLS encryption, SASL authentication, ACLs
- **Schema Management:** AVRO schemas with Schema Registry
- **Monitoring:** Comprehensive metrics and alerting
- **Audit Trail:** Immutable event log for compliance

**🔁 Flow:**
```java
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.security.auth.SecurityProtocol;
import java.util.Properties;
import java.util.Collections;
import java.time.Duration;

// Payment Processing System Architecture
public class PaymentProcessingSystem {
    
    // High-Security Producer Configuration
    public static KafkaProducer<String, PaymentEvent> createSecureProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka-cluster:9093");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, PaymentEventSerializer.class.getName());
        
        // Security Configuration
        props.put("security.protocol", SecurityProtocol.SASL_SSL.name);
        props.put(SaslConfigs.SASL_MECHANISM, "PLAIN");
        props.put(SaslConfigs.SASL_JAAS_CONFIG, "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"payment-service\" password=\"secure-password\";");
        
        // Exactly-Once Configuration
        props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.RETRIES_CONFIG, Integer.MAX_VALUE);
        props.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, 5);
        props.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "payment-txn-producer");
        
        // Performance Configuration
        props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "snappy");
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 32768);
        props.put(ProducerConfig.LINGER_MS_CONFIG, 5);
        
        KafkaProducer<String, PaymentEvent> producer = new KafkaProducer<>(props);
        producer.initTransactions();
        return producer;
    }
    
    // Payment Event Processing
    public static class PaymentProcessor {
        
        private final KafkaProducer<String, PaymentEvent> producer;
        private final PaymentService paymentService;
        private final FraudDetectionService fraudService;
        
        public PaymentProcessor() {
            this.producer = createSecureProducer();
            this.paymentService = new PaymentService();
            this.fraudService = new FraudDetectionService();
        }
        
        public void processPayment(PaymentRequest request) {
            String transactionId = generateTransactionId();
            
            try {
                producer.beginTransaction();
                
                // 1. Create payment event
                PaymentEvent paymentEvent = new PaymentEvent(
                    transactionId,
                    request.getAmount(),
                    request.getCurrency(),
                    request.getPaymentMethod(),
                    PaymentStatus.PENDING,
                    System.currentTimeMillis()
                );
                
                // 2. Send to payments topic
                ProducerRecord<String, PaymentEvent> paymentRecord = new ProducerRecord<>(
                    "payments", transactionId, paymentEvent);
                producer.send(paymentRecord);
                
                // 3. Send to fraud detection topic
                FraudCheckEvent fraudEvent = new FraudCheckEvent(
                    transactionId, request.getUserId(), request.getAmount(), request.getIpAddress());
                ProducerRecord<String, FraudCheckEvent> fraudRecord = new ProducerRecord<>(
                    "fraud-checks", transactionId, fraudEvent);
                producer.send(fraudRecord);
                
                // 4. Send to audit topic
                AuditEvent auditEvent = new AuditEvent(
                    transactionId, "PAYMENT_INITIATED", request.getUserId(), System.currentTimeMillis());
                ProducerRecord<String, AuditEvent> auditRecord = new ProducerRecord<>(
                    "audit-events", transactionId, auditEvent);
                producer.send(auditRecord);
                
                // Commit transaction
                producer.commitTransaction();
                
                System.out.println("Payment transaction initiated: " + transactionId);
                
            } catch (Exception e) {
                producer.abortTransaction();
                System.err.println("Payment processing failed: " + e);
                throw new PaymentProcessingException("Failed to process payment", e);
            }
        }
        
        private String generateTransactionId() {
            return "txn-" + System.currentTimeMillis() + "-" + Thread.currentThread().getId();
        }
    }
    
    // Fraud Detection Consumer
    public static class FraudDetectionConsumer {
        
        private final KafkaConsumer<String, FraudCheckEvent> consumer;
        private final FraudDetectionService fraudService;
        
        public FraudDetectionConsumer() {
            Properties props = new Properties();
            props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka-cluster:9093");
            props.put(ConsumerConfig.GROUP_ID_CONFIG, "fraud-detection-group");
            props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
            props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, FraudCheckEventDeserializer.class.getName());
            
            // Security Configuration
            props.put("security.protocol", SecurityProtocol.SASL_SSL.name);
            props.put(SaslConfigs.SASL_MECHANISM, "PLAIN");
            props.put(SaslConfigs.SASL_JAAS_CONFIG, "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"fraud-service\" password=\"secure-password\";");
            
            // Exactly-Once Consumer Configuration
            props.put(ConsumerConfig.ISOLATION_LEVEL_CONFIG, "read_committed");
            props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
            props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
            
            this.consumer = new KafkaConsumer<>(props);
            this.fraudService = new FraudDetectionService();
        }
        
        public void startFraudDetection() {
            consumer.subscribe(Collections.singletonList("fraud-checks"));
            
            try {
                while (true) {
                    ConsumerRecords<String, FraudCheckEvent> records = consumer.poll(Duration.ofMillis(1000));
                    
                    for (ConsumerRecord<String, FraudCheckEvent> record : records) {
                        try {
                            FraudCheckEvent fraudEvent = record.value();
                            System.out.println("Processing fraud check for transaction: " + fraudEvent.getTransactionId());
                            
                            // Perform fraud detection
                            FraudResult result = fraudService.checkFraud(fraudEvent);
                            
                            // Send result to fraud-results topic
                            sendFraudResult(fraudEvent.getTransactionId(), result);
                            
                            // Commit offset after successful processing
                            consumer.commitSync(Collections.singletonMap(
                                new TopicPartition(record.topic(), record.partition()),
                                new OffsetAndMetadata(record.offset() + 1)
                            ));
                            
                        } catch (Exception e) {
                            System.err.println("Error processing fraud check: " + e);
                            // Send to dead letter queue
                            sendToDeadLetterQueue(record, e);
                        }
                    }
                }
            } finally {
                consumer.close();
            }
        }
        
        private void sendFraudResult(String transactionId, FraudResult result) {
            // Implementation for sending fraud results
            System.out.println("Fraud check result for " + transactionId + ": " + result.getRiskLevel());
        }
        
        private void sendToDeadLetterQueue(ConsumerRecord<String, FraudCheckEvent> record, Exception e) {
            // Implementation for dead letter queue
            System.err.println("Sending to DLQ: " + record.key());
        }
    }
    
    // Topic Configuration
    public static void createPaymentTopics() {
        Properties adminProps = new Properties();
        adminProps.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka-cluster:9093");
        adminProps.put("security.protocol", SecurityProtocol.SASL_SSL.name);
        adminProps.put(SaslConfigs.SASL_MECHANISM, "PLAIN");
        adminProps.put(SaslConfigs.SASL_JAAS_CONFIG, "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"admin\" password=\"admin-password\";");
        
        try (AdminClient admin = AdminClient.create(adminProps)) {
            // Create topics with high replication and partitions
            NewTopic paymentsTopic = new NewTopic("payments", 12, (short) 3);
            NewTopic fraudChecksTopic = new NewTopic("fraud-checks", 6, (short) 3);
            NewTopic auditEventsTopic = new NewTopic("audit-events", 3, (short) 3);
            NewTopic fraudResultsTopic = new NewTopic("fraud-results", 6, (short) 3);
            NewTopic deadLetterTopic = new NewTopic("dead-letter-queue", 3, (short) 3);
            
            CreateTopicsResult result = admin.createTopics(Arrays.asList(
                paymentsTopic, fraudChecksTopic, auditEventsTopic, fraudResultsTopic, deadLetterTopic));
            
            result.all().get();
            System.out.println("Payment processing topics created successfully");
        } catch (Exception e) {
            System.err.println("Failed to create topics: " + e);
        }
    }
}

// Event Classes
class PaymentEvent {
    private String transactionId;
    private double amount;
    private String currency;
    private String paymentMethod;
    private PaymentStatus status;
    private long timestamp;
    
    // Constructors, getters, setters
}

class FraudCheckEvent {
    private String transactionId;
    private String userId;
    private double amount;
    private String ipAddress;
    
    // Constructors, getters, setters
}

class AuditEvent {
    private String transactionId;
    private String action;
    private String userId;
    private long timestamp;
    
    // Constructors, getters, setters
}

enum PaymentStatus {
    PENDING, PROCESSING, COMPLETED, FAILED, CANCELLED
}
```

**🐞 Fixes:** Implement proper error handling and dead letter queues, use appropriate partition counts based on throughput requirements, configure proper retention policies for audit compliance, and implement comprehensive monitoring and alerting.

---

2. How would you debug and resolve message dropping issues in Kafka? _(Asked in Netflix)_

**🧩 Foundation:** Message dropping in Kafka can occur due to various reasons including misconfigurations, network issues, broker failures, consumer lag, or improper offset management. A systematic debugging approach is essential to identify and resolve these issues.

**⚙️ Function:** Debugging message drops requires understanding the complete message flow from producer to consumer, analyzing logs, monitoring metrics, and implementing proper error handling and recovery mechanisms.

**🚀 Features:**
- **Comprehensive Logging:** Detailed logging at all levels
- **Metrics Monitoring:** Real-time monitoring of key metrics
- **Health Checks:** Automated health checks for all components
- **Error Tracking:** Centralized error tracking and alerting
- **Recovery Mechanisms:** Automated and manual recovery procedures

**🔁 Flow:**
```java
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.TopicPartition;
import java.util.Properties;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.time.Duration;

// Kafka Debugging and Monitoring System
public class KafkaDebuggingSystem {
    
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(4);
    private final AdminClient adminClient;
    private final MetricsCollector metricsCollector;
    
    public KafkaDebuggingSystem() {
        this.adminClient = createAdminClient();
        this.metricsCollector = new MetricsCollector();
        startMonitoring();
    }
    
    // 1. Producer Debugging
    public static class ProducerDebugger {
        
        public static void debugProducerIssues() {
            System.out.println("=== Producer Debugging ===");
            
            // Check producer configuration
            Properties props = new Properties();
            props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
            props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
            props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
            
            // Critical configurations for debugging
            props.put(ProducerConfig.ACKS_CONFIG, "all"); // Ensure durability
            props.put(ProducerConfig.RETRIES_CONFIG, 3); // Enable retries
            props.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, 100);
            props.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, 30000);
            props.put(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG, 120000);
            
            // Enable detailed logging
            props.put("log4j.logger.org.apache.kafka", "DEBUG");
            
            try (KafkaProducer<String, String> producer = new KafkaProducer<>(props)) {
                
                // Send test message with callback
                ProducerRecord<String, String> record = new ProducerRecord<>(
                    "test-topic", "debug-key", "debug-message");
                
                producer.send(record, new Callback() {
                    @Override
                    public void onCompletion(RecordMetadata metadata, Exception exception) {
                        if (exception != null) {
                            System.err.println("Producer error: " + exception);
                            logProducerError(exception);
                        } else {
                            System.out.println("Message sent successfully: " + metadata);
                        }
                    }
                });
                
                producer.flush();
                
            } catch (Exception e) {
                System.err.println("Producer creation failed: " + e);
            }
        }
        
        private static void logProducerError(Exception exception) {
            // Log to centralized logging system
            System.err.println("Producer Error Details:");
            System.err.println("Exception: " + exception.getClass().getSimpleName());
            System.err.println("Message: " + exception.getMessage());
            System.err.println("Stack Trace: " + java.util.Arrays.toString(exception.getStackTrace()));
        }
    }
    
    // 2. Consumer Debugging
    public static class ConsumerDebugger {
        
        public static void debugConsumerIssues(String topic, String groupId) {
            System.out.println("=== Consumer Debugging ===");
            
            Properties props = new Properties();
            props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
            props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
            props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
            props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
            
            // Debug configurations
            props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
            props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
            props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 30000);
            props.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, 3000);
            props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 500);
            
            try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)) {
                
                // Subscribe to topic
                consumer.subscribe(Collections.singletonList(topic));
                
                // Check consumer group status
                checkConsumerGroupStatus(consumer, groupId);
                
                // Monitor message consumption
                int messageCount = 0;
                long startTime = System.currentTimeMillis();
                
                while (System.currentTimeMillis() - startTime < 30000) { // 30 seconds
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
                    
                    if (records.isEmpty()) {
                        System.out.println("No messages received in this poll");
                        continue;
                    }
                    
                    for (ConsumerRecord<String, String> record : records) {
                        messageCount++;
                        System.out.printf("Received message %d: Topic=%s, Partition=%d, Offset=%d, Key=%s%n",
                            messageCount, record.topic(), record.partition(), record.offset(), record.key());
                        
                        try {
                            // Process message
                            processMessage(record);
                            
                            // Commit offset
                            consumer.commitSync();
                            
                        } catch (Exception e) {
                            System.err.println("Error processing message: " + e);
                            // Don't commit offset for failed messages
                        }
                    }
                }
                
                System.out.println("Total messages processed: " + messageCount);
                
            } catch (Exception e) {
                System.err.println("Consumer error: " + e);
            }
        }
        
        private static void checkConsumerGroupStatus(KafkaConsumer<String, String> consumer, String groupId) {
            try {
                // Get consumer group information
                Map<TopicPartition, OffsetAndMetadata> committedOffsets = consumer.committed(
                    consumer.assignment());
                
                System.out.println("Committed offsets: " + committedOffsets);
                
                // Get end offsets
                Map<TopicPartition, Long> endOffsets = consumer.endOffsets(consumer.assignment());
                System.out.println("End offsets: " + endOffsets);
                
                // Calculate lag
                for (Map.Entry<TopicPartition, Long> entry : endOffsets.entrySet()) {
                    TopicPartition partition = entry.getKey();
                    Long endOffset = entry.getValue();
                    OffsetAndMetadata committed = committedOffsets.get(partition);
                    
                    if (committed != null) {
                        long lag = endOffset - committed.offset();
                        System.out.printf("Lag for partition %s: %d%n", partition, lag);
                    }
                }
                
            } catch (Exception e) {
                System.err.println("Error checking consumer group status: " + e);
            }
        }
        
        private static void processMessage(ConsumerRecord<String, String> record) {
            // Simulate message processing
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    // 3. Cluster Health Monitoring
    public static class ClusterHealthMonitor {
        
        private final AdminClient adminClient;
        
        public ClusterHealthMonitor() {
            this.adminClient = createAdminClient();
        }
        
        public void monitorClusterHealth() {
            scheduler.scheduleAtFixedRate(() -> {
                try {
                    // Check broker health
                    checkBrokerHealth();
                    
                    // Check topic health
                    checkTopicHealth();
                    
                    // Check consumer groups
                    checkConsumerGroups();
                    
                } catch (Exception e) {
                    System.err.println("Health check failed: " + e);
                }
            }, 0, 30, TimeUnit.SECONDS);
        }
        
        private void checkBrokerHealth() {
            try {
                DescribeClusterResult clusterResult = adminClient.describeCluster();
                Cluster cluster = clusterResult.cluster().get();
                
                System.out.println("Cluster ID: " + cluster.clusterId());
                System.out.println("Controller: " + cluster.controller());
                System.out.println("Brokers: " + cluster.nodes().size());
                
                // Check if all brokers are healthy
                for (Node node : cluster.nodes()) {
                    System.out.println("Broker " + node.id() + ": " + node.host() + ":" + node.port());
                }
                
            } catch (Exception e) {
                System.err.println("Broker health check failed: " + e);
            }
        }
        
        private void checkTopicHealth() {
            try {
                // List all topics
                ListTopicsResult topicsResult = adminClient.listTopics();
                Set<String> topicNames = topicsResult.names().get();
                
                // Check each topic
                for (String topicName : topicNames) {
                    DescribeTopicsResult describeResult = adminClient.describeTopics(
                        Collections.singletonList(topicName));
                    TopicDescription description = describeResult.all().get().get(topicName);
                    
                    System.out.println("Topic: " + topicName);
                    System.out.println("  Partitions: " + description.partitions().size());
                    
                    // Check partition health
                    for (TopicPartitionInfo partitionInfo : description.partitions()) {
                        System.out.printf("  Partition %d: Leader=%d, Replicas=%s%n",
                            partitionInfo.partition(),
                            partitionInfo.leader().id(),
                            partitionInfo.replicas().stream()
                                .map(Node::id)
                                .collect(Collectors.toList()));
                        
                        // Check if leader is available
                        if (partitionInfo.leader() == null) {
                            System.err.println("    WARNING: No leader for partition " + partitionInfo.partition());
                        }
                    }
                }
                
            } catch (Exception e) {
                System.err.println("Topic health check failed: " + e);
            }
        }
        
        private void checkConsumerGroups() {
            try {
                ListConsumerGroupsResult groupsResult = adminClient.listConsumerGroups();
                Collection<ConsumerGroupListing> groups = groupsResult.all().get();
                
                for (ConsumerGroupListing group : groups) {
                    String groupId = group.groupId();
                    System.out.println("Consumer Group: " + groupId);
                    
                    // Get detailed group information
                    DescribeConsumerGroupsResult describeResult = adminClient.describeConsumerGroups(
                        Collections.singletonList(groupId));
                    ConsumerGroupDescription description = describeResult.all().get().get(groupId);
                    
                    System.out.println("  State: " + description.state());
                    System.out.println("  Members: " + description.members().size());
                    
                    // Check for lag
                    for (MemberDescription member : description.members()) {
                        System.out.println("  Member: " + member.consumerId());
                        System.out.println("    Assigned partitions: " + member.assignment().topicPartitions());
                    }
                }
                
            } catch (Exception e) {
                System.err.println("Consumer group check failed: " + e);
            }
        }
    }
    
    // 4. Message Tracing
    public static class MessageTracer {
        
        public static void traceMessage(String topic, String key, String value) {
            String traceId = generateTraceId();
            
            System.out.println("=== Message Trace ===");
            System.out.println("Trace ID: " + traceId);
            System.out.println("Topic: " + topic);
            System.out.println("Key: " + key);
            System.out.println("Value: " + value);
            System.out.println("Timestamp: " + System.currentTimeMillis());
            
            // Add trace headers to message
            ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);
            record.headers().add("trace-id", traceId.getBytes());
            record.headers().add("producer-timestamp", String.valueOf(System.currentTimeMillis()).getBytes());
            
            // Send with tracing
            try (KafkaProducer<String, String> producer = createTracingProducer()) {
                producer.send(record, (metadata, exception) -> {
                    if (exception != null) {
                        System.err.println("Trace " + traceId + " failed: " + exception);
                    } else {
                        System.out.println("Trace " + traceId + " sent to partition " + metadata.partition() + 
                            " offset " + metadata.offset());
                    }
                });
            }
        }
        
        private static String generateTraceId() {
            return "trace-" + System.currentTimeMillis() + "-" + Thread.currentThread().getId();
        }
        
        private static KafkaProducer<String, String> createTracingProducer() {
            Properties props = new Properties();
            props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
            props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
            props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
            
            return new KafkaProducer<>(props);
        }
    }
    
    private static AdminClient createAdminClient() {
        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, 30000);
        
        return AdminClient.create(props);
    }
    
    private void startMonitoring() {
        new ClusterHealthMonitor().monitorClusterHealth();
    }
}
```

**🐞 Fixes:** Implement comprehensive logging and monitoring, use proper error handling and retry mechanisms, monitor consumer lag and broker health, and implement message tracing for debugging complex issues.

---

---

3. How would you implement a high-throughput real-time analytics system using Kafka? _(Asked in Uber)_

**🧩 Foundation:** Building a high-throughput real-time analytics system requires careful consideration of Kafka's partitioning strategy, consumer group optimization, data serialization, and integration with stream processing frameworks to handle millions of events per second while maintaining low latency.

**⚙️ Function:** The system must ingest high-volume data streams, perform real-time aggregations and transformations, provide low-latency querying capabilities, and scale horizontally to handle increasing data volumes while maintaining system reliability.

**🚀 Features:**
- **High Throughput:** Millions of events per second processing
- **Low Latency:** Sub-second processing and query response times
- **Scalability:** Horizontal scaling capabilities
- **Real-time Aggregations:** Windowed aggregations and analytics
- **Data Partitioning:** Optimal partitioning for parallel processing
- **Integration:** Kafka Streams, KSQL, or Apache Flink integration

**🔁 Flow:**
```java
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.common.serialization.*;
import java.time.Duration;
import java.util.Properties;

// High-Throughput Real-time Analytics System
public class RealTimeAnalyticsSystem {
    
    // Kafka Streams Application for Real-time Processing
    public static class AnalyticsStreamProcessor {
        
        public static void createAnalyticsTopology() {
            Properties props = new Properties();
            props.put(StreamsConfig.APPLICATION_ID_CONFIG, "real-time-analytics-app");
            props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
            props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
            props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
            
            // Performance tuning for high throughput
            props.put(StreamsConfig.NUM_STREAM_THREADS_CONFIG, 8);
            props.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 1000);
            props.put(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, 67108864); // 64MB
            props.put(StreamsConfig.PROCESSING_GUARANTEE_CONFIG, StreamsConfig.EXACTLY_ONCE);
            
            StreamsBuilder builder = new StreamsBuilder();
            
            // 1. User Activity Stream Processing
            KStream<String, UserActivity> userActivityStream = builder
                .stream("user-activities", Consumed.with(Serdes.String(), userActivitySerde()))
                .filter((key, activity) -> activity != null && activity.getUserId() != null);
            
            // 2. Real-time User Session Analytics
            userActivityStream
                .groupByKey(Grouped.with(Serdes.String(), userActivitySerde()))
                .windowedBy(TimeWindows.of(Duration.ofMinutes(5)).grace(Duration.ofSeconds(30)))
                .aggregate(
                    UserSessionStats::new,
                    (key, activity, stats) -> stats.update(activity),
                    Materialized.<String, UserSessionStats, WindowStore<Bytes, byte[]>>as("user-session-stats")
                        .withKeySerde(Serdes.String())
                        .withValueSerde(userSessionStatsSerde())
                )
                .toStream()
                .to("user-session-analytics", Produced.with(WindowedSerdes.timeWindowedSerdeFrom(String.class), userSessionStatsSerde()));
            
            // 3. Real-time Revenue Analytics
            KStream<String, Transaction> transactionStream = builder
                .stream("transactions", Consumed.with(Serdes.String(), transactionSerde()));
            
            transactionStream
                .filter((key, transaction) -> transaction.getAmount() > 0)
                .groupBy((key, transaction) -> "global", Grouped.with(Serdes.String(), transactionSerde()))
                .windowedBy(TimeWindows.of(Duration.ofMinutes(1)).grace(Duration.ofSeconds(10)))
                .aggregate(
                    RevenueStats::new,
                    (key, transaction, stats) -> stats.addTransaction(transaction),
                    Materialized.<String, RevenueStats, WindowStore<Bytes, byte[]>>as("revenue-stats")
                        .withKeySerde(Serdes.String())
                        .withValueSerde(revenueStatsSerde())
                )
                .toStream()
                .to("revenue-analytics", Produced.with(WindowedSerdes.timeWindowedSerdeFrom(String.class), revenueStatsSerde()));
            
            // 4. Real-time Top Products Analytics
            transactionStream
                .flatMapValues(Transaction::getProducts)
                .groupBy((key, product) -> product.getCategory(), Grouped.with(Serdes.String(), productSerde()))
                .windowedBy(TimeWindows.of(Duration.ofMinutes(10)).grace(Duration.ofSeconds(30)))
                .count(Materialized.<String, Long, WindowStore<Bytes, byte[]>>as("product-counts")
                    .withKeySerde(Serdes.String())
                    .withValueSerde(Serdes.Long()))
                .toStream()
                .to("top-products", Produced.with(WindowedSerdes.timeWindowedSerdeFrom(String.class), Serdes.Long()));
            
            // 5. Real-time Alert System
            transactionStream
                .filter((key, transaction) -> transaction.getAmount() > 10000) // High-value transactions
                .mapValues(transaction -> new AlertEvent("HIGH_VALUE_TRANSACTION", transaction))
                .to("alerts", Produced.with(Serdes.String(), alertEventSerde()));
            
            KafkaStreams streams = new KafkaStreams(builder.build(), props);
            streams.start();
            
            // Graceful shutdown
            Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
        }
    }
    
    // High-Performance Producer for Analytics Data
    public static class AnalyticsDataProducer {
        
        private final KafkaProducer<String, UserActivity> producer;
        private final Random random = new Random();
        
        public AnalyticsDataProducer() {
            Properties props = new Properties();
            props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
            props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
            props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, UserActivitySerializer.class.getName());
            
            // High throughput configuration
            props.put(ProducerConfig.BATCH_SIZE_CONFIG, 65536); // 64KB
            props.put(ProducerConfig.LINGER_MS_CONFIG, 5);
            props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "snappy");
            props.put(ProducerConfig.ACKS_CONFIG, "1"); // Faster for analytics
            props.put(ProducerConfig.RETRIES_CONFIG, 3);
            props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 134217728); // 128MB
            
            this.producer = new KafkaProducer<>(props);
        }
        
        public void generateHighVolumeData(int messageCount) {
            ExecutorService executor = Executors.newFixedThreadPool(10);
            
            for (int i = 0; i < messageCount; i++) {
                executor.submit(() -> {
                    UserActivity activity = generateRandomUserActivity();
                    String key = activity.getUserId();
                    
                    ProducerRecord<String, UserActivity> record = new ProducerRecord<>(
                        "user-activities", key, activity);
                    
                    producer.send(record, (metadata, exception) -> {
                        if (exception != null) {
                            System.err.println("Failed to send analytics data: " + exception);
                        }
                    });
                });
            }
            
            executor.shutdown();
            producer.flush();
        }
        
        private UserActivity generateRandomUserActivity() {
            return new UserActivity(
                "user-" + random.nextInt(10000),
                random.nextInt(1000),
                System.currentTimeMillis(),
                ActivityType.values()[random.nextInt(ActivityType.values().length)],
                random.nextDouble() * 100
            );
        }
    }
    
    // Real-time Query Service
    public static class RealTimeQueryService {
        
        private final KafkaConsumer<String, UserSessionStats> consumer;
        private final Map<String, UserSessionStats> latestStats = new ConcurrentHashMap<>();
        
        public RealTimeQueryService() {
            Properties props = new Properties();
            props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
            props.put(ConsumerConfig.GROUP_ID_CONFIG, "query-service-group");
            props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
            props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, UserSessionStatsDeserializer.class.getName());
            props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
            props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
            
            this.consumer = new KafkaConsumer<>(props);
        }
        
        public void startQueryService() {
            consumer.subscribe(Collections.singletonList("user-session-analytics"));
            
            new Thread(() -> {
                while (true) {
                    ConsumerRecords<String, UserSessionStats> records = consumer.poll(Duration.ofMillis(100));
                    
                    for (ConsumerRecord<String, UserSessionStats> record : records) {
                        // Update latest stats for real-time queries
                        latestStats.put(record.key(), record.value());
                    }
                }
            }).start();
        }
        
        public UserSessionStats getUserStats(String userId) {
            return latestStats.get(userId);
        }
        
        public Map<String, UserSessionStats> getAllUserStats() {
            return new HashMap<>(latestStats);
        }
    }
}

// Data Classes
class UserActivity {
    private String userId;
    private int sessionId;
    private long timestamp;
    private ActivityType activityType;
    private double value;
    
    // Constructors, getters, setters
}

class UserSessionStats {
    private String userId;
    private int totalActivities;
    private double totalValue;
    private long lastActivityTime;
    
    public UserSessionStats update(UserActivity activity) {
        this.totalActivities++;
        this.totalValue += activity.getValue();
        this.lastActivityTime = activity.getTimestamp();
        return this;
    }
}

class RevenueStats {
    private double totalRevenue;
    private int transactionCount;
    private long lastUpdateTime;
    
    public RevenueStats addTransaction(Transaction transaction) {
        this.totalRevenue += transaction.getAmount();
        this.transactionCount++;
        this.lastUpdateTime = System.currentTimeMillis();
        return this;
    }
}

class Transaction {
    private String transactionId;
    private String userId;
    private double amount;
    private List<Product> products;
    
    // Constructors, getters, setters
}

enum ActivityType {
    PAGE_VIEW, CLICK, PURCHASE, LOGIN, LOGOUT
}
```

**🐞 Fixes:** Optimize partitioning strategy for even data distribution, use appropriate serialization formats (AVRO/Protobuf), implement proper error handling and monitoring, and consider using Kafka Streams state stores for complex aggregations.

---

4. How would you handle a Kafka cluster failure and implement disaster recovery? _(Asked in Microsoft)_

**🧩 Foundation:** Kafka cluster failures can occur due to various reasons including hardware failures, network issues, or software bugs. A robust disaster recovery strategy involves proactive monitoring, automated failover mechanisms, data replication across multiple regions, and well-defined recovery procedures.

**⚙️ Function:** The disaster recovery system must minimize data loss, reduce downtime, ensure business continuity, and provide automated recovery capabilities while maintaining data consistency and system integrity.

**🚀 Features:**
- **Multi-Region Deployment:** Active-passive or active-active setups
- **Automated Failover:** Automatic detection and failover mechanisms
- **Data Replication:** Cross-region data replication
- **Monitoring and Alerting:** Proactive monitoring of cluster health
- **Recovery Procedures:** Well-defined manual and automated recovery steps
- **Data Consistency:** Ensuring data consistency across regions

**🔁 Flow:**
```java
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import java.util.*;
import java.util.concurrent.*;
import java.time.Duration;

// Kafka Disaster Recovery System
public class KafkaDisasterRecovery {
    
    // Multi-Region Cluster Manager
    public static class MultiRegionClusterManager {
        
        private final Map<String, AdminClient> regionAdminClients;
        private final Map<String, String> regionEndpoints;
        private final String primaryRegion;
        private final String secondaryRegion;
        private final ScheduledExecutorService healthCheckScheduler;
        
        public MultiRegionClusterManager() {
            this.regionEndpoints = Map.of(
                "us-east-1", "kafka-us-east-1:9092",
                "us-west-2", "kafka-us-west-2:9092",
                "eu-west-1", "kafka-eu-west-1:9092"
            );
            
            this.primaryRegion = "us-east-1";
            this.secondaryRegion = "us-west-2";
            this.regionAdminClients = new HashMap<>();
            this.healthCheckScheduler = Executors.newScheduledThreadPool(3);
            
            initializeAdminClients();
            startHealthMonitoring();
        }
        
        private void initializeAdminClients() {
            for (Map.Entry<String, String> entry : regionEndpoints.entrySet()) {
                String region = entry.getKey();
                String endpoint = entry.getValue();
                
                Properties props = new Properties();
                props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, endpoint);
                props.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, 30000);
                
                regionAdminClients.put(region, AdminClient.create(props));
            }
        }
        
        // Health Monitoring
        private void startHealthMonitoring() {
            healthCheckScheduler.scheduleAtFixedRate(() -> {
                for (String region : regionEndpoints.keySet()) {
                    checkRegionHealth(region);
                }
            }, 0, 30, TimeUnit.SECONDS);
        }
        
        private void checkRegionHealth(String region) {
            try {
                AdminClient adminClient = regionAdminClients.get(region);
                DescribeClusterResult clusterResult = adminClient.describeCluster();
                Cluster cluster = clusterResult.cluster().get(5, TimeUnit.SECONDS);
                
                System.out.println("Region " + region + " is healthy. Brokers: " + cluster.nodes().size());
                
                // Update health status
                updateRegionHealthStatus(region, true);
                
            } catch (Exception e) {
                System.err.println("Region " + region + " health check failed: " + e);
                updateRegionHealthStatus(region, false);
                
                // Trigger failover if primary region fails
                if (region.equals(primaryRegion)) {
                    triggerFailover();
                }
            }
        }
        
        private void updateRegionHealthStatus(String region, boolean isHealthy) {
            // Update health status in monitoring system
            System.out.println("Region " + region + " health status: " + (isHealthy ? "HEALTHY" : "UNHEALTHY"));
        }
        
        // Failover Mechanism
        public void triggerFailover() {
            System.out.println("=== TRIGGERING FAILOVER ===");
            
            try {
                // 1. Verify secondary region is healthy
                if (!isRegionHealthy(secondaryRegion)) {
                    System.err.println("Secondary region is not healthy. Cannot perform failover.");
                    return;
                }
                
                // 2. Stop accepting new messages in primary region
                stopAcceptingNewMessages();
                
                // 3. Wait for in-flight messages to complete
                waitForInFlightMessages();
                
                // 4. Update DNS/load balancer to point to secondary region
                updateTrafficRouting();
                
                // 5. Notify applications of region change
                notifyApplicationsOfFailover();
                
                // 6. Start monitoring primary region for recovery
                startPrimaryRecoveryMonitoring();
                
                System.out.println("Failover completed successfully to region: " + secondaryRegion);
                
            } catch (Exception e) {
                System.err.println("Failover failed: " + e);
                // Implement rollback procedures
                rollbackFailover();
            }
        }
        
        private boolean isRegionHealthy(String region) {
            try {
                AdminClient adminClient = regionAdminClients.get(region);
                DescribeClusterResult clusterResult = adminClient.describeCluster();
                clusterResult.cluster().get(5, TimeUnit.SECONDS);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        
        private void stopAcceptingNewMessages() {
            System.out.println("Stopping acceptance of new messages...");
            // Implementation to stop new message acceptance
        }
        
        private void waitForInFlightMessages() {
            System.out.println("Waiting for in-flight messages to complete...");
            try {
                Thread.sleep(30000); // Wait 30 seconds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        private void updateTrafficRouting() {
            System.out.println("Updating traffic routing to secondary region...");
            // Implementation to update DNS/load balancer configuration
        }
        
        private void notifyApplicationsOfFailover() {
            System.out.println("Notifying applications of failover...");
            // Implementation to notify applications
        }
        
        private void startPrimaryRecoveryMonitoring() {
            System.out.println("Starting primary region recovery monitoring...");
            // Implementation to monitor primary region recovery
        }
        
        private void rollbackFailover() {
            System.out.println("Rolling back failover...");
            // Implementation to rollback failover
        }
    }
    
    // Cross-Region Data Replication
    public static class CrossRegionReplicator {
        
        private final Map<String, KafkaProducer<String, String>> regionProducers;
        private final Map<String, KafkaConsumer<String, String>> regionConsumers;
        
        public CrossRegionReplicator() {
            this.regionProducers = new HashMap<>();
            this.regionConsumers = new HashMap<>();
            initializeReplication();
        }
        
        private void initializeReplication() {
            Map<String, String> regions = Map.of(
                "us-east-1", "kafka-us-east-1:9092",
                "us-west-2", "kafka-us-west-2:9092"
            );
            
            for (Map.Entry<String, String> entry : regions.entrySet()) {
                String region = entry.getKey();
                String endpoint = entry.getValue();
                
                // Create producer for each region
                Properties producerProps = new Properties();
                producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, endpoint);
                producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
                producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
                producerProps.put(ProducerConfig.ACKS_CONFIG, "all");
                producerProps.put(ProducerConfig.RETRIES_CONFIG, 3);
                
                regionProducers.put(region, new KafkaProducer<>(producerProps));
                
                // Create consumer for each region
                Properties consumerProps = new Properties();
                consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, endpoint);
                consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, "cross-region-replicator-" + region);
                consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
                consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
                consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
                consumerProps.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
                
                regionConsumers.put(region, new KafkaConsumer<>(consumerProps));
            }
        }
        
        public void startReplication(String sourceRegion, String targetRegion) {
            System.out.println("Starting replication from " + sourceRegion + " to " + targetRegion);
            
            KafkaConsumer<String, String> sourceConsumer = regionConsumers.get(sourceRegion);
            KafkaProducer<String, String> targetProducer = regionProducers.get(targetRegion);
            
            sourceConsumer.subscribe(Collections.singletonList("critical-data"));
            
            new Thread(() -> {
                try {
                    while (true) {
                        ConsumerRecords<String, String> records = sourceConsumer.poll(Duration.ofMillis(1000));
                        
                        for (ConsumerRecord<String, String> record : records) {
                            try {
                                // Replicate to target region
                                ProducerRecord<String, String> replicatedRecord = new ProducerRecord<>(
                                    "critical-data", record.key(), record.value());
                                
                                targetProducer.send(replicatedRecord, (metadata, exception) -> {
                                    if (exception != null) {
                                        System.err.println("Replication failed: " + exception);
                                    } else {
                                        System.out.println("Replicated message to " + targetRegion + 
                                            " at offset " + metadata.offset());
                                    }
                                });
                                
                                // Commit offset after successful replication
                                sourceConsumer.commitSync();
                                
                            } catch (Exception e) {
                                System.err.println("Error during replication: " + e);
                            }
                        }
                    }
                } finally {
                    sourceConsumer.close();
                    targetProducer.close();
                }
            }).start();
        }
    }
    
    // Disaster Recovery Procedures
    public static class DisasterRecoveryProcedures {
        
        public static void executeRecoveryPlan(String disasterType) {
            System.out.println("=== EXECUTING DISASTER RECOVERY PLAN ===");
            System.out.println("Disaster Type: " + disasterType);
            
            switch (disasterType.toLowerCase()) {
                case "cluster_failure":
                    handleClusterFailure();
                    break;
                case "network_partition":
                    handleNetworkPartition();
                    break;
                case "data_corruption":
                    handleDataCorruption();
                    break;
                case "complete_region_failure":
                    handleCompleteRegionFailure();
                    break;
                default:
                    System.err.println("Unknown disaster type: " + disasterType);
            }
        }
        
        private static void handleClusterFailure() {
            System.out.println("Handling cluster failure...");
            
            // 1. Assess the situation
            assessClusterHealth();
            
            // 2. Activate backup cluster
            activateBackupCluster();
            
            // 3. Redirect traffic
            redirectTrafficToBackup();
            
            // 4. Verify data consistency
            verifyDataConsistency();
            
            // 5. Notify stakeholders
            notifyStakeholders("Cluster failure handled successfully");
        }
        
        private static void handleNetworkPartition() {
            System.out.println("Handling network partition...");
            
            // 1. Identify affected partitions
            identifyAffectedPartitions();
            
            // 2. Isolate affected components
            isolateAffectedComponents();
            
            // 3. Use alternative communication paths
            useAlternativeCommunicationPaths();
            
            // 4. Monitor for resolution
            monitorForResolution();
        }
        
        private static void handleDataCorruption() {
            System.out.println("Handling data corruption...");
            
            // 1. Stop affected consumers
            stopAffectedConsumers();
            
            // 2. Restore from backup
            restoreFromBackup();
            
            // 3. Validate data integrity
            validateDataIntegrity();
            
            // 4. Resume normal operations
            resumeNormalOperations();
        }
        
        private static void handleCompleteRegionFailure() {
            System.out.println("Handling complete region failure...");
            
            // 1. Activate secondary region
            activateSecondaryRegion();
            
            // 2. Restore data from backups
            restoreDataFromBackups();
            
            // 3. Update DNS and routing
            updateDnsAndRouting();
            
            // 4. Test system functionality
            testSystemFunctionality();
        }
        
        private static void assessClusterHealth() {
            System.out.println("Assessing cluster health...");
            // Implementation for cluster health assessment
        }
        
        private static void activateBackupCluster() {
            System.out.println("Activating backup cluster...");
            // Implementation for backup cluster activation
        }
        
        private static void redirectTrafficToBackup() {
            System.out.println("Redirecting traffic to backup...");
            // Implementation for traffic redirection
        }
        
        private static void verifyDataConsistency() {
            System.out.println("Verifying data consistency...");
            // Implementation for data consistency verification
        }
        
        private static void notifyStakeholders(String message) {
            System.out.println("Notifying stakeholders: " + message);
            // Implementation for stakeholder notification
        }
        
        private static void identifyAffectedPartitions() {
            System.out.println("Identifying affected partitions...");
            // Implementation for partition identification
        }
        
        private static void isolateAffectedComponents() {
            System.out.println("Isolating affected components...");
            // Implementation for component isolation
        }
        
        private static void useAlternativeCommunicationPaths() {
            System.out.println("Using alternative communication paths...");
            // Implementation for alternative communication
        }
        
        private static void monitorForResolution() {
            System.out.println("Monitoring for resolution...");
            // Implementation for resolution monitoring
        }
        
        private static void stopAffectedConsumers() {
            System.out.println("Stopping affected consumers...");
            // Implementation for consumer stopping
        }
        
        private static void restoreFromBackup() {
            System.out.println("Restoring from backup...");
            // Implementation for backup restoration
        }
        
        private static void validateDataIntegrity() {
            System.out.println("Validating data integrity...");
            // Implementation for data integrity validation
        }
        
        private static void resumeNormalOperations() {
            System.out.println("Resuming normal operations...");
            // Implementation for operation resumption
        }
        
        private static void activateSecondaryRegion() {
            System.out.println("Activating secondary region...");
            // Implementation for secondary region activation
        }
        
        private static void restoreDataFromBackups() {
            System.out.println("Restoring data from backups...");
            // Implementation for data restoration
        }
        
        private static void updateDnsAndRouting() {
            System.out.println("Updating DNS and routing...");
            // Implementation for DNS and routing updates
        }
        
        private static void testSystemFunctionality() {
            System.out.println("Testing system functionality...");
            // Implementation for functionality testing
        }
    }
}
```

**🐞 Fixes:** Implement comprehensive monitoring and alerting systems, maintain regular backups and test recovery procedures, use cross-region replication with proper conflict resolution, and document all recovery procedures with runbooks.

---

## 5. Tough and Situational Questions

### Advanced Scenario-Based Questions

1. How would you handle a scenario where Kafka consumers are processing messages 10x slower than expected? _(Asked in Google)_

**🧩 Foundation:** Consumer lag can be caused by various factors including inefficient processing logic, resource constraints, network issues, or improper configuration. A systematic approach to diagnosing and resolving performance issues is essential.

**⚙️ Function:** The solution must identify the root cause of slow processing, implement performance optimizations, scale consumers appropriately, and ensure the system can handle the expected throughput.

**🚀 Features:**
- **Performance Profiling:** Identify bottlenecks in processing logic
- **Consumer Scaling:** Horizontal scaling of consumers
- **Configuration Tuning:** Optimize consumer and JVM configurations
- **Resource Monitoring:** Monitor CPU, memory, and network usage
- **Batch Processing:** Optimize batch sizes and processing strategies

**🔁 Flow:**
```java
// Consumer Performance Optimization
public class ConsumerPerformanceOptimizer {
    
    public static class OptimizedConsumer {
        
        private final KafkaConsumer<String, String> consumer;
        private final ExecutorService processingExecutor;
        private final BlockingQueue<ConsumerRecord<String, String>> processingQueue;
        
        public OptimizedConsumer() {
            this.processingExecutor = Executors.newFixedThreadPool(10);
            this.processingQueue = new LinkedBlockingQueue<>(1000);
            
            Properties props = new Properties();
            props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
            props.put(ConsumerConfig.GROUP_ID_CONFIG, "optimized-consumer-group");
            props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
            props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
            
            // Performance optimizations
            props.put(ConsumerConfig.FETCH_MIN_BYTES_CONFIG, 1024 * 1024); // 1MB
            props.put(ConsumerConfig.FETCH_MAX_WAIT_MS_CONFIG, 500);
            props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 1000);
            props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 30000);
            props.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, 3000);
            
            this.consumer = new KafkaConsumer<>(props);
            
            // Start processing threads
            startProcessingThreads();
        }
        
        public void startConsuming() {
            consumer.subscribe(Collections.singletonList("high-volume-topic"));
            
            new Thread(() -> {
                while (true) {
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                    
                    for (ConsumerRecord<String, String> record : records) {
                        try {
                            // Add to processing queue
                            processingQueue.offer(record, 100, TimeUnit.MILLISECONDS);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            break;
                        }
                    }
                }
            }).start();
        }
        
        private void startProcessingThreads() {
            for (int i = 0; i < 10; i++) {
                processingExecutor.submit(() -> {
                    while (true) {
                        try {
                            ConsumerRecord<String, String> record = processingQueue.take();
                            processMessageOptimized(record);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            break;
                        }
                    }
                });
            }
        }
        
        private void processMessageOptimized(ConsumerRecord<String, String> record) {
            long startTime = System.currentTimeMillis();
            
            try {
                // Optimized processing logic
                String result = performOptimizedProcessing(record.value());
                
                // Commit offset
                consumer.commitSync();
                
                long processingTime = System.currentTimeMillis() - startTime;
                if (processingTime > 100) { // Log slow processing
                    System.out.println("Slow processing detected: " + processingTime + "ms for key: " + record.key());
                }
                
            } catch (Exception e) {
                System.err.println("Error processing message: " + e);
            }
        }
        
        private String performOptimizedProcessing(String message) {
            // Optimized processing implementation
            return "processed: " + message;
        }
    }
}
```

**🐞 Fixes:** Profile the application to identify bottlenecks, implement parallel processing, optimize batch sizes, tune JVM parameters, and consider using stream processing frameworks for complex transformations.

---

2. How would you design a Kafka system to handle 100 million messages per day with 99.99% availability? _(Asked in Facebook)_

**🧩 Foundation:** Handling 100 million messages per day with 99.99% availability requires careful planning of cluster architecture, partitioning strategy, replication, monitoring, and operational procedures to ensure high throughput and fault tolerance.

**⚙️ Function:** The system must process approximately 1,157 messages per second continuously, maintain high availability through redundancy, and provide monitoring and alerting for proactive issue detection.

**🚀 Features:**
- **High Throughput Architecture:** Optimized for 100M+ messages/day
- **99.99% Availability:** Less than 52 minutes downtime per year
- **Multi-Broker Cluster:** Distributed across multiple nodes
- **Comprehensive Monitoring:** Real-time monitoring and alerting
- **Automated Recovery:** Self-healing capabilities

**🔁 Flow:**
```java
// High-Throughput High-Availability Kafka System
public class HighThroughputKafkaSystem {
    
    // Cluster Configuration
    public static class ClusterConfig {
        
        public static void configureHighAvailabilityCluster() {
            // Broker configuration for high availability
            Properties brokerProps = new Properties();
            
            // Replication and durability
            brokerProps.put("default.replication.factor", "3");
            brokerProps.put("min.insync.replicas", "2");
            brokerProps.put("unclean.leader.election.enable", "false");
            
            // Performance tuning
            brokerProps.put("num.network.threads", "8");
            brokerProps.put("num.io.threads", "16");
            brokerProps.put("socket.send.buffer.bytes", "102400");
            brokerProps.put("socket.receive.buffer.bytes", "102400");
            brokerProps.put("socket.request.max.bytes", "104857600");
            
            // Log configuration
            brokerProps.put("num.partitions", "12");
            brokerProps.put("log.segment.bytes", "1073741824"); // 1GB
            brokerProps.put("log.retention.hours", "168"); // 7 days
            brokerProps.put("log.retention.check.interval.ms", "300000");
            
            System.out.println("High availability cluster configured");
        }
    }
    
    // Producer Configuration for High Throughput
    public static class HighThroughputProducer {
        
        public static KafkaProducer<String, String> createHighThroughputProducer() {
            Properties props = new Properties();
            props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "broker1:9092,broker2:9092,broker3:9092");
            props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
            props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
            
            // High throughput configuration
            props.put(ProducerConfig.BATCH_SIZE_CONFIG, 65536); // 64KB
            props.put(ProducerConfig.LINGER_MS_CONFIG, 5);
            props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "snappy");
            props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 134217728); // 128MB
            
            // Reliability configuration
            props.put(ProducerConfig.ACKS_CONFIG, "1"); // Balance between performance and durability
            props.put(ProducerConfig.RETRIES_CONFIG, 3);
            props.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, 100);
            
            return new KafkaProducer<>(props);
        }
        
        public static void generateHighVolumeData() {
            KafkaProducer<String, String> producer = createHighThroughputProducer();
            
            // Generate 100M messages over 24 hours
            int messagesPerSecond = 1157; // 100M / 86400 seconds
            int batchSize = 100;
            
            ExecutorService executor = Executors.newFixedThreadPool(20);
            
            for (int i = 0; i < messagesPerSecond; i += batchSize) {
                executor.submit(() -> {
                    for (int j = 0; j < batchSize; j++) {
                        String key = "key-" + System.currentTimeMillis() + "-" + j;
                        String value = generateMessageValue();
                        
                        ProducerRecord<String, String> record = new ProducerRecord<>(
                            "high-volume-topic", key, value);
                        
                        producer.send(record);
                    }
                });
                
                try {
                    Thread.sleep(1000); // Send batches every second
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
            
            executor.shutdown();
            producer.close();
        }
        
        private static String generateMessageValue() {
            return "message-" + System.currentTimeMillis() + "-" + UUID.randomUUID();
        }
    }
    
    // Consumer Configuration for High Throughput
    public static class HighThroughputConsumer {
        
        public static KafkaConsumer<String, String> createHighThroughputConsumer() {
            Properties props = new Properties();
            props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "broker1:9092,broker2:9092,broker3:9092");
            props.put(ConsumerConfig.GROUP_ID_CONFIG, "high-throughput-consumer-group");
            props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
            props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
            
            // High throughput configuration
            props.put(ConsumerConfig.FETCH_MIN_BYTES_CONFIG, 1024 * 1024); // 1MB
            props.put(ConsumerConfig.FETCH_MAX_WAIT_MS_CONFIG, 500);
            props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 1000);
            props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 30000);
            props.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, 3000);
            
            return new KafkaConsumer<>(props);
        }
        
        public static void startHighThroughputConsumption() {
            KafkaConsumer<String, String> consumer = createHighThroughputConsumer();
            consumer.subscribe(Collections.singletonList("high-volume-topic"));
            
            ExecutorService processingExecutor = Executors.newFixedThreadPool(10);
            
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
                
                // Process records in parallel
                List<CompletableFuture<Void>> futures = new ArrayList<>();
                
                for (ConsumerRecord<String, String> record : records) {
                    CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                        processMessage(record);
                    }, processingExecutor);
                    
                    futures.add(future);
                }
                
                // Wait for all processing to complete
                CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
                
                // Commit offsets
                consumer.commitSync();
            }
        }
        
        private static void processMessage(ConsumerRecord<String, String> record) {
            // Fast processing logic
            String result = "processed: " + record.value();
            // Store result or perform business logic
        }
    }
    
    // Monitoring and Alerting System
    public static class MonitoringSystem {
        
        private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
        private final AdminClient adminClient;
        
        public MonitoringSystem() {
            Properties props = new Properties();
            props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "broker1:9092,broker2:9092,broker3:9092");
            this.adminClient = AdminClient.create(props);
            
            startMonitoring();
        }
        
        private void startMonitoring() {
            // Monitor cluster health every 30 seconds
            scheduler.scheduleAtFixedRate(this::monitorClusterHealth, 0, 30, TimeUnit.SECONDS);
            
            // Monitor consumer lag every 10 seconds
            scheduler.scheduleAtFixedRate(this::monitorConsumerLag, 0, 10, TimeUnit.SECONDS);
        }
        
        private void monitorClusterHealth() {
            try {
                DescribeClusterResult clusterResult = adminClient.describeCluster();
                Cluster cluster = clusterResult.cluster().get();
                
                System.out.println("Cluster Health Check:");
                System.out.println("  Brokers: " + cluster.nodes().size());
                System.out.println("  Controller: " + cluster.controller());
                
                // Check for unhealthy brokers
                if (cluster.nodes().size() < 3) {
                    sendAlert("Cluster has less than 3 brokers: " + cluster.nodes().size());
                }
                
            } catch (Exception e) {
                sendAlert("Cluster health check failed: " + e.getMessage());
            }
        }
        
        private void monitorConsumerLag() {
            try {
                ListConsumerGroupsResult groupsResult = adminClient.listConsumerGroups();
                Collection<ConsumerGroupListing> groups = groupsResult.all().get();
                
                for (ConsumerGroupListing group : groups) {
                    String groupId = group.groupId();
                    
                    // Check consumer lag
                    DescribeConsumerGroupsResult describeResult = adminClient.describeConsumerGroups(
                        Collections.singletonList(groupId));
                    ConsumerGroupDescription description = describeResult.all().get().get(groupId);
                    
                    // Calculate lag and send alerts if necessary
                    if (description.members().isEmpty()) {
                        sendAlert("Consumer group " + groupId + " has no active members");
                    }
                }
                
            } catch (Exception e) {
                sendAlert("Consumer lag monitoring failed: " + e.getMessage());
            }
        }
        
        private void sendAlert(String message) {
            System.err.println("ALERT: " + message);
            // Send to alerting system (PagerDuty, Slack, etc.)
        }
    }
}
```

**🐞 Fixes:** Implement comprehensive monitoring and alerting, use appropriate replication factors, configure proper retention policies, implement circuit breakers, and maintain detailed operational runbooks for 99.99% availability.

---

### ✅ Strategy:

* Use correlation IDs in headers.
* Log ingestion and processing.
* Use distributed tracing (OpenTelemetry, Jaeger).

### 🧪 Code Snippet:

```java
headers.add(new RecordHeader("correlation-id", UUID.randomUUID().toString().getBytes()));
```

### ❓ Follow-Up:

* How to trace failures across consumers?

---

## 11. Describe a real-world problem you solved using Kafka and the impact it made.

### ✅ Example:

Migrated order processing from cron-based jobs to Kafka Streams for real-time order validation.

### 📈 Impact:

* Reduced delay from 15 minutes to <1 second.
* Increased processing throughput 3x.

### ❓ Follow-Up:

* What challenges did you face during the migration?

---

## 12. Kafka-based application requires 100% message delivery and processing. How would you achieve this?

### ✅ Strategies:

* Enable **exactly-once semantics**.
* Configure **acks=all**, **min.insync.replicas=2**.
* Use **transactional producer** and **committed consumer offsets**.
* Monitor DLQs.

### ❓ Follow-Up:

* What if the consumer crashes in the middle of processing?

---

---

> Use this mind map as a reference and tailor your preparation to the job role you're targeting.

---
