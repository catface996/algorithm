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
public class Solution5 {
    /**
     * 思路: 递归,动态规划
     *
     * @param s 源串
     * @param p 模式串
     * @return true 符合,否则不符合
     */
    public boolean isMatch(String s, String p) {
        Boolean[][] ansMap = new Boolean[s.length()][p.length()];
        return isMatch(s, p, s.length() - 1, p.length() - 1, ansMap);
    }

    /**
     * 检查是否匹配
     *
     * @param s      子串
     * @param p      目标串
     * @param sRight s串当前从右往左匹配的位置
     * @param pRight p串当前从右往左匹配的位置
     * @param ansMap 结果缓存
     * @return ture, 当前位置匹配
     */
    public boolean isMatch(String s, String p, int sRight, int pRight, Boolean[][] ansMap) {
        // 优先从缓存冲获取
        if (sRight >= 0 && pRight >= 0 && ansMap[sRight][pRight] != null) {
            return ansMap[sRight][pRight];
        }
        // s串和p串恰巧都匹配结束
        if (sRight == -1 && pRight == -1) {
            return true;
        }
        // s串未匹配结束,p串匹配结束,一定是不匹配的
        if (sRight >= 0 && pRight <= -1) {
            return false;
        }

        // s串匹配结束,p串未匹配结束,此时要考虑p串是否剩余的必须要匹配的字符
        // 当前情况下,只有pRight位置是'*',才能继续进入下一轮匹配
        if (sRight == -1 && pRight >= 0) {
            if (p.charAt(pRight) == '*') {
                // 需要将当前的pRight,即当前的'*'和pRight-1位置上的字符,作为0个字符来考虑
                return isMatch(s, p, sRight, pRight - 2, ansMap);
            }
            return false;
        }

        // s串未匹配结束,p串未匹配结束
        // 1.pRight处字符与sRight字符相等,可以进入下一轮匹配
        // 2.如果pRight处字符是'.',可以进入下一轮匹配
        // 3.如果pRight处字符是'*',
        // 3.1 将pRight处的'*'与pRight-1处的字符合并为0个字符考虑
        // 3.2 将pRight处的'*'和pRight-1处的字符合并为1个字符考虑
        // 3.3 将pRight处的'*'和pRight-1处的字符合并为多个字符考虑,此时如果pRight-1处字符与sRight处字符匹配,方可进入下一轮匹配

        // 1.pRight处字符与sRight字符相等,可以进入下一轮匹配
        if (s.charAt(sRight) == p.charAt(pRight)) {
            ansMap[sRight][pRight] = isMatch(s, p, sRight - 1, pRight - 1, ansMap);
            return ansMap[sRight][pRight];
        }
        // 2.如果pRight处字符是'.',可以进入下一轮匹配
        if (p.charAt(pRight) == '.') {
            ansMap[sRight][pRight] = isMatch(s, p, sRight - 1, pRight - 1, ansMap);
            return ansMap[sRight][pRight];

        }
        // 3.如果pRight处字符是'*',
        if (p.charAt(pRight) == '*') {
            // 3.1 将pRight处的'*'与pRight-1处的字符合并为0个字符考虑
            if (isMatch(s, p, sRight, pRight - 2, ansMap)) {
                ansMap[sRight][pRight] = true;
                return true;
            }
            // 3.2 将pRight处的'*'和pRight-1处的字符合并为1个字符考虑
            if (isMatch(s, p, sRight, pRight - 1, ansMap)) {
                ansMap[sRight][pRight] = true;
                return true;
            }
            // 3.3 将pRight处的'*'和pRight-1处的字符合并为多个字符考虑,此时如果pRight-1处字符与sRight处字符匹配,方可进入下一轮匹配
            // 根据题设,pRight处是'*'时,pRight-1处一定有有效字符,严禁起见,可以判断pRight-1>=0
            if (p.charAt(pRight - 1) == '.' || p.charAt(pRight - 1) == s.charAt(sRight)) {
                ansMap[sRight][pRight] = isMatch(s, p, sRight - 1, pRight, ansMap);
                return ansMap[sRight][pRight];
            }
        }
        // 以上条件均不符合,一定是不匹配的
        ansMap[sRight][pRight] = false;
        return false;
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
        Solution5 solution = new Solution5();
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
        Solution5 solution = new Solution5();
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
        Solution5 solution = new Solution5();
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
        Solution5 solution = new Solution5();
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
        Solution5 solution = new Solution5();
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
        Solution5 solution = new Solution5();
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
        Solution5 solution = new Solution5();
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
        Solution5 solution = new Solution5();
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
        Solution5 solution = new Solution5();
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
        Solution5 solution = new Solution5();
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
        Solution5 solution = new Solution5();
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
        Solution5 solution = new Solution5();
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
        Solution5 solution = new Solution5();
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
        Solution5 solution = new Solution5();
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
        Solution5 solution = new Solution5();
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
        Solution5 solution = new Solution5();
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
        Solution5 solution = new Solution5();
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
        Solution5 solution = new Solution5();
        String s = "acaabbaccbbacaabbbb";
        String p = "a*.*b*.*a*aa*a*";
        boolean ans = solution.isMatch(s, p);
        System.out.println(ans);
        assert !ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
