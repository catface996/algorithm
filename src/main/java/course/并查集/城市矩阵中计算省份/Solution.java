package course.并查集.城市矩阵中计算省份;

/**
 * @author by catface
 * @date 2021/4/19 11:30 上午
 */
public class Solution {

    // 本题为leetcode原题
    // 测试链接：https://leetcode.com/problems/friend-circles/
    // 可以直接通过

    public static int findCircleNum(int[][] isConnected) {
        if (isConnected == null || isConnected.length <= 0) {
            return 0;
        }
        int n = isConnected.length;
        UnionFind unionFind = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (isConnected[i][j] == 1) {
                    unionFind.union(i, j);
                }
            }
        }
        return unionFind.sets;
    }

    public static class UnionFind {

        /**
         * parent[i] = k 表示,i的父节点是k
         */
        private int[] parent;

        /**
         * 辅助数组
         */
        private int[] help;

        /**
         * 集合数量
         */
        private int sets;

        /**
         * 构造有n个元素的并查集
         *
         * @param n 并查集元素个数
         */
        public UnionFind(int n) {
            parent = new int[n];
            help = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
            sets = n;
        }

        private int find(int i) {
            int helpIndex = 0;
            while (i != parent[i]) {
                help[helpIndex++] = i;
                i = parent[i];
            }
            for (int h = --helpIndex; h > 0; h--) {
                parent[help[h]] = i;
            }
            return i;
        }

        private void union(int i, int j) {
            int iRoot = find(i);
            int jRoot = find(j);
            if (iRoot != jRoot) {
                if (iRoot <= jRoot) {
                    parent[jRoot] = iRoot;
                } else {
                    parent[iRoot] = j;
                }
                sets--;
            }
        }
    }

}
