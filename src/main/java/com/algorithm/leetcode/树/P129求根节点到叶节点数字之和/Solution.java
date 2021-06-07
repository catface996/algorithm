package com.algorithm.leetcode.树.P129求根节点到叶节点数字之和;

/**
 * @author by catface
 * @date 2021/3/30 5:19 下午
 */
public class Solution {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        Solution solution = new Solution();
        int ans = solution.sumNumbers(root);
        System.out.println(ans);
    }

    public int sumNumbers(TreeNode root) {
        SumInfo sumInfo = new SumInfo(0);
        process(root, 0, sumInfo);
        return sumInfo.sum;
    }

    private void process(TreeNode x, int sum, SumInfo sumInfo) {
        if (x == null) {
            return;
        }
        int tempSum = sum * 10 + x.val;

        if (x.left == null && x.right == null) {
            sumInfo.sum = sumInfo.sum + tempSum;
            return;
        }
        process(x.left, tempSum, sumInfo);
        process(x.right, tempSum, sumInfo);
    }

    public static class SumInfo {
        private int sum;

        public SumInfo(int sum) {
            this.sum = sum;
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
