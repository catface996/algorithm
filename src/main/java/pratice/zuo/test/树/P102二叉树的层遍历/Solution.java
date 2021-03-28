package pratice.zuo.test.树.P102二叉树的层遍历;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author by catface
 * @date 2021/3/28 1:54 下午
 */
public class Solution {

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<List<Integer>> levelList = new ArrayList<>();
        TreeNode endNode = root;
        TreeNode nextNode = root;
        List<Integer> level = new ArrayList<>();
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

            level.add(currentNode.val);
            if (currentNode == endNode) {
                levelList.add(level);
                level = new ArrayList<>();
                endNode = nextNode;
            }
        }
        return levelList;
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
