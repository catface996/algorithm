package leetcode.滑动窗口.P3无重复字符的最长子串;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/27 5:10 下午
 */
public class Solution2 {

    // 5:12 下午	info
    //				解答成功:
    //				执行耗时:7 ms,击败了79.28% 的Java用户
    //				内存消耗:38.5 MB,击败了73.07% 的Java用户

    public int lengthOfLongestSubstring(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }

    public static class TestClass {

        //输入: s = "abcabcbb"
        //输出: 3
        //解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
        @Test
        public void test1() {
            String s = "abcabcbb";
            Solution2 solution = new Solution2();
            int ans = solution.lengthOfLongestSubstring(s);
            System.out.println(ans);
        }

        //输入: s = "bbbbbb"
        //输出: 1
        @Test
        public void test2() {
            String s = "bbbbbb";
            Solution2 solution = new Solution2();
            int ans = solution.lengthOfLongestSubstring(s);
            System.out.println(ans);
        }

        //输入: s = "pwwkew"
        //输出: 3
        @Test
        public void test3() {
            String s = "pwwkew";
            Solution2 solution = new Solution2();
            int ans = solution.lengthOfLongestSubstring(s);
            System.out.println(ans);
        }

        @Test
        public void test4() {
            String s = " ";
            Solution2 solution = new Solution2();
            int ans = solution.lengthOfLongestSubstring(s);
            System.out.println(ans);
        }
    }

}
