package pratice.leetcode.树.P543二叉树的直径;

/**
 * @author by catface
 * @date 2021/4/2 2:58 下午
 */
public class Solution2 {

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Info info = process(root);
        return Math.max(info.height, info.maxPath);
    }

    private Info process(TreeNode x) {
        //TODO 需要优化
        /*
        3:55 下午	info
				解答成功:
				执行耗时:1 ms,击败了17.60% 的Java用户
				内存消耗:38.2 MB,击败了90.09% 的Java用户
         */
        if (x == null) {
            return new Info(-1, 0);
        }
        // 经过根节点,贯通左右子树的最大路径
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        // 当前节点的高度
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int max = Math.max(leftInfo.maxPath, rightInfo.maxPath);
        int leftParentRightPath = leftInfo.height + rightInfo.height + 2;
        max = Math.max(max,leftParentRightPath);
        return new Info(height,max);
    }

    public static class Info {

        // 最大路径要么出现在子树中,不经过root节点,要么经过root节点
        // 经过父节点,且父节点是终点的子树最大路径
        int height;

        // 不论是否经过子树根节点的最大路径
        int maxPath;

        public Info(int height, int maxPath) {
            this.height = height;
            this.maxPath = maxPath;
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

    public static class Test {
        public static void main(String[] args) {
            // [1,2,3,4,5]
            TreeNode root = new TreeNode(1);
            root.left = new TreeNode(2);
            root.right = new TreeNode(3);
            root.left.left = new TreeNode(4);
            root.left.right = new TreeNode(5);
            Solution2 solution = new Solution2();
            int maxPath = solution.diameterOfBinaryTree(root);
            System.out.println(maxPath);
        }
    }
}
