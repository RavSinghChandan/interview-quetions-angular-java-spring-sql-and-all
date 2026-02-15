Good. We now go deep into cluster architecture.

---

# 📁 File: `02-kubernetes-architecture-control-plane.md`

# 🔥 STEP 2 — Kubernetes Architecture (Control Plane vs Worker Nodes)

This is where interviewers test real understanding.

They may ask:

* What is control plane?
* What does scheduler do?
* What is etcd?
* What runs on worker node?
* How does a pod actually get created?

You must answer structurally.

---

# 🧠 1️⃣ High-Level Cluster Architecture

A Kubernetes cluster has:

```
Control Plane
+
Worker Nodes
```

Control Plane = Brain
Worker Nodes = Execution layer

---

# 🧠 2️⃣ Control Plane Components

Core components:

* API Server
* Scheduler
* Controller Manager
* etcd (state store)

Each has clear responsibility.

---

# 🧠 3️⃣ API Server (Entry Point of Cluster)

All communication goes through API Server.

When you run:

```bash
kubectl apply -f deployment.yaml
```

Request flow:

kubectl → API Server

API Server:

* Validates request
* Stores state in etcd
* Notifies controllers

Important:
No component talks directly to etcd except API server.

Security boundary.

---

# 🧠 4️⃣ etcd — Cluster State Database

etcd stores:

* Desired state
* Cluster configuration
* Pod definitions
* Secrets
* Services

It is:

* Distributed key-value store
* Highly consistent

If etcd fails → cluster state lost.

Critical component.

---

# 🧠 5️⃣ Scheduler — Pod Placement Logic

Scheduler decides:

“On which node should this pod run?”

It considers:

* Available CPU
* Available memory
* Node constraints
* Affinity rules
* Taints and tolerations

Example:

Pod requests:

```yaml
resources:
  requests:
    cpu: "500m"
```

Scheduler finds node with enough capacity.

If no node fits → pod stays Pending.

---

# 🧠 6️⃣ Controller Manager — Reconciliation Loop

Controller Manager runs controllers:

* Deployment controller
* ReplicaSet controller
* Node controller
* Job controller

Example:

If deployment says:

```yaml
replicas: 3
```

Controller checks:

Current pods = 2

Action:
Create 1 more pod.

This is reconciliation loop.

---

# 🧠 7️⃣ Worker Node Components

Each worker node has:

* Kubelet
* Container runtime (containerd)
* Kube-proxy

Worker node runs actual pods.

---

# 🧠 8️⃣ Kubelet

Kubelet responsibilities:

* Talks to API server
* Ensures containers running
* Executes pod specs
* Reports status back

If pod crashes:
Kubelet restarts container.

---

# 🧠 9️⃣ Container Runtime

Usually:

containerd

Responsible for:

* Pulling images
* Running containers
* Managing lifecycle

Kubernetes does not run containers directly.
Runtime does.

---

# 🧠 🔟 Kube-Proxy

Handles networking:

* Maintains service rules
* Routes traffic to pods
* Implements load balancing

It configures iptables rules.

---

# 🧠 1️⃣1️⃣ Pod Creation Flow (Step-by-Step)

When you apply deployment:

1. kubectl → API Server
2. API server stores in etcd
3. Deployment controller creates ReplicaSet
4. ReplicaSet creates Pod object
5. Scheduler assigns node
6. Kubelet pulls image
7. Container starts
8. Pod becomes Running

Full lifecycle understanding = senior signal.

---

# 🧠 1️⃣2️⃣ What Happens If Node Dies?

Node controller detects heartbeat missing.

After timeout:

* Pods marked lost
* Scheduler reschedules on another node

Self-healing at cluster level.

---

# 🧠 1️⃣3️⃣ API Server Is Single Source of Truth

All components:

* Scheduler
* Controllers
* Kubelet

Watch API server for changes.

Event-driven architecture.

---

# 🧠 1️⃣4️⃣ Real Interview Scenario

Question:

“How does Kubernetes ensure desired state?”

Answer:

* API server stores desired state in etcd
* Controllers compare actual vs desired
* Scheduler assigns pods
* Kubelet executes containers
* Continuous reconciliation maintains state

Clear explanation = strong signal.

---

# 🧠 1️⃣5️⃣ Senior-Level Mental Model

Think of Kubernetes as:

Control Plane → Decision makers
Worker Nodes → Executors

Control Plane never runs business logic.
It only orchestrates.

Workers execute.

---

# 🎯 STEP 2 REVISION CHECKLIST

You must confidently explain:

✔ Control plane vs worker node
✔ API server role
✔ etcd purpose
✔ Scheduler logic
✔ Controller reconciliation loop
✔ Kubelet responsibility
✔ Container runtime role
✔ Pod creation flow
✔ Node failure handling

If you can narrate all clearly →
Architecture foundation strong.

---

When ready, type:

**3**

Next file:

📁 `03-pod-deep-dive-lifecycle-network-storage.md`
(Pod internals + shared networking + shared volumes + pod lifecycle phases deep dive)
