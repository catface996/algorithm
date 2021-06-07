package com.algorithm.course.暴力递归动态规划.汉诺塔;

import org.junit.Test;

/**
 * @author by catface
 * @date 2021/4/12 4:10 下午
 */
public class Solution {

    public void moveLeftToMiddle(int n) {
        if (n == 1) {
            System.out.println(n + " from left to middle");
            return;
        }
        moveLeftToRight(n - 1);
        System.out.println(n + " from left to middle");
        moveRightToMiddle(n - 1);
    }

    public void moveLeftToRight(int n) {
        if (n == 1) {
            System.out.println(n + " from left to right");
            return;
        }
        moveLeftToMiddle(n - 1);
        System.out.println(n + " from left to right");
        moveMiddleToRight(n - 1);
    }

    public void moveMiddleToLeft(int n) {
        if (n == 1) {
            System.out.println(n + " from middle to left");
            return;
        }
        moveMiddleToRight(n - 1);
        System.out.println(n + " from middle to left");
        moveRightToLeft(n - 1);
    }

    public void moveMiddleToRight(int n) {
        if (n == 1) {
            System.out.println(n + " from middle to right");
            return;
        }
        moveMiddleToLeft(n - 1);
        System.out.println(n + " from middle to right");
        moveLeftToRight(n - 1);

    }

    public void moveRightToLeft(int n) {
        if (n == 1) {
            System.out.println(n + " from right to left");
            return;
        }
        moveRightToMiddle(n - 1);
        System.out.println(n + " from right to left");
        moveMiddleToLeft(n - 1);
    }

    public void moveRightToMiddle(int n) {
        if (n == 1) {
            System.out.println(n + " from right to middle");
            return;
        }
        moveRightToLeft(n - 1);
        System.out.println(n + " from right to middle");
        moveLeftToMiddle(n - 1);
    }

    public static class TestClass{
        @Test
        public void test1(){
            Solution solution = new Solution();
            solution.moveLeftToRight(3);
        }
    }
}
