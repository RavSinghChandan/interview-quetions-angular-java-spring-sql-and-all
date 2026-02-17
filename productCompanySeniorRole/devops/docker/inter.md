# DOCKER — LEVEL 3 (INTERMEDIATE PRACTICAL)

**Stage Title: “You Run Real Applications — Not Just Containers”**

*(Now you stop running single containers and start running real systems like production engineers.)*

---

# 🎯 OBJECTIVE OF THIS LEVEL

By the end you will be able to:

* run multi-container apps
* create container networks
* manage persistent storage
* run databases in containers
* connect services together
* debug real app stacks

This is where Docker becomes **real-world engineering skill**.

---

# 1️⃣ REALITY CHECK — REAL APPS ARE NEVER ONE CONTAINER

Real applications need multiple services:

Example backend app needs:

```
app
database
cache
queue
reverse proxy
```

So professionals don’t run one container.

They run **container systems**.

---

# 2️⃣ CREATE YOUR FIRST NETWORK

Create network:

```
docker network create appnet
```

Check:

```
docker network ls
```

Why network?

Because containers talk to each other via network.

---

# 3️⃣ RUN DATABASE CONTAINER

```
docker run -d \
  --name db \
  --network appnet \
  -e POSTGRES_PASSWORD=secret \
  postgres
```

Now Postgres is running inside isolated environment.

---

# 4️⃣ RUN BACKEND CONTAINER CONNECTED TO DB

```
docker run -d \
  --name backend \
  --network appnet \
  -e DB_HOST=db \
  node:18
```

Important insight:

```
DB_HOST=db
```

means container connects using container name as hostname.

Docker DNS automatically resolves it.

---

# 5️⃣ TEST CONNECTION BETWEEN CONTAINERS

Enter backend:

```
docker exec -it backend bash
```

Ping database:

```
ping db
```

This proves containers can communicate internally.

---

# 6️⃣ VOLUMES — REAL DATA PERSISTENCE

Run database with volume:

```
docker run -d \
  --name db \
  --network appnet \
  -v pgdata:/var/lib/postgresql/data \
  -e POSTGRES_PASSWORD=secret \
  postgres
```

Create volume manually:

```
docker volume create pgdata
```

List volumes:

```
docker volume ls
```

Remove volume:

```
docker volume rm pgdata
```

---

# 7️⃣ WHY VOLUMES MATTER

Without volume:

Delete container → data gone

With volume:

Delete container → data stays

This is mandatory for:

* databases
* uploads
* logs
* stateful apps

---

# 8️⃣ MULTI-CONTAINER APP USING DOCKER COMPOSE

Create file:

```
nano docker-compose.yml
```

Paste:

```
version: "3"

services:
  db:
    image: postgres
    environment:
      POSTGRES_PASSWORD: secret
    volumes:
      - pgdata:/var/lib/postgresql/data

  backend:
    image: node:18
    depends_on:
      - db

volumes:
  pgdata:
```

Run stack:

```
docker compose up
```

Detached:

```
docker compose up -d
```

Stop:

```
docker compose down
```

---

# 9️⃣ WHY COMPOSE IS POWERFUL

Without compose:

You run many long commands.

With compose:

You define system in file.

This is:

> infrastructure as code.

---

# 🔟 CHECK RUNNING STACK

```
docker compose ps
```

Logs:

```
docker compose logs
```

Logs of one service:

```
docker compose logs backend
```

---

# 11️⃣ REBUILD SERVICE

```
docker compose build
```

Restart service:

```
docker compose restart backend
```

---

# 12️⃣ SCALE CONTAINERS

Run multiple backend instances:

```
docker compose up --scale backend=3
```

Now you have 3 backend containers.

This is real scaling.

---

# 13️⃣ CUSTOM IMAGE IN COMPOSE

Update compose file:

```
backend:
  build: .
```

Build + run:

```
docker compose up --build
```

---

# 14️⃣ DEBUGGING MULTI-CONTAINER SYSTEM

List containers:

```
docker ps
```

Inspect network:

```
docker network inspect appnet
```

Check logs:

```
docker logs containerID
```

---

# 15️⃣ ENV FILE SUPPORT

Create env file:

```
nano .env
```

```
POSTGRES_PASSWORD=secret
PORT=5000
```

Use in compose automatically.

---

# 16️⃣ RESOURCE LIMITS IN COMPOSE

```
services:
  backend:
    deploy:
      resources:
        limits:
          memory: 512M
```

---

# 17️⃣ REAL DEBUG FLOW (IMPORTANT)

When container system fails:

Check:

```
docker ps
docker logs
docker inspect
docker network inspect
docker stats
```

This is real engineer toolkit.

---

# 18️⃣ CONTAINER STATS MONITORING

```
docker stats
```

Shows:

* CPU usage
* memory usage
* network I/O
* disk I/O

Used to detect bottlenecks.

---

# 19️⃣ RESTART POLICIES

Auto restart container:

```
docker run -d --restart always nginx
```

Options:

```
no
always
on-failure
unless-stopped
```

Production systems use restart policies.

---

# 20️⃣ REAL WORLD MINI ARCHITECTURE YOU CAN NOW RUN

You can now run locally:

```
Nginx
Backend API
Database
Cache
Worker
Queue
```

All isolated.
All connected.
All reproducible.

That’s production simulation.

---

# 🏁 INTERMEDIATE COMPLETION CHECK

You can now:

✔ run multi-container apps
✔ connect containers via network
✔ persist data with volumes
✔ orchestrate services using compose
✔ scale containers
✔ debug container systems
✔ monitor performance

---

# FINAL LINE

At this point:

> You’re no longer running containers.

You’re running systems.

That is real Docker skill.

---

END OF DOCKER LEVEL 3 — INTERMEDIATE
