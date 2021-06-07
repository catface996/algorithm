package com.algorithm.leetcode.树.P226翻转二叉树;

/**
 * @author by catface
 * @date 2021/3/30 10:21 上午
 */
public class Solution {

    public TreeNode invertTree(TreeNode root) {
        process(root);
        return root;
    }

    private void process(TreeNode x) {
        if (x == null) {
            return;
        }

        TreeNode tempNode = x.left;
        x.left = x.right;
        x.right = tempNode;

        process(x.left);
        process(x.right);
    }

    public class TreeNode {
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
