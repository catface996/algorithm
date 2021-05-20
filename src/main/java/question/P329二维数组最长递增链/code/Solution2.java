package question.P329二维数组最长递增链.code;

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
class Solution2 {

    // 5:47 下午	info
    //				解答成功:
    //				执行耗时:8 ms,击败了95.60% 的Java用户
    //				内存消耗:38.6 MB,击败了75.12% 的Java用户
    //记忆化搜索

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
        int[][] dp = new int[rows][cols];
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                max = Math.max(max, process(row, col, dp));
            }
        }
        return max;
    }

    private int process(int row, int col, int[][] dp) {
        if (dp[row][col] != 0) {
            return dp[row][col];
        }
        int up = row - 1 >= 0 && matrix[row - 1][col] > matrix[row][col] ? process(row - 1, col, dp) : 0;
        int down = row + 1 < rows && matrix[row + 1][col] > matrix[row][col] ? process(row + 1, col, dp) : 0;
        int left = col - 1 >= 0 && matrix[row][col - 1] > matrix[row][col] ? process(row, col - 1, dp) : 0;
        int right = col + 1 < cols && matrix[row][col + 1] > matrix[row][col] ? process(row, col + 1, dp) : 0;
        int ans = Math.max(Math.max(up, down), Math.max(left, right)) + 1;
        dp[row][col] = ans;
        return ans;
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
            Solution2 solution1 = new Solution2();
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
            Solution2 solution1 = new Solution2();
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
            Solution2 solution1 = new Solution2();
            int ans = solution1.longestIncreasingPath(matrix);
            System.out.println(ans);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

