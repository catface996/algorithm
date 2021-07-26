package com.algorithm.course.前缀树.impl;

/**
 * @author by catface
 * @date 2021/7/4 4:22 下午
 */
public class Main {

    public static void main(String[] args) {

        // Q: 1-n数字字典序第k大
        //描述信息
        //给你一个数字n(n < 1e9), 再给你一个数字k(k < n), 要求你找到1, 2, 3, ... n按照字典序排序后, 第k大的数字;
        //如, n = 15, k = 7;
        //那1 ~ 15按照字典序排序为: 1, 10, 11, 12, 13, 14, 15, 2, 3, 4, 5, 6, 7, 8, 9;
        //则答案为15;

        //Scanner in = new Scanner(System.in);
        //int a = in.nextInt();
        //System.out.println(a);

        // 冒泡

        // 如, n = 15, k = 7;
        //那1 ~ 15按照字典序排序为: 1, 10, 11, 12, 13, 14, 15, 2, 3, 4, 5, 6, 7, 8, 9;

        int[] nums = {1, 10, 11, 12, 13, 14, 15, 2, 3, 4, 5, 6, 7, 8, 9};
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = nums[i] + "";
        }
        Tire tire = new Tire();
        for (int i = 0; i < strs.length; i++) {
            tire.insert(strs[i]);
        }

        String ans = tire.getK(7);
        System.out.println(ans);
    }

    public static class Tire {

        Node root;
        private int k;

        public Tire() {
            root = new Node();
        }

        public void insert(String num) {
            Node cur = root;
            for (int i = 0; i < num.length(); i++) {
                int index = num.charAt(i) - '0';
                cur.pass++;
                if (cur.nexts[index] == null) {
                    cur.nexts[index] = new Node();
                }
                cur.nexts[index].pass++;
                cur = cur.nexts[index];
            }
            cur.end++;
            cur.num = num;
        }

        public String getK(int k) {
            this.k = k;
            return getKString(root);
        }

        public String getKString(Node x) {
            if (x.end >= k - 1) {
                return x.num;
            }
            if (x.pass >= k) {
                for (int i = 0; i < 10; i++) {
                    if (x.nexts[i] != null) {
                        String ans = getKString(x.nexts[i]);
                        if (ans != null) {
                            return ans;
                        }
                    }
                }
            } else {
                k = k - x.pass;
            }
            return null;
        }
    }

    public static class Node {
        private int pass = 0;
        private int end = 0;
        private String num;
        private Node[] nexts = new Node[10];
    }

}