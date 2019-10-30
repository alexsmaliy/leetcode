Problem Statement
-----------------

Given an integer array with all positive numbers and no duplicates, find the
number of possible combinations that add up to a positive integer target.

Example: `nums = [1, 2, 3]`, `target = 4`.

The possible combination ways are:<pre>
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)</pre>

Note that different sequences are counted as different combinations. Therefore
the output is 7.

*Difficulty: medium.*

Discussion
----------

It's weird to call these "combinations" when the order matters.

This is a dynamic programming problem, so let's do it bottom-up. It's simple.

Make an array with slots ranging from 0 to the target sum. There is one set of
coins that adds up to 0, so set the zero slot to 1. Iterate over the rest from
1 to the end. The *i*-th slot represents the number of ways to make
change for *i* using any number of any of the coins provided. The number of ways
to make change for *i* is the sum of the ways to make change for *i* starting
with the first coin, the second coin, etc. The number of ways to make change for
*i* starting with a given coin *c* is equal to the number of ways to make change
for *i*&thinsp;&minus;&thinsp;*c*. Namely, the *i*-th slot in the array is the
sum of all the possible *i*&thinsp;&minus;&thinsp;*c* slots.

If we sort the coins up-front, we can even skip the check for whether the index
*i*&thinsp;&minus;&thinsp;*c* is valid.

Similar to, but different from [Coin Change](../leet322). This one
asks for permutations of coins. That one asks for the fewest coins needed to
make up the target amount. There are so many ways to make change.
