
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

## Java's model (important)

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
public class ThreadExample {
    // Shared: static variable
    private static int sharedCounter = 0;
    
    // Shared: instance variable (if same object)
    private int instanceCounter = 0;

    public static void main(String[] args) {
        Thread t1 = new Thread(ThreadExample::work);
        Thread t2 = new Thread(ThreadExample::work);

        t1.start();
        t2.start();
        
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    static void work() {
        // Each thread has its own stack
        int localVar = 0; // Not shared
        
        // But they share static variables
        sharedCounter++;
        
        System.out.println(
            "Thread: " + Thread.currentThread().getName() +
            ", Shared: " + sharedCounter +
            ", Local: " + localVar
        );
    }
}
```

What is shared:

* Heap
* Static variables
* Objects

What is not shared:

* Stack
* Local variables

This distinction matters more than it looks.

---

## Code: demonstrating memory sharing

```java
public class MemorySharing {
    private static StringBuilder shared = new StringBuilder();
    
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                shared.append("A");
            }
        });
        
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                shared.append("B");
            }
        });
        
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        
        System.out.println("Length: " + shared.length());
        // May not be 2000 due to race conditions
    }
}
```

Both threads modify the same object in heap memory.

---

## First real problem (don't ignore this)

```java
class Counter {
    int count = 0;

    void increment() {
        count++;
    }
    
    int get() {
        return count;
    }
}

// Usage
Counter counter = new Counter();

Thread t1 = new Thread(() -> {
    for (int i = 0; i < 1000; i++) {
        counter.increment();
    }
});

Thread t2 = new Thread(() -> {
    for (int i = 0; i < 1000; i++) {
        counter.increment();
    }
});

t1.start();
t2.start();
t1.join();
t2.join();

// Expected: 2000
// Actual: may be less due to lost updates
System.out.println(counter.get());
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

If threads didn't share memory,
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

Java backend systems almost always choose threads.

---

## Code: process isolation (for comparison)

```java
// This would require separate JVM processes
// Processes don't share memory
// Communication requires IPC (Inter-Process Communication)
// Example: sockets, pipes, shared memory segments
```

Processes provide isolation but at a higher cost.

---

## Production reality

Most backend services are:

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

