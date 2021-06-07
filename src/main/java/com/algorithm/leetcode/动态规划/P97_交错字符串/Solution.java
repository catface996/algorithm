package com.algorithm.leetcode.动态规划.P97_交错字符串;

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
public class Solution {

    // 11:37 上午	info
    //					运行失败:
    //					Time Limit Exceeded
    //					测试用例:
    //					stdout:

    private String s1;
    private String s2;
    private String s3;

    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null || s2 == null || s3 == null) {
            return false;
        }
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;

        return process(0, 0, 0, true) || process(0, 0, 0, false);
    }

    /**
     * 递归计算是否是交错字符串
     *
     * @param index1   在字符串1上匹配到的位置
     * @param index2   在字符串2上匹配到的位置
     * @param index3   目标串上匹配到的位置
     * @param oneOrTwo 上一次选择是匹配了1串还是2串
     * @return true:是交替串,false 不是交替串
     */
    private boolean process(int index1, int index2, int index3, boolean oneOrTwo) {
        // 目标串匹配结束
        if (index3 >= s3.length()) {
            return index1 >= s1.length() && index2 >= s2.length();
        }

        int length = 0;
        if (oneOrTwo) {
            // 上次匹配了1串,本次要匹配2串
            while (index2 + length < s2.length() && index3 + length < s3.length()) {
                if (s2.charAt(index2 + length) == s3.charAt(index3 + length)) {
                    length++;
                } else {
                    break;
                }
            }
            for (int i = 1; i <= length; i++) {
                boolean result = process(index1, index2 + i, index3 + i, false);
                if (result) {
                    return true;
                }
            }
        } else {
            // 上次选择了2串,本次要选择1串
            while (index1 + length < s1.length() && index3 + length < s3.length()) {
                if (s1.charAt(index1 + length) == s3.charAt(index3 + length)) {
                    length++;
                } else {
                    break;
                }
            }
            for (int i = 1; i <= length; i++) {
                boolean result = process(index1 + i, index2, index3 + i, true);
                if (result) {
                    return true;
                }
            }
        }
        return false;
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
            Solution solution = new Solution();
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
            Solution solution = new Solution();
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
            Solution solution = new Solution();
            boolean ans = solution.isInterleave(s1, s2, s3);
            log.info("ans:{}", ans);
        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)

