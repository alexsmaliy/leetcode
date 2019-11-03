Problem Statement
-----------------

Given a singly linked list, group all odd nodes together followed by the even
nodes. Please note here we are talking about the node number and not the value
in the nodes.

You should try to do it in place. The program should run in *O*(1) space
complexity and *O*(nodes) time complexity.

Note:
- The relative order inside both the even and odd groups should remain as it was
  in the input.
- The first node is considered odd, the second node even and so on&hellip;


*Difficulty: medium.*

Discussion
----------

This is not a conceptually difficult problem. We set up two linked lists for odd
and even elements. Then we iterate over the original linked list, alternately
appending list nodes to the odd and even list. At the end, we append the even
list to the end of the odd list.

It does take a little bit of attention to keep track of the original heads of
odd and even lists, and making sure the tails of the odd list is not null when
you try to append the even list to it.
