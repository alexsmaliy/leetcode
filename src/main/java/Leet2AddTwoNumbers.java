import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Leet2AddTwoNumbers {
    public static void main(String[] args) {
        ListNode n1 = new ListNode(9);
        n1.next = new ListNode(9);
        n1.next.next = new ListNode(9);

        ListNode n2 =  new ListNode(9);
        n2.next = new ListNode(9);
        n2.next.next = new ListNode(9);

        System.out.println(new Solution().addTwoNumbers(n1, n2));
    }

    // Definition for singly-linked list.
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }

        @Override
        public String toString() {
            List<Integer> x =  new ArrayList<>();
            ListNode y = this;
            while (y != null) {
                x.add(y.val);
                y = y.next;
            }
            return x.stream().map(String::valueOf).collect(Collectors.joining("-"));
        }
    }

    static class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            if (l1 == null) return l2;
            if (l2 == null) return l1;
            int sum = l1.val + l2.val;
            int digit = sum % 10;
            int carry = sum / 10;
            ListNode answer = new ListNode(digit);
            ListNode pointer = answer;
            l1 = l1.next;
            l2 = l2.next;
            while (l1 != null || l2 != null) {
                int digit1 = 0;
                if (l1 != null) {
                    digit1 = l1.val;
                    l1 = l1.next;
                }
                int digit2 = 0;
                if (l2 != null) {
                    digit2 = l2.val;
                    l2 = l2.next;
                }
                sum = digit1 + digit2 + carry;
                digit = sum % 10;
                carry = sum / 10;
                answer.next = new ListNode(digit);
                answer = answer.next;
            }
            while (carry > 0) {
                digit = carry % 10;
                carry = carry / 10;
                answer.next =  new ListNode(digit);
                answer = answer.next;
            }
            return pointer;
        }
    }
}
