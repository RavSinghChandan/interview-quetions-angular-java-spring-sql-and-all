# DOCKER — LEVEL 2 (BASIC PRACTICAL)

**Stage Title: “You Run Your First Containers — For Real”**

*(Now theory ends. From here you touch real containers. Everything is practical and command-driven.)*

---

# 🎯 OBJECTIVE OF THIS LEVEL

By the end you will be able to:

* run containers
* pull images
* build images
* inspect containers
* debug containers
* manage lifecycle

This level turns Docker from concept → tool.

---

# 1️⃣ VERIFY DOCKER IS INSTALLED

```
docker --version
```

If installed correctly:

```
Docker version 24.x.x
```

Check daemon:

```
docker info
```

---

# 2️⃣ RUN YOUR FIRST CONTAINER (HELLO WORLD)

```
docker run hello-world
```

What happens internally:

```
1. Docker looks for image locally
2. Not found → pulls from Docker Hub
3. Creates container
4. Runs program
5. Prints output
6. Stops container
```

---

# 3️⃣ SEE ALL CONTAINERS

Running containers:

```
docker ps
```

All containers:

```
docker ps -a
```

---

# 4️⃣ RUN INTERACTIVE CONTAINER

Start Ubuntu shell:

```
docker run -it ubuntu bash
```

Now you’re inside container.

Try commands:

```
ls
pwd
apt update
```

Exit:

```
exit
```

Container stops.

---

# 5️⃣ RUN CONTAINER IN BACKGROUND

```
docker run -d nginx
```

Check running:

```
docker ps
```

Stop it:

```
docker stop containerID
```

---

# 6️⃣ NAME CONTAINERS

Instead of random names:

```
docker run -d --name mynginx nginx
```

Stop by name:

```
docker stop mynginx
```

---

# 7️⃣ REMOVE CONTAINERS

Delete stopped container:

```
docker rm mynginx
```

Delete all stopped:

```
docker container prune
```

---

# 8️⃣ LIST IMAGES

```
docker images
```

Output:

```
REPOSITORY   TAG   IMAGE ID   SIZE
nginx        latest
ubuntu       latest
```

---

# 9️⃣ DELETE IMAGE

```
docker rmi nginx
```

Force delete:

```
docker rmi -f imageID
```

---

# 🔟 PULL IMAGE WITHOUT RUNNING

```
docker pull redis
```

---

# 11️⃣ RUN WEB SERVER + PORT MAPPING

Run nginx and expose port:

```
docker run -d -p 8080:80 nginx
```

Meaning:

```
host:8080 → container:80
```

Open browser:

```
http://localhost:8080
```

---

# 12️⃣ EXEC INTO RUNNING CONTAINER

```
docker exec -it containerID bash
```

Inspect inside container:

```
ls
ps aux
```

---

# 13️⃣ VIEW CONTAINER LOGS

```
docker logs containerID
```

Follow logs:

```
docker logs -f containerID
```

---

# 14️⃣ INSPECT CONTAINER DETAILS

```
docker inspect containerID
```

Shows:

* IP address
* mounts
* config
* network
* environment

---

# 15️⃣ BUILD YOUR FIRST IMAGE

Create file:

```
nano app.py
```

Paste:

```
print("Hello from container")
```

Create Dockerfile:

```
nano Dockerfile
```

Paste:

```
FROM python:3.10
COPY app.py /app.py
CMD ["python","/app.py"]
```

Build image:

```
docker build -t myapp .
```

Run it:

```
docker run myapp
```

---

# 16️⃣ DOCKERFILE EXPLAINED

```
FROM python:3.10
```

Base environment

```
COPY app.py /app.py
```

Copy file into image

```
CMD ["python","/app.py"]
```

Command when container starts

---

# 17️⃣ MODIFY + REBUILD IMAGE

Change code:

```
print("Version 2")
```

Rebuild:

```
docker build -t myapp .
```

Run again:

```
docker run myapp
```

---

# 18️⃣ MOUNT LOCAL FILE INTO CONTAINER

```
docker run -it -v $(pwd):/app ubuntu bash
```

Inside container:

```
cd /app
ls
```

You see host files.

---

# 19️⃣ CONTAINER RESOURCE LIMITS

Limit memory:

```
docker run -m 256m nginx
```

Limit CPU:

```
docker run --cpus="1.5" nginx
```

---

# 20️⃣ CLEAN EVERYTHING (RESET MACHINE)

Stop all:

```
docker stop $(docker ps -aq)
```

Delete containers:

```
docker rm $(docker ps -aq)
```

Delete images:

```
docker rmi $(docker images -q)
```

---

# 🧠 REAL ENGINEER WORKFLOW

Daily Docker workflow:

```
docker build
docker run
docker logs
docker exec
docker stop
docker rm
```

Memorize this loop.

---

# 🏁 BASIC COMPLETION CHECK

You can now:

✔ run containers
✔ build images
✔ inspect containers
✔ debug logs
✔ map ports
✔ mount volumes
✔ limit resources
✔ delete containers/images

---

# FINAL LINE

At this point:

> Docker is no longer theory.

You can run real containers like a real engineer.

---

END OF DOCKER LEVEL 2 — BASIC PRACTICAL
