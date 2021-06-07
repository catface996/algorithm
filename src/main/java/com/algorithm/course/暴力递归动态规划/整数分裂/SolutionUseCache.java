package com.algorithm.course.暴力递归动态规划.整数分裂;

import com.algorithm.util.ArrayUtil;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/16 2:24 下午
 */
public class SolutionUseCache {

    //给定一个正数n，求n的裂开方法数，
    //规定：后面的数不能比前面的数小
    //比如4的裂开方法有：
    //1+1+1+1、1+1+2、1+3、2+2、4
    //5种，所以返回5

    public int splitInteger(int number) {
        int[][] cache = ArrayUtil.initCache(number + 1, number + 1, -1);
        return process(1, number, cache);
    }

    public int process(int currentInt, int leftNumber, int[][] cache) {
        // 剩余数字为0,找到一种分裂方案
        if (leftNumber == 0) {
            return 1;
        }
        // 剩余可分裂的数字小于0,说明之前的分裂组合无效
        if (leftNumber < 0) {
            return 0;
        }
        // 在以上情况未命中的前提下,尝试用于裂变的数字已经超过剩余的数值,无效的分裂组合
        if (currentInt > leftNumber) {
            return 0;
        }
        // 如果命中缓存,直接返回
        int ans = cache[currentInt][leftNumber];
        if (ans != -1) {
            return ans;
        }
        // 常规情况下
        int ways = 0;
        for (int chooseTimes = 0; chooseTimes * currentInt <= leftNumber; chooseTimes++) {
            ways += process(currentInt + 1, leftNumber - chooseTimes * currentInt, cache);
        }
        return ways;
    }

    public static class TestClass {
        @Test
        public void test1() {
            int number = 4;
            SolutionUseCache solution = new SolutionUseCache();
            int ways = solution.splitInteger(number);
            System.out.println("分裂方案数:" + ways);
        }

        @Test
        public void test2() {
            for (int number = 1; number < 50; number++) {
                SolutionUseCache solution = new SolutionUseCache();
                int ways = solution.splitInteger(number);
                int ways2 = Code03_SplitNumber.dp1(number);
                if (ways != ways2) {
                    System.out.println("裂变不一致:" + number);
                }
                assert ways == ways2;
            }
            System.out.println("Good,you success!");
        }
    }

}
