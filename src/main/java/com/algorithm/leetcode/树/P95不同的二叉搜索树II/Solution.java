package com.algorithm.leetcode.树.P95不同的二叉搜索树II;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by catface
 * @date 2021/3/26 4:43 下午
 **/
public class Solution {

    //public static void main(String[] args) {
    //    Solution solution = new Solution();
    //    List<TreeNode> result = solution.generateTrees(3);
    //    System.out.println(result);
    //}
    //
    //public List<TreeNode> generateTrees(int n) {
    //    int[] numbers = build(n);
    //    return process(numbers);
    //}
    //
    //private int[] build(int n) {
    //    int[] numbers = new int[n];
    //    for (int i = 0; i < n; i++) {
    //        numbers[i] = i + 1;
    //    }
    //    return numbers;
    //}
    //
    //private List<TreeNode> process(int[] numbers) {
    //    if (numbers == null || numbers.length <= 0) {
    //        return new ArrayList<>();
    //    }
    //    List<TreeNode> result = new ArrayList<>();
    //    for (int i = 0; i < numbers.length; i++) {
    //
    //        int[] leftNumbers = removeAndCopy(numbers, i);
    //
    //        if (leftNumbers == null || leftNumbers.length == 0) {
    //            result.add(new TreeNode(numbers[i]));
    //            return result;
    //        }
    //
    //        List<TreeNode> treeNodes = process(leftNumbers);
    //        for (TreeNode treeNode : treeNodes) {
    //            insertNode(treeNode, numbers[i]);
    //        }
    //        result.addAll(treeNodes);
    //    }
    //    return result;
    //}
    //
    //private void insertNode(TreeNode head, int value) {
    //    if (head == null) {
    //        return;
    //    }
    //    // 连续不重复数字,要么大于,要么小于,不会等于
    //    if (head.val > value) {
    //        if (head.left == null) {
    //            head.left = new TreeNode(value);
    //        } else {
    //            insertNode(head.left, value);
    //        }
    //    } else {
    //        if (head.right == null) {
    //            head.right = new TreeNode(value);
    //        } else {
    //            insertNode(head.right, value);
    //        }
    //    }
    //}
    //
    //private int[] removeAndCopy(int[] numbers, int removeIndex) {
    //    if (numbers.length == 1) {
    //        return null;
    //    }
    //    int[] result = new int[numbers.length - 1];
    //    int currentIndex = 0;
    //    for (int i = 0; i < numbers.length; i++) {
    //        if (i != removeIndex) {
    //            result[currentIndex] = numbers[i];
    //            currentIndex++;
    //        }
    //    }
    //    return result;
    //}

    public static void main(String[] args) {
        Solution solution = new Solution();
        List<TreeNode> nodes = solution.process(1, 3);
        System.out.println(nodes);
    }

    public List<TreeNode> generateTrees(int n) {
        return process(1, n);
    }

    public List<TreeNode> process(int start, int end) {
        List<TreeNode> allTrees = new ArrayList<>();
        if (start > end) {
            allTrees.add(null);
            return allTrees;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftTrees = process(start, i - 1);
            List<TreeNode> rightTrees = process(i + 1, end);
            for (TreeNode leftTree : leftTrees) {
                for (TreeNode rightTree : rightTrees) {
                    TreeNode rootNode = new TreeNode(i);
                    rootNode.left = leftTree;
                    rootNode.right = rightTree;
                    allTrees.add(rootNode);
                }
            }
        }
        return allTrees;
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
