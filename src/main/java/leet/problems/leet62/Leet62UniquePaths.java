package leet.problems.leet62;

import java.math.BigInteger;
import java.util.Arrays;

public class Leet62UniquePaths {

    /* COMBINATORIAL PASCAL'S TRIANGLE SOLUTION */
    public static int uniquePaths1(int m, int n) {
        return binomial(m + n - 2, n - 1);
    }

    private static int binomial(int n, int k) {
        BigInteger ret = BigInteger.ONE;
        if (k < n / 2) {
            k = n - k;
        }
        for (int i = n; i > k; i--) {
            ret = ret.multiply(BigInteger.valueOf(i));
        }
        for (int i = n - k; i > 1; i--) {
            ret = ret.divide(BigInteger.valueOf(i));
        }
        return ret.intValue();
    }

    /* "BRUTE FORCE" PASCAL'S TRIANGLE SOLUTION  */
    public static int uniquePaths2(int m, int n) {
        int[] prevRow = new int[n];
        int[] currRow = new int[n];
        Arrays.fill(prevRow, 1);
        for (int r = m - 2; r >= 0; r--) {
            currRow[n - 1] = 1;
            for (int c = n - 2; c >= 0; c--) {
                currRow[c] = currRow[c + 1] + prevRow[c];
            }
            prevRow = currRow;
            currRow = new int[n];
        }
        return prevRow[0];
    }
}
