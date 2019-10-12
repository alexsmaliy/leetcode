Problem Statement
-----------------

Given a binary tree, determine if it is a valid binary search tree (BST). Assume
a BST is defined as follows:
- The left subtree of a node contains only nodes with keys *less than* the
  node's key.
- The right subtree of a node contains only nodes with keys *greater than* the
  node's key.
- Both the left and right subtrees must also be binary search trees.

*Difficulty: medium.*

Discussion
----------

This is another problem that can be solved relatively trivially using recursion.
The insight is that each node in a (valid) BST is bracketed by a numerical range
its value can occupy. The root node's value *n* can be anything:
&minus;&thinsp;&infin;&thinsp;< *n* < &infin;. The value of its left child &ell;
can only be as large as *n*: &minus;&thinsp;&infin; < &ell; < *n*. The value of
the left child's right child *r* is bracketed by both *n* and &ell;: &ell; < *r*
< *n*. It must be less than *n*, because it is in *n*'s left subtree, but it
must be larger than &ell;, because it is in &ell;'s right subtree.

We can capture this constraint using a helper function that recurses into each
node's children, keeping track of the minimum and maximum values that constrain
the value of that node. If any node fails the check, we return false immediately
and let the recursion unwind.

Depending on the language of choice, you may face the wrinkle of representing
open or half-open intervals. In Java, (`Integer.MIN_VALUE`, `Integer.MAX_VALUE`)
doesn't quite work, because a valid BST could, in fact, contain nodes with those
values. The lazy way out is to use `long` instead of `int` for representing
interval bounds. We can then represent (&minus;&thinsp;&infin;, &infin;) as
(`Integer.MIN_VALUE - 1L`, `Integer.MAX_VALUE + 1L`).
