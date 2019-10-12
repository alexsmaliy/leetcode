Problem Statement
-----------------

Given an array of strings, group anagrams together. All inputs will be in
lowercase. The order of your output does not matter.

*Difficulty: medium.*

Discussion
----------

One-word anagrams partition a vocabulary of words into equivalence classes. Two
words belong to the same equivalence class if they are anagrams of each other.

We have a couple of options to identify a class uniquely. We could take one of
the words from it and arrange its letters in alphabetical order: "wolves" &rarr;
"elosvw." "Vowels" is an anagram of "wolves" and has the same key. We could also
identify each class as an array of 26 integers, representing the number of times
each letter occurs.

There are also a couple of simple strategies for grouping anagrams that scale as
*n*&thinsp;log<sub>2</sub>&thinsp;*n*, where *n* is the number of strings. We
can scan linearly through the strings and put each one into a key/value lookup
structure identified by its equivalence class (*n* iterations, each costing
something on the order on log<sub>2</sub>&thinsp;*n* for the lookup). We could
instead sort the list of strings by equivalence class. This way, we pay the
whole *n*&thinsp;log<sub>2</sub>&thinsp;*n* cost up-front, but the result is
that we can just cut up the list into continuous chunks in one pass.

The provided Java code shows the lookup approach with sorted character strings,
as well as the sorting approach with letter counts. The first approach does
slightly better, but it's hard to tell whether it's for interesting reasons, or
because the LeetCode benchmarking code penalizes programs for inline lambdas.
