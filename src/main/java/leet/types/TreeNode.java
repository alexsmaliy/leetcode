package leet.types;

import java.util.Objects;

/**
 * This is the minimal binary tree implementation that
 * many LeetCode problems use. There are some usability
 * additions, like a toString(), to help with testing.
 * All field access is public to keep solutions looking
 * similar to what might be entered into LeetCode.
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return String.format("%d [%s, %s]",
            this.val, this.left, this.right);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof TreeNode)) return false;
        TreeNode o2 = (TreeNode) o;
        return o2.val == this.val
            && Objects.equals(this.left, o2.left)
            && Objects.equals(this.right, o2.right);
    }
}
