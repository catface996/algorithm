package leetcode.动态规划.P516最长回文子序列;

/**
 * @author by catface
 * @date 2021/4/13 4:10 下午
 */
public class Solution {

    public int longestPalindromeSubseq(String s) {
        char[] strArr = s.toCharArray();
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
}
