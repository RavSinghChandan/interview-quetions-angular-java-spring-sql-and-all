```text


Garbage Collection (GC)
|
├── 🧠 Mental Model
|   └── Trade-offs: latency vs throughput vs memory
|
├── 📦 GC Types
|   ├── Minor GC (Young Gen)
|   ├── Major GC (Old Gen)
|   └── Full GC (Whole Heap + Metaspace)
|
├── 🔗 GC Roots
|   ├── Stack references
|   ├── Static fields
|   ├── Active threads
|   └── JNI references
|
├── 🪜 Generational Theory
|   ├── Most objects die young
|   └── Few objects live long
|
├── 🔄 Minor GC Flow
|   ├── Eden cleanup
|   ├── Survivor copy
|   └── Promotion
|
├── 🧱 Major GC Flow
|   ├── Mark
|   ├── Sweep
|   └── Compact
|
├── 💥 Full GC
|   ├── Young + Old + Metaspace
|   └── Worst pause
|
├── ⚙️ GC Algorithms
|   ├── Serial
|   ├── Parallel
|   ├── CMS (deprecated)
|   ├── G1 (default)
|   ├── ZGC
|   └── Shenandoah
|
├── 🧩 G1 Internals
|   ├── Regions
|   ├── Concurrent marking
|   └── Mixed GC
|
├── ⚡ ZGC Internals
|   ├── Colored pointers
|   ├── Load barriers
|   └── Concurrent compaction
|
├── 🚦 GC Triggers
|   ├── Eden full
|   ├── Old Gen full
|   ├── Promotion failure
|   └── Metaspace pressure
|
├── 📈 GC Logs
|   ├── Pause time
|   ├── Frequency
|   └── Allocation rate
|
├── 🛠️ GC Tuning Knobs
|   ├── MaxGCPauseMillis
|   ├── InitiatingHeapOccupancyPercent
|   └── GC threads
|
└── 🏆 Senior Truths
├── Allocation rate > heap size
├── GC tuning is iterative
└── Full GC = production alarm

```


SECTION 1: 🧠 WHAT GC REALLY IS (MENTAL MODEL)

Garbage Collection is not just deleting objects.  
It is a multi-phase memory orchestration system.

GC is responsible for:
- Finding unreachable objects
- Tracking reachability
- Reclaiming heap memory
- Compacting fragmented memory
- Managing allocation pressure
- Minimizing pause times
- Cooperating with JIT
- Balancing throughput vs latency

Mental flow:

Allocation pressure  
→ GC trigger  
→ Root scanning  
→ Object marking  
→ Object relocation  
→ Memory cleanup  
→ Application resumes

NOTE  
GC is a trade-off engine between pause time, throughput, and memory usage.

KEY POINTS
- GC is workload dependent
- Heap size affects GC behavior
- Tuning GC = tuning trade-offs
- GC does not fix memory leaks

INTERESTING FACT  
GC design is closer to an OS scheduler than a cleanup script.

==================================================


SECTION 2: 📦 GC TYPES (BY HEAP REGION)

Minor GC
- Cleans Young Generation
- Fast
- Frequent
- Low pause

Major GC
- Cleans Old Generation
- Slower
- Less frequent
- Higher pause

Full GC
- Cleans entire heap + Metaspace
- Longest pause
- Most disruptive

NOTE  
Minor, Major, and Full GC are defined by what memory is cleaned.

KEY POINTS
- Minor GC = cheap and fast
- Major GC = slow and heavy
- Full GC = worst case
- Major GC is not always Full GC

INTERESTING FACT  
In modern collectors like G1 and ZGC, the term "Major GC" is less clearly defined.

==================================================


SECTION 3: 🔗 GC ROOTS (HOW JVM DECIDES LIFE & DEATH)

An object is alive if reachable from GC Roots.

GC Roots include:
- Local variables in stack
- Active threads
- Static fields
- JNI references
- System classes

Reachability flow:

GC Roots  
→ Object A  
→ Object B  
→ Object C

All reachable = alive  
Unreachable = garbage

NOTE  
Reachability, not age, decides object survival.

KEY POINTS
- One live reference keeps entire object graph alive
- GC does not use reference counting
- Static fields are common leak sources

INTERESTING FACT  
Reference counting was abandoned because it cannot handle circular references.

==================================================


SECTION 4: 🪜 GENERATIONAL GC THEORY

Core assumption:

Most objects die young  
Few objects live long

Therefore:
- Young Gen → frequent, fast GC
- Old Gen → rare, heavy GC

This minimizes total GC cost.

NOTE  
Generational GC reduces overall GC workload drastically.

KEY POINTS
- Eden = high churn area
- Old Gen = expensive to clean
- Promotion happens after survivals

INTERESTING FACT  
This assumption is valid for more than 95% of real-world Java workloads.

==================================================


SECTION 5: 🔄 MINOR GC (YOUNG GEN FLOW)

What happens in Minor GC:

1. Stop-the-world pause
2. Scan GC roots
3. Copy live objects from Eden
4. Move survivors to S0/S1
5. Age objects
6. Promote if needed
7. Resume app

NOTE  
Minor GC is fast because it copies only live objects.

KEY POINTS
- Happens frequently
- Mostly parallel
- Affects only Young Gen
- Promotion increases Old Gen pressure

