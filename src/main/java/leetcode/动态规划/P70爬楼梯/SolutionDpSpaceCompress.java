package leetcode.动态规划.P70爬楼梯;
//假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
//
// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
//
// 注意：给定 n 是一个正整数。
//
// 示例 1：
//
// 输入： 2
//输出： 2
//解释： 有两种方法可以爬到楼顶。
//1.  1 阶 + 1 阶
//2.  2 阶
//
// 示例 2：
//
// 输入： 3
//输出： 3
//解释： 有三种方法可以爬到楼顶。
//1.  1 阶 + 1 阶 + 1 阶
//2.  1 阶 + 2 阶
//3.  2 阶 + 1 阶
//
// Related Topics 动态规划
// 👍 1623 👎 0

import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
class SolutionDpSpaceCompress {

    public int climbStairs(int n) {
        return processDp(n);
    }

    private int processDp(int n) {
        int dpLasLast = 1;
        int dpLast = 1;
        int ans = 1;
        for (int i = 2; i <= n; i++) {
            ans = dpLast + dpLasLast;
            dpLasLast = dpLast;
            dpLast = ans;
        }
        return ans;
    }

    public static class TestClass {

        // 示例 1：
        //
        // 输入： 2
        //输出： 2
        //解释： 有两种方法可以爬到楼顶。
        //1.  1 阶 + 1 阶
        //2.  2 阶
        @Test
        public void test1() {
            SolutionDpSpaceCompress solution = new SolutionDpSpaceCompress();
            int ans = solution.climbStairs(2);
            System.out.println(ans);
        }

        // 示例 2：
        //
        // 输入： 3
        //输出： 3
        //解释： 有三种方法可以爬到楼顶。
        //1.  1 阶 + 1 阶 + 1 阶
        //2.  1 阶 + 2 阶
        //3.  2 阶 + 1 阶
        @Test
        public void test2() {
            SolutionDpSpaceCompress solution = new SolutionDpSpaceCompress();
            int ans = solution.climbStairs(3);
            System.out.println(ans);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

