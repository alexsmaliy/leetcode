Problem Statement
-----------------

Given a string, find the length of the longest *substring* without repeating
characters.

*Difficulty: medium.*

Discussion
----------

I find that the idea of a moving window comes naturally, but doing it right is
tricky. We move from one end of the string to the other (say, left to right). If
the *i*-th character is not already present in our moving window, we advance the
leading edge of the window to include it. If the *i*-th character is already in
our moving window, we bring up the trailing edge of the window until we exclude
it, then advance the leading edge to include its new position. For example:
<pre>
 [] mississippi
 [m] ississippi
 [mi] ssissippi
 [mis] sissippi
 mis [s] issippi
 mis [si] ssippi
 missi [s] sippi
 missis [s] ippi
 missis [si] ppi
 missis [sip] pi
 mississip [p] i
 mississip [pi]
</pre>
Note that the leading edge of the window moves through every position in the
input string. Namely, we consider substrings that end at every possible position
of the input. Moreover, for every leading edge position, we find the longest
substring that meets the problem criteria and ends at that position. Therefore,
the globally best substring has to be one of the ones we find using this
process, and it's only a matter of keeping track of what the best one we have
found is.

We do have to decide how to bring up the trailing edge of the window as cheaply
as possible. One possibility is to keep track of the previous index in the
string at which we have seen the *i*-th character using a map. If the previous
index can be found (and it is within the current extent of the moving window),
we bring up the trailing edge to just past that index.

An even simpler alternative, in languages that allow it, is to treat the
characters in the input string as indexes, and use an array for lookups instead
of a map. You do need to make an assumption about the size of the input string's
character set. For ASCII, an array of length 128 would suffice.
