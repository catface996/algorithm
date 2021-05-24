package question.class2_调整最小子数组使整个数组有序.code;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import util.ArrayUtil;

/**
 * @author by catface
 * @date 2021/5/24 3:09 下午
 */
@Slf4j
public class Solution3 {

    public int getLeftIndex(int[] arr) {
        boolean sorted = true;
        int maxIndex = 0;
        int i = 1;
        while (i < arr.length && maxIndex >= 0) {
            if (arr[i] < arr[maxIndex]) {
                sorted = false;
                maxIndex--;
                continue;
            }
            if (sorted && arr[i] >= arr[maxIndex]) {
                maxIndex = i;
            }
            i++;
        }
        return maxIndex;
    }

    public int getRightIndex(int[] arr) {
        boolean sorted = true;
        int minIndex = arr.length - 1;
        int i = arr.length - 2;
        while (i >= 0 && minIndex < arr.length) {
            if (arr[i] > arr[minIndex]) {
                sorted = false;
                minIndex++;
                continue;
            }
            if (sorted && arr[i] <= arr[minIndex]) {
                minIndex = i;
            }
            i--;
        }
        return minIndex;
    }

    public int getMinLength(int[] arr) {
        int leftIndex = getLeftIndex(arr);
        if (leftIndex >= arr.length - 1) {
            return 0;
        }
        int rightIndex = getRightIndex(arr);
        return rightIndex - leftIndex - 1;
    }

    public static class TestClass {
        @Test
        public void test() {
            int[] arr = {1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19};
            Solution3 solution3 = new Solution3();
            int ans = solution3.getMinLength(arr);
            System.out.println(ans);
        }

        @Test
        public void test1() {
            int[] arr = {1, 2, 3, 4, 5, 6, 6, 9, 9, 10, 11, 11};
            Solution3 solution3 = new Solution3();
            int ans = solution3.getMinLength(arr);
            System.out.println(ans);
        }

        @Test
        public void testForce() {
            Solution3 solution3 = new Solution3();
            int[] arr = {1, 2, 3, 4, 5, 0, 0, 0, 9, 10, 11, 12};
            for (int i = 0; i < 1000; i++) {
                int[] arr2 = ArrayUtil.randomIntArray(3, 1, 12);
                int[] arr1 = ArrayUtil.randomIntArray(1, 1, 4);
                int[] arr3 = ArrayUtil.randomIntArray(1, 7, 12);
                System.arraycopy(arr2, 0, arr, 5, 3);
                arr[0] = arr1[0];
                arr[arr.length - 1] = arr3[0];
                int ans1 = solution3.getMinLength(arr);
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
