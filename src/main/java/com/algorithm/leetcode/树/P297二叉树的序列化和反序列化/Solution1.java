package com.algorithm.leetcode.树.P297二叉树的序列化和反序列化;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author by catface
 * @date 2021/3/31 10:21 上午
 */
public class Solution1 {

    // 11:19 上午	info
    //				解答成功:
    //				执行耗时:11 ms,击败了80.98% 的Java用户
    //				内存消耗:40.4 MB,击败了68.48% 的Java用户
    // root = [1,2,3,null,null,4,5]
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        Solution1 solution = new Solution1();
        String str = solution.serialize(root);
        System.out.println(str);

        TreeNode root2 = solution.deserialize(str);
        System.out.println(root2);

    }

    public static final String NULL_NODE = "#";
    public static final String SEPARATOR = ",";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeProcess(root, sb);
        return sb.toString();
    }

    private void serializeProcess(TreeNode x, StringBuilder sb) {
        if (x == null) {
            sb.append(NULL_NODE).append(SEPARATOR);
            return;
        }
        sb.append(x.val).append(SEPARATOR);

        serializeProcess(x.left, sb);

        serializeProcess(x.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] strArr = data.split(SEPARATOR);
        Queue<String> queue = new LinkedList<>(Arrays.asList(strArr));
        return deserializeProcess(queue);
    }

    public TreeNode deserializeProcess(Queue<String> queue) {
        String nodeValue = queue.poll();
        if (nodeValue == null) {
            return null;
        }
        if (NULL_NODE.equals(nodeValue)) {
            return null;
        }
        TreeNode currentRoot = new TreeNode(Integer.parseInt(nodeValue));
        currentRoot.left = deserializeProcess(queue);
        currentRoot.right = deserializeProcess(queue);
        return currentRoot;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) { val = x; }
    }
}
