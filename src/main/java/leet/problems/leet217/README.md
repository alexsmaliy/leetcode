Problem Statement
-----------------

Given an array of integers, find if the array contains any duplicates. Your
function should return true if any value appears at least twice in the array,
and it should return false if every element is distinct.

*Difficulty: easy.*

Discussion
----------

Sort the input and iterate from start to finish. Any duplicates will now be
in consecutive order.

The alternative to sorting is to stick each element into a membership structure,
like a set or a map, and check if we've seen a given element before inserting
it. Or check at the end whether the size of the set is smaller than the size of
the input.
