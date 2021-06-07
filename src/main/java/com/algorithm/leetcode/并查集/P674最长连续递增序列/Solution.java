package com.algorithm.leetcode.并查集.P674最长连续递增序列;

//给定一个未经排序的整数数组，找到最长且 连续递增的子序列，并返回该序列的长度。
//
// 连续递增的子序列 可以由两个下标 l 和 r（l < r）确定，如果对于每个 l <= i < r，都有 nums[i] < nums[i + 1] ，那
//么子序列 [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] 就是连续递增子序列。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,3,5,4,7]
//输出：3
//解释：最长连续递增序列是 [1,3,5], 长度为3。
//尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为 5 和 7 在原数组里被 4 隔开。
//
//
// 示例 2：
//
//
//输入：nums = [2,2,2,2,2]
//输出：1
//解释：最长连续递增序列是 [2], 长度为1。
//
//
//
//
// 提示：
//
//
// 0 <= nums.length <= 104
// -109 <= nums[i] <= 109
//
// Related Topics 数组
// 👍 179 👎 0

import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
public class Solution {

    public int findLengthOfLCIS(int[] nums) {
        int currentMax = Integer.MIN_VALUE;
        int maxLength = 0;
        int count = 0;
        for (int num : nums) {
            if (num > currentMax) {
                currentMax = num;
                count++;
            } else {
                maxLength = Math.max(maxLength, count);
                currentMax = num;
                count = 1;
            }
        }
        maxLength = Math.max(maxLength, count);
        return maxLength;
    }

    public static class TestClass {
        // 示例 1：
        //
        //
        //输入：nums = [1,3,5,4,7]
        //输出：3
        //解释：最长连续递增序列是 [1,3,5], 长度为3。
        //尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为 5 和 7 在原数组里被 4 隔开。
        @Test
        public void test() {
            int[] nums = {1, 3, 5, 4, 7};
            Solution solution = new Solution();
            int ans = solution.findLengthOfLCIS(nums);
            System.out.println(ans);
        }

        @Test
        public void test2() {
            // 示例 2：
            //
            //
            //输入：nums = [2,2,2,2,2]
            //输出：1
            //解释：最长连续递增序列是 [2], 长度为1。
            int[] nums = {2, 2, 2, 2, 2};
            Solution solution = new Solution();
            int ans = solution.findLengthOfLCIS(nums);
            System.out.println(ans);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

