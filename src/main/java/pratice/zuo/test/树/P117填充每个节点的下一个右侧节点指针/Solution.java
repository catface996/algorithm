package pratice.zuo.test.树.P117填充每个节点的下一个右侧节点指针;

/**
 * @author by catface
 * @date 2021/3/30 3:51 下午
 */
public class Solution {

    // [1,2,3,4,5,null,6,7,null,null,null,null,8]
    // [2,1,3,0,7,9,1,2,null,1,0,null,null,8,8,null,null,null,null,7]
    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(6);
        root.left.left.left = new Node(7);
        root.right.right.right = new Node(8);

        Solution solution = new Solution();
        solution.connect(root);
    }

    public Node connect(Node root) {
        process(root);
        return root;
    }

    private void process(Node x) {
        if (x == null) {
            return;
        }

        // 优先处理右侧子树,否则通过链表遍历寻找下一层的断开处第一个右侧节点时,链表未完成连接.
        process(x.right);

        // 再连接左子树的右侧边缘节点到右子树的左侧边缘节点
        Node left = x.left;
        Node right = x.right;
        while (left != null && right != null) {
            left.next = right;
            right = right.left != null ? right.left : right.right;
            if (right == null) {
                Node point = left.next;
                while (point.next != null) {
                    point = point.next;
                    right = point.left != null ? point.left : point.right;
                    if (right != null) {
                        break;
                    }
                }
            }
            left = left.right != null ? left.right : left.left;
        }

        // 左侧子树内部同层节点连接
        process(x.left);

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

    ;
}
