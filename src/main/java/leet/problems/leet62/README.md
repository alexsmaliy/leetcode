Problem Statement
-----------------

A robot is located at the top-left corner of a *m*&thinsp;&times;&thinsp;*n*
grid. The robot can only move either down or right at any point in time. The
robot is trying to reach the bottom-right corner of the grid (marked 'Finish'
in the diagram below). How many possible unique paths are there?

Note: *m* and *n* will be at most 100.

*Difficulty: medium.*

Discussion
----------

This puzzle starts with simple dynamic programming and then turns into math.
Spoiler: the number of ways to get from (0, 0) to (*m*&thinsp;&minus;&thinsp;1,
*n*&thinsp;&minus;&thinsp;1) is *m*&thinsp;+&thinsp;*n*&thinsp;&minus;&thinsp;2
choose *n*&thinsp;&minus;&thinsp;1. How you calculate that is up to you.

The dynamic programming insight is as follows: the number of routes from any
position is the sum of:
- the number of routes if we go down (unless we are in the bottom row)
- the number of routes if we go right (unless we are in the last column)
<pre><tt>┌┄┄┄┄┐┌┄┄┄┄┐┌┄┄┄┄┐┌┄┄┄┄┐┌┄┄┄┄┐
┊5+10 ▶ 4+6 ▶ 3+3 ▶ 2+1 ▶   1┊
└┄┄▼┄┘└┄┄▼┄┘└┄┄▼┄┘└┄┄▼┄┘└┄┄▼┄┘
┌┄┄▼┄┐┌┄┄▼┄┐┌┄┄▼┄┐┌┄┄▼┄┐┌┄┄▼┄┐
┊ 4+1 ▶ 3+1 ▶ 2+1 ▶ 1+1 ▶   1┊
└┄┄▼┄┘└┄┄▼┄┘└┄┄▼┄┘└┄┄▼┄┘└┄┄▼┄┘
┌┄┄▼┄┐┌┄┄▼┄┐┌┄┄▼┄┐┌┄┄▼┄┐┌┄┄▼┄┐
┊   1 ▶   1 ▶   1 ▶   1 ▶   1┊
└┄┄┄┄┘└┄┄┄┄┘└┄┄┄┄┘└┄┄┄┄┘└┄┄┄┄┘</tt></pre>
So that's the recurrence. You can just go ahead and build this table, starting
from the bottom-right corner.

If you keep squinting, you'll notice that the diagonals of this table are
symmetrical: (1), (1, 1), (1, 2, 1), (1, 3, 3, 1), and so on. They are just rows
in Pascal's triangle. If you number the rows and positions in Pascal's triangle
starting with 0, the position (*n*, *k*) is the number of ways to choose *k*
objects out of *n*. This number is given by the following formula:
<pre><tt>             n!
(n, k) = ──────────
         k!(n - k)!</tt></pre>
Namely, we're choosing *k* out of *n*, so there are *n* choices for the first,
*n*&thinsp;&minus;&thinsp;1 remaining choices for the second, and so on, until
we choose all *k*. This is the *n*!&#xff0f;(*n*&thinsp;&minus;&thinsp;*k*)!
part. Since the *n*-choose-*k* does not regard order, we then divide out *k*!
(the number of ways to redundantly order each of our
*n*!&#xff0f;(*n*&thinsp;&minus;&thinsp;*k*)! items).

As nice as an analytical solution is, factorials are very large numbers that
quickly overflow a machine integer. There's probably an involved way to compute
binomial coefficients without resorting to unlimited-precision numbers, but that
feels like it's outside the scope of this problem. I reached for Java's
`BigInteger`.

Ironically, computing the necessary subset of Pascal's triangle "by hand"
outperformed the `BigInteger` solution.

Note that the "brute force" solution only needs to keep track of two rows of
the table at any given time, as long as you're working from the bottom up.
