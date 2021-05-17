package leetcode.二分.P50Pow;

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
public class Solution2 {

    // 5:07 下午	info
    //				运行失败:
    //				Memory Limit Exceeded
    //				测试用例:0.00001
    //				2147483647
    //				stdout:

    Double[] dp;

    public double myPow(double x, int n) {
        dp = new Double[Math.abs(n) / 2 + 2];
        if (n == 0) {
            return 1;
        }
        return process(x, Math.abs(n), n > 0);
    }

    public double process(double x, int n, boolean f) {
        if (n == 1 && f) {
            return x;
        }
        if (n == 1 && !f) {
            return 1 / x;
        }
        int half = n / 2;
        if (n % 2 == 0) {
            if (dp[half] != null) {
                return dp[half] * dp[half];
            }
            double halfAns = process(x, half, f);
            dp[half] = halfAns;
            return halfAns * halfAns;
        } else {
            double halfAns = process(x, half, f);
            dp[half] = halfAns;
            dp[half + 1] = halfAns * dp[1];
            return dp[half] * dp[half + 1];
        }
    }

    public static class TestClass {

        @Test
        public void test1() {
            Solution2 solution2 = new Solution2();
            int n = 10;
            double x = 2.0;
            double ans = solution2.myPow(x, n);
            System.out.println(ans);
        }

        @Test
        public void test2() {
            Solution2 solution2 = new Solution2();
            int n = -1;
            double x = 2.0;
            double ans = solution2.myPow(x, n);
            System.out.println(ans);
        }

        @Test
        public void test3() {
            Solution2 solution2 = new Solution2();
            int n = -2;
            double x = 2.0;
            double ans = solution2.myPow(x, n);
            System.out.println(ans);
        }

        @Test
        public void test4() {
            Solution2 solution2 = new Solution2();
            int n = -3;
            double x = 2.0;
            double ans = solution2.myPow(x, n);
            System.out.println(ans);
        }

        @Test
        public void test5() {
            Solution2 solution2 = new Solution2();
            int n = 0;
            double x = 2.0;
            double ans = solution2.myPow(x, n);
            System.out.println(ans);
        }

        @Test
        public void test6() {
            Solution2 solution2 = new Solution2();
            int n = 1;
            double x = 2.0;
            double ans = solution2.myPow(x, n);
            System.out.println(ans);
        }

        @Test
        public void test7() {
            Solution2 solution2 = new Solution2();
            int n = 2;
            double x = 2.0;
            double ans = solution2.myPow(x, n);
            System.out.println(ans);
        }

        @Test
        public void test8() {
            Solution2 solution2 = new Solution2();
            int n = 3;
            double x = 2.0;
            double ans = solution2.myPow(x, n);
            System.out.println(ans);
        }

        @Test
        public void test9() {
            Solution2 solution2 = new Solution2();
            int n = 2147483647;
            double x = 1.0;
            double ans = solution2.myPow(x, n);
            System.out.println(ans);
        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)

