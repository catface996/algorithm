package com.algorithm.leetcode.树.P99恢复二叉搜索树;

/**
 * @author by catface
 * @date 2021/3/26 11:56 下午
 */
public class Solution {

    public void recoverTree(TreeNode root) {

    }

    private void process(TreeNode x) {
        if (x == null) {
            return;
        }

        TreeNode leftMaxNode = getMaxNode(x.left);
        TreeNode rightMinNode = getMinNode(x.right);
        if (leftMaxNode == null && rightMinNode == null) {
            return;
        }

        if (leftMaxNode != null && rightMinNode == null) {
            if (x.val > leftMaxNode.val) {
                process(x.left);
            } else {

            }
        }

        if (leftMaxNode == null && rightMinNode != null) {

        }

        if (leftMaxNode != null && rightMinNode != null) {

        }
        if (leftMaxNode.val < x.val && x.val < rightMinNode.val) {
            process(x.left);
            process(x.right);
        } else {
            int max = x.val;
            max = max > leftMaxNode.val ? max : leftMaxNode.val;
            max = max > rightMinNode.val ? max : rightMinNode.val;

        }

    }

    private TreeNode getMaxNode(TreeNode x) {
        if (x == null) {
            return null;
        }
        TreeNode maxNode = x;
        TreeNode leftMaxNode = getMaxNode(x.left);
        TreeNode rightMaxNode = getMaxNode(x.right);
        if (leftMaxNode != null) {
            maxNode = maxNode.val > leftMaxNode.val ? maxNode : leftMaxNode;
        }
        if (rightMaxNode != null) {
            maxNode = maxNode.val > rightMaxNode.val ? maxNode : rightMaxNode;
        }
        return maxNode;
    }

    private TreeNode getMinNode(TreeNode x) {
        if (x == null) {
            return null;
        }
        TreeNode minNode = x;
        TreeNode leftMinNode = getMaxNode(x.left);
        TreeNode rightMinNode = getMaxNode(x.right);
        if (leftMinNode != null) {
            minNode = minNode.val < leftMinNode.val ? minNode : leftMinNode;
        }
        if (rightMinNode != null) {
            minNode = minNode.val < rightMinNode.val ? minNode : rightMinNode;
        }
        return minNode;
    }

    public static class MaxMin {

        private TreeNode minNode;

        private TreeNode maxNode;

        public TreeNode getMinNode() {
            return minNode;
        }

        public void setMinNode(TreeNode minNode) {
            this.minNode = minNode;
        }

        public TreeNode getMaxNode() {
            return maxNode;
        }

        public void setMaxNode(TreeNode maxNode) {
            this.maxNode = maxNode;
        }
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
