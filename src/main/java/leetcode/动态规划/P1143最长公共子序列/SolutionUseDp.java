package leetcode.动态规划.P1143最长公共子序列;

import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/12 6:50 下午
 */
public class SolutionUseDp {

    //给定两个字符串str1和str2，
    //返回这两个字符串的最长公共子序列长度
    //
    //比如 ： str1 = “a12b3c456d”,str2 = “1ef23ghi4j56k”
    //最长公共子序列是“123456”，所以返回长度6

    public int maxCommonSubSeq(String str1, String str2) {
        if (str1 == null || str1.length() <= 0 || str2 == null || str2.length() <= 0) {
            return 0;
        }
        return process2(str1, str2);
    }

    /**
     * 求解最大公共子序列的长度
     *
     * @param str1 字符串1
     * @param str2 字符串2
     * @return 最大公共子序列长度
     */
    private int process(String str1, String str2) {

        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        // 两个字符串中的任何一个已遍历结束,另外一个未结束,公共子序列长度为0
        for (int i = 0; i <= str2.length(); i++) {
            dp[str1.length()][i] = 0;
        }
        for (int i = 0; i <= str1.length(); i++) {
            dp[i][str2.length()] = 0;
        }

        for (int start1 = str1.length() - 1; start1 >= 0; start1--) {
            for (int start2 = str2.length() - 1; start2 >= 0; start2--) {
                int maxLength = 0;
                // 第一个字符选不保留前位置,第二个字符串保留当前位置
                maxLength = Math.max(maxLength, dp[start1 + 1][start2]);
                // 第一个字符串保留当前位置,第二个字符串不保留当前位置
                maxLength = Math.max(maxLength, dp[start1][start2 + 1]);
                // 第一个字符串和第二个字符串均不保留当前位置,此处可以优化,见process2
                maxLength = Math.max(maxLength, dp[start1 + 1][start2 + 1]);
                // 第一个字符串和第二个字符串均保留当前位置,且当前位置字符串相同
                if (str1.charAt(start1) == str2.charAt(start2)) {
                    maxLength = Math.max(maxLength, 1 + dp[start1 + 1][start2 + 1]);
                }
                dp[start1][start2] = maxLength;
            }
        }

        return dp[0][0];
    }

    /**
     * 求解最大公共子序列的长度
     *
     * @param str1 字符串1
     * @param str2 字符串2
     * @return 最大公共子序列长度
     */
    private int process2(String str1, String str2) {

        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        // 两个字符串中的任何一个已遍历结束,另外一个未结束,公共子序列长度为0
        // 默认以初始化为0
        //for (int i = 0; i <= str2.length(); i++) {
        //    dp[str1.length()][i] = 0;
        //}
        //for (int i = 0; i <= str1.length(); i++) {
        //    dp[i][str2.length()] = 0;
        //}
        for (int start1 = str1.length() - 1; start1 >= 0; start1--) {
            for (int start2 = str2.length() - 1; start2 >= 0; start2--) {
                // 第一个字符选不保留前位置,第二个字符串保留当前位置
                int maxLength = Math.max(dp[start1 + 1][start2], dp[start1][start2 + 1]);
                // 第一个字符串保留当前位置,第二个字符串不保留当前位置
                // 第一个字符串和第二个字符串均保留当前位置,且当前位置字符串相同
                if (str1.charAt(start1) == str2.charAt(start2)) {
                    maxLength = Math.max(maxLength, 1 + dp[start1 + 1][start2 + 1]);
                }
                dp[start1][start2] = maxLength;
            }
        }

        return dp[0][0];
    }

    public static class TestClass {
        //比如 ： str1 = “”,str2 = “”
        @Test
        public void test1() {
            String str1 = "";
            String str2 = "";
            SolutionUseDp solution = new SolutionUseDp();
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
            SolutionUseDp solution = new SolutionUseDp();
            int maxLength = solution.maxCommonSubSeq(str1, str2);
            System.out.println(maxLength);
            assert maxLength == 7;
        }

        @Test
        public void test3() {
            String str1 = "12376321";
            String str2 = "12367321";
            SolutionUseDp solution = new SolutionUseDp();
            int maxLength = solution.maxCommonSubSeq(str1, str2);
            System.out.println(maxLength);
            assert maxLength == 7;
        }

    }
}
