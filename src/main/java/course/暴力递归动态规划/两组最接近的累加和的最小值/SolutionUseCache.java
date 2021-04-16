package course.暴力递归动态规划.两组最接近的累加和的最小值;

import org.junit.Test;
import util.ArrayUtil;

/**
 * @author by catface
 * @date 2021/4/16 3:28 下午
 */
public class SolutionUseCache {
    //给定一个正数数组arr，
    //请把arr中所有的数分成两个集合，尽量让两个集合的累加和接近
    //返回：
    //最接近的情况下，较小集合的累加和

    public int towCloselySumReturnMin(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        int aimNumber = sum >> 1;
        int[][] cache = ArrayUtil.initCache(arr.length, aimNumber + 1, -1);
        return process(arr, 0, aimNumber, cache);
    }

    /**
     * 递归计算最靠近目标值的和
     *
     * @param arr        数组
     * @param index      当前的下标
     * @param leftNumber 目标数值
     * @return 当前累计值
     */
    public int process(int[] arr, int index, int leftNumber, int[][] cache) {

        if (index >= arr.length) {
            return 0;
        }

        // 命中缓存,直接返回
        int ans = cache[index][leftNumber];
        if (ans != -1) {
            return ans;
        }

        // 分两种情况讨论,选择当前数字 和 不选择当前数字
        int minSum = process(arr, index + 1, leftNumber, cache);

        // 因为有次条件限制,累加和一定不会超过aimNumber
        if (arr[index] <= leftNumber) {
            int afterChooseSum = arr[index] + process(arr, index + 1, leftNumber - arr[index], cache);
            minSum = Math.max(minSum, afterChooseSum);
        }

        cache[index][leftNumber] = minSum;
        return minSum;
    }

    public static class TestClass {
        @Test
        public void test1() {
            int[] arr = ArrayUtil.randomIntArray(10, 1, 20);
            SolutionUseCache solution = new SolutionUseCache();
            int minSum1 = solution.towCloselySumReturnMin(arr);
            int minSum2 = Code01_SplitSumClosed.dp(arr);
            System.out.println("最接近的累加和1:" + minSum1);
            System.out.println("最接近的累加和2:" + minSum2);
        }

        @Test
        public void test2() {
            for (int i = 0; i < 100; i++) {
                int[] arr = ArrayUtil.randomIntArray(10, 1, 20);
                SolutionUseCache solution = new SolutionUseCache();
                int minSum1 = solution.towCloselySumReturnMin(arr);
                int minSum2 = Code01_SplitSumClosed.dp(arr);
                if (minSum1 != minSum2) {
                    System.out.println("最接近的累加和1:" + minSum1);
                    System.out.println("最接近的累加和2:" + minSum2);
                }
                assert minSum1 == minSum2;
            }
            System.out.println("Good,you success!");
        }
    }

}
