package com.algorithm.leetcode.二分.P50Pow;

//实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。
//
//
//
// 示例 1：
//
//
//输入：x = 2.00000, n = 10
//输出：1024.00000
//
//
// 示例 2：
//
//
//输入：x = 2.10000, n = 3
//输出：9.26100
//
//
// 示例 3：
//
//
//输入：x = 2.00000, n = -2
//输出：0.25000
//解释：2-2 = 1/22 = 1/4 = 0.25
//
//
//
//
// 提示：
//
//
// -100.0 < x < 100.0
// -231 <= n <= 231-1
// -104 <= xn <= 104
//
// Related Topics 数学 二分查找
// 👍 645 👎 0

import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
public class Solution1 {

    // 下标越界, n为 2147483647 时,下标越界

    Double[] dp;

    public double myPow(double x, int n) {
        dp = new Double[Math.abs(n) + 1];
        return process(x, n);
    }

    public double process(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }
        if (n == -1) {
            return 1 / x;
        }
        int index = Math.abs(n);
        if (dp[index] != null) {
            return dp[index];
        }
        int half = n / 2;
        double ans;
        if (n % 2 == 0) {
            ans = process(x, half) * process(x, half);
        } else {
            if (n > 0) {
                ans = process(x, half) * process(x, half + 1);
            } else {
                ans = process(x, half) * process(x, half - 1);
            }
        }
        dp[index] = ans;
        return ans;
    }

    public static class TestClass {

        @Test
        public void test1() {
            Solution1 solution2 = new Solution1();
            int n = 10;
            double x = 2.0;
            double ans = solution2.myPow(x, n);
            System.out.println(ans);
        }

        @Test
        public void test2() {
            Solution1 solution2 = new Solution1();
            int n = -1;
            double x = 2.0;
            double ans = solution2.myPow(x, n);
            System.out.println(ans);
        }

        @Test
        public void test3() {
            Solution1 solution2 = new Solution1();
            int n = -2;
            double x = 2.0;
            double ans = solution2.myPow(x, n);
            System.out.println(ans);
        }

        @Test
        public void test4() {
            Solution1 solution2 = new Solution1();
            int n = -3;
            double x = 2.0;
            double ans = solution2.myPow(x, n);
            System.out.println(ans);
        }

        @Test
        public void test5() {
            Solution1 solution2 = new Solution1();
            int n = 0;
            double x = 2.0;
            double ans = solution2.myPow(x, n);
            System.out.println(ans);
        }

        @Test
        public void test6() {
            Solution1 solution2 = new Solution1();
            int n = 1;
            double x = 2.0;
            double ans = solution2.myPow(x, n);
            System.out.println(ans);
        }

        @Test
        public void test7() {
            Solution1 solution2 = new Solution1();
            int n = 2;
            double x = 2.0;
            double ans = solution2.myPow(x, n);
            System.out.println(ans);
        }

        @Test
        public void test8() {
            Solution1 solution2 = new Solution1();
            int n = 3;
            double x = 2.0;
            double ans = solution2.myPow(x, n);
            System.out.println(ans);
        }

        @Test
        public void test9() {
            Solution1 solution2 = new Solution1();
            int n = 2147483647;
            double x = 1.0;
            double ans = solution2.myPow(x, n);
            System.out.println(ans);
        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)

