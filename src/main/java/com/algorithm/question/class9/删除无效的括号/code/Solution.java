package com.algorithm.question.class9.删除无效的括号.code;

//给你一个由若干括号和字母组成的字符串 s ，删除最小数量的无效括号，使得输入的字符串有效。
//
// 返回所有可能的结果。答案可以按 任意顺序 返回。
//
//
//
// 示例 1：
//
//
//输入：s = "()())()"
//输出：["(())()","()()()"]
//
//
// 示例 2：
//
//
//输入：s = "(a)())()"
//输出：["(a())()","(a)()()"]
//
//
// 示例 3：
//
//
//输入：s = ")("
//输出：[""]
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 25
// s 由小写英文字母以及括号 '(' 和 ')' 组成
// s 中至多含 20 个括号
//
// Related Topics 广度优先搜索 字符串 回溯
// 👍 452 👎 0

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
@Slf4j
public class Solution {
    public List<String> removeInvalidParentheses(String s) {

        // 最少移除的数量,可以从0 到 s.length,暴力解
        // 如果第一个位置是 右括号,必须移除,最右一个位置是左括号,也必须移除
        // 从左向右开始,左括号+1,右括号-1
        // 如果出现-1,说明右括号多,需要移除右括号,可移除的位置是之前出现为0的位置
        // 如果遍历到最后,发现>1,说明左括号有剩余,
        return null;
    }

    public static class TestClass {

        // 示例 1：
        //
        //
        //输入：s = "()())()"
        //输出：["(())()","()()()"]
        @Test
        public void test1() {
            String s = "()())()";
            Solution solution = new Solution();
            List<String> ans = solution.removeInvalidParentheses(s);
            log.info("ans:{}", ans);
        }

        // 示例 2：
        //
        //
        //输入：s = "(a)())()"
        //输出：["(a())()","(a)()()"]
        @Test
        public void test2() {
            String s = "(a)())()";
            Solution solution = new Solution();
            List<String> ans = solution.removeInvalidParentheses(s);
            log.info("ans:{}", ans);
        }

        // 示例 3：
        //
        //
        //输入：s = ")("
        //输出：[""]
        @Test
        public void test3() {
            String s = ")(";
            Solution solution = new Solution();
            List<String> ans = solution.removeInvalidParentheses(s);
            log.info("ans:{}", ans);
        }

        // s="("
        @Test
        public void test4() {
            String s = "(";
            Solution solution = new Solution();
            List<String> ans = solution.removeInvalidParentheses(s);
            log.info("ans:{}", ans);
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)

