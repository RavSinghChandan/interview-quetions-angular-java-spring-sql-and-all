# CI/CD — LEVEL 5 (ADVANCED)

**Stage Title: “You Become the Engineer Called When Pipelines Fail in Production”**

*(Story continues — now you enter real-world CI/CD engineering. This is where pipelines break, releases fail, and you’re the one who knows why.)*

---

# 🎯 OBJECTIVE OF THIS LEVEL

After this stage you will be able to:

* debug failed pipelines systematically
* diagnose deployment failures
* analyze runner issues
* fix environment mismatches
* troubleshoot artifact problems
* investigate performance bottlenecks

This is the level where you become:

```
Pipeline User → Production CI/CD Engineer
```

---

# 1️⃣ REALITY — PRODUCTION PIPELINES FAIL DIFFERENTLY

In real companies, pipeline failures rarely say:

> “Error: Something went wrong”

Instead you see:

* job stuck in queue
* pipeline hangs forever
* deployment succeeds but app broken
* artifact missing
* tests pass locally but fail in CI
* random timeout

Advanced engineers don’t guess.

They investigate layer-by-layer.

---

# 2️⃣ GOLDEN DEBUG RULE

When pipeline fails:

Never rerun immediately.

Always inspect logs first.

Debug checklist:

```
Check logs
Check stage
Check runner
Check environment
Check dependencies
Check permissions
```

This order solves most issues.

---

# 3️⃣ JOB STUCK IN QUEUE

Status:

```
Pending / Waiting
```

Cause:

No runner available.

Check:

```
Runner offline
Runner busy
Runner limit reached
```

Fix:

Add runner or free existing ones.

---

# 4️⃣ PIPELINE FAILS ONLY IN CI BUT WORKS LOCALLY

This is common.

Reason:

Environment mismatch.

Check:

```
Node version
Java version
Python version
OS version
dependencies
```

Fix by defining environment explicitly:

```
runs-on: ubuntu-22.04
```

Or using Docker image:

```
container: node:18
```

---

# 5️⃣ RANDOM BUILD FAILURES

Symptoms:

* flaky tests
* intermittent failures
* random timeout

Causes:

* race conditions
* async tests
* external API calls
* network latency

Solution:

Stabilize tests.

CI exposes hidden bugs.

---

# 6️⃣ DEPENDENCY INSTALL FAILURES

Logs show:

```
npm install failed
pip install failed
apt install failed
```

Reasons:

* network issue
* registry down
* lockfile mismatch

Fix:

Use lockfiles.

```
package-lock.json
requirements.txt
```

---

# 7️⃣ DOCKER BUILD FAILURES

Common errors:

```
file not found
permission denied
dependency missing
```

Debug:

```
docker build locally
```

If fails locally → fix Dockerfile.

---

# 8️⃣ ARTIFACT NOT FOUND ERROR

Stage error:

```
artifact missing
```

Cause:

Previous stage didn’t upload artifact.

Fix:

Ensure artifact declared:

```
artifacts:
  paths:
    - dist/
```

---

# 9️⃣ DEPLOYMENT SUCCEEDED BUT APP BROKEN

Pipeline success ≠ app success.

Debug steps:

```
check logs
check environment variables
check config
check database
check API endpoints
```

Always verify deployed system.

---

# 🔟 SECRET PERMISSION FAILURES

Error:

```
Access denied
Secret not found
```

Check:

* secret name
* permissions
* environment scope

Never print secrets in logs.

---

# 11️⃣ TIMEOUT FAILURES

If job fails with timeout:

```
Job exceeded time limit
```

Causes:

* slow tests
* large build
* network delay

Fix:

Increase timeout:

```
timeout-minutes: 20
```

Or optimize job.

---

# 12️⃣ PARALLEL JOB COLLISION

Parallel jobs may conflict if:

* using same port
* writing same file
* using shared resource

Fix:

Isolate jobs.

Use unique temp directories.

---

# 13️⃣ RUNNER CRASH

If runner crashes:

```
job lost
pipeline failed
```

Check runner logs:

```
systemctl status runner
```

Restart runner.

---

# 14️⃣ PERMISSION ERRORS

Error:

```
permission denied
```

Cause:

Runner user lacks access.

Fix:

Grant permission or change execution user.

---

# 15️⃣ NETWORK FAILURES DURING PIPELINE

Symptoms:

```
download failed
API unreachable
connection refused
```

Debug:

```
ping registry
curl API
```

Often temporary outage.

---

# 16️⃣ CACHE CORRUPTION

Cache sometimes causes failures.

Fix:

Clear cache.

Most CI tools allow:

```
Clear cache button
```

Or change cache key.

---

# 17️⃣ DEPLOYMENT RACE CONDITIONS

If multiple pipelines deploy simultaneously:

Production may break.

Solution:

Add deployment lock:

```
concurrency:
  group: production
```

Ensures one deploy at a time.

---

# 18️⃣ REAL INCIDENT DEBUG FLOW (USED BY SENIORS)

When pipeline fails:

Follow exact order:

```
Logs → Stage → Job → Runner → Environment → Network → Artifact → Deployment
```

Never jump randomly.

Always layer-based debugging.

---

# 19️⃣ ADVANCED ENGINEER MINDSET

Beginners restart pipeline.

Advanced engineers ask:

```
What layer failed?
What changed recently?
Is failure deterministic?
Is failure reproducible?
```

Because CI/CD failures always have a reason.

---

# 20️⃣ ADVANCED COMPLETION CHECK

You can now:

✔ debug failed pipelines
✔ diagnose runner issues
✔ fix environment mismatches
✔ solve dependency errors
✔ investigate deployments
✔ analyze logs professionally
✔ detect flaky tests
✔ troubleshoot real CI/CD failures

---

# FINAL LINE

At this point:

> You don’t fear broken pipelines.

Because you know how to investigate them.

That is the difference between:

Developer → DevOps engineer.

---

END OF LEVEL 5 — CI/CD ADVANCED
