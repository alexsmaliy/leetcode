Problem Statement
-----------------

Given a binary search tree, write a function `kthSmallest` to find the *k*th
smallest element in it. You may assume k is always valid, 1 ≤ *k* ≤ BST's total
elements.

*Difficulty: medium.*

Discussion
----------

This is a very basic textbook problem that requires simple reasoning about the
internals of a textbook-variety BST. We find the least element in the BST, and
then find the next larger element until we reach the *k*th.

If you draw a BST, you will find that:
  - The least element in a BST is the left-most leaf.
  - The successor of a left leaf is its parent.
  - The successor of a non-leaf node is the least element of its right child.
  - The successor of a right leaf is found by climbing back up toward the root
    until we encounter a node with a larger value.
    
Because the BST implementation that LeetCode provides does not link nodes to
their parents, we have to keep track of how far up we have to go using a LIFO
stack.
<pre>
     4               4               4               4
   &#x2b0b;   &#x2198;          &#x2199;   &#x2198;          &#x2199;   &#x2198;          &#x2b08;   &#x2198;
  2     6         2     6         2     6         2     6
&#x2b0b; &#x2198;   &#x2199; &#x2198;      &#x2b08; &#x2198;  &#x2199; &#x2198;      &#x2199; &#x2b0a;   &#x2199; &#x2198;      &#x2199; &#x2b09;  &#x2199; &#x2198;
1   3 5   7     1   3 5   7     1   3 5    7    1   3 5    7
</pre>
