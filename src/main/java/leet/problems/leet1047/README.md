Problem Statement
-----------------

Given a string *S* of lowercase letters, a duplicate removal consists of
choosing two adjacent and equal letters, and removing them. We repeatedly make
duplicate removals on *S* until we no longer can. Return the final string after
all such duplicate removals have been made. It is guaranteed the answer is
unique. *S* consists only of English lowercase letters.

*Difficulty: easy.*

Discussion
----------

This is pretty trivial. We greedily remove all repeats of two of the same
character from *S*, including repeats that appear when a nested repeat has been
removed. To accomplish this, we allocate an array of the same length as *S* (for
the case of no repeats at all), then iterate through the characters in *S* in
order. If the *i*-th character is identical to its predecessor, we decrement the
cursor variable by one ("remove" the duplicate). Otherwise, we increment the
cursor by one and copy the *i*-th character in *S* to that position in the
output.

A more general version of this problem is encountered in [LC 1209](../leet1209).
