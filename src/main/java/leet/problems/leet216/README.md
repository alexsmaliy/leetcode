Problem Statement
-----------------

Find all possible combinations of *k* numbers that add up to a number *n*, given
that only numbers from 1 to 9 can be used and each combination should be a
unique set of numbers.

Note:
- All numbers will be positive integers.
- The solution set must not contain duplicate combinations.

*Difficulty: medium.*

Discussion
----------

This is another coin change problem. The coins have values 1-9 and can only be
used once. There is an additional constraint: we must use *k* coins at a time.

We could reuse the solution from [Combination Sum II](../leet40). We only need
to add the constraint that combinations longer than *k* coins are disallowed.

But a simple recursive approach seems to work just fine here. We keep track of
which digits have been used, using something like a boolean array or a `BitSet`,
if your language makes something like that available.

At each iteration, we check how many bits are already set. If it's more than
*k*, we stop recursing. If it's exactly *k*, we check whether we have reached a
solution and also stop recursing. Otherwise, we iterate over the remaining unset
bits, flipping it to true and recursing further.
