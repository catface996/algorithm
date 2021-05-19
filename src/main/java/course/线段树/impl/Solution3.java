package course.线段树.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/5/19 3:12 下午
 */
@Slf4j
public class Solution3 {

    public static class SegmentTree {
        /**
         * 原始数组长度+1,弃用0位置
         */
        private int maxN;
        /**
         * 原始数组
         */
        private int[] origin;
        /**
         * 累加和数组
         */
        private int[] sum;
        /**
         * 延迟累加数组
         */
        private int[] lazy;
        /**
         * 延迟更新数组
         */
        private int[] change;
        /**
         * 延迟更新数组的值是否有效
         */
        private boolean[] update;

        public SegmentTree(int[] arr) {
            maxN = arr.length + 1;
            origin = new int[maxN];
            sum = new int[maxN << 2];
            lazy = new int[maxN << 2];
            change = new int[maxN << 2];
            update = new boolean[maxN << 2];
            System.arraycopy(arr, 0, origin, 1, arr.length);
            build(1, maxN - 1, 1);
        }

        private void build(int l, int r, int treeIndex) {
            if (l == r) {
                sum[treeIndex] = origin[l];
                return;
            }
            int mid = l + ((r - l) >> 1);
            int leftIndex = treeIndex << 1;
            int rightIndex = leftIndex | 1;
            build(l, mid, leftIndex);
            build(mid + 1, r, rightIndex);
            // 这一句其实就是pushUp
            sum[treeIndex] = sum[leftIndex] + sum[rightIndex];
        }

        private void pushUp(int treeIndex) {
            // leftIndex 一定是偶数
            int leftIndex = treeIndex << 1;
            // 偶数按位或上1,一定是+1
            int rightIndex = leftIndex | 1;
            sum[treeIndex] = sum[leftIndex] + sum[rightIndex];
        }

        /**
         * 向下下发时,
         *
         * @param treeIndex
         * @param ln
         * @param rn
         */
        private void pushDown(int treeIndex, int ln, int rn) {
            int leftIndex = treeIndex << 1;
            int rightIndex = leftIndex | 1;
            // 如果有更新,以更新为准,清空原有更新,清空原有的lazy
            if (update[treeIndex]) {
                // 有有效更新存在,下推更新,只是下发了更新,当前节点的sum没有发生任何变化
                change[leftIndex] = change[treeIndex];
                change[rightIndex] = change[treeIndex];
                lazy[leftIndex] = 0;
                lazy[rightIndex] = 0;
                sum[leftIndex] = change[treeIndex] * ln;
                sum[rightIndex] = change[treeIndex] * rn;
                update[leftIndex] = true;
                update[rightIndex] = true;

                // 清空当前位置的懒更新
                update[treeIndex] = false;
            }

            // 如果有懒累加
            if (lazy[treeIndex] != 0) {
                // 只需要下发累加,当前节点的sum没有发生任何变化
                lazy[leftIndex] = lazy[treeIndex];
                lazy[rightIndex] = lazy[treeIndex];
                sum[leftIndex] += lazy[treeIndex] * ln;
                sum[rightIndex] += lazy[treeIndex] * rn;

                // 清空当前节点的懒累加
                lazy[treeIndex] = 0;
            }

        }

        /**
         * 在数组的指定区间的每个数字上累加指定的值
         *
         * @param aL        指定区间的左侧下标
         * @param aR        指定区间的右侧下标
         * @param value     待累加的指定值
         * @param l         目前扫描的区间左侧下标
         * @param r         目前扫描的区间右侧下标
         * @param treeIndex 代表当前扫描区间,出现在树种的索引位置
         */
        private void add(int aL, int aR, int value, int l, int r, int treeIndex) {

            if (aL <= l && r <= aR) {
                // 待累加的区间完全覆盖了扫描到的区间,此时只需要在lazy和sum上做改动即可
                lazy[treeIndex] += value;
                sum[treeIndex] += value * (r - l + 1);
                return;
            }

            // 待累加的区间未完全覆盖扫描到的区间,首先向下发放累加或者更新
            int mid = l + ((r - l) >> 1);
            pushDown(treeIndex, mid - l + 1, r - mid);

            // 计算代表左右两侧的树中下标,以便对左右两侧区间进行递归累加
            int leftIndex = treeIndex << 1;
            int rightIndex = leftIndex | 1;
            if (aL <= mid) {
                // 累加区间有覆盖到左侧区间,需要在左侧区间上递归
                add(aL, aR, value, l, mid, leftIndex);
            }
            if (aR > mid) {
                // 累加区间有覆盖到右侧区间,需要在右侧区间上递归
                add(aL, aR, value, mid + 1, r, rightIndex);
            }
            // 左右两侧区间各自累加完成后,需要重新计算当前节点的累加和
            sum[treeIndex] = sum[leftIndex] + sum[rightIndex];
        }

