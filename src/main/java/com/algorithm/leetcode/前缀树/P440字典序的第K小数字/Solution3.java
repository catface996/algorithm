package com.algorithm.leetcode.前缀树.P440字典序的第K小数字;

//给定整数 n 和 k，找到 1 到 n 中字典序第 k 小的数字。
//
// 注意：1 ≤ k ≤ n ≤ 109。
//
// 示例 :
//
//
//输入:
//n: 13   k: 2
//
//输出:
//10
//
//解释:
//字典序的排列是 [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]，所以第二小的数字是 10。
//
// Related Topics 字典树
// 👍 220 👎 0

import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution3 {

    // 5:51 下午	info
    //				运行失败:
    //				Time Limit Exceeded
    //				测试用例:681692778
    //				351251360
    //				stdout:

    private int count = 0;

    public int findKthNumber(int n, int k) {
        count = 0;
        for (int i = 1; i < 10; i++) {
            long ans = get(i, n, k);
            if (ans > 0) {
                return (int)ans;
            }
        }
        return -1;
    }

    private long get(long ans, int n, int k) {
        if (ans > n) {
            return -1;
        }
        count++;
        if (count >= k) {
            return ans;
        }
        long tempAns = ans * 10;
        for (int i = 0; i < 10; i++) {
            long realAns = get(tempAns + i, n, k);
            if (realAns < 0) {
                break;
            }
            if (realAns == 0) {
                continue;
            }
            return realAns;
        }
        return 0;
    }

    public static class TestClass {

        @Test
        public void test1() {
            Solution3 solution = new Solution3();
            int ans = solution.findKthNumber(15, 7);
            System.out.println(ans);
        }

        @Test
        public void test12() {
            Solution3 solution = new Solution3();
            int ans = solution.findKthNumber(2, 2);
            System.out.println(ans);
        }

        @Test
        public void test2() {
            Solution3 solution = new Solution3();
            int ans = solution.findKthNumber(100, 90);
            System.out.println(ans);
        }

        // 5:17 下午	info
        //				解答失败:
        //				测试用例:681692778
        //				351251360
        //				测试结果:20964905
        //				期望结果:416126219
        //				stdout:
        @Test
        public void test3() {
            Solution3 solution = new Solution3();
            int ans = solution.findKthNumber(681692778, 351251360);
            System.out.println(ans);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

