package com.algorithm.leetcode.字符串.P6Z字形变换;
//将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
//
// 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
//
//
//P   A   H   N
//A P L S I I G
//Y   I   R
//
// 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
//
// 请你实现这个将字符串进行指定行数变换的函数：
//
//
//string convert(string s, int numRows);
//
//
//
// 示例 1：
//
//
//输入：s = "PAYPALISHIRING", numRows = 3
//输出："PAHNAPLSIIGYIR"
//
//示例 2：
//
//
//输入：s = "PAYPALISHIRING", numRows = 4
//输出："PINALSIGYAHRPI"
//解释：
//P     I    N
//A   L S  I G
//Y A   H R
//P     I
//
//
// 示例 3：
//
//
//输入：s = "A", numRows = 1
//输出："A"
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 1000
// s 由英文字母（小写和大写）、',' 和 '.' 组成
// 1 <= numRows <= 1000
//
// Related Topics 字符串 👍 1298 👎 0

import org.junit.Test;

//leetcode submit region begin(Prohibit modification and deletion)
public class Solution {

    /**
     * 解题思路
     * <p>
     * 散列表,分几行,就有几个bucket,每个bucket是一个链表,周期性类似于sin行数 分三行时,分别是 0,1,2,1,0,1,2,1,0,1,2,1
     *
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        if (numRows <= 1) {
            return s;
        }
        ListInfo[] arr = new ListInfo[numRows];
        for (int i = 0; i < numRows; i++) {
            arr[i] = new ListInfo();
        }
        int[] cycleArr = buildCycleArr(numRows);
        int index;
        ListInfo curInfo;
        for (int i = 0; i < s.length(); i++) {
            index = cycleArr[i % cycleArr.length];
            curInfo = arr[index];
            if (curInfo.head == null) {
                curInfo.head = new ListNode(s.charAt(i));
                curInfo.tail = curInfo.head;
            } else {
                curInfo.tail.next = new ListNode(s.charAt(i));
                curInfo.tail = curInfo.tail.next;
            }
        }
        ListNode cur;
        StringBuilder sb = new StringBuilder(s.length());
        for (ListInfo listInfo : arr) {
            cur = listInfo.head;
            while (cur != null) {
                sb.append(cur.val);
                cur = cur.next;
            }
        }
        return sb.toString();
    }

    public int[] buildCycleArr(int numRows) {
        int[] cycleArr = new int[numRows * 2 - 2];
        int index = 0;
        int value = 0;
        while (index < numRows) {
            cycleArr[index] = value;
            value++;
            index++;
        }
        index = 0;
        value -= 2;
        while (value > 0) {
            cycleArr[numRows + index] = value;
            index++;
            value--;
        }
        return cycleArr;
    }

    public static class ListInfo {
        private ListNode head;
        private ListNode tail;

        public ListNode getHead() {
            return head;
        }

        public void setHead(ListNode head) {
            this.head = head;
        }

        public ListNode getTail() {
            return tail;
        }

        public void setTail(ListNode tail) {
            this.tail = tail;
        }
    }

    public static class ListNode {
        private char val;
        private ListNode next;

        public ListNode(char val) {
            this.val = val;
        }

        public char getVal() {
            return val;
        }

        public void setVal(char val) {
            this.val = val;
        }

        public ListNode getNext() {
            return next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }
    }

    public static class TestClass {
        @Test
        public void test1() {
            Solution solution = new Solution();
            int[] cycleArr = solution.buildCycleArr(3);
            System.out.println(cycleArr.length);
        }

        @Test
        public void test2() {
            Solution solution = new Solution();
            int[] cycleArr = solution.buildCycleArr(4);
            System.out.println(cycleArr.length);
        }

        @Test
        public void test3() {
            String s = "PAYPALISHIRING";
            Solution solution = new Solution();
            String ans = solution.convert(s, 3);
            System.out.println(ans);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

