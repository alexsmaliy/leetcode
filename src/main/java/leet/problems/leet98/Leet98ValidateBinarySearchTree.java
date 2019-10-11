package leet.problems.leet98;

public class Leet98ValidateBinarySearchTree {
    // LeetCode's definition for a binary tree node.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

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
