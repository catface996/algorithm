package com.algorithm.course.暴力递归动态规划.数字串转字母串;

import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/12 2:45 下午
 */
public class Solution {

    //规定1和A对应、2和B对应、3和C对应...26和Z对应
    //那么一个数字字符串比如"111”就可以转化为:
    //"AAA"、"KA"和"AK"
    //给定一个只有数字字符组成的字符串str，返回有多少种转化结果
    // 0到9的ascii码对应的10进制为48到57

    public static final char ZERO = '0';

    public int calculateMaxType(String numberStr) {
        return process(numberStr, 0);
    }

    public int process(String numberStr, int currentIndex) {

        // base case
        if (currentIndex == numberStr.length()) {
            return 1;
        }

        char currentChar = numberStr.charAt(currentIndex);
        if (currentChar == ZERO) {
            return 0;
        }

        // 选择当前位置的数字转换成字符
        int chooseCurrent = process(numberStr, currentIndex + 1);

        // 不选择当前数字转字符,选当前两位
        int notChooseCurrent = 0;
        // 取当前数字,如果当前数字非0,可以转换成字母
        if (currentIndex + 1 < numberStr.length()) {
            char nextChar = numberStr.charAt(currentIndex + 1);
            if ((currentChar - ZERO) * 10 + (nextChar - ZERO) <= 26) {
                notChooseCurrent = process(numberStr, currentIndex + 2);
            }
        }
        return chooseCurrent + notChooseCurrent;
    }

    // str只含有数字字符0~9
    // 返回多少种转化方案
    public int number(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        return process(str.toCharArray(), 0);
    }

    // str[0..i-1]转化无需过问
    // str[i.....]去转化，返回有多少种转化方法
    public int process(char[] str, int i) {
        if (i == str.length) {
            return 1;
        }
        // i没到最后，说明有字符
        if (str[i] == '0') { // 之前的决定有问题
            return 0;
        }
        // str[i] != '0'
        // 可能性一，i单转
        int ways = process(str, i + 1);
        if (i + 1 < str.length && (str[i] - '0') * 10 + str[i + 1] - '0' < 27) {
            ways += process(str, i + 2);
        }
        return ways;
    }

    public static class TestClass {
        @Test
        public void test1() {
            String numberStr = "7210231231232031203123";
            Solution solution = new Solution();
            int maxTypes = solution.calculateMaxType(numberStr);
            int maxTypes2 = solution.number(numberStr);
            System.out.println("转换方法数1:" + maxTypes + " 转换方法数2:" + maxTypes2);
        }
    }
}
