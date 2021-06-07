package com.algorithm.question.class1.class1_AOE技能打怪.code;

import java.util.Arrays;

import com.algorithm.util.ArrayUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/5/21 7:29 下午
 */
@Slf4j
public class Solution4 {

    SegmentTree st;
    private int[] x;
    private int range;
    private int monsterNums;
    private int mostLeftI = -1;
    private int targetI = 0;
    private int mostRightI = 0;
    private String reg = "[{0},{1},{2},{3},{4}]";

    public int minAoeTimes(int[] x, int[] hp, int range) {
        this.x = x;
        this.range = range;
        this.monsterNums = x.length;
        this.mostLeftI = -1;
        this.targetI = 0;
        this.mostRightI = 0;
        int ans = 0;
        st = new SegmentTree(hp);
        //StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            if (findRange()) {
                //System.out.println(stringBuilder);
                return ans;
            }
            int aoeTimes = st.query(mostLeftI);
            st.add(mostLeftI, mostRightI, (-aoeTimes));
            ans += aoeTimes;
            //stringBuilder.append(MessageFormat.format(reg, mostLeftI, targetI, mostRightI, -aoeTimes, ans));
        }
    }

    private boolean findRange() {
        mostLeftI++;
        while (mostLeftI < monsterNums && st.query(mostLeftI) <= 0) {
            mostLeftI++;
        }
        if (mostLeftI >= monsterNums) {
            return true;
        }
        targetI = Math.max(mostLeftI, targetI);
        while (targetI < monsterNums && x[targetI] - x[mostLeftI] <= range) {
            targetI++;
        }
        targetI--;
        mostRightI = Math.max(targetI, mostRightI);
        while (mostRightI < monsterNums && x[mostRightI] - x[targetI] <= range) {
            mostRightI++;
        }
        mostRightI--;
        return false;
    }

    public static class SegmentTree {
        private int maxN;
        private int[] origin;
        private int[] sum;
        private int[] lazy;

        public SegmentTree(int[] arr) {
            maxN = arr.length + 1;
            origin = new int[maxN];
            sum = new int[maxN << 2];
            lazy = new int[maxN << 2];
            System.arraycopy(arr, 0, origin, 1, maxN - 1);
            build(1, maxN - 1, 1);
        }

        private void build(int l, int r, int rt) {
            if (l == r) {
                sum[rt] = origin[l];
                return;
            }

            int mid = l + ((r - l) >> 1);
            int leftRt = rt << 1;
            int rightRt = leftRt | 1;
            build(l, mid, leftRt);
            build(mid + 1, r, rightRt);
            sum[rt] = sum[leftRt] + sum[rightRt];
        }

        private void pushDown(int rt, int ln, int rn) {
            if (lazy[rt] != 0L) {
                int leftRt = rt << 1;
                int rightRt = leftRt | 1;
                lazy[leftRt] += lazy[rt];
                lazy[rightRt] += lazy[rt];
                sum[leftRt] += lazy[rt] * ln;
                sum[rightRt] += lazy[rt] * rn;
                lazy[rt] = 0;
            }
        }

        private void add(int L, int R, int value, int l, int r, int rt) {
            if (L <= l && r <= R) {
                lazy[rt] += value;
                sum[rt] += (r - l + 1) * value;
                return;
            }

            int mid = l + ((r - l) >> 1);
            pushDown(rt, mid - l + 1, r - mid);
            int leftRt = rt << 1;
            int rightRt = leftRt | 1;
            if (L <= mid) {
                add(L, R, value, l, mid, leftRt);
            }
            if (mid < R) {
                add(L, R, value, mid + 1, r, rightRt);
            }
            sum[rt] = sum[leftRt] + sum[rightRt];
        }

        private void add(int L, int R, int value) {
            add(L + 1, R + 1, value, 1, maxN - 1, 1);
        }

        private int query(int L, int R, int l, int r, int rt) {
            if (L <= l && r <= R) {
                return sum[rt];
            }
            int mid = l + ((r - l) >> 1);
            pushDown(rt, mid - l + 1, r - mid);
            int leftRt = rt << 1;
            int rightRt = leftRt | 1;
            int ans = 0;
            if (L <= mid) {
                ans += query(L, R, l, mid, leftRt);
            }
            if (mid < R) {
                ans += query(L, R, mid + 1, r, rightRt);
            }
            return ans;
        }

        private int query(int index) {
            return query(index + 1, index + 1, 1, maxN - 1, 1);
        }
    }

    public static class TestClass {
        @Test
        public void testSegmentTree() {
            int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
            SegmentTree segmentTree = new SegmentTree(arr);
            for (int i = 1; i < 10; i++) {
                long ans = segmentTree.query(i, i, 1, 9, 1);
                System.out.println(ans);
            }
            System.out.println("累加和");
            for (int i = 1; i < 10; i++) {
                long ans = segmentTree.query(1, i, 1, 9, 1);
                System.out.println(ans);
            }
            System.out.println("加一");
            for (int i = 1; i < 10; i++) {
                segmentTree.add(i, i, 1, 1, 9, 1);
            }
            for (int i = 1; i < 10; i++) {
                long ans = segmentTree.query(1, i, 1, 9, 1);
                System.out.println(ans);
            }
        }

        // 怪兽 [1,3,5,7,12,13,18]
        // 血量 [1,6,2,5,14,7,22]
        @Test
        public void test1() {
            Solution4 solution4 = new Solution4();
            int[] x = {1, 3, 5, 7, 12, 13, 18};
            int[] hp = {1, 6, 2, 5, 14, 7, 22};
            int[] hp2 = ArrayUtil.clone(hp);
            int range = 5;
            int minAoeTime = solution4.minAoeTimes(x, hp, range);
            System.out.println(minAoeTime);

            int ans2 = Code06_AOE.minAoe2(x, hp2, range);
            System.out.println(ans2);
        }

        @Test
        public void test2() {
            int[] x = {8913};
            int[] hp = {42};
            int range = 6;
            Solution4 solution4 = new Solution4();
            int ans = solution4.minAoeTimes(x, hp, range);
            System.out.println(ans);
        }

        @Test
        public void test3() {
            int[] x = {1574, 3230, 6406, 7490};
            int[] hp = {48, 19, 25, 8};
            int range = 7;
            Solution4 solution4 = new Solution4();
            int ans = solution4.minAoeTimes(x, hp, range);
            System.out.println(ans);

            Solution3 solution3 = new Solution3();
            int ans2 = solution3.minAoe(x, hp, range);
            System.out.println(ans2);
        }

        @Test
        public void test4() {
            int range = 7;
            //int[] x =
            //    {
            //        49, 198, 476, 1720, 1751, 1753, 2169, 2829, 2898, 3216,
            //        3273, 6974, 7224, 7225, 7231, 7233, 7236, 7239, 7669, 8153,
            //        8588, 8762, 9123, 9131, 9253, 9323, 9333, 9338, 9570, 9610
            //    };
            int[] x =
                {
                    49, 198, 476, 1720, 1751, 1753, 2169, 2829, 2898, 3216,
                    3273, 4272, 4682, 5219, 5546, 5810, 5879, 6532, 6565, 6639,
                    6788, 6974, 7224, 7225, 7231, 7233, 7236, 7239, 7669, 8153,
                    8588, 8762, 9123, 9131, 9253, 9323, 9333, 9338, 9570, 9610
                };
            for (int i = 0; i < x.length - 1; i++) {
                if (x[i + 1] - x[i] <= range) {
                    System.out.print(i);
                    System.out.print('-');
                    System.out.print(i + 1);
                    System.out.print(',');
                } else {
                    System.out.print(i);
                    System.out.print(',');
                }
            }
            System.out.println();
            // 0,1,2,3,4-5,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22-23-24,24-25,25-26,26-27,27,28,29,30,31,
            // 32,33,34,35,36-37,37,38,
            //int[] hp = {
            //    26, 25, 24, 47, 39, 27, 23, 29, 4, 12,
            //    42, 11, 41, 44, 38, 45, 29, 31, 40,
            //    17, 14, 47, 3, 8, 20, 1, 10, 48, 10};
            int[] hp = {
                26, 25, 24, 47, 39, 27, 23, 29, 4, 12,
                42, 10, 22, 13, 16, 39, 21, 46, 25, 7,
                23, 11, 11, 41, 44, 38, 45, 29, 31, 40,
                17, 14, 47, 3, 8, 20, 1, 10, 48, 10};
            Solution4 solution4 = new Solution4();
            int ans = solution4.minAoeTimes(x, ArrayUtil.clone(hp), range);
            System.out.println(ans);

            Solution3 solution3 = new Solution3();
            int ans2 = solution3.minAoe(x, ArrayUtil.clone(hp), range);
            System.out.println(ans2);

            Solution2 solution = new Solution2();
            int ans3 = solution.minAoe(x, ArrayUtil.clone(hp), range);
            System.out.println(ans3);
        }

        @Test
        public void testForce() {
            int X = 10000;
            int H = 50;
            int R = 10;
            int time = 50000;
            for (int N = 1000; N < 10000; N++) {
                System.out.println("test begin");
                long time1 = 0;
                long time2 = 0;

                for (int i = 0; i < time; i++) {
                    // 生成测试数据
                    int len = (int)(Math.random() * N) + 1;
                    int[] x = Code06_AOE.randomArray(len, X);
                    Arrays.sort(x);
                    int[] hp = Code06_AOE.randomArray(len, H);
                    int range = (int)(Math.random() * R) + 1;

                    try {
                        // 复制测试数据
                        int[] x2 = Code06_AOE.copyArray(x);
                        int[] hp2 = Code06_AOE.copyArray(hp);
                        // 计算并统计时间
                        Solution4 solution4 = new Solution4();
                        long start1 = System.currentTimeMillis();
                        int ans1 = solution4.minAoeTimes(x2, hp2, range);
                        long end1 = System.currentTimeMillis();
                        time1 += end1 - start1;

                        //复制测试数据
                        int[] x3 = Code06_AOE.copyArray(x);
                        int[] hp3 = Code06_AOE.copyArray(hp);
                        // 计算并统计时间
                        long start3 = System.currentTimeMillis();
                        int ans3 = Code06_AOE.minAoe3(x3, hp3, range);
                        long end3 = System.currentTimeMillis();
                        time2 += end3 - start3;

                        if (ans1 != ans3) {
                            System.out.println("Oops!");
                            System.out.println(ans1 + "," + ans3);
                            log.info("x;{},hp:{},range:{}", x, hp, range);
                        }
                    } catch (Exception e) {
                        log.info("x;{},hp:{},range:{}", x, hp, range);
                    }
                }
                System.out.println("test end");
                System.out.println(time1);
                System.out.println(time2);
            }

        }
    }
}
