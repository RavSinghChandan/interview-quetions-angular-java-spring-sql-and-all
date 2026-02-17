# NETWORKING — LEVEL 1 (NAIVE)

**Stage Title: “Meeting the System That Lets Computers Talk”**

*(This continues the story. You are seeing Networking for the first time — not as commands, but as a system.)*

---

# 🎯 PURPOSE OF THIS LEVEL

At this stage you are **not learning commands or protocols deeply**.

You are learning:

> how networking actually works conceptually.

Because once you understand its logic, tools like `ping`, `curl`, `netstat`, `tcpdump` become obvious.

Most engineers memorize commands.

Senior engineers understand communication systems.

You will understand the system.

---

# 1️⃣ WHAT NETWORKING ACTUALLY IS (REAL DEFINITION)

Networking is:

> a system that allows machines to exchange data.

That’s it.

Everything else (IP, ports, DNS, HTTP, TCP) exists only to make this possible.

---

# 2️⃣ THE MOST IMPORTANT IDEA

Networking is about:

> sending data from one machine to another reliably.

That means solving problems:

```
How to find destination?
How to reach destination?
How to send data?
How to ensure delivery?
How to handle errors?
```

Networking solves all five.

---

# 3️⃣ THE CORE MENTAL MODEL

Think of networking like postal delivery.

| Networking | Postal System    |
| ---------- | ---------------- |
| IP address | house address    |
| Packet     | letter           |
| Router     | post office      |
| Protocol   | delivery rules   |
| Port       | apartment number |

This analogy explains almost everything.

---

# 4️⃣ WHAT ACTUALLY MOVES IN NETWORKS

Not files.

Not messages.

Not requests.

What actually travels:

> packets.

A packet is:

> small chunk of data with address info.

Large data is split into many packets.

---

# 5️⃣ HOW DATA TRAVELS (HIGH LEVEL FLOW)

When you open a website:

```
Browser → Internet → Server → Internet → Browser
```

But internally it’s:

```
request → packets → routers → destination → response packets → back
```

Internet works by passing packets across many machines.

---

# 6️⃣ WHY DATA IS SPLIT INTO PACKETS

Instead of sending huge data:

```
send small packets
```

Benefits:

* faster
* reliable
* recoverable
* parallel transmission

If one packet lost → resend only that.

Not entire file.

---

# 7️⃣ IP ADDRESS — MACHINE IDENTITY

Every machine on network has identifier:

```
IP address
```

Example:

```
192.168.1.10
```

IP is like machine’s home address.

Without IP → no delivery possible.

---

# 8️⃣ PORT — APPLICATION ADDRESS

IP identifies machine.

But machines run many services:

```
web server
database
SSH
email
```

How does network know which service to send data?

Answer:

> ports.

Example:

```
80 → HTTP
443 → HTTPS
22 → SSH
3306 → MySQL
```

Port = application door.

---

# 9️⃣ ROUTERS — INTERNET TRAFFIC POLICE

Routers decide:

> where packet should go next.

They don’t know entire path.

They only know:

```
next best direction
```

Packets travel router → router → router until destination reached.

---

# 🔟 PROTOCOLS — COMMUNICATION RULES

Computers must agree on rules.

Protocols define:

```
format
timing
error handling
delivery confirmation
```

Examples:

```
TCP
UDP
HTTP
DNS
FTP
```

Protocols are networking laws.

---

# 11️⃣ TCP vs UDP (IMPORTANT CONCEPT)

Two main transport methods.

---

### TCP — Reliable Delivery

Guarantees:

```
ordered delivery
no data loss
confirmation
retransmission
```

Used for:

```
web
APIs
databases
```

---

### UDP — Fast Delivery

Does NOT guarantee delivery.

But faster.

Used for:

```
video streaming
games
voice calls
```

Tradeoff:

```
TCP = safe but slower
UDP = fast but risky
```

---

# 12️⃣ DNS — INTERNET PHONEBOOK

Humans use domain names:

```
google.com
```

Machines use IP:

```
142.250.183.14
```

DNS converts:

```
domain → IP
```

Without DNS → internet unusable.

---

# 13️⃣ NETWORK LAYERS (SIMPLIFIED MODEL)

Networking divided into layers.

Simplified stack:

```
Application
Transport
Network
Link
```

Each layer handles part of communication.

Layers keep networking organized.

---

# 14️⃣ LATENCY — TRAVEL TIME

Latency = time data takes to travel.

Measured in:

```
milliseconds
```

High latency = slow response.

Latency depends on:

* distance
* routers
* congestion
* bandwidth

---

# 15️⃣ BANDWIDTH — DATA SPEED

Bandwidth = how much data can travel per second.

Example:

```
100 Mbps
1 Gbps
```

Bandwidth = road width.

Latency = travel time.

---

# 16️⃣ PACKET LOSS

Sometimes packets don’t reach destination.

Causes:

```
network congestion
hardware issues
signal interference
```

Reliable protocols resend lost packets.

---

# 17️⃣ CONNECTION CONCEPT

Some protocols create connection first.

Example TCP:

```
handshake
connection established
data sent
connection closed
```

Connection ensures reliable communication.

---

# 18️⃣ THE GOLDEN MENTAL MODEL

If you remember only one thing:

```
Networking = sending packets between machines using protocols
```

That is networking.

---

# 19️⃣ WHY NETWORKING IS CRITICAL FOR BACKEND ENGINEERS

Backend systems depend on:

* APIs
* databases
* caches
* microservices

All communicate over network.

If networking breaks → system breaks.

Understanding networking = understanding system behavior.

---

# 20️⃣ NAIVE COMPLETION CHECK

You now understand:

✔ what networking really is
✔ what packets are
✔ what IP does
✔ what ports do
✔ what routers do
✔ what protocols are
✔ TCP vs UDP
✔ DNS purpose

No commands yet.

Only understanding.

---

# FINAL LINE

At this stage:

> Networking is no longer mysterious.

You understand its logic.

And once you understand its logic…

you can understand how systems communicate.

---

END OF LEVEL 1 — NETWORKING NAIVE
