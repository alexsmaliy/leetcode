package leet.problems.leet238;

import java.util.Arrays;

public class Leet238ProductOfArrayExceptSelf {
    public static int[] productExceptSelf(int[] nums) {
        if (nums.length == 1) { return new int[0]; }
        int[] output = new int[nums.length];
        Arrays.fill(output, 1);
        int accFw = nums[0];
        int accRw = nums[nums.length - 1];
        for (int i = 1, j = nums.length - 2; i < nums.length; i++, j--) {
            output[i] *= accFw;
            output[j] *= accRw;
            accFw *= nums[i];
            accRw *= nums[j];
        }
        return output;
    }

    public static int[] productExceptSelf2(int[] nums) {
        int product = 1;
        int max = 0;
        int countZeros = 0;
        int countNegs = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                product *= Math.abs(nums[i]);
                countNegs++;
            } else if (nums[i] > 0) {
                product *= nums[i];
            } else {
                countZeros++;
            }
            max = Math.max(max, Math.abs(nums[i]));
        }

        if (countZeros > 1) {
            return new int[nums.length];
        }

        int[] output = new int[nums.length];
        Arrays.fill(output, product);

        for (int i = 0; i < nums.length; i++) {
            int quotient;
            int n = nums[i];
            if (countZeros > 0 && n != 0) { output[i] = 0; continue; }
            if (n == 0) {
                quotient = product * (countNegs % 2 == 1 ? -1 : 1);
            } else {
                int dividend = product;
                int divisor = Math.abs(n);
                quotient = divide(dividend, divisor);
            }
            if (n < 0 && countNegs % 2 == 0) {
                quotient *= -1;
            } else if (n > 0 && countNegs % 2 == 1) {
                quotient *= -1;
            }
            output[i] = quotient;
        }

        return output;
    }

    private static int divide(int dividend, int divisor) {
        int adj = Integer.numberOfLeadingZeros(divisor) - Integer.numberOfLeadingZeros(dividend);
        divisor <<= adj;
        int quotient = 0;
        for (int i = 0; i <= adj; i++) {
            if (dividend - divisor >= 0) {
                quotient |= 1 << (adj - i);
                dividend -= divisor;
            }
            divisor >>= 1;
        }
        return quotient;
    }
}
