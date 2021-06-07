package com.algorithm.leetcode.滑动窗口.P3无重复字符的最长子串;

import java.util.Arrays;

import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/27 5:10 下午
 */
public class Solution3 {

    // 5:12 下午	info
    //				解答成功:
    //				执行耗时:7 ms,击败了79.28% 的Java用户
    //				内存消耗:38.5 MB,击败了73.07% 的Java用户

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int[] map = new int[128];
        Arrays.fill(map, -1);
        int pre = -1;
        int res = 0;
        for (int i = 0; i < str.length; i++) {
            // 对每一个字符,查找最后一次出现的位置与当前的pre位置比较
            pre = Math.max(pre, map[str[i]]);
            // 当前的最长字符串长度即当前位置减上一个位置,与已经获得的最大长度比较
            res = Math.max(res, i - pre);
            map[str[i]] = i;
        }
        return res;
    }

    public static class TestClass {

        //输入: s = "abcabcbb"
        //输出: 3
        //解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
        @Test
        public void test1() {
            String s = "abcabcbb";
            Solution3 solution = new Solution3();
            int ans = solution.lengthOfLongestSubstring(s);
            System.out.println(ans);
        }

        //输入: s = "bbbbbb"
        //输出: 1
        @Test
        public void test2() {
            String s = "bbbbbb";
            Solution3 solution = new Solution3();
            int ans = solution.lengthOfLongestSubstring(s);
            System.out.println(ans);
        }

        //输入: s = "pwwkew"
        //输出: 3
        @Test
        public void test3() {
            String s = "pwwkew";
            Solution3 solution = new Solution3();
            int ans = solution.lengthOfLongestSubstring(s);
            System.out.println(ans);
        }

        @Test
        public void test4() {
            String s = " ";
            Solution3 solution = new Solution3();
            int ans = solution.lengthOfLongestSubstring(s);
            System.out.println(ans);
        }
    }

}
