package com.algorithm.leetcode.前缀树.P421数组中两个数的最大异或值;

//给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n 。
//
// 进阶：你可以在 O(n) 的时间解决这个问题吗？
//
//
//
//
//
// 示例 1：
//
//
//输入：nums = [3,10,5,25,2,8]
//输出：28
//解释：最大运算结果是 5 XOR 25 = 28.
//
// 示例 2：
//
//
//输入：nums = [0]
//输出：0
//
//
// 示例 3：
//
//
//输入：nums = [2,4]
//输出：6
//
//
// 示例 4：
//
//
//输入：nums = [8,10,2]
//输出：10
//
//
// 示例 5：
//
//
//输入：nums = [14,70,53,83,49,91,36,80,92,51,66,70]
//输出：127
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 2 * 104
// 0 <= nums[i] <= 231 - 1
//
//
//
// Related Topics 位运算 字典树
// 👍 362 👎 0

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
@Slf4j
public class Solution {

    // 8:43 下午	info
    //				运行失败:
    //				Time Limit Exceeded
    //				测试用例:

    public int findMaximumXOR(int[] nums) {
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                ans = Math.max(ans, nums[i] ^ nums[j]);
            }
        }
        return ans;
    }

    public static class TestClass {

        // 示例 1：
        //
        //
        //输入：nums = [3,10,5,25,2,8]
        //输出：28
        //解释：最大运算结果是 5 XOR 25 = 28.
        @Test
        public void test1() {
            int[] nums = {3, 10, 5, 25, 2, 8};
            Solution solution = new Solution();
            int ans = solution.findMaximumXOR(nums);
            log.info("ans:{}", ans);
        }

    }

}
//leetcode submit region end(Prohibit modification and deletion)

