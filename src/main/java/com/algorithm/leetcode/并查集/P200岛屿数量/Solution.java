package com.algorithm.leetcode.并查集.P200岛屿数量;

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
public class Solution {

    //6:15 下午	info
    //				解答成功:
    //				执行耗时:7 ms,击败了9.11% 的Java用户
    //				内存消耗:41.3 MB,击败了5.79% 的Java用户

    public int numIslands(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        // 从左上到右下检查联通
        UnionFind unionFind = new UnionFind(grid, rows, cols);
        // 第一行,从左到右,只检查是否与右侧联通
        for (int j = 1; j < cols; j++) {
            if (grid[0][j] == '1' && grid[0][j - 1] == '1') {
                unionFind.union(new Island(0, j), new Island(0, j - 1));
            }
        }
        // 第一列,从上到下,只检查是否与上侧联通
        for (int i = 1; i < rows; i++) {
            if (grid[i][0] == '1' && grid[i - 1][0] == '1') {
                unionFind.union(new Island(i, 0), new Island(i - 1, 0));
            }
        }
        // 剩余行列,检查是否与右侧,是否与上侧联通
        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (grid[i][j] == '1') {
                    Island iLand = new Island(i, j);
                    //检查上侧
                    if (grid[i - 1][j] == '1') {
                        unionFind.union(iLand, new Island(i - 1, j));
                    }
                    //检查左侧
                    if (grid[i][j - 1] == '1') {
                        unionFind.union(iLand, new Island(i, j - 1));
                    }
                }
            }
        }
        return unionFind.sets;
    }

    public static class Island {
        int row;
        int col;

        public Island(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public static boolean compare(Island iLand, Island jLand) {
            return iLand.row == jLand.row && iLand.col == jLand.col;
        }

    }

    public static class UnionFind {
        private final Island[][] parent;
        private final Island[] help;
        private final int[][] size;
        private int sets;

        public UnionFind(char[][] grid, int rows, int cols) {
            parent = new Island[rows][cols];
            help = new Island[rows * cols];
            size = new int[rows][cols];
            sets = 0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == '1') {
                        parent[i][j] = new Island(i, j);
                        size[i][j] = 1;
                        sets++;
                    }
                }
            }
        }

        private Island find(Island island) {
            int hi = 0;
            while (!Island.compare(island, parent[island.row][island.col])) {
                help[hi++] = island;
                island = parent[island.row][island.col];
            }
            for (int h = --hi; h >= 0; h--) {
                Island hLand = help[h];
                parent[hLand.row][hLand.col] = island;
            }
            return island;
        }

        public void union(Island i, Island j) {
            Island iRoot = find(i);
            Island jRoot = find(j);
            if (!Island.compare(iRoot, jRoot)) {
                if (size[iRoot.row][iRoot.col] >= size[jRoot.row][jRoot.col]) {
                    size[iRoot.row][iRoot.col] += size[jRoot.row][jRoot.col];
                    parent[jRoot.row][jRoot.col] = iRoot;
                } else {
                    size[jRoot.row][jRoot.col] += size[iRoot.row][iRoot.col];
                    parent[iRoot.row][iRoot.col] = jRoot;
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
            Solution solution = new Solution();
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
            Solution solution = new Solution();
            int nums = solution.numIslands(grid);
            System.out.println(nums);
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)

