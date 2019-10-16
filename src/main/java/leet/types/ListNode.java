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
        int[] cycle = indexOfFirstCycle();
        if (cycle == null) {
            return String.format("%d -> %s", this.val, this.next);
        } else {
            ListNode n = this;
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < cycle[0] + cycle[1]; i++) {
                s.append(n.val);
                s.append(" -> ");
                n = n.next;
            }
            s.append("(repeat previous ");
            s.append(cycle[1]);
            if (cycle[1] > 1) {
                s.append(" elements)");
            } else {
                s.append(" element)");
            }
            return s.toString();
        }
    }

    private int[] indexOfFirstCycle() {
        if (this.next == null) return null;
        ListNode tortoise = this;
        ListNode hare = this.next;
        int tortoiseSteps = 0;
        int hareSteps = 1;
        while (true) {
            if (tortoise == hare) {
                break;
            }
            tortoise = tortoise.next;
            tortoiseSteps++;
            if (hare.next == null || hare.next.next == null) return null;
            hare = hare.next.next;
            hareSteps += 2;
        }
        int cycleLength = 1;
        hare = tortoise.next;
        while (tortoise != hare) {
            hare = hare.next;
            cycleLength++;
        }
        ListNode a = this;
        ListNode b = this;
        for (int i = 0; i < cycleLength; i++) {
            b = b.next;
        }
        int loopStart = 0;
        while (a != b) {
            a = a.next;
            b = b.next;
            loopStart++;
        }
        return new int[] {loopStart, cycleLength};
    }
}
