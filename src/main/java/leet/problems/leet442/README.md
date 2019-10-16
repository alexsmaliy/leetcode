Problem Statement
-----------------

Given an array of integers, 1 ≤ *a<sub>i</sub>* ≤ *n* (*n* = size of array),
some elements appear twice and others appear once. Find all the elements that
appear twice in this array. Could you do it without extra space and in *O*(*n*)
runtime?

*Difficulty: medium.*

Discussion
----------

This is a party-trick puzzle with a party-trick solution. The insight is as
follows: if every number 1 through *n* appeared in *a* exactly once, then
visiting `a[a[i]]` for each *i* between 1 and *n* would visit every element in
*a* exactly once. In other words, *a* is a permutation of 1 through *n*.

Then, if we could somehow mark each `a[a[i]]` when we visit it, but in a way
that doesn't destroy its original value, we would know if some elements in *a*
were visited twice.

How can we mark numbers 1 through *n*? Well, if we add a multiple of
n&thinsp;+&thinsp;1 to each one, we can recover it using modulo division.
<pre>
[1, 2, 3, 4, 5]
  &#x21b3; [1 + 6, 2 + 6, 3 + 6, 4 + 6, 5 + 6]
      &#x21b3; [7 % 6, 8 % 6, 9 % 6, 10 % 6, 11 % 6]
          &#x21b3; [1, 2, 3, 4, 5] 
</pre>
Similarly, we can find out how many times an element of *a* has been touched by
subtracting n&thinsp;+&thinsp;1 from it. If:
- 1 ≤ *a<sub>i</sub>* ≤ *n* after zero modifications (as the problem states),
- then &minus;&thinsp;*n* ≤ *a<sub>i</sub>* ≤ &minus;&thinsp;1
after one modification
- and &minus;&thinsp;2*n*&thinsp;&minus;1 ≤ *a<sub>i</sub>*
≤ &minus;&thinsp;2&minus;&thinsp;*n* after two.

Now we have the means to find out how many times the *i*-th element has been
visited, and what its original value was. We don't need to worry about integer
overflow, because in most scenarios it's hard to imagine an *n* of several
hundred million.

Putting the pieces together is simple:
- Iterate *i* from 1 to *n*.
- Recover the original value of `a[i]`, in case we have already modified it.
  Namely, add *n*&thinsp;+&thinsp;1 back into it more than twice and take the
  remainder modulo *n*&thinsp;+&thinsp;1.
- Index into *a* with the original value of `a[i]` and subtract
  *n*&thinsp;+&thinsp;1 from that element.
- Iterate from 1 to *n* again.
- If `a[i]` looks like it was modified twice, *i* is the number that's in *a*
  more than once (i.e., there are two elements *a<sub>m</sub>* and
  *a<sub>n</sub>* that are equal to each other, so that when we modified
  `a[a[m]]` and `a[a[n]]`, we were actually modifying the same element twice).
