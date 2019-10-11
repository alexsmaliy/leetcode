package leet.problems.leet98;

import leet.types.TreeNode;

public class Leet98ValidateBinarySearchTree {
    public boolean isValidBST(TreeNode root) {
        return isValidBSTRange(root, Integer.MIN_VALUE - 1L, Integer.MAX_VALUE + 1L);
    }

    private static boolean isValidBSTRange(TreeNode node, long min, long max) {
        if (node == null) return true;
        int val = node.val;

        boolean iAmValid = val > min && val < max;
        if (!iAmValid) return false;

        if (node.left != null) {
            boolean leftChildValid = isValidBSTRange(node.left, min, val);
            if (!leftChildValid) return false;
        }

        if (node.right != null) {
            return isValidBSTRange(node.right, val, max);
        }

        return true;
    }
}
