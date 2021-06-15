package com.algorithm.leetcode.剑指.剑指Offer12矩阵中的路径;

//给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
//
//
//
// 例如，在下面的 3×4 的矩阵中包含单词 "ABCCED"（单词中的字母已标出）。
//
//
//
//
//
// 示例 1：
//
//
//输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "AB
//CCED"
//输出：true
//
//
// 示例 2：
//
//
//输入：board = [["a","b"],["c","d"]], word = "abcd"
//输出：false
//
//
//
//
// 提示：
//
//
// 1 <= board.length <= 200
// 1 <= board[i].length <= 200
// board 和 word 仅由大小写英文字母组成
//
//
//
//
// 注意：本题与主站 79 题相同：https://leetcode-cn.com/problems/word-search/
// Related Topics 深度优先搜索
// 👍 336 👎 0

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
@Slf4j
public class Solution {

    // 1:46 下午	info
    //				解答成功:
    //				执行耗时:193 ms,击败了5.05% 的Java用户
    //				内存消耗:40.1 MB,击败了75.28% 的Java用户

    int rows;
    int cols;
    int wordLength;
    char[][] board;
    String word;

    public boolean exist(char[][] board, String word) {
        rows = board.length;
        cols = board[0].length;
        wordLength = word.length();
        this.board = board;
        this.word = word;
        for (int x = 0; x < cols; x++) {
            for (int y = 0; y < rows; y++) {
                boolean[][] mark = new boolean[rows][cols];
                if (process(x, y, 0, mark)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 从(x,y)位置开始触发,能否走出word字符
     *
     * @param startX   开始位置的x坐标
     * @param startY   开始位置的y坐标
     * @param curIndex 当前待决策的字符
     * @param mark     已经走过的路径
     * @return true:可以走出;false:无法走出;
     */
    public boolean process(int startX, int startY, int curIndex, boolean[][] mark) {
        if (curIndex >= wordLength) {
            // 已经走完word字符,说明可以走出
            return true;
        }

        // 走出边界
        if (startX < 0 || startX >= cols || startY < 0 || startY >= rows) {
            return false;
        }

        // 检查当前位置的字符是否与矩阵中的字符一致,如果一致,标记,并像上下左右尝试
        if (board[startY][startX] != word.charAt(curIndex)) {
            return false;
        }

        // 检查是否重复走过
        if (mark[startY][startX]) {
            return false;
        }

        // 标记
        mark[startY][startX] = true;
        curIndex++;
        // 向左
        if (process(startX - 1, startY, curIndex, mark)) {
            return true;
        }
        // 向右
        if (process(startX + 1, startY, curIndex, mark)) {
            return true;
        }
        // 向上
        if (process(startX, startY - 1, curIndex, mark)) {
            return true;
        }
        // 向下
        if (process(startX, startY + 1, curIndex, mark)) {
            return true;
        }

        // 还原现场
        mark[startY][startX] = false;
        return false;
    }

    public static class TestClass {
        // 示例 1：
        //输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
        //输出：true
        @Test
        public void test1() {
            char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
            };
            String word = "ABCCED";
            Solution solution = new Solution();
            boolean ans = solution.exist(board, word);
            log.info("ans:{}", ans);
        }

        // 示例 2：
        //输入：board = [["a","b"],["c","d"]], word = "abcd"
        //输出：false
        @Test
        public void test2() {
            char[][] board = {
                {'a', 'b'},
                {'c', 'd'}
            };
            String word = "abcd";
            Solution solution = new Solution();
            boolean ans = solution.exist(board, word);
            log.info("ans:{}", ans);
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)

