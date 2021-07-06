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
class Solution2 {

    //2:56 下午	info
    //				运行失败:
    //				Time Limit Exceeded
    //				测试用例:681692778
    //				351251360
    //				stdout:

    private int count = 0;

    public int findKthNumber(int n, int k) {
        count = 0;
        int tempN = n;
        while (tempN > 0) {
            tempN = tempN / 10;
        }
        for (int i = 1; i < 10; i++) {
            int ans = get(i, n, k);
            if (ans > 0) {
                return ans;
            }
        }
        return -1;
    }

    private int get(int ans, int n, int k) {
        if (ans > n) {
            return -1;
        }
        count++;
        if (count >= k) {
            return ans;
        }
        int tempAns = ans * 10;
        for (int i = 0; i < 10; i++) {
            int realAns = get(tempAns + i, n, k);
            if (realAns == -1) {
                break;
            }
            if (realAns == 0) {
                continue;
            }
            if (realAns > 0) {
                return realAns;
            }
        }
        return 0;
    }

    public static class TestClass {

        @Test
        public void test1() {
            Solution2 solution = new Solution2();
            int ans = solution.findKthNumber(15, 7);
            System.out.println(ans);
        }

        @Test
        public void test12() {
            Solution2 solution = new Solution2();
            int ans = solution.findKthNumber(2, 2);
            System.out.println(ans);
        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)

