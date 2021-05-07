package course.线段树.impl;

import org.junit.Test;

/**
 * @author by catface
 * @date 2021/5/6 1:43 下午
 */
public class Solution {

    public static class SegmentTree {
        private int maxN;
        private int[] arr;
        private int[] sum;
        private int[] lazy;
        private int[] change;
        private boolean[] update;

        public SegmentTree(int[] origin) {
            this.maxN = origin.length + 1;
            arr = new int[maxN];
            System.arraycopy(origin, 0, arr, 1, maxN - 1);
            int tempMqxN = maxN << 2;
            sum = new int[tempMqxN];
            lazy = new int[tempMqxN];
            change = new int[tempMqxN];
            update = new boolean[tempMqxN];
        }

        public void build(int l, int r, int rt) {
            if (l == r) {
                sum[rt] = arr[l];
                return;
            }
            int mid = (l + r) >> 1;
            build(l, mid, rt << 1);
            build(mid + 1, r, rt << 1 | 1);
            pushUp(rt);
        }

        private void pushUp(int rt) {
            // 骚包写法,非负偶数 按位或1,等于非负偶数+1
            sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
            //sum[rt] = sum[2 * rt] + sum[2 * rt +1];
        }

        private void pushDown(int rt, int ln, int rn) {
            int left = rt << 1;
            int right = rt << 1 | 1;
            if (update[rt]) {
                update[left] = true;
                update[right] = true;
                change[left] = change[rt];
                change[right] = change[rt];
                lazy[right] = 0;
                lazy[left] = 0;
                sum[left] = change[rt] * ln;
                sum[right] = change[rt] * rn;
                update[rt] = false;
            }
            if (lazy[rt] != 0) {
                lazy[right] = lazy[rt];
                lazy[left] = lazy[rt];
                sum[left] = lazy[rt] * ln;
                sum[right] = lazy[rt] * rn;
                lazy[rt] = 0;
            }
        }

        // L到R范围,累加C任务
        // rt,l~r
        public void add(int L, int R, int C, int l, int r, int rt) {
            if (L <= l && r <= R) {
                sum[rt] += C * (r - l + 1);
                lazy[rt] += C;
                return;
            }
            // 任务没有全包
            // l r mid = (l+r)>>1;
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            if (L <= mid) {
                add(L, R, C, l, mid, rt << 1);
            }
            if (R > mid) {
                add(L, R, C, mid + 1, r, rt << 1 | 1);
            }
            pushUp(rt);
        }

        public void update(int L, int R, int C, int l, int r, int rt) {
            if (L <= l && r <= R) {
                sum[rt] += C * (r - l + 1);
                lazy[rt] = C;
                return;
            }
            // 任务没有被全包
            // l r mid = (l+r)>>1;
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            if (L <= mid) {
                add(L, R, C, l, mid, rt << 1);
            }
            if (R > mid) {
                add(L, R, C, mid + 1, r, rt << 1 | 1);
            }
            pushUp(rt);
        }

        public long query(int L, int R, int l, int r, int rt) {
            if (L <= l && r <= R) {
                return sum[rt];
            }
            int mid = (l + r) >> 1;
            pushDown(rt, mid - l + 1, r - mid);
            long ans = 0;
            if (L <= mid) {
                ans += query(L, R, l, mid, rt << 1);
            }
            if (R > mid) {
                ans += query(L, R, mid + 1, r, rt << 1 | 1);
            }
            return ans;
        }
    }

    public static class ForceRight {
        public int[] arr;

        public ForceRight(int[] origin) {
            arr = new int[origin.length + 1];
            for (int i = 0; i < origin.length; i++) {
                arr[i + 1] = origin[i];
            }
        }

        public void update(int L, int R, int C) {
            for (int i = L; i <= R; i++) {
                arr[i] = C;
            }
        }

        public void add(int L, int R, int C) {
            for (int i = L; i <= R; i++) {
                arr[i] += C;
            }
        }

        public long query(int L, int R) {
            long ans = 0;
            for (int i = L; i <= R; i++) {
                ans += arr[i];
            }
            return ans;
        }

    }

    public static class TestClass {

        @Test
        public void test1() {
            int i = 2;
            int left = i << 1;
            int right = i << 1 | 1;
            System.out.println(left);
            System.out.println(right);
        }

        @Test
        public void test2() {
            for (int i = 0; i < 1000000; i++) {
                int ai = 2 * i + 1;
                int bi = 2 * i | 1;
                assert ai == bi;
            }
        }

        @Test
        public void test3() {
            int[] origin = {2, 1, 1, 2, 3, 4, 5};
            SegmentTree seg = new SegmentTree(origin);
            int S = 1; // 整个区间的开始位置，规定从1开始，不从0开始 -> 固定
            int N = origin.length; // 整个区间的结束位置，规定能到N，不是N-1 -> 固定
            int root = 1; // 整棵树的头节点位置，规定是1，不是0 -> 固定
            int L = 2; // 操作区间的开始位置 -> 可变
            int R = 5; // 操作区间的结束位置 -> 可变
            int C = 4; // 要加的数字或者要更新的数字 -> 可变
            // 区间生成，必须在[S,N]整个范围上build
            seg.build(S, N, root);
            // 区间修改，可以改变L、R和C的值，其他值不可改变
            seg.add(L, R, C, S, N, root);
            // 区间更新，可以改变L、R和C的值，其他值不可改变
            seg.update(L, R, C, S, N, root);
            // 区间查询，可以改变L和R的值，其他值不可改变
            long sum = seg.query(L, R, S, N, root);
            System.out.println(sum);
        }
    }
}
