Problem Statement
-----------------

Given a binary tree, return the *level order* traversal of its nodes' values.
(ie, from left to right, level by level).

*Difficulty: medium.*

Discussion
----------

Compared to other "medium" problems, this one is easy, because it's just BFS.
The first level is the root of the provided tree. The second level is all of its
non-null children. Each successive level is just the non-null children of each
tree node from the previous level.

There are many ways to express this that are basically all the same. The sample
Java code shows one approach that puts the nodes of each level into a queue and
another approach that puts the nodes of each level into a stream. Queues do
better than streams, but the principle is the same regardless.
