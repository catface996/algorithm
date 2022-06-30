package com.algorithm.course.递归.斐波那契数列;

import org.junit.Test;

public class Fb1 {

    public int fn(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        return fn(n - 1) + fn(n - 2);
    }

    @Test
    public void test1() {
        int ans = fn(4);
        System.out.println(ans);
    }
}
