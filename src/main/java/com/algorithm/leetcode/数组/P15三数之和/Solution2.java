package com.algorithm.leetcode.数组.P15三数之和;

//给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重
//复的三元组。
//
// 注意：答案中不可以包含重复的三元组。
//
//
//
// 示例 1：
//
//
//输入：nums = [-1,0,1,2,-1,-4]
//输出：[[-1,-1,2],[-1,0,1]]
//
//
// 示例 2：
//
//
//输入：nums = []
//输出：[]
//
//
// 示例 3：
//
//
//输入：nums = [0]
//输出：[]
//
//
//
//
// 提示：
//
//
// 0 <= nums.length <= 3000
// -10⁵ <= nums[i] <= 10⁵
//
// Related Topics 数组 双指针 排序 👍 3845 👎 0

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution2 {

    // 1:51 下午	info
    //				解答成功:
    //				执行耗时:215 ms,击败了10.81% 的Java用户
    //				内存消耗:42.5 MB,击败了35.55% 的Java用户
    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return ans;
        }

        Map<Integer, Integer> numCountMap = new HashMap<>();
        for (int num : nums) {
            Integer count = numCountMap.getOrDefault(num, 0);
            numCountMap.put(num, count + 1);
        }

        // 超过3个0
        if (numCountMap.getOrDefault(0, 0) >= 3) {
            ans.add(Arrays.asList(0, 0, 0));
        }

        // 出现过0
        if (numCountMap.getOrDefault(0, 0) >= 1) {
            numCountMap.remove(0);
            for (Integer leftNum : numCountMap.keySet()) {
                Integer rightNum = -leftNum;
                if (leftNum < rightNum && numCountMap.containsKey(rightNum)) {
                    ans.add(Arrays.asList(leftNum, 0, rightNum));
                }
            }
        }

        // 取任意一个数字作为中间的数字
        for (int middleNum : numCountMap.keySet()) {
            // 取任意一个数字作为左侧的数字
            for (int leftNum : numCountMap.keySet()) {
                int rightNum = -(middleNum + leftNum);
                if (leftNum <= middleNum && middleNum <= rightNum && numCountMap.containsKey(rightNum)) {
                    if (leftNum == middleNum || rightNum == middleNum) {
                        if (numCountMap.get(middleNum) < 2) {
                            continue;
                        }
                    }
                    ans.add(Arrays.asList(leftNum, middleNum, rightNum));
                }
            }
        }

        return ans;
    }

    public static class TestClass {

        // 示例 1：
        //
        //输入：nums = [-1,0,1,2,-1,-4]
        //输出：[[-1,-1,2],[-1,0,1]]

        @Test
        public void testThreeSum1() {
            int[] nums = new int[] {-1, 0, 1, 2, -1, -4};
            Solution2 solution = new Solution2();
            List<List<Integer>> ans = solution.threeSum(nums);
            System.out.println(ans);
        }

        // 示例 2：
        //
        //输入：nums = []
        //输出：[]
        @Test
        public void testThreeSum2() {
            int[] nums = new int[] {};
            Solution2 solution = new Solution2();
            List<List<Integer>> ans = solution.threeSum(nums);
            System.out.println(ans);
        }

        // 示例 3：
        //
        //输入：nums = []
        //输出：[]
        @Test
        public void testThreeSum3() {
            int[] nums = new int[] {0, 0, 0};
            Solution2 solution = new Solution2();
            List<List<Integer>> ans = solution.threeSum(nums);
            System.out.println(ans);
        }

        // 示例 4：
        //
        //输入：nums = []
        //输出：[]
        @Test
        public void testThreeSum4() {
            int[] nums = new int[] {0, 0, 0, 0};
            Solution2 solution = new Solution2();
            List<List<Integer>> ans = solution.threeSum(nums);
            System.out.println(ans);
        }

        //				解答失败:
        //				测试用例:[3,0,-2,-1,1,2]
        //				测试结果:[[-1,0,1],[-2,0,2],[-2,-1,3],[-1,-2,3],[-2,3,-1]]
        //				期望结果:[[-2,-1,3],[-2,0,2],[-1,0,1]]
        //				stdout:
        @Test
        public void testThreeSum5() {
            int[] nums = new int[] {3, 0, -2, -1, 1, 2};
            Solution2 solution = new Solution2();
            List<List<Integer>> ans = solution.threeSum(nums);
            System.out.println(ans);
        }

    }

}

//leetcode submit region end(Prohibit modification and deletion)

