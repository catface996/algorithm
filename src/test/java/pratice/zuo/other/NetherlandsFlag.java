package pratice.zuo.other;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import pratice.util.ArrayUtil;

/**
 * @author by catface
 * @date 2021/3/12 10:07 上午
 */
@Slf4j
public class NetherlandsFlag {

    public static void split(int[] arr, int separateIndex) {
        log.info("分割位置:{},分割值:{}", separateIndex, arr[separateIndex]);
        int separateValue = arr[separateIndex];
        int leftStart = 0;
        int rightEnd = arr.length - 1;
        while (leftStart < rightEnd) {
            while (arr[leftStart] < separateValue) {
                leftStart++;
                if (leftStart >= rightEnd) {
                    return;
                }
            }
            ArrayUtil.swap(arr, leftStart, rightEnd);
            log.info("左侧开始,左右交换后:{}", arr);
            while (arr[rightEnd] >= separateValue) {
                rightEnd--;
                if (leftStart >= rightEnd) {
                    return;
                }
            }
            ArrayUtil.swap(arr, leftStart, rightEnd);
            log.info("右侧开始,左右交换后:{}", arr);
        }
        arr[leftStart] = separateValue;
    }

    public static int[] partition(int[] arr, int L, int R, int splitIndex) {
        int less = L - 1;
        int more = R + 1;
        int p = arr[splitIndex];
        while (L < more) {
            if (arr[L] < p) {
                ArrayUtil.swap(arr, ++less, L++);
            } else if (arr[L] > p) {
                ArrayUtil.swap(arr, --more, L);
            } else {
                L++;
            }
        }
        return new int[] {less + 1, more - 1};
    }

    public static void partition2(int[] arr, int leftIndex, int rightIndex, int splitIndex) {
        int leftStart = leftIndex - 1;
        int rightEnd = rightIndex + 1;
        int currentIndex = leftIndex;
        int splitValue = arr[splitIndex];
        while (currentIndex < rightEnd) {
            if (arr[currentIndex] < splitValue) {
                leftStart++;
                ArrayUtil.swap(arr, leftStart, currentIndex);
                currentIndex++;
            } else if (arr[currentIndex] > splitValue) {
                rightEnd--;
                ArrayUtil.swap(arr, currentIndex, rightEnd);
            } else {
                currentIndex++;
            }
        }
    }

    public static boolean checkPartition(int[] arr, int splitValue) {
        int sameStart = arr.length;
        int sameEnd = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < splitValue) {
                continue;
            }
            if (arr[i] == splitValue) {
                if (i <= sameStart) {
                    sameStart = i;
                }
            }
            if (arr[i] >= splitValue) {
                if (i >= sameEnd) {
                    sameEnd = i;
                }
            }
        }
        for (int i = 0; i < sameStart; i++) {
            if (arr[i] >= splitValue) {
                log.info("左侧区间出现大于等于分界值");
                return false;
            }
        }

        for (int i = sameEnd + 1; i < arr.length; i++) {
            if (arr[i] <= splitValue) {
                log.info("右侧区间出现小于等于分界值");
                return false;
            }
        }
        return true;
    }

    @Test
    public void test_1() {
        //int[] arr = ArrayUtil.randomIntArray(10, 1, 100);
        int[] arr = new int[] {51, 91, 12, 16, 94, 72, 98, 11, 25, 38};
        log.info("分割之前:{}", arr);
        split(arr, 1);
        log.info("分割之后:{}", arr);
    }

    @Test
    public void test_partition2_1() {
        //int[] arr = ArrayUtil.randomIntArray(10, 1, 100);
        int[] arr = new int[] {65, 71, 25, 35, 90, 6, 24, 46, 82, 17};
        log.info("分割之前:{}", arr);
        int splitIndex = 1;
        int splitValue = arr[splitIndex];
        partition2(arr, 0, 9, splitIndex);
        log.info("分割之后:{}", arr);
        checkPartition(arr, splitValue);
    }

    @Test
    public void test_partition_1() {
        //int[] arr = ArrayUtil.randomIntArray(10, 1, 100);
        int[] arr = new int[] {65, 71, 25, 35, 90, 6, 24, 46, 82, 17};
        log.info("分割之前:{}", arr);
        int splitIndex = 1;
        int splitValue = arr[splitIndex];
        partition(arr, 0, 9, splitValue);
        log.info("分割之后:{}", arr);
        checkPartition(arr, splitValue);
    }

    @Test
    public void test_partition_2() {
        int testCount = 0;
        for (int i = 0; i < 1000000; i++) {
            int[] arr = ArrayUtil.randomIntArray(10, 1, 100);
            int[] cloneArr = ArrayUtil.clone(arr);
            //log.info("分割之前:{}", arr);
            int splitIndex = 1;
            int splitValue = arr[splitIndex];
            partition2(arr, 0, 9, splitIndex);
            //log.info("分割之后:{}", arr);
            boolean checkResult = checkPartition(arr, splitValue);
            if (!checkResult) {
                log.info("原始数组:{},splitValue:{},结果数组:{}", cloneArr, splitValue, arr);
            } else {
                testCount++;
            }
        }
        log.info("{}次测试通过", testCount);
    }

}
