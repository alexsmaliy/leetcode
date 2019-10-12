Problem Statement
-----------------

Given an array of integers, return indices of the two numbers such that they add
up to a specific target. You may assume that each input would have exactly one
solution, and you may not use the same element twice.

*Difficulty: easy.*

Discussion
----------

It's great when a solution to a problem is simple to code, easy to remember, and
scales acceptably.

In the case of Two Sum, we can find the answer in a single pass by iterating
over the input and stuffing each value we find into some kind of cheap
lookup/membership data structure, like a map or a set. Then, as we encounter
each value `nums[i]`, we can check whether we have already encountered
`target - nums[i]`.

The problem asks for a single, unique solution, but, in concept, this process
will find all such solutions, if any exist.
