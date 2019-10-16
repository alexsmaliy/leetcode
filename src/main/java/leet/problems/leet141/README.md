Problem Statement
-----------------

Given a linked list, determine if it has a cycle in it. To represent a cycle in
the given linked list, we use an integer `pos` which represents the position
(0-indexed) in the linked list where tail connects to. If `pos` is -1, then
there is no cycle in the linked list. Can you solve it using *O*(1) (i.e.
constant) memory?

*Difficulty: easy.*

Discussion
----------

The description I copied above was what LeetCode had on 2019-10-16, when I tried
solving this problem, and it didn't match the signature of the function stub
they provided for users, which (at least in Java) was `boolean hasCycle(ListNode
head)`. It looks like the task is just to start at the given node and detect
whether there is a cycle, not report where it starts.

This is a textbook problem that calls on you to remember (or reinvent) Flynn's
algorithm, also called the "tortoise and hare" algorithm.

The algorithm is very simple. We iterate two cursors through the list. The first
cursor (the slow "tortoise") advances one position per turn. The second cursor
(the fast "hare") advances two positions per turn. Every turn, we check whether
the value of the tortoise is equal to the value of the hare. In the case of this
problem, we check for literal object equality, but this would also apply to
other periodic sequences, such as sequences of values of a periodic function.

Every turn, the distance between the tortoise and the hare increases by one. In
other words, we consider every possible cycle length in order.

<pre><tt>1 &rarr; 2 &rarr; 3 &rarr; 4 &#x21c6 5</tt>

<tt>[1, 2, 3, 4, 5, 4, 5, 4, &hellip;]
 &uarr;  &uarr;  &uarr;  &uarr;     &uarr;     &uarr;
 <u>T  H</u>  &uarr;  &uarr;     &uarr;     &uarr;
    &uarr;  &uarr;  &uarr;     &uarr;     &uarr;
    <u>T  &uarr;  H</u>     &uarr;     &uarr;
       &uarr;  &uarr;     &uarr;     &uarr;
       <u>T  &uarr;     H</u>     &uarr;
          &uarr;           &uarr;
          <u>T           H</u> &#x2714;</tt>
</pre>

Suppose the input list has a long prefix of unique value before the cycle starts
looping. When we do reach the looping region, we would be considering cycle
periods larger than the actual cycle length of the looping region. We'd still
detect the presence of a cycle, however, because a cycle that repeats every *n*
elements obviously also repeats every 2*n* elements, every 3*n* elements, and so
on.

In other words, we'd have to do a bit more work to find the shortest cycle or
where cycling started, but we'll definitely know that there's a cycle somewhere
eventually. Not surprisingly, [Problem 142](../leet142) asks us to do exactly
that.

Tortoise and hare is very cheap. Its memory overhead is just the two pointers,
and its runtime is either the length of the list (if there is no cycle), or the
length of the unique prefix plus the next largest multiple of the cycle length.
For example, if there is a unique prefix of length 10, followed by a cycle of
length 3, we'd be considering cycles of length 10 before we reach the start of
the loop, and we'd need to consider a cycle of length 12 before we'd find the
actual cycle of length 3.
