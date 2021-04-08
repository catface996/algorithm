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

import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /*
    使用小顶堆,连续取K个数
    4:26 下午	info
				解答成功:
				执行耗时:26 ms,击败了27.95% 的Java用户
				内存消耗:46.4 MB,击败了93.85% 的Java用户
     */
    public int[] smallestK(int[] arr, int k) {
        if (arr == null) {
            return new int[0];
        }
        if (arr.length <= k) {
            return arr;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int j : arr) {
            queue.add(j);
        }
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = queue.poll();
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

