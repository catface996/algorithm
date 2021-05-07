package course.线段树.impl;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import util.ArrayUtil;

/**
 * @author by catface
 * @date 2021/5/7 10:26 上午
 */
@Slf4j
public class Solution2 {

    public static class SegmentTree {
        /**
         * 原始数组,弃用0位置,方便下标转换
         */
        private final int[] origin;
        /**
         * 累加和数组
         */
        private final int[] sum;
        /**
         * 延迟累加
         */
        private final int[] lazy;
        /**
         * 发生更改,更改后的值,记录区间更新后的值
         */
        private final int[] change;
        /**
         * 是否发生更改,change中的值为0时,无法判断是更新后的结果为0还是本身就是为0
         */
        private final boolean[] update;
        /**
         * 原始数组最大长度
         */
        private final int maxN;

        public SegmentTree(int[] nums) {
            this.maxN = nums.length + 1;
            this.origin = new int[nums.length + 1];
            System.arraycopy(nums, 0, origin, 1, nums.length);
            int maxLength = maxN << 2;
            this.sum = new int[maxLength];
            this.lazy = new int[maxLength];
            this.change = new int[maxLength];
            this.update = new boolean[maxLength];
            build(1, maxN - 1, 1);
        }

        /**
         * 向上求和
         *
         * @param rt 实际存储区间和的下标
         */
        private void pushUp(int rt) {
            sum[rt] = sum[2 * rt] + sum[2 * rt + 1];
        }

        /**
         * 下发lazy的数值到左右两个区间
         *
         * @param rt 记录当前区间累加和的下标
         * @param ln 左侧区间负责的原始数组的数字个数
         * @param rn 右侧区间负责的原始数数字的个数
         */
        private void pushDown(int rt, int ln, int rn) {
            int leftRt = rt << 1;
            int right = leftRt + 1;

            if (update[rt]) {
                // 如果rt位置负责的区域曾经发生过更新操作
                // 下发至左侧区间
                update[leftRt] = true;
                change[leftRt] = change[rt];
                lazy[leftRt] = 0;
                sum[leftRt] = change[rt] * ln;

                // 下发至右侧区间
                update[right] = true;
                change[right] = change[rt];
                lazy[right] = 0;
                sum[right] = change[rt] * rn;

                // 清空rt位置的更新信息,可以只更新update,不更新change,只有update[rt]是true,change[rt]才有效
                update[rt] = false;

            }

            // 只有在当前节点上有懒信息时,才下发
            if (lazy[rt] != 0) {
                // 下发到左侧区间
                sum[leftRt] += lazy[rt] * ln;
                lazy[leftRt] += lazy[rt];
                // 下发至右侧区间
                sum[right] += lazy[rt] * rn;
                lazy[right] += lazy[rt];
                // 清空当前节点的懒信息,已用于填充新的懒信息
                lazy[rt] = 0;
            }
        }

        /**
         * 构建线段树
         *
         * @param l  左侧下标,主方法调用build时,一般传入1
         * @param r  右侧下标,主方法调用时,一般传入原始数组的长度
         * @param rt 记录区间累加和的下标,主方法调用时,一般传入1
         */
        private void build(int l, int r, int rt) {
            if (l == r) {
                sum[rt] = origin[l];
                return;
            }
            int mid = l + ((r - l) >> 1);
            build(l, mid, rt << 1);
            build(mid + 1, r, rt << 1 | 1);
            pushUp(rt);
        }

        /**
         * 在指定区间上累加指定数值
         *
         * @param taskL     累加任务区间的左侧下标(左侧从第几个数开始,弃用0下标)
         * @param taskR     累加任务区间的右侧下标(右侧到第几个数结束,弃用0下标)
         * @param taskValue 要累加的数值
         * @param rtL       累加和数组负责的区间左侧下标
         * @param rtR       累加和数组负责的区间右侧下标
         * @param rt        当前累加和数组的当前下标
         */
        private void add(int taskL, int taskR, int taskValue, int rtL, int rtR, int rt) {
            if (taskL <= rtL && rtR <= taskR) {
                // 首先检查任务是否覆盖了整个区间,如果是,累加和增加,lazy增加
                sum[rt] += taskValue * (rtR - rtL + 1);
                lazy[rt] += taskValue;
                return;
            }
            // 任务没有覆盖rt的区间,需要将原有的lazy下发,再在左右两个区间上累加
            int mid = rtL + ((rtR - rtL) >> 1);
            pushDown(rt, mid - rtL + 1, rtR - mid);
            if (taskL <= mid) {
                // 累加任务的左侧区间有覆盖到当前节点负责的左侧区间
                add(taskL, taskR, taskValue, rtL, mid, rt << 1);
            }
            if (mid < taskR) {
                // 累加任务的右侧区间有覆盖到当前节点负责的右侧区间
                add(taskL, taskR, taskValue, mid + 1, rtR, rt << 1 | 1);
            }

            // 求最新的累加和
            pushUp(rt);
        }

