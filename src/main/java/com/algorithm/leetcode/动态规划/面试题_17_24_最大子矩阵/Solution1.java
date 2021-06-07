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

import com.algorithm.util.ArrayUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
@Slf4j
public class Solution1 {

    // 5:21 下午	info
    //				解答成功:
    //				执行耗时:96 ms,击败了8.95% 的Java用户
    //				内存消耗:43.7 MB,击败了6.43% 的Java用户

    int rows;
    int cols;
    int[][] sumMatrix;
    int max;
    int pre;
    int a;
    int b;
    int c;
    int d;

    /**
     * 求各种高度的子矩阵的每列的累加和,转换成一维数组,转换成求子数组的最大累加和子数组问题.
     *
     * @param matrix 矩阵
     * @return
     */
    public int[] getMaxMatrix(int[][] matrix) {
        this.rows = matrix.length;
        this.cols = matrix[0].length;
        max = Integer.MIN_VALUE;
        pre = Integer.MIN_VALUE;
        a = 0;
        b = 0;
        c = 0;
        d = 0;
        buildSum(matrix);
        // 在各种高度的矩阵中求解,相对容易理解
        for (int height = 1; height <= rows; height++) {

            // 高度为height时,求起始结束的行号
            int startRow = 0;
            for (; ; startRow++) {
                int endRow = startRow + height - 1;
                // 根据起始结束的行号获取累加和数列
                int[] arr = getSumArr(startRow, endRow);
                if (arr == null) {
                    break;
                }
                int startCol = 0;
                pre = 0;
                for (int col = 0; col < cols; col++) {
                    pre = Math.max(pre + arr[col], arr[col]);
                    if (pre == arr[col]) {
                        startCol = col;
                    }
                    max = Math.max(max, pre);
                    if (max == pre) {
                        a = startRow;
                        b = startCol;
                        c = endRow;
                        d = col;
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
            Solution1 solution = new Solution1();
            int[] ans = solution.getMaxMatrix(matrix);
            log.info("ans:{}", ans);
        }

        @Test
        public void test2() {
            int[][] matrix = {
                {+1, +2, -1, +2, -1, +2, -1, -1},
                {+2, -1, +2, -1, +2, -1, +2, +2},
                {-1, -1, -1, -1, -1, -1, -1, -1},
                {+7, +8, +9, 10, 11, +2, -2, -2},
                {-1, +2, -1, +2, -1, -2, -1, -1},
            };
            Solution1 solution = new Solution1();
            int[] ans = solution.getMaxMatrix(matrix);
            log.info("ans:{}", ans);
        }

        @Test
        public void testForce() {
            for (int i = 0; i < 1000; i++) {
                int[][] matrix = ArrayUtil.randomMatrix(10, 10, 1, 10, true);
                Solution1 solution1 = new Solution1();
                int[] ans1 = solution1.getMaxMatrix(matrix);
                int[] ans2 = Code03_SubMatrixMaxSum.getMaxMatrix(matrix);
                if (solution1.max != Code03_SubMatrixMaxSum.max
                    || ArrayUtil.sumMatrix(matrix, ans1[0], ans1[1], ans1[2], ans1[3])
                    != ArrayUtil.sumMatrix(matrix, ans2[0], ans2[1], ans2[2], ans2[3])) {
                    for (int[] ints : matrix) {
                        log.info("{}", ints);
                    }
                    log.info("ans1:{}", ans1);
                    log.info("ans2:{}", ans2);
                }
            }

        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

