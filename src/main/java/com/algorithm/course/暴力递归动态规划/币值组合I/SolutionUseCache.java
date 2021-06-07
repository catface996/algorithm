package com.algorithm.course.暴力递归动态规划.币值组合I;

import java.util.Arrays;

import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/14 3:13 下午
 */
public class SolutionUseCache {

    //arr是货币数组，其中的值都是正数。再给定一个正数aim。
    //每个值都认为是一张货币，
    //即便是值相同的货币也认为每一张都是不同的，
    //返回组成aim的方法数
    //例如：arr = {1,1,1}，aim = 2
    //第0个和第1个能组成2，第1个和第2个能组成2，第0个和第2个能组成2
    //一共就3种方法，所以返回3

    private int[] moneyArr;
    private int[][] cache;

    /**
     * 计算可以组成目标金额的货币序列种数
     *
     * @param moneyArr  货币列表,每一张货币都不一样,即便面值相同
     * @param aimAmount 目标金额
     * @return 组成目标金额的货币序列种数
     */
    public int calculateWays(int[] moneyArr, int aimAmount) {
        this.moneyArr = moneyArr;
        cache = new int[moneyArr.length][aimAmount + 1];
        for (int[] ints : cache) {
            Arrays.fill(ints, -1);
        }
        return process(0, aimAmount);
    }

    /**
     * 递归组成目标金额
     *
     * @param currentIndex 当前待决策的货币下标
     * @param leftAmount   剩余待组装的金额
     * @return 组成剩余金额的货币组合数量
     */
    public int process(int currentIndex, int leftAmount) {

        // 剩余金额为0,发现一种方案,无需继续向下探索
        if (leftAmount == 0) {
            return 1;
        }
        // 剩余金额为负值,后续无论选择那张货币,无论是否选择,都不是一种有效方案
        if (leftAmount < 0) {
            return 0;
        }
        // 剩余目标金额大约0,无可选货币时,不是一种有效方案
        if (currentIndex >= moneyArr.length) {
            return 0;
        }

        // 检查是否命中缓存,命中则返回
        int ans = cache[currentIndex][leftAmount];
        if (ans != -1) {
            return ans;
        }

        // 不选择当前货币,剩余目标金额不变
        int ways = process(currentIndex + 1, leftAmount);

        // 选择当前货币,(选择当前货币的前提是,当前货币面试小于等于剩余目标金额,可以通过下一次进入process判断leftAmount来决策)
        ways += process(currentIndex + 1, leftAmount - moneyArr[currentIndex]);

        // 加入缓存
        cache[currentIndex][leftAmount] = ways;
        return ways;
    }

    public static class TestClass {
        @Test
        public void test1() {
            int[] moneyArr = {1, 1, 1};
            int aimAmount = 2;
            SolutionUseCache solution = new SolutionUseCache();
            int ways = solution.calculateWays(moneyArr, aimAmount);
            System.out.println("组成目标金额的货币组合数:" + ways);
        }
    }
}
