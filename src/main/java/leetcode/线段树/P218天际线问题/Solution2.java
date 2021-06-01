package leetcode.线段树.P218天际线问题;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/6/1 9:44 上午
 */
@Slf4j
public class Solution2 {

    // 10:32 上午	info
    //					解答成功:
    //					执行耗时:650 ms,击败了5.08% 的Java用户
    //					内存消耗:41.8 MB,击败了59.90% 的Java用户

    public List<List<Integer>> getSkyline(int[][] buildings) {
        TreeSet<Integer> xSet = new TreeSet<>();
        for (int[] building : buildings) {
            xSet.add(building[0]);
            xSet.add(building[1]);
        }
        Map<Integer, Integer> xMap = new HashMap<>();
        Map<Integer, Integer> xRevertMap = new HashMap<>();
        int maxX = 0;
        for (int x : xSet) {
            xMap.put(x, maxX);
            xRevertMap.put(maxX, x);
            maxX++;
        }
        SegmentTree segmentTree = new SegmentTree(maxX + 1);
        for (int[] building : buildings) {
            int start = building[0];
            int end = building[1];
            segmentTree.set(xMap.get(start), xMap.get(end), building[2]);
        }
        List<List<Integer>> ans = new ArrayList<>();
        int preX = xMap.get(xSet.pollFirst());
        int preH = segmentTree.query(preX);
        int nextX;
        int nextH;
        while (!xSet.isEmpty()) {
            nextX = xMap.get(xSet.pollFirst());
            nextH = segmentTree.query(nextX);
            if (preH != nextH) {
                List<Integer> xAns = new ArrayList<>();
                xAns.add(xRevertMap.get(preX));
                xAns.add(preH);
                ans.add(xAns);
                preX = nextX;
                preH = nextH;
            }
        }
        List<Integer> xAns = new ArrayList<>();
        xAns.add(xRevertMap.get(preX));
        xAns.add(preH);
        ans.add(xAns);
        return ans;
    }

    public static class SegmentTree {
        private int N;
        private int[] max;

        public SegmentTree(int N) {
            this.N = N + 1;
            max = new int[this.N << 2];
        }

        public void set(int L, int R, int value) {
            // 不更新右侧
            setMax(L + 1, R, value, 1, N - 1, 1);
        }

        public int query(int x) {
            return queryMax(x + 1, x + 1, 1, N - 1, 1);
        }

        public int queryMax(int L, int R, int l, int r, int rt) {
            if (L <= l && r <= R) {
                return max[rt];
            }
            int mid = l + ((r - l) >> 1);
            int lrt = rt << 1;
            int rrt = lrt | 1;
            pushDown(lrt, rrt, rt);
            int max = Integer.MIN_VALUE;
            if (L <= mid) {
                max = Math.max(max, queryMax(L, R, l, mid, lrt));
            }
            if (mid < R) {
                max = Math.max(max, queryMax(L, R, mid + 1, r, rrt));
            }
            return max;
        }

        public void setMax(int L, int R, int value, int l, int r, int rt) {
            if (L <= l && r <= R) {
                if (value > max[rt]) {
                    max[rt] = value;
                    return;
                }
                if (l == r) {
                    return;
                }
            }

            int mid = l + ((r - l) >> 1);
            int lrt = rt << 1;
            int rrt = lrt | 1;
            pushDown(lrt, rrt, rt);
            if (L <= mid) {
                setMax(L, R, value, l, mid, lrt);
            }
            if (mid < R) {
                setMax(L, R, value, mid + 1, r, rrt);
            }
        }

        private void pushDown(int lrt, int rrt, int rt) {
            // 检查是否要跟新左侧
            max[lrt] = Math.max(max[lrt], max[rt]);
            max[rrt] = Math.max(max[rrt], max[rt]);
        }

        public static class TestClass {

            @Test
            public void testSegmentTree2() {
                SegmentTree segmentTree = new SegmentTree(15);
                segmentTree.set(3, 7, 8);
                segmentTree.set(3, 8, 7);
                segmentTree.set(3, 9, 6);
                segmentTree.set(3, 10, 5);
                segmentTree.set(3, 11, 4);
                segmentTree.set(3, 12, 3);
                segmentTree.set(3, 13, 2);
                segmentTree.set(3, 14, 1);
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
                Solution2 solution = new Solution2();
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
                Solution2 solution = new Solution2();
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
                Solution2 solution = new Solution2();
                List<List<Integer>> ans = solution.getSkyline(buildings);
                int[][] ans2 = {{3, 8}, {7, 7}, {8, 6}, {9, 5}, {10, 4}, {11, 3}, {12, 2}, {13, 1}, {14, 0}};
                boolean isCorrect = compareResult(ans2, ans);
                log.info("correct:{},ans:{}", isCorrect, ans);
            }

        }
    }
}
