Problem Statement
-----------------

Given a set of candidate numbers (`candidates`) (without duplicates) and a
target number (`target`), find all unique combinations in `candidates` where the
candidate numbers sums to `target`.

The same repeated number may be chosen from `candidates` unlimited number of
times.

Note:
- All numbers (including `target`) will be positive integers.
- The solution set must not contain duplicate combinations.

*Difficulty: medium.*

Discussion
----------

There's a recursive way to do this and a bottom-up way to do this. Let's do it
bottom-up, because it lets us visit each solution exactly once with no need
to memoize.

We'll make a table of the number of ways to add up to every amount from 0 to
`target`, first using every coin, then every coin but the first, every coin but
the first two, and so on. You could also set this up using all but the last,
all but the last two, etc. It helps to sort the coins first.

Let's consider coins of value 1, 2, and 3, and the target amount of 4.

<pre><tt>            0    1    2    3    4
          ┏━━━━━━━━━━━━━━━━━━━━━━━━━━┓
{1, 2, 3} ┃ 1                        ┃ solutions starting with 1
          ┃                          ┃
   {2, 3} ┃ 1                        ┃ solutions starting with 2
          ┃                          ┃
      {3} ┃ 1                        ┃ solutions starting with 3
          ┃                          ┃
        ∅ ┃ 1    0    0    0    0    ┃ solutions starting with nothing
          ┗━━━━━━━━━━━━━━━━━━━━━━━━━━┛</tt></pre>
It's clear that there is one way to make change for an amount equal to zero: no
coins. It's also clear that zero coins can only make change for an amount equal
to 0. How do we fill in the rest of this table?

How many ways are there to give change for 4 using only the 3-coin? Well, all
combinations (with coins in ascending order) either start with the 3-coin or
they don't.

The ones that don't start with the 3-coin are the ones that must use no coins at
all. There is no coin larger than the 3-coin. They are all the ways to make
change for 4 with nothing. It's the entry in our table for amount equal to 4 and
the set of coins being the empty set. There are zero of them.

And there are as many combinations that do start with the 3-coin as there are
ways to make change for a sum of 4&thinsp;&minus;&thinsp;3 using only the
3-coin. Namely, take any combination that only uses the 3-coin to add up to 1,
and add a second 3-coin. Now you have a combination that adds up to 4.

But of course, there is no way to use coins of value 3 to add up to 1, so that's
zero as well.

The insight is that every unfilled cell in the table is the sum of the cell
directly below (solutions that don't use any of the preceding coins) and the
cell that's one current coin's worth to the left of it. If that puts us past the
left edge of the table (as with trying to make change for 1 using a 3), we
consider that set of solutions to be empty and only use the one below. We don't
go past the bottom row.
<pre><tt>            0      1           2           3            4
          ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
{1, 2, 3} ┃ 1      1           2           3            4       ┃ solutions starting with 1
          ┃   (0,0)+(1,1) (0,1)+(1,2) (0,2)+(1,3)  (0,3)+(1,4)  ┃
   {2, 3} ┃ 1      0           1           1            1       ┃ solutions starting with 2
          ┃     ∅+(2,1)   (1,0)+(2,2) (1,1)+(2,3)  (1,2)+(2,4)  ┃
      {3} ┃ 1      0           0           1            0       ┃ solutions starting with 3
          ┃     ∅+(3,1)     ∅+(3,2)   (2,0)+(3,3)  (2,1)+(3,4)  ┃
        ∅ ┃ 1      0           0           0            0       ┃ solutions starting with nothing
          ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛</tt></pre>
The finished table tells us which combinations are possible, and which are not.
We can now read off the solutions, starting at the top right corner. We are in
the row for the 1-coin. If we step 1 to the left, we are looking at solutions
that start with 1. Another step to the left: 1, 1. Then, 1, 1, 1. And, after one
final step, we reach the left edge and a complete combination: (1, 1, 1, 1).

We could also take a step down from the top left corner and consider only
combinations that begin with other coins. In the row for the 2-coin, each step
to the left by 2 commits us to an additional 2-coin. We can step twice before
reaching the left edge: the solution (2, 2). If we reach any cell with value 0,
we know that no solutions have that coin in that position, so the best we can do
is check one row down.
<pre><tt>
            0             1            2          3       4
          ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
{1, 2, 3} ┃ 1             1            2          3       4   ┃ solutions starting with 1
          ┃ [1, 1, 1, 1]  [1, 1, 1,…]  [1, 1, …]  [1, …]  […] ┃
   {2, 3} ┃ 1             0            1          1       1   ┃ solutions starting with 2
          ┃ [1, 1, 2]                  [1, 1, …]              ┃
      {3} ┃ 1             0            0          1       0   ┃ solutions starting with 3
          ┃ [1, 3]                                [1, …]      ┃
        ∅ ┃ 1             0            0          0       0   ┃ solutions starting with nothing
          ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛</tt></pre>
Three paths through the table are illustrated above:
1. Staying in the 1-coin row and going left one cell at a time produces the
   combination (1, 1, 1, 1).
1. Going two steps to the left, stepping down to the 2-coin row, and jumping two
   more cells to the left gets us (1, 1, 2). Note that if we had gone down to
   the 2-coin row after only one step left in the 1-coin row, we'd have ended up
   on a cell with value zero: no valid combinations start with (1, 2, &hellip;).
1. If we take one step to the left, then skip down to the 3-coin row and jump
   three cells to the left, we end up with the combination (1, 3).

The path down and to the left through the table is, at worst, `target` plus the
number of coins, so the recursion should never get too deep.

Similar to [Coin Change](../leet322) and [Combination Sum IV](../leet377). The
former asks for the smallest solution size. The latter considers solutions that
are only different in coin order to be distinct.

An Aside
--------

At first it seemed like a waste to do a second pass over the table to assemble
the combinations. You can, in fact, assemble the combinations as you fill in
the table. Instead of adding up the integer values as part of the recurrence
relation, you could append new elements to a set of prefixes. Each cell would
then hold the list of valid combinations for that row and column in the table.

Sadly, at least in Java, you'd then have to make a deep copy of the combinations
you retrieve from preceding cells. Otherwise, after setting the value at some
row and column (*r*, *c*), you'd keep iterating through the row, and making
changes to (*r*, *c*&thinsp;+&thinsp;1), and so on. But all those changes would
then affect what you saved at (*r*, *c*), so when you went one row up, you'd now
have the wrong set of prefixes. We vastly reduce the amount of copying if we do
not compute the complete set of combinations for each cell.
