package leetcode.树.P199二叉树的右视图;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author by catface
 * @date 2021/3/30 10:43 上午
 */
public class Solution {

    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        return process(root);
    }

    /**
     * 层序遍历,从上到下,从左到右,取每一层的最后一个节点
     *
     * @param x
     */
    private List<Integer> process(TreeNode x) {

        TreeNode nextNode = x;
        TreeNode endNode = x;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(x);
        List<Integer> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode currentNode = queue.poll();
            if (currentNode.left != null) {
                queue.add(currentNode.left);
                nextNode = currentNode.left;
            }
            if (currentNode.right != null) {
                queue.add(currentNode.right);
                nextNode = currentNode.right;
            }
            if (currentNode == endNode) {
                ans.add(endNode.val);
                endNode = nextNode;
            }
        }
        return ans;
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
