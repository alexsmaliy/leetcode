Problem Statement
-----------------

Given a sorted array `nums`, remove the duplicates in-place such that each
element appear only once and return the new length. Do not allocate extra space
for another array, you must do this by modifying the input array in-place with
*O*(1) extra memory.

*Difficulty: easy.*

Discussion
----------

Your task is to compact an array and report the length of the compacted portion.
The instructions go to extraordinary length to make that clear. Once the task is
understood, it is very simple.

We iterate over the input array and keep track of the current position *i* and
the largest index of the compacted region *c*. Both start at the beginning of
the array. If `nums[i]` &ne; `nums[i - 1]`, we increment *c* and copy `nums[i]`
there. When we reach the end of the array, *c* points to the last element of the
compacted region of the array, so it is trivial to report how long that region
is.

Depending on the order in which you increment, compare, and copy, you might need
to fudge a &plusmn;1 here and there.
