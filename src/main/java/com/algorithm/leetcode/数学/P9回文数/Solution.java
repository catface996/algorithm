package com.algorithm.leetcode.数学.P9回文数;

//给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
//
// 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是。
//
//
//
// 示例 1：
//
//
//输入：x = 121
//输出：true
//
//
// 示例 2：
//
//
//输入：x = -121
//输出：false
//解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
//
//
// 示例 3：
//
//
//输入：x = 10
//输出：false
//解释：从右向左读, 为 01 。因此它不是一个回文数。
//
//
// 示例 4：
//
//
//输入：x = -101
//输出：false
//
//
//
//
// 提示：
//
//
// -231 <= x <= 231 - 1
//
//
//
//
// 进阶：你能不将整数转为字符串来解决这个问题吗？
// Related Topics 数学
// 👍 1492 👎 0

import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 2:49 下午	info
    //				解答成功:
    //				执行耗时:9 ms,击败了98.83% 的Java用户
    //				内存消耗:37.9 MB,击败了54.01% 的Java用户
    
    public boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        int leftNum = x;
        int halfNum = 0;
        int count = 1;
        for (; ; ) {
            leftNum = leftNum / 10;
            if (leftNum == 0) {
                break;
            }
            count++;
        }
        leftNum = x;
        for (int i = 0; i < count / 2; i++) {
            halfNum = halfNum * 10 + leftNum % 10;
            leftNum = leftNum / 10;
        }
        if (count % 2 == 1) {
            // 奇数个数字
            leftNum = leftNum / 10;
        }
        return leftNum == halfNum;
    }

    public static class TestClass {
        @Test
        public void test1() {
            int num = 1221;
            Solution solution = new Solution();
            boolean ans = solution.isPalindrome(num);
            System.out.println(ans);
        }

        @Test
        public void test2() {
            int num = 12212;
            Solution solution = new Solution();
            boolean ans = solution.isPalindrome(num);
            System.out.println(ans);
        }

        @Test
        public void test3() {
            int num = 1234321;
            Solution solution = new Solution();
            boolean ans = solution.isPalindrome(num);
            System.out.println(ans);
        }

        @Test
        public void test4() {
            int num = 10;
            Solution solution = new Solution();
            boolean ans = solution.isPalindrome(num);
            System.out.println(ans);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

