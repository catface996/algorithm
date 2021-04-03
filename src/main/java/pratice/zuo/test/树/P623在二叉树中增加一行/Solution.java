package pratice.zuo.test.树.P623在二叉树中增加一行;
//给定一个二叉树，根节点为第1层，深度为 1。在其第 d 层追加一行值为 v 的节点。
//
// 添加规则：给定一个深度值 d （正整数），针对深度为 d-1 层的每一非空节点 N，为 N 创建两个值为 v 的左子树和右子树。
//
// 将 N 原先的左子树，连接为新节点 v 的左子树；将 N 原先的右子树，连接为新节点 v 的右子树。
//
// 如果 d 的值为 1，深度 d - 1 不存在，则创建一个新的根节点 v，原先的整棵树将作为 v 的左子树。
//
// 示例 1:
//
//
//输入:
//二叉树如下所示:
//       4
//     /   \
//    2     6
//   / \   /
//  3   1 5
//
//v = 1
//
//d = 2
//
//输出:
//       4
//      / \
//     1   1
//    /     \
//   2       6
//  / \     /
// 3   1   5
//
//
//
// 示例 2:
//
//
//输入:
//二叉树如下所示:
//      4
//     /
//    2
//   / \
//  3   1
//
//v = 1
//
//d = 3
//
//输出:
//      4
//     /
//    2
//   / \
//  1   1
// /     \
//3       1
//
//
// 注意:
//
//
// 输入的深度值 d 的范围是：[1，二叉树最大深度 + 1]。
// 输入的二叉树至少有一个节点。
//
// Related Topics 树
// 👍 86 👎 0

//leetcode submit region begin(Prohibit modification and deletion)
public class Solution {

    //TODO 需要优化
    int depthParent;
    int v;

    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        this.depthParent = depth - 1;
        this.v = val;
        if (depth == 1) {
            TreeNode newRoot = new TreeNode(val);
            newRoot.left = root;
            return newRoot;
        }
        process(root, 1);
        return root;
    }

    private void process(TreeNode x, int currentDepth) {
        /*
        6:28 下午	info
				解答成功:
				执行耗时:1 ms,击败了87.52% 的Java用户
				内存消耗:38.4 MB,击败了35.60% 的Java用户
         */
        if (x == null) {
            return;
        }

        if (currentDepth == depthParent) {
            x.left = new TreeNode(v, x.left, null);
            x.right = new TreeNode(v, null, x.right);
            return;
        }
        process(x.left, ++currentDepth);
        process(x.right, currentDepth);
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
        //二叉树如下所示:
        //       4
        //     /   \
        //    2     6
        //   / \   /
        //  3   1 5
        //
        //v = 1
        //
        //d = 2
        //
        //输出:
        //       4
        //      / \
        //     1   1
        //    /     \
        //   2       6
        //  / \     /
        // 3   1   5
        public static void main(String[] args) {
            TreeNode root = new TreeNode(4);
            root.left = new TreeNode(2);
            root.right = new TreeNode(6);
            root.left.left = new TreeNode(3);
            root.left.right = new TreeNode(1);
            root.right.left = new TreeNode(5);
            Solution solution = new Solution();
            TreeNode newRoot = solution.addOneRow(root, 1, 2);
            System.out.println(newRoot);
        }
    }

    public static class Test2 {
        //输入:
        //二叉树如下所示:
        //      4
        //     /
        //    2
        //   / \
        //  3   1
        //
        //v = 1
        //
        //d = 3
        //
        //输出:
        //      4
        //     /
        //    2
        //   / \
        //  1   1
        // /     \
        //3       1
        public static void main(String[] args) {
            TreeNode root = new TreeNode(4);
            root.left = new TreeNode(2);
            root.left.left = new TreeNode(3);
            root.left.right = new TreeNode(1);
            Solution solution = new Solution();
            TreeNode newRoot = solution.addOneRow(root, 1, 3);
            System.out.println(newRoot);
        }

    }

    public static class Test3 {
        /*
        		测试用例:[1,2,3,4]
				v=5
				d=4
				期望结果:[1,2,3,4,null,null,null,5,5]
         */
        public static void main(String[] args) {
            TreeNode root = new TreeNode(1);
            root.left = new TreeNode(2);
            root.right = new TreeNode(3);
            root.left.left = new TreeNode(4);
            Solution solution = new Solution();
            TreeNode newRoot = solution.addOneRow(root, 5, 4);
            System.out.println(newRoot);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
