# CI/CD — LEVEL 1 (NAIVE)

**Stage Title: “Meeting the System That Deploys Code Automatically”**

*(This continues the story. You are seeing CI/CD for the first time — not as tools, but as a system.)*

---

# 🎯 PURPOSE OF THIS LEVEL

At this stage you are **not learning Jenkins or GitHub Actions**.

You are learning:

> how CI/CD thinks.

Because once you understand its logic, any tool becomes easy.

Most people fail CI/CD interviews because they memorize tools.

Senior engineers understand systems.

You will understand the system.

---

# 1️⃣ WHAT CI/CD ACTUALLY IS (REAL DEFINITION)

CI/CD is:

> an automated software delivery system.

It automatically:

```
builds code
tests code
packages code
deploys code
```

Without manual intervention.

---

# 2️⃣ WHAT CI/CD IS NOT

CI/CD is NOT:

* a tool
* a server
* a script
* a framework

CI/CD is:

> a process automation model.

Tools only implement it.

---

# 3️⃣ THE MOST IMPORTANT IDEA

CI/CD removes humans from repetitive deployment steps.

Why?

Because humans make mistakes.

Automation doesn’t forget steps.

---

# 4️⃣ THE CORE PROBLEM CI/CD SOLVES

Without CI/CD:

Deployments depend on humans.

With CI/CD:

Deployments depend on pipelines.

So CI/CD guarantees:

> consistency.

---

# 5️⃣ THE MENTAL MODEL YOU MUST REMEMBER

Think of CI/CD as:

> a factory assembly line.

Raw material → code
Machines → pipeline steps
Final product → deployed application

Factory runs automatically.

---

# 6️⃣ WHAT “CONTINUOUS” REALLY MEANS

Continuous doesn’t mean constant.

It means:

> automated and repeatable.

Whenever code changes, pipeline runs.

---

# 7️⃣ WHAT CI DOES (CONTINUOUS INTEGRATION)

CI ensures:

```
code compiles
tests pass
build succeeds
```

Before code is accepted.

So CI guarantees:

> broken code never enters system.

---

# 8️⃣ WHAT CD DOES (CONTINUOUS DELIVERY)

CD ensures:

> code is always ready to deploy.

Pipeline prepares release automatically.

Deployment may still be manual.

---

# 9️⃣ WHAT CONTINUOUS DEPLOYMENT DOES

Continuous Deployment means:

> code deploys automatically after tests pass.

No human approval.

Fully automated releases.

---

# 🔟 PIPELINE — THE MOST IMPORTANT TERM

Pipeline = sequence of automated steps.

Example pipeline:

```
Install dependencies
Run tests
Build app
Build Docker image
Push image
Deploy to Kubernetes
```

Pipeline is the heart of CI/CD.

---

# 11️⃣ WHAT TRIGGERS PIPELINE

Pipeline runs when event happens:

Events:

```
code push
pull request
tag created
schedule
manual trigger
```

CI/CD systems are event-driven.

---

# 12️⃣ WHAT RUNS PIPELINE

Pipeline runs on:

> agents (workers)

Agents are machines that execute tasks.

They may be:

* local machine
* cloud VM
* container
* Kubernetes pod

---

# 13️⃣ STAGES — PIPELINE PHASES

Pipelines divided into stages.

Example:

```
Stage 1 → Build
Stage 2 → Test
Stage 3 → Security scan
Stage 4 → Deploy
```

Stages make pipelines readable and organized.

---

# 14️⃣ JOBS — TASK UNITS

Stage contains jobs.

Example:

Test stage:

```
job1 → unit tests
job2 → integration tests
job3 → lint check
```

Jobs can run in parallel.

---

# 15️⃣ WHY CI/CD IS FAST

Because jobs run simultaneously.

Example:

```
test Java
test Python
test frontend
```

Run parallel → faster pipeline.

---

# 16️⃣ ARTIFACTS — BUILD OUTPUT

Pipeline produces artifact.

Artifact examples:

```
jar file
docker image
zip package
compiled binary
```

Artifacts move between stages.

---

# 17️⃣ ENVIRONMENTS

Pipeline deploys to environments:

```
dev
staging
production
```

Each environment represents system stage.

---

# 18️⃣ WHY CI/CD IS SAFE

Because pipeline verifies everything before deployment:

* tests
* security
* build success
* environment compatibility

So deployment risk becomes low.

---

# 19️⃣ THE GOLDEN MENTAL MODEL

If you remember only one thing:

```
Code change → Pipeline runs → System validates → App deploys
```

That is CI/CD.

---

# 20️⃣ NAIVE LEVEL COMPLETION CHECK

You now understand:

✔ what CI/CD really is
✔ what pipeline means
✔ difference between CI and CD
✔ what triggers pipelines
✔ what agents are
✔ what stages are
✔ what artifacts are
✔ why CI/CD exists

No tools yet.

Only understanding.

---

# FINAL LINE

At this stage:

> CI/CD is no longer mysterious.

You understand its logic.

And once you understand the logic…

you can control any CI/CD tool.

---

END OF LEVEL 1 — CI/CD NAIVE
