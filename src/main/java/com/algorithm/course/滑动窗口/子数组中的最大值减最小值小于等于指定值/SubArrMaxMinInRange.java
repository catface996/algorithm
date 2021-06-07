package com.algorithm.course.滑动窗口.子数组中的最大值减最小值小于等于指定值;

import java.util.LinkedList;

import com.algorithm.util.ArrayUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/22 2:05 下午
 */
@Slf4j
public class SubArrMaxMinInRange {

    //给定一个整型数组arr，和一个整数num
    //某个arr中的子数组sub，如果想达标，必须满足：
    //sub中最大值 – sub中最小值 <= num，
    //返回arr中达标子数组的数量

    // 暴力的对数器方法
    public int right(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum < 0) {
            return 0;
        }
        int N = arr.length;
        int count = 0;
        for (int L = 0; L < N; L++) {
            for (int R = L; R < N; R++) {
                int max = arr[L];
                int min = arr[L];
                for (int i = L + 1; i <= R; i++) {
                    max = Math.max(max, arr[i]);
                    min = Math.min(min, arr[i]);
                }
                if (max - min <= sum) {
                    count++;
                }
            }
        }
        return count;
    }

    public int num(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum < 0) {
            return 0;
        }
        int N = arr.length;
        int count = 0;
        LinkedList<Integer> maxWindow = new LinkedList<>();
        LinkedList<Integer> minWindow = new LinkedList<>();
        int R = 0;
        for (int L = 0; L < N; L++) {
            while (R < N) {
                while (!maxWindow.isEmpty() && arr[maxWindow.peekLast()] <= arr[R]) {
                    maxWindow.pollLast();
                }
                maxWindow.addLast(R);
                while (!minWindow.isEmpty() && arr[minWindow.peekLast()] >= arr[R]) {
                    minWindow.pollLast();
                }
                minWindow.addLast(R);
                // 返现最大值减最小值不满足条件时
                if (arr[maxWindow.peekFirst()] - arr[minWindow.peekFirst()] > sum) {
                    break;
                } else {
                    R++;
                }
            }
            count += R - L;
            if (maxWindow.peekFirst() == L) {
                maxWindow.pollFirst();
            }
            if (minWindow.peekFirst() == L) {
                minWindow.pollFirst();
            }
        }
        return count;
    }

    public int getSubArrInRangeForce(int[] arr, int num) {
        if (arr == null || arr.length <= 0 || num < 0) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int max = Integer.MIN_VALUE;
                int min = Integer.MAX_VALUE;
                for (int k = i; k <= j; k++) {
                    max = Math.max(max, arr[k]);
                    min = Math.min(min, arr[k]);
                }
                if (max - min <= num) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public int getSubArrInRange(int[] arr, int num) {
        if (arr == null || arr.length <= 0 || num < 0) {
            return 0;
        }
        int length = arr.length;
        int count = 0;
        LinkedList<Integer> maxWindow = new LinkedList<>();
        LinkedList<Integer> minWindow = new LinkedList<>();
        int rightIndex = 0;
        for (int leftIndex = 0; leftIndex < length; leftIndex++) {
            while (rightIndex < length) {
                while (!maxWindow.isEmpty() && arr[maxWindow.peekLast()] <= arr[rightIndex]) {
                    maxWindow.pollLast();
                }
                maxWindow.addLast(rightIndex);
                while (!minWindow.isEmpty() && arr[minWindow.peekLast()] >= arr[rightIndex]) {
                    minWindow.pollLast();
                }
                minWindow.addLast(rightIndex);
                if (arr[maxWindow.peekFirst()] - arr[minWindow.peekFirst()] > num) {
                    break;
                } else {
                    rightIndex++;
                }
            }
            count += (rightIndex - leftIndex);
            if (leftIndex == maxWindow.peekFirst()) {
                maxWindow.pollFirst();
            }
            if (leftIndex == minWindow.peekFirst()) {
                minWindow.pollFirst();
            }
        }
        return count;
    }

    public static class TestClass {
        @Test
        public void test1() {
            for (int i = 0; i < 1000; i++) {
                int[] arr = ArrayUtil.randomIntArray(10, 1, 40);
                SubArrMaxMinInRange subArrMaxMinInRange = new SubArrMaxMinInRange();
                int ans1 = subArrMaxMinInRange.right(arr, 10);
                int ans2 = subArrMaxMinInRange.num(arr, 10);
                if (ans1 != ans2) {
                    log.info("arr:{},ans1:{},ans2:{}", arr, ans1, ans2);
                }
                assert ans1 == ans2;
            }
        }

        @Test
        public void test2() {
            int[] arr = {39, 34, 14, 10, 6, 12, 8, 11, 33, 4};
            int num = 10;
            SubArrMaxMinInRange subArrMaxMinInRange = new SubArrMaxMinInRange();
            int ans1 = subArrMaxMinInRange.getSubArrInRange(arr, num);
            int ans2 = subArrMaxMinInRange.right(arr, num);
            log.info("ans1:{},ans2:{}", ans1, ans2);
        }
    }
}
