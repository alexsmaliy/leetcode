package leet.problems.leet725;

import leet.types.ListNode;

import java.util.ArrayList;
import java.util.List;

public class Leet725SplitLinkedListInParts {
    public static ListNode[] splitListToParts(ListNode root, int k) {
        List<ListNode> nodes = new ArrayList<>();
        ListNode node = root;
        while (node != null) {
            nodes.add(node);
            node = node.next;
        }
        int numNodes = nodes.size();
        ListNode[] subsets = new ListNode[k];
        if (numNodes < k) {
            for (int subset = 0; subset < numNodes; subset++) {
                subsets[subset] = nodes.get(subset);
                subsets[subset].next = null;
            }
        } else {
            // Integer ceiling division.
            int subsetSize = (numNodes + k - 1) / k;
            // Example: 10 divided into four lists would be 3, 3, 2, 2.
            // Because 10 is two nodes short of 3 * 4, two of the lists are shorter.
            int numListsToTruncate = subsetSize * k - numNodes;
            int currentSubset = 0;
            int cursor = 0;
            // The lists of normal length.
            while (currentSubset < k - numListsToTruncate) {
                subsets[currentSubset] = nodes.get(cursor);
                cursor += subsetSize;
                nodes.get(cursor - 1).next = null;
                currentSubset++;
            }
            // The shortened lists.
            while (numListsToTruncate > 1) {
                subsets[currentSubset] = nodes.get(cursor);
                cursor += subsetSize - 1;
                nodes.get(cursor - 1).next = null;
                numListsToTruncate--;
                currentSubset++;
            }
            // The last list does not need to be explicitly terminated with null.
            // It is the end of the original list, so its last node is already
            // pointing to null.
            if (numListsToTruncate > 0) {
                subsets[currentSubset] = nodes.get(cursor);
            }
        }
        return subsets;
    }
}
