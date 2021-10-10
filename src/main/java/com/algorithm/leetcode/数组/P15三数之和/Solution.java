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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.algorithm.util.ArrayUtil;
import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 5:31 下午	info
    //				解答成功:
    //				执行耗时:123 ms,击败了12.19% 的Java用户
    //				内存消耗:42.9 MB,击败了10.03% 的Java用户

    public List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return ans;
        }

        // 先排序
        quickSort(nums, 0, nums.length - 1);

        // 如果三个最大值的和小于0,或者,三个最小值的和小于0,没有可能的方案
        if (nums[0] + nums[1] + nums[2] > 0
            || nums[nums.length - 1] + nums[nums.length - 2] + nums[nums.length - 3] < 0) {
            return ans;
        }

        Map<Integer, Set<Integer>> middleLeft = new HashMap<>();
        // 考虑每个位置的数字作为中间数字时的情况
        for (int middle = 1; middle < nums.length - 1; middle++) {
            // 仅当 middle位置为中值, 三数和的最小值 <=0 并且 三数和的最大值 >=0 时,才有可能
            if (nums[0] + nums[middle] + nums[middle + 1] <= 0
                && nums[middle - 1] + nums[middle] + nums[nums.length - 1] >= 0) {
                int left = 0;
                int right = nums.length - 1;
                while (left < middle && middle < right) {
                    int sum = nums[left] + nums[middle] + nums[right];
                    if (sum == 0) {
                        // 发现一种组合方案
                        Set<Integer> leftSet = middleLeft.computeIfAbsent(nums[middle], k -> new HashSet<>());
                        if (!leftSet.contains(nums[left])) {
                            ans.add(Arrays.asList(nums[left], nums[middle], nums[right]));
                            leftSet.add(nums[left]);
                        }
                        left++;
                        right--;
                        continue;
                    }
                    if (sum > 0) {
                        right--;
                        continue;
                    }
                    if (sum < 0) {
                        left++;
                        continue;
                    }
                }
            }
        }
        return ans;
    }

    void quickSort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        // 这个地方可以炫技 pivot = start + (end-start)/2;
        int pivot = nums[(start + end) / 2];
        int less = start - 1;
        int more = end + 1;
        int cur = start;
        while (less < more) {

            if (cur >= more) {
                break;
            }
            // 当前数字比标尺值小,左侧小于标尺的下线右移
            if (nums[cur] < pivot) {
                swap(nums, cur, ++less);
                cur++;
                continue;
            }
            // 当当前值等于标尺值,仅当前值右移
            if (nums[cur] == pivot) {
                cur++;
                continue;
            }
            // 当当前值大于标尺值,当前位置与大于标尺的区间中的前一个位置交换
            // 为了可读性,多写一个if判断
            if (nums[cur] > pivot) {
                swap(nums, cur, --more);
                continue;
            }
        }

        // 排列比标尺值小的区间
        quickSort(nums, start, less);
        // 排列比标尺值大的区间
        quickSort(nums, more, end);

    }

    private void swap(int[] nums, int index1, int index2) {
        if (index1 == index2) {
            return;
        }
        nums[index1] = nums[index1] ^ nums[index2];
        nums[index2] = nums[index1] ^ nums[index2];
        nums[index1] = nums[index1] ^ nums[index2];
    }

    public static class TestClass {
        @Test
        public void test1() {
            Solution solution = new Solution();
            int[] nums = new int[] {1, 3};
            solution.swap(nums, 0, 1);
            System.out.println(nums.length);
        }

        @Test
        public void testQuickSort1() {
            for (int i = 0; i < 100; i++) {
                int[] nums = ArrayUtil.randomIntArray(10, 1, 300, false);
                int[] ans1 = ArrayUtil.clone(nums);
                int[] ans2 = ArrayUtil.clone(nums);
                Solution solution = new Solution();
                solution.quickSort(ans1, 0, ans1.length - 1);
                Arrays.sort(ans2);
                boolean same = ArrayUtil.sameArr(ans1, ans2);
                System.out.println(same);
                assert same;
            }

        }

        @Test
        public void testQuickSort2() {
            int[] nums = new int[] {167, 193, 216, 59, 108, 252, 118, 57, 28, 98};
            int[] ans1 = ArrayUtil.clone(nums);
            int[] ans2 = ArrayUtil.clone(nums);
            Solution solution = new Solution();
            solution.quickSort(ans1, 0, ans1.length - 1);
            Arrays.sort(ans2);
            boolean same = ArrayUtil.sameArr(ans1, ans2);
            System.out.println(same);
        }

        // 示例 1：
        //
        //输入：nums = [-1,0,1,2,-1,-4]
        //输出：[[-1,-1,2],[-1,0,1]]

        @Test
        public void testThreeSum1() {
            int[] nums = new int[] {-1, 0, 1, 2, -1, -4};
            Solution solution = new Solution();
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
            Solution solution = new Solution();
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
            Solution solution = new Solution();
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
            Solution solution = new Solution();
            List<List<Integer>> ans = solution.threeSum(nums);
            System.out.println(ans);
        }

    }

}

//leetcode submit region end(Prohibit modification and deletion)

