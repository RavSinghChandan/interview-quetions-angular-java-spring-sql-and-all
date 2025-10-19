# 🧠 Complete DSA Patterns (Tier-Wise Breakdown)

A full breakdown of DSA question patterns categorized by **importance tiers** — each pattern includes its **sub-cases and variations** so that no topic or style of problem remains uncovered.

---

## 🥇 **TIER 1 — Core Patterns (Most Common in Interviews)**

### 1. Sliding Window
- Fixed-size window problems
- Variable-size window problems
- Window with condition (sum, distinct count, character frequency)
- Shrinking and expanding window
- Maximum/Minimum/Count-based window logic
- Two array or string window comparison (anagram type)

### 2. Two Pointers
- Opposite direction pointers (start–end)
- Same direction pointers (fast-slow)
- Pointer convergence problems (meeting in middle)
- Sorted array operations using pointers
- Merging or removing duplicates with pointers
- Partitioning arrays using two pointers

### 3. Fast & Slow Pointers
- Cycle detection in linked lists or arrays
- Midpoint or length-based traversal
- Detect intersection points
- Reversing or splitting linked lists using pointers
- Linked list palindrome checking

### 4. Prefix Sum / Difference Array
- Prefix sum (1D cumulative addition)
- Prefix XOR / Prefix product
- Difference array (range updates efficiently)
- Subarray sum problems using prefix
- Prefix matrix (2D prefix sum)

### 5. Hashing / Frequency Map
- Count of elements/frequency maintenance
- Hash map + prefix combination
- Hashing with pair/triple sum
- Hashing with character/string mapping
- Hash set for unique element tracking
- Index storage using hash map

---

## 🥈 **TIER 2 — Mid-Level Core Patterns (Every Interview Has Few)**

### 6. Binary Search (and Variants)
- Basic binary search (sorted array)
- Binary search on answer space (optimization)
- Binary search on monotonic functions
- Binary search with condition (custom comparator)
- Binary search on rotated sorted array
- Binary search in 2D matrix

### 7. Monotonic Stack / Queue
- Next Greater / Smaller element
- Previous Greater / Smaller element
- Stock span / daily temperature patterns
- Range queries using stack
- Sliding window maximum using deque
- Histogram or rectangle area problems

### 8. Merge Intervals
- Merge overlapping intervals
- Insert new interval
- Minimum number of intervals to remove
- Meeting room allocation
- Interval intersections
- Sorting + merging + condensing intervals pattern

### 9. Kadane’s Algorithm (Subarray Optimization)
- Maximum subarray sum
- Minimum subarray sum
- Circular subarray
- Subarray with at most k negatives/positives
- Subarray sum constraint optimization

### 10. Recursion + Divide & Conquer
- Classic divide and combine (sorts, searches)
- Divide by halves (binary logic)
- Combine results recursively
- Recursive tree traversal logic
- Backtracking base + recursive case identification

---

## 🥉 **TIER 3 — Advanced & Optimization Patterns**

### 11. Dynamic Programming (DP)
- 1D DP (state based on previous index)
- 2D DP (grid/matrix DP)
- String DP (subsequence, substring)
- Subset DP (knapsack type)
- Interval DP (merge cost, burst balloons)
- Bitmask DP (subset optimization)
- State compression DP
- DP on trees
- DP with prefix optimization (Kadane/convex hull trick)

### 12. Backtracking
- Combination generation
- Permutation generation
- Subset generation
- Partition problems
- Board problems (N-Queens, Sudoku pattern)
- Decision-based recursion with pruning
- Path search (maze, word search, etc.)

### 13. Greedy Algorithms
- Sorting + greedy pick
- Activity selection / interval scheduling
- Minimum resource usage (coin change, jobs)
- Huffman or encoding problems
- Local vs global optimal greedy proof pattern
- Ratio-based or value-based greedy choices

### 14. Heap / Priority Queue
- Top-K elements
- Merge sorted lists / arrays
- Running median or mean
- Frequency-based heap
- Custom comparator heap (max/min logic)
- Kth smallest/largest type

### 15. Binary Search Tree (BST)
- Insert/Delete/Search in BST
- Validate BST
- Successor/Predecessor in BST
- Kth smallest/largest in BST
- Lowest common ancestor (LCA) in BST
- Balanced BST construction / conversion
- BST to doubly linked list conversion

---

## 🧱 **TIER 4 — Structural & Graph-Based Patterns**

### 16. Graph Traversal
- BFS traversal (level-order, shortest path)
- DFS traversal (connected components, cycle check)
- Multi-source BFS
- Bidirectional BFS
- BFS/DFS on grid (matrix traversal)
- Path reconstruction using parent array

### 17. Topological Sort
- Kahn’s algorithm (BFS-based)
- DFS-based topological ordering
- Detect cycle using indegree
- Course scheduling pattern
- Build order dependency resolution

### 18. Union-Find / Disjoint Set
- Union by rank / size
- Path compression
- Connected components detection
- Cycle detection in undirected graph
- Kruskal’s algorithm (MST)
- Redundant connection problems

### 19. Trie / Prefix Tree
- Insert, search, delete operations
- Prefix match and word completion
- Word frequency / prefix count
- Replace word with shortest prefix
- Autocomplete system logic
- Trie with end markers and counts

### 20. Matrix Traversal
- Row-wise, column-wise, diagonal traversal
- Spiral order traversal
- Boundary traversal
- Search in sorted matrix
- Rotation / reflection / transpose operations
- Island and region marking using DFS/BFS

---

## 🧩 **TIER 5 — Specialized / High-Level Patterns**

### 21. Tree Traversal & Manipulation
- Preorder, inorder, postorder traversal
- Level order / zigzag traversal
- Recursive vs iterative traversal
- Diameter / height / depth computation
- Mirror / symmetry / flatten tree
- Serialize and deserialize tree
- Construct tree from traversals

### 22. Segment Tree / Fenwick Tree
- Range sum query
- Range minimum/maximum query
- Lazy propagation
- Point and range updates
- Difference array + tree integration
- Prefix query optimization

### 23. Bit Manipulation
- Bit masking and shifting
- Counting set bits
- Subset generation using bits
- XOR-based uniqueness or pairing
- Bitwise DP or subset optimization
- Power of two checks / manipulation tricks

### 24. Math & Number Theory
- Prime numbers and sieves
- GCD, LCM, modular arithmetic
- Modular exponentiation
- Combinatorics and nCr computation
- Probability / expected value basics
- Euclidean and extended Euclidean algorithms

### 25. Simulation / Implementation
- Simulation of real-world processes (snake game, robot moves)
- State machine or pattern simulation
- Grid simulation with multiple conditions
- Event or time-driven simulation
- Brute-force pattern optimized with hash or prefix logic

---

# 🧭 **Study Flow**

1. **Start with Tier 1 patterns** (master every sub-case).
2. Move to **Tier 2** once you can implement Tier 1 patterns blindfolded.
3. **Tier 3** builds optimization and depth (DP, backtracking, greedy).
4. **Tier 4** gives you structural control (graphs, trees, union-find).
5. **Tier 5** gives mathematical, bitwise, and simulation edge.

---

> 🧩 Once you master all these tiers, every DSA question you see will map cleanly to a **pattern + sub-case**, allowing you to solve any unseen problem within minutes of structured reasoning.
