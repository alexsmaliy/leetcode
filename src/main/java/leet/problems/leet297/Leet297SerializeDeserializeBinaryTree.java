package leet.problems.leet297;

import leet.types.TreeNode;

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

        private static final char NULL_NODE = '.';
        private static final int[] POWERS_OF_10 = new int[] {
            1, 10, 100, 1_000, 10_000, 100_000, 1_000_000,
            10_000_000, 100_000_000, 1_000_000_000,
        };

        /**
         * Serialize to string. Chose {@link StringBuilder} instead of
         * {@link String#format(String, Object...)}, because the former
         * seems dramatically faster, at least on LeetCode with Java 8.
         */
        public String serialize(TreeNode root) {
            if (root == null) return ".";
            return serializeHelper(root).toString();
        }

        private StringBuilder serializeHelper(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            sb.append(root.val);
            sb.append(' ');
            sb.append(root.left == null ? NULL_NODE : serialize(root.left));
            sb.append(' ');
            sb.append(root.right == null ? NULL_NODE : serialize(root.right));
            return sb;
        }


        /**
         * Deserialize from string recursively. Default max call stack depth
         * on modern JVMs is at least 2000, so this feels relatively safe.
         */
        public TreeNode deserialize(String data) {
            if (data.charAt(0) == NULL_NODE) return null;
            Cursor c = new Cursor();
            return deserializeHelper(data, c, new TreeNode(0));
        }

        // Surprisingly simple recursive parser.
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

        /**
         * Targeting Java 8, so can't use the convenient
         * {@link Integer#parseInt(CharSequence, int, int, int)}.
         * The alternative would be parseInt() with a substring,
         * which should be as cheap as this.
         */
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

    // Because there are no pointers to int in Java.
    private static final class Cursor {
        private int cursor;
    }
}
