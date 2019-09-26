public class KthSmallestElementInBst {
    //  Definition for a binary tree node.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        node2.left = node1;
        node3.left = node2;
        node3.right = node4;
        node5.left = node3;
        node5.right = node6;
        TreeNode root = node5;
        Solution s = new Solution();
        System.out.println(String.format("first: %d\nsecond: %d\nthird: %d\nfourth: %d\nfifth: %d\nsixth: %d\n",
            s.kthSmallest(root, 1),
            s.kthSmallest(root, 2),
            s.kthSmallest(root, 3),
            s.kthSmallest(root, 4),
            s.kthSmallest(root, 5),
            s.kthSmallest(root, 6)));
    }

    static class Solution {
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
}
