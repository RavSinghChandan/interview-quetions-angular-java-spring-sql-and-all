```
# 🚀 JVM ARCHITECTURE — MASTER NOTE
(Senior Java Engineer | Product-Based Companies)

==================================================

🗺️ MIND MAP — ONE-GLANCE REVISION (10 SECONDS)

JVM Architecture
|
├── 🧠 Mental Model
|   └── Runtime engine + optimizer + memory manager
|
├── 🧱 High-Level Structure
|   ├── Class Loader Subsystem
|   ├── Runtime Data Areas
|   └── Execution Engine
|
├── 📦 Class Loader Subsystem
|   ├── Bootstrap Loader
|   ├── Extension Loader
|   ├── Application Loader
|
├── 🛡️ Delegation Model
|   ├── Parent-first loading
|   ├── Security isolation
|
├── 🧾 Class Loading Phases
|   ├── Loading
|   ├── Linking
|   |   ├── Verification
|   |   ├── Preparation
|   |   └── Resolution
|   └── Initialization
|
├── 🧠 Runtime Data Areas
|   ├── Heap
|   ├── Metaspace
|   ├── JVM Stack
|   ├── PC Register
|   └── Native Stack
|
├── 🗄️ Heap Structure
|   ├── Young Gen (Eden, S0, S1)
|   └── Old Gen
|
├── 🧩 Metaspace
|   ├── Class metadata
|   ├── Method bytecode
|   └── Constant pool
|
├── ⚙️ Execution Engine
|   ├── Interpreter
|   ├── JIT Compiler
|   └── Garbage Collector
|
├── 🚀 JIT Internals
|   ├── C1 Compiler
|   ├── C2 Compiler
|   └── Tiered Compilation
|
├── 🔄 Execution Flow
|   ├── Source → Bytecode
|   ├── Load → Verify → Link → Init
|   └── Execute → JIT → GC
|
└── 🏆 Senior Truths
├── JVM is adaptive
├── GC + JIT cooperate
└── ClassLoader leaks are common

==================================================

```
SECTION 1: 🧠 WHAT JVM REALLY IS

The JVM is not just “where Java runs.”  
It is a managed runtime system.

Responsibilities:
- Loads bytecode
- Verifies code safety
- Manages memory
- Optimizes execution
- Executes instructions
- Runs garbage collection
- Isolates threads
- Enforces security

NOTE  
JVM is a runtime + optimizer + memory manager.

KEY POINTS
- JVM ≠ compiler
- JVM ≠ operating system
- JVM behavior is workload-dependent
- JVM loads classes lazily

INTERESTING FACT  
HotSpot constantly rewrites your running code while your app is live.

==================================================


SECTION 2: 🧱 HIGH-LEVEL JVM STRUCTURE

Three macro components:

1) Class Loader Subsystem
2) Runtime Data Areas
3) Execution Engine

Interaction:

Class Loader  
→ loads + verifies + links classes  
→ stores metadata in Metaspace

Runtime Data Areas  
→ store objects and thread data

Execution Engine  
→ executes bytecode  
→ JIT compiles hot code  
→ runs garbage collector

NOTE  
Every JVM issue maps to loader, memory, or execution.

KEY POINTS
- Memory tuning = Runtime Data Areas
- Performance tuning = Execution Engine
- Security issues = Class Loader

INTERESTING FACT  
More than 80% of JVM production bugs trace back to memory or class loading.

==================================================


SECTION 3: 📦 CLASS LOADER SUBSYSTEM

Responsibilities:
- Find `.class` files
- Load bytecode into memory
- Verify safety
- Create Class objects
- Resolve dependencies
- Store metadata in Metaspace

Properties:
- Lazy loading
- One Class object per class
- Same class + different loader = different type

NOTE  
Class loading is demand-driven, not startup-driven.

KEY POINTS
- Custom loaders enable plugin systems
- Loader leaks cause Metaspace leaks
- Same class name ≠ same type across loaders

INTERESTING FACT  
Application servers isolate apps using custom class loaders.

