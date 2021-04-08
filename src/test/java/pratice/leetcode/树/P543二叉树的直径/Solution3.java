package pratice.leetcode.树.P543二叉树的直径;

/**
 * @author by catface
 * @date 2021/4/2 2:58 下午
 */
public class Solution3 {

    int maxPath = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        process(root);
        return maxPath;
    }

    private Integer process(TreeNode x) {
        /*
        5:14 下午	info
				解答成功:
				执行耗时:0 ms,击败了100.00% 的Java用户
				内存消耗:38.5 MB,击败了38.28% 的Java用户
         */
        if (x == null) {
            return -1;
        }
        // 经过根节点,贯通左右子树的最大路径
        int leftHeight = process(x.left);
        int rightHeight = process(x.right);
        // 当前节点的高度
        maxPath = Math.max(maxPath, leftHeight + rightHeight + 2);
        return Math.max(leftHeight, rightHeight) + 1;
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

    public static class Test {
        public static void main(String[] args) {
            // [1,2,3,4,5]
            TreeNode root = new TreeNode(1);
            root.left = new TreeNode(2);
            root.right = new TreeNode(3);
            root.left.left = new TreeNode(4);
            root.left.right = new TreeNode(5);
            Solution3 solution = new Solution3();
            int maxPath = solution.diameterOfBinaryTree(root);
            System.out.println(maxPath);
        }
    }
}
