package pratice.leetcode.树.P662二叉树最大宽度;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author by catface
 * @date 2021/3/29 9:59 上午
 */
public class Solution {

    //TODO 11:05 上午	info
    //					解答成功:
    //					执行耗时:2 ms,击败了69.71% 的Java用户
    //					内存消耗:37.8 MB,击败了80.12% 的Java用户

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        DepthNode depthNodeRoot = new DepthNode(root, 0);
        DepthNode nextNode = depthNodeRoot;
        DepthNode endNode = depthNodeRoot;
        DepthNode startNode = depthNodeRoot;
        Queue<DepthNode> queue = new LinkedList<>();
        queue.add(depthNodeRoot);
        int maxWith = 0;
        while (!queue.isEmpty()) {
            DepthNode currentNode = queue.poll();
            if (currentNode.treeNode.left != null) {
                nextNode = new DepthNode(currentNode.treeNode.left, 2 * currentNode.position + 1);
                queue.add(nextNode);
            }
            if (currentNode.treeNode.right != null) {
                nextNode = new DepthNode(currentNode.treeNode.right, 2 * currentNode.position + 2);
                queue.add(nextNode);
            }

            if (currentNode == endNode) {
                maxWith = Math.max(maxWith, currentNode.position - startNode.position);
                startNode = queue.peek();
                endNode = nextNode;
            }
        }
        return maxWith + 1;
    }

    public static class DepthNode {
        TreeNode treeNode;
        int position;

        public DepthNode(TreeNode treeNode, int position) {
            this.treeNode = treeNode;
            this.position = position;
        }
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