==================================================


SECTION 4: 🧬 TYPES OF CLASS LOADERS

Bootstrap Loader
- Loads core Java classes
- Implemented in native code
- Source: rt.jar (Java 8), jmods (Java 9+)

Extension Loader
- Loads extension libraries
- Path: $JAVA_HOME/lib/ext

Application Loader
- Loads user application classes
- Reads from classpath

NOTE  
Most business code is loaded by Application ClassLoader.

KEY POINTS
- Bootstrap cannot be overridden
- Custom loaders extend ClassLoader
- Spring Boot uses a fat-jar loader

INTERESTING FACT  
Same class loaded by two loaders behaves as two unrelated types.

==================================================


SECTION 5: 🛡️ DELEGATION MODEL

Loading order:

Child loader  
→ Parent loader  
→ Bootstrap loader  
→ Return if found  
→ Otherwise bubble back

Purpose:
- Prevent spoofing core classes
- Avoid duplicate definitions
- Maintain JVM consistency

NOTE  
Delegation is a security feature.

KEY POINTS
- Prevents fake java.lang.String
- Tomcat uses parent-last
- Parent-last improves isolation

INTERESTING FACT  
Delegation was introduced to prevent malicious JDK class overrides.

==================================================


SECTION 6: 🧾 CLASS LOADING PHASES

1) Loading
- Reads bytecode
- Creates Class object
- Stores in Metaspace

2) Linking  
   Verification
- Bytecode safety
- Stack safety
- Type correctness

Preparation
- Allocates static variables
- Assigns default values

Resolution
- Symbolic → direct references

3) Initialization
- Executes static blocks
- Assigns real static values

NOTE  
Preparation ≠ Initialization.

KEY POINTS
- Resolution can be lazy
- Static blocks run once
- Verification is mandatory

INTERESTING FACT  
static final constants may be inlined at compile time.

==================================================


SECTION 7: 🧠 RUNTIME DATA AREAS

Thread-Shared:
- Heap
- Metaspace

Thread-Private:
- JVM Stack
- PC Register
- Native Method Stack

NOTE  
Heap is not total JVM memory.

KEY POINTS
- GC touches Heap only
- Stack is never GC-managed
- Metaspace is native memory

INTERESTING FACT  
Many OutOfMemoryErrors occur outside the heap.

==================================================


SECTION 8: 🗄️ HEAP

Stores:
- All Java objects

Structure:
- Young Generation (Eden, S0, S1)
- Old Generation

Lifecycle:
New → Eden → Survivor → Old → Collected

NOTE  
Heap is optimized for short-lived objects.

KEY POINTS
- Eden = first allocation
- Promotion threshold configurable
- Large objects may skip Eden

INTERESTING FACT  
Over 90% of objects die in Eden.

==================================================


SECTION 9: 🧩 METASPACE

Stores:
- Class metadata
- Method bytecode
- Constant pool
- Static variables
- Annotations

Java 8+:
- PermGen removed
- Metaspace uses native memory

NOTE  
Metaspace growth is unbounded unless capped.

KEY POINTS
- Loader leaks cause Metaspace OOM
- Native memory, not heap
- Auto-resizes

INTERESTING FACT  
Metaspace OOM kills JVM even if heap is free.

==================================================


SECTION 10: 🧵 JVM STACK

Each thread has its own stack.

Each method call creates:
- Local variables
- Operand stack
- Frame metadata
- Return address

NOTE  
Stack memory is fast and isolated.

KEY POINTS
- Deep recursion → StackOverflowError
- Larger stack = fewer threads
- Stack stores references, not objects

INTERESTING FACT  
JVM stack is never garbage collected.

==================================================


SECTION 11: 🧭 PC REGISTER

Stores:
- Current bytecode instruction address

Purpose:
- Thread switching
- Instruction sequencing

NOTE  
One PC register exists per thread.

KEY POINTS
- Required for concurrency
- No OutOfMemoryError possible

