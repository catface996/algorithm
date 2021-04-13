package leetcode.动态规划.P1143最长公共子序列;

import org.junit.Test;
//给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
//
// 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
//
//
// 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
//
//
// 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
//
//
//
// 示例 1：
//
//
//输入：text1 = "abcde", text2 = "ace"
//输出：3
//解释：最长公共子序列是 "ace" ，它的长度为 3 。
//
//
// 示例 2：
//
//
//输入：text1 = "abc", text2 = "abc"
//输出：3
//解释：最长公共子序列是 "abc" ，它的长度为 3 。
//
//
// 示例 3：
//
//
//输入：text1 = "abc", text2 = "def"
//输出：0
//解释：两个字符串没有公共子序列，返回 0 。
//
//
//
//
// 提示：
//
//
// 1 <= text1.length, text2.length <= 1000
// text1 和 text2 仅由小写英文字符组成。
//
// Related Topics 动态规划
// 👍 517 👎 0

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * @author by catface
 * @date 2021/4/12 6:50 下午
 */
public class SolutionUseDpCompress {

    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text1.length() <= 0 || text2 == null || text2.length() <= 0) {
            return 0;
        }
        char[] str1Arr = text1.toCharArray();
        char[] str2Arr = text2.toCharArray();
        if (str1Arr.length < str2Arr.length) {
            return process(str2Arr, str1Arr);
        } else {
            return process(str1Arr, str2Arr);
        }
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

    //leetcode submit region end(Prohibit modification and deletion)

    public static class TestClass {
        //比如 ： str1 = “”,str2 = “”
        @Test
        public void test1() {
            String str1 = "";
            String str2 = "";
            SolutionUseDpCompress solution = new SolutionUseDpCompress();
            int maxLength = solution.longestCommonSubsequence(str1, str2);
            System.out.println(maxLength);
            assert maxLength == 0;
        }

        //比如 ： str1 = “a12b3c456d”,str2 = “1ef23ghi4j56k”
        //最长公共子序列是“123456”，所以返回长度6
        @Test
        public void test2() {
            String str1 = "a12b3c43def2ghi1kpm";
            String str2 = "mpk1ihg2fed34c3b21a";
            SolutionUseDpCompress solution = new SolutionUseDpCompress();
            int maxLength = solution.longestCommonSubsequence(str1, str2);
            System.out.println(maxLength);
            assert maxLength == 7;
        }

        @Test
        public void test3() {
            String str1 = "12376321";
            String str2 = "12367321";
            SolutionUseDpCompress solution = new SolutionUseDpCompress();
            int maxLength = solution.longestCommonSubsequence(str1, str2);
            System.out.println(maxLength);
            assert maxLength == 7;
        }

        //测试用例:"abcba"            "abcbcba"
        //期望结果:5
        @Test
        public void test4() {
            String str1 = "abcba";
            String str2 = "abcbcba";
            SolutionUseDpCompress solution = new SolutionUseDpCompress();
            int maxLength = solution.longestCommonSubsequence(str1, str2);
            System.out.println(maxLength);
            assert maxLength == 5;
        }

        //测试用例:"abcde"            "ace"
        //期望结果:3
        @Test
        public void test5() {
            String str1 = "abcde";
            String str2 = "ace";
            SolutionUseDpCompress solution = new SolutionUseDpCompress();
            int maxLength = solution.longestCommonSubsequence(str1, str2);
            System.out.println(maxLength);
            assert maxLength == 3;
        }

    }
}
