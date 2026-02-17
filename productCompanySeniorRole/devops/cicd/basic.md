# CI/CD — LEVEL 2 (BASIC PRACTICAL)

**Stage Title: “You Build Your First Real Pipeline”**

*(Now theory ends. You start building real pipelines like real engineers.)*

---

# 🎯 OBJECTIVE OF THIS LEVEL

After this level you will be able to:

* create pipeline file
* run automated builds
* run tests automatically
* build artifacts
* trigger pipelines
* debug pipeline failures

You move from:

```
Understanding CI/CD → Using CI/CD
```

Everything below is practical and tool-realistic.

---

# 1️⃣ PIPELINE FILE — THE HEART OF CI/CD

Every CI/CD system uses a configuration file.

Examples:

```
GitHub Actions → .github/workflows/main.yml
GitLab → .gitlab-ci.yml
Jenkins → Jenkinsfile
```

Pipeline = code.

---

# 2️⃣ YOUR FIRST PIPELINE (GITHUB ACTIONS EXAMPLE)

Create file:

```
.github/workflows/ci.yml
```

Paste:

```
name: First Pipeline

on: [push]

jobs:
  build:
    runs-on: ubuntu-latest

    steps:
      - uses: actions/checkout@v3

      - name: Install Node
        uses: actions/setup-node@v3
        with:
          node-version: 18

      - run: npm install
      - run: npm test
```

Push code → pipeline runs automatically.

---

# 3️⃣ WHAT JUST HAPPENED INTERNALLY

When you pushed code:

```
Git push event
→ CI system triggered
→ Runner created
→ Steps executed
→ Result reported
```

You didn’t run anything manually.

Automation did everything.

---

# 4️⃣ VIEW PIPELINE STATUS

In CI dashboard you’ll see:

```
Running
Success
Failed
```

This is your build status.

Teams block merges if pipeline fails.

---

# 5️⃣ ADD BUILD STEP

Add build command:

```
- run: npm run build
```

Now pipeline:

```
install → test → build
```

---

# 6️⃣ ADD ARTIFACT UPLOAD

Store build output:

```
- uses: actions/upload-artifact@v3
  with:
    name: build
    path: dist/
```

Now pipeline saves compiled output.

---

# 7️⃣ MULTIPLE JOBS

Add second job:

```
jobs:
  build:
  test:
```

Example:

```
jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - run: npm test

  lint:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - run: npm run lint
```

Jobs run in parallel.

---

# 8️⃣ JOB DEPENDENCIES

Make job wait:

```
needs: build
```

Example:

```
deploy:
  needs: test
```

Meaning:

Deploy runs only after tests pass.

---

# 9️⃣ ENVIRONMENT VARIABLES

Define env:

```
env:
  NODE_ENV: production
```

Access inside step:

```
echo $NODE_ENV
```

Used for config.

---

# 🔟 SECRET VARIABLES

Add secret:

```
Settings → Secrets → Add
```

Use in pipeline:

```
${{ secrets.API_KEY }}
```

Never hardcode credentials.

---

# 11️⃣ CONDITIONAL EXECUTION

Run step only on main branch:

```
if: github.ref == 'refs/heads/main'
```

Used for:

```
deploy only from main
```

---

# 12️⃣ MANUAL PIPELINE TRIGGER

Add:

```
on:
  workflow_dispatch:
```

Now you can manually run pipeline.

---

# 13️⃣ SCHEDULED PIPELINES

Run nightly:

```
on:
  schedule:
    - cron: "0 2 * * *"
```

Used for:

* nightly tests
* security scans
* backups

---

# 14️⃣ CACHE DEPENDENCIES (IMPORTANT)

Speed up pipeline:

```
- uses: actions/cache@v3
```

Example:

```
with:
  path: ~/.npm
  key: node-modules
```

Without cache → installs every run.
With cache → faster builds.

---

# 15️⃣ DOCKER BUILD IN PIPELINE

Add step:

```
- run: docker build -t app .
```

Push image:

```
- run: docker push repo/app
```

Now pipeline builds containers automatically.

---

# 16️⃣ DEPLOY STEP EXAMPLE

Deploy to server:

```
- run: ssh user@server "deploy.sh"
```

Or deploy to Kubernetes:

```
- run: kubectl apply -f k8s.yaml
```

Pipeline now deploys app automatically.

---

# 17️⃣ DEBUGGING FAILED PIPELINE

Check logs inside CI UI.

Common failures:

```
dependency install failed
test failed
build error
missing env variable
```

Logs always show exact failing step.

---

# 18️⃣ REAL PIPELINE FLOW (PRODUCTION STYLE)

Real pipelines look like:

```
Checkout
Install
Test
Lint
Security Scan
Build
Push Image
Deploy Staging
Run Integration Tests
Deploy Production
```

---

# 19️⃣ REAL ENGINEER WORKFLOW

Daily CI/CD workflow:

```
write code
commit
push
pipeline runs
fix failures
merge
deploy
```

Developers rarely deploy manually.

Pipeline handles it.

---

# 20️⃣ BASIC COMPLETION CHECK

You can now:

✔ write pipeline config
✔ trigger builds automatically
✔ run tests in pipeline
✔ upload artifacts
✔ use secrets
✔ build Docker images
✔ deploy automatically
✔ debug failed pipelines

---

# FINAL LINE

At this point:

> CI/CD is no longer theory.

You can create real pipelines that build and deploy applications automatically.

That’s real engineering skill.

---

END OF LEVEL 2 — CI/CD BASIC
