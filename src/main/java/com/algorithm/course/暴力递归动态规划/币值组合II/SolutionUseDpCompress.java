package com.algorithm.course.暴力递归动态规划.币值组合II;

import java.util.Random;

import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/14 6:59 下午
 */
public class SolutionUseDpCompress {

    //arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。
    //每个值都认为是一种面值，且认为张数是无限的。
    //返回组成aim的方法数
    //例如：arr = {1,2}，aim = 4
    //方法如下：1+1+1+1、1+1+2、2+2
    //一共就3种方法，所以返回3

    private int[] moneyValueArr;

    /**
     * 计算可以组成目标金额的币值组合
     *
     * @param moneyValueArr 币值数组
     * @param aimAmount     目标金额
     * @return 有效组合数
     */
    public int calculateWays(int[] moneyValueArr, int aimAmount) {
        this.moneyValueArr = moneyValueArr;
        return process(aimAmount);
    }

    /**
     * 递归计算,从指定的币值开始,组合出剩余金额的组合数量
     *
     * @param aimAmount 剩余待组合的金额
     * @return 有效的组合数
     */
    public int process(int aimAmount) {
        int[][] dp = new int[2][aimAmount + 1];
        dp[0][0] = 1;
        dp[1][0] = 1;
        int source = 1;
        int target = 0;
        for (int currentIndex = moneyValueArr.length - 1; currentIndex >= 0; currentIndex--) {
            for (int leftAmount = 1; leftAmount <= aimAmount; leftAmount++) {
                int ways = 0;
                for (int chooseNum = 0; ; chooseNum++) {
                    int leftAmountAfterChoose = leftAmount - chooseNum * moneyValueArr[currentIndex];
                    if (leftAmountAfterChoose < 0) {
                        break;
                    }
                    ways += dp[source][leftAmountAfterChoose];
                }
                dp[target][leftAmount] = ways;
            }
            source = source ^ target;
            target = source ^ target;
            source = source ^ target;
        }
        return dp[source][aimAmount];
    }

    public static class TestClass {

        //例如：arr = {1,2}，aim = 4
        //方法如下：1+1+1+1、1+1+2、2+2
        @Test
        public void test1() {
            for (int i = 0; i < 1000; i++) {
                int[] moneyValueArr = {1, 5, 10, 20};
                int aimAmount = new Random().nextInt(200);
                SolutionUseDpCompress solutionUseDp = new SolutionUseDpCompress();
                Solution solution = new Solution();
                int waysUseDp = solutionUseDp.calculateWays(moneyValueArr, aimAmount);
                int ways = solution.calculateWays(moneyValueArr, aimAmount);
                //System.out.println("组合数Dp:" + waysUseDp);
                //System.out.println("组合数:" + ways);
                assert waysUseDp == ways;
            }
            System.out.println("Good,you success!");
        }
    }
}
