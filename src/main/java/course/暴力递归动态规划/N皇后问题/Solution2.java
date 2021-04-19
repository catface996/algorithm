package course.暴力递归动态规划.N皇后问题;

import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/16 5:45 下午
 */
public class Solution2 {
    //N皇后问题是指在N*N的棋盘上要摆N个皇后，
    //要求任何两个皇后不同行、不同列， 也不在同一条斜线上
    //给定一个整数n，返回n皇后的摆法有多少种 n=1，返回1
    //n=2或3，2皇后和3皇后问题无论怎么摆都不行，返回0
    //n=8，返回92

    public int calculateWays(int n) {
        if (n < 1 || n > 32) {
            return 0;
        }
        // 如果你是13皇后问题，limit 最右13个1，其他都是0,左移n位-1,数字中从右到左会有n个1
        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process(limit, 0, 0, 0);
    }

    /**
     * 使用位运算来处理N皇后问题
     *
     * @param limit         当前处理到第N位的皇后
     * @param colLimit
     * @param leftDiaLimit
     * @param rightDiaLimit
     * @return
     */
    public int process(int limit, int colLimit, int leftDiaLimit, int rightDiaLimit) {
        if (colLimit == limit) {
            return 1;
        }
        // pos中所有是1的位置，是你可以去尝试皇后的位置
        int pos = limit & (~(colLimit | leftDiaLimit | rightDiaLimit));
        int mostRightOne = 0;
        int res = 0;
        while (pos != 0) {
            mostRightOne = pos & (~pos + 1);
            pos = pos - mostRightOne;
            res += process(limit, colLimit | mostRightOne, (leftDiaLimit | mostRightOne) << 1,
                (rightDiaLimit | mostRightOne) >>> 1);
        }
        return res;
    }

    public static class TestClass {
        @Test
        public void test1() {
            int n = 6;
            Solution2 solution = new Solution2();
            int ways = solution.calculateWays(n);
            int ways2 = Code03_NQueens.num1(n);
            System.out.println(n + "皇后的摆放方案数:" + ways);
            System.out.println(n + "皇后的摆放方案数:" + ways2);
        }

        @Test
        public void test2() {
            for (int n = 1; n < 15; n++) {
                Solution2 solution = new Solution2();
                int ways = solution.calculateWays(n);
                int ways2 = Code03_NQueens.num1(n);
                assert ways == ways2;
            }
            System.out.println("Good,you success!");
        }
    }
}
