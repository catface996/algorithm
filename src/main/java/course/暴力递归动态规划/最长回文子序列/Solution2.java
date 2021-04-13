package course.暴力递归动态规划.最长回文子序列;

import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/13 2:45 下午
 */
public class Solution2 {

    //给定一个字符串str，返回这个字符串的最长回文子序列长度
    //比如 ： str = “a12b3c43def2ghi1kpm”
    //最长回文子序列是“1234321”或者“123c321”，返回长度7
    //解题思路:最长回文子序列,原文翻转后,求最长公共子序列即可.

    public int maxPalindromicSubSeq(String str) {
        char[] strArr = str.toCharArray();
        return process(strArr, 0, strArr.length - 1);
    }

    private int process(char[] chars, int start, int end) {

        if (start > end) {
            return 0;
        }

        if (start == end) {
            return 1;
        }

        // 保留左侧,不保留右侧,求剩余回文数量
        int maxLength = process(chars, start, end - 1);

        // 不保留左侧,保留右侧,求剩余的回文数量
        maxLength = Math.max(maxLength, process(chars, start + 1, end));

        // 不保留左侧,也不保留右侧,求剩余回文长度
        maxLength = Math.max(maxLength, process(chars, start + 1, end - 1));

        // 保留左侧,也保留右侧,求回文长度,前提是左右两侧字符相同
        if (chars[start] == chars[end]) {
            maxLength = Math.max(maxLength, 2 + process(chars, start + 1, end - 1));
        }

        return maxLength;
    }

    public static class TestClass {
        //比如 ： str = “a12b3c43def2ghi1kpm”
        //最长回文子序列是“1234321”或者“123c321”，返回长度7
        @Test
        public void test1() {
            String str = "a12b3c43def2ghi1kpm";
            Solution solution = new Solution();
            int maxLength = solution.maxPalindromicSubSeq(str);
            System.out.println("最大回文长度:" + maxLength);
        }

        //比如 ： str = “12376321”
        //最长回文子序列是“1237321”或者“1236321”，返回长度7
        @Test
        public void test2() {
            String str = "12376321";
            Solution2 solution2 = new Solution2();
            int maxLength = solution2.maxPalindromicSubSeq(str);
            System.out.println("最大回文长度:" + maxLength);
        }

        //比如 ： str = “12376321”
        //最长回文子序列是“1237321”或者“1236321”，返回长度7
        @Test
        public void test3() {
            String str = "7232764321";
            Solution2 solution2 = new Solution2();
            int maxLength = solution2.maxPalindromicSubSeq(str);
            System.out.println("最大回文长度:" + maxLength);
        }
    }
}
