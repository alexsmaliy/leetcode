Problem Statement
-----------------

Given a linked list, return the node where the cycle begins. If there is no
cycle, return `null`. To represent a cycle in the given linked list, we use an
integer `pos` which represents the position (0-indexed) in the linked list where
tail connects to. If `pos` is -1, then there is no cycle in the linked list.

Note: Do not modify the linked list.

Follow-up: Can you solve it without using extra space?

*Difficulty: medium.*

Discussion
----------

This is [Problem 141](../leet141) with some additional cleanup. Just like we did
earlier, we race the tortoise and the hare until they are equal. This gives us
a cycle between the tortoise and the hare, but not the *shortest* cycle, nor the
*first* incidence of the cycle. Namely, if the cycle length is *p*, but it
doesn't start until well into the list, we likely found some multiple *kp*
instead. And it's possible we've been going around the loop for a few iterations
before finding this *kp*.

We could try to find the prime factorization of *kp*. If we knew that
*kp*&thinsp;=&thinsp;*f<sub>0</sub>f<sub>1</sub>f<sub>2</sub>*&hellip;, we could
try advancing the tortoise from its last recorded position by *f<sub>0</sub>*,
by *f<sub>1</sub>*, and so on, until we found the shortest cycle.

But since prime factorization is kind of hard, we could just advance the
tortoise by 1 from its last recorded position until we find a repeat. This gives
us the shortest cycle period *p*.

Now that we know that, all that remains is to find where this cycle starts. We
reset the tortoise to the start of the list and check (0, *p*), (1,
*p*&thinsp;+&thinsp;1), (2, *p*&thinsp;+&thinsp;2), and so on, until we find the
first instance of the loop.
