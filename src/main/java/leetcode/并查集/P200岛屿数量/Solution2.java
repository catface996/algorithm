package leetcode.并查集.P200岛屿数量;

//给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
//
// 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
//
// 此外，你可以假设该网格的四条边均被水包围。
//
//
//
// 示例 1：
//
//
//输入：grid = [
//  ['1','1','1','1','0'],
//  ['1','1','0','1','0'],
//  ['1','1','0','0','0'],
//  ['0','0','0','0','0']
//]
//输出：1
//
//
// 示例 2：
//
//
//输入：grid = [
//  ['1','1','0','0','0'],
//  ['1','1','0','0','0'],
//  ['0','0','1','0','0'],
//  ['0','0','0','1','1']
//]
//输出：3
//
//
//
//
// 提示：
//
//
// m == grid.length
// n == grid[i].length
// 1 <= m, n <= 300
// grid[i][j] 的值为 '0' 或 '1'
//
// Related Topics 深度优先搜索 广度优先搜索 并查集
// 👍 1110 👎 0

import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
public class Solution2 {

    //7:44 下午	info
    //				解答成功:
    //				执行耗时:7 ms,击败了9.11% 的Java用户
    //				内存消耗:41 MB,击败了50.22% 的Java用户

    public int numIslands(char[][] grid) {
        // 从左上到右下检查联通
        UnionFind unionFind = new UnionFind(grid);
        // 第一行,从左到右,只检查是否与右侧联通
        for (int j = 1; j < unionFind.cols; j++) {
            if (grid[0][j] == '1' && grid[0][j - 1] == '1') {
                unionFind.union(unionFind.calculateIndex(0, j), unionFind.calculateIndex(0, j - 1));
            }
        }
        // 第一列,从上到下,只检查是否与上侧联通
        for (int i = 1; i < unionFind.rows; i++) {
            if (grid[i][0] == '1' && grid[i - 1][0] == '1') {
                unionFind.union(unionFind.calculateIndex(i, 0), unionFind.calculateIndex(i - 1, 0));
            }
        }
        // 剩余行列,检查是否与右侧,是否与上侧联通
        for (int i = 1; i < unionFind.rows; i++) {
            for (int j = 1; j < unionFind.cols; j++) {
                if (grid[i][j] == '1') {
                    //检查上侧
                    if (grid[i - 1][j] == '1') {
                        unionFind.union(unionFind.calculateIndex(i - 1, j), unionFind.calculateIndex(i, j));
                    }
                    //检查左侧
                    if (grid[i][j - 1] == '1') {
                        unionFind.union(unionFind.calculateIndex(i, j - 1), unionFind.calculateIndex(i, j));
                    }
                }
            }
        }
        return unionFind.sets;
    }

    public static class UnionFind {
        private final int[] parent;
        private final int[] help;
        private final int rows;
        private final int cols;
        private int sets;

        public UnionFind(char[][] grid) {
            rows = grid.length;
            cols = grid[0].length;
            int totalSize = rows * cols;
            parent = new int[totalSize];
            help = new int[totalSize];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == '1') {
                        int globalIndex = calculateIndex(i, j);
                        parent[globalIndex] = globalIndex;
                        sets++;
                    }
                }
            }
        }

        private int calculateIndex(int i, int j) {
            return i * cols + j;
        }

        /**
         * 查找指定节点的代表节点
         *
         * @param globalIndex 全局下标
         * @return 代表当前节点的全局下标
         */
        private int find(int globalIndex) {
            int hIndex = 0;
            while (globalIndex != parent[globalIndex]) {
                help[hIndex++] = globalIndex;
                globalIndex = parent[globalIndex];
            }
            for (int h = --hIndex; h >= 0; h--) {
                parent[help[h]] = globalIndex;
            }
            return globalIndex;
        }

        /**
         * 合并i和j两个节点
         *
         * @param globalI 代表i节点的全局索引
         * @param globalJ 代表j节点的全局索引
         */
        public void union(int globalI, int globalJ) {
            int iRoot = find(globalI);
            int jRoot = find(globalJ);
            if (iRoot != jRoot) {
                if (iRoot <= jRoot) {
                    parent[jRoot] = iRoot;
                } else {
                    parent[iRoot] = jRoot;
                }
                sets--;
            }
        }
    }

    public static class TestClass {
        @Test
        public void test1() {
            char[][] grid = new char[][]
                {
                    {'1', '1', '0', '0', '0'},
                    {'1', '1', '0', '0', '0'},
                    {'0', '0', '1', '0', '0'},
                    {'0', '0', '0', '1', '1'}
                };
            Solution2 solution = new Solution2();
            int nums = solution.numIslands(grid);
            System.out.println(nums);
        }

        @Test
        public void test2() {
            char[][] grid = {
                {'1', '1', '1', '1', '1', '0', '1', '1', '1', '1'},
                {'1', '0', '1', '0', '1', '1', '1', '1', '1', '1'},
                {'0', '1', '1', '1', '0', '1', '1', '1', '1', '1'},
                {'1', '1', '0', '1', '1', '0', '0', '0', '0', '1'},
                {'1', '0', '1', '0', '1', '0', '0', '1', '0', '1'},
                {'1', '0', '0', '1', '1', '1', '0', '1', '0', '0'},
                {'0', '0', '1', '0', '0', '1', '1', '1', '1', '0'},
                {'1', '0', '1', '1', '1', '0', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '0', '1'},
                {'1', '0', '1', '1', '1', '1', '1', '1', '1', '0'}
            };
            Solution2 solution = new Solution2();
            int nums = solution.numIslands(grid);
            System.out.println(nums);
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)

