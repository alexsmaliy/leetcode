package leet.leet226;

import java.util.ArrayDeque;
import java.util.Queue;

public class Leet226InvertBinaryTree {
    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    class Solution {
        public TreeNode invertTree(TreeNode root) {
            if (root == null || (root.left == null && root.right == null)) {
                return root;
            }
            Queue<TreeNode> q = new ArrayDeque<>();
            q.add(root);
            while (!q.isEmpty()) {
                TreeNode node = q.poll();
                TreeNode temp = node.left;
                node.left = node.right;
                node.right = temp;
                if (node.left != null) q.add(node.left);
                if (node.right != null) q.add(node.right);
            }
            return root;
        }
    }
}
