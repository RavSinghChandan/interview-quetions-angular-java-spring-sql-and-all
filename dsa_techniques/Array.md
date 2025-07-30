# Array DSA Techniques (Categorized by Priority: 0 → 1 → 2)

---

1. Two Pointers

2. Sliding Window

3. Prefix Sum / Running Sum

4. Hashing / Frequency Map

5. Divide and Conquer

6. Binary Search (generic pattern)

7. In-Place Modification

8. Merge Intervals (merge-based interval handling)

9. Difference Array

10. Monotonic Stack / Deque

11. Greedy on Arrays

12. Index-as-Hash / Negation Technique

--------------------------------------------------------------------------------
# Array DSA Techniques (Categorized by Priority: 0 → 1 → 2)

---

## 🧠 General Techniques (Lifelong Patterns)

```java
// 1. Two Pointers
// ➤ Initialize one pointer at start, one at end
// ➤ Move pointers inward based on condition
int i = 0, j = arr.length - 1;
while (i < j) {
    // logic using arr[i] and arr[j]
    i++;
    j--;
}

// 2. Sliding Window
// ➤ Expand window from right
// ➤ Shrink window from left if condition breaks
// ➤ Track result within window bounds
int sum = 0, start = 0;
for (int end = 0; end < arr.length; end++) {
    sum += arr[end];
    while (sum > target) {
        sum -= arr[start++];
    }
    // check or update result
}

// 3. Prefix Sum
// ➤ Precompute cumulative sum for fast range queries
int[] pre = new int[arr.length + 1];
for (int k = 0; k < arr.length; k++) {
    pre[k + 1] = pre[k] + arr[k];
}

// 4. Hashing / Frequency Map
// ➤ Count frequency of each element using HashMap
Map<Integer, Integer> freq = new HashMap<>();
for (int num : arr) {
    freq.put(num, freq.getOrDefault(num, 0) + 1);
}

// 5. Divide and Conquer
// ➤ Divide array into halves recursively
// ➤ Combine results from both halves
int solve(int[] a, int l, int r) {
    if (l == r) return a[l];
    int m = (l + r) / 2;
    return merge(solve(a, l, m), solve(a, m + 1, r));
}

// 6. Binary Search (generic)
// ➤ Repeatedly divide the array to find target
int l = 0, r = arr.length - 1;
while (l <= r) {
    int m = l + (r - l) / 2;
    if (arr[m] == target) break;
    else if (arr[m] < target) l = m + 1;
    else r = m - 1;
}

// 7. In-Place Modification
// ➤ Modify array directly to save space
for (int p = 0; p < arr.length; p++) {
    if (arr[p] < 0) arr[p] = 0; // Example modification
}

// 8. Merge Intervals
// ➤ Sort by starting index
// ➤ Merge overlapping intervals in one pass
Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
List<int[]> merged = new ArrayList<>();
for (int[] cur : intervals) {
    if (merged.isEmpty() || merged.getLast()[1] < cur[0]) merged.add(cur);
    else merged.getLast()[1] = Math.max(merged.getLast()[1], cur[1]);
}

// 9. Difference Array
// ➤ Apply range increment using a diff array
int[] diff = new int[n + 1];
diff[l] += val;
diff[r + 1] -= val;
for (int x = 1; x < n; x++) {
    diff[x] += diff[x - 1];
}

// 10. Monotonic Stack
// ➤ Maintain elements in increasing/decreasing order in stack
Stack<Integer> stack = new Stack<>();
for (int z = 0; z < arr.length; z++) {
    while (!stack.isEmpty() && arr[stack.peek()] < arr[z]) {
        stack.pop();
    }
    stack.push(z);
}

// 11. Greedy on Arrays
// ➤ Choose local optimal to reach global solution (e.g., Jump Game)
int far = 0, jumps = 0, end = 0;
for (int i2 = 0; i2 < arr.length - 1; i2++) {
    far = Math.max(far, i2 + arr[i2]);
    if (i2 == end) {
        jumps++;
        end = far;
    }
}

// 12. Index-as-Hash / Negation Technique
// ➤ Use index position to track occurrence (O(1) space)
for (int v = 0; v < arr.length; v++) {
    int idx = Math.abs(arr[v]) - 1;
    if (arr[idx] > 0) arr[idx] = -arr[idx];
}
```
