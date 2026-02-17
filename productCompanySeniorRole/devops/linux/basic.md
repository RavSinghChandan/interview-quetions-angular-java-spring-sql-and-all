# LINUX — LEVEL 2 (BASIC)

**Stage Title: “The Day You Start Controlling the Machine”**

*(This continues your Linux story. Now you don’t just understand Linux — you begin commanding it.)*

---

## CHAPTER CONTEXT — WHAT CHANGES AT BASIC LEVEL

In the story, this is the moment when:

> Linux stops being something you observe
> and becomes something you control.

At naive level you learned **what Linux is**.
At basic level you learn **how to operate it**.

This stage is about **control fundamentals**.

---

# 1. THE FIRST SKILL — KNOWING WHERE YOU ARE

Every Linux session begins with location awareness.

Check your current location:

```
pwd
```

List what exists there:

```
ls
```

Detailed view:

```
ls -l
```

Hidden files too:

```
ls -la
```

Move somewhere:

```
cd /var/log
```

Move back:

```
cd ..
```

Go home:

```
cd ~
```

Rule:

> Engineers who don’t know where they are cannot debug systems.

---

# 2. LEARNING TO CREATE AND REMOVE THINGS

Create file:

```
touch notes.txt
```

Create folder:

```
mkdir project
```

Create nested folders:

```
mkdir -p app/src/config
```

Delete file:

```
rm file.txt
```

Delete folder:

```
rm -r folder
```

Rename:

```
mv old.txt new.txt
```

Copy:

```
cp file.txt backup.txt
```

---

# 3. READING WHAT THE MACHINE WRITES

Linux communicates through files.

Read file:

```
cat file.txt
```

Scrollable view:

```
less file.txt
```

First lines:

```
head file.txt
```

Last lines:

```
tail file.txt
```

Live logs:

```
tail -f server.log
```

Real-world meaning:

> Logs are Linux talking to you.

---

# 4. SEARCHING — FINDING SIGNAL IN NOISE

Find file:

```
find / -name config.yml
```

Search text:

```
grep ERROR app.log
```

Case-insensitive:

```
grep -i error app.log
```

Recursive search:

```
grep -r database .
```

---

# 5. PERMISSIONS — WHY ACCESS FAILS

Check permissions:

```
ls -l
```

Example:

```
-rwxr-xr--
```

Change permission:

```
chmod +x script.sh
```

Numeric permission:

```
chmod 755 script.sh
```

Change ownership:

```
chown user file.txt
```

Reality:

> Most production failures happen because of wrong permissions.

---

# 6. PROCESSES — SEEING RUNNING PROGRAMS

View processes:

```
ps aux
```

Real-time system:

```
top
```

Better monitor:

```
htop
```

Kill process:

```
kill PID
```

Force kill:

```
kill -9 PID
```

Find process:

```
ps aux | grep java
```

---

# 7. SYSTEM HEALTH CHECK

Memory:

```
free -m
```

CPU + load:

```
uptime
```

Disk:

```
df -h
```

Folder sizes:

```
du -sh *
```

System info:

```
uname -a
```

---

# 8. SERVICES — PROGRAMS THAT RUN FOREVER

Check service:

```
systemctl status nginx
```

Start:

```
systemctl start nginx
```

Stop:

```
systemctl stop nginx
```

Restart:

```
systemctl restart nginx
```

Enable on boot:

```
systemctl enable nginx
```

---

# 9. NETWORK CHECKING

Check IP:

```
ip a
```

Test connection:

```
ping google.com
```

Check open ports:

```
ss -tulnp
```

Test API:

```
curl localhost:8080
```

DNS lookup:

```
nslookup google.com
```

---

# 10. INSTALLING SOFTWARE

Update package list:

```
sudo apt update
```

Install program:

```
sudo apt install nginx
```

Remove:

```
sudo apt remove nginx
```

This is how servers get tools.

---

# 11. CONNECTING TO SERVERS

Remote login:

```
ssh user@server-ip
```

Copy file to server:

```
scp file.txt user@ip:/home/user
```

Meaning:

> SSH is how engineers control remote machines.

---

# 12. ENVIRONMENT VARIABLES — HIDDEN SETTINGS

Set variable:

```
export APP_ENV=prod
```

Read:

```
echo $APP_ENV
```

List all:

```
printenv
```

Apps read configuration from these.

---

# 13. SIMPLE AUTOMATION

Create script:

```
nano script.sh
```

Inside:

```
#!/bin/bash
echo Hello Server
```

Run:

```
bash script.sh
```

Executable:

```
chmod +x script.sh
./script.sh
```

---

# 14. COMPRESSION — MOVING DATA EFFICIENTLY

Compress folder:

```
tar -czvf logs.tar.gz logs/
```

Extract:

```
tar -xzvf logs.tar.gz
```

---

# 15. BASIC TROUBLESHOOTING SEQUENCE

When something breaks:

Check CPU:

```
top
```

Check memory:

```
free -m
```

Check disk:

```
df -h
```

Check ports:

```
ss -tulnp
```

Check logs:

```
tail -f app.log
```

This is real engineer workflow.

---

# BASIC LEVEL COMPLETION CHECK

You can now:

✔ navigate server
✔ create/delete files
✔ inspect processes
✔ monitor system
✔ manage services
✔ check ports
✔ read logs
✔ install software
✔ connect remotely

---

# FINAL LINE FOR THIS CHAPTER

At this point in the story:

> You are no longer a person using Linux.
> You are a person operating Linux.

---

END OF LEVEL 2 — BASIC
