package com.algorithm.leetcode.排序.P147对链表进行插入排序;

//对链表进行插入排序。
//
//
//插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。
//每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。
//
//
//
// 插入排序算法：
//
//
// 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
// 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
// 重复直到所有输入数据插入完为止。
//
//
//
//
// 示例 1：
//
// 输入: 4->2->1->3
//输出: 1->2->3->4
//
//
// 示例 2：
//
// 输入: -1->5->3->4->0
//输出: -1->0->3->4->5
//
// Related Topics 排序 链表
// 👍 371 👎 0

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode next; ListNode() {} ListNode(int val) {
 * this.val = val; } ListNode(int val, ListNode next) { this.val = val; this.next = next; } }
 */
public class Solution1 {

    /*
    解题思路:从原有链表中依次取头结点,依次在新链表中插入
    3:02 下午	info
				解答成功:
				执行耗时:27 ms,击败了20.01% 的Java用户
				内存消耗:38.3 MB,击败了25.72% 的Java用户
     */
    public ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        ListNode newHead = head;
        ListNode insertNode = head.next;
        head = insertNode.next;
        newHead.next = null;
        insertNode.next = null;
        while (true) {
            newHead = insertNode(newHead, insertNode);
            insertNode = head;
            if (insertNode == null) {
                return newHead;
            }
            head = insertNode.next;
            insertNode.next = null;
        }
    }

    private ListNode insertNode(ListNode head, ListNode insertNode) {
        if (insertNode.val <= head.val) {
            insertNode.next = head;
            return insertNode;
        }
        ListNode currentNode = head;
        while (insertNode.val > currentNode.val
            && currentNode.next != null
            && insertNode.val > currentNode.next.val) {
            currentNode = currentNode.next;
        }
        insertNode.next = currentNode.next;
        currentNode.next = insertNode;
        return head;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {}

        ListNode(int val) { this.val = val; }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static class Test1 {
        public static void main(String[] args) {
            String txt = "4->2->1->3";
            String[] values = txt.split("->");
            ListNode head = new ListNode(Integer.parseInt(values[0]));
            ListNode tail = head;
            for (int i = 1; i < values.length; i++) {
                ListNode node = new ListNode(Integer.parseInt(values[i]));
                tail.next = node;
                tail = node;
            }
            Solution1 solution = new Solution1();
            ListNode newHead = solution.insertionSortList(head);
            System.out.println(newHead);
        }
    }

    public static class Test2 {
        public static void main(String[] args) {
            String txt = "-1->5->3->4->0";
            String[] values = txt.split("->");
            ListNode head = new ListNode(Integer.parseInt(values[0]));
            ListNode tail = head;
            for (int i = 1; i < values.length; i++) {
                ListNode node = new ListNode(Integer.parseInt(values[i]));
                tail.next = node;
                tail = node;
            }
            Solution1 solution = new Solution1();
            ListNode newHead = solution.insertionSortList(head);
            System.out.println(newHead);
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)

