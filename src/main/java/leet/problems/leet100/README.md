Problem Statement
-----------------

Given two binary trees, write a function to check if they are the same or not.
Two binary trees are considered the same if they are structurally identical and
the nodes have the same value.

*Difficulty: easy.*

Discussion
----------

Try recursion. Two binary trees are identical if:
  - they have the same root value;
  - their left children are identical to each other;
  - and so are their right children.
  
Presto. But if the input is pathological (or simply very large), this approach
might overflow the call stack, like any other recursion-based approach in
languages without tail call optimization.

In that case, we can consider a slightly less intuitive approach, such as
iterating through the tree in breadth-first order. If the two trees are the
same, we will iterate through the same set of tree nodes in the same order. If
the two trees are different, either we will find two nodes that aren't the same,
or one tree will run out of nodes before the other.

Like with all simple BFS on a tree or another graph, we put the starting node
into a FIFO queue, then repeatedly remove nodes and feed their children back
into the queue. In this case, since we are iterating over two trees, we have two
queues.

The trickiest part is making sure we can distinguish a node with just a left
child from a node with just a right child. To do so, we add null children to the
queue, but don't do anything with them when we remove them from the queue (aside
from making sure that both queues have null elements in the same place). Java's
`ArrayDeque` does not allow null entries, so we use a sentinel object instead.
