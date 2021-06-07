package com.algorithm.leetcode.树.P116填充每个节点的下一右节点指针;

/**
 * @author by catface
 * @date 2021/3/30 2:17 下午
 */
public class Solution2 {

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        Solution2 solution2 = new Solution2();
        solution2.connect(root);
    }

    /**
     * 采用先序遍历,
     * <p>
     * 如果有左孩子和右孩子,把左孩子指向右孩子,
     * <p>
     * 如果左孩子有右孩子,把左孩子的右孩子,指向 右孩子的左孩子
     *
     * @param root 根节点
     * @return
     */
    public Node connect(Node root) {
        process(root);
        return root;
    }

    private void process(Node x) {
        if (x == null) {
            return;
        }
        Node left = x.left;
        Node right = x.right;
        while (left != null) {
            left.next = right;
            left = left.right;
            right = right.left;
        }
        process(x.left);
        process(x.right);
    }

    public static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}
