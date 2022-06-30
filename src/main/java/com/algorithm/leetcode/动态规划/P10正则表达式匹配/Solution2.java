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
public class Solution2 {
    /**
     * 思路: 递归,动态规划
     *
     * @param s 源串
     * @param p 模式串
     * @return true 符合,否则不符合
     */
    public boolean isMatch(String s, String p) {
        boolean[][] ansMap = new boolean[s.length() + 1][p.length() + 2];
        // s和p均在最后一个匹配成功,说明匹配
        ansMap[s.length()][p.length()] = true;
        int pCur = p.length() - 1;
        // s已经匹配结束,p陪最后一位,或者倒数第二位,且最后一位是*,可以匹配
        // 如果倒数第1位,第3位,第5位,依次类推均为*,则第均可以匹配
        while (pCur >= 1 && p.charAt(pCur) == '*') {
            ansMap[s.length()][pCur] = true;
            ansMap[s.length()][pCur - 1] = true;
            pCur -= 2;
        }
        for (int sCur = s.length() - 1; sCur >= 0; sCur--) {
            for (pCur = p.length() - 1; pCur >= 0; pCur--) {
                ansMap[sCur][pCur] = isMatch(s, p, sCur, pCur, ansMap);
            }
        }
        return ansMap[0][0];
    }

    /**
     * 检查是否匹配
     *
     * @param s    子串
     * @param p    目标串
     * @param sCur 子串当前要匹配的位置
     * @return ture, 当前位置匹配
     */
    public boolean isMatch(String s, String p, int sCur, int pCur, boolean[][] ansMap) {
        // 当sCur超过s串的长度,一定是匹配事变
        if (sCur > s.length()) {
            return false;
        }

        // s串全部检查结束,说明匹配,sCur始终是逐个字符向右匹配,严格+1递增
        if (sCur == s.length() && pCur == p.length()) {
            return true;
        }

        // p串匹配结束,且超过了p的长度,说明不匹配
        if (pCur >= p.length()) {
            return false;
        }

        boolean ans = false;

        // 首先要决策当前的pCur字符要不要比较,决定因素是看下一个字符是不是*
        // 如果是*,可以合并成0个字符,所以不需要比较
        // 如果不是*,一定要比较

        // 下一个字符是'*',可以跳过当前字符和下一个字符,直接与pCur+2个字符进行比较
        if (pCur + 1 < p.length() && p.charAt(pCur + 1) == '*') {
            ans = ansMap[sCur][pCur + 2];
            if (ans) {
                return true;
            }
        }

        // pCur+1不是星号,不能跳过当前要比较的字符,分以下三种情况
        // 1.当前的pCur是*
        // 2.当前的pCur是.
        // 3.当前的pCur是字母

        // 当前字符是*号
        if (p.charAt(pCur) == '*') {

            //与之前的元素合并为1个,即忽略当前的*,直接与p的下一个字符比对
            ans = ansMap[sCur][pCur + 1];
            if (ans) {
                return ans;
            }

            // 与之前的元素合并为多个
            // 如果pCur-1不是'.',需要判断pCur-1与s的sCur是否相等,如果不相等,不匹配
            if (sCur < s.length() && p.charAt(pCur - 1) != '.' && s.charAt(sCur) != p.charAt(pCur - 1)) {
                return false;
            }

            // 在*代表多个之前的字符的情况下,有两种选择
            // 1.p串停留在pCur处继续与s的sCur+1继续进行匹配(此时要求s的sCur+1与sCur相等方可,此处不判断,在下一次递归中会进行判断)
            ans = ansMap[sCur + 1][pCur];
            if (ans) {
                return ans;
            }

            // 2.p串继续向右,在pCur+1处于sCur+1处进行匹配
            return ansMap[sCur + 1][pCur + 1];
        }


        // 当前的pCur不是*,是字母或者.
        // 如果s串的当前字母和p串的当前字母相等,则是否匹配需要考虑的是剩余的串是否匹配
        if ((sCur < s.length() && s.charAt(sCur) == p.charAt(pCur)) || p.charAt(pCur) == '.') {
            return ansMap[sCur + 1][pCur + 1];
        }
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
        Solution2 solution = new Solution2();
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
        Solution2 solution = new Solution2();
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
        Solution2 solution = new Solution2();
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
        Solution2 solution = new Solution2();
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
        Solution2 solution = new Solution2();
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
        Solution2 solution = new Solution2();
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
        Solution2 solution = new Solution2();
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
        Solution2 solution = new Solution2();
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
        Solution2 solution = new Solution2();
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
        Solution2 solution = new Solution2();
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
        Solution2 solution = new Solution2();
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
        Solution2 solution = new Solution2();
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
        Solution2 solution = new Solution2();
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
        Solution2 solution = new Solution2();
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
        Solution2 solution = new Solution2();
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
        Solution2 solution = new Solution2();
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
        Solution2 solution = new Solution2();
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
        Solution2 solution = new Solution2();
        String s = "acaabbaccbbacaabbbb";
        String p = "a*.*b*.*a*aa*a*";
        boolean ans = solution.isMatch(s, p);
        System.out.println(ans);
        assert !ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
