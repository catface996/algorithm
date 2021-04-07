package pratice.leetcode.树.P449序列化和反序列化二叉搜索树;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author by catface
 * @date 2021/3/31 3:55 下午
 */
public class Solution1 {

    //TODO 4:16 下午	info
    //				解答成功:
    //				执行耗时:11 ms,击败了56.21% 的Java用户
    //				内存消耗:39.8 MB,击败了27.16% 的Java用户
    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.left.left.left = new TreeNode(1);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);
        root.right.left.right = new TreeNode(7);
        Solution1 solution1 = new Solution1();
        String str = solution1.serialize(root);
        System.out.println(str);
        TreeNode root2 = solution1.deserialize(str);
        System.out.println(root2);
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        serializeProcess(root, sb);
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    private void serializeProcess(TreeNode x, StringBuilder sb) {
        if (x == null) {
            return;
        }
        sb.append(x.val).append(',');
        serializeProcess(x.left, sb);
        serializeProcess(x.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null) {
            return null;
        }
        String[] strArr = data.split(",");
        Queue<Integer> queue = new LinkedList<>();
        for (String s : strArr) {
            queue.add(Integer.parseInt(s));
        }
        return deserializeProcess(queue);
    }

    private TreeNode deserializeProcess(Queue<Integer> queue) {
        if (queue.isEmpty()) {
            return null;
        }
        int rootValue = queue.poll();
        TreeNode root = new TreeNode(rootValue);
        Queue<Integer> leftQ = new LinkedList<>();
        Queue<Integer> rightQ = new LinkedList<>();
        while (!queue.isEmpty()) {
            int tempValue = queue.poll();
            if (tempValue < rootValue) {
                leftQ.add(tempValue);
            } else {
                rightQ.add(tempValue);
            }
        }
        root.left = deserializeProcess(leftQ);
        root.right = deserializeProcess(rightQ);
        return root;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) { val = x; }
    }
}
