package leet.problems.leet139;

import java.util.BitSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Leet139WordBreak {
    public static boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>();
        int maxLength = 0;
        BitSet lengths = new BitSet();
        for (String word : wordDict) {
            maxLength = Math.max(maxLength, word.length());
            set.add(word);
            lengths.set(word.length());
        }
        boolean[] tracker = new boolean[s.length()];
        for (int i = lengths.nextSetBit(0); i > -1 && i <= s.length(); i = lengths.nextSetBit(i + 1)) {
            tracker[i - 1] = set.contains(s.substring(0, i));
        }
        for (int i = 1; i < s.length(); i++) {
            for (int j = lengths.nextSetBit(0); j > -1 && j + i <= s.length(); j = lengths.nextSetBit(j + 1)) {
                tracker[i + j - 1] = tracker[i + j - 1]
                    || (tracker[i - 1] && set.contains(s.substring(i, i + j)));
            }
        }
        return tracker[s.length() - 1];
    }
}
