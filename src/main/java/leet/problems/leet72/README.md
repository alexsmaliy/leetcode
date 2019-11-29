Problem Statement
-----------------

Given two words _word1_ and _word2_, find the minimum number of operations
required to convert _word1_ to _word2_.

You have the following 3 operations permitted on a word:
1. Insert a character
1. Delete a character
1. Replace a character

*Difficulty: hard.*

Discussion
----------

String edit distance is a canonical dynamic programming problem. The outline of
the solution to it is worth memorizing, because it can be applied with slight
modifications to a range of similar problems.

Like with all dynamic programming problems, you can devise a recursive solution
and a bottom-up dynamic programming solution. I find the latter easier to
remember. It's also the more efficient solution, even if you were to memoize the
intermediate steps in the recursive solution. All of this is typical of DP
problems.

The recursive way to find the minimum edit distance looks a lot like a
change-making problem. You have a menu of operations you can apply to _word1_ to
turn it into _word2_. Here we have insertion, deletion, and replacement. Often,
a transpose operation ("ab" &rarr; "ba") is included as well. We keep track of
our positions in _word1_ and _word2_ (call these position markers _i_ and _j_).
At each recursive step, we take the lowest of all valid possibilities:
1. If _i_ and _j_ have reached the ends of _word1_ and _word2_, the edit
   distance is 0 (namely, the edit distance between two empty strings).
1. If only one of _i_ and _j_ has reached the end of its string, the edit
   distance is the length of the remaining prefix of whichever string is left
   over. This is the edit distance between a non-empty string and the empty
   string.
1. Otherwise, we compute and return the _lowest_ of several recursive cases:
   1. If `word1[i]` is the same as `word2[j]`, compute the (recursive) edit
      distance with _i_&thinsp;+&thinsp;1 and _j_&thinsp;+&thinsp;1. This is
      how we represent skipping a matching character in both strings.
   1. Compute 1 plus the recursive edit distance with _i_ and
      _j_&thinsp;+&thinsp;1. This is how we represent the operation of inserting
      the _j_-th character of _word2_ into _word1_.
   1. Compute 1 plus the recursive edit distance with _i_&thinsp;+&thinsp;1 and
      _j_. This is how we represent deleting the _i_-th character in _word1_.

Other operations (like transposition of two characters) are represented
similarly. For a transpose, you'd check if `word1[i]` is the same as
`word2[j+1]` and vice versa, then increment the cost by 1 and skip two
characters. That's not the hard part of the problem.

The edit distance between _word1_ and _word2_ is equal to the edit distance
between _word2_ and _word1_ (i.e., it is symmetrical). Nevertheless, since the
operations we are given are typically asymmetrical in nature (insertion _into_,
deletion _from_), I find it easier to model the problem in terms of turning
_word1_ into _word2_.

Conceptually, the recursive approach computes the edit distance between every
suffix of _word1_ and every suffix of _word2_. The problem is that, given _n_
operations, every recursive call will itself make _n_ recursive calls. If
_word1_ has _w_<sub>1</sub> characters, that translates into something on the
order of _w_<sub>1</sub><sup>_n_</sup> calls. Even with memoization, that's a
lot.

The DP strategy, very typically, inverts this approach and computes the edit
distance between every suffix of _word1_ and every suffix of _word2_ exactly
once, for a memory and time complexity of _w_<sub>1</sub>_w_<sub>2</sub>.

We make a table of _w_<sub>1</sub>&thinsp;+&thinsp;1 rows and
_w_<sub>2</sub>&thinsp;+&thinsp;1 columns. The extra row and extra column are
for the empty-string suffixes. We fill in the minimum edit distances that we
know already: the edit distance between any string and the empty string is the
length of that string (e.g., "nana" and "" is 4).
<pre>    O R A N G E ""
  ┌──────────────┐
B │             6│
A │             5│
N │             4│
A │             3│
N │             2│
A │             1│
""│ 6 5 4 3 2 1 0│
  └──────────────┘</pre>
Then, we start at the first unfilled cell in the bottom right and proceed to
fill out the table. The cell (_i_, _j_) is the lowest of the available
alternatives:
1. If `word1[i]` equals `word2[j]`, the value (_i_&thinsp;+&thinsp;1,
   _j_&thinsp;+&thinsp;1). For example, the edit distance between "a123" and
   "a456" is no greater than the edit distance between "123" and "456."
1. The value (_i_&thinsp;+&thinsp;1, _j_) plus 1. This is our attempt to delete
   a character from _word1_.
1. The value (_i_, _j_&thinsp;+&thinsp;1) plus 1. This is our attempt to insert
   a character into _word1_.
1. The value (_i_&thinsp;+&thinsp;1, _j_&thinsp;+&thinsp;1) plus 1. This is our
   attempt to replace a character in _word1_.

Just as in the recursive case, your alternatives are determined by the set of
possible operations given in the problem definition.

This is the end result of filling out our example table.
<pre>    O R A N G E ""
  ┌──────────────┐
B │ 5 4 4 5 6 6 6│
A │ 4 4 3 4 5 5 5│
N │ 4 3 3 3 4 4 4│
A │ 4 3 2 3 3 3 3│
N │ 5 4 3 2 2 2 2│
A │ 5 4 3 3 2 1 1│
""│ 6 5 4 3 2 1 0│
  └──────────────┘</pre>
The (0, 0) entry is the minimum edit distance between "banana" and "orange."

Here are some related problems we can solve using only slight modifications:
- We can construct the actual sequence of edit operations that turn _word1_ into
  _word2_. To accomplish this, use cells in the table to store the operation, as
  well as the cost up to that point. You can then follow the chain of operations
  from the top left to the bottom right.
- We can find the longest common subsequence (in order, but not necessarily
  contiguous) of two sequences _s_ and _t_ by constructing an edit distance
  table using only the deletion, insertion, and skip operations (namely, those
  operations that preserve element order). If we consider the actual sequence of
  edit operations required, the longest common subsequence is the set of all
  "skip a character" operations in the edit sequence. This is because the
  minimum set of edits must preserve all elements common to both sequences.
- We can find the longest contiguous subsequence that two sequences _s_ and _t_
  share by constructing an edit distance table with a single update rule: the
  cell (_i_, _j_) is 1 plus (_i_&thinsp;+&thinsp;1, _j_&thinsp;+&thinsp;1) if
  _s_<sub>_i_</sub>&thinsp;=&thinsp;_t_<sub>_j_</sub>, and 0 otherwise. The
  longest common subsequence will be found at the maximum value in the table.
  Namely, if the maximum element in the table is equal to _k_ and is found at
  position (_i_, _j_), the longest subsequence is the set of _k_ elements
  starting at _s_<sub>_i_</sub> (or, equivalently, at _t_<sub>_j_</sub>).
