package com.algorithm.leetcode.分治.P1755最接近目标值的子序列和;

import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/5/26 8:18 下午
 */
@Slf4j
public class Solution3 {

    // 9:04 下午	info
    //				解答成功:
    //				执行耗时:389 ms,击败了61.54% 的Java用户
    //				内存消耗:50.5 MB,击败了38.98% 的Java用户

    int[] left = new int[1 << 20];
    int[] right = new int[1 << 20];
    int[] nums;

    public int minAbsDifference(int[] nums, int goal) {
        this.nums = nums;
        int mid = nums.length >> 1;
        // 0到mid-1
        int lSize = process(0, mid, 0, 0, left);
        // mid到nums.length-1
        int rSize = process(mid, nums.length, 0, 0, right);
        // 排序时,末尾的0均保留,用于覆盖一个元素都不取的情况,即子序列是空
        Arrays.sort(left, 0, lSize);
        Arrays.sort(right, 0, rSize);
        int rightMax = rSize - 1;
        int ans = Math.abs(goal);
        int rest;
        for (int i = 0; i < lSize; i++) {
            rest = goal - left[i];
            // 因为right和left中都有子序列和是0的情况,所以当left取0,表示只在right中寻找
            // 相应的,right中取0,表示只在left中取
            // 因为左右两侧都排序了,rest 只会越来越小,i只会递增
            // rest越小,减去越小的,才会越接近0,所以rightMax不回退.
            while (rightMax > 0 && Math.abs(rest - right[rightMax - 1]) <= Math.abs(rest - right[rightMax])) {
                rightMax--;
            }
            ans = Math.min(ans, Math.abs(rest - right[rightMax]));
        }
        return ans;
    }

    private int process(int index, int end, int sum, int fill, int[] sums) {
        if (index == end) {
            sums[fill++] = sum;
        } else {
            fill = process(index + 1, end, sum + nums[index], fill, sums);
            fill = process(index + 1, end, sum, fill, sums);
        }
        return fill;
    }

    public static class TestClass {
        // 5:18 下午	info
        //				运行成功:
        //				测试用例:[5,-7,3,5]
        //				6
        //				测试结果:1
        //				期望结果:0
        //				stdout:
        @Test
        public void test1() {
            int[] arr = {5, -7, 3, 5};
            int goal = 6;
            Solution3 solution = new Solution3();
            int ans = solution.minAbsDifference(arr, goal);
            int ans2 = Code06_ClosestSubsequenceSum.minAbsDifference(arr, goal);
            log.info("arr:{},ans1:{},ans2:{}", arr, ans, ans2);
        }

        // 5:32 下午	info
        //				解答失败:
        //				测试用例:[7,-9,15,-2]
        //				-5
        //				测试结果:3
        //				期望结果:1
        //				stdout:
        @Test
        public void test2() {
            int[] arr = {7, -9, 15, -2};
            int goal = -5;
            Solution3 solution = new Solution3();
            int ans = solution.minAbsDifference(arr, goal);
            int ans2 = Code06_ClosestSubsequenceSum.minAbsDifference(arr, goal);
            log.info("arr:{},ans1:{},ans2:{}", arr, ans, ans2);
        }
    }
}
