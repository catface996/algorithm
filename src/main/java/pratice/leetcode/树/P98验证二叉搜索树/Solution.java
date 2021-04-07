package pratice.leetcode.树.P98验证二叉搜索树;

/**
 * @author by catface
 * @date 2021/3/26 9:44 下午
 */
public class Solution {

    public boolean isValidBST(TreeNode root) {
        return process(root).binarySearchTree;
    }

    private Info process(TreeNode x) {
        if (x == null) {
            return null;
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        int max = x.val;
        int min = x.val;
        boolean binarySearchTree = true;
        if (leftInfo != null) {
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
            if (!leftInfo.binarySearchTree || leftInfo.max >= x.val) {
                binarySearchTree = false;
            }
        }
        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
            if (!rightInfo.binarySearchTree || rightInfo.min <= x.val) {
                binarySearchTree = false;
            }
        }
        return new Info(max, min, binarySearchTree);
    }

    public static class Info {
        int max;
        int min;
        boolean binarySearchTree;

        public Info(int max, int min, boolean binarySearchTree) {
            this.max = max;
            this.min = min;
            this.binarySearchTree = binarySearchTree;
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
