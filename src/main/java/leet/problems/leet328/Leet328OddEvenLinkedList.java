package leet.problems.leet328;

import leet.types.ListNode;

public class Leet328OddEvenLinkedList {
    public static ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;
        ListNode node = even.next;
        boolean appendToOdd = true;
        while (node.next != null) {
            if (appendToOdd) {
                appendToOdd = false;
                odd.next = node;
                odd = node;
                node = node.next;
            } else {
                appendToOdd = true;
                even.next = node;
                even = node;
                node = node.next;
            }
        }
        if (appendToOdd) {
            odd.next = node;
            odd.next.next = evenHead;
            even.next = null;
        } else {
            odd.next = evenHead;
            even.next = node;
        }
        return head;
    }
}
