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
class Solution {

    // 7:00 下午	info
    //				解答成功:
    //				执行耗时:22 ms,击败了7.19% 的Java用户
    //				内存消耗:39.1 MB,击败了77.40% 的Java用户

    int[][] sum1ForRow;
    int[][] sum1ForCol;
    int[][] grid;
    int rows;
    int cols;

    public int largest1BorderedSquare(int[][] grid) {
        rows = grid.length;
        cols = grid[0].length;
        this.grid = grid;
        sum1ForRow = count1EveryRow(grid);
        sum1ForCol = count1EveryCol(grid);
        int ans = 0;
        for (int startRow = 0; startRow < rows; startRow++) {
            for (int startCol = 0; startCol < cols; startCol++) {
                for (int borderLength = ans + 1; borderLength <= getMaxBorderLength(startRow, startCol);
                     borderLength++) {
                    if (isSquare(startRow, startCol, borderLength)) {
                        ans = Math.max(ans, borderLength);
                    }
                }
            }
        }
        return ans * ans;
    }

    private int[][] count1EveryRow(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] sum1ForRow = new int[rows][cols];
        for (int row = 0; row < rows; row++) {
            sum1ForRow[row][0] = grid[row][0];
            for (int col = 1; col < cols; col++) {
                sum1ForRow[row][col] = sum1ForRow[row][col - 1] + grid[row][col];
            }
        }
        return sum1ForRow;
    }

    private int[][] count1EveryCol(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] sum1ForCol = new int[rows][cols];
        for (int col = 0; col < cols; col++) {
            sum1ForCol[0][col] = grid[0][col];
            for (int row = 1; row < rows; row++) {
                sum1ForCol[row][col] = sum1ForCol[row - 1][col] + grid[row][col];
            }
        }
        return sum1ForCol;
    }

    private boolean isSquare(int startRow, int startCol, int borderLength) {
        int endRow = startRow + borderLength - 1;
        int endCol = startCol + borderLength - 1;
        int right1Num = sum1ForRow[startRow][endCol] - sum1ForRow[startRow][startCol] + grid[startRow][startCol];
        if (right1Num != borderLength) {
            return false;
        }
        int down1Num = sum1ForCol[endRow][startCol] - sum1ForCol[startRow][startCol] + grid[startRow][startCol];
        if (down1Num != borderLength) {
            return false;
        }
        int rightDown = sum1ForCol[endRow][endCol] - sum1ForCol[startRow][endCol] + grid[startRow][endCol];
        if (rightDown != borderLength) {
            return false;
        }
        int downRight = sum1ForRow[endRow][endCol] - sum1ForRow[endRow][startCol] + grid[endRow][startCol];
        if (downRight != borderLength) {
            return false;
        }
        return true;
    }

    /**
     * 从起点开始,可能扩展的最大边长
     *
     * @param startRow
     * @param startCol
     * @return
     */
    public int getMaxBorderLength(int startRow, int startCol) {
        int endRow = rows - 1;
        int endCol = cols - 1;
        int right1Num = sum1ForRow[startRow][endCol] - sum1ForRow[startRow][startCol] + grid[startRow][startCol];
        int down1Num = sum1ForCol[endRow][startCol] - sum1ForCol[startRow][startCol] + grid[startRow][startCol];
        return Math.min(right1Num, down1Num);
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
            Solution solution = new Solution();
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
            Solution solution = new Solution();
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
            Solution solution = new Solution();
            int largest = solution.largest1BorderedSquare(grid);
            System.out.println(largest);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

