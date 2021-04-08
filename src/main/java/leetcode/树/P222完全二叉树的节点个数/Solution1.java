package leetcode.树.P222完全二叉树的节点个数;

/**
 * @author by catface
 * @date 2021/3/29 10:31 下午
 */
public class Solution1 {

    public int countNodes(TreeNode root) {
        return process(root);
    }

    private int process(TreeNode x) {
        if (x == null) {
            return 0;
        }
        int leftNum = process(x.left);
        int rightNum = process(x.right);
        return leftNum + rightNum + 1;
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
