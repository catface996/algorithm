package course.索引树.impl;

import org.junit.Test;

/**
 * @author by catface
 * @date 2021/5/10 10:43 上午
 */
public class Solution2 {

    public static class IndexTree {
        private final int[] tree;
        private final int size;

        public IndexTree(int size) {
            // 弃用0位置,从1位置开始
            this.size = size + 1;
            tree = new int[this.size];
        }

        public void add(int index, int value) {
            while (index < size) {
                tree[index] += value;
                index += index & -index;
            }
        }

        public int sum(int index) {
            int ans = 0;
            while (index > 0) {
                ans += tree[index];
                index -= index & -index;
            }
            return ans;
        }

        public int sumOfRange(int left, int right) {
            int sumOfLeft = sum(left - 1);
            int sumOfRight = sum(right);
            return sumOfRight - sumOfLeft;
        }
    }

    public static class TestClass {
        @Test
        public void test() {
            int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
            IndexTree indexTree = new IndexTree(arr.length);
            for (int i = 0; i < arr.length; i++) {
                indexTree.add(i + 1, arr[i]);
            }
            int ans = indexTree.sum(5);
            int ans2 = indexTree.sumOfRange(4, 8);
            System.out.println(ans);
            System.out.println(ans2);
        }
    }
}
