package leetcode.树.P107二叉树层序遍历II;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author by catface
 * @date 2021/3/28 6:05 下午
 */
public class Solution {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        TreeNode nextNode = root;
        TreeNode endNode = root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<List<Integer>> listList = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode currentNode = queue.poll();
            list.add(currentNode.val);
            if (currentNode.left != null) {
                queue.add(currentNode.left);
                nextNode = currentNode.left;
            }
            if (currentNode.right != null) {
                queue.add(currentNode.right);
                nextNode = currentNode.right;
            }
            if (currentNode == endNode) {
                endNode = nextNode;
                listList.add(list);
                list = new ArrayList<>();
            }
        }
        for (int i = 0; i < listList.size() / 2; i++) {
            List<Integer> temp = listList.get(i);
            int j = listList.size() - i - 1;
            listList.set(i, listList.get(j));
            listList.set(j, temp);
        }
        return listList;
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
