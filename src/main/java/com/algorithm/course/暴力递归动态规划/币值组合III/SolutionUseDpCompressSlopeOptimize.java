package com.algorithm.course.暴力递归动态规划.币值组合III;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.algorithm.util.ArrayUtil;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/15 10:16 上午
 */
public class SolutionUseDpCompressSlopeOptimize {

    //arr是货币数组，其中的值都是正数。再给定一个正数aim。
    //每个值都认为是一张货币，
    //认为值相同的货币没有任何不同，
    //返回组成aim的方法数
    //例如：arr = {1,2,1,1,2,1,2}，aim = 4
    //方法：1+1+1+1、1+1+2、2+2
    //一共就3种方法，所以返回3

    public int calculateWays(int[] moneyArr, int aimAmount) {
        MoneyInfo[] moneyInfos = convert(moneyArr);
        return process(moneyInfos, aimAmount);
    }

    /**
     * 计算在当前剩余目标金额的情况下,有效的币值组合数
     *
     * @param moneyInfos 币值->数量 列表
     * @param aimAmount  目标金额
     * @return 有效的币值组合数量
     */
    public int process(MoneyInfo[] moneyInfos, int aimAmount) {
        int[][] dp = new int[2][aimAmount + 1];
        dp[0][0] = 1;
        dp[1][0] = 1;
        int source = 1;
        int target = 0;
        for (int currentIndex = moneyInfos.length - 1; currentIndex >= 0; currentIndex--) {
            for (int leftAmount = 1; leftAmount <= aimAmount; leftAmount++) {
                MoneyInfo currentMoney = moneyInfos[currentIndex];
                dp[target][leftAmount] = dp[source][leftAmount];
                int leftAmountAfterChoose = leftAmount - currentMoney.moneyValue;
                if (leftAmountAfterChoose >= 0) {
                    dp[target][leftAmount] += dp[target][leftAmountAfterChoose];
                }
                int lastLeftAmountAfterChoose = leftAmount - (currentMoney.num + 1) * currentMoney.moneyValue;
                if (lastLeftAmountAfterChoose >= 0) {
                    dp[target][leftAmount] -= dp[source][lastLeftAmountAfterChoose];
                }
            }
            source = source ^ target;
            target = source ^ target;
            source = source ^ target;
        }
        return dp[source][aimAmount];
    }

    /**
     * 货币列表按币值统计
     *
     * @param moneyArr 货币列表
     * @return 币值->数量 列表
     */
    private MoneyInfo[] convert(int[] moneyArr) {
        Map<Integer, Integer> moneyMap = new HashMap<>();
        for (int j : moneyArr) {
            Integer num = moneyMap.getOrDefault(j, 0);
            num += 1;
            moneyMap.put(j, num);
        }
        MoneyInfo[] infos = new MoneyInfo[moneyMap.size()];
        int i = 0;
        for (Integer moneyValue : moneyMap.keySet()) {
            MoneyInfo info = new MoneyInfo(moneyValue, moneyMap.get(moneyValue));
            infos[i] = info;
            i++;
        }
        return infos;
    }

    /**
     * 货币模型
     */
    public static class MoneyInfo {
        /**
         * 币值
         */
        private final int moneyValue;
        private final int num;

        public MoneyInfo(int moneyValue, int num) {
            this.moneyValue = moneyValue;
            this.num = num;
        }
    }

    public static class TestClass {
        //例如：arr = {1,2,1,1,2,1,2}，aim = 4
        //方法：1+1+1+1、1+1+2、2+2
        //一共就3种方法，所以返回3
        @Test
        public void test1() {
            int[] moneyArr = {1, 2, 1, 1, 2, 1, 2};
            int aimAmount = 4;
            SolutionUseDpCompressSlopeOptimize solution = new SolutionUseDpCompressSlopeOptimize();
            int ways = solution.calculateWays(moneyArr, aimAmount);
            System.out.println("有效组合数:" + ways);
        }

        @Test
        public void test2() {
            for (int i = 0; i < 1000; i++) {
                int[] moneyArr = ArrayUtil.randomIntArray(20, 1, 5);
                int aimAmount = new Random().nextInt(60);
                SolutionUseDpCompressSlopeOptimize solution = new SolutionUseDpCompressSlopeOptimize();
                int ways = solution.calculateWays(moneyArr, aimAmount);
                int waysCompare = Code04_CoinsWaySameValueSamePapper.dp1(moneyArr, aimAmount);
                //System.out.println("roud " + i + " " + ways + " compare " + waysCompare);
                assert ways == waysCompare;
            }
            System.out.println("Good,you success!");
        }
    }

}
