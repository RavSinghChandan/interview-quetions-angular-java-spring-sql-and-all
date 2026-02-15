Excellent.
Now we move into **Cloud Security Maturity**.

This is one of the strongest senior signals.

---

# 📁 File: `09-iam-roles-least-privilege-security-deep.md`

# 🔥 STEP 9 — IAM Roles + Least Privilege + Cloud Security Deep Dive

(Temporary Credentials + Policy Design + Backend Security Patterns)

Interviewers may ask:

* How does your app access S3 securely?
* What is IAM role vs user?
* What is least privilege?
* What are temporary credentials?
* How do you avoid credential leakage?

You must answer confidently.

---

# 🧠 1️⃣ What Is IAM?

IAM = Identity and Access Management.

It controls:

✔ Who can access what
✔ What actions allowed
✔ On which resources

IAM is the backbone of cloud security.

---

# 🧠 2️⃣ IAM User vs IAM Role

### IAM User

* Permanent identity
* Access keys
* Long-lived credentials

Bad practice for applications.

---

### IAM Role

* Temporary credentials
* Assumed by service (EC2, Lambda, EKS pod)
* Rotated automatically

Best practice for backend apps.

---

# 🧠 3️⃣ Never Hardcode Credentials

❌ Wrong:

```java
AmazonS3ClientBuilder.standard()
    .withCredentials(new AWSStaticCredentialsProvider(
        new BasicAWSCredentials("ACCESS_KEY", "SECRET_KEY")
    ));
```

Keys can leak.

✔ Correct:

Attach IAM role to instance or pod.

SDK automatically fetches temporary credentials.

---

# 🧠 4️⃣ IAM Policy Structure

Example policy:

```json
{
  "Effect": "Allow",
  "Action": "s3:GetObject",
  "Resource": "arn:aws:s3:::my-bucket/*"
}
```

Fields:

* Effect (Allow/Deny)
* Action
* Resource

Always restrict to specific resource.

---

# 🧠 5️⃣ Principle of Least Privilege

Grant:

Only required permissions.

Example:

If service only reads from S3:

Allow only:

```
s3:GetObject
```

Not:

```
s3:*
```

Senior-level security mindset.

---

# 🧠 6️⃣ IAM Role for EC2

Attach role to EC2 instance.

Then inside app:

```java
AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
```

No keys needed.

SDK retrieves credentials from metadata service.

---

# 🧠 7️⃣ IAM Role for Lambda

Lambda automatically gets IAM role attached.

Used for:

* Access S3
* Publish SNS
* Read from DynamoDB

No key management required.

---

# 🧠 8️⃣ IAM Role for Kubernetes Pods (IRSA)

In EKS:

Use IAM Role for Service Account.

YAML:

```yaml
apiVersion: v1
kind: ServiceAccount
metadata:
  name: s3-reader
  annotations:
    eks.amazonaws.com/role-arn: arn:aws:iam::123456:role/S3AccessRole
```

Pods using this SA get temporary credentials.

Best practice for microservices.

---

# 🧠 9️⃣ Temporary Credentials

IAM roles provide:

* Access key
* Secret key
* Session token
* Expiration time

Automatically rotated.

Safer than static keys.

---

# 🧠 🔟 Resource-Based vs Identity-Based Policies

Identity-based:

Attached to user/role.

Resource-based:

Attached to resource (e.g., S3 bucket policy).

Both can work together.

---

# 🧠 1️⃣1️⃣ Cross-Service Access Example

Backend wants:

* Read S3
* Publish SNS

Policy should allow only:

```
s3:GetObject
sns:Publish
```

Nothing more.

Avoid wildcard.

---

# 🧠 1️⃣2️⃣ Common Security Mistakes

✘ Hardcoding credentials in code
✘ Granting full admin access
✘ Making S3 bucket public
✘ Not rotating keys
✘ Over-permissive policies

Senior engineers avoid these.

---

# 🧠 1️⃣3️⃣ Real Interview Scenario

Question:

“How does your Kubernetes pod securely access S3?”

Strong answer:

* Use IAM role for service account (IRSA)
* Attach least privilege policy
* No static credentials
* Temporary credentials auto-rotated

Clear and mature.

---

# 🧠 1️⃣4️⃣ Encryption Awareness

Enable:

✔ Encryption at rest (S3, RDS)
✔ TLS for data in transit
✔ KMS key management

Security not just IAM.

---

# 🧠 1️⃣5️⃣ Senior-Level Mental Model

IAM = access gatekeeper
Role = temporary identity
Policy = permission contract
Least privilege = safety principle
Temporary credentials = best practice

Security is design responsibility.

---

# 🎯 STEP 9 REVISION CHECKLIST

You must confidently explain:

✔ IAM user vs role
✔ Least privilege principle
✔ Policy structure
✔ IAM role for EC2
✔ IAM role for Lambda
✔ IRSA for Kubernetes
✔ Temporary credentials
✔ Common mistakes
✔ Encryption basics

If you can explain all clearly →
Cloud security maturity achieved.

---

When ready, type:

**10**

Next file:

📁 `10-cloud-monitoring-high-availability-dr.md`
(CloudWatch + monitoring + multi-AZ HA + multi-region DR + RTO/RPO deep dive)
