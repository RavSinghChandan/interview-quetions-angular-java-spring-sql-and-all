# ♻️ JVM GARBAGE COLLECTION — PERSONAL MASTER NOTE
### (Senior Java Engineer | Barclays • MasterCard • Visa • FAANG-tier)

---

## 🎯 WHY THIS SECTION EXISTS
This section is built to:
- 🧠 Make GC behavior **predictable, not mysterious**
- 🚀 Turn GC tuning into a **senior-level strength**
- 💼 Prepare you for **low-latency JVM interviews**
- 🧩 Explain not just *what GC does*, but *how & why*
- 🏆 Make you sound like a JVM performance engineer

---

## 🧩 SECTION 1: WHAT GC REALLY IS (MENTAL MODEL)

Garbage Collection is **not just deleting objects**.  
It is a **multi-phase memory orchestration system**.

GC is responsible for:

- 🔍 Finding unreachable objects
- 🧠 Tracking object reachability
- ♻️ Reclaiming heap memory
- 🧹 Compacting fragmented memory
- 📈 Managing allocation pressure
- ⚡ Minimizing pause times
- 🔄 Cooperating with JIT
- 🎯 Balancing throughput vs latency

**Mental flow:**

Allocation pressure  
➡️ GC trigger  
➡️ Root scanning  
➡️ Object marking  
➡️ Object relocation  
➡️ Memory cleanup  
➡️ Application resumes

💡 **Core Idea:**
> GC is a **trade-off engine** between pause time, throughput, and memory usage.

.note GC_CORE
- 🧠 GC ≠ just delete dead objects
- ⚡ GC is workload-dependent
- ♻️ GC behavior changes with heap size
- 🎯 GC tuning = trade-offs
- 💡 Follow-up: Throughput vs Latency GC
- 🧨 Trap: GC tuning without profiling

---

## 🧱 SECTION 2: GC TYPES (BY HEAP REGION)

GC types are defined by **what memory they clean**:

**Minor GC**
- Cleans Young Generation
- Fast
- Frequent
- Low pause

**Major GC**
- Cleans Old Generation
- Slower
- Less frequent
- Higher pause

**Full GC**
- Cleans entire heap + Metaspace
- Longest pause
- Most disruptive

.note GC_TYPES
- 🌱 Minor GC = cheap & fast
- 🧱 Major GC = slow & heavy
- 💥 Full GC = worst-case
- 💡 Follow-up: When Major ≠ Full GC
- 🧨 Trap: Using “Major GC” incorrectly

---

## 🧬 SECTION 3: GC ROOTS (HOW JVM DECIDES LIFE & DEATH)

An object is alive if reachable from **GC Roots**.

GC Roots include:

- 🧵 Local variables (stack)
- 🧠 Active threads
- 🧩 Static fields
- 🔗 JNI references
- 🛠️ System classes

Reachability flow:

GC Roots  
➡️ Object A  
➡️ Object B  
➡️ Object C

All reachable = alive  
Unreachable = garbage

.note GC_ROOTS
- 🧠 Reachability defines liveness
- 🔗 One live ref keeps whole graph alive
- 💡 Follow-up: Strong vs Weak reachability
- 🧨 Trap: GC does not use reference counting

---

## 🪜 SECTION 4: GENERATIONAL GC THEORY

Core assumption:

> 🌱 Most objects die young  
> 🧱 Few objects live long

Therefore:

- Young Gen → frequent, fast GC
- Old Gen → rare, heavy GC

This minimizes total GC cost.

.note GENERATIONAL_THEORY
- 🧠 Reduces GC workload
- ⚡ Faster allocation recovery
- 💡 Follow-up: What breaks this assumption
- 🧨 Trap: Long-living temp objects

---

## 🧮 SECTION 5: MINOR GC (YOUNG GEN FLOW)

What happens in Minor GC:

1) Stop-the-world pause
2) Scan GC roots
3) Copy live objects from Eden
4) Move survivors to S0/S1
5) Age objects
6) Promote if needed
7) Resume app

.note MINOR_GC
- ⚡ Very fast
- ♻️ Mostly parallel
- 🌱 Happens frequently
- 💡 Follow-up: Survivor sizing
- 🧨 Trap: Promotion storm

---

## 🧱 SECTION 6: MAJOR GC (OLD GEN FLOW)

What happens in Major GC:

1) Stop-the-world pause
2) Mark live objects
3) Sweep dead objects
4) Compact memory
5) Resume app

.note MAJOR_GC
- 🧨 Expensive
- 🧠 High latency
- ♻️ Less frequent
- 💡 Follow-up: Concurrent marking
- 🧨 Trap: Major ≠ Full GC

---

## 💥 SECTION 7: FULL GC (WHOLE HEAP CLEANUP)

Triggered by:

- Promotion failure
- Metaspace pressure
- Heap fragmentation
- Explicit System.gc()
- Allocation failure

What it does:

- Cleans Young Gen
- Cleans Old Gen
- Cleans Metaspace
- Compacts everything

.note FULL_GC
- 💥 Worst pause times
- 🧨 Freezes application
- ♻️ Heavy CPU usage
- 💡 Follow-up: Avoiding Full GC
- 🧨 Trap: Ignoring Full GC logs

