package leetcode.树.P100相同的树;

/**
 * @author by catface
 * @date 2021/3/28 2:22 下午
 */
public class Solution {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p != null && q != null && p.val == q.val) {
            boolean leftSame = isSameTree(p.left, q.left);
            if (!leftSame) {
                return false;
            }
            return isSameTree(p.right, q.right);
        }

        return false;
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