INTERESTING FACT  
PC register is undefined during native method execution.

==================================================


SECTION 12: 🔧 NATIVE METHOD STACK

Used for:
- JNI calls
- Native libraries

NOTE  
Native crashes can terminate JVM.

KEY POINTS
- Separate from JVM stack
- Not GC-managed

INTERESTING FACT  
JNI bugs bypass Java safety checks completely.

==================================================


SECTION 13: ⚙️ EXECUTION ENGINE

Components:
- Interpreter
- JIT Compiler
- Garbage Collector

NOTE  
Interpreter + JIT cooperate dynamically.

KEY POINTS
- JVM never runs purely on JIT
- GC is part of engine
- Tiered compilation is default

INTERESTING FACT  
HotSpot profiles code before compiling it.

==================================================


SECTION 14: 🐢 INTERPRETER

Executes bytecode line by line.

NOTE  
Interpreter runs cold code.

KEY POINTS
- Fast startup
- Slow execution
- Memory efficient

INTERESTING FACT  
Interpreter is never disabled in JVM.

==================================================


SECTION 15: 🚀 JIT COMPILER

Compiles hot bytecode into native code.

Types:
- C1 (fast compile)
- C2 (deep optimization)
- Tiered compilation

Optimizations:
- Method inlining
- Loop unrolling
- Escape analysis
- Lock elimination

NOTE  
JIT changes runtime behavior.

KEY POINTS
- Hot methods → native code
- Escape analysis enables stack allocation
- JIT vs AOT differences

INTERESTING FACT  
JIT may de-optimize compiled code if assumptions break.

==================================================


SECTION 16: ♻️ GARBAGE COLLECTOR

Reclaims unreachable objects.

Algorithms:
- Serial
- Parallel
- CMS (deprecated)
- G1 (default)
- ZGC
- Shenandoah

NOTE  
GC is part of execution engine.

KEY POINTS
- Minor = Young
- Major = Old
- Full = Whole heap

INTERESTING FACT  
Changing GC can change performance 10×.

==================================================


SECTION 17: 🔄 JVM EXECUTION FLOW

Java Source  
→ javac  
→ Bytecode  
→ Class Loader  
→ Runtime Data Areas  
→ Interpreter  
→ JIT Compilation  
→ Native Execution  
→ Garbage Collection

NOTE  
JVM is a continuous feedback system.

KEY POINTS
- Class loading happens multiple times
- JIT + GC are dynamic
- Bytecode verification timing matters

INTERESTING FACT  
JVM can recompile the same method multiple times.

==================================================


SECTION 18: 🏗️ JVM vs JRE vs JDK

JVM
- Bytecode execution engine

JRE
- JVM + core libraries

JDK
- JRE + dev tools

NOTE  
Production servers need only JRE.

KEY POINTS
- Dev machines need JDK
- Java 11 introduced modular runtime

INTERESTING FACT  
You can build a custom JRE image using jlink.

==================================================


SECTION 19: 🏆 SENIOR-LEVEL JVM TRUTHS

- JVM is adaptive
- Performance is workload-specific
- GC tuning is trade-offs
- ClassLoader leaks are common
- Memory leaks ≠ GC bugs
- JIT optimizations change behavior
- Stack size affects scalability

NOTE  
JVM problems are usually system design problems.

KEY POINTS
- Always mention Java version
- Always ask workload type
- Never tune blindly

INTERESTING FACT  
Most senior JVM bugs are configuration bugs.

==================================================


SECTION 20: 📌 MUST-KNOW JVM FACTS

- Default GC (Java 9+) = G1
- PermGen removed = Java 8
- StackOverflowError = deep recursion
- Heap OOM = leak or low Xmx
- Metaspace OOM = too many classes

NOTE  
Version-specific knowledge matters.

KEY POINTS
- Always state GC type
- Always state Java version
- Know at least two collectors

INTERESTING FACT  
Most JVM interview failures come from confusing JVM, JRE, and JDK.

==================================================

END OF JVM ARCHITECTURE SECTION
