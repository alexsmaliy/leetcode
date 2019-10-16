package leet.problems.leet230;

import leet.types.TreeNode;

public class Leet230KthSmallestElementInBst {
    public static int kthSmallest(TreeNode root, int k) {
        TreeNode[] traceback = new TreeNode[1000];
        traceback[0] = root;
        int depth = min(root, traceback, 0);
        for (int i = 1; i < k; i++) {
            depth = next(traceback, depth);
        }
        return traceback[depth].val;
    }

    private static int min(TreeNode node, TreeNode[] traceback, int depth) {
        while (node.left != null) {
            node = node.left;
            depth++;
            traceback[depth] = node;
        }
        return depth;
    }

    private static int next(TreeNode[] traceback, int depth) {
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
