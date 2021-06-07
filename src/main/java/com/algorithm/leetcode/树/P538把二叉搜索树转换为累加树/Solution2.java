package com.algorithm.leetcode.树.P538把二叉搜索树转换为累加树;
//给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），使每个节点 node 的新值等于原树中大于或等于
// node.val 的值之和。
//
// 提醒一下，二叉搜索树满足下列约束条件：
//
//
// 节点的左子树仅包含键 小于 节点键的节点。
// 节点的右子树仅包含键 大于 节点键的节点。
// 左右子树也必须是二叉搜索树。
//
//
// 注意：本题和 1038: https://leetcode-cn.com/problems/binary-search-tree-to-greater-s
//um-tree/ 相同
//
//
//
// 示例 1：
//
//
//
// 输入：[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
//输出：[30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
//
//
// 示例 2：
//
// 输入：root = [0,null,1]
//输出：[1,null,1]
//
//
// 示例 3：
//
// 输入：root = [1,0,2]
//输出：[3,3,2]
//
//
// 示例 4：
//
// 输入：root = [3,2,4,1]
//输出：[7,9,4,10]
//
//
//
//
// 提示：
//
//
// 树中的节点数介于 0 和 104 之间。
// 每个节点的值介于 -104 和 104 之间。
// 树中的所有值 互不相同 。
// 给定的树为二叉搜索树。
//
// Related Topics 树 深度优先搜索 二叉搜索树 递归
// 👍 500 👎 0

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * @author catface
 */
public class Solution2 {

    //TODO 需要优化
    public static void main(String[] args) {

        // [4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(1);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        root.left.left.left = null;
        root.left.left.right = null;
        root.left.right.left = null;
        root.left.right.right = new TreeNode(3);
        root.right.left.left = null;
        root.right.left.right = null;
        root.right.right.left = null;
        root.right.right.right = new TreeNode(8);

        Solution2 solution = new Solution2();
        TreeNode newRoot = solution.convertBST(root);
        System.out.println(newRoot);
    }

    /*
     * 将搜索二叉树 中序遍历,转换成数组后为source[],累加和数组为sum[]
     * <p>
     * sum[source.length-1] = source[source.length-1]
     * <p>
     * 其余为 sum[i] = source[i] + sum[i-1] 通过数组的遍历累加方式,迁移到搜索树上即可完成该算法
     * <p> 第二种方案,只要记录上一次计算得出的累加和
     *  1. 当前节点的累加和 = 当前节点值 + 上一次累加和
     *  2. 当前累加和作为下个节点的上一次累加和参与下轮的计算
     *
     * 1:34 下午	info
				解答成功:
				执行耗时:1 ms,击败了85.50% 的Java用户
				内存消耗:38.6 MB,击败了77.57% 的Java用户
     * @param x
     */
    private int lastSum = 0;


    public TreeNode convertBST(TreeNode root) {
        process(root);
        return root;
    }

    private void process(TreeNode x) {
        if (x == null) {
            return;
        }
        // 优先处理右侧节点,处理右侧节点完成后,右侧节点的值已经更新成累加和
        process(x.right);

        // 处理中间节点,如果右侧节点不为空,中间节点的值为 中间节点 + 右侧节点值(已经是累加和)
        x.val += lastSum;
        lastSum = x.val;

        // 处理左侧节点
        process(x.left);

    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
