package pratice.zuo.test.树.满二叉树;

/**
 * @author by catface
 * @date 2021/3/25 2:21 下午
 */
public class FullBinaryTree {

    public static boolean isFullBinaryTree(Node head) {
        if (head == null) {
            return true;
        }
        TravelInfo travelInfo = processTravel(head);
        return travelInfo.nodeNum == ((1 << travelInfo.maxLevel) - 1);

    }

    public static TravelInfo processTravel(Node x) {
        if (x == null) {

            return new TravelInfo(0, 0);
        }
        TravelInfo leftInfo = processTravel(x.left);
        TravelInfo rightInfo = processTravel(x.right);
        int nodeNum = leftInfo.nodeNum + rightInfo.nodeNum + 1;
        int maxLevel = Math.max(leftInfo.maxLevel, rightInfo.maxLevel) + 1;
        return new TravelInfo(nodeNum, maxLevel);
    }

    public static boolean isFull1(Node head) {
        if (head == null) {
            return true;
        }
        int height = h(head);
        int nodes = n(head);
        return (1 << height) - 1 == nodes;
    }

    public static int h(Node head) {
        if (head == null) {
            return 0;
        }
        return Math.max(h(head.left), h(head.right)) + 1;
    }

    public static int n(Node head) {
        if (head == null) {
            return 0;
        }
        return n(head.left) + n(head.right) + 1;
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
            if (isFull1(head) != isFullBinaryTree(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

    public static class TravelInfo {
        private int nodeNum;
        private int maxLevel;

        public TravelInfo(int nodeNum, int maxLevel) {
            this.nodeNum = nodeNum;
            this.maxLevel = maxLevel;
        }
    }

    public static class Node {
        private int value;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }
    }
}
