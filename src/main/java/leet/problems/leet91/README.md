Problem Statement
-----------------

A message containing letters from A-Z is being encoded to numbers using the
following mapping:

<tt>'A' &rarr; 1<br>
'B' &rarr; 2<br>
&nbsp;&hellip;<br>
'Z' &rarr; 26
</tt>

Given a non-empty string containing only digits, determine the total number of
ways to decode it.

*Difficulty: medium.*

Discussion
----------

Ugh. The insight about the recurrence arrives almost immediately. Compare this
problem with [Climbing Stairs](../leet70) and [House Robber](../leet198). The
iterative solution clearly has the following structure:
```java
if (ambiguous) {
    numWays[i] = numWays[i + 2] + numWays[i + 1];
} else {
    numWays[i] = numWays[i + 1];
}
```
Namely, if we encounter an ambiguous sequence of two characters, we can either
parse them as one two-digit number, and then parse the rest, or parse the first
digit as a number and then parse the rest.

But what sequences are ambiguous?
- It's immediately clear that 11 is ambiguous ("1, 1" versus "11"), but 10 is
  not.
- Similarly, 21 is ambiguous, but 20 and 27 are not.
- It's harder to notice that 11 is ambiguous, but not if it's followed by 0.
  That is, 110 is only "1, 10", not "11, 0" or "1, 1, 0."

LeetCode will also test against strings that are invalid, and expect an answer
of 0 for those.
- A sequence like "#0" makes the whole string invalid, unless # is 1 or 2.
- A string that begins with 0 is invalid. In particular, the string "0" is
  invalid.

I'm sure there's a less messy way to do it, but that's what I ended up with.
