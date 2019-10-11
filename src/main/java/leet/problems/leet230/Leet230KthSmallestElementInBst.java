package leet.problems.leet230;

public class Leet230KthSmallestElementInBst {
    //  Definition for a binary tree node.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public int kthSmallest(TreeNode root, int k) {
        TreeNode[] traceback = new TreeNode[1000];
        traceback[0] = root;
        int depth = min(root, traceback, 0);
        for (int i = 1; i < k; i++) {
            depth = next(traceback, depth);
        }
        return traceback[depth].val;
    }

    public static int min(TreeNode node, TreeNode[] traceback, int depth) {
        while (node.left != null) {
            node = node.left;
            depth++;
            traceback[depth] = node;
        }
        return depth;
    }

    public static int next(TreeNode[] traceback, int depth) {
        TreeNode node = traceback[depth];
        if (node.right == null) {
            while (traceback[depth - 1].right == traceback[depth]) {
                depth--;
            }
            return depth - 1;
        } else {
            depth++;
            traceback[depth] = node.right;
            return min(traceback[depth], traceback, depth);
        }
    }
}
