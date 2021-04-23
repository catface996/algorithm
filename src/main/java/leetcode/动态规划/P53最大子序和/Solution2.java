package leetcode.动态规划.P53最大子序和;
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
class Solution2 {

    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        // 获得所有以end为结尾的子数组的最大值,取其中的最大
        for (int end = 0; end < nums.length; end++) {
            // 已end为结尾的所有数组中的最大值
            max = Math.max(max, maxByEnd(nums, end));
        }
        return max;
    }

    /**
     * 以end为结尾的所有子数组中的最大值
     *
     * @param nums 原始数组
     * @param end  结尾下标
     * @return 以end为结尾的最大值
     */
    public int maxByEnd(int[] nums, int end) {
        if (end == 0) {
            return nums[0];
        }
        // 通式,因为要连续,要么是maxByEnd(end-1)+nums[end],要么是nums[end]
        int max = maxByEnd(nums, end - 1);
        return Math.max(max + nums[end], nums[end]);
    }

    public static class TestClass {

        //输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
        //输出：6
        //解释：连续子数组[4,-1,2,1] 的和最大，为6 。
        @Test
        public void test1() {
            int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
            Solution2 solution = new Solution2();
            int ans = solution.maxSubArray(nums);
            System.out.println(ans);
        }

        @Test
        public void test2() {
            int[] nums = {1};
            Solution2 solution = new Solution2();
            int ans = solution.maxSubArray(nums);
            System.out.println(ans);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
