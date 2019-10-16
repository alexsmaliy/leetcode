package leet.problems.leet206;

import leet.types.ListNode;

public class Leet206ReverseLinkedList {
    // The iterative process to reverse a singly linked list.
    // This is written as an in-place reversal, but doesn't
    // have to be. We iterate down the list with an accumulator.
    // At each step, we append the accumulator to the current
    // node, then set the accumulator to the result of that.
    public static ListNode reverseList(ListNode head) {
        ListNode acc = null;
        while (head != null) {
            ListNode copy = head;
            head = head.next;
            copy.next = acc;
            acc = copy;
        }
        return acc;
    }

    // The non-tail-recursive way to reverse a linked list.
    // We recursively reverse the tail of the list, then
    // append the head to the end of the result. The repeated
    // traversal is wasteful.
    public static ListNode reverseListRec(ListNode head) {
        if (head.next == null) {
            return head;
        }
        ListNode rest = reverseList(head.next);
        ListNode tmp = rest;
        while (tmp.next != null) tmp = tmp.next;
        tmp.next = new ListNode(head.val);
        return rest;
    }

    // The tail-recursive way to reverse a linked list.
    // Start with an empty accumulator. At each iteration,
    // take the current head node of the input list and
    // append the accumulator to it, then set the
    // accumulator to the result of that operation.
    // Finally, recurse into the rest of the list until
    // the end is reached. This is not an in-place reversal,
    // but certainly can be rewritten as one.
    public static ListNode reverseListTailRec(ListNode head) {
        return reverseListTailRec(head, null);
    }

    public static ListNode reverseListTailRec(ListNode head, ListNode acc) {
        if (head == null) return acc;
        ListNode copy = new ListNode(head.val);
        copy.next = acc;
        acc = copy;
        return reverseListTailRec(head.next, acc);
    }
}
