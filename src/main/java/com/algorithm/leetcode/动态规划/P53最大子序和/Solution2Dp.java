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
class Solution2Dp {

    public int maxSubArray(int[] nums) {
        return processDp(nums);
    }

    public int processDp(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];
        for (int end = 1; end < nums.length; end++) {
            int tempMax = Math.max(dp[end - 1] + nums[end], nums[end]);
            dp[end] = tempMax;
            max = Math.max(max, tempMax);
        }
        return max;
    }

    public static class TestClass {

        //输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
        //输出：6
        //解释：连续子数组[4,-1,2,1] 的和最大，为6 。
        @Test
        public void test1() {
            int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
            Solution2Dp solution = new Solution2Dp();
            int ans = solution.maxSubArray(nums);
            System.out.println(ans);
        }

        @Test
        public void test2() {
            int[] nums = {1};
            Solution2Dp solution = new Solution2Dp();
            int ans = solution.maxSubArray(nums);
            System.out.println(ans);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
