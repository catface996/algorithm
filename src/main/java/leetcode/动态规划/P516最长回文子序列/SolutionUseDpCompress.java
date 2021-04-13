package leetcode.动态规划.P516最长回文子序列;

import java.util.Arrays;

import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/13 2:45 下午
 */
public class SolutionUseDpCompress {

    //给定一个字符串str，返回这个字符串的最长回文子序列长度
    //比如 ： str = “a12b3c43def2ghi1kpm”
    //最长回文子序列是“1234321”或者“123c321”，返回长度7
    //解题思路:最长回文子序列,原文翻转后,求最长公共子序列即可.

    //4:22 下午	info
    //				解答成功:
    //				执行耗时:18 ms,击败了98.47% 的Java用户
    //				内存消耗:36.4 MB,击败了99.26% 的Java用户

    public int longestPalindromeSubseq(String s) {
        char[] strArr = s.toCharArray();
        return process(strArr);
    }

    private int process(char[] chars) {
        int[] dp = new int[chars.length];
        Arrays.fill(dp, 1);
        int nextStartLastEnd;
        int maxLength;
        for (int start = chars.length - 2; start >= 0; start--) {
            nextStartLastEnd = 0;
            for (int end = start + 1; end < chars.length; end++) {
                maxLength = Math.max(dp[end - 1], dp[end]);
                // 保留左侧,也保留右侧,求回文长度,前提是左右两侧字符相同
                if (chars[start] == chars[end]) {
                    maxLength = Math.max(maxLength, 2 + nextStartLastEnd);
                }
                nextStartLastEnd = dp[end];
                dp[end] = maxLength;
            }
        }
        return dp[chars.length - 1];
    }

    public static class TestClass {

        //比如 ： str = “a12b3c43def2ghi1kpm”
        //最长回文子序列是“1234321”或者“123c321”，返回长度7
        @Test
        public void test1() {
            String str = "a12b3c43def2ghi1kpm";
            SolutionUseDpCompress solutionUseDp = new SolutionUseDpCompress();
            int maxLength = solutionUseDp.longestPalindromeSubseq(str);
            System.out.println("最大回文长度:" + maxLength);
        }

        //比如 ： str = “12376321”
        //最长回文子序列是“1237321”或者“1236321”，返回长度7
        @Test
        public void test2() {
            String str = "12376321";
            SolutionUseDpCompress solutionUseDp = new SolutionUseDpCompress();
            int maxLength = solutionUseDp.longestPalindromeSubseq(str);
            System.out.println("最大回文长度:" + maxLength);
        }

        // 5
        @Test
        public void test3() {
            String str = "7232764321";
            SolutionUseDpCompress solutionUseDp = new SolutionUseDpCompress();
            int maxLength = solutionUseDp.longestPalindromeSubseq(str);
            System.out.println("最大回文长度:" + maxLength);
        }
    }
}
