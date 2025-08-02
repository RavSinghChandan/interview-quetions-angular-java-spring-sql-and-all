# 🧠 Apache Kafka Interview Questions for Senior Backend Developers

This markdown file contains a curated list of **Apache Kafka** interview questions categorized by **Basic**, **Intermediate**, **Advanced**, and **Practical Application-Based** topics. It is intended for **senior backend developers** preparing for interviews or enhancing their understanding.

---

## ✅ Basic Questions

# 💡 Apache Kafka Interview Questions for Senior Java Developers

---

## ✅ Basic Questions (with Definitions, Code Examples & Follow-ups)

---

### 1. **What is Kafka?**

**Definition:**
Apache Kafka is a distributed event streaming platform capable of handling trillions of events per day. It's used for building real-time data pipelines and streaming applications.

**Code Example (Java Producer):**

```java
Properties props = new Properties();
props.put("bootstrap.servers", "localhost:9092");
props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

Producer<String, String> producer = new KafkaProducer<>(props);
producer.send(new ProducerRecord<>("my-topic", "key", "value"));
producer.close();
```

**Follow-up Questions:**

* How is Kafka different from traditional message brokers like RabbitMQ?
* What are some use cases of Kafka in microservice architecture?

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

## ⚙️ Intermediate Questions

# Kafka Interview Questions (Intermediate Level)

> This section contains detailed explanations, real Java code examples, and follow-up questions for each intermediate-level Kafka interview question, following the structured 4F format:
> **Fact ➤ Flow ➤ Follow-Up ➤ Future-Proofing**

---

### 1. What is a Kafka Topic? How is it different from a Queue?

**Fact:**
A **Kafka Topic** is a category/feed name to which records are published. It can be consumed by one or more consumers. Unlike traditional queues (which typically support one-consumer-per-message), Kafka topics allow multiple consumers to read the same message independently.

**Flow (Java Code Example):**

```java
Properties props = new Properties();
props.put("bootstrap.servers", "localhost:9092");
props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

Producer<String, String> producer = new KafkaProducer<>(props);
producer.send(new ProducerRecord<>("my-topic", "key1", "Hello Kafka Topic!"));
producer.close();
```

**Follow-Up:**

* What happens if multiple consumers read from the same topic?
* How does Kafka ensure message ordering within a partition?

**Future-Proofing:**
Use Kafka Consumer Groups when scaling read operations to avoid duplicate processing while still supporting parallelism.

---

### 2. Explain Kafka Partitions. Why are they important?

**Fact:**
Kafka topics are split into **partitions** to allow parallel processing, scalability, and fault-tolerance. Each partition is an ordered, immutable sequence of messages.

**Flow (Java Code Example):**
You can assign partitions manually like:

```java
ProducerRecord<String, String> record = new ProducerRecord<>("my-topic", 2, "key1", "Partitioned Message");
producer.send(record);
```

**Follow-Up:**

* What determines which partition a message goes to?
* Can partitions be increased after topic creation?

**Future-Proofing:**
Partitioning based on keys helps maintain ordering guarantees for messages with the same key.

---

### 3. What is Kafka Broker? How many brokers do you need?

**Fact:**
A **Kafka Broker** is a Kafka server that stores and serves data (messages) in topics. Brokers handle message requests from producers and consumers.

**Flow:**
You can start multiple Kafka brokers with unique configurations like `broker.id`, `log.dirs`, and `port`.

**Follow-Up:**

* What happens if a broker fails?
* What is the role of Zookeeper in managing brokers?

**Future-Proofing:**
Use Kafka in a cluster of 3+ brokers with replication to ensure high availability.

---

### 4. What is a Kafka Consumer Group?

**Fact:**
A **Consumer Group** is a group of consumers that work together to consume messages from a topic. Each message is read by only one consumer in the group.

**Flow (Java Code Example):**

```java
Properties props = new Properties();
props.put("bootstrap.servers", "localhost:9092");
props.put("group.id", "group1");
props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
consumer.subscribe(Collections.singletonList("my-topic"));
```

**Follow-Up:**

* What if two consumers have the same group ID?
* What is rebalancing in consumer groups?

**Future-Proofing:**
Enable `heartbeat.interval.ms` and `session.timeout.ms` properly to ensure fast and safe consumer group rebalancing.

---

### 5. How does Kafka ensure reliability?

**Fact:**
Kafka ensures reliability through **replication**, **acknowledgments**, and **commit logs**.

**Flow:**
Set producer properties like:

```java
props.put("acks", "all");
props.put("retries", 3);
```

**Follow-Up:**

* What is the difference between `acks=1`, `acks=0`, and `acks=all`?
* How does Kafka commit messages?

**Future-Proofing:**
Use Idempotent Producers and enable `enable.idempotence=true` to prevent duplicate message production.

---

### 6. Explain Kafka Offset and its management.

**Fact:**
An **Offset** is a unique ID assigned to each message in a partition. Consumers track which offset they have read to avoid reprocessing.

