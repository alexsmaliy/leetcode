Problem Statement
-----------------

Given a string *s* that consists of only uppercase English letters, you can
perform at most *k* operations on that string. In one operation, you can choose
*any* character of the string and change it to any other uppercase English
character. Find the length of the longest sub-string containing all repeating
letters you can get after performing the above operations.

*Difficulty: medium.*

Discussion
----------

There is a clever solution to this problem that uses a sliding window. I did not
come up with it, and even my explanation is very post hoc. I'd have trouble
reproducing this is an interview, and certainly have no hope of extending this
mechanism of action to another problem context. This is not a *medium* problem
for me.

What I did come up with was a worst-case *n*&sup2; solution (where *n* is the
length of the string), in which I simply iterated over the string and, for each
letter, checked how far I'd get on *k* repairs (worst case here is a string
`AAAA…` that needs no repair). I special-cased cases like `BAAAB` by adding a
look-ahead if a look-behind reached the beginning of the string with the
budget of repair operations not yet exhausted.

In any case, here's the clever solution:
- Use something like an array to keep track of the count of individual letters
  A&ndash;Z.
- Use two cursors, &ell; and *r*, to define the edges of a moving window. Both
  start at index 0.
- Iterate over the string by incrementing *r*. Also increment the count of the
  letter *s<sub>r</sub>* each time.
- If the count of the letter *s<sub>r</sub>* exceeds *m* (the previously
  recorded maximum count of a single letter), update *m*.
- If the width, in letters, of the moving window (i.e.,
  *r*&thinsp;&minus;&thinsp;&ell;&thinsp;+&thinsp;1) is larger than
  we can fill with *m*&thinsp;+&thinsp;*k*, decrement the count of
  *s<sub>&ell;</sub>* and advance &ell;. Because *r* advances every iteration,
  this represents the window sliding to the right by 1 without changing size.
- On the other hand, if *m*&thinsp;+&thinsp;*k* is sufficient to cover
  *r*&thinsp;&minus;&thinsp;&ell;&thinsp;+&thinsp;1, do not advance &ell;. This
  represents the sliding window growing by 1 in size.
- Because the window never shrinks, by the time *r* reaches the end of the
  string, the window is as large as it has ever been, and it's enough to report
  its current width |*s*|&thinsp;&minus;&thinsp;&ell;.

The array of character counts is always up to date. We increment and decrement
the count of a letter every time the right edge advances on one or the left edge
drops one. However, *m* is the not the max count of a single letter in the
current window. It's the max count of a single letter at any earlier point. We
don't actually need to know how often any given letter occurs in the current
window, if it's not often enough to allow us to extend it. We only need to know
if a letter has been encountered enough times to extend the window.

That's the insight of this solution, and not one I would be able to come up with
spontaneously.
