package com.algorithm.leetcode.滑动窗口.P3无重复字符的最长子串;

//给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
//
//
//
// 示例 1:
//
//
//输入: s = "abcabcbb"
//输出: 3
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
//
//
// 示例 2:
//
//
//输入: s = "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
//
//
// 示例 3:
//
//
//输入: s = "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
//
//
// 示例 4:
//
//
//输入: s = ""
//输出: 0
//
//
//
//
// 提示：
//
//
// 0 <= s.length <= 5 * 104
// s 由英文字母、数字、符号和空格组成
//
// Related Topics 哈希表 双指针 字符串 Sliding Window
// 👍 5356 👎 0

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //TODO 需要优化
    // 11:47 上午	info
    //					解答成功:
    //					执行耗时:10 ms,击败了31.41% 的Java用户
    //					内存消耗:39.2 MB,击败了8.19% 的Java用户

    public int lengthOfLongestSubstring(String s) {
        int left = 0;
        int right = 0;
        int max = 0;
        Map<Character, Integer> cMap = new HashMap<>();
        LinkedList<Integer> queue = new LinkedList<>();
        while (right < s.length()) {
            char ch = s.charAt(right);
            while (!cMap.containsKey(ch)) {
                cMap.put(ch, right);
                queue.addLast(right);
                right++;
                if (right >= s.length()) {
                    break;
                }
                ch = s.charAt(right);
            }
            max = Math.max(max, right - left);
            left = cMap.get(ch) + 1;
            while (!queue.isEmpty()) {
                if (queue.peekFirst() < left) {
                    cMap.remove(s.charAt(queue.pollFirst()));
                } else {
                    break;
                }
            }
        }
        return max;
    }

    public static class TestClass {

        //输入: s = "abcabcbb"
        //输出: 3
        //解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
        @Test
        public void test1() {
            String s = "abcabcbb";
            Solution solution = new Solution();
            int ans = solution.lengthOfLongestSubstring(s);
            System.out.println(ans);
        }

        //输入: s = "bbbbbb"
        //输出: 1
        @Test
        public void test2() {
            String s = "bbbbbb";
            Solution solution = new Solution();
            int ans = solution.lengthOfLongestSubstring(s);
            System.out.println(ans);
        }

        //输入: s = "pwwkew"
        //输出: 3
        @Test
        public void test3() {
            String s = "pwwkew";
            Solution solution = new Solution();
            int ans = solution.lengthOfLongestSubstring(s);
            System.out.println(ans);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

