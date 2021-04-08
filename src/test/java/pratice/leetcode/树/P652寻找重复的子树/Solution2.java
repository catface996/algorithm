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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Definition for a binary tree node. public class TreeNode { int val; TreeNode left; TreeNode right; TreeNode() {}
 * TreeNode(int val) { this.val = val; } TreeNode(int val, TreeNode left, TreeNode right) { this.val = val; this.left =
 * left; this.right = right; } }
 */
class Solution2 {

    /*
    5:00 下午	info
				解答成功:
				执行耗时:40 ms,击败了7.66% 的Java用户
				内存消耗:40.1 MB,击败了97.68% 的Java用户
     */
    Map<String, List<TreeNode>> sameNodeNumMaxWidthMaxHeight = new HashMap<>();
    List<TreeNode> ans = new ArrayList<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        calculateSubTreeInfo(root);
        for (List<TreeNode> treeNodeList : sameNodeNumMaxWidthMaxHeight.values()) {
            while (treeNodeList.size() > 1) {
                Iterator<TreeNode> treeNodeIt = treeNodeList.iterator();
                TreeNode currentNode = treeNodeIt.next();
                treeNodeIt.remove();
                boolean findSame = false;
                while (treeNodeIt.hasNext()) {
                    TreeNode nextTreeNode = treeNodeIt.next();
                    boolean sameTree = compare(currentNode, nextTreeNode);
                    if (sameTree) {
                        // 第一次发现,添加到结果集合中
                        if (!findSame) {
                            ans.add(currentNode);
                        }
                        findSame = true;
                        treeNodeIt.remove();
                    }
                }
            }
        }
        return ans;
    }

    private Info calculateSubTreeInfo(TreeNode x) {
        if (x == null) {
            return new Info(null, 0, 0, 0);
        }
        Info leftInfo = calculateSubTreeInfo(x.left);
        Info rightInfo = calculateSubTreeInfo(x.right);
        int width = leftInfo.width + rightInfo.width + 1;
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int nodeNum = leftInfo.nodeNum + rightInfo.nodeNum + 1;
        String key = nodeNum + ":" + height + ":" + width;
        List<TreeNode> nodeList = sameNodeNumMaxWidthMaxHeight.getOrDefault(key, new ArrayList<>());
        nodeList.add(x);
        sameNodeNumMaxWidthMaxHeight.put(key, nodeList);
        return new Info(x, height, width, nodeNum);
    }

    private boolean compare(TreeNode xTreeRoot, TreeNode subTreeRoot) {

        if (xTreeRoot == null && subTreeRoot != null || xTreeRoot != null && subTreeRoot == null) {
            return false;
        }

        if (xTreeRoot == null) {
            return true;
        }

        boolean leftSame = compare(xTreeRoot.left, subTreeRoot.left);
        if (!leftSame) {
            return false;
        }
        boolean rightSame = compare(xTreeRoot.right, subTreeRoot.right);
        if (!rightSame) {
            return false;
        }
        // 比较到了同一棵树,直接返回false
        if (xTreeRoot == subTreeRoot) {
            return false;
        }
        return xTreeRoot.val == subTreeRoot.val;
    }

    public static class Info {
        TreeNode subTreeRoot;
        int height;
        int width;
        int nodeNum;

        public Info(TreeNode subTreeRoot, int height, int width, int nodeNum) {
            this.subTreeRoot = subTreeRoot;
            this.height = height;
            this.width = width;
            this.nodeNum = nodeNum;
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
            Solution2 solution = new Solution2();
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
            Solution2 solution = new Solution2();
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
            Solution2 solution = new Solution2();
            List<TreeNode> ans = solution.findDuplicateSubtrees(root);
            System.out.println(ans);
        }
    }

    public static class Test4 {
        public static void main(String[] args) {
            Solution2 solution = new Solution2();
            List<TreeNode> ans = solution.findDuplicateSubtrees(null);
            System.out.println(ans);
        }
    }

    public static class Test5 {
        public static void main(String[] args) {
            List<Integer> numberList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                numberList.add(i);
            }
            Iterator<Integer> intIt = numberList.iterator();
            while (intIt.hasNext()) {
                int number = intIt.next();
                if (number == 5) {
                    intIt.remove();
                }
            }
        }
    }
}
