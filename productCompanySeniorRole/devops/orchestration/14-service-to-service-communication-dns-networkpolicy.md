Excellent.
Now we move into **network security + internal communication maturity**.

This is where senior engineers separate themselves.

---

# 📁 File: `14-service-to-service-communication-dns-networkpolicy.md`

# 🔥 STEP 14 — Service-to-Service Communication + DNS + NetworkPolicy

(Cluster Networking + Security Boundaries — Senior Level)

Interviewers may ask:

* How do services talk to each other?
* How does DNS work inside Kubernetes?
* Can one pod call another directly?
* How do you restrict traffic?
* What is NetworkPolicy?

You must answer structurally.

---

# 🧠 1️⃣ Internal DNS in Kubernetes

Every service gets DNS entry automatically.

Format:

```
service-name.namespace.svc.cluster.local
```

Short form (same namespace):

```
http://user-service
```

KubeDNS / CoreDNS handles resolution.

Check DNS inside pod:

```bash
kubectl exec -it mypod -- nslookup user-service
```

---

# 🧠 2️⃣ Service-to-Service Call Example

Service A calls Service B:

```java
RestTemplate restTemplate = new RestTemplate();
String response = restTemplate.getForObject(
    "http://payment-service/api/pay",
    String.class
);
```

No IP hardcoding.

DNS resolves to ClusterIP.

Service load balances across pods.

---

# 🧠 3️⃣ Cross-Namespace Communication

If services in different namespace:

Use full DNS:

```
http://payment-service.finance.svc.cluster.local
```

Or shorter:

```
http://payment-service.finance
```

Namespace isolation exists logically, not physically.

---

# 🧠 4️⃣ Can Pods Call Pod IP Directly?

Technically yes.

But not recommended.

Why?

* Pod IP changes
* No load balancing
* Breaks resilience

Always use Service.

---

# 🧠 5️⃣ How kube-proxy Routes Traffic

When Service called:

1. DNS resolves to ClusterIP
2. kube-proxy iptables rules forward to one of pod IPs
3. Connection established

Service load balancing is L4.

No advanced routing.

---

# 🧠 6️⃣ Default Network Behavior

By default:

All pods can talk to all pods.

Cluster is flat network.

No isolation unless defined.

Security risk in large clusters.

---

# 🧠 7️⃣ NetworkPolicy Introduction

NetworkPolicy controls:

* Which pods can talk to which
* Which ports allowed
* Ingress and egress rules

Without NetworkPolicy:
Open communication.

---

# 🧠 8️⃣ Basic NetworkPolicy Example

Allow only frontend to call backend:

```yaml
apiVersion: networking.k8s.io/v1
kind: NetworkPolicy
metadata:
  name: allow-frontend
spec:
  podSelector:
    matchLabels:
      app: backend
  ingress:
    - from:
        - podSelector:
            matchLabels:
              app: frontend
```

Now only frontend pods can reach backend.

---

# 🧠 9️⃣ Deny All Policy

Block all incoming traffic:

```yaml
apiVersion: networking.k8s.io/v1
kind: NetworkPolicy
metadata:
  name: deny-all
spec:
  podSelector: {}
  policyTypes:
    - Ingress
```

Everything blocked unless explicitly allowed.

Zero-trust style networking.

---

# 🧠 🔟 Egress Policy Example

Restrict outbound traffic:

```yaml
egress:
  - to:
      - ipBlock:
          cidr: 10.0.0.0/16
```

Prevent pods from calling internet.

Useful for security compliance.

---

# 🧠 1️⃣1️⃣ Real Interview Scenario

Question:

“How do you secure communication between microservices?”

Strong answer:

* Use NetworkPolicy
* Limit ingress/egress
* Restrict namespace communication
* Use TLS between services (mTLS if service mesh)

Shows security awareness.

---

# 🧠 1️⃣2️⃣ What If NetworkPolicy Not Working?

Common reasons:

* CNI plugin does not support NetworkPolicy
* Incorrect label selector
* Namespace mismatch

NetworkPolicy works only if network plugin supports it (e.g., Calico, Cilium).

Senior awareness point.

---

# 🧠 1️⃣3️⃣ Service Mesh (Advanced Mention)

At high scale:

Use service mesh:

* Istio
* Linkerd

Provides:

* mTLS
* Traffic shaping
* Observability
* Retries

Not mandatory to implement.
But must know concept.

---

# 🧠 1️⃣4️⃣ Internal vs External Traffic

Internal:

Service → Service (ClusterIP)

External:

LoadBalancer → Ingress → Service

NetworkPolicy applies at pod level.

Ingress controls HTTP routing.
NetworkPolicy controls network-level access.

---

# 🧠 1️⃣5️⃣ Senior-Level Mental Model

DNS = service discovery
Service = load balancing
NetworkPolicy = traffic firewall

Kubernetes networking by default is open.
Security must be enforced intentionally.

---

# 🎯 STEP 14 REVISION CHECKLIST

You must confidently explain:

✔ Internal DNS format
✔ Service-to-service calls
✔ Cross-namespace communication
✔ Why not call pod IP
✔ kube-proxy role
✔ Default open networking
✔ NetworkPolicy ingress rules
✔ Egress control
✔ Service mesh concept

If you can explain all clearly →
Networking + security maturity achieved.

---

When ready, type:

**15**

Next file:

📁 `15-observability-monitoring-logging-production-readiness.md`
(Logs + Metrics + Tracing + Alerts + SLO thinking — final orchestration maturity layer)
