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

import org.junit.Test;

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution6 {

    /**
     * 解答成功:
     * 执行耗时:546 ms,击败了6.21% 的Java用户
     * 内存消耗:47.9 MB,击败了5.03% 的Java用户
     *
     * @param nums 参数
     * @return 结果
     */
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        Set<String> asnSet = new HashSet<>();
        for (int mIndex = 1; mIndex < nums.length - 1; mIndex++) {
            find(nums, mIndex, 0, mIndex - 1, asnSet);
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (String s : asnSet) {
            String[] cop = s.split(":");
            List<Integer> copIntegers = new ArrayList<>();
            for (String s1 : cop) {
                copIntegers.add(Integer.valueOf(s1));
            }
            ans.add(copIntegers);
        }
        return ans;
    }

    public void find(int[] nums, int mIndex, int ll, int lr, Set<String> asnSet) {
        if (ll > lr) {
            return;
        }
        int minValue = nums[ll] + nums[mIndex] + nums[mIndex + 1];
        if (minValue > 0) {
            return;
        }
        int maxValue = nums[lr] + nums[mIndex] + nums[nums.length - 1];
        if (maxValue < 0) {
            return;
        }
        // 如果区间中的最大值
        if (ll == lr) {
            int targetValue = -nums[mIndex] - nums[ll];
            int targetIndex = Arrays.binarySearch(nums, mIndex + 1, nums.length, targetValue);
            if (targetIndex > 0) {
                asnSet.add(nums[ll] + ":" + nums[mIndex] + ":" + nums[targetIndex]);
            }
        } else {
            find(nums, mIndex, ll, (ll + lr) / 2, asnSet);
            find(nums, mIndex, (ll + lr) / 2 + 1, lr, asnSet);
        }
    }

    public static class TestClass {

        // 示例 1：
        //
        //输入：nums = [-1,0,1,2,-1,-4]
        //输出：[[-1,-1,2],[-1,0,1]]

        @Test
        public void testThreeSum1() {
            int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
            Solution6 solution = new Solution6();
            List<List<Integer>> ans = solution.threeSum(nums);
            System.out.println(ans);
        }

        // 示例 2：
        //
        //输入：nums = []
        //输出：[]
        @Test
        public void testThreeSum2() {
            int[] nums = new int[]{};
            Solution6 solution = new Solution6();
            List<List<Integer>> ans = solution.threeSum(nums);
            System.out.println(ans);
        }

        // 示例 3：
        //
        //输入：nums = []
        //输出：[[0,0,0]]
        @Test
        public void testThreeSum3() {
            int[] nums = new int[]{0, 0, 0};
            Solution6 solution = new Solution6();
            List<List<Integer>> ans = solution.threeSum(nums);
            System.out.println(ans);
        }

        // 示例 4：
        //
        //输入：nums = []
        //输出：[[0,0,0]]
        @Test
        public void testThreeSum4() {
            int[] nums = new int[]{0, 0, 0, 0};
            Solution6 solution = new Solution6();
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
            int[] nums = new int[]{3, 0, -2, -1, 1, 2};
            Solution6 solution = new Solution6();
            List<List<Integer>> ans = solution.threeSum(nums);
            System.out.println(ans);
        }

    }

}

//leetcode submit region end(Prohibit modification and deletion)

