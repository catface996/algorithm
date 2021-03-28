package pratice.zuo.test.树.P101对称二叉树;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author by catface
 * @date 2021/3/28 2:36 下午
 */
public class Solution {

    //TODO 				解答成功:
    //				执行耗时:1 ms,击败了28.04% 的Java用户
    //				内存消耗:37.9 MB,击败了6.86% 的Java用户

    public static void main(String[] args) {
        test_2();
    }

    public static void test_1() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(3);
        Solution solution = new Solution();
        boolean result = solution.isSymmetric(root);
        System.out.println(result);
    }

    public static void test_2() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(2);
        root.right.left = new TreeNode(2);
        Solution solution = new Solution();
        boolean result = solution.isSymmetric(root);
        System.out.println(result);
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        Queue<Integer> leftTravel = new LinkedList<>();
        leftRootRight(root.left, leftTravel);
        return rightMiddleLeft(root.right, leftTravel);
    }

    private void leftRootRight(TreeNode x, Queue<Integer> travel) {
        if (x == null) {
            travel.add(null);
            return;
        }

        travel.add(x.val);

        leftRootRight(x.left, travel);

        leftRootRight(x.right, travel);

    }

    private boolean rightMiddleLeft(TreeNode x, Queue<Integer> travel) {
        if (x == null) {
            Integer value = travel.poll();
            return value == null;
        }

        Integer value = travel.poll();
        boolean isSame = same(x.val, value);
        if (!isSame) {
            return false;
        }

        boolean rightSame = rightMiddleLeft(x.right, travel);
        if (!rightSame) {
            return false;
        }

        return rightMiddleLeft(x.left, travel);
    }

    private boolean same(Integer leftValue, Integer rightValue) {
        if (leftValue == null && rightValue == null) {
            return true;
        }
        if (leftValue == null) {
            return false;
        }
        if (rightValue == null) {
            return false;
        }
        return leftValue.compareTo(rightValue) == 0;
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
