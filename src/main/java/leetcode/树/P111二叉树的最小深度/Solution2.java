package leetcode.树.P111二叉树的最小深度;

//给定一个二叉树，找出其最小深度。
//
// 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
//
// 说明：叶子节点是指没有子节点的节点。
//
//
//
// 示例 1：
//
//
//输入：root = [3,9,20,null,null,15,7]
//输出：2
//
//
// 示例 2：
//
//
//输入：root = [2,null,3,null,4,null,5,null,6]
//输出：5
//
//
//
//
// 提示：
//
//
// 树中节点数的范围在 [0, 105] 内
// -1000 <= Node.val <= 1000
//
// Related Topics 树 深度优先搜索 广度优先搜索
// 👍 499 👎 0

//leetcode submit region begin(Prohibit modification and deletion)

import util.TreeNode;

/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode left; TreeNode right; TreeNode() {}
 * TreeNode(int val) { this.val = val; } TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left =
 * left; this.right = right; } }
 */
public class Solution2 {

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        TreeNode cur = root;
        TreeNode mostRight = null;
        int curLevel = 0;
        int minHeight = Integer.MAX_VALUE;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                int rightBoardSize = 1;
                while (mostRight.right != null && mostRight.right != cur) {
                    rightBoardSize++;
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) { // 第一次到达
                    curLevel++;
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else { // 第二次到达
                    if (mostRight.left == null) {
                        minHeight = Math.min(minHeight, curLevel);
                    }
                    curLevel -= rightBoardSize;
                    mostRight.right = null;
                }
            } else { // 只有一次到达
                curLevel++;
            }
            cur = cur.right;
        }
        int finalRight = 1;
        cur = root;
        while (cur.right != null) {
            finalRight++;
            cur = cur.right;
        }
        if (cur.left == null && cur.right == null) {
            minHeight = Math.min(minHeight, finalRight);
        }
        return minHeight;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

