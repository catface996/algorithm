package com.algorithm.leetcode.链表.P2两数相加;

//给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
//
// 请你将两个数相加，并以相同形式返回一个表示和的链表。
//
// 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
//
//
//
// 示例 1：
//
//
//输入：l1 = [2,4,3], l2 = [5,6,4]
//输出：[7,0,8]
//解释：342 + 465 = 807.
//
//
// 示例 2：
//
//
//输入：l1 = [0], l2 = [0]
//输出：[0]
//
//
// 示例 3：
//
//
//输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
//输出：[8,9,9,9,0,0,0,1]
//
//
//
//
// 提示：
//
//
// 每个链表中的节点数在范围 [1, 100] 内
// 0 <= Node.val <= 9
// 题目数据保证列表表示的数字不含前导零
//
// Related Topics 递归 链表 数学 👍 6851 👎 0

//leetcode submit region begin(Prohibit modification and deletion)

import org.junit.Test;

/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode next; ListNode() {} ListNode(int val) {
 * this.val = val; } ListNode(int val, ListNode next) { this.val = val; this.next = next; } }
 */
class Solution {

    /**
     * 注意,题目给出的测试用例迷惑性太强
     *
     * @param l1 第一个数字的逆序链表
     * @param l2 第二个数字的逆序链表
     * @return 结果的逆序链表
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode resultHead = new ListNode(-1);
        ListNode tail = resultHead;
        ListNode cur1 = l1;
        ListNode cur2 = l2;
        int j = 0;
        int tempResult;
        for (; ; ) {
            tempResult = 0;
            // 两个链表全部遍历结束,跳出循环
            if (cur1 == null && cur2 == null) {
                break;
            }

            if (cur1 != null) {
                tempResult += cur1.val;
                cur1 = cur1.next;
            }

            if (cur2 != null) {
                tempResult += cur2.val;
                cur2 = cur2.next;
            }

            // 计算结果
            tempResult += j;
            j = tempResult / 10;
            tail.next = new ListNode(tempResult % 10);
            tail = tail.next;
        }
        if (j == 1) {
            tail.next = new ListNode(1);
        }
        return resultHead.next;
    }

    public ListNode revertList(ListNode head) {
        ListNode rHead = null;
        ListNode cur = head;
        ListNode next;
        while (cur != null) {
            next = cur.next;
            cur.next = rHead;
            rHead = cur;
            cur = next;
        }
        return rHead;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {}

        ListNode(int val) {this.val = val;}

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

    }

    public static class TestClass {

        public static void print(ListNode head) {
            ListNode cur = head;
            while (cur != null) {
                System.out.print(cur.val + "->");
                cur = cur.next;
            }
        }

        public static ListNode convert(int[] arr) {
            ListNode head = new ListNode(arr[0]);
            ListNode cur = head;
            for (int i = 1; i < arr.length; i++) {
                cur.next = new ListNode(arr[i]);
                cur = cur.next;
            }
            return head;
        }

        @Test
        public void test1() {
            ListNode head = new ListNode(2);
            head.next = new ListNode(4);
            head.next.next = new ListNode(3);
            Solution solution = new Solution();
            ListNode rHead = solution.revertList(head);
            System.out.println(rHead.val);
        }

        // 				运行成功:
        //				测试用例:[2,4,3]
        //				[5,6,4]
        //				测试结果:[7,0,8]
        //				期望结果:[7,0,8]
        //				stdout:
        @Test
        public void test2() {
            ListNode l1 = convert(new int[] {2, 4, 3});
            ListNode l2 = convert(new int[] {5, 6, 4});
            Solution solution = new Solution();
            ListNode resultHead = solution.addTwoNumbers(l1, l2);
            print(resultHead);
        }

        // 				运行成功:
        //				测试用例:[9,9,9,9,9,9,9]
        //				[9,9,9,9]
        //				测试结果:[8,9,9,9,0,0,0,1]
        //				期望结果:[8,9,9,9,0,0,0,1]
        @Test
        public void test3() {
            ListNode l1 = convert(new int[] {9, 9, 9, 9, 9, 9, 9});
            ListNode l2 = convert(new int[] {9, 9, 9, 9});
            Solution solution = new Solution();
            ListNode resultHead = solution.addTwoNumbers(l1, l2);
            print(resultHead);
        }

        //				解答失败:
        //				测试用例:[2,4,9]
        //				[5,6,4,9]
        //				测试结果:[8,9,8,5]
        //				期望结果:[7,0,4,0,1]
        //				stdout:
        @Test
        public void test4() {
            ListNode l1 = convert(new int[] {2, 4, 9});
            ListNode l2 = convert(new int[] {5, 6, 4, 9});
            Solution solution = new Solution();
            ListNode resultHead = solution.addTwoNumbers(l1, l2);
            print(resultHead);
        }
    }
}

//leetcode submit region end(Prohibit modification and deletion)
