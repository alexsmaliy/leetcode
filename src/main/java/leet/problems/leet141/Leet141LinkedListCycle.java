package leet.problems.leet141;

import leet.types.ListNode;

public class Leet141LinkedListCycle {
    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        ListNode tortoise = head;
        ListNode hare = head.next;
        while (true) {
            if (tortoise == hare) return true;
            tortoise = tortoise.next;
            if (hare.next == null || hare.next.next == null) return false;
            hare = hare.next.next;
        }
    }
}
