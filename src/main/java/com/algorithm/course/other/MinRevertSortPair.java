package com.algorithm.course.other;

import com.algorithm.util.ArrayUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 最小逆序对
 * <p>
 * 设 A 为一个有 n 个数字的有序集 (n>1)，其中所有数字各不相同。
 * <p>
 * 如果存在正整数 i, j 使得 1 ≤ i < j ≤ n 而且 A[i] > A[j]，则 <A[i], A[j]> 这个有序对称为 A 的一个逆序对，也称作逆序数。
 *
 * @author by catface
 * @date 2021/2/19 10:37 上午
 */
@Slf4j
public class MinRevertSortPair {

    @Test
    public void testGetOnePair() {
        int passCount = 0;
        for (int size = 0; size < 100; size++) {
            for (int round = 0; round < 1000; round++) {
                int[] sourceArray = ArrayUtil.randomIntArray(4, 1, 100);
                int[] sourceArrayClone = ArrayUtil.clone(sourceArray);
                int foreachCount = foreachCountRevertSortPair(sourceArrayClone);
                int mergeCount = mergeCountRevertSortPair(sourceArrayClone, 0, sourceArrayClone.length - 1);
                boolean checkResult = foreachCount == mergeCount;
                if (!checkResult) {
                    log.info("逆序对数量,foreachCount:{},mergeCount:{},结果是否一致:{},原始数组:{}", foreachCount, mergeCount,
                        checkResult, sourceArray);
                } else {
                    passCount++;
                }
            }
        }
        log.info("通过次数:{}", passCount);
    }

    /**
     * 统计数组中出现的逆序对数量(for循环)
     *
     * @param sourceArray 原始数组
     * @return 逆序对的数量
     */
    public int foreachCountRevertSortPair(int[] sourceArray) {
        int count = 0;
        for (int i = 0; i < sourceArray.length; i++) {
            for (int j = i + 1; j < sourceArray.length; j++) {
                if (sourceArray[i] > sourceArray[j]) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 统计数组中出现的逆序对数量(归并)
     *
     * @param sourceArray 原始数组
     * @return 逆序对的数量
     */
    public int mergeCountRevertSortPair(int[] sourceArray, int leftIndex, int rightIndex) {
        if (leftIndex == rightIndex) {
            return 0;
        }
        int middleIndex = leftIndex + ((rightIndex - leftIndex) >> 1);
        int leftCount = mergeCountRevertSortPair(sourceArray, leftIndex, middleIndex);
        int rightCount = mergeCountRevertSortPair(sourceArray, middleIndex + 1, rightIndex);
        int mergeCount = mergeCount(sourceArray, leftIndex, middleIndex, rightIndex);
        return leftCount + rightCount + mergeCount;
    }

    /**
     * 合并左右两侧已排序的数组,并统计合并过程中发现的逆序对数量
     * <p>
     * 合并过程中,如果左侧下标对应的值与右侧下标对应的值相等,优先复制左侧,便于计算左侧大于右侧的个数
     *
     * @param sourceArray 原始数组
     * @param leftIndex   左侧开始下标
     * @param middleIndex 中间位置下标(左侧结束下标)
     * @param rightIndex  右侧下标
     * @return 合并过程中发现的逆序对数量
     */
    public int mergeCount(int[] sourceArray, int leftIndex, int middleIndex, int rightIndex) {

        int leftStart = leftIndex;
        int leftEnd = middleIndex;
        int rightStart = middleIndex + 1;
        int rightEnd = rightIndex;
        int[] tempResult = new int[rightEnd - leftStart + 1];
        int resultIndex = 0;
        int revertSortedPairCount = 0;

        while (true) {

            // 如果左侧小于等于右侧,复制左侧到数组中,不累加逆序对数量
            if (sourceArray[leftStart] <= sourceArray[rightStart]) {
                tempResult[resultIndex] = sourceArray[leftStart];
                leftStart++;
            } else {
                // 相反,左侧大于右侧,产生逆序对,左侧剩余数字个数即为逆序对的数量
                tempResult[resultIndex] = sourceArray[rightStart];
                rightStart++;

                // 累计逆序对
                revertSortedPairCount = revertSortedPairCount + (leftEnd - leftStart + 1);
            }
            resultIndex++;

            // 右侧已复制完成,只剩余左侧,不再构成逆序对,仅复制左侧到临时数组中
            if (rightStart > rightEnd) {
                while (leftStart <= leftEnd) {
                    tempResult[resultIndex] = sourceArray[leftStart];
                    leftStart++;
                    resultIndex++;
                }
                break;
            }

            // 左侧已复制完成,仅剩余右侧,不再构成逆序对,仅复制右侧到临时数组中
            if (leftStart > leftEnd) {
                while (rightStart <= rightEnd) {
                    tempResult[resultIndex] = sourceArray[rightStart];
                    rightStart++;
                    resultIndex++;
                }
                break;
            }
        }

        System.arraycopy(tempResult, 0, sourceArray, leftIndex, tempResult.length);
        return revertSortedPairCount;
    }
}
