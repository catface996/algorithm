package com.algorithm.leetcode.数学.P7整数翻转;

//给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
//
// 如果反转后整数超过 32 位的有符号整数的范围 [−231, 231 − 1] ，就返回 0。
//假设环境不允许存储 64 位整数（有符号或无符号）。
//
//
//
// 示例 1：
//
//
//输入：x = 123
//输出：321
//
//
// 示例 2：
//
//
//输入：x = -123
//输出：-321
//
//
// 示例 3：
//
//
//输入：x = 120
//输出：21
//
//
// 示例 4：
//
//
//输入：x = 0
//输出：0
//
//
//
//
// 提示：
//
//
// -231 <= x <= 231 - 1
//
// Related Topics 数学
// 👍 2948 👎 0

import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public int reverse(int x) {
        int absValue = Math.abs(x);
        int ans = 0;
        while (absValue > 0) {
            int rightBit = absValue % 10;
            absValue = absValue / 10;
            if (ans > Integer.MAX_VALUE / 10) {
                return 0;
            }
            ans = (ans * 10) + rightBit;
        }
        if (x > 0) {
            return ans;
        } else {
            return -ans;
        }
    }

    public static class TestClass {
        // 示例 1：
        //
        //
        //输入：x = 123
        //输出：321
        @Test
        public void test1() {
            Solution solution = new Solution();
            long ans = solution.reverse(123);
            System.out.println(ans);
        }

        // 示例 2：
        //
        //
        //输入：x = -123
        //输出：-321
        @Test
        public void test2() {
            Solution solution = new Solution();
            long ans = solution.reverse(-123);
            System.out.println(ans);
        }

        // 示例 3：
        //
        //
        //输入：x = 120
        //输出：21
        @Test
        public void test3() {
            Solution solution = new Solution();
            long ans = solution.reverse(120);
            System.out.println(ans);
        }

        // 示例 4：
        //
        //
        //输入：x = 0
        //输出：0
        @Test
        public void test4() {
            Solution solution = new Solution();
            long ans = solution.reverse(0);
            System.out.println(ans);
        }

        //1534236469
        @Test
        public void test5() {
            Solution solution = new Solution();
            long ans = solution.reverse(1534236469);
            System.out.println(ans);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

