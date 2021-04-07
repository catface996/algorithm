package pratice.leetcode.树.P437路径总和;

/**
 * @author by catface
 * @date 2021/3/31 3:27 下午
 */
public class Solution3 {

    //TODO 3:40 下午	info: 等待结果超时
    public static void main(String[] args) {
        test_1();
    }

    // [10,5,-3,3,2,null,11,3,-2,null,1]
    public static void test_1() {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.right.right = new TreeNode(11);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);
        root.left.right.right = new TreeNode(1);
        Solution3 solution3 = new Solution3();
        int total = solution3.pathSum(root, 8);
        System.out.println(total);
    }

    public int pathSum(TreeNode root, int sum) {
        return process(root, 0, sum);
    }

    private int process(TreeNode x, int preSum, int sum) {
        if (x == null) {
            return 0;
        }
        int totalCount = 0;
        int newPreSum = preSum + x.val;
        if (newPreSum == sum) {
            totalCount++;
        }
        int leftNumWithParent = process(x.left, newPreSum, sum);
        int leftNumWithoutParent = process(x.left, 0, sum);
        int rightNumWithParent = process(x.right, newPreSum, sum);
        int rightNumWithoutParent = process(x.right, 0, sum);
        return totalCount + leftNumWithParent + leftNumWithoutParent + rightNumWithParent + rightNumWithoutParent;
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
