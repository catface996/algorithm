package course.单调栈.子数组最小值累加和;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import util.ArrayUtil;

/**
 * @author by catface
 * @date 2021/4/27 3:33 下午
 */
@Slf4j
public class Solution1 {

    //给定一个数组arr，
    //返回所有子数组最小值的累加和

    MyStack stack;



    public int sumSubarrayMins(int[] arr) {
        stack = new MyStack(arr.length);
        int[] left = nearLessEqualLeft(arr);
        int[] right = nearLessRight(arr);
        long ans = 0;
        for (int i = 0; i < arr.length; i++) {
            long start = i - left[i];
            long end = right[i] - i;
            ans += start * end * (long)arr[i];
            ans %= 1000000007;
        }
        return (int)ans;
    }

    public int[] nearLessEqualLeft(int[] arr) {
        int N = arr.length;
        int[] left = new int[N];
        for (int i = N - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[i] <= arr[stack.peek()]) {
                left[stack.pop()] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            left[stack.pop()] = -1;
        }
        return left;
    }

    public int[] nearLessRight(int[] arr) {
        int N = arr.length;
        int[] right = new int[N];
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                right[stack.pop()] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            right[stack.pop()] = N;
        }
        return right;
    }

    public static class MyStack {
        int[] arr;
        int top;

        public MyStack(int size) {
            this.arr = new int[size];
            top = -1;
        }

        public int pop() {
            return arr[top--];
        }

        public int peek() {
            return arr[top];
        }

        public void push(int value) {
            arr[++top] = value;
        }

        public boolean isEmpty() {
            return top == -1;
        }
    }

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

    public static class TestClass {
        @Test
        public void test1() {
            int[] arr = ArrayUtil.randomIntArray(10, 1, 40);
            Solution1 solution = new Solution1();
            int ans1 = solution.sumSubarrayMins(arr);
            int ans2 = solution.getMinNumSumForce(arr);
            log.info("arr:{},ans1:{},ans2:{}", arr, ans1, ans2);
        }

        @Test
        public void test2() {
            int[] arr = {29, 19, 19, 28, 3, 37, 31, 22, 31, 39};
            Solution1 solution1 = new Solution1();
            int ans1 = solution1.sumSubarrayMins(arr);
            int ans2 = solution1.getMinNumSumForce(arr);
            int[] leftRange = solution1.nearLessEqualLeft(arr);
            int[] rightRange = solution1.nearLessRight(arr);
            Solution solution = new Solution();
            int[][] range = solution.nearLess(arr);
            log.info("arr:{},ans1:{},ans2:{}", arr, ans1, ans2);
        }

        @Test
        public void test3() {
            for (int i = 0; i < 1000; i++) {
                int[] arr = ArrayUtil.randomIntArray(10, 1, 40);
                Solution1 solution = new Solution1();
                int ans1 = solution.sumSubarrayMins(arr);
                int ans2 = solution.getMinNumSumForce(arr);
                if (ans1 != ans2) {
                    log.info("arr:{},ans1:{},ans2:{}", arr, ans1, ans2);
                }
                assert ans1 == ans2;
            }
        }
    }
}
