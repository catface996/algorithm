package leetcode.动态规划.P213打家劫舍II;

//你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的
//房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
//
// 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
//
//
//
// 示例 1：
//
//
//输入：nums = [2,3,2]
//输出：3
//解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
//
//
// 示例 2：
//
//
//输入：nums = [1,2,3,1]
//输出：4
//解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
//     偷窃到的最高金额 = 1 + 3 = 4 。
//
// 示例 3：
//
//
//输入：nums = [0]
//输出：0
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 100
// 0 <= nums[i] <= 1000
//
// Related Topics 动态规划
// 👍 662 👎 0

import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 4:05 下午	info
    //				解答成功:
    //				执行耗时:0 ms,击败了100.00% 的Java用户
    //				内存消耗:35.8 MB,击败了72.75% 的Java用户

    public int rob(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return 0;
        }
        int num1 = nums[0] + process(nums, 2, nums.length - 2);
        int num2 = process(nums, 1, nums.length - 1);
        return Math.max(num1, num2);
    }

    private int process(int[] nums, int start, int lastIndex) {
        int current2 = 0;
        int current1 = 0;
        int ans = 0;
        for (int index = lastIndex; index >= start; index--) {
            ans = Math.max(current1, nums[index] + current2);
            current2 = current1;
            current1 = ans;
        }
        return ans;
    }

    public static class TestClass {

        //输入：nums = [2,3,2]
        //输出：3
        //解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
        @Test
        public void test1() {
            int[] nums = {2, 3, 2};
            Solution solution = new Solution();
            int ans = solution.rob(nums);
            System.out.println(ans);
        }

        //输入：nums = [1,2,3,1]
        //输出：4
        //解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
        //     偷窃到的最高金额 = 1 + 3 = 4 。
        @Test
        public void test2() {
            int[] nums = {1, 2, 3, 1};
            Solution solution = new Solution();
            int ans = solution.rob(nums);
            System.out.println(ans);
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)

