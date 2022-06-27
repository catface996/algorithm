package com.algorithm.leetcode.动态规划.P10正则表达式匹配;

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
public class Solution {
    /**
     * 思路: 递归,动态规划
     *
     * @param s 源串
     * @param p 模式串
     * @return
     */
    public boolean isMatch(String s, String p) {
        // 首先获得模式串的开头字符,如果不是"."和"*",则检查是否与s的首个字符一致,如果一致,继续,否则返回false
        // 如果p的首个字符是"."
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
