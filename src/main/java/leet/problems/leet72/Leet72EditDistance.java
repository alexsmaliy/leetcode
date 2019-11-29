package leet.problems.leet72;

public class Leet72EditDistance {
    public static int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] table = new int[len1 + 1][len2 + 1];

        for (int i = 0; i <= len1; i++)
            for (int j = 0; j <= len2; j++)
                table[i][j] = Integer.MAX_VALUE;

        table[len1][len2] = 0;

        for (int i = 0; i < len2; i++) {
            table[len1][i] = len2 - i;
        }

        for (int i = 0; i < len1; i++) {
            table[i][len2] = len1 - i;
        }

        for (int r = len1 - 1; r >= 0; r--) {
            for (int c = len2 - 1; c >= 0; c--) {
                if (word1.charAt(r) == word2.charAt(c) && table[r][c] > table[r + 1][c + 1]) {
                    table[r][c] = table[r + 1][c + 1];
                }

                if (table[r][c] > table[r + 1][c + 1] + 1) {
                    table[r][c] = table[r + 1][c + 1] + 1;
                }

                if (table[r][c] > table[r][c + 1] + 1) {
                    table[r][c] = table[r][c + 1] + 1;
                }

                if (table[r][c] > table[r + 1][c] + 1) {
                    table[r][c] = table[r + 1][c] + 1;
                }
            }
        }
        return table[0][0];
    }
}
