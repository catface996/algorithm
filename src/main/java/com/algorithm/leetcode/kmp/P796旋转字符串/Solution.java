package com.algorithm.leetcode.kmp.P796旋转字符串;

//给定两个字符串, A 和 B。
//
// A 的旋转操作就是将 A 最左边的字符移动到最右边。 例如, 若 A = 'abcde'，在移动一次之后结果就是'bcdea' 。如果在若干次旋转操作之后
//，A 能变成B，那么返回True。
//
//
//示例 1:
//输入: A = 'abcde', B = 'cdeab'
//输出: true
//
//示例 2:
//输入: A = 'abcde', B = 'abced'
//输出: false
//
// 注意：
//
//
// A 和 B 长度不超过 100。
//
// 👍 126 👎 0

import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 解题思路,A是B的旋转串,必定满足A是BB的子串

    //3:50 下午	info
    //				解答成功:
    //				执行耗时:1 ms,击败了32.29% 的Java用户
    //				内存消耗:36.4 MB,击败了52.76% 的Java用户

    public boolean rotateString(String A, String B) {
        if (A == null || B == null) {
            return false;
        }
        if (A.length() != B.length()) {
            return false;
        }
        if ("".equals(A) && "".equals(B)) {
            return true;
        }
        String BB = B + B;
        int index = indexOf(BB, A);
        return index >= 0;
    }

    private int indexOf(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() < str2.length() || str2.length() < 1) {
            return -1;
        }
        int[] next = buildNext(str2);
        int i = 0;
        int j = 0;
        while (i < str1.length() && j < str2.length()) {
            if (str1.charAt(i) == str2.charAt(j)) {
                i++;
                j++;
            } else if (next[j] != -1) {
                j = next[j];
            } else {
                i++;
            }
        }
        return j == str2.length() ? i - j : -1;
    }

    private int[] buildNext(String str) {
        if (str.length() == 1) {
            return new int[] {-1};
        }
        int[] next = new int[str.length()];
        next[0] = -1;
        int i = 2;
        int cn = 0;
        while (i < str.length()) {
            if (str.charAt(i - 1) == str.charAt(cn)) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

    public static class TestClass {
        //示例 1:
        //输入: A = 'abcde', B = 'cdeab'
        //输出: true
        @Test
        public void test1() {
            String A = "abcde";
            String B = "cdeab";
            Solution solution = new Solution();
            boolean ans = solution.rotateString(A, B);
            System.out.println(ans);
        }

        //示例 2:
        //输入: A = 'abcde', B = 'abced'
        //输出: false
        @Test
        public void test2() {
            String A = "abcde";
            String B = "abced";
            Solution solution = new Solution();
            boolean ans = solution.rotateString(A, B);
            System.out.println(ans);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

