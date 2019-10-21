package leet.problems.leet76;

import java.util.HashSet;
import java.util.Set;

public class Leet76MinimumWindowSubstring {
    public static String minWindow(String s, String t) {
        if (t == null || s == null || t.isEmpty() || s.isEmpty()) {
            return "";
        }
        // Keep track of required character *multiplicity*. This conceptually
        // ought to be a Map<Character, Integer>, but faking it with an array
        // of char works fine for small alphabets.
        int[] requiredCharBudget = new int[512];
        // Track characters that we need more of in the current window.
        // Whenever this is empty, the current window meets the criteria.
        Set<Character> deficitChars = new HashSet<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            requiredCharBudget[c]--;
            deficitChars.add(c);
        }
        // Track the set of unique characters in `t`, so that we can ignore
        // other ones in `s`.
        Set<Character> tchars = new HashSet<>(deficitChars);

        int trailingEdge = 0;
        int leadingEdge = 0;
        int bestLeadingEdge = Integer.MAX_VALUE;
        int bestTrailingEdge = 0;

        while (leadingEdge < s.length()) {
            char c = s.charAt(leadingEdge);

            // `s[i]` is also a character in `t`.
            if (tchars.contains(c)) {
                // Update how much and of which characters we still need more of.
                if (++requiredCharBudget[c] >= 0) {
                    deficitChars.remove(c);
                }
                // If current window is valid...
                while (deficitChars.isEmpty()) {
                    // Check if we need to update "best window" candidate.
                    if (leadingEdge - trailingEdge < bestLeadingEdge - bestTrailingEdge) {
                        bestLeadingEdge = leadingEdge;
                        bestTrailingEdge = trailingEdge;
                    }
                    // Try shrinking the window from the right.
                    char d = s.charAt(trailingEdge);
                    trailingEdge++;
                    // Keep track if we are now short on one of the characters.
                    if (tchars.contains(d) && --requiredCharBudget[d] < 0) {
                        deficitChars.add(d);
                    }
                }
            }
            leadingEdge++;
        }
        // `MAX_VALUE` is the sentinel value for "match not found."
        return bestLeadingEdge == Integer.MAX_VALUE
            ? ""
            : s.substring(bestTrailingEdge, bestLeadingEdge + 1);
    }
}
