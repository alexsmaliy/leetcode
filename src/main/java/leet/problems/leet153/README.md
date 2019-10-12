Problem Statement
-----------------

Suppose an array sorted in ascending order is rotated at some pivot unknown to
you beforehand. (i.e., `[0,1,2,4,5,6,7]` might become `[4,5,6,7,0,1,2]`). Find
the minimum element. You may assume no duplicate exists in the array.

*Difficulty: medium.*

Discussion
----------

This problem is tedious, but not conceptually difficult. We need to find the
index *i* in the input array `nums` such that `nums[i] < nums[i - 1]`. If *i* is
0, we should remember to map *i* - 1 correctly to the last element of `nums`.

If we eliminate the possibility that the shift is 0 (and the input array is in
normal sorted order), we can finish the task in ln<sub>2</sub> time using
midpoint divide-and-conquer.

Specifically, suppose we are sure that the discontinuity is somewhere between
`nums[i]` and `nums[k]`. We break up the range into halves:
`[nums[i], ..., nums[j]]` and `[nums[j + 1], ..., nums[k]]`. One of three things
is true:
- We find that `nums[j] > nums[j + 1]`, meaning we have found the discontinuity.
- We find that `nums[i] > nums[j]`, meaning that the left half is the region we
  should focus on.
- We find that `nums[j + 1] > nums[k]`, meaning that the discontinuity is in the
  right half.
  
As with all midpoint algorithms on arrays, be careful to think about what
happens when you recurse long enough to get search subregions of length 1 or 0,
and whether your bounds are exclusive or inclusive. I find it helpful to be as
explicit as possible about where the subregions start and end, so I can be
certain whether *i* < *j* or *i* &le; *j*, and so on.
