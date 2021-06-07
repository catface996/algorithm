package com.algorithm.leetcode.树.P92二叉树的中序遍历;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        process(root, ans);
        return ans;
    }

    private void process(TreeNode x, List<Integer> ans) {
        if (x == null) {
            return;
        }
        process(x.left, ans);
        ans.add(x.val);
        process(x.right, ans);
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
