
# STEP 2: Process vs Thread

---

## Why this step exists

Concurrency becomes dangerous the moment **memory is shared**.

Until now, concurrency was just about *multiple tasks*.  
Here, it becomes about **correctness**.

Processes isolate memory.  
Threads share it.

Everything hard in concurrency starts here.

---

## Core understanding

**Process**
- Independent execution unit
- Own memory space
- Strong isolation
- Expensive to create and communicate

**Thread**
- Lightweight execution unit
- Shares memory within a process
- Fast communication
- Easy to break correctness

> Processes give safety.  
> Threads give speed.

Java chooses speed — and accepts the risk.

---

## Java’s model (important)

A Java application:
- Runs as **one OS process**
- Executes work using **multiple threads**
- Shares heap memory across threads

This choice enables:
- High throughput
- Low-latency communication

It also introduces:
- Race conditions
- Visibility problems
- Ordering issues

---

## Code: multiple threads, same process

```java
public class Example {

    public static void main(String[] args) {
        Thread t1 = new Thread(Example::work);
        Thread t2 = new Thread(Example::work);

        t1.start();
        t2.start();
    }

    static void work() {
        System.out.println(
            "Running on: " + Thread.currentThread().getName()
        );
    }
}
````

What is shared:

* Heap
* Static variables
* Objects

What is not shared:

* Stack
* Local variables

This distinction matters more than it looks.

---

## First real problem (don’t ignore this)

```java
class Counter {
    int count = 0;

    void increment() {
        count++;
    }
}
```

Two threads calling `increment()`:

* Read the same value
* Increment independently
* Write back incorrect state

No exception.
No warning.
Just wrong data.

This is not a thread problem.
This is a **shared-memory problem**.

---

## Mental checkpoint

If threads didn’t share memory,
there would be no race conditions.

They share memory because:

* Performance matters
* Isolation is expensive

Concurrency is a trade-off, not a feature.

---

## Process vs Thread (clean comparison)

| Aspect        | Process   | Thread          |
| ------------- | --------- | --------------- |
| Memory        | Isolated  | Shared          |
| Creation cost | High      | Low             |
| Communication | IPC       | Shared objects  |
| Failure scope | Contained | Affects process |
| Speed         | Slower    | Faster          |

Java serviceBasedMNCLevel.backend systems almost always choose threads.

---

## Production reality

Most serviceBasedMNCLevel.backend services are:

* Single process
* Multi-threaded
* Shared-memory systems

Stability comes from:

* Discipline
* Synchronization
* Immutability
* Clear ownership

Not from threads themselves.

---

## Senior takeaway

Threads are chosen for performance,
but shared memory is the price paid for it.

Concurrency problems are rarely about threads.
They are about **shared state without control**.

---

## Where this leads next

Now the real question appears:

> What actually goes wrong when multiple threads touch shared data?

That is where **race conditions and critical sections** begin.

```
```
