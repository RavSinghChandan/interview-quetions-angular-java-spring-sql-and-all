Good. Now we go deep into configuration maturity inside Kubernetes.

This is not just “ConfigMap stores config.”

This is production-level configuration strategy.

---

# 📁 File: `10-configmap-secret-advanced-usage.md`

# 🔥 STEP 10 — ConfigMap & Secret (Advanced Usage + Security + Rotation)

Interviewers may ask:

* How do you manage config in Kubernetes?
* Difference between ConfigMap and Secret?
* How do you rotate secrets?
* Env injection vs volume mount?
* Is Kubernetes Secret encrypted?

You must answer precisely.

---

# 🧠 1️⃣ Why External Configuration Matters

Golden rule:

Build once → Deploy everywhere.

Container image must be identical across:

* Dev
* Staging
* Prod

Only configuration changes.

Kubernetes provides:

* ConfigMap (non-sensitive)
* Secret (sensitive)

---

# 🧠 2️⃣ ConfigMap Basics

Create ConfigMap:

```bash
kubectl create configmap app-config \
  --from-literal=LOG_LEVEL=INFO \
  --from-literal=FEATURE_X=true
```

Or YAML:

```yaml
apiVersion: v1
kind: ConfigMap
metadata:
  name: app-config
data:
  LOG_LEVEL: "INFO"
  FEATURE_X: "true"
```

---

# 🧠 3️⃣ Inject ConfigMap as Environment Variables

```yaml
envFrom:
  - configMapRef:
      name: app-config
```

Now container gets:

```
LOG_LEVEL=INFO
FEATURE_X=true
```

Simple and common.

---

# 🧠 4️⃣ Inject ConfigMap as Volume (Advanced)

```yaml
volumes:
  - name: config-volume
    configMap:
      name: app-config

containers:
  - name: app
    volumeMounts:
      - name: config-volume
        mountPath: /etc/config
```

Now files created:

```
/etc/config/LOG_LEVEL
/etc/config/FEATURE_X
```

Use case:

When app expects config file, not env vars.

---

# 🧠 5️⃣ ConfigMap Update Behavior

Important:

If ConfigMap updated:

* Env injection → pod must restart
* Volume mount → file updates automatically (after short delay)

But app must reload config manually.

Senior awareness point.

---

# 🧠 6️⃣ Secret Basics

Create secret:

```bash
kubectl create secret generic db-secret \
  --from-literal=DB_PASSWORD=supersecret
```

YAML:

```yaml
apiVersion: v1
kind: Secret
metadata:
  name: db-secret
type: Opaque
data:
  DB_PASSWORD: c3VwZXJzZWNyZXQ=
```

Base64 encoded.

Important:

Base64 ≠ encryption.

---

# 🧠 7️⃣ Inject Secret as Environment Variable

```yaml
env:
  - name: DB_PASSWORD
    valueFrom:
      secretKeyRef:
        name: db-secret
        key: DB_PASSWORD
```

Common pattern.

---

# 🧠 8️⃣ Inject Secret as Volume

```yaml
volumes:
  - name: secret-volume
    secret:
      secretName: db-secret

containers:
  - name: app
    volumeMounts:
      - name: secret-volume
        mountPath: "/etc/secrets"
        readOnly: true
```

Secret available as file.

More secure for some applications.

---

# 🧠 9️⃣ Are Kubernetes Secrets Secure?

By default:

Secrets stored in etcd as base64.

Not encrypted unless:

Encryption at rest enabled.

In production clusters:

Enable etcd encryption.

Senior-level security awareness.

---

# 🧠 🔟 Secret Rotation Strategy

Problem:

DB password changes.

Steps:

1. Update Secret
2. Restart pods to pick new secret
3. Ensure backward compatibility during transition

Advanced approach:

Use external secret manager:

* AWS Secrets Manager
* HashiCorp Vault

Avoid storing secrets directly in cluster.

---

# 🧠 1️⃣1️⃣ Avoid Secret in Image

Never:

```dockerfile
ENV DB_PASSWORD=supersecret
```

Secret becomes part of image layer.
Anyone pulling image can inspect it.

Always inject at runtime.

---

# 🧠 1️⃣2️⃣ ConfigMap vs Secret Comparison

| Feature        | ConfigMap     | Secret       |
| -------------- | ------------- | ------------ |
| Sensitive      | No            | Yes          |
| Stored in etcd | Yes           | Yes          |
| Base64         | No            | Yes          |
| Encryption     | Optional      | Optional     |
| Typical usage  | Feature flags | DB passwords |

---

# 🧠 1️⃣3️⃣ Real Production Scenario

Scenario:

Pod crashing with error:

“DB_PASSWORD not found”

Check:

```bash
kubectl describe pod mypod
```

Likely:

* Secret missing
* Typo in key
* Namespace mismatch

Common interview debugging scenario.

---

# 🧠 1️⃣4️⃣ Advanced: Immutable ConfigMap

In newer versions:

```yaml
immutable: true
```

Prevents accidental modification.

Used for stable config.

---

# 🧠 1️⃣5️⃣ Senior-Level Answer Structure

If asked:

“How do you manage configuration in Kubernetes?”

Strong answer:

* Use ConfigMap for non-sensitive config
* Use Secret for sensitive data
* Inject via env or volume
* Enable etcd encryption
* Rotate secrets safely
* Prefer external secret manager in production

Clear. Structured. Mature.

---

# 🎯 STEP 10 REVISION CHECKLIST

You must confidently explain:

✔ ConfigMap creation
✔ Env vs volume injection
✔ Secret injection
✔ Base64 vs encryption
✔ Secret rotation strategy
✔ Avoid secrets in image
✔ etcd encryption awareness
✔ Debugging config issues

If you can explain all clearly →
Configuration maturity achieved.

---

When ready, type:

**11**

Next file:

📁 `11-resource-management-scheduling-deep.md`
(Resource requests vs limits + scheduling algorithm + QoS classes + eviction + node pressure handling)
