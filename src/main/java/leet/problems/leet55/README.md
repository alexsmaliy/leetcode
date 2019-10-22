Problem Statement
-----------------

Given an array of non-negative integers, you are initially positioned at the
first index of the array. Each element in the array represents your maximum jump
length at that position. Determine if you are able to reach the last index.

*Difficulty: medium.*

Discussion
----------

To rephrase, given input array *S*, if our current position is *i*, we can
advance by *S<sub>i</sub>* steps, *S<sub>i</sub>*&thinsp;&minus;&thinsp;1 steps,
*S<sub>i</sub>*&thinsp;&minus;&thinsp;2 steps, &hellip;, or as few as 0 steps.

Is this a medium problem because something something recursion? I've found tons
of other "medium" LeetCode problems much harder to crack. But here, a simple
iterative solution suggests itself immediately.

The last position can clearly reach itself (by making zero steps). If the
next-to-last position can reach the last position, we change the target to be
the next-to-last position. If the one before the next-to-last cannot reach the
next-to-last, well, maybe the one before that can reach the next-to-last.
Proceed this way toward the start of the array. If the first position can reach
any position that itself can reach the last position, return true.

Example:
<pre><tt>                         i & target
                         &darr;
[4, 5, 3, 2, 1, 0, 2, 1, 2]</tt></pre>
<pre><tt>                      i  target
                      &darr;  &darr;
[4, 5, 3, 2, 1, 0, 2, 1, 2]
One step is enough to reach target. Update target.</tt></pre>
<pre><tt>                   i  target
                   &darr;  &darr;
[4, 5, 3, 2, 1, 0, 2, 1, 2]
Two steps are more than enough to reach target. Update target.</tt></pre>
<pre><tt>                i  target
                &darr;  &darr;
[4, 5, 3, 2, 1, 0, 2, 1, 2]
Zero steps are not enough to reach target. Do not update.</tt></pre>
<pre><tt>             i     target
             &darr;     &darr;
[4, 5, 3, 2, 1, 0, 2, 1, 2]
One step is not enough. Do not update.</tt></pre>
<pre><tt>          i        target
          &darr;        &darr;
[4, 5, 3, 2, 1, 0, 2, 1, 2]
Two steps are not enough. Do not update.</tt></pre>
<pre><tt>       i           target
       &darr;           &darr;
[4, 5, 3, 2, 1, 0, 2, 1, 2]
Three steps are not enough. Do not update.</tt></pre>
<pre><tt>    i              target
    &darr;              &darr;
[4, 5, 3, 2, 1, 0, 2, 1, 2]
Five steps are just enough. Update target.</tt></pre>
<pre><tt> i  target
 &darr;  &darr;
[4, 5, 3, 2, 1, 0, 2, 1, 2]
Four steps are more than enough to reach the new target.</tt></pre>
Conclusion: first index can reach last index.
