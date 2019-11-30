package leet.problems.leet140;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Leet140WordBreakIi {
    public static List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> words = new HashSet<>();
        BitSet wordLengths = new BitSet();
        for (String word : wordDict) {
            words.add(word);
            wordLengths.set(word.length());
        }

        // Initialize with words matching the beginning of the string.
        BitSet[] tracker = new BitSet[s.length()];
        for (int i = wordLengths.nextSetBit(0); i != -1 && i <= s.length(); i = wordLengths.nextSetBit(i + 1)) {
            if (words.contains(s.substring(0, i))) {
                if (tracker[i - 1] == null) {
                    tracker[i - 1] = new BitSet();
                }
                tracker[i - 1].set(i);
            }
        }

        // Iterate over the string, marking potential word breaks and the lengths of words that
        // end there.
        for (int i = 0; i < s.length(); i++) {
            if (tracker[i] != null) {
                for (int j = wordLengths.nextSetBit(0); j != -1 && i + j < s.length(); j = wordLengths.nextSetBit(j + 1)) {
                    if (words.contains(s.substring(i + 1, i + j + 1))) {
                        if (tracker[i + j] == null) {
                            tracker[i + j] = new BitSet();
                        }
                        tracker[i + j].set(j);
                    }
                }
            }
        }

        // Failure condition: can't tokenize input string given this word dictionary.
        if (tracker[s.length() - 1] == null) return Collections.emptyList();

        return recurse(s, "", tracker, s.length() - 1);
    }

    private static List<String> recurse(String s, String suffix, BitSet[] tracker, int pos) {
        if (pos == -1) {
            return Collections.singletonList(suffix.trim());
        }
        List<String> suffixes = new ArrayList<>();
        for (int i = tracker[pos].nextSetBit(0); i != -1; i = tracker[pos].nextSetBit(i + 1)) {
            String word = s.substring(pos - i + 1, pos + 1);
            List<String> suffixesFromHere = recurse(s, word + " " + suffix, tracker, pos - i);
            suffixes.addAll(suffixesFromHere);
        }
        return suffixes;
    }
}
