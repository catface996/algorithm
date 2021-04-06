package pratice.zuo.test.other;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author by catface
 * @date 2021/3/24 2:49 下午
 */
/*
 * 二叉树可以通过先序、后序或者按层遍历的方式序列化和反序列化，
 * 以下代码全部实现了。
 * 但是，二叉树无法通过中序遍历的方式实现序列化和反序列化
 * 因为不同的两棵树，可能得到同样的中序序列，即便补了空位置也可能一样。
 * 比如如下两棵树
 *         __2
 *        /
 *       1
 *       和
 *       1__
 *          \
 *           2
 * 补足空位置的中序遍历结果都是{ null, 1, null, 2, null}
 *
 * */
public class SerializeAndReconstructTree {

    public static Queue<String> preSerial(Node head) {
        Queue<String> ans = new LinkedList<>();
        pres(head, ans);
        return ans;
    }

    public static void pres(Node node, Queue<String> ans) {
        if (node == null) {
            ans.add(null);
        } else {
            ans.add(String.valueOf(node.value));
            pres(node.left, ans);
            pres(node.right, ans);
        }
    }

    public static Queue<String> inSerial(Node head) {
        Queue<String> ans = new LinkedList<>();
        ins(head, ans);
        return ans;
    }

    private static void ins(Node node, Queue<String> ans) {
        if (node == null) {
            ans.add(null);
        } else {
            ins(node.left, ans);
            ans.add(String.valueOf(node.value));
            ins(node.right, ans);
        }
    }

    public static Queue<String> posSerial(Node head) {
        Queue<String> ans = new LinkedList<>();
        poss(head, ans);
        return ans;
    }

    public static void poss(Node node, Queue<String> ans) {
        if (node == null) {
            ans.add(null);
        } else {
            // 后续,先左,再右,后中
            poss(node.left, ans);
            poss(node.right, ans);
            ans.add(String.valueOf(node.value));
        }
    }

    public static Node buildTreeFromPreSerial(Queue<String> serial) {
        if (serial == null || serial.isEmpty()) {
            return null;
        }
        String value = serial.poll();
        if (value == null) {
            return null;
        }
        Node currentNode = new Node(Integer.parseInt(value));
        currentNode.left = buildTreeFromPreSerial(serial);
        currentNode.right = buildTreeFromPreSerial(serial);
        ;
        return currentNode;
    }

    public static Node buildTreeFromPosSerial(Queue<String> serial) {
        if (serial == null || serial.isEmpty()) {
            return null;
        }
        Stack<String> stack = new Stack<>();
        while (!serial.isEmpty()) {
            stack.push(serial.poll());
        }
        return posb(stack);
    }

    /*
    后序遍历 左右中,如果要通过递归来实现反序列化,需要逆序 成  中右左
     */
    private static Node posb(Stack<String> posStack) {
        String value = posStack.pop();
        if (value == null) {
            return null;
        }
        Node parent = new Node(Integer.parseInt(value));
        parent.right = posb(posStack);
        parent.left = posb(posStack);
        return parent;
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

    // for test
    public static boolean isSameValueStructure(Node head1, Node head2) {
        if (head1 == null && head2 != null) {
            return false;
        }
        if (head1 != null && head2 == null) {
            return false;
        }
        if (head1 == null && head2 == null) {
            return true;
        }
        if (head1.value != head2.value) {
            return false;
        }
        return isSameValueStructure(head1.left, head2.left) && isSameValueStructure(head1.right, head2.right);
    }

    // for test
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        Node head = generateRandomBST(maxLevel, maxValue);
        Queue<String> pre = preSerial(head);
        System.out.println("前序序列化");
        for (String s : pre) {
            System.out.print(s + ";");
        }
        System.out.println("");
        Node head2 = buildTreeFromPreSerial(pre);
        Queue<String> pre2 = preSerial(head2);
        for (String s : pre2) {
            System.out.print(s + ";");
        }
        System.out.println("");

        Queue<String> ins1 = inSerial(head);
        System.out.println("中序序列化");
        for (String s : ins1) {
            System.out.print(s + ";");
        }
        System.out.println("");

        Queue<String> pos1 = posSerial(head);
        System.out.println("后序序列化");
        for (String s : pos1) {
            System.out.print(s + ";");
        }
        System.out.println("");
        head2 = buildTreeFromPosSerial(pos1);
        Queue<String> pos2 = posSerial(head2);
        System.out.println("后序序列化");
        for (String s : pos2) {
            System.out.print(s + ";");
        }
        System.out.println("");
    }

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

}
