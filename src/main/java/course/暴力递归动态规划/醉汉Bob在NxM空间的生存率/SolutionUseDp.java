package course.暴力递归动态规划.醉汉Bob在NxM空间的生存率;

import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/15 2:23 下午
 */
public class SolutionUseDp {

    //给定5个参数，N，M，row，col，k
    //表示在N*M的区域上，醉汉Bob初始在(row,col)位置
    //Bob一共要迈出k步，且每步都会等概率向上下左右四个方向走一个单位
    //任何时候Bob只要离开N*M的区域，就直接死亡
    //返回k步之后，Bob还在N*M的区域的概率

    /**
     * 计算醉汉Bob的生存率
     *
     * @param n   区域行数
     * @param m   区域列数
     * @param row 当前所在的行
     * @param col 当前所在列
     * @param k   总共要走多少步数
     * @return 存货的行走方案数
     */
    public double survivorRate(int n, int m, int row, int col, int k) {
        return 1.0 * process(n, m, row, col, k) / Math.pow(4, k);
    }

    /**
     * 计算醉汉Bob的生存率
     *
     * @param n        区域行数
     * @param m        区域列数
     * @param row      当前所在的行
     * @param col      当前所在列
     * @param leftStep 还剩多少步要走
     * @return 存货的行走方案数
     */
    public long process(int n, int m, int row, int col, int leftStep) {
        long[][][] dp = new long[leftStep + 1][n][m];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                dp[0][r][c] = 1;
            }
        }
        for (int k = 1; k <= leftStep; k++) {
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < m; c++) {
                    long ways = 0;
                    ways += pickFromDp(k - 1, r - 1, c, dp);
                    ways += pickFromDp(k - 1, r + 1, c, dp);
                    ways += pickFromDp(k - 1, r, c - 1, dp);
                    ways += pickFromDp(k - 1, r, c + 1, dp);
                    dp[k][r][c] = ways;
                }
            }
        }
        return dp[leftStep][row][col];
    }

    public long pickFromDp(int k, int r, int c, long[][][] dp) {
        if (r < 0 || r >= dp[0].length || c < 0 || c >= dp[0][0].length) {
            return 0;
        }
        return dp[k][r][c];
    }

    public static class TestClass {
        @Test
        public void test1() {
            int n = 50;
            int m = 50;
            int row = 6;
            int col = 6;
            int k = 10;
            SolutionUseDp solution = new SolutionUseDp();
            long survivorNum1 = solution.process(n, m, row, col, k);
            long survivorNum2 = Code05_BobDie.process(row, col, k, n, m);
            System.out.println("存活率方案数1:" + survivorNum1);
            System.out.println("存活率方案数2:" + survivorNum2);
            assert survivorNum1 == survivorNum2;

            double rate1 = solution.survivorRate(n, m, row, col, k);
            double rate2 = Code05_BobDie.livePosibility2(row, col, k, n, m);
            System.out.println("存活率1:" + rate1);
            System.out.println("存活率2:" + rate2);
            assert Double.compare(rate1, rate2) == 0;
        }

        @Test
        public void test2() {
            for (int i = 0; i < 100; i++) {
                int min = 30;
                int max = 50;
                int n = (int)(Math.random() * (max - min + 1) + min);
                int m = (int)(Math.random() * (max - min + 1) + min);
                max = 10;
                min = 6;
                int row = (int)(Math.random() * (max - min + 1) + min);
                int col = (int)(Math.random() * (max - min + 1) + min);
                int k = (int)(Math.random() * (15 - 10 + 1) + 10);
                SolutionUseDp solution = new SolutionUseDp();
                long survivorNum1 = solution.process(n, m, row, col, k);
                long survivorNum2 = Code05_BobDie.process(row, col, k, n, m);
                System.out.println("存活率方案数1:" + survivorNum1);
                System.out.println("存活率方案数2:" + survivorNum2);
                assert survivorNum1 == survivorNum2;

                double rate1 = solution.survivorRate(n, m, row, col, k);
                double rate2 = Code05_BobDie.livePosibility2(row, col, k, n, m);
                System.out.println("存活率1:" + rate1);
                System.out.println("存活率2:" + rate2);
                assert Double.compare(rate1, rate2) == 0;
            }
        }
    }
}
