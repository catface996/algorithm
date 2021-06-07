package com.algorithm.leetcode.线段树.P699掉落的方块;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
//在无限长的数轴（即 x 轴）上，我们根据给定的顺序放置对应的正方形方块。
//
// 第 i 个掉落的方块（positions[i] = (left, side_length)）是正方形，其中 left 表示该方块最左边的点位置(posit
//ions[i][0])，side_length 表示该方块的边长(positions[i][1])。
//
// 每个方块的底部边缘平行于数轴（即 x 轴），并且从一个比目前所有的落地方块更高的高度掉落而下。在上一个方块结束掉落，并保持静止后，才开始掉落新方块。
//
// 方块的底边具有非常大的粘性，并将保持固定在它们所接触的任何长度表面上（无论是数轴还是其他方块）。邻接掉落的边不会过早地粘合在一起，因为只有底边才具有粘性。
//
//
//
//
// 返回一个堆叠高度列表 ans 。每一个堆叠高度 ans[i] 表示在通过 positions[0], positions[1], ..., positio
//ns[i] 表示的方块掉落结束后，目前所有已经落稳的方块堆叠的最高高度。
//
//
//
//
//
// 示例 1:
//
// 输入: [[1, 2], [2, 3], [6, 1]]
//输出: [2, 5, 5]
//解释:
//
//第一个方块 positions[0] = [1, 2] 掉落：
//_aa
//_aa
//-------
//方块最大高度为 2 。
//
//第二个方块 positions[1] = [2, 3] 掉落：
//__aaa
//__aaa
//__aaa
//_aa__
//_aa__
//--------------
//方块最大高度为5。
//大的方块保持在较小的方块的顶部，不论它的重心在哪里，因为方块的底部边缘有非常大的粘性。
//
//第三个方块 positions[1] = [6, 1] 掉落：
//__aaa
//__aaa
//__aaa
//_aa
//_aa___a
//--------------
//方块最大高度为5。
//
//因此，我们返回结果[2, 5, 5]。
//
//
//
//
// 示例 2:
//
// 输入: [[100, 100], [200, 100]]
//输出: [100, 100]
//解释: 相邻的方块不会过早地卡住，只有它们的底部边缘才能粘在表面上。
//
//
//
//
// 注意:
//
//
// 1 <= positions.length <= 1000.
// 1 <= positions[i][0] <= 10^8.
// 1 <= positions[i][1] <= 10^6.
//
//
//
// Related Topics 线段树 Ordered Map
// 👍 58 👎 0

/**
 * @author by catface
 * @date 2021/5/7 4:47 下午
 */
@Slf4j
public class Solution3 {

    // 7:14 下午	info
    //				解答成功:
    //				执行耗时:19 ms,击败了86.01% 的Java用户
    //				内存消耗:39.4 MB,击败了46.85% 的Java用户

    public List<Integer> fallingSquares(int[][] positions) {
        Map<Integer, Integer> indexMapping = convertIndexMapping(positions);
        SegmentTree st = new SegmentTree(indexMapping.size());
        int taskL;
        int taskR;
        int taskValue;
        int maxHeight = 0;
        List<Integer> ans = new ArrayList<>(positions.length);
        for (int[] position : positions) {
            taskL = indexMapping.get(position[0]);
            taskR = indexMapping.get(position[0] + position[1] - 1);
            taskValue = position[1];
            int tempHeight = st.query(taskL, taskR) + taskValue;
            st.update(taskL, taskR, tempHeight);
            maxHeight = Math.max(maxHeight, tempHeight);
            ans.add(maxHeight);
        }
        return ans;
    }

    private Map<Integer, Integer> convertIndexMapping(int[][] positions) {
        TreeSet<Integer> indexSet = new TreeSet<>();
        for (int[] position : positions) {
            indexSet.add(position[0]);
            indexSet.add(position[0] + position[1] - 1);
        }
        int currentIndex = 0;
        Map<Integer, Integer> indexMapping = new HashMap<>();
        for (Integer integer : indexSet) {
            indexMapping.put(integer, ++currentIndex);
        }
        return indexMapping;
    }

    public static class SegmentTree {
        /**
         * 线段树原始数组的长度,下落方块的最右下标+方块的边长
         */
        private final int maxN;
        /**
         * 懒更新数组
         */
        private final int[] change;
        /**
         * 记录懒更新数组中的值是否生效
         */
        private final boolean[] update;
        /**
         * 记录区间中的最大值
         */
        private final int[] max;

        /**
         * 初始化线段树,默认未掉落方块之前,高度均为0
         *
         * @param size 线段树原始数组的长度,下落方块的最右下标+方块的边长-1
         */
        public SegmentTree(int size) {
            maxN = size + 1;
            int maxN4 = maxN << 2;
            change = new int[maxN4];
            update = new boolean[maxN4];
            max = new int[maxN4];
        }

        /**
         * 向上推送最大值
         *
         * @param rt 线段树中的指定位置
         */
        private void pushUp(int rt) {
            max[rt] = Math.max(max[rt << 1], max[rt << 1 | 1]);
        }

