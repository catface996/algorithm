package com.algorithm.question.class8.贪吃蛇.code;

import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/6/23 11:12 上午
 */
@Slf4j
public class Solution2 {

    int rows;
    int cols;
    int[][] matrix;

    public int eatMaxScore(int[][] matrix, int leftTimes) {
        if (matrix == null || matrix.length <= 0) {
            return 0;
        }
        this.rows = matrix.length;
        this.cols = matrix[0].length;
        this.matrix = matrix;
        int maxScore = Integer.MIN_VALUE;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                maxScore = Math.max(maxScore, process(row, col, leftTimes));
            }
        }
        return maxScore;
    }

    /**
     * 当前算法是不能经过值为负的格子
     *
     * @param row       当前所在行
     * @param col       当前所在列
     * @param leftTimes 剩余使用翻转能力的次数
     * @return 最大得分
     */
    public int process(int row, int col, int leftTimes) {
        // 首先检查是否越界,如果越界,得分为0
        if (row >= rows || row < 0 || col >= cols) {
            return 0;
        }
        // 当前位置为负数,有剩余翻转能力时,出于贪心考虑,使用能力,不使用会死亡,分数不会继续增加.
        if (matrix[row][col] < 0) {
            // 无翻转能力可使用,停止在当前位置,否则,使用翻转能力
            if (leftTimes <= 0) {
                return 0;
            }
            int score = -matrix[row][col];
            int upRightScore = process(row - 1, col + 1, leftTimes - 1);
            int rightScore = process(row, col + 1, leftTimes - 1);
            int downRightScore = process(row + 1, col + 1, leftTimes - 1);
            return score + Math.max(Math.max(upRightScore, rightScore), downRightScore);
        } else {
            // 当前位置不为负数时,不需要使用翻转能力
            int score = matrix[row][col];
            int upRightScore = process(row - 1, col + 1, leftTimes);
            int rightScore = process(row, col + 1, leftTimes);
            int downRightScore = process(row + 1, col + 1, leftTimes);
            return score + Math.max(Math.max(upRightScore, rightScore), downRightScore);
        }
    }

    public static class TestClass {
        @Test
        public void test1() {
            int[][] matrix = {
                {1, 2, 3},
                {2, -1, -4},
                {6, 2, -5}
            };
            int leftTimes = 1;
            Solution2 solution = new Solution2();
            int maxScore = solution.eatMaxScore(matrix, leftTimes);
            log.info("maxScore:{}", maxScore);
        }

        @Test
        public void testForce() {
            int N = 7;
            int M = 7;
            int V = 10;
            int times = 1000000;
            Solution2 solution = new Solution2();
            for (int i = 0; i < times; i++) {
                int r = (int)(Math.random() * (N + 1));
                int c = (int)(Math.random() * (M + 1));
                int[][] matrix = Code04_SnakeGame.generateRandomArray(r, c, V);
                int ans1 = solution.eatMaxScore(matrix, 1);
                int ans2 = Code04_SnakeGame.walk2(matrix);
                if (ans1 != ans2) {
                    for (int j = 0; j < matrix.length; j++) {
                        System.out.println(Arrays.toString(matrix[j]));
                    }
                    System.out.println("Oops   ans1: " + ans1 + "   ans2:" + ans2);
                    break;
                }
            }
            System.out.println("finish");
        }
    }

}
