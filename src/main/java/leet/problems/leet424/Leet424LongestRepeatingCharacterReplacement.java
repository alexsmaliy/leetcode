package leet.problems.leet424;

public class Leet424LongestRepeatingCharacterReplacement {
    public static int characterReplacement(String s, int k) {
        int length = s.length();
        char[] chars = s.toCharArray();
        int[] letterCounts = new int[26 + 'A'];
        int leftEdge = 0;
        int rightEdge = 0;
        int maxCountOfALetter = 0;

        while (rightEdge < length) {
            char c = chars[rightEdge];
            int currentCountofC = ++letterCounts[c];
            maxCountOfALetter = Math.max(maxCountOfALetter, currentCountofC);
            if (rightEdge - leftEdge + 1 > maxCountOfALetter + k) {
                letterCounts[chars[leftEdge]]--;
                leftEdge++;
            }
            rightEdge++;
        }

        return s.length() - leftEdge;
    }
}
