package leetcode.线段树.P218天际线问题;

//城市的天际线是从远处观看该城市中所有建筑物形成的轮廓的外部轮廓。给你所有建筑物的位置和高度，请返回由这些建筑物形成的 天际线 。
//
// 每个建筑物的几何信息由数组 buildings 表示，其中三元组 buildings[i] = [lefti, righti, heighti] 表示：
//
//
//
// lefti 是第 i 座建筑物左边缘的 x 坐标。
// righti 是第 i 座建筑物右边缘的 x 坐标。
// heighti 是第 i 座建筑物的高度。
//
//
// 天际线 应该表示为由 “关键点” 组成的列表，格式 [[x1,y1],[x2,y2],...] ，并按 x 坐标 进行 排序 。关键点是水平线段的左端点。
//列表中最后一个点是最右侧建筑物的终点，y 坐标始终为 0 ，仅用于标记天际线的终点。此外，任何两个相邻建筑物之间的地面都应被视为天际线轮廓的一部分。
//
// 注意：输出天际线中不得有连续的相同高度的水平线。例如 [...[2 3], [4 5], [7 5], [11 5], [12 7]...] 是不正确的答
//案；三条高度为 5 的线应该在最终输出中合并为一个：[...[2 3], [4 5], [12 7], ...]
//
//
//
// 示例 1：
//
//
//输入：buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
//输出：[[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
//解释：
//图 A 显示输入的所有建筑物的位置和高度，
//图 B 显示由这些建筑物形成的天际线。图 B 中的红点表示输出列表中的关键点。
//
// 示例 2：
//
//
//输入：buildings = [[0,2,3],[2,5,3]]
//输出：[[0,3],[5,0]]
//
//
//
//
// 提示：
//
//
// 1 <= buildings.length <= 104
// 0 <= lefti < righti <= 231 - 1
// 1 <= heighti <= 231 - 1
// buildings 按 lefti 非递减排序
//
// Related Topics 堆 树状数组 线段树 分治算法 Line Sweep
// 👍 405 👎 0

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
@Slf4j
public class Solution {

    // 下标越界,需要压缩下标

    public List<List<Integer>> getSkyline(int[][] buildings) {
        TreeSet<Integer> xSet = new TreeSet<>();
        int maxX = 0;
        for (int[] building : buildings) {
            maxX = Math.max(maxX, building[0]);
            maxX = Math.max(maxX, building[1]);
        }
        SegmentTree segmentTree = new SegmentTree(maxX + 1);
        for (int[] building : buildings) {
            int start = building[0];
            int end = building[1];
            xSet.add(building[0]);
            xSet.add(building[1]);
            segmentTree.update(start, end, building[2]);
        }
        List<List<Integer>> ans = new ArrayList<>();
        int preX = xSet.pollFirst();
        int preH = segmentTree.query(preX);
        int nextX;
        int nextH;
        while (!xSet.isEmpty()) {
            nextX = xSet.pollFirst();
            nextH = segmentTree.query(nextX);
            if (preH != nextH) {
                List<Integer> xAns = new ArrayList<>();
                xAns.add(preX);
                xAns.add(preH);
                ans.add(xAns);
                preX = nextX;
                preH = nextH;
            }
        }
        List<Integer> xAns = new ArrayList<>();
        xAns.add(preX);
        xAns.add(preH);
        ans.add(xAns);
        return ans;
    }

    public static class SegmentTree {
        private int maxN;
        private int[] sum;
        private int[] update;
        private boolean[] change;

        public SegmentTree(int maxN) {
            this.maxN = maxN + 1;
            this.sum = new int[this.maxN << 2];
            this.update = new int[this.maxN << 2];
            this.change = new boolean[this.maxN << 2];
        }

        public int query(int x) {
            return queryMax(x + 1, x + 1, 1, this.maxN - 1, 1);
        }

        public int queryMax(int L, int R, int l, int r, int rt) {
            if (L <= l && r <= R) {
                // 查询范围覆盖了扫描到的范围
                return sum[rt];
            }

            // 下发懒更新,左右两侧区间递归
            int mid = (l + r) / 2;
            int leftRt = rt << 1;
            int rightRt = leftRt | 1;
            int ln = mid - l + 1;
            int rn = r - mid;
            pushDown(leftRt, rightRt, ln, rn, rt);
            int max = 0;
            if (L <= mid) {
                max += queryMax(L, R, l, mid, leftRt);
            }
            if (R > mid) {
                max += queryMax(L, R, mid + 1, r, rightRt);
            }
            return max;
        }

        public void update(int L, int R, int value) {
            updateMax(L + 1, R, value, 1, this.maxN - 1, 1);
        }

        public void updateMax(int L, int R, int value, int l, int r, int rt) {
            if (L <= l && r <= R) {
                // 待更新区间完全覆盖了扫描到的区间,判断区间代表的最大值是否哟啊更新
                if (value > update[rt]) {
                    sum[rt] = value * (r - l + 1);
                    update[rt] = value;
                    change[rt] = true;
                }
                return;
            }

            // 下发懒更新,左右两侧区间递归
            int mid = (l + r) / 2;
            int leftRt = rt << 1;
            int rightRt = leftRt | 1;
            int ln = mid - l + 1;
            int rn = r - mid;
            pushDown(leftRt, rightRt, ln, rn, rt);

            if (L <= mid) {
                updateMax(L, R, value, l, mid, leftRt);
            }
            if (R > mid) {
                updateMax(L, R, value, mid + 1, r, rightRt);
            }

            sum[rt] = sum[leftRt] + sum[rightRt];
        }

