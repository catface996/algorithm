package pratice.zuo.test.树.P144二叉树的前序遍历;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by catface
 * @date 2021/3/28 6:22 下午
 */
public class Solution {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        process(root, ans);
        return ans;
    }

    private void process(TreeNode x, List<Integer> ans) {
        if (x == null) {
            return;
        }
        ans.add(x.val);

        process(x.left, ans);

        process(x.right, ans);
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
