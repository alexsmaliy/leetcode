Problem Statement
-----------------

Given an array `nums` of *n* integers where *n*&thinsp;>&thinsp;1,  return an
array output such that `output[i]` is equal to the product of all the elements
of nums except `nums[i]`.

Note: Please solve it without division and in *O*(*n*).

Follow up: Could you solve it with constant space complexity? (The output array
does not count as extra space for the purpose of space complexity analysis.)

*Difficulty: medium.*

Discussion
----------
This is a trick problem. The insight is as follows:
<pre><tt>INPUT:   [a, b, c, d, e]
PRODUCT: [bcde, acde, abde, abce, abcd]

         ┌───────────┐     ┌──────────────────────┐
         │   a a a a │     │ 1    a   ab abc abcd │
         │ b   b b b │ ==> │ ×    ×   ×  ×   ×    │
         │ c c   c c │     │ bcde cde de e   1    │
         │ d d d   d │     └──────────────────────┘
         │ e e e e   │
         └───────────┘
</tt></pre>
Namely, we can divide the *i*-th product into two factors: the product of all
elements before, and the product of all elements after.

We create the output array and set every element to 1. Then we do a forward pass
with an accumulator. We multiply the *i*-th element of the output by the
accumulator, and then multiply the accumulator by the *i*-th element of the
input array. The forward accumulator thus takes on values 1,
*a*, *a*&thinsp;&times;&thinsp;*b*,
*a*&thinsp;&times;&thinsp;*b*&thinsp;&times;&thinsp;*c*, and so on.

When we get to the end, we do an reverse pass. The reverse accumulator takes on
values 1, *e*, *e*&thinsp;&times;&thinsp;*d*,
*e*&thinsp;&times;&thinsp;*d*&thinsp;&times;&thinsp;*c*, and so on.

Or, alternatively, you could discover that computer division used to be
implemented in many CPUs using binary long division, and think that that's what
this question is about: implementing binary long division by hand. Fun!
