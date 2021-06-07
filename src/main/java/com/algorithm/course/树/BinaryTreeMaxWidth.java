package com.algorithm.course.树;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author by catface
 * @date 2021/3/24 5:12 下午
 */
public class BinaryTreeMaxWidth {

    public static int maxWidth1(Node head) {
        Map<Integer, Integer> levelMap = new HashMap<>();
        process1(head, 1, levelMap);
        int max = 0;
        for (Integer value : levelMap.values()) {
            max = Math.max(max, value);
        }
        return max;
    }

    public static void process1(Node node, Integer level, Map<Integer, Integer> levelMap) {
        if (node == null) {
            return;
        }
        levelMap.merge(level, 1, Integer::sum);
        process1(node.left, level + 1, levelMap);
        process1(node.right, level + 1, levelMap);
    }

    public static int maxWidthUseMap(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        // key 在 哪一层，value
        HashMap<Node, Integer> levelMap = new HashMap<>();
        levelMap.put(head, 1);
        int curLevel = 1; // 当前你正在统计哪一层的宽度
        int curLevelNodes = 0; // 当前层curLevel层，宽度目前是多少
        int max = 0;
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int curNodeLevel = levelMap.get(cur);
            if (cur.left != null) {
                levelMap.put(cur.left, curNodeLevel + 1);
                queue.add(cur.left);
            }
            if (cur.right != null) {
                levelMap.put(cur.right, curNodeLevel + 1);
                queue.add(cur.right);
            }
            if (curNodeLevel == curLevel) {
                curLevelNodes++;
            } else {
                max = Math.max(max, curLevelNodes);
                curLevel++;
                curLevelNodes = 1;
            }
        }
        max = Math.max(max, curLevelNodes);
        return max;
    }

    public static int maxWidth2(Node head) {
        if (head == null) {
            return 0;
        }
        Queue<Node> queue = new LinkedList<>();
        Node currentLevelEndNode = head;
        Node nextLevelEndNode = null;
        queue.add(head);
        int maxWidth = 0;
        int currentWidth = 0;
        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();

            if (currentNode.left != null) {
                queue.add(currentNode.left);
                nextLevelEndNode = currentNode.left;
            }
            if (currentNode.right != null) {
                queue.add(currentNode.right);
                nextLevelEndNode = currentNode.right;
            }

            currentWidth++;
            if (currentNode == currentLevelEndNode) {
                maxWidth = Math.max(maxWidth, currentWidth);
                currentWidth = 0;
                currentLevelEndNode = nextLevelEndNode;
            }

        }
        return maxWidth;
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
        int maxLevel = 10;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxWidth1(head) != maxWidth2(head)) {
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
}
