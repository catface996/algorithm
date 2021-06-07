package com.algorithm.leetcode.动态规划.P968监控二叉树;

//给定一个二叉树，我们在树的节点上安装摄像头。
//
// 节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。
//
// 计算监控树的所有节点所需的最小摄像头数量。
//
//
//
// 示例 1：
//
//
//
// 输入：[0,0,null,0,0]
//输出：1
//解释：如图所示，一台摄像头足以监控所有节点。
//
//
// 示例 2：
//
//
//
// 输入：[0,0,null,0,null,0,null,null,0]
//输出：2
//解释：需要至少两个摄像头来监视树的所有节点。 上图显示了摄像头放置的有效位置之一。
//
//
//
//提示：
//
//
// 给定树的节点数的范围是 [1, 1000]。
// 每个节点的值都是 0。
//
// Related Topics 树 深度优先搜索 动态规划
// 👍 294 👎 0

//leetcode submit region begin(Prohibit modification and deletion)

import com.algorithm.util.PrintTreeNodeUtil;
import com.algorithm.util.TreeConvert;
import com.algorithm.util.TreeNode;
import org.junit.Test;

/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode left; TreeNode right; TreeNode() {}
 * TreeNode(int val) { this.val = val; } TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left =
 * left; this.right = right; } }
 */
public class Solution {

    // 11:49 上午	info
    //					解答成功:
    //					执行耗时:1 ms,击败了47.74% 的Java用户
    //					内存消耗:38.1 MB,击败了69.45% 的Java用户

    public int minCameraCover(TreeNode root) {
        Info info = process(root);
        // 如果根节点未被监控,只能在根节点上防止相机
        if (!info.monitor) {
            return info.cameraNum + 1;
        }
        return info.cameraNum;
    }

    private Info process(TreeNode x) {
        if (x == null) {
            return new Info(true, false, 0);
        }

        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        Info info = new Info(false, false, 0);

        // 任意一个孩子没有被监控,当前节点必须放相机,否则,未被监控的孩子将永远监控不到
        if (!leftInfo.monitor || !rightInfo.monitor) {
            info.monitor = true;
            info.hasCamera = true;
            info.cameraNum = leftInfo.cameraNum + rightInfo.cameraNum + 1;
            return info;
        }
        // 任何一个孩子有摄像机,当前节点就不需要防止摄像机(贪心)
        if (leftInfo.hasCamera || rightInfo.hasCamera) {
            info.monitor = true;
            info.hasCamera = false;
            info.cameraNum = leftInfo.cameraNum + rightInfo.cameraNum;
            return info;
        }

        // 所有孩子都没有放置摄像机,但是却被监控了,当前节点设置无相机,未监控,由上层决策
        info.monitor = false;
        info.hasCamera = false;
        info.cameraNum = leftInfo.cameraNum + rightInfo.cameraNum;
        return info;
    }

    public static class Info {
        private boolean monitor;
        private boolean hasCamera;
        private int cameraNum;

        public Info(boolean monitor, boolean hasCamera, int cameraNum) {
            this.monitor = monitor;
            this.hasCamera = hasCamera;
            this.cameraNum = cameraNum;
        }
    }

    public static class TestClass {

        // 示例 1：
        //
        //
        //
        // 输入：[0,0,null,0,0]
        //输出：1
        //解释：如图所示，一台摄像头足以监控所有节点。
        @Test
        public void test1() {
            Integer[] arr = {0, 0, null, 0, 0};
            TreeNode root = TreeConvert.convert(arr);
            Solution solution = new Solution();
            int cameras = solution.minCameraCover(root);
            System.out.println(cameras);
        }

        // 示例 2：
        //
        //
        //
        // 输入：[0,0,null,0,null,0,null,null,0]
        //输出：2
        //解释：需要至少两个摄像头来监视树的所有节点。 上图显示了摄像头放置的有效位置之一。
        @Test
        public void test2() {
            Integer[] arr = {0, 0, null, 0, null, 0, null, null, 0};
            TreeNode root = TreeConvert.convert(arr);
            PrintTreeNodeUtil.printTree(root);
            Solution solution = new Solution();
            int cameras = solution.minCameraCover(root);
            System.out.println(cameras);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

