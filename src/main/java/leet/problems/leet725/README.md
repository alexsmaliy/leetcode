Problem Statement
-----------------

Given a (singly) linked list with head node `root`, write a function to split
the linked list into *k* consecutive linked list "parts".

The length of each part should be as equal as possible: no two parts should have
a size differing by more than 1. This may lead to some parts being null.

The parts should be in order of occurrence in the input list, and parts
occurring earlier should always have a size greater than or equal parts
occurring later.

Return a `List` of `ListNode` representing the linked list parts that are
formed. 

*Difficulty: medium.*

Discussion
----------

This only challenging part of this problem is the arithmetic. Once we know how
long the sublists must be, we can just jump to the corresponding cutoff points
in the input and set the preceding node to point to null to "snip off" a
sublist.

The test cases for this problem must not be very large, because sticking
LeetCode's singly linked list into a Java `ArrayList` for arbitrary access did
not reflect poorly on the runtime.

One simple approach to calculating the length of each sublist is to consider
sublists of length &lceil;*n*&#x002f;*k*&rceil;. For example, with an input list
of length *n*&thinsp;=&thinsp;10 and 6 sublists, we'd consider sublists of size
&lceil;10&#x002f;6&rceil;&thinsp;=&thinsp;2. But we don't have
6&thinsp;&times;&thinsp;2&thinsp;=&thinsp;12 elements, we only have 10. Thus,
the last two lists will have to be shorter by one element, with lengths
`[2, 2, 2, 2, 1, 1]`.

Now that we know how many lists of length &lceil;*n*&#x002f;*k*&rceil; and how
many of length &lceil;*n*&#x002f;*k*&rceil;&thinsp;&minus;&thinsp;1 we must
assemble, we can just proceed linearly through the input, advancing by the
corresponding quantity and creating new lists as we go.

The case of *n*&thinsp;&lt;&thinsp;*k* can be handled separately, by making *n*
lists of length 1 and leaving the rest null.