**Flow:**

```java
consumer.commitSync(); // Manual commit of offset
```

**Follow-Up:**

* What is the difference between auto-commit and manual-commit?
* How can you reset a consumer offset?

**Future-Proofing:**
Use manual commit in production to avoid data loss during consumer failures.

---

Would you like to move on to **Advanced Kafka Questions** next?

---

## 🚀 Advanced Questions
# Kafka Interview Questions — Advanced

---

### 1. **What is Kafka's Exactly-Once Semantics (EOS), and how does it work?**

**Definition:**
Exactly-once semantics ensure that records are neither lost nor duplicated during processing — a critical feature for financial and data-sensitive applications.

**Code Example:**

```java
Properties props = new Properties();
props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");
props.put(ProducerConfig.ACKS_CONFIG, "all");
props.put(ProducerConfig.RETRIES_CONFIG, Integer.toString(Integer.MAX_VALUE));
props.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, "5");

KafkaProducer<String, String> producer = new KafkaProducer<>(props);
```

**Follow-up Questions:**

* What are the trade-offs of enabling EOS?
* How does Kafka ensure idempotency in this context?

---

### 2. **How does Kafka handle message compression, and when should you use it?**

**Definition:**
Kafka supports message compression using algorithms like GZIP, Snappy, LZ4, and Zstd to reduce payload size and increase throughput.

**Code Example:**

```java
props.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "snappy");
```

**Follow-up Questions:**

* How does compression affect latency and CPU usage?
* Which compression type is best for high-throughput scenarios?

---

### 3. **What is Kafka's ISR (In-Sync Replica) and how does it affect fault tolerance?**

**Definition:**
ISR is a set of replicas that are fully caught up with the leader. Kafka only commits messages to the log once all ISRs acknowledge the write (based on the configured `acks`).

**Code Insight:**

```properties
min.insync.replicas=2
acks=all
```

**Follow-up Questions:**

* What happens if ISR falls below `min.insync.replicas`?
* How can ISR fluctuations affect availability?

---

### 4. **How do Kafka transactions work and how are they different from idempotent producers?**

**Definition:**
Kafka transactions allow multiple messages across multiple partitions to be written atomically.

**Code Example:**

```java
producer.initTransactions();
producer.beginTransaction();
producer.send(new ProducerRecord<>("topic1", key, value));
producer.send(new ProducerRecord<>("topic2", key, value));
producer.commitTransaction();
```

**Follow-up Questions:**

* What is the role of the transaction coordinator?
* Can you use transactions with consumer groups?

---

### 5. **What is Log Compaction in Kafka and how does it differ from retention?**

**Definition:**
Log compaction retains only the latest value for each key. Retention deletes messages based on time or size.

**Code Example (topic config):**

```bash
kafka-topics.sh --create --topic compacted-topic \
  --partitions 1 --replication-factor 1 \
  --config cleanup.policy=compact
```

**Follow-up Questions:**

* When should you prefer compaction over time-based retention?
* What are compaction performance implications?

---

### 6. **Explain Kafka Quotas.**

**Definition:**
Kafka quotas restrict the amount of data producers/consumers can publish or consume to prevent resource starvation.

**Code Insight (CLI):**

```bash
kafka-configs.sh --alter --add-config 'producer_byte_rate=1048576' \
--entity-type clients --entity-name clientA --bootstrap-server localhost:9092
```

**Follow-up Questions:**

* How do you monitor quota violations?
* Can quotas be set at the user level?

---

### 7. **How would you secure Kafka for enterprise usage?**

**Definition:**
Kafka provides SSL, SASL, and ACLs to secure data in transit, authenticate users, and authorize actions.

**Security Options:**

* SSL encryption
* SASL/PLAIN or SASL/GSSAPI (Kerberos) authentication
* ACLs for authorization

**Follow-up Questions:**

* What is the order of configuration for security setup?
* How does Kafka handle expired certificates?

---

### 8. **How does Kafka Connect ensure fault-tolerance and scalability?**

**Definition:**
Kafka Connect uses distributed mode, worker rebalancing, and offset storage in Kafka to ensure scalability and resilience.

**Follow-up Questions:**

* How are connectors restarted after failure?
* Where are offsets stored in Connect?

---

### 9. **What is the internal architecture of Kafka’s Consumer Group?**

**Definition:**
A Kafka consumer group allows multiple consumers to read from a topic in parallel, coordinating offset tracking and partition assignment.

**Follow-up Questions:**

* What is the role of the group coordinator?
* How does Kafka rebalance consumers?

---

### 10. **How can you monitor Kafka performance and health in production?**

**Definition:**
Kafka provides metrics via JMX, and integrates with tools like Prometheus, Grafana, Confluent Control Center.

**Key Metrics to Monitor:**

* Message throughput (ingress/egress)
* Under-replicated partitions
* ISR size
* GC pause times

**Follow-up Questions:**

* How would you alert on consumer lag?
* Which tools do you prefer for end-to-end Kafka monitoring?

