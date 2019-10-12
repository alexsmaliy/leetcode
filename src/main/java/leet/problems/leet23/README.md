Problem Statement
-----------------

Merge *k* sorted linked lists and return it as one sorted list. Analyze and
describe its complexity.

*Difficulty: hard.*

Discussion
----------

This is a textbook problem. Therefore, classifying it as *hard* is somewhat
misleading. The code is not the difficult part here.

The most naive solution is to generalize the process of merging two sorted lists
to apply to *k* lists instead. Namely, at each iteration, you have up to *k*
candidate nodes from which to choose the smallest. At each iteration, you would
make up to *k* comparisons (fewer than *k* as input lists run out of nodes). You
iterate as many times as there are nodes in total (call that number *N*). The
number of operations required to complete the task clearly grows as *kN*.

Still relatively intuitively, we can just merge pairs of lists until there is
only one left. Regardless of order, that's `k - 1` pairwise merges. For the sake
of clarity, let's suppose we merge lists in "tree" order: 1 with 2, 3 with 4,
and so on, then 1-2 with 3-4, etc. We can see that we iterate over all *N* nodes
*log<sub>2</sub>&thinsp;k* times &mdash; the number of layers in a complete
binary tree with *k* leaves. We see that this merge strategy scales with
*N&thinsp;log<sub>2</sub>&thinsp;k*, much better than *kN*.

The data structure used in this problem is the singly-linked list, which, among
many shortcomings, does offer the advantage of short-circuiting a merge when one
of the operands runs out of elements. We just staple the remainder of the longer
of the two lists to the end of the merged list and call it a day. Suppose we
used a list implementation that required us to copy every value from each list
in order to merge them. In that case, merging two lists *a* and *b* would take
size(*a*) + size(*b*) operations, and we'd do well to consider the order in
which we merge all *k* to minimize the total number of operations.

As an illustration, consider four lists of size 1, 1, 1, and 50. If we always
merged the largest list with the smallest, we would:
- merge a list of length 1 with the one of length 50, resulting in one of length
  51 and costing 51 operations;
- merge this list of 51 with another list of 1, resulting in a list of length 52
  and costing another 52 operations (103 total);
- merge that list of 52 with the remaining list of 1, resulting in the final,
  fully merged list of length 53, and costing another 53 operations (156 total).
  
Now let's try choosing the two shortest lists available at each iteration. We
would:
- merge two lists of length 1, resulting in a new one of length 2 and costing 2
  operations;
- merge this new list of 2 with the remaining list of 1, producing a list of 3
  and costing 3 operations (5 total);
- merge the list of 3 with the list of 50, producing the same fully merged list
  of 53 as before (another 53 operations, 58 in total).
  
It can be shown that the second merge strategy is optimal (see *Huffman coding*
and the proof of its optimality). To implement it for this LeetCode problem, we
would first need to iterate through each list to find and store its length.
Then, we'd use a priority queue of some sort to get the two shortest lists to
merge, and keep merging two at a time until completion. Java offers an unbounded
priority queue in the form of `PriorityQueue`. If your language does not,
writing an array-based heap (a bag data structure that stays sorted as elements
are added or removed) is straightforward and educational.

The Java solution offered in this directory demonstrates a simple min-heap
implementation (a heap which always offers the smallest element it contains).
