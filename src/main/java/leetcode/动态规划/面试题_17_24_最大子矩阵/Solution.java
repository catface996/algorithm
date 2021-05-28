package leetcode.动态规划.面试题_17_24_最大子矩阵;
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
public class Solution {

    // 有缺陷,不能用此种分析方法

    SumInfo min = new SumInfo(0, 0, 0, 0, Integer.MIN_VALUE);
    SumInfo max = new SumInfo(0, 0, 0, 0, Integer.MIN_VALUE);
    int[][] matrix;

    /**
     * 暴力解法
     *
     * @param matrix 矩阵
     * @return
     */
    public int[] getMaxMatrix(int[][] matrix) {
        this.matrix = matrix;
        process(0, 0, matrix.length - 1, matrix[0].length - 1);
        return new int[] {max.startRow, max.startCol, max.endRow, max.endCol};
    }

    public SumInfo process(int startRow, int startCol, int endRow, int endCol) {
        if (endRow < 0 || endCol < 0) {
            return min;
        }
        SumInfo p1 = process(startRow, startCol, endRow - 1, endCol-1);
        SumInfo p2 = process(startRow, startCol, endRow, endCol - 1);
        SumInfo p3 = new SumInfo(endRow, endCol, endRow, endCol, matrix[endRow][endCol]);
        SumInfo ans = p1.sum > p2.sum ? p1 : p2;
        ans = ans.sum > p3.sum ? ans : p3;
        if (ans.sum > max.sum) {
            max = ans;
        }
        return ans;
    }

    public static class SumInfo {
        int startRow;
        int startCol;
        int endRow;
        int endCol;
        int sum;

        public SumInfo(int startRow, int startCol, int endRow, int endCol, int sum) {
            this.startRow = startRow;
            this.startCol = startCol;
            this.endRow = endRow;
            this.endCol = endCol;
            this.sum = sum;
        }
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
            Solution solution = new Solution();
            int[] ans = solution.getMaxMatrix(matrix);
            log.info("ans:{}", ans);
        }

        @Test
        public void test2() {
            int[][] matrix = {
                {-1, 2, -1, 2, -1, 2, -1 - 1},
                {2, -1, 2, -1, 2, -1, 2, 2},
                {-1, -1, -1, -1, -1, -1, -1, -1},
                {7, 8, 9, 10, 11, 2, 2, 2},
                {-1, 2, -1, 2, -1, 2, -1 - 1},
            };
            Solution solution = new Solution();
            int[] ans = solution.getMaxMatrix(matrix);
            log.info("ans:{}", ans);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

