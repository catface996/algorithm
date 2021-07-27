package com.algorithm.leetcode.字符串.P20有效的括号;

//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
//
// 有效字符串需满足：
//
//
// 左括号必须用相同类型的右括号闭合。
// 左括号必须以正确的顺序闭合。
//
//
//
//
// 示例 1：
//
//
//输入：s = "()"
//输出：true
//
//
// 示例 2：
//
//
//输入：s = "()[]{}"
//输出：true
//
//
// 示例 3：
//
//
//输入：s = "(]"
//输出：false
//
//
// 示例 4：
//
//
//输入：s = "([)]"
//输出：false
//
//
// 示例 5：
//
//
//输入：s = "{[]}"
//输出：true
//
//
//
// 提示：
//
//
// 1 <= s.length <= 104
// s 仅由括号 '()[]{}' 组成
//
// Related Topics 栈 字符串
// 👍 2514 👎 0

import java.util.Stack;

import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case '(':
                    stack.push('(');
                    break;
                case ')':
                    if (!stack.isEmpty() && stack.peek() == '(') {
                        stack.pop();
                    } else {
                        return false;
                    }
                    break;
                case '[':
                    stack.push('[');
                    break;
                case ']':
                    if (!stack.isEmpty() && stack.peek() == '[') {
                        stack.pop();
                    } else {
                        return false;
                    }
                    break;
                case '{':
                    stack.push('{');
                    break;
                case '}':
                    if (!stack.isEmpty() && stack.peek() == '{') {
                        stack.pop();
                    } else {
                        return false;
                    }
                    break;
                default:
            }
        }
        return stack.isEmpty();
    }

    public static class TestClass {
        // 示例 1：
        //
        //
        //输入：s = "()"
        //输出：true
        @Test
        public void test1() {
            String s = "()";
            Solution solution = new Solution();
            boolean ans = solution.isValid(s);
            System.out.println(ans);
        }

        //
        //
        // 示例 2：
        //
        //
        //输入：s = "()[]{}"
        //输出：true
        @Test
        public void test2() {
            String s = "()[]{}";
            Solution solution = new Solution();
            boolean ans = solution.isValid(s);
            System.out.println(ans);
        }

        //
        //
        // 示例 3：
        //
        //
        //输入：s = "(]"
        //输出：false
        @Test
        public void test3() {
            String s = "(]";
            Solution solution = new Solution();
            boolean ans = solution.isValid(s);
            System.out.println(ans);
        }

        //
        //
        // 示例 4：
        //
        //
        //输入：s = "([)]"
        //输出：false
        @Test
        public void test4() {
            String s = "([)]";
            Solution solution = new Solution();
            boolean ans = solution.isValid(s);
            System.out.println(ans);
        }

        //
        //
        // 示例 5：
        //
        //
        //输入：s = "{[]}"
        //输出：true
        @Test
        public void test5() {
            String s = "{[]}";
            Solution solution = new Solution();
            boolean ans = solution.isValid(s);
            System.out.println(ans);
        }

        // 测试用例:"]"
        @Test
        public void test6() {
            String s = "]";
            Solution solution = new Solution();
            boolean ans = solution.isValid(s);
            System.out.println(ans);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

