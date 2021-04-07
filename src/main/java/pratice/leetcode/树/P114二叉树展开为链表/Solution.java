package pratice.leetcode.树.P114二叉树展开为链表;

/**
 * @author by catface
 * @date 2021/3/29 4:54 下午
 */
public class Solution {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);

        root.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        Solution solution = new Solution();
        TreeNode head = new TreeNode();
        solution.process(root, head);
        System.out.println(head);
    }

    public void flatten(TreeNode root) {
        TreeNode head = new TreeNode();
        process(root, head);
    }

    /**
     * 算法描述:
     * <p>
     * 展开链表为先序遍历,即 中->左->右
     * <p>
     * 只需要用 右->中->左的遍历顺序,并且通过头插法,即可完成
     *
     * @param x    当前待处理的节点
     * @param head 链表的头结点
     */
    private void process(TreeNode x, TreeNode head) {
        if (x == null) {
            return;
        }

        process(x.right, head);

        process(x.left, head);

        x.right = head.right;
        head.right = x;
        x.left = null;

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
