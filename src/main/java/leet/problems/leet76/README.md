Problem Statement
-----------------

Given a string *S* and a string *T*, find the minimum window in *S* which will
contain all the characters in *T* in complexity *O*(*n*).

Note:
1. If there is no such window in *S* that covers all characters in *T*, return
   the empty string `""`.
1. If there is such window, you are guaranteed that there will always be only
   one unique minimum window in *S*.

*Difficulty: hard.*

Discussion
----------

Two observations:
1. This problem is more tedious than difficult.
1. It's more difficult than it seems initially because the *multiplicity* of
   characters in *T* matters. The problem description does not make that clear.
   We want each all characters that appear in *T*, as many times as they appear
   in *T*.

This problem can be solved using the basic moving window approach. Keep track of
the leading edge and the trailing edge. Advance the leading edge until you have
found all the characters you need. Once that happens, bring up the trailing edge
until you've dropped some of the characters you need. Whenever the window is
valid, check if that's the smallest valid window located so far. Repeat.

We must decide how to keep track of which characters in *T* we have seen, and
how many. I decided to keep track of how much the current window is over/under
quota using a key-value map data structure (or, really, just the hack of an
array indexed by the numerical value of a given character). I separately kept
two set objects. One tracked the unique characters which we need more of in the
current window. That set being empty indicated that the window is valid, and we
should check if it is the best window so far. The other was just the set of
unique characters in *T*, so I just skip any other character when looping over
*S*.

Seemed to work well enough to beat the median on LeetCode.
