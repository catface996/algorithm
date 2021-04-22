package leetcode.滑动窗口.P239滑动窗口最大值;
//给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位
//。
//
// 返回滑动窗口中的最大值。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
//输出：[3,3,5,5,6,7]
//解释：
//滑动窗口的位置                最大值
//---------------               -----
//[1  3  -1] -3  5  3  6  7       3
// 1 [3  -1  -3] 5  3  6  7       3
// 1  3 [-1  -3  5] 3  6  7       5
// 1  3  -1 [-3  5  3] 6  7       5
// 1  3  -1  -3 [5  3  6] 7       6
// 1  3  -1  -3  5 [3  6  7]      7
//
//
// 示例 2：
//
//
//输入：nums = [1], k = 1
//输出：[1]
//
//
// 示例 3：
//
//
//输入：nums = [1,-1], k = 1
//输出：[1,-1]
//
//
// 示例 4：
//
//
//输入：nums = [9,11], k = 2
//输出：[11]
//
//
// 示例 5：
//
//
//输入：nums = [4,-2], k = 2
//输出：[4]
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 105
// -104 <= nums[i] <= 104
// 1 <= k <= nums.length
//
// Related Topics 堆 Sliding Window
// 👍 963 👎 0

import java.util.LinkedList;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 9:58 下午	info
    //				解答成功:
    //				执行耗时:36 ms,击败了72.16% 的Java用户
    //				内存消耗:50.5 MB,击败了70.32% 的Java用户

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
