package pratice.leetcode.树.P230二叉搜索树中第K小的元素;

/**
 * @author by catface
 * @date 2021/3/30 11:10 上午
 */
public class Solution {

    public static void main(String[] args) {
        // [5,3,6,2,4,null,null,1]
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);

        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);

        root.left.left.left = new TreeNode(1);

        Solution solution = new Solution();
        int kValue = solution.kthSmallest(root, 3);
        System.out.println(kValue);
    }

    public int kthSmallest(TreeNode root, int k) {
        Info info = new Info(0, null);
        process(root, k, info);
        return info.node.val;
    }

    private void process(TreeNode x, int k, Info info) {

        if (x == null) {
            return;
        }

        process(x.left, k, info);

        if (info.k == k) {
            return;
        }

        info.k = info.k + 1;
        if (info.k == k) {
            info.node = x;
            return;
        }

        process(x.right, k, info);
    }

    public static class Info {
        int k;
        TreeNode node;

        public Info(int k, TreeNode node) {
            this.k = k;
            this.node = node;
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
