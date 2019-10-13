Problem Statement
-----------------

Given a string *s*, a *k duplicate removal* consists of choosing *k* adjacent
and equal letters from *s* and removing them causing the left and the right side
of the deleted substring to concatenate together. We repeatedly make *k*
duplicate removals on s until we no longer can. Return the final string after
all such duplicate removals have been made. It is guaranteed that the answer is
unique.

*Difficulty: medium.*

Discussion
----------

This is a slightly more general case of [LC 1047](../leet1047), and this
solution will work for that problem as well.

The insight here, much like in LC 1047, is that removing one *k*-duplicate may
uncover another when we splice the remaining pieces back together. If *k* = 3,
removing the repeated *b* in *aabbbac* uncovers a new repeat *aaa*. In other
words, when we remove a repeat, we need to keep track of how many times the
previous character has been seen.

That sounds like a stack of some kind, but we can just fake one with two arrays
that share a cursor variable. One stores the characters we encounter in *s*, and
the other stores how many repeats we have seen of that character. If the *i*-th
character in *s* is not a repeat of the preceding one, we advance the cursor,
record what character it is in one array, and set its count in the other array
to 1. If the character is a repeat, we increment its count in the count array.
If the count is now *k*, we decrement the cursor ("deleting" the repeat).
<pre>
("mississippi", k = 2)

    [m]         [1]
    [m, i]      [1, 1]
    [m, i, s]   [1, 1, 1]
    [m, i, s]   [1, 1, 2]
    [m, i]      [1, 1]
    [m, i]      [1, 2]
    [m]         [1]
    [m, s]      [1, 1]
    [m, s]      [1, 2]
    [m]         [1]
    [m, i]      [1, 1]
    [m, i, p]   [1, 1, 1]
    [m, i, p]   [1, 1, 2]
    [m, i]      [1, 1]
    [m, i]      [1, 2]
    [m]         [1]
</pre>
