package com.algorithm.course.单调栈.子数组最小值累加和;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.algorithm.util.ArrayUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/27 11:50 上午
 */
@Slf4j
public class Solution {

    //给定一个数组arr，
    //返回所有子数组最小值的累加和

    public int getMinNumSumForce(int[] arr) {
        int sum = 0;
        for (int end = 0; end < arr.length; end++) {
            for (int start = 0; start <= end; start++) {
                int min = Integer.MAX_VALUE;
                for (int i = start; i <= end; i++) {
                    min = Math.min(min, arr[i]);
                }
                sum += min;
            }
        }
        return sum;
    }

    public int subArrayMinSum1(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int min = arr[i];
                for (int k = i + 1; k <= j; k++) {
                    min = Math.min(min, arr[k]);
                }
                ans += min;
            }
        }
        return ans;
    }

    //TODO 此种算法无法解决重复值的问题

    public int subArrMinSum(int[] arr) {
        int ans = 0;
        int[][] sumArrRange = nearLess(arr);
        for (int i = 0; i < sumArrRange.length; i++) {
            if (i < arr.length - 1 && arr[i] == arr[i + 1]) {
                continue;
            }
            int[] range = sumArrRange[i];
            int start = i - range[0];
            int end = range[1] - i;
            ans += start * end * arr[i];
        }
        return ans;
    }

    public int[][] nearLess(int[] arr) {
        int[][] sumArrRange = new int[arr.length][2];
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[i] < arr[stack.peek().get(0)]) {
                List<Integer> popIndexList = stack.pop();
                int leftIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                for (int biggerIndex : popIndexList) {
                    sumArrRange[biggerIndex][0] = leftIndex;
                    sumArrRange[biggerIndex][1] = i;
                }
            }
            if (!stack.isEmpty() && arr[i] == arr[stack.peek().get(0)]) {
                stack.peek().add(i);
            } else {
                List<Integer> indexList = new ArrayList<>();
                indexList.add(i);
                stack.push(indexList);
            }
        }

        while (!stack.isEmpty()) {
            List<Integer> popIndexList = stack.pop();
            int leftIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
            for (int biggerIndex : popIndexList) {
                sumArrRange[biggerIndex][0] = leftIndex;
                sumArrRange[biggerIndex][1] = arr.length;
            }
        }
        return sumArrRange;
    }

    public static class TestClass {
        @Test
        public void test1() {
            int[] arr = ArrayUtil.randomIntArray(10, 1, 40);
            Solution solution = new Solution();
            int ans1 = solution.subArrMinSum(arr);
            int ans2 = solution.getMinNumSumForce(arr);
            log.info("arr:{},ans1:{},ans2:{}", arr, ans1, ans2);
        }

        @Test
        public void test2() {
            int[] arr = {29, 19, 19, 28, 3, 37, 31, 22, 31, 39};
            Solution solution = new Solution();
            int ans1 = solution.subArrMinSum(arr);
            int ans2 = solution.getMinNumSumForce(arr);
            log.info("arr:{},ans1:{},ans2:{}", arr, ans1, ans2);
        }

        @Test
        public void test3() {
            for (int i = 0; i < 1000; i++) {
                int[] arr = ArrayUtil.randomIntArray(10, 1, 40);
                Solution solution = new Solution();
                int ans1 = solution.subArrMinSum(arr);
                int ans2 = solution.getMinNumSumForce(arr);
                if (ans1 != ans2) {
                    log.info("arr:{},ans1:{},ans2:{}", arr, ans1, ans2);
                }
                assert ans1 == ans2;
            }
        }
    }
}
