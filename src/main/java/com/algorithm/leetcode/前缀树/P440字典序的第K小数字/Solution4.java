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
class Solution4 {

    // 11:37 上午	info
    //					运行失败:
    //					Memory Limit Exceeded
    //					测试用例:4289384
    //					1922239
    //					stdout:

    public int findKthNumber(int n, int k) {
        return 1;
    }

    public static class TestClass {

        @Test
        public void test1() {
            Solution4 solution = new Solution4();
            int ans = solution.findKthNumber(15, 7);
            System.out.println(ans);
        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)

