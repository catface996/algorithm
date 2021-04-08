package pratice.zuo.other;

import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import pratice.util.ArrayUtil;

/**
 * 快速排序
 *
 * @author by catface
 * @date 2021/2/20 2:49 下午
 */
@Slf4j
public class QuickSort {

    /**
     * 容易理解的快速排序
     *
     * @param nums 原始数组
     * @param l    左侧下标
     * @param r    右侧下标
     */
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

    /**
     * 特别难懂的算法
     *
     * @param nums 原始数组
     * @param l    左侧下标
     * @param r    右侧下标
     */
    void quickSort2(int[] nums, int l, int r) {
        if (l >= r) { return; }
        int i = l, j = r;
        int tmp = nums[l];
        while (i < j) {
            while (nums[j] >= nums[l] && i < j) { j--; }
            while (nums[i] <= nums[l] && i < j) { i++; }
            tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
        nums[i] = nums[l];
        nums[l] = tmp;
        quickSort2(nums, l, i - 1);
        quickSort2(nums, i + 1, r);
    }

    /**
     * 使用荷兰国旗分区做快排
     * <p>
     * 对左分区迭代时,只需要从l到less即可
     * <p>
     * 对右侧分区迭代,只需要从more到r即可
     *
     * @param nums 待排序数组
     * @param l    左侧下标
     * @param r    右侧下标
     */
    public void quickSort3(int[] nums, int l, int r) {
        if (l >= r) {
            return;
        }
        int less = l - 1, more = r + 1, cur = l, p = nums[l + ((r - l) >> 1)];
        while (cur < more) {
            if (nums[cur] < p) {
                less++;
                swap(nums, less, cur);
                cur++;
                continue;
            }
            if (nums[cur] > p) {
                more--;
                swap(nums, cur, more);
                continue;
            }
            if (nums[cur] == p) {
                cur++;
            }
        }
        quickSort3(nums, l, less);
        quickSort1(nums, more, r);
    }

    private void swap(int[] nums, int i, int j) {
        if (i == j) {
            return;
        }
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }

    @Test
    public void testRound() {
        long quick1 = 0;
        long quick2 = 0;
        long quick3 = 0;
        for (int size = 1; size < 1000; size++) {
            for (int i = 0; i < 1000; i++) {
                int[] originalArray = ArrayUtil.randomIntArray(size, 1, 1000);

                int[] quickSort1 = Arrays.copyOf(originalArray, originalArray.length);
                long start1 = System.currentTimeMillis();
                quickSort1(quickSort1, 0, quickSort1.length - 1);
                long end1 = System.currentTimeMillis();
                quick1 += (end1 - start1);

                int[] quickSort2 = Arrays.copyOf(originalArray, originalArray.length);
                long start2 = System.currentTimeMillis();
                quickSort2(quickSort2, 0, quickSort2.length - 1);
                long end2 = System.currentTimeMillis();
                quick2 += (end2 - start2);

                int[] quickSort3 = Arrays.copyOf(originalArray, originalArray.length);
                long start3 = System.currentTimeMillis();
                quickSort3(quickSort3, 0, quickSort3.length - 1);
                long end3 = System.currentTimeMillis();
                quick3 += (end3 - start3);

            }
        }
        System.out.println(quick1);
        System.out.println(quick2);
        System.out.println(quick3);
    }

    //@Test
    //public void test() {
    //    int passCount = 0;
    //    for (int i = 1; i < 100; i++) {
    //        for (int j = 0; j < 100; j++) {
    //            int[] sourceArray = ArrayUtil.randomIntArray(i, 1, 100);
    //            int[] originalArray = ArrayUtil.clone(sourceArray);
    //            // quickSort3(originalArray, 0, originalArray.length - 1);
    //            int[] bobbleSortResult = BubbleSort.bubbleSort(sourceArray);
    //            boolean checkResult = ArrayUtil.checkSortResult(originalArray, bobbleSortResult);
    //            if (!checkResult) {
    //                log.info("原始数据:{}", sourceArray);
    //                log.info("结果数据:{}", originalArray);
    //            } else {
    //                passCount++;
    //            }
    //        }
    //    }
    //    log.info("比对通过次数:{}", passCount);
    //
    //}
    //
    @Test
    public void test3() {
        int[] nums = new int[] {48, 70, 1, 1, 2, 3, 4, 4, 3, 2, 2, 73, 66};
        quickSort3(nums, 0, nums.length - 1);
        System.out.println(nums);
    }

}
