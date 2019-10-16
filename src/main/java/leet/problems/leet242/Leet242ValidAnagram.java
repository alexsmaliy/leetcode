package leet.problems.leet242;

import java.util.Arrays;

public class Leet242ValidAnagram {
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] countsS = new int[26];
        int[] countsT = new int[26];
        char[] sarr = s.toLowerCase().toCharArray();
        char[] tarr = t.toLowerCase().toCharArray();
        for (int i = 0; i < sarr.length; i++) {
            countsS[sarr[i] - 'a']++;
            countsT[tarr[i] - 'a']++;
        }
        return Arrays.equals(countsS, countsT);
    }
}

