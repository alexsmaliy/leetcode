Problem Statement
-----------------

You are a professional robber planning to rob houses along a street. Each house
has a certain amount of money stashed. All houses at this place are arranged in
a circle. That means the first house is the neighbor of the last one. Meanwhile,
adjacent houses have security system connected and it will automatically contact
the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each
house, determine the maximum amount of money you can rob tonight without
alerting the police.

*Difficulty: medium.*

Discussion
----------

This is a slight variation on [House Robber I](../leet198), with the additional
constraint that solutions that rob the first house cannot rob the last, and
those that rob the last cannot rob the first.

One obvious thing to try is exactly what we did for *House Robber I*, but twice:
- Find the best haul if you consider every house but the first.
- Find the best haul if you consider every house but the last.
- Return the better of the two.

Whether you do it recursively or iteratively is, again, up to you.
