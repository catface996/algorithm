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
class Solution3 {

    // 10:28 上午	info
    //					解答成功:
    //					执行耗时:11 ms,击败了23.55% 的Java用户
    //					内存消耗:39.1 MB,击败了73.72% 的Java用户

    int[][] serial1InRow;
    int[][] serial1InCol;
    int[][] grid;
    int rows;
    int cols;

    public int largest1BorderedSquare(int[][] grid) {
        this.grid = grid;
        rows = grid.length;
        cols = grid[0].length;
        countSerial1InRow();
        countSerial1InCol();
        // 最外层循环用size来限定
        int ans = 0;
        for (int borderLength = Math.min(rows, cols); borderLength >= 1; borderLength--) {
            if (hasSquire(borderLength)) {
                ans = borderLength;
                break;
            }
        }
        return ans * ans;
    }

    private boolean hasSquire(int borderLength) {
        for (int startRow = 0; startRow <= rows - borderLength; startRow++) {
            for (int startCol = 0; startCol <= cols - borderLength; startCol++) {
                if (isSquare(startRow, startCol, borderLength)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void countSerial1InRow() {
        serial1InRow = new int[rows][cols];
        for (int row = 0; row < rows; row++) {
            serial1InRow[row][cols - 1] = grid[row][cols - 1];
            for (int col = cols - 2; col >= 0; col--) {
                if (grid[row][col] == 0) {
                    serial1InRow[row][col] = 0;
                } else {
                    serial1InRow[row][col] = serial1InRow[row][col + 1] + 1;
                }
            }
        }
    }

    private void countSerial1InCol() {
        serial1InCol = new int[rows][cols];
        for (int col = 0; col < cols; col++) {
            serial1InCol[rows - 1][col] = grid[rows - 1][col];
            for (int row = rows - 2; row >= 0; row--) {
                if (grid[row][col] == 0) {
                    serial1InCol[row][col] = 0;
                } else {
                    serial1InCol[row][col] = serial1InCol[row + 1][col] + 1;
                }
            }
        }
    }

    private boolean isSquare(int startRow, int startCol, int borderLength) {
        int endRow = startRow + borderLength - 1;
        int endCol = startCol + borderLength - 1;
        if (serial1InRow[startRow][startCol] < borderLength) {
            return false;
        }
        if (serial1InCol[startRow][startCol] < borderLength) {
            return false;
        }
        if (serial1InCol[startRow][endCol] < borderLength) {
            return false;
        }
        return serial1InRow[endRow][startCol] >= borderLength;
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
            Solution3 solution = new Solution3();
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
            Solution3 solution = new Solution3();
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
            Solution3 solution = new Solution3();
            int largest = solution.largest1BorderedSquare(grid);
            System.out.println(largest);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

