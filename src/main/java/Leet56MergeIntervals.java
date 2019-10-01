import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

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

    public static void main(String[] args) {
        System.out.println(Arrays.stream(new Leet56MergeIntervals().merge(
            new int[][] {
                new int[] {1,3},
                new int[] {4, 5},
                new int[] {6, 7},
                new int[] {8, 9},
                new int[] {15,18},
                new int[] {18,19}})).map(Arrays::toString).collect(Collectors.toList()));
    }
}
