package com.algorithm.leetcode.排序.P4寻找两个正序数组的中位数;

import org.junit.Test;

public class Solution1 {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int total = m + n;
        int k = (total - 1) / 2;
        if (total % 2 == 0) {
            // 总共偶数个数字,中值是第,中值是 第 (m+n-1)/2 个和 第 (m+n)/2个
            return (findTopK(nums1, nums2, k) + findTopK(nums1, nums2, k + 1)) / 2.0;
        }
        // 总共奇数个数字,中值是第 (m + n) / 2 个
        return findTopK(nums1, nums2, k);
    }

    /**
     * 该方法在参数调用时,需要保证传入的数组的元素总个数大于等于k #归并
     *
     * @param nums1 数组1
     * @param nums2 数组2
     * @param k     第k个数
     * @return 结果
     */
    public int findTopK(int[] nums1, int[] nums2, int k) {
        if (nums1.length == 0 && nums2.length == 0) {
            return 0;
        }
        int index1 = 0;
        int index2 = 0;
        int count = -1;
        int ans = 0;
        while (count < k) {
            if (index1 >= nums1.length) {
                ans = nums2[index2];
                index2++;
                count++;
                continue;
            }
            if (index2 >= nums2.length) {
                ans = nums1[index1];
                index1++;
                count++;
                continue;
            }
            if (nums1[index1] <= nums2[index2]) {
                ans = nums1[index1];
                index1++;
                count++;
                continue;
            }
            ans = nums2[index2];
            index2++;
            count++;
        }
        return ans;
    }


    @Test
    public void test_left_nums() {
        int[] nums1 = new int[]{1, 3, 5, 8, 9, 10};
        int[] nums2 = new int[]{1, 3, 5, 8, 9, 10};
        Solution1 solution = new Solution1();
        for (int i = 0; i < nums1.length + nums2.length; i++) {
            double ans = solution.findTopK(nums1, nums2, i);
            System.out.println(ans);
        }
    }

    /**
     * 示例 1：
     * <p>
     * 输入：nums1 = [1,3], nums2 = [2]
     * <p>
     * 输出：2.00000
     * <p>
     * 解释：合并数组 = [1,2,3] ，中位数 2
     */
    @Test
    public void test_1() {
        int[] nums1 = new int[]{1, 3};
        int[] nums2 = new int[]{2};
        Solution1 solution = new Solution1();
        double ans = solution.findMedianSortedArrays(nums1, nums2);
        System.out.println(ans);
    }

    /**
     * 示例 2：
     * <p>
     * 输入：nums1 = [1,2], nums2 = [3,4]
     * <p>
     * 输出：2.50000
     * <p>
     * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
     */
    @Test
    public void test_2() {
        int[] nums1 = new int[]{1, 2};
        int[] nums2 = new int[]{3, 4};
        Solution1 solution = new Solution1();
        double ans = solution.findMedianSortedArrays(nums1, nums2);
        System.out.println(ans);
    }

    /**
     * 示例 3：
     * <p>
     * 输入：nums1 = [], nums2 = [3,4]
     * <p>
     * 输出：2.50000
     * <p>
     * 解释：合并数组 = [3,4] ，中位数 (3 + 4) / 2 = 3.5
     */
    @Test
    public void test_3() {
        int[] nums1 = new int[]{};
        int[] nums2 = new int[]{3, 4};
        Solution1 solution = new Solution1();
        double ans = solution.findMedianSortedArrays(nums1, nums2);
        System.out.println(ans);
    }

    /**
     * 示例 4：
     * <p>
     * 输入：nums1 = [1,2], nums2 = []
     * <p>
     * 输出：2.50000
     * <p>
     * 解释：合并数组 = [1,2] ，中位数 (1 + 2) / 2 = 1.5
     */
    @Test
    public void test_4() {
        int[] nums1 = new int[]{1, 2};
        int[] nums2 = new int[]{};
        Solution1 solution = new Solution1();
        double ans = solution.findMedianSortedArrays(nums1, nums2);
        System.out.println(ans);
    }

    /**
     * 示例 5：
     * <p>
     * 输入：nums1 = [], nums2 = []
     * <p>
     * 输出：2.50000
     * <p>
     * 解释：合并数组 = [] ，中位数 0
     */
    @Test
    public void test_5() {
        int[] nums1 = new int[]{};
        int[] nums2 = new int[]{};
        Solution1 solution = new Solution1();
        double ans = solution.findMedianSortedArrays(nums1, nums2);
        System.out.println(ans);
    }


}
