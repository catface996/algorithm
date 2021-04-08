package pratice.leetcode.排序.P57插入区间;

//给你一个 无重叠的 ，按照区间起始端点排序的区间列表。
//
// 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
//
//
//
// 示例 1：
//
//
//输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
//输出：[[1,5],[6,9]]
//
//
// 示例 2：
//
//
//输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
//输出：[[1,2],[3,10],[12,16]]
//解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
//
// 示例 3：
//
//
//输入：intervals = [], newInterval = [5,7]
//输出：[[5,7]]
//
//
// 示例 4：
//
//
//输入：intervals = [[1,5]], newInterval = [2,3]
//输出：[[1,5]]
//
//
// 示例 5：
//
//
//输入：intervals = [[1,5]], newInterval = [2,7]
//输出：[[1,7]]
//
//
//
//
// 提示：
//
//
// 0 <= intervals.length <= 104
// intervals[i].length == 2
// 0 <= intervals[i][0] <= intervals[i][1] <= 105
// intervals 根据 intervals[i][0] 按 升序 排列
// newInterval.length == 2
// 0 <= newInterval[0] <= newInterval[1] <= 105
//
// Related Topics 排序 数组
// 👍 397 👎 0

import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /*
    11:13 下午	info
				解答成功:
				执行耗时:2 ms,击败了71.35% 的Java用户
				内存消耗:40.7 MB,击败了73.11% 的Java用户
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> ans = new ArrayList<>();
        int start = newInterval[0];
        int end = newInterval[1];
        boolean mark1 = true;
        for (int[] interval : intervals) {
            if (interval[1] < start || !mark1) {
                ans.add(interval);
            } else if (interval[0] > end) {
                ans.add(new int[] {start, end});
                ans.add(interval);
                mark1 = false;
            } else {
                start = Math.min(start, interval[0]);
                end = Math.max(end, interval[1]);
            }
        }
        if (mark1) {
            ans.add(new int[] {start, end});
        }
        int[][] ans1 = new int[ans.size()][2];
        for (int i = 0; i < ans1.length; i++) {
            ans1[i] = ans.get(i);
        }
        return ans1;
    }

    public static class Test1 {

        /*
        输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
        输出：[[1,2],[3,10],[12,16]]
        解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
         */
        public static void main(String[] args) {
            int[][] intervals = new int[][] {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
            Solution solution = new Solution();
            int[][] ans = solution.insert(intervals, new int[] {4, 8});
            System.out.println(ans);
        }

    }

    public static class Test2 {

        /*
        输入：intervals = [], newInterval = [5,7]
        输出：[[5,7]]
         */
        public static void main(String[] args) {
            int[][] intervals = new int[][] {};
            Solution solution = new Solution();
            int[][] ans = solution.insert(intervals, new int[] {5, 7});
            System.out.println(ans);
        }

    }

    public static class Test3 {

        /*
        输入：intervals = [[1,5]], newInterval = [2,3]
        输出：[[1,5]]
         */
        public static void main(String[] args) {
            int[][] intervals = new int[][] {{1, 5}};
            Solution solution = new Solution();
            int[][] ans = solution.insert(intervals, new int[] {2, 3});
            System.out.println(ans);
        }

    }

    public static class Test4 {

        /*
        输入：intervals = [[1,5]], newInterval = [2,7]
        输出：[[1,7]]
         */
        public static void main(String[] args) {
            int[][] intervals = new int[][] {{1, 5}};
            Solution solution = new Solution();
            int[][] ans = solution.insert(intervals, new int[] {2, 7});
            System.out.println(ans);
        }

    }

    public static class Test5 {

        /*
        测试用例:[[1,3],[6,9]]  [2,5]
        期望结果:[[1,5],[6,9]]
         */
        public static void main(String[] args) {
            int[][] intervals = new int[][] {{1, 3}, {6, 9}};
            Solution solution = new Solution();
            int[][] ans = solution.insert(intervals, new int[] {2, 5});
            System.out.println(ans);
        }

    }

}
//leetcode submit region end(Prohibit modification and deletion)
