Problem Statement
-----------------

You are a professional robber planning to rob houses along a street. Each house
has a certain amount of money stashed, the only constraint stopping you from
robbing each of them is that adjacent houses have security system connected and
it will automatically contact the police if two adjacent houses were broken into
on the same night.

Given a list of non-negative integers representing the amount of money of each
house, determine the maximum amount of money you can rob tonight without
alerting the police.

*Difficulty: easy.*

Discussion
----------

In other words, "what is the maximum sum of any subsequence in which no two
elements are immediately next to each other." The problem suggests an obvious
recurrence:
- If there is only one house, the best I can do is to rob it.
- If there are two houses, the best I can do is to rob the one with more money.
- If there are more than two houses, the best I can do is whichever is better:
  - Rob the nearest, then skip one and rob the rest using these rules.
  - Skip the nearest and rob the rest using these rules.

For whatever reason, I find working from the end of the array more intuitive in
scenarios like these, but that's clearly immaterial. Like many similar problems,
this one admits both a recursive solution or an iterative solution.

If you pursue the iterative solution, you will work from one end of the array
and assign to each successive slot the best haul you'd get if you started
robbing from that house. Because you only ever look at the `i + 1` and `i + 2`
elements for each `i`, you can even do this in *O*(1) space by overwriting the
elements of the input array as you iterate over them.
