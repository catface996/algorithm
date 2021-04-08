package leetcode.树.P112路径总和;

/**
 * @author by catface
 * @date 2021/3/29 1:58 下午
 */
public class Solution {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        Solution solution = new Solution();
        boolean has = solution.hasPathSum(root, 0);
        System.out.println(has);
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        return process(root, 0, targetSum);
    }

    public boolean process(TreeNode x, int preSum, int targetSum) {
        if (x == null) {
            return false;
        }
        int currentSum = x.val + preSum;
        if (currentSum == targetSum && x.left == null && x.right == null) {
            return true;
        }
        boolean leftResult = process(x.left, currentSum, targetSum);
        if (leftResult) {
            return true;
        }
        return process(x.right, currentSum, targetSum);
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
