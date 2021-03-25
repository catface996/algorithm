package pratice.zuo.test.树.完全二叉树;

import java.util.LinkedList;

/**
 * @author by catface
 * @date 2021/3/25 1:53 下午
 */
public class CompleteBinaryTree {

    public static boolean isCBT1(Node head) {
        if (head == null) {
            return true;
        }
        LinkedList<Node> queue = new LinkedList<>();
        // 是否遇到过左右两个孩子不双全的节点
        boolean leaf = false;
        Node l = null;
        Node r = null;
        queue.add(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            l = head.left;
            r = head.right;
            if (
                // 如果遇到了不双全的节点之后，又发现当前节点不是叶节点
                (leaf && (l != null || r != null))
                    ||
                    (l == null && r != null)

            ) {
                return false;
            }
            if (l != null) {
                queue.add(l);
            }
            if (r != null) {
                queue.add(r);
            }
            if (l == null || r == null) {
                leaf = true;
            }
        }
        return true;
    }

    public static boolean isCompleteBinaryTree(Node head) {
        if (head == null) {
            return true;
        }
        return processComplete(head).complete;
    }

    public static Info processComplete(Node x) {
        if (x == null) {
            return new Info(true, true, 0);
        }
        Info leftInfo = processComplete(x.left);
        Info rightInfo = processComplete(x.right);
        boolean full = leftInfo.full && rightInfo.full && (leftInfo.height == rightInfo.height);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean complete = false;
        if (full) {
            complete = true;
        } else {
            if (leftInfo.complete && rightInfo.complete) {
                if (rightInfo.full && (leftInfo.height == rightInfo.height + 1)) {
                    complete = true;
                }
                if (leftInfo.full && leftInfo.height == rightInfo.height) {
                    complete = true;
                }
            }
        }
        return new Info(full, complete, height);
    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int)(Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isCBT1(head) != isCompleteBinaryTree(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    // 对每一棵子树，是否是满二叉树、是否是完全二叉树、高度
    public static class Info {
        public boolean full;
        public boolean complete;
        public int height;

        public Info(boolean full, boolean complete, int height) {
            this.full = full;
            this.complete = complete;
            this.height = height;
        }
    }

}
