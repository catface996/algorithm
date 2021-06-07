package com.algorithm.leetcode.动态规划.P516最长回文子序列;

import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/13 2:45 下午
 */
public class SolutionUseDp {

    //给定一个字符串str，返回这个字符串的最长回文子序列长度
    //比如 ： str = “a12b3c43def2ghi1kpm”
    //最长回文子序列是“1234321”或者“123c321”，返回长度7
    //解题思路:最长回文子序列,原文翻转后,求最长公共子序列即可.

    public int maxPalindromicSubSeq(String str) {
        char[] strArr = str.toCharArray();
        return process(strArr);
    }

    private int process(char[] chars) {

        int[][] dp = new int[chars.length][chars.length];
        for (int i = 0; i < chars.length; i++) {
            dp[i][i] = 1;
        }

        for (int start = chars.length - 2; start >= 0; start--) {
            for (int end = start + 1; end < chars.length; end++) {
                int maxLength = Math.max(dp[start][end - 1], dp[start + 1][end]);
                // 保留左侧,也保留右侧,求回文长度,前提是左右两侧字符相同
                if (chars[start] == chars[end]) {
                    maxLength = Math.max(maxLength, 2 + dp[start + 1][end - 1]);
                }
                dp[start][end] = maxLength;
            }
        }

        return dp[0][chars.length - 1];
    }

    public static class TestClass {

        //比如 ： str = “a12b3c43def2ghi1kpm”
        //最长回文子序列是“1234321”或者“123c321”，返回长度7
        @Test
        public void test1() {
            String str = "a12b3c43def2ghi1kpm";
            SolutionUseDp solutionUseDp = new SolutionUseDp();
            int maxLength = solutionUseDp.maxPalindromicSubSeq(str);
            System.out.println("最大回文长度:" + maxLength);
        }

        //比如 ： str = “12376321”
        //最长回文子序列是“1237321”或者“1236321”，返回长度7
        @Test
        public void test2() {
            String str = "12376321";
            SolutionUseDp solutionUseDp = new SolutionUseDp();
            int maxLength = solutionUseDp.maxPalindromicSubSeq(str);
            System.out.println("最大回文长度:" + maxLength);
        }


        // 5
        @Test
        public void test3() {
            String str = "7232764321";
            SolutionUseDp solutionUseDp = new SolutionUseDp();
            int maxLength = solutionUseDp.maxPalindromicSubSeq(str);
            System.out.println("最大回文长度:" + maxLength);
        }
    }
}
