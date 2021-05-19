package leetcode.动态规划.P494目标和;

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
public class Solution3 {

    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        // 如果target 大于sum,所有数字均使用正数,都无法构成target
        // 因为所有的数字都要参与,所以,如果能构成target,那么所有数字的累加和与target的就行一定是一致的
        // 如果target 和 sum 为奇数,必定不能构成target
        if (target > sum || (target + sum) % 2 == 1) {
            return 0;
        }
        // 因为target可能为负值,target + sum 一定不会小于0
        // 题目转换成,有两个一模一样的数组,第一个数组中的数字可以取正,也可以取负,第二个数组中的数字只能取正
        // 求组成target的问题,就变成了求 target+sum 的问题,target+sum>=0
        // -sum <= target <=sum  所以  0<=target+sum<=2*sum
        // 0<=(target+sum)/2<=sum
        // 举例子: sum = 5,target = -3,实际上只要求出 target=2时的方案,将符全部取反即可.
        int realTarget = (target + sum) / 2;
        int[] dp = new int[realTarget + 1];
        dp[0] = 1;
        for (int num : nums) {
            for (int j = realTarget; j >= num; j--) {
                dp[j] = dp[j] + dp[j - num];
            }
        }
        return dp[realTarget];
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
            Solution3 solution = new Solution3();
            int ans = solution.findTargetSumWays(nums, target);
            System.out.println(ans);
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)

