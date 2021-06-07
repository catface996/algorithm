package com.algorithm.leetcode.树.平衡二叉树;

/**
 * @author by catface
 * @date 2021/3/25 11:05 上午
 */
public class BalanceBinaryTree {

    public static Info process(Node x) {
        if (x == null) {
            return new Info(0, true);
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);

        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean balanced = true;
        if (!leftInfo.balanced) {
            balanced = false;
        }
        if (!rightInfo.balanced) {
            balanced = false;
        }
        if (Math.abs(leftInfo.height - rightInfo.height) > 1) {
            balanced = false;
        }
        return new Info(height, balanced);
    }

    public static boolean isBalanced1(Node head) {
        boolean[] ans = new boolean[1];
        ans[0] = true;
        process1(head, ans);
        return ans[0];
    }

    public static int process1(Node head, boolean[] ans) {
        if (!ans[0] || head == null) {
            return -1;
        }
        int leftHeight = process1(head.left, ans);
        int rightHeight = process1(head.right, ans);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            ans[0] = false;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int)(Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isBalanced1(head) != isBalanceBinaryTree(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

    public static boolean isBalanceBinaryTree(Node head) {
        if (head == null) {
            return true;
        }
        return process(head).balanced;
    }

    public static class Info {

        private int height;
        private boolean balanced;

        public Info(int height, boolean balanced) {
            this.height = height;
            this.balanced = balanced;
        }
    }

    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }
}
