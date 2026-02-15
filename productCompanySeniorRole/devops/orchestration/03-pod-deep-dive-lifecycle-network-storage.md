Good. Now we go into the most fundamental object.

---

# 📁 File: `03-pod-deep-dive-lifecycle-network-storage.md`

# 🔥 STEP 3 — Pod Deep Dive (Lifecycle + Networking + Storage Internals)

This is critical.

Interviewers ask:

* What exactly is a Pod?
* Why not deploy container directly?
* Do containers inside a pod share IP?
* What are pod lifecycle phases?
* What happens when pod restarts?

You must answer clearly.

---

# 🧠 1️⃣ What Is a Pod?

Pod is the **smallest deployable unit** in Kubernetes.

Important:

You do NOT deploy containers.
You deploy Pods.

A Pod contains:

* One or more containers
* Shared network
* Shared storage (optional)

---

# 🧠 2️⃣ Why Pod Exists (Design Thinking)

Why not just deploy container?

Because sometimes you need:

* Sidecar container (logging, proxy)
* Init container
* Shared storage between containers

Pod groups tightly coupled containers.

---

# 🧠 3️⃣ Single-Container Pod (Most Common)

Example:

```yaml
apiVersion: v1
kind: Pod
metadata:
  name: myapp
spec:
  containers:
    - name: app
      image: myapp:1.0
      ports:
        - containerPort: 8080
```

But in production → usually managed via Deployment.

---

# 🧠 4️⃣ Multi-Container Pod (Sidecar Pattern)

Example:

```yaml
spec:
  containers:
    - name: app
      image: myapp:1.0
    - name: log-agent
      image: fluentd
```

Both share:

* Same IP
* Same localhost
* Same volumes (if mounted)

---

# 🧠 5️⃣ Shared Network Namespace

Inside pod:

Containers share same IP.

If pod IP = 10.0.0.15

All containers inside pod use that IP.

They communicate via:

```
localhost
```

Example:

App container calls:

```
http://localhost:9090
```

Sidecar listens on 9090.

Very important concept.

---

# 🧠 6️⃣ Pod Networking Rule

Each Pod gets:

* Unique IP
* Flat network model
* No NAT inside cluster

Pods can directly talk to each other.

---

# 🧠 7️⃣ Pod Lifecycle Phases

Pod phases:

1. Pending
2. Running
3. Succeeded
4. Failed
5. Unknown

Check:

```bash
kubectl get pods
```

Example:

```
myapp-12345   Running
```

---

# 🧠 8️⃣ Pod Creation Flow

When pod created:

1. Scheduler assigns node
2. Kubelet pulls image
3. Containers start
4. Readiness probe runs
5. Pod marked Ready

If image pull fails → stays in Pending.

---

# 🧠 9️⃣ Pod Restart Behavior

Important:

Pod is not restarted.

Container inside pod is restarted.

If container crashes:

* Restarted based on policy
* Pod stays same

If pod deleted:
New pod created (new IP).

---

# 🧠 🔟 Pod IP Changes

Pods are ephemeral.

If pod recreated:

* New IP assigned

Never depend on pod IP.

Always use Service.

---

# 🧠 1️⃣1️⃣ Volumes in Pod

Containers inside pod can share volume.

Example:

```yaml
volumes:
  - name: shared-data
    emptyDir: {}

containers:
  - name: app
    volumeMounts:
      - mountPath: /data
        name: shared-data
```

emptyDir:

* Exists as long as pod exists
* Deleted when pod removed

---

# 🧠 1️⃣2️⃣ Init Containers

Used for setup tasks before main container starts.

Example:

```yaml
initContainers:
  - name: init-db
    image: busybox
    command: ["sh", "-c", "echo initializing"]
```

Init container must complete before app starts.

Used for:

* DB migration
* Config generation

---

# 🧠 1️⃣3️⃣ Pod Termination Flow

When pod deleted:

1. Marked Terminating
2. Readiness fails
3. SIGTERM sent to container
4. Wait for terminationGracePeriod
5. SIGKILL if needed

Pod fully removed.

---

# 🧠 1️⃣4️⃣ Real Interview Scenarios

---

### Scenario 1

Why not deploy container directly?

Answer:

Pod groups containers with shared networking and storage, allowing sidecars and tightly coupled processes.

---

### Scenario 2

Can two containers inside same pod use different IP?

No.
They share same IP and network namespace.

---

### Scenario 3

Why pod IP not reliable?

Because pods are ephemeral and recreated with new IP.

Use Service abstraction.

---

# 🧠 1️⃣5️⃣ Senior-Level Explanation Structure

If asked:

“What is a Pod?”

Answer:

A Pod is the smallest deployable unit in Kubernetes that encapsulates one or more containers sharing the same network namespace and storage volumes. It represents a single instance of a running application.

Calm. Structured. Mature.

---

# 🎯 STEP 3 REVISION CHECKLIST

You must confidently explain:

✔ Why Pod exists
✔ Pod vs container difference
✔ Shared network namespace
✔ Sidecar pattern
✔ Pod lifecycle phases
✔ Container restart vs pod recreation
✔ Pod IP ephemerality
✔ Volumes & emptyDir
✔ Init containers

If you can explain this clearly →
Pod-level maturity achieved.

---

When ready, type:

**4**

Next file:

📁 `04-deployment-replicaset-reconciliation.md`
(Deployment deep dive + ReplicaSet + reconciliation loop + version history + rollout strategy internals)
