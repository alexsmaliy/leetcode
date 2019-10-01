package others;

import java.util.Arrays;

public final class Sorting {
    public static void main(String[] args) {
        int[] data = new int[] {9,8,7,6,5,4,3,2,1};
        mergeSort(data);
        System.out.println(Arrays.toString(data));
    }

    private static void merge(int w1FromIncl, int w1ToExcl, int w2FromIncl, int w2ToExcl, int[] from, int[] to) {
        int total = w1ToExcl - w1FromIncl + w2ToExcl - w2FromIncl;
        int j = w1FromIncl;
        int k = w2FromIncl;
        for (int i = 0; i < total; i++) {
            if (j == w1ToExcl) {
                System.arraycopy(from, k, to, i + w1FromIncl, w2ToExcl - k);
                break;
            } else if (k == w2ToExcl) {
                System.arraycopy(from, j, to, i + w1FromIncl, w1ToExcl - j);
                break;
            } else {
                if (from[j] < from[k]) {
                    to[i + w1FromIncl] = from[j];
                    j++;
                } else {
                    to[i + w1FromIncl] = from[k];
                    k++;
                }
            }
        }
    }

    public static void mergeSort(int[] data) {
        int totalLength = data.length;
        int[] data2 = new int[totalLength];
        int[] from = data;
        int[] to = data2;
        boolean flipflop = false;
        for (int windowSize = 1; windowSize < totalLength; windowSize *= 2) {
            for (int window = 0,
                    windowPos = window * windowSize,
                    nextWindowPos = windowPos + windowSize,
                    nextNextWindowPos = nextWindowPos + windowSize;
                 windowPos < totalLength;
                 window += 2,
                    windowPos = window * windowSize,
                    nextWindowPos = windowPos + windowSize,
                    nextNextWindowPos = nextWindowPos + windowSize) {
                if (nextWindowPos >= totalLength) {
                    System.arraycopy(
                        from,
                        windowPos,
                        to,
                        windowPos,
                        totalLength - windowPos);
                } else if (nextNextWindowPos >= totalLength) {
                    merge(
                        windowPos,
                        nextWindowPos,
                        nextWindowPos,
                        totalLength,
                        from,
                        to);
                } else {
                    merge(
                        window * windowSize,
                        nextWindowPos,
                        nextWindowPos,
                        nextNextWindowPos,
                        from,
                        to);
                }
            }
            int[] tmp = to;
            to = from;
            from = tmp;
            flipflop = !flipflop;
        }
        if (flipflop) {
            System.arraycopy(from, 0, data, 0, totalLength);
        }
    }
}
