package pratice.zuo.test.树.P606根据二叉树创建字符串;
//你需要采用前序遍历的方式，将一个二叉树转换成一个由括号和整数组成的字符串。
//
// 空节点则用一对空括号 "()" 表示。而且你需要省略所有不影响字符串与原始二叉树之间的一对一映射关系的空括号对。
//
// 示例 1:
//
//
//输入: 二叉树: [1,2,3,4]
//       1
//     /   \
//    2     3
//   /
//  4
//
//输出: "1(2(4))(3)"
//
//解释: 原本将是“1(2(4)())(3())”，
//在你省略所有不必要的空括号对之后，
//它将是“1(2(4))(3)”。
//
//
// 示例 2:
//
//
//输入: 二叉树: [1,2,3,null,4]
//       1
//     /   \
//    2     3
//     \
//      4
//
//输出: "1(2()(4))(3)"
//
//解释: 和第一个示例相似，
//除了我们不能省略第一个对括号来中断输入和输出之间的一对一映射关系。
//
// Related Topics 树 字符串
// 👍 187 👎 0

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * @author by catface
 * @date 2021/4/3 10:37 上午
 */
class Solution2 {
    StringBuilder sb = new StringBuilder();

    public String tree2str(TreeNode t) {
        if (t == null) {
            return "";
        }
        process(t);
        return sb.toString();
    }

    private void process(TreeNode x) {
        /*
         4:04 下午	info
				解答成功:
				执行耗时:2 ms,击败了94.61% 的Java用户
				内存消耗:38 MB,击败了98.18% 的Java用户
         */
        sb.append(x.val);

        if (x.left != null && x.right != null) {
            sb.append('(');
            process(x.left);
            sb.append(')').append('(');
            process(x.right);
            sb.append(')');
            return;
        }

        if (x.left == null && x.right == null) {
            return;
        }

        if (x.left != null && x.right == null) {
            sb.append('(');
            process(x.left);
            sb.append(')');
        }

        if (x.left == null && x.right != null) {
            sb.append('(').append(')').append('(');
            process(x.right);
            sb.append(')');
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) { val = x; }
    }

    public static class Test1 {
        public static void main(String[] args) {
            // [1,2,3,4]
            TreeNode root = new TreeNode(1);
            root.left = new TreeNode(2);
            root.right = new TreeNode(3);
            root.left.left = new TreeNode(4);
            Solution2 solution = new Solution2();
            String str = solution.tree2str(root);
            System.out.println(str);
        }
    }

    public static class Test2 {
        public static void main(String[] args) {
            // [1,2,3,null,4]
            TreeNode root = new TreeNode(1);
            root.left = new TreeNode(2);
            root.right = new TreeNode(3);
            root.left.left = null;
            root.left.right = new TreeNode(4);
            Solution2 solution = new Solution2();
            String str = solution.tree2str(root);
            System.out.println(str);
        }
    }

    public static class Test3 {
        public static void main(String[] args) {
            int a = '(';
            int b = ')';
            System.out.println(a);
            System.out.println(b);
        }
    }
}
