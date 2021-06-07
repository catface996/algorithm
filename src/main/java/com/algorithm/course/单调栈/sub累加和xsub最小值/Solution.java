package com.algorithm.course.单调栈.sub累加和xsub最小值;

import java.util.Stack;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/26 1:20 下午
 */
@Slf4j
public class Solution {

    //给定一个只包含正数的数组arr，arr中任何一个子数组sub，
    //一定都可以算出(sub累加和 )* (sub中的最小值)是什么，
    //那么所有子数组中，这个值最大是多少？

    // 以i位置为最小值的子数组,因为只包含正数,所以只要求包含i为最小值的子数据的长度尽可能大
    // 题目就变成,求 左侧 距离位置最近且别i位置的值小的位置 m,右侧 距离i位置最近且比i位置小的的位置n,求(m,n)开区间的累加和 * arr[i]
    // (m,n) 范围即[m+1,n-1]范围的累加和 * arr[i]

    /**
     * 暴力解题
     *
     * @param arr 正整数数组
     * @return 符合标准的最大值
     */
    public int getMaxOfSubSumMultiplySubMinForce(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int end = 0; end < arr.length; end++) {
            for (int start = 0; start <= end; start++) {
                int sum = 0;
                int min = Integer.MAX_VALUE;
                for (int i = start; i <= end; i++) {
                    sum += arr[i];
                    min = Math.min(min, arr[i]);
                }
                max = Math.max(max, sum * min);
            }
        }
        return max;
    }

    public int getMaxOfSubSumMultiplySubMin(int[] arr) {
        int size = arr.length;
        int[] sum = new int[arr.length];
        sum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum[i] = arr[i] + sum[i - 1];
        }
        log.info("sums:{}", sum);
        int max = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> stackValue = new Stack<>();
        for (int i = 0; i < size; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                int j = stack.pop();
                int minValue = arr[j];
                stackValue.pop();
                // 如果栈空了,取sum[i-1],即第i-1项的累加和,子数组中只有i-1项
                int tempMax = (stack.isEmpty() ? sum[i - 1] : (sum[i - 1] - sum[stack.peek()])) * minValue;
                max = Math.max(max, tempMax);
            }
            stack.push(i);
            stackValue.push(arr[i]);
        }
        while (!stack.isEmpty()) {
            int j = stack.pop();
            stackValue.pop();
            int tempMax = (stack.isEmpty() ? sum[size - 1] : (sum[size - 1] - sum[stack.peek()])) * arr[j];
            max = Math.max(max, tempMax);
        }
        return max;
    }

    public int getMaxValue(int[] arr) {
        int[] sums = calculateSums(arr);
        int max = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[i] <= arr[stack.peek()]) {
                // 当待入栈的数值小于等于(等于也出栈,最后一个相等的值,一定能算对)栈顶数值,栈顶数值出栈
                int minValue = arr[stack.pop()];
                int rangeSum;
                if (stack.isEmpty()) {
                    rangeSum = sums[i - 1];
                } else {
                    // mIndex = stack.peek()  即出现下一最小值的位置,如果是mIndex = 0,rangeSum = sums[i-1],如果是其他值,rangeSum=sums[i-1]-sums[mIndex]
                    rangeSum = sums[i - 1] - sums[stack.peek()];
                }
                max = Math.max(max, minValue * rangeSum);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int minValue = arr[stack.pop()];
            int rangeSum;
            if (stack.isEmpty()) {
                rangeSum = sums[arr.length - 1];
            } else {
                rangeSum = sums[arr.length - 1] - sums[stack.peek()];
            }
            max = Math.max(max, minValue * rangeSum);
        }
        return max;
    }

    /**
     * 求前缀和
     *
     * @param arr 原始数组
     * @return 前缀和数组
     */
    private int[] calculateSums(int[] arr) {
        int[] sums = new int[arr.length];
        sums[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sums[i] = sums[i - 1] + arr[i];
        }
        return sums;
    }

    public static class TestClass {
        @Test
        public void test() {
            int[] arr = {30, 16, 13, 9, 10, 14, 23, 4, 25, 34};
            Solution solution = new Solution();
            int max = solution.getMaxOfSubSumMultiplySubMinForce(arr);
            int max2 = solution.getMaxValue(arr);
            log.info("arr:{},max:{},max2:{}", arr, max, max2);
        }
    }
}
