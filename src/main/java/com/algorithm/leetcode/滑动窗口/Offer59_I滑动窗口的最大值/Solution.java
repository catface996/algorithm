package com.algorithm.leetcode.滑动窗口.Offer59_I滑动窗口的最大值;

//给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
//
// 示例:
//
// 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
//输出: [3,3,5,5,6,7]
//解释:
//
//  滑动窗口的位置                最大值
//---------------               -----
//[1  3  -1] -3  5  3  6  7       3
// 1 [3  -1  -3] 5  3  6  7       3
// 1  3 [-1  -3  5] 3  6  7       5
// 1  3  -1 [-3  5  3] 6  7       5
// 1  3  -1  -3 [5  3  6] 7       6
// 1  3  -1  -3  5 [3  6  7]      7
//
//
//
// 提示：
//
// 你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。
//
// 注意：本题与主站 239 题相同：https://leetcode-cn.com/problems/sliding-window-maximum/
// Related Topics 队列 Sliding Window
// 👍 245 👎 0

import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    //TODO 需要优化
    // 9:53 下午	info
    //				解答成功:
    //				执行耗时:12 ms,击败了85.59% 的Java用户
    //				内存消耗:47.3 MB,击败了41.15% 的Java用户

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length < k || k <= 0) {
            return new int[0];
        }
        int[] ans = new int[nums.length - k + 1];
        LinkedList<Integer> maxValueIndexQ = new LinkedList<>();
        for (int right = 0; right < k - 1; right++) {
            while (!maxValueIndexQ.isEmpty() && nums[maxValueIndexQ.peekLast()] <= nums[right]) {
                maxValueIndexQ.pollLast();
            }
            maxValueIndexQ.addLast(right);
        }
        for (int right = k - 1, left = -1, ansIndex = 0; right < nums.length; right++, left++, ansIndex++) {
            while (!maxValueIndexQ.isEmpty() && nums[maxValueIndexQ.peekLast()] <= nums[right]) {
                maxValueIndexQ.pollLast();
            }
            maxValueIndexQ.addLast(right);
            if (left == maxValueIndexQ.peekFirst()) {
                maxValueIndexQ.pollFirst();
            }
            ans[ansIndex] = nums[maxValueIndexQ.peekFirst()];
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

