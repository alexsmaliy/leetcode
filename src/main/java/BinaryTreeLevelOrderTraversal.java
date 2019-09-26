import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BinaryTreeLevelOrderTraversal {
    //  Definition for a binary tree node.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode left = new TreeNode(9);
        root.left = left;
        TreeNode right = new TreeNode(20);
        root.right = right;
        TreeNode node15 = new TreeNode(15);
        root.right.left = node15;
        TreeNode node7 = new TreeNode(7);
        root.right.right = node7;
        System.out.println(new Solution().levelOrder(root));
    }

    static class Solution {
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> levels = new ArrayList<>();
            if (root == null) return levels;
            Queue<TreeNode> queue = new ArrayDeque<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                Queue<TreeNode> queue2 = new ArrayDeque<>();
                List<Integer> level =  new LinkedList<>();
                while (!queue.isEmpty()) {
                    TreeNode node = queue.poll();
                    level.add(node.val);
                    if (node.left != null) {
                        queue2.add(node.left);
                    }
                    if (node.right != null) {
                        queue2.add(node.right);
                    }
                }
                levels.add(level);
                queue = queue2;
            }
            return levels;
        }
    }

    static class Solution1 {
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> levels = new ArrayList<>();
            if (root == null) return levels;
            List<TreeNode> level = new ArrayList<>();
            level.add(root);
            while (!level.isEmpty()) {
                levels.add(
                    level.stream()
                        .mapToInt(x -> x.val)
                        .boxed()
                        .collect(Collectors.toList()));
                level = level.stream()
                    .flatMap(x -> Stream.of(x.left, x.right))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            }
            return levels;
        }
    }
}
