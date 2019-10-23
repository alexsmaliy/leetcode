What This Is
------------

This repo is a collection of Java solutions to some LeetCode problems, with a
bit of discussion included with each.

I formatted the code to run conveniently in my IDE. You may need to fiddle with
`static` modifiers on classes and methods if you want to paste code into the
LeetCode UI verbatim.

The argument types and return types of the functions the LeetCode grader expects
to call have been kept the same. Where alternative solutions are offered, the
function names have been changed to distinguish them.

The common data types that LeetCode requires users to use have been factored out
into [a separate package](./src/main/java/leet/types). I have only modified
these classes to add convenience methods, like `toString()`, to make solutions
more easily testable.

Table of Contents
-----------------
| LeetCode ID | Discussion |
| :--- | :--- |
| [LC 1](https://leetcode.com/problems/two-sum/) | [Two Sum](./src/main/java/leet/problems/leet1) |
| [LC 2](https://leetcode.com/problems/add-two-numbers/) | [Add Two Numbers](./src/main/java/leet/problems/leet2)
| [LC 3](https://leetcode.com/problems/longest-substring-without-repeating-characters/) | [Longest Substring Without Repeating Characters](./src/main/java/leet/problems/leet3) |
| [LC 15](https://leetcode.com/problems/3sum/) | [3Sum](./src/main/java/leet/problems/leet15) |
| [LC 19](https://leetcode.com/problems/remove-nth-node-from-end-of-list/) | [Remove Nth Node from End of List](./src/main/java/leet/problems/leet19) |
| [LC 20](https://leetcode.com/problems/valid-parentheses/) | [Valid Parentheses](./src/main/java/leet/problems/leet20) |
| [LC 23](https://leetcode.com/problems/merge-k-sorted-lists/) | [Merge k Sorted Lists](./src/main/java/leet/problems/leet23) |
| [LC 26](https://leetcode.com/problems/remove-duplicates-from-sorted-array/) | [Remove Duplicates from Sorted Array](./src/main/java/leet/problems/leet26) |
| [LC 49](https://leetcode.com/problems/group-anagrams/) | [Group Anagrams](./src/main/java/leet/problems/leet49) |
| [LC 53](https://leetcode.com/problems/maximum-subarray/) | [Maximum Subarray](./src/main/java/leet/problems/leet53) |
| [LC 55](https://leetcode.com/problems/jump-game/) | [Jump Game](./src/main/java/leet/problems/leet55) |
| [LC 56](https://leetcode.com/problems/merge-intervals/) | [Merge Intervals](./src/main/java/leet/problems/leet56) |
| [LC 70](https://leetcode.com/problems/climbing-stairs/) | [Climbing Stairs](./src/main/java/leet/problems/leet70) |
| [LC 76](https://leetcode.com/problems/minimum-window-substring/) | [Minimum Window Substring](./src/main/java/leet/problems/leet76) |
| [LC 91](https://leetcode.com/problems/decode-ways/) | [Decode Ways](./src/main/java/leet/problems/leet91) |
| [LC 98](https://leetcode.com/problems/validate-binary-search-tree/) | [Validate Binary Search Tree](./src/main/java/leet/problems/leet98) |
| [LC 100](https://leetcode.com/problems/same-tree/) | [Same Tree](./src/main/java/leet/problems/leet100) |
| [LC 102](https://leetcode.com/problems/binary-tree-level-order-traversal/) | [Binary Tree Level Order Traversal](./src/main/java/leet/problems/leet102) |
| [LC 104](https://leetcode.com/problems/maximum-depth-of-binary-tree/) | [Maximum Depth of Binary Tree](./src/main/java/leet/problems/leet104) |
| [LC 121](https://leetcode.com/problems/best-time-to-buy-and-sell-stock/) | [Best Time to Buy and Sell Stock](./src/main/java/leet/problems/leet121) |
| [LC 133](https://leetcode.com/problems/clone-graph/) | [Clone Graph](./src/main/java/leet/problems/leet133) |
| [LC 141](https://leetcode.com/problems/linked-list-cycle/) | [Linked List Cycle](./src/main/java/leet/problems/leet141) |
| [LC 142](https://leetcode.com/problems/linked-list-cycle-ii/) | [Linked List Cycle II](./src/main/java/leet/problems/leet142) |
| [LC 153](https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/) | [Find Minimum in Rotated Sorted Array](./src/main/java/leet/problems/leet153) |
| [LC 198](https://leetcode.com/problems/house-robber/) | [House Robber](./src/main/java/leet/problems/leet198) |
| [LC 200](https://leetcode.com/problems/number-of-islands/) | [Number of Islands](./src/main/java/leet/problems/leet200) |
| [LC 206](https://leetcode.com/problems/reverse-linked-list/) | [Reverse Linked List](./src/main/java/leet/problems/leet206) |
| [LC 207](https://leetcode.com/problems/course-schedule/) | [Course Schedule](./src/main/java/leet/problems/leet207) |
| [LC 208](https://leetcode.com/problems/implement-trie-prefix-tree/) | [Implement Trie (Prefix Tree)](./src/main/java/leet/problems/leet208) |
| [LC 213](https://leetcode.com/problems/house-robber-ii/) | [House Robber II](./src/main/java/leet/problems/leet213) |
| [LC 217](https://leetcode.com/problems/contains-duplicate/) | [Contains Duplicate](./src/main/java/leet/problems/leet217) |
| [LC 219](https://leetcode.com/problems/contains-duplicate-ii/) | [Contains Duplicate II](./src/main/java/leet/problems/leet219) |
| [LC 226](https://leetcode.com/problems/invert-binary-tree/) | [Invert Binary Tree](./src/main/java/leet/problems/leet226) |
| [LC 230](https://leetcode.com/problems/kth-smallest-element-in-a-bst/) | [kth Smallest Element in a BST](./src/main/java/leet/problems/leet230) |
| [LC 238](https://leetcode.com/problems/product-of-array-except-self/) | [Product of Array Except Self](./src/main/java/leet/problems/leet238) |
| [LC 242](https://leetcode.com/problems/valid-anagram/) | [Valid Anagram](./src/main/java/leet/problems/leet242) |
| [LC 297](https://leetcode.com/problems/serialize-and-deserialize-binary-tree/) | [Serialize and Deserialize Binary Tree](./src/main/java/leet/problems/leet297) |
| [LC 300](https://leetcode.com/problems/longest-increasing-subsequence/) | [Longest Increasing Subsequence](./src/main/java/leet/problems/leet300) |
| [LC 322](https://leetcode.com/problems/coin-change/) | [Coin Change](./src/main/java/leet/problems/leet322) |
| [LC 417](https://leetcode.com/problems/pacific-atlantic-water-flow/) | [Pacific Atlantic Water Flow](./src/main/java/leet/problems/leet417) |
| [LC 424](https://leetcode.com/problems/longest-repeating-character-replacement/) | [Longest Repeating Character Replacement](./src/main/java/leet/problems/leet424) |
| [LC 435](https://leetcode.com/problems/non-overlapping-intervals/) | [Non-Overlapping Intervals](./src/main/java/leet/problems/leet435) |
| [LC 442](https://leetcode.com/problems/find-all-duplicates-in-an-array/) | [Find All Duplicates in an Array](./src/main/java/leet/problems/leet442) |
| [LC 1047](https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/) | [Remove All Adjacent Duplicates in String](./src/main/java/leet/problems/leet1047) |
| [LC 1209](https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/) | [Remove All Adjacent Duplicates in String II](./src/main/java/leet/problems/leet1209) |
