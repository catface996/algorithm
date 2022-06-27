package com.algorithm.leetcode.数组.P16最接近的三数之和;

//给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和
//。假定每组输入只存在唯一答案。
//
//
//
// 示例：
//
// 输入：nums = [-1,2,1,-4], target = 1
//输出：2
//解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
//
//
//
//
// 提示：
//
//
// 3 <= nums.length <= 10^3
// -10^3 <= nums[i] <= 10^3
// -10^4 <= target <= 10^4
//
// Related Topics 数组 双指针 排序 👍 903 👎 0

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int threeSumClosest(int[] nums, int target) {

        int minRange = Integer.MAX_VALUE;
        TreeMap<Integer, Integer> numCountMap = new TreeMap<>();
        int count;
        for (int num : nums) {
            count = numCountMap.getOrDefault(num, 0);
            count++;
            numCountMap.put(num, count);
        }
        List<Integer> arr = new ArrayList<>();
        numCountMap.forEach((num, c) -> {
            for (int i = 0; i < Math.min(3, c); i++) {
                arr.add(num);
            }
        });

        return 1;
    }

    private int find(List<Integer> arr, int start, int target) {
        return 0;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

