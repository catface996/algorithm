package com.algorithm.question.class2.class2_分配司机获得最大收益.code;

import org.junit.Test;

/**
 * @author by catface
 * @date 2021/5/25 2:17 下午
 */
public class SolutionUseDp {

    public int maxProfit(int[][] income) {
        return process(income);
    }

    /**
     * 对第i号司机做决策
     *
     * @param income 收益表
     * @return 第i个司机决策出的最大收益
     */
    public int process(int[][] income) {
        int[][] dp = new int[income.length + 1][(income.length >> 1) + 1];
        for (int i = income.length - 1; i >= 0; i--) {
            for (int aRestNum = 0; aRestNum <= income.length / 2; aRestNum++) {
                int ans;
                if (aRestNum == income.length - i) {
                    // 如果剩余司机数量和a区域剩余派遣数量一致,剩余的所有司机只能去a区域
                    ans = income[i][0] + dp[i + 1][aRestNum - 1];
                } else if (aRestNum == 0) {
                    // A区的司机已经满员,只能去B区
                    ans = income[i][1] + dp[i + 1][aRestNum];
                } else {
                    // 否则,可分以下两种情况
                    // 1.i号司机去A区
                    // 2.i号司机去B去
                    int profit1 = income[i][1] + dp[i + 1][aRestNum];
                    int profit2 = income[i][0] + dp[i + 1][aRestNum - 1];
                    ans = Math.max(profit1, profit2);
                }
                dp[i][aRestNum] = ans;
            }
        }
        return dp[0][income.length / 2];
    }

    public static class TestClass {
        @Test
        public void testForce() {
            int N = 10;
            int value = 100;
            int testTime = 500;
            System.out.println("测试开始");
            SolutionUseDp solution = new SolutionUseDp();
            for (int i = 0; i < testTime; i++) {
                int len = (int)(Math.random() * N) + 1;
                int[][] matrix = Code04_Drive.randomMatrix(len, value);
                int ans1 = Code04_Drive.maxMoney1(matrix);
                int ans2 = solution.maxProfit(matrix);
                if (ans1 != ans2) {
                    System.out.println(ans1);
                    System.out.println(ans2);
                    System.out.println("Oops!");
                }
            }
            System.out.println("测试结束");
        }
    }
}
