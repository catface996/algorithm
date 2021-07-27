package com.algorithm.leetcode.字符串.P14最长公共前缀;

//编写一个函数来查找字符串数组中的最长公共前缀。
//
// 如果不存在公共前缀，返回空字符串 ""。
//
//
//
// 示例 1：
//
//
//输入：strs = ["flower","flow","flight"]
//输出："fl"
//
//
// 示例 2：
//
//
//输入：strs = ["dog","racecar","car"]
//输出：""
//解释：输入不存在公共前缀。
//
//
//
// 提示：
//
//
// 0 <= strs.length <= 200
// 0 <= strs[i].length <= 200
// strs[i] 仅由小写英文字母组成
//
// Related Topics 字符串
// 👍 1703 👎 0

import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length <= 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        int index = findIndex(strs);
        if (index < 0) {
            return "";
        }
        return strs[0].substring(0, index + 1);
    }

    public int findIndex(String[] strs) {
        int index = 0;
        for (; ; ) {
            if (index >= strs[0].length()) {
                return index - 1;
            }
            char c = strs[0].charAt(index);
            for (int i = 1; i < strs.length; i++) {
                if (index >= strs[i].length()) {
                    return index - 1;
                }
                if (strs[i].charAt(index) != c) {
                    return index - 1;
                }
            }
            index++;
        }
    }

    public static class TestClass {

        // 示例 1：
        //
        //
        //输入：strs = ["flower","flow","flight"]
        //输出："fl"
        @Test
        public void test1() {
            Solution solution = new Solution();
            String[] strs = new String[] {"flower", "flow", "flight"};
            String ans = solution.longestCommonPrefix(strs);
            System.out.println(ans);
        }

        //
        //
        // 示例 2：
        //
        //
        //输入：strs = ["dog","racecar","car"]
        //输出：""
        //解释：输入不存在公共前缀。
        @Test
        public void test2() {
            Solution solution = new Solution();
            String[] strs = new String[] {"dog", "racecar", "car"};
            String ans = solution.longestCommonPrefix(strs);
            System.out.println(ans);
        }

        // 测试用例:["",""]
        @Test
        public void test3() {
            Solution solution = new Solution();
            String[] strs = new String[] {"", ""};
            String ans = solution.longestCommonPrefix(strs);
            System.out.println(ans);
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)

