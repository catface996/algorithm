package com.algorithm.leetcode.排序.剑指Offer45把数组排成最小的数;

//输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
//
//
//
// 示例 1:
//
// 输入: [10,2]
//输出: "102"
//
// 示例 2:
//
// 输入: [3,30,34,5,9]
//输出: "3033459"
//
//
//
// 提示:
//
//
// 0 < nums.length <= 100
//
//
// 说明:
//
//
// 输出结果可能非常大，所以你需要返回一个字符串而不是整数
// 拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0
//
// Related Topics 排序
// 👍 192 👎 0

import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution2 {

    public String minNumber(int[] nums) {
        String[] strArr = sortNumByStr(nums);
        StringBuilder sb = new StringBuilder();
        for (String str : strArr) {
            sb.append(str);
        }
        return sb.toString();
    }

    public void quick1(String[] numStrArr, int l, int r) {
        if (l >= r) { return; }
        int i = l - 1, j = r + 1;
        String x = numStrArr[(l + r) >> 1];
        while (i < j) {
            do {
                i++;
            } while ((numStrArr[i] + x).compareTo(x + numStrArr[i]) < 0);
            do {
                j--;
            } while ((numStrArr[j] + x).compareTo(x + numStrArr[j]) > 0);
            if (i < j) {
                String temp = numStrArr[i];
                numStrArr[i] = numStrArr[j];
                numStrArr[j] = temp;
            }
        }
        // i 和 j 均可
        quick1(numStrArr, l, j);
        quick1(numStrArr, j + 1, r);
    }

    void quick3(String[] strs, int l, int r) {
        if (l >= r) { return; }
        int i = l, j = r;
        String tmp = strs[l];
        while (i < j) {
            while ((strs[j] + strs[l]).compareTo(strs[l] + strs[j]) >= 0 && i < j) { j--; }
            while ((strs[i] + strs[l]).compareTo(strs[l] + strs[i]) <= 0 && i < j) { i++; }
            tmp = strs[i];
            strs[i] = strs[j];
            strs[j] = tmp;
        }
        strs[i] = strs[l];
        strs[l] = tmp;
        quick3(strs, l, i - 1);
        quick3(strs, i + 1, r);
    }

    public String[] sortNumByStr(int[] nums) {
        String[] strArr = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strArr[i] = String.valueOf(nums[i]);
        }
        quick1(strArr, 0, strArr.length - 1);
        return strArr;
    }

    public static class Test1 {
        //输入: [10,2]
        //输出: "102"
        public static void main(String[] args) {
            int[] nums = new int[] {10, 2};
            Solution2 solution = new Solution2();
            String minValueStr = solution.minNumber(nums);
            System.out.println(minValueStr);
        }
    }

    // 输入: [3,30,34,5,9]
    //输出: "3033459"
    public static class Test2 {
        public static void main(String[] args) {
            int[] nums = new int[] {3, 30, 34, 5, 9};
            Solution2 solution = new Solution2();
            String minValueStr = solution.minNumber(nums);
            System.out.println(minValueStr);
        }
    }

    //测试用例:[1,2,3,4,5,6,7,8,9,0]
    //期望结果:"0123456789"
    public static class Test3 {
        public static void main(String[] args) {
            int[] nums = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
            Solution2 solution = new Solution2();
            String[] strArr1 = new String[nums.length];
            for (int i = 0; i < nums.length; i++) {
                strArr1[i] = String.valueOf(nums[i]);
            }
            String[] strArr2 = Arrays.copyOf(strArr1, strArr1.length);
            String[] strArr3 = Arrays.copyOf(strArr1, strArr1.length);
            solution.quick1(strArr1, 0, strArr1.length - 1);
            solution.quick3(strArr3, 0, strArr3.length - 1);
            System.out.println("");
        }
    }

    //测试用例:[1,2,3,4,5,6,7,8,9,0]
    //期望结果:"0123456789"
    public static class Test4 {
        public static void main(String[] args) {
            int[] nums = new int[] {1, 1, 1};
            Solution2 solution = new Solution2();
            String[] strArr1 = new String[nums.length];
            for (int i = 0; i < nums.length; i++) {
                strArr1[i] = String.valueOf(nums[i]);
            }
            String[] strArr2 = Arrays.copyOf(strArr1, strArr1.length);
            String[] strArr3 = Arrays.copyOf(strArr1, strArr1.length);
            solution.quick1(strArr1, 0, strArr1.length - 1);//
            solution.quick3(strArr3, 0, strArr3.length - 1); //
            System.out.println("");
        }
    }

    //测试用例:[2,1]
    //期望结果:"12"
    public static class Test5 {
        public static void main(String[] args) {
            int[] nums = new int[] {2, 1};
            Solution2 solution = new Solution2();
            String[] strArr1 = new String[nums.length];
            for (int i = 0; i < nums.length; i++) {
                strArr1[i] = String.valueOf(nums[i]);
            }
            String[] strArr2 = Arrays.copyOf(strArr1, strArr1.length);
            String[] strArr3 = Arrays.copyOf(strArr1, strArr1.length);
            solution.quick1(strArr1, 0, strArr1.length - 1);
            solution.quick3(strArr3, 0, strArr3.length - 1);
            System.out.println("");
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)