        /**
         * 批量更新指定区间中的所有数字
         *
         * @param uL        指定区间的左侧下标
         * @param uR        指定区间的右侧下标
         * @param value     待更新的值
         * @param l         扫描区间的左侧下标
         * @param r         扫描区间的右侧下标
         * @param treeIndex 代表扫描区间的二叉树节点下标
         */
        private void update(int uL, int uR, int value, int l, int r, int treeIndex) {
            if (uL <= l && r <= uR) {
                // 待更新区间完全覆盖了扫描到的区间,直接在sum和change和update上更改即可
                sum[treeIndex] = value * (r - l + 1);
                change[treeIndex] = value;
                update[treeIndex] = true;
                return;
            }

            // 下发缓存的更新或者累加
            int mid = l + ((r - l) >> 1);
            pushDown(treeIndex, mid - l + 1, r - mid);

            int leftIndex = treeIndex << 1;
            int rightIndex = leftIndex | 1;
            if (uL <= mid) {
                // 待更新区间有覆盖扫描区间的左半部分,需要在左半侧递归更新
                update(uL, uR, value, l, mid, leftIndex);
            }
            if (uR > mid) {
                // 待更新区间有覆盖扫描区间的右半侧,需要在右半侧递归更新
                update(uL, uR, value, mid + 1, r, rightIndex);
            }

            // 更新当前节点的累加和
            sum[treeIndex] = sum[leftIndex] + sum[rightIndex];

        }

        private int query(int qL, int qR, int l, int r, int treeIndex) {
            if (qL <= l && r <= qR) {
                // 查询区间完整覆盖了遍历到的区间,直接返回sum[treeIndex]
                return sum[treeIndex];
            }
            // 查询区间未完全覆盖,下发懒累加,和懒更新
            int mid = l + ((r - l) >> 1);
            pushDown(treeIndex, mid - l + 1, r - mid);

            // 递归向下
            int leftIndex = treeIndex << 1;
            int rightIndex = leftIndex | 1;
            int ans = 0;
            if (qL <= mid) {
                // 查询区间与左侧区间有重合,需要计算左侧区间的值
                ans += query(qL, qR, l, mid, leftIndex);
            }
            if (qR > mid) {
                // 查询区间与右侧区间有重合,需要计算右侧区间的值
                ans += query(qL, qR, mid + 1, r, rightIndex);
            }
            //TODO query时,并不改变累加和,即使发生了pushDown,pushDown 不改变当前节点的sum值
            // sum[treeIndex] = sum[leftIndex] + sum[rightIndex];
            return ans;
        }

    }

    public static class TestClass {
        @Test
        public void testBuild() {
            int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
            SegmentTree segmentTree = new SegmentTree(arr);
            System.out.println("1");
        }

        @Test
        public void testQuery() {
            int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
            SegmentTree segmentTree = new SegmentTree(arr);
            int ans;
            for (int i = 1; i <= 8; i++) {
                ans = segmentTree.query(1, i, 1, 8, 1);
                log.info("{} to {},sum:{}", 1, i, ans);
            }
        }

        @Test
        public void testAdd() {
            for (int i = 1; i <= 8; i++) {
                int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
                SegmentTree segmentTree = new SegmentTree(arr);
                segmentTree.add(1, i, 1, 1, 8, 1);
                int ans = segmentTree.query(1, i, 1, 8, 1);
                log.info("{} to {},sum:{}", 1, i, ans);
            }
        }

        @Test
        public void testUpdate() {
            int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
            SegmentTree segmentTree = new SegmentTree(arr);
            int ans = segmentTree.query(1, 8, 1, 8, 1);
            log.info("{} to {},sum:{}", 1, 8, ans);
            for (int i = 1; i <= 8; i++) {
                segmentTree.update(1, i, 1, 1, 8, 1);
                ans = segmentTree.query(1, 8, 1, 8, 1);
                log.info("{} to {},sum:{}", 1, 8, ans);
            }
        }

    }

}
