package leet.problems.leet1047;

public class Leet1047RemoveAllAdjacentDuplicatesInString {
    public static String removeDuplicates(String S) {
        if (S == null || S.length() < 2) return S;
        char[] c = new char[S.length()];
        int cursorTo = -1;
        for (int i = 0; i < S.length(); i++) {
            if (cursorTo == -1) {
                cursorTo++;
                c[cursorTo] = S.charAt(i);
                continue;
            }
            if (c[cursorTo] == S.charAt(i)) {
                cursorTo--;
                continue;
            }
            cursorTo++;
            c[cursorTo] = S.charAt(i);
        }
        return String.valueOf(c, 0, cursorTo + 1);
    }
}
