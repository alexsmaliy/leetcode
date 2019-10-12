Problem Statement
-----------------

Given a reference of a node in a connected undirected graph, return a deep copy
(clone) of the graph. Each node in the graph contains a val (`int`) and a list
(`List[Node]`) of its neighbors.

Example:

<pre>
  1 ----- 2
  |       |
  |       |
  4 ----- 3
</pre>

Note:
  1. The number of nodes will be between 1 and 100.
  1. The undirected graph is a simple graph, which means no repeated edges and
      no self-loops in the graph.
  1. Since the graph is undirected, if node *p* has node *q* as neighbor, then
     node *q* must have node *p* as neighbor too.
  1. You must return the *copy of the given node* as a reference to the cloned
     graph.

*Difficulty: medium.*

Discussion
----------

This is another problem solved by exhaustively and uniquely visiting each node
in the graph using BFS or DFS. We must duplicate an undirected graph with simple
edges and no self-links. Graph nodes appear to be uniquely identified by their
values. If they weren't, we could come up with a compound key based on the
values of their immediate neighbors.

We start copying at the provided entry point, and ultimately return that copy as
the entry point to the cloned graph. Both BFS and DFS will traverse the graph
completely in linear time. All we need to do is avoid recursing into nodes that
we have already visited once: 
  1. we want the recursion to terminate, and
  1. we want to avoid creating multiple copies of a given node.
<pre>
      &#x2714;              &#x2718;
  1 ----- 2   1 ----- 2 ----- 3
  |       |   |               |
  |       |   |               |
  4 ----- 3   4               4
</pre>
We accomplish both goals by stuffing nodes into a lookup structure as we create
them. If the lookup structure already contains a copy of the node we're about
to create, we return it instead. The graph eventually runs out of unvisited
nodes, and all the recursive calls resolve.

Example: Consider a graph with two nodes `1 ----- 2`.
  - We enter at node 1.
  - We check if node 1 can be looked up. It cannot. We create node 1-copy
    without any neighbors and add it to the lookup.
  - We see that in the input graph, node 1 has node 2 as its neighbor. We
    recurse into node 2 (recursion depth 1).
  - Node 2 is not in the lookup. We create node 2-copy without any neighbors.
    We see that node 2 has node 1 as its neighbor. We recurse into node 1
    (recursion depth 2).
  - Node 1-copy is present in the lookup, so we return it and do not recurse any
    further (recursion depth 2 resolves).
  - Node 2-copy gets node 1-copy as its neighbor.
  - Recursion depth 1 resolves. We are back at node 1, the entry point. Node
    1-copy now has node 2-copy as neighbor. There is nothing left to copy.
  - We return node 1-copy as the entry point into the cloned graph.
