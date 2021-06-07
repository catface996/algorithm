package com.algorithm.leetcode.树.P1008前序遍历构造二叉搜索树;

//返回与给定前序遍历 preorder 相匹配的二叉搜索树（binary search tree）的根结点。
//
// (回想一下，二叉搜索树是二叉树的一种，其每个节点都满足以下规则，对于 node.left 的任何后代，值总 < node.val，而 node.right
// 的任何后代，值总 > node.val。此外，前序遍历首先显示节点 node 的值，然后遍历 node.left，接着遍历 node.right。）
//
// 题目保证，对于给定的测试用例，总能找到满足要求的二叉搜索树。
//
//
//
// 示例：
//
// 输入：[8,5,1,7,10,12]
//输出：[8,5,10,1,7,null,12]
//
//
//
//
//
// 提示：
//
//
// 1 <= preorder.length <= 100
// 1 <= preorder[i] <= 10^8
// preorder 中的值互不相同
//
// Related Topics 树
// 👍 147 👎 0

//leetcode submit region begin(Prohibit modification and deletion)

import com.algorithm.util.PrintTreeNodeUtil;
import com.algorithm.util.TreeNode;
import org.junit.Test;

/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode left; TreeNode right; TreeNode() {}
 * TreeNode(int val) { this.val = val; } TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left =
 * left; this.right = right; } }
 */
public class Solution {

    // 11:21 上午	info
    //				解答成功:
    //				执行耗时:0 ms,击败了100.00% 的Java用户
    //				内存消耗:36.4 MB,击败了53.56% 的Java用户

    public TreeNode bstFromPreorder(int[] preorder) {
        if (preorder == null) {
            return null;
        }
        return buildTreeNode(preorder, 0, preorder.length - 1);
    }

    private TreeNode buildTreeNode(int[] arr, int start, int end) {
        if (start == end) {
            return new TreeNode(arr[start]);
        }
        if (start > end) {
            return null;
        }
        TreeNode head = new TreeNode(arr[start]);
        int firstBiggerIndex = findFirstBiggerIndex(arr, start + 1, end, arr[start]);
        head.right = buildTreeNode(arr, firstBiggerIndex, end);
        head.left = buildTreeNode(arr, start + 1, firstBiggerIndex - 1);
        return head;
    }

    private int findFirstBiggerIndex(int[] arr, int start, int end, int num) {
        if (start == end) {
            if (arr[start] > num) {
                return start;
            } else {
                return start + 1;
            }
        }
        int mid = (start + end) / 2;
        if (arr[mid] <= num) {
            return findFirstBiggerIndex(arr, mid + 1, end, num);
        } else {
            return findFirstBiggerIndex(arr, start, mid, num);
        }
    }

    public static class TestClass {

        @Test
        public void testFindFirstBigger() {
            int[] arr = new int[10];
            for (int i = 0; i < 10; i++) {
                arr[i] = i;
            }
            Solution solution = new Solution();
            for (int i = 0; i < 10; i++) {
                int firstBiggerIndex = solution.findFirstBiggerIndex(arr, 0, arr.length - 1, i);
                System.out.println(firstBiggerIndex);
            }
        }

        @Test
        public void test1() {
            int[] arr = {8, 5, 1, 7, 10, 12};
            Solution solution = new Solution();
            TreeNode root = solution.bstFromPreorder(arr);
            PrintTreeNodeUtil.printTree(root);
        }

        //11:16 上午	info
        //				解答失败:
        //				测试用例:[4,2]
        //				测试结果:[4]
        //				期望结果:[4,2]
        @Test
        public void test2() {
            int[] arr = {4, 2};
            Solution solution = new Solution();
            TreeNode root = solution.bstFromPreorder(arr);
            PrintTreeNodeUtil.printTree(root);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
