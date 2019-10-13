package leet.problems.leet3;

import java.util.Arrays;

public class Leet3LongestSubstringWithoutRepeatingCharacters {
    public static int lengthOfLongestSubstring(String s) {
        int len = s.length();
        int[] index = new int[256];
        Arrays.fill(index, -1);
        int best = 0;
        for (int to = 0, from = 0; to < len; to++) {
            char c = s.charAt(to);
            int x = index[c];
            if (x >= from) {
                from = x + 1;
            }
            index[c] = to;
            best = Math.max(best, to - from + 1);
        }
        return best;
    }
}
