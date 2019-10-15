Problem Statement
-----------------

Given a 2d grid map of '1's (land) and '0's (water), count the number of
islands. An island is surrounded by water and is formed by connecting adjacent 
lands horizontally or vertically. You may assume all four edges of the grid are
all surrounded by water.

*Difficulty: medium.*

Discussion
----------

Man, why did I have so much trouble with this one? A simple and pretty decent
solution comes to mind immediately: iterate over the grid and flood every island
you reach, while keeping count of how many you have flooded. You can flood an
island via BFS/DFS and setting every coordinate in it to "water."

But boy, howdy, how I got stuck on this one. My first attempt was probably
the closest to success, but I got stuck in the morass of if/thens that tried to
handle every edge and corner position on the map. It's a lot easier just to
discard invalid coordinates in one statement! Trying to generate only valid
coordinates to feed into the search queue is way too fiddly, and I should
remember to stop trying that for problems involving 2D grids. Discard invalid
elements when consuming from the queue, instead of writing error-prone code that
only generates valid elements to add to the queue.

At any rate, LeetCode failed that attempt for consuming too much memory,
somehow.

I next thought, "What if I only need to check a given row and compare it with
the preceding one to see if new islands have been introduced to the map?" I'm
sure that approach has interesting features (it reminds me of linear cellular
automata), but the code quickly fell apart trying to deal with look-behinds and
look-aheads.

The next thought was, "What if I save time by only flooding the boundary of each
island?" That substantially complicated the detection of the edge of new,
untouched islands, but at least the search would not have to flood cells in an
island's interior. That approach&hellip; worked on the small test cases, but
failed on a large one that was impractical to debug.

So yeah. Iterate over the grid, point by point. If you find a '1', do BFS/DFS to
flood every other '1' reachable from that point. If you find yourself writing
tons of fiddly code, consider whether filtering a dirty stream could be easier
than creating a perfectly clean stream of search candidates.

Runtime for a grid of *n* points: *n* operations for the iteration, and a
constant multiple of *n* for searching. Every neighbor a point has will try to
submit it to the queue, and a point can have up to four "land" neighbors in this
problem.
