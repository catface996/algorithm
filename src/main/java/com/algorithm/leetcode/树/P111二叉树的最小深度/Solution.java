package com.algorithm.leetcode.树.P111二叉树的最小深度;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author by catface
 * @date 2021/3/28 5:09 下午
 */
public class Solution {

    public int minDepth(TreeNode root) {
        TreeNode nextNode = root;
        TreeNode endNode = root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int minDepth = 1;
        while (!queue.isEmpty()) {
            TreeNode currentNode = queue.poll();
            if (currentNode.left == null && currentNode.right == null) {
                return minDepth;
            }
            if (currentNode.left != null) {
                queue.add(currentNode.left);
                nextNode = currentNode.left;
            }
            if (currentNode.right != null) {
                queue.add(currentNode.right);
                nextNode = currentNode.right;
            }
            if (currentNode == endNode) {
                if (queue.isEmpty()) {
                    return minDepth;
                }
                minDepth++;
                endNode = nextNode;
            }
        }
        return minDepth;
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
