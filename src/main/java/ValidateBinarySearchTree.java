public class ValidateBinarySearchTree {
    //  Definition for a binary tree node.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode node1 = new TreeNode(1);
        root.left = node1;
        TreeNode node4 = new TreeNode(4);
        root.right = node4;
        TreeNode node3 = new TreeNode(3);
        root.right.left = node3;
        TreeNode node6 = new TreeNode(6);
        root.right.right = node6;
        System.out.println(new Solution().isValidBST(root));
    }

    static class Solution {
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
}
