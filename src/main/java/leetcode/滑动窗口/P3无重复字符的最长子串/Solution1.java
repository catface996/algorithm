package leetcode.滑动窗口.P3无重复字符的最长子串;

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

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution1 {

    // 5:09 下午	info
    //				解答成功:
    //				执行耗时:147 ms,击败了8.06% 的Java用户
    //				内存消耗:39 MB,击败了18.72% 的Java用户

    public int lengthOfLongestSubstring(String s) {
        if (s == null || "".equals(s)) {
            return 0;
        }
        int max = 0;
        int start = 0;
        while (start < s.length() - max) {
            Set<Character> exist = new HashSet<>();
            int i = start;
            while (i < s.length() && !exist.contains(s.charAt(i))) {
                exist.add(s.charAt(i));
                i++;
            }
            max = Math.max(max, i - start);
            start++;
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
            Solution1 solution = new Solution1();
            int ans = solution.lengthOfLongestSubstring(s);
            System.out.println(ans);
        }

        //输入: s = "bbbbbb"
        //输出: 1
        @Test
        public void test2() {
            String s = "bbbbbb";
            Solution1 solution = new Solution1();
            int ans = solution.lengthOfLongestSubstring(s);
            System.out.println(ans);
        }

        //输入: s = "pwwkew"
        //输出: 3
        @Test
        public void test3() {
            String s = "pwwkew";
            Solution1 solution = new Solution1();
            int ans = solution.lengthOfLongestSubstring(s);
            System.out.println(ans);
        }

        @Test
        public void test4() {
            String s = " ";
            Solution1 solution = new Solution1();
            int ans = solution.lengthOfLongestSubstring(s);
            System.out.println(ans);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

