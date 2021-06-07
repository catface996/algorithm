package com.algorithm.course.暴力递归动态规划.最长回文子序列;

import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/12 7:56 下午
 */
public class Solution {

    //给定一个字符串str，返回这个字符串的最长回文子序列长度
    //比如 ： str = “a12b3c43def2ghi1kpm”
    //最长回文子序列是“1234321”或者“123c321”，返回长度7
    //解题思路:最长回文子序列,原文翻转后,求最长公共子序列即可.

    public int maxPalindromicSubSeq(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            sb.append(str.charAt(i));
        }
        String revert = sb.toString();
        return maxCommonSubSeq(str, revert);
    }

    public int maxCommonSubSeq(String str, String revert) {
        return process(str, 0, revert, 0);
    }

    public int process(String str, int start1, String revert, int start2) {
        // 两个字符串中的任何一个已遍历结束,另外一个未结束,公共子序列长度为0
        if (start1 == str.length() - 1 && start2 == revert.length() - 1) {
            return 1;
        }

        if (start1 == str.length() - 1 && start2 < revert.length() - 1) {
            if (str.charAt(start1) == revert.charAt(start2)) {
                return 1;
            }
            return process(str, start1, revert, start2 + 1);
        }

        if (start1 < str.length() - 1 && start2 == revert.length() - 1) {
            if (str.charAt(start1) == revert.charAt(start2)) {
                return 1;
            }
            return process(str, start1 + 1, revert, start2);
        }

        // 第一个字符选不保留前位置,第二个字符串保留当前位置
        int maxLength = process(str, start1 + 1, revert, start2);
        // 第一个字符串保留当前位置,第二个字符串不保留当前位置
        maxLength = Math.max(maxLength, process(str, start1, revert, start2 + 1));
        // 第一个字符串和第二个字符串均保留当前位置,且当前位置字符串相同
        if (str.charAt(start1) == revert.charAt(start2)) {
            maxLength = Math.max(maxLength, 1 + process(str, start1 + 1, revert, start2 + 1));
        }
        return maxLength;
    }

    public static class TestClass {
        //比如 ： str = “a12b3c43def2ghi1kpm”
        //最长回文子序列是“1234321”或者“123c321”，返回长度7
        @Test
        public void test1() {
            String str = "12376321";
            Solution solution = new Solution();
            int maxLength = solution.maxPalindromicSubSeq(str);
            System.out.println("最大回文长度:" + maxLength);
        }

        //比如 ： str = “12376321”
        //最长回文子序列是“1237321”或者“1236321”，返回长度7
        @Test
        public void test2() {
            String str = "12376321";
            Solution solution = new Solution();
            int maxLength = solution.maxPalindromicSubSeq(str);
            System.out.println("最大回文长度:" + maxLength);
        }
    }
}
