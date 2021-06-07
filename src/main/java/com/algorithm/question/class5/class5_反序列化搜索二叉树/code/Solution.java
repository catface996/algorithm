package com.algorithm.question.class5.class5_反序列化搜索二叉树.code;

import com.algorithm.util.PrintTreeNodeUtil;
import com.algorithm.util.TreeNode;
import org.junit.Test;

/**
 * @author by catface
 * @date 2021/6/1 10:51 上午
 */
public class Solution {

    public TreeNode deSerial(int[] arr) {
        return buildTreeNode(arr, 0, arr.length - 1);
    }

    private TreeNode buildTreeNode(int[] arr, int start, int end) {
        if (start == end) {
            return new TreeNode(arr[start]);
        }
        if (start > end) {
            return null;
        }
        TreeNode head = new TreeNode(arr[start]);
        int firstBiggerIndex = findFirstBiggerIndex(arr, start + 1, end, arr[start]);
        if (firstBiggerIndex != -1) {
            head.left = buildTreeNode(arr, start + 1, firstBiggerIndex - 1);
            head.right = buildTreeNode(arr, firstBiggerIndex, end);
        }
        return head;
    }

    private int findFirstBiggerIndex(int[] arr, int start, int end, int num) {
        if (start == end) {
            if (arr[start] > num) {
                return start;
            } else {
                return -1;
            }
        }
        int mid = (start + end) / 2;
        if (arr[mid] <= num) {
            return findFirstBiggerIndex(arr, mid + 1, end, num);
        } else {
            return findFirstBiggerIndex(arr, start, mid, num);
        }
    }

    public static class TestClass {

        @Test
        public void testFindFirstBigger() {
            int[] arr = new int[10];
            for (int i = 0; i < 10; i++) {
                arr[i] = i;
            }
            Solution solution = new Solution();
            for (int i = 0; i < 10; i++) {
                int firstBiggerIndex = solution.findFirstBiggerIndex(arr, 0, arr.length - 1, i);
                System.out.println(firstBiggerIndex);
            }
        }

        @Test
        public void test1() {
            int[] arr = {8, 5, 1, 7, 10, 12};
            Solution solution = new Solution();
            TreeNode root = solution.buildTreeNode(arr, 0, arr.length - 1);
            PrintTreeNodeUtil.printTree(root);
        }
    }

}
