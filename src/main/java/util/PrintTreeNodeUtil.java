package util;

import lombok.extern.slf4j.Slf4j;

/**
 * @author by catface
 * @date 2021/4/8 5:28 下午
 */
@Slf4j
public class PrintTreeNodeUtil {

    private static int getMaxDepth(TreeNode x, int depth) {
        if (x == null) {
            return depth;
        }
        int leftDepth = getMaxDepth(x.left, depth + 1);
        int rightDepth = getMaxDepth(x.right, depth + 1);
        return Math.max(leftDepth, rightDepth);
    }

    private static char[][] initTreeBitmap(TreeNode x) {
        int nodeWidth = 2;
        int depth = getMaxDepth(x, 0);
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

    private static void printBitmap(char[][] bitmap) {
        for (char[] chars : bitmap) {
            for (char aChar : chars) {
                System.out.print(aChar);
            }
            System.out.println("");
        }
    }

    public static void printTree(TreeNode root) {
        char[][] bitmap = initTreeBitmap(root);
        process(root, 0, bitmap[0].length, 0, bitmap);
        printBitmap(bitmap);
    }

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
        for (int i = 0; i < context.length; i++) {
            bitmap[row][middle + i - halfContextLength] = context[i];
        }
        process(x.left, start, middle, depth + 1, bitmap);
        process(x.right, middle, end, depth + 1, bitmap);
    }

}
