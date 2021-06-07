package com.algorithm.course.暴力递归动态规划.二维数组沿途最小累加和;

import com.algorithm.util.ArrayUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/14 1:49 下午
 */
@Slf4j
public class SolutionUseDp {

    //给定一个二维数组matrix，一个人必须从左上角出发，最后到达右下角
    //沿途只可以向下或者向右走，沿途的数字都累加就是距离累加和
    //返回最小距离累加和

    /**
     * 计算从矩阵(0,0)位置触发,到达右下角(rowMax,colMax)位置处,经过路径的最小和
     *
     * @param matrix 二维矩阵
     * @return 路径最小和
     */
    public int calculateMinPathSum(int[][] matrix) {
        return process(matrix);
    }

    /**
     * 暴力递归计算最小路径和
     *
     * @param matrix 二维矩阵
     * @return 最小路径和
     */
    private int process(int[][] matrix) {
        int rowSize = matrix.length;
        int colSize = matrix[0].length;
        int[][] dp = new int[rowSize][colSize];
        // 从最右下角出发到最右下角最小路径和
        dp[rowSize - 1][colSize - 1] = matrix[rowSize - 1][colSize - 1];

        // 最后一行,从右到做
        for (int col = colSize - 2; col >= 0; col--) {
            dp[rowSize - 1][col] = matrix[rowSize - 1][col] + dp[rowSize - 1][col + 1];
        }

        // 最后一列,从下到上
        for (int row = rowSize - 2; row >= 0; row--) {
            dp[row][colSize - 1] = matrix[row][colSize - 1] + dp[row + 1][colSize - 1];
        }

        // 其他任意位置
        for (int row = rowSize - 2; row >= 0; row--) {
            for (int col = colSize - 2; col >= 0; col--) {
                dp[row][col] = matrix[row][col] + Math.min(dp[row][col + 1], dp[row + 1][col]);
            }
        }

        return dp[0][0];
    }

    public static class TestClass {
        @Test
        public void test1() {
            int[][] matrix = ArrayUtil.randomMatrix(5, 5, 1, 30);
            log.info("matrix:{}", (Object)matrix);
            SolutionUseDp solution = new SolutionUseDp();
            int minPathSum = solution.calculateMinPathSum(matrix);
            System.out.println("最小路径和:" + minPathSum);
            int minPathSum2 = Code01_MinPathSum.minPathSum1(matrix);
            System.out.println("最小路径和:" + minPathSum2);
        }
    }
}
