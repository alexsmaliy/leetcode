package leet.types;

/**
 * This is the minimal singly linked list that many
 * LeetCode problems use. All access is public to
 * keep the solution similar in appearance to what
 * might be entered into LeetCode. There are some
 * usability additions, like a toString() to help
 * with testing, that do not affect functionality.
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return String.format("%d -> %s", this.val, this.next);
    }
}
