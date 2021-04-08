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
class Solution3 {

    public int[] smallestK(int[] arr, int k) {
        quickSort(0, arr.length - 1, k, arr);
        return Arrays.copyOf(arr, k);
    }

    private void quickSort(int l, int r, int k, int[] a) {
        if (l >= r) {
            return;
        }
        int p = a[(l + r) >> 1];
        int polite = partition(a, l, r, p);
        int cnt = polite - l + 1;
        if (cnt == k) {
            return;
        }
        if (cnt > k) {
            // 分区后,左侧区间的数量大于K个,在左侧区间继续分区
            quickSort(l, polite, k, a);
        } else {
            // 分区后,左侧区间数量小于K个,在右侧区间分区,选取 k-cnt 个最小值即可
            quickSort(polite + 1, r, k - cnt, a);
        }
    }

    /*
     分区是,不能使用荷兰国旗的解题分区算法
     解决荷兰国旗问题的more值,只有在arr[L]>p时,才会--,
     对数组[1,0,3,3,3,7] start = 0,end = 5;取中值作为标尺进行分区时,与原数组一致,
     more不会--,即,不会收缩右侧区间的起始下标,进入死循环
     */
    private int partition(int[] arr, int L, int R, int p) {
        int less = L - 1;
        int more = R + 1;
        while (L < more) {
            if (arr[L] < p) {
                swap(arr, ++less, L++);
            } else if (arr[L] > p) {
                swap(arr, --more, L);
            } else {
                L++;
            }
        }
        return more;
    }

    private void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static class Test1 {

        public static void main(String[] args) {
            int[] arr = new int[] {1, 3, 6, 9, 7, 3, 0, 12, 56, 3};
            Solution3 solution = new Solution3();
            int[] ans = solution.smallestK(arr, 3);
            System.out.println(ans);
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)

