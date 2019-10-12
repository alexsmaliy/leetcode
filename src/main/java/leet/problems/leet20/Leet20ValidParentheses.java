package leet.problems.leet20;

import java.util.Stack;

public class Leet20ValidParentheses {
    public static boolean isValid(String s) {
        if (s.length() == 0) return true;
        if (s.length() == 1) return false;
        char[] stack = new char[s.length()];
        int cursor = 0;
        stack[cursor] = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '(':
                case '[':
                case '{':
                    cursor++;
                    stack[cursor] = c;
                    break;
                case ')':
                    if (cursor == -1 || stack[cursor] != '(') return false;
                    else cursor--;
                    break;
                case ']':
                    if (cursor == -1 || stack[cursor] != '[') return false;
                    else cursor--;
                    break;
                case '}':
                    if (cursor == -1 || stack[cursor] != '{') return false;
                    else cursor--;
                    break;
            }
        }
        return cursor == -1;
    }
}
