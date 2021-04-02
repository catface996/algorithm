package pratice.zuo.test.树.P530二叉搜索树的最小绝对值;

/**
 * @author by catface
 * @date 2021/4/1 11:04 下午
 */
public class Solution3 {

    int minAbs = Integer.MAX_VALUE;
    TreeNode lastNode = new TreeNode(Integer.MAX_VALUE);

    public int getMinimumDifference(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 0;
        }
        process(root);
        return minAbs;
    }

    private void process(TreeNode x) {
        /*
         * 绝对值最小,说明两个数越是接近
         *
         * 把二叉树按中序遍历,得到数组 source[]
         * int minAbs = Integer.MAX_VALUE;
         * int tempAbs = source[i] - source[i-1]
         * minAbs=Math.min(minAbs,tempAbs);
         * 将该算法改写成搜索二叉树的中序遍历即可
         */
        if (x == null) {
            return;
        }

        // 优先处理左子树
        process(x.left);

        // 计算当前节点与上一个节点的差值绝对值,并与全局最小值比较,取最小
        minAbs = Math.min(minAbs, Math.abs(x.val - lastNode.val));
        lastNode = x;

        // 处理右侧节点
        process(x.right);

    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //TODO 需要提升
    public static class Test {
        public static void main(String[] args) {
            // [1,null,5,3] 2
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
            Solution3 solution = new Solution3();
            int minDifference = solution.getMinimumDifference(root);
            System.out.println(minDifference);
        }
    }
}
