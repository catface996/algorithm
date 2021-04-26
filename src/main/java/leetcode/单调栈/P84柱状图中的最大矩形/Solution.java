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

import java.util.Stack;

import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length <= 0) {
            return 0;
        }
        int maxArea = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
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

    public static class TestClass {
        // 输入: [2,1,5,6,2,3]
        //输出: 10
        @Test
        public void test() {
            int[] heights = {2, 1, 5, 6, 2, 3};
            Solution solution = new Solution();
            int maxArea = solution.largestRectangleArea(heights);
            System.out.println(maxArea);
        }

        // 输入: [1]
        //输出: 1
        @Test
        public void test1() {
            int[] heights = {1};
            Solution solution = new Solution();
            int maxArea = solution.largestRectangleArea(heights);
            System.out.println(maxArea);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

