package pratice.leetcode.树.P530二叉搜索树的最小绝对值;

/**
 * @author by catface
 * @date 2021/4/1 11:04 下午
 */
public class Solution2 {

    //TODO 需要提升

    public static void main(String[] args) {
        // [1,null,5,3]
        TreeNode root = new TreeNode(1);
        root.left = null;
        root.right = new TreeNode(5);
        root.right.left = new TreeNode(3);

        // :[5,4,7]  1
        //TreeNode root = new TreeNode(5);
        //root.left = new TreeNode(4);
        //root.right = new TreeNode(7);

        // [236,104,701,null,227,null,911]  9
        //TreeNode root = new TreeNode(236);
        //root.left = new TreeNode(104);
        //root.right = new TreeNode(701);
        //root.left.left = null;
        //root.left.right = new TreeNode(227);
        //root.right.left = null;
        //root.right.right = new TreeNode(911);
        Solution2 solution = new Solution2();
        int minDifference = solution.getMinimumDifference(root);
        System.out.println(minDifference);
    }

    public int getMinimumDifference(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 0;
        }
        return process(root).minDifference;
    }

    /**
     * 绝对值最小,说明两个数越是接近
     * <p>
     * 核心计算逻辑,任何一棵树,要找与根几点的值的差值最小的两个节点做差值,然后取绝对值.
     * <p>
     * 根节点 与左子树的最大值最接近,因为根节点的值,大于左子树的所有节点值
     * <p>
     * 根节点 与右子树的最小值最接近,因为根节点的值小于右子树的所有节点值
     * <p>
     * 分析完成,so easy
     * <p>
     * 11:10 上午	info
     * <p>
     * 解答成功:
     * <p>
     * 执行耗时:1 ms,击败了71.55% 的Java用户
     * <p>
     * 内存消耗:39.6 MB,击败了5.21% 的Java用户
     *
     * @param x
     * @return 对左右两颗子树提要求, 需要子树返回差值的最小绝对值即可.
     */
    private Info process(TreeNode x) {
        if (x == null) {
            return null;
        }

        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);

        Integer maxValue = x.val;
        Integer minValue = x.val;
        int minDifference = Integer.MAX_VALUE;

        if (rightInfo != null) {
            maxValue = rightInfo.maxValue;
        }

        if (leftInfo != null) {
            minValue = leftInfo.minValue;
        }

        if (leftInfo != null) {
            int differenceBetweenParentLeftSubTree = Math.abs(x.val - leftInfo.maxValue);
            minDifference = Math.min(minDifference, differenceBetweenParentLeftSubTree);
            minDifference = Math.min(minDifference, leftInfo.minDifference);
        }

        if (rightInfo != null) {
            int differenceBetweenParentRightSubTree = Math.abs(x.val - rightInfo.minValue);
            minDifference = Math.min(minDifference, differenceBetweenParentRightSubTree);
            minDifference = Math.min(minDifference, rightInfo.minDifference);
        }

        return new Info(maxValue, minValue, minDifference);
    }

    public static class Info {

        // 如果你是我的左孩子,请你告诉我,以你为根节点的整棵树的最大值
        // 左子树的最大值最接近parent节点的值
        Integer maxValue;

        // 如果你是我的右孩子,请你告诉我,已你为根节点的整棵树的最小值
        // 右子树的最小值最接近parent节点的值
        Integer minValue;

        // 请告诉我,以你为根节点的子树中的最小差值的绝对值
        Integer minDifference;

        public Info(Integer maxValue, Integer minValue, Integer minDifference) {
            this.maxValue = maxValue;
            this.minValue = minValue;
            this.minDifference = minDifference;
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) { val = x; }
    }
}
