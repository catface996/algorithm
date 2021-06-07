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

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
public class Solution4 {

    public double myPow(double x, int n) {
        if (Double.valueOf(x).equals(Double.valueOf(0.0))) {
            return 0;
        }
        if (Double.valueOf(x).equals(Double.valueOf(1))) {
            return 1;
        }

        int ans = 1;
        if (n < 0) {
            x = 1 / x;
            if (n == -2147483648) {
                n = 2147483647;
                ans *= x;
            } else {
                n = -n;
            }
        }
        return ans * process(x, n);
    }

    public double process(double x, int n) {
        List<Integer> bitList = new ArrayList<>();
        int times = 1;
        while (n > 0 || n < -1) {
            if ((n & 1) == 1) {
                bitList.add(times);
            }
            times *= 2;
            n = n >> 1;
        }
        times = 1;
        double p = x;
        double ans = 1;
        for (Integer lowBit : bitList) {
            while (lowBit > times) {
                times *= 2;
                p *= p;
            }
            ans *= p;
        }
        return ans;
    }

    public static class TestClass {

        @Test
        public void test1() {
            Solution4 solution2 = new Solution4();
            int n = 10;
            double x = 2.0;
            double ans = solution2.myPow(x, n);
            System.out.println(ans);
        }

        @Test
        public void test2() {
            Solution4 solution2 = new Solution4();
            int n = -1;
            double x = 2.0;
            double ans = solution2.myPow(x, n);
            System.out.println(ans);
        }

        @Test
        public void test3() {
            Solution4 solution2 = new Solution4();
            int n = -2;
            double x = 2.0;
            double ans = solution2.myPow(x, n);
            System.out.println(ans);
        }

        @Test
        public void test4() {
            Solution4 solution2 = new Solution4();
            int n = -3;
            double x = 2.0;
            double ans = solution2.myPow(x, n);
            System.out.println(ans);
        }

        @Test
        public void test5() {
            Solution4 solution2 = new Solution4();
            int n = 0;
            double x = 2.0;
            double ans = solution2.myPow(x, n);
            System.out.println(ans);
        }

        @Test
        public void test6() {
            Solution4 solution2 = new Solution4();
            int n = 1;
            double x = 2.0;
            double ans = solution2.myPow(x, n);
            System.out.println(ans);
        }

        @Test
        public void test7() {
            Solution4 solution2 = new Solution4();
            int n = 2;
            double x = 2.0;
            double ans = solution2.myPow(x, n);
            System.out.println(ans);
        }

        @Test
        public void test8() {
            Solution4 solution2 = new Solution4();
            int n = 3;
            double x = 2.0;
            double ans = solution2.myPow(x, n);
            System.out.println(ans);
        }

        @Test
        public void test9() {
            Solution4 solution2 = new Solution4();
            int n = 2147483647;
            double x = 1.000000001;
            double ans = solution2.myPow(x, n);
            System.out.println(ans);
        }

        @Test
        public void test10() {
            Solution4 solution2 = new Solution4();
            int n = -2147483648;
            double x = 2.0;
            double ans = solution2.myPow(x, n);
            System.out.println(ans);
        }

        @Test
        public void test11() {
            Solution4 solution2 = new Solution4();
            int n = -2147483647;
            double x = 2.0;
            double ans = solution2.myPow(x, n);
            System.out.println(ans);
        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)

