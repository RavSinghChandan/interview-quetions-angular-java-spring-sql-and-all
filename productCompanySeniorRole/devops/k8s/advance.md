# KUBERNETES — LEVEL 5 (ADVANCED)

**Stage Title: “You Become the Engineer Called When Production Clusters Break”**

*(Story continues — now you enter real production engineering. This is where Kubernetes stops being theoretical and starts being mission-critical.)*

---

# 🎯 OBJECTIVE OF THIS LEVEL

After this level you will be able to:

* debug real production cluster failures
* analyze pod crashes
* diagnose networking issues
* fix scheduling problems
* troubleshoot performance bottlenecks
* investigate node failures

This is where you become:

```
Kubernetes User → Production Troubleshooter
```

---

# 1️⃣ REALITY — PRODUCTION CLUSTERS FAIL DIFFERENTLY

Real failures rarely say:

> “Error: Something broke”

Instead they look like:

* pods stuck pending
* service unreachable
* CPU spike
* random restarts
* slow response
* deployment stuck

Advanced engineers don’t guess.

They investigate systematically.

---

# 2️⃣ THE GOLDEN DEBUG RULE

When something fails:

Never restart first.

Always inspect first.

Checklist:

```
kubectl get pods
kubectl describe pod
kubectl logs
kubectl get events
```

This solves most issues.

---

# 3️⃣ POD STUCK IN PENDING

Check:

```
kubectl describe pod podName
```

Look for:

```
FailedScheduling
```

Common causes:

* insufficient CPU
* insufficient memory
* node selector mismatch
* taints
* no nodes available

Pending = scheduler couldn’t place pod.

---

# 4️⃣ POD CRASHING (CRASHLOOPBACKOFF)

Check logs:

```
kubectl logs podName
```

Check previous logs:

```
kubectl logs podName --previous
```

Reasons:

* app crash
* bad config
* missing env variable
* dependency failure

---

# 5️⃣ IMAGE PULL ERRORS

Error:

```
ImagePullBackOff
```

Check:

```
kubectl describe pod
```

Common causes:

* wrong image name
* private registry auth missing
* network issue

Fix secret auth:

```
kubectl create secret docker-registry regcred
```

---

# 6️⃣ SERVICE NOT REACHABLE

Check service:

```
kubectl get svc
```

Check endpoints:

```
kubectl get endpoints
```

If endpoints empty → service not connected to pods.

Cause:

Label mismatch.

---

# 7️⃣ LABEL DEBUGGING

Check pod labels:

```
kubectl get pods --show-labels
```

Check service selector:

```
kubectl describe svc serviceName
```

Labels must match exactly.

---

# 8️⃣ POD RESTARTING RANDOMLY

Check restart count:

```
kubectl get pods
```

Check reason:

```
kubectl describe pod
```

Common causes:

* OOM kill
* failing health check
* crashing process

---

# 9️⃣ MEMORY CRASH DETECTION

If container killed:

Look for:

```
OOMKilled
```

Fix by increasing limits:

```
resources:
  limits:
    memory: "512Mi"
```

---

# 🔟 NODE FAILURE DEBUGGING

Check nodes:

```
kubectl get nodes
```

If node NotReady:

Describe:

```
kubectl describe node nodeName
```

Check conditions:

```
MemoryPressure
DiskPressure
NetworkUnavailable
```

---

# 11️⃣ POD RUNNING BUT APP NOT WORKING

Enter container:

```
kubectl exec -it podName -- bash
```

Test inside:

```
curl localhost:3000
```

If works inside but not outside → networking issue.

---

# 12️⃣ NETWORK DEBUG FLOW

Check DNS:

```
kubectl exec podName -- nslookup serviceName
```

Check connectivity:

```
kubectl exec podName -- ping serviceName
```

Check endpoints:

```
kubectl get endpoints
```

---

# 13️⃣ DEPLOYMENT STUCK

Check rollout:

```
kubectl rollout status deployment/app
```

Describe deployment:

```
kubectl describe deployment app
```

Common causes:

* readiness probe failing
* image pull error
* resource shortage

---

# 14️⃣ READINESS PROBE FAILURES

If readiness fails:

Pod runs but not added to service.

Check probe:

```
kubectl describe pod
```

Fix endpoint path or port.

---

# 15️⃣ RESOURCE BOTTLENECK ANALYSIS

Check usage:

```
kubectl top pods
kubectl top nodes
```

High CPU or memory = scaling needed.

---

# 16️⃣ CLUSTER EVENTS — HIDDEN GOLD

Always check:

```
kubectl get events --sort-by=.metadata.creationTimestamp
```

Events reveal real reason for failures.

---

# 17️⃣ STORAGE FAILURES

If pod stuck mounting volume:

Check:

```
kubectl describe pod
```

Common issues:

* PVC not bound
* storage class missing
* disk full

---

# 18️⃣ REAL INCIDENT DEBUG FLOW (USED BY SENIORS)

When system fails:

Follow this exact order:

```
Pods → Logs → Events → Nodes → Services → Network → Resources
```

Never random debugging.

Always layered approach.

---

# 19️⃣ THE PRODUCTION MINDSET

Beginners restart pods.

Advanced engineers ask:

```
What killed it?
What changed?
What resource exhausted?
What dependency failed?
```

Because Kubernetes failures always have a cause.

---

# 20️⃣ ADVANCED COMPLETION CHECK

You can now:

✔ diagnose pod failures
✔ debug scheduling issues
✔ fix service connectivity
✔ detect memory crashes
✔ analyze node failures
✔ troubleshoot deployments
✔ investigate cluster events
✔ debug real production systems

---

# FINAL LINE

At this point:

> You don’t fear Kubernetes failures.

Because you know how to investigate them.

That is the difference between:

Engineer → Production Engineer.

---

END OF LEVEL 5 — KUBERNETES ADVANCED
