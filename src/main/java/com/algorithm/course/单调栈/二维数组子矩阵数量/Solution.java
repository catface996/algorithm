package com.algorithm.course.单调栈.二维数组子矩阵数量;

import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
public class Solution {

    //TODO 需要优化常数时间
    // 11:17 上午	info
    //				解答成功:
    //				执行耗时:7 ms,击败了59.75% 的Java用户
    //				内存消耗:39.1 MB,击败了67.18% 的Java用户

    //给定一个二维数组matrix，其中的值不是0就是1，
    //返回全部由1组成的子矩形数量

    // 解题思路: 以每一行为底的所有子矩阵的和即所有子矩阵的和
    // 问题就转换成如何求每一行为底的子数组个数
    // 首先对矩阵处理,处理成"直方"矩阵
    // 以高度为h的矩阵为例,找到左右两侧最近且比它小的矩阵下标,左侧为l,右侧为r 假设左侧高度为h1,右侧高度为h2,h1和h2的最大值为hMin
    // 则 以h,h-1,h-2,...,hMin+1为高度的所有子矩阵累加,高度为h的子矩阵数量为 (r-l-1) * (r-l-1+1) /2
    // 其他高度为h-1,h-2,...,hMin+1的子矩阵数量与高度为h的子矩阵的数量相同

    int rows;
    int cols;
    MyStack myStack;
    int count = 0;

    public int numSubmat(int[][] mat) {
        return getSubMatrixNum(mat);
    }

    public int getSubMatrixNum(int[][] matrix) {
        rows = matrix.length;
        cols = matrix[0].length;
        myStack = new MyStack(cols);
        sumHeightAndProcess(matrix);
        return count;
    }

    private void sumHeightAndProcess(int[][] matrix) {
        int[][] tempMatrix = new int[rows][cols];
        System.arraycopy(matrix[0], 0, tempMatrix[0], 0, cols);
        process(tempMatrix[0]);
        for (int row = 1; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (matrix[row][col] == 1) {
                    tempMatrix[row][col] = tempMatrix[row - 1][col] + 1;
                } else {
                    tempMatrix[row][col] = 0;
                }
            }
            process(tempMatrix[row]);
        }
    }

    private void process(int[] data) {
        for (int i = 0; i < cols; i++) {
            while (!myStack.isEmpty() && data[i] <= data[myStack.peek()]) {
                int popHeight = data[myStack.pop()];
                if (popHeight == 0) {
                    continue;
                }
                int leftHeight = myStack.isEmpty() ? 0 : data[myStack.peek()];
                if (popHeight == leftHeight) {
                    // 如果弹出高度与剩余栈顶高度一致,不结算,等下次弹出结算
                    continue;
                }
                int rightHeight = data[i];
                int leftIndex = myStack.isEmpty() ? 0 : myStack.peek() + 1;
                int n = i - leftIndex;
                int tempCount = (n * (n + 1)) >> 1;
                for (int height = popHeight; height > Math.max(rightHeight, leftHeight); height--) {
                    count += tempCount;
                }
            }
            myStack.push(i);
        }

        while (!myStack.isEmpty()) {
            int popHeight = data[myStack.pop()];
            if (popHeight == 0) {
                continue;
            }
            int leftHeight = myStack.isEmpty() ? 0 : data[myStack.peek()];
            if (popHeight == leftHeight) {
                // 如果弹出高度与剩余栈顶高度一致,不结算,等下次弹出结算
                continue;
            }
            int leftIndex = myStack.isEmpty() ? 0 : myStack.peek() + 1;
            int n = cols - leftIndex;
            int tempCount = (n * (n + 1)) >> 1;
            for (int height = popHeight; height > leftHeight; height--) {
                count += tempCount;
            }
        }
    }

    public static class MyStack {
        int[] arr;
        int top;

        public MyStack(int size) {
            arr = new int[size];
            top = -1;
        }

        public int pop() {
            return arr[top--];
        }

        public void push(int value) {
            arr[++top] = value;
        }

        public int peek() {
            if (top == -1) {
                return 0;
            }
            return arr[top];
        }

        public boolean isEmpty() {
            return top == -1;
        }
    }

    public static class TestClass {
        // Input: mat = [[1,0,1],
        //              [1,1,0],
        //              [1,1,0]]
        //Output: 13
        @Test
        public void test1() {
            int[][] matrix = {
                {1, 0, 1},
                {1, 1, 0},
                {1, 1, 0}
            };
            Solution solution = new Solution();
            int count = solution.numSubmat(matrix);
            System.out.println(count);
        }

        //解答失败:
        //测试用例:[[0,1],[1,1],[1,0]]
        //测试结果:8
        //期望结果:7
        @Test
        public void test2() {
            int[][] matrix = {
                {0, 1},
                {1, 1},
                {1, 0}
            };
            Solution solution = new Solution();
            int count = solution.numSubmat(matrix);
            System.out.println(count);
        }
    }
}
