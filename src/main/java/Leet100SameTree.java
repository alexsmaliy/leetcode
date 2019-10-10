import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;

public class Leet100SameTree {
    // Definition for a binary tree node.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public boolean isSameTreeRecursive(TreeNode p, TreeNode q) {
        if (p == null || q == null) {
            return Objects.equals(p, q);
        }
        return p.val == q.val
            && isSameTreeRecursive(p.left, q.left)
            && isSameTreeRecursive(p.right, q.right);
    }

    public boolean isSameTreeBfs(TreeNode p, TreeNode q) {
        // ArrayDeque does not allow null elements,
        // so let's have a sentinel that we compare
        // using reference equality.
        TreeNode SENTINEL = new TreeNode(0);
        if (p == null || q == null) {
            return Objects.equals(p, q);
        }
        Queue<TreeNode> q1 = new ArrayDeque<>();
        q1.add(p);
        Queue<TreeNode> q2 = new ArrayDeque<>();
        q2.add(q);
        while (!q1.isEmpty()) {
            TreeNode q1n = q1.poll();
            TreeNode q2n = q2.poll();
            if (q1n == SENTINEL && q2n == SENTINEL) continue;
            if (q1n == SENTINEL || q2n == SENTINEL) return false;
            if (q1n.val != q2n.val) return false;
            q1.add(q1n.left == null ? SENTINEL : q1n.left);
            q1.add(q1n.right == null ? SENTINEL : q1n.right);
            q2.add(q2n.left == null ? SENTINEL : q2n.left);
            q2.add(q2n.right == null ? SENTINEL : q2n.right);
        }
        return q2.isEmpty();
    }
}
