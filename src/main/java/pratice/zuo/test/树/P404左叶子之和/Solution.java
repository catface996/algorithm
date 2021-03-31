package pratice.zuo.test.树.P404左叶子之和;

/**
 * @author by catface
 * @date 2021/3/31 2:07 下午
 */
public class Solution {

    public static void main(String[] args) {
        test_1();
    }

    // [3,9,20,null,null,15,7]
    public static void test_1() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        Solution solution = new Solution();
        int sum = solution.sumOfLeftLeaves(root);
        System.out.println(sum);
    }

    int sum = 0;

    public int sumOfLeftLeaves(TreeNode root) {
        process(root);
        return sum;
    }

    private void process(TreeNode x) {
        if (x == null) {
            return;
        }
        if (x.left != null && x.left.left == null && x.left.right == null) {
            sum += x.left.val;
        } else {
            process(x.left);
        }
        process(x.right);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) { val = x; }
    }
}
