# KUBERNETES — LEVEL 3 (INTERMEDIATE PRACTICAL)

**Stage Title: “You Run Real Systems — Not Just Pods”**

*(Now you move from running single apps to running real distributed systems like production engineers.)*

---

# 🎯 OBJECTIVE OF THIS LEVEL

After this level you will be able to:

* run multi-service applications
* connect services internally
* manage configuration & secrets
* handle storage
* control networking
* debug real cluster systems

You move from:

```
Running Pods → Operating Systems
```

This is where Kubernetes starts feeling like infrastructure control.

---

# 1️⃣ REALITY CHECK — REAL APPS HAVE MANY PARTS

Real applications are never one container.

Typical system:

```
Frontend
Backend API
Database
Cache
Queue
Worker
```

Each runs separately.

Kubernetes runs them as independent pods.

---

# 2️⃣ NAMESPACE — ENVIRONMENT ISOLATION

Create namespace:

```
kubectl create namespace dev
```

Use namespace:

```
kubectl get pods -n dev
```

Deploy into namespace:

```
kubectl apply -f app.yaml -n dev
```

Namespaces separate environments:

```
dev
staging
prod
```

---

# 3️⃣ SERVICE TYPES (CRITICAL CONCEPT)

Kubernetes networking uses services.

Types:

```
ClusterIP → internal access
NodePort → external access
LoadBalancer → cloud access
```

Create internal service:

```
kubectl expose deployment backend --port=5000 --type=ClusterIP
```

---

# 4️⃣ INTERNAL SERVICE DISCOVERY

Pods don’t use IPs.

They use service names.

Example connection string:

```
http://backend:5000
```

Kubernetes DNS resolves it automatically.

Test DNS:

```
kubectl exec podName -- nslookup backend
```

---

# 5️⃣ CONFIGMAP — EXTERNAL CONFIGURATION

Create config:

```
kubectl create configmap app-config \
--from-literal=ENV=prod
```

Use in pod YAML:

```
env:
- name: ENV
  valueFrom:
    configMapKeyRef:
      name: app-config
      key: ENV
```

Why ConfigMaps?

So config can change without rebuilding image.

---

# 6️⃣ SECRETS — SENSITIVE DATA

Create secret:

```
kubectl create secret generic db-secret \
--from-literal=password=1234
```

Use secret:

```
env:
- name: DB_PASS
  valueFrom:
    secretKeyRef:
      name: db-secret
      key: password
```

Secrets are base64 encoded and stored securely.

---

# 7️⃣ PERSISTENT STORAGE

Pods are temporary.

Storage must persist.

Create Persistent Volume Claim:

```
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: data-pvc
spec:
  accessModes:
    - ReadWriteOnce
  resources:
    requests:
      storage: 1Gi
```

Apply:

```
kubectl apply -f pvc.yaml
```

Mount into pod:

```
volumeMounts:
- mountPath: /data
  name: storage

volumes:
- name: storage
  persistentVolumeClaim:
    claimName: data-pvc
```

---

# 8️⃣ MULTI-CONTAINER POD

Example pod with sidecar:

```
containers:
- name: app
  image: node
- name: logger
  image: busybox
```

Both containers share:

* localhost
* filesystem
* lifecycle

Used for:

* logging
* proxies
* monitoring agents

---

# 9️⃣ RESOURCE LIMITS (IMPORTANT)

Define limits:

```
resources:
  requests:
    memory: "128Mi"
    cpu: "250m"
  limits:
    memory: "256Mi"
    cpu: "500m"
```

Why?

Without limits → one pod can crash node.

---

# 🔟 PROBES — HEALTH CHECKS

Readiness probe:

```
readinessProbe:
  httpGet:
    path: /
    port: 80
```

Liveness probe:

```
livenessProbe:
  httpGet:
    path: /
    port: 80
```

Difference:

```
Readiness → ready to receive traffic
Liveness → alive or dead
```

---

# 11️⃣ ROLLING UPDATES

Update image:

```
kubectl set image deployment/app app=nginx:1.25
```

Kubernetes replaces pods gradually:

```
old pod dies → new pod starts
```

Zero downtime deployment.

---

# 12️⃣ ROLLBACK SYSTEM

Check history:

```
kubectl rollout history deployment/app
```

Rollback:

```
kubectl rollout undo deployment/app
```

Production-safe deployments.

---

# 13️⃣ JOBS — RUN ONCE TASKS

Example job YAML:

```
kind: Job
spec:
  template:
    spec:
      containers:
      - name: job
        image: busybox
        command: ["echo","hello"]
      restartPolicy: Never
```

Used for:

* migrations
* batch tasks
* cron jobs

---

# 14️⃣ CRONJOBS — SCHEDULED TASKS

```
schedule: "*/5 * * * *"
```

Runs every 5 minutes.

Used for:

* backups
* cleanup
* reports

---

# 15️⃣ DAEMONSETS — ONE POD PER NODE

Example:

```
kind: DaemonSet
```

Runs on every node.

Used for:

* monitoring agents
* logging agents
* security scanners

---

# 16️⃣ DEBUGGING REAL SYSTEM

Real debugging flow:

```
kubectl get pods
kubectl describe pod
kubectl logs
kubectl get events
```

Events show scheduling failures.

---

# 17️⃣ NODE FAILURE SIMULATION

Drain node:

```
kubectl drain nodeName
```

Kubernetes moves pods automatically.

Self-healing cluster in action.

---

# 18️⃣ RESOURCE MONITORING

```
kubectl top nodes
kubectl top pods
```

Shows real usage.

Used to detect bottlenecks.

---

# 19️⃣ REAL SYSTEM ARCHITECTURE YOU CAN NOW RUN

You can now deploy locally:

```
Frontend
Backend
Database
Cache
Worker
Monitoring
```

All inside cluster.

That is real distributed system.

---

# 20️⃣ THE INTERMEDIATE MENTAL MODEL

If you remember one thing:

```
Pods run apps
Services connect apps
ConfigMaps configure apps
Volumes store data
Controllers manage apps
```

That is Kubernetes.

---

# 🏁 INTERMEDIATE COMPLETION CHECK

You can now:

✔ run multi-service systems
✔ configure apps dynamically
✔ manage secrets
✔ persist data
✔ scale systems
✔ deploy safely
✔ debug failures
✔ manage workloads

---

# FINAL LINE

At this point:

> You’re not deploying containers anymore.

You’re running distributed systems.

That’s real Kubernetes skill.

---

END OF LEVEL 3 — KUBERNETES INTERMEDIATE
