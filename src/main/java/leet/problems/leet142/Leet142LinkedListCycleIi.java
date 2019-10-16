package leet.problems.leet142;

import leet.types.ListNode;

public class Leet142LinkedListCycleIi {
    public static ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode tortoise = head;
        ListNode hare = head.next;

        while (true) {
            if (tortoise == hare) {
                break;
            }
            tortoise = tortoise.next;
            if (hare.next == null || hare.next.next == null) return null;
            hare = hare.next.next;
        }

        hare = tortoise.next;
        int cycleLength = 1;
        while (tortoise != hare) {
            cycleLength++;
            hare = hare.next;
        }

        tortoise = head;
        hare = head;
        for (int i = 0; i < cycleLength; i++) {
            hare = hare.next;
        }
        while (tortoise != hare) {
            tortoise = tortoise.next;
            hare = hare.next;
        }

        return tortoise;
    }
}
