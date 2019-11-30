Problem Statement
-----------------

Given two non-empty binary trees *s* and *t*, check whether tree *t* has exactly
the same structure and node values with a subtree of *s*. A subtree of *s* is a
tree consists of a node in *s* and all of this node's descendants. The tree *s*
could also be considered as a subtree of itself.

*Difficulty: easy.*

Discussion
----------

The simplest solution is just a trivial traversal of the tree, depth-first or
layer by layer. When you encounter a node in *s* that has the same value as the
root node of *t*, check whether the subtree of *s* rooted at that node is
identical to *t*.

There's a pathological case where both *s* and *t* are deep, and many nodes in
*s* trigger expensive searches. For example, if *s*&thinsp;&equals;&thinsp;1&thinsp;&rarr;&thinsp;1&thinsp;&rarr;&thinsp;1&thinsp;&rarr;&thinsp;1&thinsp;&rarr;&thinsp;1&thinsp;&rarr;&thinsp;1&thinsp;&rarr;&thinsp;1&thinsp;&rarr;&thinsp;1&thinsp;&rarr;&thinsp;1&thinsp;&rarr;&thinsp;1
and *t*&thinsp;&equals;&thinsp;1&thinsp;&rarr;&thinsp;1&thinsp;&rarr;&thinsp;1&thinsp;&rarr;&thinsp;1&thinsp;&rarr;&thinsp;1&thinsp;&rarr;&thinsp;1&thinsp;&rarr;&thinsp;1&thinsp;&rarr;&thinsp;1&thinsp;&rarr;&thinsp;1&thinsp;&rarr;&thinsp;0,
the runtime becomes quadratic.

The null tree is a subtree of every tree.
