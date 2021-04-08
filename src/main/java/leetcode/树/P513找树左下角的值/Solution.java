package leetcode.树.P513找树左下角的值;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author by catface
 * @date 2021/4/1 4:01 下午
 */
public class Solution {

    //TODO 5:06 下午	info
    //				解答成功:
    //				执行耗时:2 ms,击败了68.24% 的Java用户
    //				内存消耗:38 MB,击败了78.34% 的Java用户
    // [1,null,1]
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(1);
        Solution solution = new Solution();
        int result = solution.findBottomLeftValue(root);
        System.out.println(result);
    }

    public int findBottomLeftValue(TreeNode root) {
        TreeNode nextNode = root;
        TreeNode endNode = root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode currentNode = queue.poll();
            if (currentNode.right != null) {
                queue.add(currentNode.right);
                nextNode = currentNode.right;
            }
            if (currentNode.left != null) {
                queue.add(currentNode.left);
                nextNode = currentNode.left;
            }
            if (currentNode == endNode) {
                endNode = nextNode;
            }
        }
        return endNode.val;
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
