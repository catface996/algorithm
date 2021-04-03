package pratice.zuo.test.树.P617合并二叉树;

//给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
//
// 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点
//。
//
// 示例 1:
//
//
//输入:
//	Tree 1                     Tree 2
//          1                         2
//         / \                       / \
//        3   2                     1   3
//       /                           \   \
//      5                             4   7
//输出:
//合并后的树:
//	     3
//	    / \
//	   4   5
//	  / \   \
//	 5   4   7
//
//
// 注意: 合并必须从两个树的根节点开始。
// Related Topics 树
// 👍 652 👎 0

//leetcode submit region begin(Prohibit modification and deletion)

class Solution {

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        process(root1, root2);
        return root1;
    }

    private void process(TreeNode node1, TreeNode node2) {
        /*
        将第二棵树合并到第一棵树
         */
        // 第一课树已经遍历到根节点
        if (node1 == null) {
            return;
        }

        if (node2 == null) {
            return;
        }

        if (node1.left != null) {
            process(node1.left, node2.left);
        }
        if (node1.right != null) {
            process(node1.right, node2.right);
        }

        node1.val += node2.val;

        if (node1.left == null && node2.left != null) {
            node1.left = node2.left;
        }
        if (node1.right == null && node2.right != null) {
            node1.right = node2.right;
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
//leetcode submit region end(Prohibit modification and deletion)

