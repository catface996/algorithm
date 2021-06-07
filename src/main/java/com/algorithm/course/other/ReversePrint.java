package com.algorithm.course.other;

import java.util.Stack;

import org.junit.Test;

/**
 * @author by catface
 * @date 2020/11/04
 */
public class ReversePrint {

    /**
     * 2:54 下午	info 解答成功: 执行耗时:1 ms,击败了72.11% 的Java用户 内存消耗:39.1 MB,击败了83.50% 的Java用户
     *
     * @param head
     * @return
     */
    public int[] reversePrint(ListNode head) {
        if (head == null) {
            return new int[0];
        }
        ListNode2 currentNode = new ListNode2(head.val);
        ListNode node = head;
        int nodeSize = 1;
        for (; ; ) {
            if (node.next != null) {
                node = node.next;
                ListNode2 newNode2 = new ListNode2(node.val);
                newNode2.parent = currentNode;
                currentNode = newNode2;
                nodeSize++;
            } else {
                break;
            }
        }
        int[] values = new int[nodeSize];
        int index = 0;
        do {
            values[index] = currentNode.val;
            currentNode = currentNode.parent;
            index++;
        } while (currentNode != null);
        return values;
    }

    private class ListNode2 {
        int val;

        ListNode2 parent;

        public ListNode2(int val) {
            this.val = val;
        }
    }

    public class ListNode {
        int val;

        ListNode next;

        ListNode(int x) { val = x; }
    }

    public int[] reversePrint2(ListNode head) {
        if (head == null) {
            return new int[0];
        }
        Stack<ListNode> stack = new Stack<ListNode>();
        ListNode node = head;
        for (; ; ) {
            stack.push(node);
            node = node.next;
            if (node == null) {
                break;
            }
        }
        int size = stack.size();
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = stack.pop().val;
        }
        return result;
    }

    @Test
    public void test() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        node1.next = node2;
        node2.next = node3;

        int[] result = reversePrint(node1);
        System.out.println(result);

    }

    @Test
    public void test2() {
        int[] result = reversePrint(null);
        System.out.println(result);
    }

    @Test
    public void test3() {
        ListNode node1 = new ListNode(1);
        int[] result = reversePrint(node1);
        System.out.println(result);
    }

    @Test
    public void test4() {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        node1.next = node2;
        node2.next = node3;

        int[] result = reversePrint2(node1);
        System.out.println(result);

    }

}
