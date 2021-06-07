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

class Solution3 {

    /*
    8:04 下午	info
				解答成功:
				执行耗时:2 ms,击败了99.04% 的Java用户
				内存消耗:40.3 MB,击败了63.01% 的Java用户
     */
    List<Info> sumCountArray = new ArrayList<>();

    public List<Double> averageOfLevels(TreeNode root) {
        process(root, 0);
        List<Double> ans = new ArrayList<>(sumCountArray.size());
        sumCountArray.forEach(info->{
            ans.add((double)info.sum/info.count);
        });
        return ans;
    }

    private void process(TreeNode x, int depth) {
        if (x == null) {
            return;
        }
        if (depth == sumCountArray.size()) {
            sumCountArray.add(new Info(x.val,1));
        } else {
            Info info = sumCountArray.get(depth);
            info.count++;
            info.sum+=x.val;
        }
        process(x.left, ++depth);
        process(x.right, depth);
    }

    public static class Info{
        private long sum;
        private int count;

        public Info(long sum, int count) {
            this.sum = sum;
            this.count = count;
        }
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
            Solution3 solution = new Solution3();
            List<Double> ans = solution.averageOfLevels(root);
            System.out.println(ans);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
