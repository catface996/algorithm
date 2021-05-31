package leetcode.动态规划.P97_交错字符串;

//给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。
//
// 两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：
//
//
// s = s1 + s2 + ... + sn
// t = t1 + t2 + ... + tm
// |n - m| <= 1
// 交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
//
//
// 提示：a + b 意味着字符串 a 和 b 连接。
//
//
//
// 示例 1：
//
//
//输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
//输出：true
//
//
// 示例 2：
//
//
//输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
//输出：false
//
//
// 示例 3：
//
//
//输入：s1 = "", s2 = "", s3 = ""
//输出：true
//
//
//
//
// 提示：
//
//
// 0 <= s1.length, s2.length <= 100
// 0 <= s3.length <= 200
// s1、s2、和 s3 都由小写英文字母组成
//
// Related Topics 字符串 动态规划
// 👍 454 👎 0

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
@Slf4j
public class Solution2 {

    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null) {
            return false;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        char[] str3 = s3.toCharArray();
        if (str3.length != str1.length + str2.length) {
            return false;
        }
        boolean[][] dp = new boolean[str1.length + 1][str2.length + 1];
        dp[0][0] = true;
        for (int i = 1; i <= str1.length; i++) {
            if (str1[i - 1] != str3[i - 1]) {
                break;
            }
            dp[i][0] = true;
        }
        for (int j = 1; j <= str2.length; j++) {
            if (str2[j - 1] != str3[j - 1]) {
                break;
            }
            dp[0][j] = true;
        }
        for (int i = 1; i <= str1.length; i++) {
            for (int j = 1; j <= str2.length; j++) {
                if (
                    (str1[i - 1] == str3[i + j - 1] && dp[i - 1][j])
                        ||
                        (str2[j - 1] == str3[i + j - 1] && dp[i][j - 1])
                ) {
                    dp[i][j] = true;
                }
            }
        }
        return dp[str1.length][str2.length];
    }

    public static class TestClass {

        //
        // 示例 1：
        //
        //
        //输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
        //输出：true
        @Test
        public void test1() {
            String s1 = "aabcc";
            String s2 = "dbbca";
            String s3 = "aadbbcbcac";
            Solution2 solution = new Solution2();
            boolean ans = solution.isInterleave(s1, s2, s3);
            log.info("ans:{}", ans);
        }

        //
        // 示例 2：
        //
        //
        //输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
        //输出：false
        @Test
        public void test2() {
            String s1 = "aabcc";
            String s2 = "dbbca";
            String s3 = "aadbbbaccc";
            Solution2 solution = new Solution2();
            boolean ans = solution.isInterleave(s1, s2, s3);
            log.info("ans:{}", ans);
        }

        //
        // 示例 3：
        //
        //
        //输入：s1 = "", s2 = "", s3 = ""
        //输出：true
        @Test
        public void test3() {
            String s1 = "";
            String s2 = "";
            String s3 = "";
            Solution2 solution = new Solution2();
            boolean ans = solution.isInterleave(s1, s2, s3);
            log.info("ans:{}", ans);
        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)

