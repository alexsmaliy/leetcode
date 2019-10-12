Problem Statement
-----------------

Given a collection of intervals, merge all overlapping intervals.

*Difficulty: medium.*

Discussion
----------

This problem feels like something you're told at some point, instead of coming
up with it on the spot. Sort the intervals by the first value (left boundary).
Initialize the first merged interval with the first interval in the sorted
input, then iterate through the rest in order. If the *i*-th input interval
overlaps with the current merged interval, merge it into the interval (namely,
check if you need to advance the right boundary). If the *i*-th input interval
does not overlap with the current merged interval, start a new merged interval.
