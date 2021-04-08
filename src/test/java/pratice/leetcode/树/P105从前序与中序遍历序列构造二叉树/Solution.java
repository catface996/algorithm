package pratice.leetcode.树.P105从前序与中序遍历序列构造二叉树;

/**
 * @author by catface
 * @date 2021/3/30 1:45 下午
 */
public class Solution {

    //TODO 未完成

    public TreeNode buildTree(int[] preorder, int[] inorder) {

        return null;
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
