Problem Statement
-----------------

Given a collection of intervals, find the minimum number of intervals you need
to remove to make the rest of the intervals non-overlapping.

Note:
1. You may assume the interval's end point is always bigger than its start
   point.
1. Intervals like `[1,2]` and `[2,3]` have borders "touching" but they don't
   overlap each other.


*Difficulty: medium.*

Discussion
----------

This is another textbook problem, the "activity selection problem." On sorted
input, a greedy heuristic can be shown to be optimal.
1. Sort the intervals in order of increasing *endpoint*.
1. Begin with the first interval in sorted order and record its endpoint.
1. Iterate over the rest in order.
1. Every time you encounter an interval whose starting point does not come
   before the previously recorded endpoint, add it to the count of chosen
   intervals and check if the endpoint needs to be updated.

<pre><tt>        UNSORTED                   SORTED                  SELECTED
7-            6▬▬▬▬▬9                  6▬▬▬▬▬9                  6▬▬▬▬▬9
6-          5▬▬▬▬▬8          1▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬9        1▭▭▭▭▭▭▭▭▭▭▭▭▭▭▭9
5-        4▬▬▬▬▬7                    5▬▬▬▬▬8                  5▭▭▭▭▭8
4-      3▬▬▬▬▬6                    4▬▬▬▬▬7                  4▭▭▭▭▭7
3-    2▬▬▬▬▬5                  2▬▬▬▬▬▬▬▬▬7              2▭▭▭▭▭▭▭▭▭7
2-  1▬▬▬3                        3▬▬▬▬▬6                  3▬▬▬▬▬6
1-    2▬▬▬▬▬▬▬▬▬7              2▬▬▬▬▬5                  2▭▭▭▭▭5
0-  1▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬9        1▬▬▬3                    1▬▬▬3
    -----------------        -----------------        -----------------
    1 2 3 4 5 6 7 8 9        1 2 3 4 5 6 7 8 9        1 2 3 4 5 6 7 8 9
</tt></pre>
