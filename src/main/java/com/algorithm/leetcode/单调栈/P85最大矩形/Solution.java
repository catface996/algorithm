package com.algorithm.leetcode.单调栈.P85最大矩形;

//给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
//
//
//
// 示例 1：
//
//
//输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"]
//,["1","0","0","1","0"]]
//输出：6
//解释：最大矩形如上图所示。
//
//
// 示例 2：
//
//
//输入：matrix = []
//输出：0
//
//
// 示例 3：
//
//
//输入：matrix = [["0"]]
//输出：0
//
//
// 示例 4：
//
//
//输入：matrix = [["1"]]
//输出：1
//
//
// 示例 5：
//
//
//输入：matrix = [["0","0"]]
//输出：0
//
//
//
//
// 提示：
//
//
// rows == matrix.length
// cols == matrix[0].length
// 0 <= row, cols <= 200
// matrix[i][j] 为 '0' 或 '1'
//
// Related Topics 栈 数组 哈希表 动态规划
// 👍 896 👎 0

import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 5:51 下午	info
    //				解答成功:
    //				执行耗时:4 ms,击败了97.91% 的Java用户
    //				内存消耗:41.4 MB,击败了60.54% 的Java用户

    int max = 0;
    int rows;
    int cols;
    MyStack myStack;

    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length <= 0) {
            return 0;
        }
        rows = matrix.length;
        cols = matrix[0].length;
        int[][] tempMatrix = preProcess(matrix);
        myStack = new MyStack(cols);
        for (int row = 0; row < rows; row++) {
            getMaxArea(tempMatrix[row]);
        }
        return max;
    }

    private int[][] preProcess(char[][] matrix) {
        // 预处理成多个直方图,当前行列,遇到0,高度为0,遇到1,上一行高度加1
        int[][] tempMatrix = new int[rows][cols];
        for (int i = 0; i < cols; i++) {
            tempMatrix[0][i] = matrix[0][i] == '1' ? 1 : 0;
        }
        for (int row = 1; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (matrix[row][col] == '1') {
                    tempMatrix[row][col] = tempMatrix[row - 1][col] + 1;
                } else {
                    tempMatrix[row][col] = 0;
                }
            }
        }
        return tempMatrix;
    }

    private void getMaxArea(int[] heights) {
        for (int i = 0; i < cols; i++) {
            while (!myStack.isEmpty() && heights[i] <= heights[myStack.peek()]) {
                int height = heights[myStack.pop()];
                int width;
                if (myStack.isEmpty()) {
                    width = i;
                } else {
                    width = i - myStack.peek() - 1;
                }
                max = Math.max(max, width * height);
            }
            myStack.push(i);
        }
        while (!myStack.isEmpty()) {
            int height = heights[myStack.pop()];
            int width;
            if (myStack.isEmpty()) {
                width = cols;
            } else {
                width = cols - myStack.peek() - 1;
            }
            max = Math.max(max, width * height);
        }
    }

    public static class MyStack {
        int[] arr;
        int top;

        public MyStack(int size) {
            this.arr = new int[size];
        }

        public void push(int value) {
            arr[++top] = value;
        }

        public int pop() {
            return arr[top--];
        }

        public int peek() {
            return arr[top];
        }

        public boolean isEmpty() {
            return top == -1;
        }
    }

    public static class TestClass {

        // [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
        // 6
        @Test
        public void test() {
            char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
            };
            Solution solution = new Solution();
            int maxRectangle = solution.maximalRectangle(matrix);
            System.out.println(maxRectangle);
        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)

