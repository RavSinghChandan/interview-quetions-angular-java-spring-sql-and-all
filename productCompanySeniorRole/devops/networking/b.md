# NETWORKING — LEVEL 2 (BASIC PRACTICAL)

**Stage Title: “You Watch Data Travel Between Machines”**

*(Now theory ends. You start using real networking tools and observe real communication like engineers do.)*

---

# 🎯 OBJECTIVE OF THIS LEVEL

After this stage you will be able to:

* inspect real network connections
* test connectivity
* debug basic network issues
* trace request paths
* inspect ports
* analyze real packet flow

You move from:

```
Understanding Networking → Seeing Networking
```

Everything below is real, practical, production-level basics.

---

# 1️⃣ CHECK YOUR MACHINE IP

Command:

```
ip a
```

or

```
ifconfig
```

Output example:

```
inet 192.168.1.12/24
```

This is your local network identity.

If your IP missing → network not configured.

---

# 2️⃣ CHECK INTERNET CONNECTIVITY

Command:

```
ping google.com
```

Example output:

```
64 bytes from 142.250.183.14: icmp_seq=1 ttl=118 time=18 ms
```

This tells you:

```
destination reachable
latency = 18ms
```

If ping fails → network problem exists.

---

# 3️⃣ TRACE ROUTE PATH

Command:

```
traceroute google.com
```

Shows every hop:

```
router1
router2
ISP node
international node
destination
```

This reveals path packets take.

Useful for diagnosing routing issues.

---

# 4️⃣ TEST WEBSITE RESPONSE

Command:

```
curl -I https://google.com
```

Output:

```
HTTP/1.1 200 OK
```

This confirms:

```
DNS working
network working
TLS working
server reachable
```

Curl is one of the most powerful debugging tools.

---

# 5️⃣ CHECK OPEN PORTS ON YOUR MACHINE

Command:

```
ss -tuln
```

Output example:

```
LISTEN 0 128 0.0.0.0:22
```

Meaning:

```
SSH running on port 22
```

This shows which services are listening.

---

# 6️⃣ CHECK PROCESS USING PORT

Command:

```
lsof -i :3000
```

Shows:

```
PID
process name
user
```

Used when port already in use error occurs.

---

# 7️⃣ TEST PORT CONNECTIVITY

Command:

```
nc -zv google.com 443
```

Output:

```
connection succeeded
```

This checks if specific port reachable.

Useful for firewall debugging.

---

# 8️⃣ CHECK DNS RESOLUTION

Command:

```
nslookup google.com
```

Output:

```
Address: 142.250.183.14
```

This confirms DNS working.

If DNS fails → domains won’t load.

---

# 9️⃣ SEE ACTIVE CONNECTIONS

Command:

```
netstat -an
```

Shows:

```
active connections
remote IPs
ports
states
```

Useful for debugging:

* hanging connections
* stuck sockets
* connection leaks

---

# 🔟 CHECK DOWNLOAD SPEED

Command:

```
curl -o /dev/null https://speed.hetzner.de/100MB.bin
```

Shows transfer speed.

Used for testing bandwidth.

---

# 11️⃣ CAPTURE PACKETS (INTRO)

Command:

```
tcpdump -i any
```

Shows real packets flowing.

Example:

```
IP 192.168.1.12 → 142.250.183.14
```

You are literally watching network traffic.

---

# 12️⃣ TEST LOCAL SERVER CONNECTION

Start local server:

```
python3 -m http.server 8000
```

Test:

```
curl localhost:8000
```

This confirms:

```
server running
port open
network stack working
```

---

# 13️⃣ COMMON NETWORK ERRORS YOU CAN NOW DIAGNOSE

You can now debug:

```
site not loading
server unreachable
port blocked
DNS failure
connection timeout
slow response
```

These are real production issues.

---

# 14️⃣ REAL DEBUGGING ORDER (IMPORTANT)

Professional engineers debug network in order:

```
1. Check local IP
2. Ping destination
3. Test DNS
4. Test port
5. Trace route
6. Check firewall
7. Inspect logs
```

Never random debugging.

Always layered.

---

# 15️⃣ FIREWALL BLOCK DETECTION

If ping works but port fails:

```
network reachable
service blocked
```

Likely firewall or server issue.

---

# 16️⃣ LATENCY TESTING

Command:

```
ping -c 5 google.com
```

Check:

```
avg latency
packet loss
jitter
```

Helps detect unstable networks.

---

# 17️⃣ LOCALHOST VS PUBLIC NETWORK

Test local:

```
curl localhost
```

Test external:

```
curl public-ip
```

If local works but external fails:

Problem = firewall / routing / port exposure.

---

# 18️⃣ REAL ENGINEER WORKFLOW

Daily networking checks engineers perform:

```
test service connectivity
inspect ports
check routes
validate DNS
measure latency
trace failures
```

Networking tools are everyday tools.

---

# 19️⃣ WHAT YOU JUST LEARNED

You can now:

* test connectivity
* trace routes
* inspect ports
* analyze connections
* debug network failures

This is core production debugging skill.

---

# 20️⃣ BASIC COMPLETION CHECK

You can now:

✔ test network connectivity
✔ check IP addresses
✔ verify DNS
✔ inspect open ports
✔ trace routes
✔ capture packets
✔ debug network issues
✔ verify services

---

# FINAL LINE

At this point:

> Networking is no longer invisible.

You can see it.

And engineers who can see networks…

are the ones who can fix systems.

---

END OF LEVEL 2 — NETWORKING BASIC
