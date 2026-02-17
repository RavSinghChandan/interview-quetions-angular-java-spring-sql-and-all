# CI/CD — LEVEL 4 (INTERMEDIATE+)

**Stage Title: “You See Inside Pipelines — How CI/CD Actually Works Internally”**

*(Story continues — now you stop writing pipelines blindly and start understanding how they execute as systems.)*

---

# 🎯 OBJECTIVE OF THIS LEVEL

After this stage you will:

* understand CI/CD internals
* know how runners actually execute jobs
* understand pipeline execution engines
* debug pipeline system failures
* understand caching, artifacts, and concurrency deeply
* think like a DevOps platform engineer

You move from:

```
Pipeline User → Pipeline Systems Engineer
```

---

# 1️⃣ THE BIG REALIZATION

CI/CD is not “running scripts.”

CI/CD is:

> a distributed execution system.

When pipeline runs, tasks are executed across machines, containers, or cloud workers.

It’s not one computer running commands.

It’s a system coordinating many workers.

---

# 2️⃣ INTERNAL ARCHITECTURE OF CI/CD SYSTEM

Pipeline execution architecture:

```
Trigger → Server → Scheduler → Runner → Executor → Logs → Result
```

Each component has a role.

---

## Trigger

Event happens:

```
git push
PR created
tag created
manual click
```

This event starts pipeline.

---

## Server

CI server receives event and loads pipeline config.

Example servers:

* GitHub Actions engine
* GitLab CI server
* Jenkins master

Server decides:

```
which jobs
which order
which runner
```

---

## Scheduler

Scheduler assigns jobs to available runners.

It decides:

```
which runner has capacity
which runner matches environment
```

---

## Runner

Runner is machine that executes job.

Runner may be:

```
VM
container
bare metal
kubernetes pod
```

Runners are workers.

---

## Executor

Executor is runtime inside runner.

Examples:

```
shell executor
docker executor
kubernetes executor
```

Executor runs actual commands.

---

# 3️⃣ WHAT ACTUALLY HAPPENS WHEN PIPELINE RUNS

When you push code:

```
event triggered
server reads config
jobs created
scheduler assigns runner
runner downloads code
runner executes steps
runner uploads logs
server reports result
```

Experts mentally simulate this chain.

---

# 4️⃣ WHY PIPELINES SOMETIMES STUCK IN QUEUE

If job stuck:

```
pending
queued
waiting
```

Reason:

No runner available.

Check:

```
runner busy
runner offline
runner misconfigured
```

Solution:

Add more runners.

---

# 5️⃣ JOB EXECUTION SANDBOX

Each job runs in isolated environment.

Isolation prevents:

* dependency conflicts
* environment contamination
* version mismatch

Common environments:

```
Docker container
ephemeral VM
temporary pod
```

After job ends → environment destroyed.

---

# 6️⃣ WHY PIPELINES ARE REPRODUCIBLE

Because jobs run in fresh environments.

This guarantees:

```
same input → same output
```

That is core principle of CI/CD reliability.

---

# 7️⃣ ARTIFACT FLOW INTERNALLY

Artifact lifecycle:

```
job builds artifact
artifact uploaded to server
next job downloads artifact
job uses artifact
```

Artifact storage is centralized.

---

# 8️⃣ CACHE SYSTEM INTERNALS

Cache differs from artifact.

Artifact = build output
Cache = reusable dependency data

Example:

```
node_modules cache
```

Flow:

```
job checks cache
if exists → download
else → build + store cache
```

Cache improves speed.

---

# 9️⃣ PARALLEL EXECUTION ENGINE

CI systems run jobs simultaneously.

Example:

```
test backend
test frontend
test API
```

Scheduler distributes jobs across runners.

Parallelization = speed.

---

# 🔟 PIPELINE GRAPH EXECUTION

Pipelines are not linear.

They are graphs.

Example:

```
build → test
test → deploy
lint → deploy
```

Graph determines job order.

---

# 11️⃣ FAILURE PROPAGATION LOGIC

If job fails:

Downstream jobs stop.

Because pipeline engine checks dependencies.

Fail-fast logic saves time and compute.

---

# 12️⃣ LOG STREAMING SYSTEM

While job runs:

Runner streams logs to server.

Server displays logs in UI in real time.

If job crashes → logs still preserved.

---

# 13️⃣ SECRET INJECTION SYSTEM

Secrets are never stored in pipeline file.

They are injected dynamically at runtime:

```
runner requests secret
server validates permission
server sends secret
job uses secret
```

Secrets disappear after job ends.

---

# 14️⃣ WHY PIPELINES ARE SECURE

Security comes from:

* isolation
* ephemeral environments
* secret injection
* permission checks

Secure pipelines are designed systems.

Not just scripts.

---

# 15️⃣ SCALING PIPELINE INFRASTRUCTURE

Large companies run thousands of pipelines simultaneously.

They scale runners using:

```
autoscaling VMs
Kubernetes pods
serverless runners
```

This makes CI/CD horizontally scalable.

---

# 16️⃣ WHY PIPELINE FAILURES ARE PREDICTABLE

Every failure belongs to one layer:

```
config error
runner error
network error
dependency error
code error
permission error
```

Experts debug layer-by-layer.

---

# 17️⃣ EVENT-DRIVEN EXECUTION MODEL

Pipelines don’t run continuously.

They run only when triggered.

CI/CD systems are:

> event-driven distributed systems.

---

# 18️⃣ INTERNAL MENTAL MODEL

If you remember one thing:

```
Server decides
Scheduler assigns
Runner executes
Executor runs
```

That is pipeline system.

---

# 19️⃣ WHY THIS LEVEL IS RARE

Most developers know:

```
how to write pipeline
```

Few understand:

```
how pipeline engine works
```

Senior interviews test internals.

---

# 20️⃣ COMPLETION CHECK

You now understand:

✔ pipeline architecture
✔ runner system
✔ execution model
✔ artifact lifecycle
✔ cache system
✔ parallel execution
✔ scheduling logic
✔ failure propagation

---

# FINAL LINE

At this stage:

> CI/CD is no longer a black box.

It’s a system you understand.

And systems you understand…

you can optimize and control.

---

END OF LEVEL 4 — CI/CD INTERMEDIATE+
