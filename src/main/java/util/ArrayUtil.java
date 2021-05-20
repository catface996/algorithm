package util;

import java.util.Arrays;
import java.util.Random;

import course.排序.BubbleSort;

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

    public static int[][] randomMatrix(int rows, int cols, int minNum, int maxNum) {
        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            matrix[i] = randomIntArray(cols, minNum, maxNum);
        }
        return matrix;
    }

    public static int[][] initCache(int rows, int cols, int initValue) {
        int[][] cache = new int[rows][];
        for (int i = 0; i < rows; i++) {
            int[] rowArr = new int[cols];
            Arrays.fill(rowArr, initValue);
            cache[i] = rowArr;
        }
        return cache;
    }

    public static Integer[] randomIntegerArray(int size, int minNum, int maxNum) {
        Random random = new Random();
        int range = maxNum - minNum;
        Integer[] tempArray = new Integer[size];
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

    public static int[] randomSortedNotRepeat(int size, int start, int maxStepRange) {
        int[] ans = new int[size];
        ans[0] = start;
        for (int i = 1; i < size; i++) {
            ans[i] = ans[i - 1] + (int)(Math.random() * maxStepRange + 1);
        }
        return ans;
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
