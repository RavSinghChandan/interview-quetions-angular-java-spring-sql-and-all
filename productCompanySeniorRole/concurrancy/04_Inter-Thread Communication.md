# Module 4: Inter-Thread Communication (Self Notes)

---

## Core Purpose

Inter-thread communication allows threads to **coordinate execution**, **wait for conditions**, and **exchange signals** without busy-waiting.

**Golden Rule:**

> Threads should never poll blindly. Always wait on a condition and get notified.

---

## Mental Model

* One thread produces data
* Another thread waits for it
* They coordinate using a shared lock + condition

---

## Topic 24: wait()

* Releases the current lock
* Moves thread to WAITING state
* Must be called inside synchronized block
* Resumes only after notify/notifyAll

```java
synchronized(lock) {
    while(!condition) {
        lock.wait();
    }
}
```

---

## Topic 25: notify()

* Wakes one waiting thread
* Does not release lock immediately
* Thread resumes only after lock is released

```java
synchronized(lock) {
    condition = true;
    lock.notify();
}
```

---

## Topic 26: notifyAll()

* Wakes all waiting threads
* Prevents missed signals
* Higher CPU usage

```java
synchronized(lock) {
    condition = true;
    lock.notifyAll();
}
```

---

## Topic 27: Producer–Consumer Problem

* One or more producers generate data
* One or more consumers process data
* Uses wait/notify to block when:

    * Queue is full (producer waits)
    * Queue is empty (consumer waits)

```java
synchronized(queue) {
    while(queue.isEmpty()) {
        queue.wait();
    }
    int item = queue.remove();
    queue.notifyAll();
}
```

---

## Topic 28: Guarded Blocks

* Pattern: wait until condition becomes true
* Always use while loop
* Prevents spurious wakeups

```java
synchronized(lock) {
    while(!ready) {
        lock.wait();
    }
}
```

---

## Topic 29: Spurious Wakeups

* Thread wakes up without notify
* Happens due to JVM or OS
* Always guard wait() using while

---

## Topic 30: Missed Signals

* notify() happens before wait()
* Thread waits forever
* Prevented using:

    * Guarded blocks
    * notifyAll()
    * Proper ordering

---

## Execution Rules

* Always call wait/notify inside synchronized
* Always check condition in while loop
* Never assume notify wakes specific thread
* Avoid notify when multiple waiters exist

---

## Real-World Mapping

* Payment event processing
* Order fulfillment workflows
* Fraud score waiting pipeline
* Cache refresh coordination

---

## Performance Implications

* notifyAll() wakes all threads → CPU spike
* wait() releases lock → improves concurrency
* Poor signaling → thread starvation

---

## Common Mistakes

* Using if instead of while
* Calling wait() outside synchronized
* Using notify() instead of notifyAll()
* Forgetting to update condition before notify

---

## Design Rules

* Prefer BlockingQueue over manual wait/notify
* Use notifyAll when multiple consumers
* Always guard with while
* Keep wait sections minimal

---

## JVM Insight

* wait() parks thread at OS level
* notify moves thread to BLOCKED state
* Actual scheduling depends on JVM + OS

---

## Senior-Level Takeaway

> wait/notify is powerful but fragile. Prefer higher-level concurrency utilities when possible.

---

## Ultra-Crisp Recall

* wait() releases lock
* notify() wakes one
* notifyAll() wakes all
* Always use while
* Prefer BlockingQueue

---

## Interview Punchline

> Inter-thread communication in Java is done using wait(), notify(), and notifyAll() under a synchronized block. Threads wait on conditions and get notified when state changes. In production, higher-level abstractions like BlockingQueue are preferred.

---