        public void pushDown(int leftRt, int rightRt, int ln, int rn, int rt) {
            if (change[rt]) {
                if (update[rt] > update[leftRt]) {
                    sum[leftRt] = update[rt] * ln;
                    update[leftRt] = update[rt];
                    change[leftRt] = true;
                }
                if (update[rt] > update[rightRt]) {
                    sum[rightRt] = update[rt] * rn;
                    update[rightRt] = update[rt];
                    change[rightRt] = true;
                }
                change[rt] = false;
            }
        }

    }

    public static class TestClass {

        boolean compareResult(int[][] ans1, List<List<Integer>> ans2) {
            List<List<Integer>> ans = new ArrayList<>();
            for (int[] ints : ans1) {
                List<Integer> a1 = new ArrayList<>();
                a1.add(ints[0]);
                a1.add(ints[1]);
                ans.add(a1);
            }
            return compareResult(ans, ans2);
        }

        boolean compareResult(List<List<Integer>> ans1, List<List<Integer>> ans2) {
            if (ans1.size() != ans2.size()) {
                return false;
            }
            for (int i = 0; i < ans1.size(); i++) {
                if (!ans1.get(i).get(0).equals(ans2.get(i).get(0))) {
                    return false;
                }
                if (!ans1.get(i).get(1).equals(ans2.get(i).get(1))) {
                    return false;
                }
            }
            return true;
        }

        // 示例 1：
        //
        //
        //输入：buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
        //输出：[[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
        //解释：
        //图 A 显示输入的所有建筑物的位置和高度，
        //图 B 显示由这些建筑物形成的天际线。图 B 中的红点表示输出列表中的关键点。
        @Test
        public void test1() {
            int[][] buildings = {{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
            Solution solution = new Solution();
            List<List<Integer>> ans = solution.getSkyline(buildings);
            int[][] ans2 = {{2, 10}, {3, 15}, {7, 12}, {12, 0}, {15, 10}, {20, 8}, {24, 0}};
            boolean isCorrect = compareResult(ans2, ans);
            log.info("correct:{},ans:{}", isCorrect, ans);
        }

        // 示例 2：
        //
        //
        //输入：buildings = [[0,2,3],[2,5,3]]
        //输出：[[0,3],[5,0]]
        @Test
        public void test2() {
            int[][] buildings = {{0, 2, 3}, {2, 5, 3}};
            Solution solution = new Solution();
            List<List<Integer>> ans = solution.getSkyline(buildings);
            int[][] ans2 = {{0, 3}, {5, 0}};
            boolean isCorrect = compareResult(ans2, ans);
            log.info("correct:{},ans:{}", isCorrect, ans);
        }

        //8:55 下午	info
        //        解答失败:
        //        测试用例:{{3,7,8},{3,8,7},{3,9,6},{3,10,5},{3,11,4},{3,12,3},{3,13,2},{3,14,1}}
        //        测试结果:[[3,8],[7,1],[9,5],[10,1],[11,3],[12,1],[14,0]]
        //        期望结果:{{3,8},{7,7},{8,6},{9,5},{10,4},{11,3},{12,2},{13,1},{14,0}}
        @Test
        public void test3() {
            int[][] buildings = {{3, 7, 8}, {3, 8, 7}, {3, 9, 6}, {3, 10, 5}, {3, 11, 4}, {3, 12, 3}, {3, 13, 2},
                {3, 14, 1}};
            Solution solution = new Solution();
            List<List<Integer>> ans = solution.getSkyline(buildings);
            int[][] ans2 = {{3, 8}, {7, 7}, {8, 6}, {9, 5}, {10, 4}, {11, 3}, {12, 2}, {13, 1}, {14, 0}};
            boolean isCorrect = compareResult(ans2, ans);
            log.info("correct:{},ans:{}", isCorrect, ans);
        }

        @Test
        public void testSegmentTree() {
            SegmentTree segmentTree = new SegmentTree(15);

            segmentTree.update(3, 7, 8);
            int ans = segmentTree.query(7);
            System.out.println(ans);

            segmentTree.update(3, 8, 7);
            ans = segmentTree.query(7);
            System.out.println(ans);

            segmentTree.update(3, 9, 6);
            ans = segmentTree.query(7);
            System.out.println(ans);

            segmentTree.update(3, 10, 5);
            ans = segmentTree.query(7);
            System.out.println(ans);

            segmentTree.update(3, 11, 4);
            ans = segmentTree.query(7);
            System.out.println(ans);

            segmentTree.update(3, 12, 3);
            ans = segmentTree.query(7);
            System.out.println(ans);

            segmentTree.update(3, 13, 2);
            ans = segmentTree.query(7);
            System.out.println(ans);

            segmentTree.update(3, 14, 1);
            ans = segmentTree.query(7);
            System.out.println(ans);

        }

        @Test
        public void testSegmentTree2() {
            SegmentTree segmentTree = new SegmentTree(15);
            segmentTree.update(3, 7, 8);
            segmentTree.update(3, 8, 7);
            segmentTree.update(3, 9, 6);
            segmentTree.update(3, 10, 5);
            segmentTree.update(3, 11, 4);
            segmentTree.update(3, 12, 3);
            segmentTree.update(3, 13, 2);
            segmentTree.update(3, 14, 1);
            int ans = segmentTree.query(3);
            System.out.println(ans);

            ans = segmentTree.query(7);
            System.out.println(ans);

            ans = segmentTree.query(8);
            System.out.println(ans);

            ans = segmentTree.query(9);
            System.out.println(ans);

            ans = segmentTree.query(10);
            System.out.println(ans);

            ans = segmentTree.query(11);
            System.out.println(ans);

            ans = segmentTree.query(12);
            System.out.println(ans);

            ans = segmentTree.query(13);
            System.out.println(ans);

            ans = segmentTree.query(14);
            System.out.println(ans);
        }

    }

}
//leetcode submit region end(Prohibit modification and deletion)

