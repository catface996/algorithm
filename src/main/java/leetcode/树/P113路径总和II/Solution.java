package leetcode.树.P113路径总和II;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author by catface
 * @date 2021/3/29 3:50 下午
 */
public class Solution {

    //TODO 4:31 下午	info
    //				解答成功:
    //				执行耗时:3 ms,击败了15.80% 的Java用户
    //				内存消耗:38.9 MB,击败了42.38% 的Java用户

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<LinkNode> ans = new ArrayList<>();
        process(new LinkNode(null, root), targetSum, 0, ans);
        List<List<Integer>> listList = new ArrayList<>();
        for (LinkNode an : ans) {
            Stack<Integer> stack = new Stack<>();
            while (an != null) {
                stack.push(an.value.val);
                an = an.parent;
            }
            ;
            List<Integer> list = new ArrayList<>();
            while (!stack.isEmpty()) {
                list.add(stack.pop());
            }
            listList.add(list);
        }
        return listList;
    }

    public void process(LinkNode node, int targetSum, int preSum, List<LinkNode> ans) {
        if (node.value == null) {
            return;
        }
        int sum = preSum + node.value.val;
        if (sum == targetSum && node.value.left == null && node.value.right == null) {
            ans.add(node);
        }
        process(new LinkNode(node, node.value.left), targetSum, sum, ans);
        process(new LinkNode(node, node.value.right), targetSum, sum, ans);
    }

    public static class LinkNode {
        LinkNode parent;
        TreeNode value;

        public LinkNode(LinkNode parent, TreeNode value) {
            this.parent = parent;
            this.value = value;
        }
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
