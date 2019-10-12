Problem Statement
-----------------

Given an array `nums` of *n* integers, are there elements *a*, *b*, *c* in
`nums` such that *a* + *b* + *c* = 0? Find all unique triplets in the array
which gives the sum of zero.  The solution set must not contain duplicate
triplets.

*Difficulty: medium.*

Discussion
----------

The brute-force solution of computing the sum of every triplet is clearly
untenable here. However, we can reduce the problem to the repeated application
of what we came up with for [Two Sum](../leet1/README.md) earlier with only a
little creative insight. If anything, this version of Three Sum is simpler,
because it only requires us to report the addends themselves, not their
positions in the input.

Suppose we sort the input in ascending order. Then, as we consider some
`nums[i]`, if there exist `nums[j]` and `nums[k]` that all together sum to zero,
it is clear that *i* &le; *j* &le; *k*. In other words, we can reduce Three Sum
to repeated Two Sum, where `-nums[i]` is the target sum and the tail of the
input is the set of values we must consider.

Because the input (and, therefore, any subset of it) is already in sorted order,
we can slightly improve how we search for *j* and *k*. We position cursors at
`i + 1` and the last value in `nums`, and consider their sum. If this number is
greater than the target (`-nums[i]`), we decrement *k*. Because `nums[k - 1]` is
at most `nums[k]`, the sum will at worst remain the same. Analogously, if the
sum is too low, we increment *j*. By the time when *j* meets *k*, we will have
found every pair of numbers that sum to `nums[i]`.

Note that there could be multiple valid choices for *j* and *k*, as
input could contain duplicates. We must complete the iteration for each choice
of *i*.

Note that the function must not return the same set of three integers more than
once. Because we sort our input and iterate over it in sequence, we will never
return (*i*, *j*, *k*) and (*j*, *i*, *k*), and so on. The remaining problem is
numbers that occur more than once. Our options are to deduplicate the input up
front, or skip duplicates when we increment *i*, increment *j*, and decrement
*k*.

The laziest option is to deduplicate the response by sticking it into a set, if
your language provides a convenient implementation.
