Excellent.
Now we move into **real cloud networking maturity**.

This is where many backend engineers struggle.

If you understand this clearly, you think like a system architect.

---

# 📁 File: `07-vpc-networking-subnet-routing-deep.md`

# 🔥 STEP 7 — VPC + Subnets + Routing + NAT + Internet Gateway

(Cloud Networking Deep Dive — Senior Backend Level)

Interviewers may ask:

* What is VPC?
* What is public vs private subnet?
* How does instance access internet?
* What is NAT Gateway?
* How traffic flows inside VPC?
* How do you secure DB?

You must answer structurally.

---

# 🧠 1️⃣ What Is VPC?

VPC = Virtual Private Cloud.

It is:

Your isolated network inside cloud.

Example CIDR block:

```
10.0.0.0/16
```

Inside VPC you create:

* Subnets
* Route tables
* Security groups

Think of VPC as your private data center network.

---

# 🧠 2️⃣ Subnets

Subnet = smaller network inside VPC.

Example:

VPC: 10.0.0.0/16

Subnets:

* 10.0.1.0/24
* 10.0.2.0/24
* 10.0.3.0/24

Each subnet belongs to one Availability Zone.

---

# 🧠 3️⃣ Public vs Private Subnet

### Public Subnet

* Has route to Internet Gateway (IGW)
* Instances can receive internet traffic
* Used for Load Balancer

### Private Subnet

* No direct internet route
* Used for app servers and databases
* More secure

Best practice:

✔ Load balancer → public subnet
✔ App + DB → private subnet

---

# 🧠 4️⃣ Internet Gateway (IGW)

IGW connects VPC to internet.

Attach IGW to VPC.

Route table entry:

```
Destination: 0.0.0.0/0
Target: Internet Gateway
```

Only public subnets have this route.

---

# 🧠 5️⃣ NAT Gateway (Very Important)

Problem:

Private subnet instances cannot access internet.

But they may need:

* Software updates
* Calling external APIs

Solution:

NAT Gateway.

Flow:

Private instance
↓
NAT Gateway (in public subnet)
↓
Internet

NAT allows outbound internet.
Not inbound.

---

# 🧠 6️⃣ Route Tables

Each subnet has route table.

Example public route table:

```
10.0.0.0/16 → local
0.0.0.0/0 → IGW
```

Example private route table:

```
10.0.0.0/16 → local
0.0.0.0/0 → NAT Gateway
```

Routing defines traffic behavior.

---

# 🧠 7️⃣ How Traffic Flows (External User)

User
↓
Public IP
↓
Load Balancer (Public Subnet)
↓
Private App Instance
↓
Private DB

App never directly exposed.

---

# 🧠 8️⃣ Security Groups (Stateful Firewall)

Security group controls:

* Inbound traffic
* Outbound traffic

Example DB security group:

Allow inbound:

Port 3306
Source: App server security group

Not open to 0.0.0.0/0.

Principle of least privilege.

---

# 🧠 9️⃣ Network ACL (Stateless)

Applied at subnet level.

Less commonly configured directly.

Security groups are primary mechanism.

Know difference:

Security Group → stateful
NACL → stateless

---

# 🧠 🔟 Multi-AZ VPC Design

Best practice:

Public Subnet AZ1
Public Subnet AZ2

Private Subnet AZ1
Private Subnet AZ2

Load balancer spans AZs.
App runs across AZs.
DB Multi-AZ enabled.

High availability architecture.

---

# 🧠 1️⃣1️⃣ Real Interview Scenario

Question:

“How do you design secure backend architecture in cloud?”

Strong answer:

* Create VPC
* Public subnet for ALB
* Private subnets for app and DB
* Security groups restrict traffic
* NAT gateway for outbound
* Multi-AZ deployment

Clear and structured.

---

# 🧠 1️⃣2️⃣ Common Production Mistakes

✘ Putting DB in public subnet
✘ Opening DB to 0.0.0.0/0
✘ No NAT for private instances
✘ Single AZ deployment

Senior engineers avoid these.

---

# 🧠 1️⃣3️⃣ Internal Communication

Instances inside same VPC:

Use private IP.

Faster, cheaper, secure.

Never use public IP internally.

---

# 🧠 1️⃣4️⃣ Cost Awareness

NAT Gateway costs per hour + data.

Design carefully.

Large traffic through NAT can increase bill.

---

# 🧠 1️⃣5️⃣ Senior-Level Mental Model

VPC = isolated network boundary
Public subnet = internet-facing layer
Private subnet = protected layer
Security group = firewall
Route table = traffic controller
NAT = outbound internet bridge

Backend must live in private subnet.

---

# 🎯 STEP 7 REVISION CHECKLIST

You must confidently explain:

✔ What VPC is
✔ Subnets concept
✔ Public vs private subnet
✔ Internet Gateway
✔ NAT Gateway
✔ Route table behavior
✔ Security group vs NACL
✔ Multi-AZ network design
✔ Secure architecture pattern

If you can explain all clearly →
Cloud networking maturity achieved.

---

When ready, type:

**8**

Next file:

📁 `08-cloud-load-balancer-auto-scaling-deep.md`
(Cloud load balancer internals + health checks + auto scaling group + scaling policies + backend elasticity deep dive)
