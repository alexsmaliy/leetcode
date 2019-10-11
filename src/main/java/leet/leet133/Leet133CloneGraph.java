package leet.leet133;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * We must duplicate an undirected graph with simple edges and no
 * self-links. In this problem, graph nodes are uniquely identified
 * by their values. If they were not, we could use some kind of
 * compound key of their value and their neighbors' values. We start
 * copying at the provided entry point. Both BFS and DFS will
 * traverse the graph completely in linear time, but we must be
 * careful to avoid recursing into nodes we have already visited:
 * (a) we want the recursion to terminate, and (b) we want to avoid
 * creating duplicate nodes. Each recursive call, when it completes,
 * fills in the list of a given node's neighbors. The depth of
 * recursion is up to the number of nodes in the graph.
 *
 *   1 ----- 2  By the time we get to node 4, its neighbor should
 *   |       |  be the same node 1 we started with, not a duplicate
 *   |       |  node with value 1.
 *   4 ----- 3
 */
public class Leet133CloneGraph {

    // LeetCode's definition for a Node.
    static class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {}

        public Node(int _val,List<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    public Node cloneGraph(Node node) {
        Map<Integer, Node> created = new HashMap<>();
        return dfs(node, created);
    }

    private Node dfs(Node node, Map<Integer, Node> created) {
        Node nCopy = created.compute(node.val, MAPPER);
        nCopy.neighbors = new ArrayList<>();
        for (int i = 0; i < node.neighbors.size(); i++) {
            Node ithNeighbor = node.neighbors.get(i);
            if (created.containsKey(ithNeighbor.val)) {
                nCopy.neighbors.add(created.get(ithNeighbor.val));
            }
            else {
                nCopy.neighbors.add(dfs(ithNeighbor, created));
            }
        }
        return nCopy;
    }

    // LeetCode's JVM must be pretty bad at optimizing code. Without
    // extracting this lambda into a static variable, the solution
    // performs pretty dismally on LC.
    private static final BiFunction<Integer, Node, Node> MAPPER = (num, node) -> {
        if (node == null) {
            return new Node(num, new ArrayList<>());
        } else {
            return node;
        }
    };
}
