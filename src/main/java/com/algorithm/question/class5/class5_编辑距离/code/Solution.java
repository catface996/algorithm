package com.algorithm.question.class5.class5_编辑距离.code;

import org.junit.Test;

/**
 * @author by catface
 * @date 2021/6/1 4:29 下午
 */
public class Solution {

    /**
     * 插入成本
     */
    int insertCost;
    /**
     * 删除成本
     */
    int deleteCost;
    /**
     * 替换成本
     */
    int replaceCost;
    /**
     * 源字符串
     */
    private String s1;
    /**
     * 目标字符串
     */
    private String s2;

    /**
     * 编辑距离
     *
     * @param s1          源字符串
     * @param s2          目标字符串
     * @param insertCost  插入成本
     * @param deleteCost  删除成本
     * @param replaceCost 替换成本
     * @return 最小成本
     */
    public int minEditCost(String s1, String s2, int insertCost, int deleteCost, int replaceCost) {
        this.insertCost = insertCost;
        this.deleteCost = deleteCost;
        this.replaceCost = replaceCost;
        this.s1 = s1;
        this.s2 = s2;
        return process(s1.length(), s2.length());
    }

    /**
     * 由 length1 编辑到 length2 的长度,需要付出的代价
     *
     * @param length1 源串的长度
     * @param length2 目标串的长度
     * @return 最小代价
     */
    public int process(int length1, int length2) {
        // 源串长度为0时,只能通过添加字符来构成目标串
        if (length1 == 0) {
            return length2 * insertCost;
        }
        // 目标串长度为0时,只能通过删除字符来构成目标串
        if (length2 == 0) {
            return length1 * deleteCost;
        }

        int minCost = Integer.MAX_VALUE;

        // 总共有四种情况,分别是 添加,删除,保留,替换(选择替换 或者 删除+添加)
        // 添加一个字符构成目标字符串,注意,无需判断length<length2 含义是,让length1长度的串变成length2-1长度的串的代价+插入一个字符的代价
        minCost = Math.min(minCost, process(length1, length2 - 1) + insertCost);

        // 删除一个字符串构成目标字符串,让length-1长度的串变成length2长度的串的代价+删除一个字符的代价
        minCost = Math.min(minCost, process(length1 - 1, length2) + deleteCost);

        if (s1.charAt(length1 - 1) == s2.charAt(length2 - 1)) {
            // 只有当末尾两个字符串相同时,允许保留一个字符串构成目标字符串
            minCost = Math.min(minCost, process(length1 - 1, length2 - 1));
        } else {
            // 替换一个字符,构成目标字符
            int minReplaceCost = Math.min(replaceCost, deleteCost + insertCost);
            minCost = Math.min(minCost, process(length1 - 1, length2 - 1) + minReplaceCost);
        }

        return minCost;
    }

    public static class TestClass {
        @Test
        public void test1() {
            String s1 = "love", s2 = "lolpe";
            Solution solution = new Solution();
            int minCost = solution.minEditCost(s1, s2, 1, 1, 1);
            System.out.println(minCost);
        }

        // 示例 1：
        //
        //
        //输入：word1 = "horse", word2 = "ros"
        //输出：3
        //解释：
        //horse -> rorse (将 'h' 替换为 'r')
        //rorse -> rose (删除 'r')
        //rose -> ros (删除 'e')
        @Test
        public void test2() {
            String s1 = "horse", s2 = "ros";
            Solution solution = new Solution();
            int minCost = solution.minEditCost(s1, s2, 1, 1, 1);
            System.out.println(minCost);
        }

        //
        //
        // 示例 2：
        //
        //
        //输入：word1 = "intention", word2 = "execution"
        //输出：5
        //解释：
        //intention -> inention (删除 't')
        //inention -> enention (将 'i' 替换为 'e')
        //enention -> exention (将 'n' 替换为 'x')
        //exention -> exection (将 'n' 替换为 'c')
        //exection -> execution (插入 'u')
        @Test
        public void test3() {
            String s1 = "intention", s2 = "execution";
            Solution solution = new Solution();
            int minCost = solution.minEditCost(s1, s2, 1, 1, 1);
            System.out.println(minCost);
        }
    }

}
