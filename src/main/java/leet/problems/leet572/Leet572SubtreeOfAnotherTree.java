package leet.problems.leet572;

import leet.types.TreeNode;

public class Leet572SubtreeOfAnotherTree {
    public static boolean isSubtree(TreeNode s, TreeNode t) {
        if (t == null) return true;
        return findHeadOfT(s, t);
    }

    private static boolean findHeadOfT(TreeNode s, TreeNode t) {
        if (s == null) {
            return false;
        }
        if (s.val == t.val) {
            boolean leftSubtreesEqual = equal(s.left, t.left);
            boolean rightSubtreesEqual = equal(s.right, t.right);
            boolean sameTrees = leftSubtreesEqual && rightSubtreesEqual;

            boolean keepLookingLeftForMoreT = findHeadOfT(s.left, t);
            boolean keepLookingRightForMoreT = findHeadOfT(s.right, t);
            boolean keepLookingForT = keepLookingLeftForMoreT || keepLookingRightForMoreT;

            return sameTrees || keepLookingForT;
        }
        return findHeadOfT(s.left, t) || findHeadOfT(s.right, t);
    }

    private static boolean equal(TreeNode s, TreeNode t) {
        if (s == null) return t == null;
        if (t == null) return false;
        if (s.val != t.val) return false;

        boolean leftSubtreesEqual =
            (s.left == null && t.left == null) || equal(s.left, t.left);
        boolean rightSubtreesEqual =
            (s.right == null && t.right == null) || equal(s.right, t.right);

        return leftSubtreesEqual && rightSubtreesEqual;
    }
}