        /**
         * 更新指定区间中的所有数值
         *
         * @param taskL     更新任务区间的左侧下标(取值范围为1~原始数组长度,弃用0位置)
         * @param taskR     更新任务区间的右侧下标(取值范围为1~原始数组长度,弃用0位置)
         * @param taskValue 待更新成的值
         * @param rtL       rt位置的累加和代表的区间左侧下标
         * @param rtR       rt位置的累加和代表的区间右侧下标
         * @param rt        记录累加和的当前位置
         */
        private void update(int taskL, int taskR, int taskValue, int rtL, int rtR, int rt) {
            if (taskL <= rtL && rtR <= taskR) {
                // 任务区间完全覆盖了rt位置代表的区间
                // 懒更新设置
                change[rt] = taskValue;
                update[rt] = true;
                sum[rt] = taskValue * (rtR - rtL + 1);
                // 懒累加清空
                lazy[rt] = 0;
                return;
            }

            // 当任务值覆盖了rt代表的区间中的部分时
            int mid = rtL + ((rtR - rtL) >> 1);
            pushDown(rt, mid - rtL + 1, rtR - mid);
            if (taskL <= mid) {
                // 更新任务有覆盖左侧部分区间,需要递归更新左侧区间
                update(taskL, taskR, taskValue, rtL, mid, rt << 1);
            }
            if (mid < taskR) {
                // 更新任务有覆盖右侧部分区间,需要递归更新右侧区间
                update(taskL, taskR, taskValue, mid + 1, rtR, rt << 1 | 1);
            }

            // 求最新的累加和
            pushUp(rt);
        }

        /**
         * 查询给定区间上的所有数字的累加和
         *
         * @param taskL 查询任务区间的左侧下标,取值范围1~原始数组的长度,弃用0位置
         * @param taskR 查询任务区间的右侧下标,取值范围1~原始数组的长度,弃用0位置
         * @param rtL   当前累加和节点代表区间的左侧下标
         * @param rtR   当前累加和节点代表区间的右侧下标
         * @param rt    当前累加和节点下标
         * @return 查询结果
         */
        private long query(int taskL, int taskR, int rtL, int rtR, int rt) {
            if (taskL <= rtL && rtR <= taskR) {
                // 查询任务的区间覆盖了rt位置代表的区间
                return sum[rt];
            }
            // 查询区间未完全覆盖rt位置代表的区间
            // 首先将积攒的任务下发
            int mid = rtL + ((rtR - rtL) >> 1);
            pushDown(rt, mid - rtL + 1, rtR - mid);
            long ans = 0;
            if (taskL <= mid) {
                // 查询任务有覆盖左侧区间,在左侧区间递归查询
                ans += query(taskL, taskR, rtL, mid, rt << 1);
            }
            if (mid < taskR) {
                ans += query(taskL, taskR, mid + 1, rtR, rt << 1 | 1);
            }
            return ans;
        }

        /**
         * 简单参数的累加
         *
         * @param taskL     累加区间的左侧下标
         * @param taskR     累加区间的右侧下标
         * @param taskValue 累加值
         */
        public void add(int taskL, int taskR, int taskValue) {
            add(taskL, taskR, taskValue, 1, maxN - 1, 1);
        }

        /**
         * 简单参数的更新
         *
         * @param taskL     更新区间的左侧下标
         * @param taskR     更新区间的右侧下标
         * @param taskValue 待更新的值
         */
        public void update(int taskL, int taskR, int taskValue) {
            update(taskL, taskR, taskValue, 1, maxN - 1, 1);
        }

        /**
         * 简单参数的查询
         *
         * @param taskL 查询范围的左侧下标
         * @param taskR 查询范围的右侧下标
         * @return 查询结果
         */
        public long query(int taskL, int taskR) {
            return query(taskL, taskR, 1, maxN - 1, 1);
        }

    }

    public static class TestClass {

        @Test
        public void testBuild() {
            int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
            SegmentTree segmentTree = new SegmentTree(arr);
            segmentTree.build(1, arr.length, 1);
            long ans1 = segmentTree.query(5, 4);
            System.out.println(ans1);
            //assert ans1 == 18;
            segmentTree.add(5, 4, 5);
            long ans2 = segmentTree.query(0, 10);
            System.out.println(ans2);
            //assert ans1 + (6 - 3 + 1) * 5 == ans2;
            segmentTree.update(5, 4, 10);
            long ans3 = segmentTree.query(0, 10);
            System.out.println(ans3);
            //assert ans3 == 40;
        }

        @Test
        public void testForce() {
            for (int time = 0; time < 1000; time++) {
                int size = (int)(Math.random() * 20) + 1;
                int[] nums = ArrayUtil.randomIntArray(size, 1, 2000);
                SegmentTree st = new SegmentTree(nums);
                Code01_SegmentTree.SegmentTree st2 = new Code01_SegmentTree.SegmentTree(nums);
                st.build(1, size, 1);
                st2.build(1, size, 1);
                for (int subTime = 0; subTime < 50; subTime++) {
                    int taskL = (int)(Math.random() * size / 2) + 1;
                    int taskR = taskL + (int)(Math.random() * size / 2);
                    int taskValue = (int)(Math.random() * 30) + 1;
                    //log.info("taskL:{},taskR:{},taskValue:{}", taskL, taskR, taskValue);
                    st.add(taskL, taskR, taskValue);
                    st2.add(taskL, taskR, taskValue, 1, size, 1);
                    long ans1 = st.query(taskL, taskR);
                    long ans2 = st2.query(taskL, taskR, 1, size, 1);
                    assert ans1 == ans2;
                    st.update(taskL, taskR, taskValue);
                    st2.update(taskL, taskR, taskValue, 1, size, 1);
                    ans1 = st.query(taskL, taskR);
                    ans2 = st2.query(taskL, taskR, 1, size, 1);
                    assert ans1 == ans2;
                }
            }
            System.out.println("Good,you success!");
        }
    }

}
