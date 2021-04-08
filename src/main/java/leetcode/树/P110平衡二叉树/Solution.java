package leetcode.树.P110平衡二叉树;

/**
 * @author by catface
 * @date 2021/3/28 5:29 下午
 */
public class Solution {

    public boolean isBalanced(TreeNode root) {
        return process(root).balanceTree;
    }

    private Info process(TreeNode x) {
        if (x == null) {
            return new Info(true, 0);
        }
        Info leftInfo = process(x.left);
        if (!leftInfo.balanceTree) {
            return new Info(false, 0);
        }
        Info rightInfo = process(x.right);
        if (!rightInfo.balanceTree) {
            return new Info(false, 0);
        }
        if (Math.abs(leftInfo.height - rightInfo.height) <= 1) {
            int height = Math.max(leftInfo.height, rightInfo.height) + 1;
            return new Info(true, height);
        }

        return new Info(false, 0);
    }

    public static class Info {
        boolean balanceTree;
        int height;

        public Info(boolean balanceTree, int height) {
            this.balanceTree = balanceTree;
            this.height = height;
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
