# MONITORING — LEVEL 2 (BASIC PRACTICAL)

**Stage Title: “You Collect Real Metrics From Real Systems”**

*(Now theory ends. You start monitoring real machines and applications like real engineers.)*

---

# 🎯 OBJECTIVE OF THIS LEVEL

After this stage you will be able to:

* collect system metrics
* run monitoring stack
* visualize real data
* understand metric queries
* monitor applications
* verify system health

You move from:

```
Understanding Monitoring → Using Monitoring
```

Everything below is real, practical, and production-style.

---

# 1️⃣ REAL MONITORING STACK

A real monitoring stack contains:

```
Exporter → Collector → Database → Dashboard
```

Example real-world stack:

```
Node Exporter → Prometheus → Time-series DB → Grafana
```

You’ll now simulate this flow.

---

# 2️⃣ RUN NODE EXPORTER (COLLECT SYSTEM METRICS)

Run exporter:

```
docker run -d -p 9100:9100 prom/node-exporter
```

Check metrics:

```
curl localhost:9100/metrics
```

You’ll see raw metrics:

```
node_cpu_seconds_total
node_memory_MemAvailable_bytes
node_disk_io_time_seconds_total
```

These are real system signals.

---

# 3️⃣ UNDERSTANDING RAW METRICS

Metrics format:

```
metric_name value
```

Example:

```
node_cpu_seconds_total 12345
```

Monitoring systems only understand numbers.

---

# 4️⃣ RUN PROMETHEUS (COLLECTOR)

Create config file:

```
prometheus.yml
```

```
global:
  scrape_interval: 5s

scrape_configs:
  - job_name: "node"
    static_configs:
      - targets: ["host.docker.internal:9100"]
```

Run Prometheus:

```
docker run -p 9090:9090 \
-v $(pwd)/prometheus.yml:/etc/prometheus/prometheus.yml \
prom/prometheus
```

Open:

```
http://localhost:9090
```

You now have real monitoring system.

---

# 5️⃣ QUERY METRICS (PROMQL)

Example queries:

CPU usage:

```
rate(node_cpu_seconds_total[1m])
```

Memory available:

```
node_memory_MemAvailable_bytes
```

Disk usage:

```
node_filesystem_size_bytes
```

PromQL is monitoring query language.

---

# 6️⃣ INSTALL GRAFANA (VISUALIZATION)

Run:

```
docker run -d -p 3000:3000 grafana/grafana
```

Login:

```
admin / admin
```

Add data source:

```
Prometheus → http://host.docker.internal:9090
```

---

# 7️⃣ CREATE DASHBOARD

Add panel → Query:

```
node_memory_MemAvailable_bytes
```

Graph shows memory in real time.

You’re now observing live system.

---

# 8️⃣ MONITOR CPU USAGE

Query:

```
100 - (avg by (instance) (rate(node_cpu_seconds_total{mode="idle"}[1m])) * 100)
```

This shows actual CPU usage.

---

# 9️⃣ MONITOR DISK USAGE

Query:

```
node_filesystem_avail_bytes
```

Helps detect disk-full issues before crash.

---

# 🔟 MONITOR NETWORK TRAFFIC

Query:

```
rate(node_network_receive_bytes_total[1m])
```

Shows network throughput.

---

# 11️⃣ ADD ALERT RULE

Example alert rule:

```
if CPU > 80% for 2 minutes → alert
```

Prometheus rule:

```
alert: HighCPU
expr: cpu_usage > 80
for: 2m
```

Monitoring now reacts automatically.

---

# 12️⃣ APPLICATION METRICS

Apps can expose metrics endpoint:

Example endpoint:

```
/metrics
```

Example output:

```
http_requests_total 500
active_users 21
```

Prometheus scrapes this automatically.

---

# 13️⃣ SIMPLE APP METRICS EXAMPLE (NODE APP)

Example code:

```
const client = require('prom-client');
const counter = new client.Counter({ name: 'requests', help: 'total requests' });
counter.inc();
```

App now emits metrics.

Monitoring sees it.

---

# 14️⃣ VERIFY TARGET HEALTH

Check scrape targets:

```
http://localhost:9090/targets
```

If status:

```
UP
```

Monitoring working.

If:

```
DOWN
```

Target unreachable.

---

# 15️⃣ BASIC DEBUGGING

If metrics missing:

Check:

```
Is exporter running?
Is port open?
Is Prometheus config correct?
```

Monitoring failures are usually configuration errors.

---

# 16️⃣ MONITOR MULTIPLE SERVERS

Add more targets:

```
targets:
  - server1:9100
  - server2:9100
  - server3:9100
```

Prometheus monitors all simultaneously.

---

# 17️⃣ REAL ENGINEER WORKFLOW

Daily monitoring workflow:

```
check dashboards
review alerts
investigate anomalies
analyze trends
optimize system
```

Monitoring is daily tool, not occasional tool.

---

# 18️⃣ REAL PRODUCTION METRICS ENGINEERS WATCH

Critical metrics:

```
CPU usage
memory usage
error rate
latency
request volume
disk usage
```

These tell system health instantly.

---

# 19️⃣ WHAT YOU JUST BUILT

You built real monitoring system:

```
Exporter → Prometheus → Grafana
```

This is same architecture used in:

* startups
* enterprises
* cloud systems
* Kubernetes clusters

---

# 20️⃣ BASIC COMPLETION CHECK

You can now:

✔ collect real metrics
✔ run monitoring stack
✔ query metrics
✔ visualize data
✔ monitor system health
✔ add alerts
✔ monitor apps
✔ debug monitoring issues

---

# FINAL LINE

At this point:

> Monitoring is no longer theory.

You can observe real systems in real time.

That is real DevOps skill.

---

END OF LEVEL 2 — MONITORING BASIC
