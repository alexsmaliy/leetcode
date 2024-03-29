package leet.problems.leet23;

import leet.types.ListNode;

public class Leet23MergeKSortedLists {
    public static ListNode mergeKLists(ListNode[] lists) {
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

    private static ListNodeWithSize merge(ListNodeWithSize list1, ListNodeWithSize list2) {
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

    private static class ListNodeWithSize implements Comparable<ListNodeWithSize> {
        private final int size;
        private final ListNode list;

        private ListNodeWithSize(ListNode list) {
            this.list = list;
            ListNode tmp = list;
            int size = 0;
            while (tmp != null) {
                tmp = tmp.next;
                size++;
            }
            this.size = size;
        }

        private ListNodeWithSize(ListNode list, int size) {
            this.list = list;
            this.size = size;
        }

        @Override
        public int compareTo(ListNodeWithSize other) {
            return other == null ? 1 : this.size - other.size;
        }
    }

    private static final class MinHeap<T extends Comparable<? super T>> {
        private final Object[] data;
        private int cursor;

        public MinHeap(int size) {
            this.cursor = 1;
            this.data = new Object[size + 1];
        }

        public int size() {
            return cursor - 1;
        }

        public T remove() {
            T ret = (T) data[1];
            cursor--;
            data[1] = data[cursor];
            data[cursor] = null;
            int index = 1;
            while (index <= size() / 2) {
                boolean rightChildExists = data[2 * index + 1] != null;
                T leftChild = (T) data[2 * index];
                T rightChild = (T) data[2 * index + 1];
                int smallerChild = rightChildExists && leftChild.compareTo(rightChild) > 0
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

        public void add(T element) {
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

        private void swap(int x, int y) {
            T tmp = (T) data[x];
            data[x] = data[y];
            data[y] = tmp;
        }
    }
}
