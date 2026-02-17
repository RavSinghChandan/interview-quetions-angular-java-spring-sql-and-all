# LINUX — LEVEL 1 (NAIVE NOTES)

**Stage Title: “Meeting Linux for the First Time”**
*(This directly continues the story you read. This is the technical layer of that story.)*

---

## 1. Where You Actually Are When You Open Terminal

When you open terminal, you are not opening Linux.

You are opening a **communication window** to Linux.

Structure:

```
You → Terminal → Shell → Kernel → Hardware
```

Meaning:

* Terminal = interface
* Shell = translator
* Kernel = decision maker
* Hardware = worker

---

## 2. The Real Definition You Must Remember Forever

Linux = Kernel

Kernel = software that controls:

* CPU
* RAM
* disk
* processes
* devices
* network

One-line truth:

> Linux is the resource manager of a computer.

---

## 3. Why Linux Exists

Without kernel:

Every program would need to know how hardware works.

That would mean:

Every developer must write code for:

* RAM access
* disk access
* CPU scheduling

Impossible.

So kernel was invented to handle hardware once, for all programs.

Programs → ask kernel → kernel handles hardware.

---

## 4. What Happens When You Run a Command

When you type:

```
ls
```

This is what actually happens:

```
Keyboard input
↓
Shell reads command
↓
Shell requests kernel
↓
Kernel checks filesystem
↓
Kernel returns result
↓
Shell prints output
```

You never talk to hardware directly.

You always talk to Linux.

---

## 5. Terminal vs Shell (Never Confuse)

Terminal
= window

Shell
= program interpreting commands

Check shell:

```
echo $SHELL
```

Common shells:

```
bash
zsh
sh
```

---

## 6. Linux’s Most Important Philosophy

Linux simplifies world using one rule:

> Everything is a file.

Examples:

Disk:

```
/dev/sda
```

CPU info:

```
/proc/cpuinfo
```

Network config:

```
/etc/hosts
```

Meaning:

Hardware + processes + configs
→ all treated like files.

This is why Linux is consistent.

---

## 7. The Linux Filesystem World Map

Root directory:

```
/
```

Important locations:

```
/home → users
/etc  → configs
/var  → logs
/dev  → devices
/proc → system info
/tmp  → temporary
```

Why this matters:

No matter which server you log into worldwide, structure is same.

---

## 8. The Superuser (Root)

There is one user with unlimited power:

```
root
```

Root can:

* modify system
* install anything
* delete anything
* kill any process

To act as root:

```
sudo command
```

Meaning:

> execute with administrator power

---

## 9. Programs Are Not Static

When you run a program, Linux creates a:

```
process
```

Process = running instance of program.

Linux assigns it:

* memory
* CPU time
* priority
* permissions

When program ends → process disappears.

Linux manages thousands simultaneously.

---

## 10. Services — Programs That Never Stop

Some programs run continuously:

Examples:

* web server
* database
* scheduler
* monitoring agent

These are called:

> services

They usually start automatically when system boots.

---

## 11. Why Servers Prefer Linux

Servers need:

* stability
* speed
* low memory usage
* remote control
* security

Linux provides all.

Linux can run for years without reboot.

That’s why almost all production servers use Linux.

---

## 12. Logs — Linux’s Memory

Linux records everything that happens.

Errors
warnings
crashes
access events

These records are:

```
logs
```

Logs are first place engineers look when something breaks.

---

## 13. Environment Variables — Hidden Configuration

Programs don’t store all config in code.

They read values from environment.

Example:

```
PORT=8080
ENV=production
DB_HOST=localhost
```

Linux stores these values and passes them to programs.

---

## 14. Permissions — Linux Security System

Every file has rules:

Who can read
Who can write
Who can execute

Three permission groups:

```
owner
group
others
```

Linux security relies heavily on permissions.

---

## 15. Networking Exists Inside Linux

When request hits server:

Linux handles:

* connection
* port
* socket
* routing
* response

Applications don’t directly manage network.

Linux does.

---

## 16. Package Managers — Installing Software

Linux installs software using package managers.

Examples:

```
apt
yum
dnf
apk
```

They automatically:

* download software
* install dependencies
* configure system

---

## 17. The Boot Sequence (What Happens When Machine Starts)

When system powers on:

```
BIOS
↓
Bootloader
↓
Kernel loads
↓
Services start
↓
System ready
```

Kernel is first real software that runs.

---

## 18. The Hidden Truth Most Developers Don’t Know

When an application fails in production…

It is usually NOT a code bug.

It is usually:

* memory exhausted
* port blocked
* permission denied
* disk full
* process killed

All of these are Linux-level problems.

---

## 19. Mental Model You Must Keep

Whenever something happens on server, think:

```
Is this CPU?
Is this memory?
Is this disk?
Is this network?
Is this process?
Is this permission?
```

Linux issues always fall into these categories.

---

## 20. NAIVE LEVEL COMPLETION CHECK

You are done with this level when you clearly understand:

* what Linux actually is
* why kernel exists
* how commands run
* what process is
* what service is
* why logs matter
* what root is
* how filesystem structured
* why Linux runs servers

No commands memorization needed yet.

Only understanding.

---

# FINAL LINE FOR THIS LEVEL

After this stage:

> Linux is no longer mysterious to you.

It is now understandable.

That is the real beginning.

---

END OF LEVEL 1 — NAIVE
