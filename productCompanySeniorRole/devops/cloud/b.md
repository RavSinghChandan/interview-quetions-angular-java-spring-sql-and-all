# CLOUD (AWS) — LEVEL 2 (BASIC PRACTICAL)

**Stage Title: “You Launch Real Infrastructure Like an Engineer”**

*(Now theory ends. You start using real cloud infrastructure. This is where cloud stops being concept and becomes something you control.)*

---

# 🎯 OBJECTIVE OF THIS LEVEL

After this stage you will be able to:

* launch real servers
* connect to them
* deploy apps
* store files in cloud
* manage access
* understand real infrastructure behavior

You move from:

```
Understanding Cloud → Using Cloud
```

Everything below is practical foundation of real cloud engineering.

---

# 1️⃣ CREATE YOUR FIRST CLOUD SERVER (EC2)

In AWS console → launch instance.

What actually happens internally:

```
AWS allocates CPU
allocates RAM
allocates storage
assigns network
boots OS
```

You just created a real remote machine.

Equivalent CLI:

```
aws ec2 run-instances --image-id ami-id --instance-type t2.micro
```

You now own a server.

---

# 2️⃣ CONNECT TO YOUR SERVER

Using SSH:

```
ssh ubuntu@public-ip
```

Now your terminal controls remote machine.

You are operating computer thousands of kilometers away.

---

# 3️⃣ VERIFY SERVER IS REAL

Run:

```
top
```

You’ll see CPU + memory usage.

Run:

```
lsblk
```

Shows disk.

Run:

```
ip a
```

Shows network.

This proves cloud server = real machine.

---

# 4️⃣ DEPLOY SIMPLE WEB SERVER

Inside instance:

```
sudo apt update
sudo apt install nginx -y
```

Start server:

```
sudo systemctl start nginx
```

Open browser:

```
http://your-public-ip
```

You just deployed real production server.

---

# 5️⃣ SECURITY GROUP — CLOUD FIREWALL

By default cloud blocks traffic.

You must allow ports.

Example rule:

```
Allow TCP 80 from 0.0.0.0/0
```

This opens HTTP access.

Security groups control:

> who can talk to your server.

---

# 6️⃣ STOPPING SERVER

Command:

```
aws ec2 stop-instances --instance-ids id
```

Server stops.

Billing for compute stops.

You are billed only for storage.

Cloud saves money automatically.

---

# 7️⃣ TERMINATE SERVER

Command:

```
aws ec2 terminate-instances --instance-ids id
```

Server deleted permanently.

Hardware returned to cloud pool.

Cloud resources are temporary by design.

---

# 8️⃣ CLOUD STORAGE (S3)

Upload file:

```
aws s3 cp file.txt s3://mybucket/
```

File stored in cloud storage.

Accessible globally.

Durable across data centers.

S3 is not disk.

It is distributed storage system.

---

# 9️⃣ DOWNLOAD FILE

```
aws s3 cp s3://mybucket/file.txt .
```

Cloud storage behaves like remote filesystem.

---

# 🔟 ACCESS CONTROL (IAM)

Cloud must control permissions.

IAM defines:

```
who can access what
```

Example policy:

```
Allow user → read S3
Deny user → delete EC2
```

IAM = cloud security foundation.

---

# 11️⃣ CLOUD NETWORKING BASICS

Each server gets:

```
private IP
public IP
firewall rules
routing table
```

Cloud networking behaves like real networking.

Because it is real networking.

---

# 12️⃣ CREATE PRIVATE SERVER

Launch instance without public IP.

Now it can:

```
talk to internal services
NOT accessible from internet
```

This is how databases run securely.

---

# 13️⃣ CLOUD RESOURCE LIFECYCLE

Every cloud resource has lifecycle:

```
create
configure
use
monitor
scale
delete
```

Understanding lifecycle = infrastructure mastery.

---

# 14️⃣ REAL DEBUGGING SCENARIOS YOU CAN NOW HANDLE

You can now debug:

```
cannot connect to server
website not loading
SSH failing
file upload failing
permission denied
```

These are real production problems.

---

# 15️⃣ REAL ENGINEER WORKFLOW

Daily cloud workflow:

```
launch instances
deploy apps
check logs
monitor metrics
scale resources
update configs
secure access
```

Cloud is daily tool.

---

# 16️⃣ MOST IMPORTANT PRACTICAL CONCEPT

Cloud resources are:

> disposable.

Never depend on single instance.

Always assume servers can disappear anytime.

Design systems accordingly.

---

# 17️⃣ WHAT YOU JUST LEARNED

You can now:

* create servers
* connect remotely
* deploy apps
* manage storage
* control access
* configure firewall
* manage lifecycle

You are now using cloud.

---

# 18️⃣ REAL INTERVIEW QUESTION THEY ASK HERE

They won’t ask:

> What is EC2?

They ask:

> How would you deploy a backend service on cloud?

Expected flow:

```
launch server
configure firewall
deploy app
expose port
monitor logs
```

They test practical thinking.

---

# 19️⃣ BASIC COMPLETION CHECK

You can now:

✔ launch servers
✔ connect remotely
✔ deploy services
✔ store files in cloud
✔ manage access
✔ configure networking
✔ control lifecycle
✔ debug connectivity

---

# 20️⃣ FINAL LINE

At this point:

> Cloud is no longer abstract.

You can create real infrastructure.

And engineers who can create infrastructure…

are the ones trusted with production systems.

---

END OF LEVEL 2 — CLOUD BASIC
