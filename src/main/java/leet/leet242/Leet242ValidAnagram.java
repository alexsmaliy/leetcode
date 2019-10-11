package leet.leet242;

import java.util.Arrays;

public class Leet242ValidAnagram {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        byte[] countsS = new byte[26];
        byte[] countsT = new byte[26];
        char[] sarr = s.toLowerCase().toCharArray();
        char[] tarr = t.toLowerCase().toCharArray();
        for (int i = 0; i < sarr.length; i++) {
            countsS[sarr[i] - 'a']++;
            countsT[tarr[i] - 'a']++;
        }
        return Arrays.equals(countsS, countsT);
    }
}

