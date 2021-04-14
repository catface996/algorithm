package course.暴力递归动态规划.币值组合II;

import java.util.Random;

import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/14 6:59 下午
 */
public class Solution {

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
        return process(0, aimAmount);
    }

    /**
     * 递归计算,从指定的币值开始,组合出剩余金额的组合数量
     *
     * @param currentIndex 当前待待决策的币值对应的下标
     * @param leftAmount   剩余待组合的金额
     * @return 有效的组合数
     */
    public int process(int currentIndex, int leftAmount) {

        // 剩余金额小于0,无论后续做何种决策,均无法构成有效组合
        if (leftAmount < 0) {
            return 0;
        }
        // 发现一种有效组合,后续选择只有一种,不选择剩余的任何币值加入组合,否则将使组合失效
        if (leftAmount == 0) {
            return 1;
        }
        // 以上两种情况未命中的情况下,剩余目标金额>0
        // 如果已经无可选币值,当前方案是无效组合
        if (currentIndex >= moneyValueArr.length) {
            return 0;
        }
        int ways = 0;
        for (int chooseNum = 0; ; chooseNum++) {
            int leftAmountAfterChoose = leftAmount - chooseNum * moneyValueArr[currentIndex];
            if (leftAmountAfterChoose < 0) {
                break;
            }
            ways += process(currentIndex + 1, leftAmountAfterChoose);
        }
        return ways;

    }

    public static class TestClass {

        //例如：arr = {1,2}，aim = 4
        //方法如下：1+1+1+1、1+1+2、2+2
        @Test
        public void test1() {
            for (int i = 0; i < 1000; i++) {
                int[] moneyValueArr = {1, 5, 10, 20};
                int aimAmount = new Random().nextInt(200);
                Solution solution = new Solution();
                int ways = solution.calculateWays(moneyValueArr, aimAmount);
                int ways2 = Code03_CoinsWayNoLimit.dp1(moneyValueArr, aimAmount);
                assert ways == ways2;
            }
            System.out.println("Good,you success!");
        }
    }
}
