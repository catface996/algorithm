package com.algorithm.course.暴力递归动态规划.最长公共子序列;

import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/12 6:50 下午
 */
public class SolutionUseCompressDpSpace {

    //给定两个字符串str1和str2，
    //返回这两个字符串的最长公共子序列长度
    //
    //比如 ： str1 = “a12b3c456d”,str2 = “1ef23ghi4j56k”
    //最长公共子序列是“123456”，所以返回长度6

    public int maxCommonSubSeq(String str1, String str2) {
        if (str1 == null || str1.length() <= 0 || str2 == null || str2.length() <= 0) {
            return 0;
        }
        char[] str1Arr = new char[str1.length()];
        for (int i = 0; i < str1.length(); i++) {
            str1Arr[i] = str1.charAt(i);
        }
        char[] str2Arr = new char[str2.length()];
        for (int i = 0; i < str2.length(); i++) {
            str2Arr[i] = str2.charAt(i);
        }
        return process(str1Arr, str2Arr);
    }
    
    private int process(char[] str1Arr, char[] str2Arr) {
        int[] dp = new int[str2Arr.length + 1];
        int temp;
        for (int row = str1Arr.length - 1; row >= 0; row--) {
            temp = 0;
            for (int col = str2Arr.length - 1; col >= 0; col--) {
                int colValue = dp[col];
                int colNextValue = dp[col + 1];
                int newColValue = Math.max(colValue, colNextValue);
                if (str1Arr[row] == str2Arr[col]) {
                    newColValue = Math.max(newColValue, temp + 1);
                }
                temp = colValue;
                if (newColValue > colValue) {
                    dp[col] = newColValue;
                }
            }
        }
        return dp[0];
    }

    public static class TestClass {
        //比如 ： str1 = “”,str2 = “”
        @Test
        public void test1() {
            String str1 = "";
            String str2 = "";
            SolutionUseCompressDpSpace solution = new SolutionUseCompressDpSpace();
            int maxLength = solution.maxCommonSubSeq(str1, str2);
            System.out.println(maxLength);
            assert maxLength == 0;
        }

        //比如 ： str1 = “a12b3c456d”,str2 = “1ef23ghi4j56k”
        //最长公共子序列是“123456”，所以返回长度6
        @Test
        public void test2() {
            String str1 = "a12b3c43def2ghi1kpm";
            String str2 = "mpk1ihg2fed34c3b21a";
            SolutionUseCompressDpSpace solution = new SolutionUseCompressDpSpace();
            int maxLength = solution.maxCommonSubSeq(str1, str2);
            System.out.println(maxLength);
            assert maxLength == 7;
        }

        @Test
        public void test3() {
            String str1 = "12376321";
            String str2 = "12367321";
            SolutionUseCompressDpSpace solution = new SolutionUseCompressDpSpace();
            int maxLength = solution.maxCommonSubSeq(str1, str2);
            System.out.println(maxLength);
            assert maxLength == 7;
        }

        //测试用例:"abcba"            "abcbcba"
        //期望结果:5
        @Test
        public void test4() {
            String str1 = "abcba";
            String str2 = "abcbcba";
            SolutionUseCompressDpSpace solution = new SolutionUseCompressDpSpace();
            int maxLength = solution.maxCommonSubSeq(str1, str2);
            System.out.println(maxLength);
            assert maxLength == 5;
        }

        //测试用例:"abcde"            "ace"
        //期望结果:3
        @Test
        public void test5() {
            String str1 = "abcde";
            String str2 = "ace";
            SolutionUseCompressDpSpace solution = new SolutionUseCompressDpSpace();
            int maxLength = solution.maxCommonSubSeq(str1, str2);
            System.out.println(maxLength);
            assert maxLength == 3;
        }

    }
}
