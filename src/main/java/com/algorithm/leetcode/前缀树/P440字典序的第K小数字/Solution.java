package com.algorithm.leetcode.前缀树.P440字典序的第K小数字;

//给定整数 n 和 k，找到 1 到 n 中字典序第 k 小的数字。
//
// 注意：1 ≤ k ≤ n ≤ 109。
//
// 示例 :
//
//
//输入:
//n: 13   k: 2
//
//输出:
//10
//
//解释:
//字典序的排列是 [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]，所以第二小的数字是 10。
//
// Related Topics 字典树
// 👍 220 👎 0

import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    // 11:37 上午	info
    //					运行失败:
    //					Memory Limit Exceeded
    //					测试用例:4289384
    //					1922239
    //					stdout:
    
    private static final char ZERO = '0';

    public int findKthNumber(int n, int k) {
        Tire tire = new Tire();
        for (int i = 1; i <= n; i++) {
            tire.insert(i + "");
        }
        String num = tire.getTheK(k);
        return Integer.parseInt(num);
    }

    public static class Tire {
        private final Node root;

        public Tire() {
            root = new Node();
        }

        public void insert(String num) {
            Node cur = root;
            cur.pass++;
            for (int i = 0; i < num.length(); i++) {
                int index = num.charAt(i) - ZERO;
                if (cur.next[index] == null) {
                    cur.next[index] = new Node();
                }
                cur = cur.next[index];
                cur.pass++;
            }
            cur.end++;
            cur.num = num;
        }

        public String getTheK(int k) {
            return getTheK(root, k);
        }

        public String getTheK(Node x, int k) {
            if (x.end >= k) {
                return x.num;
            }
            if (x.pass >= k) {
                int pass = x.end;
                for (int i = 0; i < x.next.length; i++) {
                    if (x.next[i] != null) {
                        String ans = getTheK(x.next[i], k - pass);
                        if (ans != null) {
                            return ans;
                        }
                        pass += x.next[i].pass;
                    }
                }
            }
            return null;
        }
    }

    public static class Node {
        private int pass = 0;
        private int end = 0;
        private String num;
        private Node[] next = new Node[10];
    }

    public static class TestClass {

        @Test
        public void test1() {
            Solution solution = new Solution();
            int ans = solution.findKthNumber(15, 7);
            System.out.println(ans);
        }

    }
}
//leetcode submit region end(Prohibit modification and deletion)

