package util;

import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

import lombok.extern.slf4j.Slf4j;

/**
 * @author by catface
 * @date 2021/4/8 5:28 下午
 */
@Slf4j
public class PrintTreeNodeUtil {

    /**
     * 打印二叉树
     *
     * @param root 二叉树根节点
     */
    public static void printTree(util.TreeNode root) {
        if (root == null) {
            System.out.println("空树:[]");
            return;
        }

        char[][] bitmap = initTreeBitmap(root);
        process(root, 0, bitmap[0].length, 0, bitmap);
        printSplitLine(bitmap[0].length);
        int nodeNum = getNodeNum(root);
        int height = getHeight(root);
        int maxWidth = widthOfBinaryTree(root);
        int terminalNodeNum = getTerminalNodeNum(root);
        boolean isBst = isBst(root);
        boolean isBt = isBt(root);
        boolean isCbt = isCbt(root);
        System.out.println("节点数: " + nodeNum);
        System.out.println("高度: " + height);
        System.out.println("最大宽度: " + maxWidth);
        System.out.println("叶子节点数: " + terminalNodeNum);
        System.out.println("搜索二叉树(BST): " + isBst);
        System.out.println("平衡二叉树(BT): " + isBt);
        System.out.println("完全二叉树(CBT): " + isCbt);
        System.out.println("");
        printBitmap(bitmap);
        printSplitLine(bitmap[0].length);
    }

