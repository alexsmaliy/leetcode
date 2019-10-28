package leet.problems.leet139;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Leet139WordBreak {
    public static boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>();
        int maxLength = 0;
        for (String word : wordDict) {
            maxLength = Math.max(maxLength, word.length());
            set.add(word);
        }
        boolean[] tracker = new boolean[s.length()];
        for (int i = 0; i < Math.min(s.length(), maxLength); i++) {
            tracker[i] = set.contains(s.substring(0, i + 1));
        }
        for (int i = 1; i < s.length(); i++) {
            for (int j = i; j < Math.min(i + maxLength, s.length()); j++) {
                tracker[j] = tracker[j]
                    || (tracker[i - 1] && set.contains(s.substring(i, j + 1)));
            }
        }
        return tracker[s.length() - 1];
    }
}
