Problem Statement
-----------------

Invert a binary tree.

This problem was inspired by
[this original tweet](https://twitter.com/mxcl/status/608682016205344768) by
[Max Howell](https://twitter.com/mxcl):<blockquote>Google: 90% of our engineers
use the software you wrote (Homebrew), but you can’t invert a binary tree on a
whiteboard so f*** off.</blockquote>

*Difficulty: easy.*

Discussion
----------

"Invert" here means "put the elements in reverse order." Maybe that's not the
best word for it, or maybe that's not what the eponymous Google question was
asking.

At any rate, if a BST stores elements in increasing order, at every node we
have max(left child) < node value < min(right child).

What we want is the reverse: min(left child) > node value > max(right child).

Solution: visit every node and swap its left and right child. It doesn't matter
if you do t&thinsp;his recursively (DFS) or level by level (BFS).
<pre><tt>ORIGINAL        REVERSED
     4               4
   /   \           /   \
  2     7         7     2
 / \   / \       / \   / \
1   3 6   9     9   6 3   1</tt></pre>
