package com.algorithm.leetcode.树.P515在每个树行中找最大值;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author by catface
 * @date 2021/4/1 5:21 下午
 */
public class Solution {

    Comparator<TreeNode> nodeComparator = new Comparator<TreeNode>() {
        @Override
        public int compare(TreeNode o1, TreeNode o2) {
            if (o1.val == o2.val) {
                return 0;
            }
            return o1.val > o2.val ? -1 : 1;
        }
    };

    //TODO 5:55 下午	info
    //				解答成功:
    //				执行耗时:5 ms,击败了8.40% 的Java用户
    //				内存消耗:38.8 MB,击败了38.86% 的Java用户
    public static void main(String[] args) {
        //[1,3,2,5,3,null,9]
        //TreeNode root = new TreeNode(1);
        //root.left = new TreeNode(3);
        //root.right = new TreeNode(2);
        //root.left.left = new TreeNode(5);
        //root.left.right = new TreeNode(3);
        //root.right.left = null;
        //root.right.right = new TreeNode(9);

        // [0,-2147483648,2147483647]
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(-2147483648);
        root.right = new TreeNode(2147483647);
        Solution solution = new Solution();
        List<Integer> ans = solution.largestValues(root);
        System.out.println(ans);
    }

    public List<Integer> largestValues(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        if (root.left == null && root.right == null) {
            return Collections.singletonList(root.val);
        }

        TreeNode nextNode = root;
        TreeNode endNode = root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<Integer> ans = new ArrayList<>();
        ans.add(root.val);
        PriorityQueue<TreeNode> heap = new PriorityQueue<>(nodeComparator);
        while (!queue.isEmpty()) {
            TreeNode currentNode = queue.poll();
            if (currentNode.left != null) {
                nextNode = currentNode.left;
                queue.add(nextNode);
                heap.add(nextNode);
            }
            if (currentNode.right != null) {
                nextNode = currentNode.right;
                queue.add(nextNode);
                heap.add(nextNode);
            }
            if (currentNode == endNode) {
                endNode = nextNode;
                TreeNode treeNode = heap.peek();
                if (treeNode != null) {
                    ans.add(treeNode.val);
                }
                heap = new PriorityQueue<>(nodeComparator);
            }
        }
        return ans;
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
