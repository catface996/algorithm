package course.暴力递归动态规划.两组最接近的累加和的最小值;

import org.junit.Test;
import util.ArrayUtil;

/**
 * @author by catface
 * @date 2021/4/16 3:28 下午
 */
public class SolutionUseDp {
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
        return process(arr, aimNumber);
    }

    /**
     * 递归计算最靠近目标值的和
     *
     * @param arr       数组
     * @param aimNumber 目标数值
     * @return 当前累计值
     */
    public int process(int[] arr, int aimNumber) {
        int[][] dp = new int[arr.length + 1][aimNumber + 1];
        // 如果已无剩余可选数字,无论当前剩余度搜好,返回0
        for (int leftNumber = 0; leftNumber <= aimNumber; leftNumber++) {
            dp[arr.length][leftNumber] = 0;
        }
        for (int index = arr.length - 1; index >= 0; index--) {
            for (int leftNumber = 0; leftNumber <= aimNumber; leftNumber++) {
                // 分两种情况讨论,选择当前数字 和 不选择当前数字
                int minSum = dp[index + 1][leftNumber];
                // 因为有次条件限制,累加和一定不会超过aimNumber
                if (arr[index] <= leftNumber) {
                    int afterChooseSum = arr[index] + dp[index + 1][leftNumber - arr[index]];
                    minSum = Math.max(minSum, afterChooseSum);
                }
                dp[index][leftNumber] = minSum;
            }
        }
        return dp[0][aimNumber];
    }

    public static class TestClass {
        @Test
        public void test1() {
            int[] arr = ArrayUtil.randomIntArray(10, 1, 20);
            SolutionUseDp solution = new SolutionUseDp();
            int minSum1 = solution.towCloselySumReturnMin(arr);
            int minSum2 = Code01_SplitSumClosed.dp(arr);
            System.out.println("最接近的累加和1:" + minSum1);
            System.out.println("最接近的累加和2:" + minSum2);
        }

        @Test
        public void test2() {
            for (int i = 0; i < 100; i++) {
                int[] arr = ArrayUtil.randomIntArray(10, 1, 20);
                SolutionUseDp solution = new SolutionUseDp();
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
