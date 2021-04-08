package util;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author by catface
 * @date 2021/4/8 5:06 下午
 */
public class TreeConvert {

    public static TreeNode convert(Integer[] values) {
        TreeNode root = new TreeNode(values[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        while (i < values.length) {
            TreeNode currentNode = queue.poll();
            if (currentNode == null) {
                break;
            }
            Integer leftValue = values[i++];
            if (leftValue != null) {
                currentNode.left = new TreeNode(leftValue);
                queue.add(currentNode.left);
            }
            if (i >= values.length) {
                break;
            }
            Integer rightValue = values[i++];
            if (rightValue != null) {
                currentNode.right = new TreeNode(rightValue);
                queue.add(currentNode.right);
            }
        }
        return root;
    }

}
