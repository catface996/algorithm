package com.algorithm.leetcode.动态规划.P329矩阵中的最长递增路径;

//给定一个 m x n 整数矩阵 matrix ，找出其中 最长递增路径 的长度。
//
// 对于每个单元格，你可以往上，下，左，右四个方向移动。 你 不能 在 对角线 方向上移动或移动到 边界外（即不允许环绕）。
//
//
//
// 示例 1：
//
//
//输入：matrix = [[9,9,4],[6,6,8],[2,1,1]]
//输出：4
//解释：最长递增路径为 [1, 2, 6, 9]。
//
// 示例 2：
//
//
//输入：matrix = [[3,4,5],[3,2,6],[2,2,1]]
//输出：4
//解释：最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。
//
//
// 示例 3：
//
//
//输入：matrix = [[1]]
//输出：1
//
//
//
//
// 提示：
//
//
// m == matrix.length
// n == matrix[i].length
// 1 <= m, n <= 200
// 0 <= matrix[i][j] <= 231 - 1
//
// Related Topics 深度优先搜索 拓扑排序 记忆化
// 👍 468 👎 0

import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution1 {

    // 5:44 下午	info
    //				运行失败:
    //				Time Limit Exceeded
    //				测试用例:[[0,1,2,3,4,5,6,7,8,9],[19,18,17,16,15,14,13,12,11,10],[20,21,22,23,24,25,26,27,28,29],[39,
    //				38,37,36,35,34,33,32,31,30],[40,41,42,43,44,45,46,47,48,49],[59,58,57,56,55,54,53,52,51,50],[60,
    //				61,62,63,64,65,66,67,68,69],[79,78,77,76,75,74,73,72,71,70],[80,81,82,83,84,85,86,87,88,89],[99,
    //				98,97,96,95,94,93,92,91,90],[100,101,102,103,104,105,106,107,108,109],[119,118,117,116,115,114,
    //				113,112,111,110],[120,121,122,123,124,125,126,127,128,129],[139,138,137,136,135,134,133,132,131,
    //				130],[0,0,0,0,0,0,0,0,0,0]]
    //				stdout:
    // 超时
    int rows;
    int cols;
    private int[][] matrix;

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length < 1) {
            return 0;
        }
        this.matrix = matrix;
        this.rows = matrix.length;
        this.cols = matrix[0].length;
        int max = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                max = Math.max(max, process(row, col));
            }
        }
        return max;
    }

    private int process(int row, int col) {
        int up = row - 1 >= 0 && matrix[row - 1][col] > matrix[row][col] ? process(row - 1, col) : 0;
        int down = row + 1 < rows && matrix[row + 1][col] > matrix[row][col] ? process(row + 1, col) : 0;
        int left = col - 1 >= 0 && matrix[row][col - 1] > matrix[row][col] ? process(row, col - 1) : 0;
        int right = col + 1 < cols && matrix[row][col + 1] > matrix[row][col] ? process(row, col + 1) : 0;
        return Math.max(Math.max(up, down), Math.max(left, right)) + 1;
    }

    public static class TestClass {

        // 示例 1：
        //
        //
        //输入：matrix = [[9,9,4],[6,6,8],[2,1,1]]
        //输出：4
        //解释：最长递增路径为 [1, 2, 6, 9]。
        @Test
        public void test1() {
            int[][] matrix = {
                {9, 9, 4},
                {6, 6, 8},
                {2, 1, 1}
            };
            Solution1 solution1 = new Solution1();
            int ans = solution1.longestIncreasingPath(matrix);
            System.out.println(ans);
        }

        // 示例 2：
        //
        //
        //输入：matrix = [[3,4,5],[3,2,6],[2,2,1]]
        //输出：4
        //解释：最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。
        @Test
        public void test2() {
            int[][] matrix = {
                {3, 4, 5},
                {3, 2, 6},
                {2, 2, 1}
            };
            Solution1 solution1 = new Solution1();
            int ans = solution1.longestIncreasingPath(matrix);
            System.out.println(ans);
        }

        //
        // 示例 3：
        //
        //
        //输入：matrix = [[1]]
        //输出：1
        @Test
        public void test3() {
            int[][] matrix = {
                {1}
            };
            Solution1 solution1 = new Solution1();
            int ans = solution1.longestIncreasingPath(matrix);
            System.out.println(ans);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

