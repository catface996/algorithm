package course.暴力递归动态规划.两组最接近的累加和分奇偶;

import java.util.Arrays;

import org.junit.Test;
import util.ArrayUtil;

/**
 * @author by catface
 * @date 2021/4/16 4:52 下午
 */
public class SolutionUseCache {

    //给定一个正数数组arr，请把arr中所有的数分成两个集合
    //如果arr长度为偶数，两个集合包含数的个数要一样多
    //如果arr长度为奇数，两个集合包含数的个数必须只差一个
    //请尽量让两个集合的累加和接近
    //返回：
    //最接近的情况下，较小集合的累加和

    public int calculateMinSum(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        int aimNumber = sum >> 1;
        int[][][] cache = new int[arr.length][arr.length / 2 + 2][aimNumber + 1];
        for (int[][] ints : cache) {
            for (int[] anInt : ints) {
                Arrays.fill(anInt, -1);
            }
        }
        if (sum % 2 == 0) {
            return process(arr, 0, arr.length >> 1, aimNumber, cache);
        } else {
            int halfCount = arr.length >> 1;
            int minSum1 = process(arr, 0, halfCount, aimNumber, cache);
            int minSum2 = process(arr, 0, halfCount + 1, aimNumber, cache);
            return Math.min(minSum1, minSum2);
        }
    }

    private int process(int[] arr, int index, int leftCount, int leftNumber, int[][][] cache) {
        if (index >= arr.length) {
            return 0;
        }
        // 命中缓存直接返回
        int ans = cache[index][leftCount][leftNumber];
        if (ans != -1) {
            return ans;
        }
        // 不选择当前数字
        int minSum = process(arr, index + 1, leftCount, leftNumber, cache);
        // 选择当前数字
        if (arr[index] <= leftNumber && leftCount >= 1) {
            int chooseSum = arr[index] + process(arr, index + 1, leftCount - 1, leftNumber - arr[index], cache);
            minSum = Math.max(minSum, chooseSum);
        }
        cache[index][leftCount][leftNumber] = minSum;
        return minSum;
    }

    public static class TestClass {
        @Test
        public void test1() {
            int[] arr = ArrayUtil.randomIntArray(20, 1, 40);
            SolutionUseCache solution = new SolutionUseCache();
            int minSum1 = solution.calculateMinSum(arr);
            int minSum2 = Code02_SplitSumClosedSizeHalf.dp2(arr);
            System.out.println("最接近的小和1:" + minSum1);
            System.out.println("最接近的小和2:" + minSum2);
        }

        @Test
        public void test2() {
            for (int i = 0; i < 1000; i++) {
                int j = i % 2;
                int[] arr = ArrayUtil.randomIntArray(20 + j, 1, 40);
                SolutionUseCache solution = new SolutionUseCache();
                int minSum1 = solution.calculateMinSum(arr);
                int minSum2 = Code02_SplitSumClosedSizeHalf.dp2(arr);
                System.out.println("最接近的小和1:" + minSum1);
                if (minSum1 != minSum2) {
                    System.out.println("最接近的小和1:" + minSum1);
                    System.out.println("最接近的小和2:" + minSum2);
                }
                assert minSum1 == minSum2;
            }
            System.out.println("Good,you success!");
        }
    }
}
