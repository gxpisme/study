package io.github.gxpisme;

/**
 * https://leetcode.com/problems/3sum-closest/
 * <p>
 * Given an integer array nums of length n and an integer target, find three integers in nums such that the sum is closest to target.
 * Return the sum of the three integers.
 * You may assume that each input would have exactly one solution.
 * <p>
 * Example 1:
 * Input: nums = [-1,2,1,-4], target = 1
 * Output: 2
 * Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 * <p>
 * Example 2:
 * Input: nums = [0,0,0], target = 1
 * Output: 0
 * <p>
 * Constraints:
 * 3 <= nums.length <= 1000
 * -1000 <= nums[i] <= 1000
 * -10^4 <= target <= 10^4
 *
 * @author gxp177726@alibaba-inc.com
 * @date 2021/10/22
 */
public class Sixteen {

    public static void main(String[] args) {
        int[] nums = {-1,2,1,-4};
        int target = 1;
        int result = threeSumClosest(nums, target);
        System.out.println(result);
    }

    public static int threeSumClosest(int[] nums, int target) {
        int closestValue = Integer.MAX_VALUE;
        Integer minValue = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    // 求每三个值之和 与 目标值的差
                    int diff = nums[i] + nums[j] + nums[k] - target;
                    // 记录与目标值最小的差
                    minValue = Math.min(minValue, Math.abs(diff));
                    // 若最小的差就是minValue值，则closestValue就是最接近target值
                    if (Math.abs(diff) == minValue) {
                        closestValue = nums[i] + nums[j] + nums[k];
                    }
                }
            }
        }
        return closestValue;
    }
}
