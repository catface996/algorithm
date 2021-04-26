package course.单调栈.sub累加和xsub最小值;

import java.util.Stack;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/26 1:20 下午
 */
@Slf4j
public class Solution {

    public static int max2(int[] arr) {
        int size = arr.length;
        int[] sums = new int[size];
        sums[0] = arr[0];
        for (int i = 1; i < size; i++) {
            sums[i] = sums[i - 1] + arr[i];
        }
        int max = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < size; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                int j = stack.pop();
                max = Math.max(max, (stack.isEmpty() ? sums[i - 1] : (sums[i - 1] - sums[stack.peek()])) * arr[j]);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int j = stack.pop();
            max = Math.max(max, (stack.isEmpty() ? sums[size - 1] : (sums[size - 1] - sums[stack.peek()])) * arr[j]);
        }
        return max;
    }

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

    public static class TestClass {
        @Test
        public void test() {
            int[] arr = {30, 16, 13, 9, 10, 14, 23, 4, 25, 34};
            Solution solution = new Solution();
            int max = solution.getMaxOfSubSumMultiplySubMinForce(arr);
            int max2 = solution.getMaxOfSubSumMultiplySubMin(arr);
            log.info("arr:{},max:{},max2:{}", arr, max, max2);
        }
    }
}
