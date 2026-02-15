Good. Now we move to networking layer.

---

# 📁 File: `10-networking-service-communication.md`

# 🔥 STEP 10 — Container Networking + Service Communication + DNS + Load Balancing

(Senior Backend Cloud Networking Basics)

This is where backend meets infrastructure.

Interviewers may ask:

* How do containers communicate?
* What is bridge network?
* How do services talk inside Kubernetes?
* How does internal DNS work?
* How does load balancing happen?

You must answer clearly.

---

# 🧠 1️⃣ Docker Networking Basics

List networks:

```bash
docker network ls
```

Default network:

```
bridge
```

Inspect bridge:

```bash
docker network inspect bridge
```

---

# 🧠 2️⃣ Bridge Network (Default)

When container runs:

```bash
docker run -d --name app1 myapp
docker run -d --name app2 myapp
```

Both connected to bridge network.

Each container gets:

* Private IP
* Isolated network namespace

Check IP:

```bash
docker inspect app1
```

Look under:

```
NetworkSettings
```

---

# 🧠 3️⃣ Port Mapping

Run container:

```bash
docker run -p 8080:8080 myapp
```

Format:

```
HostPort:ContainerPort
```

Meaning:

* Host port 8080
* Mapped to container port 8080

Access from browser:

```
http://localhost:8080
```

Without -p → container not accessible externally.

---

# 🧠 4️⃣ Custom Docker Network (Service-to-Service Communication)

Create network:

```bash
docker network create mynet
```

Run services:

```bash
docker run -d --network mynet --name user-service user-app
docker run -d --network mynet --name order-service order-app
```

Inside order-service, call:

```
http://user-service:8080
```

Docker provides internal DNS.

No need for IP.

Important concept:
Service name = hostname.

---

# 🧠 5️⃣ Host Network Mode (Rare Use)

Run container:

```bash
docker run --network host myapp
```

Container shares host network stack.

Not common in production microservices.

---

# 🧠 6️⃣ Kubernetes Networking Model (High-Level)

Kubernetes networking rule:

> Every pod gets its own IP.

No port mapping needed inside cluster.

Pods communicate directly via IP.

---

# 🧠 7️⃣ Kubernetes Service (Critical Concept)

Pods are ephemeral.

You don’t call pod IP directly.

You create a Service:

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
```

Now call:

```
http://user-service
```

Service load balances across pods.

---

# 🧠 8️⃣ Service Discovery (Internal DNS)

Inside cluster:

Call:

```
http://user-service.default.svc.cluster.local
```

But usually:

```
http://user-service
```

Kubernetes DNS resolves service name.

Important interview point.

---

# 🧠 9️⃣ Load Balancing Types in Kubernetes

### 1️⃣ ClusterIP (default)

Internal communication only.

---

### 2️⃣ NodePort

Exposes service on node IP + port.

---

### 3️⃣ LoadBalancer

Cloud provider creates external load balancer.

Example:

```yaml
type: LoadBalancer
```

Used in production for public APIs.

---

# 🧠 🔟 Ingress (HTTP Routing Layer)

Ingress allows:

* Path-based routing
* Domain-based routing

Example:

```yaml
rules:
  - host: api.myapp.com
    http:
      paths:
        - path: /users
          backend:
            service:
              name: user-service
              port:
                number: 80
```

Advanced traffic control.

---

# 🧠 1️⃣1️⃣ Internal vs External Communication

Internal:

Service-to-service via ClusterIP.

External:

LoadBalancer or Ingress.

Security best practice:
Internal services not exposed publicly.

---

# 🧠 1️⃣2️⃣ Debugging Networking Issues

If service not reachable:

Check:

```bash
kubectl get pods
kubectl get services
kubectl describe service user-service
```

Inside pod:

```bash
kubectl exec -it mypod -- curl http://user-service
```

---

# 🧠 1️⃣3️⃣ Common Failure Scenarios

---

### Scenario 1 — Service not reachable

Likely:

* Wrong selector labels
* Wrong port mapping
* Pod not ready
* DNS issue

---

### Scenario 2 — High latency between services

Possible:

* Network congestion
* Service misconfiguration
* Too many hops (Ingress → Service → Pod)

---

### Scenario 3 — Works locally, fails in cluster

Likely:

* Using localhost instead of service name
* Hardcoded IP
* Port mismatch

---

# 🧠 1️⃣4️⃣ Backend Best Practice

Never call:

```
http://localhost:8080
```

Inside container for another service.

Use:

```
http://service-name
```

Containers are isolated.

---

# 🧠 1️⃣5️⃣ Senior-Level Answer Structure

If asked:

“How do microservices communicate in Kubernetes?”

Answer:

* Each pod has its own IP
* Services provide stable virtual IP
* DNS resolves service name
* Service load balances across pods
* Ingress handles external routing

Structured. Clear.

---

# 🎯 STEP 10 REVISION CHECKLIST

You must confidently explain:

✔ Bridge network
✔ Port mapping
✔ Custom Docker network
✔ Internal DNS
✔ Kubernetes Service
✔ ClusterIP vs NodePort vs LoadBalancer
✔ Ingress basics
✔ Service discovery
✔ Networking debugging

If you can explain all calmly →
Networking foundation strong.

---

When ready, type:

**11**

Next file:

📁 `11-rolling-deployment-strategies.md`
(Rolling update + Blue-Green + Canary + rollback + production traffic shifting deep dive)
