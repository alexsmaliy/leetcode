package leet.leet1209;

/*
    Greedily remove all repeats of exactly `k` of the same
    character from a string, including repeats of length
    `k` that appear when a nested repeat is removed.
 */
public class Leet1209RemoveAllAdjacentDuplicatesInStringIi {
    public static String removeDuplicates(String s, int k) {
        if (s == null || s.length() < k) return s;
        char[] chars = new char[s.length()];
        int[] counts = new int[s.length()];
        int cursor = -1;
        for (int i = 0; i < s.length(); i++) {
            if (cursor == -1 || s.charAt(i) != chars[cursor]) {
                cursor++;
                chars[cursor] = s.charAt(i);
                counts[cursor] = 1;
            } else if (s.charAt(i) == chars[cursor]) {
                counts[cursor]++;
                if (counts[cursor] == k) {
                    cursor--;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= cursor; i++) {
            for (int j = 0; j < counts[i]; j++) {
                sb.append(chars[i]);
            }
        }
        return sb.toString();
    }
}
