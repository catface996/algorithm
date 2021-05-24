package question.class2_调整最小子数组使整个数组有序.code;

import java.util.Stack;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import util.ArrayUtil;

/**
 * @author by catface
 * @date 2021/5/24 2:31 下午
 */
@Slf4j
public class Solution2 {

    public int getMinLength(int[] arr) {
        int leftIndex = getLeftIndex(arr);
        int rightIndex = getRightIndex(arr);
        if (leftIndex < rightIndex) {
            return rightIndex - leftIndex - 1;
        }
        return 0;
    }

    private int getLeftIndex(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        boolean sorted = true;
        stack.push(0);
        int i = 1;
        while (!stack.isEmpty() && i < arr.length) {
            if (arr[i] < arr[stack.peek()]) {
                sorted = false;
                stack.pop();
                continue;
            }
            if (sorted && arr[i] >= arr[stack.peek()]) {
                stack.push(i);
            }
            i++;
        }
        return stack.isEmpty() ? 0 : stack.peek();
    }

    private int getRightIndex(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        boolean sorted = true;
        stack.push(arr.length - 1);
        int i = arr.length - 2;
        while (i >= 0 && !stack.isEmpty()) {
            if (arr[i] > arr[stack.peek()]) {
                sorted = false;
                stack.pop();
                continue;
            }
            if (sorted && arr[i] <= arr[stack.peek()]) {
                stack.push(i);
            }
            i--;
        }
        return stack.isEmpty() ? arr.length - 1 : stack.peek();
    }

    public static class TestClass {
        @Test
        public void test() {
            int[] arr = {1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19};
            Solution2 solution2 = new Solution2();
            int leftIndex = solution2.getLeftIndex(arr);
            System.out.println(leftIndex);
            int rightIndex = solution2.getRightIndex(arr);
            System.out.println(rightIndex);

            int ans = solution2.getMinLength(arr);
            System.out.println(ans);
        }

        @Test
        public void testForce() {
            Solution2 solution2 = new Solution2();
            int[] arr = {1, 2, 3, 4, 5, 0, 0, 0, 9, 10, 11, 12};
            for (int i = 0; i < 1000; i++) {
                int[] arr2 = ArrayUtil.randomIntArray(3, 1, 12);
                int[] arr1 = ArrayUtil.randomIntArray(1, 1, 4);
                int[] arr3 = ArrayUtil.randomIntArray(1, 7, 12);
                System.arraycopy(arr2, 0, arr, 5, 3);
                arr[0] = arr1[0];
                arr[arr.length - 1] = arr3[0];
                int ans1 = solution2.getMinLength(arr);
                int ans2 = Code06_MinLengthForSort.getMinLength(arr);
                if (ans1 != ans2) {
                    log.info("arr:{},ans1:{},ans2:{}", arr, ans1, ans2);
                    assert false;
                }
            }
            log.info("Good,you success.");
        }

    }
}
