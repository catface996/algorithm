package pratice.leetcode.树.P116填充每个节点的下一右节点指针;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author by catface
 * @date 2021/3/30 2:17 下午
 */
public class Solution1 {

    /**
     * 使用层序遍历
     *
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Node nextNode = root;
        Node endNode = root;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        Node tail = null;
        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();

            if (currentNode.left != null) {
                queue.add(currentNode.left);
                nextNode = currentNode.left;
                if (tail != null) {
                    tail.next = nextNode;
                }
                tail = nextNode;
            }
            if (currentNode.right != null) {
                queue.add(currentNode.right);
                nextNode = currentNode.right;
                if (tail != null) {
                    tail.next = nextNode;
                }
                tail = nextNode;
            }
            if (currentNode == endNode) {
                tail = null;
                endNode = nextNode;
            }
        }
        return root;
    }

    public static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}
