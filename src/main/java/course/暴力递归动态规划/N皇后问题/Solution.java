package course.暴力递归动态规划.N皇后问题;

import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/16 5:45 下午
 */
public class Solution {
    //N皇后问题是指在N*N的棋盘上要摆N个皇后，
    //要求任何两个皇后不同行、不同列， 也不在同一条斜线上
    //给定一个整数n，返回n皇后的摆法有多少种 n=1，返回1
    //n=2或3，2皇后和3皇后问题无论怎么摆都不行，返回0
    //n=8，返回92

    public int calculateWays(int n) {
        int[] choose = new int[n];
        return process(n, 0, choose);
    }

    /**
     * 递归摆放皇后
     *
     * @param n       总共要摆放多少个皇后
     * @param current 当前摆放到第几个皇后,第current个皇后凡在第current行上
     * @param choose  已经摆放好的皇后,下标代表行,值代表列
     * @return 摆放皇后的方案数
     */
    public int process(int n, int current, int[] choose) {
        // 所有皇后摆放完成,发现一种可行方案
        if (current >= n) {
            return 1;
        }
        // 对每一行中的所有列,逐一尝试
        int ways = 0;
        for (int col = 0; col < n; col++) {
            // 判断是否可以放置当前皇后时,只需要跟已经放置的皇后做冲突检测,choose数组不需要回溯
            boolean canSet = canSet(current, col, choose);
            if (canSet) {
                choose[current] = col;
                ways += process(n, current + 1, choose);
            }
        }
        return ways;
    }

    /**
     * 检查当前要摆放的位置,是否与已摆放的位置冲突
     *
     * @param row    要摆放的行
     * @param col    要摆放的列
     * @param choose 已经摆放的位置
     * @return true:可以摆放;false:不可以摆放
     */
    public boolean canSet(int row, int col, int[] choose) {
        for (int chooseRow = 0; chooseRow < row; chooseRow++) {
            // 待放置的位置与已放置的皇后同列
            if (choose[chooseRow] == col) {
                return false;
            }
            // 是否是对角线(如何判断对角线,如果行坐标的距离差的绝对值==列坐标的距离差的绝对值,说明是同一条对角线)
            if (Math.abs(choose[chooseRow] - col) == Math.abs(row - chooseRow)) {
                return false;
            }
        }
        return true;
    }

    public static class TestClass {
        @Test
        public void test1() {
            int n = 6;
            Solution solution = new Solution();
            int ways = solution.calculateWays(n);
            int ways2 = Code03_NQueens.num1(n);
            System.out.println(n + "皇后的摆放方案数:" + ways);
            System.out.println(n + "皇后的摆放方案数:" + ways2);
        }

        @Test
        public void test2() {
            for (int n = 1; n < 10; n++) {
                Solution solution = new Solution();
                int ways = solution.calculateWays(n);
                int ways2 = Code03_NQueens.num1(n);
                assert ways == ways2;
            }
            System.out.println("Good,you success!");
        }
    }
}
