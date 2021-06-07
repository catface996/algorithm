package com.algorithm.leetcode.动态规划.面试题_17_24_最大子矩阵;
//给定一个正整数、负整数和 0 组成的 N × M 矩阵，编写代码找出元素总和最大的子矩阵。
//
// 返回一个数组 [r1, c1, r2, c2]，其中 r1, c1 分别代表子矩阵左上角的行号和列号，r2, c2 分别代表右下角的行号和列号。若有多个满
//足条件的子矩阵，返回任意一个均可。
//
// 注意：本题相对书上原题稍作改动
//
// 示例：
//
// 输入：
//[
//   [-1,0],
//   [0,-1]
//]
//输出：[0,1,0,1]
//解释：输入中标粗的元素即为输出所表示的矩阵
//
//
//
// 说明：
//
//
// 1 <= matrix.length, matrix[0].length <= 200
//
// Related Topics 动态规划
// 👍 74 👎 0

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
@Slf4j
public class Solution0 {

    int rows;
    int cols;
    int[][] sumMatrix;
    int max = Integer.MIN_VALUE;
    int pre = Integer.MIN_VALUE;
    int a = 0;
    int b = 0;
    int c = 0;
    int d = 0;

    /**
     * 暴力解法
     *
     * @param matrix 矩阵
     * @return
     */
    public int[] getMaxMatrix(int[][] matrix) {
        this.rows = matrix.length;
        this.cols = matrix[0].length;
        buildSum(matrix);
        for (int startRow = 0; startRow < rows; startRow++) {
            for (int endRow = startRow; endRow < rows; endRow++) {
                int[] arr = getSumArr(startRow, endRow);
                for (int startCol = 0; startCol < cols; startCol++) {
                    for (int endCol = startCol; endCol < cols; endCol++) {
                        int sum = 0;
                        for (int col = startCol; col < endCol; col++) {
                            sum += arr[col];
                        }
                        max = Math.max(max, sum);
                        if (max == sum) {
                            a = startRow;
                            b = startCol;
                            c = endRow;
                            d = endCol;
                        }
                    }
                }
            }
        }
        return new int[] {a, b, c, d};
    }

    public void buildSum(int[][] matrix) {
        sumMatrix = new int[rows + 1][cols];
        for (int col = 0; col < cols; col++) {
            for (int row = 0; row < rows; row++) {
                sumMatrix[row + 1][col] = sumMatrix[row][col] + matrix[row][col];
            }
        }
    }

    public int[] getSumArr(int startRow, int endRow) {
        if (endRow < rows) {
            int[] sumArr = new int[cols];
            for (int col = 0; col < cols; col++) {
                sumArr[col] = sumMatrix[endRow + 1][col] - sumMatrix[startRow][col];
            }
            return sumArr;
        }
        return null;
    }

    public static class TestClass {

        // // 示例：
        ////
        //// 输入：
        ////[
        ///  [-1,0],
        //// [0,-1]
        ////]
        @Test
        public void test() {
            int[][] matrix = {
                {-1, 0},
                {0, -1},
            };
            Solution0 solution = new Solution0();
            int[] ans = solution.getMaxMatrix(matrix);
            log.info("ans:{}", ans);
        }

        @Test
        public void test2() {
            int[][] matrix = {
                {-1, +2, -1, +2, -1, +2, -1, -1},
                {+2, -1, +2, -1, +2, -1, +2, +2},
                {-1, -1, -1, -1, -1, -1, -1, -1},
                {+7, +8, +9, 10, 11, +2, -2, -2},
                {-1, +2, -1, +2, -1, -2, -1, -1},
            };
            Solution0 solution = new Solution0();
            int[] ans = solution.getMaxMatrix(matrix);
            log.info("ans:{}", ans);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

