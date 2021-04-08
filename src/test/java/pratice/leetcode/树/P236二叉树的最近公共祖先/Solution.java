package pratice.leetcode.树.P236二叉树的最近公共祖先;

/**
 * @author by catface
 * @date 2021/3/29 10:29 上午
 */
public class Solution {
    //TODO 未达到最优解

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return process(root, p, q).lowestAncestor;
    }

    private Info process(TreeNode x, TreeNode p, TreeNode q) {
        if (x == null) {
            return new Info(false, false, null);
        }
        Info leftInfo = process(x.left, p, q);
        if (leftInfo.lowestAncestor != null) {
            return leftInfo;
        }
        Info rightInfo = process(x.right, p, q);
        if (rightInfo.lowestAncestor != null) {
            return rightInfo;
        }
        boolean hasP = leftInfo.hasP || rightInfo.hasP || x == p;
        boolean hasQ = leftInfo.hasQ || rightInfo.hasQ || x == q;
        if (hasP && hasQ) {
            return new Info(true, true, x);
        }

        return new Info(hasP, hasQ, null);
    }

    public static class Info {
        private boolean hasP;
        private boolean hasQ;
        private TreeNode lowestAncestor;

        public Info(boolean hasP, boolean hasQ, TreeNode lowestAncestor) {
            this.hasP = hasP;
            this.hasQ = hasQ;
            this.lowestAncestor = lowestAncestor;
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) { val = x; }
    }

    public static void main(String[] args) {

    }

    public static void test_1() {
        TreeNode treeNode = new TreeNode(3);

    }

}
