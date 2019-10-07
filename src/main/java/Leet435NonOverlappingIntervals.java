import java.util.Arrays;
import java.util.Comparator;

public class Leet435NonOverlappingIntervals {

    private static final Comparator<int[]> C = new Comparator<int[]>() {
        @Override
        public int compare(int[] a, int[] b) {
            return a[1] - b[1];
        }
    };

    public static int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length < 2) return 0;
        Arrays.sort(intervals, C);
        int previousGood = 0;
        int removals = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[previousGood][1]) {
                removals++;
            } else {
                previousGood = i;
            }
        }
        return removals;
    }
}
