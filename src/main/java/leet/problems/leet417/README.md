Problem Statement
-----------------

Given an *m*&thinsp;&times;&thinsp;*n* matrix of non-negative integers
representing the height of each unit cell in a continent, the "Pacific ocean"
touches the left and top edges of the matrix and the "Atlantic ocean" touches
the right and bottom edges.

Water can only flow in four directions (up, down, left, or right) from a cell to
another one with height equal or lower.

Find the list of grid coordinates where water can flow to both the Pacific and
Atlantic ocean.

Note:
1. The order of returned grid coordinates does not matter.
1. Both *m* and *n* are less than 150.

Example:

Given the following 5&thinsp;&times;&thinsp;5 matrix:
<pre><tt>  Pacific ~   ~   ~   ~   ~ 
       ~  1   2   2   3  (5) *
       ~  3   2   3  (4) (4) *
       ~  2   4  (5)  3   1  *
       ~ (6) (7)  1   4   5  *
       ~ (5)  1   1   2   4  *
          *   *   *   *   * Atlantic</tt></pre>

Return the positions with parentheses in the above matrix:
<pre><tt>[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]]</tt></pre>


*Difficulty: medium.*

Discussion
----------

We are looking for the "continental divide" between the part of the grid that
drains into the "Pacific" and the part that drains into the "Atlantic." Note
that there is also a third state: cells that drain into
neither&thinsp;&mdash;&thinsp;an "inland sea" sort of situation.
<pre><tt>  Pacific ~   ~   ~   ~   ~ 
       ~  1   1   1   1  (2) *
       ~  1   3   3  (4)  1  *
       ~  1   3   1   3   1  *
       ~  1  (4)  3   3   1  *
       ~ (2)  1   1   1   1  *
          *   *   *   *   * Atlantic</tt></pre>
In the example above, the cell at position (2, 2) does not drain to either edge.

BFS and DFS certainly look like plausible ways to solve this problem.

Iterating over every point in the grid and checking whether it can drain into
both oceans sounds incredibly wasteful. We should definitely memoize partial
solutions to avoid redundant work.

We can try DFS and keep track of points we have already visited, so that we can
skip them as we iterate over the grid. The insight is that if you are higher up
than your neighbor, and your neighbor drains into one of the two oceans, ipso
facto so do you. In fact, you drain into the union of all the oceans your
immediate lower-altitude neighbors drain into.

This is more tricky than it seems. We want to avoid getting trapped in a cycle
of bouncing between two adjacent cells of equal value, but each neighbor must
know the other one's drainage before knowing its own. Consider:
<pre><tt>   P A C I F I C
  ┌─────┐   ┌─────┐
P │ 1   │   │ 1   │ A
A │     │ ▶ │     │ T
C └─────┘   └─────┘ L
I    ▲         ▼    A
F ┌─────┐   ┌─────┐ N
I │ 1   │ ✘ │ 1   │ T
C │     │ ◀ │     │ I
  └─────┘   └─────┘ C
   A T L A N T I C</tt></pre>
Suppose we proceed depth-first starting at (1, 0). We mark it as visited to
avoid looping back to it. Proceeding from (1, 0), we eventually reach (1, 1).
But (1, 1) would need to know the value that comes back to (1, 0) to know for
sure itself which oceans it drains into.

We can break the circular dependency if we do two passes, one per ocean. I find
BFS easier to think about in this scenario, so that's what I chose for the task.

We pick one of the oceans and seed the BFS queue with the cells that form its
beaches. We draw candidate cells from the head of queue and mark them as
draining into the chosen ocean. We then feed the queue with all the neighbors of
equal or greater altitude. In normal BFS fashion, we iterate until the queue is
empty. We then repeat the process with the other ocean.

<pre><tt>
  Pacific ~   ~   ~   ~   ~           Pacific ~   ~   ~   ~   ~    
                        ┌───┐               ╔═══════════════════╗  
       ~  1   1   1   1 │ 2 │ *            ~║ 1   1   1   1 │ ✔ ║ *
       ~            ┌───┘   │              ~║           ┌───╔═══╝  
       ~  1   3   3 │ 4   1 │ *            ~║ 1   3   3 │ ✔ ║ 1 │ *
       ~            │       │              ~║       ╔═══════╝   │  
       ~  1   3   1 │ 3   1 │ *            ~║ 1   3 ║ 1 │ 3   1 │ *
       ~    ┌───────┘       │              ~║   ┌───║───┘       │  
       ~  1 │ 4   3   3   1 │ *            ~║ 1 │ ✔ ║ 3   3   1 │ *
       ~┌───┘               │              ~║───╔═══╝           │  
       ~│ 2   1   1   1   1 │ *            ~║ ✔ ║ 1   1   1   1 │ *
        └───────────────────┘               ╚═══╝───────────────┘  
          *   *   *   *   * Atlantic          *   *   *   *   * Atlantic</tt></pre>

Unless we do something, any given cell will be submitted to the queue up to four
times (by its neighbors). If we're keeping track of which cells we have visited
using some kind of array or list or whatever, we could introduce a third state:
"unvisited," "visited," and "waiting to be visited." Then, we have the option of
feeding the queue only with neighbors that haven't been fed into the queue
earlier. However, that does add an extra `if` to the queue-feeding code. We'd
also need to be careful to update the status in the tracking list/array/whatever
when we do finally process the cell.

There is a bit of a savings to be had if you use the same data structure to keep
track of visited cells for the second pass, because then we can collect the
cells we need to return as part of the answer as we find them. The alternative
would be to keep two trackers and do an additional pass over them to identify
the cells they have in common.

To use a single tracker, we'd like to be able to associate each cell with one or
more statuses that are not mutually exclusive. For example, a cell might be
"visited," "Atlantic," and "Pacific" all at once. We could devise something like
an enum for all combinations we can expect to encounter, but bit masks seem like
a simpler alternative. Anything along the following lines should work:
- If the cell is 0, it is unvisited.
- If the first bit is 1, the cell is being explored.
- If the second bit is 1, the cell drains into the Atlantic.
- If the third bit is 1, the cell drains into the Pacific.

Yes, you have to mess around with bit shifts, but you can now set a cell to
`ATLANTIC | PACIFIC` simultaneously as a benefit. Make sure you pick a numeric
type that has enough bits for your bit-encoding scheme.
