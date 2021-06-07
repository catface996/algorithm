package com.algorithm.leetcode.动态规划.P70爬楼梯;
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

import java.util.Arrays;

import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
class SolutionUseCache {

    // 8:17 下午	info
    //				解答成功:
    //				执行耗时:0 ms,击败了100.00% 的Java用户
    //				内存消耗:35.3 MB,击败了32.42% 的Java用户

    public int climbStairs(int n) {
        int[] cache = new int[n + 1];
        Arrays.fill(cache, -1);
        return process(n, cache);
    }

    private int process(int leftStep, int[] cache) {
        if (leftStep < 0) {
            return 0;
        }
        if (leftStep == 0) {
            return 1;
        }
        int ans = cache[leftStep];
        if (ans != -1) {
            return ans;
        }
        // 当前选择走一步
        int ways = process(leftStep - 1, cache);
        // 当前选择走两步
        ways += process(leftStep - 2, cache);
        cache[leftStep] = ways;
        return ways;
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
            SolutionUseCache solution = new SolutionUseCache();
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
            SolutionUseCache solution = new SolutionUseCache();
            int ans = solution.climbStairs(3);
            System.out.println(ans);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

