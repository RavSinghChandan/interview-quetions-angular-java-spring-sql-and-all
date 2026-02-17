# TERRAFORM — LEVEL 2 (BASIC PRACTICAL)

**Stage Title: “You Create Real Infrastructure From Code”**

*(Now theory ends. You start building real infrastructure like real engineers.)*

---

# 🎯 OBJECTIVE OF THIS LEVEL

After this stage you will be able to:

* write Terraform configs
* initialize Terraform project
* create real resources
* understand execution flow
* modify infrastructure safely
* destroy infrastructure cleanly

You move from:

```
Understanding Terraform → Using Terraform
```

Everything below is practical and real.

---

# 1️⃣ TERRAFORM PROJECT STRUCTURE

Basic project:

```
project/
 ├── main.tf
 ├── variables.tf
 ├── outputs.tf
 └── terraform.tfstate
```

Terraform reads `.tf` files automatically.

---

# 2️⃣ INSTALL TERRAFORM (VERIFY)

Check installation:

```
terraform -v
```

If installed correctly → version shows.

---

# 3️⃣ YOUR FIRST TERRAFORM CONFIG

Create file:

```
main.tf
```

Paste:

```
provider "aws" {
  region = "ap-south-1"
}

resource "aws_instance" "web" {
  ami           = "ami-0abcdef1234567890"
  instance_type = "t2.micro"
}
```

This describes:

> one EC2 instance should exist.

---

# 4️⃣ INITIALIZE TERRAFORM

Run:

```
terraform init
```

What it does:

```
downloads provider plugins
creates .terraform folder
prepares working directory
```

Must run before anything else.

---

# 5️⃣ PLAN INFRASTRUCTURE

Run:

```
terraform plan
```

Example output:

```
+ aws_instance.web will be created
```

Terraform shows exact changes before applying.

Always review plan.

---

# 6️⃣ APPLY INFRASTRUCTURE

Run:

```
terraform apply
```

Confirm:

```
yes
```

Terraform will:

```
call AWS API
create server
store state
```

Now infrastructure exists.

---

# 7️⃣ VERIFY CREATED RESOURCE

Check cloud console or CLI.

Terraform already stored resource info inside:

```
terraform.tfstate
```

State contains:

```
instance ID
IP address
metadata
```

---

# 8️⃣ MODIFY INFRASTRUCTURE

Change instance type:

```
instance_type = "t3.micro"
```

Run:

```
terraform plan
```

Output:

```
~ modify instance type
```

Apply change:

```
terraform apply
```

Terraform updates infrastructure safely.

---

# 9️⃣ ADD SECOND RESOURCE

Add new block:

```
resource "aws_s3_bucket" "data" {
  bucket = "my-bucket-demo"
}
```

Plan again:

```
terraform plan
```

Terraform now shows:

```
+ create bucket
```

Multiple resources managed together.

---

# 🔟 DESTROY INFRASTRUCTURE

Remove everything:

```
terraform destroy
```

Terraform deletes all managed resources safely.

Important for:

* cleanup
* testing
* cost control

---

# 11️⃣ VARIABLES — PARAMETERIZE CONFIG

Create:

```
variables.tf
```

```
variable "instance_type" {
  default = "t2.micro"
}
```

Use it:

```
instance_type = var.instance_type
```

Now config reusable.

---

# 12️⃣ PASS VARIABLE VALUES

Override variable:

```
terraform apply -var="instance_type=t3.micro"
```

Used for different environments.

---

# 13️⃣ OUTPUT VALUES

Create:

```
outputs.tf
```

```
output "instance_ip" {
  value = aws_instance.web.public_ip
}
```

After apply:

Terraform prints IP automatically.

---

# 14️⃣ DEPENDENCY HANDLING

Terraform auto-detects dependencies.

Example:

```
server depends on network
```

Terraform creates network first automatically.

You don’t manage order.

Terraform builds dependency graph internally.

---

# 15️⃣ FORMAT CONFIG (PRO TIP)

Format code:

```
terraform fmt
```

Ensures clean readable config.

---

# 16️⃣ VALIDATE CONFIG

Check syntax:

```
terraform validate
```

Catches errors before execution.

---

# 17️⃣ REFRESH STATE

Sync state with real infra:

```
terraform refresh
```

Useful if infra changed outside Terraform.

---

# 18️⃣ IMPORT EXISTING RESOURCE

If resource already exists:

```
terraform import aws_instance.web i-123456
```

Terraform begins managing it.

---

# 19️⃣ REAL ENGINEER WORKFLOW

Daily Terraform workflow:

```
write config
terraform init
terraform plan
review changes
terraform apply
verify infra
```

Never skip plan.

---

# 20️⃣ BASIC COMPLETION CHECK

You can now:

✔ write Terraform configs
✔ create infrastructure
✔ modify resources
✔ destroy infrastructure
✔ use variables
✔ output values
✔ validate configs
✔ manage state

---

# FINAL LINE

At this point:

> Terraform is no longer theory.

You can create real infrastructure using code.

That’s real DevOps skill.

---

END OF LEVEL 2 — TERRAFORM BASIC
