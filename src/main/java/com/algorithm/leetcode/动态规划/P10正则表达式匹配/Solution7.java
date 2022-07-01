package com.algorithm.leetcode.动态规划.P10正则表达式匹配;

import org.junit.Test;

/**
 * @author by 大猫
 * @date 2022/2/28 3:43 PM
 */
//给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
//
//
// '.' 匹配任意单个字符
// '*' 匹配零个或多个前面的那一个元素
//
//
// 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
//
//
// 示例 1：
//
//
//输入：s = "aa", p = "a"
//输出：false
//解释："a" 无法匹配 "aa" 整个字符串。
//
//
// 示例 2:
//
//
//输入：s = "aa", p = "a*"
//输出：true
//解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
//
//
// 示例 3：
//
//
//输入：s = "ab", p = ".*"
//输出：true
//解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 20
// 1 <= p.length <= 30
// s 只包含从 a-z 的小写字母。
// p 只包含从 a-z 的小写字母，以及字符 . 和 *。
// 保证每次出现字符 * 时，前面都匹配到有效的字符
//
// Related Topics 递归 字符串 动态规划 👍 2748 👎 0

//leetcode submit region begin(Prohibit modification and deletion)
public class Solution7 {
    /**
     * 思路: 递归,动态规划
     *
     * @param s 源串
     * @param p 模式串
     * @return true 符合,否则不符合
     */
    public boolean isMatch(String s, String p) {
        return isMatch(s, p, s.length() - 1, p.length() - 1);
    }

    /**
     * 检查是否匹配
     *
     * @param s      子串
     * @param p      目标串
     * @param sRight s串从右往左匹配时,当前要匹配的下标
     * @param pRight p串从右往左匹配时,当前要匹配的下标
     * @return ture, 当前位置匹配
     */
    public boolean isMatch(String s, String p, int sRight, int pRight) {
        // 当p串已经匹配结束时,如果s串也匹配结束,表示两串匹配
        if (pRight <= -1) {
            return sRight <= -1;
        }
        // p串未匹配结束,继续匹配p串
        switch (p.charAt(pRight)) {
            case '.':
                // 1.当pRight处是'.'时,sRight处是任意字符均匹配,除非sRight处不存在
                return isMatch(s, p, sRight - 1, pRight - 1);
            // 2.当pRight处是'*'时,有以下两种情况
            case '*':
                // 2.1 合并pRight处字符和pRight-1处字符为0个字符,直接跳过
                if (isMatch(s, p, sRight, pRight - 2)) {
                    return true;
                }
                // 2.2 合并pRight处字符和pRight-1处字符为1到多个字符
                // 2.2.1 如果s串机已经匹配结束,s串仍旧有剩余,即当前的'*'作为有效字符,说明两串不匹配
                if (sRight < 0) {
                    return false;
                }
                // 2.2.2 s串有剩余,且s串的sRight处字符与p串的pRight处字符不匹配
                if (p.charAt(pRight - 1) != '.' && p.charAt(pRight - 1) != s.charAt(sRight)) {
                    return false;
                }
                // 2.2.3 合并为一个字符,即跳过当前的sRight字符和pRight处的'*'以及pRight-1处的字符
                if (isMatch(s, p, sRight - 1, pRight - 2)) {
                    return true;
                }
                // 2.2.4 合并为多个字符,不跳过pRight处的'*',跳过sRight处的字符,继续匹配
                return isMatch(s, p, sRight - 1, pRight);
            default:
                // 3.当pRight位置是字母时,sRight处不存在或者sRight与pRight处不相等,一定是不匹配的
                if (sRight < 0 || s.charAt(sRight) != p.charAt(pRight)) {
                    return false;
                }
                return isMatch(s, p, sRight - 1, pRight - 1);
        }
    }


    /**
     * // 示例 1：
     * //
     * //
     * //输入：s = "aa", p = "a"
     * //输出：false
     * //解释："a" 无法匹配 "aa" 整个字符串。
     */
    @Test
    public void test1() {
        Solution7 solution = new Solution7();
        String s = "aa";
        String p = "a";
        boolean ans = solution.isMatch(s, p);
        System.out.println(ans);
        assert !ans;
    }

    /**
     * // 示例 2:
     * //
     * //
     * //输入：s = "aa", p = "a*"
     * //输出：true
     * //解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
     */
    @Test
    public void test2() {
        Solution7 solution = new Solution7();
        String s = "aa";
        String p = "a*";
        boolean ans = solution.isMatch(s, p);
        System.out.println(ans);
        assert ans;
    }

    /**
     * //
     * // 示例 3：
     * //
     * //
     * //输入：s = "ab", p = ".*"
     * //输出：true
     * //解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
     */
    @Test
    public void test3() {
        Solution7 solution = new Solution7();
        String s = "aa";
        String p = ".*";
        boolean ans = solution.isMatch(s, p);
        System.out.println(ans);
        assert ans;
    }

    /**
     * //
     * // 示例 4：
     * //
     * //
     * //输入：s = "aaaa", p = "a.*"
     * //输出：true
     * //解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
     */
    @Test
    public void test4() {
        Solution7 solution = new Solution7();
        String s = "aaaa";
        String p = "a.*";
        boolean ans = solution.isMatch(s, p);
        System.out.println(ans);
        assert ans;
    }

