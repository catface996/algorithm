package com.algorithm.course.暴力递归动态规划.币值组合IV;

import java.util.Random;

import com.algorithm.util.ArrayUtil;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/16 10:37 上午
 */
public class SolutionUseCache {

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
        int[][] cache = ArrayUtil.initCache(moneyValueArr.length, aimAmount + 1, -1);
        return process(moneyValueArr, 0, aimAmount, cache);
    }

    /**
     * 递归计算组成剩余目标面值所用到的最少货币数量
     *
     * @param moneyValueArr 币值数组
     * @param current       当前待决策的币值下标
     * @param leftAimAmount 剩余待组合的币值
     * @param cache         缓存
     * @return 组合成剩余目标金额, 所需的最少货币数量
     */
    public int process(int[] moneyValueArr, int current, int leftAimAmount, int[][] cache) {
        if (leftAimAmount == 0) {
            return 0;
        }
        // 如果剩余金额小于0,说明之前选择的组合已经超出目标金额,是无效的组合方案
        if (leftAimAmount < 0) {
            return Integer.MAX_VALUE;
        }
        // 基于以上两种情况未命中的前提下,剩余目标金额大于0,如果已经无可选币值,之前所做选择是无效方案
        if (current >= moneyValueArr.length) {
            return Integer.MAX_VALUE;
        }

        // 优先查找缓存
        int ans = cache[current][leftAimAmount];
        if (ans != -1) {
            return ans;
        }

        // 通常情况下
        // 选择当前币值[0,...N]张,N*moneyValueArr[current]<=leftAimAmount,然后将剩余目标金额交给下一次迭代去计算
        int minNum = Integer.MAX_VALUE;
        for (int nums = 0; nums * moneyValueArr[current] <= leftAimAmount; nums++) {
            int subNums = process(moneyValueArr, current + 1, leftAimAmount - nums * moneyValueArr[current], cache);
            if (subNums != Integer.MAX_VALUE) {
                minNum = Math.min(minNum, nums + subNums);
            }
        }
        cache[current][leftAimAmount] = minNum;
        return minNum;
    }

    public static class TestClass {
        @Test
        public void test1() {
            int[] moneyValueArr = new int[] {1, 5, 10, 20};
            int aimAmount = 101;
            SolutionUseCache solution = new SolutionUseCache();
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
                SolutionUseCache solution = new SolutionUseCache();
                int minNum = solution.calculateMinNums(moneyValueArr, aimAmount);
                int minNum2 = Code02_MinCoinsNoLimit.minCoins(moneyValueArr, aimAmount);
                assert minNum == minNum2;
            }
            System.out.println("Good,you success!");
        }
    }

}
