package lc;

import java.util.ArrayList;
import java.util.List;

public class ReverseLinkedList {
    // Definition for singly-linked list.
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
        @Override
        public String toString() {
            List<String> x = new ArrayList<>();
            ListNode me = this;
            while (me != null) {
                x.add(me.val + "");
                me = me.next;
            }
            return String.join(" -> ", x);
        }
    }

    public static void main(String[] args) {
        ListNode aList = new ListNode(1);
        ListNode head = aList;
        for (int i = 2; i < 5; i++) {
            head.next = new ListNode(i);
            head = head.next;
        }
        System.out.println(new Solution().reverseListTailRec(aList));
    }

    static class Solution {
        public ListNode reverseList(ListNode head) {
            ListNode toCopy = head;
            ListNode acc = null;
            while (toCopy != null) {
                ListNode copy = new ListNode(toCopy.val);
                copy.next = acc;
                acc = copy;
                toCopy = toCopy.next;
            }
            return acc;
        }

        public ListNode reverseListRec(ListNode head) {
            if (head.next == null) {
                return head;
            }
            ListNode rest = reverseList(head.next);
            ListNode tmp = rest;
            while (tmp.next != null) tmp = tmp.next;
            tmp.next = new ListNode(head.val);
            return rest;
        }

        public ListNode reverseListTailRec(ListNode head) {
            return reverseListTailRec(head, null);
        }

        public ListNode reverseListTailRec(ListNode head, ListNode acc) {
            if (head == null) return acc;
            ListNode copy = new ListNode(head.val);
            copy.next = acc;
            acc = copy;
            return reverseListTailRec(head.next, acc);
        }
    }
}
