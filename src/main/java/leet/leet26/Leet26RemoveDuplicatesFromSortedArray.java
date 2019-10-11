package leet.leet26;

/*
    Remove duplicates in place from a sorted array.
    Return the length of the deduplicated portion,
    counting from the left.
 */
public class Leet26RemoveDuplicatesFromSortedArray {
    public int removeDuplicates(int[] nums) {
        if (nums == null) return 0;
        if (nums.length < 2) return nums.length;
        int cursor = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[cursor] != nums[i]) {
                cursor++;
                nums[cursor] = nums[i];
            }
        }
        return cursor + 1;
    }
}
