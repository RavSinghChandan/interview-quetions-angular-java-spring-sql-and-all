# CLOUD (AWS) — LEVEL 4 (INTERMEDIATE+)

**Stage Title: “You See Inside the Cloud — How Infrastructure Actually Works”**

*(Story continues — now you go beneath architecture diagrams and enter real cloud internals. This is where you stop seeing AWS as services and start seeing it as a massive distributed system.)*

---

# 🎯 OBJECTIVE OF THIS LEVEL

After this stage you will:

* understand cloud internals
* understand virtualization deeply
* understand how AWS allocates resources
* understand storage internals
* understand networking inside cloud
* understand how reliability is achieved

You move from:

```
Understanding Cloud Architecture → Understanding Cloud Mechanics
```

---

# 1️⃣ THE BIG REALIZATION

Cloud is not magic.

Cloud is:

> a giant distributed operating system for the world.

AWS is basically:

```
global hardware
+ automation software
+ virtualization
+ orchestration
```

You are interacting with that system.

---

# 2️⃣ WHAT ACTUALLY HAPPENS WHEN YOU LAUNCH INSTANCE

You click:

```
Launch EC2
```

Internally AWS performs:

```
find available physical server
allocate CPU slice
allocate RAM slice
allocate disk block
assign network interface
configure firewall rules
boot virtual machine
```

All automated in seconds.

---

# 3️⃣ HYPERVISOR — CLOUD ENGINE

Hypervisor is software that runs virtual machines.

It divides physical machine into:

```
VM1
VM2
VM3
VM4
```

Each VM thinks it owns hardware.

But hypervisor controls everything.

Hypervisor is heart of cloud.

---

# 4️⃣ RESOURCE SCHEDULING

Cloud must decide:

```
which physical server runs your VM?
```

Scheduler considers:

```
available CPU
available RAM
region load
hardware health
network topology
```

Placement decisions affect performance.

---

# 5️⃣ STORAGE INTERNALS

Cloud storage is not one disk.

It is:

> distributed storage cluster.

When you upload file:

```
split into chunks
store across multiple disks
replicate across machines
replicate across zones
```

Why?

Durability.

If one disk fails → data safe.

---

# 6️⃣ DATA REPLICATION MODEL

Cloud stores multiple copies of data.

Example:

```
Copy 1 → Server A
Copy 2 → Server B
Copy 3 → Server C
```

This protects against:

```
disk failure
server failure
rack failure
data center failure
```

Replication = reliability.

---

# 7️⃣ CLOUD NETWORK INTERNALS

Inside AWS, networking is software-defined.

Instead of physical cables:

```
virtual networks
virtual routers
virtual firewalls
virtual switches
```

This is called:

> Software Defined Networking (SDN).

Network is controlled by software.

---

# 8️⃣ IP ASSIGNMENT LOGIC

When instance launches:

Cloud allocates:

```
private IP
public IP
routing table
network rules
```

These are virtual.

But behave like real network.

---

# 9️⃣ LOAD BALANCER INTERNALS

Cloud load balancer is not single machine.

It is:

```
distributed routing system
```

Behind scenes:

```
many nodes
shared routing logic
health checks
traffic distribution engine
```

It scales automatically.

---

# 🔟 CLOUD FAILURE DESIGN

Cloud assumes hardware WILL fail.

So systems designed to survive:

```
disk failure
node failure
rack failure
zone failure
```

This philosophy is:

> failure is normal.

Cloud design = failure-tolerant architecture.

---

# 11️⃣ ISOLATION MODEL

Cloud must isolate customers.

Your server must not affect others.

Isolation layers:

```
hypervisor isolation
network isolation
storage isolation
permission isolation
```

Without isolation cloud would be insecure.

---

# 12️⃣ CLOUD SECURITY MODEL

Security is multi-layered:

```
physical security
network security
VM isolation
identity permissions
encryption
```

Security is built into infrastructure.

---

# 13️⃣ CLOUD NETWORK ROUTING

Traffic inside cloud routed using:

```
internal routing fabric
region routers
availability zone routers
edge routers
```

Cloud networking is high-speed private backbone.

Much faster than internet.

---

# 14️⃣ EDGE LOCATIONS

Cloud providers run edge locations globally.

Purpose:

```
cache content
reduce latency
serve users faster
```

Edge servers sit close to users.

---

# 15️⃣ SCALING INTERNALS

When auto-scaling triggers:

Cloud system:

```
provisions new VM
attaches storage
assigns network
registers with load balancer
starts health checks
```

All automated.

Scaling is orchestration problem solved by cloud.

---

# 16️⃣ BILLING ENGINE INTERNALS

Cloud tracks usage in real time:

```
CPU time
disk usage
network transfer
API calls
storage size
```

Billing is metering system.

Cloud is measured infrastructure.

---

# 17️⃣ WHY CLOUD CAN SCALE GLOBALLY

Because infrastructure is:

```
distributed
automated
virtualized
replicated
programmable
```

These five properties enable massive scale.

---

# 18️⃣ MASTER INTERNAL MODEL

If you remember only one thing:

```
Hardware → Hypervisor → Virtual Resources → APIs → User
```

That is cloud stack.

---

# 19️⃣ WHY THIS LEVEL IS RARE

Most engineers know:

```
how to use cloud
```

Few know:

```
how cloud works internally
```

Senior interviews test this.

---

# 20️⃣ COMPLETION CHECK

You now understand:

✔ virtualization internals
✔ resource scheduling
✔ distributed storage
✔ replication systems
✔ cloud networking internals
✔ scaling automation
✔ isolation layers
✔ reliability engineering

You now understand cloud as system.

---

# FINAL LINE

At this stage:

> Cloud is no longer services.

It is a global distributed system you understand.

And systems you understand…

you can design, debug, and scale.

---

END OF LEVEL 4 — CLOUD INTERMEDIATE+
