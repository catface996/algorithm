package course.other;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import util.ArrayUtil;

/**
 * 求小和
 * <p>
 * 小和问题
 * <p>
 * 在一个数组中， 每一个数左边比当前数小的数累加起来， 叫做这个数组的小和。 求一个数组 的小和。
 * <p>
 * 例子：
 * <p>
 * [1,3,4,2,5]
 * <p>
 * 1左边比1小的数， 没有；
 * <p>
 * 3左边比3小的数， 1；
 * <p>
 * 4左边比4小的数， 1、 3；
 * <p>
 * 2左边比2小的数， 1；
 * <p>
 * 5左边比5小的数， 1、 3、 4、 2；
 * <p>
 * 所以小和为1+1+3+1+1+3+4+2=16
 * <p>
 * <p>
 * 如果直接用两层for循环扫一遍，时间复杂度O(n*n)，这个题目可以利用归并排序把时间复杂度降到O(nlogn)
 *
 * @author by catface
 * @date 2021/2/20 10:24 上午
 */
@Slf4j
public class CalculateMinSum {

    @Test
    public void testMinSum() {
        int passCount = 0;
        for (int size = 1; size < 100; size++) {
            for (int round = 0; round < 1000; round++) {
                int[] sourceArray = ArrayUtil.randomIntArray(size, 1, 100);
                int foreachMinSum = foreachCalculate(sourceArray);
                int[] originalArray = ArrayUtil.clone(sourceArray);
                int mergeMinSum = mergeCalculateMinSum(originalArray, 0, originalArray.length - 1);
                boolean checkResult = foreachMinSum == mergeMinSum;
                if (!checkResult) {
                    log.info("foreachMinSum:{},mergeMinSum:{},sourceArray:{}", foreachMinSum, mergeMinSum, sourceArray);
                } else {
                    passCount++;
                }
            }
        }
        log.info("通过次数:{}", passCount);
    }

    /**
     * for 循环累加小和
     *
     * @param sourceArray 原始数组
     * @return 小和
     */
    public int foreachCalculate(int[] sourceArray) {
        int minSum = 0;
        for (int i = 0; i < sourceArray.length; i++) {
            for (int j = 0; j < i; j++) {
                if (sourceArray[j] < sourceArray[i]) {
                    minSum = minSum + sourceArray[j];
                }
            }
        }
        return minSum;
    }

    /**
     * 归并计算小和
     *
     * @param sourceArray 原始数组
     * @param leftIndex   左侧开始的下标
     * @param rightIndex  右侧结束的下标
     * @return 小和
     */
    public int mergeCalculateMinSum(int[] sourceArray, int leftIndex, int rightIndex) {
        if (leftIndex == rightIndex) {
            return 0;
        }

        int middleIndex = leftIndex + ((rightIndex - leftIndex) >> 1);

        int leftMinSum = mergeCalculateMinSum(sourceArray, leftIndex, middleIndex);
        int rightMinSum = mergeCalculateMinSum(sourceArray, middleIndex + 1, rightIndex);
        int mergeMinSum = mergeMinSum(sourceArray, leftIndex, middleIndex, rightIndex);
        return leftMinSum + rightMinSum + mergeMinSum;
    }

    /**
     * 合并左右两侧数组,并累计小和
     *
     * @param sourceArray 原始数组
     * @param leftIndex   左侧下标
     * @param middleIndex 中间下标
     * @param rightIndex  右侧下标
     * @return 合并时计算得出的小和
     */
    private int mergeMinSum(int[] sourceArray, int leftIndex, int middleIndex, int rightIndex) {

        int leftStart = leftIndex;
        int leftEnd = middleIndex;
        int rightStart = middleIndex + 1;
        int rightEnd = rightIndex;
        int minSum = 0;

        int[] tempResult = new int[rightIndex - leftIndex + 1];
        int resultIndex = 0;
        while (true) {
            if (sourceArray[leftStart] < sourceArray[rightStart]) {
                tempResult[resultIndex] = sourceArray[leftStart];
                minSum = minSum + (sourceArray[leftStart] * (rightEnd - rightStart + 1));
                leftStart++;
            } else {
                tempResult[resultIndex] = sourceArray[rightStart];
                rightStart++;
            }
            resultIndex++;

            // 仅复制右侧,因为左侧已复制结束,无法产生小和( 0 * 右侧剩余个数 = 0)
            if (leftStart > leftEnd) {
                while (rightStart <= rightEnd) {
                    tempResult[resultIndex] = sourceArray[rightStart];
                    rightStart++;
                    resultIndex++;
                }
                break;
            }

            // 仅复制左侧,因为右侧已复制结束,无法产生小和( 左侧剩余数 * 0 = 0 )
            if (rightStart > rightEnd) {
                while (leftStart <= leftEnd) {
                    tempResult[resultIndex] = sourceArray[leftStart];
                    leftStart++;
                    resultIndex++;
                }
                break;
            }
        }

        System.arraycopy(tempResult, 0, sourceArray, leftIndex, tempResult.length);
        return minSum;
    }

}
