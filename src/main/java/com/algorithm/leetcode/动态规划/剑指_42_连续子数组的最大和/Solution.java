package com.algorithm.leetcode.动态规划.剑指_42_连续子数组的最大和;

//输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
//
// 要求时间复杂度为O(n)。
//
//
//
// 示例1:
//
// 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
//输出: 6
//解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
//
//
//
// 提示：
//
//
// 1 <= arr.length <= 10^5
// -100 <= arr[i] <= 100
//
//
// 注意：本题与主站 53 题相同：https://leetcode-cn.com/problems/maximum-subarray/
//
//
// Related Topics 分治算法 动态规划
// 👍 275 👎 0

import com.algorithm.util.ArrayUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
@Slf4j
public class Solution {


    // 11:46 上午	info
    //					解答成功:
    //					执行耗时:8 ms,击败了15.98% 的Java用户
    //					内存消耗:55.7 MB,击败了5.10% 的Java用户

    int max = Integer.MIN_VALUE;

    /**
     * 子数组,子序列问题,一般是以i位置为结尾
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        process(nums.length - 1, nums);
        return max;
    }

    public int process(int index, int[] nums) {

        if (index == 0) {
            max = Math.max(max, nums[0]);
            return nums[0];
        }

        // index位置的累加和分两种情况
        // 1.index位置累加和,包括index-1位置的累加和
        // 2.index位置的累加和,只保留index位置的值,不包括index-1位置的累加和
        int p1 = nums[index] + process(index - 1, nums);
        int p2 = nums[index];
        int tempMax = Math.max(p1, p2);
        max = Math.max(max, tempMax);
        return tempMax;
    }

    public static class TestClass {
        // 示例1:
        //
        // 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
        //输出: 6
        @Test
        public void test1() {
            int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
            Solution solution = new Solution();
            int ans = solution.maxSubArray(nums);
            log.info("ans:{}", ans);

            int ans2 = Code02_SubArrayMaxSum.maxSubArray2(nums);
            log.info("ans2:{}", ans2);
        }

        @Test
        public void test2() {
            int[] nums = {8, -5, -3, -5, -7, 2, -7, -9, -1, 2};
            Solution solution = new Solution();
            int ans = solution.maxSubArray(nums);
            log.info("ans:{}", ans);

            int ans2 = Code02_SubArrayMaxSum.maxSubArray2(nums);
            log.info("ans2:{}", ans2);
        }

        @Test
        public void testForce() {
            for (int i = 0; i < 100; i++) {
                int[] nums = ArrayUtil.randomIntArray(10, 1, 10, true);
                Solution solution = new Solution();
                int ans = solution.maxSubArray(nums);
                int ans2 = Code02_SubArrayMaxSum.maxSubArray2(nums);
                if (ans != ans2) {
                    log.info("ans:{},ans2:{},nums:{}", ans, ans2, nums);
                }
                assert ans == ans2;
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

