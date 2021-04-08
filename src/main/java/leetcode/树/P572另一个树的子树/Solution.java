package leetcode.树.P572另一个树的子树;

/**
 * @author by catface
 * @date 2021/4/2 9:46 下午
 */
//给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看
//做它自身的一棵子树。 
//
// 示例 1: 
//给定的树 s: 
//
// 
//     3
//    / \
//   4   5
//  / \
// 1   2
// 
//
// 给定的树 t： 
//
// 
//   4 
//  / \
// 1   2
// 
//
// 返回 true，因为 t 与 s 的一个子树拥有相同的结构和节点值。 
//
// 示例 2: 
//给定的树 s： 
//
// 
//     3
//    / \
//   4   5
//  / \
// 1   2
//    /
//   0
// 
//
// 给定的树 t： 
//
// 
//   4
//  / \
// 1   2
// 
//
// 返回 false。 
// Related Topics 树 
// 👍 471 👎 0

//leetcode submit region begin(Prohibit modification and deletion)

class Solution {

    public boolean isSubtree(TreeNode s, TreeNode t) {
        return process(s, t);
    }

    public boolean process(TreeNode x, TreeNode t) {
        if (x == null) {
            return false;
        }
        // 判断以s树种的x节点开始,是否包含t树
        boolean isParent = isSameNode(x, t);
        if (isParent) {
            return true;
        }
        boolean isLeft = process(x.left, t);
        if (isLeft) {
            return true;
        }
        return process(x.right, t);
    }

    /**
     * 保证
     *
     * @param sNode 可以为空
     * @param tNode 保证传入的tNode 一定是非null
     * @return
     */
    private boolean isSameNode(TreeNode sNode, TreeNode tNode) {
        if (sNode != null && tNode != null) {
            if (sNode.val == tNode.val) {
                if (isSameNode(sNode.left, tNode.left)) {
                    return isSameNode(sNode.right, tNode.right);
                }
                return false;
            }
            return false;
        }
        return sNode == null && tNode == null;
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

    public static class Test2 {
        public static void main(String[] args) {
            // s [3,4,5,1,2,null,null,0] t [4,1,2]
            TreeNode s = new TreeNode(3);
            s.left = new TreeNode(4);
            s.right = new TreeNode(5);
            s.left.left = new TreeNode(1);
            s.left.right = new TreeNode(2);
            s.right.left = null;
            s.right.right = null;
            s.left.left.left = new TreeNode(0);

            TreeNode t = new TreeNode(4);
            t.left = new TreeNode(1);
            t.right = new TreeNode(2);
            Solution solution = new Solution();
            boolean isSub = solution.isSubtree(s, t);
            System.out.println(isSub);
        }
    }

    public static class Test1 {
        public static void main(String[] args) {
            // s [3,4,5,1,2] t [4,1,2]
            TreeNode s = new TreeNode(3);
            s.left = new TreeNode(4);
            s.right = new TreeNode(5);
            s.left.left = new TreeNode(1);
            s.left.right = new TreeNode(2);

            TreeNode t = new TreeNode(4);
            t.left = new TreeNode(1);
            t.right = new TreeNode(2);
            Solution solution = new Solution();
            boolean isSub = solution.isSubtree(s, t);
            System.out.println(isSub);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

