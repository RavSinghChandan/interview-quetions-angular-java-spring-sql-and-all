Excellent. This step is where interviewers detect **true production engineers**.

Anyone can deploy.
Senior engineers are judged by:

> What they do when production breaks.

Master this → you signal **on-call ownership maturity**.

---

# 📁 FILE: `Step6_Rollback_Failure_Recovery_Engineering.md`

---

## 🎯 Goal of This Step

Learn **exactly how real systems recover from failures** in production.

This step covers:

```
failure detection
rollback logic
incident handling
mitigation strategies
hotfix workflows
```

---

# 🧠 SECTION 1 — Production Failure Reality

Production failures are inevitable.

Common causes:

```
bad deploy
memory leak
DB migration issue
third-party outage
config mistake
traffic spike
```

Senior mindset:

> Failure is not the problem. Slow recovery is.

---

# 🚨 SECTION 2 — Failure Detection Pipeline

Failure detection is automated.

Monitoring signals:

```
Error rate ↑
Latency ↑
CPU ↑
Memory ↑
Crash loops
Timeouts
```

Prometheus alert rule:

```yaml
- alert: HighErrorRate
  expr: rate(errors_total[2m]) > 0.05
  for: 2m
```

---

# 🔔 SECTION 3 — Alert Routing

Alerts must go to humans.

Alertmanager example:

```yaml
receivers:
  - name: oncall
    email_configs:
      - to: devops@company.com
```

Modern routing:

```
PagerDuty
Slack
Opsgenie
SMS
```

Senior rule:

> Alerts must be actionable, not noisy.

---

# 🔁 SECTION 4 — Automated Rollback

Best systems rollback automatically.

Example logic:

```java
if(errorRate > 5% || latency > 2s){
    triggerRollback();
}
```

Kubernetes native rollback:

```bash
kubectl rollout undo deployment payment
```

Behind scenes:

```
previous replica set restored
```

---

# 🌳 SECTION 5 — Rollback Decision Tree (Critical Interview Answer)

Senior engineers follow structured thinking:

```
Is failure from deploy?
    yes → rollback
    no ↓

Is DB issue?
    yes → restore backup
    no ↓

Is external API down?
    yes → enable fallback
    no ↓

Investigate logs
```

---

# 🔥 SECTION 6 — Incident Response Flow

Real incident lifecycle:

```
Alert
↓
Triage
↓
Mitigate
↓
Fix
↓
Root Cause Analysis
↓
Prevention
```

---

### Triage Script

```bash
kubectl get pods
kubectl logs pod-name
kubectl top pod
```

Check:

```
restarts
OOM kills
errors
```

---

# 🛠 SECTION 7 — Live Production Debugging Commands

Must know:

### Check rollout status

```bash
kubectl rollout status deployment app
```

---

### Check events

```bash
kubectl describe pod pod-name
```

---

### View logs

```bash
kubectl logs pod-name --tail=100
```

---

### Exec into container

```bash
kubectl exec -it pod-name -- sh
```

Senior signal:

> Knows production debugging commands.

---

# 🧯 SECTION 8 — Mitigation Techniques

When you cannot fix immediately:

Mitigate impact.

Examples:

```
scale pods
disable feature
increase timeout
enable cache
rate limit traffic
switch fallback API
```

Scaling example:

```bash
kubectl scale deployment app --replicas=10
```

---

# 🧪 SECTION 9 — Hotfix Deployment Strategy

When urgent bug:

```
branch from prod tag
fix
test
deploy immediately
```

Example:

```bash
git checkout v1.2.1
git checkout -b hotfix-nullpointer
```

After fix:

```
merge → main
tag release
deploy
```

---

# 📜 SECTION 10 — Postmortem (Elite Engineering Culture)

After incident:

Write RCA doc.

Template:

```
Incident Time:
Impact:
Root Cause:
Resolution:
Lessons Learned:
Prevention:
```

Senior line:

> Mature teams fix system, not blame people.

---

# 🏆 Elite Interview Answer

If interviewer asks:

**What would you do if production fails right after deployment?**

Answer:

> First I check metrics to confirm impact, then determine if the issue is deployment-related. If yes, I immediately rollback using deployment history. If not, I triage logs, resource usage, and dependencies. I mitigate impact by scaling or disabling features, then fix root cause and publish postmortem to prevent recurrence.

That answer signals:

```
Real production ownership experience
```

---

# 📊 Company Signal Table

| Knowledge                   | Level         |
| --------------------------- | ------------- |
| Knows rollback command      | Mid           |
| Knows triage steps          | Senior        |
| Knows mitigation            | Strong Senior |
| Knows incident lifecycle    | Staff         |
| Mentions postmortem culture | Principal     |

---

# 📌 Mastery Checklist

You must confidently explain:

* alert triggers
* rollback flow
* triage commands
* mitigation tactics
* hotfix workflow
* incident lifecycle
* postmortem culture

Miss any → interviewer assumes you never handled incidents.

---

✅ Reply **"7"** when ready for next file:

> `Step7_Observability_Logs_Metrics_Tracing.md`

Next step = **how senior engineers see inside production systems**
(centralized logs, structured logs, correlation IDs, metrics design, tracing architecture).
