package course.暴力递归动态规划.二维数组沿途最小累加和;

import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import util.ArrayUtil;

/**
 * @author by catface
 * @date 2021/4/14 1:49 下午
 */
@Slf4j
public class SolutionUseCache {

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
        int[][] cache = new int[matrix.length][matrix[0].length];
        for (int[] ints : cache) {
            Arrays.fill(ints, -1);
        }
        return process(matrix, 0, 0, cache);
    }

    /**
     * 暴力递归计算最小路径和
     *
     * @param matrix     二维矩阵
     * @param currentRow 当前所在行
     * @param currentCol 当前所在列
     * @param cache      缓存
     * @return 最小路径和
     */
    private int process(int[][] matrix, int currentRow, int currentCol, int[][] cache) {
        // 如果超出矩阵,返回不可到达,用max_value表示
        if (currentRow >= matrix.length || currentCol >= matrix[currentRow].length) {
            return Integer.MAX_VALUE;
        }
        // 正好走到最右下角
        if (currentRow == matrix.length - 1 && currentCol == matrix[currentCol].length - 1) {
            return matrix[currentRow][currentCol];
        }
        // 扩展到任意位置
        // 如果命中缓存,直接返回
        int ans = cache[currentRow][currentCol];
        if (ans != -1) {
            return ans;
        }
        // 向下走
        int chooseDownValue = process(matrix, currentRow + 1, currentCol, cache);
        // 向右走
        int chooseRightValue = process(matrix, currentRow, currentCol + 1, cache);
        // 选择路径和最小返回
        int minPathSum = Math.min(chooseDownValue, chooseRightValue) + matrix[currentRow][currentCol];
        cache[currentRow][currentCol] = minPathSum;
        return minPathSum;
    }

    public static class TestClass {
        @Test
        public void test1() {
            int[][] matrix = ArrayUtil.randomMatrix(5, 5, 1, 30);
            log.info("matrix:{}", (Object)matrix);
            SolutionUseCache solution = new SolutionUseCache();
            int minPathSum = solution.calculateMinPathSum(matrix);
            System.out.println("最小路径和:" + minPathSum);
            int minPathSum2 = Code01_MinPathSum.minPathSum1(matrix);
            System.out.println("最小路径和:" + minPathSum2);
        }
    }
}
