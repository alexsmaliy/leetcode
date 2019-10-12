Problem Statement
-----------------

You are given two non-empty linked lists representing two non-negative integers.
The digits are stored in reverse order and each of their nodes contain a single
digit. Add the two numbers and return it as a linked list. You may assume the
two numbers do not contain any leading zero, except the number 0 itself.

*Difficulty: medium.*

Discussion
----------

The goal is simply the long addition of two integers, just like you might do it
by hand, but using LeetCode's linked lists instead. The digits of the two
addends are in reverse order because long addition by hand starts from the least
significant digit (the "end" of the number).

We iterate over the digits `l1[i]` and `l2[i]` in each of the two inputs, and
set the corresponding digit in the output to `(l1[i] + l2[i] + carry) % 10`. The
updated carry is `(l1[i] + l2[i] + carry) / 10` (integer division).

The only sources of challenge are accidental carelessness and remembering to
deal with the remaining carry after you run out of digits in the input.
