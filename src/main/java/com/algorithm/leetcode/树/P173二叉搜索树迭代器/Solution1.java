package com.algorithm.leetcode.树.P173二叉搜索树迭代器;

/**
 * @author by catface
 * @date 2021/3/29 8:48 下午
 */
public class Solution1 {

    public static void main(String[] args) {
        BSTIterator.TreeNode root = new BSTIterator.TreeNode(4);
        root.left = new BSTIterator.TreeNode(2);
        root.left.left = new BSTIterator.TreeNode(1);
        root.left.right = new BSTIterator.TreeNode(3);
        root.right = new BSTIterator.TreeNode(6);
        root.right.left = new BSTIterator.TreeNode(5);
        root.right.right = new BSTIterator.TreeNode(7);
        Solution1.BSTIterator bstIterator = new Solution1.BSTIterator(root);
        while (bstIterator.hasNext()) {
            System.out.println(bstIterator.next());
        }
    }

    /**
     * 空间复杂度最低
     */
    public static class BSTIterator {
        private TreeNode root;

        public BSTIterator(TreeNode root) {
            this.root = root;
        }

        public int next() {
            if (root == null) {
                return -1;
            }
            return process(root).val;
        }

        public boolean hasNext() {
            return root != null;
        }

        private TreeNode process(TreeNode x) {

            if (x == root && x.left == null) {
                this.root = root.right;
                return x;
            }
            if (x.left.left == null && x.left.right == null) {
                TreeNode ans = x.left;
                x.left = null;
                return ans;
            }

            if (x.left.left == null) {
                TreeNode ans = x.left;
                x.left = x.left.right;
                return ans;
            }

            return process(x.left);
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
