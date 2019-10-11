package leet.leet20;

import java.util.Stack;

public class Leet20ValidParentheses {
    class Solution {
        public boolean isValid(String s) {
            if (s.length() == 0) return true;
            if (s.length() == 1) return false;
            Stack<Character> stack = new Stack<>();
            stack.push(s.charAt(0));
            for (int i = 1; i < s.length(); i++) {
                char c = s.charAt(i);
                switch (c) {
                    case '(':
                    case '[':
                    case '{':
                        stack.push(c);
                        break;
                    case ')':
                        if (stack.isEmpty() || stack.peek() != '(') return false;
                        else stack.pop();
                        break;
                    case ']':
                        if (stack.isEmpty() || stack.peek() != '[') return false;
                        else stack.pop();
                        break;
                    case '}':
                        if (stack.isEmpty() || stack.peek() != '{') return false;
                        else stack.pop();
                        break;
                    default:
                        break;
                }
            }
            return stack.isEmpty();
        }
    }
}
