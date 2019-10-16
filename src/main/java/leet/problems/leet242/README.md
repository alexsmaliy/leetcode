Problem Statement
-----------------

Given two strings *s* and *t*, write a function to determine if *t* is an
anagram of *s*. You may assume the string contains only lowercase alphabets.
What if the inputs contain unicode characters? How would you adapt your solution
to such case?

*Difficulty: easy.*

Discussion
----------

In the trivial case of two strings containing only `[a-z]`, we don't need to do
anything more complicated than check if *s* and *t* are the same length, and
then verify that they contain the same number of each letter. Two arrays of
length 26 suffice. Proceed linearly over each string, incrementing the count of
whatever the *i*-th letter in each one is, and compare the two character count
arrays at the end of the process.

If the alphabet of characters was much larger (or unknown altogether), we'd have
to sort the characters within the two strings and compare them that way.
