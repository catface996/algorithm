package pratice.leetcode.树.P652寻找重复的子树;

//给定一棵二叉树，返回所有重复的子树。对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
//
// 两棵树重复是指它们具有相同的结构以及相同的结点值。
//
// 示例 1：
//
//         1
//       / \
//      2   3
//     /   / \
//    4   2   4
//       /
//      4
//
//
// 下面是两个重复的子树：
//
//       2
//     /
//    4
//
//
// 和
//
//     4
//
//
// 因此，你需要以列表的形式返回上述重复子树的根结点。
// Related Topics 树
// 👍 255 👎 0

//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode left; TreeNode right; TreeNode() {}
 * TreeNode(int val) { this.val = val; } TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left =
 * left; this.right = right; } }
 */
class Solution3 {

    int t;
    Map<String, Integer> trees;
    Map<Integer, Integer> count;
    List<TreeNode> ans;

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        t = 1;
        trees = new HashMap();
        count = new HashMap();
        ans = new ArrayList();
        lookup(root);
        return ans;
    }

    public int lookup(TreeNode node) {
        if (node == null) { return 0; }
        String serial = node.val + "," + lookup(node.left) + "," + lookup(node.right);
        int uid = trees.computeIfAbsent(serial, x -> t++);
        count.put(uid, count.getOrDefault(uid, 0) + 1);
        if (count.get(uid) == 2) {
            ans.add(node);
        }
        return uid;
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

    public static class Test1 {
        // 示例 1：
        //         1
        //       / \
        //      2   3
        //     /   / \
        //    4   2   4
        //       /
        //      4
        // 下面是两个重复的子树：
        //       2
        //     /
        //    4
        // 和
        //     4
        // 因此，你需要以列表的形式返回上述重复子树的根结点。
        public static void main(String[] args) {
            TreeNode root = new TreeNode(1);
            root.left = new TreeNode(2);
            root.right = new TreeNode(3);
            root.left.left = new TreeNode(4);
            root.right.left = new TreeNode(2);
            root.right.right = new TreeNode(4);
            root.right.left.left = new TreeNode(4);
            Solution3 solution = new Solution3();
            List<TreeNode> ans = solution.findDuplicateSubtrees(root);
            System.out.println(ans);
        }
    }

    public static class Test2 {
        //测试用例:[2,1,3,2,null,1,null,null,null,2,null]
        //期望结果:[[1,2],[2]]
        public static void main(String[] args) {
            TreeNode root = new TreeNode(2);
            root.left = new TreeNode(1);
            root.right = new TreeNode(3);
            root.left.left = new TreeNode(2);
            root.right.left = new TreeNode(1);
            root.right.left.left = new TreeNode(2);
            Solution3 solution = new Solution3();
            List<TreeNode> ans = solution.findDuplicateSubtrees(root);
            System.out.println(ans);
        }
    }

    public static class Test3 {
        // 	测试用例:[0,0,0,0,null,null,0,null,null,null,0]
        //	期望结果:[[0]]
        public static void main(String[] args) {
            TreeNode root = new TreeNode(0);
            root.left = new TreeNode(0);
            root.right = new TreeNode(0);
            root.left.left = new TreeNode(0);
            root.right.right = new TreeNode(0);
            root.right.right.right = new TreeNode(0);
            Solution3 solution = new Solution3();
            List<TreeNode> ans = solution.findDuplicateSubtrees(root);
            System.out.println(ans);
        }
    }

    public static class Test4 {
        public static void main(String[] args) {
            Solution3 solution = new Solution3();
            List<TreeNode> ans = solution.findDuplicateSubtrees(null);
            System.out.println(ans);
        }
    }
}
