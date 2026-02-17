# KUBERNETES — LEVEL 2 (BASIC PRACTICAL)

**Stage Title: “You Deploy Your First Real App into a Cluster”**

*(Now theory ends. You start interacting with a real Kubernetes system like a real engineer.)*

---

# 🎯 OBJECTIVE OF THIS LEVEL

After this stage you will be able to:

* connect to cluster
* deploy applications
* inspect pods
* expose services
* scale apps
* debug pods

You move from:

```
Understanding Kubernetes → Using Kubernetes
```

Everything below is practical and command-driven.

---

# 1️⃣ VERIFY CLUSTER CONNECTION

Check cluster:

```
kubectl cluster-info
```

Check nodes:

```
kubectl get nodes
```

Expected output:

```
NAME       STATUS   ROLES
node-1     Ready    worker
node-2     Ready    worker
```

If nodes are Ready → cluster working.

---

# 2️⃣ SEE EVERYTHING RUNNING

```
kubectl get all
```

This shows:

* pods
* services
* deployments
* replicasets

---

# 3️⃣ RUN YOUR FIRST APPLICATION

Deploy nginx:

```
kubectl create deployment web --image=nginx
```

Check pods:

```
kubectl get pods
```

You’ll see pod name like:

```
web-7d9f7c6c4d-abc12
```

This is real running container inside cluster.

---

# 4️⃣ WATCH POD START LIVE

```
kubectl get pods -w
```

Shows lifecycle:

```
Pending → ContainerCreating → Running
```

---

# 5️⃣ UNDERSTAND WHAT JUST HAPPENED

When you ran:

```
kubectl create deployment web
```

Kubernetes:

```
created deployment
created replicaset
created pod
scheduled pod
started container
```

You ran ONE command.

Kubernetes did 5 operations.

---

# 6️⃣ SEE DEPLOYMENT OBJECT

```
kubectl get deployments
```

Details:

```
kubectl describe deployment web
```

Shows:

* replicas
* image
* events
* rollout status

---

# 7️⃣ SCALE APPLICATION

Scale to 3 pods:

```
kubectl scale deployment web --replicas=3
```

Check:

```
kubectl get pods
```

You’ll see 3 pods running.

You didn’t create them.

Kubernetes did.

---

# 8️⃣ DELETE A POD (SELF-HEAL DEMO)

Delete one pod:

```
kubectl delete pod podName
```

Watch:

```
kubectl get pods -w
```

Kubernetes instantly creates new one.

This proves:

> Kubernetes self-heals.

---

# 9️⃣ EXPOSE APPLICATION (MAKE ACCESSIBLE)

Create service:

```
kubectl expose deployment web --port=80 --type=NodePort
```

Check service:

```
kubectl get svc
```

Output:

```
web   NodePort   80:30007
```

Access app:

```
http://nodeIP:30007
```

---

# 🔟 GET POD DETAILS

```
kubectl describe pod podName
```

Shows:

* container info
* IP address
* node location
* restart count
* events

This is primary debugging command.

---

# 11️⃣ VIEW LOGS

```
kubectl logs podName
```

Follow logs:

```
kubectl logs -f podName
```

---

# 12️⃣ EXEC INTO POD

```
kubectl exec -it podName -- bash
```

Now you are inside container.

Check files:

```
ls
```

Check processes:

```
ps aux
```

---

# 13️⃣ DELETE DEPLOYMENT

```
kubectl delete deployment web
```

Pods disappear automatically.

Because deployment controlled them.

---

# 14️⃣ DECLARATIVE DEPLOYMENT (REAL WAY)

Create YAML file:

```
nano app.yaml
```

Paste:

```
apiVersion: apps/v1
kind: Deployment
metadata:
  name: web
spec:
  replicas: 2
  selector:
    matchLabels:
      app: web
  template:
    metadata:
      labels:
        app: web
    spec:
      containers:
      - name: nginx
        image: nginx
        ports:
        - containerPort: 80
```

Apply:

```
kubectl apply -f app.yaml
```

---

# 15️⃣ WHY YAML IS BETTER

Commands are temporary.

YAML is permanent.

YAML allows:

* version control
* reproducibility
* automation

Real engineers use YAML.

---

# 16️⃣ UPDATE DEPLOYMENT

Change image:

```
kubectl set image deployment/web nginx=nginx:1.25
```

Watch rollout:

```
kubectl rollout status deployment/web
```

---

# 17️⃣ ROLL BACK DEPLOYMENT

Undo update:

```
kubectl rollout undo deployment/web
```

This is production-grade safety feature.

---

# 18️⃣ CHECK RESOURCE USAGE

```
kubectl top pods
```

Shows:

* CPU
* memory

Used for performance debugging.

---

# 19️⃣ DEBUG FAILING POD

If pod fails:

Check:

```
kubectl describe pod name
kubectl logs name
```

These two commands solve most issues.

---

# 20️⃣ REAL ENGINEER WORKFLOW

Daily Kubernetes workflow:

```
kubectl apply
kubectl get pods
kubectl describe
kubectl logs
kubectl exec
kubectl scale
kubectl rollout
```

Memorize this loop.

---

# 🏁 BASIC COMPLETION CHECK

You can now:

✔ deploy apps
✔ scale apps
✔ inspect pods
✔ debug logs
✔ expose services
✔ update deployments
✔ roll back releases
✔ exec into containers

---

# FINAL LINE

At this point:

> Kubernetes is no longer theory.

You can deploy real apps in a cluster like a real engineer.

---

END OF LEVEL 2 — KUBERNETES BASIC
