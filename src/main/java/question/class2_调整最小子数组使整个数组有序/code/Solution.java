package question.class2_调整最小子数组使整个数组有序.code;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import util.ArrayUtil;

/**
 * @author by catface
 * @date 2021/5/24 11:40 上午
 */
@Slf4j
public class Solution {

    public int getMinLength(int[] arr) {
        int leftIndex = findLefIndex(arr);
        int i = arr.length - 1;
        while (i > leftIndex && leftIndex >= 0) {
            if (arr[i] < arr[leftIndex]) {
                leftIndex--;
                continue;
            }
            i--;
        }
        int rightIndex = findRightIndex(arr);
        i = 0;
        while (i < rightIndex && rightIndex < arr.length) {
            if (arr[i] > arr[rightIndex]) {
                rightIndex++;
                continue;
            }
            i++;
        }
        if (leftIndex < rightIndex) {
            return rightIndex - leftIndex - 1;
        }
        return 0;
    }

    private int findLefIndex(int[] arr) {
        int leftIndex = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] <= arr[i + 1]) {
                leftIndex = i + 1;
            } else {
                break;
            }
        }
        return leftIndex;
    }

    private int findRightIndex(int[] arr) {
        int rightIndex = arr.length - 1;
        for (int i = arr.length - 1; i >= 1; i--) {
            if (arr[i] >= arr[i - 1]) {
                rightIndex = i - 1;
            } else {
                break;
            }
        }
        return rightIndex;
    }

    public static class TestClass {
        @Test
        public void test() {
            int[] arr = {1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19};
            Solution solution = new Solution();
            int ans = solution.getMinLength(arr);
            System.out.println(ans);
        }

        @Test
        public void test2() {
            int[] arr = {1, 2, 3, 4, 5, 5, 6, 9, 9, 10, 11, 12};
            Solution solution = new Solution();
            int ans = solution.getMinLength(arr);
            System.out.println(ans);
            int ans2 = Code06_MinLengthForSort.getMinLength(arr);
            System.out.println(ans2);
        }

        @Test
        public void testForce() {
            Solution solution = new Solution();
            int[] arr = {1, 2, 3, 4, 5, 0, 0, 0, 9, 10, 11, 12};
            for (int i = 0; i < 1000; i++) {
                int[] arr2 = ArrayUtil.randomIntArray(3, 1, 12);
                int[] arr1 = ArrayUtil.randomIntArray(1,1,4);
                int[] arr3 = ArrayUtil.randomIntArray(1,7,12);
                System.arraycopy(arr2, 0, arr, 5, 3);
                arr[0] = arr1[0];
                arr[arr.length-1] = arr3[0];
                int ans1 = solution.getMinLength(arr);
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
