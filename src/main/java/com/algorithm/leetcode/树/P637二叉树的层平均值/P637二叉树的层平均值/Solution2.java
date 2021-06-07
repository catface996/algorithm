package com.algorithm.leetcode.树.P637二叉树的层平均值.P637二叉树的层平均值;
//给定一个非空二叉树, 返回一个由每层节点平均值组成的数组。 
//
// 
//
// 示例 1： 
//
// 输入：
//    3
//   / \
//  9  20
//    /  \
//   15   7
//输出：[3, 14.5, 11]
//解释：
//第 0 层的平均值是 3 ,  第1层是 14.5 , 第2层是 11 。因此返回 [3, 14.5, 11] 。
// 
//
// 
//
// 提示： 
//
// 
// 节点值的范围在32位有符号整数范围内。 
// 
// Related Topics 树 
// 👍 247 👎 0

//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.List;

class Solution2 {

    /*
    8:04 下午	info
				解答成功:
				执行耗时:2 ms,击败了99.04% 的Java用户
				内存消耗:40.3 MB,击败了63.01% 的Java用户
     */
    List<Long> sumArray = new ArrayList<>();
    List<Integer> countArray = new ArrayList<>();

    public List<Double> averageOfLevels(TreeNode root) {
        process(root, 0);
        List<Double> ans = new ArrayList<>(sumArray.size());
        for (int i = 0; i < sumArray.size(); i++) {
            ans.add(1.0 * sumArray.get(i) / countArray.get(i));
        }
        return ans;
    }

    private void process(TreeNode x, int depth) {
        if (x == null) {
            return;
        }
        if (depth == sumArray.size()) {
            sumArray.add((long)x.val);
            countArray.add(1);
        } else {
            sumArray.set(depth, sumArray.get(depth) + x.val);
            countArray.set(depth, countArray.get(depth) + 1);
        }
        process(x.left, ++depth);
        process(x.right, depth);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {}

        TreeNode(int val) { this.val = val; }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static class Test1 {
        public static void main(String[] args) {
            // 示例 1：
            //
            // 输入：
            //    3
            //   / \
            //  9  20
            //    /  \
            //   15   7
            //输出：[3, 14.5, 11]
            //解释：
            //第 0 层的平均值是 3 ,  第1层是 14.5 , 第2层是 11 。因此返回 [3, 14.5, 11] 。
            TreeNode root = new TreeNode(3);
            root.left = new TreeNode(9);
            root.right = new TreeNode(20);
            root.right.left = new TreeNode(15);
            root.right.right = new TreeNode(7);
            Solution2 solution = new Solution2();
            List<Double> ans = solution.averageOfLevels(root);
            System.out.println(ans);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
