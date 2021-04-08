package leetcode.排序.面试题17_14最小K个数;

//设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。
//
// 示例：
//
// 输入： arr = [1,3,5,7,2,4,6,8], k = 4
//输出： [1,2,3,4]
//
//
// 提示：
//
//
// 0 <= len(arr) <= 100000
// 0 <= k <= min(100000, len(arr))
//
// Related Topics 堆 排序 分治算法
// 👍 53 👎 0

import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution4 {

    public int[] smallestK(int[] arr, int k) {
        qks(0, arr.length - 1, k, arr);
        return Arrays.copyOf(arr, k);
    }

    /*
    解题思路: 通过快速排序的分区,快速收敛
    5:31 下午	info
				解答成功:
				执行耗时:1 ms,击败了100.00% 的Java用户
				内存消耗:48.2 MB,击败了30.33% 的Java用户
     */
    void qks(int l, int r, int k, int[] a) {
        if (l >= r) {
            return;
        }
        int leftRangeStartIndex = partition(l, r, a);
        int cnt = leftRangeStartIndex - l + 1;
        if (cnt == k) {
            return;
        }
        if (cnt > k) {
            // 左侧分区中的数字个数大于K,说明刻个最小值一定在左侧区间
            qks(l, leftRangeStartIndex, k, a);
        } else {
            // 否则,在右侧区间中寻找k-cnt个最小值即可
            qks(leftRangeStartIndex + 1, r, k - cnt, a);
        }
    }

    /*
    分区,并且返回分区右侧区间的起始坐标
     */
    private int partition(int l, int r, int[] a) {
        int i = l - 1, j = r + 1, x = a[(l + r) >> 1];
        while (i < j) {
            do {
                i++;
            } while (a[i] < x);
            do {
                j--;
            } while (a[j] > x);
            if (i < j) {
                a[i] = a[i] ^ a[j];
                a[j] = a[i] ^ a[j];
                a[i] = a[i] ^ a[j];
            }
        }
        return j;
    }

    public static class Test1 {

        public static void main(String[] args) {
            int[] arr = new int[] {1, 3, 6, 9, 7, 3, 0, 12, 56, 3};
            Solution4 solution = new Solution4();
            int[] ans = solution.smallestK(arr, 4);
            System.out.println(ans);
        }
    }

    public static class Test2 {

        public static void main(String[] args) {
            int[] arr = new int[] {1, 1, 1, 1, 1};
            Solution4 solution = new Solution4();
            int[] ans = solution.smallestK(arr, 2);
            System.out.println(ans);
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)

