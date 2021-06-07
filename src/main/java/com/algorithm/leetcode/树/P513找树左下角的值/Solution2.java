package com.algorithm.leetcode.树.P513找树左下角的值;

/**
 * @author by catface
 * @date 2021/4/1 4:01 下午
 */
public class Solution2 {

    public int maxDepth = 0;
    public TreeNode lefNode;

    //TODO 5:16 下午	info
    //				解答成功:
    //				执行耗时:0 ms,击败了100.00% 的Java用户
    //				内存消耗:38.2 MB,击败了44.15% 的Java用户
    // [1,null,1]
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(1);
        Solution2 solution = new Solution2();
        int result = solution.findBottomLeftValue(root);
        System.out.println(result);
    }

    public int findBottomLeftValue(TreeNode root) {
        process(root, 0);
        return lefNode.val;
    }

    private void process(TreeNode x, int depth) {
        if (x == null) {
            return;
        }
        process(x.left, depth + 1);
        process(x.right, depth + 1);

        if (depth + 1 > maxDepth) {
            lefNode = x;
            maxDepth = depth + 1;
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
}
