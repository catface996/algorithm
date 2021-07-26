package com.algorithm.leetcode.数组.P1两数之和;

//给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那 两个 整数，并返回它们的数组下标。
//
// 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
//
// 你可以按任意顺序返回答案。
//
//
//
// 示例 1：
//
//
//输入：nums = [2,7,11,15], target = 9
//输出：[0,1]
//解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
//
//
// 示例 2：
//
//
//输入：nums = [3,2,4], target = 6
//输出：[1,2]
//
//
// 示例 3：
//
//
//输入：nums = [3,3], target = 6
//输出：[0,1]
//
//
//
//
// 提示：
//
//
// 2 <= nums.length <= 104
// -109 <= nums[i] <= 109
// -109 <= target <= 109
// 只会存在一个有效答案
//
//
// 进阶：你可以想出一个时间复杂度小于 O(n2) 的算法吗？
// Related Topics 数组 哈希表
// 👍 11664 👎 0

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
@Slf4j
class Solution {

    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length <= 0) {
            return new int[] {};
        }
        Map<Integer, Integer> munMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            munMap.put(nums[i], i);
        }
        int left;
        for (int i = 0; i < nums.length; i++) {
            left = target - nums[i];
            Integer index = munMap.get(left);
            if (index != null && index != i) {
                return new int[] {i, index};
            }
        }
        return new int[] {};
    }

    public static class TestClass {
        @Test
        public void test1() {
            Solution solution = new Solution();
            int[] nums = new int[] {3, 2, 4};
            int target = 6;
            int[] ans = solution.twoSum(nums, target);
            log.info("ans:{}", ans);
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)

