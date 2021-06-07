package com.algorithm.course.暴力递归动态规划.背包问题;

import java.util.Arrays;

import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/12 1:39 下午
 */
public class SolutionUseCache {
    //给定两个长度都为N的数组weights和values，
    //weights[i]和values[i]分别代表 i号物品的重量和价值。
    //给定一个正数bag，表示一个载重bag的袋子，
    //你装的物品不能超过这个重量。
    //返回你能装下最多的价值是多少?

    /**
     * 计算背包可以容纳的最大价值
     *
     * @param weights 物品重量列表
     * @param values  物品价值列表
     * @param bag     背包的最大容量
     * @return 背包能装下的最大价值
     */
    public int calculateMaxValue(int[] weights, int[] values, int bag) {
        if (weights == null || values == null || values.length <= 0 || bag < 0
            || weights.length != values.length) {
            return 0;
        }
        int[][] cache = new int[weights.length][bag + 1];
        initCache(cache);
        return process(weights, values, 0, weights.length - 1, bag, cache);
    }

    private int process(int[] weights, int[] values, int start, int end, int leftCapacity, int[][] cache) {

        if (start == end) {
            if (leftCapacity >= weights[start]) {
                return values[start];
            }
            return 0;
        }
        int maxValue = cache[start][leftCapacity];
        if (maxValue != -1) {
            return maxValue;
        }

        // 选择当前的物品装入
        // 当前背包升入容量足够装下start位置的物品,并且选择装入
        int chooseStart = 0;
        if (weights[start] <= leftCapacity) {
            chooseStart = values[start] + process(weights, values, start + 1, end, leftCapacity - weights[start],
                cache);
        }

        // 无论当前背包容量剩余多少,都不选择装入start位置的物品
        int notChooseStart = process(weights, values, start + 1, end, leftCapacity, cache);

        maxValue = Math.max(chooseStart, notChooseStart);
        cache[start][leftCapacity] = maxValue;
        return maxValue;
    }

    private void initCache(int[][] cache) {
        for (int[] ints : cache) {
            Arrays.fill(ints, -1);
        }
    }

    public static class TestClass {
        @Test
        public void test1() {
            int[] weights = {3, 2, 4, 7, 3, 1, 3, 9, 4};
            int[] values = {5, 6, 3, 19, 12, 4, 20, 6, 19};
            int bag = 25;
            Solution solution = new Solution();
            SolutionUseCache solutionUseCache = new SolutionUseCache();
            int maxValue = solution.calculateMaxValue(weights, values, bag);
            int maxValueUseCache = solutionUseCache.calculateMaxValue(weights, values, bag);
            System.out.println("背包能容纳的最大价值:" + " 方法1:" + maxValue + " 方法2:" + maxValueUseCache);
        }
    }
}
