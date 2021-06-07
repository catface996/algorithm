package com.algorithm.course.索引树.impl;

import org.junit.Test;

/**
 * @author by catface
 * @date 2021/5/8 4:01 下午
 */
public class Solution {

    public static class IndexTree {

        private final int[] tree;
        private final int n;

        public IndexTree(int size) {
            this.n = size;
            // 弃用0位置
            this.tree = new int[n + 1];
        }

        public void add(int index, int value) {
            while (index <= n) {
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
    }

    public static class TestClass {
        @Test
        public void test1() {
            int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
            IndexTree indexTree = new IndexTree(arr.length);
            for (int i = 0; i < arr.length; i++) {
                indexTree.add(i + 1, arr[i]);
                int ans = indexTree.sum(5);
                System.out.println(ans);
            }
        }
    }
}