    /**
     * 初始化二叉树位图矩阵
     *
     * @param x 树种的某个节点
     * @return 二维矩阵
     */
    private static char[][] initTreeBitmap(util.TreeNode x) {
        int nodeWidth = 2;
        int depth = getMaxDepth(x);
        int row = depth * 2;
        int col = (int)Math.pow(2, depth) * nodeWidth;
        char[][] bitmap = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                bitmap[i][j] = ' ';
            }
        }
        return bitmap;
    }

    /**
     * 获得树的最大深度
     *
     * @param x 根节点
     * @return 最大深度
     */
    private static int getMaxDepth(util.TreeNode x) {
        if (x == null) {
            return 0;
        }
        int leftDepth = getMaxDepth(x.left);
        int rightDepth = getMaxDepth(x.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    /**
     * 在二叉树矩阵中,递归设置每个节点的内容
     *
     * @param x      待处理节点
     * @param start  在矩阵中开始书写的起始列
     * @param end    在矩阵中结束书写的终止列
     * @param depth  当前处理的深度
     * @param bitmap 表示二叉树位图的二维矩阵
     */
    private static void process(util.TreeNode x, int start, int end, int depth, char[][] bitmap) {
        if (x == null) {
            return;
        }
        int middle = (start + end) / 2;
        int row = depth * 2;
        int sideStart = (start + middle) / 2;
        int sideEnd = (middle + end) / 2;

        char[] context = String.valueOf(x.val).toCharArray();
        int halfContextLength = context.length / 2;

        bitmap[row][sideEnd] = ' ';
        if (x.left != null) {
            for (int i = sideStart; i < middle - halfContextLength; i++) {
                bitmap[row][i] = '_';
            }
            bitmap[row + 1][sideStart] = '|';
        }
        if (x.right != null) {
            for (int i = middle + halfContextLength; i < sideEnd; i++) {
                bitmap[row][i] = '_';
            }
            bitmap[row + 1][sideEnd] = '|';
        }
        System.arraycopy(context, 0, bitmap[row], middle - halfContextLength, context.length);
        process(x.left, start, middle, depth + 1, bitmap);
        process(x.right, middle, end, depth + 1, bitmap);
    }

    /**
     * 将二叉树的二维矩阵输出到控制台
     *
     * @param bitmap 二维矩阵
     */
    private static void printBitmap(char[][] bitmap) {
        for (char[] chars : bitmap) {
            for (char aChar : chars) {
                System.out.print(aChar);
            }
            System.out.println("");
        }
    }

    /**
     * 打印分割线
     *
     * @param length 分割线长度
     */
    private static void printSplitLine(int length) {
        length = Math.max(length, 50);
        for (int i = 0; i < length; i++) {
            System.out.print('*');
        }
        System.out.println("");
    }

    /**
     * 获取二叉树的节点个数
     *
     * @param x 二叉树节点
     * @return 节点个数
     */
    private static int getNodeNum(util.TreeNode x) {
        if (x == null) {
            return 0;
        }
        int leftNodeNum = getNodeNum(x.left);
        int rightNodeNum = getNodeNum(x.right);
        return leftNodeNum + rightNodeNum + 1;
    }

    /**
     * 获取二叉树的高度
     *
     * @param x 二叉树节点
     * @return 二叉树高度
     */
    private static int getHeight(util.TreeNode x) {
        if (x == null) {
            return 0;
        }
        int leftHeight = getHeight(x.left);
        int rightHeight = getHeight(x.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * 二叉树求宽度（求每层左右两个节点相差最大是多少时，空节点也算）leetcode662
     */
    public static int widthOfBinaryTree(util.TreeNode root) {
        //两个map分别存了当前层数最左边节点编号和最右边节点编号，key是层数，value是编号
        Map<Integer, Integer> rightMap = new TreeMap<>();
        // key 层数 value 编号
        Map<Integer, Integer> leftMap = new TreeMap<>();
        dfs(root, 1, 1, leftMap, rightMap);
        int max = 0;
        for (int key : rightMap.keySet()) {
            max = Math.max(max, rightMap.get(key) - leftMap.get(key) + 1);
        }
        return max;
    }

    /**
     * @param num    编号
     * @param layers 层数
     */
    public static void dfs(util.TreeNode root, int num, int layers, Map<Integer, Integer> leftMap,
                           Map<Integer, Integer> rightMap) {
        if (root == null) {
            return;
        }
        if (!leftMap.containsKey(layers)) {
            //因为每层首先到达的是本层最左边节点，所以只需要判断最左边节点第一次到达就几率编号即可
            leftMap.put(layers, num);
        }
        if (!rightMap.containsKey(layers) || rightMap.get(layers) < num) {
            rightMap.put(layers, num);
        }
        dfs(root.left, 2 * num, layers + 1, leftMap, rightMap);
        dfs(root.right, 2 * num + 1, layers + 1, leftMap, rightMap);
    }

    /**
     * 获取叶子节点数量
     *
     * @param x 二叉树节点
     * @return 叶子节点数量
     */
    private static int getTerminalNodeNum(util.TreeNode x) {
        if (x == null) {
            return 0;
        }
        if (x.left == null && x.right == null) {
            return 1;
        }
        int leftTerminalNum = getTerminalNodeNum(x.left);
        int rightTerminalNum = getTerminalNodeNum(x.right);
        return leftTerminalNum + rightTerminalNum;
    }

    /**
     * 判断一棵树是否是搜索二叉树
     *
     * @param root 二叉树根节点
     * @return true:是搜索二叉树;false:不是搜索二叉树
     */
    private static boolean isBst(util.TreeNode root) {
        if (root == null) {
            return true;
        }
        return processIsBst(root).isBst;
    }

    /**
     * 判断一棵树是否是搜索二叉树
     *
     * @param x 二叉树节点
     * @return true:是搜索二叉树;false:不是搜索二叉树
     */
    private static BstInfo processIsBst(util.TreeNode x) {

        if (x == null) {
            return null;
        }

        // 左树不是搜索树,整棵树不是搜索树,无需计算最大最小值,直接返回
        BstInfo leftInfo = processIsBst(x.left);
        if (leftInfo != null && !leftInfo.isBst) {
            return leftInfo;
        }

        // 右树不是搜索树,整棵树不是搜索树,无需计算最大最小值,直接返回
        BstInfo rightInfo = processIsBst(x.right);
        if (rightInfo != null && !rightInfo.isBst) {
            return rightInfo;
        }

        // 当前节点值,如果不大于左树的最大值,不是搜索树,无需计算最大最小值供parent决策
        if (leftInfo != null) {
            boolean gtLeft = x.val >= leftInfo.maxValue;
            if (!gtLeft) {
                return new BstInfo(0, 0, false);
            }
        }

        // 当前节点值,如果不小于右树的最小值,不是搜索树,无需计算最大最小值供parent决策
        if (rightInfo != null) {
            boolean ltRight = x.val <= rightInfo.minValue;
            if (!ltRight) {
                return new BstInfo(0, 0, false);
            }
        }

        int minValue = x.val;
        int maxValue = x.val;
        if (leftInfo != null) {
            // 此处无需比较最大值是否是左树的最大值,如果x小于左树的最大值,已经在之前返回,不会执行到当前代码
            minValue = Math.min(minValue, leftInfo.minValue);
        }

        if (rightInfo != null) {
            minValue = Math.min(minValue, rightInfo.minValue);
            maxValue = Math.max(maxValue, rightInfo.maxValue);
        }

        return new BstInfo(maxValue, minValue, true);

    }

    /**
     * 判断一棵树是否是平衡树
     *
     * @param root 根节点
     * @return true:是平衡树;false:不是平衡树
     */
    private static boolean isBt(util.TreeNode root) {
        return processIsBt(root).isBt;
    }

    /**
     * 递归判断是否是平衡树
     *
     * @param x 当前处理节点
     * @return 返回个parent的信息, 包括子树是否是平衡树以及子树的高度
     * <p>
     * 如果子树不是平衡树,高度将失去意义,可用任意值填充
     */
    private static BtInfo processIsBt(util.TreeNode x) {
        if (x == null) {
            return new BtInfo(0, true);
        }
        // 如果左子树不是平衡树,整棵树不是平衡树,直接返回,无需计算高度供parent决策
        BtInfo leftInfo = processIsBt(x.left);
        if (!leftInfo.isBt) {
            return leftInfo;
        }
        // 如果右子树不是平衡树,整棵树不是平衡树,直接返回,无需计算高度供parent决策
        BtInfo rightInfo = processIsBt(x.right);
        if (!rightInfo.isBt) {
            return rightInfo;
        }
        // 如果左右两棵子树的高度差小于等于1,是平衡树,否则不是,无需计算高度,直接返回
        boolean isBt = Math.abs(leftInfo.height - rightInfo.height) <= 1;
        if (!isBt) {
            return new BtInfo(0, false);
        }
        // 计算当前子树的高度,返回给parent
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        return new BtInfo(height, true);
    }

    /**
     * 判断是否是完全二叉树
     * <p>
     * 如果之前已经遇到过左右子树不双全的节点,并且又发现后续节点有非叶子节点,则不完全
     *
     * @param root 二叉树根节点
     * @return true:是完全二叉树;false:不是完全二叉树
     */
    private static boolean isCbt(TreeNode root) {
        if (root == null) {
            return true;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        // 是否遇到过左右两个孩子不双全的节点
        boolean leaf = false;
        TreeNode l;
        TreeNode r;
        queue.add(root);
        while (!queue.isEmpty()) {
            root = queue.poll();
            l = root.left;
            r = root.right;
            if (
                // 如果遇到了不双全的节点之后，又发现当前节点不是叶节点
                (leaf && (l != null || r != null))
                    || (l == null && r != null)
            ) {
                return false;
            }
            if (l != null) {
                queue.add(l);
            }
            if (r != null) {
                queue.add(r);
            }
            if (l == null || r == null) {
                leaf = true;
            }
        }
        return true;
    }

    /**
     * 提供给parent节点,用于判断以parent节点为根的二叉树是否是平衡树
     */
    private static class BtInfo {
        /**
         * 子树高度
         */
        int height;
        /**
         * 子树是否是平衡树,true:是,false:不是
         */
        boolean isBt;

        public BtInfo(int height, boolean isBt) {
            this.height = height;
            this.isBt = isBt;
        }
    }

    /**
     * 是否是搜索二叉树,需要子树返回的信息
     */
    private static class BstInfo {
        /**
         * 子树的最大值
         */
        int maxValue;
        /**
         * 子树的最小值
         */
        int minValue;
        /**
         * 子树是否是搜索树
         */
        boolean isBst;

        public BstInfo(int maxValue, int minValue, boolean isBst) {
            this.maxValue = maxValue;
            this.minValue = minValue;
            this.isBst = isBst;
        }
    }
}
