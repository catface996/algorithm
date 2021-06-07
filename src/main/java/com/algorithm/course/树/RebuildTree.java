package com.algorithm.course.树;

import org.junit.Test;

/**
 * @author by catface
 * @date 2020/11/09
 */
public class RebuildTree {

    public TreeNode buildTree(int[] preorder, int[] inorder) {

        if (preorder.length == 1) {
            return new TreeNode(preorder[0]);
        }

        int root = preorder[0];
        int rootIndex = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (root == inorder[i]) {
                rootIndex = i;
                break;
            }
        }

        int[] leftTreePreOrder = new int[rootIndex];
        int[] leftTreeInOrder = new int[rootIndex];
        int[] rightTreePreOrder = new int[inorder.length - rootIndex - 1];
        int[] rightTreeInOrder = new int[inorder.length - rootIndex - 1];

        for (int i = 1; i < preorder.length; i++) {
            if (i <= rootIndex) {
                leftTreePreOrder[i - 1] = preorder[i];
            } else {
                rightTreePreOrder[i - rootIndex - 1] = preorder[i];
            }
        }

        for (int i = 0; i < inorder.length; i++) {
            if (i < rootIndex) {
                leftTreeInOrder[i] = inorder[i];
                continue;
            }
            if (i > rootIndex) {
                rightTreeInOrder[i - rootIndex - 1] = inorder[i];
            }
        }

        TreeNode treeNode = new TreeNode(preorder[0]);
        treeNode.left = buildTree(leftTreePreOrder, leftTreeInOrder);
        treeNode.right = buildTree(rightTreePreOrder, rightTreeInOrder);
        return treeNode;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) { val = x; }
    }

    @Test
    public void testRebuildTree() {
        TreeNode treeNode = buildTree(new int[] {3, 9, 20, 15, 7}, new int[] {9, 3, 15, 20, 7});
        System.out.println(treeNode);
    }

}
