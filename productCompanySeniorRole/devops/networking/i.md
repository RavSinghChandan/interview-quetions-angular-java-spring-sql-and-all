# NETWORKING — LEVEL 3 (INTERMEDIATE)

**Stage Title: “You Understand How Real Systems Communicate Internally”**

*(Story continues — now you move from testing connectivity → understanding how real production architectures communicate across networks like real backend engineers.)*

---

# 🎯 OBJECTIVE OF THIS LEVEL

After this stage you will be able to:

* understand internal system networking
* analyze microservice communication
* debug service-to-service failures
* understand load balancing
* understand NAT and private networks
* design network flow mentally

You move from:

```
Testing Network → Understanding System Communication
```

---

# 1️⃣ REALITY — PRODUCTION SYSTEMS ARE NETWORKS INSIDE NETWORKS

Beginner view:

```
Browser → Server
```

Real production architecture:

```
Client
 → CDN
 → Load Balancer
 → API Gateway
 → Microservices
 → Databases
 → Caches
 → Queues
```

Each step is a network hop.

Every hop can fail.

---

# 2️⃣ INTERNAL VS EXTERNAL NETWORKS

Real systems use two networks:

### External Network

Public internet.

Used by users.

---

### Internal Network

Private infrastructure network.

Used by services.

Example:

```
public IP → gateway
private IP → backend service
```

Most production traffic happens internally.

---

# 3️⃣ PRIVATE IP ADDRESSING

Internal systems don’t use public IPs.

They use private ranges:

```
10.x.x.x
172.16.x.x – 172.31.x.x
192.168.x.x
```

Why?

Because public IPs are limited.

Private IPs allow millions of internal machines.

---

# 4️⃣ NAT — HOW PRIVATE MACHINES ACCESS INTERNET

Private machines cannot access internet directly.

They use:

> NAT (Network Address Translation)

Flow:

```
private server → NAT gateway → internet
```

NAT replaces private IP with public IP.

Internet replies to NAT → NAT forwards back.

NAT = translator between networks.

---

# 5️⃣ LOAD BALANCERS — TRAFFIC DISTRIBUTORS

If thousands of users hit server…

One machine cannot handle all.

Solution:

> Load balancer.

Flow:

```
Client → Load Balancer → Server1
                      → Server2
                      → Server3
```

Load balancer distributes traffic.

Prevents overload.

---

# 6️⃣ LOAD BALANCING STRATEGIES

Different algorithms:

```
Round robin
Least connections
IP hash
Weighted routing
```

Each strategy optimizes performance differently.

Senior engineers choose algorithm intentionally.

---

# 7️⃣ SERVICE DISCOVERY

Microservices don’t hardcode IP addresses.

Because containers restart and IPs change.

Instead they use:

> service discovery.

Example:

```
auth-service.internal
payment-service.internal
```

DNS resolves service names to current IPs.

This keeps systems dynamic.

---

# 8️⃣ INTERNAL DNS

Production systems run internal DNS servers.

Purpose:

```
resolve service names
route internal traffic
enable dynamic infrastructure
```

Without internal DNS → microservices fail.

---

# 9️⃣ CONNECTION FLOW INSIDE SYSTEM

Real backend request flow:

```
Client
→ Load balancer
→ API service
→ Auth service
→ Database
→ Cache
→ Response back
```

Each arrow is a network connection.

Each connection can fail.

---

# 🔟 NETWORK FAILURE TYPES INSIDE SYSTEMS

Common failures:

```
connection timeout
connection refused
DNS failure
routing error
packet loss
port blocked
```

Most production incidents are networking failures.

---

# 11️⃣ TIMEOUT — MOST IMPORTANT CONCEPT

Timeout = waiting too long for response.

Example:

```
API waits 3s for DB
DB slow → timeout
API fails → user error
```

Timeout protects systems from hanging forever.

---

# 12️⃣ RETRIES — FAILURE RECOVERY

Systems retry failed requests.

Example logic:

```
try request
if fail → retry
if fail → retry again
if fail → error
```

Retries improve reliability but must be limited.

Too many retries → overload.

---

# 13️⃣ CIRCUIT BREAKER PATTERN

If dependency failing repeatedly:

System stops calling it temporarily.

Why?

To prevent cascading failure.

This is:

> circuit breaker pattern.

Networking concept applied to architecture.

---

# 14️⃣ LATENCY INSIDE SYSTEMS

Latency not only internet problem.

Internal calls also have latency.

Example:

```
Service A → Service B → DB
```

Each hop adds delay.

Total latency = sum of all hops.

---

# 15️⃣ CASCADING FAILURE MODEL

One slow service can break entire system.

Example:

```
DB slow
→ API slow
→ Load balancer queue grows
→ Clients timeout
→ System appears down
```

Root cause = DB.

Symptoms everywhere else.

---

# 16️⃣ CONNECTION POOLS

Opening connections is expensive.

Systems reuse connections:

```
connection pool
```

Instead of:

```
open connection → close → open → close
```

They reuse existing connections.

Improves performance dramatically.

---

# 17️⃣ REAL DEBUGGING FLOW FOR SERVICE FAILURES

Senior engineers debug networked systems in order:

```
Check entry point
Trace request path
Check each service hop
Find slow or failing hop
Confirm dependency issue
```

Always trace path.

Never guess.

---

# 18️⃣ WHAT INTERVIEWERS TEST HERE

They don’t ask:

> What is TCP?

They ask:

> Why would one microservice fail to call another?

Expected reasoning:

```
DNS
ports
firewall
routing
timeouts
load
```

They test system thinking.

---

# 19️⃣ REAL DIFFERENCE BETWEEN LEVELS

| Level        | Networking Skill          |
| ------------ | ------------------------- |
| Beginner     | test connectivity         |
| Intermediate | understand system traffic |
| Advanced     | debug failures            |
| Pro          | design architecture       |

---

# 20️⃣ INTERMEDIATE COMPLETION CHECK

You can now:

✔ understand internal networks
✔ trace service communication
✔ understand load balancing
✔ understand NAT
✔ analyze failures
✔ debug timeouts
✔ reason about system traffic
✔ understand distributed communication

---

# FINAL LINE

At this point:

> Networking is no longer cables.

It is system communication.

And engineers who understand system communication…

are the ones trusted with production systems.

---

END OF LEVEL 3 — NETWORKING INTERMEDIATE
