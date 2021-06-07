package com.algorithm.course.暴力递归动态规划.象棋跳马;

import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/13 4:38 下午
 */
public class SolutionUseDp {

    //请同学们自行搜索或者想象一个象棋的棋盘，
    //然后把整个棋盘放入第一象限，棋盘的最左下角是(0,0)位置
    //那么整个棋盘就是横坐标上9条线、纵坐标上10条线的区域
    //给你三个 参数 x，y，k
    //返回“马”从(0,0)位置出发，必须走k步
    //最后落在(x,y)上的方法数有多少种?

    private static final int MAX_X = 8;
    private static final int MAX_Y = 9;
    private int aimX;
    private int aimY;

    public int horseJump(int leftStep, int aimX, int aimY) {
        this.aimX = aimX;
        this.aimY = aimY;
        return process(0, 0, leftStep);
    }

    public int process(int startX, int startY, int leftStep) {
        int[][][] dp = new int[leftStep + 1][MAX_X + 1][MAX_Y + 1];
        // 正好到达目标位置
        dp[0][aimX][aimY] = 1;
        for (int step = 1; step <= leftStep; step++) {
            for (int x = 0; x <= MAX_X; x++) {
                for (int y = 0; y <= MAX_Y; y++) {
                    int ways = 0;
                    ways += pickFormDp(x + 1, y + 2, step - 1, dp);
                    ways += pickFormDp(x + 1, y - 2, step - 1, dp);
                    ways += pickFormDp(x - 1, y + 2, step - 1, dp);
                    ways += pickFormDp(x - 1, y - 2, step - 1, dp);
                    ways += pickFormDp(x + 2, y + 1, step - 1, dp);
                    ways += pickFormDp(x + 2, y - 1, step - 1, dp);
                    ways += pickFormDp(x - 2, y + 1, step - 1, dp);
                    ways += pickFormDp(x - 2, y - 1, step - 1, dp);
                    dp[step][x][y] = ways;
                }
            }
        }
        return dp[leftStep][startX][startY];
    }

    public int pickFormDp(int startX, int startY, int leftStep, int[][][] dp) {
        // 跳出棋盘
        if (startX < 0 || startX > MAX_X || startY < 0 || startY > MAX_Y) {
            return 0;
        }
        return dp[leftStep][startX][startY];
    }

    public static class TestClass {
        @Test
        public void test1() {
            int aimX = 7;
            int aimY = 7;
            int leftStep = 10;
            SolutionUseDp solutionUseDp = new SolutionUseDp();
            int ways = solutionUseDp.horseJump(leftStep, aimX, aimY);
            System.out.println("方法数:" + ways);

            Solution solution = new Solution();
            int ways2 = solution.horseJump(leftStep, aimX, aimY);
            System.out.println("方法数:" + ways2);
        }
    }
}
