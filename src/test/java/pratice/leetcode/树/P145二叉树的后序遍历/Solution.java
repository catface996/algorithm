package pratice.leetcode.树.P145二叉树的后序遍历;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by catface
 * @date 2021/3/28 6:27 下午
 */
public class Solution {

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        process(root, ans);
        return ans;
    }

    private void process(TreeNode x, List<Integer> ans) {
        if (x == null) {
            return;
        }

        process(x.left, ans);
        process(x.right, ans);
        ans.add(x.val);
    }

    public class TreeNode {
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
