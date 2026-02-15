Good. Continuing properly.

---

# 📁 File: `02-docker-architecture.md`

# 🔥 STEP 2 — Docker Architecture + Internals + Lifecycle Deep Dive

This file makes you **technically dangerous** in interviews.

If interviewer says:

* “Explain Docker architecture.”
* “What happens when you run docker run?”
* “Explain image layering.”
* “How is container isolation achieved?”

You must answer structurally.

---

# 🧠 1️⃣ High-Level Docker Architecture

Core components:

```
Docker Client
        ↓
Docker Daemon (dockerd)
        ↓
Container Runtime (containerd → runc)
        ↓
Linux Kernel (namespaces + cgroups)
```

And externally:

```
Docker Registry (DockerHub / ECR / GCR)
```

---

## 🔹 Docker Client

CLI commands:

```bash
docker build
docker run
docker pull
docker push
docker ps
```

Client communicates with daemon via REST API.

---

## 🔹 Docker Daemon (dockerd)

Main engine.

Responsibilities:

* Build images
* Manage containers
* Manage networks
* Manage volumes
* Pull/push images
* Track container lifecycle

Check daemon:

```bash
docker info
```

---

## 🔹 Container Runtime (containerd + runc)

Daemon does not directly run containers.

It delegates to:

* containerd
* runc

runc actually interacts with kernel.

Senior awareness: Docker is not magic — it wraps Linux primitives.

---

# 🧠 2️⃣ Docker Image — What It Really Is

Image = Read-only layered filesystem.

Contains:

* Base OS layer
* Runtime layer
* Dependency layer
* Application layer

Check layers:

```bash
docker history myapp:1.0
```

---

# 🧠 3️⃣ Layered Filesystem (CRITICAL CONCEPT)

Each Dockerfile instruction creates a layer.

Example:

```dockerfile
FROM openjdk:17
WORKDIR /app
COPY app.jar .
RUN chmod +x app.jar
```

Layers created:

1. Base image layer
2. WORKDIR layer
3. COPY layer
4. RUN layer

Layers are:

* Immutable
* Cached
* Reusable

---

## 🔥 Why Layering Matters

If 10 services use:

```dockerfile
FROM openjdk:17
```

That layer is shared.

No duplication in memory.

Huge optimization.

---

# 🧠 4️⃣ What Is a Container Really?

Container = Running instance of an image.

Internally:

```
Image layers (read-only)
+
Writable container layer
```

All runtime file changes go to writable layer.

If container removed → writable layer gone.

That’s why containers are ephemeral.

---

# 🧠 5️⃣ What Happens When You Run:

```bash
docker run myapp
```

Internal flow:

1. CLI sends request to Docker daemon
2. Daemon checks image locally
3. If not present → pulls from registry
4. Creates writable layer
5. Creates namespaces:

    * PID namespace
    * Network namespace
    * Mount namespace
6. Applies cgroup resource limits
7. Starts main process (PID 1)

Container lives as long as PID 1 runs.

If PID 1 exits → container stops.

---

# 🧠 6️⃣ Namespaces (Isolation Mechanism)

Docker uses Linux namespaces.

### PID Namespace

Each container has its own process tree.

Check inside container:

```bash
docker exec -it myapp ps aux
```

You see only container processes.

---

### Network Namespace

Each container has separate:

* IP
* Routing table
* Network interfaces

---

### Mount Namespace

Each container has its own filesystem view.

---

### User Namespace

Maps container users to host users.

---

# 🧠 7️⃣ cgroups (Resource Control)

Control:

* CPU
* Memory
* IO
* Network

Example:

```bash
docker run --memory="512m" --cpus="1.5" myapp
```

If memory exceeded:

Container → OOMKilled.

Important interview point.

---

# 🧠 8️⃣ Docker Registry

Stores images.

Example:

```bash
docker tag myapp:1.0 myrepo/myapp:1.0
docker push myrepo/myapp:1.0
```

Registry types:

* DockerHub
* AWS ECR
* GCP Artifact Registry
* Azure ACR

---

# 🧠 9️⃣ Writable Layer Behavior

When container runs:

Image (read-only)
+
Writable layer

If you modify file inside container:

```bash
docker exec -it myapp touch test.txt
```

File stored in writable layer.

Delete container → file gone.

Never use container FS for persistence.

---

# 🧠 🔟 Volumes (Data Persistence)

Persist data using volumes:

```bash
docker run -v myvolume:/data myapp
```

Bind mount:

```bash
docker run -v /host/data:/container/data myapp
```

Volume lifecycle independent of container.

---

# 🧠 1️⃣1️⃣ Docker Networking Basics

Default network: bridge

Run:

```bash
docker network ls
```

Port mapping:

```bash
docker run -p 8080:8080 myapp
```

Format:

```
HostPort:ContainerPort
```

---

# 🧠 1️⃣2️⃣ ENTRYPOINT vs CMD

Example:

```dockerfile
ENTRYPOINT ["java","-jar"]
CMD ["app.jar"]
```

ENTRYPOINT → main executable
CMD → default arguments

Override CMD:

```bash
docker run myapp other.jar
```

Override ENTRYPOINT:

```bash
docker run --entrypoint bash myapp
```

Interview favorite.

---

# 🧠 1️⃣3️⃣ Inspecting Containers

Inspect details:

```bash
docker inspect myapp
```

See:

* IP
* Mounts
* Env variables
* Resource limits

---

# 🧠 1️⃣4️⃣ Debugging a Container

Enter shell:

```bash
docker exec -it myapp sh
```

Check logs:

```bash
docker logs myapp
```

---

# 🧠 1️⃣5️⃣ Real Interview Simulation

### ❓ What happens internally when you run docker run?

Structured answer:

> Docker client sends request to daemon, daemon pulls image if needed, creates a writable layer, sets up namespaces for isolation, applies cgroups for resource control, and starts the container process as PID 1.

Memorize the structure.

---

# 🎯 STEP 2 REVISION CHECKLIST

You must confidently explain:

✔ Docker architecture components
✔ containerd + runc awareness
✔ Layered filesystem
✔ docker run lifecycle
✔ Namespaces
✔ cgroups
✔ Writable layer
✔ Volumes
✔ Networking basics
✔ ENTRYPOINT vs CMD

If you can explain these clearly →
Docker fundamentals strong.

---

When ready, type:

**3**

Next file:

📁 `03-dockerfile-production-mastery.md`
(Full production Dockerfile patterns + multi-stage + caching + security hardening)
