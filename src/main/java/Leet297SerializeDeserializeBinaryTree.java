import java.util.Objects;

public class Leet297SerializeDeserializeBinaryTree {
    /**
       <p>Produces a string representing a root->left->right traversal of the tree:</p>
       <pre>
               1
              / \
             2   3
                /   ===> "1 2 . . 3 4 5 . . 6 . . ."
               4
              / \
             5   6
       </pre>
       <p>The deserialization is then as simple as:</p>
       <ul>
         <li>
            If the cursor points to {@code '.'}, return null and advance the cursor.
         </li>
         <li>
            Otherwise, parse a number and two children, return the resulting node,
            and advance the cursor.
         </li>
       </ul>
     */
    public static class Codec {

        private static final String SERIALIZATION_FORMAT = "%d %s %s";
        private static final char NULL_NODE = '.';
        private static final int[] POWERS_OF_10 = new int[] {
            1, 10, 100, 1_000, 10_000, 100_000, 1_000_000,
            10_000_000, 100_000_000, 1_000_000_000,
        };

        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) return ".";
            return String.format(SERIALIZATION_FORMAT,
                root.val,
                serialize(root.left),
                serialize(root.right));
        }


        public TreeNode deserialize(String data) {
            if (data.charAt(0) == NULL_NODE) return null;
            Cursor c = new Cursor();
            return deserializeHelper(data, c, new TreeNode(0));
        }

        private static TreeNode deserializeHelper(String data, Cursor c, TreeNode head) {
            if (data.charAt(c.cursor) == NULL_NODE) {
                c.cursor += 2;
                return null;
            }
            int val = readPrimitiveValue(data, c);
            TreeNode left =
                deserializeHelper(data, c, new TreeNode(0));
            TreeNode right =
                deserializeHelper(data, c, new TreeNode(0));
            head.val = val;
            head.left = left;
            head.right = right;
            return head;
        }

        private static int readPrimitiveValue(String data, Cursor c) {
            int oldCursor = c.cursor;
            while (data.charAt(c.cursor) != ' ') {
                c.cursor++;
            }
            boolean isNegative = false;
            if (data.charAt(oldCursor) == '-') {
                isNegative = true;
            }
            int numFigs = c.cursor - oldCursor;
            if (isNegative) numFigs--;
            int ret = 0;
            for (int i = 0; i < numFigs; i++) {
                ret += (data.charAt(c.cursor - i - 1) - '0') * POWERS_OF_10[i];
            }
            c.cursor++;
            return isNegative ? -ret : ret;
        }
    }

    private static final class Cursor {
        private int cursor;
    }

    // LeetCode's TreeNode definition, with usability additions.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }

        @Override
        public String toString() {
            return String.format(Codec.SERIALIZATION_FORMAT,
                this.val,
                left == null ? Codec.NULL_NODE : left.toString(),
                right == null ? Codec.NULL_NODE : right.toString());
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
}
