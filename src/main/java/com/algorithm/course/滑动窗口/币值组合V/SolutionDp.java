package com.algorithm.course.滑动窗口.币值组合V;

import java.util.Arrays;

import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/23 8:42 下午
 */
public class SolutionDp {

    // 给定面值组合[1,5,10,20]
    // 给定张数组合[7,15,20,5]
    // 求组成目标金额的方案数

    public int calculateMinNums(int[] money, int[] nums, int aim) {
        return processDp(money, nums, aim);
    }

    /**
     * 使用指定位置的货币的前提下,能够组成目标金额的方案数
     * <p>
     * 通过滑动窗口做斜率优化
     *
     * @param money 面值数组
     * @param nums  面值对应的张数
     * @return 组合数
     */
    private int processDp(int[] money, int[] nums, int aim) {
        int[][] dp = new int[money.length + 1][aim + 1];
        Arrays.fill(dp[money.length], Integer.MAX_VALUE);
        dp[money.length][0] = 0;
        for (int current = money.length - 1; current >= 0; current--) {
            for (int leftAmount = 0; leftAmount <= aim; leftAmount++) {
                int minNum = Integer.MAX_VALUE;
                for (int i = 0; i <= nums[current] && i * money[current] <= leftAmount; i++) {
                    int temNum = dp[current + 1][leftAmount - i * money[current]];
                    if (temNum != Integer.MAX_VALUE) {
                        temNum += i;
                        minNum = Math.min(minNum, temNum);
                    }
                }
                dp[current][leftAmount] = minNum;
            }
        }
        return dp[0][aim];
    }

    public static class TestClass {
        // 给定面值组合[1,5,10,20]
        // 给定张数组合[7,15,20,5]
        // 目标金额: 20
        // 1张
        @Test
        public void test() {
            int[] money = {1, 5, 10, 20};
            int[] nums = {7, 15, 20, 5};
            int aim = 20;
            SolutionDp solution = new SolutionDp();
            int ans = solution.calculateMinNums(money, nums, aim);
            System.out.println(ans);
        }

        // 给定面值组合[1,5,10,20]
        // 给定张数组合[7,15,20,5]
        // 目标金额: 20
        // 10张 3 * 1 + 2 * 10 + 5 * 20
        @Test
        public void test1() {
            int[] money = {1, 5, 10, 20};
            int[] nums = {7, 15, 20, 5};
            int aim = 123;
            SolutionDp solution = new SolutionDp();
            int ans = solution.calculateMinNums(money, nums, aim);
            System.out.println(ans);
        }

        // 给定面值组合[1,5,10,20]
        // 给定张数组合[7,15,20,5]
        // 目标金额: 20
        // 8张
        @Test
        public void test2() {
            int[] money = {1, 5, 10, 20};
            int[] nums = {7, 15, 20, 5};
            int aim = 125;
            SolutionDp solution = new SolutionDp();
            int ans = solution.calculateMinNums(money, nums, aim);
            System.out.println(ans);
        }

    }

}
