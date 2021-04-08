package pratice.leetcode.排序.P147对链表进行插入排序;

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
public class Solution3 {

    /*
    解题思路: 桶排序
    4:19 下午	info
				解答成功:
				执行耗时:2 ms,击败了99.08% 的Java用户
				内存消耗:38.4 MB,击败了12.75% 的Java用户
     */
    public ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        ListNode currentNode = head;
        int minValue = Integer.MAX_VALUE;
        int maxValue = Integer.MIN_VALUE;
        while (currentNode != null) {
            minValue = Math.min(minValue, currentNode.val);
            maxValue = Math.max(maxValue, currentNode.val);
            currentNode = currentNode.next;
        }
        ListNodeExd[] bucket = new ListNodeExd[maxValue - minValue + 1];
        currentNode = head;
        while (currentNode != null) {
            ListNodeExd listNodeExd = bucket[currentNode.val - minValue];
            if (listNodeExd == null) {
                bucket[currentNode.val - minValue] = new ListNodeExd(currentNode);
            } else {
                listNodeExd.tail.next = currentNode;
                listNodeExd.tail = listNodeExd.tail.next;
            }
            currentNode = currentNode.next;
        }
        ListNode headHead = new ListNode();
        ListNode tail = headHead;
        for (ListNodeExd listNodeExd : bucket) {
            if (listNodeExd == null) {
                continue;
            }
            tail.next = listNodeExd.head;
            tail = listNodeExd.tail;
        }
        tail.next = null;
        return headHead.next;
    }

    public static class ListNodeExd {
        ListNode head;
        ListNode tail;

        public ListNodeExd(ListNode head) {
            this.head = head;
            this.tail = head;
        }
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
            Solution3 solution = new Solution3();
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
            Solution3 solution = new Solution3();
            ListNode newHead = solution.insertionSortList(head);
            System.out.println(newHead);
        }
    }

    public static class Test3 {
        public static void main(String[] args) {
            String txt = "2->1";
            String[] values = txt.split("->");
            ListNode head = new ListNode(Integer.parseInt(values[0]));
            ListNode tail = head;
            for (int i = 1; i < values.length; i++) {
                ListNode node = new ListNode(Integer.parseInt(values[i]));
                tail.next = node;
                tail = node;
            }
            Solution3 solution = new Solution3();
            ListNode newHead = solution.insertionSortList(head);
            System.out.println(newHead);
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)

