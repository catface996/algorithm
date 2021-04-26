package leetcode.单调栈.P84柱状图中的最大矩形;

//给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
//
// 求在该柱状图中，能够勾勒出来的矩形的最大面积。
//
//
//
//
//
// 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
//
//
//
//
//
// 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
//
//
//
// 示例:
//
// 输入: [2,1,5,6,2,3]
//输出: 10
// Related Topics 栈 数组
// 👍 1329 👎 0

import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution2 {

    // 5:14 下午	info
    //				解答成功:
    //				执行耗时:10 ms,击败了94.64% 的Java用户
    //				内存消耗:47.8 MB,击败了68.53% 的Java用户

    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length <= 0) {
            return 0;
        }
        int maxArea = Integer.MIN_VALUE;
        MyStack stack = new MyStack(heights.length);
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int width;
                if (stack.isEmpty()) {
                    // 如果栈空了,说明当前出栈的,是截止到待入栈下标的最小值,宽度就是 个数-1 即 (i+1)-1
                    width = i;
                } else {
                    width = i - stack.peek() - 1;
                }
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int height = heights[stack.pop()];
            int width;
            if (stack.isEmpty()) {
                // 栈空了,说明最后弹出的是整个数组中的最小高度,那么宽度就是整个数组的元素个数,即数组长度
                width = heights.length;
            } else {
                width = heights.length - stack.peek() - 1;
            }
            maxArea = Math.max(maxArea, height * width);
        }
        return maxArea;
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

        public int peek() {
            return arr[top];
        }

        public boolean isEmpty() {
            return top == -1;
        }

        public void push(int value) {
            arr[++top] = value;
        }
    }

    public static class TestClass {
        // 输入: [2,1,5,6,2,3]
        //输出: 10
        @Test
        public void test() {
            int[] heights = {2, 1, 5, 6, 2, 3};
            Solution2 solution = new Solution2();
            int maxArea = solution.largestRectangleArea(heights);
            System.out.println(maxArea);
        }

        // 输入: [1]
        //输出: 1
        @Test
        public void test1() {
            int[] heights = {1};
            Solution2 solution = new Solution2();
            int maxArea = solution.largestRectangleArea(heights);
            System.out.println(maxArea);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

