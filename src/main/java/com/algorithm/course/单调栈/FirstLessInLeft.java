package com.algorithm.course.单调栈;

import java.util.Stack;

import com.algorithm.util.ArrayUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/26 10:41 上午
 */
@Slf4j
public class FirstLessInLeft {

    public int getFirstLessInLeftForce(int[] arr, int n) {
        int ans = -1;
        for (int i = n - 1; i >= 0; i--) {
            if (arr[i] < arr[n]) {
                ans = arr[i];
                break;
            }
        }
        return ans;
    }

    public int getFirstLessInLeft(int[] arr, int n) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i <= n; i++) {
            while (!stack.isEmpty() && stack.peek() >= arr[i]) {
                stack.pop();
            }
            stack.push(arr[i]);
        }
        stack.pop();
        if (stack.isEmpty()) {
            return -1;
        }
        return stack.peek();
    }

    public int[] getFirstLessInLeft(int[] arr) {
        int[] ans = new int[arr.length];
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> stackValue = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                stack.pop();
                stackValue.pop();
            }
            if (stack.isEmpty()) {
                ans[i] = -1;
            } else {
                ans[i] = stack.peek();
            }
            stack.push(i);
            stackValue.push(arr[i]);
        }
        return ans;
    }

    public static class TestClass {
        @Test
        public void test() {
            int[] arr = ArrayUtil.randomIntArray(10, 1, 40);
            int n = 5;
            FirstLessInLeft firstLessInLeft = new FirstLessInLeft();
            int ans1 = firstLessInLeft.getFirstLessInLeft(arr, n);
            int ans2 = firstLessInLeft.getFirstLessInLeftForce(arr, n);
            log.info("arr:{},n:{}:value:{},ans1:{},ans2:{}", arr, n, arr[n], ans1, ans2);
        }

        @Test
        public void test2() {
            for (int i = 0; i < 1000; i++) {
                int[] arr = ArrayUtil.randomIntArray(10, 1, 40);
                int n = 5;
                FirstLessInLeft firstLessInLeft = new FirstLessInLeft();
                int ans1 = firstLessInLeft.getFirstLessInLeft(arr, n);
                int ans2 = firstLessInLeft.getFirstLessInLeftForce(arr, n);
                if (ans1 != ans2) {
                    log.info("arr:{},n:{}:value:{},ans1:{},ans2:{}", arr, n, arr[n], ans1, ans2);
                }
                assert ans1 == ans2;
            }
        }

        @Test
        public void test3() {
            int[] arr = ArrayUtil.randomIntArray(10, 1, 40);
            FirstLessInLeft firstLessInLeft = new FirstLessInLeft();
            int[] ans = firstLessInLeft.getFirstLessInLeft(arr);
            log.info("arr:{},ans:{}", arr, ans);
        }

    }
}
