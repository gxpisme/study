package io.github.gxpisme;

import java.util.*;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/4sum/
 * <p>
 * <p>
 * Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:
 * <p>
 * 0 <= a, b, c, d < n
 * a, b, c, and d are distinct.
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * You may return the answer in any order.
 * <p>
 * Example 1:
 * Input: nums = [1,0,-1,0,-2,2], target = 0
 * Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * Example 2:
 * Input: nums = [2,2,2,2,2], target = 8
 * Output: [[2,2,2,2]]
 * Constraints:
 * 1 <= nums.length <= 200
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 */
public class Eighteen {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int[] nums = {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2};
//        int[] nums = {1,0,-1,0,-2,2};
        List<List<Integer>> res = fourSum(nums, 8);
        System.out.println(res);
        System.out.println(System.currentTimeMillis() - start);
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums.length < 4) {
            return new ArrayList<>();
        }
        // 跟3sum同样的解法，先排序，找到最后一个值，用二分查找
        Arrays.sort(nums);

        for (int num : nums) {
            System.out.print(" " + num);
        }
        System.out.println();
        System.out.println();
        System.out.println();

        Map<String, List<Integer>> map = new HashMap<>(nums.length);

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (i != j && i != k && j != k) {
                        int findValue = target - nums[i] - nums[j] - nums[k];
                        int resIndex = binSearch(nums, findValue, 0);
                        if (resIndex >= 0 && resIndex != j && resIndex != i && resIndex != k) {
                            List<Integer> integerList = Arrays.asList(nums[i], nums[j], nums[k], nums[resIndex]);
                            Collections.sort(integerList);
                            map.put(integerList.toString(), integerList);
                        }
                    }
                }
            }
        }

        return map.values().stream().collect(Collectors.toList());
    }

    public static int binSearch(int[] nums, int findValue, int left) {
        Integer right = nums.length - 1;
        while (left < right) {
            Integer middle = (right + left) / 2;
            if (nums[middle] == findValue) {
                return middle;
            }
            if (nums[middle] > findValue) {
                right = middle;
            }
            if (nums[middle] < findValue) {
                left = middle + 1;
            }
        }
        return -1;
    }
}
