package com.algorithm.leetcode.树.P652寻找重复的子树;

//给定一棵二叉树，返回所有重复的子树。对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
//
// 两棵树重复是指它们具有相同的结构以及相同的结点值。
//
// 示例 1：
//
//         1
//       / \
//      2   3
//     /   / \
//    4   2   4
//       /
//      4
//
//
// 下面是两个重复的子树：
//
//       2
//     /
//    4
//
//
// 和
//
//     4
//
//
// 因此，你需要以列表的形式返回上述重复子树的根结点。
// Related Topics 树
// 👍 255 👎 0

//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode left; TreeNode right; TreeNode() {}
 * TreeNode(int val) { this.val = val; } TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left =
 * left; this.right = right; } }
 */
class Solution {

    /*
    12:29 上午	info
					解答成功:
					执行耗时:92 ms,击败了5.05% 的Java用户
					内存消耗:46.4 MB,击败了17.30% 的Java用户
     */
    Set<String> preOrderStrSet = new HashSet<>();
    Set<String> alreadyCount = new HashSet<>();
    List<TreeNode> ans = new ArrayList<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        process(root.left);
        process(root.right);
        return ans;
    }

    private void process(TreeNode x) {
        if (x == null) {
            return;
        }
        preOrderSerial(x);
        process(x.left);
        process(x.right);
    }

    private void preOrderSerial(TreeNode subTreeRoot) {
        StringBuilder sb = new StringBuilder();
        preOrderSerialProcess(subTreeRoot, sb);
        String serialStr = sb.toString();
        if (preOrderStrSet.contains(serialStr)) {
            // 重复出现
            if (alreadyCount.contains(serialStr)) {
                // 已统计
                return;
            }
            // 未曾统计
            alreadyCount.add(serialStr);
            ans.add(subTreeRoot);
            return;
        }
        // 未曾出现
        preOrderStrSet.add(serialStr);
    }

    private void preOrderSerialProcess(TreeNode x, StringBuilder sb) {
        if (x == null) {
            sb.append('#').append(',');
            return;
        }
        sb.append(x.val).append(',');
        preOrderSerialProcess(x.left, sb);
        preOrderSerialProcess(x.right, sb);
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
        // 示例 1：
        //         1
        //       / \
        //      2   3
        //     /   / \
        //    4   2   4
        //       /
        //      4
        // 下面是两个重复的子树：
        //       2
        //     /
        //    4
        // 和
        //     4
        // 因此，你需要以列表的形式返回上述重复子树的根结点。
        public static void main(String[] args) {
            TreeNode root = new TreeNode(1);
            root.left = new TreeNode(2);
            root.right = new TreeNode(3);
            root.left.left = new TreeNode(4);
            root.right.left = new TreeNode(2);
            root.right.right = new TreeNode(4);
            root.right.left.left = new TreeNode(4);
            Solution solution = new Solution();
            List<TreeNode> ans = solution.findDuplicateSubtrees(root);
            System.out.println(ans);
        }
    }

    public static class Test2 {
        //测试用例:[2,1,3,2,null,1,null,null,null,2,null]
        //期望结果:[[1,2],[2]]
        public static void main(String[] args) {
            TreeNode root = new TreeNode(2);
            root.left = new TreeNode(1);
            root.right = new TreeNode(3);
            root.left.left = new TreeNode(2);
            root.right.left = new TreeNode(1);
            root.right.left.left = new TreeNode(2);
            Solution solution = new Solution();
            List<TreeNode> ans = solution.findDuplicateSubtrees(root);
            System.out.println(ans);
        }
    }

    public static class Test3 {
        // 	测试用例:[0,0,0,0,null,null,0,null,null,null,0]
        //	期望结果:[[0]]
        public static void main(String[] args) {
            TreeNode root = new TreeNode(0);
            root.left = new TreeNode(0);
            root.right = new TreeNode(0);
            root.left.left = new TreeNode(0);
            root.right.right = new TreeNode(0);
            root.right.right.right = new TreeNode(0);
            Solution solution = new Solution();
            List<TreeNode> ans = solution.findDuplicateSubtrees(root);
            System.out.println(ans);
        }
    }

    public static class Test4{
        public static void main(String[] args){
            Solution solution = new Solution();
            List<TreeNode> ans = solution.findDuplicateSubtrees(null);
            System.out.println(ans);
        }
    }
}
