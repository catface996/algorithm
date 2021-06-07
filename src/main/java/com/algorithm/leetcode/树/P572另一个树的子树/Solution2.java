package com.algorithm.leetcode.树.P572另一个树的子树;

import org.junit.Test;

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

class Solution2 {

    public boolean isSubtree(TreeNode s, TreeNode t) {
        //TODO 序列化二叉树,用kmp查找子串
        return true;
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
        @Test
        public void test1() {
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
            Solution2 solution = new Solution2();
            boolean isSub = solution.isSubtree(s, t);
            System.out.println(isSub);
        }

        @Test
        public void test2() {
            // s [3,4,5,1,2] t [4,1,2]
            TreeNode s = new TreeNode(3);
            s.left = new TreeNode(4);
            s.right = new TreeNode(5);
            s.left.left = new TreeNode(1);
            s.left.right = new TreeNode(2);

            TreeNode t = new TreeNode(4);
            t.left = new TreeNode(1);
            t.right = new TreeNode(2);
            Solution2 solution = new Solution2();
            boolean isSub = solution.isSubtree(s, t);
            System.out.println(isSub);
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)

