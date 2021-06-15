package com.algorithm.leetcode.双指针.P11盛水最多的容器;

//给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i,
//ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
//
// 说明：你不能倾斜容器。
//
//
//
// 示例 1：
//
//
//
//
//输入：[1,8,6,2,5,4,8,3,7]
//输出：49
//解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
//
// 示例 2：
//
//
//输入：height = [1,1]
//输出：1
//
//
// 示例 3：
//
//
//输入：height = [4,3,2,1,4]
//输出：16
//
//
// 示例 4：
//
//
//输入：height = [1,2,1]
//输出：2
//
//
//
//
// 提示：
//
//
// n = height.length
// 2 <= n <= 3 * 104
// 0 <= height[i] <= 3 * 104
//
// Related Topics 数组 双指针
// 👍 2534 👎 0

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
@Slf4j
public class Solution {

    // 60 / 60 个通过测试用例
    //状态：通过
    //执行用时: 5 ms
    //内存消耗: 51.7 MB

    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int ans = 0;
        while (left < right) {
            ans = Math.max(ans, Math.min(height[left], height[right]) * (right - left));
            if (height[left] >= height[right]) {
                right--;
            } else {
                left++;
            }
        }
        return ans;
    }

    public static class TestClass {

        // 示例 1：
        //输入：[1,8,6,2,5,4,8,3,7]
        //输出：49
        //解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
        //
        @Test
        public void test1() {
            int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
            Solution solution = new Solution();
            int maxArea = solution.maxArea(height);
            log.info("maxArea:{}", maxArea);
        }

        // 示例 2：
        //输入：height = [1,1]
        //输出：1
        @Test
        public void test2() {
            int[] height = {1, 1};
            Solution solution = new Solution();
            int maxArea = solution.maxArea(height);
            log.info("maxArea:{}", maxArea);
        }

        // 示例 3：
        //输入：height = [4,3,2,1,4]
        //输出：16
        @Test
        public void test3() {
            int[] height = {4, 3, 2, 1, 4};
            Solution solution = new Solution();
            int maxArea = solution.maxArea(height);
            log.info("maxArea:{}", maxArea);
        }

        // 示例 4：
        //输入：height = [1,2,1]
        //输出：2
        @Test
        public void test4() {
            int[] height = {1, 2, 1};
            Solution solution = new Solution();
            int maxArea = solution.maxArea(height);
            log.info("maxArea:{}", maxArea);
        }

    }

}
//leetcode submit region end(Prohibit modification and deletion)

