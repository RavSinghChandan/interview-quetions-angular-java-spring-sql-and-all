# KUBERNETES — LEVEL 4 (INTERMEDIATE+)

**Stage Title: “You See Inside the Cluster — How Kubernetes Actually Works”**

*(Story continues — now you stop using Kubernetes as a tool and start understanding how it works internally as a distributed system.)*

---

# 🎯 OBJECTIVE OF THIS LEVEL

After this stage you will:

* understand Kubernetes internals
* know how scheduling decisions happen
* understand control loops
* understand cluster architecture deeply
* debug cluster-level failures
* think like a platform engineer

This level transforms you from:

```
Kubernetes User → Kubernetes Systems Engineer
```

---

# 1️⃣ THE BIG REALIZATION

Kubernetes is not a deployment tool.

Kubernetes is:

> a distributed control system.

It constantly watches cluster state and fixes problems.

It behaves like a thermostat:

```
desired = 3 pods
actual = 2 pods
→ create 1 pod
```

This loop runs continuously.

---

# 2️⃣ CONTROL LOOP — THE HEART OF KUBERNETES

Every controller runs:

```
observe → compare → act
```

Example:

Deployment controller loop:

```
check replicas
if < desired → create pods
if > desired → delete pods
```

This loop never stops.

---

# 3️⃣ MASTER NODE INTERNAL ARCHITECTURE

Master node components:

```
API Server
Scheduler
Controller Manager
etcd
```

Each is independent service.

---

## API SERVER — FRONT DOOR

Everything goes through API server.

Command:

```
kubectl get pods
```

Actually becomes:

```
kubectl → API request → API server → response
```

API server is Kubernetes gateway.

---

## ETCD — CLUSTER DATABASE

etcd stores cluster state:

```
pods
nodes
configs
secrets
deployments
```

Check etcd objects:

```
kubectl get pods -o yaml
```

This data comes from etcd.

etcd = single source of truth.

---

## SCHEDULER — DECISION ENGINE

Scheduler decides:

> which node runs which pod.

It checks:

```
CPU availability
memory availability
node labels
taints
affinity rules
```

Scheduler never runs containers.

It only assigns location.

---

## CONTROLLER MANAGER — MAINTENANCE BRAIN

Controllers enforce state.

Types:

```
Replica controller
Node controller
Job controller
Endpoint controller
```

Each controller ensures something stays correct.

---

# 4️⃣ WORKER NODE INTERNALS

Worker nodes run:

```
kubelet
container runtime
kube-proxy
```

---

## KUBELET — NODE AGENT

kubelet receives instructions:

```
Run this pod
Stop this pod
Restart this pod
```

It talks to API server continuously.

Check kubelet logs:

```
journalctl -u kubelet
```

---

## CONTAINER RUNTIME

Runtime actually runs containers.

Examples:

```
containerd
CRI-O
```

Docker is not required anymore.

---

## KUBE-PROXY — NETWORK BRAIN

kube-proxy manages networking rules.

It creates:

```
iptables rules
virtual IPs
service routing
```

That’s how services work.

---

# 5️⃣ HOW A POD ACTUALLY STARTS

When you deploy pod:

```
kubectl apply -f pod.yaml
```

Real flow:

```
API server saves spec
Scheduler picks node
API updates assignment
kubelet sees assignment
kubelet pulls image
runtime starts container
kubelet reports status
```

Understanding this sequence is crucial.

---

# 6️⃣ WHY POD SOMETIMES STAYS PENDING

If pod stuck in Pending:

Check:

```
kubectl describe pod name
```

Possible reasons:

```
not enough CPU
not enough memory
node taint
node selector mismatch
no nodes available
```

Pending always means scheduling failure.

---

# 7️⃣ NODE SELECTION LOGIC

You can control placement.

Label node:

```
kubectl label nodes node1 type=backend
```

Schedule pod only there:

```
nodeSelector:
  type: backend
```

This is scheduling constraint.

---

# 8️⃣ TAINTS AND TOLERATIONS

Taints block pods from nodes.

Add taint:

```
kubectl taint nodes node1 gpu=true:NoSchedule
```

Pod must tolerate it:

```
tolerations:
- key: "gpu"
```

Used for:

* dedicated nodes
* special hardware
* isolation

---

# 9️⃣ AFFINITY RULES (ADVANCED SCHEDULING)

Control placement logic.

Example:

```
podAffinity
podAntiAffinity
nodeAffinity
```

Used for:

* high availability
* load spreading
* failure isolation

---

# 🔟 WHY KUBERNETES IS SELF-HEALING

Because controllers constantly compare:

```
desired state
actual state
```

If mismatch → fix.

Self-healing is not magic.

It’s continuous reconciliation loops.

---

# 11️⃣ EVENTS — THE MOST POWERFUL DEBUG TOOL

Check cluster events:

```
kubectl get events
```

Shows:

* scheduling failures
* image pull errors
* crashes
* permission issues

Always check events when debugging.

---

# 12️⃣ RESOURCE SCHEDULING SCIENCE

Scheduler calculates node fitness score.

It evaluates:

```
available CPU
available memory
node load
pod priority
constraints
```

Then selects best node.

It is algorithmic decision-making.

---

# 13️⃣ WHAT HAPPENS IF NODE DIES

If node fails:

```
node controller detects failure
pods marked lost
scheduler reschedules pods
```

System heals automatically.

---

# 14️⃣ WHY KUBERNETES IS EVENT-DRIVEN

Every change triggers event.

Example:

```
new deployment created → scheduler triggered
pod crashed → controller triggered
node added → scheduler triggered
```

Cluster reacts to events instantly.

---

# 15️⃣ DECLARATIVE ENGINE INSIGHT

You don’t command Kubernetes.

You describe desired system.

Kubernetes calculates steps needed.

It’s not imperative.

It’s declarative.

---

# 16️⃣ INTERNAL MENTAL MODEL

If you remember one thing:

```
API Server = brain entry
etcd = memory
Scheduler = decision maker
Controller = fixer
Kubelet = executor
```

That is Kubernetes.

---

# 17️⃣ WHY UNDERSTANDING THIS LEVEL IS RARE

Most engineers know:

```
kubectl commands
```

Few know:

```
how cluster thinks
```

Interviews for senior roles test internals.

Not commands.

---

# 18️⃣ REAL ENGINEER DEBUG QUESTIONS

Experts ask:

```
Did scheduler assign node?
Did kubelet receive spec?
Did runtime start container?
Did network route traffic?
```

They debug layer by layer.

---

# 19️⃣ WHAT MOST PEOPLE NEVER REALIZE

Kubernetes is basically:

> a distributed state machine.

It constantly moves system toward desired configuration.

---

# 20️⃣ COMPLETION CHECK

You now understand:

✔ cluster architecture
✔ scheduling logic
✔ controller loops
✔ kubelet role
✔ runtime role
✔ networking internals
✔ why pods fail scheduling
✔ how self-healing works

---

# FINAL LINE

At this stage:

> Kubernetes is no longer a mystery system.

It is a machine whose logic you understand.

And machines whose logic you understand…

can be controlled.

---

END OF LEVEL 4 — KUBERNETES INTERMEDIATE+
