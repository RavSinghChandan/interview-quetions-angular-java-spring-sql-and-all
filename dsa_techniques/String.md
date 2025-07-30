| #  | Technique                                                        |
| -- | ---------------------------------------------------------------- |
| 1  | Sliding Window                                                   |
| 2  | Two Pointers                                                     |
| 3  | Hashing / Frequency / Count Array                                |
| 4  | Prefix-based / Prefix Sum                                        |
| 5  | Pattern Matching (KMP, Rabin-Karp, Z, Aho‑Corasick, Boyer‑Moore) |
| 6  | Palindrome Expansion / Manacher’s                                |
| 7  | Trie / Prefix Tree                                               |
| 8  | Suffix Arrays / Suffix Trees                                     |
| 9  | Greedy Sliding Window / Shrinking                                |
| 10 | Stack-based String Processing                                    |
| 11 | Divide & Conquer / Recursive String Exploration                  |
-------------------------------------------------------------------------
# String DSA Techniques (General Patterns with Steps and Java Code)

This markdown file includes general techniques for solving **String problems in DSA**. Each section includes:

* Technique name
* Step-by-step explanation in English
* Java code implementation

---

## 1. Sliding Window

**Steps:**

1. Use two pointers to define the current window: `start` and `end`.
2. Expand the window by moving `end` forward.
3. Shrink the window from `start` if constraints are violated.
4. Track the result (length, count, etc.) during traversal.

```java
public int longestSubstringWithoutRepeat(String s) {
    Set<Character> set = new HashSet<>();
    int maxLen = 0, start = 0;
    for (int end = 0; end < s.length(); end++) {
        while (set.contains(s.charAt(end))) {
            set.remove(s.charAt(start++));
        }
        set.add(s.charAt(end));
        maxLen = Math.max(maxLen, end - start + 1);
    }
    return maxLen;
}
```

---

## 2. Two Pointers

**Steps:**

1. Use one pointer from start and another from end.
2. Compare characters or perform conditional movements.
3. Continue until the pointers cross.

```java
public boolean isPalindrome(String s) {
    int i = 0, j = s.length() - 1;
    while (i < j) {
        if (s.charAt(i) != s.charAt(j)) return false;
        i++; j--;
    }
    return true;
}
```

---

## 3. Hashing / Frequency Count

**Steps:**

1. Create a frequency map or array.
2. Traverse the string to populate the count.
3. Use the map/array for lookups, matching, or validation.

```java
public boolean isAnagram(String s, String t) {
    if (s.length() != t.length()) return false;
    int[] count = new int[26];
    for (char c : s.toCharArray()) count[c - 'a']++;
    for (char c : t.toCharArray()) {
        if (--count[c - 'a'] < 0) return false;
    }
    return true;
}
```

---

## 4. Prefix-based Algorithm

**Steps:**

1. Preprocess string to compute prefix information (e.g., count, hash, etc.).
2. Use prefix data for efficient range queries or match.

```java
public int[] buildPrefixSum(String s) {
    int[] prefix = new int[s.length() + 1];
    for (int i = 0; i < s.length(); i++) {
        prefix[i + 1] = prefix[i] + (s.charAt(i) - 'a');
    }
    return prefix;
}
```

---

## 5. Pattern Matching (KMP Algorithm)

**Steps:**

1. Preprocess pattern to build LPS (Longest Prefix Suffix) array.
2. Use LPS to skip unnecessary comparisons during match.

```java
public int strStr(String haystack, String needle) {
    if (needle.isEmpty()) return 0;
    int[] lps = new int[needle.length()];
    for (int i = 1, len = 0; i < needle.length();) {
        if (needle.charAt(i) == needle.charAt(len)) lps[i++] = ++len;
        else if (len != 0) len = lps[len - 1];
        else lps[i++] = 0;
    }
    for (int i = 0, j = 0; i < haystack.length();) {
        if (needle.charAt(j) == haystack.charAt(i)) {
            i++; j++;
            if (j == needle.length()) return i - j;
        } else if (j != 0) j = lps[j - 1];
        else i++;
    }
    return -1;
}
```

---

## 6. Palindrome Expansion

**Steps:**

1. Pick every character as a center.
2. Expand outward for even and odd length palindromes.
3. Track max length.

```java
public int longestPalindromicSubstring(String s) {
    int max = 0;
    for (int i = 0; i < s.length(); i++) {
        max = Math.max(max, expand(s, i, i));     // Odd
        max = Math.max(max, expand(s, i, i + 1)); // Even
    }
    return max;
}
    
private int expand(String s, int l, int r) {
    while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
        l--; r++;
    }
    return r - l - 1;
}
```

---

## 7. Trie (Prefix Tree)

**Steps:**

1. Build a tree where each node represents a character.
2. Insert words character by character.
3. Use the tree to search or auto-complete.

```java
class TrieNode {
    TrieNode[] children = new TrieNode[26];
    boolean isEnd = false;
}

class Trie {
    TrieNode root = new TrieNode();

    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.children[c - 'a'] == null) node.children[c - 'a'] = new TrieNode();
            node = node.children[c - 'a'];
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.children[c - 'a'] == null) return false;
            node = node.children[c - 'a'];
        }
        return node.isEnd;
    }
}
```

---

## 8. Greedy Shrinking Window

**Steps:**

1. Expand the window.
2. Check if constraints are met.
3. Shrink the window from the left side.

```java
public String minWindow(String s, String t) {
    Map<Character, Integer> map = new HashMap<>();
    for (char c : t.toCharArray()) map.put(c, map.getOrDefault(c, 0) + 1);

    int count = t.length(), left = 0, minLen = Integer.MAX_VALUE, start = 0;
    for (int right = 0; right < s.length(); right++) {
        if (map.getOrDefault(s.charAt(right), 0) > 0) count--;
        map.put(s.charAt(right), map.getOrDefault(s.charAt(right), 0) - 1);

        while (count == 0) {
            if (right - left + 1 < minLen) {
                minLen = right - left + 1;
                start = left;
            }
            map.put(s.charAt(left), map.getOrDefault(s.charAt(left), 0) + 1);
            if (map.get(s.charAt(left)) > 0) count++;
            left++;
        }
    }
    return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
}
```

---

(You can request to continue for Suffix Arrays, Stack-based, and Divide & Conquer techniques as well.)
