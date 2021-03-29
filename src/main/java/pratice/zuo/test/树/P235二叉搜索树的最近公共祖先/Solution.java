package pratice.zuo.test.树.P235二叉搜索树的最近公共祖先;

/**
 * @author by catface
 * @date 2021/3/29 8:09 下午
 */
public class Solution {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode minNode = p.val < q.val ? p : q;
        TreeNode maxNode = minNode == p ? q : p;
        return process(root, minNode, maxNode);
    }

    /**
     * 充分利用搜索二叉树的特性,判断pq是在左侧子树还是右侧子树还是各在左右
     *
     * @param x       当前节点
     * @param minNode 值小的节点
     * @param maxNode 值大的节点
     * @return 最近的祖先
     */
    private TreeNode process(TreeNode x, TreeNode minNode, TreeNode maxNode) {
        if (x == null) {
            return null;
        }

        if ((minNode.val <= x.val && x.val < maxNode.val) ||
            (minNode.val < x.val && x.val <= maxNode.val)) {
            return x;
        }

        if (minNode.val > x.val) {
            return process(x.right, minNode, maxNode);
        }

        if (maxNode.val < x.val) {
            return process(x.left, minNode, maxNode);
        }

        return null;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) { val = x; }
    }
}
