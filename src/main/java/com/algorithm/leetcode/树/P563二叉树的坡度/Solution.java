package com.algorithm.leetcode.树.P563二叉树的坡度;

//给定一个二叉树，计算 整个树 的坡度 。 
//
// 一个树的 节点的坡度 定义即为，该节点左子树的节点之和和右子树节点之和的 差的绝对值 。如果没有左子树的话，左子树的节点之和为 0 ；没有右子树的话也是一
//样。空结点的坡度是 0 。 
//
// 整个树 的坡度就是其所有节点的坡度之和。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [1,2,3]
//输出：1
//解释：
//节点 2 的坡度：|0-0| = 0（没有子节点）
//节点 3 的坡度：|0-0| = 0（没有子节点）
//节点 1 的坡度：|2-3| = 1（左子树就是左子节点，所以和是 2 ；右子树就是右子节点，所以和是 3 ）
//坡度总和：0 + 0 + 1 = 1
// 
//
// 示例 2： 
//
// 
//输入：root = [4,2,9,3,5,null,7]
//输出：15
//解释：
//节点 3 的坡度：|0-0| = 0（没有子节点）
//节点 5 的坡度：|0-0| = 0（没有子节点）
//节点 7 的坡度：|0-0| = 0（没有子节点）
//节点 2 的坡度：|3-5| = 2（左子树就是左子节点，所以和是 3 ；右子树就是右子节点，所以和是 5 ）
//节点 9 的坡度：|0-7| = 7（没有左子树，所以和是 0 ；右子树正好是右子节点，所以和是 7 ）
//节点 4 的坡度：|(3+5+2)-(9+7)| = |10-16| = 6（左子树值为 3、5 和 2 ，和是 10 ；右子树值为 9 和 7 ，和是 1
//6 ）
//坡度总和：0 + 0 + 0 + 2 + 7 + 6 = 15
// 
//
// 示例 3： 
//
// 
//输入：root = [21,7,14,1,1,2,2,3,3]
//输出：9
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数目的范围在 [0, 104] 内 
// -1000 <= Node.val <= 1000 
// 
// Related Topics 树 深度优先搜索 递归 
// 👍 122 👎 0

//leetcode submit region begin(Prohibit modification and deletion)

public class Solution {

    public int findTilt(TreeNode root) {

        Info info = process(root);
        return info.slopeSum;
    }

    public Info process(TreeNode x) {
        /*
        5:52 下午	info
				解答成功:
				执行耗时:1 ms,击败了48.44% 的Java用户
				内存消耗:38.7 MB,击败了20.17% 的Java用户
         */
        if (x == null) {
            return new Info(0, 0, 0);
        }

        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);

        // 坡度
        int slope = Math.abs(leftInfo.valSum - rightInfo.valSum);
        // 坡度和
        int slopeSum = slope + leftInfo.slopeSum + rightInfo.slopeSum;
        // 节点值的和
        int valSum = x.val + leftInfo.valSum + rightInfo.valSum;
        return new Info(slope, slopeSum, valSum);
    }

    public static class Info {
        // 坡度
        int slope;
        // 坡度和
        int slopeSum;
        // 节点值的和
        int valSum;

        public Info(int slope, int slopeSum, int valSum) {
            this.slope = slope;
            this.slopeSum = slopeSum;
            this.valSum = valSum;
        }
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

    public static class Test {

        public static void main(String[] args) {
            // [1,2,null,3,null,4,null,5]
            TreeNode root = new TreeNode(1);
            root.left = new TreeNode(2);
            root.right = null;
            root.left.left = new TreeNode(3);
            root.left.right = null;
            root.left.left.left = new TreeNode(4);
            root.left.left.right = null;
            root.left.left.left.left = new TreeNode(5);
            Solution solution = new Solution();
            int slopeSum = solution.findTilt(root);
            System.out.println(slopeSum);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

