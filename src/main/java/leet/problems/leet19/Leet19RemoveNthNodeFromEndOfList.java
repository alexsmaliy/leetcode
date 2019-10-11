package leet.problems.leet19;

import leet.types.ListNode;

public class Leet19RemoveNthNodeFromEndOfList {
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
