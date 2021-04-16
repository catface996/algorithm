package course.暴力递归动态规划.币值组合IV;

import java.util.Random;

import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/16 10:37 上午
 */
public class SolutionUseDp {

    //arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。
    //每个值都认为是一种面值，且认为张数是无限的。
    //返回组成aim的最少货币数

    /**
     * 计算组装成目标金额使用到最少的货币数量
     *
     * @param moneyValueArr 货币面值数组
     * @param aimAmount     目标金额
     * @return 组成目标金额用到的货币最少数量
     */
    public int calculateMinNums(int[] moneyValueArr, int aimAmount) {
        return process(moneyValueArr, aimAmount);
    }

    /**
     * 递归计算组成剩余目标面值所用到的最少货币数量
     *
     * @param moneyValueArr 币值数组
     * @param aimAmount     目标组合金额
     * @return 组合成剩余目标金额, 所需的最少货币数量
     */
    public int process(int[] moneyValueArr, int aimAmount) {
        int[][] dp = new int[moneyValueArr.length + 1][aimAmount + 1];
        for (int current = 0; current <= moneyValueArr.length; current++) {
            dp[current][0] = 0;
        }
        for (int leftAimAmount = 1; leftAimAmount <= aimAmount; leftAimAmount++) {
            dp[moneyValueArr.length][leftAimAmount] = Integer.MAX_VALUE;
        }
        // 通常情况下
        // 选择当前币值[0,...N]张,N*moneyValueArr[current]<=leftAimAmount,然后将剩余目标金额交给下一次迭代去计算
        for (int leftAimAmount = 1; leftAimAmount <= aimAmount; leftAimAmount++) {
            for (int current = moneyValueArr.length - 1; current >= 0; current--) {
                int minNum = Integer.MAX_VALUE;
                for (int nums = 0; nums * moneyValueArr[current] <= leftAimAmount; nums++) {
                    int subNums = dp[current + 1][leftAimAmount - nums * moneyValueArr[current]];
                    if (subNums != Integer.MAX_VALUE) {
                        minNum = Math.min(minNum, nums + subNums);
                    }
                }
                dp[current][leftAimAmount] = minNum;
            }
        }
        return dp[0][aimAmount];
    }

    public static class TestClass {
        @Test
        public void test1() {
            int[] moneyValueArr = new int[] {1, 5, 10, 20};
            int aimAmount = 101;
            SolutionUseDp solution = new SolutionUseDp();
            int minNums = solution.calculateMinNums(moneyValueArr, aimAmount);
            int minNum2 = Code02_MinCoinsNoLimit.minCoins(moneyValueArr, aimAmount);
            System.out.println("需要最小货币数量1:" + minNums);
            System.out.println("需要最小货币数量2:" + minNum2);
        }

        @Test
        public void test2() {
            for (int i = 0; i < 100; i++) {
                int[] moneyValueArr = new int[] {1, 5, 10, 20};
                int aimAmount = new Random().nextInt(200);
                SolutionUseDp solution = new SolutionUseDp();
                int minNum = solution.calculateMinNums(moneyValueArr, aimAmount);
                int minNum2 = Code02_MinCoinsNoLimit.minCoins(moneyValueArr, aimAmount);
                assert minNum == minNum2;
            }
            System.out.println("Good,you success!");
        }
    }

}
