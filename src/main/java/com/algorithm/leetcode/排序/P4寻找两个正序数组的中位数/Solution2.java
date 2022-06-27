package com.algorithm.leetcode.排序.P4寻找两个正序数组的中位数;

import org.junit.Test;

public class Solution2 {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length == 0 && nums2.length == 0) {
            return 0.0;
        }
        int k = (nums1.length + nums2.length + 1) / 2;
        double ans = findTheK(nums1, nums2, k) * 1.0;
        if ((nums1.length + nums2.length) % 2 == 1) {
            return ans;
        } else {
            ans += findTheK(nums1, nums2, k + 1) * 1.0;
        }
        return ans / 2;
    }

    /**
     * 获取数组1和数组2中第k大的值
     *
     * @param nums1 数组1
     * @param nums2 数组2
     * @param k     第k大
     * @return 第k大的值
     */
    public Integer findTheK(int[] nums1, int[] nums2, int k) {
        int total = nums1.length + nums2.length;
        if (total < k) {
            return null;
        }
        return findTheK(nums1, nums2, 0, nums1.length - 1, 0, nums2.length - 1, k);
    }

    /**
     * 查找数组1和数组2中的第k大的值  #二分 #迭代
     *
     * @param nums1  数组1
     * @param nums2  数组2
     * @param left1  数组1的开始下标
     * @param right1 数组1的结束下标
     * @param left2  数组2的开始下下标
     * @param right2 数组2的结束下标
     * @param k      下标从0开始
     * @return 第k大的值
     */
    public int findTheK(int[] nums1, int[] nums2, int left1, int right1, int left2, int right2, int k) {
        int l1 = left1;
        int l2 = left2;
        int curK = k;
        int mk, m1, m2;
        while (true) {
            if (l1 > right1) {
                return nums2[l2 + curK - 1];
            }
            if (l2 > right2) {
                return nums1[l1 + curK - 1];
            }
            if (curK == 1) {
                return Math.min(nums1[l1], nums2[l2]);
            }
            // 计算 k/2 - 1 个
            mk = curK / 2;
            m1 = Math.min(l1 + mk - 1, nums1.length - 1);
            m2 = Math.min(l2 + mk - 1, nums2.length - 1);
            if (nums1[m1] <= nums2[m2]) {
                curK = curK - (m1 - l1 + 1);
                l1 = m1 + 1;
                //return findTheK(nums1, nums2, m1 + 1, right1, left2, right2, k - (m1 - left1 + 1));
            } else {
                curK = curK - (m2 - l2 + 1);
                l2 = m2 + 1;
                //return findTheK(nums1, nums2, left1, right1, m2 + 1, right2, k - (m2 - left2 + 1));
            }
        }

    }


    @Test
    public void test_findK() {
        int[] nums1 = new int[]{2, 2, 4, 4};
        int[] nums2 = new int[]{2, 2, 4, 4};
        Solution2 solution = new Solution2();
        Integer ans;
        ans = solution.findTheK(nums1, nums2, 1);
        System.out.println(ans);
        ans = solution.findTheK(nums1, nums2, 2);
        System.out.println(ans);
        ans = solution.findTheK(nums1, nums2, 3);
        System.out.println(ans);
        ans = solution.findTheK(nums1, nums2, 4);
        System.out.println(ans);
        ans = solution.findTheK(nums1, nums2, 5);
        System.out.println(ans);
        ans = solution.findTheK(nums1, nums2, 6);
        System.out.println(ans);
        ans = solution.findTheK(nums1, nums2, 7);
        System.out.println(ans);
        ans = solution.findTheK(nums1, nums2, 8);
        System.out.println(ans);
        ans = solution.findTheK(nums1, nums2, 9);
        System.out.println(ans);
    }

    @Test
    public void test_findTheK() {
        int[] nums1 = new int[]{1, 3};
        int[] nums2 = new int[]{2};
        Solution2 solution = new Solution2();
        Integer ans;
        ans = solution.findTheK(nums1, nums2, 1);
        System.out.println(ans);
        ans = solution.findTheK(nums1, nums2, 2);
        System.out.println(ans);
        ans = solution.findTheK(nums1, nums2, 3);
        System.out.println(ans);
        ans = solution.findTheK(nums1, nums2, 4);
        System.out.println(ans);
    }

    @Test
    public void test_findTheK4() {
        int[] nums1 = new int[]{1};
        int[] nums2 = new int[]{2, 3, 4, 5, 6};
        Solution2 solution = new Solution2();
        Integer ans;
        ans = solution.findTheK(nums1, nums2, 3);
        System.out.println(ans);
        ans = solution.findTheK(nums1, nums2, 4);
        System.out.println(ans);
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
        Solution2 solution = new Solution2();
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
        Solution2 solution = new Solution2();
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
        Solution2 solution = new Solution2();
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
        Solution2 solution = new Solution2();
        double ans = solution.findMedianSortedArrays(nums1, nums2);
        System.out.println(ans);
    }

    /**
     * 示例 5：
     * <p>
     * 输入：nums1 = [], nums2 = []
     * <p>
     * 输出：0.0
     * <p>
     * 解释：合并数组 = [] ，中位数 0
     */
    @Test
    public void test_5() {
        int[] nums1 = new int[]{};
        int[] nums2 = new int[]{};
        Solution2 solution = new Solution2();
        double ans = solution.findMedianSortedArrays(nums1, nums2);
        System.out.println(ans);
    }

    /**
     * 解答失败:
     * 测试用例:[2,2,4,4]
     * [2,2,4,4]
     * 测试结果:1.00000
     * 期望结果:3.0
     * stdout:
     */
    @Test
    public void test_6() {
        int[] nums1 = new int[]{2, 2, 4, 4};
        int[] nums2 = new int[]{2, 2, 4, 4};
        Solution2 solution = new Solution2();
        double ans = solution.findMedianSortedArrays(nums1, nums2);
        System.out.println(ans);
    }

    /**
     * 解答失败:
     * 测试用例:[1]
     * [2,3,4,5,6]
     * 测试结果:3.00000
     * 期望结果:3.5
     */
    @Test
    public void test_7() {
        int[] nums1 = new int[]{1};
        int[] nums2 = new int[]{2, 3, 4, 5, 6};
        Solution2 solution = new Solution2();
        double ans = solution.findMedianSortedArrays(nums1, nums2);
        System.out.println(ans);
    }


}
