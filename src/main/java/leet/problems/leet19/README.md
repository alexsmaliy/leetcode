Problem Statement
-----------------

Given a linked list, remove the *n*-th node from the end of list and return its
head. Given *n* will always be valid. Could you do this in one pass?

*Difficulty: medium.*

Discussion
----------

I found this problem surprisingly easy, at least in the context of LeetCode. We
modify the list in place. Recurse into the list with a function that keeps track
of the current node and the desired index *n*. The recursion bottoms out and
reports 1 when the current node has a null successor. The other nodes report 1 +
whatever their successors report. If the successor reports that it is the *n*th
node from the end, we dump it from the list by making the current node point at
its successor's successor. The only wrinkle is if the head of the original list
is itself the *n*th node. In that case, we simply return its child.
