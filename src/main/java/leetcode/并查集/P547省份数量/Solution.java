package leetcode.并查集.P547省份数量;

/**
 * @author by catface
 * @date 2021/4/19 1:57 下午
 */
public class Solution {

    //3:22 下午	info
    //				解答成功:
    //				执行耗时:1 ms,击败了100.00% 的Java用户
    //				内存消耗:39.4 MB,击败了56.13% 的Java用户

    public static int findCircleNum(int[][] isConnected) {
        if (isConnected == null || isConnected.length <= 0) {
            return 0;
        }
        int N = isConnected.length;
        UnionFind unionFind = new UnionFind(N);
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (isConnected[i][j] == 1) {
                    // i和j互相认识
                    unionFind.union(i, j);
                }
            }
        }
        return unionFind.getSets();
    }

    public static class UnionFind {
        /**
         * parent[i]=k,表达的含义是,k是i的父亲
         */
        private int[] parent;
        /**
         * 辅助结构
         */
        private int[] help;
        /**
         * 一共有多少个集合
         */
        private int sets;

        public UnionFind(int n) {
            parent = new int[n];
            help = new int[n];
            sets = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        /**
         * 查找某一节点的最父级节点
         * <p>
         * 产生副作用,沿途经过的所有父节点,均改变最父级节点
         *
         * @param i 节点i
         * @return 节点i的最父级节点
         */
        private int find(int i) {
            int hi = 0;
            // 查找当前节点的最父级节点,并记录经过的所有父级接地那
            while (i != parent[i]) {
                help[hi++] = i;
                i = parent[i];
            }
            // 将所有经过的父级节点的parent均设置为最父级节点
            for (hi--; hi >= 0; hi--) {
                parent[help[hi]] = i;
            }
            return i;
        }

        public void union(int i, int j) {
            int iRootParent = find(i);
            int jRootParent = find(j);
            // 两相邻节点的最父级节点不是同一个,表明两相邻节点不在同一个集合
            if (iRootParent != jRootParent) {
                if (iRootParent <= jRootParent) {
                    parent[jRootParent] = iRootParent;
                } else {
                    parent[iRootParent] = jRootParent;
                }
                sets--;
            }
        }

        public int getSets() {
            return sets;
        }
    }
}
