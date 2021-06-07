package com.algorithm.leetcode.排序.P977有序数组的平方;
//给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
//
//
//
//
//
//
// 示例 1：
//
//
//输入：nums = [-4,-1,0,3,10]
//输出：[0,1,9,16,100]
//解释：平方后，数组变为 [16,1,0,9,100]
//排序后，数组变为 [0,1,9,16,100]
//
// 示例 2：
//
//
//输入：nums = [-7,-3,2,3,11]
//输出：[4,9,9,49,121]
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 104
// -104 <= nums[i] <= 104
// nums 已按 非递减顺序 排序
//
//
//
//
// 进阶：
//
//
// 请你设计时间复杂度为 O(n) 的算法解决本问题
//
// Related Topics 数组 双指针
// 👍 237 👎 0

import java.util.Arrays;

import com.algorithm.util.ArrayUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
@Slf4j
public class Solution {

    public int[] sortedSquares(int[] nums) {
        int[] ans = new int[nums.length];
        int l = 0;
        int r = nums.length - 1;
        int ansIndex = 0;
        int absL;
        int absR;
        while (l <= r) {
            absL = Math.abs(nums[l]);
            absR = Math.abs(nums[r]);
            if (absL > absR) {
                ans[ansIndex++] = absL * absL;
                l++;
            } else if (absL == absR) {
                ans[ansIndex++] = absL * absL;
                if (r == l) {
                    break;
                } else {
                    ans[ansIndex++] = absL * absL;
                    l++;
                    r--;
                }
            } else {
                ans[ansIndex++] = absR * absR;
                r--;
            }
        }
        int[] realAns = new int[ansIndex];
        for (int i = 0; i < realAns.length; i++) {
            realAns[i] = ans[ansIndex - i - 1];
        }
        return realAns;
    }

    public static class TestClass {

        @Test
        public void test1() {
            Solution solution = new Solution();
            int[] arr = ArrayUtil.randomIntArray(10, 1, 10, true);
            Arrays.sort(arr);
            int[] ans = solution.sortedSquares(arr);
            log.info("ans:{}", ans);
        }

        // 示例 1：
        //
        //
        //输入：nums = [-4,-1,0,3,10]
        //输出：[0,1,9,16,100]
        //解释：平方后，数组变为 [16,1,0,9,100]
        //排序后，数组变为 [0,1,9,16,100]
        @Test
        public void test2() {
            int[] arr = {-4, -1, 0, 3, 10};
            Solution solution = new Solution();
            int[] ans = solution.sortedSquares(arr);
            log.info("ans:{}", ans);
        }

        // 示例 2：
        //
        //
        //输入：nums = [-7,-3,2,3,11]
        //输出：[4,9,9,49,121]
        @Test
        public void test3() {
            int[] arr = {-7, -3, 2, 3, 11};
            Solution solution = new Solution();
            int[] ans = solution.sortedSquares(arr);
            log.info("ans:{}", ans);
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)

