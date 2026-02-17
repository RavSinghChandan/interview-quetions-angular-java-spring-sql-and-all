# TERRAFORM — LEVEL 5 (ADVANCED)

**Stage Title: “You Become the Engineer Called When Infrastructure Breaks”**

*(Story continues — now you enter real-world Terraform engineering. This is where infrastructure fails, environments drift, and production systems break. You’re the one who knows why.)*

---

# 🎯 OBJECTIVE OF THIS LEVEL

After this stage you will be able to:

* debug real infrastructure failures
* fix state corruption
* diagnose apply failures
* detect configuration drift
* troubleshoot provider issues
* recover broken environments

You move from:

```
Terraform User → Production Infrastructure Engineer
```

---

# 1️⃣ REALITY — PRODUCTION INFRA FAILURES LOOK DIFFERENT

Real failures don’t say:

> “Terraform error”

Instead you see:

* apply stuck forever
* resource already exists
* resource not found
* permission denied
* dependency cycle
* inconsistent state

Advanced engineers don’t panic.

They diagnose systematically.

---

# 2️⃣ GOLDEN DEBUG RULE

When Terraform fails:

Never rerun immediately.

Always inspect first.

Debug checklist:

```
Check error message
Check plan output
Check state file
Check provider logs
Check cloud console
```

This solves most failures.

---

# 3️⃣ RESOURCE ALREADY EXISTS ERROR

Error:

```
ResourceAlreadyExists
```

Cause:

Resource created manually outside Terraform.

Fix:

Import resource:

```
terraform import aws_instance.web i-12345
```

Now Terraform manages it.

---

# 4️⃣ RESOURCE NOT FOUND ERROR

Error:

```
ResourceNotFound
```

Cause:

Resource deleted manually in cloud.

Fix:

Run:

```
terraform apply
```

Terraform recreates missing resource.

---

# 5️⃣ PERMISSION DENIED FAILURE

Error:

```
AccessDenied
```

Cause:

IAM role lacks permission.

Debug:

Check credentials used by Terraform.

Fix:

Grant required permissions.

Terraform cannot create what it’s not allowed to.

---

# 6️⃣ APPLY STUCK FOREVER

Symptoms:

```
Still creating...
Still modifying...
```

Possible causes:

* API slow
* network issue
* provider bug
* dependency loop

Debug:

Enable logs:

```
TF_LOG=DEBUG terraform apply
```

---

# 7️⃣ DEPENDENCY CYCLE ERROR

Error:

```
Cycle detected
```

Meaning:

Resource A depends on B
Resource B depends on A

Fix:

Break dependency chain.

Terraform graphs must be acyclic.

---

# 8️⃣ STATE DRIFT DETECTION

If infrastructure changed manually:

Run:

```
terraform plan
```

Terraform shows unexpected differences.

Example:

```
~ security group changed
```

Drift must be corrected.

Never ignore drift.

---

# 9️⃣ STATE FILE CORRUPTION

If state corrupted:

Symptoms:

```
missing resource
invalid JSON
unexpected changes
```

Fix options:

```
restore backup state
terraform refresh
terraform import
```

Always keep state backups.

---

# 🔟 LOCK FILE STUCK

Error:

```
state locked
```

Cause:

Previous run didn’t release lock.

Fix:

```
terraform force-unlock LOCK_ID
```

Use carefully.

---

# 11️⃣ PROVIDER VERSION BREAKAGE

If provider upgraded:

```
terraform init -upgrade
```

New provider may change behavior.

Fix:

Pin provider version:

```
version = "~> 5.0"
```

Always lock versions in production.

---

# 12️⃣ PLAN SHOWS UNEXPECTED CHANGES

If plan shows unexpected modifications:

Possible causes:

```
state drift
provider update
config typo
variable change
```

Never apply until you understand changes.

---

# 13️⃣ PARTIAL APPLY FAILURE

If apply fails midway:

Some resources created, some not.

State may be inconsistent.

Fix:

Run:

```
terraform apply
```

Terraform resumes from incomplete state.

---

# 14️⃣ TIMEOUT ERRORS

Error:

```
timeout while waiting for resource
```

Cause:

Cloud resource slow.

Fix:

Increase timeout:

```
timeouts {
  create = "10m"
}
```

---

# 15️⃣ NETWORK/API FAILURES

Symptoms:

```
connection refused
API unavailable
TLS error
```

Debug:

Check:

```
internet connectivity
API status
credentials
DNS
```

Many failures are external, not Terraform issues.

---

# 16️⃣ MODULE DEBUGGING

If module fails:

Check module variables.

Run:

```
terraform console
```

Test variable values interactively.

---

# 17️⃣ REAL INCIDENT DEBUG FLOW (USED BY SENIORS)

When infrastructure fails:

Follow exact order:

```
Error → Plan → State → Config → Provider → Cloud → Network
```

Never random debugging.

Always layered investigation.

---

# 18️⃣ PRODUCTION MINDSET

Beginners rerun apply.

Advanced engineers ask:

```
What changed?
What layer failed?
Is state accurate?
Is provider behaving correctly?
```

Infrastructure failures always have a cause.

---

# 19️⃣ FAILURE CLASSIFICATION MODEL

Every Terraform failure belongs to one category:

```
Config bug
State mismatch
Permission issue
Provider issue
Cloud issue
Dependency issue
```

Experts identify category instantly.

---

# 20️⃣ ADVANCED COMPLETION CHECK

You can now:

✔ debug apply failures
✔ fix state problems
✔ detect drift
✔ resolve dependencies
✔ troubleshoot providers
✔ recover broken infra
✔ diagnose permission issues
✔ handle real production failures

---

# FINAL LINE

At this point:

> You don’t fear infrastructure failures.

Because you know how to investigate them.

That is the difference between:

Engineer → Infrastructure Engineer.

---

END OF LEVEL 5 — TERRAFORM ADVANCED
