Problem Statement
-----------------

Reverse a singly linked list. A linked list can be reversed either iteratively
or recursively. Could you implement both?

*Difficulty: easy.*

Discussion
----------

This problem is on the tricky side of easy. We can reverse the list in one pass,
but details depend on how we set up the recurrence. We also have the choice of
reusing the nodes of the original list and making new copies, but this is left
unspecified in the conditions. The example solutions show both.

Denote the list by *n*, with `n[0]` &rarr; `n[1]` &rarr; ... &rarr; `n[end]`,
and consider it as the head node `n[0]` and the rest `n[1..end]`. Then, the
reverse list is `reverse(n[1..end])` &rarr; `n[0]`. The `reverse(n[1..end])`
term itself is `reverse(n[2..end])` &rarr; `n[1]`, and so on, until the
recursion is fully resolved.

Note that the recursive term is in the head position of the recurrence: we can't
evaluate the first term until we fully expand it.

Alternatively, suppose we set up the recursion so that we have access to both
the original list *n* and the reverse list *r* at the same time. Then, we can
simply pop off list nodes from *n* and attach them top *r* as we iterate.

| Iteration |  *r*  |  *n*  |
|   :---:   | :---  | :---  |  
| 0 | `null` | `n[0]` &rarr; `n[1]` &rarr; `n[2]` &rarr; `n[3]` &rarr; ... |
| 1 | `n[0]` | `n[1]` &rarr; `n[2]` &rarr; `n[3]` &rarr; ... |
| 2 | `n[1]` &rarr; `n[0]` | `n[2]` &rarr; `n[3]` &rarr; ... |
| 3 | `n[2]` &rarr; `n[1]` &rarr; `n[0]` | `n[3]` &rarr; ... |

Notice that this "recursion" is just iteration. Each time, we remove one list
node and move it to the reversed list that we are building. We call this "tail"
recursion.

Put more simply, the difference is the one between these two ways to add a
sequence of numbers:
```
(1 + 2 + 3 + 4) = 1 + (2 + 3 + 4) = 1 + 2 + (3 + 4) = 1 + 2 + 3 + 4
```
```
(1 + 2 + 3 + 4) = (1 + 2 + 3) + 4 = (1 + 2) + 3 + 4 = 1 + 2 + 3 + 4
```
When the recursive term comes first in the expression, we must recurse all the
way down before we can evaluate anything.

On the other hand, when the recursive term comes last, we can do the work one
step at a time.

Tail recursion can also be easily rewritten using a loop, avoiding the
possibility of a stack overflow. Some compilers and interpreters offer "tail
call optimization" and do this rewriting invisibly to the code author.
