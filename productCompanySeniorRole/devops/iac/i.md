# TERRAFORM — LEVEL 3 (INTERMEDIATE PRACTICAL)

**Stage Title: “You Build Real Production Infrastructure Systems”**

*(Now you move from creating single resources → building real cloud architectures like companies run in production.)*

---

# 🎯 OBJECTIVE OF THIS LEVEL

After this stage you will be able to:

* design multi-resource infrastructure
* structure Terraform projects professionally
* use modules
* manage environments
* handle remote state
* build production-style infra

You move from:

```
Creating Resources → Designing Infrastructure Systems
```

---

# 1️⃣ REALITY — REAL INFRASTRUCTURE IS NEVER ONE RESOURCE

Beginner config:

```
create EC2
```

Real system:

```
VPC
subnets
security groups
load balancer
autoscaling group
database
monitoring
```

Terraform must manage all together.

---

# 2️⃣ PROFESSIONAL PROJECT STRUCTURE

Real Terraform project layout:

```
infra/
 ├── modules/
 │     ├── vpc/
 │     ├── compute/
 │     └── database/
 ├── env/
 │     ├── dev/
 │     ├── stage/
 │     └── prod/
 └── main.tf
```

This structure is used in real companies.

---

# 3️⃣ MODULES — REUSABLE INFRASTRUCTURE

Module = reusable infrastructure block.

Example module call:

```
module "web_server" {
  source = "./modules/compute"
  instance_type = "t3.micro"
}
```

Modules prevent duplication.

They are like functions in programming.

---

# 4️⃣ CREATE YOUR FIRST MODULE

Create folder:

```
modules/server/
```

Inside:

```
main.tf
variables.tf
outputs.tf
```

Example module resource:

```
resource "aws_instance" "server" {
  ami = var.ami
  instance_type = var.type
}
```

Now reusable anywhere.

---

# 5️⃣ ENVIRONMENT ISOLATION

Separate configs for:

```
dev
stage
prod
```

Example:

```
env/dev/main.tf
env/prod/main.tf
```

Each environment has its own infrastructure.

---

# 6️⃣ REMOTE STATE (CRITICAL PRODUCTION FEATURE)

Local state is dangerous for teams.

Use remote state:

Example S3 backend:

```
terraform {
  backend "s3" {
    bucket = "terraform-state"
    key    = "prod.tfstate"
    region = "ap-south-1"
  }
}
```

Benefits:

* shared state
* safe collaboration
* locking
* backups

---

# 7️⃣ STATE LOCKING (TEAM SAFETY)

Remote state systems support locking.

When someone runs apply:

```
state locked
```

Prevents two engineers modifying infra simultaneously.

Avoids corruption.

---

# 8️⃣ WORKSPACES — MULTI-ENV SUPPORT

List workspaces:

```
terraform workspace list
```

Create:

```
terraform workspace new dev
```

Switch:

```
terraform workspace select prod
```

Same config → different infra.

---

# 9️⃣ DATA SOURCES — READ EXISTING INFRA

Example:

```
data "aws_vpc" "default" {
  default = true
}
```

This reads existing VPC instead of creating new one.

Used to integrate with existing systems.

---

# 🔟 OUTPUT SHARING BETWEEN MODULES

Module output:

```
output "ip" {
  value = aws_instance.server.public_ip
}
```

Use output:

```
module.server.ip
```

Modules can communicate safely.

---

# 11️⃣ VARIABLES FILES (.tfvars)

Define environment variables:

```
dev.tfvars
prod.tfvars
```

Run:

```
terraform apply -var-file=dev.tfvars
```

Used for environment customization.

---

# 12️⃣ CONDITIONAL RESOURCES

Example:

```
count = var.create_db ? 1 : 0
```

Terraform creates resource only if condition true.

Used for optional infra.

---

# 13️⃣ LOOPING RESOURCES

Create multiple instances:

```
count = 3
```

Or:

```
for_each = var.instances
```

Used for:

* multiple servers
* multiple buckets
* multiple networks

---

# 14️⃣ DEPENDENCY CONTROL

Force dependency:

```
depends_on = [aws_vpc.main]
```

Used when implicit dependency not detected.

---

# 15️⃣ DYNAMIC BLOCKS

Generate repeated config blocks:

```
dynamic "ingress" {
  for_each = var.ports
}
```

Used for flexible infrastructure.

---

# 16️⃣ VERSION PINNING (CRITICAL PRACTICE)

Always lock provider version:

```
required_providers {
  aws = {
    version = "~> 5.0"
  }
}
```

Prevents breaking changes.

---

# 17️⃣ TERRAFORM GRAPH (HIDDEN POWER)

Visualize dependency graph:

```
terraform graph
```

Shows how resources relate internally.

Experts use this for debugging.

---

# 18️⃣ REAL PRODUCTION WORKFLOW

Real infrastructure workflow:

```
write module
validate
plan
review PR
apply staging
test infra
apply production
monitor
```

Infra changes must be reviewed like code.

---

# 19️⃣ ENTERPRISE PRINCIPLE

Professional Terraform teams follow rule:

> Infrastructure must be reproducible from code alone.

If infra can’t be rebuilt from code → system is fragile.

---

# 20️⃣ INTERMEDIATE COMPLETION CHECK

You can now:

✔ design multi-resource infra
✔ structure projects professionally
✔ create reusable modules
✔ manage environments
✔ use remote state
✔ share outputs
✔ loop resources
✔ design production infra

---

# FINAL LINE

At this point:

> You’re not creating resources anymore.

You’re designing infrastructure systems.

That is real Terraform skill.

---

END OF LEVEL 3 — TERRAFORM INTERMEDIATE
