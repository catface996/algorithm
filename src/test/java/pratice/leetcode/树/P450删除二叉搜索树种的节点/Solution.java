package pratice.leetcode.树.P450删除二叉搜索树种的节点;

/**
 * @author by catface
 * @date 2021/3/31 5:05 下午
 */
public class Solution {

    //TODO 超级恶心
    public static void main(String[] args) {
        //test1();
        //test2();
        test3();
    }

    public static void test1() {
        Solution solution = new Solution();
        TreeNode root = buildTree();
        TreeNode newRoot = solution.deleteNode(root, 100);
        System.out.println(newRoot);
    }

    public static void test2() {
        Solution solution = new Solution();
        TreeNode root = buildTree2();
        TreeNode newRoot = solution.deleteNode(root, 5);
        System.out.println(newRoot);
    }

    public static void test3() {
        Solution solution = new Solution();
        TreeNode root = buildTree3();
        TreeNode newRoot = solution.deleteNode(root, 50);
        System.out.println(newRoot);
    }

    public static void test4() {

    }

    public static TreeNode buildTree() {
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(6);

        root.right = new TreeNode(12);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(14);
        root.right.right.right = new TreeNode(18);
        root.right.right.right.right = new TreeNode(32);

        return root;
    }

    // 5,3,6,2,4,null,7]
    public static TreeNode buildTree2() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.left = null;
        root.right.right = new TreeNode(7);
        return root;
    }

    // [50,30,70,null,40,60,80]
    public static TreeNode buildTree3() {
        TreeNode root = new TreeNode(50);
        root.left = new TreeNode(30);
        root.right = new TreeNode(70);
        root.left.left = null;
        root.left.right = new TreeNode(40);
        root.right.left = new TreeNode(60);
        root.right.right = new TreeNode(80);
        return root;
    }

    // [8,0,31,null,6,28,45,1,7,25,30,32,49,null,4,null,null,9,26,29,null,null,42,47,null,2,5,null,12,null,27,null,
    // null,41,43,46,48,null,3,null,null,10,19,null,null,33,null,null,44,null,null,null,null,null,null,null,11,18,20,
    // null,37,null,null,null,null,14,null,null,22,36,38,13,15,21,24,34,null,null,39,null,null,null,16,null,null,23,
    // null,null,35,null,40,null,17]
    public static TreeNode buildTree4() {
        Integer[] values = new Integer[] {8, 0, 31, null, 6, 28, 45, 1, 7, 25, 30, 32, 49, null, 4, null, null, 9, 26,
            29, null, null, 42, 47, null, 2, 5, null, 12, null, 27, null, null, 41, 43, 46, 48, null, 3, null, null, 10,
            19, null, null, 33, null, null, 44, null, null, null, null, null, null, null, 11, 18, 20, null, 37, null,
            null, null, null, 14, null, null, 22, 36, 38, 13, 15, 21, 24, 34, null, null, 39, null, null, null, 16,
            null, null, 23, null, null, 35, null, 40, null, 17};

        int key = 1;
        return null;
    }

    //:[2,0,33,null,1,25,40,null,null,11,31,34,45,10,18,29,32,null,36,43,46,4,null,12,24,26,30,null,null,35,39,42,44,
    // null,48,3,9,null,14,22,null,null,27,null,null,null,null,38,null,41,null,null,null,47,49,null,null,5,null,13,
    // 15,21,23,null,28,37,null,null,null,null,null,null,null,null,8,null,null,null,17,19,null,null,null,null,null,
    // null,null,7,null,16,null,null,20,6]
    //
    public static TreeNode buildTree5() {
        Integer[] value = new Integer[] {2, 0, 33, null, 1, 25, 40, null, null, 11, 31, 34, 45, 10, 18, 29, 32, null,
            36, 43, 46, 4, null, 12, 24, 26, 30, null, null, 35, 39, 42, 44, null, 48, 3, 9, null, 14, 22, null, null,
            27, null, null, null, null, 38, null, 41, null, null, null, 47, 49, null, null, 5, null, 13, 15, 21, 23,
            null, 28, 37, null, null, null, null, null, null, null, null, 8, null, null, null, 17, 19, null, null, null,
            null, null, null, null, 7, null, 16, null, null, 20, 6};
        int key = 33;
        return null;
    }

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null && root.val == key) {
            return null;
        }
        Info info = findParentParent(root, key);
        if (info == null || info.keyNode == null) {
            return root;
        }
        ReplaceNodeInfo replaceNodeInfo = getReplaceInfo(info.keyNode);
        TreeNode newRoot = replace(info, replaceNodeInfo);
        if (newRoot != null) {
            return newRoot;
        }
        return root;
    }

    private Info findParentParent(TreeNode x, int key) {
        if (x == null) {
            return null;
        }
        if (x.val == key) {
            return new Info(x, null);
        }
        if (x.left != null && x.left.val == key) {
            return new Info(x.left, x);
        }
        if (x.right != null && x.right.val == key) {
            return new Info(x.right, x);
        }
        if (x.val > key) {
            return findParentParent(x.left, key);
        }
        return findParentParent(x.right, key);
    }

    public TreeNode replace(Info info, ReplaceNodeInfo replaceInfo) {

        // 1. 移除的时叶子节点
        if (info.keyNode.left == null && info.keyNode.right == null) {
            if (info.parent.left == info.keyNode) {
                info.parent.left = null;
            }
            if (info.parent.right == info.keyNode) {
                info.parent.right = null;
            }
            return null;
        }

        // 当前步骤优先
        // 将用于填充的节点从父节点上减掉,并将父亲节点直接指向用于填充节点的子节点
        if (replaceInfo.cutNodeParent != null) {
            TreeNode cutNodeChild = replaceInfo.cutNode.left != null ? replaceInfo.cutNode.left
                : replaceInfo.cutNode.right;
            if (replaceInfo.cutNodeParent.left == replaceInfo.cutNode) {
                replaceInfo.cutNodeParent.left = cutNodeChild;
            }
            if (replaceInfo.cutNodeParent.right == replaceInfo.cutNode) {
                replaceInfo.cutNodeParent.right = cutNodeChild;
            }
        }

        // 移除的节点和替换移除节点的节点 是 父子关系,不做任何操作
        // 不是父子关系,需将填充节点指向被替换节点的左右孩子
        if (replaceInfo.cutNodeParent != null) {
            replaceInfo.cutNode.left = info.keyNode.left;
            replaceInfo.cutNode.right = info.keyNode.right;
        } else {
            if (info.keyNode.right == replaceInfo.cutNode) {
                replaceInfo.cutNode.left = info.keyNode.left;
            }
            if (info.keyNode.left == replaceInfo.cutNode) {
                replaceInfo.cutNode.right = info.keyNode.right;
            }
        }

        // 如果移除的不是父节点,需要将被移除节点的父节点指向新填充的节点
        // 否则新填充的节点作为根节点返回
        if (info.parent != null) {
            if (info.parent.left == info.keyNode) {
                // 如果移除的节点是父节点的左孩子
                info.parent.left = replaceInfo.cutNode;
            }
            if (info.parent.right == info.keyNode) {
                // 如果移除的节点是父节点的右孩子
                info.parent.right = replaceInfo.cutNode;
            }
            return null;
        } else {
            return replaceInfo.cutNode;
        }

    }

    private ReplaceNodeInfo getReplaceInfo(TreeNode x) {
        if (x.left != null) {
            return getMax(x.left);
        }
        if (x.right != null) {
            return getMin(x.right);
        }
        return new ReplaceNodeInfo(null, null);
    }

    public ReplaceNodeInfo getMax(TreeNode x) {
        if (x.right == null) {
            return new ReplaceNodeInfo(x, null);
        }
        TreeNode currentNode = x;
        while (currentNode.right != null && currentNode.right.right != null) {
            currentNode = currentNode.right;
        }
        return new ReplaceNodeInfo(currentNode.right, currentNode);
    }

    public ReplaceNodeInfo getMin(TreeNode x) {
        if (x.left == null) {
            return new ReplaceNodeInfo(x, null);
        }
        TreeNode currentNode = x;
        while (currentNode.left != null && currentNode.left.left != null) {
            currentNode = currentNode.left;
        }
        return new ReplaceNodeInfo(currentNode.left, currentNode);
    }

    public static class ReplaceNodeInfo {
        TreeNode cutNode;
        TreeNode cutNodeParent;

        public ReplaceNodeInfo(TreeNode cutNode, TreeNode cutNodeParent) {
            this.cutNode = cutNode;
            this.cutNodeParent = cutNodeParent;
        }
    }

    public static class Info {
        TreeNode keyNode;
        TreeNode parent;

        public Info(TreeNode keyNode, TreeNode parent) {
            this.keyNode = keyNode;
            this.parent = parent;
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
