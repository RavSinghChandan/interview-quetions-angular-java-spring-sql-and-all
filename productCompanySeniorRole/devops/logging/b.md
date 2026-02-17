# LOGGING — LEVEL 2 (BASIC PRACTICAL)

**Stage Title: “You Generate and Analyze Real Logs Like Real Engineers”**

*(Now theory ends. You start generating real logs, collecting them, and analyzing them like engineers do in production.)*

---

# 🎯 OBJECTIVE OF THIS LEVEL

After this stage you will be able to:

* generate logs from applications
* structure logs properly
* store logs centrally
* search logs
* filter logs
* debug systems using logs

You move from:

```
Understanding Logging → Using Logging
```

Everything below is practical and real.

---

# 1️⃣ GENERATE YOUR FIRST REAL LOG

Simple Node.js example:

```
console.log("Server started")
```

Output:

```
Server started
```

This is a log.

But this is **not production logging** yet.

Why?

Because it lacks:

* timestamp
* level
* structure
* context

---

# 2️⃣ PROPER LOG FORMAT

Production log must include:

```
timestamp level service message context
```

Example:

```
2026-02-17T10:21:33Z INFO auth-service login success user=42
```

This is readable + searchable.

---

# 3️⃣ USING A REAL LOGGER (NODE EXAMPLE)

Install logger:

```
npm install winston
```

Code:

```
const winston = require("winston");

const logger = winston.createLogger({
 level: "info",
 format: winston.format.json(),
 transports: [new winston.transports.Console()]
});

logger.info("server started");
logger.error("database failed");
```

Output:

```
{"level":"info","message":"server started"}
{"level":"error","message":"database failed"}
```

Structured logs.

---

# 4️⃣ LOG LEVEL CONTROL

Change level:

```
level: "error"
```

Now only errors printed.

This prevents log noise in production.

---

# 5️⃣ ADD CONTEXT DATA

Better log:

```
logger.info("user login", { userId: 42, ip: "1.2.3.4" });
```

Output:

```
{
 "level":"info",
 "message":"user login",
 "userId":42,
 "ip":"1.2.3.4"
}
```

Context makes logs powerful.

---

# 6️⃣ LOG FILE STORAGE

Save logs to file:

```
transports: [
 new winston.transports.File({ filename: "app.log" })
]
```

Now logs persist.

Check:

```
cat app.log
```

---

# 7️⃣ SEARCH LOGS USING CLI

Find errors:

```
grep ERROR app.log
```

Find user logs:

```
grep userId app.log
```

Filtering logs is core debugging skill.

---

# 8️⃣ TAIL LIVE LOGS

Watch logs in real time:

```
tail -f app.log
```

Used during production debugging.

Shows live events.

---

# 9️⃣ MULTIPLE LOG OUTPUTS

Production apps log to:

```
console
file
remote server
```

Example:

```
transports: [
 consoleTransport,
 fileTransport
]
```

Logs must be available everywhere.

---

# 🔟 CENTRALIZED LOGGING (REAL SYSTEM)

Production systems send logs to central server.

Example architecture:

```
App → Log Agent → Log Server → Search UI
```

Common stack:

```
App → Filebeat → Elasticsearch → Kibana
```

This is industry standard.

---

# 11️⃣ RUN SIMPLE CENTRAL LOG STACK (DOCKER)

Run Elasticsearch:

```
docker run -p 9200:9200 elasticsearch
```

Run Kibana:

```
docker run -p 5601:5601 kibana
```

Now logs can be stored + searched centrally.

---

# 12️⃣ STRUCTURED SEARCHING

Example query:

```
level:error AND service:auth
```

Returns only auth service errors.

Structured logs enable powerful filtering.

---

# 13️⃣ TRACE SINGLE USER REQUEST

Logs allow request tracing.

Example:

```
request_id = abc123
```

Search:

```
request_id:abc123
```

Shows full request journey.

Critical for debugging.

---

# 14️⃣ LOG ROTATION (IMPORTANT)

Logs grow forever.

Need rotation.

Linux rotation:

```
logrotate
```

Example policy:

```
rotate daily
keep 7 days
delete old
```

Prevents disk full issues.

---

# 15️⃣ COMMON BEGINNER LOGGING MISTAKES

Avoid:

❌ logging everything
❌ logging nothing
❌ logging secrets
❌ inconsistent formats
❌ missing timestamps

Good logging is intentional.

---

# 16️⃣ WHAT PROFESSIONAL LOGS LOOK LIKE

Good production log:

```
{
 timestamp:"2026-02-17T10:22:00Z",
 level:"ERROR",
 service:"payment",
 message:"transaction failed",
 user:42,
 order:991,
 reason:"timeout"
}
```

One log line = complete event.

---

# 17️⃣ REAL ENGINEER DEBUG FLOW USING LOGS

When bug occurs:

```
Check recent logs
Find error entries
Filter by request
Trace execution path
Identify failure step
```

Logs reconstruct event history.

---

# 18️⃣ LOGGING BEST PRACTICE RULE

Golden rule:

> log events, not guesses.

Bad log:

```
something wrong
```

Good log:

```
db connection timeout after 3s
```

Specific logs solve problems faster.

---

# 19️⃣ WHAT YOU JUST BUILT

You now know how to:

```
generate logs
structure logs
store logs
search logs
trace events
```

That is real logging workflow.

---

# 20️⃣ BASIC COMPLETION CHECK

You can now:

✔ generate real logs
✔ structure logs properly
✔ store logs in files
✔ search logs
✔ filter logs
✔ trace requests
✔ debug using logs
✔ build logging pipeline

---

# FINAL LINE

At this point:

> Logging is no longer theory.

You can produce and analyze real logs.

That is real DevOps skill.

---

END OF LEVEL 2 — LOGGING BASIC
