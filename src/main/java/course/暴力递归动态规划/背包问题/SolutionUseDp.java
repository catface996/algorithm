package course.暴力递归动态规划.背包问题;

import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/12 1:39 下午
 */
public class SolutionUseDp {
    //给定两个长度都为N的数组weights和values，
    //weights[i]和values[i]分别代表 i号物品的重量和价值。
    //给定一个正数bag，表示一个载重bag的袋子，
    //你装的物品不能超过这个重量。
    //返回你能装下最多的价值是多少?

    public int calculateMaxValue(int[] weights, int[] values, int bag) {
        int[][] dp = new int[weights.length][bag + 1];
        for (int leftCapacity = bag; leftCapacity >= 0; leftCapacity--) {
            if (leftCapacity >= weights[weights.length - 1]) {
                dp[weights.length - 1][leftCapacity] = values[weights.length - 1];
            }
        }

        for (int start = weights.length - 2; start >= 0; start--) {
            for (int leftCapacity = bag; leftCapacity >= 0; leftCapacity--) {
                int chooseStart = 0;
                if (weights[start] <= leftCapacity) {
                    chooseStart = values[start] + dp[start + 1][leftCapacity - weights[start]];
                }

                // 无论当前背包容量剩余多少,都不选择装入start位置的物品
                int notChooseStart = dp[start + 1][leftCapacity];
                dp[start][leftCapacity] = Math.max(chooseStart, notChooseStart);
            }
        }
        return dp[0][bag];
    }

    public static class TestClass {
        @Test
        public void test1() {
            int[] weights = {3, 2, 4, 7, 3, 1, 3, 22, 4};
            int[] values = {5, 6, 3, 19, 12, 4, 20, 6, 19};
            int bag = 15;
            Solution solution = new Solution();
            SolutionUseCache solutionUseCache = new SolutionUseCache();
            SolutionUseDp solutionUseDp = new SolutionUseDp();
            int maxValue = solution.calculateMaxValue(weights, values, bag);
            int maxValueUseCache = solutionUseCache.calculateMaxValue(weights, values, bag);
            int maxValueUseDp = solutionUseDp.calculateMaxValue(weights, values, bag);
            System.out.println("背包能容纳的最大价值:" + " 方法1:" + maxValue + " 方法2:" + maxValueUseCache
                + " 方法3:" + maxValueUseDp);
        }
    }
}
