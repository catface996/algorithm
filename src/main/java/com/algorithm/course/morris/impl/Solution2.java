package com.algorithm.course.morris.impl;

import com.algorithm.util.PrintTreeNodeUtil;
import com.algorithm.util.TreeConvert;
import com.algorithm.util.TreeNode;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/5/6 10:10 上午
 */
public class Solution2 {

    public static void printEdge(TreeNode head) {
        TreeNode tail = reverseEdge(head);
        TreeNode cur = tail;
        while (cur != null) {
            System.out.print(cur.val + "->");
            cur = cur.right;
        }
        reverseEdge(tail);
    }

    public static TreeNode reverseEdge(TreeNode from) {
        TreeNode pre = null;
        TreeNode next = null;
        while (from != null) {
            next = from.right;
            from.right = pre;
            pre = from;
            from = next;
        }
        return pre;
    }

    public void morris(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode cur = root;
        TreeNode mostRight;
        while (cur != null) {
            System.out.print(cur.val + "->");
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            cur = cur.right;
        }
        System.out.println();
    }

    public void preMorris(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode cur = root;
        TreeNode mostRight;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    System.out.print(cur.val + "->");
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            } else {
                System.out.print(cur.val + "->");
            }
            cur = cur.right;
        }
        System.out.println();
    }

    public void inMorris(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode cur = root;
        TreeNode mostRight;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            System.out.print(cur.val + "->");
            cur = cur.right;
        }
        System.out.println();
    }

    public void postMorris(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode cur = root;
        TreeNode mostRight;
        while (cur != null) {
            if (cur.left != null) {
                mostRight = cur.left;
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    // 第一次进入cur节点,cur节点左孩子的最右子节点的右孩子指向cur节点
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    // 第二次进入cur节点,cur节点左孩子的左右子节点断开与cur的指向
                    mostRight.right = null;
                    printEdge(cur.left);
                }
            }
            cur = cur.right;
        }
        printEdge(root);
        System.out.println();
    }

    public static class TestClass {

        @Test
        public void testMorris() {
            Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
            TreeNode root = TreeConvert.convert(arr);
            root.right.right.right = new TreeNode(100);
            PrintTreeNodeUtil.printTree(root);
            Solution2 solution2 = new Solution2();
            solution2.morris(root);
        }

        @Test
        public void testPreMorris() {
            Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
            TreeNode root = TreeConvert.convert(arr);
            root.right.right.right = new TreeNode(100);
            PrintTreeNodeUtil.printTree(root);
            Solution2 solution2 = new Solution2();
            solution2.preMorris(root);
        }

        @Test
        public void testInMorris() {
            Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
            TreeNode root = TreeConvert.convert(arr);
            root.right.right.right = new TreeNode(100);
            PrintTreeNodeUtil.printTree(root);
            Solution2 solution2 = new Solution2();
            solution2.inMorris(root);
        }

        @Test
        public void testPostMorris() {
            Integer[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
            TreeNode root = TreeConvert.convert(arr);
            root.right.right.right = new TreeNode(100);
            PrintTreeNodeUtil.printTree(root);
            Solution2 solution2 = new Solution2();
            solution2.postMorris(root);
        }
    }

}
