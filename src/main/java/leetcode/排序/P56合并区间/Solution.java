package leetcode.排序.P56合并区间;

//以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返
//回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
//
//
//
// 示例 1：
//
//
//输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
//输出：[[1,6],[8,10],[15,18]]
//解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
//
//
// 示例 2：
//
//
//输入：intervals = [[1,4],[4,5]]
//输出：[[1,5]]
//解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
//
//
//
// 提示：
//
//
// 1 <= intervals.length <= 104
// intervals[i].length == 2
// 0 <= starti <= endi <= 104
//
// Related Topics 排序 数组
// 👍 882 👎 0

import java.util.ArrayList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /*
        4:41 下午	info
				解答成功:
				执行耗时:9 ms,击败了34.27% 的Java用户
				内存消耗:40.6 MB,击败了98.45% 的Java用户
     */
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length <= 0) {
            return new int[0][2];
        }
        int[][] startSorted = mergeSortByEndStart(intervals,0,intervals.length-1);
        List<int[]> merged = new ArrayList<>();
        int[] currentMerge = startSorted[0];
        merged.add(currentMerge);
        for (int i = 1; i < startSorted.length; i++) {
            if (startSorted[i][0] > currentMerge[1]) {
                currentMerge = startSorted[i];
                merged.add(currentMerge);
            } else {
                currentMerge[1] = Math.max(currentMerge[1], startSorted[i][1]);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    public int[][] mergeSortByEndStart(int[][] intervals, int start, int end) {
        if (start == end) {
            return new int[][] {{intervals[start][0], intervals[start][1]}};
        }
        int middle = start + ((end - start) >> 1);
        int[][] leftArr = mergeSortByEndStart(intervals, start, middle);
        int[][] rightArr = mergeSortByEndStart(intervals, middle + 1, end);
        return mergeForSortByEndStart(leftArr, rightArr);
    }

    public int[][] mergeForSortByEndStart(int[][] leftArr, int[][] rightArr) {
        int leftPoint = 0;
        int rightPoint = 0;
        int currentPoint = 0;
        int[][] temp = new int[leftArr.length + rightArr.length][2];
        while (leftPoint < leftArr.length && rightPoint < rightArr.length) {

            while (leftPoint < leftArr.length && leftArr[leftPoint][0] <= rightArr[rightPoint][0]) {
                temp[currentPoint] = leftArr[leftPoint];
                leftPoint++;
                currentPoint++;
            }

            while (rightPoint < rightArr.length && leftPoint < leftArr.length
                && rightArr[rightPoint][0] < leftArr[leftPoint][0]) {
                temp[currentPoint] = rightArr[rightPoint];
                rightPoint++;
                currentPoint++;
            }

            if (leftPoint >= leftArr.length) {
                while (rightPoint < rightArr.length) {
                    temp[currentPoint] = rightArr[rightPoint];
                    rightPoint++;
                    currentPoint++;
                }
            }
            if (rightPoint >= rightArr.length) {
                while (leftPoint < leftArr.length) {
                    temp[currentPoint] = leftArr[leftPoint];
                    leftPoint++;
                    currentPoint++;
                }
            }
        }
        return temp;
    }

    public static class Test1 {
        //{1, 3}, {4, 6}, {3, 6}, {5, 6}, {15, 18}, {6, 11}
        // 1,11  15,18
        public static void main(String[] args) {
            int[][] intervals = new int[][] {{1, 3}, {4, 6}, {3, 6}, {5, 6}, {15, 18}, {6, 11}};
            Solution solution = new Solution();
            int[][] temp = solution.merge(intervals);
            System.out.println(temp);
        }
    }

    public static class Test2 {
        // {1, 4}, {2, 3}
        // 1,4
        public static void main(String[] args) {
            int[][] intervals = new int[][] {{1, 4}, {2, 3}};
            Solution solution = new Solution();
            int[][] temp = solution.merge(intervals);
            System.out.println(temp);
        }
    }

    public static class Test3 {
        //{1, 4}, {0, 5}
        //0,5
        public static void main(String[] args) {
            int[][] intervals = new int[][] {{1, 4}, {0, 5}};
            Solution solution = new Solution();
            int[][] temp = solution.merge(intervals);
            System.out.println(temp);
        }
    }

    public static class Test4 {
        //测试用例:[[2,3],[4,5],[6,7],[8,9],[1,10]]
        //期望结果:[[1,10]]
        public static void main(String[] args) {
            int[][] intervals = new int[][] {{2, 3}, {4, 5}, {6, 7}, {8, 9}, {1, 10}};
            Solution solution = new Solution();
            int[][] temp = solution.merge(intervals);
            System.out.println(temp);
        }
    }

    public static class Test5 {
        //测试用例:[[1,3],[0,2],[2,3],[4,6],[4,5],[5,5],[0,2],[3,3]]
        //期望结果:[[0,3],[4,6]]
        public static void main(String[] args) {
            int[][] intervals = new int[][] {{1, 3}, {0, 2}, {2, 3}, {4, 6}, {4, 5}, {5, 5}, {0, 2}, {3, 3}};
            Solution solution = new Solution();
            int[][] temp = solution.merge(intervals);
            System.out.println(temp);
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)

