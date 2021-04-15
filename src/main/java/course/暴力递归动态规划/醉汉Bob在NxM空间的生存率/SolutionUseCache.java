package course.暴力递归动态规划.醉汉Bob在NxM空间的生存率;

import java.util.Arrays;

import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/15 2:23 下午
 */
public class SolutionUseCache {

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
        long[][][] cache = new long[k + 1][n][m];
        for (int i = 0; i <= k; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(cache[i][j], -1);
            }
        }
        return 1.0 * process(n, m, row, col, k, cache) / Math.pow(4, k);
    }

    /**
     * 计算醉汉Bob的生存率
     *
     * @param n        区域行数
     * @param m        区域列数
     * @param row      当前所在的行
     * @param col      当前所在列
     * @param leftStep 还剩多少步要走
     * @param cache    缓存
     * @return 存货的行走方案数
     */
    public long process(int n, int m, int row, int col, int leftStep, long[][][] cache) {
        // 先判断,进入row,col位置时,是否会死亡,如果不会,再判断是否要继续行走,避免初始位置已经死亡
        // Bob还有步数要走,但是越界,Bob未能存活,无效的行走方案
        if (row < 0 || row >= n || col < 0 || col >= m) {
            return 0;
        }

        // Bob无需走动,发现一种存活方案
        if (leftStep == 0) {
            return 1;
        }

        // 命中缓存
        long ans = cache[leftStep][row][col];
        if (ans != -1) {
            return ans;
        }

        long ways = 0;
        ways += process(n, m, row - 1, col, leftStep - 1, cache);
        ways += process(n, m, row + 1, col, leftStep - 1, cache);
        ways += process(n, m, row, col - 1, leftStep - 1, cache);
        ways += process(n, m, row, col + 1, leftStep - 1, cache);
        cache[leftStep][row][col] = ways;
        return ways;
    }

    public static class TestClass {
        @Test
        public void test1() {
            int n = 50;
            int m = 50;
            int row = 6;
            int col = 6;
            int k = 10;
            SolutionUseCache solution = new SolutionUseCache();
            double rate1 = solution.survivorRate(n, m, row, col, k);
            double rate2 = Code05_BobDie.livePosibility2(row, col, k, n, m);
            System.out.println("存活率1:" + rate1);
            System.out.println("存活率2:" + rate2);
            assert Double.compare(rate1, rate2) == 0;
        }

        @Test
        public void test2() {
            for (int i = 0; i < 1000; i++) {
                int min = 30;
                int max = 50;
                int n = (int)(Math.random() * (max - min + 1) + min);
                int m = (int)(Math.random() * (max - min + 1) + min);
                max = 10;
                min = 6;
                int row = (int)(Math.random() * (max - min + 1) + min);
                int col = (int)(Math.random() * (max - min + 1) + min);
                int k = (int)(Math.random() * (15 - 10 + 1) + 10);
                SolutionUseCache solution = new SolutionUseCache();
                double rate1 = solution.survivorRate(n, m, row, col, k);
                double rate2 = Code05_BobDie.livePosibility2(row, col, k, n, m);
                assert Double.compare(rate1, rate2) == 0;
            }
            System.out.println("Good,you success!");
        }
    }
}
