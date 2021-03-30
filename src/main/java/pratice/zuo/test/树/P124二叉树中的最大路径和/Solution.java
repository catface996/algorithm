package pratice.zuo.test.树.P124二叉树中的最大路径和;

/**
 * @author by catface
 * @date 2021/3/30 8:14 下午
 */
public class Solution {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(-1);
        //root.right = new TreeNode(3);
        //root.right.left = new TreeNode(15);
        //root.right.right = new TreeNode(7);
        Solution solution = new Solution();
        solution.maxPathSum(root);
    }

    public int maxPathSum(TreeNode root) {
        int maxSum = process(root).totalMax;
        return maxSum;
    }

    private Info process(TreeNode x) {
        if (x == null) {
            return null;
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);

        // 经过根节点的路径
        int subMax = x.val;
        int totalMax = x.val;
        // 左子树 + 根 + 右子树
        if (leftInfo != null && rightInfo != null) {
            subMax += Math.max(leftInfo.subMax, rightInfo.subMax);
            int allSum = leftInfo.subMax + x.val + rightInfo.subMax;
            totalMax = Math.max(totalMax, allSum);
            totalMax = Math.max(totalMax, leftInfo.totalMax);
            totalMax = Math.max(totalMax, rightInfo.totalMax);
        } else if (leftInfo == null && rightInfo != null) {
            subMax += rightInfo.subMax;
            totalMax = Math.max(totalMax, rightInfo.totalMax);
        } else if (leftInfo != null && rightInfo == null) {
            subMax += leftInfo.subMax;
            totalMax = Math.max(totalMax, leftInfo.totalMax);
        }

        // 如果只保留当前节点
        subMax = Math.max(subMax, x.val);
        // 不论是否经过根节点,比较下根节点触发的最大和
        totalMax = Math.max(totalMax, subMax);

        return new Info(subMax, totalMax);
    }

    public static class Info {
        int subMax;
        int totalMax;

        public Info(int subMax, int totalMax) {
            this.subMax = subMax;
            this.totalMax = totalMax;
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
}
