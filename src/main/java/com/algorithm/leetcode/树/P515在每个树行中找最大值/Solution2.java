package com.algorithm.leetcode.树.P515在每个树行中找最大值;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by catface
 * @date 2021/4/1 5:21 下午
 */
public class Solution2 {

    public static void main(String[] args) {
        //[1,3,2,5,3,null,9]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(3);
        root.right.left = null;
        root.right.right = new TreeNode(9);

        // [0,-2147483648,2147483647]
        //TreeNode root = new TreeNode(0);
        //root.left = new TreeNode(-2147483648);
        //root.right = new TreeNode(2147483647);
        Solution2 solution = new Solution2();
        List<Integer> ans = solution.largestValues(root);
        System.out.println(ans);
    }

    //TODO 11:14 下午	info
    //				解答成功:
    //				执行耗时:1 ms,击败了100.00% 的Java用户
    //				内存消耗:39.1 MB,击败了5.05% 的Java用户
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        process(root, 0, ans);
        return ans;
    }

    private void process(TreeNode x, int depth, List<Integer> ans) {
        if (x == null) {
            return;
        }
        if (ans.size() - 1 < depth) {
            ans.add(x.val);
        } else {
            int max = Math.max(x.val, ans.get(depth));
            ans.set(depth, max);
        }
        process(x.left, depth + 1, ans);
        process(x.right, depth + 1, ans);
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

}
