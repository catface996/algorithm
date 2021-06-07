package com.algorithm.leetcode.树.P173二叉搜索树迭代器;

import java.util.LinkedList;
import java.util.Queue;

import com.algorithm.leetcode.树.P173二叉搜索树迭代器.Solution2.BSTIterator.TreeNode;

/**
 * @author by catface
 * @date 2021/3/29 8:48 下午
 */
public class Solution2 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right = new TreeNode(6);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        Solution2.BSTIterator bstIterator = new Solution2.BSTIterator(root);
        while (bstIterator.hasNext()) {
            System.out.println(bstIterator.next());
        }
    }

    /**
     * 时间复杂度最低
     */
    public static class BSTIterator {

        private Queue<Integer> queue;

        public BSTIterator(TreeNode root) {
            this.queue = new LinkedList<>();
            process(root, queue);
        }

        private void process(TreeNode x, Queue<Integer> queue) {
            if (x == null) {
                return;
            }
            process(x.left, queue);
            queue.add(x.val);
            process(x.right, queue);
        }

        public int next() {
            if (!queue.isEmpty()) {
                return queue.poll();
            }
            return -1;
        }

        public boolean hasNext() {
            return !queue.isEmpty();
        }

        public static class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;

            TreeNode() {}

            TreeNode(int val) { this.val = val; }

            TreeNode(int val, TreeNode left, TreeNode right) {
                this.val = val;
                this.left = left;
                this.right = right;
            }
        }

    }

}
