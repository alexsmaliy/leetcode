Problem Statement
-----------------

Given a binary tree, find its maximum depth. The maximum depth is the number of
nodes along the longest path from the root node down to the farthest leaf node.

*Difficulty: easy.*

Discussion
----------

Many problems about navigating from the root of the tree to the leaves are
fundamentally similar, and this is one of them. Like in all such problems, we
have the choice of DFS, which can be expressed recursively, but might overflow
the call stack for big or pathological inputs, and BFS, which requires us to
keep track of the current set of candidates.

The DFS option just takes the max of the depth of the root node's right and left
children, and proceeds recursively.

The BFS option navigates the tree level by level, collecting each level in a
list of tree nodes. It gets the answer by reporting how many levels are in the
graph.
