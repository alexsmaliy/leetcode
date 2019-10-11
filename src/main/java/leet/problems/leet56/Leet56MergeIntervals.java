package leet.problems.leet56;

import java.util.Arrays;
import java.util.Comparator;

// The simplest solution: sort by start value and do a linear sweep.
// Intervals are compressed in place, and a truncated copy of the input is returned.
public class Leet56MergeIntervals {
    private static final Comparator<int[]> C = new Comparator<int[]>() {
        @Override
        public int compare(int[] o1, int[] o2) {
            return o1[0] - o2[0];
        }
    };

    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return new int[][] {};
        if (intervals.length == 1) return intervals;
        Arrays.sort(intervals, C);
        int currentBag = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= intervals[currentBag][1]) {
                intervals[currentBag][0] = Math.min(intervals[i][0], intervals[currentBag][0]);
                intervals[currentBag][1] = Math.max(intervals[i][1], intervals[currentBag][1]);
            } else {
                currentBag++;
                if (i != currentBag) {
                    intervals[currentBag] = intervals[i];
                }
            }
        }
        return Arrays.copyOf(intervals, currentBag + 1);
    }
}
