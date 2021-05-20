package course.索引树.impl;

import org.junit.Test;

/**
 * @author by catface
 * @date 2021/5/20 11:34 上午
 */
public class Solution3 {

    public static class IndexTree {

        private final int size;
        private final int[] tree;

        public IndexTree(int size) {
            this.size = size;
            tree = new int[size + 1];
        }

        private void add(int index, int value) {
            while (index <= size) {
                tree[index] += value;
                index += index & -index;
            }
        }

        private int sum(int index) {
            int ans = 0;
            while (index > 0) {
                ans += tree[index];
                index -= index & -index;
            }
            return ans;
        }

    }

    public static class TestClass {
        @Test
        public void testAdd() {
            IndexTree indexTree = new IndexTree(10);
            for (int i = 1; i <= 10; i++) {
                indexTree.add(i, 1);
            }
        }

        @Test
        public void testSum() {
            IndexTree indexTree = new IndexTree(10);
            for (int i = 1; i <= 10; i++) {
                indexTree.add(i, 1);
            }
            for (int i = 1; i <= 10; i++) {
                int ans = indexTree.sum(i);
                System.out.println(ans);
            }
        }
    }
}