---

Would you like these advanced topics to be explained with hands-on projects or architecture diagrams next?


---

## 💡 Practical/Scenario-Based Questions
# Kafka Practical / Scenario-Based Questions with Answers

---

## 1. How would you design a Kafka architecture for a payment processing system?

### ✅ Definition:

Designing a payment processing system requires high reliability, exactly-once semantics, security, and fault-tolerance. Kafka fits well as a backbone due to its durability and event-driven architecture.

### 🧠 Explanation:

* **Producer Layer**: Payment services produce transaction events to Kafka topics (e.g., `payments`).
* **Kafka Cluster**: Use multiple brokers, replication (RF=3), and partitions.
* **Consumers**: Downstream services like fraud detection, ledger update, notification system consume.
* **Exactly-once Semantics (EOS)**: Enable idempotency in producers and transactional processing in consumers.
* **Schema Registry**: For AVRO schema validation and evolution.
* **Monitoring**: Prometheus + Grafana.

### 🧪 Code Snippet:

```java
Properties props = new Properties();
props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");
props.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "txn-123");
KafkaProducer<String, Payment> producer = new KafkaProducer<>(props);
producer.initTransactions();
```

### ❓ Follow-Up:

* How do you handle failures in this architecture?
* What is the role of Kafka Streams or ksqlDB?

---

## 2. Kafka is dropping messages — how do you debug?

### ✅ Definition:

Message drops can happen due to misconfigurations, timeouts, network issues, or incorrect consumer offsets.

### 🔍 Steps:

* Check **broker logs** for errors.
* Analyze **producer retry config** (`acks`, `retries`, `linger.ms`).
* Validate consumer **offsets and commits**.
* Look for **broker disk pressure**, retention policies.

### 🧪 Example Config:

```properties
acks=all
retries=3
retry.backoff.ms=100
```

### ❓ Follow-Up:

* How do you ensure zero data loss?
* How does replication factor affect this?

---

## 3. What tools do you use to monitor Kafka in production?

### ✅ Tools:

* **Prometheus + Grafana**: Metrics visualization.
* **Kafka Manager / Confluent Control Center**: Cluster management.
* **JMX Exporter**: Expose JVM/Kafka metrics.
* **Burrow**: Consumer lag monitoring.

### 📈 Metrics Monitored:

* Under-replicated partitions
* Consumer lag
* Broker health (heap, GC)

### ❓ Follow-Up:

* How do you monitor message throughput and latency?

---

## 4. How would you handle schema evolution in a Kafka data pipeline?

### ✅ Definition:

Schema evolution allows producers to change the message structure without breaking consumers.

### 🛠️ Tools:

* **Confluent Schema Registry** with AVRO/Protobuf.
* Register schema with compatibility mode (e.g., `BACKWARD`).

### 🧪 Code Snippet:

```json
{"type":"record","name":"Payment","fields":[
  {"name":"amount","type":"float"},
  {"name":"method","type":"string", "default":"card"}
]}
```

### ❓ Follow-Up:

* What’s the difference between `BACKWARD` and `FORWARD` compatibility?

---

## 5. You need to migrate a monolithic batch job to real-time Kafka processing. Walk us through your approach.

### 🧠 Steps:

1. Identify the batch logic boundaries.
2. Break logic into producers and consumers.
3. Stream records instead of collecting them.
4. Use Kafka Streams or Flink.
5. Handle stateful operations using local state or RocksDB.

### ❓ Follow-Up:

* What challenges do you face in this migration?

---

## 6. How to implement a retry mechanism for failed Kafka messages?

### ✅ Options:

* Use **Dead Letter Queues (DLQs)**.
* Retry within consumer logic using exponential backoff.
* Use Kafka Streams `transform()` or `retry-topic` pattern.

### 🧪 Code Logic:

```java
try {
  process(record);
} catch(Exception e) {
  sendToRetryTopic(record);
}
```

### ❓ Follow-Up:

* How would you avoid infinite retry loops?

---

## 7. Kafka consumers are lagging — what steps would you take to investigate?

### 🔍 Steps:

1. Check consumer throughput.
2. Increase partition count.
3. Parallelize consumers.
4. Tune `max.poll.records`, `session.timeout.ms`.

### ❓ Follow-Up:

* What if the lag is only for one topic?

---

## 8. What would you do if one partition receives significantly more traffic than others?

### ✅ Solution:

* Rebalance producer’s key selection (custom Partitioner).
* Increase partition count.
* Apply load balancing logic.

### ❓ Follow-Up:

* What is the role of sticky partitioning?

---

## 9. How do you version your Kafka topics and schemas?

### ✅ Approach:

* Add version suffix to topic: `payments-v1`, `payments-v2`.
* Register versioned schemas in Schema Registry.

### ❓ Follow-Up:

* What are the downsides of versioned topics?

---

## 10. How do you ensure end-to-end traceability in a Kafka-based microservices architecture?

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
