package leetcode.剑指.剑指Offer21调整数组顺序使奇数位于偶数前面;

//输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
//
//
//
// 示例：
//
//
//输入：nums = [1,2,3,4]
//输出：[1,3,2,4]
//注：[3,1,2,4] 也是正确的答案之一。
//
//
//
// 提示：
//
//
// 0 <= nums.length <= 50000
// 1 <= nums[i] <= 10000
//
// 👍 123 👎 0

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import util.ArrayUtil;

//leetcode submit region begin(Prohibit modification and deletion)
@Slf4j
public class Solution {

    public int[] exchange(int[] nums) {
        if (nums == null || nums.length < 1) {
            return nums;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {

            while (left < right && nums[left] % 2 == 1) {
                left++;
            }
            while (left < right && nums[right] % 2 == 0) {
                right--;
            }
            if (left < right) {
                swap(nums, left, right);
            }

        }
        return nums;
    }

    private void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static class TestClass {

        // 示例：
        //
        //
        //输入：nums = [1,2,3,4]
        //输出：[1,3,2,4]
        //注：[3,1,2,4] 也是正确的答案之一。
        @Test
        public void test() {
            int[] nums = {1, 2, 3, 4};
            Solution solution = new Solution();
            int[] ans = solution.exchange(nums);
            log.info("ans:{}", ans);
        }

        @Test
        public void test2() {
            int[] nums = {1, 3, 5};
            Solution solution = new Solution();
            int[] ans = solution.exchange(nums);
            log.info("ans:{}", ans);
        }

        @Test
        public void testForce() {
            int[] nums = ArrayUtil.randomIntArray(10, 1, 20);
            Solution solution = new Solution();
            int[] ans = solution.exchange(nums);
            log.info("ans:{}", ans);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

