package pratice.leetcode.树.P103二叉树的锯齿形层序遍历;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * @author by catface
 * @date 2021/3/29 11:32 上午
 */
public class Solution {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        Solution solution = new Solution();
        List<List<Integer>> ans = solution.zigzagLevelOrder(root);
        System.out.println(ans);
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        TreeNode nextNode = root;
        TreeNode endNode = root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        Stack<TreeNode> stack = new Stack<>();
        int i = 0;
        List<Integer> levelList = new ArrayList<>();
        List<List<Integer>> allList = new ArrayList<>();
        while (!queue.isEmpty()) {
            boolean left = (i % 2) == 0;
            TreeNode queueNode = queue.poll();
            if (left) {
                levelList.add(queueNode.val);
            } else {
                TreeNode stockNode = stack.pop();
                levelList.add(stockNode.val);
            }
            if (queueNode.left != null) {
                queue.add(queueNode.left);
                if (left) {
                    stack.push(queueNode.left);
                }
                nextNode = queueNode.left;
            }
            if (queueNode.right != null) {
                queue.add(queueNode.right);
                if (left) {
                    stack.push(queueNode.right);
                }
                nextNode = queueNode.right;
            }
            if (queueNode == endNode) {
                endNode = nextNode;
                allList.add(levelList);
                levelList = new ArrayList<>();
                i++;
            }
        }
        return allList;
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