---

## ⚙️ SECTION 8: GC ALGORITHMS (WHAT STRATEGY JVM USES)

Serial GC
- Single-threaded
- Small apps

Parallel GC
- Throughput optimized
- Multi-threaded

CMS (Deprecated)
- Low pause
- Fragmentation

G1 (Default Java 9+)
- Region-based
- Predictable pauses

ZGC
- Ultra-low latency
- Concurrent everything

Shenandoah
- Pause-time focused
- Concurrent compaction

.note GC_ALGORITHMS
- 🧠 Choose GC based on workload
- ⚡ Low latency ≠ high throughput
- 💡 Follow-up: Why CMS deprecated
- 🧨 Trap: Assuming one GC fits all

---

## 🗺️ SECTION 9: G1 GC (DEFAULT HERO)

Why G1 exists:

- Replace CMS
- Predictable pause times
- Large heap support
- Concurrent marking
- Region-based heap

How G1 works:

Heap split into regions  
➡️ Young GC on selected regions  
➡️ Concurrent marking  
➡️ Mixed GC  
➡️ Cleanup

.note G1
- 🧠 Region-based GC
- ⚡ Predictable pauses
- ♻️ Concurrent marking
- 💡 Follow-up: G1 Mixed GC
- 🧨 Trap: G1 ≠ always fastest

---

## ⚡ SECTION 10: ZGC (LOW-LATENCY BEAST)

ZGC goals:

- Sub-10ms pauses
- Huge heaps (TBs)
- Fully concurrent GC

Key ideas:

- Colored pointers
- Load barriers
- Concurrent compaction

.note ZGC
- ⚡ Ultra-low latency
- 🧠 Concurrent everything
- 💡 Follow-up: ZGC vs G1
- 🧨 Trap: ZGC needs newer JVM

---

## 🔍 SECTION 11: GC TRIGGERS (WHAT STARTS GC)

GC starts when:

- Eden fills
- Old Gen fills
- Promotion fails
- Metaspace grows
- Explicit GC call

.note GC_TRIGGERS
- 🧠 Allocation rate drives GC
- ⚡ Fast alloc = frequent GC
- 💡 Follow-up: GC ergonomics
- 🧨 Trap: Ignoring allocation rate

---

## 📈 SECTION 12: GC LOGS (YOUR DIAGNOSTIC GOLD)

GC logs reveal:

- Pause times
- GC frequency
- Memory reclaimed
- Promotion failures
- Fragmentation

Enable logs:

- Java 8: -XX:+PrintGCDetails
- Java 9+: -Xlog:gc*

.note GC_LOGS
- 🧠 Logs = GC truth
- 🔍 Always analyze logs
- 💡 Follow-up: GC log analysis tools
- 🧨 Trap: Blind tuning

---

## 🛠️ SECTION 13: GC TUNING KNOBS

Key flags:

- -XX:+UseG1GC
- -XX:MaxGCPauseMillis
- -XX:InitiatingHeapOccupancyPercent
- -XX:ParallelGCThreads
- -XX:ConcGCThreads

.note GC_TUNING
- ⚖️ Trade-offs always
- 🧠 Tune based on logs
- 💡 Follow-up: Pause vs throughput tuning
- 🧨 Trap: Over-tuning GC

---

## 🧠 SECTION 14: REAL-WORLD GC HEURISTICS

- 🎯 Prefer G1 for microservices
- ⚡ Prefer ZGC for ultra-low latency
- 🧱 Avoid tiny survivor spaces
- 🛠️ Set Xms = Xmx
- 📈 Monitor allocation rate
- ♻️ Avoid explicit GC

.note GC_HEURISTICS
- 🧠 Profile before tuning
- 🎯 Workload-specific
- 💡 Follow-up: Kubernetes memory limits
- 🧨 Trap: Copy-paste GC flags

---

## 🏆 SECTION 15: SENIOR-LEVEL GC TRUTHS

- 🧬 GC is adaptive
- ⚡ Allocation rate > heap size
- ♻️ GC tuning is iterative
- 🧠 Most GC issues are app bugs
- 🧨 Full GC = production alarm
- 🏆 GC mastery = JVM mastery

.note GC_TRUTHS
- 🧠 Always mention Java version
- 🎯 Always ask latency SLA
- 💡 Follow-up: Throughput vs tail latency
- 🧨 Trap: Overconfidence in GC

---

## 🧾 SECTION 16: MUST-KNOW GC FACTS

- 🏆 Default GC (Java 9+) = G1
- 🌱 Minor GC = Young Gen
- 🧱 Major GC = Old Gen
- 💥 Full GC = Whole heap
- 🧠 CMS deprecated, not removed
- ⚡ ZGC ultra-low latency

.note GC_FACTS
- ⚠️ Version traps common
- 🎯 Always state GC type
- 💡 Follow-up: Java 17 GC defaults
- 🧨 Trap: Wrong default GC

---

🎉 **END OF GARBAGE COLLECTION SECTION**  
(Next: JVM Tuning & Performance Playbook)
