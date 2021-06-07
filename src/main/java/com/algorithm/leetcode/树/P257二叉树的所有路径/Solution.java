package com.algorithm.leetcode.树.P257二叉树的所有路径;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by catface
 * @date 2021/3/30 10:16 下午
 */
public class Solution {

    //TODO 10:56 下午	info
    //					解答成功:
    //					执行耗时:2 ms,击败了78.72% 的Java用户
    //					内存消耗:38.6 MB,击败了53.53% 的Java用户

    public static String split = "->";

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);
        Solution solution = new Solution();
        solution.binaryTreePaths(root);
    }

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ans = new ArrayList<>();
        process(root, new StringBuilder(), ans);
        return ans;
    }

    private void process(TreeNode x, StringBuilder temp, List<String> ans) {
        if (x == null) {
            return;
        }

        if (temp.length() <= 0) {
            temp.append(x.val);
        } else {
            temp.append(split).append(x.val);
        }
        if (x.left == null && x.right == null) {
            ans.add(temp.toString());
            return;
        }

        process(x.left, new StringBuilder(temp), ans);

        process(x.right, new StringBuilder(temp), ans);

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
