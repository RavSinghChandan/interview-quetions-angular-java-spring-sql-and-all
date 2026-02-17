# CI/CD — LEVEL 3 (INTERMEDIATE PRACTICAL)

**Stage Title: “You Build Production Pipelines Like Real Companies”**

*(Now you move from simple pipelines → real production delivery systems.)*

---

# 🎯 OBJECTIVE OF THIS LEVEL

After this stage you will be able to:

* design multi-stage pipelines
* manage environments (dev/stage/prod)
* implement approval gates
* deploy safely
* manage artifacts across stages
* structure enterprise pipelines

You move from:

```
Basic Pipeline → Production Pipeline Engineer
```

---

# 1️⃣ REALITY — PRODUCTION PIPELINES ARE NOT SIMPLE

Basic pipeline:

```
build → test → deploy
```

Real pipeline:

```
build → test → scan → package → publish → stage deploy → tests → prod deploy
```

Production pipelines must guarantee:

* safety
* traceability
* rollback
* verification

---

# 2️⃣ MULTI-STAGE PIPELINE STRUCTURE

Example pipeline:

```
stages:
  - build
  - test
  - security
  - package
  - deploy
```

Each stage represents a verification gate.

---

# 3️⃣ REAL BUILD STAGE

```
build:
  script:
    - npm install
    - npm run build
```

Purpose:

Compile code and verify it builds successfully.

---

# 4️⃣ TEST STAGE (MANDATORY)

```
test:
  script:
    - npm test
```

Failing test = pipeline stops.

Broken code never deploys.

---

# 5️⃣ LINT + QUALITY STAGE

```
lint:
  script:
    - npm run lint
```

This ensures:

* code quality
* style consistency
* syntax correctness

Companies enforce this automatically.

---

# 6️⃣ SECURITY SCAN STAGE

Scan dependencies:

```
security:
  script:
    - npm audit
```

Or container scan:

```
docker scan image
```

Purpose:

Prevent vulnerable code from reaching production.

---

# 7️⃣ ARTIFACT TRANSFER BETWEEN STAGES

Build stage creates artifact:

```
artifacts:
  paths:
    - dist/
```

Next stage uses it.

This guarantees:

Same build → same deployment.

---

# 8️⃣ ENVIRONMENT DEPLOYMENTS

Deploy to staging first:

```
deploy_staging:
  environment: staging
```

Then production:

```
deploy_prod:
  environment: production
```

Never deploy directly to production.

---

# 9️⃣ APPROVAL GATES

Require manual approval before prod deploy.

Example:

```
when: manual
```

This ensures:

Human verifies before production release.

---

# 🔟 BRANCH-BASED DEPLOYMENT

Deploy only from main branch:

```
if: branch == main
```

Used for:

```
feature branch → test only
main branch → deploy
```

---

# 11️⃣ VERSIONED BUILDS

Tag release:

```
git tag v1.0.0
```

Pipeline triggers:

```
on tag → deploy production
```

Production deployments must be versioned.

---

# 12️⃣ DOCKER BUILD + PUSH PIPELINE

```
docker build -t repo/app:$VERSION .
docker push repo/app:$VERSION
```

Now deployment uses exact image version.

Guarantees reproducibility.

---

# 13️⃣ SAFE DEPLOYMENT STRATEGIES

Production pipelines deploy using:

```
rolling update
blue-green
canary
```

Pipeline controls deployment method.

---

# 14️⃣ ROLLBACK MECHANISM

If deployment fails:

Rollback automatically:

```
kubectl rollout undo deployment/app
```

Professional pipelines always support rollback.

---

# 15️⃣ PARALLEL JOB EXECUTION

Example:

```
test backend
test frontend
test api
```

Run simultaneously.

Reduces pipeline time dramatically.

---

# 16️⃣ PIPELINE SPEED OPTIMIZATION

Techniques:

```
dependency caching
parallel jobs
incremental builds
test splitting
```

Slow pipelines reduce developer productivity.

---

# 17️⃣ PIPELINE FAIL-FAST STRATEGY

Stop early if failure detected.

Example:

```
if build fails → stop pipeline
```

No need to run later stages.

Saves time and resources.

---

# 18️⃣ REAL COMPANY PIPELINE EXAMPLE

Production-grade pipeline:

```
Checkout
Install
Lint
Unit Test
Integration Test
Security Scan
Build Artifact
Push Docker Image
Deploy Staging
Run Smoke Tests
Approval
Deploy Production
Monitor Health
```

That is real-world pipeline.

---

# 19️⃣ ENTERPRISE PIPELINE PRINCIPLE

Professional pipelines follow rule:

> No unverified code reaches production.

Verification must be automatic.

---

# 20️⃣ INTERMEDIATE COMPLETION CHECK

You can now:

✔ design multi-stage pipelines
✔ manage environments
✔ implement approvals
✔ handle artifacts
✔ deploy safely
✔ enable rollback
✔ optimize performance
✔ structure enterprise pipelines

---

# FINAL LINE

At this point:

> You’re not running pipelines anymore.

You’re designing delivery systems.

That is real CI/CD skill.

---

END OF LEVEL 3 — CI/CD INTERMEDIATE