        /**
         * 向下下发可能存在的懒更新
         *
         * @param rt 线段树中的指定位置
         */
        private void pushDown(int rt) {
            if (update[rt]) {
                int left = rt << 1;
                int right = left + 1;
                // 更新左侧,max和change均从change中取值进行更新
                max[left] = change[rt];
                change[left] = change[rt];
                update[left] = true;

                // 更新右侧
                max[right] = change[rt];
                change[right] = change[rt];
                update[right] = true;

                // 清空rt
                update[rt] = false;
            }
        }

        /**
         * 更新指定区间内的所有数字的值
         *
         * @param taskL     待更新区间的左侧下标,有效范围为1~maxN-1
         * @param taskR     待更新区间的右侧下标,有效范围为1~maxN-1
         * @param taskValue 待更新的值
         * @param rtL       当前位置代表的区间左侧下标
         * @param rtR       当前位置代表的右侧区间下标
         * @param rt        当前位置
         */
        private void update(int taskL, int taskR, int taskValue, int rtL, int rtR, int rt) {
            if (taskL <= rtL && rtR <= taskR) {
                // 任务区间完全覆盖rt代表的区间
                max[rt] = taskValue;
                change[rt] = taskValue;
                update[rt] = true;
                return;
            }

            // 向下分发之前的懒更新
            pushDown(rt);

            // 任务区间未完全覆盖rt代表的区间,取中点,对左右两侧区间进行递归处理
            int mid = rtL + ((rtR - rtL) >> 1);
            if (taskL <= mid) {
                // 更新任务有覆盖左侧区间,对左侧区间进行递归更新
                update(taskL, taskR, taskValue, rtL, mid, rt << 1);
            }
            if (mid < taskR) {
                // 更新任务有覆盖右侧区间,对右侧区间进行递归更新
                update(taskL, taskR, taskValue, mid + 1, rtR, rt << 1 | 1);
            }

            // 之前发生了下发行为,并且偶有两侧区间至少有一个数据发生变更,需要重新计算当前节点的值
            pushUp(rt);
        }

        /**
         * 查询区间中的最大值
         *
         * @param taskL 查询任务区间的左侧下标,有效范围为1~maxN-1
         * @param taskR 查询任务区间的右侧下标,有效范围为1~maxN-1
         * @param rtL   当前节点代表的区间左侧下标
         * @param rtR   当前节点代表的区间右侧下标
         * @param rt    当前节点的下标
         * @return 指定区间中的最大值
         */
        private int query(int taskL, int taskR, int rtL, int rtR, int rt) {
            if (taskL <= rtL && rtR <= taskR) {
                // 任务区间完全覆盖rt位置代表的区间,直接返回max[rt]
                return max[rt];
            }

            // 任务区间未完全覆盖rt代表的区间,将区间分左右两侧,分别查询,查询前先下发之前可能存在的更新任务
            pushDown(rt);

            int mid = rtL + ((rtR - rtL) >> 1);
            int max = Integer.MIN_VALUE;
            if (taskL <= mid) {
                // 查询任务有覆盖左侧区间,在左侧区间进行递归
                max = query(taskL, taskR, rtL, mid, rt << 1);
            }
            if (mid < taskR) {
                // 更新任务有覆盖右侧区间,在右侧区间上进行递归
                max = Math.max(max, query(taskL, taskR, mid + 1, rtR, rt << 1 | 1));
            }
            return max;
        }

        /**
         * 简化更新参数
         *
         * @param taskL     更新任务指定区间的左侧下标
         * @param taskR     更新任务指定区间的右侧下标
         * @param taskValue 待更新的值
         */
        public void update(int taskL, int taskR, int taskValue) {
            update(taskL, taskR, taskValue, 1, maxN - 1, 1);
        }

        /**
         * 简化查询参数
         *
         * @param taskL 查询任务指定区间的左侧下标
         * @param taskR 查询任务指定区间的右侧下标
         * @return 区间中的最大值
         */
        public int query(int taskL, int taskR) {
            return query(taskL, taskR, 1, maxN - 1, 1);
        }

    }

    public static class TestClass {

        // 示例 1:
        //
        // 输入: [[1, 2], [2, 3], [6, 1]]
        //输出: [2, 5, 5]
        @Test
        public void test1() {
            int[][] positions = {{1, 2}, {2, 3}, {6, 1}};
            Solution3 solution2 = new Solution3();
            List<Integer> ans = solution2.fallingSquares(positions);
            log.info("ans:{}", ans);
        }

        // 示例 2:
        //
        // 输入: [[100, 100], [200, 100]]
        //输出: [100, 100]
        @Test
        public void test2() {
            int[][] positions = {{100, 100}, {200, 100}};
            Solution3 solution2 = new Solution3();
            List<Integer> ans = solution2.fallingSquares(positions);
            log.info("ans:{}", ans);
        }
    }
}
