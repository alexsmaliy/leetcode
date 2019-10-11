package leet.types;

import java.util.List;

/**
 * This is LeetCode's minimal implementation of a graph. Problems
 * will specify whether edges are directed, or whether their
 * multiplicity can be greater than zero.
 */
public class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {}

    public Node(int _val, List<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
