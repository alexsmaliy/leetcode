Problem Statement
-----------------

There are a total of *n* courses you have to take, labeled from 0 to
*n*&thinsp;&minus;&thinsp;1. Some courses may have prerequisites, for example to
take course 0 you have to first take course 1, which is expressed as a pair:
`[0,1]`. Given the total number of courses and a list of prerequisite pairs, is
it possible for you to finish all courses? You may assume that there are no
duplicate edges in the input prerequisites.

*Difficulty: medium.*

Discussion
----------

This problem is on the harder end of medium. The task is to check whether a
cycle is present in a directed graph (in the context of the problem, a vicious
circle of course prerequisites). There are, of course, several well-known
algorithms to find cycles in graphs and more, such as the family of algorithms
to find all strongly connected components in a directed graph (see Tarjan's,
Korasaju's, etc.). But they are too complicated to reproduce from memory. And,
at any rate, our task is to find even one cycle, not all cycles.

I found this pretty simple to do using a modified version of DFS that *backs
off* when it finds a dead end. Intuitively, imagine walking through a maze with
a ball of yarn. At each fork, you take the left-most branch you haven't explored
yet. If you find a dead end, you *rewind the yarn* until you return to the most
recent fork that still has unexplored branches, and you again take the left-most
unexplored branch. If you cross over your own yarn, you know you've found a loop
in the maze.

We express this in code as a normal DFS with an array to keep track of which
nodes we have visited already, so we don't revisit them. We augment this DFS
with a stack of nodes that we add to as we explore the graph. Whenever we reach
a dead end (a graph node with no non-null children), we pop elements from this
stack and unmark visited nodes until we return to the most recently visited fork
that still has edges left to explore. We store edges in the stack of locations
to visit, so that we know to keep unwinding our path until we return to the edge
with an unvisited branch: if the DFS stack tells us that the next location to
visit is not the continuation of the current path, we know it is time to unwind.

There remain two complications: where to start the search and how to handle
the possibility that the graph contains multiple connected components (namely,
if not all nodes are reachable with a single search). We take care of both by
running searches from every node that has no incoming edges (in-degree 0) and
keeping track globally of every node visited. If, after DFSing from every node
of in-degree 0 and not finding cycles, there are still unvisited nodes in the
graph, we can conclude that there exists a connected component that forms a
vicious circle.
