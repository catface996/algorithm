package util;

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
    public static void printTree(TreeNode root) {
        if (root == null) {
            System.out.println("空树:[]");
            return;
        }

        char[][] bitmap = initTreeBitmap(root);
        process(root, 0, bitmap[0].length, 0, bitmap);
        printSplitLine(bitmap[0].length);
        System.out.println("节点数:");
        System.out.println("最大深度:");
        System.out.println("最大宽度:");
        System.out.println("叶子节点数:");
        System.out.println("是否是搜索二叉树(BST):");
        System.out.println("是否是平衡二叉树(BT)");
        System.out.println("是否是完全二叉树(CBT):");
        System.out.println("");
        printBitmap(bitmap);
    }

    /**
     * 初始化二叉树位图矩阵
     *
     * @param x 树种的某个节点
     * @return 二维矩阵
     */
    private static char[][] initTreeBitmap(TreeNode x) {
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
    private static int getMaxDepth(TreeNode x) {
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
    private static void process(TreeNode x, int start, int end, int depth, char[][] bitmap) {
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

}
