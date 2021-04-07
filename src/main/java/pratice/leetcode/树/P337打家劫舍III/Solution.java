package pratice.leetcode.树.P337打家劫舍III;

/**
 * @author by catface
 * @date 2021/3/31 11:48 上午
 */
public class Solution {

    // TODO 2:03 下午	info
    //				解答成功:
    //				执行耗时:1 ms,击败了81.75% 的Java用户
    //				内存消耗:38.2 MB,击败了45.24% 的Java用户

    public static void main(String[] args) {
        test_1();
    }

    // [3,2,3,null,3,null,1]
    public static void test_1() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(1);
        Solution solution = new Solution();
        int robNum = solution.rob(root);
        System.out.println(robNum);
    }

    public int rob(TreeNode root) {
        return process(root).max;
    }

    private Info process(TreeNode x) {
        if (x == null) {
            return new Info(0, 0);
        }
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);

        // 当前节点抢劫,子节点一定不能抢劫
        int robNum = x.val + leftInfo.notRobNum + rightInfo.notRobNum;

        // 当前节点不抢劫,后续节点可以抢劫,也可以不抢劫,故而取下一层的最大值即可
        int notRobNum = leftInfo.max + rightInfo.max;

        int max = Math.max(robNum, notRobNum);

        return new Info(notRobNum, max);
    }

    public static class Info {

        int notRobNum;
        int max;

        public Info(int notRobNum, int max) {
            this.notRobNum = notRobNum;
            this.max = max;
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
