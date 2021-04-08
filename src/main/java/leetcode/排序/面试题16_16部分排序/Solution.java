package leetcode.排序.面试题16_16部分排序;

//给定一个整数数组，编写一个函数，找出索引m和n，只要将索引区间[m,n]的元素排好序，整个数组就是有序的。注意：n-m尽量最小，也就是说，找出符合条件的最短
//序列。函数返回值为[m,n]，若不存在这样的m和n（例如整个数组是有序的），请返回[-1,-1]。
// 示例：
// 输入： [1,2,4,7,10,11,7,12,6,7,16,18,19]
//输出： [3,9]
//
// 提示：
//
// 0 <= len(array) <= 1000000
//
// Related Topics 排序 数组
// 👍 65 👎 0

import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /*
    9:54 下午	info
				解答成功:
				执行耗时:21 ms,击败了23.00% 的Java用户
				内存消耗:62.9 MB,击败了10.26% 的Java用户
     */
    // 暴力解题,先排好序,建立新数组,比对原数组和新数组中的第一个不同位置和最后一个不同位置
    public int[] subSort(int[] array) {
        int[] sortedArr = Arrays.copyOf(array, array.length);
        Arrays.sort(sortedArr);
        int start = 0;
        while (start < array.length) {
            if (array[start] == sortedArr[start]) {
                start++;
                continue;
            }
            break;
        }
        if (start == array.length) {
            return new int[] {-1, -1};
        }
        int end = array.length - 1;
        while (end > 0) {
            if (array[end] == sortedArr[end]) {
                end--;
                continue;
            }
            break;
        }
        return new int[] {start, end};
    }

    public static class Test1 {
        // 输入： [1,2,4,7,10,11,7,12,6,7,16,18,19]
        //输出： [3,9]
        public static void main(String[] args) {
            int[] arr = new int[] {1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19};
            Solution solution = new Solution();
            int[] ans = solution.subSort(arr);
            System.out.println(ans);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

