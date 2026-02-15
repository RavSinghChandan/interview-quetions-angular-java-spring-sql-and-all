Good. Now we move to the networking abstraction that makes microservices possible.

---

# 📁 File: `05-service-deep-dive-clusterip-nodeport-loadbalancer.md`

# 🔥 STEP 5 — Service Deep Dive (ClusterIP + NodePort + LoadBalancer + kube-proxy)

This is critical.

Interviewers ask:

* Why do we need Service?
* What happens when pod IP changes?
* How does load balancing work internally?
* What is kube-proxy?
* Difference between ClusterIP, NodePort, LoadBalancer?

You must answer with system clarity.

---

# 🧠 1️⃣ Why Service Exists

Pods are ephemeral.

If pod crashes → new pod created → new IP.

Example:

Pod IP:

```
10.0.0.15
```

After restart:

```
10.0.0.27
```

If other services depend on pod IP → broken.

Service solves this.

Service provides:

✔ Stable virtual IP
✔ DNS name
✔ Load balancing across pods

---

# 🧠 2️⃣ Basic Service Example

```yaml
apiVersion: v1
kind: Service
metadata:
  name: user-service
spec:
  selector:
    app: user
  ports:
    - port: 80
      targetPort: 8080
  type: ClusterIP
```

Key fields:

* selector → which pods
* port → service port
* targetPort → container port

---

# 🧠 3️⃣ How Service Actually Works

Flow:

```
Client → Service → Pod
```

Service selects pods via labels:

```yaml
selector:
  app: user
```

If 3 pods match → traffic distributed across 3.

---

# 🧠 4️⃣ kube-proxy Role

kube-proxy runs on every node.

It:

* Watches Services
* Configures iptables rules
* Routes traffic to appropriate pods

Load balancing is not magic.
It is iptables rules configured by kube-proxy.

---

# 🧠 5️⃣ ClusterIP (Default)

Default service type.

Accessible:

Only inside cluster.

Example:

```
http://user-service
```

Resolved via internal DNS.

Use case:

Service-to-service communication.

---

# 🧠 6️⃣ NodePort

Exposes service on each node’s IP.

Example:

```yaml
type: NodePort
```

Kubernetes assigns port like:

```
30007
```

Access via:

```
http://node-ip:30007
```

Use case:

Basic external access.

Not ideal for production internet traffic.

---

# 🧠 7️⃣ LoadBalancer

Cloud-managed load balancer created.

Example:

```yaml
type: LoadBalancer
```

Cloud provider:

* AWS ELB
* GCP Load Balancer

Public IP assigned.

Best for exposing APIs externally.

---

# 🧠 8️⃣ Internal DNS Resolution

Inside cluster:

Call service using:

```
http://user-service
```

Full DNS:

```
user-service.default.svc.cluster.local
```

But short name works inside same namespace.

Important interview point.

---

# 🧠 9️⃣ How Load Balancing Works Internally

If 3 pods exist:

* Pod A
* Pod B
* Pod C

Service distributes traffic roughly equally.

kube-proxy uses round-robin at connection level.

Important:

It is L4 load balancing.

Not advanced traffic splitting.

---

# 🧠 🔟 What Happens If One Pod Fails?

If readiness fails:

* Pod removed from service endpoints
* kube-proxy stops routing traffic to it

Automatic resilience.

---

# 🧠 1️⃣1️⃣ Endpoints Object

Service maintains endpoints list.

Check:

```bash
kubectl get endpoints user-service
```

Shows pod IPs currently serving traffic.

If no endpoints → service unreachable.

---

# 🧠 1️⃣2️⃣ Common Service Misconfiguration

---

### Scenario 1 — Service not routing traffic

Possible:

* Label mismatch
* Wrong targetPort
* Pod not ready

---

### Scenario 2 — Service accessible internally but not externally

Likely:

* Using ClusterIP instead of LoadBalancer
* Security group blocking traffic

---

### Scenario 3 — High latency

Possible:

* Too few replicas
* Network bottleneck
* Cross-node traffic overhead

---

# 🧠 1️⃣3️⃣ Headless Service (Advanced Concept)

Set:

```yaml
clusterIP: None
```

Used for:

* StatefulSets
* Direct pod-to-pod DNS

No load balancing.
DNS returns individual pod IPs.

Senior awareness point.

---

# 🧠 1️⃣4️⃣ Service vs Ingress

Service:

Routes traffic to pods.

Ingress:

Routes HTTP traffic based on:

* Host
* Path

Ingress sits above Service.

Flow:

```
User → Ingress → Service → Pod
```

---

# 🧠 1️⃣5️⃣ Senior-Level Answer Structure

If asked:

“How does service load balance traffic?”

Answer:

* Service selects pods using labels
* kube-proxy configures iptables rules
* Requests routed to pod IPs
* Readiness probe ensures only healthy pods receive traffic

Clear. Structured. Mature.

---

# 🎯 STEP 5 REVISION CHECKLIST

You must confidently explain:

✔ Why Service needed
✔ Stable virtual IP
✔ Selector mechanism
✔ kube-proxy role
✔ ClusterIP vs NodePort vs LoadBalancer
✔ Endpoints object
✔ Internal DNS
✔ Headless service
✔ Service vs Ingress

If you can explain this clearly →
Service-level maturity achieved.

---

When ready, type:

**6**

Next file:

📁 `06-request-flow-load-balancing-internals.md`
(Full traffic path deep dive — User → LB → Ingress → Service → Pod + packet flow explanation + scaling interaction)
