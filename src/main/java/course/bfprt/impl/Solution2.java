package course.bfprt.impl;

import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import util.ArrayUtil;

/**
 * @author by catface
 * @date 2021/4/30 2:28 下午
 */
@Slf4j
public class Solution2 {

    int[] fiveArr = new int[5];
    private void quickSort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int pivot = getPivotWithBfprt(arr, l, r);
        int less = l - 1;
        int more = r + 1;
        int left = l;
        while (left < more) {
            if (arr[left] < pivot) {
                swap(arr, left++, ++less);
                continue;
            }
            if (arr[left] == pivot) {
                left++;
                continue;
            }
            if (arr[left] > pivot) {
                swap(arr, left, --more);
            }
        }
        quickSort(arr, l, less);
        quickSort(arr, more, r);
    }

    private void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    private int getPivotWithBfprt(int[] arr, int left, int right) {
        if (right - left < 5) {
            return geMedium(arr,left,right);
        }
        int groupCount = (right - left + 1) / 5;
        if ((right - left + 1) % 5 > 0) {
            groupCount++;
        }
        int[] newArr = new int[groupCount];
        for (int group = 0; group < groupCount; group++) {
            newArr[group] = geMedium(arr, left + group * 5, Math.min(left + group * 5 + 5 - 1, right));
        }
        return getPivotWithBfprt(newArr, 0, newArr.length - 1);
    }

    private int geMedium(int[] arr, int l, int r) {
        int size = 0;
        for (int i = l; i <= r; i++) {
            fiveArr[size] = arr[i];
            int cur = size;
            while (cur >= 1 && fiveArr[cur] < fiveArr[cur - 1]) {
                swap(fiveArr, cur, cur - 1);
                cur--;
            }
            size++;
        }
        return fiveArr[size / 2];
    }

    public static class TestClass {
        @Test
        public void test1() {
            int[] arr = ArrayUtil.randomIntArray(10, 1, 40);
            log.info("source:{}", arr);
            Solution2 solution2 = new Solution2();
            solution2.quickSort(arr, 0, arr.length - 1);
            log.info("result:{}", arr);
        }

        @Test
        public void test2() {
            Solution2 solution2 = new Solution2();
            for (int i = 0; i < 10000; i++) {
                int[] source = ArrayUtil.randomIntArray(10, 1, 40);
                int[] arr1 = ArrayUtil.clone(source);
                int[] arr2 = ArrayUtil.clone(source);
                solution2.quickSort(arr1, 0, arr1.length - 1);
                Arrays.sort(arr2);
                for (int j = 0; j < arr1.length; j++) {
                    assert arr1[j] == arr2[j];
                }
            }
        }
    }
}
