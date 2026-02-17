# DOCKER — LEVEL 4 (INTERMEDIATE+)

**Stage Title: “You See Inside Containers — How Docker Actually Works Internally”**

*(Story continues — now you stop using Docker as a tool and start understanding its internal mechanics.)*

---

# 🎯 OBJECTIVE OF THIS LEVEL

By the end you will:

* understand how containers really work
* know how Docker isolates processes
* understand namespaces & cgroups
* understand image layers deeply
* debug low-level container issues
* think like a systems engineer

This level transforms you from:

```
Docker User → Container Engineer
```

---

# 1️⃣ THE BIG REALIZATION

Containers are NOT magic.

A container is simply:

> a normal Linux process with isolation.

Yes — that’s it.

Run:

```
docker run -d nginx
```

Then check processes:

```
ps aux | grep nginx
```

You’ll see nginx running on host.

Meaning:

Container ≠ VM
Container = process

---

# 2️⃣ HOW DOCKER CREATES ISOLATION

Docker isolates processes using Linux kernel features:

```
Namespaces
Cgroups
Union Filesystem
Capabilities
```

These existed before Docker.

Docker just automated them.

---

# 3️⃣ NAMESPACES — THE INVISIBLE WALLS

Namespaces isolate resources.

Types:

```
PID namespace → process isolation
NET namespace → network isolation
MNT namespace → filesystem isolation
UTS namespace → hostname isolation
IPC namespace → memory isolation
USER namespace → user isolation
```

Check namespaces:

```
lsns
```

Each container runs inside its own namespace.

That’s why containers don’t see each other’s processes.

---

# 4️⃣ CGROUPS — RESOURCE CONTROL

Cgroups limit resources.

Example limits:

```
CPU
Memory
Disk I/O
Network bandwidth
```

Run container with limit:

```
docker run -m 200m nginx
```

Check cgroup info:

```
cat /proc/self/cgroup
```

Cgroups prevent one container from crashing entire system.

---

# 5️⃣ UNION FILESYSTEM — IMAGE LAYERS SECRET

Images are layered.

Example Dockerfile:

```
FROM ubuntu
RUN apt update
RUN apt install python
COPY app.py .
```

Each instruction creates a new layer.

View layers:

```
docker history imageName
```

Why layers matter:

* faster builds
* caching
* smaller images
* layer reuse

---

# 6️⃣ COPY-ON-WRITE MECHANISM

Images are read-only.

Containers add writable layer.

Meaning:

```
Image layer → shared
Container layer → private
```

So 100 containers can run from one image without duplicating files.

This is why containers are lightweight.

---

# 7️⃣ HOW CONTAINERS START SO FAST

VM startup:

```
boot OS → load kernel → start services
```

Container startup:

```
start process
```

Time difference:

VM → seconds/minutes
Container → milliseconds

---

# 8️⃣ DOCKER DAEMON ARCHITECTURE

Docker actually runs like this:

```
CLI → Docker API → Docker Daemon → Container Runtime → Kernel
```

You type:

```
docker run nginx
```

Flow:

```
CLI → daemon → containerd → runc → kernel
```

Each layer handles specific responsibility.

---

# 9️⃣ WHAT IS RUNC

`runc` is the low-level container runtime.

It directly interacts with kernel.

Docker is just user-friendly layer on top of runc.

You can even run container manually using runc (advanced).

---

# 🔟 CONTAINER NETWORKING INTERNALS

When container starts, Docker creates:

* virtual ethernet interface
* bridge network
* internal IP
* NAT rules

Check bridge:

```
ip a
```

You’ll see:

```
docker0
```

That’s Docker virtual network.

---

# 11️⃣ HOW PORT MAPPING WORKS INTERNALLY

Command:

```
docker run -p 8080:80 nginx
```

Internally Docker:

```
iptables rule created
host port 8080 → container IP:80
```

View rules:

```
iptables -t nat -L
```

---

# 12️⃣ WHY CONTAINERS ARE SECURE (AND WHEN THEY AREN’T)

Containers are isolated.

But isolation is kernel-level.

If kernel compromised → container compromised.

So containers are:

```
lightweight isolation
```

Not full virtualization.

Security best practice:

```
never run container as root
```

Run as user:

```
docker run -u 1000 nginx
```

---

# 13️⃣ PROCESS TREE INSIDE CONTAINER

Run container:

```
docker run -it ubuntu bash
```

Inside container:

```
ps aux
```

You’ll see only container processes.

But from host:

```
ps aux | grep bash
```

You see real process.

Meaning:

Container hides processes, not removes them.

---

# 14️⃣ CONTAINER FILESYSTEM LOCATION

Docker stores container data in:

```
/var/lib/docker/
```

Explore:

```
sudo ls /var/lib/docker
```

You’ll see:

```
overlay2
containers
volumes
images
```

This is Docker’s internal storage engine.

---

# 15️⃣ WHY IMAGES ARE IMMUTABLE

Images don’t change.

If you modify container, image stays same.

To change image:

You rebuild it.

This ensures:

> reproducibility.

---

# 16️⃣ WHY DOCKER BUILDS ARE CACHED

Docker rebuilds only changed layers.

Example:

If you modify only app code:

Docker rebuilds only last layer.

Not entire image.

That’s why Dockerfiles should be ordered carefully.

---

# 17️⃣ OPTIMAL DOCKERFILE ORDER (IMPORTANT)

Bad order:

```
COPY . .
RUN npm install
```

Good order:

```
COPY package.json .
RUN npm install
COPY . .
```

Why?

Dependencies cached → builds faster.

---

# 18️⃣ HOW CONTAINERS DIE

Container stops when main process stops.

Example:

```
docker run ubuntu
```

It exits immediately because no foreground process.

To keep alive:

```
docker run ubuntu sleep infinity
```

---

# 19️⃣ LOW-LEVEL DEBUG TOOLKIT

Real container debugging commands:

```
docker inspect
docker logs
docker stats
docker exec
docker top
```

These are used by real production engineers.

---

# 20️⃣ THE INTERNAL MENTAL MODEL

If you remember only one thing from this level:

Remember:

```
Container = process + isolation + limits
```

That is Docker’s core.

---

# 🏁 INTERMEDIATE+ COMPLETION CHECK

You now understand:

✔ containers are processes
✔ namespaces isolate containers
✔ cgroups limit resources
✔ images are layered filesystems
✔ containers use copy-on-write
✔ networking uses bridges + NAT
✔ Docker daemon architecture
✔ how builds are cached

---

# FINAL LINE

At this stage:

> Docker is no longer a tool.

It is a system you understand.

And systems you understand…

you can control.

---

END OF DOCKER LEVEL 4 — INTERMEDIATE+
