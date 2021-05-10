package course.索引树.impl;

import org.junit.Test;

/**
 * @author by catface
 * @date 2021/5/10 11:14 上午
 */
public class Solution2D {

    public static class IndexTree2D {
        int[][] tree;
        int rows;
        int cols;

        public IndexTree2D(int[][] matrix) {
            if (matrix.length <= 0 || matrix[0].length <= 0) {
                return;
            }
            // 弃用第0行
            rows = matrix.length + 1;
            // 弃用第0列
            cols = matrix[0].length + 1;
            tree = new int[rows][cols];
            for (int row = 0; row < rows - 1; row++) {
                for (int col = 0; col < cols - 1; col++) {
                    add(row + 1, col + 1, matrix[row][col]);
                }
            }
        }

        /**
         * 累加到指定位置
         *
         * @param row   行号,弃用0位置,从1位置开始
         * @param col   列号,弃用0位置,从1位置开始
         * @param value 要累加的值
         */
        public void add(int row, int col, int value) {
            for (int tempRow = row; tempRow <= rows; tempRow += tempRow & -tempRow) {
                for (int tempCol = col; tempCol <= cols; tempCol += tempCol & -tempCol) {
                    tree[tempRow][tempCol] += value;
                }
            }
        }

        /**
         * 计算从(1,1)到(row,col)构成的子矩阵的累加和
         *
         * @param row 子矩阵的最大行号
         * @param col 子矩阵的最大列号
         * @return 子矩阵的累加和
         */
        public int sum(int row, int col) {
            int ans = 0;
            for (int tempRow = row; tempRow > 0; tempRow -= tempRow & -tempRow) {
                for (int tempCol = col; tempCol > 0; tempCol -= tempCol & -tempCol) {
                    ans += tree[tempRow][tempCol];
                }
            }
            return ans;
        }
    }

    public static class TestClass {
        @Test
        public void test() {
            int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
            };
            IndexTree2D indexTree2D = new IndexTree2D(matrix);
            int ans = indexTree2D.sum(3, 3);
            System.out.println(ans);
            indexTree2D.add(3, 3, 10);
            int ans2 = indexTree2D.sum(3, 3);
            System.out.println(ans2);
        }
    }
}
