package leet.problems.leet104;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Leet104MaximumDepthOfBinaryTree {
    // LeetCode's definition for a binary tree node.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    // A sensible solution: Traverse the tree depth-first.
    // At each branch, recursively pick the child with the
    // deepest subtree.
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return maxDepth(root, 1);
    }

    private int maxDepth(TreeNode node, int depth) {
        int ret = depth;
        if (node.left != null) {
            ret = maxDepth(node.left, depth + 1);
        }
        if (node.right != null) {
            ret = Math.max(ret, maxDepth(node.right, depth + 1));
        }
        return ret;
    }

    // A less sensible solution using a method that is useful
    // elsewhere. Traverse the tree breadth-first, until we
    // reach a depth that contains no nodes.
    public int maxDepth2(TreeNode root) {
        if (root == null) return 0;
        int depth = 0;
        List<TreeNode> items = new ArrayList<>();
        items.add(root);
        while (!items.isEmpty()) {
            depth++;
            items = items.stream()
                .flatMap(item -> Stream.of(item.left, item.right))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        }
        return depth;
    }
}
