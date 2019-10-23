package leet.problems.leet91;

public class Leet91DecodeWays {
    public static int numDecodings(String s) {
        // Sanity-checking.
        if (s == null || s.length() == 0) {
            return 1;
        }
        // No valid sequence starts with '0'.
        if (s.charAt(0) == '0') {
            return 0;
        }

        int len = s.length();
        int[] numWays = new int[len + 2];
        // Initialize so that we don't have to special-case strings starting with ambiguous
        // two-character sequences.
        numWays[len - 1] = 1;
        // Initialize so that we don't need to special-case the first character we consider.
        numWays[len] = 1;

        for (int i = len - 2; i >= 0; i--) {
            char thisDigit = s.charAt(i);
            char nextDigit = s.charAt(i + 1);

            // A sequence like '60' anywhere in the string makes the whole thing invalid.
            boolean invalidSubsequence =
                thisDigit != '1' && thisDigit != '2' && nextDigit == '0';
            // '11' is ambiguous, but '110' is not.
            boolean nextNextCharIsValidAndNot0 =
                i > len - 3 || s.charAt(i + 2) != '0';
            // '11' is ambiguous, '10' is not.
            boolean ambiguousStartingWith1 =
                thisDigit == '1' && nextDigit > '0';
            // '21' is ambiguous, '20' and '27' are not.
            boolean ambiguousStartingWith2 =
                thisDigit == '2' && nextDigit <= '6' && nextDigit > '0';
            boolean genuinelyAmbiguous =
                nextNextCharIsValidAndNot0 && (ambiguousStartingWith1 || ambiguousStartingWith2);

            if (invalidSubsequence) {
                return 0;
            } else if (genuinelyAmbiguous) {
                numWays[i] = numWays[i + 2] + numWays[i + 1];
            } else {
                numWays[i] = numWays[i + 1];
            }
        }
        return numWays[0];
    }
}
