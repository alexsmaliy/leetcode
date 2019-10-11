package leet.problems.leet19;

public class Leet19RemoveNthNodeFromEndOfList {
    // LeetCode's definition for singly-linked list, with quality of life additions.
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }

        @Override
        public String toString() {
            return String.format("%d -> %s", this.val, this.next);
        }
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        int indexFromEnd = indexFromEnd(head, n);
        if (indexFromEnd == n) {
            head = head.next;
        }
        return head;
    }

    private static int indexFromEnd(ListNode node, int n) {
        if (node.next == null) return 1;
        int indexOfChild = indexFromEnd(node.next, n);
        if (indexOfChild == n) {
            node.next = node.next.next;
        }
        return 1 + indexOfChild;
    }
}
