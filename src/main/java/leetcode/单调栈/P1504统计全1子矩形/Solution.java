package leetcode.单调栈.P1504统计全1子矩形;

//给你一个只包含 0 和 1 的 rows * columns 矩阵 mat ，请你返回有多少个 子矩形 的元素全部都是 1 。
//
//
//
// 示例 1：
//
//
//输入：mat = [[1,0,1],
//            [1,1,0],
//            [1,1,0]]
//输出：13
//解释：
//有 6 个 1x1 的矩形。
//有 2 个 1x2 的矩形。
//有 3 个 2x1 的矩形。
//有 1 个 2x2 的矩形。
//有 1 个 3x1 的矩形。
//矩形数目总共 = 6 + 2 + 3 + 1 + 1 = 13 。
//
//
// 示例 2：
//
//
//输入：mat = [[0,1,1,0],
//            [0,1,1,1],
//            [1,1,1,0]]
//输出：24
//解释：
//有 8 个 1x1 的子矩形。
//有 5 个 1x2 的子矩形。
//有 2 个 1x3 的子矩形。
//有 4 个 2x1 的子矩形。
//有 2 个 2x2 的子矩形。
//有 2 个 3x1 的子矩形。
//有 1 个 3x2 的子矩形。
//矩形数目总共 = 8 + 5 + 2 + 4 + 2 + 2 + 1 = 24 。
//
//
// 示例 3：
//
//
//输入：mat = [[1,1,1,1,1,1]]
//输出：21
//
//
// 示例 4：
//
//
//输入：mat = [[1,0,1],[0,1,0],[1,0,1]]
//输出：5
//
//
//
//
// 提示：
//
//
// 1 <= rows <= 150
// 1 <= columns <= 150
// 0 <= mat[i][j] <= 1
//
// Related Topics 动态规划
// 👍 95 👎 0

import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    //TODO 需要优化常数时间
    // 11:17 上午	info
    //				解答成功:
    //				执行耗时:7 ms,击败了59.75% 的Java用户
    //				内存消耗:39.1 MB,击败了67.18% 的Java用户

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
//leetcode submit region end(Prohibit modification and deletion)

