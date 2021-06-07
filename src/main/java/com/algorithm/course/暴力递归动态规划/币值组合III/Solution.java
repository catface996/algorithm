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
public class Solution {

    //arr是货币数组，其中的值都是正数。再给定一个正数aim。
    //每个值都认为是一张货币，
    //认为值相同的货币没有任何不同，
    //返回组成aim的方法数
    //例如：arr = {1,2,1,1,2,1,2}，aim = 4
    //方法：1+1+1+1、1+1+2、2+2
    //一共就3种方法，所以返回3

    public int calculateWays(int[] moneyArr, int aimAmount) {
        MoneyInfo[] moneyInfos = convert(moneyArr);
        return process(moneyInfos, 0, aimAmount);
    }

    /**
     * 计算在当前剩余目标金额的情况下,有效的币值组合数
     *
     * @param moneyInfos   币值->数量 列表
     * @param currentIndex 当前待决策的币值下标
     * @param leftAmount   剩余目标金额
     * @return 有效的币值组合数量
     */
    public int process(MoneyInfo[] moneyInfos, int currentIndex, int leftAmount) {
        // 剩余目标金额为0,发现一种有效组合
        if (leftAmount == 0) {
            return 1;
        }
        // 币值组合总额超出目标金额,非有效组合
        if (leftAmount < 0) {
            return 0;
        }
        // 上述情况未命中时,剩余目标金额必定>0,此时已无可选择的币值,之前的选择组合金额未达到目标金额,非有效组合
        if (currentIndex >= moneyInfos.length) {
            return 0;
        }

        int ways = 0;
        MoneyInfo currentMoney = moneyInfos[currentIndex];
        for (int chooseNum = 0; chooseNum <= currentMoney.num; chooseNum++) {
            int leftAmountAfterChoose = leftAmount - chooseNum * currentMoney.moneyValue;
            if (leftAmountAfterChoose < 0) {
                break;
            }
            ways += process(moneyInfos, currentIndex + 1, leftAmountAfterChoose);
        }
        return ways;
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
            Solution solution = new Solution();
            int ways = solution.calculateWays(moneyArr, aimAmount);
            System.out.println("有效组合数:" + ways);
        }

        @Test
        public void test2() {
            for (int i = 0; i < 1000; i++) {
                int[] moneyArr = ArrayUtil.randomIntArray(20, 1, 5);
                int aimAmount = new Random().nextInt(100);
                Solution solution = new Solution();
                int ways = solution.calculateWays(moneyArr, aimAmount);
                int waysCompare = Code04_CoinsWaySameValueSamePapper.dp1(moneyArr, aimAmount);
                assert ways == waysCompare;
            }
            System.out.println("Good,you success!");
        }
    }

}
