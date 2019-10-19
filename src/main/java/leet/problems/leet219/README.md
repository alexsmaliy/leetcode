Problem Statement
-----------------

Given an array of integers and an integer *k*, find out whether there are two
distinct indices *i* and *j* in the array such that `nums[i] = nums[j]` and the
absolute difference between *i* and *j* is at most *k*.

*Difficulty: easy.*

Discussion
----------

Not a particularly tricky problem. I did what seemed obvious and beat 98% of
submissions on runtime (with the usual caveat that LeetCode timing is almost
entirely meaningless).

Take the original array `nums` and augment it with a second dimension that
records the original position of each element. Sort the elements in increasing
order by value, then by position. Iterate over the sorted array. Each time you
encounter successive duplicates, check if their original positions are less than
*k* apart.
<pre><tt>ORIGINAL:   [2, 5, 3, 8, 4, 8, 1, 5],   k = 3

AUGMENTED:  [2, 5, 3, 8, 4, 8, 1, 5]
            [0, 1, 2, 3, 4, 5, 6, 7]

SORTED:     [1, 2, 3, 4, 5, 5, 8, 8]
            [6, 0, 2, 4, 1, 7, 3, 5]

DUPLICATES: 5 at 1 & 7 ✘
            8 at 3 & 5 ✔
</tt></pre>
