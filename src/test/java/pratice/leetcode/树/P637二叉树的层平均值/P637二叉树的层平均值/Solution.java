package pratice.leetcode.树.P637二叉树的层平均值.P637二叉树的层平均值;
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
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {

    /*
   7:45 下午	info
           解答成功:
           执行耗时:3 ms,击败了60.60% 的Java用户
           内存消耗:40 MB,击败了95.25% 的Java用户
    */
    public List<Double> averageOfLevels(TreeNode root) {
        TreeNode endNode = root;
        TreeNode nextNode = root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        double tempSum = 0;
        int tempCount = 0;
        List<Double> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode currentNode = queue.poll();
            tempCount++;
            tempSum += currentNode.val;

            if (currentNode.left != null) {
                nextNode = currentNode.left;
                queue.add(nextNode);

            }
            if (currentNode.right != null) {
                nextNode = currentNode.right;
                queue.add(nextNode);
            }
            if (currentNode == endNode) {
                ans.add(tempSum / tempCount);
                tempCount = 0;
                tempSum = 0;
                endNode = nextNode;
            }
        }
        return ans;
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
            Solution solution = new Solution();
            List<Double> ans = solution.averageOfLevels(root);
            System.out.println(ans);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
