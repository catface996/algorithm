package com.algorithm.question.class6.class6_最大异或和子数组.code;

import com.algorithm.util.ArrayUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/6/3 10:14 上午
 */
@Slf4j
public class Solution {

    Node root;

    public int maxSubArrXor(int[] arr) {
        int[] pre = preXor(arr);
        root = new Node();
        int maxSubArrXor = Integer.MIN_VALUE;
        // 首先添加0前缀树中,代表一个元素都没有的情况下的异或前缀和
        addAndChoose(0);
        for (int j : pre) {
            maxSubArrXor = Math.max(maxSubArrXor, addAndChoose(j));
        }
        return maxSubArrXor;
    }

    public int[] preXor(int[] arr) {
        int[] pre = new int[arr.length];
        pre[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            pre[i] = pre[i - 1] ^ arr[i];
        }
        return pre;
    }

    public int addAndChoose(int num) {
        Node cur = root;
        Node choose = root;
        // 首位是符号位,符号位取相同的,异或结果为0,异或和最大
        int bit = (num >> 31) & 1;
        int chooseBit = bit;
        cur = add(cur, bit);
        choose = choose(choose, chooseBit);

        for (int i = 30; i >= 0; i--) {
            bit = (num >> i) & 1;
            chooseBit = bit ^ 1;
            cur = add(cur, bit);
            choose = choose(choose, chooseBit);
        }

        cur.end = num;
        return choose.end ^ num;
    }

    private Node add(Node cur, int bit) {
        if (cur.next[bit] == null) {
            cur.next[bit] = new Node();
        }
        return cur.next[bit];
    }

    private Node choose(Node choose, int chooseBit) {
        if (choose.next[chooseBit] != null) {
            return choose.next[chooseBit];
        }
        return choose.next[chooseBit ^ 1];
    }

    public static class Node {
        private int end;
        private Node[] next = new Node[2];
    }

    public static class TestClass {
        @Test
        public void test1() {
            int[] arr = {1, 2, 3, 4, 5, 6};
            Solution solution = new Solution();
            int ans = solution.maxSubArrXor(arr);
            log.info("ans:{}", ans);
        }

        @Test
        public void testForce() {
            Solution solution = new Solution();
            SolutionForce solutionForce = new SolutionForce();
            for (int i = 0; i < 1000; i++) {
                // 数组中允许出现负数,负数是补码形式,除符号位外,其他规则一致.
                int[] arr = ArrayUtil.randomIntArray(10, 1, 20, true);
                int ans1 = solution.maxSubArrXor(arr);
                int ans2 = solutionForce.maxSubArrXor(arr);
                int ans3 = Code01_MaxXOR.maxXorSubarray1(arr);
                if (ans1 != ans2 || ans2 != ans3) {
                    log.info("ans1:{},ans2:{},ans3:{},arr:{}", ans1, ans2, ans3, arr);
                }
            }
            log.info("Good,you success!");
        }

        @Test
        public void testPrintBinary() {
            int num = -5;
            System.out.println(Integer.toBinaryString(num));
        }
    }

}
