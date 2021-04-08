package leetcode.排序.P57插入区间;

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

//leetcode submit region begin(Prohibit modification and deletion)
public class Solution3 {

    /*
    11:44 下午	info
					解答成功:
					执行耗时:1 ms,击败了99.84% 的Java用户
					内存消耗:40.8 MB,击败了42.70% 的Java用户
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals == null || intervals.length <= 0) {
            return new int[][] {{newInterval[0], newInterval[1]}};
        }
        if (newInterval[1] < intervals[0][0]) {
            // 在最左侧插入
            int[][] ans = new int[intervals.length + 1][2];
            ans[0] = newInterval;
            System.arraycopy(intervals, 0, ans, 1, intervals.length);
            return ans;
        }
        if (newInterval[0] > intervals[intervals.length - 1][1]) {
            // 在最右侧插入
            int[][] ans = new int[intervals.length + 1][2];
            System.arraycopy(intervals, 0, ans, 0, intervals.length);
            ans[ans.length - 1] = newInterval;
            return ans;
        }

        // 在中间插入
        int leftEnd = 0;
        int i = 0;
        while (i < intervals.length) {
            if (intervals[i][1] < newInterval[0]) {
                i++;
            } else {
                leftEnd = i - 1;
                break;
            }
        }

        // 要插入的的在中间
        int[] currentMerge = newInterval[0] < intervals[i][0] ? newInterval : intervals[i];
        if (newInterval[0] > currentMerge[1]) {
            currentMerge = newInterval;
        } else {
            currentMerge[1] = Math.max(currentMerge[1], newInterval[1]);
        }
        int rightStart = 0;
        boolean mergeEnd = false;
        while (i < intervals.length) {
            if (mergeEnd) {
                i++;
                continue;
            }
            if (intervals[i][0] > currentMerge[1]) {
                mergeEnd = true;
                rightStart = i;
            } else {
                currentMerge[1] = Math.max(currentMerge[1], intervals[i][1]);
            }
            i++;
        }
        int ansLength = 0;
        if (leftEnd >= 0) {
            ansLength = leftEnd + 1 + 1;
        }
        if (!mergeEnd) {
            ansLength++;
        } else {
            ansLength += intervals.length - rightStart;
        }
        int[][] ans = new int[ansLength][2];
        if (leftEnd >= 0) {
            System.arraycopy(intervals, 0, ans, 0, leftEnd + 1);
        }
        int currentIndex = leftEnd + 1;
        ans[currentIndex] = currentMerge;
        if (mergeEnd) {
            currentIndex++;
            for (int j = rightStart; j < intervals.length; j++) {
                ans[currentIndex] = intervals[j];
                currentIndex++;
            }
        }
        return ans;
    }

    public static class Test1 {

        /*
        输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
        输出：[[1,2],[3,10],[12,16]]
        解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
         */
        public static void main(String[] args) {
            int[][] intervals = new int[][] {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
            Solution3 solution = new Solution3();
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
            Solution3 solution = new Solution3();
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
            Solution3 solution = new Solution3();
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
            Solution3 solution = new Solution3();
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
            Solution3 solution = new Solution3();
            int[][] ans = solution.insert(intervals, new int[] {2, 5});
            System.out.println(ans);
        }

    }

}
//leetcode submit region end(Prohibit modification and deletion)
