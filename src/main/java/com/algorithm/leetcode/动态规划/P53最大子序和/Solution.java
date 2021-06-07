package com.algorithm.leetcode.动态规划.P53最大子序和;
//给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
//
//
//
// 示例 1：
//
//
//输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
//输出：6
//解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
//
//
// 示例 2：
//
//
//输入：nums = [1]
//输出：1
//
//
// 示例 3：
//
//
//输入：nums = [0]
//输出：0
//
//
// 示例 4：
//
//
//输入：nums = [-1]
//输出：-1
//
//
// 示例 5：
//
//
//输入：nums = [-100000]
//输出：-100000
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 3 * 104
// -105 <= nums[i] <= 105
//
//
//
//
// 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。
// Related Topics 数组 分治算法 动态规划
// 👍 3155 👎 0

import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 超时
    private int max = Integer.MIN_VALUE;

    public int maxSubArray(int[] nums) {
        int sum = processSum(nums, 0, nums.length - 1);
        return Math.max(max, sum);
    }

    private int processSum(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }
        // 除去左侧
        int noLeft = processSum(nums, left + 1, right);
        int hasLeft = noLeft + nums[left];
        max = Math.max(max, noLeft);
        max = Math.max(max, hasLeft);

        // 除去右侧
        int notRight = processSum(nums, left, right - 1);
        max = Math.max(max, notRight);

        return hasLeft;
    }

    public static class TestClass {

        //输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
        //输出：6
        //解释：连续子数组[4,-1,2,1] 的和最大，为6 。
        @Test
        public void test1() {
            int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
            Solution solution = new Solution();
            int ans = solution.maxSubArray(nums);
            System.out.println(ans);
        }

        @Test
        public void test2() {
            int[] nums = {1};
            Solution solution = new Solution();
            int ans = solution.maxSubArray(nums);
            System.out.println(ans);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
