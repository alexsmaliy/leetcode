package leet.leet49;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Leet49GroupAnagrams {
    static class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {
            Map<String, Integer> index = new HashMap<>();
            List<List<String>> answer = new ArrayList<>();
            for (int i = 0; i < strs.length; i++) {
                char[] s = strs[i].toCharArray();
                Arrays.sort(s);
                int indexOfS = index.computeIfAbsent(
                    String.valueOf(s),
                    arg -> index.size());
                if (indexOfS >= answer.size()) {
                    answer.add(new ArrayList<>());
                }
                answer.get(indexOfS).add(strs[i]);
            }
            return answer;
        }

        static Comparator<char[][]> COMPARATOR = (char[][] val1, char[][] val2) -> {
            for (int i = 0; i < 26; i++) {
                if (val1[1][i] < val2[1][i]) return -1;
                if (val1[1][i] > val2[1][i]) return 1;
            }
            return 0;
        };

        public List<List<String>> groupAnagrams2(String[] strs) {
            if (strs.length == 0) return new ArrayList<>();
            char[][][] chars = new char[strs.length][2][];
            for (int i = 0; i < strs.length; i++) {
                char[] s = strs[i].toCharArray();
                chars[i][0] = s;
                chars[i][1] = new char[26];
                for (int j = 0; j < s.length; j++) {
                    chars[i][1][s[j] - 'a']++;
                }
            }
            Arrays.sort(chars, COMPARATOR);
            List<List<String>> answer = new ArrayList<>();
            List<String> a1 = new ArrayList<>();
            a1.add(String.valueOf(chars[0][0]));
            answer.add(a1);
            List<String> currentList = a1;
            for (int i = 1; i < strs.length; i++) {
                if (COMPARATOR.compare(chars[i], chars[i - 1]) != 0) {
                    currentList = new ArrayList<>();
                    answer.add(currentList);
                }
                currentList.add(String.valueOf(chars[i][0]));
            }
            return answer;
        }
    }

    public static void main(String[] args) {
        System.out.println(
            new Solution().groupAnagrams(
                new String[] {"eat", "tea", "tan", "ate", "nat", "bat"}));
    }
}
