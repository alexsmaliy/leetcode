Problem Statement
-----------------

You are climbing a stair case. It takes *n* steps to reach to the top. Each time
you can either climb 1 or 2 steps. In how many distinct ways can you climb to
the top? Note: Given *n* will be a positive integer.

*Difficulty: easy.*

Discussion
----------

The problem reduces to finding the *n*th Fibonacci number, but it takes a minute
to understand why. The formulation certainly makes one think of dynamic
programming and recurrences.

Consider *n* stairs. Every way to climb *n* either start with 1 and climbs the
remaining *n* - 1, or it starts with 2 and climbs the remaining *n* - 2. In
other words, ways(*n*) = ways(*n* - 1) + ways(*n* - 2).

There is one way to climb zero stairs. There is one way to climb a one-stair
staircase. There are two ways to climb a two-stair staircase (climb two at once
or one at a time twice). There are Fib(*n*) ways to climb *n* stairs.
