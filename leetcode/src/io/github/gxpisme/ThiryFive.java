package io.github.gxpisme;

/**
 * https://leetcode.com/problems/search-insert-position/
 *
 * Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
 * You must write an algorithm with O(log n) runtime complexity.
 * Example 1:
 * Input: nums = [1,3,5,6], target = 5
 * Output: 2
 *
 * Example 2:
 * Input: nums = [1,3,5,6], target = 2
 * Output: 1
 *
 * Example 3:
 * Input: nums = [1,3,5,6], target = 7
 * Output: 4
 *
 * Example 4:
 * Input: nums = [1,3,5,6], target = 0
 * Output: 0
 *
 * Example 5:
 * Input: nums = [1], target = 0
 * Output: 0
 *
 * Constraints:
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * nums contains distinct values sorted in ascending order.
 * -104 <= target <= 104
 */
public class ThiryFive {

    public static void main(String[] args) {
        int[] nums = {1,3,5,6};

        int i = searchInsert(nums, 2);
        System.out.println(i);
    }

    /**
     * 二分法
     * @param nums
     * @param target
     * @return
     */
    public static int searchInsert(int[] nums, int target) {
        if (nums.length < 1) {
            return 0;
        }

        if (nums[nums.length - 1] < target) {
            return nums.length;
        }

        int left = 0;
        int right = nums.length - 1;
        int middle = 0;

        // 二分法
        while (left < right) {
            middle = (left + right) / 2;
            if (target == nums[middle]) {
                return middle;
            }
            if (target < nums[middle]) {
                right = middle;
            }
            if (target > nums[middle]) {
                left = middle + 1;
            }
        }

        // 根据target与nums[middle]的关系返回index
        if (target < nums[middle]) {
            return middle;
        }
        if (target > nums[middle]) {
            return middle + 1;
        }
        return 0;
    }
}
