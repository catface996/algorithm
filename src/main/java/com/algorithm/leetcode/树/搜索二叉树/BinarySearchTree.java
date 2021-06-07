package com.algorithm.leetcode.树.搜索二叉树;

import java.util.ArrayList;

/**
 * @author by catface
 * @date 2021/3/25 10:22 上午
 */
public class BinarySearchTree {

    public static boolean isBst(Node head) {
        if (head == null) {
            return true;
        }
        return process(head).bst;
    }

    public static Info process(Node x) {
        if (x == null) {
            return null;
        }
        int max = x.value;
        int min = x.value;
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);

        if (leftInfo != null) {
            max = Math.max(leftInfo.max, max);
            min = Math.min(leftInfo.min, min);
        }
        if (rightInfo != null) {
            max = Math.max(rightInfo.max, max);
            min = Math.min(rightInfo.min, min);
        }
        boolean isBst = true;
        if (leftInfo != null && !leftInfo.bst) {
            isBst = false;
        }
        if (rightInfo != null && !rightInfo.bst) {
            isBst = false;
        }
        if (leftInfo != null && leftInfo.max >= x.value) {
            isBst = false;
        }
        if (rightInfo != null && rightInfo.min <= x.value) {
            isBst = false;
        }
        return new Info(max, min, isBst);
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

    public static boolean isBST1(Node head) {
        if (head == null) {
            return true;
        }
        ArrayList<Node> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return false;
            }
        }
        return true;
    }

    public static void in(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isBST1(head) != isBst(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

    public static class Info {

        private int max;
        private int min;
        private boolean bst;

        public Info(int max, int min, boolean bst) {
            this.max = max;
            this.min = min;
            this.bst = bst;
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
