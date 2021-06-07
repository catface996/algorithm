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
public class Solution2 {

    /*
    解题思路:
    1.取头结点作为已排序的链表,此时只有一个节点,并设置头尾两个指针,为方便头插,头指针可以是一个指向head的节点
    2.从剩余的原始链表上取头节点,节点值大于等于尾部节点,做尾插,否则,做头插
    3.头插时,从头节点往后遍历,找到最后一个节点(节点值小于待插入节点值),将节点在插入找到的几点后

    3:36 下午	info
				解答成功:
				执行耗时:3 ms,击败了98.28% 的Java用户
				内存消耗:38 MB,击败了82.69% 的Java用户
     */
    public ListNode insertionSortList(ListNode head) {

        ListNode headHead = new ListNode();
        headHead.next = head;
        ListNode lastSortedNode = head, currentNode = head.next;
        while (currentNode != null) {
            // 如果当前节点大于等于最后一个有序节点,说明head->lastSortedNode子链是有序的
            if (currentNode.val >= lastSortedNode.val) {
                lastSortedNode = lastSortedNode.next;
                currentNode = lastSortedNode.next;
                continue;
            }

            // 如果当前节点值大于最后的有序节点,则需要将当前节点插入到head->lastSortedNoe节点间的子链
            ListNode offsetNode = headHead;
            while (offsetNode.next.val < currentNode.val) {
                offsetNode = offsetNode.next;
            }
            // 保护待比较的下一个节点,即当前节点的下一个节点(即将当前要插入的节点从原有的链表中剪掉)
            lastSortedNode.next = currentNode.next;

            // 完成插入
            // 当前节点指向最后一个小于它的节点的孩子
            currentNode.next = offsetNode.next;
            // 最后一个小于它的几点,指向当前插入的几点
            offsetNode.next = currentNode;

            // 当前节点去最后一个已排序节点的下游节点,继续下一轮
            currentNode = lastSortedNode.next;
        }

        return headHead.next;
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
            Solution2 solution = new Solution2();
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
            Solution2 solution = new Solution2();
            ListNode newHead = solution.insertionSortList(head);
            System.out.println(newHead);
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)

