package leetcode.动态规划.P300最长递增子序列;

//给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
//
// 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序
//列。
//
//
// 示例 1：
//
//
//输入：nums = [10,9,2,5,3,7,101,18]
//输出：4
//解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
//
//
// 示例 2：
//
//
//输入：nums = [0,1,0,3,2,3]
//输出：4
//
//
// 示例 3：
//
//
//输入：nums = [7,7,7,7,7,7,7]
//输出：1
//
//
//
//
// 提示：
//
//
// 1 <= nums.length <= 2500
// -104 <= curValue <= 104
//
//
//
//
// 进阶：
//
//
// 你可以设计时间复杂度为 O(n2) 的解决方案吗？
// 你能将算法的时间复杂度降低到 O(n log(n)) 吗?
//
// Related Topics 二分查找 动态规划
// 👍 1515 👎 0

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import util.ArrayUtil;

//leetcode submit region begin(Prohibit modification and deletion)
@Slf4j
class Solution2 {

    public int lengthOfLIS(int[] nums) {
        int len = 1, n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] d = new int[n + 1];
        d[len] = nums[0];
        for (int i = 1; i < n; ++i) {
            int curValue = nums[i];
            if (curValue > d[len]) {
                d[++len] = curValue;
            } else {
                int l = 1, r = len, pos = 0;
                // 如果找不到说明所有的数都比 curValue 大，此时要更新 d[1]，所以这里将 pos 设为 0
                while (l <= r) {
                    int mid = (l + r) >> 1;
                    if (d[mid] < curValue) {
                        pos = mid;
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                d[pos + 1] = curValue;
            }
        }
        return len;
    }

    public static class TestClass {
        //输入：nums = [10,9,2,5,3,7,101,18]
        //输出：4
        @Test
        public void test1() {
            int[] nums = new int[] {10, 9, 2, 5, 3, 7, 101, 18};
            Solution2 solution = new Solution2();
            int maxLength = solution.lengthOfLIS(nums);
            System.out.println("最长递增子序列:" + maxLength);
        }

        //输入：nums = [7,7,7,7,7,7,7]
        //输出：1
        @Test
        public void test2() {
            int[] nums = new int[] {7, 7, 7, 7, 7, 7, 7};
            Solution2 solution = new Solution2();
            int maxLength = solution.lengthOfLIS(nums);
            System.out.println("最长递增子序列:" + maxLength);
        }

        //输入：nums = [0,1,0,3,2,3]
        //输出：4
        @Test
        public void test3() {
            int[] nums = new int[] {0, 1, 0, 3, 2, 3};
            Solution2 solution = new Solution2();
            int maxLength = solution.lengthOfLIS(nums);
            System.out.println("最长递增子序列:" + maxLength);
        }

        @Test
        public void test4() {
            int[] nums = ArrayUtil.randomIntArray(20, 1, 100);
            Solution2 solution = new Solution2();
            int maxLength = solution.lengthOfLIS(nums);
            System.out.println("最长递增子序列:" + maxLength);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