    /**
     * //
     * // 示例 5：
     * //
     * //
     * //输入：s = "aaaa", p = "aa.*"
     * //输出：true
     * //解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
     */
    @Test
    public void test5() {
        Solution7 solution = new Solution7();
        String s = "aaaa";
        String p = "aa.*";
        boolean ans = solution.isMatch(s, p);
        System.out.println(ans);
        assert ans;
    }

    /**
     * //
     * // 示例 6：
     * //
     * //
     * //输入：s = "aaaa", p = "aaab.*"
     * //输出：false
     * //解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。 aaaa与aaab不匹配,与.*匹配
     */
    @Test
    public void test6() {
        Solution7 solution = new Solution7();
        String s = "aaaa";
        String p = "aaab.*";
        boolean ans = solution.isMatch(s, p);
        System.out.println(ans);
        assert !ans;
    }

    /**
     * //
     * // 示例 6：
     * //
     * //
     * //输入：s = "aaac", p = "aaab.*"
     * //输出：false
     * //解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
     */
    @Test
    public void test7() {
        Solution7 solution = new Solution7();
        String s = "aaac";
        String p = "aaab.*";
        boolean ans = solution.isMatch(s, p);
        System.out.println(ans);
        assert !ans;
    }

    /**
     * 解答失败:
     * 测试用例:"ab"
     * ".*"
     * 测试结果:false
     * 期望结果:true
     */
    @Test
    public void test8() {
        Solution7 solution = new Solution7();
        String s = "ab";
        String p = ".*";
        boolean ans = solution.isMatch(s, p);
        System.out.println(ans);
        assert ans;
    }

    /**
     * 解答失败:
     * 测试用例:"ab"
     * ".*c"
     * 测试结果:true
     * 期望结果:false
     * stdout:
     */
    @Test
    public void test9() {
        Solution7 solution = new Solution7();
        String s = "ab";
        String p = ".*c";
        boolean ans = solution.isMatch(s, p);
        System.out.println(ans);
        assert !ans;
    }

    /**
     * 解答失败:
     * 测试用例:"aaa"
     * "aaaa"
     * 测试结果:true
     * 期望结果:false
     * stdout:
     */
    @Test
    public void test10() {
        Solution7 solution = new Solution7();
        String s = "aaa";
        String p = "aaaa";
        boolean ans = solution.isMatch(s, p);
        System.out.println(ans);
        assert !ans;
    }

    /**
     * 解答失败:
     * 测试用例:"aab"
     * "c*a*b"
     * 测试结果:false
     * 期望结果:true
     * stdout:
     */
    @Test
    public void test11() {
        Solution7 solution = new Solution7();
        String s = "aab";
        String p = "c*a*b";
        boolean ans = solution.isMatch(s, p);
        System.out.println(ans);
        assert ans;
    }

    /**
     * 解答失败:
     * 测试用例:"aaa"
     * "ab*ac*a"
     * 测试结果:false
     * 期望结果:true
     * stdout:
     */
    @Test
    public void test12() {
        Solution7 solution = new Solution7();
        String s = "aaa";
        String p = "ab*ac*a";
        boolean ans = solution.isMatch(s, p);
        System.out.println(ans);
        assert ans;
    }

    /**
     * 解答失败:
     * 测试用例:"aaa"
     * "ab*a*c*a"
     * 测试结果:false
     * 期望结果:true
     * stdout:
     */
    @Test
    public void test13() {
        Solution7 solution = new Solution7();
        String s = "aaa";
        String p = "ab*a*c*a";
        boolean ans = solution.isMatch(s, p);
        System.out.println(ans);
        assert ans;
    }

    /**
     * 解答失败:
     * 测试用例:"bbbba"
     * ".*a*a"
     * 测试结果:false
     * 期望结果:true
     * stdout:
     */
    @Test
    public void test14() {
        Solution7 solution = new Solution7();
        String s = "bbbba";
        String p = ".*a*a";
        boolean ans = solution.isMatch(s, p);
        System.out.println(ans);
        assert ans;
    }

    /**
     * 解答失败:
     * 测试用例:"a"
     * "ab*"
     * 测试结果:false
     * 期望结果:true
     * stdout:
     */
    @Test
    public void test15() {
        Solution7 solution = new Solution7();
        String s = "a";
        String p = "ab*";
        boolean ans = solution.isMatch(s, p);
        System.out.println(ans);
        assert ans;
    }

    /**
     * 解答失败:
     * 测试用例:"a"
     * ".*"
     * 测试结果:false
     * 期望结果:true
     * stdout:
     */
    @Test
    public void test16() {
        Solution7 solution = new Solution7();
        String s = "a";
        String p = ".*";
        boolean ans = solution.isMatch(s, p);
        System.out.println(ans);
        assert ans;
    }

    /**
     * 解答失败:
     * 测试用例:"abcaaaaaaabaabcabac"
     * ".*ab.a.*a*a*.*b*b*"
     * 测试结果:false
     * 期望结果:true
     * stdout:
     */
    @Test
    public void test17() {
        Solution7 solution = new Solution7();
        String s = "abcaaaaaaabaabcabac";
        String p = ".*ab.a.*a*a*.*b*b*";
        boolean ans = solution.isMatch(s, p);
        System.out.println(ans);
        assert ans;
    }

    /**
     * 测试用例:"acaabbaccbbacaabbbb"
     * "a*.*b*.*a*aa*a*"
     */
    @Test
    public void test18() {
        Solution7 solution = new Solution7();
        String s = "acaabbaccbbacaabbbb";
        String p = "a*.*b*.*a*aa*a*";
        boolean ans = solution.isMatch(s, p);
        System.out.println(ans);
        assert !ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
