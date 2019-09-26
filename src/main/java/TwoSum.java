import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    class Solution {
        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<>();
            int[] answer = new int[2];
            for (int i = 0; i < nums.length; i++) {
                int n = nums[i];
                int diff = target - n;
                if (map.containsKey(diff)) {
                    int j = map.get(diff);
                    answer[0] = Math.min(i, j);
                    answer[1] = Math.max(i, j);
                    return answer;
                }
                map.put(n, i);
            }
            return answer;
        }
    }
}
