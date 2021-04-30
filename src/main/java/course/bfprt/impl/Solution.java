package course.bfprt.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import util.ArrayUtil;

/**
 * @author by catface
 * @date 2021/4/29 5:58 下午
 */
@Slf4j
public class Solution {

    private void quickWithBfprt(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int pivot = bfprt(arr, l, r);
        int left = l - 1;
        int right = r + 1;
        while (left < right) {
            do {
                left++;
            } while (arr[left] < pivot);
            do {
                right--;
            } while (arr[right] > pivot);
            if (left < right) {
                swap(arr, left, right);
            }
            quickWithBfprt(arr, l, right - 1);
            quickWithBfprt(arr, right + 1, r);
        }
    }

    public void quickSort1(int[] nums, int l, int r) {
        if (l >= r) { return; }
        int i = l - 1, j = r + 1, p = nums[l + ((r - l) >> 1)];
        while (i < j) {
            do { i++; } while (nums[i] < p);
            do { j--; } while (nums[j] > p);
            if (i < j) {
                nums[i] = nums[i] ^ nums[j];
                nums[j] = nums[i] ^ nums[j];
                nums[i] = nums[i] ^ nums[j];
            }
            quickSort1(nums, l, j - 1);
            quickSort1(nums, j + 1, r);
        }
    }

    private int bfprt(int[] arr, int l, int r) {
        if (l == r) {
            return arr[l];
        }
        if (r - l + 1 <= 5) {
            return getMedian(arr, l, r);
        }
        int round = (r - l + 1) / 5;
        if ((r - l + 1) % 5 > 0) {
            round++;
        }
        int[] newArr = new int[round];
        for (int group = 0; group < round; group++) {
            newArr[group] = getMedian(arr, l + group * 5, Math.min(r, l + (group + 1) * 5 - 1));
        }
        return bfprt(newArr, 0, newArr.length - 1);
    }

    private int getMedian(int[] sourceArr, int start, int end) {
        int[] tempArr = new int[end - start + 1];
        int size = 0;
        for (int i = start; i <= end; i++) {
            tempArr[size++] = sourceArr[i];
            for (int j = size - 1; j > 0; j--) {
                if (tempArr[j] < tempArr[j - 1]) {
                    swap(tempArr, j - 1, j);
                } else {
                    break;
                }
            }
        }
        return tempArr[size / 2];
    }

    private void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static class TestClass {
        @Test
        public void test1() {
            int[] arr = ArrayUtil.randomIntArray(10, 1, 40);
            Solution solution = new Solution();
            int ans = solution.bfprt(arr, 0, arr.length - 1);
            log.info("bfprt ans:{}", ans);
        }

        @Test
        public void test2() {
            int[] arr = ArrayUtil.randomIntArray(10, 1, 40);
            Solution solution = new Solution();
            log.info("sourceArr:{}", arr);
            solution.quickWithBfprt(arr, 0, arr.length - 1);
            log.info("resultArr:{}", arr);
        }

        @Test
        public void test3() {
            int[] arr = {9, 27, 26, 2, 36, 4, 4, 24, 2, 15};
            int[] arr2 = ArrayUtil.clone(arr);
            Solution solution = new Solution();
            log.info("sourceArr:{}", arr);
            solution.quickSort1(arr, 0, arr.length - 1);
            log.info("resultArr:{}", arr);
            solution.quickWithBfprt(arr2, 0, arr2.length - 1);
            log.info("resultArr2:{}", arr2);
        }
    }

}
