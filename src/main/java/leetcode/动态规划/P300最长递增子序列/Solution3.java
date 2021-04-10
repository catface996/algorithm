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
// -104 <= nums[i] <= 104
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

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import util.ArrayUtil;

//leetcode submit region begin(Prohibit modification and deletion)
@Slf4j
class Solution3 {

    //11:06 下午	info
    //					运行失败:
    //					Time Limit Exceeded
    public int lengthOfLIS(int[] nums) {
        List<Integer> numList = new ArrayList<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            numList.add(nums[i]);
        }
        return process(numList);
    }

    private int process(List<Integer> nums) {
        if (nums.isEmpty()) {
            return 0;
        }
        int choose = process(removeLet(nums, nums.get(0)));
        nums.remove(0);
        int notChoose = process(nums);
        return Math.max(choose + 1, notChoose);
    }

    private List<Integer> removeLet(List<Integer> nums, int letValue) {
        List<Integer> leftNum = new ArrayList<>();
        for (Integer num : nums) {
            if (num <= letValue) {
                continue;
            }
            leftNum.add(num);
        }
        return leftNum;
    }

    public static class TestClass {
        //输入：nums = [10,9,2,5,3,7,101,18]
        //输出：4
        @Test
        public void test1() {
            int[] nums = new int[] {10, 9, 2, 5, 3, 7, 101, 18};
            Solution3 solution = new Solution3();
            int maxLength = solution.lengthOfLIS(nums);
            System.out.println("最长递增子序列:" + maxLength);
        }

        //输入：nums = [7,7,7,7,7,7,7]
        //输出：1
        @Test
        public void test2() {
            int[] nums = new int[] {7, 7, 7, 7, 7, 7, 7};
            Solution3 solution = new Solution3();
            int maxLength = solution.lengthOfLIS(nums);
            System.out.println("最长递增子序列:" + maxLength);
        }

        //输入：nums = [0,1,0,3,2,3]
        //输出：4
        @Test
        public void test3() {
            int[] nums = new int[] {0, 1, 0, 3, 2, 3};
            Solution3 solution = new Solution3();
            int maxLength = solution.lengthOfLIS(nums);
            System.out.println("最长递增子序列:" + maxLength);
        }

        @Test
        public void test4() {
            Solution2 solution2 = new Solution2();
            Solution3 solution3 = new Solution3();
            for (int size = 40; size < 60; size++) {
                for (int i = 0; i < 100; i++) {
                    int[] nums = ArrayUtil.randomIntArray(size, 20, 1000);
                    int ans2 = solution2.lengthOfLIS(nums);
                    int ans3 = solution3.lengthOfLIS(nums);
                    if (ans2 != ans3) {
                        System.out.println("Ops!!! Error.");
                    }
                }
            }
            System.out.println("success!");
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

