package com.algorithm.leetcode.动态规划.P1139最大以1为边界的正方形;

//给你一个由若干 0 和 1 组成的二维网格 grid，请你找出边界全部由 1 组成的最大 正方形 子网格，并返回该子网格中的元素数量。如果不存在，则返回 0
//。
//
//
//
// 示例 1：
//
// 输入：grid = [[1,1,1],[1,0,1],[1,1,1]]
//输出：9
//
//
// 示例 2：
//
// 输入：grid = [[1,1,0,0]]
//输出：1
//
//
//
//
// 提示：
//
//
// 1 <= grid.length <= 100
// 1 <= grid[0].length <= 100
// grid[i][j] 为 0 或 1
//
// Related Topics 动态规划
// 👍 71 👎 0

import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution4 {

    // 10:55 上午	info
    //					解答成功:
    //					执行耗时:9 ms,击败了42.32% 的Java用户
    //					内存消耗:39.1 MB,击败了64.50% 的Java用户

    int[][] serial1InRow;
    int[][] serial1InCol;
    int[][] grid;
    int R;
    int C;

    public int largest1BorderedSquare(int[][] grid) {
        this.grid = grid;
        R = grid.length;
        C = grid[0].length;
        countSerial1();
        // 最外层循环用size来限定
        int ans = 0;
        for (int borderLength = Math.min(R, C); borderLength >= 1; borderLength--) {
            if (hasSquire(borderLength)) {
                ans = borderLength;
                break;
            }
        }
        return ans * ans;
    }

    private boolean hasSquire(int borderLength) {
        for (int startRow = 0; startRow <= R - borderLength; startRow++) {
            for (int startCol = 0; startCol <= C - borderLength; startCol++) {
                if (isSquare(startRow, startCol, borderLength)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void countSerial1() {
        serial1InRow = new int[R][C];
        serial1InCol = new int[R][C];
        for (int row = 0; row < R; row++) {
            serial1InRow[row][C - 1] = grid[row][C - 1];
        }
        for (int col = 0; col < C; col++) {
            serial1InCol[R - 1][col] = grid[R - 1][col];
        }
        for (int col = C - 2; col >= 0; col--) {
            if (grid[R - 1][col] == 1) {
                serial1InRow[R - 1][col] = serial1InRow[R - 1][col + 1] + 1;
            }
        }
        for (int row = R - 2; row >= 0; row--) {
            if (grid[row][C - 1] == 1) {
                serial1InCol[row][C - 1] = serial1InCol[row + 1][C - 1] + 1;
            }
        }
        for (int row = R - 2; row >= 0; row--) {
            for (int col = C - 2; col >= 0; col--) {
                if (grid[row][col] == 1) {
                    serial1InRow[row][col] = serial1InRow[row][col + 1] + 1;
                    serial1InCol[row][col] = serial1InCol[row + 1][col] + 1;
                }
            }
        }
    }

    private boolean isSquare(int startRow, int startCol, int borderLength) {
        int endRow = startRow + borderLength - 1;
        int endCol = startCol + borderLength - 1;
        return serial1InRow[startRow][startCol] >= borderLength && serial1InCol[startRow][startCol] >= borderLength
            && serial1InCol[startRow][endCol] >= borderLength && serial1InRow[endRow][startCol] >= borderLength;
    }

    public static class TestClass {

        // 示例 1：
        //
        // 输入：grid = [[1,1,1],[1,0,1],[1,1,1]]
        //输出：9
        @Test
        public void test() {
            int[][] grid = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
            };
            Solution4 solution = new Solution4();
            int largest = solution.largest1BorderedSquare(grid);
            System.out.println(largest);
        }

        // 示例 2：
        //
        // 输入：grid = [[1,1,0,0]]
        //输出：1
        @Test
        public void test2() {
            int[][] grid = {{1, 1, 0, 0}};
            Solution4 solution = new Solution4();
            int largest = solution.largest1BorderedSquare(grid);
            System.out.println(largest);
        }

        // 6:05 下午	info
        //				解答失败:
        //				测试用例:[[0]]
        //				测试结果:1
        //				期望结果:0
        //				stdout:
        @Test
        public void test3() {
            int[][] grid = {{0}};
            Solution4 solution = new Solution4();
            int largest = solution.largest1BorderedSquare(grid);
            System.out.println(largest);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

