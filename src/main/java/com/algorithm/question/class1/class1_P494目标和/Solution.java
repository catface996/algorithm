package com.algorithm.question.class1.class1_P494目标和;

//给你一个整数数组 nums 和一个整数 target 。
//
// 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
//
//
// 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
//
//
// 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,1,1,1,1], target = 3
//输出：5
//解释：一共有 5 种方法让最终目标和为 3 。
//-1 + 1 + 1 + 1 + 1 = 3
//+1 - 1 + 1 + 1 + 1 = 3
//+1 + 1 - 1 + 1 + 1 = 3
//+1 + 1 + 1 - 1 + 1 = 3
//+1 + 1 + 1 + 1 - 1 = 3
//
//
// 示例 2：
//
//
//输入：nums = [1], target = 1
//输出：1
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 20
// 0 <= nums[i] <= 1000
// 0 <= sum(nums[i]) <= 1000
// -1000 <= target <= 100
//
// Related Topics 深度优先搜索 动态规划
// 👍 667 👎 0

import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
public class Solution {

    // 2:18 下午	info
    //				解答成功:
    //				执行耗时:735 ms,击败了7.80% 的Java用户
    //				内存消耗:36.2 MB,击败了47.82% 的Java用户

    public int findTargetSumWays(int[] nums, int target) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        return process(nums, 0, target);
    }

    public int process(int[] nums, int start, int target) {

        if (start == nums.length) {
            return target == 0 ? 1 : 0;
        }

        // 当前位置是加号
        int ways = process(nums, start + 1, target - nums[start]);

        // 当前位置是减号
        ways += process(nums, start + 1, target + nums[start]);

        return ways;
    }

    public static class TestClass {

        // 示例 1：
        //
        //
        //输入：nums = [1,1,1,1,1], target = 3
        //输出：5
        //解释：一共有 5 种方法让最终目标和为 3 。
        @Test
        public void test1() {
            int[] nums = {1, 1, 1, 1, 1};
            int target = 3;
            Solution solution = new Solution();
            int ans = solution.findTargetSumWays(nums, target);
            System.out.println(ans);
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)

