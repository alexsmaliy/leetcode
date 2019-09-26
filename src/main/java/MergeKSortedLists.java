package lc;

import java.util.ArrayList;
import java.util.List;

public class MergeKSortedLists {
    public static void main(String[] args) {
        ListNode x = new ListNode(1);
        x.next = new ListNode(3);
        x.next.next = new ListNode(5);
        x.next.next.next = new ListNode(7);
        ListNode y = new ListNode(2);
        y.next = new ListNode(4);
        y.next.next = new ListNode(6);
        ListNode z = new ListNode(0);
        z.next = new ListNode(8);
        ListNode w = null;
        ListNode v = null;
        ListNode u = new ListNode(9);
        ListNode n = new Solution().mergeKLists(new ListNode[]{x, y, z, u, v, w});
        System.out.println(n);
    }

    // Definition for singly-linked list.
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            List<String> s = new ArrayList<>();
            ListNode tmp = this;
            while (tmp != null) {
                s.add(tmp.val + "");
                tmp = tmp.next;
            }
            return String.join(" -> ", s);
        }
    }

    static class Solution {
        public ListNode mergeKLists(ListNode[] lists) {
            int numLists = lists.length;
            if (numLists == 0) {
                return null;
            }
            MinHeap<ListNodeWithSize> heap = new MinHeap<>(numLists);
            for (int i = 0; i < numLists; i++) {
                heap.add(new ListNodeWithSize(lists[i]));
            }
            while (heap.size() > 1) {
                ListNodeWithSize list1 = heap.remove();
                ListNodeWithSize list2 = heap.remove();
                heap.add(merge(list1, list2));
            }
            return heap.remove().list;
        }

        static ListNodeWithSize merge(ListNodeWithSize list1, ListNodeWithSize list2) {
            int size1 = list1.size;
            int size2 = list2.size;
            if (size1 == 0 && size2 == 0) return new ListNodeWithSize(null);
            if (size1 == 0) return list2;
            if (size2 == 0) return list1;
            ListNode node1 = list1.list;
            ListNode node2 = list2.list;
            ListNode out;
            if (node1.val < node2.val) {
                out = node1;
                node1 = node1.next;
            } else {
                out = node2;
                node2 = node2.next;
            }
            ListNode ret = out;
            while (node1 != null || node2 != null) {
                if (node1 == null) {
                    out.next = node2;
                    out = out.next;
                    break;
                } else if (node2 == null) {
                    out.next = node1;
                    out = out.next;
                    break;
                } else if (node1.val < node2.val) {
                    out.next = node1;
                    out = out.next;
                    node1 = node1.next;
                } else {
                    out.next = node2;
                    out = out.next;
                    node2 = node2.next;
                }
            }
            out.next = null;
            return new ListNodeWithSize(ret, size1 + size2);
        }
    }

    static class ListNodeWithSize implements Comparable<ListNodeWithSize> {
        int size;
        ListNode list;

        ListNodeWithSize(ListNode list) {
            this.list = list;
            ListNode tmp = list;
            this.size = 0;
            while (tmp != null) {
                tmp = tmp.next;
                size++;
            }
        }

        ListNodeWithSize(ListNode list, int size) {
            this.list = list;
            this.size = size;
        }

        @Override
        public int compareTo(ListNodeWithSize other) {
            return other == null ? 1 : this.size - other.size;
        }
    }

    static class MinHeap<T extends Comparable<? super T>> {
        Object[] data;
        int cursor;

        MinHeap(int size) {
            this.cursor = 1;
            this.data = new Object[size + 1];
        }

        int size() {
            return cursor - 1;
        }

        T remove() {
            T ret = (T) data[1];
            cursor--;
            data[1] = data[cursor];
            data[cursor] = null;
            int index = 1;
            while (index <= size() / 2) {
                int smallerChild = data[2 * index + 1] != null && ((T) data[2 * index]).compareTo((T) data[2 * index + 1]) > 0
                    ? 2 * index + 1
                    : 2 * index;
                if (((T) data[index]).compareTo((T) data[smallerChild]) > 0) {
                    swap(index, smallerChild);
                    index = smallerChild;
                } else {
                    break;
                }
            }
            return ret;
        }

        void add(T element) {
            data[cursor] = element;
            int elem = cursor;
            int parent = cursor / 2;
            while (parent > 0) {
                if (((T) data[parent]).compareTo((T) data[elem]) > 0) {
                    swap(parent, elem);
                    elem = parent;
                    parent = parent / 2;
                } else {
                    break;
                }
            }
            cursor++;
        }

        void swap(int x, int y) {
            T tmp = (T) data[x];
            data[x] = data[y];
            data[y] = tmp;
        }
    }
}
