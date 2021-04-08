//package pratice.leetcode.practice.树.P572另一个树的子树;
//
///**
// * @author by catface
// * @date 2021/4/2 9:46 下午
// */
////给定两个非空二叉树 s 和 t，检验 s 中是否包含和 t 具有相同结构和节点值的子树。s 的一个子树包括 s 的一个节点和这个节点的所有子孙。s 也可以看
////做它自身的一棵子树。
////
//// 示例 1:
////给定的树 s:
////
////
////     3
////    / \
////   4   5
////  / \
//// 1   2
////
////
//// 给定的树 t：
////
////
////   4
////  / \
//// 1   2
////
////
//// 返回 true，因为 t 与 s 的一个子树拥有相同的结构和节点值。
////
//// 示例 2:
////给定的树 s：
////
////
////     3
////    / \
////   4   5
////  / \
//// 1   2
////    /
////   0
////
////
//// 给定的树 t：
////
////
////   4
////  / \
//// 1   2
////
////
//// 返回 false。
//// Related Topics 树
//// 👍 471 👎 0
//
////leetcode submit region begin(Prohibit modification and deletion)
//
//class Solution2 {
//
//    int tMaxIndex = 0;
//    int[] tArr = null;
//
//    public boolean isSubtree(TreeNode s, TreeNode t) {
//        convertToArray(t);
//        return process(s);
//    }
//
//    public boolean process(TreeNode x) {
//        if (isSameNode(x, 0)) {
//            return true;
//        }
//        if (isSameNode(x.left, 0)) {
//            return true;
//        }
//        if (isSameNode(x.right, 0)) {
//            return true;
//        }
//        return false;
//    }
//
//    private boolean isSameNode(TreeNode subRoot, int start,int compareNum) {
//        if (start > tMaxIndex) {
//            return false;
//        }
//        compareNum++;
//        if (subRoot.val == tArr[start]) {
//            if (isSameNode(subRoot.left, (start << 1) + 1,compareNum)) {
//                return isSameNode(subRoot.right, (start << 1) + 2,compareNum);
//            }
//            return false;
//        }
//        return false;
//    }
//
//    /**
//     * 后续遍历,到
//     *
//     * @return
//     */
//    public void convertToArray(TreeNode tNode) {
//        tMaxIndex = getMaxIndex(tNode, 0);
//        tArr = new int[tMaxIndex + 1];
//        putNodeInArray(tNode, 0);
//    }
//
//    public int getMaxIndex(TreeNode tNode, int start) {
//        if (tNode == null) {
//            return start;
//        }
//        int max = start;
//        if (tNode.left != null) {
//            max = getMaxIndex(tNode.left, (start << 1) + 1);
//        }
//        if (tNode.right != null) {
//            max = Math.max(max, getMaxIndex(tNode.right, (start << 1) + 2));
//        }
//        return max;
//    }
//
//    public void putNodeInArray(TreeNode tNode, int start) {
//        if (tNode == null) {
//            return;
//        }
//        tArr[start] = tNode.val;
//        putNodeInArray(tNode.left, (start << 1) + 1);
//        putNodeInArray(tNode.right, (start << 1) + 2);
//    }
//
//    public static class TreeNode {
//        int val;
//        TreeNode left;
//        TreeNode right;
//
//        TreeNode() {}
//
//        TreeNode(int val) { this.val = val; }
//
//        TreeNode(int val, TreeNode left, TreeNode right) {
//            this.val = val;
//            this.left = left;
//            this.right = right;
//        }
//    }
//
//    public static class Test2 {
//        public static void main(String[] args) {
//            // s [3,4,5,1,2,null,null,0] t [4,1,2]
//            TreeNode s = new TreeNode(3);
//            s.left = new TreeNode(4);
//            s.right = new TreeNode(5);
//            s.left.left = new TreeNode(1);
//            s.left.right = new TreeNode(2);
//            s.right.left = null;
//            s.right.right = null;
//            s.left.left.left = new TreeNode(0);
//
//            TreeNode t = new TreeNode(4);
//            t.left = new TreeNode(1);
//            t.right = new TreeNode(2);
//            Solution2 solution = new Solution2();
//            boolean isSub = solution.isSubtree(s, t);
//            System.out.println(isSub);
//        }
//    }
//
//    public static class Test1 {
//        public static void main(String[] args) {
//            // s [3,4,5,1,2] t [4,1,2]
//            TreeNode s = new TreeNode(3);
//            s.left = new TreeNode(4);
//            s.right = new TreeNode(5);
//            s.left.left = new TreeNode(1);
//            s.left.right = new TreeNode(2);
//
//            TreeNode t = new TreeNode(4);
//            t.left = new TreeNode(1);
//            t.right = new TreeNode(2);
//            Solution2 solution = new Solution2();
//            boolean isSub = solution.isSubtree(s, t);
//            System.out.println(isSub);
//        }
//    }
//}
////leetcode submit region end(Prohibit modification and deletion)
//
