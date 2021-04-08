package pratice.util;

import java.util.Random;

import pratice.zuo.other.BubbleSort;

/**
 * @author by catface
 * @date 2021/2/18 11:37 上午
 */
public class ArrayUtil {

    public static int[] randomIntArray(int size, int minNum, int maxNum) {
        Random random = new Random();
        int range = maxNum - minNum;
        int[] tempArray = new int[size];
        for (int i = 0; i < tempArray.length; i++) {
            int tempNum = random.nextInt(range) + minNum;
            tempArray[i] = tempNum;
        }
        return tempArray;
    }

    public static int[] clone(int[] sourceArray) {
        int[] tempArray = new int[sourceArray.length];
        System.arraycopy(sourceArray, 0, tempArray, 0, sourceArray.length);
        return tempArray;
    }

    public static int[] clone(int[] sourceArray, int length) {
        if (length <= 0) {
            return null;
        }
        int[] result = new int[length];
        System.arraycopy(sourceArray, 0, result, 0, length);
        return result;
    }

    public static boolean checkSortResult(int[] originalArray, int[] result) {
        if (result == null || originalArray == null) {
            return false;
        }
        int[] targetResult = BubbleSort.bubbleSort(originalArray);
        boolean checkResult = true;
        for (int i = 0; i < result.length; i++) {
            if (result[i] != targetResult[i]) {
                System.out.println("第" + i + "位," + result[i] + ":" + targetResult[i] + ",结果不匹配");
                checkResult = false;
            }
        }
        return checkResult;
    }

    public static void swap(int[] arr, int leftIndex, int rightIndex) {
        if (leftIndex == rightIndex) {
            return;
        }
        arr[leftIndex] = arr[leftIndex] ^ arr[rightIndex];
        arr[rightIndex] = arr[leftIndex] ^ arr[rightIndex];
        arr[leftIndex] = arr[leftIndex] ^ arr[rightIndex];
    }
}
