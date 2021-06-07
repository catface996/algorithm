package com.algorithm.leetcode.manacher.P5最长回文子串;

//给你一个字符串 s，找到 s 中最长的回文子串。
//
//
//
// 示例 1：
//
//
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
//
//
// 示例 2：
//
//
//输入：s = "cbbd"
//输出："bb"
//
//
// 示例 3：
//
//
//输入：s = "a"
//输出："a"
//
//
// 示例 4：
//
//
//输入：s = "ac"
//输出："a"
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 1000
// s 仅由数字和英文字母（大写和/或小写）组成
//
// Related Topics 字符串 动态规划
// 👍 3590 👎 0

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
@Slf4j
class Solution {

    // 5:22 下午	info
    //				解答成功:
    //				执行耗时:9 ms,击败了96.17% 的Java用户
    //				内存消耗:38.6 MB,击败了75.56% 的Java用户
    // manacher解决方案

    public String longestPalindrome(String s) {
        if (s == null || "".equals(s)) {
            return s;
        }
        char[] preStr = preProcessForPalindrome(s);
        int[] radiusArr = new int[preStr.length];
        radiusArr[0] = 1;
        int c = 0;
        int maxR = 0;
        int i = 0;
        int ans = 1;
        int maxC = 0;
        while (i < preStr.length - 1) {
            i++;
            // 如果要左右延伸探测时的起点
            int left = i - 1;
            int right = i + 1;
            if (i < maxR) {
                // 当i位置落在最大右回文边界以内,必定存在以c为中心的对称位置i',检查i'位置记录的回文半径,判断i'位置的回文半径是否超过左侧的L
                int leftI = (c << 1) - i;
                int radius = radiusArr[leftI];
                if (i + radius - 1 < maxR) {
                    // i位置的回文半径最右侧仍旧在最大回文右边界以内,i位置扩展出的回文无需再左右扩展探测
                    radiusArr[i] = radiusArr[leftI];
                    continue;
                }
                // 更改已i位置为中心时,左右延伸探测的起点
                left = i * 2 - maxR - 1;
                right = maxR + 1;
            }

            // i> 最大回文右边界 或者 i<=最大回文右边界且i位置的回文半径右边界超过了最大回文右边界
            while (left >= 0 && right < preStr.length) {
                if (preStr[left] == preStr[right]) {
                    left--;
                    right++;
                } else {
                    break;
                }
            }

            // 计算新的最大回文右边界,计算新的中心点,填充i位置的回文半径,计算新的最大回文直径
            maxR = right - 1;
            c = i;
            radiusArr[i] = maxR - c + 1;
            if ((maxR - c) * 2 + 1 > ans) {
                maxC = c;
            }
            ans = Math.max(ans, (maxR - c) * 2 + 1);
        }
        StringBuilder sb = new StringBuilder();
        // 根据最长回文的中心点,获取字符串
        for (int index = maxC - radiusArr[maxC] + 1; index < maxC + radiusArr[maxC]; index++) {
            if (preStr[index] == '#') {
                continue;
            }
            sb.append(preStr[index]);
        }
        return sb.toString();
    }

    public char[] preProcessForPalindrome(String str) {
        char[] preChars = new char[str.length() * 2 + 1];
        int preIndex = 0;
        for (int i = 0; i < str.length(); i++) {
            preChars[preIndex++] = '#';
            preChars[preIndex++] = str.charAt(i);
        }
        preChars[preIndex] = '#';
        return preChars;
    }

    public static class TestClass {
        @Test
        public void test1() {
            String s = "";
            Solution solution = new Solution();
            String ans = solution.longestPalindrome(s);
            log.info("ans:{}", ans);
        }

        @Test
        public void test12() {
            String s = null;
            Solution solution = new Solution();
            String ans = solution.longestPalindrome(s);
            log.info("ans:{}", ans);
        }

        @Test
        public void test3() {
            String s = "a";
            Solution solution = new Solution();
            String ans = solution.longestPalindrome(s);
            log.info("ans:{}", ans);
        }

        @Test
        public void test4() {
            String s = "ab";
            Solution solution = new Solution();
            String ans = solution.longestPalindrome(s);
            log.info("ans:{}", ans);
        }

        @Test
        public void test5() {
            String s = "aba";
            Solution solution = new Solution();
            String ans = solution.longestPalindrome(s);
            log.info("ans:{}", ans);
        }

        @Test
        public void test6() {
            String s = "abaaba";
            Solution solution = new Solution();
            String ans = solution.longestPalindrome(s);
            log.info("ans:{}", ans);
        }

        @Test
        public void test7() {
            String s = "85274658432433811226";
            Solution solution = new Solution();
            String ans = solution.longestPalindrome(s);
            log.info("ans:{}", ans);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

