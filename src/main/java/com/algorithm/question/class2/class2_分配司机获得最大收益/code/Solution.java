package com.algorithm.question.class2.class2_分配司机获得最大收益.code;

import org.junit.Test;

/**
 * @author by catface
 * @date 2021/5/25 2:17 下午
 */
public class Solution {

    public int maxProfit(int[][] income) {
        // 需要去A区的人(和需要去B区的人一致)
        int aDriverNum = income.length >> 1;
        return process(0, aDriverNum, income);
    }

    /**
     * 对第i号司机做决策
     *
     * @param i        第i号司机 i从0开始
     * @param aRestNum 还需要去a区的司机数量
     * @param income   收益表
     * @return 第i个司机决策出的最大收益
     */
    public int process(int i, int aRestNum, int[][] income) {
        if (i == income.length) {
            return 0;
        }
        // 如果剩余司机数量和a区域剩余派遣数量一致,剩余的所有司机只能去a区域
        if (aRestNum == income.length - i) {
            return income[i][0] + process(i + 1, aRestNum - 1, income);
        }
        // A区的司机已经满员,只能去B区
        if (aRestNum == 0) {
            return income[i][1] + process(i + 1, aRestNum, income);
        }
        // 否则,可分以下两种情况
        // 1.i号司机去A区
        // 2.i号司机去B去
        int profit1 = income[i][1] + process(i + 1, aRestNum, income);
        int profit2 = income[i][0] + process(i + 1, aRestNum - 1, income);
        return Math.max(profit1, profit2);
    }

    public static class TestClass {
        @Test
        public void testForce() {
            int N = 10;
            int value = 100;
            int testTime = 500;
            System.out.println("测试开始");
            Solution solution = new Solution();
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
