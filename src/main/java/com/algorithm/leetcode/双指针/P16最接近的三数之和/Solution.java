package com.algorithm.leetcode.双指针.P16最接近的三数之和;

import java.util.Arrays;
import org.junit.Test;

/**
 * @author catface
 * @since 2022/9/13
 */
public class Solution {

  public int threeSumClosest(int[] nums, int target) {
    // 排序一下
    Arrays.sort(nums);
    // 转换成 a + b + c - target = 0
    int left, right, mostSum, result = Integer.MAX_VALUE, minRange = Integer.MAX_VALUE, tmpMinRange;
    for (int i = 1; i < nums.length - 1; i++) {

      left = i - 1;
      right = i + 1;

      while (left >= 0 && right < nums.length) {

        mostSum = nums[left] + nums[i] + nums[right];
        if (mostSum - target == 0) {
          return mostSum;
        }

        // 是否是个更好的选择
        tmpMinRange = Math.abs(mostSum - target);
        if (tmpMinRange < minRange) {
          result = mostSum;
          minRange = tmpMinRange;
        }

        // 如果差值是负数,需要增大;差值是正数,需要减小;
        if (mostSum > target) {
          left--;
        } else {
          right++;
        }
      }
    }
    return result;
  }

  public static class TestClass {

    /**
     * 示例 1：
     * <p>
     * 输入：nums = [-1,2,1,-4], target = 1
     * 输出：2
     * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
     */
    @Test
    public void test_1() {
      int[] nums = {-1, 2, 1, -4};
      int target = 1;
      int ans = 2;
      Solution solution = new Solution();
      int result = solution.threeSumClosest(nums, target);
      assert result == ans;
    }

    /**
     * 示例 2：
     * <p>
     * 输入：nums = [0,0,0], target = 1
     * 输出：0
     */
    @Test
    public void test_2() {
      int[] nums = {0, 0, 0};
      int target = 1;
      int ans = 0;
      Solution solution = new Solution();
      int result = solution.threeSumClosest(nums, target);
      assert result == ans;
    }

    /**
     * 测试用例:[4,0,5,-5,3,3,0,-4,-5]
     * -2
     * 测试结果:-4
     * 期望结果:-2
     */
    @Test
    public void test_3() {
      int[] nums = {4, 0, 5, -5, 3, 3, 0, -4, -5};
      int target = -2;
      int ans = -2;
      Solution solution = new Solution();
      int result = solution.threeSumClosest(nums, target);
      assert result == ans;
    }

  }
}
