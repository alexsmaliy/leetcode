Problem Statement
-----------------

Given an integer array `nums`, find the contiguous subarray (containing at least
one number) which has the largest sum and return its sum.

Follow up: If you have figured out the *O*(*n*) solution, try coding another
solution using the divide and conquer approach, which is more subtle.

*Difficulty: easy.*

Discussion
----------

Despite being categorized as "easy," even the *O*(*n*) solution took me a while
to understand. Apparently it even has a name: Kadane's algorithm. That fact
alone suggests that this is something you know, not something you invent on the
spot.

But if you're used to thinking about moving window approaches to problems, it's
actually pretty simple. Conceptually, we iterate through the input element by
element. At every iteration, we advance the leading edge of the window by one
and consider the sum of the elements in the window. If we are on the first
element of the array, the sum is clearly just that element. For elements after
the first, we want to pick the optimal sum by bringing up the trailing end of
the sliding window as needed.

If the sum of the preceding elements in the window is positive, then we keep the
trailing end where it is and add in the new element. This is the maximum sum
starting at the current element and going left.

If the sum of the preceding elements in the window is negative, then the current
element by itself is a better sum. This is analogous to bringing up the trailing
end of the window.
<pre><tt>[-2]  1  -3  4  -1  2  1  -5  4   sum = -2
-2  [1]  -3  4  -1  2  1  -5  4   sum =  1 (better than -2 + 1)
-2  [1  -3]  4  -1  2  1  -5  4   sum = -2
-2  1  -3  [4]  -1  2  1  -5  4   sum =  4 (better than 1 + -3 + 4)
-2  1  -3  [4  -1]  2  1  -5  4   sum =  3
-2  1  -3  [4  -1  2]  1  -5  4   sum =  5
-2  1  -3  [4  -1  2  1]  -5  4   sum =  6 ✔
-2  1  -3  [4  -1  2  1  -5]  4   sum =  1
-2  1  -3  [4  -1  2  1  -5  4]   sum =  5</tt></pre>
Because we end up considering the optimal sliding window sum for every element
in the input array, the global best must be among them. To identify it, we just
maintain a variable that we can update every time we find a better candidate
sum.