INTERESTING FACT  
Minor GC cost is proportional to live objects, not total heap size.

==================================================


SECTION 6: 🧱 MAJOR GC (OLD GEN FLOW)

What happens in Major GC:

1. Stop-the-world pause
2. Mark live objects
3. Sweep dead objects
4. Compact memory
5. Resume app

NOTE  
Major GC is slow because Old Gen is large and fragmented.

KEY POINTS
- High latency
- Rare but impactful
- Often causes performance spikes
- Can be concurrent in modern GC

INTERESTING FACT  
Most production outages blamed on "GC" are actually Major or Full GC events.

==================================================


SECTION 7: 💥 FULL GC (WHOLE HEAP CLEANUP)

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

NOTE  
Full GC is a production emergency signal.

KEY POINTS
- Longest pause times
- Freezes application
- Heavy CPU usage
- Usually avoidable

INTERESTING FACT  
In well-tuned systems, Full GC should be near-zero.

==================================================


SECTION 8: ⚙️ GC ALGORITHMS (WHAT STRATEGY JVM USES)

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

NOTE  
GC choice defines JVM behavior more than any other flag.

KEY POINTS
- Low latency ≠ high throughput
- G1 is safe default
- ZGC for ultra-low latency
- CMS is deprecated

INTERESTING FACT  
Changing GC can change performance by 10× without touching application code.

==================================================


SECTION 9: 🧩 G1 GC (DEFAULT COLLECTOR)

Why G1 exists:
- Replace CMS
- Predictable pause times
- Large heap support
- Concurrent marking
- Region-based heap

How G1 works:

Heap split into regions  
→ Young GC on selected regions  
→ Concurrent marking  
→ Mixed GC  
→ Cleanup

NOTE  
G1 is designed for balanced workloads.

KEY POINTS
- Region-based GC
- Predictable pauses
- Concurrent marking
- Mixed GC phase

INTERESTING FACT  
G1 does not treat the heap as strictly Young + Old; it uses flexible regions.

==================================================


SECTION 10: ⚡ ZGC (LOW-LATENCY GC)

ZGC goals:
- Sub-10ms pauses
- Huge heaps (TBs)
- Fully concurrent GC

Key ideas:
- Colored pointers
- Load barriers
- Concurrent compaction

NOTE  
ZGC trades throughput for ultra-low latency.

KEY POINTS
- Almost pause-less
- Concurrent everything
- Requires newer JVM
- Best for latency-critical systems

INTERESTING FACT  
ZGC pauses remain nearly constant even as heap size grows.

==================================================


SECTION 11: 🚦 GC TRIGGERS (WHAT STARTS GC)

GC starts when:
- Eden fills
- Old Gen fills
- Promotion fails
- Metaspace grows
- Explicit GC call

NOTE  
Allocation rate drives GC more than heap size.

KEY POINTS
- Faster allocation = more GC
- Promotion failure is dangerous
- Metaspace pressure can cause Full GC

INTERESTING FACT  
Most GC storms are caused by sudden spikes in allocation rate.

==================================================


SECTION 12: 📈 GC LOGS (YOUR DIAGNOSTIC GOLD)

GC logs reveal:
- Pause times
- GC frequency
- Memory reclaimed
- Promotion failures
- Fragmentation

Enable logs:

Java 8:
- -XX:+PrintGCDetails

Java 9+:
- -Xlog:gc*

NOTE  
GC logs are the single source of truth for tuning.

KEY POINTS
- Always enable logs in prod
- Analyze before tuning
- Look for patterns, not single events

INTERESTING FACT  
Many production teams tune JVM blindly without ever opening GC logs.

==================================================


SECTION 13: 🛠️ GC TUNING KNOBS

Key flags:
- -XX:+UseG1GC
- -XX:MaxGCPauseMillis
- -XX:InitiatingHeapOccupancyPercent
- -XX:ParallelGCThreads
- -XX:ConcGCThreads

NOTE  
Every flag has a cost.

KEY POINTS
- Lower pause target = more GC cycles
- More GC threads = CPU contention
- Tune using logs only

INTERESTING FACT  
Most GC tuning mistakes make performance worse, not better.

==================================================


SECTION 14: 🏆 SENIOR-LEVEL GC TRUTHS

- GC is adaptive
- Allocation rate > heap size
- GC tuning is iterative
- Most GC issues are app bugs
- Full GC = production alarm
- GC mastery = JVM mastery

NOTE  
GC problems are usually application design problems.

KEY POINTS
- Always mention Java version
- Always ask SLA (latency vs throughput)
- Never tune blindly

INTERESTING FACT  
The fastest JVM is often the one with the least tuning.

==================================================


SECTION 15: 📌 MUST-KNOW GC FACTS

- Default GC (Java 9+) = G1
- Minor GC = Young Gen
- Major GC = Old Gen
- Full GC = Whole heap
- CMS deprecated, not removed
- ZGC ultra-low latency

NOTE  
Version-specific knowledge matters in interviews.

KEY POINTS
- Always state GC type
- Always state Java version
- Know at least two collectors deeply

INTERESTING FACT  
Most senior candidates fail JVM rounds because they memorize terms instead of mental models.

==================================================

END OF GARBAGE COLLECTION SECTION
