package com.algorithm.question.class7.非负数组中任意两个数字按位与的最大值.code;

import com.algorithm.util.ArrayUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/6/4 4:44 下午
 */
@Slf4j
public class Solution {

    //TODO 非有效解

    public int maxAnd(int[] arr) {
        Node root = new Node();
        add(root, arr[0]);
        int ans = 0;
        for (int i = 1; i < arr.length; i++) {
            ans = Math.max(ans, addAnd(root, arr[i]));
        }
        return ans;
    }

    /**
     * 前缀树无法完成&的最右解
     * <p>
     * 当当前数字的i位的值为1时,最右选择是走1的路径,如果没有1的路径,走0的路径,都是唯一的
     * <p>
     * 当当前数字的i位置为0时,既可以选择走1路径,也可以选择走0路径,复杂度超越O(N)
     *
     * @param root
     * @param num
     * @return
     */
    public int addAnd(Node root, int num) {
        Node choose = root;
        Node cur = root;
        int bit;
        for (int bitPosition = 30; bitPosition >= 0; bitPosition--) {
            bit = (num >> bitPosition) & 1;
            if (choose.next[bit] == null) {
                choose = choose.next[(bit ^ 1)];
            } else {
                choose = choose.next[bit];
            }
            if (cur.next[bit] == null) {
                cur.next[bit] = new Node();
            }
            cur = cur.next[bit];
        }
        cur.end = num;
        return choose.end & num;
    }

    public void add(Node root, int num) {
        Node cur = root;
        int bit;
        for (int bitPosition = 30; bitPosition >= 0; bitPosition--) {
            bit = (num >> bitPosition) & 1;
            if (cur.next[bit] == null) {
                cur.next[bit] = new Node();
            }
            cur = cur.next[bit];
        }
        cur.end = num;
    }

    public static class Node {
        private Node[] next = new Node[2];
        private int end;
        private int pass;
    }

    public static class TestClass {

        @Test
        public void test1() {
            Solution solution = new Solution();
            SolutionForce solutionForce = new SolutionForce();
            int[] arr = {2, 7, 5, 13, 8, 10, 9, 17, 4, 5};
            int ans = solution.maxAnd(arr);
            int ans3 = solutionForce.maxAnd(arr);
            int ans2 = Code01_MaxAndValue.maxAndValue2(arr);
            log.info("ans:{},ans2:{},ans3:{},,arr:{}", ans, ans2, ans3, arr);
        }

        @Test
        public void testForce() {
            for (int i = 0; i < 10000; i++) {
                int[] arr = ArrayUtil.randomIntArray(10, 1, 20);
                Solution solution = new Solution();
                int ans = solution.maxAnd(arr);
                int ans2 = Code01_MaxAndValue.maxAndValue2(arr);
                if (ans != ans2) {
                    log.info("ans:{},ans2:{},arr:{}", ans, ans2, arr);
                }
            }
        }

        @Test
        public void testForce2() {
            Solution solution = new Solution();
            for (int i = 0; i < 10000; i++) {
                int[] arr = ArrayUtil.randomIntArray(10, 1, 20);
                int[] arr2 = ArrayUtil.clone(arr);
                int ans = solution.maxAnd(arr);
                int ans2 = Code01_MaxAndValue.maxAndValue2(arr2);
                if (ans != ans2) {
                    log.info("first ans:{},ans2:{},arr:{}", ans, ans2, arr);
                }
            }
        }
    }
}
